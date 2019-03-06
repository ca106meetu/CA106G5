package com.meetU.meetup_mem.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.meetup_mem.model.MeetupMemService;
import com.meetU.meetup_mem.model.MeetupMemVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/MeetupMemServlet")
public class MeetupMemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求 //看個人參加的所有聯誼
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*=================1.接收請求參數 - 輸入格式的錯誤處理=================*/
				String meetup_ID = req.getParameter("meetup_ID");
				if(meetup_ID == null || (meetup_ID.trim()).length()==0) {
					errorMsgs.add("請輸入聯誼編號");
				}
			
				String mem_ID = req.getParameter("mem_ID");
				if(mem_ID == null || (mem_ID.trim()).length()==0) {
					errorMsgs.add("請輸入成員編號");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/meetupMem/select_page_mem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*=================2.開始查詢資料=================*/
				MeetupMemService meetupMemSvc = new MeetupMemService();
				MeetupMemVO meetupMemVO = meetupMemSvc.getOneMeetupMem(meetup_ID, mem_ID);
				if(meetupMemVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/meetupMem/select_page_mem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*=================3.查詢完成,準備轉交(Send the Success view)=================*/
				req.setAttribute("meetupMemVO", meetupMemVO); // 資料庫取出的empVO物件,存入req
				String url = "/meetupMem/listOneMeetupMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/meetupMem/select_page_mem.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {//目前沒用到這個功能
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*=================1.接收請求參數=================*/
				String meetup_ID = req.getParameter("meetup_ID");
				String mem_ID = req.getParameter("mem_ID");
				/*=================2.開始查詢資料=================*/
				MeetupMemService meetupMemSvc = new MeetupMemService();
				MeetupMemVO meetupMemVO = meetupMemSvc.getOneMeetupMem(meetup_ID, mem_ID);
				/*=================3.查詢完成,準備轉交(Send the Success view)*/
				req.setAttribute("meetupMemVO", meetupMemVO);
				String url = "/meetup/update_meetup_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);	
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/meetupMem/listAllMeetupMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {//來自 listAllMyMeetup //給評價
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*=================1.接收請求參數，輸入格式的錯誤處理----------*/
				String meetup_ID = req.getParameter("meetup_ID").trim();
				String mem_ID = req.getParameter("mem_ID").trim();
				Integer meetup_rate;
				try{
					meetup_rate = new Integer(req.getParameter("meetup_rate").trim());
				} catch(NumberFormatException e) {
					meetup_rate = 0;
					errorMsgs.add("請填數字");
				}
				
				String meetup_comment = req.getParameter("meetup_comment").trim();
				
				MeetupMemVO meetupMemVO = new MeetupMemVO();
				meetupMemVO.setMeetup_ID(meetup_ID);
				meetupMemVO.setMem_ID(mem_ID);
				meetupMemVO.setMeetup_rate(meetup_rate);
				meetupMemVO.setMeetup_comment(meetup_comment);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("meetupMemVO", meetupMemVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/meetupMem/listAllMyMeetup.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/*=================2.開始修改資料---------------*/
				MeetupMemService meetupMemSvc = new MeetupMemService();
				meetupMemVO = meetupMemSvc.updateMeetupMem(meetup_ID, mem_ID, meetup_rate, meetup_comment);
				/*=================3.修改完成,準備轉交(Send the Success view)----------*/
				req.setAttribute("meetupMemVO", meetupMemVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/meetupMem/select_page_mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/meetupMem/listAllMyMeetup.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) { //報名參加meetup
			List<String> errorMsgs = new LinkedList<String> ();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*=================1.接收請求參數，輸入格式的錯誤處理------------------*/
				String meetup_ID = req.getParameter("meetup_ID");
				if(meetup_ID == null || (meetup_ID.trim()).length()==0) {
					errorMsgs.add("請輸入聯誼編號");
				}
			
				String mem_ID = req.getParameter("mem_ID");
				if(mem_ID == null || (mem_ID.trim()).length()==0) {
					errorMsgs.add("請輸入成員編號");
				}
				MeetupMemVO meetupMemVO = new MeetupMemVO();
				meetupMemVO.setMeetup_ID(meetup_ID);
				meetupMemVO.setMem_ID(mem_ID);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("meetupMemVO", meetupMemVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/meetupMem/addMeetupMem.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/*=================2.開始新增資料-----------------*/
				MeetupMemService meetupMemSvc = new MeetupMemService();
				meetupMemVO = meetupMemSvc.addMeetupMem(meetup_ID, mem_ID);
				/*=================3.新增完成,準備轉交(Send the Success view)-----------*/
				String url = "/FrontEnd/meetupMem/select_page_mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("新增資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/meetupMem/addMeetupMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllMyMeetup.jsp，退出該聯誼

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數***************************************/
				String meetup_ID = req.getParameter("meetup_ID");
				String mem_ID = req.getParameter("mem_ID");
				/***************************2.開始刪除資料***************************************/
				MeetupMemService meetupMemSvc = new MeetupMemService();
				meetupMemSvc.deleteMeetupMem(meetup_ID, mem_ID);
	
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/meetupMem/select_page_mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/meetupMem/select_page_mem.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("list_All_MyMeetup".equals(action)) { // 來自select_page.jsp的請求 //看個人參加的所有聯誼
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*=================1.接收請求參數 - 輸入格式的錯誤處理=================*/
				String mem_ID = req.getParameter("mem_ID");
				if(mem_ID == null || (mem_ID.trim()).length()==0) {
					errorMsgs.add("請輸入成員編號");
				}
				
				/*=================2.開始查詢資料=================*/
				MeetupMemService meetupMemSvc = new MeetupMemService();
				List<MeetupMemVO> list = meetupMemSvc.getMyAllMeetup(mem_ID);
				
				if(list == null) {   //?
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/meetupMem/select_page_mem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*=================3.查詢完成,準備轉交(Send the Success view)=================*/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/meetupMem/listAllMyMeetup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/meetupMem/select_page_mem.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("list_AllMeetup_Mem".equals(action)) { // 來自select_page.jsp的請求 //看個人參加的所有聯誼
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*=================1.接收請求參數 - 輸入格式的錯誤處理=================*/
				String meetup_ID = req.getParameter("meetup_ID");
				if(meetup_ID == null || (meetup_ID.trim()).length()==0) {
					errorMsgs.add("請輸入聯誼編號");
				}
						
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/meetupMem/select_page_mem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*=================2.開始查詢資料=================*/
				MeetupMemService meetupMemSvc = new MeetupMemService();
				List<MeetupMemVO> list = meetupMemSvc.getAll(meetup_ID);
				
				if(list == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/meetupMem/select_page_mem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*=================3.查詢完成,準備轉交(Send the Success view)=================*/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/meetupMem/listAllMeetupMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/meetupMem/select_page_mem.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
