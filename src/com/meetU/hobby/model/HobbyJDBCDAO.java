package com.meetU.hobby.model;

import java.sql.*;
import java.util.*;


public class HobbyJDBCDAO implements HobbyDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";
	private static final String INSERT_STMT = 
		"INSERT INTO HOBBY (hobby_ID, HOBBY_NAME) VALUES ('H'||LPAD(to_char(mem_hobby_seq.NEXTVAL), 5, '0'), ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM HOBBY";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM HOBBY where HOBBY_ID = ?";
	private static final String DELETE = 
		"DELETE FROM HOBBY where HOBBY_ID = ?";
	private static final String UPDATE = 
		"UPDATE HOBBY set HOBBY_NAME=? where HOBBY_ID = ?";
	@Override
	public void insert(HobbyVO hobbyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, hobbyVO.getHobby_name());
	
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
	public void update(HobbyVO hobbyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, hobbyVO.getHobby_name());
			pstmt.setString(2, hobbyVO.getHobby_ID());
				
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
	public void delete(String hobby_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, hobby_ID);
			
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
	public HobbyVO findByPrimaryKey(String hobby_ID) {
		HobbyVO hobbyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, hobby_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				hobbyVO = new HobbyVO();
				hobbyVO.setHobby_ID(rs.getString("hobby_ID"));
				hobbyVO.setHobby_name(rs.getString("hobby_name"));			
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
		return hobbyVO;
	}

	@Override
	public List<HobbyVO> getAll() {
		List<HobbyVO> list = new ArrayList<>();
		HobbyVO hobbyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				hobbyVO = new HobbyVO();
				hobbyVO.setHobby_ID(rs.getString("hobby_ID"));
				hobbyVO.setHobby_name(rs.getString("hobby_name"));
				
				list.add(hobbyVO);
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
		
		HobbyJDBCDAO dao = new HobbyJDBCDAO();
		// 新增
		HobbyVO hobbyVO1 = new HobbyVO();
		hobbyVO1.setHobby_name("JAVA");
		dao.insert(hobbyVO1);
		
		//修改
		HobbyVO hobbyVO2 = new HobbyVO();
		hobbyVO2.setHobby_ID("H00011");	
	    hobbyVO2.setHobby_name("JAVA");
		dao.update(hobbyVO2);
		
		//刪除
		//dao.delete("H00015");
		
		//查詢1
		HobbyVO hobbyVO3 = dao.findByPrimaryKey("H00010");
		
		System.out.println(hobbyVO3.getHobby_ID() + ","); 
		System.out.println(hobbyVO3.getHobby_name() + ","); 
		System.out.println("----------------------------");
		
		//查詢全
		List<HobbyVO> list = dao.getAll();
		
		for(HobbyVO hobbyVO4 : list) {
			System.out.println(hobbyVO4.getHobby_ID() + ","); 
			System.out.println(hobbyVO4.getHobby_name() + ","); 
			System.out.println("----------------------------");
		}
		
	}
}
