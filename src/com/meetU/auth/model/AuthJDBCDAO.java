package com.meetU.auth.model;

import java.sql.*;
import java.util.*;

public class AuthJDBCDAO implements AuthDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO AUTH (AUTH_ID, AUTH_NAME) VALUES ('AUTH'||LPAD(to_char(auth_seq.NEXTVAL), 6, '0'), ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM AUTH";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM AUTH where AUTH_ID = ?";
	private static final String DELETE = 
		"DELETE FROM AUTH where AUTH_ID = ?";
	private static final String UPDATE = 
		"UPDATE AUTH set AUTH_NAME=? where AUTH_ID = ?";
	
	@Override
	public void insert(AuthVO authVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, authVO.getAuth_name());
	
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
	public void update(AuthVO authVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, authVO.getAuth_name());
			pstmt.setString(2, authVO.getAuth_ID());
				
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
	public void delete(String auth_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, auth_ID);
			
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
	public AuthVO findByPrimaryKey(String auth_ID) {
		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, auth_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				authVO = new AuthVO();
				authVO.setAuth_ID(rs.getString("auth_ID"));
				authVO.setAuth_name(rs.getString("auth_name"));			
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
		return authVO;
	}

	@Override
	public List<AuthVO> getAll() {
		List<AuthVO> list = new ArrayList<>();
		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				authVO = new AuthVO();
				authVO.setAuth_ID(rs.getString("auth_ID"));
				authVO.setAuth_name(rs.getString("auth_name"));
				
				list.add(authVO);
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
	
	public static void main(String[] args) {
		
		AuthJDBCDAO dao = new AuthJDBCDAO();
		// 新增
		AuthVO authVO1 = new AuthVO();
		authVO1.setAuth_name("工讀生");
		dao.insert(authVO1);
		
		//修改
		AuthVO authVO2 = new AuthVO();
		authVO2.setAuth_ID("AUTH00070");	
	    authVO2.setAuth_name("工讀生2");
		dao.update(authVO2);
		
		//刪除
		//dao.delete("AUTH00070");
		
		//查詢1
		AuthVO authVO3 = dao.findByPrimaryKey("AUTH000010");
		
		System.out.println(authVO3.getAuth_ID() + ","); 
		System.out.println(authVO3.getAuth_name() + ","); 
		System.out.println("----------------------------");
		
		//查詢全
		List<AuthVO> list = dao.getAll();
		
		for(AuthVO authVO4 : list) {
			System.out.println(authVO4.getAuth_ID() + ","); 
			System.out.println(authVO4.getAuth_name() + ","); 
			System.out.println("----------------------------");
		}
		
	}
	

}
