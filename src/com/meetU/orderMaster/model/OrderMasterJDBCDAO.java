package com.meetU.orderMaster.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.meetU.product.model.ProductJDBCDAO;
import com.meetU.product.model.ProductVO;

public class OrderMasterJDBCDAO implements OrderMasterDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ORDER_MASTER (ORDER_ID, MEM_ID, PRICE, ORDER_DATE, TIP, OUT_ADD, RECIPIENT, PHONE, OUT_DATE, OUT_STATUS, ORDER_STATUS) VALUES ('OM'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ORDER_MASTER";
	private static final String GET_ONE_STMT = 
		"SELECT ORDER_ID, MEM_ID, PRICE, ORDER_DATE, TIP, OUT_ADD, RECIPIENT, PHONE, OUT_DATE, OUT_STATUS, ORDER_STATUS FROM ORDER_MASTER where ORDER_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ORDER_MASTER where ORDER_ID = ?";
	private static final String UPDATE = 
		"UPDATE ORDER_MASTER set MEM_ID=?, PRICE=?, ORDER_DATE=?, TIP=?, OUT_ADD=?, RECIPIENT=?, PHONE=?, OUT_DATE=?, OUT_STATUS=?, ORDER_STATUS=?  where ORDER_ID = ?";
	@Override
	public void insert(OrderMasterVO omVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, omVO.getMem_ID());
			pstmt.setDouble(2, omVO.getPrice());
			pstmt.setTimestamp(3, omVO.getOrder_date());
			pstmt.setString(4, omVO.getTip());
			pstmt.setString(5, omVO.getOut_add());
			pstmt.setString(6, omVO.getRecipient());
			pstmt.setString(7, omVO.getPhone());
			pstmt.setTimestamp(8, omVO.getOut_date());
			pstmt.setInt(9, omVO.getOut_status());
			pstmt.setInt(10, omVO.getOrder_status());
			
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
	public void update(OrderMasterVO omVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, omVO.getMem_ID());
			pstmt.setDouble(2, omVO.getPrice());
			pstmt.setTimestamp(3, omVO.getOrder_date());
			pstmt.setString(4, omVO.getTip());
			pstmt.setString(5, omVO.getOut_add());
			pstmt.setString(6, omVO.getRecipient());
			pstmt.setString(7, omVO.getPhone());
			pstmt.setTimestamp(8, omVO.getOut_date());
			pstmt.setInt(9, omVO.getOut_status());
			pstmt.setInt(10, omVO.getOrder_status());
			pstmt.setString(11, omVO.getOrder_ID());
			
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
	public void delete(String Order_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, Order_ID);
			
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
	public OrderMasterVO findByPrimaryKey(String Order_ID) {
		
		OrderMasterVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, Order_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				orderVO = new OrderMasterVO();
				orderVO.setOrder_ID(rs.getString("ORDER_ID"));
				orderVO.setMem_ID(rs.getString("MEM_ID"));
				orderVO.setPrice(rs.getDouble("PRICE"));
				orderVO.setOrder_date(rs.getTimestamp("ORDER_DATE"));
				orderVO.setTip(rs.getString("TIP"));
				orderVO.setOut_add(rs.getString("OUT_ADD"));
				orderVO.setRecipient(rs.getString("RECIPIENT"));
				orderVO.setPhone(rs.getString("PHONE"));
				orderVO.setOut_date(rs.getTimestamp("OUT_DATE"));
				orderVO.setOut_status(rs.getInt("OUT_STATUS"));
				orderVO.setOrder_status(rs.getInt("ORDER_STATUS"));
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
		return orderVO;
	}

	@Override
	public List<OrderMasterVO> getAll() {
		
		List<OrderMasterVO> list = new ArrayList<>();
		OrderMasterVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				orderVO = new OrderMasterVO();
				orderVO.setOrder_ID(rs.getString("ORDER_ID"));
				orderVO.setMem_ID(rs.getString("MEM_ID"));
				orderVO.setPrice(rs.getDouble("PRICE"));
				orderVO.setOrder_date(rs.getTimestamp("ORDER_DATE"));
				orderVO.setTip(rs.getString("TIP"));
				orderVO.setOut_add(rs.getString("OUT_ADD"));
				orderVO.setRecipient(rs.getString("RECIPIENT"));
				orderVO.setPhone(rs.getString("PHONE"));
				orderVO.setOut_date(rs.getTimestamp("OUT_DATE"));
				orderVO.setOut_status(rs.getInt("OUT_STATUS"));
				orderVO.setOrder_status(rs.getInt("ORDER_STATUS"));
				list.add(orderVO);
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

	public OrderMasterJDBCDAO() {
	}

	public static void main(String[] args) {
		
		OrderMasterJDBCDAO dao = new OrderMasterJDBCDAO();
		// 新增
		
		Date today = new Date();
		
		OrderMasterVO orderVO1 = new OrderMasterVO();
		orderVO1.setMem_ID("M000002");
		orderVO1.setPrice(new Double(666.99));
		orderVO1.setOrder_date(new Timestamp(today.getTime()));
		orderVO1.setTip("一三五七九");
		orderVO1.setOut_add("在你心裡");
		orderVO1.setRecipient("馬小九");
		orderVO1.setPhone("0800092000");
		orderVO1.setOut_date(new Timestamp(today.getTime()));
		orderVO1.setOut_status(1);
		orderVO1.setOrder_status(0);
		dao.insert(orderVO1);
		
		//修改
		OrderMasterVO orderVO2 = new OrderMasterVO();
		orderVO2.setOrder_ID("OM000002");
		orderVO2.setMem_ID("M000002");
		orderVO2.setPrice(new Double(666.99));
		orderVO2.setOrder_date(new Timestamp(today.getTime()));
		orderVO2.setTip("一三五七九");
		orderVO2.setOut_add("在你心裡");
		orderVO2.setRecipient("馬小九");
		orderVO2.setPhone("0800092000");
		orderVO2.setOut_date(new Timestamp(today.getTime()));
		orderVO2.setOut_status(1);
		orderVO2.setOrder_status(0);
		dao.update(orderVO2);
		
		//刪除
		dao.delete("OM000006");
		
		//查詢1
		OrderMasterVO orderVO3 = dao.findByPrimaryKey("OM000003");
//		System.out.println(orderVO3 == null);
		System.out.println(orderVO3.getOrder_ID() + ",");
		System.out.println(orderVO3.getMem_ID() + ",");
		System.out.println(orderVO3.getPrice() + ",");
		System.out.println(orderVO3.getOrder_date() + ",");
		System.out.println(orderVO3.getTip() + ",");
		System.out.println(orderVO3.getOut_add() + ",");
		System.out.println(orderVO3.getRecipient() + ",");
		System.out.println(orderVO3.getPhone() + ",");
		System.out.println(orderVO3.getOut_date() + ",");
		System.out.println(orderVO3.getOut_status() + ",");
		System.out.println(orderVO3.getOrder_status() + ",");
		System.out.println("----------------------------");
		//查詢全
//		List<OrderMasterVO> list = dao.getAll();
////		
//		for(OrderMasterVO orderVO4 : list) {
//			System.out.println(orderVO4.getOrder_ID() + ",");
//			System.out.println(orderVO4.getMem_ID() + ",");
//			System.out.println(orderVO4.getPrice() + ",");
//			System.out.println(orderVO4.getOrder_date() + ",");
//			System.out.println(orderVO4.getTip() + ",");
//			System.out.println(orderVO4.getOut_add() + ",");
//			System.out.println(orderVO4.getRecipient() + ",");
//			System.out.println(orderVO4.getPhone() + ",");
//			System.out.println(orderVO4.getOut_date() + ",");
//			System.out.println(orderVO4.getOut_status() + ",");
//			System.out.println(orderVO4.getOrder_status() + ",");
//		System.out.println("----------------------------");
//		}

	}

	@Override
	public List<OrderMasterVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<OrderMasterVO> getOmByMem(String mem_ID) {
		// TODO Auto-generated method stub
		return null;
	}

}
