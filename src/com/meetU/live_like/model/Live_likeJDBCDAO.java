package com.meetU.live_like.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Live_likeJDBCDAO implements Live_likeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO LIVE_LIKE  (MEM_ID, HOST_ID) VALUES (?,?)";
	private static final String UPDATE = "UPDATE LIVE_LIKE set HOST_ID = ? where MEM_ID = ? and HOST_ID = ?";
	private static final String DELETE = "DELETE FROM LIVE_LIKE where MEM_ID = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE_LIKE where MEM_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE_LIKE";

	public Live_likeJDBCDAO() {

	}

	@Override
	public void insert(Live_likeVO live_likeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, live_likeVO.getMem_ID());
			pstmt.setString(2, live_likeVO.getHost_ID());

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
	public void update(Live_likeVO live_likeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, live_likeVO.getHost_ID());
			pstmt.setString(2, live_likeVO.getMem_ID());
			pstmt.setString(3, live_likeVO.getHost_ID());

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
	public void delete(Live_likeVO live_likeVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public Live_likeVO findByPrimaryKey(String men_ID, String host_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Live_likeVO> getALL() {
		// TODO Auto-generated method stub
		return null;
	}

}
