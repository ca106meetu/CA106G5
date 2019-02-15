package com.meetU.giftbox.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meetU.friend.model.FriendVO;
import com.meetU.product.model.ProductJDBCDAO;
import com.meetU.product.model.ProductVO;

public class GiftboxJDBCDAO implements GiftboxDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, giftboxVO.getMem_ID());
			pstmt.setString(2, giftboxVO.getProd_ID());
			pstmt.setInt(3, giftboxVO.getGift_quantity());
			
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
	public void update(GiftboxVO giftboxVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, giftboxVO.getGift_quantity());
			pstmt.setString(2, giftboxVO.getMem_ID());
			pstmt.setString(3, giftboxVO.getProd_ID());
			
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
	public void delete(String mem_ID, String prod_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, mem_ID);
			pstmt.setString(2, prod_ID);
			
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
	public GiftboxVO findByPrimaryKey(String mem_ID, String prod_ID) {
		GiftboxVO giftboxVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, mem_ID);
			pstmt.setString(2, prod_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				giftboxVO = new GiftboxVO();
				giftboxVO.setMem_ID(rs.getString("mem_ID"));
				giftboxVO.setProd_ID(rs.getString("prod_ID"));
				giftboxVO.setGift_quantity(rs.getInt("gift_quantity"));
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				giftboxVO = new GiftboxVO();
				giftboxVO.setMem_ID(rs.getString("mem_ID"));
				giftboxVO.setProd_ID(rs.getString("prod_ID"));
				giftboxVO.setGift_quantity(rs.getInt("gift_quantity"));
				
				list.add(giftboxVO);
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
		
		GiftboxJDBCDAO dao = new GiftboxJDBCDAO();
		// 新增
		GiftboxVO giftboxVO1 = new GiftboxVO();
		giftboxVO1.setMem_ID("M000005");
		giftboxVO1.setProd_ID("P000004");
		giftboxVO1.setGift_quantity(1);
		dao.insert(giftboxVO1);
		
		//修改
		GiftboxVO giftboxVO2 = new GiftboxVO();
		giftboxVO2.setMem_ID("M000005");
		giftboxVO2.setProd_ID("P000004");
		giftboxVO2.setGift_quantity(50);
		dao.update(giftboxVO2);
		
		//刪除
		dao.delete("M000005", "P000004");
		
		//查詢1
		GiftboxVO giftboxVO3 = dao.findByPrimaryKey("M000003","P000001");
		
		System.out.println(giftboxVO3.getMem_ID() + ",");
		System.out.println(giftboxVO3.getProd_ID() + ",");
		System.out.println(giftboxVO3.getGift_quantity() + ",");
		System.out.println("----------------------------");
		//查詢全
		List<GiftboxVO> list = dao.getAll();
		
		for(GiftboxVO giftboxVO4 : list) {
			System.out.println(giftboxVO4.getMem_ID() + ",");
			System.out.println(giftboxVO4.getProd_ID() + ",");
			System.out.println(giftboxVO4.getGift_quantity() + ",");
			System.out.println("----------------------------");
		}
		

	}
}
