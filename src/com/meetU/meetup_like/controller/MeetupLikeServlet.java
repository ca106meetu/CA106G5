package com.meetU.meetup_like.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.meetup_like.model.MeetupLikeService;
import com.meetU.meetup_like.model.MeetupLikeVO;


@WebServlet("/meetupLikeServlet")
public class MeetupLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*=================1.接收請求參數，輸入格式的錯誤處理------------------*/
				String meetup_ID = req.getParameter("meetup_ID");
				String mem_ID = req.getParameter("mem_ID").trim();
								
				MeetupLikeVO meetupLikeVO = new MeetupLikeVO();
				meetupLikeVO.setMeetup_ID(meetup_ID);
				meetupLikeVO.setMem_ID(mem_ID);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("meetupLikeVO", meetupLikeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/meetupLike/select_page_Like.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/*=================2.開始新增資料-----------------*/
				MeetupLikeService meetupLikeSvc = new MeetupLikeService();
				meetupLikeVO = meetupLikeSvc.getOneMeetupLike(meetup_ID, mem_ID);
				if(meetupLikeVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/meetupLike/select_page_Like.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*=================3.查詢完成,準備轉交(Send the Success view)-----------*/
				req.setAttribute("meetupLikeVO", meetupLikeVO); // 資料庫取出的empVO物件,存入req
				String url = "/FrontEnd/meetupLike/listOneMeetupLike.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
			}catch (Exception e) {
				errorMsgs.add("新增資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/meetupLike/select_page_Like.jsp");
				failureView.forward(req, res);
			}		
	}
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			try {
				/*=================1.接收請求參數，輸入格式的錯誤處理------------------*/		
				String meetup_ID = req.getParameter("meetup_ID");
				String mem_ID = req.getParameter("mem_ID");
			
				MeetupLikeVO meetupLikeVO = new MeetupLikeVO();
				meetupLikeVO.setMeetup_ID(meetup_ID);
				meetupLikeVO.setMem_ID(mem_ID);
				
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("meetupLikeVO", meetupLikeVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/FrontEnd/meetup/meetupHomePg.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
				/*=================2.開始新增資料-----------------*/
				MeetupLikeService meetupLikeSvc = new MeetupLikeService();
				meetupLikeVO = meetupLikeSvc.addMeetupLike(meetup_ID, mem_ID);
				/*=================3.新增完成,準備轉交(Send the Success view)-----------*/
				PrintWriter out = res.getWriter();
				out.println("{}");
				out.close();
//				String url = "/FrontEnd/meetup/meetupHomePg.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
				
//			}catch (Exception e) {
//				errorMsgs.add("新增資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/FrontEnd/meetup/meetupHomePg.jsp");
//				failureView.forward(req, res);
//			}
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String meetup_ID = req.getParameter("meetup_ID");
				String mem_ID = req.getParameter("mem_ID").trim();
				/***************************2.開始刪除資料***************************************/
				MeetupLikeService meetupLikeSvc = new MeetupLikeService();
				meetupLikeSvc.deleteMeetupLike(meetup_ID, mem_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/FrontEnd/meetupLike/select_page_Like.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
				PrintWriter out = res.getWriter();
				out.print("{}");
				out.close();
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/meetupLike/listAllMyMeetupLike.jsp");
				failureView.forward(req, res);
			}
		}	
		
		
		
	}
		
}
