package com.meetU.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		res.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		List<ProductVO> buyList = (Vector<ProductVO>) session.getAttribute("shappingCart");
		
		//新增
		String action = req.getParameter("action");
		if("add".equals(action)) {
			
			ProductService prodSvc = new ProductService();
			ProductVO prodVO = prodSvc.getOneProd(req.getParameter("prod_ID"));
			System.out.println(prodVO);
			ProductVO innerProdVO =null;
			if(buyList == null) {
				buyList = new Vector<ProductVO>();
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
			session.setAttribute("shappingCart", buyList);
			
			RequestDispatcher rd = req.getRequestDispatcher("/FrontEnd/cart/EShop.jsp");
			rd.forward(req, res);
					
		}
	
	}
	
	

}
