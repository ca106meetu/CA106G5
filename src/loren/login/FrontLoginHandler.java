package loren.login;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;

import com.meetU.mem.model.*;

@WebServlet("/lorenTest")
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
	  
	  public void doPost(HttpServletRequest req, HttpServletResponse res)
           throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		// 【取得使用者帳號(mem_acc) 密碼(emp_pw)】
		String mem_acc = req.getParameter("mem_acc");
		String mem_pw = req.getParameter("mem_pw");
		
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		String register_text_mem_acc = req.getParameter("register_text_mem_acc");
		String register_text_mem_email = req.getParameter("register_text_mem_email");
		JSONObject obj = new JSONObject();
		MemService mAccSvc = new MemService();
		boolean mem_accChick = mAccSvc.getOneMemByACC(register_text_mem_acc);
		MemService mEmailSvc = new MemService();
		boolean mem_emailChick = mEmailSvc.getOneMemByEMAIL(register_text_mem_email);
		
		if ("front_logout".equals(action)) {
			session.invalidate();
			res.sendRedirect(req.getContextPath()+"/front-end/lorenTest/test.jsp");
		}
		if("askEmail".equals(action)) {
			if(mem_emailChick) {
				JSONObject obj3 = new JSONObject();
				obj3.accumulate("register_text_mem_email_answer", "此信箱已有人使用");
				out.print(obj3.toString());
				out.flush();
				out.close();
				return;
			}else {
				JSONObject obj3 = new JSONObject();
				obj3.accumulate("register_text_mem_email_answer", "此信箱可以使用");
				out.print(obj3.toString());
				out.flush();
				out.close();
				return;
			}
		}
				
		if("askACC".equals(action)) {
			if(mem_accChick) {
				JSONObject obj2 = new JSONObject();
				obj2.accumulate("register_text_mem_acc_answer", "此帳號有人使用");
				out.print(obj2.toString());
				out.flush();
				out.close();
				return;
			}else {
				JSONObject obj2 = new JSONObject();
				obj2.accumulate("register_text_mem_acc_answer", "此帳號可以使用");
				out.print(obj2.toString());
				out.flush();
				out.close();
				return;
			}
		}
		if("register".equals(action)) {
			
		}
		
		System.out.println(mem_acc+mem_pw);
			// 【檢查該帳號 , 密碼是否有效】
			if (!allowUser(mem_acc, mem_pw, session)) {          //【帳號 , 密碼無效時】
			
				
			}else {                                       //【帳號 , 密碼有效時, 才做以下工作】
				session.setAttribute("mem_acc", mem_acc);   //*工作1: 才在session內做已經登入過的標識
				
				try {                                                        
					JSONObject obj1 = new JSONObject();
					obj1.accumulate("access", "true");
					out.print(obj1.toString());
					out.flush();
					out.close();
				}catch (Exception ignored) { 
					
				}
				
			}
		}

}
