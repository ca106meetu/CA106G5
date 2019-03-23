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
		
		if ("register".equals(action)) {
			MemVO regMemVO = (MemVO) req.getSession().getAttribute("regMemVO");
			
			String mem_ID = regMemVO.getMem_ID();
			String charpool = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			StringBuilder sb = new StringBuilder("/");			

			for (int i = 0; i < 20; i++) {
				int rnd = (int) (Math.random() * 62);
				sb.append(charpool.charAt(rnd));
			}
			
			String url = sb.toString();
			System.out.println(url);
			pendingList.put(url,mem_ID);
			
			MailService mailSvc = new MailService();
			String to = regMemVO.getMem_email();
			String subject = regMemVO.getMem_acc() + "你好: 這是您的信箱驗證信";
			
			String link = req.getRequestURL() + url;
			String messageText = "請到" + link + "驗證您的信箱";
			
			mailSvc.sendMail(to, subject, messageText);
				
			
			RequestDispatcher successView = req.getRequestDispatcher( 
					"/FrontEnd/mem/ValidationMailSent.jsp");
			successView.forward(req, res);
			return;
		}

		String validationLink = req.getPathInfo();
		//System.out.println(validationLink);
		//System.out.println(pendingList);
		//System.out.println(pendingList.get(validationLink));
		
		if (!pendingList.containsKey(validationLink)) {
			req.setAttribute("errorMsg", "連結無效!!!");
			RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/mem/ValidationFailed.jsp");
			failureView.forward(req, res);
			return;
		}
		
		if (pendingList.get(validationLink) == null) {
			req.setAttribute("errorMsg", "偽裝註冊是危險動作");
			RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/mem/ValidationFailed.jsp");
			failureView.forward(req, res);
			return;
		}
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(pendingList.get(validationLink));
			pendingList.remove(validationLink);
			System.out.println(pendingList.get(memVO.getMem_ID()));
			memVO.setMem_state(1);
			memSvc.updateMem(memVO);
			req.getSession().setAttribute("memVO", memVO);
			req.getSession().setAttribute("mem_acc", memVO.getMem_acc());
			RequestDispatcher successView = req.getRequestDispatcher("/FrontEnd/mem/ValidationSuccess.jsp");
			//RequestDispatcher successView = req.getRequestDispatcher("/FrontEnd/mem/reg_mem_input.jsp");
			successView.forward(req, res);
	}
}
