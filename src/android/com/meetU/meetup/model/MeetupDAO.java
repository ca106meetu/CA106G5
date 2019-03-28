package android.com.meetU.meetup.model;

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

import android.com.meetU.meetup.model.MeetupVO;

public class MeetupDAO implements MeetupDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	private static final String INSERT_STMT = 
//			"INSERT INTO MEETUP (meetup_ID, meetup_name, mem_ID, meetup_date, meetup_loc, meetup_status, meetup_info)"
//			+ "VALUES ('MP'||LPAD(to_char(meetup_seq.NEXTVAL), 6, '0'), ?,?,?,?,?,?)";
//	
//	private static final String GET_ALL_STMT = "SELECT * FROM MEETUP";
//	private static final String GET_ONE_STMT = "SELECT * FROM MEETUP where meetup_ID=?";
//	private static final String GET_HOST_STMT = "SELECT * FROM MEETUP WHERE MEM_ID = ?";
//	private static final String DELETE = "DELETE FROM MEETUP WHERE MEETUP_ID=?";
//	private static final String UPDATE = "UPDATE MEETUP SET meetup_name=?, meetup_date=?, meetup_loc=?, meetup_status=?, meetup_info=? where meetup_ID =?";
	private static final String FIND_IMG_BY_ID = "SELECT MEETUP_PIC FROM MEETUP WHERE MEETUP_ID = ?";
	private static final String UPDATE_MEETUP_IMG = "UPDATE MEETUP SET MEETUP_PIC=? WHERE MEETUP_ID=?";
	
	private static final String INSERT_STMT = 
			"INSERT INTO MEETUP (meetup_ID, meetup_name, mem_ID, meetup_date, meetup_loc, meetup_status, meetup_pic, meetup_info, MEETUP_MINPPL, MEETUP_MAXPPL, meetup_joindate)"
			+ "VALUES ('MP'||LPAD(to_char(meetup_seq.NEXTVAL), 6, '0'), ?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_HOST_ALL_STMT = "SELECT * FROM MEETUP WHERE MEETUP_STATUS=1 and mem_ID=? order by meetup_ID desc";//
	private static final String GET_ALL_STMT = "SELECT * FROM MEETUP WHERE MEETUP_STATUS=1 order by meetup_ID desc";
	private static final String GET_VISIBLE_ALL_STMT = "SELECT * FROM MEETUP WHERE MEETUP_STATUS=1 order by meetup_ID desc";//
	
	private static final String GET_ONE_STMT = "SELECT * FROM MEETUP where meetup_ID=?";
	private static final String DELETE = "DELETE FROM MEETUP WHERE MEETUP_ID=?";
	private static final String UPDATE = "UPDATE MEETUP SET meetup_name=?, meetup_date=?, meetup_loc=?, meetup_status=?, meetup_pic=?, meetup_info=?, MEETUP_MINPPL=?, MEETUP_MAXPPL=?, meetup_joindate=? where meetup_ID =?";
	private static final String INVISIBLE = "UPDATE MEETUP SET meetup_status=0 where meetup_ID =?";
	private static final String GET_LOCATION_STMT1 = "SELECT * FROM MEETUP where MEETUP_STATUS=1 and meetup_loc like '%";//
	private static final String GET_STMT2= "%' order by meetup_ID desc";
	private static final String GET_NAME_STMT1 = "SELECT * FROM MEETUP where MEETUP_STATUS=1 and meetup_name like '%";//

	private static final String INSERT_FOUNDER_STMT = "INSERT INTO MEETUP_MEM (meetup_ID, mem_ID, mem_showup) VALUES (?,?,?)";
	
	@Override
	public void insert(MeetupVO meetupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			//先新增社團
			String cols[]= {"MEETUP_ID"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			
			pstmt.setString(1, meetupVO.getMeetup_name());
			pstmt.setString(2, meetupVO.getMem_ID());
			pstmt.setTimestamp(3, meetupVO.getMeetup_date());
			pstmt.setString(4, meetupVO.getMeetup_loc());
			pstmt.setInt(5, meetupVO.getMeetup_status());
			pstmt.setString(6, meetupVO.getMeetup_info());
			pstmt.setInt(7, meetupVO.getMeetup_minppl());
			pstmt.setInt(8, meetupVO.getMeetup_maxppl());
			pstmt.setDate(9, meetupVO.getMeetup_joindate());
			pstmt.executeUpdate();
			
			//攫取對應的自增主鍵值
			String last_meetup_ID = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				last_meetup_ID = rs.getString(1);
				System.out.println("自增主鍵值="+last_meetup_ID+"(剛創建的聯誼編號)");
			}else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			//再同時新增創辦人為聯誼成員
			pstmt = con.prepareStatement(INSERT_FOUNDER_STMT);
			
			pstmt.setString(1, last_meetup_ID);
			pstmt.setString(2, meetupVO.getMem_ID());
			pstmt.setInt(3, 1);
			
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
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
	public boolean update(MeetupVO meetupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isUpdated = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, meetupVO.getMeetup_name());
			pstmt.setTimestamp(2, meetupVO.getMeetup_date());
			pstmt.setString(3, meetupVO.getMeetup_loc());
			pstmt.setInt(4, meetupVO.getMeetup_status());
			pstmt.setString(5, meetupVO.getMeetup_info());
			pstmt.setInt(6, meetupVO.getMeetup_minppl());
			pstmt.setInt(7, meetupVO.getMeetup_maxppl());
			pstmt.setDate(8, meetupVO.getMeetup_joindate());
			pstmt.setString(9, meetupVO.getMeetup_ID());
			int count = pstmt.executeUpdate();
			isUpdated = count > 0 ? true : false;
		}catch(SQLException se) {
			se.printStackTrace();
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
		return isUpdated;
	}
	
	@Override
	public void InvisibleUpdate(String meetup_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INVISIBLE);
			
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
				meetupVO.setMeetup_date(rs.getTimestamp("meetup_date"));
				meetupVO.setMeetup_loc(rs.getString("meetup_loc"));
				meetupVO.setMeetup_status(rs.getInt("meetup_status"));
				meetupVO.setMeetup_info(rs.getString("meetup_info"));
				meetupVO.setMeetup_minppl(rs.getInt("meetup_minppl"));
				meetupVO.setMeetup_maxppl(rs.getInt("meetup_maxppl"));
				meetupVO.setMeetup_joindate(rs.getDate("meetup_joindate"));
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
				meetupVO.setMeetup_date(rs.getTimestamp("meetup_date"));
				meetupVO.setMeetup_loc(rs.getString("meetup_loc"));
				meetupVO.setMeetup_status(rs.getInt("meetup_status"));
				meetupVO.setMeetup_info(rs.getString("meetup_info"));
				meetupVO.setMeetup_minppl(rs.getInt("meetup_minppl"));
				meetupVO.setMeetup_maxppl(rs.getInt("meetup_maxppl"));
				meetupVO.setMeetup_joindate(rs.getDate("meetup_joindate"));
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
	@Override
	public List<MeetupVO> getVisibleAll() {
		List<MeetupVO> list = new ArrayList<MeetupVO>();
		MeetupVO meetupVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
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
//				meetupVO.setMeetup_info(rs.getString("meetup_info"));
				meetupVO.setMeetup_minppl(rs.getInt("meetup_minppl"));
				meetupVO.setMeetup_maxppl(rs.getInt("meetup_maxppl"));
				meetupVO.setMeetup_joindate(rs.getDate("meetup_joindate"));
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
	
	@Override
	public List<MeetupVO> getHost(String mem_ID) {
		List<MeetupVO> list = new ArrayList<MeetupVO>();
		MeetupVO meetupVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
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
				//meetupVO.setMeetup_status(rs.getInt("meetup_status"));
				meetupVO.setMeetup_info(rs.getString("meetup_info"));
				meetupVO.setMeetup_minppl(rs.getInt("meetup_minppl"));
				meetupVO.setMeetup_maxppl(rs.getInt("meetup_maxppl"));
				meetupVO.setMeetup_joindate(rs.getDate("meetup_joindate"));
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

	@Override
	public byte[] getImage(String meetup_ID) {
		byte[] picture = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_IMG_BY_ID);
			pstmt.setString(1, meetup_ID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				picture = rs.getBytes("meetup_pic");
			}
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return picture;
	}
	
	@Override
	public boolean update_image(String meetup_ID,byte[] meetup_image) {
		boolean isUpdated = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEETUP_IMG);
			
			pstmt.setBytes(1, meetup_image);
			pstmt.setString(2, meetup_ID);
			
			int count = pstmt.executeUpdate();
			isUpdated = count > 0 ? true : false;
			
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
		
		return isUpdated;
	}
	
}
