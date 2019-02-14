package com.product.model;

import java.sql.*;
import java.util.*;

public class ProductJDBCDAO implements ProductDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO PRODUCT (PROD_ID, PROD_NAME, PROD_PRICE, PROD_TYPE, PROD_STOCK, PROD_PIC, PROD_PROMT_STATUS, PROD_STATUS, PROD_INFO) VALUES ('P'||LPAD(to_char(product_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM PRODUCT";
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, productVO.getProdName());
			pstmt.setDouble(2, productVO.getProdPrice());
			pstmt.setInt(3, productVO.getProdType());
			pstmt.setInt(4, productVO.getProdStock());
			pstmt.setBytes(5, productVO.getProdPic());
			pstmt.setInt(6, productVO.getProdPromtStatus());
			pstmt.setInt(7, productVO.getProdStatus());
			pstmt.setString(8, productVO.getProdInfo());
			
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
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, productVO.getProdName());
			pstmt.setDouble(2, productVO.getProdPrice());
			pstmt.setInt(3, productVO.getProdType());
			pstmt.setInt(4, productVO.getProdStock());
			pstmt.setBytes(5, productVO.getProdPic());
			pstmt.setInt(6, productVO.getProdPromtStatus());
			pstmt.setInt(7, productVO.getProdStatus());
			pstmt.setString(8, productVO.getProdInfo());
			pstmt.setString(9, productVO.getProdID());
			
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
	public void delete(String prodID) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, prodID);
			
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
	public ProductVO findByPrimaryKey(String prodID) {
		ProductVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, prodID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				prodVO = new ProductVO();
				prodVO.setProdID(rs.getString("PROD_ID"));
				prodVO.setProdName(rs.getString("PROD_NAME"));
				prodVO.setProdPrice(rs.getDouble("PROD_PRICE"));
				prodVO.setProdType(rs.getInt("PROD_TYPE"));
				prodVO.setProdStock(rs.getInt("PROD_STOCK"));
				prodVO.setProdPic(rs.getBytes("PROD_PIC"));
				prodVO.setProdPromtStatus(rs.getInt("PROD_PROMT_STATUS"));
				prodVO.setProdStatus(rs.getInt("PROD_STATUS"));
				prodVO.setProdInfo(rs.getString("PROD_INFO"));
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				prodVO = new ProductVO();
				prodVO.setProdID(rs.getString("PROD_ID"));
				prodVO.setProdName(rs.getString("PROD_NAME"));
				prodVO.setProdPrice(rs.getDouble("PROD_PRICE"));
				prodVO.setProdType(rs.getInt("PROD_TYPE"));
				prodVO.setProdStock(rs.getInt("PROD_STOCK"));
				prodVO.setProdPic(rs.getBytes("PROD_PIC"));
				prodVO.setProdPromtStatus(rs.getInt("PROD_PROMT_STATUS"));
				prodVO.setProdStatus(rs.getInt("PROD_STATUS"));
				prodVO.setProdInfo(rs.getString("PROD_INFO"));
				list.add(prodVO);
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
		
		ProductJDBCDAO dao = new ProductJDBCDAO();
		// 新增
		ProductVO prodVO1 = new ProductVO();
		prodVO1.setProdName("心痛的感覺");
		prodVO1.setProdPrice(new Double(699.88));
		prodVO1.setProdType(0);
		prodVO1.setProdStock(999);
		prodVO1.setProdPic(null);
		prodVO1.setProdPromtStatus(1);
		prodVO1.setProdStatus(0);
		prodVO1.setProdInfo("痛痛痛痛痛痛痛痛");
		dao.insert(prodVO1);
		
		//修改
		ProductVO prodVO2 = new ProductVO();
		prodVO2.setProdID("P000006");
		prodVO2.setProdName("心痛的感覺2");
		prodVO2.setProdPrice(new Double(999.11));
		prodVO2.setProdType(1);
		prodVO2.setProdStock(777);
		prodVO2.setProdPic(null);
		prodVO2.setProdPromtStatus(0);
		prodVO2.setProdStatus(1);
		prodVO2.setProdInfo("不痛痛痛痛痛痛痛痛");
		dao.update(prodVO2);
		
		//刪除
		dao.delete("P000006");
		
		//查詢1
		ProductVO prodVO3 = dao.findByPrimaryKey("P000003");
		
		System.out.println(prodVO3.getProdID() + ",");
		System.out.println(prodVO3.getProdName() + ",");
		System.out.println(prodVO3.getProdPrice() + ",");
		System.out.println(prodVO3.getProdType() + ",");
		System.out.println(prodVO3.getProdStock() + ",");
		System.out.println(prodVO3.getProdPic() + ",");
		System.out.println(prodVO3.getProdPromtStatus() + ",");
		System.out.println(prodVO3.getProdStatus() + ",");
		System.out.println(prodVO3.getProdInfo() + ",");
		System.out.println("----------------------------");
		//查詢全
		List<ProductVO> list = dao.getAll();
		
		for(ProductVO prodVO4 : list) {
		System.out.println(prodVO4.getProdID() + ",");
		System.out.println(prodVO4.getProdName() + ",");
		System.out.println(prodVO4.getProdPrice() + ",");
		System.out.println(prodVO4.getProdType() + ",");
		System.out.println(prodVO4.getProdStock() + ",");
		System.out.println(prodVO4.getProdPic() + ",");
		System.out.println(prodVO4.getProdPromtStatus() + ",");
		System.out.println(prodVO4.getProdStatus() + ",");
		System.out.println(prodVO4.getProdInfo() + ",");
		System.out.println("----------------------------");
		}
		

	}

}
