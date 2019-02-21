


import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/** 
 * Servlet implementation class ShowPic
 */
public class ShowPic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String sql_prod = "SELECT PROD_PIC FROM PRODUCT WHERE PROD_ID = '";
	public static final String sql_live = "SELECT LIVE_PIC FROM LIVE WHERE HOST_ID = '";
	
	Connection con;
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Integer eqPos = req.getQueryString().indexOf("=");
		String column = req.getQueryString().substring(0, eqPos);
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		BufferedInputStream in = null;
		String col_pic= null;
		
		
		if ("PROD_ID".equals(column)) {
			sql = sql_prod+ req.getParameter(column)+"'";
			col_pic = "PROD_PIC";
		}else if ("HOST_ID".equals(column)) {
			sql = sql_live+ req.getParameter(column)+"'";
			col_pic = "LIVE_PIC";
			System.out.println(req.getParameter(column));
		}
		
		
		
		
		
			try {
				con = ds.getConnection();
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
	
				if (rs.next()) {
					in = new BufferedInputStream(rs.getBinaryStream(col_pic));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
				} else {
					res.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				if(out != null) {
					out.close();
				}
				if(in != null) 
					in.close();
				if(rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
				
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
