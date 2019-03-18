package com.meetU.friend.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.friend.model.*;

@WebServlet("/FriendServlet")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getSome_For_Display".equals(action)) { // 來自addFriend.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str1 = req.getParameter("mem_ID");
//				String str2 = req.getParameter("friend_mem_ID");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入會員ID");
				}
//				if (str2== null || (str2.trim()).length() == 0) {
//					errorMsgs.add("請輸入對方ID");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_ID = null;
				try {
					mem_ID = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("會員ID格式不正確");
				}
				
//				String friend_mem_ID = null;
//				try {
//					friend_mem_ID = new String(str2);
//				} catch (Exception e) {
//					errorMsgs.add("對方ID格式不正確");
//				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FriendService friendSvc = new FriendService();//???
				List<FriendVO> list  = friendSvc.getPartOfOneFriend(mem_ID);
				
				if (list.isEmpty() == true) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.getSession().setAttribute("list", list); // 資料庫取出的friendVO物件,存入req
				String url = "/FrontEnd/friend/listSomeFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneFriend.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/friend/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // 來自addFriend.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str1 = req.getParameter("mem_ID");
				String str2 = req.getParameter("friend_mem_ID");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入會員ID");
				}
				if (str2== null || (str2.trim()).length() == 0) {
					errorMsgs.add("請輸入對方ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_ID = null;
				try {
					mem_ID = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("會員ID格式不正確");
				}
				
				String friend_mem_ID = null;
				try {
					friend_mem_ID = new String(str2);
				} catch (Exception e) {
					errorMsgs.add("對方ID格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FriendService friendSvc = new FriendService();//???
				FriendVO friendVO  = friendSvc.getOneFriend(mem_ID, friend_mem_ID);
				
				if (friendVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("friendVO", friendVO); // 資料庫取出的friendVO物件,存入req
				String url = "/FrontEnd/friend/listOneFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneFriend.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/friend/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllFriend.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_ID = new String(req.getParameter("mem_ID"));
				String friend_mem_ID = new String(req.getParameter("friend_mem_ID"));
				
				/***************************2.開始查詢資料****************************************/
				FriendService friendSvc = new FriendService();
				FriendVO friendVO = friendSvc.getOneFriend(mem_ID, friend_mem_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("friendVO", friendVO);         // 資料庫取出的friendVO物件,存入req
				String url = "/FrontEnd/friend/update_friend_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_friend_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/friend/listAllFriend.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_friend_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_ID = new String(req.getParameter("mem_ID").trim());
				String friend_mem_ID = new String(req.getParameter("friend_mem_ID").trim());
				
				Integer relation_status = null;
				try {
					relation_status = new Integer(req.getParameter("relation_status").trim());
				} catch (NumberFormatException e) {
					relation_status = 0;
					errorMsgs.add("好友關係狀態請填數字.");
				}
								
				Integer friend_intimate = null;
				try {
					friend_intimate = new Integer(req.getParameter("friend_intimate").trim());
				} catch (NumberFormatException e) {
					relation_status = 0;
					errorMsgs.add("好友親密度請填數字.");
				}
				FriendVO friendVO = new FriendVO();
				friendVO.setMem_ID(mem_ID);
				friendVO.setFriend_mem_ID(friend_mem_ID);
				friendVO.setRelation_status(relation_status);
				friendVO.setFriend_intimate(friend_intimate);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");

					req.setAttribute("friendVO", friendVO); // 含有輸入格式錯誤的friendVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/friend/update_friend_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FriendService friendSvc = new FriendService();
				friendVO = friendSvc.updateFriend(mem_ID, friend_mem_ID, relation_status, friend_intimate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("friendVO", friendVO); // 資料庫update成功後,正確的的friendVO物件,存入req
				String url = "/FrontEnd/friend/listOneFriend.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFriend.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點 4");

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/friend/update_friend_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addfriend.jsp的請求  
			
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
				
				String friend_mem_ID = req.getParameter("friend_mem_ID");
				String friend_mem_IDReg = "^[(a-zA-Z0-9_)]{7,20}$";
				if (friend_mem_ID == null || friend_mem_ID.trim().length() == 0) {
					errorMsgs.add("對方ID: 請勿空白");
				} else if(!friend_mem_ID.trim().matches(friend_mem_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("對方ID:只能是英文字母、數字 , 且長度必需在7到20之間");
	            }
				
				Integer relation_status = null;
				try {
					relation_status = new Integer(req.getParameter("relation_status").trim());
				} catch (NumberFormatException e) {
					relation_status = 0;
					errorMsgs.add("好友關係狀態請填數字.");
				}
								
				Integer friend_intimate = null;
				try {
					friend_intimate = new Integer(req.getParameter("friend_intimate").trim());
				} catch (NumberFormatException e) {
					relation_status = 0;
					errorMsgs.add("好友親密度請填數字.");
				}
				FriendVO friendVO = new FriendVO();
				friendVO.setMem_ID(mem_ID);
				friendVO.setFriend_mem_ID(friend_mem_ID);
				friendVO.setRelation_status(relation_status);
				friendVO.setFriend_intimate(friend_intimate);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");
					req.setAttribute("friendVO", friendVO); // 含有輸入格式錯誤的friendVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/friend/addFriend.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FriendService friendSvc = new FriendService();
				System.out.println(mem_ID);
				System.out.println(friend_mem_ID);
				friendVO = friendSvc.addFriend(mem_ID, friend_mem_ID, relation_status, friend_intimate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/FrontEnd/friend/listAllFriend.jsp";
				System.out.println("檢查點 3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFriend.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("檢查點 4");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/friend/addFriend.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllFriend.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mem_ID = new String(req.getParameter("mem_ID"));
				String friend_mem_ID = new String(req.getParameter("friend_mem_ID"));
				
				/***************************2.開始刪除資料***************************************/
				FriendService friendSvc = new FriendService();
				friendSvc.deleteFriend(mem_ID, friend_mem_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/FrontEnd/friend/listAllFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/friend/listAllFriend.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
