package android.com.meetU.stick.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.meetU.stick.model.StickVO;

public class StickDAO implements StickDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ONE_STMT = "SELECT * FROM STICK where STICK_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM STICK";
	private static final String GET_IMG_STMT = "SELECT STICKER FROM STICK where STICK_ID = ?";

	public StickDAO() {

	}

	@Override
	public StickVO findByPrimaryKey(String stick_ID) {
		StickVO stickVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, stick_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				stickVO = new StickVO();

				stickVO.setStick_ID(rs.getString("STICK_ID"));
				stickVO.setStick_name(rs.getString("STICK_NAME"));
//				stickVO.setSticker(rs.getBytes("STICKER"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return stickVO;
	}

	@Override
	public List<StickVO> getALL() {
		List<StickVO> list = new ArrayList<>();
		StickVO stickVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				stickVO = new StickVO();

				stickVO.setStick_ID(rs.getString("STICK_ID"));
				stickVO.setStick_name(rs.getString("STICK_NAME"));
//				stickVO.setSticker(rs.getBytes("STICKER"));

				list.add(stickVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	public byte[] getImage(String stick_ID) {
		byte[] picture = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_IMG_STMT);
			pstmt.setString(1, stick_ID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				picture = rs.getBytes("STICKER");
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
