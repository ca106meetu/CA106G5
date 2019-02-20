package com.meetU.gift.model;

import java.sql.*;
import java.util.*;


public class GiftJDBCDAO implements GiftDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO GIFT (GIFT_REC_ID, RECV_ID, SEND_ID, PROD_ID, GIFT_QUANTITY) VALUES ( 'GR'||LPAD(to_char(GIFT_REC_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM GIFT";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM GIFT where GIFT_REC_ID = ?";
	private static final String DELETE = 
		"DELETE FROM GIFT where GIFT_REC_ID = ?";
	private static final String UPDATE = 
		"UPDATE GIFT set RECV_ID=?, SEND_ID=?, PROD_ID=?, GIFT_QUANTITY=? where GIFT_REC_ID=?";
	@Override
	public void insert(GiftVO giftVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, giftVO.getRecv_ID());
			pstmt.setString(2, giftVO.getSend_ID());
			pstmt.setString(3, giftVO.getProd_ID());
			pstmt.setInt(4, giftVO.getGift_quantity());
			
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
/*
	public static void main(String[] args) {
		
		GiftJDBCDAO dao = new GiftJDBCDAO();
		
		// 新增
		GiftVO giftVO1 = new GiftVO();
		giftVO1.setRecv_ID("M000003");
		giftVO1.setSend_ID("M000001");
		giftVO1.setProd_ID("P000004");
		giftVO1.setGift_quantity(3);
		dao.insert(giftVO1);
		
		//修改
		GiftVO giftVO2 = new GiftVO();
		giftVO2.setGift_rec_ID("GR000012");
		giftVO2.setRecv_ID("M000003");
		giftVO2.setSend_ID("M000001");
		giftVO2.setProd_ID("P000003");
		giftVO2.setGift_quantity(18);
		dao.update(giftVO2);
		
		//刪除
		dao.delete("GR000012");
		
		//查詢1
		GiftVO giftVO3 = dao.findByPrimaryKey("GR000010");
		System.out.println(giftVO3.getGift_rec_ID() + ",");
		System.out.println(giftVO3.getRecv_ID() + ",");
		System.out.println(giftVO3.getSend_ID() + ",");
		System.out.println(giftVO3.getProd_ID() + ",");
		System.out.println(giftVO3.getGift_quantity() + ",");
		System.out.println("----------------------------");
		
		//查詢全
		List<GiftVO> list = dao.getAll();
		
		for( GiftVO giftVO4 : list) {
			System.out.println(giftVO4.getGift_rec_ID() + ",");
			System.out.println(giftVO4.getRecv_ID() + ",");
			System.out.println(giftVO4.getSend_ID() + ",");
			System.out.println(giftVO4.getProd_ID() + ",");
			System.out.println(giftVO4.getGift_quantity() + ",");
			System.out.println("----------------------------");
		}
		
	}
    */
	
}
