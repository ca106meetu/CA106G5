package com.meetU.toolClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitializeStatus implements ServletContextListener{
	
	private List<String> pt;
	private List<String> pps;
	private List<String> ps;
	private List<String> outs;
	private List<String> ords;
	private List<String> mS;
	private List<String> eS;
	private String[] arrayCity = {"台北市", "基隆市", "新北市", "連江縣", "宜蘭縣", "釣魚台", "新竹市", "新竹縣", "桃園市", "苗栗縣", "台中市", 
	           	"彰化縣", "南投縣", "嘉義市", "嘉義縣", "雲林縣", "台南市", "高雄市", "南海島", "澎湖縣", "金門縣", "屏東縣", "台東縣", "花蓮縣"};
	private List<String> listCity = Arrays.asList(arrayCity);

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContextListener.super.contextInitialized(sce);
		ServletContext context = sce.getServletContext();
		pt = new ArrayList<>();
		pt.add(0, "實體");
		pt.add(1, "虛擬");
		
		ps = new ArrayList<>();
		ps.add(0, "上架");
		ps.add(1, "下架");
		
		pps = new ArrayList<>();
		pps.add(0, "未促銷");
		pps.add(1, "促銷中");
		
		outs = new ArrayList<>();
		outs.add(0, "無退貨申請");
		outs.add(1, "退貨申請中");
		outs.add(2, "退貨成功");
		outs.add(3, "退貨失敗");
		
		ords = new ArrayList<>();
		ords.add(0,"待出貨");
		ords.add(1,"出貨中");
		ords.add(2,"已到貨");

		mS =  new ArrayList<>();
		mS.add(0, "尚未驗證");
		mS.add(1, "普通會員");
		
		eS = new ArrayList<>();
		eS.add(0, "尚未開通");
		eS.add(1, "已開通");
		eS.add(2, "離職");
				
		context.setAttribute("pt", pt);
		context.setAttribute("ps", ps);
		context.setAttribute("pps", pps);
		context.setAttribute("outs", outs);
		context.setAttribute("ords", ords);
		
		context.setAttribute("mS", mS);
		context.setAttribute("eS", eS);
		
		context.setAttribute("listCity", listCity);
		System.out.println("阿彌陀佛阿門阿拉~BUG不要來");
	}
	
}
