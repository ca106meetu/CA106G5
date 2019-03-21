package com.meetU.emp.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.meetU.emp.model.*;
import com.meetU.product.model.ProductService;
import com.meetU.product.model.ProductVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException { 
		doPost(req, res);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String emp_ID = req.getParameter("emp_ID");
				
				
				
				if (emp_ID == null || (emp_ID.trim()).length() == 0) {
					errorMsgs.add("請輸入員工ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_ID);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String emp_ID = new String(req.getParameter("emp_ID"));
				
				
				
				/***************************2.開始查詢資料****************************************/
				/*===========================*/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_ID);
				Base64.Encoder encoder = Base64.getEncoder();
				
				if(empVO.getEmp_pic() != null) {
				String encodeText = encoder.encodeToString(empVO.getEmp_pic());
				req.setAttribute("encodeText", encodeText);
				}
				
				/*===========================*/
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String emp_ID = new String(req.getParameter("emp_ID").trim());
				
				String emp_pw = req.getParameter("emp_pw");
				String emp_pwReg = "^[(a-zA-Z0-9_)]{4,20}$";
				if (emp_pw == null || emp_pw.trim().length() == 0) {
					errorMsgs.add("員工密碼: 請勿空白");
				} else if(!emp_pw.trim().matches(emp_pwReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工密碼:只能是英文字母、數字和_ , 且長度必需在4到20之間");
	            }
				
				String emp_name = req.getParameter("emp_name");
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!emp_name.trim().matches(emp_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				
				java.sql.Date emp_bday = null;
				try {
					emp_bday = java.sql.Date.valueOf(req.getParameter("emp_bday").trim());
				} catch (IllegalArgumentException e) {
					emp_bday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("員工生日請勿空白");
				}
				
				String emp_email = req.getParameter("emp_email");
				String emp_emailReg = "^[a-zA-Z0-9\\._-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
				if (emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.add("員工電子信箱: 請勿空白");
				} else if(!emp_email.trim().matches(emp_emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工電子信箱: 只能是英文字母、數字和_,且必需包含@符號");
	            }
				
				String emp_pho = req.getParameter("emp_pho");
				String emp_phoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
				if (emp_pho == null || emp_pho.trim().length() == 0) {
					errorMsgs.add("員工電話: 請勿空白");
				} //else if(!emp_pho.trim().matches(emp_phoReg)) { //以下練習正則(規)表示式(regular-expression)
					//errorMsgs.add("員工電話: 只能是數字");
	            //}
				
				String emp_gend = req.getParameter("emp_gend");
				String emp_gendReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
				if (emp_gend == null || emp_gend.trim().length() == 0) {
					errorMsgs.add("員工性別: 請勿空白");
				} //else if(!emp_gend.trim().matches(emp_gendReg)) { //以下練習正則(規)表示式(regular-expression)
				//	errorMsgs.add("員工性別: 只能是中、英文字母 , 且長度必需在10以內");
	            //}
				
				/*=======================*/
				InputStream in = null;
				byte[] emp_pic = null;

				Part part = req.getPart("emp_pic");
				if(getFileNameFromPart(part) != null) {
					in = part.getInputStream();
					emp_pic = new byte[in.available()];
					in.read(emp_pic);
					in.close();
				}else {
					emp_pic = new EmpService().getOneEmp(emp_ID).getEmp_pic();
				}
				/*=======================*/
				
				
				//InputStream in = part.getInputStream();
				//emp_pic = new byte[in.available()];
				//in.read(emp_pic);
				//in.close();
				
				Integer emp_state = null;
				try {
					emp_state = new Integer(req.getParameter("emp_state").trim());
				} catch (NumberFormatException e) {
					emp_state = 0;
					errorMsgs.add("員工帳號狀態請填數字.");
				}
				
				java.sql.Date emp_hday = null;
				try {
					emp_hday = java.sql.Date.valueOf(req.getParameter("emp_hday").trim());
				} catch (IllegalArgumentException e) {
					emp_hday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入員工就職日期!");
				}
				
				String emp_address = req.getParameter("emp_address").trim();
				if (emp_address == null || emp_address.trim().length() == 0) {
					errorMsgs.add("員工居住地: 請勿空白");
				}	
				
				//String auth_ID = new String(req.getParameter("auth_ID").trim());

				EmpVO empVO = new EmpVO();
				empVO.setEmp_ID(emp_ID);
				empVO.setEmp_pw(emp_pw);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_bday(emp_bday);
				empVO.setEmp_email(emp_email);
				empVO.setEmp_pho(emp_pho);
				empVO.setEmp_gend(emp_gend);
				empVO.setEmp_pic(emp_pic);//??
				empVO.setEmp_state(emp_state);
				empVO.setEmp_hday(emp_hday);
				empVO.setEmp_address(emp_address);
				System.out.println("檢查點 1");
				
				Base64.Encoder encoder = Base64.getEncoder();
				String encodeText = encoder.encodeToString(emp_pic);
				req.setAttribute("encodeText", encodeText);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");

					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(emp_pw, emp_name, emp_bday, emp_email,
						emp_pho, emp_gend, emp_pic, emp_state, emp_hday, emp_address,
						emp_ID);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/emp/listOneEmp.jsp";
				System.out.println("檢查點 3");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點 4");

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
								
				String emp_pw = req.getParameter("emp_pw");
				String emp_pwReg = "^[(a-zA-Z0-9_)]{4,20}$";
				if (emp_pw == null || emp_pw.trim().length() == 0) {
					errorMsgs.add("員工密碼: 請勿空白");
				} else if(!emp_pw.trim().matches(emp_pwReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工密碼:只能是英文字母、數字和_ , 且長度必需在4到20之間");
	            }
				
				String emp_name = req.getParameter("emp_name");
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!emp_name.trim().matches(emp_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				
				java.sql.Date emp_bday = null;
				try {
					emp_bday = java.sql.Date.valueOf(req.getParameter("emp_bday").trim());
				} catch (IllegalArgumentException e) {
					emp_bday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("員工生日請勿空白");
				}
				
				String emp_email = req.getParameter("emp_email");
				String emp_emailReg = "^[a-zA-Z0-9\\._-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
				if (emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.add("員工電子信箱: 請勿空白");
				} else if(!emp_email.trim().matches(emp_emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工電子信箱: 只能是英文字母、數字和_,且必需包含@符號");
	            }
				
				String emp_pho = req.getParameter("emp_pho");
				String emp_phoReg = "^[(0-9)]$";
				if (emp_pho == null || emp_pho.trim().length() == 0) {
					errorMsgs.add("員工電話: 請勿空白");
				} //else if(!emp_pho.trim().matches(emp_phoReg)) { //以下練習正則(規)表示式(regular-expression)
				//	errorMsgs.add("員工電話: 只能是數字");
	            //}
				
				String emp_gend = req.getParameter("emp_gend");
				String emp_gendReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]$";
				if (emp_gend == null || emp_gend.trim().length() == 0) {
					errorMsgs.add("員工性別: 請勿空白");
				} //else if(!emp_gend.trim().matches(emp_gendReg)) { //以下練習正則(規)表示式(regular-expression)
				//	errorMsgs.add("員工性別: 只能是中、英文字母 , 且長度必需在10以內");
	            //}
				
				InputStream in = null;
				byte[] emp_pic = null;//??
				Part part = req.getPart("emp_pic");
				
				Base64.Encoder encoder = Base64.getEncoder();
				if(getFileNameFromPart(part) != null) {
					in = part.getInputStream();
					emp_pic = new byte[in.available()];
					in.read(emp_pic);
					in.close();
					String encodeText = encoder.encodeToString(emp_pic);
					req.setAttribute("encodeText", encodeText);
				} else {
					if(req.getParameter("encodeText") != null && req.getParameter("encodeText").trim().length() !=0) {
						Base64.Decoder decoder = Base64.getDecoder();
						emp_pic = decoder.decode(req.getParameter("encodeText"));
						String encodeText = encoder.encodeToString(emp_pic);
						req.setAttribute("encodeText", encodeText);
					}
				}

				
				Integer emp_state = null;
				try {
					emp_state = new Integer(req.getParameter("emp_state").trim());
				} catch (NumberFormatException e) {
					emp_state = 0;
					errorMsgs.add("員工帳號狀態請填數字.");
				}
				
				java.sql.Date emp_hday = null;
				try {
					emp_hday = java.sql.Date.valueOf(req.getParameter("emp_hday").trim());
				} catch (IllegalArgumentException e) {
					emp_hday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入員工就職日期!");
				}
				
				String emp_address = req.getParameter("emp_address").trim();
				if (emp_address == null || emp_address.trim().length() == 0) {
					errorMsgs.add("員工居住地: 請勿空白");
				}	
				
//				//String auth_ID = new String(req.getParameter("auth_ID").trim());

				EmpVO empVO = new EmpVO();
				empVO.setEmp_pw(emp_pw);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_bday(emp_bday);
				empVO.setEmp_email(emp_email);
				empVO.setEmp_pho(emp_pho);
				empVO.setEmp_gend(emp_gend);
				empVO.setEmp_pic(emp_pic);//??
				empVO.setEmp_state(emp_state);
				empVO.setEmp_hday(emp_hday);
				empVO.setEmp_address(emp_address);
				System.out.println("檢查點 1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點 2");
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.addEmp(emp_pw, emp_name, emp_bday, emp_email,
						  emp_pho, emp_gend, emp_pic, emp_state, emp_hday,
						  emp_address);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/emp/listAllEmp.jsp";
				System.out.println("檢查點 3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("檢查點 4");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String emp_ID = new String(req.getParameter("emp_ID"));
				
				/***************************2.開始刪除資料***************************************/
				EmpService empSvc = new EmpService();
				empSvc.deleteEmp(emp_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
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
