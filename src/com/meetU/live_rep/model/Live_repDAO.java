package com.meetU.live_rep.model;

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

public class Live_repDAO implements Live_repDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO LIVE_REP (REP_ID, HOST_ID, MEM_ID, REP_CONT, REP_DATE, REP_STATUS, REP_ANS, REP_ANS_DATE) VALUES ('LR'||LPAD(to_char(live_rep_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE LIVE_REP set REP_STATUS = ?,REP_ANS = ?,REP_ANS_DATE =? where REP_ID=?";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE_REP where MEM_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE_REP";

	public Live_repDAO() {

	}

	@Override
	public void insert(Live_repVO live_repVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, live_repVO.getHost_ID());
			pstmt.setString(2, live_repVO.getMem_ID());
			pstmt.setString(3, live_repVO.getRep_cont());
			pstmt.setTimestamp(4, live_repVO.getRep_date());
			pstmt.setInt(5, live_repVO.getRep_status());
			pstmt.setString(6, live_repVO.getRep_ans());
			pstmt.setTimestamp(7, live_repVO.getRep_ans_date());

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
	public void update(Live_repVO live_repVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, live_repVO.getRep_status());
			pstmt.setString(2, live_repVO.getRep_ans());
			pstmt.setTimestamp(3, live_repVO.getRep_ans_date());
			pstmt.setString(4, live_repVO.getRep_ID());

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
	public List<Live_repVO> findByPrimaryKey(String mem_ID) {
		List<Live_repVO> list = new ArrayList<>();
		Live_repVO live_repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, mem_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				live_repVO = new Live_repVO();

				live_repVO.setRep_ID(rs.getString("REP_ID"));
				live_repVO.setHost_ID(rs.getString("HOST_ID"));
				live_repVO.setMem_ID(rs.getString("MEM_ID"));
				live_repVO.setRep_cont(rs.getString("REP_CONT"));
				live_repVO.setRep_date(rs.getTimestamp("REP_DATE"));
				live_repVO.setRep_status(rs.getInt("REP_STATUS"));
				live_repVO.setRep_ans(rs.getString("REP_ANS"));
				live_repVO.setRep_ans_date(rs.getTimestamp("REP_ANS_DATE"));

				list.add(live_repVO);

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
	public List<Live_repVO> getALL() {
		List<Live_repVO> list = new ArrayList<>();
		Live_repVO live_repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				live_repVO = new Live_repVO();

				live_repVO.setRep_ID(rs.getString("REP_ID"));
				live_repVO.setHost_ID(rs.getString("HOST_ID"));
				live_repVO.setMem_ID(rs.getString("MEM_ID"));
				live_repVO.setRep_cont(rs.getString("REP_CONT"));
				live_repVO.setRep_date(rs.getTimestamp("REP_DATE"));
				live_repVO.setRep_status(rs.getInt("REP_STATUS"));
				live_repVO.setRep_ans(rs.getString("REP_ANS"));
				live_repVO.setRep_ans_date(rs.getTimestamp("REP_ANS_DATE"));

				list.add(live_repVO);

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
