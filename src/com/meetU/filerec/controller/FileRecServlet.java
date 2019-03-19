package com.meetU.filerec.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetU.filerec.model.FileRecService;
import com.meetU.filerec.model.FileRecVO;
import com.meetU.live.model.LiveService;
import com.meetU.live.model.LiveVO;

@MultipartConfig
public class FileRecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileRecServlet() {

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//      條件查詢前端
		if ("go_to_fileRec_front".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String host_ID = req.getParameter("host_ID");
				LiveService lSvc = new LiveService();
				LiveVO liveVO = lSvc.getOneLive(host_ID);
				liveVO.setLive_acc(liveVO.getLive_acc() + 1);
				lSvc.updateLive(host_ID, liveVO.getLive_name(), liveVO.getLive_acc(), liveVO.getLive_pic(),
						liveVO.getLive_date(), liveVO.getLive_status());
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/liveHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("host_ID", host_ID); // 資料庫取出的empVO物件,存入req
				String url = "/FrontEnd/live/liveHome2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/liveHome.jsp");
				failureView.forward(req, res);
			}
		}

//		影片修改前端
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String host_ID = new String(req.getParameter("host_ID"));
				String file_ID = req.getParameter("file_ID");
			
				/*************************** 2.開始查詢資料 ****************************************/
				FileRecService fileRecSve = new FileRecService();
				FileRecVO fileRecVO = fileRecSve.getOneLive2(file_ID, host_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("fileRecVO",fileRecVO); // 資料庫取出的empVO物件,存入req
				String url = "/FrontEnd/fileRec/update_fileRec.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/fileRec/update_fileRec.jsp");
				failureView.forward(req, res);
			}
		}

//		進行修改
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			FileRecVO fileRecVO = new FileRecVO();

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String file_ID = req.getParameter("file_ID").trim();
				
				String host_ID = req.getParameter("host_ID").trim();
				
				

				String file_name = req.getParameter("file_name").trim();
				if (file_name == null || file_name.trim().length() == 0) {
					errorMsgs.add("影片名稱請勿空白");
				}
				
				String live_des = req.getParameter("live_des").trim();
				if (live_des == null || live_des.trim().length() == 0) {
					errorMsgs.add("影片描述請勿空白");
				}
				
				String file_cont = req.getParameter("file_cont").trim();
				if (file_cont == null || file_cont.trim().length() == 0) {
					errorMsgs.add("影片內容請勿空白");
				}
				
				Timestamp file_date = new FileRecService().getOneLive2(file_ID,host_ID).getFile_date();

				Integer file_pop = 0;

				fileRecVO.setFile_ID(file_ID);
				fileRecVO.setHost_ID(host_ID);
				fileRecVO.setFile_name(file_name);
				fileRecVO.setLive_des(live_des);
				fileRecVO.setFile_cont(file_cont);
				fileRecVO.setFile_date(file_date);
				fileRecVO.setFile_pop(file_pop);

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fileRecVO",fileRecVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/fileRec/update_fileRec.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				FileRecService fileRecSvc = new FileRecService();
				fileRecVO = fileRecSvc.updateFileRec(file_ID, host_ID,file_name,live_des, file_cont,file_date,file_pop);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("fileRecVO",fileRecVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/FrontEnd/fileRec/fileRecHome.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/liveHome.jsp");
				failureView.forward(req, res);
			}
		}

////       新增
		if ("insertfile".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			FileRecVO fileRecVO = new FileRecVO();

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String host_ID = req.getParameter("host_ID").trim();

				String file_name = req.getParameter("file_name").trim();
				if (file_name == null || file_name.trim().length() == 0) {
					errorMsgs.add("影片名稱請勿空白");
				}
				
				String live_des = req.getParameter("live_des").trim();
				if (live_des == null ||live_des.trim().length() == 0) {
					errorMsgs.add("影片描述請勿空白");
				}
				
				String file_cont = req.getParameter("file_cont").trim();
				if (file_cont == null ||file_cont.trim().length() == 0) {
					errorMsgs.add("影片網址請勿空白");
				}
				
				Date today = new Date();
				Timestamp file_date = new Timestamp(today.getTime());
				
				Integer file_pop = 0;
				
				
				fileRecVO.setHost_ID(host_ID);
				fileRecVO.setFile_name(file_name);
				fileRecVO.setLive_des(live_des);
				fileRecVO.setFile_cont(file_cont);
				fileRecVO.setFile_date(file_date);
				fileRecVO.setFile_pop(file_pop);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fileRecVO",fileRecVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/fileRec/addfileRecHome.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				FileRecService fileRecSvc = new FileRecService();
				fileRecVO = fileRecSvc.addFileRec(host_ID, file_name, live_des, file_cont, file_date, file_pop);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/FrontEnd/fileRec/fileRecHome.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				req.setAttribute("fileRecVO",fileRecVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/fileRec/addfileRecHome.jsp");
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
				String file_ID = new String(req.getParameter("file_ID"));
				String host_ID = new String(req.getParameter("host_ID"));

				/*************************** 2.開始刪除資料 ***************************************/
				FileRecService fileRecSve = new FileRecService();
				fileRecSve.deleteFileRec(file_ID);
				
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/FrontEnd/fileRec/fileRecHome.jsp";
				req.setAttribute("host_ID",host_ID);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				
				RequestDispatcher failureView = req.getRequestDispatcher("/FrontEnd/live/liveHome.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
