package android.com.meetU.friend.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.com.meetU.friend.model.*;
import android.com.meetU.mem.model.MemService;
import android.com.meetU.mem.model.MemVO;


public class android_FriendServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
       
    
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		FriendService fndSvc = new FriendService();
		MemService memSvc = new MemService();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		if (action.equals("getPartOfOneFriend")) {
			String mem_ID = jsonObject.get("mem_ID").getAsString();
			List<FriendVO> fndList = fndSvc.getPartOfOneFriend(mem_ID);
			writeText(res,gson.toJson(fndList));
		} else if(action.equals("getPair")){
			String mem_ID = jsonObject.get("mem_ID").getAsString();
			List<FriendVO> fndList = fndSvc.getPartOfOneFriend(mem_ID);
			List<MemVO> memList = memSvc.getAll();
			
			for(int i = 0;i < fndList.size();i++) {
				for(int j = 0; j <  memList.size(); j++) {
					if(memList.get(j).getMem_ID().equals(fndList.get(i).getFriend_mem_ID())) {
						memList.remove(j);
					} 
				}
			}
			
			for(int i = 0;i<memList.size();i++) {
				if(memList.get(i).getMem_ID().equals(mem_ID)) {
					memList.remove(i);
				}
			}
			
			int random = (int)(Math.random()*memList.size());
			System.out.println("ListSize:"+memList.size());
			MemVO pair = memList.get(random);
			System.out.println("random:"+random);
			writeText(res,gson.toJson(pair));
		} else if(action.equals("add")) {
			String mem_ID = jsonObject.get("mem_ID").getAsString();
			String friend_ID = jsonObject.get("friend_ID").getAsString();
			if(fndSvc.addFriend(mem_ID, friend_ID, 1, 10)) {
				if(fndSvc.addFriend(friend_ID, mem_ID, 1, 10)) {
					writeText(res,"true");
				} else {
					writeText(res,"false");
				}
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}
    private void writeText(HttpServletResponse res,String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

}
