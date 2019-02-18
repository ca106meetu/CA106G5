package com.meetU.live.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Date;

public class LiveJDBCDAO implements LiveDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO LIVE (HOST_ID, LIVE_NAME, LIVE_ACC, LIVE_PIC, LIVE_DATE, LIVE_STATUS) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE LIVE set LIVE_NAME = ?, LIVE_ACC = ?, LIVE_PIC = ?, LIVE_STATUS = ?  where HOST_ID = ?";
	private static final String DELETE = "DELETE FROM LIVE where HOST_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE where HOST_ID = ?";

//	新增
	@Override
	public void insert(LiveVO liveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, liveVO.getHost_ID());
			pstmt.setString(2, liveVO.getLive_name());
			pstmt.setInt(3, liveVO.getLive_acc());
			pstmt.setBytes(4, liveVO.getLive_pic());
			pstmt.setTimestamp(5, liveVO.getLive_date());
			pstmt.setInt(6, liveVO.getLive_status());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

//	修改
	@Override
	public void update(LiveVO liveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, liveVO.getLive_name());
			pstmt.setInt(2, liveVO.getLive_acc());
			pstmt.setBytes(3, liveVO.getLive_pic());
			pstmt.setInt(4, liveVO.getLive_status());
			pstmt.setString(5, liveVO.getHost_ID());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

//  刪除
	@Override
	public void delete(LiveVO liveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, liveVO.getHost_ID());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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

	@Override
	public LiveVO findByPrimaryKey(String host_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LiveVO> getALL() {
		// TODO Auto-generated method stub
		return null;
	}

//	以下測試
	public static void main(String[] args) throws IOException {
		LiveJDBCDAO dao = new LiveJDBCDAO();

//		新增
		File pic = new File("WebContent/Smoke_pic/Live/P01.jpg");
		FileInputStream fis = new FileInputStream(pic);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8000];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		Date today = new Date();
		LiveVO liveVO1 = new LiveVO();

		liveVO1.setHost_ID("M000006");
		liveVO1.setLive_name("測試房間新增");
		liveVO1.setLive_acc(100);
		liveVO1.setLive_pic(baos.toByteArray());
		liveVO1.setLive_date(new Timestamp(today.getTime()));
		liveVO1.setLive_status(1);
		dao.insert(liveVO1);
		baos.close();
		fis.close();
		System.out.println("成功新增");

//		修改
		LiveVO liveVO2 = new LiveVO();

		liveVO2.setLive_name("測試房間修改");
		liveVO2.setLive_acc(200);
		liveVO2.setLive_pic(null);
		liveVO2.setLive_status(0);
		liveVO2.setHost_ID("M000006");
		dao.update(liveVO2);
		System.out.println("修改成功");

//		刪除
		LiveVO liveVO3 = new LiveVO();

		liveVO3.setHost_ID("M000006");
		dao.delete(liveVO3);
		System.out.println("刪除成功");

	}

}
