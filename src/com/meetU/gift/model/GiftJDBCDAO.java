package com.meetU.gift.model;

import java.sql.*;
import java.util.*;


public class GiftJDBCDAO implements GiftDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO GIFT (GIFT_REC_ID, RECV_ID, SEND_ID, PROD_ID, GIFT_QUANTITY) VALUES ( ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM GIFT";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM GIFT where GIFT_REC_ID = ?";
	private static final String DELETE = 
		"DELETE FROM GIFT where GIFT_REC_ID = ?";
	private static final String UPDATE = 
		"UPDATE FRIEND set RECV_ID=?, SEND_ID=?, PROD_ID=?, GIFT_QUANTITY=? where GIFT_REC_ID=?";
	@Override
	public void insert(GiftVO giftVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, giftVO.getGift_rec_ID());
			pstmt.setString(2, giftVO.getRecv_ID());
			pstmt.setString(3, giftVO.getSend_ID());
			pstmt.setString(4, giftVO.getProd_ID());
			pstmt.setInt(5, giftVO.getGift_quantity());
			
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
	public void update(GiftVO giftVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, giftVO.getRecv_ID());
			pstmt.setString(2, giftVO.getSend_ID());
			pstmt.setString(3, giftVO.getProd_ID());
			pstmt.setInt(4, giftVO.getGift_quantity());
			pstmt.setString(5, giftVO.getGift_rec_ID());
			
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
	public void delete(String gift_rec_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, gift_rec_ID);
			
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
	public GiftVO findByPrimaryKey(String gift_rec_ID) {
		GiftVO giftVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, gift_rec_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				giftVO = new GiftVO();
				giftVO.setGift_rec_ID(rs.getString("gift_rec_ID"));
				giftVO.setRecv_ID(rs.getString("recv_ID"));
				giftVO.setSend_ID(rs.getString("send_ID"));
				giftVO.setProd_ID(rs.getString("prod_ID"));
				giftVO.setGift_quantity(rs.getInt("gift_quantity"));			
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
		return giftVO;
	}

	@Override
	public List<GiftVO> getAll() {
		List<GiftVO> list = new ArrayList<>();
		GiftVO giftVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				giftVO = new GiftVO();
				giftVO.setGift_rec_ID(rs.getString("gift_rec_ID"));
				giftVO.setRecv_ID(rs.getString("recv_ID"));
				giftVO.setSend_ID(rs.getString("send_ID"));
				giftVO.setProd_ID(rs.getString("prod_ID"));
				giftVO.setGift_quantity(rs.getInt("gift_quantity"));
				list.add(giftVO);
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
