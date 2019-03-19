package com.meetU.live_chat.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.meetU.live_chat.model.Live_chatService;
import com.meetU.live_chat.model.Live_chatVO;
import com.meetU.mem.model.MemService;
import com.meetU.stick.model.StickService;

@ServerEndpoint("/live_chatHome/{myName}")
public class live_chatHome {

	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
private static final Map<Session, String> map = Collections.synchronizedMap(new HashMap<>());
	
	@OnOpen
	public void onOpen(@PathParam("myName") String myName, Session userSession) throws IOException {
		allSessions.add(userSession);
		map.put(userSession, myName);
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(myName + ": 已連線");
		
//		Message received: {"mem_ID":"M000001","userName":"湯姆","message":"123","action":"text","host_ID":"M000001"}
//		Message received: {"action":"stick","stick_ID":"ST000004","userName":"湯姆","mem_ID":"M000001","host_ID":"M000001"}
		
		Live_chatService live_chatSvc = new Live_chatService();
		 MemService memSvc = new MemService();
		
		List<Live_chatVO> result =  live_chatSvc.getOneLive_chat(myName);
//		for (Session session : allSessions) {
			if (userSession.isOpen() && map.get(userSession).equals(myName)) {
				for (Live_chatVO live_chatVO : result ) {
					if ("stick".equals(live_chatVO.getChat_type())) {
						JSONObject jObject=new JSONObject();
						
						Base64.Encoder encoder = Base64.getEncoder();
						StickService sSvc = new StickService(); 
						byte[] stick =sSvc.getOneStick(live_chatVO.getChat_cont()).getSticker();
						String encodeText = encoder.encodeToString(stick);
						jObject.put("stick", encodeText);
						
						
						jObject.put("action","stick");
//						jObject.put("stick_ID",live_chatVO.getChat_cont()); 
						jObject.put("userName",memSvc.getOneMem(live_chatVO.getMem_ID()).getMem_name()); 
						jObject.put("mem_ID",live_chatVO.getMem_ID()); 
						jObject.put("host_ID",live_chatVO.getHost_ID());
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						String formattedDate = sdf.format(new Date(live_chatVO.getChat_date().getTime()));
						jObject.put("chat_date",formattedDate); 
						
						
						
						userSession.getAsyncRemote().sendText(jObject.toString());
					} else {
						JSONObject jObject=new JSONObject();
						jObject.put("mem_ID",live_chatVO.getMem_ID()); 
						jObject.put("userName",memSvc.getOneMem(live_chatVO.getMem_ID()).getMem_name()); 
						jObject.put("message",live_chatVO.getChat_cont()); 
						jObject.put("action","text");
						jObject.put("host_ID",live_chatVO.getHost_ID());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						String formattedDate = sdf.format(new Date(live_chatVO.getChat_date().getTime()));
						jObject.put("chat_date",formattedDate); 
						userSession.getAsyncRemote().sendText(jObject.toString());


						
//						session.getAsyncRemote().sendText();
					}
				}
			}
//		}
		
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	
	@OnMessage
	public void onMessage(@PathParam("myName") String myName, Session userSession, String message) {
		
		JSONObject js = null;
		js = new JSONObject(message);
		Live_chatService live_chatSvc = new Live_chatService();
		Date today = new Date();
		String action = js.getString("action");
		Timestamp chat_date = new Timestamp(today.getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String formattedDate = sdf.format(new Timestamp(today.getTime()));
		js.accumulate("chat_date",formattedDate);
		
		if("stick".equals(action)) {
			Base64.Encoder encoder = Base64.getEncoder();
			StickService sSvc = new StickService(); 
			String stick_ID = js.getString("stick_ID");
			byte[] stick =sSvc.getOneStick(stick_ID).getSticker();
			String encodeText = encoder.encodeToString(stick);
			js.accumulate("stick", encodeText);
		}
		
		for (Session session : allSessions) {
			if (session.isOpen() && map.get(session).equals(myName)) {
				session.getAsyncRemote().sendText(js.toString());
//--------------------------------------------------------------松松改------------------------------
//				String action = js.getString("action");
//				if("stick".equals(action)) {
					
//					Base64.Encoder encoder = Base64.getEncoder();
//					StickService sSvc = new StickService(); 
//					String stick_ID = js.getString("stick_ID");
//					byte[] stick =sSvc.getOneStick(stick_ID).getSticker();
//					String encodeText = encoder.encodeToString(stick);
//					js.accumulate("stick", encodeText);
//					session.getAsyncRemote().sendText(js.toString());
//-----------------------------------------------------------------------------------------------
//				}else {
//				session.getAsyncRemote().sendText(js.toString());
//				}				
			}
		}
		
		//以下存資料
//		String action = js.getString("action");
		String host_ID =  js.get("host_ID").toString();
		String mem_ID =  js.get("mem_ID").toString();
		String chat_cont;
//		Timestamp chat_date = new Timestamp(today.getTime());
		String chat_type;
		if("stick".equals(action)) {
		chat_cont =  js.get("stick_ID").toString();
		chat_type = "stick";
		}else {
		chat_cont =  js.get("message").toString();
		chat_type = "text";
		}
		live_chatSvc.addLive_chat(mem_ID, host_ID, chat_cont,chat_date, chat_type);
		System.out.println("Message received: " + message);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}
}
