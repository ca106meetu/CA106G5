package com.meetU.auth.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.meetU.auth.model.*;

public class AuthServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // 來自addAuth.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("auth_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入權限ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/auth/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String auth_ID = null;
				try {
					auth_ID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工權限格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/auth/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AuthService authSvc = new AuthService();
				AuthVO authVO = authSvc.getOneAuth(auth_ID);
				if (authVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/auth/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("authVO", authVO); // 資料庫取出的authVO物件,存入req
				String url = "/back-end/auth/listOneAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneAuth.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/auth/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllAuth.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String auth_ID = new String(req.getParameter("auth_ID"));
				
				/***************************2.開始查詢資料****************************************/
				AuthService authSvc = new AuthService();
				AuthVO authVO = authSvc.getOneAuth(auth_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("authVO", authVO);         // 資料庫取出的authVO物件,存入req
				String url = "/back-end/auth/update_auth_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_auth_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/auth/listAllAuth.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_auth_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String auth_ID = new String(req.getParameter("auth_ID").trim());
				
				
				
				String auth_name = req.getParameter("auth_name");
				String auth_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (auth_name == null || auth_name.trim().length() == 0) {
					errorMsgs.add("員工權限: 請勿空白");
				} else if(!auth_name.trim().matches(auth_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工權限: 只能是中、英文字母、數字和_ , 且長度必需在20以內");
	            }
				
		        AuthVO authVO = new AuthVO();
				authVO.setAuth_ID(auth_ID);
				authVO.setAuth_name(auth_name);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");

					req.setAttribute("authVO", authVO); // 含有輸入格式錯誤的authVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/auth/update_auth_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AuthService authSvc = new AuthService();
				authVO = authSvc.updateAuth(auth_ID, auth_name);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("authVO", authVO); // 資料庫update成功後,正確的的authVO物件,存入req
				String url = "/back-end/auth/listOneAuth.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneauth.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點 4");

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/auth/update_auth_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addAuth.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
								
				
				String auth_name = req.getParameter("auth_name");
				String auth_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (auth_name == null || auth_name.trim().length() == 0) {
					errorMsgs.add("員工權限: 請勿空白");
				} else if(!auth_name.trim().matches(auth_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工權限: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				
				AuthVO authVO = new AuthVO();
				authVO.setAuth_name(auth_name);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");
					req.setAttribute("authVO", authVO); // 含有輸入格式錯誤的authVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/auth/addAuth.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AuthService authSvc = new AuthService();
				authVO = authSvc.addAuth(auth_name);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/auth/listAllAuth.jsp";
				System.out.println("檢查點 3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAuth.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("檢查點 4");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/auth/addAuth.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllAuth.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String auth_ID = new String(req.getParameter("auth_ID"));
				
				/***************************2.開始刪除資料***************************************/
				AuthService authSvc = new AuthService();
				authSvc.deleteAuth(auth_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/auth/listAllAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/auth/listAllAuth.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
