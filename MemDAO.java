package com.meetU.mem.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;

public class MemDAO implements MemDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/meetUDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO MEM (MEM_ID, MEM_PW, MEM_NAME, MEM_ACC, MEM_NICKNAME, MEM_BDAY, MEM_EMAIL, MEM_PHO, MEM_GEND, MEM_PIC,"
				+               " MEM_INTRO, MEM_CODE, MEM_STATE, MEM_DATE, MEM_SIGN_DAY, MEM_LOGIN_STATE, MEM_ADDRESS, LAST_PAIR, MEM_HOBBY, MEM_QRCODE,MEM_GET_POINT)"
				+        " VALUES ( 'M'||LPAD(to_char(mem_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+                 " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM MEM";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM MEM where MEM_ID = ?";
	private static final String DELETE = 
		"DELETE FROM MEM where MEM_ID = ?";
	private static final String UPDATE = 
		"UPDATE MEM set MEM_PW=?, MEM_NAME=?, MEM_ACC=?, MEM_NICKNAME=?, MEM_BDAY=?, MEM_EMAIL=?, MEM_PHO=?, MEM_GEND=?, MEM_PIC=?,"
		+              "MEM_INTRO=?, MEM_CODE=?, MEM_STATE=?, MEM_DATE=?, MEM_SIGN_DAY=?, MEM_LOGIN_STATE=?, MEM_ADDRESS=?, LAST_PAIR=?, MEM_HOBBY=?, MEM_QRCODE=?,MEM_GET_POINT=?"
		+              " where MEM_ID=?";
	
	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memVO.getMem_pw());
			pstmt.setString(2, memVO.getMem_name());
			pstmt.setString(3, memVO.getMem_acc());
			pstmt.setString(4, memVO.getMem_nickname());
			pstmt.setDate(5, memVO.getMem_Bday());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_pho());
			pstmt.setString(8, memVO.getMem_gend());
			pstmt.setBytes(9, memVO.getMem_PIC());
			pstmt.setString(10, memVO.getMem_intro());
			
			pstmt.setInt(11, memVO.getMem_code());
			pstmt.setInt(12, memVO.getMem_state());
			pstmt.setDate(13, memVO.getMem_date());
			pstmt.setTimestamp(14, memVO.getMem_sign_day());
			pstmt.setInt(15, memVO.getMem_login_state());
			pstmt.setString(16, memVO.getMem_address());
			pstmt.setTimestamp(17, memVO.getLast_pair());
			pstmt.setString(18, memVO.getMem_hobby());
			pstmt.setBytes(19, memVO.getMem_QRCODE());
			pstmt.setInt(20, memVO.getMem_get_point());
			
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
			
			pstmt.setString(1, memVO.getMem_pw());
			pstmt.setString(2, memVO.getMem_name());
			pstmt.setString(3, memVO.getMem_acc());
			pstmt.setString(4, memVO.getMem_nickname());
			pstmt.setDate(5, memVO.getMem_Bday());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_pho());
			pstmt.setString(8, memVO.getMem_gend());
			pstmt.setBytes(9, memVO.getMem_PIC());
			pstmt.setString(10, memVO.getMem_intro());
			
			pstmt.setInt(11, memVO.getMem_code());
			pstmt.setInt(12, memVO.getMem_state());
			pstmt.setDate(13, memVO.getMem_date());
			pstmt.setTimestamp(14, memVO.getMem_sign_day());
			pstmt.setInt(15, memVO.getMem_login_state());
			pstmt.setString(16, memVO.getMem_address());
			pstmt.setTimestamp(17, memVO.getLast_pair());
			pstmt.setString(18, memVO.getMem_hobby());
			pstmt.setBytes(19, memVO.getMem_QRCODE());
			pstmt.setInt(20, memVO.getMem_get_point());
			pstmt.setString(21, memVO.getMem_ID());
			
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
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_nickname(rs.getString("mem_nickname"));
				memVO.setMem_Bday(rs.getDate("mem_Bday"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_pho(rs.getString("mem_pho"));
				memVO.setMem_gend(rs.getString("mem_gend"));
				memVO.setMem_PIC(rs.getBytes("mem_PIC"));
				memVO.setMem_intro(rs.getString("mem_intro"));
				memVO.setMem_code(rs.getInt("mem_code"));
				memVO.setMem_state(rs.getInt("mem_state"));
				memVO.setMem_date(rs.getDate("mem_date"));
				memVO.setMem_sign_day(rs.getTimestamp("mem_sign_day"));
				memVO.setMem_login_state(rs.getInt("mem_login_state"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setLast_pair(rs.getTimestamp("last_pair"));
				memVO.setMem_hobby(rs.getString("mem_hobby"));
				memVO.setMem_QRCODE(rs.getBytes("mem_QRCODE"));
				memVO.setMem_get_point(rs.getInt("mem_get_point"));
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
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_nickname(rs.getString("mem_nickname"));
				memVO.setMem_Bday(rs.getDate("mem_Bday"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_pho(rs.getString("mem_pho"));
				memVO.setMem_gend(rs.getString("mem_gend"));
				memVO.setMem_PIC(rs.getBytes("mem_PIC"));
				memVO.setMem_intro(rs.getString("mem_intro"));
				memVO.setMem_code(rs.getInt("mem_code"));
				memVO.setMem_state(rs.getInt("mem_state"));
				memVO.setMem_date(rs.getDate("mem_date"));
				memVO.setMem_sign_day(rs.getTimestamp("mem_sign_day"));
				memVO.setMem_login_state(rs.getInt("mem_login_state"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setLast_pair(rs.getTimestamp("last_pair"));
				memVO.setMem_hobby(rs.getString("mem_hobby"));
				memVO.setMem_QRCODE(rs.getBytes("mem_QRCODE"));
				memVO.setMem_get_point(rs.getInt("mem_get_point"));
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