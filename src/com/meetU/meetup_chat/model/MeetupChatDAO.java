package com.meetU.meetup_chat.model;

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

public class MeetupChatDAO implements MeetupChatDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO MEETUP_CHAT (chat_ID, meetup_ID, mem_ID, chat_msg_time, chat_msg)"
			+"VALUES ('MPCH'||LPAD(to_char(meetup_chat_seq.NEXTVAL), 6,'0'), ?, ?, SYSTIMESTAMP, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MEETUP_CHAT";
	private static final String DELETE = "DELETE FROM MEETUP_CHAT WHERE meetup_ID =? and MEM_ID =?";
	
	@Override
	public void insert(MeetupChatVO meetupChatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, meetupChatVO.getMeetup_ID());
			pstmt.setString(2, meetupChatVO.getMem_ID());
			pstmt.setString(3, meetupChatVO.getChat_msg());
			
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
	public List<MeetupChatVO> getAll(String meetup_ID) {
		List<MeetupChatVO> list = new ArrayList<MeetupChatVO>();
		MeetupChatVO mtupChatVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mtupChatVO = new MeetupChatVO();
				mtupChatVO.setChat_ID(rs.getString("chat_ID"));
				mtupChatVO.setMeetup_ID(rs.getString("Meetup_ID"));
				mtupChatVO.setMem_ID(rs.getString("mem_ID"));
				mtupChatVO.setChat_msg_time(rs.getTimestamp("chat_msg_time"));
				mtupChatVO.setChat_msg(rs.getString("chat_msg"));
				list.add(mtupChatVO);
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
}
