package android.com.meetU.meetup_like.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MeetupLikeDAO implements MeetupLikeDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO MEETUP_LIKE (meetup_ID, mem_ID)"+ "VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MEETUP_LIKE WHERE MEM_ID =?";
	private static final String GET_ONE_STMT = "SELECT * FROM MEETUP_LIKE WHERE meetup_ID =? and MEM_ID =?";
	private static final String DELETE = "DELETE FROM MEETUP_LIKE WHERE meetup_ID =? and MEM_ID =?";
	
	@Override
	public boolean insert(MeetupLikeVO meetupLikeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isAdded = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, meetupLikeVO.getMeetup_ID());
			pstmt.setString(2, meetupLikeVO.getMem_ID());
			
			int count = pstmt.executeUpdate();
			isAdded = count > 0 ? true : false;
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+ se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return isAdded;
	}
	
	@Override
	public boolean delete(String meetup_ID, String mem_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isDeleted = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, meetup_ID);
			pstmt.setString(2, mem_ID);
			int count = pstmt.executeUpdate();
			isDeleted =  count > 0 ? true : false;
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return isDeleted;
	}
	
	@Override
	public MeetupLikeVO findByPrimaryKey(String meetup_ID, String mem_ID) {
		MeetupLikeVO meetupLikeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, meetup_ID);
			pstmt.setString(2, mem_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupLikeVO = new MeetupLikeVO();
				meetupLikeVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupLikeVO.setMem_ID(rs.getString("mem_ID"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "	+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}return meetupLikeVO;
	}
	@Override
	public List<MeetupLikeVO> getAll(String mem_ID) {
		List<MeetupLikeVO> list = new ArrayList<MeetupLikeVO>();
		MeetupLikeVO meetupLikeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, mem_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupLikeVO = new MeetupLikeVO();
				meetupLikeVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupLikeVO.setMem_ID(rs.getString("mem_ID"));
				
				list.add(meetupLikeVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "	+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}return list;
	}
}
