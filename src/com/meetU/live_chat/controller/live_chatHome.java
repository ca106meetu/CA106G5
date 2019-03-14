package com.meetU.live_chat.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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

import com.google.gson.JsonObject;
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
		
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	
	@OnMessage
	public void onMessage(@PathParam("myName") String myName, Session userSession, String message) {
		JSONObject js = null;
		for (Session session : allSessions) {
			if (session.isOpen() && map.get(session).equals(myName)) {
//--------------------------------------------------------------松松改------------------------------
				js = new JSONObject(message);
				String action = js.getString("action");
				if("stick".equals(action)) {
					Base64.Encoder encoder = Base64.getEncoder();
					StickService sSvc = new StickService(); 
					String stick_ID = js.getString("stick_ID");
					byte[] stick =sSvc.getOneStick(stick_ID).getSticker();
					String encodeText = encoder.encodeToString(stick);
					js.accumulate("stick", encodeText);
					session.getAsyncRemote().sendText(js.toString());
//-----------------------------------------------------------------------------------------------
				}else {
				session.getAsyncRemote().sendText(message);
				}				
			}
		}
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
