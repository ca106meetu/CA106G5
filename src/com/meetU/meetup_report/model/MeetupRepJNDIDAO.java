package com.meetU.meetup_report.model;

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

public class MeetupRepJNDIDAO implements MeetupRepDAO_interface{
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
			"INSERT INTO MEETUP_REP (meetup_rep_ID, meetup_ID, mem_ID, rep_date, rep_status)"
			+ "VALUES ('MPREP'||LPAD(to_char(meetup_rep_seq.NEXTVAL), 6, '0'), ?,?,SYSTIMESTAMP,?)";
	private static final String UPDATE =  "UPDATE MEETUP_REP SET rep_status=?, rep_ans=?, rep_ans_date=SYSTIMESTAMP WHERE meetup_rep_ID=?";
	private static final String GET_ALL_STMT = "SELECT * FROM MEETUP_REP";	
	private static final String GET_ONE_STMT = "SELECT * FROM MEETUP_REP WHERE meetup_rep_ID =?";
	private static final String DELETE = "DELETE FROM MEETUP_REP WHERE MEETUP_REP_ID=?";
	
	@Override
	public void insert(MeetupRepVO meetupRepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, meetupRepVO.getMeetup_ID());
			pstmt.setString(2, meetupRepVO.getMem_ID());
			pstmt.setString(3, meetupRepVO.getRep_content());
//			pstmt.setTimestamp(4, meetupRepVO.getRep_date());
			pstmt.setInt(4, meetupRepVO.getRep_status());
			
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
	public void update(MeetupRepVO meetupRepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, meetupRepVO.getRep_status());
			pstmt.setString(2, meetupRepVO.getRep_ans());
			pstmt.setString(3, meetupRepVO.getMeetup_rep_ID());
			
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
	public void invisible(String meetup_rep_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, meetup_rep_ID);
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
	public MeetupRepVO findByPrimaryKey(String meetup_rep_ID) {
		MeetupRepVO meetupRepVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, meetup_rep_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupRepVO = new MeetupRepVO();
				meetupRepVO.setMeetup_ID(rs.getString("MEETUP_ID"));
				meetupRepVO.setMem_ID(rs.getString("mem_ID"));
				meetupRepVO.setRep_content(rs.getString("rep_content"));
				meetupRepVO.setRep_date(rs.getTimestamp("rep_date"));
				meetupRepVO.setRep_status(rs.getInt("rep_status"));
//				meetupRepVO.setRep_ans(rs.getBytes("rep_ans"));
				meetupRepVO.setRep_ans_date(rs.getTimestamp("rep_ans_date"));
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
		}return meetupRepVO;
	}
	@Override
	public List<MeetupRepVO> getAll() {
		List<MeetupRepVO> list = new ArrayList<MeetupRepVO>();
		MeetupRepVO meetupRepVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupRepVO = new MeetupRepVO();
				meetupRepVO.setMeetup_rep_ID(rs.getString("meetup_rep_ID"));
				meetupRepVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupRepVO.setMem_ID(rs.getString("mem_ID"));
				meetupRepVO.setRep_content(rs.getString("rep_content"));
				meetupRepVO.setRep_date(rs.getTimestamp("rep_date"));
				meetupRepVO.setRep_status(rs.getInt("rep_status"));
				meetupRepVO.setRep_ans(rs.getString("rep_ans"));
				meetupRepVO.setRep_ans_date(rs.getTimestamp("rep_ans_date"));
				list.add(meetupRepVO);
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
