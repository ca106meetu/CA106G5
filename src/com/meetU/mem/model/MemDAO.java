package com.meetU.mem.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemDAO implements MemDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO mem (mem_id,mem_acc,mem_pw,mem_email,mem_code,mem_state,mem_date,mem_address) VALUES "
		              + "('M'||LPAD(to_char(mem_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mem_id,mem_pw,mem_acc,mem_email,mem_code,to_char(mem_date,'yyyy-mm-dd') mem_date,mem_address FROM mem order by empno";
	private static final String GET_ONE_STMT = 
		"SELECT mem_id,mem_pw,mem_acc,mem_email,mem_code,to_char(mem_date,'yyyy-mm-dd') mem_date,mem_address FROM mem where mem_id = ?";
	private static final String DELETE = 
		"DELETE FROM mem where mem_id = ?";
	private static final String UPDATE = 
		"UPDATE mem set mem_acc=?, mem_pw=?, mem_email=?, mem_code=?, mem_state=?, mem_date=?, mem_address=? where mem_id = ?";
	
	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memVO.getMem_acc());
			pstmt.setString(2, memVO.getMem_pw());
			pstmt.setString(3, memVO.getMem_email());
			pstmt.setInt(4, memVO.getMem_code());
			pstmt.setInt(5, memVO.getMem_state());
			pstmt.setDate(6, memVO.getMem_date());
			pstmt.setString(7, memVO.getMem_address());
			
			pstmt.executeUpdate();
			
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memVO.getMem_acc());
			pstmt.setString(2, memVO.getMem_pw());
			pstmt.setString(3, memVO.getMem_email());
			pstmt.setInt(4, memVO.getMem_code());
			pstmt.setInt(5, memVO.getMem_state());
			pstmt.setDate(6, memVO.getMem_date());
			pstmt.setString(7, memVO.getMem_address());
			
			pstmt.setString(8, memVO.getMem_ID());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public MemVO findByPrimaryKey(String mem_id) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMem_ID(rs.getString("mem_id"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_code(rs.getInt("mem_code"));
				memVO.setMem_state(rs.getInt("mem_state"));
				memVO.setMem_date(rs.getDate("mem_date"));
				memVO.setMem_address(rs.getString("mem_address"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMem_ID(rs.getString("mem_id"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_code(rs.getInt("mem_code"));
				memVO.setMem_state(rs.getInt("mem_state"));
				memVO.setMem_date(rs.getDate("mem_date"));
				memVO.setMem_address(rs.getString("mem_address"));
				list.add(memVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
