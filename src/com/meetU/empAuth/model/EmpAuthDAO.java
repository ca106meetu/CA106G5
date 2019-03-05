package com.meetU.empAuth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class EmpAuthDAO implements EmpAuthDAO_interface {
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
		"INSERT INTO EMP_AUTH (EMP_ID, AUTH_ID) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM EMP_AUTH";
	private static final String GET_PART_OF_ONE_STMT = 
		"SELECT * FROM EMP_AUTH where EMP_ID = ? ";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM EMP_AUTH where EMP_ID = ? AND AUTH_ID = ?";
	private static final String DELETE = 
		"DELETE FROM EMP_AUTH where EMP_ID = ? AND AUTH_ID = ?";
	private static final String DELETE_AUTHS = 
		"DELETE FROM EMP_AUTH where EMP_ID = ?";
	private static final String UPDATE_STEP1 = 
		"SELECT * FROM EMP_AUTH where EMP_ID = ?";
	private static final String UPDATE_STEP2 = 
		"DELETE FROM EMP_AUTH where EMP_ID = ?";
	private static final String UPDATE_STEP3 = 
		"INSERT INTO EMP_AUTH (EMP_ID, AUTH_ID) VALUES (?, ?)";//??
	@Override
	public void insert(EmpAuthVO empAuthVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empAuthVO.getEmp_ID());
			pstmt.setString(2, empAuthVO.getAuth_ID());

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
	public void update(EmpAuthVO empAuthVO_old, EmpAuthVO empAuthVO_new) {
		List<EmpAuthVO> list = new ArrayList<EmpAuthVO>();
		EmpAuthVO empAuthVOtemp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STEP1);
			pstmt.setString(1, empAuthVO_old.getEmp_ID());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				empAuthVOtemp = new EmpAuthVO();
				empAuthVOtemp.setEmp_ID(rs.getString("emp_ID"));
				empAuthVOtemp.setAuth_ID(rs.getString("auth_ID"));
				list.add(empAuthVOtemp);
			}
			
			for(EmpAuthVO empAuthVO_each : list) {
				if(empAuthVO_each.getAuth_ID().equals(empAuthVO_old.getAuth_ID())) {
					empAuthVO_each.setAuth_ID(empAuthVO_new.getAuth_ID());
				}
			}
			
			pstmt = null;
			pstmt = con.prepareStatement(UPDATE_STEP2);
			pstmt.setString(1, empAuthVO_old.getEmp_ID());
			pstmt.executeUpdate();
			
			
			for(EmpAuthVO empAuthVO_each : list) {
				pstmt = null;
				pstmt = con.prepareStatement(UPDATE_STEP3);
				pstmt.setString(1, empAuthVO_each.getEmp_ID());
				pstmt.setString(2, empAuthVO_each.getAuth_ID());
				
				pstmt.executeUpdate();
			}
			
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
	public void delete(String emp_ID, String auth_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, emp_ID);
			pstmt.setString(2, auth_ID);

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
	public void deleteAuths(String emp_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_AUTHS);

			pstmt.setString(1, emp_ID);
			
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
	public List<EmpAuthVO> findByPartOfOnePrimaryKey(String emp_ID) {
		List<EmpAuthVO> list = new ArrayList<>();
		EmpAuthVO empAuthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PART_OF_ONE_STMT);

			pstmt.setString(1, emp_ID);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empAuthVO 也稱為 Domain objects
				empAuthVO = new EmpAuthVO();
				empAuthVO.setEmp_ID(rs.getString("emp_ID"));
				empAuthVO.setAuth_ID(rs.getString("auth_ID"));
				
				list.add(empAuthVO);
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
	
	@Override
	public EmpAuthVO findByPrimaryKey(String emp_ID, String auth_ID) {
		EmpAuthVO empAuthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, emp_ID);
			pstmt.setString(2, auth_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empAuthVO 也稱為 Domain objects
				empAuthVO = new EmpAuthVO();
				empAuthVO.setEmp_ID(rs.getString("emp_ID"));
				empAuthVO.setAuth_ID(rs.getString("auth_ID"));
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
		return empAuthVO;
	}
	
	@Override
	public List<EmpAuthVO> getAll() {
		List<EmpAuthVO> list = new ArrayList<EmpAuthVO>();
		EmpAuthVO empAuthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empAuthVO 也稱為 Domain objects
				empAuthVO = new EmpAuthVO();
				empAuthVO.setEmp_ID(rs.getString("emp_ID"));
				empAuthVO.setAuth_ID(rs.getString("auth_ID"));

				list.add(empAuthVO);
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
	@Override
	public List<EmpAuthVO> insertAuths(List<EmpAuthVO> listEmpAuthVO) {
		List<EmpAuthVO> listEmpAuthVO_new = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
						
			
			for(EmpAuthVO empAuthVO_each : listEmpAuthVO) {
				pstmt = null;
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, empAuthVO_each.getEmp_ID());
				pstmt.setString(2, empAuthVO_each.getAuth_ID());
				
				pstmt.executeUpdate();
			}
			listEmpAuthVO_new.addAll(listEmpAuthVO);
			
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
			return listEmpAuthVO_new;
		}
		
	}
		
}
