package com.meetU.frontLogin;

import java.io.*;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.meetU.mem.model.*;
import com.meetU.memHobby.model.*;

@WebServlet("/FrontLoginHandler")
public class FrontLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】
	protected boolean allowUser(String mem_acc, String mem_pw, HttpSession session, HttpServletRequest req,
			HttpServletResponse res) {
		MemService memSvc = new MemService();
		MemVO memVO = memSvc.getOneMem(mem_acc, mem_pw);
		String mem_ID = memVO.getMem_ID();
		if (memVO != null) {
			if (memVO.getMem_state() == 0) {
				session.setAttribute("regMemVO", memVO);
				String url = "/FrontEnd/mem/Email.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_mem_input.jsp
				try {
					successView.forward(req, res);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			} else {
				session.setAttribute("memVO", memVO);
				session.setAttribute("mem_ID", mem_ID);
				
				MemHobbyService memHobbySvc = new MemHobbyService();
				List<String> listHobby_ID = memHobbySvc.getPartOfOneMemHobby2(mem_ID);
				//session.setAttribute("listHobby_ID", listHobby_ID);
				return true;
			}
		} else {
			return false;
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
		String register_text_mem_pw = req.getParameter("register_text_mem_pw");
		JSONObject obj = new JSONObject();
		MemService mAccSvc = new MemService();
		boolean mem_accChick = mAccSvc.getOneMemByACC(register_text_mem_acc);
		MemService mEmailSvc = new MemService();
		boolean mem_emailChick = mEmailSvc.getOneMemByEMAIL(register_text_mem_email);

		if ("front_logout".equals(action)) {
			session.removeAttribute("memVO");
			session.removeAttribute("mem_acc");
			session.removeAttribute("regMemVO");
			session.removeAttribute("mem_ID");
			session.removeAttribute("encodeText");
			session.removeAttribute("listHobby_ID");
			session.invalidate();
			res.sendRedirect(req.getContextPath() + "/FrontEnd/lorenTest/test.jsp");
			return;
		}
		if ("askEmail".equals(action)) {
			String register_text_mem_email_RegExp = "^[\\.a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
			if (mem_emailChick) {
				JSONObject obj3 = new JSONObject();
				obj3.accumulate("register_text_mem_email_answer", "此信箱已有人使用");
				out.print(obj3.toString());
				out.flush();
				out.close();
				return;
			} else if (register_text_mem_email.trim() == null || register_text_mem_email.trim().length() == 0) {
				JSONObject obj3 = new JSONObject();
				obj3.accumulate("register_text_mem_email_answer", "電子信箱: 請勿空白");
				obj3.accumulate("register_text_mem_email_flag", false);
				out.print(obj3.toString());
				out.flush();
				out.close();
				return;
			} else if (!register_text_mem_email.trim().matches(register_text_mem_email_RegExp)) {
				JSONObject obj3 = new JSONObject();
				obj3.accumulate("register_text_mem_email_answer", "請輸入有效的電子郵件");
				obj3.accumulate("register_text_mem_email_flag", false);
				out.print(obj3.toString());
				out.flush();
				out.close();
				return;
			} else {
				JSONObject obj3 = new JSONObject();
				obj3.accumulate("register_text_mem_email_answer", "此信箱可以使用");
				obj3.accumulate("register_text_mem_email_flag", true);
				out.print(obj3.toString());
				out.flush();
				out.close();
				return;
			}
		}

		if ("askACC".equals(action)) {
			String register_text_mem_acc_RegExp = "^[(a-zA-Z0-9_)]{4,30}$";
			if (mem_accChick) {
				JSONObject obj2 = new JSONObject();
				obj2.accumulate("register_text_mem_acc_answer", "此帳號有人使用");
				obj2.accumulate("register_text_mem_acc_flag", false);
				out.print(obj2.toString());
				out.flush();
				out.close();
				return;
			} else if (register_text_mem_acc.trim() == null || register_text_mem_acc.trim().length() == 0) {
				JSONObject obj2 = new JSONObject();
				obj2.accumulate("register_text_mem_acc_answer", "會員帳號: 請勿空白");
				obj2.accumulate("register_text_mem_acc_flag", false);
				out.print(obj2.toString());
				out.flush();
				out.close();
				return;
			} else if (!register_text_mem_acc.trim().matches(register_text_mem_acc_RegExp)) {
				JSONObject obj2 = new JSONObject();
				obj2.accumulate("register_text_mem_acc_answer", "會員帳號: 只能是英文字母、數字和_ , 且長度必需在4到30之間");
				obj2.accumulate("register_text_mem_acc_flag", false);
				out.print(obj2.toString());
				out.flush();
				out.close();
				return;
			} else {
				JSONObject obj2 = new JSONObject();
				obj2.accumulate("register_text_mem_acc_answer", "此帳號可以使用");
				obj2.accumulate("register_text_mem_acc_flag", true);
				out.print(obj2.toString());
				out.flush();
				out.close();
				return;
			}
		}

		if ("askPw".equals(action)) {
			String register_text_mem_pw_RegExp = "^[(a-zA-Z0-9_)]{4,30}$";
			if (register_text_mem_pw.trim() == null || register_text_mem_pw.trim().length() == 0) {
				JSONObject obj4 = new JSONObject();
				obj4.accumulate("register_text_mem_pw_answer", "會員密碼: 請勿空白");
				obj4.accumulate("register_text_mem_pw_flag", false);
				out.print(obj4.toString());
				out.flush();
				out.close();
				return;
			} else if (!register_text_mem_pw.trim().matches(register_text_mem_pw_RegExp)) {
				JSONObject obj4 = new JSONObject();
				obj4.accumulate("register_text_mem_pw_answer", "會員密碼: 只能是英文字母、數字和_,且長度必需在4到30之間");
				obj4.accumulate("register_text_mem_pw_flag", false);
				out.print(obj4.toString());
				out.flush();
				out.close();
				return;
			} else {
				JSONObject obj4 = new JSONObject();
				obj4.accumulate("register_text_mem_pw_answer", "此密碼可以使用");
				obj4.accumulate("register_text_mem_pw_flag", true);
				out.print(obj4.toString());
				out.flush();
				out.close();
				return;
			}
		}

		if ("register".equals(action)) {
			String register_mem_acc = req.getParameter("register_text_mem_acc");
			String register_mem_pw = req.getParameter("register_text_mem_pw");
			String register_mem_email = req.getParameter("register_text_mem_email");
			String register_mem_addr = req.getParameter("register_text_mem_addr");
//			System.out.println(register_mem_acc);
//			System.out.println(register_mem_pw);
//			System.out.println(register_mem_email);
//			System.out.println(register_mem_addr);
			/* ==================================================================== */
//			String to = register_mem_email;
//		      
//		      String subject = "密碼通知";
//		      
//		      String ch_name = register_mem_acc;
//		      String passRandom = returnAuthCode();
//		      String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)"; 
//		       
//		      MailService mailService = new MailService();
//		      mailService.sendMail(to, subject, messageText);
			/* =============================================================== */
			MemService mRegAccSvc = new MemService();
			MemVO regMemVO = new MemVO();
			regMemVO = mRegAccSvc.regInsert(register_mem_pw, register_mem_acc, register_mem_email, register_mem_addr);
			session.setAttribute("regMemVO", regMemVO);
			// String url = "/FrontEnd/mem/reg_mem_input.jsp";
			String url = "/EmailValidator";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_mem_input.jsp
			successView.forward(req, res);
			return;
		}

		// 【檢查該帳號 , 密碼是否有效】
		if (!allowUser(mem_acc, mem_pw, session, req, res)) { // 【帳號 , 密碼無效時】
			res.setContentType("text/plain; charset=UTF-8");

			JSONObject obj1 = new JSONObject();
			obj1.accumulate("access", "false");
			out.print(obj1.toString());
			out.flush();
			out.close();

		} else { // 【帳號 , 密碼有效時, 才做以下工作】
			session.setAttribute("mem_acc", mem_acc); // *工作1: 才在session內做已經登入過的標識
			res.setContentType("text/plain; charset=UTF-8");

			try {
				JSONObject obj1 = new JSONObject();
				obj1.accumulate("access", "true");
				out.print(obj1.toString());
				out.flush();
				out.close();
			} catch (Exception ignored) {

			}

		}
	}

	

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//			System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//			System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

	
}
