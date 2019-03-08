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
	public void update(EmpAuthVO empAuthVO_old, EmpAuthVO empAuthVO_new) {
		List<EmpAuthVO> list = new ArrayList<EmpAuthVO>();
		EmpAuthVO empAuthVOtemp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			pstmt = null;
			for(EmpAuthVO empAuthVO_each : list) {
				
				pstmt = con.prepareStatement(UPDATE_STEP3);
				pstmt.setString(1, empAuthVO_each.getEmp_ID());
				pstmt.setString(2, empAuthVO_each.getAuth_ID());
				
				pstmt.executeUpdate();
			}
			
			
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
	public void deleteAuths(String emp_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_AUTHS);
			
			pstmt.setString(1, emp_ID);
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
	public List<EmpAuthVO> findByPartOfOnePrimaryKey(String emp_ID){
		List<EmpAuthVO> list = new ArrayList<>();
		EmpAuthVO empAuthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PART_OF_ONE_STMT);
			pstmt.setString(1, emp_ID);
			
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
	
	@Override
	public List<String> findByPartOfOnePrimaryKey2(String emp_ID) {
		List<String> list = new ArrayList<>();
		String auth_ID = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PART_OF_ONE_STMT);
			
			pstmt.setString(1, emp_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empAuthVO 也稱為 Domain objects
				auth_ID = rs.getString("auth_ID");
				
				list.add(auth_ID);
			}
			
			// Handle any driver errors
		}  catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} 
		catch (SQLException se) {
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
	@Override
	public List<EmpAuthVO> insertAuths(List<EmpAuthVO> listEmpAuthVO) {
		List<EmpAuthVO> listEmpAuthVO_new = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			for(EmpAuthVO empAuthVO_each : listEmpAuthVO) {
				pstmt = null;
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, empAuthVO_each.getEmp_ID());
				pstmt.setString(2, empAuthVO_each.getAuth_ID());
				
				pstmt.executeUpdate();
			}
			listEmpAuthVO_new.addAll(listEmpAuthVO);
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
		return listEmpAuthVO_new;
		
	}
	public static void main(String[] args) {
		
		EmpAuthJDBCDAO dao = new EmpAuthJDBCDAO();
		// 新增
//		EmpAuthVO empAuthVO1 = new EmpAuthVO();
//		empAuthVO1.setEmp_ID("E000010");
//		empAuthVO1.setAuth_ID("AUTH00140");	
//		dao.insert(empAuthVO1);
		
		//修改
		EmpAuthVO empAuthVO2 = new EmpAuthVO();
		empAuthVO2.setEmp_ID("E000002");	
//	    empAuthVO2.setAuth_ID("AUTH00140");
	    dao.deleteAuths(empAuthVO2.getEmp_ID());
	    
	    List<EmpAuthVO> list = new ArrayList<>();
	    Integer j;
	    for(Integer i = 1; i < 6; i++) {
	    	empAuthVO2 = new EmpAuthVO();
	    	empAuthVO2.setEmp_ID("E000002");
	    	j = i * 10;
	    	String s = j.toString(); 
			empAuthVO2.setAuth_ID("AUTH000"+s);
			
			list.add(empAuthVO2);
		}	    
	    dao.insertAuths(list);
	    
//	    EmpAuthVO empAuthVO3 = new EmpAuthVO();
//	    empAuthVO3.setEmp_ID("E000002");	
//	    empAuthVO3.setAuth_ID("AUTH00140");
//		dao.insert(empAuthVO3);
		
//		//刪除
//		dao.delete("E000013", "AUTH00070");
//		
//		//查詢1
//		EmpAuthVO empAuthVO3 = dao.findByPrimaryKey("E000001","AUTH00010");
//		
//		System.out.println(empAuthVO3.getEmp_ID() + ","); 
//		System.out.println(empAuthVO3.getAuth_ID() + ","); 
//		System.out.println("----------------------------");
//		
//		//查詢全
//		List<EmpAuthVO> list = dao.getAll();
//		
//		for(EmpAuthVO empAuthVO4 : list) {
//			System.out.println(empAuthVO4.getEmp_ID() + ","); 
//			System.out.println(empAuthVO4.getAuth_ID() + ","); 
//			System.out.println("----------------------------");
//		}
//		
	}
	
}
