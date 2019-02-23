package com.meetU.empAuth.model;

import java.sql.*;
import java.util.*;


public class EmpAuthJDBCDAO implements EmpAuthDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO EMP_AUTH (EMP_ID, AUTH_ID) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM EMP_AUTH";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM EMP_AUTH where EMP_ID = ? AND AUTH_ID = ?";
	private static final String DELETE = 
		"DELETE FROM EMP_AUTH where EMP_ID = ? AND AUTH_ID = ?";
	private static final String UPDATE = 
		"UPDATE EMP_AUTH set EMP_ID=?, AUTH_ID=? where EMP_ID=? AND AUTH_ID = ?";//??
	@Override
	public void insert(EmpAuthVO empAuthVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, empAuthVO.getEmp_ID());
			pstmt.setString(2, empAuthVO.getAuth_ID());
	
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(EmpAuthVO empAuthVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, empAuthVO.getEmp_ID());
			pstmt.setString(2, empAuthVO.getAuth_ID());
			pstmt.setString(3, empAuthVO.getEmp_ID());//??
			pstmt.setString(4, empAuthVO.getAuth_ID());
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String emp_ID, String auth_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, emp_ID);
			pstmt.setString(2, auth_ID);
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public EmpAuthVO findByPrimaryKey(String emp_ID, String auth_ID) {
		EmpAuthVO empAuthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, emp_ID);
			pstmt.setString(2, auth_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				empAuthVO = new EmpAuthVO();
				empAuthVO.setEmp_ID(rs.getString("emp_ID"));
				empAuthVO.setAuth_ID(rs.getString("auth_ID"));			
			}
						
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		List<EmpAuthVO> list = new ArrayList<>();
		EmpAuthVO empAuthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				empAuthVO = new EmpAuthVO();
				empAuthVO.setEmp_ID(rs.getString("emp_ID"));
				empAuthVO.setAuth_ID(rs.getString("auth_ID"));
				
				list.add(empAuthVO);
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
    /*
	public static void main(String[] args) {
		
		EmpAuthJDBCDAO dao = new EmpAuthJDBCDAO();
		// 新增
		EmpAuthVO empAuthVO1 = new EmpAuthVO();
		empAuthVO1.setEmp_ID("E000013");
		empAuthVO1.setAuth_ID("AUTH00060");	
		dao.insert(empAuthVO1);
		
		//修改
//		EmpAuthVO empAuthVO2 = new EmpAuthVO();
//		empAuthVO2.setEmp_ID("E000013");	
//	    empAuthVO2.setAuth_ID("AUTH00060");
//		dao.update(empAuthVO2);
		
		//刪除
		dao.delete("E000013", "AUTH00070");
		
		//查詢1
		EmpAuthVO empAuthVO3 = dao.findByPrimaryKey("E000001","AUTH00010");
		
		System.out.println(empAuthVO3.getEmp_ID() + ","); 
		System.out.println(empAuthVO3.getAuth_ID() + ","); 
		System.out.println("----------------------------");
		
		//查詢全
		List<EmpAuthVO> list = dao.getAll();
		
		for(EmpAuthVO empAuthVO4 : list) {
			System.out.println(empAuthVO4.getEmp_ID() + ","); 
			System.out.println(empAuthVO4.getAuth_ID() + ","); 
			System.out.println("----------------------------");
		}
		
	}
	*/
}
