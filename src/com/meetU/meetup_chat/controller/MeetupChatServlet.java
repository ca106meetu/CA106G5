package com.meetU.meetup_chat.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/MeetupReportServlet")
public class MeetupChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/*=================1.接收請求參數 - 輸入格式的錯誤處理=================*/
//				String str = req.getParameter("meetup_ID");
//				if(str==null || (str.trim()).length()==0) {
//					errorMsgs.add("請輸入聯誼編號");
//				}
//				if(!errorMsgs.isEmpty()) {
//					req.setAttribute("meetupChatVO", meetupChatVO);
//					RequestDispatcher failureView = req.getRequestDispatcher("/meetupChat/chat.jsp");
//					failureView.forward(req,res);
//					return;
//				}
			
//			}
		}
		
	}

}
