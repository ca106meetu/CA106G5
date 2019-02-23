package com.meetU.gift.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.gift.model.*;
import com.meetU.inform.model.InformService;
import com.meetU.inform.model.InformVO;


public class GiftServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自addGift.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("gift_rec_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入禮物ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/gift/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String gift_rec_ID = null;
				try {
					gift_rec_ID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("禮物編號格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/gift/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				GiftService giftSvc = new GiftService();
				GiftVO giftVO = giftSvc.getOneGift(gift_rec_ID);
				if (giftVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/gift/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("giftVO", giftVO); // 資料庫取出的giftVO物件,存入req
				String url = "/back-end/gift/listOneGift.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneGift.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/gift/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllGift.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String gift_rec_ID = new String(req.getParameter("gift_rec_ID"));
				
				/***************************2.開始查詢資料****************************************/
				GiftService giftSvc = new GiftService();
				GiftVO giftVO = giftSvc.getOneGift(gift_rec_ID);
				System.out.println(giftVO);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("giftVO", giftVO);         // 資料庫取出的giftVO物件,存入req
				String url = "/back-end/gift/update_gift_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_gift_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/gift/listAllGift.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_gift_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String gift_rec_ID = new String(req.getParameter("gift_rec_ID").trim());
				
								
				String recv_ID = req.getParameter("recv_ID");
				String recv_IDReg = "^[(a-zA-Z0-9)]{7,20}$";
				if (recv_ID == null || recv_ID.trim().length() == 0) {
					errorMsgs.add("接收者ID: 請勿空白");
				} else if(!recv_ID.trim().matches(recv_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("接收者ID:只能是英文字母、數字 , 且長度必需在7以上");
	            }
				
				String send_ID = req.getParameter("send_ID");
				String send_IDReg = "^[(a-zA-Z0-9)]{7,20}$";
				if (send_ID == null || send_ID.trim().length() == 0) {
					errorMsgs.add("發送者ID: 請勿空白");
				} else if(!send_ID.trim().matches(send_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("發送者ID:只能是英文字母、數字 , 且長度必需在7以上");
	            }
				
				String prod_ID = req.getParameter("prod_ID");
				String prod_IDReg = "^[(a-zA-Z0-9)]{7,20}$";
				if (prod_ID == null || prod_ID.trim().length() == 0) {
					errorMsgs.add("禮物ID: 請勿空白");
				} else if(!send_ID.trim().matches(send_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("禮物ID:只能是英文字母、數字 , 且長度必需在7以上");
	            }
				Integer gift_quantity = null;
				try {
					gift_quantity = new Integer(req.getParameter("gift_quantity").trim());
				} catch (NumberFormatException e) {
					gift_quantity = 0;
					errorMsgs.add("禮物數量請填數字.");
				}
							
				GiftVO giftVO = new GiftVO();
				
				giftVO.setGift_rec_ID(gift_rec_ID);
				giftVO.setRecv_ID(recv_ID);
				giftVO.setSend_ID(send_ID);
				giftVO.setProd_ID(prod_ID);
				giftVO.setGift_quantity(gift_quantity);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");

					req.setAttribute("giftVO", giftVO); // 含有輸入格式錯誤的giftVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/gift/update_gift_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				GiftService giftSvc = new GiftService();
				giftVO = giftSvc.updateGift(recv_ID, send_ID, prod_ID, gift_quantity, gift_rec_ID);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("giftVO", giftVO); // 資料庫update成功後,正確的的giftVO物件,存入req
				String url = "/back-end/gift/listOneGift.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneGift.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點 4");

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/gift/update_gift_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addGift.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
								
				String recv_ID = req.getParameter("recv_ID");
				String recv_IDReg = "^[(a-zA-Z0-9)]{7,20}$";
				if (recv_ID == null || recv_ID.trim().length() == 0) {
					errorMsgs.add("接收者ID: 請勿空白");
				} else if(!recv_ID.trim().matches(recv_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("接收者ID:只能是英文字母、數字 , 且長度必需在7以上");
	            }
				
				String send_ID = req.getParameter("send_ID");
				String send_IDReg = "^[(a-zA-Z0-9)]{7,20}$";
				if (send_ID == null || send_ID.trim().length() == 0) {
					errorMsgs.add("發送者ID: 請勿空白");
				} else if(!send_ID.trim().matches(send_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("發送者ID:只能是英文字母、數字 , 且長度必需在7以上");
	            }
				
				String prod_ID = req.getParameter("prod_ID");
				String prod_IDReg = "^[(a-zA-Z0-9)]{7,20}$";
				if (prod_ID == null || prod_ID.trim().length() == 0) {
					errorMsgs.add("禮物ID: 請勿空白");
				} else if(!send_ID.trim().matches(send_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("禮物ID:只能是英文字母、數字 , 且長度必需在7以上");
	            }
				Integer gift_quantity = null;
				try {
					gift_quantity = new Integer(req.getParameter("gift_quantity").trim());
				} catch (NumberFormatException e) {
					gift_quantity = 0;
					errorMsgs.add("禮物數量請填數字.");
				}
							
				GiftVO giftVO = new GiftVO();
				
				giftVO.setRecv_ID(recv_ID);
				giftVO.setSend_ID(send_ID);
				giftVO.setProd_ID(prod_ID);
				giftVO.setGift_quantity(gift_quantity);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");
					req.setAttribute("giftVO", giftVO); // 含有輸入格式錯誤的giftVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/gift/addGift.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				GiftService giftSvc = new GiftService();
				giftVO = giftSvc.addGift(recv_ID, send_ID, prod_ID, gift_quantity);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/gift/listAllGift.jsp";
				System.out.println("檢查點 3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllGift.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("檢查點 4");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/gift/addGift.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllGift.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String gift_rec_ID = new String(req.getParameter("gift_rec_ID"));
				
				/***************************2.開始刪除資料***************************************/
				GiftService giftSvc = new GiftService();
				giftSvc.deleteGift(gift_rec_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/gift/listAllGift.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/gift/listAllGift.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
