package com.meetU.product.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.meetU.product.model.ProductService;
import com.meetU.product.model.ProductVO;

@MultipartConfig
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
		
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					String prod_name = req.getParameter("prod_name");
					String prod_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if(prod_name == null || prod_name.trim().length() ==0) {
						errorMsgs.add("商品名稱:請勿空白");					
					}else if(!prod_name.trim().matches(prod_nameReg)){
						errorMsgs.add("商品名稱:只能是中、英文字母、數字和_，且長度必須在2到10之間");
					}
					

					Double prod_price = null;
					try {
						prod_price = new Double(req.getParameter("prod_price").trim());
						if (prod_price < 0)
							errorMsgs.add("商品價格:商品價格必須大於0");
					} catch (NumberFormatException e) {
						prod_price = 0.0;
						errorMsgs.add("商品價格:商品價格請填數字");
					}
					
					Integer prod_type = null;
					String prod_typeStr = req.getParameter("prod_type");
					String prod_typeReg = "^[0-1]{1}$";
					
					if(prod_typeStr == null || prod_typeStr.trim().length() == 0) {
						errorMsgs.add("商品種類:不得為空");
					}else if (!prod_typeStr.trim().matches(prod_typeReg)) {
						errorMsgs.add("商品種類:商品種類請填數字0或1，0為實體商品，1為虛擬商品");
					}
					
					try {
						prod_type = Integer.parseInt(prod_typeStr);
					} catch (NumberFormatException e1) {
						prod_type = 0;
						errorMsgs.add("商品種類:商品種類請填數字0或1，0為實體商品，1為虛擬商品");
					}
					
					Integer prod_stock = null;
					
					try {
						prod_stock = new Integer(req.getParameter("prod_stock").trim());
						if (prod_stock < 0)
							errorMsgs.add("商品庫存:商品庫存必須大於0");
					
					} catch (NumberFormatException e) {
						prod_stock = 0;
						errorMsgs.add("商品庫存:商品庫存請填正整數");
					}
					
						
					Integer prod_promt_status = null;
					
					String prod_promt_statusStr = req.getParameter("prod_promt_status");
					String prod_promt_statusReg = "^[0-1]{1}$";
					
					if(prod_promt_statusStr == null || prod_promt_statusStr.trim().length() == 0) {
						errorMsgs.add("促銷狀態:不得為空");
					}else if (!prod_promt_statusStr.trim().matches(prod_promt_statusReg)) {
						errorMsgs.add("促銷狀態:促銷狀態請填數字0或1，0為未促銷，1為促銷中");
					}
					
					try {
						prod_promt_status = Integer.parseInt(prod_promt_statusStr);
					} catch (NumberFormatException e) {
						errorMsgs.add("促銷狀態:促銷狀態請填數字0或1，0為未促銷，1為促銷中");
					}
					
					Integer prod_status = null;
					
					String prod_statusStr = req.getParameter("prod_status");
					String prod_statusReg = "^[0-1]{1}$";
					
					if(prod_statusStr == null || prod_statusStr.trim().length() == 0) {
						errorMsgs.add("商品狀態:不得為空");
					}else if (!prod_statusStr.trim().matches(prod_statusReg)) {
						errorMsgs.add("商品狀態:商品狀態請填數字0或1，0下架，1上架");
					}
					
					try {
						prod_status = Integer.parseInt(prod_statusStr);
					} catch (NumberFormatException e) {
						errorMsgs.add("商品狀態:商品狀態請填數字0或1，0下架，1上架");
					}
					
					
					String prod_info = req.getParameter("prod_info");
					
					byte[] prod_pic=null;
					Part part = req.getPart("prod_pic");
					
					Base64.Encoder encoder = Base64.getEncoder();
					if(getFileNameFromPart(part) != null) {
						InputStream in = part.getInputStream();
						prod_pic = new byte[in.available()];
						in.read(prod_pic);
						in.close();
						String encodeText = encoder.encodeToString(prod_pic);
						req.setAttribute("encodeText", encodeText);
					} else {
						if(req.getParameter("encodeText") != null && req.getParameter("encodeText").trim().length() !=0) {
							Base64.Decoder decoder = Base64.getDecoder();
							prod_pic = decoder.decode(req.getParameter("encodeText"));
							String encodeText = encoder.encodeToString(prod_pic);
							req.setAttribute("encodeText", encodeText);
						}
					}
					
					ProductVO prodVO = new ProductVO();
					prodVO.setProd_name(prod_name);
					prodVO.setProd_price(prod_price);
					prodVO.setProd_stock(prod_stock);
					prodVO.setProd_promt_status(prod_promt_status);
					prodVO.setProd_pic(prod_pic);
					prodVO.setProd_type(prod_type);
					prodVO.setProd_info(prod_info);
					prodVO.setProd_status(prod_status);
					
					
					
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("prodVO", prodVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/prod/addProd.jsp");
						failureView.forward(req, res);
						return;
					}
					
					//**********************************
					ProductService prodSvc = new ProductService();
					prodVO = prodSvc.addprod(prod_name, prod_price, prod_type, prod_stock, prod_pic, prod_promt_status, prod_status, prod_info);
					req.setAttribute("lastPage", true);
					//**********************************
					String url = "/FrontEnd/prod/listAllProd.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/prod/addProd.jsp");
					failureView.forward(req, res);
				}
			
		}
		
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String prod_ID = req.getParameter("prod_ID");
				//**********************************************
				ProductService prodSvc = new ProductService();
				ProductVO prodVO = prodSvc.getOneProd(prod_ID);
				Base64.Encoder encoder = Base64.getEncoder();
				
				if(prodVO.getProd_pic() != null) {
				String encodeText = encoder.encodeToString(prodVO.getProd_pic());
				req.setAttribute("encodeText", encodeText);
				}
			
				//**********************************************
				req.setAttribute("prodVO", prodVO);
				
				String url = "/FrontEnd/prod/update_prod_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/prod/addProd.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					String prod_ID = req.getParameter("prod_ID");
					String prod_name = req.getParameter("prod_name");
					String prod_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if(prod_name == null || prod_name.trim().length() ==0) {
						errorMsgs.add("商品名稱:請勿空白");					
					}else if(!prod_name.trim().matches(prod_nameReg)){
						errorMsgs.add("商品名稱:只能是中、英文字母、數字和_，且長度必須在2到10之間");
					}
					

					Double prod_price = null;
					try {
						prod_price = new Double(req.getParameter("prod_price").trim());
						if (prod_price < 0)
							errorMsgs.add("商品價格:商品價格必須大於0");
					} catch (NumberFormatException e) {
						prod_price = 0.0;
						errorMsgs.add("商品價格:商品價格請填數字");
					}
					
					Integer prod_type = null;
					String prod_typeStr = req.getParameter("prod_type");
					String prod_typeReg = "^[0-1]{1}$";
					
					if(prod_typeStr == null || prod_typeStr.trim().length() == 0) {
						errorMsgs.add("商品種類:不得為空");
					}else if (!prod_typeStr.trim().matches(prod_typeReg)) {
						errorMsgs.add("商品種類:商品種類請填數字0或1，0為實體商品，1為虛擬商品");
					}
					
					try {
						prod_type = Integer.parseInt(prod_typeStr);
					} catch (NumberFormatException e1) {
						prod_type = 0;
						errorMsgs.add("商品種類:商品種類請填數字0或1，0為實體商品，1為虛擬商品");
					}
					
					Integer prod_stock = null;
					
					try {
						prod_stock = new Integer(req.getParameter("prod_stock").trim());
						if (prod_stock < 0)
							errorMsgs.add("商品庫存:商品庫存必須大於0");
					
					} catch (NumberFormatException e) {
						prod_stock = 0;
						errorMsgs.add("商品庫存:商品庫存請填正整數");
					}
					
						
					Integer prod_promt_status = null;
					
					String prod_promt_statusStr = req.getParameter("prod_promt_status");
					String prod_promt_statusReg = "^[0-1]{1}$";
					
					if(prod_promt_statusStr == null || prod_promt_statusStr.trim().length() == 0) {
						errorMsgs.add("促銷狀態:不得為空");
					}else if (!prod_promt_statusStr.trim().matches(prod_promt_statusReg)) {
						errorMsgs.add("促銷狀態:促銷狀態請填數字0或1，0為未促銷，1為促銷中");
					}
					
					try {
						prod_promt_status = Integer.parseInt(prod_promt_statusStr);
					} catch (NumberFormatException e) {
						errorMsgs.add("促銷狀態:促銷狀態請填數字0或1，0為未促銷，1為促銷中");
					}
					
					Integer prod_status = null;
					
					String prod_statusStr = req.getParameter("prod_status");
					String prod_statusReg = "^[0-1]{1}$";
					
					if(prod_statusStr == null || prod_statusStr.trim().length() == 0) {
						errorMsgs.add("商品狀態:不得為空");
					}else if (!prod_statusStr.trim().matches(prod_statusReg)) {
						errorMsgs.add("商品狀態:商品狀態請填數字0或1，0下架，1上架");
					}
					
					try {
						prod_status = Integer.parseInt(prod_statusStr);
					} catch (NumberFormatException e) {
						errorMsgs.add("商品狀態:商品狀態請填數字0或1，0下架，1上架");
					}
					
					String prod_info = req.getParameter("prod_info");
					InputStream in = null;
					byte[] prod_pic = null;

					Part part = req.getPart("prod_pic");
					if(getFileNameFromPart(part) != null) {
						in = part.getInputStream();
						prod_pic = new byte[in.available()];
						in.read(prod_pic);
						in.close();
					}else {
						
						prod_pic = new ProductService().getOneProd(prod_ID).getProd_pic();
					}
					
					ProductVO prodVO = new ProductVO();
					prodVO.setProd_ID(prod_ID);
					prodVO.setProd_name(prod_name);
					prodVO.setProd_price(prod_price);
					prodVO.setProd_stock(prod_stock);
					prodVO.setProd_promt_status(prod_promt_status);
					prodVO.setProd_pic(prod_pic);
					prodVO.setProd_type(prod_type);
					prodVO.setProd_info(prod_info);
					prodVO.setProd_status(prod_status);
					if(prod_pic != null) {
						Base64.Encoder encoder = Base64.getEncoder();
						String encodeText = encoder.encodeToString(prod_pic);
						req.setAttribute("encodeText", encodeText);
					}
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("prodVO", prodVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/prod/update_prod_input.jsp");
						failureView.forward(req, res);
						return;
					}
					
					//**********************************
					ProductService prodSvc = new ProductService();
					prodVO = prodSvc.updateProd(prod_ID, prod_name, prod_price, prod_type, prod_stock, prod_pic, prod_promt_status, prod_status, prod_info);
					//**********************************
					req.setAttribute("prodVO", prodVO);
					String url = "/FrontEnd/prod/listAllProd.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/prod/update_prod_input.jsp");
					failureView.forward(req, res);
				}
		}
		
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String prod_ID = req.getParameter("prod_ID");
				ProductService prodSvc = new ProductService();
				prodSvc.deleteProd(prod_ID);
				String url = "/FrontEnd/prod/listAllProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/prod/listAllProd.jsp");
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
