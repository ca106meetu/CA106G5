package com.meetU.product.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ForStatus",urlPatterns="/ForStatus",loadOnStartup=1)
public class ForStatus extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private List<String> pt;
	private List<String> pps;
	private List<String> ps;
       
    public ForStatus() {
        super();
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		pt = new ArrayList<>();
		pt.add(0, "實體");
		pt.add(1, "虛擬");
		
		ps = new ArrayList<>();
		ps.add(0, "上架");
		ps.add(1, "下架");
		
		pps = new ArrayList<>();
		pps.add(0, "未促銷");
		pps.add(1, "促銷中");
		getServletContext().setAttribute("pt", pt);
		getServletContext().setAttribute("ps", ps);
		getServletContext().setAttribute("pps", pps);
		System.out.println("阿彌陀佛阿門阿拉~BUG不要來");
		
		
		
	}
	
	

}
