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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.meetU.emp.model.EmpVO;
import com.meetU.product.model.ProductJDBCDAO;



public class PointRecDAO implements PointRecDAO_interface {
	
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_PR_BY_MEM= 
		"SELECT * FROM POINT_REC where MEM_ID = ?";
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

	public PointRecDAO() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void insert(PointRecVO prVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, prVO.getMem_ID());
			pstmt.setDouble(2, prVO.getAmount());
			pstmt.setTimestamp(3, prVO.getRec_date());
			
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

	public void insert(PointRecVO prVO, EmpVO empVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, prVO.getMem_ID());
			pstmt.setDouble(2, prVO.getAmount());
			pstmt.setTimestamp(3, prVO.getRec_date());
			
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
	public void update(PointRecVO prVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, prVO.getMem_ID());
			pstmt.setDouble(2, prVO.getAmount());
			pstmt.setTimestamp(3, prVO.getRec_date());
			pstmt.setString(4, prVO.getRec_ID());
			
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
	public void delete(String rec_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, rec_ID);
			
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
	public PointRecVO findByPrimaryKey(String rec_ID) {
		PointRecVO prVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
	
			con = ds.getConnection();
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
	
			con = ds.getConnection();
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


	@Override
	public List<PointRecVO> getPrByMem(String mem_ID) {
		List<PointRecVO> list = new ArrayList<>();
		PointRecVO prVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_PR_BY_MEM);
			pstmt.setString(1, mem_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				prVO = new PointRecVO();
				prVO.setRec_ID(rs.getString("REC_ID"));
				prVO.setMem_ID(rs.getString("MEM_ID"));
				prVO.setAmount(rs.getDouble("AMOUNT"));
				prVO.setRec_date(rs.getTimestamp("REC_DATE"));
				
				list.add(prVO);
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
