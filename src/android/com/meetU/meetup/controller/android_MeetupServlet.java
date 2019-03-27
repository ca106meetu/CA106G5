package android.com.meetU.meetup.controller;

import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.com.meetU.live.model.LiveService;
import android.com.meetU.main.ImageUtil;
import android.com.meetU.meetup.model.MeetupService;
import android.com.meetU.meetup.model.MeetupVO;


public class android_MeetupServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		MeetupService meetupSvc = new MeetupService();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		if(action.equals("getAll")) {
			List<MeetupVO> meetupList = meetupSvc.getAll();
			writeText(res, gson.toJson(meetupList));
			
		} else if(action.equals("getHost")) {
			String mem_ID = jsonObject.get("mem_ID").getAsString();
			List<MeetupVO> meetupList = meetupSvc.getHost(mem_ID);
			writeText(res, gson.toJson(meetupList));
		
		} else if(action.equals("getOneMeetup")) {
			String meetup_ID = jsonObject.get("meetup_ID").getAsString();
			MeetupVO meetupVO = meetupSvc.getOneMeetup(meetup_ID);
			writeText(res,meetupVO == null ? "" : gson.toJson(meetupVO));
			
		} else if(action.equals("getLike")) {
			String mem_ID = jsonObject.get("mem_ID").getAsString();
			String meetup_ID = jsonObject.get("meetup_ID").getAsString();
			
			
		} else if(action.equals("update")) {
			MeetupVO meetupVO = gson.fromJson(jsonObject.get("meetup").getAsString(), MeetupVO.class);
			String meetup_ID = meetupVO.getMeetup_ID();
			String meetup_name = meetupVO.getMeetup_name();
			Timestamp meetup_date = meetupVO.getMeetup_date();
			String meetup_loc = meetupVO.getMeetup_loc();
			Integer meetup_status = meetupVO.getMeetup_status();
			String meetup_info = meetupVO.getMeetup_info();
			Integer meetup_minppl = meetupVO.getMeetup_minppl();
			Integer meetup_maxppl = meetupVO.getMeetup_maxppl();
			Date meetup_joindate = meetupVO.getMeetup_joindate();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			String meetup_ID = jsonObject.get("meetup_ID").getAsString();
//			String meetup_name = jsonObject.get("meetup_name").getAsString();
//			Date meetup_date = null;
//			try {
//				meetup_date = new Date((sdf.parse(jsonObject.get("meetup_date").getAsString())).getTime());
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			String meetup_loc = jsonObject.get("meetup_loc").getAsString();
//			Integer meetup_status = jsonObject.get("meetup_status").getAsInt();
//			String meetup_info = jsonObject.get("meetup_info").getAsString();
			
			writeText(res,String.valueOf(meetupSvc.updateMeetup(meetup_ID, meetup_name, meetup_date, meetup_loc, meetup_status, meetup_info,meetup_minppl,meetup_maxppl,meetup_joindate)));
			
		} else if(action.equals("updateImage")) {
			String meetup_ID = jsonObject.get("meetup_ID").getAsString();
			String imageBase64 = jsonObject.get("imageBase64").getAsString();
			byte[] meetup_image = Base64.getMimeDecoder().decode(imageBase64);
			
			writeText(res, String.valueOf(meetupSvc.updateMeetupImage(meetup_ID, meetup_image)));
			
		} else if(action.equals("getImage")) {
			OutputStream os = res.getOutputStream();
			String meetup_ID = jsonObject.get("mem_acc").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = meetupSvc.getImage(meetup_ID);
			if (image != null) {
				// 縮圖 in server side
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			os.write(image);
	
		} else {
			writeText(res, "");
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
