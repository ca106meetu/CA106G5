package com.meetU.frontLogin;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.meetU.mem.model.*;

@WebServlet("/FrontLoginHandler")
public class FrontLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	   //【實際上應至資料庫搜尋比對】
	  protected boolean allowUser(String mem_acc, String mem_pw, HttpSession session) {
		MemService memSvc = new MemService();
		MemVO memVO = memSvc.getOneMem(mem_acc, mem_pw);
	    if (memVO != null) {
	    	session.setAttribute("memVO", memVO);
	    	
	    	return true;
	    }else {
	      return false;
	    }
	  }
//	  public void doGet(HttpServletRequest req, HttpServletResponse res)
//				throws ServletException, IOException {
//			doPost(req, res);
//	  }
	  public void doPost(HttpServletRequest req, HttpServletResponse res)
           throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		// 【取得使用者帳號(mem_acc) 密碼(emp_pw)】
		String mem_acc = req.getParameter("mem_acc");
		String mem_pw = req.getParameter("mem_pw");
		
		HttpSession session = req.getSession();
		String backPage = (String) session.getAttribute("location");
			// 【檢查該帳號 , 密碼是否有效】
			if (!allowUser(mem_acc, mem_pw, session)) {          //【帳號 , 密碼無效時】
//				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
//				out.println("<BODY>你的帳號 , 密碼無效!<BR>");
//				out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/frontLogin.html>重新登入</A>");
//				out.println("</BODY></HTML>");
				/*=====================================*/
				String location = (String) session.getAttribute("location");
				System.out.println(location);
				if (location != null) {
					//session.removeAttribute("location");
					out.println("<!doctype html><html lang='zh-TW'><head><meta charset='utf-8'>");
					out.println("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
					out.println("<script src='Templates/bootstrap4/js/jquery-3.2.1.min.js'></script>");
					out.println("<script src='Templates/bootstrap4/js/sweetalert2.all.js'></script>");
					out.println("<link rel='stylesheet' type='text/css' href='Templates/bootstrap4/css/sweetalert2.css'>");
				
					out.println("<TITLE>登入失敗</TITLE></head><body><script type='text/javascript'>");
					out.println("$(function(){swal('你的帳號,密碼無效!','請您重新輸入帳號密碼','error').then(function (result) {");
					out.println("window.location.href='"+location+"';});});");
					out.println("</script></body></HTML>");
				   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					session.removeAttribute("location");            
					return;
				}
				//req.getRequestDispatcher(backPage).forward(req, res);
				//res.sendRedirect(req.getRequestURI());
			}else {                                       //【帳號 , 密碼有效時, 才做以下工作】
				session.setAttribute("mem_acc", mem_acc);   //*工作1: 才在session內做已經登入過的標識
				
				try {                                                        
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						res.sendRedirect(location);            
						return;
					}
				}catch (Exception ignored) { }
				
				
				//res.sendRedirect(req.getContextPath()+"/frontLogin_success.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
			}
		}

}
