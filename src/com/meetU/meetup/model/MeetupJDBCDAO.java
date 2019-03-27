package com.meetU.meetup.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MeetupJDBCDAO implements MeetupDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO MEETUP (meetup_ID, meetup_name, mem_ID, meetup_date, meetup_loc, meetup_status, meetup_info, MEETUP_MINPPL, MEETUP_MAXPPL, meetup_joindate)"
			+ "VALUES ('MP'||LPAD(to_char(meetup_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?,?,?,?)";
	private static final String GET_HOST_ALL_STMT = "SELECT * FROM MEETUP WHERE MEETUP_STATUS=1 and mem_ID=? order by meetup_ID desc";//
	private static final String GET_ALL_STMT = "SELECT * FROM MEETUP order by meetup_ID desc";
	private static final String GET_VISIBLE_ALL_STMT = "SELECT * FROM MEETUP WHERE MEETUP_STATUS=1 order by meetup_ID desc";//
	
	private static final String GET_ONE_STMT = "SELECT * FROM MEETUP where meetup_ID=?";
	private static final String DELETE = "DELETE FROM MEETUP WHERE MEETUP_ID=?";
	private static final String UPDATE = "UPDATE MEETUP SET meetup_name=?, meetup_date=?, meetup_loc=?, meetup_status=?, meetup_info=?, MEETUP_MINPPL=?, MEETUP_MAXPPL=?, meetup_joindate=? where meetup_ID =?";
	private static final String INVISIBLE = "UPDATE MEETUP SET meetup_status=0 where meetup_ID =?";
	private static final String GET_LOCATION_STMT1 = "SELECT * FROM MEETUP where MEETUP_STATUS=1 and meetup_loc like '%";//
	private static final String GET_STMT2= "%' order by meetup_ID desc";
	private static final String GET_NAME_STMT1 = "SELECT * FROM MEETUP where MEETUP_STATUS=1 and meetup_name like '%";//

	private static final String INSERT_FOUNDER_STMT = "INSERT INTO MEETUP_MEM (meetup_ID, mem_ID, mem_showup) VALUES (?,?,?)";
	private static final String HOMEPAGE = "SELECT * FROM MEETUP WHERE ROWNUM <=3 order by meetup_ID desc";//for HomePG
	@Override
	public void insert(MeetupVO meetupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, meetupVO.getMeetup_name());
			pstmt.setString(2, meetupVO.getMem_ID());
			pstmt.setTimestamp(3, meetupVO.getMeetup_date());
			pstmt.setString(4, meetupVO.getMeetup_loc());
			pstmt.setInt(5, meetupVO.getMeetup_status());
//			pstmt.setBytes(6, meetupVO.getMeetup_pic());
			pstmt.setString(6, meetupVO.getMeetup_info());
			pstmt.setInt(7, meetupVO.getMeetup_minppl());
			pstmt.setInt(8, meetupVO.getMeetup_maxppl());
			pstmt.setDate(9, meetupVO.getMeetup_joindate());
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
	public void update(MeetupVO meetupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, meetupVO.getMeetup_name());
			pstmt.setTimestamp(2, meetupVO.getMeetup_date());
			pstmt.setString(3, meetupVO.getMeetup_loc());
			pstmt.setInt(4, meetupVO.getMeetup_status());
