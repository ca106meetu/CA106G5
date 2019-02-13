package com.checkIn.model;

import java.sql.*;
import java.util.*;


public class CheckInJDBCDAO implements CheckInDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO CHECK_IN (CHECK_IN_ID,CHECK_IN_NAME,CHECK_IN_LNT,CHECK_IN_LAT) VALUES ('CK'||LPAD(to_char(check_in_seq.NEXTVAL),6,'0'),?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT CHECK_IN_ID,CHECK_IN_NAME,CHECK_IN_LNT,CHECK_IN_LAT FROM CHECK_IN ORDER BY CHECK_IN_ID";
	private static final String GET_ONE_STMT = 
			"SELECT CHECK_IN_ID,CHECK_IN_NAME,CHECK_IN_LNT,CHECK_IN_LAT FROM CHECK_IN WHERE CHECK_IN_ID = ?";
	private static final String DELETE = 
			"DELETE FROM CHECK_IN WHERE CHECK_IN_ID = ?";
	private static final String UPDATE = 
			"UPDATE CHECK_IN SET CHECK_IN_NAME=?,CHECK_IN_LNT=?,CHECK_IN_LAT=? WHERE CHECK_IN_ID = ?";

	@Override
	public void insert(CheckInVO CheckInVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, CheckInVO.getCheck_in_name());
			pstmt.setDouble(2, CheckInVO.getCheck_in_lnt());
			pstmt.setDouble(3, CheckInVO.getCheck_in_lat());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch(SQLException se) {
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
	public void update(CheckInVO CheckInVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, CheckInVO.getCheck_in_name());
			pstmt.setDouble(2, CheckInVO.getCheck_in_lnt());
			pstmt.setDouble(3, CheckInVO.getCheck_in_lat());
			pstmt.setString(4, CheckInVO.getCheck_in_id());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch(SQLException se) {
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
	public void delete(String CheckIn_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, CheckIn_id);
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch(SQLException se) {
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
	public CheckInVO findByPrimaryKey(String CheckIn_id) {
		
		CheckInVO checkInVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, CheckIn_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				checkInVO = new CheckInVO();
				checkInVO.setCheck_in_id(rs.getString("CHECK_IN_ID"));
				checkInVO.setCheck_in_name(rs.getString("CHECK_IN_NAME"));
				checkInVO.setCheck_in_lnt(rs.getDouble("CHECK_IN_LNT"));
				checkInVO.setCheck_in_lat(rs.getDouble("CHECK_IN_LAT"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return checkInVO;
	}

	@Override
	public List<CheckInVO> getAll() {
		List<CheckInVO> list = new ArrayList<CheckInVO>();
		CheckInVO checkInVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				checkInVO = new CheckInVO();
				checkInVO.setCheck_in_id(rs.getString("CHECK_IN_ID"));
				checkInVO.setCheck_in_name(rs.getString("CHECK_IN_NAME"));
				checkInVO.setCheck_in_lnt(rs.getDouble("CHECK_IN_LNT"));
				checkInVO.setCheck_in_lat(rs.getDouble("CHECK_IN_LAT"));
				
				list.add(checkInVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	public static void main(String[] args) {
		CheckInJDBCDAO dao = new CheckInJDBCDAO();
		
		//新增
//		CheckInVO checkInVO1 = new CheckInVO();
//		checkInVO1.setCheck_in_name("測試1");
//		checkInVO1.setCheck_in_lnt(123.0);
//		checkInVO1.setCheck_in_lat(123.0);
//		dao.insert(checkInVO1);

		
		//修改
//		CheckInVO checkInVO2 = new CheckInVO();
//		checkInVO2.setCheck_in_id("CK000004");
//		checkInVO2.setCheck_in_name("測試2");
//		checkInVO2.setCheck_in_lnt(456.0);
//		checkInVO2.setCheck_in_lat(456.0);
//		dao.update(checkInVO2);
		
		//刪除
//		dao.delete("CK000005");
		
		//查詢一筆
		CheckInVO checkInVO3 = dao.findByPrimaryKey("CK000001");
		System.out.print(checkInVO3.getCheck_in_id()+",");
		System.out.print(checkInVO3.getCheck_in_name()+",");
		System.out.print(checkInVO3.getCheck_in_lnt()+",");
		System.out.println(checkInVO3.getCheck_in_lat());
		System.out.println("----------------------------");
		//查詢全部
		List<CheckInVO> list = dao.getAll();
		for(CheckInVO aCheckIn : list) {
			System.out.print(aCheckIn.getCheck_in_id()+",");
			System.out.print(aCheckIn.getCheck_in_name()+",");
			System.out.print(aCheckIn.getCheck_in_lnt()+",");
			System.out.print(aCheckIn.getCheck_in_lat());
			System.out.println();
		}
		
		
	}

}
