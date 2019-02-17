package com.meetU.pointRec.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.meetU.product.model.ProductJDBCDAO;



public class PointRecJDBCDAO implements PointRecDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO POINT_REC (REC_ID, MEM_ID, AMOUNT, REC_DATE) VALUES ('PR'||LPAD(to_char(point_rec_seq.NEXTVAL), 6, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM POINT_REC";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM POINT_REC where REC_ID = ?";
	private static final String DELETE =
			"DELETE FROM POINT_REC WHERE REC_ID = ?";
	private static final String UPDATE = 
		"UPDATE POINT_REC set MEM_ID = ?, AMOUNT = ?, REC_DATE = ? WHERE  REC_ID = ? ";

	public PointRecJDBCDAO() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void insert(PointRecVO prVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, prVO.getMem_ID());
			pstmt.setDouble(2, prVO.getAmount());
			pstmt.setTimestamp(3, prVO.getRec_date());
			
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
	public void update(PointRecVO prVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, prVO.getMem_ID());
			pstmt.setDouble(2, prVO.getAmount());
			pstmt.setTimestamp(3, prVO.getRec_date());
			pstmt.setString(4, prVO.getRec_ID());
			
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
	public void delete(String rec_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, rec_ID);
			
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
	public PointRecVO findByPrimaryKey(String rec_ID) {
		PointRecVO prVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, rec_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				prVO = new PointRecVO();
				prVO.setRec_ID(rs.getString("REC_ID"));
				prVO.setMem_ID(rs.getString("MEM_ID"));
				prVO.setAmount(rs.getDouble("AMOUNT"));
				prVO.setRec_date(rs.getTimestamp("REC_DATE"));
				
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
		return prVO;
	}


	@Override
	public List<PointRecVO> getAll() {
		List<PointRecVO> list = new ArrayList<>();
		PointRecVO prVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				prVO = new PointRecVO();
				prVO.setRec_ID(rs.getString("REC_ID"));
				prVO.setMem_ID(rs.getString("MEM_ID"));
				prVO.setAmount(rs.getDouble("AMOUNT"));
				prVO.setRec_date(rs.getTimestamp("REC_DATE"));
				
				list.add(prVO);
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
		PointRecJDBCDAO dao = new PointRecJDBCDAO();
		// 新增
		Date today = new Date();
//		PointRecVO prVO1 = new PointRecVO();
//		prVO1.setMem_ID("M000005");
//		prVO1.setAmount(new Double(555));
//		prVO1.setRec_date(new Timestamp(today.getTime()));
//
//		dao.insert(prVO1);
		
		//修改
//		PointRecVO prVO2 = new PointRecVO();
//		prVO2.setRec_ID("PR000002");
//		prVO2.setMem_ID("M000002");
//		prVO2.setAmount(new Double(999.11));
//		prVO2.setRec_date(new Timestamp(today.getTime()));
//		dao.update(prVO2);
//		
//		//刪除
//		dao.delete("P000006");
//		
		//查詢1
		PointRecVO prVO3 = dao.findByPrimaryKey("PR000003");
		
		System.out.println(prVO3.getRec_ID() + ",");
		System.out.println(prVO3.getMem_ID() + ",");
		System.out.println(prVO3.getAmount() + ",");
		System.out.println(prVO3.getRec_date() + ",");
		
		System.out.println("----------------------------");
		//查詢全
		List<PointRecVO> list = dao.getAll();
//		
		for(PointRecVO prVO4 : list) {
		System.out.println(prVO4.getRec_ID() + ",");
		System.out.println(prVO4.getMem_ID() + ",");
		System.out.println(prVO4.getAmount() + ",");
		System.out.println(prVO4.getRec_date() + ",");
		
		System.out.println("----------------------------");
		}

	}

}
