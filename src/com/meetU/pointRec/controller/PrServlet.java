package com.meetU.pointRec.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.emp.model.EmpVO;
import com.meetU.pointRec.model.PointRecDAO;
import com.meetU.pointRec.model.PointRecVO;
import com.meetU.pointRec.model.PrService;

@WebServlet("/FrontEnd/point/Pr.do")
public class PrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			
			
						
			if("0".equals(req.getParameter("amount"))) {
				errorMsgs.add("請選取欲儲值金額");
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/point/storePoint.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			
			
			EmpVO empVO = (EmpVO) req.getSession().getAttribute("empVO");
			
			Date now = new Date();
			String mem_ID = empVO.getEmp_ID();
			Timestamp rec_date = new Timestamp(now.getTime());
			PointRecVO prVO = new PointRecVO();
			Double amount = Double.valueOf(req.getParameter("amount")); 
			
			prVO.setAmount(amount);
			prVO.setMem_ID(empVO.getEmp_ID());
			prVO.setRec_date(rec_date);
			
			
			PointRecDAO dao = new PointRecDAO();
//			dao.
			
			
			
		}
		
	}

}
