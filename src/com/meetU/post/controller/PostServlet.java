package com.meetU.post.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.meetU.post.model.*;

public class PostServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("post_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String post_ID = null;
				String regex = "^[P]\\d{6}$";
				if(str.trim().matches(regex)) {
					post_ID = str;
				}else {
					errorMsgs.add("文章編號格式不正確");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOnePost(post_ID);
				if (postVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("postVO", postVO); // 資料庫取出的PostVO物件,存入req
				String url = "/post/listOnePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePost.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/post/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllPost.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String post_ID = new String(req.getParameter("post_ID"));
				
				/***************************2.開始查詢資料****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOnePost(post_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("postVO", postVO);         // 資料庫取出的postVO物件,存入req
				String url = "/post/updatePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updatePost.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/post/listAllPost.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自updatePost.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String post_ID = new String(req.getParameter("post_ID").trim());
				String poster_ID = req.getParameter("poster_ID").trim();
				String mem_ID = req.getParameter("mem_ID").trim();
				String check_in_ID = req.getParameter("check_in_ID").trim();
				String post_content = req.getParameter("post_content").trim();
				Timestamp publish_time = Timestamp.valueOf(req.getParameter("publish_time").trim());
				Integer post_like = new Integer(req.getParameter("post_like").trim());
				
				String ck_reg = "^(CK)\\d{6}$";

				if (!check_in_ID.matches(ck_reg)) {
					check_in_ID = null;
				}
				
				
				if (post_content == null || post_content.trim().length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				}
				
				Integer post_visb = new Integer(req.getParameter("post_visb").trim());
				if(post_visb < 0) {
					errorMsgs.add("請選擇文章能見度");
				}

				PostVO postVO = new PostVO();
				postVO.setPost_ID(post_ID);
				postVO.setPoster_ID(poster_ID);
				postVO.setMem_ID(mem_ID);
				postVO.setCheck_in_ID(check_in_ID);
				postVO.setPublish_time(publish_time);
				postVO.setPost_content(post_content);
				postVO.setPost_like(post_like);
				postVO.setPost_visb(post_visb);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postVO", postVO); // 含有輸入格式錯誤的postVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/updatePost.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PostService postSvc = new PostService();
				postVO = postSvc.updatePost(poster_ID,mem_ID,check_in_ID,post_content,publish_time,post_like, post_visb, post_ID);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("postVO", postVO); // 資料庫update成功後,正確的的postVO物件,存入req
				String url = "/post/listOnePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePost.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/post/updatePost.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // 來自addPost.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String poster_ID = req.getParameter("poster_ID");
				if (poster_ID == null || poster_ID.trim().length() == 0) {
					errorMsgs.add("發文者編號請勿空白");
				}
				
				String mem_ID = req.getParameter("mem_ID").trim();
				if (mem_ID == null || mem_ID.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				
				String check_in_ID = req.getParameter("check_in_ID").trim();
				
				String post_content = req.getParameter("post_content").trim();
				if (post_content == null || post_content.trim().length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				}
				
				
				
				Integer post_visb = new Integer(req.getParameter("post_visb").trim());
				if(post_visb < 0) {
					errorMsgs.add("請選擇文章能見度");
				}
				
				java.sql.Timestamp publish_time = new java.sql.Timestamp(System.currentTimeMillis());

				PostVO postVO = new PostVO();
				postVO.setPoster_ID(poster_ID);
				postVO.setMem_ID(mem_ID);
				postVO.setCheck_in_ID(check_in_ID);
				postVO.setPublish_time(publish_time);
				postVO.setPost_content(post_content);
				postVO.setPost_visb(post_visb);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postVO", postVO); // 含有輸入格式錯誤的postVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/addPost.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				PostService postSvc = new PostService();
				postVO = postSvc.addPost(poster_ID, mem_ID,check_in_ID, publish_time,post_content, post_visb);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/post/listAllPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPost.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/post/addPost.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllPost.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String post_ID = new String(req.getParameter("post_ID"));
				
				/***************************2.開始刪除資料***************************************/
				PostService postSvc = new PostService();
				postSvc.deletePost(post_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/post/listAllPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/post/listAllPost.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
