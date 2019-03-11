package com.meetU.live_like.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.live_like.model.Live_likeService;



public class Live_likeServlet extends HttpServlet {

   
    public Live_likeServlet() {
    
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//		觀看我的收藏
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/*=================1.接收請求參數，輸入格式的錯誤處理------------------*/
			
				String mem_ID = req.getParameter("mem_ID").trim();
				
				/*=================3.查詢完成,準備轉交(Send the Success view)-----------*/
				req.setAttribute("mem_ID", mem_ID); // 資料庫取出的empVO物件,存入req
				String url = "/FrontEnd/live_like/listAllLive_like.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				}
		
//		收藏直播
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			try {
				/*=================1.接收請求參數，輸入格式的錯誤處理------------------*/		
				String host_ID = req.getParameter("host_ID");
				String mem_ID = req.getParameter("mem_ID");
				
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("meetupLikeVO", meetupLikeVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/FrontEnd/meetup/meetupHomePg.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
				/*=================2.開始新增資料-----------------*/
			    Live_likeService live_likeSvc =new Live_likeService();
			    live_likeSvc.addLive_like(mem_ID,host_ID); 
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
		
//		移除收藏
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
//			try {
				/***************************1.接收請求參數***************************************/
				String host_ID = req.getParameter("host_ID");
				String mem_ID = req.getParameter("mem_ID");
				/***************************2.開始刪除資料***************************************/
					Live_likeService live_likeSvc =new Live_likeService();
				    live_likeSvc.deleteLive_like(mem_ID,host_ID); 
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/FrontEnd/meetupLike/select_page_Like.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
				PrintWriter out = res.getWriter();
				out.print("{}");
				out.close();
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/FrontEnd/meetupLike/listAllMyMeetupLike.jsp");
//				failureView.forward(req, res);
//			}
		}	
		
		
//		移除收藏2
		if ("delete2".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
//			try {
				/***************************1.接收請求參數***************************************/
				String host_ID = req.getParameter("host_ID");
				String mem_ID = req.getParameter("mem_ID");
				/***************************2.開始刪除資料***************************************/
					Live_likeService live_likeSvc =new Live_likeService();
				    live_likeSvc.deleteLive_like(mem_ID,host_ID); 
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				    req.setAttribute("mem_ID", mem_ID); // 資料庫取出的empVO物件,存入req
					String url = "/FrontEnd/live_like/listAllLive_like.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/FrontEnd/meetupLike/listAllMyMeetupLike.jsp");
//				failureView.forward(req, res);
//			}
		}	
	}
}
