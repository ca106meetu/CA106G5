package android.com.meetU.mem.controller;

import java.io.BufferedReader;
import android.com.meetU.main.ImageUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.com.meetU.mem.model.MemService;
import android.com.meetU.mem.model.MemVO;

public class android_MemServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		MemService memSvc = new MemService();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		if(action.equals("isMem")) {
			String mem_acc = jsonObject.get("mem_acc").getAsString();
			String mem_pw = jsonObject.get("mem_pw").getAsString();
			writeText(res,String.valueOf(memSvc.isMem(mem_acc, mem_pw)));
		} else if (action.equals("isAccountExist")) {
			String mem_acc = jsonObject.get("mem_acc").getAsString();
			writeText(res, String.valueOf(memSvc.isAccountExist(mem_acc)));
		} else if (action.equals("add")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String mem_acc = jsonObject.get("mem_acc").getAsString();
			String mem_pw = jsonObject.get("mem_pw").getAsString();
			String mem_email = jsonObject.get("mem_email").getAsString();
			Integer mem_code = jsonObject.get("mem_code").getAsInt();
			Integer mem_state = jsonObject.get("mem_state").getAsInt();
			Date mem_date = null;
			try {
				mem_date = new Date((sdf.parse(jsonObject.get("mem_date").getAsString())).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String mem_address = jsonObject.get("mem_address").getAsString();
			
			writeText(res,String.valueOf(memSvc.addMem(mem_pw, mem_acc, mem_email, mem_code, mem_state, mem_date, mem_address)));
		} else if(action.equals("findByAccount")) {
			String mem_acc = jsonObject.get("mem_acc").getAsString();
			MemVO memVO = memSvc.getOneMem(mem_acc);
			writeText(res, memVO == null ? "" : gson.toJson(memVO));		
		} else if(action.equals("findByID")){
			String mem_ID = jsonObject.get("mem_ID").getAsString();
			MemVO memVO = memSvc.findByID(mem_ID);
			writeText(res, memVO == null ? "" : gson.toJson(memVO));
			
		} else if(action.equals("getQRcode")) {
			OutputStream os = res.getOutputStream();
			String mem_ID = jsonObject.get("mem_ID").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = memSvc.getQRcode(mem_ID);
			if (image != null) {
				// 縮圖 in server side
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			os.write(image);

		} else if(action.equals("getImage")) {
			OutputStream os = res.getOutputStream();
			String mem_acc = jsonObject.get("mem_acc").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = memSvc.getImage(mem_acc);
			if (image != null) {
				// 縮圖 in server side
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			os.write(image);

		} else {
			writeText(res, "");
		}
		
	}
	
	private void writeText(HttpServletResponse res,String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

}
