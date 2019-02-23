package com.meetU.inform.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.meetU.inform.model.*;


public class InformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自addInform.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("inform_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入通知ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/inform/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String inform_ID = null;
				try {
					inform_ID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("通知編號格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/inform/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				InformService informSvc = new InformService();
				InformVO informVO = informSvc.getOneInform(inform_ID);
				if (informVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/inform/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("informVO", informVO); // 資料庫取出的informVO物件,存入req
				String url = "/back-end/inform/listOneInform.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneInform.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/inform/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllInform.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String inform_ID = new String(req.getParameter("inform_ID"));
				
				/***************************2.開始查詢資料****************************************/
				InformService informSvc = new InformService();
				InformVO informVO = informSvc.getOneInform(inform_ID);
				System.out.println(informVO);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("informVO", informVO);         // 資料庫取出的informVO物件,存入req
				String url = "/back-end/inform/update_inform_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_inform_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/inform/listAllInform.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_inform_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String inform_ID = new String(req.getParameter("inform_ID").trim());
				
				Integer inform_status = null;
				try {
					inform_status= new Integer(req.getParameter("inform_status").trim());
				} catch (NumberFormatException e) {
					inform_status = 0;
					errorMsgs.add("通知狀態請填數字.");
				}
				
				String mem_ID = req.getParameter("mem_ID");
				String mem_IDReg = "^[(a-zA-Z0-9)]{7,20}$";
				if (mem_ID == null || mem_ID.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!mem_ID.trim().matches(mem_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號:只能是英文字母、數字 , 且長度必需在7以內");
	            }
				
				String inform_content = req.getParameter("inform_content").trim();
				if (inform_content == null || inform_content.trim().length() == 0) {
					errorMsgs.add("通知內容: 請勿空白");
				}
				
							
				InformVO informVO = new InformVO();
				
				informVO.setInform_ID(inform_ID);
				informVO.setMem_ID(mem_ID);
				informVO.setInform_status(inform_status);
				informVO.setInform_content(inform_content);
//				informVO.setInform_time(inform_time);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");

					req.setAttribute("informVO", informVO); // 含有輸入格式錯誤的informVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/inform/update_inform_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				InformService informSvc = new InformService();
				informVO = informSvc.updateInform(inform_status, mem_ID, inform_content, inform_ID);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("informVO", informVO); // 資料庫update成功後,正確的的informVO物件,存入req
				String url = "/back-end/inform/listOneInform.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneInform.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點 4");

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/inform/update_inform_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addInform.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
								

				
				Integer inform_status = null;
				try {
					inform_status= new Integer(req.getParameter("inform_status").trim());
				} catch (NumberFormatException e) {
					inform_status = 0;
					errorMsgs.add("通知狀態請填數字.");
				}
				
				String mem_ID = req.getParameter("mem_ID");
				String mem_IDReg = "^[(a-zA-Z0-9)]{7,20}$";
				if (mem_ID == null || mem_ID.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!mem_ID.trim().matches(mem_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號:只能是英文字母、數字 , 且長度必需在7以內");
	            }
				
				String inform_content = req.getParameter("inform_content").trim();
				if (inform_content == null || inform_content.trim().length() == 0) {
					errorMsgs.add("通知內容: 請勿空白");
				}
				
				InformVO informVO = new InformVO();
				informVO.setInform_status(inform_status);
				informVO.setMem_ID(mem_ID);
				informVO.setInform_content(inform_content);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");
					req.setAttribute("informVO", informVO); // 含有輸入格式錯誤的informVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/inform/addInform.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				InformService informSvc = new InformService();
				informVO = informSvc.addInform(inform_status, mem_ID, inform_content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/inform/listAllInform.jsp";
				System.out.println("檢查點 3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllInform.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("檢查點 4");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/inform/addInform.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllInform.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String inform_ID = new String(req.getParameter("inform_ID"));
				
				/***************************2.開始刪除資料***************************************/
				InformService informSvc = new InformService();
				informSvc.deleteInform(inform_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/inform/listAllInform.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/inform/listAllInform.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
