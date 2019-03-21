package com.meetU.meetup_like.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MeetupLikeJNDIDAO implements MeetupLikeDAO_interface{
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
	private static final String GET_ALL_STMT = 
			"SELECT MEETUP.*,MEETUP_LIKE.MEM_ID FROM MEETUP JOIN MEETUP_LIKE ON MEETUP.MEETUP_ID = MEETUP_LIKE.MEETUP_ID AND MEETUP.MEETUP_STATUS=1 AND MEETUP_LIKE.MEM_ID =?";
	
	private static final String LIKE_BY_WHO = "SELECT * FROM MEETUP_LIKE WHERE meetup_ID =? ";
	private static final String GET_ONE_STMT = "SELECT * FROM MEETUP_LIKE WHERE meetup_ID =? and MEM_ID =?";
	private static final String DELETE = "DELETE FROM MEETUP_LIKE WHERE meetup_ID =? and MEM_ID =?";
	
	@Override
	public void insert(MeetupLikeVO meetupLikeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, meetupLikeVO.getMeetup_ID());
			pstmt.setString(2, meetupLikeVO.getMem_ID());
			
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
	public void delete(String meetup_ID, String mem_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, meetup_ID);
			pstmt.setString(2, mem_ID);
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

	@Override
	public Set<MeetupLikeVO> LikeByWho(String meetup_ID) {
		Set<MeetupLikeVO> hset = new HashSet<MeetupLikeVO>();
		MeetupLikeVO meetupLikeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LIKE_BY_WHO);
			pstmt.setString(1, meetup_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupLikeVO = new MeetupLikeVO();
				meetupLikeVO.setMem_ID(rs.getString("mem_ID"));
				hset.add(meetupLikeVO);
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
		}return hset;
	}
}
