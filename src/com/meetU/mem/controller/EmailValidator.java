package com.meetU.mem.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;

import com.meetU.mem.model.MemService;
import com.meetU.mem.model.MemVO;

import tools.MailService;

@WebServlet("/EmailValidator/*")
public class EmailValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Map<String, String> pendingList = Collections.synchronizedMap(new HashMap<String, String>());

    public EmailValidator() {
        super();
    }

	@Override
	public void init(ServletConfig cfg) throws ServletException {
		super.init(cfg);
		
		ServletContext ctx = cfg.getServletContext();
		ctx.setAttribute("pendingList", pendingList);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		//MemVO memvo = (MemVO) req.getSession().getAttribute("memVO");
		MemVO memvo = (MemVO) req.getSession().getAttribute("regMemVO");
		
		if (memvo == null) {
			req.setAttribute("errorMsg", "請先登入會員!!!");
			RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/mem/ValidationFailed.jsp");
			failureView.forward(req, res);
			return;
		}
		
		String mem_ID = memvo.getMem_ID();
		
		if ("ask_validation_email".equals(action)) {
			String charpool = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			StringBuilder sb = new StringBuilder("/");			

			for (int i = 0; i < 20; i++) {
				int rnd = (int) (Math.random() * 62);
				sb.append(charpool.charAt(rnd));
			}
			
			String url = sb.toString();
			
			if (pendingList.containsKey(mem_ID)) {
				pendingList.remove(mem_ID);
			}
			pendingList.put(mem_ID, url);
			
			MailService mailSvc = new MailService();
			String to = memvo.getMem_email();
			String subject = memvo.getMem_acc() + "你好: 這是您的信箱驗證信";
			/*==============================================================*/
			String link = req.getRequestURL() + url;
			String messageText = "請到" + link + "驗證您的信箱";
			//String messageText = "<a href=\"" + link + "\">請點此連結驗證您的信箱</a>";
			mailSvc.sendMail(to, subject, messageText);
			/*===============================================================*/
//            MimeBodyPart textPart = new MimeBodyPart();
//            StringBuffer html = new StringBuffer();
//            html.append("<a href=\"");
//            html.append(link);
//            html.append("\">請點此連結驗證您的信箱</a>");
//            try {
//				textPart.setContent(html.toString(), "text/html; charset=UTF-8");
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
			/*===============================================================*/
			
//			mailSvc.sendMail(to, subject, textPart);
			
			
			RequestDispatcher successView = req.getRequestDispatcher( 
					"/FrontEnd/mem/ValidationMailSent.jsp");
			successView.forward(req, res);
			return;
		}

		String validationLink = req.getPathInfo();
		if (!pendingList.containsValue(validationLink)) {
			req.setAttribute("errorMsg", "連結無效!!!");
			RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/mem/ValidationFailed.jsp");
			failureView.forward(req, res);
			return;
		}
		
		if (mem_ID == null) {
			req.setAttribute("errorMsg", "請先登入會員再點連結");
			RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/mem/ValidationFailed.jsp");
			failureView.forward(req, res);
			return;
		}
		
		if (pendingList.get(mem_ID).equals(validationLink)) {
			MemService memSvc = new MemService();
			memvo.setMem_state(1);
			memSvc.updateMem(memvo);
			RequestDispatcher successView = req.getRequestDispatcher("/FrontEnd/mem/ValidationSuccess.jsp");
			//RequestDispatcher successView = req.getRequestDispatcher("/FrontEnd/mem/reg_mem_input.jsp");
			successView.forward(req, res);
		} else {
			req.setAttribute("errorMsg", "連結不對喔 請不要亂登別人的帳號");
			RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/mem/ValidationFailed.jsp");
			failureView.forward(req, res);
		}
	}
}
