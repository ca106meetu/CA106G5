package com.meetU.live.model;

import java.sql.*;
import java.util.List; 

public class LiveJDBCDAO implements LiveDAO_interface {

	String drive = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO LIVE (HOST_ID, LIVE_NAME, LIVE_ACC, LIVE_PIC, LIVE_DATE, LIVE_STATUS) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE LIVE set LIVE_NAME = ?, LIVE_ACC = ?, LIVE_PIC = ?, LIVE_STATUS = ?  where HOST_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE where HOST_ID = ?";
	private static final String DELETE = "DELETE FROM LIVE where HOST_ID = ?";

	@Override
	public void insert(LiveVO liveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(drive);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void update(LiveVO liveVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(LiveVO liveVO) {
		// TODO Auto-generated method stub

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

}
