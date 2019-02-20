package com.meetU.giftbox.model;

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

import com.meetU.auth.model.AuthVO;

public class GiftboxDAO implements GiftboxDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
		"INSERT INTO GIFTBOX (MEM_ID, PROD_ID, GIFT_QUANTITY) VALUES ( ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM GIFTBOX";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM GIFTBOX where MEM_ID = ? AND PROD_ID=?";
	private static final String DELETE = 
		"DELETE FROM GIFTBOX WHERE MEM_ID = ? AND PROD_ID=?";
	private static final String UPDATE = 
		"UPDATE GIFTBOX set GIFT_QUANTITY=? WHERE MEM_ID=? AND PROD_ID=?";
	@Override
	public void insert(GiftboxVO giftboxVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, giftboxVO.getMem_ID());
			pstmt.setString(2, giftboxVO.getProd_ID());
			pstmt.setInt(3, giftboxVO.getGift_quantity());
			

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
	public void update(GiftboxVO giftboxVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, giftboxVO.getGift_quantity());
			pstmt.setString(2, giftboxVO.getMem_ID());
			pstmt.setString(3, giftboxVO.getProd_ID());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String mem_ID, String prod_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_ID);
			pstmt.setString(1, prod_ID);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public GiftboxVO findByPrimaryKey(String mem_ID, String prod_ID) {
		GiftboxVO giftboxVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_ID);
			pstmt.setString(2, prod_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// giftboxVO 也稱為 Domain objects
				giftboxVO = new GiftboxVO();
				giftboxVO.setMem_ID(rs.getString("mem_ID"));
				giftboxVO.setProd_ID(rs.getString("prod_ID"));
				giftboxVO.setGift_quantity(rs.getInt("gift_quantity"));
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
		return giftboxVO;
	}
	@Override
	public List<GiftboxVO> getAll() {
		List<GiftboxVO> list = new ArrayList<>();
		GiftboxVO giftboxVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// giftboxVO 也稱為 Domain objects
				giftboxVO = new GiftboxVO();
				giftboxVO.setMem_ID(rs.getString("mem_ID"));
				giftboxVO.setProd_ID(rs.getString("prod_ID"));
				giftboxVO.setGift_quantity(rs.getInt("gift_quantity"));
			
				list.add(giftboxVO);
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
	
	
}
