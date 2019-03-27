package android.com.meetU.meetup_like.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.com.meetU.meetup_like.model.MeetupLikeService;
import android.com.meetU.meetup_like.model.MeetupLikeVO;


public class android_MeetupLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		MeetupLikeService meetuplikeSvc = new MeetupLikeService();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		if(action.equals("getAll")) {
			String mem_ID = jsonObject.get("mem_ID").getAsString();
			List<MeetupLikeVO> meetupList = meetuplikeSvc.getAll(mem_ID);
			writeText(res, gson.toJson(meetupList));
		} else if(action.equals("add")) {
			String meetup_ID = jsonObject.get("meetup_ID").getAsString();
			String mem_ID = jsonObject.get("mem_ID").getAsString();
			writeText(res,String.valueOf(meetuplikeSvc.addMeetupLike(meetup_ID, mem_ID)));
			
		} else if(action.equals("delete")) {
			String meetup_ID = jsonObject.get("meetup_ID").getAsString();
			String mem_ID = jsonObject.get("mem_ID").getAsString();
			writeText(res,String.valueOf(meetuplikeSvc.deleteMeetupLike(meetup_ID, mem_ID)));
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
