package com.meetU.meetup_mem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.meetU.meetup.model.MeetupDAO_interface;
import com.meetU.meetup.model.MeetupJDBCDAO;
import com.meetU.meetup.model.MeetupVO;

public class MeetupMemJDBCDAO implements MeetupMemDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO MEETUP_MEM (meetup_ID, mem_ID, mem_showup) VALUES (?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MEETUP_MEM WHERE meetup_ID =?";
	private static final String GET_ONE_STMT = "SELECT * FROM MEETUP_MEM WHERE meetup_ID =? AND MEM_ID=?";
	private static final String GET_MYALL_STMT = "SELECT * FROM MEETUP_MEM WHERE MEM_ID =?";
	private static final String GET_HOST_RATE  = "SELECT MEETUP.*,MEETUP_MEM.* FROM MEETUP JOIN MEETUP_MEM ON MEETUP.MEETUP_ID = MEETUP_MEM.MEETUP_ID "
			+ "AND MEETUP.MEETUP_STATUS=1 AND MEETUP_MEM.MEETUP_RATE IS NOT NULL AND MEETUP_MEM.MEM_ID =?";
	private static final String DELETE = "DELETE FROM MEETUP_MEM WHERE meetup_ID =? and MEM_ID =?";
	
	private static final String UPDATE = "UPDATE MEETUP_MEM SET meetup_rate=?, meetup_comment=?, mem_showup=? WHERE meetup_ID =? and MEM_ID =?";
	
	@Override
	public void insert(MeetupMemVO meetupMemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, meetupMemVO.getMeetup_ID());
			pstmt.setString(2, meetupMemVO.getMem_ID());
			pstmt.setInt(3, 1);
			
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
	public void update(MeetupMemVO meetupMemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, meetupMemVO.getMeetup_rate());
			pstmt.setString(2, meetupMemVO.getMeetup_comment());
			pstmt.setInt(3, meetupMemVO.getMem_showup());
			pstmt.setString(4, meetupMemVO.getMeetup_ID());
			pstmt.setString(5, meetupMemVO.getMem_ID());
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
	public MeetupMemVO findByPrimaryKey(String meetup_ID, String mem_ID) {
		MeetupMemVO meetupMemVO = null;
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
				meetupMemVO = new MeetupMemVO();
				meetupMemVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupMemVO.setMem_ID(rs.getString("mem_ID"));
				meetupMemVO.setMem_showup(rs.getInt("mem_showup"));
				meetupMemVO.setMeetup_rate(rs.getInt("meetup_rate"));
				meetupMemVO.setMeetup_comment(rs.getString("meetup_comment"));
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
		}return meetupMemVO;
	}

	@Override
	public List<MeetupMemVO> findMyAllMeetup(String mem_ID) {
		List<MeetupMemVO> list = new ArrayList<MeetupMemVO>();
		MeetupMemVO meetupMemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	@Override
	public List<MeetupMemVO> getAll(String meetup_ID) {
		List<MeetupMemVO> list = new ArrayList<MeetupMemVO>();
		MeetupMemVO meetupMemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	@Override
	public Set<MeetupMemVO> findHostRate(String mem_ID) {
		Set<MeetupMemVO> hset = new HashSet<MeetupMemVO>();
		MeetupMemVO meetupMemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_HOST_RATE);
			pstmt.setString(1, mem_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				meetupMemVO = new MeetupMemVO();
				meetupMemVO.setMeetup_ID(rs.getString("meetup_ID"));
				meetupMemVO.setMem_ID(rs.getString("mem_ID"));
				meetupMemVO.setMeetup_rate(rs.getInt("meetup_rate"));
				meetupMemVO.setMeetup_comment(rs.getString("meetup_comment"));
				hset.add(meetupMemVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	
	public static void main(String[] args) {
		MeetupMemJDBCDAO dao = new MeetupMemJDBCDAO();
		
		//新增
		MeetupMemVO meetupMemVO1 = new MeetupMemVO();
		meetupMemVO1.setMeetup_ID("MP000006");
		meetupMemVO1.setMem_ID("M000012");
//		meetupMemVO1.setMem_showup(new Integer(1));
		dao.insert(meetupMemVO1);
		
//		//update
//		MeetupMemVO meetupMemVO2 = new MeetupMemVO();
//		meetupMemVO2.setMeetup_ID("MP000003");
//		meetupMemVO2.setMem_ID("M000003");
//		meetupMemVO2.setMem_showup(1);
//		meetupMemVO2.setMeetup_rate(3);
//		meetupMemVO2.setMeetup_comment("ok");
//		dao.update(meetupMemVO2);
		
//		//delete
//		dao.delete("MP000006", "M000003");
//		
//		//查詢
//		MeetupMemVO meetupMemVO3 = dao.findByPrimaryKey("MP000001" ,"M000001");
//		System.out.println(meetupMemVO3.getMeetup_ID());
//		System.out.println(meetupMemVO3.getMem_ID());
//		System.out.println(meetupMemVO3.getMem_showup());
//		System.out.println(meetupMemVO3.getMeetup_rate());
//		System.out.println(meetupMemVO3.getMeetup_comment());
//		System.out.println("----------------");
		
//		//查詢
//		List<MeetupMemVO> list = dao.getAll("MP000006");
//		for(MeetupMemVO mtupVO : list) {
//			System.out.println(mtupVO.getMeetup_ID());
//			System.out.println(mtupVO.getMem_ID());
//			System.out.println(mtupVO.getMem_showup());
//			System.out.println(mtupVO.getMeetup_rate());
//			System.out.println(mtupVO.getMeetup_comment());
//			System.out.println("----------------");
//		}
		
//		//查詢
//		List<MeetupMemVO> list = dao.findMyAllMeetup("M000006");
//		for(MeetupMemVO mtupVO : list) {
//			System.out.println(mtupVO.getMeetup_ID());
//			System.out.println(mtupVO.getMem_ID());
//			System.out.println(mtupVO.getMem_showup());
//			System.out.println(mtupVO.getMeetup_rate());
//			System.out.println(mtupVO.getMeetup_comment());
//			System.out.println("----------------");
//		}
	}

	
}
