package com.meetU.meetup_mem.model;

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

public class MeetupMemDAO implements MeetupMemDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO MEETUP_MEM (meetup_ID, mem_ID, mem_showup) VALUES (?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MEETUP_MEM WHERE meetup_ID =?";
	private static final String GET_ONE_STMT = "SELECT * FROM MEETUP_MEM WHERE meetup_ID =? AND MEM_ID=?";
	private static final String GET_MYALL_STMT = "SELECT MEETUP.*,MEETUP_MEM.* FROM MEETUP JOIN MEETUP_MEM ON MEETUP.MEETUP_ID = MEETUP_MEM.MEETUP_ID AND MEETUP.MEETUP_STATUS=1 AND MEETUP_MEM.MEM_ID =?";
	
	private static final String DELETE = "DELETE FROM MEETUP_MEM WHERE meetup_ID =? and MEM_ID =?";
	
	private static final String UPDATE = "UPDATE MEETUP_MEM SET meetup_rate=?, meetup_comment=?, mem_showup=? WHERE meetup_ID =? and MEM_ID =?";
	

	@Override
	public void insert(MeetupMemVO meetupMemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, meetupMemVO.getMeetup_ID());
			pstmt.setString(2, meetupMemVO.getMem_ID());
			pstmt.setInt(3, 1);
			
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
	public void update(MeetupMemVO meetupMemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, meetupMemVO.getMeetup_rate());
			pstmt.setString(2, meetupMemVO.getMeetup_comment());
			pstmt.setInt(3, meetupMemVO.getMem_showup());
			pstmt.setString(4, meetupMemVO.getMeetup_ID());
			pstmt.setString(5, meetupMemVO.getMem_ID());
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
	public MeetupMemVO findByPrimaryKey(String meetup_ID ,String mem_ID) {
		MeetupMemVO meetupMemVO = null;
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
				meetupMemVO = new MeetupMemVO();
				meetupMemVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupMemVO.setMem_ID(rs.getString("mem_ID"));
				meetupMemVO.setMem_showup(rs.getInt("mem_showup"));
				meetupMemVO.setMeetup_rate(rs.getInt("meetup_rate"));
				meetupMemVO.setMeetup_comment(rs.getString("meetup_comment"));
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
		}return meetupMemVO;
	}

	@Override
	public List<MeetupMemVO> getAll(String meetup_ID) {
		List<MeetupMemVO> list = new ArrayList<MeetupMemVO>();
		MeetupMemVO meetupMemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, meetup_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupMemVO = new MeetupMemVO();
				meetupMemVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupMemVO.setMem_ID(rs.getString("mem_ID"));
				meetupMemVO.setMem_showup(rs.getInt("mem_showup"));
				meetupMemVO.setMeetup_rate(rs.getInt("meetup_rate"));
				meetupMemVO.setMeetup_comment(rs.getString("meetup_comment"));
				list.add(meetupMemVO);
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
	public List<MeetupMemVO> findMyAllMeetup(String mem_ID) {
		List<MeetupMemVO> list = new ArrayList<MeetupMemVO>();
		MeetupMemVO meetupMemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MYALL_STMT);
			pstmt.setString(1, mem_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupMemVO = new MeetupMemVO();
				meetupMemVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupMemVO.setMem_ID(rs.getString("mem_ID"));
				meetupMemVO.setMem_showup(rs.getInt("mem_showup"));
				meetupMemVO.setMeetup_rate(rs.getInt("meetup_rate"));
				meetupMemVO.setMeetup_comment(rs.getString("meetup_comment"));
				list.add(meetupMemVO);
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
