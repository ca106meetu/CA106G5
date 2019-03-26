package com.meetU.orderMaster.controller;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.orderMaster.model.OrderMasterService;
import com.meetU.orderMaster.model.OrderMasterVO;


public class OmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    public OmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("out".equals(action)) {
			try {
				String order_ID = req.getParameter("order_ID");
				OrderMasterService omSvc = new OrderMasterService();
				OrderMasterVO omVO = omSvc.getOneOm(order_ID);
				
				Date now = new Date();
				Timestamp out_date = new Timestamp(now.getTime());
				
				omVO.setOut_date(out_date);
				omVO.setOrder_status(1);
				omVO = omSvc.updateOm(omVO.getOrder_ID(),omVO.getMem_ID(), omVO.getPrice(), omVO.getOrder_date(), omVO.getTip(), omVO.getOut_add()
						, omVO.getRecipient(), omVO.getPhone(), omVO.getOut_date(), omVO.getOut_status(), omVO.getOrder_status());
				req.setAttribute("omVO", omVO);
				String url = req.getParameter("location");
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
		try {
			//*****1.錯誤驗證****************
					String order_ID = req.getParameter("order_ID");
					String order_IDReg = "^OM[0]{4}[0-9]{2}$";
					if (order_ID == null || (order_ID.trim()).length() == 0) {
						errorMsgs.add("請輸入訂單編號");
					}else if(!order_ID.trim().matches(order_IDReg)) {
						errorMsgs.add("訂單編號格式錯誤");	
					}
					
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/om/selectPageOm.jsp");
						failureView.forward(req, res);
						return;
					}
					
			//*****2.查詢資料****************		
					OrderMasterService omSvc = new OrderMasterService();
					OrderMasterVO omVO = omSvc.getOneOm(order_ID);
					if(omVO == null) {
						errorMsgs.add("查無資料");
					}
					
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/om/selectPageOm.jsp");
						failureView.forward(req, res);
						return;
					}
			//*******3.查詢完成準備轉交**************************
					req.setAttribute("omVO", omVO);
					String url = "/back-end/om/listOneOm.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
			//************其他錯誤處理************************
		} catch (Exception e) {
			errorMsgs.add("無法取得資料" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/om/selectPageOm.jsp");
			failureView.forward(req, res);
		}
			
		}
		
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					String mem_ID = req.getParameter("mem_ID");
					
					

					Double price = null;
					try {
						price = new Double(req.getParameter("price").trim());
						if (price < 0)
							errorMsgs.add("訂單價格:訂單價格必須大於0");
					} catch (NumberFormatException e) {
						price = 0.0;
						errorMsgs.add("訂單價格:訂單價格請填數字");
					}
					
					
					
					
					String tip = req.getParameter("tip").trim();
					
					
					String out_add = req.getParameter("out_add");
					String recipient = req.getParameter("recipient");
					String phone = req.getParameter("phone");
					Timestamp out_date = null;
					
					String out_statusStr = req.getParameter("out_status");
					Integer out_status = Integer.parseInt(out_statusStr);
					
					String order_statusStr = req.getParameter("order_status");
					Integer order_status = Integer.parseInt(order_statusStr);					
					
					
					
					
					Date now = new Date();
					Timestamp order_date = new Timestamp(now.getTime());
					
					OrderMasterVO omVO = new OrderMasterVO();
					
					omVO.setMem_ID(mem_ID);
					omVO.setPrice(price);
					omVO.setOrder_date(order_date);
					omVO.setTip(tip);
					omVO.setOut_add(out_add);
					omVO.setRecipient(recipient);
					omVO.setPhone(phone);
					omVO.setOut_date(out_date);
					omVO.setOut_status(out_status);
					omVO.setOrder_status(order_status);
					
					
					
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("omVO", omVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/om/addOm.jsp");
						failureView.forward(req, res);
						return;
					}
					
					//**********************************
					OrderMasterService omSvc = new OrderMasterService();
					omVO = omSvc.addOm(mem_ID, price, order_date, tip, out_add, recipient, phone, out_date, out_status, order_status);
					req.setAttribute("lastPage", true);
					//**********************************
					String url = "/back-end/om/listAllOm.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/om/addOm.jsp");
					failureView.forward(req, res);
				}
			
		}
		
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String order_ID = req.getParameter("order_ID");
				//**********************************************
				OrderMasterService omSvc = new OrderMasterService();
				OrderMasterVO omVO = omSvc.getOneOm(order_ID);
			
				//**********************************************
				req.setAttribute("omVO", omVO);
				
				String url = "/back-end/om/update_om_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/om/addOm.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					String order_ID = req.getParameter("order_ID");
					String mem_ID = req.getParameter("mem_ID");
					
					Double price = null;
					try {
						price = new Double(req.getParameter("price").trim());
						if (price < 0)
							errorMsgs.add("訂單價格:訂單價格必須大於0");
					} catch (NumberFormatException e) {
						price = 0.0;
						errorMsgs.add("訂單價格:訂單價格請填數字");
					}
					
					
					
					
					String tip = req.getParameter("tip");
					String out_add = req.getParameter("out_add");
					String recipient = req.getParameter("recipient");
					String phone = req.getParameter("phone");
					Timestamp out_date = null;
					
					String out_statusStr = req.getParameter("out_status");
					Integer out_status = Integer.parseInt(out_statusStr);
					
					String order_statusStr = req.getParameter("order_status");
					Integer order_status = Integer.parseInt(order_statusStr);					
					
					
					
					
					Date now = new Date();
					Timestamp order_date = new Timestamp(now.getTime());
					
					OrderMasterVO omVO = new OrderMasterVO();
					
					omVO.setOrder_ID(order_ID);
					omVO.setMem_ID(mem_ID);
					System.out.println(mem_ID);
					omVO.setPrice(price);
					omVO.setOrder_date(order_date);
					omVO.setTip(tip);
					omVO.setOut_add(out_add);
					omVO.setRecipient(recipient);
					omVO.setPhone(phone);
					omVO.setOut_date(out_date);
					omVO.setOut_status(out_status);
					omVO.setOrder_status(order_status);					
					
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("omVO", omVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/om/update_om_input.jsp");
						failureView.forward(req, res);
						return;
					}
					
					//**********************************
					OrderMasterService omSvc = new OrderMasterService();
					omVO = omSvc.updateOm(order_ID, mem_ID, price, order_date, tip, out_add, recipient, phone, out_date, out_status, order_status);
					req.setAttribute("lastPage", true);
					//**********************************
					
					req.setAttribute("omVO", omVO);
					String url = "/back-end/om/listOneOm.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/om/update_om_input.jsp");
					failureView.forward(req, res);
				}
		}
		
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String order_ID = req.getParameter("order_ID");
				OrderMasterService omSvc = new OrderMasterService();
				omSvc.deleteOm(order_ID);
				req.setAttribute("lastPage", true);
				String url = "/back-end/om/listAllOm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/om/listAllOm.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
	}
	
}
