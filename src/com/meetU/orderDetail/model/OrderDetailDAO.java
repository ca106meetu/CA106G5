package com.meetU.orderDetail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.meetU.orderDetail.model.OrderDetailVO;
import com.meetU.orderMaster.model.OrderMasterDAO;
import com.meetU.orderMaster.model.OrderMasterVO;
import com.meetU.product.model.ProductVO;

public class OrderDetailDAO implements OrderDetailDAO_interface{

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
		"INSERT INTO ORDER_DETAIL (PROD_ID, ORDER_ID, QUANTITY, PRICE) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ORDER_DETAIL";
	private static final String GET_OD_BY_OM = 
			"SELECT * FROM ORDER_DETAIL where ORDER_ID = ?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM ORDER_DETAIL where PROD_ID = ? AND ORDER_ID = ?";
	private static final String UPDATE = 
		"UPDATE ORDER_DETAIL set QUANTITY=?, PRICE=? where PROD_ID = ? AND ORDER_ID = ?";
	private static final String DELETE = 
			"DELETE FROM ORDER_DETAIL where PROD_ID = ? AND ORDER_ID = ?";
		

	@Override
	public void insert(OrderDetailVO odVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, odVO.getProd_ID());
			pstmt.setString(2, odVO.getOrder_ID());
			pstmt.setInt(3, odVO.getQuantity());
			pstmt.setDouble(4, odVO.getPrice());
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
	public void update(OrderDetailVO odVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, odVO.getQuantity());
			pstmt.setDouble(2, odVO.getPrice());
			pstmt.setString(3, odVO.getProd_ID());
			pstmt.setString(4, odVO.getOrder_ID());
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
	public OrderDetailVO findByPrimaryKey(String prod_ID, String order_ID) {
		
		OrderDetailVO odVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, prod_ID);
			pstmt.setString(2, order_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				odVO = new OrderDetailVO();
				odVO.setProd_ID(rs.getString("PROD_ID"));
				odVO.setOrder_ID(rs.getString("ORDER_ID"));
				odVO.setQuantity(rs.getInt("QUANTITY"));
				odVO.setPrice(rs.getDouble("PRICE"));
				
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
		return odVO;
	}
	
	public void delete(String prod_ID, String order_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, prod_ID);
			pstmt.setString(2, order_ID);
			
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
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> list = new ArrayList<>();
		OrderDetailVO odVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				odVO = new OrderDetailVO();
				odVO.setProd_ID(rs.getString("PROD_ID"));
				odVO.setOrder_ID(rs.getString("ORDER_ID"));
				odVO.setQuantity(rs.getInt("QUANTITY"));
				odVO.setPrice(rs.getDouble("PRICE"));
				list.add(odVO);
				
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

	public OrderDetailDAO() {
	}


	@Override
	public void insertList(OrderMasterVO omVO, List<ProductVO> buyList) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		OrderMasterDAO ordDAO = new OrderMasterDAO();
		try {
			
			
			con = ds.getConnection();
			con.setAutoCommit(false);
			String key = ordDAO.insert(con, omVO);
			
			
			pstmt = con.prepareStatement(INSERT_STMT);
			
			for(ProductVO prodVO: buyList) {
			pstmt.setString(1, prodVO.getProd_ID());
			pstmt.setString(2, key);
			pstmt.setInt(3, prodVO.getQuantity());
			pstmt.setDouble(4, prodVO.getProd_price());
			pstmt.executeUpdate();
			pstmt.clearParameters();
			}
			
			
			con.commit();
			con.setAutoCommit(true);
			
			Vector<ProductVO> list = (Vector<ProductVO>) buyList;
			list.removeAllElements();
			
			
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
	public List<OrderDetailVO> findOdByOm(String order_ID) {
		List<OrderDetailVO> list = new ArrayList<>();
		OrderDetailVO odVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_OD_BY_OM);
			pstmt.setString(1, order_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				odVO = new OrderDetailVO();
				odVO.setProd_ID(rs.getString("PROD_ID"));
				odVO.setOrder_ID(rs.getString("ORDER_ID"));
				odVO.setQuantity(rs.getInt("QUANTITY"));
				odVO.setPrice(rs.getDouble("PRICE"));
				list.add(odVO);
				
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
