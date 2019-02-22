package com.meetU.ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AdJDBCDAO implements AdDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO AD (AD_ID, HOST_ID, AD_NAME, AD_CONT, AD_COST, APPLY_STATUS, AD_STAR, AD_END) VALUES ('AD'||LPAD(to_char(ad_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE AD set AD_NAME = ?, AD_CONT = ?, AD_COST = ?, APPLY_STATUS = ?, AD_STAR = ?, AD_END = ?  where AD_ID = ?";
	private static final String DELETE = "DELETE FROM LIVE where HOST_ID = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE where HOST_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE";

	public AdJDBCDAO() {

	}

	@Override
	public void insert(AdVO adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adVO.getHost_ID());
			pstmt.setString(2, adVO.getAd_name());
			pstmt.setBytes(3, adVO.getAd_cont());
			pstmt.setInt(4, adVO.getAd_cost());
			pstmt.setInt(5, adVO.getApply_status());
			pstmt.setDate(6, adVO.getAd_star());
			pstmt.setDate(7, adVO.getAd_end());

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

	@Override
	public void update(AdVO adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adVO.getHost_ID());
			pstmt.setString(2, adVO.getAd_name());
			pstmt.setBytes(3, adVO.getAd_cont());
			pstmt.setInt(4, adVO.getAd_cost());
			pstmt.setInt(5, adVO.getApply_status());
			pstmt.setDate(6, adVO.getAd_star());
			pstmt.setDate(7, adVO.getAd_end());

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

	@Override
	public void delete(AdVO adVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public AdVO findByPrimaryKey(String ad_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdVO> getALL() {
		// TODO Auto-generated method stub
		return null;
	}

}
