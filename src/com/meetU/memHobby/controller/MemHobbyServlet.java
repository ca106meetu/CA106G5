package com.meetU.memHobby.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.memHobby.model.*;
import com.meetU.mem.model.*;

@WebServlet("/MemHobbyServlet")
public class MemHobbyServlet extends HttpServlet {
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
				String str1 = req.getParameter("mem_ID");
				String str2[] = req.getParameterValues("hobby_ID");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入會員ID");
				}
//				if (str2== null || (str2.trim()).length() == 0) {
//					errorMsgs.add("請輸入興趣ID");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/memHobby/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_ID = null;
				try {
					mem_ID = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("會員ID格式不正確");
				}
				
				String hobby_ID[] = null;
				System.out.println(str2);
				try {
					hobby_ID = str2;
				} catch (Exception e) {
					errorMsgs.add("興趣ID格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/memHobby/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemHobbyService memHobbySvc = new MemHobbyService();//???
				List<MemHobbyVO> list  = memHobbySvc.getPartOfOneMemHobby(mem_ID);
				
				if (list.isEmpty() == true) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/memHobby/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.getSession().setAttribute("list", list); // 資料庫取出的MemHobbyVO物件,存入req
				String url = "/FrontEnd/memHobby/listSomeMemHobby.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMemHobby.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/memHobby/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // 來自addMemHobby.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str1 = req.getParameter("mem_ID");
				String str2 = req.getParameter("hobby_ID");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入會員ID");
				}
				if (str2== null || (str2.trim()).length() == 0) {
					errorMsgs.add("請輸入興趣ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/memHobby/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_ID = null;
				try {
					mem_ID = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("會員ID格式不正確");
				}
				
				String hobby_ID = null;
				try {
					hobby_ID = new String(str2);
				} catch (Exception e) {
					errorMsgs.add("興趣ID格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/memHobby/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemHobbyService memHobbySvc = new MemHobbyService();//???
				MemHobbyVO memHobbyVO  = memHobbySvc.addMemHobby(mem_ID, hobby_ID);
				
				if (memHobbyVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/memHobby/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memHobbyVO", memHobbyVO); // 資料庫取出的memHobbyVO物件,存入req
				String url = "/FrontEnd/memHobby/listOneMemHobby.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMemHobby.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/memHobby/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getHobbys_For_Update".equals(action)) { // 來自listAllMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_ID = new String(req.getParameter("mem_ID"));
				System.out.println(mem_ID);
				//String hobby_ID[] = new String(req.getParameter("hobby_ID"));
				
				/***************************2.開始查詢資料****************************************/
				MemHobbyService memHobbySvc = new MemHobbyService();
				List<MemHobbyVO> listMemHobbyVO = memHobbySvc.getPartOfOneMemHobby(mem_ID);
				List<String> listHobby_ID = memHobbySvc.getPartOfOneMemHobby2(mem_ID);
				
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.getSession().setAttribute("memVO", memVO);
				req.getSession().setAttribute("listMemHobbyVO", listMemHobbyVO);         // 資料庫取出的MemHobbyVO物件,存入req //???
				req.getSession().setAttribute("listHobby_ID", listHobby_ID);         // 資料庫取出的MemHobbyVO物件,存入req //???
				String url = "/FrontEnd/memHobby/update_memHobby_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_memHobby_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/memHobby/listAllMemHobby.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMemHobby.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_ID = new String(req.getParameter("mem_ID"));
				String hobby_ID = new String(req.getParameter("hobby_ID"));
				
				/***************************2.開始查詢資料****************************************/
				MemHobbyService memHobbySvc = new MemHobbyService();
				MemHobbyVO memHobbyVO = memHobbySvc.getOneMemHobby(mem_ID, hobby_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memHobbyVO", memHobbyVO);         // 資料庫取出的memHobbyVO物件,存入req
				String url = "/FrontEnd/memHobby/update_memHobby_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_memHobby_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/memHobby/listAllMemHobby.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_memHobby_input.jsp的請求 //?
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_ID = new String(req.getParameter("mem_ID").trim());
				String hobby_ID[] = req.getParameterValues("hobby_ID");
				
				List<MemHobbyVO> listMemHobbyVO = new LinkedList<MemHobbyVO>();
				for(Integer i = 0; i < hobby_ID.length; i++) {
					MemHobbyVO memHobbyVO = new MemHobbyVO();
					memHobbyVO.setMem_ID(mem_ID);
					memHobbyVO.setHobby_ID(hobby_ID[i]);
					listMemHobbyVO.add(memHobbyVO);
				}
				
				
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");

					req.setAttribute("list", listMemHobbyVO); // 含有輸入格式錯誤的MemHobbyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/mem/listAllMem.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				MemHobbyService memHobbySvc = new MemHobbyService();
				listMemHobbyVO = memHobbySvc.updateAllHobby(mem_ID, listMemHobbyVO);
				
				MemHobbyService memHobbySvc2 = new MemHobbyService();//???
				List<MemHobbyVO> list  = memHobbySvc2.getPartOfOneMemHobby(mem_ID);
				
				if (list.isEmpty() == true) {
					errorMsgs.add("查無資料");
				}
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.getSession().setAttribute("list", list); // 資料庫update成功後,正確的的MemHobbyVO物件,存入req
				String url = "/FrontEnd/memHobby/listSomeMemHobby.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMemHobby.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點 4");

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/memHobby/update_memHobby_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addMemHobby.jsp的請求  
			
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
				
				String hobby_ID = req.getParameter("hobby_ID");
				String hobby_IDReg = "^[(a-zA-Z0-9_)]{7,20}$";
				if (hobby_ID == null || hobby_ID.trim().length() == 0) {
					errorMsgs.add("興趣ID: 請勿空白");
				} else if(!hobby_ID.trim().matches(hobby_IDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("興趣ID:只能是英文字母、數字 , 且長度必需在7到20之間");
	            }
										
				MemHobbyVO memHobbyVO = new MemHobbyVO();
								System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");
					req.setAttribute("memHobbyVO", memHobbyVO); // 含有輸入格式錯誤的memHobbyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FrontEnd/memHobby/addMemHobby.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemHobbyService memHobbySvc = new MemHobbyService();
				System.out.println(mem_ID);
				System.out.println(hobby_ID);
				memHobbyVO = memHobbySvc.addMemHobby(mem_ID, hobby_ID);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/FrontEnd/memHobby/listAllMemHobby.jsp";
				System.out.println("檢查點 3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMemHobby.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("檢查點 4");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/memHobby/addMemHobby.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllMemHobby.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mem_ID = new String(req.getParameter("mem_ID"));
				String hobby_ID = new String(req.getParameter("hobby_ID"));
				
				/***************************2.開始刪除資料***************************************/
				MemHobbyService memHobbySvc = new MemHobbyService();
				memHobbySvc.deleteMemHobby(mem_ID, hobby_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/FrontEnd/memHobby/listAllMemHobby.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/memHobby/listAllMemHobby.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
