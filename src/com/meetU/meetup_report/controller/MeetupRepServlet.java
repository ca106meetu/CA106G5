package com.meetU.meetup_report.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meetU.meetup.model.MeetupService;
import com.meetU.meetup_report.model.MeetupRepService;
import com.meetU.meetup_report.model.MeetupRepVO;

@WebServlet("/MeetupRepServlet")
public class MeetupRepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req,res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*=================1.接收請求參數 - 輸入格式的錯誤處理=================*/
				String meetup_rep_ID = req.getParameter("meetup_rep_ID");
				if(meetup_rep_ID == null || (meetup_rep_ID.trim()).length()==0) {
					errorMsgs.add("請輸入聯誼編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/meetupRep/listAllRep.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*=================2.開始查詢資料=================*/
				MeetupRepService meetupRepSvc = new MeetupRepService();
				MeetupRepVO meetupRepVO = meetupRepSvc.getOneMeetupRep(meetup_rep_ID);
				if(meetupRepVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meetupRep/listAllRep.jsp");
					failureView.forward(req, res);
					return;
				}
				/*=================3.查詢完成,準備轉交(Send the Success view)=================*/
				req.setAttribute("meetupRepVO", meetupRepVO);
				String url = "/back-end/meetupRep/listOneMeetupRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得資料: "+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meetupRep/listAllRep.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*=================1.接收請求參數=================*/
				String meetup_rep_ID = req.getParameter("meetup_rep_ID");
				/*=================2.開始查詢資料=================*/
				MeetupRepService meetupRepSvc = new MeetupRepService();
				MeetupRepVO meetupRepVO = meetupRepSvc.getOneMeetupRep(meetup_rep_ID);
				/*=================3.查詢完成,準備轉交(Send the Success view)=================*/
				req.setAttribute("meetupRepVO", meetupRepVO);

				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
			
				String url = "/back-end/meetupRep/MeetupRep.jsp";
//				String url = "/back-end/meetupRep/listAllEmp_06_EL_Test_Bootstrap_modal.jsp";
//				String url = "/back-end/meetupRep/updateRepContentAns.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req,res);
				return;
				
			}catch(Exception e) {
				errorMsgs.add("無法取得檢舉內容:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meetupRep/listAllRep.jsp");
			}
		
		}
	
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			try {
				/*=================1.接收請求參數，輸入格式的錯誤處理----------*/
				String meetup_rep_ID = req.getParameter("meetup_rep_ID").trim();
				String rep_ans = req.getParameter("rep_ans");
				if(rep_ans == null || rep_ans.trim().length()==0) {
					errorMsgs.add("檢舉回覆原因請勿空白");
				}
				
//				java.sql.Timestamp rep_ans_date = null;
//				try {
//					rep_ans_date = java.sql.Timestamp.valueOf(req.getParameter("rep_ans_date").trim());
//				}catch(IllegalArgumentException e) {
//					rep_ans_date = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("時間格式不對");
//				}
			
				Integer rep_status;
				try {
					rep_status = new Integer(req.getParameter("rep_status").trim());
				}catch(NumberFormatException e) {
					rep_status=0;
					errorMsgs.add("請填狀態碼");
				}
				
				MeetupRepVO meetupRepVO = new MeetupRepVO();
				meetupRepVO.setMeetup_rep_ID(meetup_rep_ID);
				meetupRepVO.setRep_status(rep_status);
				meetupRepVO.setRep_ans(rep_ans);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("meetupRepVO", meetupRepVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meetupRep/repContent.jsp");
					failureView.forward(req, res);
					return;
				}
				/*=================2.開始修改資料---------------*/
				MeetupRepService meetupRepSvc = new MeetupRepService();
				meetupRepVO = meetupRepSvc.updateMeetupRep(rep_status, rep_ans, meetup_rep_ID);
				/*=================3.修改完成,準備轉交(Send the Success view)----------*/
//				req.setAttribute("meetupRepVO", meetupRepVO);

PrintWriter out = res.getWriter();
out.print("{}");
out.close();
//				String url = "/back-end/meetupRep/MeetupRep.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);

//			}catch(Exception e) {
//				errorMsgs.add("回覆失敗:"+ e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meetupRep/repContent.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
//				/*=================1.接收請求參數，輸入格式的錯誤處理------------------*/
				String meetup_ID = req.getParameter("meetup_ID");
				String mem_ID = req.getParameter("mem_ID");
				String rep_content = req.getParameter("rep_content");
				if(rep_content == null || rep_content.trim().length()==0) {
					errorMsgs.add("聯誼編號 請勿空白");
				}
				Integer rep_status;
				try {
					rep_status=new Integer(req.getParameter("rep_status"));	
				}catch(NumberFormatException ne) {
					rep_status=0;
					errorMsgs.add("請輸入狀態碼");
				}

				MeetupRepVO meetupRepVO = new MeetupRepVO();
				meetupRepVO.setMeetup_ID(meetup_ID);
				meetupRepVO.setMem_ID(mem_ID);
				meetupRepVO.setRep_content(rep_content);
				meetupRepVO.setRep_status(rep_status);
	
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("meetupRepVO", meetupRepVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/meetup/meetupHomePg.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/*=================2.開始新增資料-----------------*/
				MeetupRepService meetupRepSvc = new MeetupRepService();
				meetupRepVO = meetupRepSvc.addMeetupRep(meetup_ID, mem_ID, rep_content, rep_status);
				/*=================3.新增完成,準備轉交(Send the Success view)-----------*/
				PrintWriter out = res.getWriter();
				out.print("{}");
				out.close();
//				String url = "/FrontEnd/meetup/meetupHomePg.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);	
//				successView.forward(req, res);	
//			}catch(Exception e) {
//				errorMsgs.add("新增資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/FrontEnd/meetup/meetupHomePg.jsp");
//				failureView.forward(req, res);	
//			}
		}
				
		//這個指令應該是刪除MEETUP or 隱形MEETUP
		if ("invisible".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String meetup_rep_ID = req.getParameter("meetup_rep_ID");
				
				/***************************2.開始刪除資料***************************************/
				MeetupRepService meetupRepSvc = new MeetupRepService();
				meetupRepSvc.deleteMeetupRep(meetup_rep_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/FrontEnd/meetupRep/meetupHomePg.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/meetupRep/listAllRep.jsp");
				failureView.forward(req, res);
			}
		}	
	
	}
}
