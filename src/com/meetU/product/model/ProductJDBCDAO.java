package com.meetU.product.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
			
			pstmt.setString(1, productVO.getProd_name());
			pstmt.setDouble(2, productVO.getProd_price());
			pstmt.setInt(3, productVO.getProd_type());
			pstmt.setInt(4, productVO.getProd_stock());
			pstmt.setBytes(5, productVO.getProd_pic());
			pstmt.setInt(6, productVO.getProd_promt_status());
			pstmt.setInt(7, productVO.getProd_status());
			pstmt.setString(8, productVO.getProd_info());
			
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
	public void delete(String Prod_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, Prod_ID);
			
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
	public ProductVO findByPrimaryKey(String Prod_ID) {
		ProductVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	public static void main(String[] args) throws IOException {
		
		ProductJDBCDAO dao = new ProductJDBCDAO();
		
		File pic = new File("WebContent/FrontEnd/prod/pic/G8 (1).jpg");
//		System.out.println(pic.exists());
//		String fileName = pic.getName();
//		int dotPos = fileName.indexOf(".");
//		String fno = fileName.substring(0, dotPos);
		FileInputStream fis = new FileInputStream(pic);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		
		// 新增
		ProductVO prodVO1 = new ProductVO();
		prodVO1.setProd_name("心痛的感覺");
		prodVO1.setProd_price(new Double(699.88));
		prodVO1.setProd_type(0);
		prodVO1.setProd_stock(999);
		prodVO1.setProd_pic(baos.toByteArray());
		prodVO1.setProd_promt_status(1);
		prodVO1.setProd_status(0);
		prodVO1.setProd_info("痛痛痛痛痛痛痛痛");
		dao.insert(prodVO1);
		
		baos.close();
		fis.close();
		
//		//修改
//		ProductVO prodVO2 = new ProductVO();
//		prodVO2.setProd_ID("P000006");
//		prodVO2.setProd_name("心痛的感覺2");
//		prodVO2.setProd_price(new Double(999.11));
//		prodVO2.setProd_type(1);
//		prodVO2.setProd_stock(777);
//		prodVO2.setProd_pic(null);
//		prodVO2.setProd_promt_status(0);
//		prodVO2.setProd_status(1);
//		prodVO2.setProd_info("不痛痛痛痛痛痛痛痛");
//		dao.update(prodVO2);
//		
//		//刪除
//		dao.delete("P000006");
//		
//		//查詢1
//		ProductVO prodVO3 = dao.findByPrimaryKey("P000003");
//		
//		System.out.println(prodVO3.getProd_ID() + ",");
//		System.out.println(prodVO3.getProd_name() + ",");
//		System.out.println(prodVO3.getProd_price() + ",");
//		System.out.println(prodVO3.getProd_type() + ",");
//		System.out.println(prodVO3.getProd_stock() + ",");
//		System.out.println(prodVO3.getProd_pic() + ",");
//		System.out.println(prodVO3.getProd_promt_status() + ",");
//		System.out.println(prodVO3.getProd_status() + ",");
//		System.out.println(prodVO3.getProd_info() + ",");
//		System.out.println("----------------------------");
//		//查詢全
//		List<ProductVO> list = dao.getAll();
//		
//		for(ProductVO prodVO4 : list) {
//		System.out.println(prodVO4.getProd_ID() + ",");
//		System.out.println(prodVO4.getProd_name() + ",");
//		System.out.println(prodVO4.getProd_price() + ",");
//		System.out.println(prodVO4.getProd_type() + ",");
//		System.out.println(prodVO4.getProd_stock() + ",");
//		System.out.println(prodVO4.getProd_pic() + ",");
//		System.out.println(prodVO4.getProd_promt_status() + ",");
//		System.out.println(prodVO4.getProd_status() + ",");
//		System.out.println(prodVO4.getProd_info() + ",");
//		System.out.println("----------------------------");
//		}
		

	}

	@Override
	public List<ProductVO> getSome() {
		// TODO Auto-generated method stub
		return null;
	}

}
