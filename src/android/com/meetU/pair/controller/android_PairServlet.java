package android.com.meetU.pair.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.com.meetU.friend.*;
import android.com.meetU.friend.model.FriendService;
import android.com.meetU.friend.model.FriendVO;
import android.com.meetU.mem.model.MemService;

public class android_PairServlet extends HttpServlet {

    public android_PairServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
			System.out.println("input: " + jsonIn);
			JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
			String action = jsonObject.get("action").getAsString();
			FriendService friendSvc = new FriendService();
			MemService memSvc = new MemService();
			if(action.equals("getPair")) {
				String mem_ID = jsonObject.get("mem_ID").getAsString();
				List<FriendVO> fndList = friendSvc.getPartOfOneFriend(mem_ID); 
				
			}
		}
		
	

}
