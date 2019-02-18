package com.meetU.emp.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmpDAO implements EmpDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/meetUDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
		"INSERT INTO EMP (EMP_ID, EMP_PW, EMP_NAME, EMP_BDAY, EMP_EMAIL, EMP_PHO, EMP_GEND, EMP_PIC, EMP_STATE, EMP_HDAY, EMP_ADDRESS) VALUES "
		+ " ('E'||LPAD(to_char(emp_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM EMP";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM EMP where EMP_ID = ?";
	private static final String DELETE = 
		"DELETE FROM EMP where EMP_ID=? ";
	private static final String UPDATE = 
		"UPDATE EMP set EMP_PW=?, EMP_NAME=?, EMP_BDAY=?, EMP_EMAIL=?, EMP_PHO=?, EMP_GEND=?, EMP_PIC=?, EMP_STATE=?, EMP_HDAY=?, EMP_ADDRESS=? where EMP_ID=?";
	
	@Override
	public void insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmp_pw());
			pstmt.setString(2, empVO.getEmp_name());
			pstmt.setDate(3, empVO.getEmp_bday());
			pstmt.setString(4, empVO.getEmp_email());
			pstmt.setString(5, empVO.getEmp_pho());
			pstmt.setString(6, empVO.getEmp_gend());
			pstmt.setBytes(7, empVO.getEmp_pic());
			pstmt.setInt(8, empVO.getEmp_state());
			pstmt.setDate(9, empVO.getEmp_hday());
			pstmt.setString(10, empVO.getEmp_address());

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
	public void update(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmp_pw());
			pstmt.setString(2, empVO.getEmp_name());
			pstmt.setDate(3, empVO.getEmp_bday());
			pstmt.setString(4, empVO.getEmp_email());
			pstmt.setString(5, empVO.getEmp_pho());
			pstmt.setString(6, empVO.getEmp_gend());
			pstmt.setBytes(7, empVO.getEmp_pic());
			pstmt.setInt(8, empVO.getEmp_state());
			pstmt.setDate(9, empVO.getEmp_hday());
			pstmt.setString(10, empVO.getEmp_address());
			pstmt.setString(11, empVO.getEmp_ID());

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
	public void delete(String emp_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, emp_ID);

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
	public EmpVO findByPrimaryKey(String emp_ID) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, emp_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmp_ID(rs.getString("emp_id"));
				empVO.setEmp_pw(rs.getString("emp_pw"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_bday(rs.getDate("emp_bday"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_pho(rs.getString("emp_pho"));
				empVO.setEmp_gend(rs.getString("emp_gend"));
				empVO.setEmp_pic(rs.getBytes("emp_pic"));
				empVO.setEmp_state(rs.getInt("emp_state"));
				empVO.setEmp_bday(rs.getDate("emp_bday"));
				empVO.setEmp_address(rs.getString("emp_address"));
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmp_ID(rs.getString("emp_id"));
				empVO.setEmp_pw(rs.getString("emp_pw"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_bday(rs.getDate("emp_bday"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_pho(rs.getString("emp_pho"));
				empVO.setEmp_gend(rs.getString("emp_gend"));
				empVO.setEmp_pic(rs.getBytes("emp_pic"));
				empVO.setEmp_state(rs.getInt("emp_state"));
				empVO.setEmp_bday(rs.getDate("emp_bday"));
				empVO.setEmp_address(rs.getString("emp_address"));
				list.add(empVO); // Store the row in the list
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