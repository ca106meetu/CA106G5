package com.meetU.giftbox.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.giftbox.model.*;


public class GiftboxServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // 來自addGiftbox.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str1 = req.getParameter("mem_ID");
				String str2 = req.getParameter("prod_ID");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入會員ID");
				}
				if (str2== null || (str2.trim()).length() == 0) {
					errorMsgs.add("請輸入禮物ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/giftbox/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_ID = null;
				try {
					mem_ID = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("會員ID格式不正確");
				}
				
				String prod_ID = null;
				try {
					prod_ID = new String(str2);
				} catch (Exception e) {
					errorMsgs.add("禮物ID格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/giftbox/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				GiftboxService giftboxSvc = new GiftboxService();
				GiftboxVO giftboxVO = giftboxSvc.getOneGiftbox(mem_ID, prod_ID);
				if (giftboxVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/giftbox/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("giftboxVO", giftboxVO); // 資料庫取出的giftboxVO物件,存入req
				String url = "/back-end/giftbox/listOneGiftbox.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneGiftbox.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/giftbox/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllGiftbox.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_ID = new String(req.getParameter("mem_ID"));
				String prod_ID = new String(req.getParameter("prod_ID"));
				
				/***************************2.開始查詢資料****************************************/
				GiftboxService giftboxSvc = new GiftboxService();
				GiftboxVO giftboxVO = giftboxSvc.getOneGiftbox(mem_ID, prod_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("giftboxVO", giftboxVO);         // 資料庫取出的giftboxVO物件,存入req
				String url = "/back-end/giftbox/update_giftbox_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_giftbox_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/giftbox/listAllGiftbox.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_giftbox_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_ID = new String(req.getParameter("mem_ID").trim());
				String prod_ID = new String(req.getParameter("prod_ID").trim());
				
				Integer gift_quantity = null;
				try {
					gift_quantity = new Integer(req.getParameter("gift_quantity").trim());
				} catch (NumberFormatException e) {
					gift_quantity = 0;
					errorMsgs.add("員工帳號狀態請填數字.");
				}
								
		        GiftboxVO giftboxVO = new GiftboxVO();
				giftboxVO.setMem_ID(mem_ID);
				giftboxVO.setProd_ID(prod_ID);
				giftboxVO.setGift_quantity(gift_quantity);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");

					req.setAttribute("giftboxVO", giftboxVO); // 含有輸入格式錯誤的giftboxVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/giftbox/update_giftbox_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				GiftboxService giftboxSvc = new GiftboxService();
				giftboxVO = giftboxSvc.updateGiftbox(mem_ID, prod_ID, gift_quantity);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("giftboxVO", giftboxVO); // 資料庫update成功後,正確的的giftboxVO物件,存入req
				String url = "/back-end/giftbox/listOneGiftbox.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneGiftbox.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點 4");

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/giftbox/update_giftbox_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addGiftbox.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_ID = req.getParameter("mem_ID");
				String mem_IDReg = "^[(a-zA-Z0-9_)]{7,20}$";
				if (mem_ID == null || mem_ID.trim().length() == 0) {
					errorMsgs.add("會員ID: 請勿空白");
				} else if(!mem_ID.trim().matches(mem_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員ID:只能是英文字母、數字 , 且長度必需在7到20之間");
	            }
				
				String prod_ID = req.getParameter("mem_ID");
				String prod_IDReg = "^[(a-zA-Z0-9_)]{7,20}$";
				if (prod_ID == null || prod_ID.trim().length() == 0) {
					errorMsgs.add("禮物ID: 請勿空白");
				} else if(!prod_ID.trim().matches(prod_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("禮物ID:只能是英文字母、數字 , 且長度必需在7到20之間");
	            }
				
				Integer gift_quantity = null;
				try {
					gift_quantity = new Integer(req.getParameter("gift_quantity").trim());
				} catch (NumberFormatException e) {
					gift_quantity = 0;
					errorMsgs.add("禮物數量請填數字.");
				}
				
				GiftboxVO giftboxVO = new GiftboxVO();
				giftboxVO.setGift_quantity(gift_quantity);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");
					req.setAttribute("giftboxVO", giftboxVO); // 含有輸入格式錯誤的giftboxVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/giftbox/addGiftbox.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				GiftboxService giftboxSvc = new GiftboxService();
				giftboxVO = giftboxSvc.addGiftbox(mem_ID, prod_ID, gift_quantity);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/giftbox/listAllGiftbox.jsp";
				System.out.println("檢查點 3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllGiftbox.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("檢查點 4");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/giftbox/addGiftbox.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllGiftbox.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mem_ID = new String(req.getParameter("mem_ID"));
				String prod_ID = new String(req.getParameter("prod_ID"));
				
				/***************************2.開始刪除資料***************************************/
				GiftboxService giftboxSvc = new GiftboxService();
				giftboxSvc.deleteGiftbox(mem_ID, prod_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/giftbox/listAllGiftbox.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/giftbox/listAllGiftbox.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
