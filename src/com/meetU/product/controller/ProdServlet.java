package com.meetU.product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.product.model.ProductService;
import com.meetU.product.model.ProductVO;

@WebServlet("/ProdServlet")
public class ProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("12212");
		
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
		try {
			//*****1.錯誤驗證****************
					String prod_ID = req.getParameter("prod_ID");
					String prod_IDReg = "^P[0]{4}[0-9]{2}$";
					if (prod_ID == null || (prod_ID.trim()).length() == 0) {
						errorMsgs.add("請輸入商品編號");
					}else if(!prod_ID.trim().matches(prod_IDReg)) {
						errorMsgs.add("商品編號格式錯誤");	
					}
					
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/FrontEnd/prod/selectPage.jsp");
						failureView.forward(req, res);
						return;
					}
					
			//*****2.查詢資料****************		
					ProductService prodSvc = new ProductService();
					ProductVO prodVO = prodSvc.getOneProd(prod_ID);
					if(prodVO == null) {
						errorMsgs.add("查無資料");
					}
					
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/FrontEnd/prod/selectPage.jsp");
						failureView.forward(req, res);
						return;
					}
			//*******3.查詢完成準備轉交**************************
					req.setAttribute("prodVO", prodVO);
					String url = "/FrontEnd/prod/listOneProd.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
			//************其他錯誤處理************************
		} catch (Exception e) {
			errorMsgs.add("無法取得資料" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/FrontEnd/prod/selectPage.jsp");
			failureView.forward(req, res);
		}
			
		}
		
	}

}
