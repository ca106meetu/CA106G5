package com.meetU.sale.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meetU.saleType.model.SaleTypeVO;

public class SaleJDBCDAO implements SaleDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO SALE (SALE_ID, SALE_TYPE_ID, SALE_NAME, START_DATE, END_DATE, SALE_INFO, SALE_PIC) VALUES ('S'||LPAD(to_char(product_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM SALE";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM SALE where SALE_ID = ?";
	private static final String UPDATE = 
		"UPDATE SALE set SALE_TYPE_ID = ?, SALE_NAME = ?, START_DATE = ?, END_DATE = ?, SALE_INFO = ?, SALE_PIC = ? WHERE SALE_ID = ?";


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(SaleVO sVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, sVO.getSale_type_ID());
			pstmt.setString(2, sVO.getSale_name());
			pstmt.setTimestamp(3, sVO.getStart_date());
			pstmt.setTimestamp(4, sVO.getEnd_date());
			pstmt.setString(5, sVO.getSale_info());
			pstmt.setBytes(6, sVO.getSale_pic());
			
			
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
	public void update(SaleVO sVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, sVO.getSale_type_ID());
			pstmt.setString(2, sVO.getSale_name());
			pstmt.setTimestamp(3, sVO.getStart_date());
			pstmt.setTimestamp(4, sVO.getEnd_date());
			pstmt.setString(5, sVO.getSale_info());
			pstmt.setBytes(6, sVO.getSale_pic());
			pstmt.setString(7, sVO.getSale_ID());
			
			
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
	public SaleVO findByPrimaryKey(String Sale_ID) {
		SaleVO sVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, Sale_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sVO = new SaleVO();
				sVO.setSale_ID(rs.getString("SALE_ID"));
				sVO.setSale_type_ID(rs.getString("SALE_TYPE_ID"));
				sVO.setSale_name(rs.getString("SALE_NAME"));
				sVO.setStart_date(rs.getTimestamp("START_DATE"));
				sVO.setEnd_date(rs.getTimestamp("END_DATE"));
				sVO.setSale_info(rs.getString("SALE_INFO"));
				sVO.setSale_pic(rs.getBytes("SALE_PIC"));
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
		return sVO;

	}

	@Override
	public List<SaleVO> getAll() {
		List<SaleVO> list = new ArrayList<>();
		SaleVO sVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sVO = new SaleVO();
				sVO.setSale_ID(rs.getString("SALE_ID"));
				sVO.setSale_type_ID(rs.getString("SALE_TYPE_ID"));
				sVO.setSale_name(rs.getString("SALE_NAME"));
				sVO.setStart_date(rs.getTimestamp("START_DATE"));
				sVO.setEnd_date(rs.getTimestamp("END_DATE"));
				sVO.setSale_info(rs.getString("SALE_INFO"));
				sVO.setSale_pic(rs.getBytes("SALE_PIC"));
				list.add(sVO);
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

}
