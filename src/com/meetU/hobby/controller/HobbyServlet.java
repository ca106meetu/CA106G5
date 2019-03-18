package com.meetU.hobby.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.hobby.model.*;

public class HobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // 來自addHobby.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("hobby_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入興趣ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/hobby/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String hobby_ID = null;
				try {
					hobby_ID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("興趣名稱格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/hobby/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				HobbyService HobbySvc = new HobbyService();
				HobbyVO hobbyVO = HobbySvc.getOneHobby(hobby_ID);
				if (hobbyVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/hobby/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("hobbyVO", hobbyVO); // 資料庫取出的hobbyVO物件,存入req
				String url = "/FrontEnd/hobby/listOneHobby.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneHobby.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/hobby/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllHobby.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String hobby_ID = new String(req.getParameter("hobby_ID"));
				
				/***************************2.開始查詢資料****************************************/
				HobbyService HobbySvc = new HobbyService();
				HobbyVO hobbyVO = HobbySvc.getOneHobby(hobby_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("hobbyVO", hobbyVO);         // 資料庫取出的hobbyVO物件,存入req
				String url = "/FrontEnd/hobby/update_hobby_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_hobby_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/hobby/listAllHobby.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_hobby_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String hobby_ID = new String(req.getParameter("hobby_ID").trim());
				
				
				
				String hobby_name = req.getParameter("hobby_name");
				String hobby_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (hobby_name == null || hobby_name.trim().length() == 0) {
					errorMsgs.add("興趣名稱: 請勿空白");
				} else if(!hobby_name.trim().matches(hobby_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("興趣名稱: 只能是中、英文字母、數字和_ , 且長度必需在20以內");
	            }
				
		        HobbyVO hobbyVO = new HobbyVO();
				hobbyVO.setHobby_ID(hobby_ID);
				hobbyVO.setHobby_name(hobby_name);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");

					req.setAttribute("hobbyVO", hobbyVO); // 含有輸入格式錯誤的hobbyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/hobby/update_hobby_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				HobbyService hobbySvc = new HobbyService();
				hobbyVO = hobbySvc.updateHobby(hobby_ID, hobby_name);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("hobbyVO", hobbyVO); // 資料庫update成功後,正確的的hobbyVO物件,存入req
				String url = "/FrontEnd/hobby/listOneHobby.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneHobby.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點 4");

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/hobby/update_hobby_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addHobby.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
								
				
				String hobby_name = req.getParameter("hobby_name");
				String hobby_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (hobby_name == null || hobby_name.trim().length() == 0) {
					errorMsgs.add("興趣名稱: 請勿空白");
				} else if(!hobby_name.trim().matches(hobby_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("興趣名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				
				HobbyVO hobbyVO = new HobbyVO();
				hobbyVO.setHobby_name(hobby_name);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");
					req.setAttribute("hobbyVO", hobbyVO); // 含有輸入格式錯誤的hobbyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/hobby/addHobby.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				HobbyService hobbySvc = new HobbyService();
				hobbyVO = hobbySvc.addHobby(hobby_name);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/FrontEnd/hobby/listAllHobby.jsp";
				System.out.println("檢查點 3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllHobby.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("檢查點 4");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/hobby/addHobby.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllHobby.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String hobby_ID = new String(req.getParameter("hobby_ID"));
				
				/***************************2.開始刪除資料***************************************/
				HobbyService hobbySvc = new HobbyService();
				hobbySvc.deletehobby(hobby_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/FrontEnd/hobby/listAllHobby.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/hobby/listAllHobby.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
