package com.meetU.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.meetU.orderDetail.model.OrderDetailService;
import com.meetU.orderMaster.model.OrderMasterService;
import com.meetU.orderMaster.model.OrderMasterVO;
import com.meetU.product.model.ProductService;
import com.meetU.product.model.ProductVO;


/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet("/FrontEnd/cart/ShoppingServlet")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShoppingServlet() {
        super();
    }
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		List<ProductVO> buyList = (Vector<ProductVO>) session.getAttribute("shoppingCart");
		ProductService prodSvc = new ProductService();
		
		//新增
		String action = req.getParameter("action");
		if("add".equals(action)) {
			System.out.println(req.getParameter("prod_ID"));
			ProductVO prodVO = prodSvc.getOneProd(req.getParameter("prod_ID"));
			System.out.println(prodVO);
			ProductVO innerProdVO =null;
			if(buyList == null) {
				buyList = new Vector<ProductVO>();
				System.out.println(req.getParameter("quantity"));
				prodVO.setQuantity(Integer.valueOf(req.getParameter("quantity")));
				buyList.add(prodVO);
			
			}else {
				if(buyList.contains(prodVO)) {
					 innerProdVO = buyList.get(buyList.indexOf(prodVO));
					 innerProdVO.setQuantity(innerProdVO.getQuantity()
							+Integer.valueOf(req.getParameter("quantity")));
					
					 System.out.println(prodVO.getProd_name());
					 System.out.println(innerProdVO.getQuantity());
				}else {
					prodVO.setQuantity(Integer.valueOf(req.getParameter("quantity")));
					buyList.add(prodVO);
					System.out.println(prodVO.getProd_name());
					System.out.println(prodVO.getQuantity());
				}
			}
			session.setAttribute("shoppingCart", buyList);
			PrintWriter out = res.getWriter();
			JSONObject object = new JSONObject();
			try {
				object.put("QQ", "成功加入購物車");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.write("{}");
			out.flush();
			out.close();
			
//			RequestDispatcher rd = req.getRequestDispatcher("/FrontEnd/cart/EShop.jsp");
//			rd.forward(req, res);
//					
		}
		
		if ("del".equals(action)) {
			if(buyList != null) {
				String prod_ID = req.getParameter("prod_ID");
				buyList.remove(prodSvc.getOneProd(prod_ID));
				session.setAttribute("shoppingCart", buyList);
				RequestDispatcher rd = req.getRequestDispatcher("/FrontEnd/cart/cart.jsp");
				
				rd.forward(req, res);
			}
		}
		
		if ("checkOut".equals(action)) {
			if(buyList != null) {
				double total = 0;
				for (int i = 0; i < buyList.size(); i++) {
					ProductVO prodVO = buyList.get(i);
					Double price = prodVO.getProd_price();
					Integer quantity = prodVO.getQuantity();
					total += (price * quantity);
				}

				String amount = String.valueOf(total);
				req.getSession().setAttribute("amount", amount);
				String url = "/FrontEnd/cart/checkOut.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
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
						RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/cart/checkOut.jsp");
						failureView.forward(req, res);
						return;
					}
					
					//**********************************
					OrderDetailService odSvc = new OrderDetailService();
					odSvc.insertOmOd(mem_ID, price, order_date, tip, out_add, recipient, phone, out_date, out_status, order_status, buyList);
					req.setAttribute("lastPage", true);
					//**********************************
					String url = "/FrontEnd/cart/success.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/cart/checkOut.jsp");
					failureView.forward(req, res);
				}
			
		}
		
	
	}
	
	

}
