package com.meetU.empAuth.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.empAuth.model.*;



@WebServlet("/EmpAuthServlet")
public class EmpAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if("getSome_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str1 = req.getParameter("emp_ID");
//				String str2 = req.getParameter("auth_ID");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入員工ID");
				}
//				if (str2== null || (str2.trim()).length() == 0) {
//					errorMsgs.add("請輸入權限ID");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empAuth/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String emp_ID = null;
				try {
					emp_ID = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("員工ID格式不正確");
				}
				
//				String auth_ID = null;
//				try {
//					auth_ID = new String(str2);
//				} catch (Exception e) {
//					errorMsgs.add("權限ID格式不正確");
//				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empAuth/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmpAuthService empAuthSvc = new EmpAuthService();//???
				List<EmpAuthVO> list  = empAuthSvc.getPartOfOneEmpAuth(emp_ID);
				
				if (list.isEmpty() == true) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empAuth/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.getSession().setAttribute("list", list); // 資料庫取出的EmpAuthVO物件,存入req
				String url = "/back-end/empAuth/listSomeEmpAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmpAuth.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empAuth/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // 來自addEmpAuth.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str1 = req.getParameter("emp_ID");
				String str2 = req.getParameter("auth_ID");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入員工ID");
				}
				if (str2== null || (str2.trim()).length() == 0) {
					errorMsgs.add("請輸入權限ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empAuth/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String emp_ID = null;
				try {
					emp_ID = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("員工ID格式不正確");
				}
				
				String auth_ID = null;
				try {
					auth_ID = new String(str2);
				} catch (Exception e) {
					errorMsgs.add("權限ID格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empAuth/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmpAuthService empAuthSvc = new EmpAuthService();//???
				EmpAuthVO empAuthVO  = empAuthSvc.getOneEmpAuth(emp_ID, auth_ID);
				
				if (empAuthVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empAuth/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empAuthVO", empAuthVO); // 資料庫取出的empAuthVO物件,存入req
				String url = "/back-end/empAuth/listOneEmpAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmpAuth.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empAuth/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmpAuth.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String emp_ID = new String(req.getParameter("emp_ID"));
				String auth_ID = new String(req.getParameter("auth_ID"));
				
				/***************************2.開始查詢資料****************************************/
				EmpAuthService empAuthSvc = new EmpAuthService();
				EmpAuthVO empAuthVO = empAuthSvc.getOneEmpAuth(emp_ID, auth_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empAuthVO", empAuthVO);         // 資料庫取出的empAuthVO物件,存入req
				String url = "/back-end/empAuth/update_empAuth_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_empAuth_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empAuth/listAllEmpAuth.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_empAuth_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String emp_ID = new String(req.getParameter("emp_ID").trim());
				String auth_ID = new String(req.getParameter("auth_ID").trim());
				
				Integer gift_quantity = null;
				try {
					gift_quantity = new Integer(req.getParameter("gift_quantity").trim());
				} catch (NumberFormatException e) {
					gift_quantity = 0;
					errorMsgs.add("權限數量請填數字.");
				}
								
		        EmpAuthVO empAuthVO = new EmpAuthVO();
				empAuthVO.setEmp_ID(emp_ID);
				empAuthVO.setAuth_ID(auth_ID);
				
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");

					req.setAttribute("empAuthVO", empAuthVO); // 含有輸入格式錯誤的empAuthVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empAuth/update_empAuth_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				EmpAuthService empAuthSvc = new EmpAuthService();
				empAuthVO = empAuthSvc.updateEmpAuth(emp_ID, auth_ID);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empAuthVO", empAuthVO); // 資料庫update成功後,正確的的empAuthVO物件,存入req
				String url = "/back-end/empAuth/listOneEmpAuth.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmpAuth.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				System.out.println("檢查點 4");
//
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/empAuth/update_empAuth_input.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("insert".equals(action)) { // 來自addEmpAuth.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String emp_ID = req.getParameter("emp_ID");
				String emp_IDReg = "^[(a-zA-Z0-9_)]{7,20}$";
				if (emp_ID == null || emp_ID.trim().length() == 0) {
					errorMsgs.add("員工ID: 請勿空白");
				} else if(!emp_ID.trim().matches(emp_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工ID:只能是英文字母、數字 , 且長度必需在7到20之間");
	            }
				
				String auth_ID = req.getParameter("auth_ID");
				String auth_IDReg = "^[(a-zA-Z0-9_)]{7,20}$";
				if (auth_ID == null || auth_ID.trim().length() == 0) {
					errorMsgs.add("權限ID: 請勿空白");
				} else if(!auth_ID.trim().matches(auth_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("權限ID:只能是英文字母、數字 , 且長度必需在7到20之間");
	            }
										
				EmpAuthVO empAuthVO = new EmpAuthVO();
								System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");
					req.setAttribute("empAuthVO", empAuthVO); // 含有輸入格式錯誤的empAuthVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empAuth/addEmpAuth.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EmpAuthService empAuthSvc = new EmpAuthService();
				System.out.println(emp_ID);
				System.out.println(auth_ID);
				empAuthVO = empAuthSvc.addEmpAuth(emp_ID, auth_ID);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/empAuth/listAllEmpAuth.jsp";
				System.out.println("檢查點 3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmpAuth.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("檢查點 4");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empAuth/addEmpAuth.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmpAuth.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String emp_ID = new String(req.getParameter("emp_ID"));
				String auth_ID = new String(req.getParameter("auth_ID"));
				
				/***************************2.開始刪除資料***************************************/
				EmpAuthService empAuthSvc = new EmpAuthService();
				empAuthSvc.deleteEmpAuth(emp_ID, auth_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/empAuth/listAllEmpAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empAuth/listAllEmpAuth.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
