package com.meetU.meetup_chat.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meetU.meetup.model.MeetupJDBCDAO;
import com.meetU.meetup.model.MeetupVO;

public class MeetupChatJDBCDAO implements MeetupChatDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO MEETUP_CHAT (chat_ID, meetup_ID, mem_ID, chat_msg_time, chat_msg)"
			+"VALUES ('MPCH'||LPAD(to_char(meetup_chat_seq.NEXTVAL), 6,'0'), ?, ?, SYSTIMESTAMP, ?)";
	private static final String DELETE = "DELETE FROM MEETUP_CHAT WHERE meetup_ID =? and MEM_ID =?";
	private static final String GET_ALL_STMT = "SELECT * FROM MEETUP_CHAT";
	
	@Override
	public void insert(MeetupChatVO meetupChatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, meetupChatVO.getMeetup_ID());
			pstmt.setString(2, meetupChatVO.getMem_ID());
			pstmt.setString(3, meetupChatVO.getChat_msg());
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e){
			throw new RuntimeException("Failed to load database driver."+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, meetup_ID);
			pstmt.setString(2, mem_ID);
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e){
			throw new RuntimeException("Failed to load database driver."+ e.getMessage());	
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
	public List<MeetupChatVO> getAll(String meetup_ID) {
		List<MeetupChatVO> list = new ArrayList<MeetupChatVO>();
		MeetupChatVO mtupChatVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		
	public static void main(String[] args) {
		MeetupChatJDBCDAO dao = new MeetupChatJDBCDAO();

		//新增
		MeetupChatVO meetupChatVO1 = new MeetupChatVO();
		meetupChatVO1.setMeetup_ID("MP000001");
		meetupChatVO1.setMem_ID("M000003");
		meetupChatVO1.setChat_msg("ALOHA");
		dao.insert(meetupChatVO1);
		
//		//delete
//		dao.delete("MP000006", "M000002");
//		
		
		//查詢
//		List<MeetupChatVO> list = dao.getAll("MP000001");
//		for(MeetupChatVO mtupVO : list) {
//			System.out.println(mtupVO.getChat_ID());
//			System.out.println(mtupVO.getMeetup_ID());
//			System.out.println(mtupVO.getMem_ID());
//			System.out.println(mtupVO.getChat_msg_time());
//			System.out.println(mtupVO.getChat_msg());
//			System.out.println("----------------");
//		}
		
		
		
	}
}
