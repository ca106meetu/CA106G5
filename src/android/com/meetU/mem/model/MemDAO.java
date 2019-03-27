package android.com.meetU.mem.model;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.*;
import javax.sql.*;

public class MemDAO implements MemDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
		"INSERT INTO MEM (MEM_ID, MEM_PW, MEM_NAME, MEM_ACC, MEM_NICKNAME, MEM_BDAY, MEM_EMAIL, MEM_PHO, MEM_GEND, "
		+               " MEM_INTRO, MEM_CODE, MEM_STATE, MEM_DATE, MEM_SIGN_DAY, MEM_LOGIN_STATE, MEM_ADDRESS, LAST_PAIR, MEM_HOBBY, MEM_GET_POINT)"
		+        " VALUES ( 'M'||LPAD(to_char(mem_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?,"
		+                 " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String INSERT_STMT = "INSERT INTO MEM (MEM_ID,MEM_PW,MEM_ACC,MEM_EMAIL,MEM_CODE,MEM_STATE,MEM_DATE,MEM_ADDRESS) VALUES('M'||LPAD(to_char(mem_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MEM";
	private static final String GET_ONE_NO_PIC_STMT = "SELECT * FROM MEM WHERE MEM_ACC =?";
	private static final String GET_ONE_BY_ID_STMT = "SELECT * FROM MEM WHERE MEM_ID =?";
	private static final String DELETE = "DELETE FROM MEM where MEM_ID = ?";
	private static final String UPDATE = 
		"UPDATE MEM set MEM_PW=?, MEM_NAME=?, MEM_ACC=?, MEM_NICKNAME=?, MEM_BDAY=?,"
		+             " MEM_EMAIL=?, MEM_PHO=?, MEM_GEND=?, MEM_INTRO=?,"
		+             " MEM_CODE=?, MEM_STATE=?, MEM_DATE=?, MEM_SIGN_DAY=?, MEM_LOGIN_STATE=?,"
		+             " MEM_ADDRESS=?, LAST_PAIR=?, MEM_HOBBY=?, MEM_GET_POINT=?"
		+             " where MEM_ID=?";
	private static final String FIND_BY_ID_PASWD = "SELECT * FROM MEM WHERE MEM_ACC=? AND MEM_PW=?";
	private static final String CHECK_ACC_EXIST = "SELECT MEM_ACC FROM MEM WHERE MEM_ACC = ?";
	private static final String FIND_IMG_BY_ACC = "SELECT MEM_PIC FROM MEM WHERE MEM_ACC = ?";
	private static final String FIND_QRCODE_BY_ID = "SELECT MEM_QRCODE FROM MEM WHERE MEM_ID = ?";
	
	@Override
	public boolean insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isAdded = false;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
//			pstmt.setString(1, memVO.getMem_pw());
//			pstmt.setString(2, memVO.getMem_acc());
//			pstmt.setString(3, memVO.getMem_email());
//			pstmt.setInt(4,123456);//驗證碼
//			pstmt.setInt(5,0);//會員狀態碼
//			pstmt.setDate(6, memVO.getMem_date());//會員註冊日期
//			pstmt.setString(7, memVO.getMem_address());
			pstmt.setString(1, memVO.getMem_pw());
			pstmt.setString(2, "未設定");
			pstmt.setString(3, memVO.getMem_acc());
			pstmt.setString(4, "未設定");
			pstmt.setDate(5, null);
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, "未設定");
			pstmt.setString(8, "未設定");
//			pstmt.setBytes(9, memVO.getMem_pic());
			pstmt.setString(9, "未設定");
			
			pstmt.setInt(10, 12345);
			pstmt.setInt(11, 0);
			pstmt.setDate(12, memVO.getMem_date());
			pstmt.setTimestamp(13, null);
			pstmt.setInt(14, 0);
			pstmt.setString(15, memVO.getMem_address());
			pstmt.setTimestamp(16, null);
			pstmt.setString(17, "未設定");
//			pstmt.setBytes(19, memVO.getMem_QRCODE());
			pstmt.setInt(18, 0);
						
			int count = pstmt.executeUpdate();
			isAdded = count > 0 ? true : false;
			
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
		return isAdded;
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
			pstmt.setDate(5, memVO.getMem_bday());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_pho());
			pstmt.setString(8, memVO.getMem_gend());
//			pstmt.setBytes(9, memVO.getMem_pic());
			pstmt.setString(10, memVO.getMem_intro());
			
			pstmt.setInt(11, memVO.getMem_code());
			pstmt.setInt(12, memVO.getMem_state());
			pstmt.setDate(13, memVO.getMem_date());
			pstmt.setTimestamp(14, memVO.getMem_sign_day());
			pstmt.setInt(15, memVO.getMem_login_state());
			pstmt.setString(16, memVO.getMem_address());
			pstmt.setTimestamp(17, memVO.getLast_pair());
			pstmt.setString(18, memVO.getMem_hobby());
//			pstmt.setBytes(19, memVO.getMem_QRCODE());
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
	public void delete(String mem_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_ID);

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
	public MemVO findByAccount(String mem_acc) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_NO_PIC_STMT);

			pstmt.setString(1, mem_acc);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMem_ID(rs.getString("mem_ID"));
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_nickname(rs.getString("mem_nickname"));
				memVO.setMem_bday(rs.getDate("mem_bday"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_pho(rs.getString("mem_pho"));
				memVO.setMem_gend(rs.getString("mem_gend"));
				memVO.setMem_intro(rs.getString("mem_intro"));
				memVO.setMem_code(rs.getInt("mem_code"));
				memVO.setMem_state(rs.getInt("mem_state"));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date date = rs.getDate("mem_date");
				String temp = sdf.format(date.getTime());
				java.sql.Date mem_date = java.sql.Date.valueOf(temp);
				memVO.setMem_date(mem_date);
//				memVO.setMem_date(rs.getDate("mem_date"));
				memVO.setMem_sign_day(rs.getTimestamp("mem_sign_day"));
				memVO.setMem_login_state(rs.getInt("mem_login_state"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setLast_pair(rs.getTimestamp("last_pair"));
				memVO.setMem_hobby(rs.getString("mem_hobby"));
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
	public MemVO findByID(String mem_ID) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_ID_STMT);

			pstmt.setString(1, mem_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMem_ID(rs.getString("mem_ID"));
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_nickname(rs.getString("mem_nickname"));
				memVO.setMem_bday(rs.getDate("mem_bday"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_pho(rs.getString("mem_pho"));
				memVO.setMem_gend(rs.getString("mem_gend"));
				memVO.setMem_intro(rs.getString("mem_intro"));
				memVO.setMem_code(rs.getInt("mem_code"));
				memVO.setMem_state(rs.getInt("mem_state"));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date date = rs.getDate("mem_date");
				String temp = sdf.format(date.getTime());
				java.sql.Date mem_date = java.sql.Date.valueOf(temp);
				memVO.setMem_date(mem_date);
//				memVO.setMem_date(rs.getDate("mem_date"));
				memVO.setMem_sign_day(rs.getTimestamp("mem_sign_day"));
				memVO.setMem_login_state(rs.getInt("mem_login_state"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setLast_pair(rs.getTimestamp("last_pair"));
				memVO.setMem_hobby(rs.getString("mem_hobby"));
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
				memVO.setMem_ID(rs.getString("mem_ID"));
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_nickname(rs.getString("mem_nickname"));
				memVO.setMem_bday(rs.getDate("mem_bday"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_pho(rs.getString("mem_pho"));
				memVO.setMem_gend(rs.getString("mem_gend"));
//				memVO.setMem_pic(rs.getBytes("mem_pic"));
				memVO.setMem_intro(rs.getString("mem_intro"));
				memVO.setMem_code(rs.getInt("mem_code"));
				memVO.setMem_state(rs.getInt("mem_state"));
				memVO.setMem_date(rs.getDate("mem_date"));
				memVO.setMem_sign_day(rs.getTimestamp("mem_sign_day"));
				memVO.setMem_login_state(rs.getInt("mem_login_state"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setLast_pair(rs.getTimestamp("last_pair"));
				memVO.setMem_hobby(rs.getString("mem_hobby"));
//				memVO.setMem_QRCODE(rs.getBytes("mem_QRCODE"));
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

	@Override
	public boolean isMem(String mem_acc, String mem_pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean isMem = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_ID_PASWD);
			pstmt.setString(1, mem_acc);
			pstmt.setString(2, mem_pw);
			ResultSet rs = pstmt.executeQuery();
			isMem = rs.next();
			return isMem;
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return isMem;
	}
	
	public boolean isAccountExist(String mem_acc) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean isAccountExist = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(CHECK_ACC_EXIST);
			
			pstmt.setString(1, mem_acc);
			ResultSet rs = pstmt.executeQuery();
			isAccountExist = rs.next();
			return isAccountExist;
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return isAccountExist;
	}
	
	public byte[] getImage(String mem_acc) {
		byte[] picture = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_IMG_BY_ACC);
			pstmt.setString(1, mem_acc);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				picture = rs.getBytes("mem_pic");
			}
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return picture;
	}
	
	public byte[] getQRcode(String mem_ID) {
		byte[] picture = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_QRCODE_BY_ID);
			pstmt.setString(1, mem_ID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				picture = rs.getBytes("mem_qrcode");
			}
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return picture;
	}
	
}
