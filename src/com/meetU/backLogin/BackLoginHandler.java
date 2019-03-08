package com.meetU.backLogin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meetU.emp.model.EmpService;
import com.meetU.emp.model.EmpVO;
import com.meetU.empAuth.model.EmpAuthService;

@WebServlet("/BackLoginHandler")
public class BackLoginHandler extends HttpServlet{
	private static final long serialVersionUID = 1L;

	   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	   //【實際上應至資料庫搜尋比對】
	  protected boolean allowUser(String emp_ID, String emp_pw, HttpSession session) {
		EmpService empSvc = new EmpService();
		EmpVO empVO = empSvc.getOneEmp(emp_ID, emp_pw);
	    if (empVO != null) {
	    	session.setAttribute("empVO", empVO);
	    	return true;
	    }else {
	      return false;
	    }
	  }
	  
	  public void doPost(HttpServletRequest req, HttpServletResponse res)
              throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		
		// 【取得使用者ID(emp_ID) 密碼(emp_pw)】
		String emp_ID = req.getParameter("emp_ID");
		String emp_pw = req.getParameter("emp_pw");
		//System.out.println(emp_ID+emp_pw);
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		
		if ("back_logout".equals(action)) {
			session.invalidate();
			res.sendRedirect(req.getContextPath()+"/backIndex.jsp"); 
		}
			// 【檢查該帳號 , 密碼是否有效】
			if (!allowUser(emp_ID, emp_pw, session)) {          //【帳號 , 密碼無效時】
//				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
//				out.println("<BODY>你的帳號 , 密碼無效!<BR>");
//				out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/backLogin.html>重新登入</A>");
//				out.println("</BODY></HTML>");
				/*=======================*/
				
				out.println("<!doctype html><html lang='zh-TW'><head><meta charset='utf-8'>");
				out.println("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
				out.println("<script src='Templates/bootstrap4/js/jquery-3.2.1.min.js'></script>");
				out.println("<script src='Templates/bootstrap4/js/sweetalert2.all.js'></script>");
				out.println("<link rel='stylesheet' type='text/css' href='Templates/bootstrap4/css/sweetalert2.css'>");
				
				out.println("<TITLE>登入失敗</TITLE></head><body><script type='text/javascript'>");
				out.println("$(function(){swal('你的帳號,密碼無效!','請您重新輸入帳號密碼','error').then(function (result) {");
				out.println("window.location.href='"+req.getContextPath()+"/backIndex.jsp';});});");
				out.println("</script></body></HTML>");
				
				//res.sendRedirect(req.getRequestURI());
			}else {                                       //【帳號 , 密碼有效時, 才做以下工作】
				session.setAttribute("emp_ID", emp_ID);   //*工作1: 才在session內做已經登入過的標識
				EmpAuthService empAuthSvc = new EmpAuthService();
				List<String> auth_IDs = empAuthSvc.getPartOfOneEmpAuth2(emp_ID);
				//System.out.println(auth_IDs);
				session.setAttribute("auth_IDs", auth_IDs);
				try {                                                        
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						res.sendRedirect(location);            
						return;
					}
				}catch (Exception ignored) { }
				
				res.sendRedirect(req.getContextPath()+"/backLogin_success.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
			}
		}

}
