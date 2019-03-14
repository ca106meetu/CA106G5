package com.meetU.stick.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StickJDBCDAO implements StickDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String GET_ONE_STMT = "SELECT * FROM STICK where STICK_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM STICK";

	public StickJDBCDAO() {

	}

	@Override
	public StickVO findByPrimaryKey(String stick_ID) {
		StickVO stickVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, stick_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				stickVO = new StickVO();

				stickVO.setStick_ID(rs.getString("STICK_ID"));
				stickVO.setStick_name(rs.getString("STICK_NAME"));
				stickVO.setSticker(rs.getBytes("STICKER"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

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
		return stickVO;
	}

	@Override
	public List<StickVO> getALL() {
		List<StickVO> list = new ArrayList<>();
		StickVO stickVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				stickVO = new StickVO();

				stickVO.setStick_ID(rs.getString("STICK_ID"));
				stickVO.setStick_name(rs.getString("STICK_NAME"));
				stickVO.setSticker(rs.getBytes("STICKER"));

				list.add(stickVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}

	// 以下測試
	public static void main(String[] args) throws IOException {
		StickJDBCDAO dao = new StickJDBCDAO();

//			條件查詢
		StickVO stickVO = dao.findByPrimaryKey("ST000001");
		System.out.println(stickVO.getStick_ID() + ",");
		System.out.println(stickVO.getStick_name() + ",");
		System.out.println(stickVO.getSticker() + ",");
		System.out.println("----------------------------");

//			查詢全部
		List<StickVO> list = dao.getALL();
		for (StickVO stickVO2 : list) {
			System.out.println(stickVO2.getStick_ID() + ",");
			System.out.println(stickVO2.getStick_name() + ",");
			System.out.println(stickVO2.getSticker() + ",");
			System.out.println("----------------------------");
		}
	}

}
