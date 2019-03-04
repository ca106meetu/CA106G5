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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.meetU.product.model.ProductJDBCDAO;
import com.meetU.product.model.ProductVO;

public class OrderMasterDAO implements OrderMasterDAO_interface {

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
		"INSERT INTO ORDER_MASTER (ORDER_ID, MEM_ID, PRICE, ORDER_DATE, TIP, OUT_ADD, RECIPIENT, PHONE, OUT_DATE, OUT_STATUS, ORDER_STATUS) VALUES ('OM'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ORDER_MASTER";
	private static final String GET_ONE_STMT = 
		"SELECT ORDER_ID, MEM_ID, PRICE, ORDER_DATE, TIP, OUT_ADD, RECIPIENT, PHONE, OUT_DATE, OUT_STATUS, ORDER_STATUS FROM ORDER_MASTER where ORDER_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ORDER_MASTER where ORDER_ID = ?";
	private static final String GET_OM_BY_MEM =
		"SELECT ORDER_ID, MEM_ID, PRICE, ORDER_DATE, TIP, OUT_ADD, RECIPIENT, PHONE, OUT_DATE, OUT_STATUS, ORDER_STATUS FROM ORDER_MASTER WHERE MEM_ID = ?";
	private static final String UPDATE = 
		"UPDATE ORDER_MASTER set MEM_ID=?, PRICE=?, ORDER_DATE=?, TIP=?, OUT_ADD=?, RECIPIENT=?, PHONE=?, OUT_DATE=?, OUT_STATUS=?, ORDER_STATUS=?  where ORDER_ID = ?";
	@Override
	public void insert(OrderMasterVO omVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
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
	
	public String insert(Connection con, OrderMasterVO omVO) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String key = null;
		String[] cols = { "ORDER_ID" };
		try {
		
			pstmt = con.prepareStatement(INSERT_STMT, cols);
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
			
			rs = pstmt.getGeneratedKeys();
			rs.next();
			key = rs.getString(1);
			

			
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			
		}
		return key;
	}

	@Override
	public void update(OrderMasterVO omVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, Order_ID);
			
			pstmt.executeUpdate();
			
			
		
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
			
			con = ds.getConnection();
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
			con = ds.getConnection();
			
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

	public OrderMasterDAO() {
	}

	@Override
	public List<OrderMasterVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<OrderMasterVO> getOmByMem(String mem_ID) {
		List<OrderMasterVO> list = new ArrayList<>();
		OrderMasterVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_OM_BY_MEM);
			pstmt.setString(1, mem_ID);
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
