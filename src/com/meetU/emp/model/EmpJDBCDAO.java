package com.meetU.emp.model;

import java.sql.*;
import java.util.*;


public class EmpJDBCDAO implements EmpDAO_interface {


	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String emp_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
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
	public EmpVO findByPrimaryKey(String emp_ID) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, emp_ID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				empVO = new EmpVO();
				empVO.setEmp_ID(rs.getString("emp_ID"));
				empVO.setEmp_pw(rs.getString("emp_pw"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_bday(rs.getDate("emp_bday"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_pho(rs.getString("emp_pho"));
				empVO.setEmp_gend(rs.getString("emp_gend"));
				empVO.setEmp_pic(rs.getBytes("emp_pic"));
				empVO.setEmp_state(rs.getInt("emp_state"));
				empVO.setEmp_hday(rs.getDate("emp_hday"));
				empVO.setEmp_address(rs.getString("emp_address"));
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
		return empVO;
	}
	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<>();
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				empVO = new EmpVO();
				empVO.setEmp_ID(rs.getString("emp_ID"));
				empVO.setEmp_pw(rs.getString("emp_pw"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_bday(rs.getDate("emp_bday"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_pho(rs.getString("emp_pho"));
				empVO.setEmp_gend(rs.getString("emp_gend"));
				empVO.setEmp_pic(rs.getBytes("emp_pic"));
				empVO.setEmp_state(rs.getInt("emp_state"));
				empVO.setEmp_hday(rs.getDate("emp_hday"));
				empVO.setEmp_address(rs.getString("emp_address"));
			
				list.add(empVO);
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
		
	    EmpJDBCDAO dao = new EmpJDBCDAO();
//		//新增
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEmp_pw("123456");	
//	    empVO1.setEmp_name("王子慶"); 
//	    empVO1.setEmp_bday(java.sql.Date.valueOf("1989-07-17"));
//	    empVO1.setEmp_email("bell0901tw@gmail.com");
//	    empVO1.setEmp_pho("0958885761");
//		empVO1.setEmp_gend("男性");
//	    empVO1.setEmp_pic(null);
//	    empVO1.setEmp_state(1);
//	    empVO1.setEmp_hday(java.sql.Date.valueOf("2017-09-20"));
//	    empVO1.setEmp_address("台北市-信義區-市府路1號");
//		dao.insert(empVO1);
//		
//		//修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmp_ID("E000011");
//		empVO2.setEmp_pw("123456");	
//	    empVO2.setEmp_name("王子慶2"); 
//	    empVO2.setEmp_bday(java.sql.Date.valueOf("1989-07-17"));
//	    empVO2.setEmp_email("bell0901tw@gmail.com");
//	    empVO2.setEmp_pho("0958885761");
//		empVO2.setEmp_gend("男性");
//	    empVO2.setEmp_pic(null);
//	    empVO2.setEmp_state(1);
//	    empVO2.setEmp_hday(java.sql.Date.valueOf("2017-09-20"));
//	    empVO2.setEmp_address("台北市-信義區-市府路2號");
//		dao.update(empVO2);
//		
//		//刪除
//		dao.delete("E000014");
//		
//		//查詢1
//		EmpVO empVO3 = dao.findByPrimaryKey("E000006");
//		
//		System.out.println(empVO3.getEmp_ID() + ","); 
//		System.out.println(empVO3.getEmp_pw() + ","); 
//		System.out.println(empVO3.getEmp_name() + ",");
//		System.out.println(empVO3.getEmp_bday() + ",");
//		System.out.println(empVO3.getEmp_email() + ",");
//		
//		System.out.println(empVO3.getEmp_pho() + ",");
//		System.out.println(empVO3.getEmp_gend() + ",");
//		System.out.println(empVO3.getEmp_pic() + ","); 
//		System.out.println(empVO3.getEmp_state() + ",");
//		System.out.println(empVO3.getEmp_hday() + ",");
//		System.out.println(empVO3.getEmp_address() + ","); 
//		
//		System.out.println("----------------------------");
//		
		//查詢全
		List<EmpVO> list = dao.getAll();
		
		for(EmpVO empVO4 : list) {
			System.out.println(empVO4.getEmp_ID() + ","); 
			System.out.println(empVO4.getEmp_pw() + ","); 
			System.out.println(empVO4.getEmp_name() + ",");
			System.out.println(empVO4.getEmp_bday() + ",");
			System.out.println(empVO4.getEmp_email() + ",");
			
			System.out.println(empVO4.getEmp_pho() + ",");
			System.out.println(empVO4.getEmp_gend() + ",");
			System.out.println(empVO4.getEmp_pic() + ","); 
			System.out.println(empVO4.getEmp_state() + ",");
			System.out.println(empVO4.getEmp_hday() + ",");
			System.out.println(empVO4.getEmp_address() + ","); 
			
			System.out.println("----------------------------");
		}
		
	}
	

}
