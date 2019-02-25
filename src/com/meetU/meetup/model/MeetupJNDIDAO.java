package com.meetU.meetup.model;

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

public class MeetupJNDIDAO implements MeetupDAO_interface{
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
			"INSERT INTO MEETUP (meetup_ID, meetup_name, mem_ID, meetup_date, meetup_loc, meetup_status, meetup_pic, meetup_info)"
			+ "VALUES ('MP'||LPAD(to_char(meetup_seq.NEXTVAL), 6, '0'), ?,?,?,?,?,?,?)";
	
	private static final String GET_ALL_STMT = "SELECT * FROM MEETUP";
	private static final String GET_ONE_STMT = "SELECT * FROM MEETUP where meetup_ID=?";
	private static final String DELETE = "DELETE FROM MEETUP WHERE MEETUP_ID=?";
	private static final String UPDATE = "UPDATE MEETUP SET meetup_name=?, meetup_date=?, meetup_loc=?, meetup_status=?, meetup_pic=?, meetup_info=? where meetup_ID =?";
	
	
	@Override
	public void insert(MeetupVO meetupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, meetupVO.getMeetup_name());
			pstmt.setString(2, meetupVO.getMem_ID());
			pstmt.setDate(3, meetupVO.getMeetup_date());
			pstmt.setString(4, meetupVO.getMeetup_loc());
			pstmt.setInt(5, meetupVO.getMeetup_status());
			pstmt.setBytes(6, meetupVO.getMeetup_pic());
			pstmt.setString(7, meetupVO.getMeetup_info());
			
			pstmt.executeUpdate();
			
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
	}

	@Override
	public void update(MeetupVO meetupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, meetupVO.getMeetup_name());
			pstmt.setDate(2, meetupVO.getMeetup_date());
			pstmt.setString(3, meetupVO.getMeetup_loc());
			pstmt.setInt(4, meetupVO.getMeetup_status());
			pstmt.setBytes(5, meetupVO.getMeetup_pic());
			pstmt.setString(6, meetupVO.getMeetup_info());
			pstmt.setString(7, meetupVO.getMeetup_ID());
			
			pstmt.executeUpdate();			
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
		}
		if(con != null) {
			try {
				con.close();
			}catch(Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

	@Override
	public void delete(String meetup_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, meetup_ID);
			pstmt.executeUpdate();
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
	}

	@Override
	public MeetupVO findByPrimaryKey(String meetup_ID) {
		MeetupVO meetupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, meetup_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupVO = new MeetupVO();
				meetupVO.setMeetup_ID(rs.getString("MEETUP_ID"));
				meetupVO.setMeetup_name(rs.getString("MEETUP_name"));
				meetupVO.setMem_ID(rs.getString("mem_ID"));
				meetupVO.setMeetup_date(rs.getDate("meetup_date"));
				meetupVO.setMeetup_loc(rs.getString("meetup_loc"));
				meetupVO.setMeetup_status(rs.getInt("meetup_status"));
				meetupVO.setMeetup_pic(rs.getBytes("meetup_pic"));
				meetupVO.setMeetup_info(rs.getString("meetup_info"));
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
		}return meetupVO;
	}

	@Override
	public List<MeetupVO> getAll() {
		List<MeetupVO> list = new ArrayList<MeetupVO>();
		MeetupVO meetupVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupVO = new MeetupVO();
				meetupVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupVO.setMeetup_name(rs.getString("meetup_name"));
				meetupVO.setMem_ID(rs.getString("mem_ID"));
				meetupVO.setMeetup_date(rs.getDate("meetup_date"));
				meetupVO.setMeetup_loc(rs.getString("meetup_loc"));
				meetupVO.setMeetup_status(rs.getInt("meetup_status"));
				meetupVO.setMeetup_pic(rs.getBytes("meetup_pic"));
				meetupVO.setMeetup_info(rs.getString("meetup_info"));
				list.add(meetupVO);
			}
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
		}return list;
	}
	
}