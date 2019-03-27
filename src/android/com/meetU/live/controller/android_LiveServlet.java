package android.com.meetU.live.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.com.meetU.live.model.LiveDAO;
import android.com.meetU.live.model.LiveService;
import android.com.meetU.live.model.LiveVO;
import android.com.meetU.main.ImageUtil;
import android.com.meetU.mem.model.MemVO;

@MultipartConfig
public class android_LiveServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public android_LiveServlet() {
		super();

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		LiveService liveSvc = new LiveService();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		if(action.equals("getAll")) {
			List<LiveVO> Livelist = liveSvc.getAll();
			writeText(res, gson.toJson(Livelist));
		} else if (action.equals("add")) {
			
			
		} else if(action.equals("getOneLive")) {
			String host_ID = jsonObject.get("host_ID").getAsString();
			LiveVO liveVO = liveSvc.getOneLive(host_ID);
			writeText(res, liveVO == null ? "" : gson.toJson(liveVO));		
		} else if(action.equals("getImage")) {
			OutputStream os = res.getOutputStream();
			String host_ID = jsonObject.get("mem_acc").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = liveSvc.getImage(host_ID);
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
