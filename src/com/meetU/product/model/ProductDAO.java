package com.meetU.product.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO implements ProductDAO_interface{
	
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
		"INSERT INTO PRODUCT (PROD_ID, PROD_NAME, PROD_PRICE, PROD_TYPE, PROD_STOCK, PROD_PIC, PROD_PROMT_STATUS, PROD_STATUS, PROD_INFO) VALUES ('P'||LPAD(to_char(product_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM PRODUCT";
	private static final String GET_SOME_STMT = 
			"SELECT * FROM PRODUCT WHERE PROD_STATUS = 0 AND PROD_STOCK > 0";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM PRODUCT where PROD_ID = ?";
	private static final String DELETE = 
		"DELETE FROM PRODUCT where PROD_ID = ?";
	private static final String UPDATE = 
		"UPDATE PRODUCT set PROD_NAME=?, PROD_PRICE=?, PROD_TYPE=?, PROD_STOCK=?, PROD_PIC=?, PROD_PROMT_STATUS=?, PROD_STATUS=?, PROD_INFO=? where PROD_ID = ?";

	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, productVO.getProd_name());
			pstmt.setDouble(2, productVO.getProd_price());
			pstmt.setInt(3, productVO.getProd_type());
			pstmt.setInt(4, productVO.getProd_stock());
			pstmt.setBytes(5, productVO.getProd_pic());
			pstmt.setInt(6, productVO.getProd_promt_status());
			pstmt.setInt(7, productVO.getProd_status());
			pstmt.setString(8, productVO.getProd_info());
			
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
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, productVO.getProd_name());
			pstmt.setDouble(2, productVO.getProd_price());
			pstmt.setInt(3, productVO.getProd_type());
			pstmt.setInt(4, productVO.getProd_stock());
			pstmt.setBytes(5, productVO.getProd_pic());
			pstmt.setInt(6, productVO.getProd_promt_status());
			pstmt.setInt(7, productVO.getProd_status());
			pstmt.setString(8, productVO.getProd_info());
			pstmt.setString(9, productVO.getProd_ID());
			
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
	public void delete(String Prod_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, Prod_ID);
			
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
	public ProductVO findByPrimaryKey(String Prod_ID) {
		ProductVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, Prod_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				prodVO = new ProductVO();
				prodVO.setProd_ID(rs.getString("PROD_ID"));
				prodVO.setProd_name(rs.getString("PROD_NAME"));
				prodVO.setProd_price(rs.getDouble("PROD_PRICE"));
				prodVO.setProd_type(rs.getInt("PROD_TYPE"));
				prodVO.setProd_stock(rs.getInt("PROD_STOCK"));
				prodVO.setProd_pic(rs.getBytes("PROD_PIC"));
				prodVO.setProd_promt_status(rs.getInt("PROD_PROMT_STATUS"));
				prodVO.setProd_status(rs.getInt("PROD_STATUS"));
				prodVO.setProd_info(rs.getString("PROD_INFO"));
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
		return prodVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<>();
		ProductVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				prodVO = new ProductVO();
				prodVO.setProd_ID(rs.getString("PROD_ID"));
				prodVO.setProd_name(rs.getString("PROD_NAME"));
				prodVO.setProd_price(rs.getDouble("PROD_PRICE"));
				prodVO.setProd_type(rs.getInt("PROD_TYPE"));
				prodVO.setProd_stock(rs.getInt("PROD_STOCK"));
				prodVO.setProd_pic(rs.getBytes("PROD_PIC"));
				prodVO.setProd_promt_status(rs.getInt("PROD_PROMT_STATUS"));
				prodVO.setProd_status(rs.getInt("PROD_STATUS"));
				prodVO.setProd_info(rs.getString("PROD_INFO"));
				list.add(prodVO);
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
	
	public List<ProductVO> getSome() {
		List<ProductVO> list = new ArrayList<>();
		ProductVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SOME_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				prodVO = new ProductVO();
				prodVO.setProd_ID(rs.getString("PROD_ID"));
				prodVO.setProd_name(rs.getString("PROD_NAME"));
				prodVO.setProd_price(rs.getDouble("PROD_PRICE"));
				prodVO.setProd_type(rs.getInt("PROD_TYPE"));
				prodVO.setProd_stock(rs.getInt("PROD_STOCK"));
				prodVO.setProd_pic(rs.getBytes("PROD_PIC"));
				prodVO.setProd_promt_status(rs.getInt("PROD_PROMT_STATUS"));
				prodVO.setProd_status(rs.getInt("PROD_STATUS"));
				prodVO.setProd_info(rs.getString("PROD_INFO"));
				list.add(prodVO);
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
