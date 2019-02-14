package com.meetU.orderDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meetU.orderDetail.model.OrderDetailVO;
import com.meetU.product.model.ProductVO;

public class OrderDetailJDBCDAO implements OrderDetailDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ORDER_DETAIL (PROD_ID, ORDER_ID, QUANTITY, PRICE) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ORDER_DETAIL";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM ORDER_DETAIL where PROD_ID = ? AND ORDER_ID = ?";
	private static final String UPDATE = 
		"UPDATE ORDER_DETAIL set QUANTITY=?, PRICE=? where PROD_ID = ? AND ORDER_ID = ?";
		

	@Override
	public void insert(OrderDetailVO odVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, odVO.getProdID());
			pstmt.setString(2, odVO.getOrderID());
			pstmt.setInt(3, odVO.getQuantity());
			pstmt.setDouble(4, odVO.getPrice());
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
	public void update(OrderDetailVO odVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, odVO.getQuantity());
			pstmt.setDouble(2, odVO.getPrice());
			pstmt.setString(3, odVO.getProdID());
			pstmt.setString(4, odVO.getOrderID());
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
	public OrderDetailVO findByPrimaryKey(String prodID, String orderID) {
		
		OrderDetailVO odVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, prodID);
			pstmt.setString(2, orderID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				odVO = new OrderDetailVO();
				odVO.setProdID(rs.getString("PROD_ID"));
				odVO.setOrderID(rs.getString("ORDER_ID"));
				odVO.setQuantity(rs.getInt("QUANTITY"));
				odVO.setPrice(rs.getDouble("PRICE"));
				
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
		return odVO;
	}

	@Override
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> list = new ArrayList<>();
		OrderDetailVO odVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				odVO = new OrderDetailVO();
				odVO.setProdID(rs.getString("PROD_ID"));
				odVO.setOrderID(rs.getString("ORDER_ID"));
				odVO.setQuantity(rs.getInt("QUANTITY"));
				odVO.setPrice(rs.getDouble("PRICE"));
				list.add(odVO);
				
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

	public OrderDetailJDBCDAO() {
	}
	
	public static void main(String[] args) {
		
		OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();
		
		
		
		//�s�W
//		OrderDetailVO odVO1 = new OrderDetailVO();
//		odVO1.setProdID("P000005");
//		odVO1.setOrderID("OM000005");
//		odVO1.setQuantity(5);
//		odVO1.setPrice(new Double(123321));
//		dao.insert(odVO1);
		
		//�ק�
		
		OrderDetailVO odVO2 = new OrderDetailVO();
		odVO2.setProdID("P000001");
		odVO2.setOrderID("OM000005");
		odVO2.setQuantity(6);
		odVO2.setPrice(new Double(321123));
		dao.update(odVO2);
		
		
		
		
		//�d��1
//		OrderDetailVO odVO3 = dao.findByPrimaryKey("P000001", "OM000001");
//		
//		System.out.println(odVO3.getProdID() + ",");
//		System.out.println(odVO3.getOrderID() + ",");
//		System.out.println(odVO3.getQuantity() + ",");
//		System.out.println(odVO3.getPrice() + ",");
//		System.out.println("----------------------------");
		
		
		
		//�d�ߥ�
//		List<OrderDetailVO> list = dao.getAll();
//				
//		for(OrderDetailVO odVO4 : list) {
//			System.out.println(odVO4.getProdID() + ",");
//			System.out.println(odVO4.getOrderID() + ",");
//			System.out.println(odVO4.getQuantity() + ",");
//			System.out.println(odVO4.getPrice() + ",");
//			System.out.println("----------------------------");
//		}

		
		
	}
}
