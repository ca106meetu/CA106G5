package com.meetU.mem.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.meetU.mem.model.*;
import com.meetU.memHobby.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自addMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_ID = null;
				try {
					mem_ID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員ID格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_ID);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
				String url = "/back-end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMem.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mem/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_ID = new String(req.getParameter("mem_ID"));
				
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的memVO物件,存入req
				String url = "/back-end/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_mem_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_mem_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_ID = new String(req.getParameter("mem_ID").trim());//01
				String hobby_ID[] = req.getParameterValues("hobby_ID");
				
				String mem_pw = req.getParameter("mem_pw");//02
				String mem_pwReg = "^[(a-zA-Z0-9_)]{4,30}$";
				if (mem_pw == null || mem_pw.trim().length() == 0) {
					errorMsgs.add("會員密碼: 請勿空白");
				} else if(!mem_pw.trim().matches(mem_pwReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員密碼:只能是英文字母、數字和_ , 且長度必需在4到20之間");
	            }
				
				String mem_name = req.getParameter("mem_name");//03
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				
				String mem_acc = req.getParameter("mem_acc");//04
				String mem_accReg = "^[(a-zA-Z0-9_)]{4,30}$";
				if (mem_acc == null || mem_acc.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if(!mem_acc.trim().matches(mem_accReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英文字母、數字和_ , 且長度必需在4到30之間");
	            }
				
				String mem_nickname = req.getParameter("mem_nickname");//05
				String mem_nicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,30}$";
				if (mem_nickname == null || mem_nickname.trim().length() == 0) {
					errorMsgs.add("會員暱稱: 請勿空白");
				} else if(!mem_nickname.trim().matches(mem_nicknameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員暱稱: 只能是中、英文字母、數字和_ , 且長度必需在1到30之間");
	            }
				
				java.sql.Date mem_bday = null;//06
				try {
					mem_bday = java.sql.Date.valueOf(req.getParameter("mem_bday").trim());
				} catch (IllegalArgumentException e) {
					mem_bday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("會員生日請勿空白");
				}
				
				String mem_email = req.getParameter("mem_email");//07
				//String mem_emailReg = "^[a-zA-Z0-9\\._-@]+$";
				String mem_emailReg = "^[\\.a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("會員電子信箱: 請勿空白");
				} else if(!mem_email.trim().matches(mem_emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("請輸入有效的電子郵件");
	            }
				
				String mem_pho = req.getParameter("mem_pho");//08
				String mem_phoReg = "^[(a-zA-Z0-9_)]$";
				if (mem_pho == null || mem_pho.trim().length() == 0) {
					errorMsgs.add("會員手機: 請勿空白");
				} //else if(!mem_pho.trim().matches(mem_phoReg)) { //以下練習正則(規)表示式(regular-expression)
					//errorMsgs.add("會員電話: 只能是數字");
	            //}
				
				String mem_gend = req.getParameter("mem_gend"); //09
				String mem_gendReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
				if (mem_gend == null || mem_gend.trim().length() == 0) {
					errorMsgs.add("會員性別: 請勿空白");
				} //else if(!mem_gend.trim().matches(mem_gendReg)) { //以下練習正則(規)表示式(regular-expression)
				//	errorMsgs.add("會員性別: 只能是中、英文字母 , 且長度必需在10以內");
	            //}
												
				byte[] mem_pic = null;
				Part part = req.getPart("mem_pic");//10會員大頭照
				Base64.Encoder encoder = Base64.getEncoder();
				if(getFileNameFromPart(part) != null) {
					InputStream in = part.getInputStream();
					mem_pic = new byte[in.available()];
					in.read(mem_pic);
					in.close();
					String encodeText = encoder.encodeToString(mem_pic);
					req.setAttribute("encodeText", encodeText);
				} else {
					if(req.getParameter("encodeText") != null && req.getParameter("encodeText").trim().length() !=0) {
						Base64.Decoder decoder = Base64.getDecoder();
						mem_pic = decoder.decode(req.getParameter("encodeText"));
						String encodeText = encoder.encodeToString(mem_pic);
						req.setAttribute("encodeText", encodeText);
					}
				}
				
				//InputStream in = part.getInputStream();
				//mem_pic = new byte[in.available()];
				//in.read(mem_pic);
				//in.close();
				
				String mem_intro = req.getParameter("mem_intro").trim();//11
				String mem_introReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{0,1000}$";
				//System.out.println(mem_intro);
				//if(!mem_intro.trim().matches(mem_introReg)) {
				//	errorMsgs.add("會員自我介紹:不能超過1000個字");
				//}
				
				Integer mem_code = null;//12會員登入狀態
				try {
					mem_code = new Integer(req.getParameter("mem_code").trim());
				} catch (NumberFormatException e) {
					mem_code = 0;
					errorMsgs.add("會員登入狀態請填數字.");
				}
								
				Integer mem_state = null;//13會員帳號狀態
				try {
					mem_state = new Integer(req.getParameter("mem_state").trim());
				} catch (NumberFormatException e) {
					mem_state = 0;
					errorMsgs.add("會員帳號狀態請填數字.");
				}
				
				java.sql.Date mem_date = null;//14會員註冊日期
				try {
					mem_date = java.sql.Date.valueOf(req.getParameter("mem_date").trim());
				} catch (IllegalArgumentException e) {
					mem_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入會員註冊日期!");
				}
				
				java.sql.Timestamp  mem_sign_day = null; //15會員最後簽到時間
				try {
					mem_sign_day = java.sql.Timestamp.valueOf(req.getParameter("mem_sign_day").trim());
				} catch (IllegalArgumentException e) {
					mem_sign_day = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入會員最後簽到時間!");
				}
				
				Integer mem_login_state = null;//16會員登入狀態
				try {
					mem_login_state = new Integer(req.getParameter("mem_login_state").trim());
				} catch (NumberFormatException e) {
					mem_state = 0;
					errorMsgs.add("會員登入狀態請填數字.");
				}
				
				String mem_address = req.getParameter("mem_address").trim(); //17會員居住地
				if (mem_address == null || mem_address.trim().length() == 0) {
					errorMsgs.add("會員居住地: 請勿空白");
				}
				
				java.sql.Timestamp last_pair = null; //18上次配對時間
				try {
					last_pair = java.sql.Timestamp.valueOf(req.getParameter("last_pair").trim());
				} catch (IllegalArgumentException e) {
					last_pair = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入會員上次配對時間!");
				}
				
				String mem_hobby = req.getParameter("mem_hobby").trim(); //19會員興趣
				//if (mem_address == null || mem_address.trim().length() == 0) {
				//	errorMsgs.add("會員興趣: 請勿空白");
				//}
				List<MemHobbyVO> listMemHobbyVO = new LinkedList<MemHobbyVO>();
				for(Integer i = 0; i < hobby_ID.length; i++) {
					MemHobbyVO memHobbyVO = new MemHobbyVO();
					memHobbyVO.setMem_ID(mem_ID);
					memHobbyVO.setHobby_ID(hobby_ID[i]);
					listMemHobbyVO.add(memHobbyVO);
				}
				
				byte[] mem_QRCODE = null;
				Part part2 = req.getPart("mem_QRCODE");//20會員QRCODE
				Base64.Encoder encoder2 = Base64.getEncoder();
				if(getFileNameFromPart(part2) != null) {
					InputStream in2 = part2.getInputStream();
					mem_QRCODE = new byte[in2.available()];
					in2.read(mem_QRCODE);
					in2.close();
					String encodeText2 = encoder2.encodeToString(mem_QRCODE);
					req.setAttribute("encodeText2", encodeText2);
				} else {
					if(req.getParameter("encodeText2") != null && req.getParameter("encodeText2").trim().length() !=0) {
						Base64.Decoder decoder2 = Base64.getDecoder();
						mem_pic = decoder2.decode(req.getParameter("encodeText2"));
						String encodeText2 = encoder2.encodeToString(mem_QRCODE);
						req.setAttribute("encodeText2", encodeText2);
					}
				}
				
				//InputStream in2 = part.getInputStream();
				//mem_QRCODE = new byte[in2.available()];
				//in2.read(mem_QRCODE);
				//in2.close();
				
				Integer mem_get_point = null;//21會員點數
				try {
					mem_get_point = new Integer(req.getParameter("mem_get_point").trim());
				} catch (NumberFormatException e) {
					mem_state = 0;
					errorMsgs.add("會員點數請填數字.");
				}
				
				MemVO memVO = new MemVO();
				memVO.setMem_pw(mem_pw);
				memVO.setMem_name(mem_name);
				memVO.setMem_acc(mem_acc);
				memVO.setMem_nickname(mem_nickname);
				memVO.setMem_bday(mem_bday);
				memVO.setMem_email(mem_email);
				memVO.setMem_pho(mem_pho);
				memVO.setMem_gend(mem_gend);
				memVO.setMem_pic(mem_pic);
				memVO.setMem_intro(mem_intro);
				
				memVO.setMem_code(mem_code);
				memVO.setMem_state(mem_state);
				memVO.setMem_date(mem_date);
				memVO.setMem_sign_day(mem_sign_day);
				memVO.setMem_login_state(mem_login_state);
				memVO.setMem_address(mem_address);
				memVO.setLast_pair(last_pair);
				memVO.setMem_hobby(mem_hobby);
				memVO.setMem_QRCODE(mem_QRCODE);
				memVO.setMem_get_point(mem_get_point);
				memVO.setMem_ID(mem_ID);
				
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");

					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_pw, mem_name, mem_acc, mem_nickname, mem_bday,
						                 mem_email, mem_pho, mem_gend, mem_pic, mem_intro,
						                 mem_code, mem_state, mem_date, mem_sign_day, mem_login_state,
						                 mem_address, last_pair, mem_hobby, mem_QRCODE, mem_get_point,
						                 mem_ID);
				MemHobbyService memHobbySvc = new MemHobbyService();
				listMemHobbyVO = memHobbySvc.updateAllHobby(mem_ID, listMemHobbyVO);
				
				MemHobbyService memHobbySvc2 = new MemHobbyService();
				List<MemHobbyVO> list = memHobbySvc2.getPartOfOneMemHobby(mem_ID);
				if (list.isEmpty() == true) {
					errorMsgs.add("查無資料");
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的memVO物件,存入req
				req.setAttribute("list", list);
				String url = "/back-end/mem/listOneMem.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點 4");

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addMem.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
								
				String mem_pw = req.getParameter("mem_pw");//02
				String mem_pwReg = "^[(a-zA-Z0-9_)]{4,30}$";
				if (mem_pw == null || mem_pw.trim().length() == 0) {
					errorMsgs.add("會員密碼: 請勿空白");
				} else if(!mem_pw.trim().matches(mem_pwReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員密碼:只能是英文字母、數字和_ , 且長度必需在4到20之間");
	            }
				
				String mem_name = req.getParameter("mem_name");//03
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				
				String mem_acc = req.getParameter("mem_acc");//04
				String mem_accReg = "^[(a-zA-Z0-9_)]{4,30}$";
				if (mem_acc == null || mem_acc.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if(!mem_acc.trim().matches(mem_accReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英文字母、數字和_ , 且長度必需在4到30之間");
	            }
				
				String mem_nickname = req.getParameter("mem_nickname");//05
				String mem_nicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,30}$";
				if (mem_nickname == null || mem_nickname.trim().length() == 0) {
					errorMsgs.add("會員暱稱: 請勿空白");
				} else if(!mem_nickname.trim().matches(mem_nicknameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員暱稱: 只能是中、英文字母、數字和_ , 且長度必需在1到30之間");
	            }
				
				java.sql.Date mem_bday = null;//06
				try {
					mem_bday = java.sql.Date.valueOf(req.getParameter("mem_bday").trim());
				} catch (IllegalArgumentException e) {
					mem_bday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("會員生日請勿空白");
				}
				
				String mem_email = req.getParameter("mem_email");//07
				String mem_emailReg = "^[a-zA-Z0-9\\._-@]$";
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("會員電子信箱: 請勿空白");
				}// else if(!mem_email.trim().matches(mem_emailReg)) { //以下練習正則(規)表示式(regular-expression)
				//	errorMsgs.add("會員電子信箱: 只能是英文字母、數字和_");
	            //}
				
				String mem_pho = req.getParameter("mem_pho");//08
				String mem_phoReg = "^[(a-zA-Z0-9_)]$";
				if (mem_pho == null || mem_pho.trim().length() == 0) {
					errorMsgs.add("會員手機: 請勿空白");
				} //else if(!mem_pho.trim().matches(mem_phoReg)) { //以下練習正則(規)表示式(regular-expression)
					//errorMsgs.add("會員電話: 只能是數字");
	            //}
				
				String mem_gend = req.getParameter("mem_gend"); //09
				String mem_gendReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
				if (mem_gend == null || mem_gend.trim().length() == 0) {
					errorMsgs.add("會員性別: 請勿空白");
				} //else if(!mem_gend.trim().matches(mem_gendReg)) { //以下練習正則(規)表示式(regular-expression)
				//	errorMsgs.add("會員性別: 只能是中、英文字母 , 且長度必需在10以內");
	            //}
												
				byte[] mem_pic = null;
				Part part = req.getPart("mem_pic");//10會員大頭照
				Base64.Encoder encoder = Base64.getEncoder();
				if(getFileNameFromPart(part) != null) {
					InputStream in = part.getInputStream();
					mem_pic = new byte[in.available()];
					in.read(mem_pic);
					in.close();
					String encodeText = encoder.encodeToString(mem_pic);
					req.setAttribute("encodeText", encodeText);
				} else {
					if(req.getParameter("encodeText") != null && req.getParameter("encodeText").trim().length() !=0) {
						Base64.Decoder decoder = Base64.getDecoder();
						mem_pic = decoder.decode(req.getParameter("encodeText"));
						String encodeText = encoder.encodeToString(mem_pic);
						req.setAttribute("encodeText", encodeText);
					}
				}
				
				//InputStream in = part.getInputStream();
				//mem_pic = new byte[in.available()];
				//in.read(mem_pic);
				//in.close();
				
				StringBuffer bf_intro = new StringBuffer();
				
				String mem_intro = req.getParameter("mem_intro").trim();//11
				bf_intro.append(mem_intro);
				String mem_introReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{0,1000}$";
				//System.out.println(mem_intro);
				if(!mem_intro.trim().matches(mem_introReg)) {
					errorMsgs.add("會員自我介紹:不能超過1000個字");
				}
				
				
				Integer mem_code = null;//12
				try {
					mem_code = new Integer(req.getParameter("mem_code").trim());
				} catch (NumberFormatException e) {
					mem_code = 0;
					errorMsgs.add("會員登入狀態請填數字.");
				}
								
				Integer mem_state = null;//13會員帳號狀態
				try {
					mem_state = new Integer(req.getParameter("mem_state").trim());
				} catch (NumberFormatException e) {
					mem_state = 0;
					errorMsgs.add("會員帳號狀態請填數字.");
				}
				
				java.sql.Date mem_date = null;//14會員註冊日期
				try {
					mem_date = java.sql.Date.valueOf(req.getParameter("mem_date").trim());
				} catch (IllegalArgumentException e) {
					mem_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入會員註冊日期!");
				}
				
				java.sql.Timestamp  mem_sign_day = null; //15會員最後簽到時間
				try {
					mem_sign_day = java.sql.Timestamp.valueOf(req.getParameter("mem_sign_day").trim());
				} catch (IllegalArgumentException e) {
					mem_sign_day = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入會員最後簽到時間!");
				}
				
				Integer mem_login_state = null;//16會員登入狀態
				try {
					mem_login_state = new Integer(req.getParameter("mem_login_state").trim());
				} catch (NumberFormatException e) {
					mem_state = 0;
					errorMsgs.add("會員登入狀態請填數字.");
				}
				
				String mem_address = req.getParameter("mem_address").trim(); //17會員居住地
				if (mem_address == null || mem_address.trim().length() == 0) {
					errorMsgs.add("會員居住地: 請勿空白");
				}
				
				java.sql.Timestamp last_pair = null; //18上次配對時間
				try {
					last_pair = java.sql.Timestamp.valueOf(req.getParameter("last_pair").trim());
				} catch (IllegalArgumentException e) {
					last_pair = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入會員上次配對時間!");
				}
				
				String mem_hobby = req.getParameter("mem_hobby").trim(); //19會員興趣
				if (mem_address == null || mem_address.trim().length() == 0) {
					errorMsgs.add("會員興趣: 請勿空白");
				}
				
				byte[] mem_QRCODE = null;
				Part part2 = req.getPart("mem_QRCODE");//20會員QRCODE
				Base64.Encoder encoder2 = Base64.getEncoder();
				if(getFileNameFromPart(part2) != null) {
					InputStream in2 = part2.getInputStream();
					mem_QRCODE = new byte[in2.available()];
					in2.read(mem_QRCODE);
					in2.close();
					String encodeText2 = encoder2.encodeToString(mem_QRCODE);
					req.setAttribute("encodeText2", encodeText2);
				} else {
					if(req.getParameter("encodeText2") != null && req.getParameter("encodeText2").trim().length() !=0) {
						Base64.Decoder decoder2 = Base64.getDecoder();
						mem_pic = decoder2.decode(req.getParameter("encodeText2"));
						String encodeText2 = encoder2.encodeToString(mem_QRCODE);
						req.setAttribute("encodeText2", encodeText2);
					}
				}
				
				//InputStream in2 = part.getInputStream();
				//mem_QRCODE = new byte[in2.available()];
				//in2.read(mem_QRCODE);
				//in2.close();
				
				Integer mem_get_point = null;//21會員點數
				try {
					mem_get_point = new Integer(req.getParameter("mem_get_point").trim());
				} catch (NumberFormatException e) {
					mem_state = 0;
					errorMsgs.add("會員點數請填數字.");
				}
				
				MemVO memVO = new MemVO();
				memVO.setMem_pw(mem_pw);
				memVO.setMem_name(mem_name);
				memVO.setMem_acc(mem_acc);
				memVO.setMem_nickname(mem_nickname);
				memVO.setMem_bday(mem_bday);
				memVO.setMem_email(mem_email);
				memVO.setMem_pho(mem_pho);
				memVO.setMem_gend(mem_gend);
				memVO.setMem_pic(mem_pic);
				memVO.setMem_intro(mem_intro);
				
				memVO.setMem_code(mem_code);
				memVO.setMem_state(mem_state);
				memVO.setMem_date(mem_date);
				memVO.setMem_sign_day(mem_sign_day);
				memVO.setMem_login_state(mem_login_state);
				memVO.setMem_address(mem_address);
				memVO.setLast_pair(last_pair);
				memVO.setMem_hobby(mem_hobby);
				memVO.setMem_QRCODE(mem_QRCODE);
				memVO.setMem_get_point(mem_get_point);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(mem_pw, mem_name, mem_acc, mem_nickname, mem_bday,
						              mem_email, mem_pho, mem_gend, mem_pic, mem_intro,
						              mem_code, mem_state, mem_date, mem_sign_day, mem_login_state,
						              mem_address, last_pair, mem_hobby, mem_QRCODE, mem_get_point);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/mem/listAllMem.jsp";
				System.out.println("檢查點 3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMem.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("檢查點 4");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mem/addMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllMem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mem_ID = new String(req.getParameter("mem_ID"));
				
				/***************************2.開始刪除資料***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(mem_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
