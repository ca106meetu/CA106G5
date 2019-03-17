package com.meetU.meetup_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meetU.meetup.model.MeetupJDBCDAO;
import com.meetU.meetup.model.MeetupVO;

public class MeetupRepJDBCDAO implements MeetupRepDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO MEETUP_REP (meetup_rep_ID, meetup_ID, mem_ID, Rep_content, rep_date, rep_status)"
			+ "VALUES ('MPREP'||LPAD(to_char(meetup_rep_seq.NEXTVAL), 6, '0'), ?,?,?,SYSTIMESTAMP,?)";
	private static final String UPDATE =  "UPDATE MEETUP_REP SET rep_status=?, rep_ans=?, rep_ans_date=SYSTIMESTAMP WHERE meetup_rep_ID=?";
	private static final String GET_ALL_STMT = "SELECT * FROM MEETUP_REP order by rep_date desc";	
	private static final String GET_ONE_STMT = "SELECT * FROM MEETUP_REP WHERE meetup_rep_ID =?";
	private static final String DELETE = "DELETE FROM MEETUP_REP WHERE MEETUP_REP_ID=?";
		
	@Override
	public void insert(MeetupRepVO meetupRepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, meetupRepVO.getMeetup_ID());
			pstmt.setString(2, meetupRepVO.getMem_ID());
			pstmt.setString(3, meetupRepVO.getRep_content());
			pstmt.setInt(4, meetupRepVO.getRep_status());
			
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

	public void update(MeetupRepVO meetupRepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, meetupRepVO.getRep_status());
			pstmt.setString(2, meetupRepVO.getRep_ans());
			pstmt.setString(3, meetupRepVO.getMeetup_rep_ID());
			
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
	public void invisible(String meetup_rep_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, meetup_rep_ID);
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
	public MeetupRepVO findByPrimaryKey(String meetup_rep_ID) {
		MeetupRepVO meetupRepVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				meetupRepVO.setRep_ans(rs.getString("rep_ans"));
				meetupRepVO.setRep_ans_date(rs.getTimestamp("rep_ans_date"));
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		MeetupRepJDBCDAO dao = new MeetupRepJDBCDAO();
		
		//新增
//		MeetupRepVO meetupRepVO1 = new MeetupRepVO();
//		meetupRepVO1.setMeetup_ID("MP000003");
//		meetupRepVO1.setMem_ID("M000001");
//		meetupRepVO1.setRep_content("lalalala");
//		meetupRepVO1.setRep_status(0);
//		dao.insert(meetupRepVO1);
		
		//更新
//		MeetupRepVO meetupRepVO2 = new MeetupRepVO();
//		meetupRepVO2.setRep_status(1);
//		meetupRepVO2.setRep_ans("okokokok");
//		meetupRepVO2.setMeetup_rep_ID("MPREP000005");
//		dao.update(meetupRepVO2);
		
		
		//delete
//		dao.invisible("MPREP000001");
		
		//查詢
//		MeetupRepVO meetupRepVO3 = dao.findByPrimaryKey("MPREP000015");
//		System.out.println(meetupRepVO3.getMeetup_rep_ID());
//		System.out.println(meetupRepVO3.getMeetup_ID());
//		System.out.println(meetupRepVO3.getMem_ID());
//		System.out.println(meetupRepVO3.getRep_content());
//		System.out.println(meetupRepVO3.getRep_date());
//		System.out.println(meetupRepVO3.getRep_status());
//		System.out.println(meetupRepVO3.getRep_ans());
//		System.out.println(meetupRepVO3.getRep_ans_date());
//		System.out.println("----------------");
		
		//查詢
		List<MeetupRepVO> list = dao.getAll();
		for(MeetupRepVO mtupVO : list) {
			System.out.println(mtupVO.getMeetup_rep_ID());
			System.out.println(mtupVO.getMeetup_ID());
			System.out.println(mtupVO.getMem_ID());
			System.out.println(mtupVO.getRep_content());
			System.out.println(mtupVO.getRep_date());
			System.out.println(mtupVO.getRep_status());
			System.out.println(mtupVO.getRep_ans());
			System.out.println(mtupVO.getRep_ans_date());
			System.out.println("----------------");	
		}
	}
}