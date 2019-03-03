package com.meetU.live_chat.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Live_chatDAO implements Live_chatDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO LIVE_CHAT (CHAT_ID, HOST_ID, MEM_ID, CHAT_CONT, CHAT_DATE) VALUES('CH'||LPAD(to_char(live_chat_seq.NEXTVAL), 6, '0'),?,?,?,?)";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE_CHAT where HOST_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE_CHAT";

	public Live_chatDAO() {

	}

	@Override
	public void insert(Live_chatVO live_chatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, live_chatVO.getHost_ID());
			pstmt.setString(2, live_chatVO.getMem_ID());
			pstmt.setString(3, live_chatVO.getChat_cont());
			pstmt.setTimestamp(4, live_chatVO.getChat_date());

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

	@Override
	public List<Live_chatVO> findByPrimaryKey(String host_ID) {
		List<Live_chatVO> list = new ArrayList<>();
		Live_chatVO live_chatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, host_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				live_chatVO = new Live_chatVO();

				live_chatVO.setChat_ID(rs.getString("CHAT_ID"));
				live_chatVO.setHost_ID(rs.getString("HOST_ID"));
				live_chatVO.setMem_ID(rs.getString("MEM_ID"));
				live_chatVO.setChat_cont(rs.getString("CHAT_CONT"));
				live_chatVO.setChat_date(rs.getTimestamp("CHAT_DATE"));

				list.add(live_chatVO);
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

	@Override
	public List<Live_chatVO> getALL() {
		List<Live_chatVO> list = new ArrayList<>();
		Live_chatVO live_chatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				live_chatVO = new Live_chatVO();

				live_chatVO.setChat_ID(rs.getString("CHAT_ID"));
				live_chatVO.setHost_ID(rs.getString("HOST_ID"));
				live_chatVO.setMem_ID(rs.getString("MEM_ID"));
				live_chatVO.setChat_cont(rs.getString("CHAT_CONT"));
				live_chatVO.setChat_date(rs.getTimestamp("CHAT_DATE"));

				list.add(live_chatVO);
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
