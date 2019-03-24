package com.meetU.mem.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.meetU.mem.model.*;
import com.meetU.memHobby.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/FrontMemServlet")
public class FrontMemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("update".equals(action)) { // 來自update_mem_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				MemVO memVO = (MemVO)req.getSession().getAttribute("memVO"); 
				
				String mem_ID = new String(req.getParameter("mem_ID").trim());//01
								
				String mem_pw = req.getParameter("mem_pw");//02
				System.out.println("mem_pw = "+mem_pw);
				String mem_pwReg = "^[(a-zA-Z0-9_)]{4,30}$";
				if (mem_pw == null || mem_pw.trim().length() == 0) {
					errorMsgs.add("會員密碼: 請勿空白");
				} else if(!mem_pw.trim().matches(mem_pwReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員密碼:只能是英文字母、數字和_ , 且長度必需在4到20之間");
	            }
				
				String mem_name = req.getParameter("mem_name");//03
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{0,30}$";
//				if (mem_name == null || mem_name.trim().length() == 0) {
//					errorMsgs.add("會員姓名: 請勿空白");
//				} else 
				if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需30字以內");
	            }
				
				String mem_acc = req.getParameter("mem_acc");//04
				String mem_accReg = "^[(a-zA-Z0-9_)]{3,30}$";
				if (mem_acc == null || mem_acc.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if(!mem_acc.trim().matches(mem_accReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英文字母、數字和_ , 且長度必需在3到30之間");
	            }
				
				String mem_nickname = req.getParameter("mem_nickname");//05
				String mem_nicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{0,30}$";
//				if (mem_nickname == null || mem_nickname.trim().length() == 0) {
//					errorMsgs.add("會員暱稱: 請勿空白");
//				} else 
				if(!mem_nickname.trim().matches(mem_nicknameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員暱稱: 只能是中、英文字母、數字和_ , 且長度必需在30字以內");
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
				String mem_phoReg = "^[(0-9_)]{0,20}$";
//				if (mem_pho == null || mem_pho.trim().length() == 0) {
//					errorMsgs.add("會員手機: 請勿空白");
//				} else 
				if(!mem_pho.trim().matches(mem_phoReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員電話: 只能是數字, 且長度必需在20以內");
	            }
				
				String mem_gend = req.getParameter("mem_gend"); //09
				String mem_gendReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{0,10}$";
				//if (mem_gend == null || mem_gend.trim().length() == 0) {
				//	errorMsgs.add("會員性別: 請勿空白");
				//}
				if(!mem_gend.trim().matches(mem_gendReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員性別: 只能是中、英文字母 , 且長度必需在10以內");
	            }
				
				Base64.Encoder encoder = Base64.getEncoder();
				byte[] mem_pic = null;
//				if (memVO.getMem_pic() != null) {
//					String encodeText = encoder.encodeToString(memVO.getMem_pic());
//					req.setAttribute("encodeText", encodeText);
//				}
				Part part = req.getPart("mem_pic");//10會員大頭照
				if(getFileNameFromPart(part) != null) {
					InputStream in = part.getInputStream();
					mem_pic = new byte[in.available()];
					in.read(mem_pic);
					String encodeText = encoder.encodeToString(mem_pic);
					req.setAttribute("encodeText", encodeText);
					in.close();
				} else {
					if(req.getParameter("encodeText") != null && req.getParameter("encodeText").trim().length() !=0) {
						Base64.Decoder decoder = Base64.getDecoder();
						mem_pic = decoder.decode(req.getParameter("encodeText"));
						String encodeText = encoder.encodeToString(mem_pic);
						req.setAttribute("encodeText", encodeText);
					}
				}
				
				String mem_intro = req.getParameter("mem_intro").trim();//11
				String mem_introReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{0,1000}$";
				if(!mem_intro.trim().matches(mem_introReg)) {
					errorMsgs.add("會員自我介紹:不能超過1000個字");
				}
				
				Integer mem_code = 1;//12
								
				Integer mem_state = 1;//13會員帳號狀態
				
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
					mem_login_state = 0;
					errorMsgs.add("會員登入狀態請填數字.");
				}
				
				String mem_address = req.getParameter("mem_address").trim(); //17會員居住地
				String mem_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (mem_address == null || mem_address.trim().length() == 0) {
					errorMsgs.add("會員居住地: 請勿空白");
				} else if(!mem_address.trim().matches(mem_addressReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員居住地:只能是中、英文字母 , 且長度必需在100以內");
	            }
				
				java.sql.Timestamp last_pair = null; //18上次配對時間
				
				String mem_hobby = req.getParameter("mem_hobby").trim(); //19會員興趣

				
				byte[] mem_QRCODE = null;
				Part part2 = req.getPart("mem_QRCODE");//20會員QRCODE
				Base64.Encoder encoder2 = Base64.getEncoder();
//				if(getFileNameFromPart(part2) != null) {
//					InputStream in2 = part2.getInputStream();
//					mem_QRCODE = new byte[in2.available()];
//					in2.read(mem_QRCODE);
//					in2.close();
//					String encodeText2 = encoder2.encodeToString(mem_QRCODE);
//					req.setAttribute("encodeText2", encodeText2);
//				} else {
//					if(req.getParameter("encodeText2") != null && req.getParameter("encodeText2").trim().length() !=0) {
//						Base64.Decoder decoder2 = Base64.getDecoder();
//						mem_pic = decoder2.decode(req.getParameter("encodeText2"));
//						String encodeText2 = encoder2.encodeToString(mem_QRCODE);
//						req.setAttribute("encodeText2", encodeText2);
//					}
//				}
				
				
				Integer mem_get_point = null;//21會員點數
				try {
					mem_get_point = new Integer(req.getParameter("mem_get_point").trim());
				} catch (NumberFormatException e) {
					mem_get_point = 0;
					errorMsgs.add("會員點數請填數字.");
				}
				
				//MemVO memVO = new MemVO();
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
							.getRequestDispatcher("/FrontEnd/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem2(mem_pw, mem_name, mem_acc, mem_nickname, mem_bday,
						                 mem_email, mem_pho, mem_gend, mem_pic, mem_intro,
						                 mem_code, mem_state, mem_date, mem_sign_day, mem_login_state,
						                 mem_address, last_pair, mem_hobby, mem_get_point, mem_ID);

				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的memVO物件,存入req
				
				String url = "/FrontEnd/lorenTest/test.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點 4");

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("reg".equals(action)) { // 來自reg_mem_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				MemVO memVO = (MemVO)req.getSession().getAttribute("memVO"); 
				
				String mem_ID = new String(req.getParameter("mem_ID").trim());//01
								
				String mem_pw = req.getParameter("mem_pw");//02
				System.out.println("mem_pw = "+mem_pw);
				String mem_pwReg = "^[(a-zA-Z0-9_)]{4,30}$";
				if (mem_pw == null || mem_pw.trim().length() == 0) {
					errorMsgs.add("會員密碼: 請勿空白");
				} else if(!mem_pw.trim().matches(mem_pwReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員密碼:只能是英文字母、數字和_ , 且長度必需在4到20之間");
	            }
				
				String mem_name = req.getParameter("mem_name");//03
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{0,30}$";
//				if (mem_name == null || mem_name.trim().length() == 0) {
//					errorMsgs.add("會員姓名: 請勿空白");
//				} else 
				if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需30字以內");
	            }
				
				String mem_acc = req.getParameter("mem_acc");//04
				String mem_accReg = "^[(a-zA-Z0-9_)]{3,30}$";
				if (mem_acc == null || mem_acc.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if(!mem_acc.trim().matches(mem_accReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英文字母、數字和_ , 且長度必需在3到30之間");
	            }
				
				String mem_nickname = req.getParameter("mem_nickname");//05
				String mem_nicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{0,30}$";
//				if (mem_nickname == null || mem_nickname.trim().length() == 0) {
//					errorMsgs.add("會員暱稱: 請勿空白");
//				} else 
				if(!mem_nickname.trim().matches(mem_nicknameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員暱稱: 只能是中、英文字母、數字和_ , 且長度必需在30字以內");
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
				String mem_phoReg = "^[(0-9_)]{0,20}$";
//				if (mem_pho == null || mem_pho.trim().length() == 0) {
//					errorMsgs.add("會員手機: 請勿空白");
//				} else 
				if(!mem_pho.trim().matches(mem_phoReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員電話: 只能是數字, 且長度必需在20以內");
	            }
				
				String mem_gend = req.getParameter("mem_gend"); //09
				String mem_gendReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{0,10}$";
				//if (mem_gend == null || mem_gend.trim().length() == 0) {
				//	errorMsgs.add("會員性別: 請勿空白");
				//}
				if(!mem_gend.trim().matches(mem_gendReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員性別: 只能是中、英文字母 , 且長度必需在10以內");
	            }
				
				Base64.Encoder encoder = Base64.getEncoder();
				byte[] mem_pic = null;
//				if (memVO.getMem_pic() != null) {
//					String encodeText = encoder.encodeToString(memVO.getMem_pic());
//					req.setAttribute("encodeText", encodeText);
//				}
				Part part = req.getPart("mem_pic");//10會員大頭照
				if(getFileNameFromPart(part) != null) {
					InputStream in = part.getInputStream();
					mem_pic = new byte[in.available()];
					in.read(mem_pic);
					String encodeText = encoder.encodeToString(mem_pic);
					req.setAttribute("encodeText", encodeText);
					in.close();
				} else {
					if(req.getParameter("encodeText") != null && req.getParameter("encodeText").trim().length() !=0) {
						Base64.Decoder decoder = Base64.getDecoder();
						mem_pic = decoder.decode(req.getParameter("encodeText"));
						String encodeText = encoder.encodeToString(mem_pic);
						req.setAttribute("encodeText", encodeText);
					}
				}
				
				String mem_intro = req.getParameter("mem_intro").trim();//11
				String mem_introReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{0,1000}$";
				if(!mem_intro.trim().matches(mem_introReg)) {
					errorMsgs.add("會員自我介紹:不能超過1000個字");
				}
				
				Integer mem_code = 1;//12
								
				Integer mem_state = 1;//13會員帳號狀態
				
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
					mem_login_state = 0;
					errorMsgs.add("會員登入狀態請填數字.");
				}
				
				String mem_address = req.getParameter("mem_address").trim(); //17會員居住地
				String mem_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (mem_address == null || mem_address.trim().length() == 0) {
					errorMsgs.add("會員居住地: 請勿空白");
				} else if(!mem_address.trim().matches(mem_addressReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員居住地:只能是中、英文字母 , 且長度必需在100以內");
	            }
				
				java.sql.Timestamp last_pair = null; //18上次配對時間
				
				String mem_hobby = req.getParameter("mem_hobby").trim(); //19會員興趣

				
				byte[] mem_QRCODE = null;
				Part part2 = req.getPart("mem_QRCODE");//20會員QRCODE
				Base64.Encoder encoder2 = Base64.getEncoder();
//				if(getFileNameFromPart(part2) != null) {
//					InputStream in2 = part2.getInputStream();
//					mem_QRCODE = new byte[in2.available()];
//					in2.read(mem_QRCODE);
//					in2.close();
//					String encodeText2 = encoder2.encodeToString(mem_QRCODE);
//					req.setAttribute("encodeText2", encodeText2);
//				} else {
//					if(req.getParameter("encodeText2") != null && req.getParameter("encodeText2").trim().length() !=0) {
//						Base64.Decoder decoder2 = Base64.getDecoder();
//						mem_pic = decoder2.decode(req.getParameter("encodeText2"));
//						String encodeText2 = encoder2.encodeToString(mem_QRCODE);
//						req.setAttribute("encodeText2", encodeText2);
//					}
//				}
				
				
				Integer mem_get_point = null;//21會員點數
				try {
					mem_get_point = new Integer(req.getParameter("mem_get_point").trim());
				} catch (NumberFormatException e) {
					mem_get_point = 0;
					errorMsgs.add("會員點數請填數字.");
				}
				
				//MemVO memVO = new MemVO();
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
							.getRequestDispatcher("/FrontEnd/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem2(mem_pw, mem_name, mem_acc, mem_nickname, mem_bday,
						                 mem_email, mem_pho, mem_gend, mem_pic, mem_intro,
						                 mem_code, mem_state, mem_date, mem_sign_day, mem_login_state,
						                 mem_address, last_pair, mem_hobby, mem_get_point, mem_ID);

				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.getSession().setAttribute("memVO", memVO);
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的memVO物件,存入req
				String url = "/FrontEnd/lorenTest/test.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點 4");

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FrontEnd/mem/reg_mem_input.jsp");
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