//			pstmt.setBytes(5, meetupVO.getMeetup_pic());
			pstmt.setString(5, meetupVO.getMeetup_info());
			pstmt.setInt(6, meetupVO.getMeetup_minppl());
			pstmt.setInt(7, meetupVO.getMeetup_maxppl());
			pstmt.setString(8, meetupVO.getMeetup_ID());
			pstmt.setDate(9, meetupVO.getMeetup_joindate());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, meetup_ID);
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
	public MeetupVO findByPrimaryKey(String meetup_ID) {
		MeetupVO meetupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, meetup_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupVO = new MeetupVO();
				meetupVO.setMeetup_ID(rs.getString("MEETUP_ID"));
				meetupVO.setMeetup_name(rs.getString("MEETUP_name"));
				meetupVO.setMem_ID(rs.getString("mem_ID"));
				meetupVO.setMeetup_date(rs.getTimestamp("meetup_date"));
				meetupVO.setMeetup_loc(rs.getString("meetup_loc"));
				meetupVO.setMeetup_status(rs.getInt("meetup_status"));
//				meetupVO.setMeetup_pic(rs.getBytes("meetup_pic"));
				meetupVO.setMeetup_info(rs.getString("meetup_info"));
				meetupVO.setMeetup_minppl(rs.getInt("meetup_minppl"));
				meetupVO.setMeetup_maxppl(rs.getInt("meetup_maxppl"));
				meetupVO.setMeetup_joindate(rs.getDate("meetup_joindate"));
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupVO = new MeetupVO();
				meetupVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupVO.setMeetup_name(rs.getString("meetup_name"));
				meetupVO.setMem_ID(rs.getString("mem_ID"));
				meetupVO.setMeetup_date(rs.getTimestamp("meetup_date"));
				meetupVO.setMeetup_loc(rs.getString("meetup_loc"));
				meetupVO.setMeetup_status(rs.getInt("meetup_status"));
//				meetupVO.setMeetup_pic(rs.getBytes("meetup_pic"));
				meetupVO.setMeetup_info(rs.getString("meetup_info"));
				meetupVO.setMeetup_minppl(rs.getInt("meetup_minppl"));
				meetupVO.setMeetup_maxppl(rs.getInt("meetup_maxppl"));
				meetupVO.setMeetup_joindate(rs.getDate("meetup_joindate"));
				list.add(meetupVO);
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
	
	@Override
	public List<MeetupVO> getHostAll(String mem_ID) {
	
		List<MeetupVO> list = new ArrayList<MeetupVO>();
		MeetupVO meetupVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_HOST_ALL_STMT);
			pstmt.setString(1, mem_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupVO = new MeetupVO();
				meetupVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupVO.setMeetup_name(rs.getString("meetup_name"));
				meetupVO.setMem_ID(rs.getString("mem_ID"));
				meetupVO.setMeetup_date(rs.getTimestamp("meetup_date"));
				meetupVO.setMeetup_loc(rs.getString("meetup_loc"));
				meetupVO.setMeetup_status(rs.getInt("meetup_status"));
//				meetupVO.setMeetup_pic(rs.getBytes("meetup_pic"));
				meetupVO.setMeetup_info(rs.getString("meetup_info"));
				meetupVO.setMeetup_minppl(rs.getInt("meetup_minppl"));
				meetupVO.setMeetup_maxppl(rs.getInt("meetup_maxppl"));
				meetupVO.setMeetup_joindate(rs.getDate("meetup_joindate"));
				list.add(meetupVO);
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
	
	public List<MeetupVO> getSearchLoc(String location) {
		List<MeetupVO> list = new ArrayList<MeetupVO>();
		MeetupVO meetupVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_LOCATION_STMT1+location+GET_STMT2);
//			pstmt.setString(1, location);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupVO = new MeetupVO();
				meetupVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupVO.setMeetup_name(rs.getString("meetup_name"));
				meetupVO.setMem_ID(rs.getString("mem_ID"));
				meetupVO.setMeetup_date(rs.getTimestamp("meetup_date"));
				meetupVO.setMeetup_loc(rs.getString("meetup_loc"));
				meetupVO.setMeetup_status(rs.getInt("meetup_status"));
//				meetupVO.setMeetup_pic(rs.getBytes("meetup_pic"));
				meetupVO.setMeetup_info(rs.getString("meetup_info"));
				meetupVO.setMeetup_minppl(rs.getInt("meetup_minppl"));
				meetupVO.setMeetup_maxppl(rs.getInt("meetup_maxppl"));
				meetupVO.setMeetup_joindate(rs.getDate("meetup_joindate"));
				list.add(meetupVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public List<MeetupVO> getSearchName(String name) {
		List<MeetupVO> list = new ArrayList<MeetupVO>();
		MeetupVO meetupVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_NAME_STMT1+name+GET_STMT2);
//			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupVO = new MeetupVO();
				meetupVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupVO.setMeetup_name(rs.getString("meetup_name"));
				meetupVO.setMem_ID(rs.getString("mem_ID"));
				meetupVO.setMeetup_date(rs.getTimestamp("meetup_date"));
				meetupVO.setMeetup_loc(rs.getString("meetup_loc"));
				meetupVO.setMeetup_status(rs.getInt("meetup_status"));
//				meetupVO.setMeetup_pic(rs.getBytes("meetup_pic"));
				meetupVO.setMeetup_info(rs.getString("meetup_info"));
				meetupVO.setMeetup_minppl(rs.getInt("meetup_minppl"));
				meetupVO.setMeetup_maxppl(rs.getInt("meetup_maxppl"));
				meetupVO.setMeetup_joindate(rs.getDate("meetup_joindate"));
				list.add(meetupVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<MeetupVO> getVisibleAll() {
		List<MeetupVO> list = new ArrayList<MeetupVO>();
		MeetupVO meetupVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_VISIBLE_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupVO = new MeetupVO();
				meetupVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupVO.setMeetup_name(rs.getString("meetup_name"));
				meetupVO.setMem_ID(rs.getString("mem_ID"));
				meetupVO.setMeetup_date(rs.getTimestamp("meetup_date"));
				meetupVO.setMeetup_loc(rs.getString("meetup_loc"));
				meetupVO.setMeetup_status(rs.getInt("meetup_status"));
				meetupVO.setMeetup_pic(rs.getBytes("meetup_pic"));
				meetupVO.setMeetup_info(rs.getString("meetup_info"));
				meetupVO.setMeetup_minppl(rs.getInt("meetup_minppl"));
				meetupVO.setMeetup_maxppl(rs.getInt("meetup_maxppl"));
				meetupVO.setMeetup_joindate(rs.getDate("meetup_joindate"));
				list.add(meetupVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public void InvisibleUpdate(String meetup_ID) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INVISIBLE);
			
			pstmt.setString(1, meetup_ID);
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public Set<MeetupVO> homePG() {
		Set<MeetupVO> HomeSet = new HashSet<MeetupVO>();
		MeetupVO meetupVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(HOMEPAGE);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupVO = new MeetupVO();
				meetupVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupVO.setMeetup_name(rs.getString("meetup_name"));
				meetupVO.setMem_ID(rs.getString("mem_ID"));
				meetupVO.setMeetup_date(rs.getTimestamp("meetup_date"));
				meetupVO.setMeetup_loc(rs.getString("meetup_loc"));
				meetupVO.setMeetup_status(rs.getInt("meetup_status"));
				meetupVO.setMeetup_pic(rs.getBytes("meetup_pic"));
				meetupVO.setMeetup_info(rs.getString("meetup_info"));
				meetupVO.setMeetup_minppl(rs.getInt("meetup_minppl"));
				meetupVO.setMeetup_maxppl(rs.getInt("meetup_maxppl"));
				meetupVO.setMeetup_joindate(rs.getDate("meetup_joindate"));
				HomeSet.add(meetupVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		}return HomeSet;
	}
	
	public static void main(String[] args) {
		MeetupJDBCDAO dao = new MeetupJDBCDAO();

		//新增
//		MeetupVO meetupVO1 = new MeetupVO();
//		meetupVO1.setMeetup_name("love520");
//		meetupVO1.setMem_ID("M000005");
//		meetupVO1.setMeetup_date(java.sql.Timestamp.valueOf("2019-04-01 22:30:15"));
//		meetupVO1.setMeetup_loc("綠島");
//		meetupVO1.setMeetup_status(1);
////		meetupVO1.setMeetup_pic();
//		meetupVO1.setMeetup_info("一起唱綠島小夜曲");
//		meetupVO1.setMeetup_minppl(3);
//		meetupVO1.setMeetup_maxppl(10);
//		meetupVO1.setMeetup_joindate(java.sql.Date.valueOf("2019-03-28"));
//		dao.insert(meetupVO1);
		
		//update
//		MeetupVO meetupVO2 = new MeetupVO();
//		meetupVO2.setMeetup_ID("MP000002");
//		meetupVO2.setMeetup_name("好山好水好幸福");
//		meetupVO2.setMem_ID("M000003");
//		meetupVO2.setMeetup_date(java.sql.Timestamp.valueOf("2019-06-06 22:30:15"));
//		meetupVO2.setMeetup_loc("970花蓮市中正路1號");
//		meetupVO2.setMeetup_status(new Integer(1));
////		meetupVO2.setMeetup_pic(null);
//		meetupVO2.setMeetup_info("come on :)");
//		meetupVO2.setMeetup_minppl(3);
//		meetupVO2.setMeetup_maxppl(10);
//		meetupVO1.setMeetup_joindate(java.sql.Date.valueOf("2019-05-30"));
//		dao.update(meetupVO2);
		
		//delete
//		dao.delete("MP000001");
		
//		//查詢
//		MeetupVO meetupVO3 = dao.findByPrimaryKey("MP000007");
//		System.out.println(meetupVO3.getMeetup_ID());
//		System.out.println(meetupVO3.getMeetup_name());
//		System.out.println(meetupVO3.getMem_ID());
//		System.out.println(meetupVO3.getMeetup_date());
//		System.out.println(meetupVO3.getMeetup_loc());
//		System.out.println(meetupVO3.getMeetup_status());
//		System.out.println(meetupVO3.getMeetup_pic());
//		System.out.println(meetupVO3.getMeetup_info());
//		System.out.println(meetupVO3.getMeetup_minppl());
//		System.out.println(meetupVO3.getMeetup_maxppl());
//		System.out.println(meetupVO3.getMeetup_joindate());
//		System.out.println("----------------");
		
		//查詢
//		List<MeetupVO> list = dao.getAll();
//		for(MeetupVO mtupVO : list) {
//			System.out.println(mtupVO.getMeetup_ID());
//			System.out.println(mtupVO.getMeetup_name());
//			System.out.println(mtupVO.getMem_ID());
//			System.out.println(mtupVO.getMeetup_date());
//			System.out.println(mtupVO.getMeetup_loc());
//			System.out.println(mtupVO.getMeetup_status());
//			System.out.println(mtupVO.getMeetup_pic());
//			System.out.println(mtupVO.getMeetup_info());
//			System.out.println(mtupVO.getMeetup_minppl());
//			System.out.println(mtupVO.getMeetup_maxppl());
//			System.out.println(mtupVO.getMeetup_joindate());
//			System.out.println("----------------");
//		}
		
		//查詢
//		List<MeetupVO> list = dao.getHostAll("M000001");
//		for(MeetupVO mtupVO : list) {
//			System.out.println(mtupVO.getMeetup_ID());
//			System.out.println(mtupVO.getMeetup_name());
//			System.out.println(mtupVO.getMem_ID());
//			System.out.println(mtupVO.getMeetup_date());
//			System.out.println(mtupVO.getMeetup_loc());
//			System.out.println(mtupVO.getMeetup_status());
////			System.out.println(mtupVO.getMeetup_pic());
//			System.out.println(mtupVO.getMeetup_info());
//			System.out.println(mtupVO.getMeetup_minppl());
//			System.out.println(mtupVO.getMeetup_maxppl());
//			System.out.println(mtupVO.getMeetup_joindate());	
//			System.out.println("----------------");
//		}
		
		//查詢
//		List<MeetupVO> list = dao.getSearchLoc("媽媽");
//			for(MeetupVO mtupVO : list) {
//				System.out.println(mtupVO.getMeetup_ID());
//				System.out.println(mtupVO.getMeetup_name());
//				System.out.println(mtupVO.getMem_ID());
//				System.out.println(mtupVO.getMeetup_date());
//				System.out.println(mtupVO.getMeetup_loc());
//				System.out.println(mtupVO.getMeetup_status());
////				System.out.println(mtupVO.getMeetup_pic());
//				System.out.println(mtupVO.getMeetup_info());
//				System.out.println(mtupVO.getMeetup_minppl());
//				System.out.println(mtupVO.getMeetup_maxppl());
//				System.out.println(mtupVO.getMeetup_joindate());
//				System.out.println("----------------");
//		}
			
			//查詢
//		List<MeetupVO> list = dao.getSearchName("媽媽");
//			for(MeetupVO mtupVO : list) {
//				System.out.println(mtupVO.getMeetup_ID());
//				System.out.println(mtupVO.getMeetup_name());
//				System.out.println(mtupVO.getMem_ID());
//				System.out.println(mtupVO.getMeetup_date());
//				System.out.println(mtupVO.getMeetup_loc());
//				System.out.println(mtupVO.getMeetup_status());
//				System.out.println(mtupVO.getMeetup_pic());
//				System.out.println(mtupVO.getMeetup_info());
//				System.out.println(mtupVO.getMeetup_minppl());
//				System.out.println(mtupVO.getMeetup_maxppl());
//				System.out.println(mtupVO.getMeetup_joindate());
//				System.out.println("----------------");
//			}	
		
		Set<MeetupVO> homeSet = dao.homePG();
		for(MeetupVO mtupVO : homeSet) {
			System.out.println(mtupVO.getMeetup_ID());
			System.out.println(mtupVO.getMeetup_name());
			System.out.println(mtupVO.getMem_ID());
			System.out.println(mtupVO.getMeetup_date());
			System.out.println(mtupVO.getMeetup_loc());
			System.out.println(mtupVO.getMeetup_status());
			System.out.println(mtupVO.getMeetup_pic());
			System.out.println(mtupVO.getMeetup_info());
			System.out.println(mtupVO.getMeetup_minppl());
			System.out.println(mtupVO.getMeetup_maxppl());
			System.out.println(mtupVO.getMeetup_joindate());
			System.out.println("----------------");
		}
		
	}

}
