package com.meetU.meetup_like.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meetU.meetup_mem.model.MeetupMemJDBCDAO;
import com.meetU.meetup_mem.model.MeetupMemVO;

public class MeetupLikeJDBCDAO implements MeetupLikeDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO MEETUP_LIKE (meetup_ID, mem_ID)"+ "VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MEETUP_LIKE WHERE MEM_ID =?";
//	private static final String LIKE_BY_WHO = "SELECT * FROM MEETUP_LIKE WHERE MEET_ID =?";
	private static final String GET_ONE_STMT = "SELECT * FROM MEETUP_LIKE WHERE meetup_ID =? and MEM_ID =?";
	private static final String DELETE = "DELETE FROM MEETUP_LIKE WHERE meetup_ID =? and MEM_ID =?";
	
	@Override
	public void insert(MeetupLikeVO meetupLikeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, meetupLikeVO.getMeetup_ID());
			pstmt.setString(2, meetupLikeVO.getMem_ID());
			
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
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Failed to load database driver." + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, meetup_ID);
			pstmt.setString(2, mem_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupLikeVO = new MeetupLikeVO();
				meetupLikeVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupLikeVO.setMem_ID(rs.getString("mem_ID"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, mem_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupLikeVO = new MeetupLikeVO();
				meetupLikeVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupLikeVO.setMem_ID(rs.getString("mem_ID"));
				
				list.add(meetupLikeVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
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
	
	public static void main(String[] args) {
		MeetupLikeJDBCDAO dao = new MeetupLikeJDBCDAO();
		
		//新增
//		MeetupLikeVO meetupLikeVO1 = new MeetupLikeVO();
//		meetupLikeVO1.setMeetup_ID("MP000006");
//		meetupLikeVO1.setMem_ID("M000002");
//		dao.insert(meetupLikeVO1);
		
//		//delete
//		dao.delete("MP000006", "M000002");
//		
//		//查詢
//		MeetupLikeVO meetupLikeVO3 = dao.findByPrimaryKey("MP000006" ,"M000002");
//		System.out.println(meetupLikeVO3.getMeetup_ID());
//		System.out.println(meetupLikeVO3.getMem_ID());
//		System.out.println("----------------");
		
		//查詢
//		List<MeetupLikeVO> list = dao.getAll("M000002");
//		for(MeetupLikeVO mtupVO : list) {
//			System.out.println(mtupVO.getMeetup_ID());
//			System.out.println(mtupVO.getMem_ID());
//			System.out.println("----------------");
//		}

	}

}
