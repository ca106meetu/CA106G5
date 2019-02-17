package com.meetU.saleDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meetU.saleType.model.SaleTypeJDBCDAO;

public class SaleDetailJDBCDAO implements SaleDetailDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO SALE_DETAIL (PROD_ID, SALE_ID, PROMT_PRICE) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM SALE_DETAIL";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM SALE_DETAIL where PROD_ID = ? AND SALE_ID = ?";
	private static final String DELETE =
			"DELETE FROM SALE_DETAIL WHERE PROD_ID = ? AND SALE_ID = ?";
	private static final String UPDATE = 
		"UPDATE SALE_DETAIL set PROMT_PRICE = ? WHERE  PROD_ID = ? AND SALE_ID = ? ";


	
	
	public SaleDetailJDBCDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void insert(SaleDetailVO sdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, sdVO.getProd_ID());
			pstmt.setString(2, sdVO.getSale_ID());
			pstmt.setDouble(3, sdVO.getPromt_price());
			
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
	public void update(SaleDetailVO sdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setDouble(1, sdVO.getPromt_price());
			pstmt.setString(2, sdVO.getProd_ID());
			pstmt.setString(3, sdVO.getSale_ID());
			
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
	public void delete(String prod_ID, String sale_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, prod_ID);
			pstmt.setString(2, sale_ID);
			
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
	public SaleDetailVO findByPrimaryKey(String prod_ID, String sale_ID) {
		SaleDetailVO sdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, prod_ID);
			pstmt.setString(2, sale_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sdVO = new SaleDetailVO();
				sdVO.setProd_ID(rs.getString("PROD_ID"));
				sdVO.setSale_ID(rs.getString("SALE_ID"));
				sdVO.setPromt_price(rs.getDouble("PROMT_PRICE"));
				
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
		return sdVO;
	}

	@Override
	public List<SaleDetailVO> getAll() {
		List<SaleDetailVO> list = new ArrayList<>();
		SaleDetailVO sdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sdVO = new SaleDetailVO();
				sdVO.setProd_ID(rs.getString("PROD_ID"));
				sdVO.setSale_ID(rs.getString("SALE_ID"));
				sdVO.setPromt_price(rs.getDouble("PROMT_PRICE"));
				list.add(sdVO);
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

		SaleDetailJDBCDAO dao = new SaleDetailJDBCDAO();
		// 新增
//		SaleDetailVO stVO1 = new SaleDetailVO();
//		stVO1.setProd_ID("P000002");
//		stVO1.setSale_ID("S000001");
//		stVO1.setPromt_price(new Double(555));
//		dao.insert(stVO1);
		
		//修改
//		SaleDetailVO stVO2 = new SaleDetailVO();
//		stVO2.setProd_ID("P000003");
//		stVO2.setSale_ID("S000001");
//		stVO2.setPromt_price(new Double(123));
//		dao.update(stVO2);
		
		//刪除
//		dao.delete("P000002", "S000001");
//		
//		//查詢1
//		SaleDetailVO stVO3 = dao.findByPrimaryKey("P000003", "S000001");
//		
//		System.out.println(stVO3.getProd_ID() + ",");
//		System.out.println(stVO3.getSale_ID() + ",");
//		System.out.println(stVO3.getPromt_price() + ",");
//		System.out.println("----------------------------");
//		//查詢全
		List<SaleDetailVO> list = dao.getAll();
		
		for(SaleDetailVO stVO4 : list) {
			System.out.println(stVO4.getProd_ID() + ",");
			System.out.println(stVO4.getSale_ID() + ",");
			System.out.println(stVO4.getPromt_price() + ",");
			System.out.println("----------------------------");
		}
	}
	

}
