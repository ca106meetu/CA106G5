package com.meetU.meetup.controller;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
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

import com.meetU.meetup.model.MeetupService;
import com.meetU.meetup.model.MeetupVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/MeetupServlet")
public class MeetupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*=================1.接收請求參數 - 輸入格式的錯誤處理=================*/
				String meetup_ID = req.getParameter("meetup_ID");
				if(meetup_ID == null || (meetup_ID.trim()).length()==0) {
					errorMsgs.add("請輸入聯誼編號");
				}
				
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/meetup/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*=================2.開始查詢資料=================*/
				MeetupService meetupSvc = new MeetupService();
				MeetupVO meetupVO = meetupSvc.getOneMeetup(meetup_ID);
				if(meetupVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/meetup/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*=================3.查詢完成,準備轉交(Send the Success view)=================*/
				req.getSession().setAttribute("meetupVO", meetupVO); // 資料庫取出的empVO物件,存入req
				String url = "/FrontEnd/meetup/listOneMeetup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/meetup/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOne_For_Update".equals(action)) {// 單筆修改;來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*=================1.接收請求參數=================*/
				String meetup_ID = req.getParameter("meetup_ID");
				/*=================2.開始查詢資料=================*/
				MeetupService meetupSvc = new MeetupService();
				MeetupVO meetupVO = meetupSvc.getOneMeetup(meetup_ID);
				Base64.Encoder encoder = Base64.getEncoder(); //why?
				
				if(meetupVO.getMeetup_pic()!=null) {
					String encodeText = encoder.encodeToString(meetupVO.getMeetup_pic());
					req.setAttribute("encodeText", encodeText);
				}
				/*=================3.查詢完成,準備轉交(Send the Success view)=================*/
				req.setAttribute("meetupVO", meetupVO); // 資料庫取出的empVO物件,存入req
				String url = "/FrontEnd/meetup/update_meetup_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/meetup/listAllMeetup.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*=================1.接收請求參數，輸入格式的錯誤處理----------*/
				String meetup_ID = req.getParameter("meetup_ID").trim();
				if(meetup_ID == null || meetup_ID.trim().length()==0) {
					errorMsgs.add("聯誼編號 請勿空白");
				}
				
				String meetup_name = req.getParameter("meetup_name");
				String meetup_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,255}$";
				if(meetup_name == null || meetup_name.trim().length()==0) {
					errorMsgs.add("聯誼名稱 請勿空白");
				}else if(!meetup_name.trim().matches(meetup_nameReg)) {
					errorMsgs.add("聯誼名稱只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}
				
				java.sql.Date meetup_date = null;
				try {
					meetup_date = java.sql.Date.valueOf(req.getParameter("meetup_date").trim());
				}catch(IllegalArgumentException e) {
					meetup_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				String meetup_loc = req.getParameter("meetup_loc").trim();
				if(meetup_loc == null || meetup_loc.trim().length()==0) {
					errorMsgs.add("地址請勿空白");
				}
				
				//讓圖片不會消失
				Part part = req.getPart("meetup_pic");
				InputStream in = part.getInputStream();
				byte[] meetup_pic;
				if(part.getSize()==0){ //從資料庫撈出
					MeetupService meetupSvc = new MeetupService();
					MeetupVO meetupVO = new MeetupVO();
					meetupVO = meetupSvc .getOneMeetup(meetup_ID);
					meetup_pic=meetupVO.getMeetup_pic();
				}else { //從網頁上傳
					meetup_pic = new byte[in.available()];
					in.read(meetup_pic);
					in.close();
				}
				Base64.Encoder encoder = Base64.getEncoder();
				String encodeText = encoder.encodeToString(meetup_pic);
				
				//鬆鬆的方法
//				InputStream in = null;
//				byte[] prod_pic = null;
//
//				Part part = req.getPart("prod_pic");
//				if(getFileNameFromPart(part) != null) {
//					in = part.getInputStream();
//					prod_pic = new byte[in.available()];
//					in.read(prod_pic);
//					in.close();
//				}else {
//					prod_pic = new ProductService().getOneProd(prod_ID).getProd_pic();
//				}
				req.setAttribute("encodeText", encodeText);
							
				Integer meetup_status;
				try {
					meetup_status = new Integer(req.getParameter("meetup_status").trim());
				}catch(NumberFormatException e) {
					meetup_status=0;
					errorMsgs.add("請填狀態碼");
				}
				
				String meetup_info = req.getParameter("meetup_info").trim();
				if(meetup_info == null || meetup_info.trim().length()==0) {
					errorMsgs.add("聯誼內容請勿空白");
				}
				
				MeetupVO meetupVO = new MeetupVO();
				meetupVO.setMeetup_ID(meetup_ID);
				meetupVO.setMeetup_name(meetup_name);
				meetupVO.setMeetup_date(meetup_date);
				meetupVO.setMeetup_loc(meetup_loc);
				meetupVO.setMeetup_status(meetup_status);
				meetupVO.setMeetup_pic(meetup_pic);
				meetupVO.setMeetup_info(meetup_info);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("meetupVO", meetupVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/meetup/update_meetup_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/*=================2.開始修改資料---------------*/
				MeetupService meetupSvc = new MeetupService();
				meetupVO = meetupSvc.updateMeetup(meetup_ID, meetup_name, meetup_date, meetup_loc, meetup_status, meetup_pic, meetup_info);
				/*=================3.修改完成,準備轉交(Send the Success view)----------*/
				req.setAttribute("meetupVO", meetupVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/FrontEnd/meetup/listOneMeetup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/meetup/update_meetup_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*=================1.接收請求參數，輸入格式的錯誤處理------------------*/
				String meetup_name = req.getParameter("meetup_name");
				String meetup_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,255}$";
				if(meetup_name == null || meetup_name.trim().length()==0) {
					errorMsgs.add("聯誼名稱 請勿空白");
				}else if(!meetup_name.trim().matches(meetup_nameReg)) {
					errorMsgs.add("聯誼名稱只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}
				
				String mem_ID = req.getParameter("mem_ID").trim();
				if(mem_ID == null || mem_ID.trim().length()==0) {
					errorMsgs.add("聯誼編號 請勿空白");
				}
				
				java.sql.Date meetup_date = null;
				try {
					meetup_date = java.sql.Date.valueOf(req.getParameter("meetup_date").trim());
				}catch(IllegalArgumentException e) {
					meetup_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				String meetup_loc = req.getParameter("meetup_loc").trim();
				if(meetup_loc == null || meetup_loc.trim().length()==0) {
					errorMsgs.add("地址請勿空白");
				}
				
				Integer meetup_status;
				try {
					meetup_status=new Integer(req.getParameter("meetup_status").trim());
				}catch(NumberFormatException e) {
					meetup_status=0;
					errorMsgs.add("請輸入狀態碼");
				}
				
//				//圖片讀
//				Part part = req.getPart("meetup_pic");
//				InputStream in = part.getInputStream();
//				byte[] meetup_pic = new byte[in.available()];
//				in.read(meetup_pic);
//				in.close();
				
				String meetup_info = req.getParameter("meetup_info").trim();
				if(meetup_info == null || meetup_info.trim().length()==0) {
					errorMsgs.add("聯誼內容請勿空白");
				}
				
				byte[] meetup_pic=null;
				Part part = req.getPart("meetup_pic");
				
				Base64.Encoder encoder = Base64.getEncoder();
				if(getFileNameFromPart(part) != null) {
					InputStream in = part.getInputStream();
					meetup_pic = new byte[in.available()];
					in.read(meetup_pic);
					in.close();
					String encodeText = encoder.encodeToString(meetup_pic);
					req.setAttribute("encodeText", encodeText);
				} else {
					if(req.getParameter("encodeText") != null && req.getParameter("encodeText").trim().length() !=0) {
						Base64.Decoder decoder = Base64.getDecoder();
						meetup_pic = decoder.decode(req.getParameter("encodeText"));
						String encodeText = encoder.encodeToString(meetup_pic);
						req.setAttribute("encodeText", encodeText);
					}
				}				
				
				MeetupVO meetupVO = new MeetupVO();
				meetupVO.setMeetup_name(meetup_name);
				meetupVO.setMem_ID(mem_ID);
				meetupVO.setMeetup_date(meetup_date);
				meetupVO.setMeetup_loc(meetup_loc);
				meetupVO.setMeetup_status(meetup_status);
				meetupVO.setMeetup_pic(meetup_pic);
				meetupVO.setMeetup_info(meetup_info);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("meetupVO", meetupVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/meetup/addMeetup.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/*=================2.開始新增資料-----------------*/
				MeetupService meetupSvc = new MeetupService();
				meetupVO = meetupSvc.addMeetup(meetup_name, mem_ID, meetup_date, meetup_loc, meetup_status, meetup_pic, meetup_info);
				/*=================3.新增完成,準備轉交(Send the Success view)-----------*/
				String url = "/FrontEnd/meetup/listAllMeetup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
			}catch (Exception e) {
				errorMsgs.add("新增資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/meetup/addMeetup.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String meetup_ID = req.getParameter("meetup_ID");
				
				/***************************2.開始刪除資料***************************************/
				MeetupService meetupSvc = new MeetupService();
				meetupSvc.deleteMeetup(meetup_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/FrontEnd/meetup/listAllMeetup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/meetup/listAllMeetup.jsp");
				failureView.forward(req, res);
			}
		}	
		
		if ("getAllByHost".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mem_ID = req.getParameter("mem_ID");
				
				/***************************2.開始刪除資料***************************************/
				MeetupService meetupSvc = new MeetupService();
				List<MeetupVO> list = meetupSvc.getAllByHost(mem_ID);
				
				/***************************3.準備轉交(Send the Success view)***********/								
				req.setAttribute("list", list);
				String url = "/FrontEnd/meetup/listMeetupByHost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //成功後
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/meetup/listAllMeetup.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getSearch".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String searchInfo = req.getParameter("searchInfo");
				String searchType = req.getParameter("searchType");
				/***************************2.開始刪除資料***************************************/
				MeetupService meetupSvc = new MeetupService();
				List<MeetupVO> list;
System.out.println("0");
				
				if("nam".equals(searchType)) {
System.out.println("2");
					list = meetupSvc.getSearchName(searchInfo);
				}else if("loc".equals(searchType)) {
					list = meetupSvc.getSearchLoc(searchInfo);
				}else {
					list = meetupSvc.getAll();
				}
				/***************************3.準備轉交(Send the Success view)***********/								
				req.setAttribute("list", list);
//				String url = "/FrontEnd/meetup/meetupHomePg.jsp";
				String url = "/FrontEnd/meetup/listAllMeetup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
System.out.println("1");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/meetup/listAllMeetup.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
