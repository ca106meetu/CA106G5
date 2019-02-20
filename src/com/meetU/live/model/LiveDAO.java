package com.meetU.live.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LiveDAO implements LiveDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO LIVE (HOST_ID, LIVE_NAME, LIVE_ACC, LIVE_PIC, LIVE_DATE, LIVE_STATUS) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE LIVE set LIVE_NAME = ?, LIVE_ACC = ?, LIVE_PIC = ?, LIVE_STATUS = ?  where HOST_ID = ?";
	private static final String DELETE = "DELETE FROM LIVE where HOST_ID = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE where HOST_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE";

	public LiveDAO() {

	}

	// 新增
	@Override
	public void insert(LiveVO liveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, liveVO.getHost_ID());
			pstmt.setString(2, liveVO.getLive_name());
			pstmt.setInt(3, liveVO.getLive_acc());
			pstmt.setBytes(4, liveVO.getLive_pic());
			pstmt.setTimestamp(5, liveVO.getLive_date());
			pstmt.setInt(6, liveVO.getLive_status());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, liveVO.getLive_name());
			pstmt.setInt(2, liveVO.getLive_acc());
			pstmt.setBytes(3, liveVO.getLive_pic());
			pstmt.setInt(4, liveVO.getLive_status());
			pstmt.setString(5, liveVO.getHost_ID());

			pstmt.executeUpdate();

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

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, liveVO.getHost_ID());

			pstmt.executeUpdate();

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

//  條件查詢
	@Override
	public LiveVO findByPrimaryKey(String host_ID) {
		LiveVO liveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, host_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				liveVO = new LiveVO();

				liveVO.setHost_ID(rs.getString("HOST_ID"));
				liveVO.setLive_name(rs.getString("LIVE_NAME"));
				liveVO.setLive_acc(rs.getInt("LIVE_ACC"));
				liveVO.setLive_pic(rs.getBytes("LIVE_PIC"));
				liveVO.setLive_date(rs.getTimestamp("LIVE_DATE"));
				liveVO.setLive_status(rs.getInt("LIVE_STATUS"));
			}

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
		return liveVO;
	}

//  查詢全部
	@Override
	public List<LiveVO> getALL() {
		List<LiveVO> list = new ArrayList<>();
		LiveVO liveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				liveVO = new LiveVO();

				liveVO.setHost_ID(rs.getString("HOST_ID"));
				liveVO.setLive_name(rs.getString("LIVE_NAME"));
				liveVO.setLive_acc(rs.getInt("LIVE_ACC"));
				liveVO.setLive_pic(rs.getBytes("LIVE_PIC"));
				liveVO.setLive_date(rs.getTimestamp("LIVE_DATE"));
				liveVO.setLive_status(rs.getInt("LIVE_STATUS"));

				list.add(liveVO);
			}
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

}
