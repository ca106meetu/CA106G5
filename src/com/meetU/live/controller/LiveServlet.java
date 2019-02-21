package com.meetU.live.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.meetU.live.model.LiveService;
import com.meetU.live.model.LiveVO;

@MultipartConfig
public class LiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LiveServlet() {
		super();

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//      條件查詢
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String host_ID = req.getParameter("host_ID");
				if (host_ID == null || (host_ID.trim()).length() == 0) {
					errorMsgs.add("請輸入直播主ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/selectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				LiveService liveSvc = new LiveService();
				LiveVO liveVO = liveSvc.getOneLive(host_ID);
				if (liveVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/selectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("liveVO", liveVO); // 資料庫取出的empVO物件,存入req
				String url = "/FrontEnd/live/listOneLive.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/selectPage.jsp");
				failureView.forward(req, res);
			}
		}

//		條件修改
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String host_ID = new String(req.getParameter("host_ID"));

				/*************************** 2.開始查詢資料 ****************************************/
				LiveService liveSvc = new LiveService();
				LiveVO liveVO = liveSvc.getOneLive(host_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("liveVO", liveVO); // 資料庫取出的empVO物件,存入req
				String url = "/FrontEnd/live/update_live_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/listAllLive.jsp");
				failureView.forward(req, res);
			}
		}

//		進行修改
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String host_ID = req.getParameter("host_ID").trim();

				String live_name = req.getParameter("live_name").trim();
				if (live_name == null || live_name.trim().length() == 0) {
					errorMsgs.add("直播間名稱請勿空白");
				}

				Integer live_acc = null;
				try {
					live_acc = new Integer(req.getParameter("live_acc").trim());
				} catch (NumberFormatException e) {
					live_acc = 0;
					errorMsgs.add("累積瀏覽人數請填數字.");
				}

				Part part = req.getPart("live_pic");				
				InputStream in = part.getInputStream();
				byte[] live_pic = new byte[in.available()];
				in.read(live_pic);
				in.close();
				

				Timestamp live_date = new LiveService().getOneLive(host_ID).getLive_date();

				Integer live_status = null;
				
				try {
					live_status = new Integer(req.getParameter("live_status").trim());
					String live_statusa =live_status.toString();
					String regreg = "[^0-1]";
					if(live_statusa.matches(regreg)) {
//					if (!live_status.equals(new Integer(0))&&!live_status.equals(new Integer(1)))
						errorMsgs.add("直播間狀態請填0:關閉或1:正常");}
				} catch (Exception e) {
					e.printStackTrace();
//					errorMsgs.add("直播間狀態請填0:關閉或1:正常");
				}

				LiveVO liveVO = new LiveVO();
				liveVO.setHost_ID(host_ID);
				liveVO.setLive_name(live_name);
				liveVO.setLive_acc(live_acc);
				liveVO.setLive_pic(live_pic);
				liveVO.setLive_date(live_date);
				liveVO.setLive_status(live_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("liveVO", liveVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/update_live_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				LiveService liveSvc = new LiveService();
				liveVO = liveSvc.updateLive(host_ID, live_name, live_acc, live_pic, live_date, live_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("liveVO", liveVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/FrontEnd/live/listOneLive.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/update_live_input.jsp");
				failureView.forward(req, res);
			}
		}

//       新增
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			LiveVO liveVO = new LiveVO();

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String host_ID = req.getParameter("host_ID").trim();

				String live_name = req.getParameter("live_name").trim();
				if (live_name == null || live_name.trim().length() == 0) {
					errorMsgs.add("直播間名稱請勿空白");
				}

				Integer live_acc = null;
				try {
					live_acc = new Integer(req.getParameter("live_acc").trim());
				} catch (NumberFormatException e) {
					live_acc = 0;
					errorMsgs.add("累積瀏覽人數請填數字.");
				}
				
				
				Part part = req.getPart("live_pic");				
				InputStream in = part.getInputStream();
				byte[] live_pic = new byte[in.available()];
				in.read(live_pic);
				in.close();
				

				

				Date today = new Date();
				Timestamp live_date = new Timestamp(today.getTime());

				Integer live_status = null;
				try {
					live_status = new Integer(req.getParameter("live_status").trim());
				} catch (NumberFormatException e) {
					live_status = 0;
					errorMsgs.add("直播間狀態請填數字.");
				}

				
				
				liveVO.setLive_name(live_name);
				liveVO.setLive_acc(live_acc);
				liveVO.setLive_pic(live_pic);
				liveVO.setLive_date(live_date);
				liveVO.setLive_status(live_status);
				liveVO.setHost_ID(host_ID);
				
				Base64.Encoder encoder = Base64.getEncoder();
				String encodeText = encoder.encodeToString(live_pic);
				req.setAttribute("encodeText", encodeText);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("liveVO", liveVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/addLive.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				LiveService liveSvc = new LiveService();
				liveVO = liveSvc.addLive(host_ID, live_name, live_acc, live_pic, live_date, live_status);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/FrontEnd/live/listAllLive.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				req.setAttribute("liveVO", liveVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/addLive.jsp");
				failureView.forward(req, res);
			}
		}

//		刪除
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String host_ID = new String(req.getParameter("host_ID"));

				/*************************** 2.開始刪除資料 ***************************************/
				LiveService liveSvc = new LiveService();
				liveSvc.deleteLive(host_ID);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/FrontEnd/live/listAllLive.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/selectPage.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
