package com.meetU.inform.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.meetU.emp.model.EmpVO;

public class InformDAO implements InformDAO_interface{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
		"INSERT INTO INFORM (INFORM_ID, INFORM_STATUS, MEM_ID, INFORM_CONTENT, INFORM_TIME) VALUES ('INF'||LPAD(to_char(inform_seq.NEXTVAL), 6, '0'), ?, ?, ?, current_timestamp)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM INFORM";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM INFORM where INFORM_ID = ?";
	private static final String DELETE = 
		"DELETE FROM INFORM where INFORM_ID = ?";
	private static final String UPDATE = 
		"UPDATE INFORM set INFORM_STATUS=?, MEM_ID=?, INFORM_CONTENT=?, INFORM_TIME=current_timestamp where INFORM_ID = ?";
	@Override
	public void insert(InformVO informVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, informVO.getInform_status());
			pstmt.setString(2, informVO.getMem_ID());
			pstmt.setString(3, informVO.getInform_content());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void update(InformVO informVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, informVO.getInform_status());
			pstmt.setString(2, informVO.getMem_ID());
			pstmt.setString(3, informVO.getInform_content());
			pstmt.setString(4, informVO.getInform_ID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String inform_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, inform_ID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public InformVO findByPrimaryKey(String inform_ID) {
		InformVO informVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, inform_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// informVO 也稱為 Domain objects
				informVO = new InformVO();
				informVO.setInform_ID(rs.getString("inform_ID"));
				informVO.setInform_status(rs.getInt("inform_status"));
				informVO.setMem_ID(rs.getString("mem_ID"));
				informVO.setInform_content(rs.getString("inform_content"));
				informVO.setInform_time(rs.getTimestamp("inform_time"));
			
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return informVO;
	}
	@Override
	public List<InformVO> getAll() {
		List<InformVO> list = new ArrayList<InformVO>();
		InformVO informVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// informVO 也稱為 Domain objects
				informVO = new InformVO();
				informVO.setInform_ID(rs.getString("inform_ID"));
				informVO.setInform_status(rs.getInt("inform_status"));
				informVO.setMem_ID(rs.getString("mem_ID"));
				informVO.setInform_content(rs.getString("inform_content"));
				informVO.setInform_time(rs.getTimestamp("inform_time"));
				
				list.add(informVO);
				// Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
