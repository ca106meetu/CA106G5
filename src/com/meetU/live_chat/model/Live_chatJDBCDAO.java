package com.meetU.live_chat.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.meetU.ad.model.AdJDBCDAO;
import com.meetU.ad.model.AdVO;

public class Live_chatJDBCDAO implements Live_chatDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO LIVE_CHAT (CHAT_ID, HOST_ID, MEM_ID, CHAT_CONT, CHAT_DATE) VALUES('CH'||LPAD(to_char(live_chat_seq.NEXTVAL), 6, '0'),?,?,?,?)";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE_CHAT where HOST_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE_CHAT";

	public Live_chatJDBCDAO() {

	}

	@Override
	public void insert(Live_chatVO live_chatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, live_chatVO.getHost_ID());
			pstmt.setString(2, live_chatVO.getMem_ID());
			pstmt.setString(3, live_chatVO.getChat_cont());
			pstmt.setTimestamp(4, live_chatVO.getChat_date());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<Live_chatVO> findByPrimaryKey(String host_ID) {
		List<Live_chatVO> list = new ArrayList<>();
		Live_chatVO live_chatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, host_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				live_chatVO = new Live_chatVO();

				live_chatVO.setChat_ID(rs.getString("CHAT_ID"));
				live_chatVO.setHost_ID(rs.getString("HOST_ID"));
				live_chatVO.setMem_ID(rs.getString("MEM_ID"));
				live_chatVO.setChat_cont(rs.getString("CHAT_CONT"));
				live_chatVO.setChat_date(rs.getTimestamp("CHAT_DATE"));

				list.add(live_chatVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		}
		return list;
	}

	@Override
	public List<Live_chatVO> getALL() {
		List<Live_chatVO> list = new ArrayList<>();
		Live_chatVO live_chatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				live_chatVO = new Live_chatVO();

				live_chatVO.setChat_ID(rs.getString("CHAT_ID"));
				live_chatVO.setHost_ID(rs.getString("HOST_ID"));
				live_chatVO.setMem_ID(rs.getString("MEM_ID"));
				live_chatVO.setChat_cont(rs.getString("CHAT_CONT"));
				live_chatVO.setChat_date(rs.getTimestamp("CHAT_DATE"));

				list.add(live_chatVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		}
		return list;
	}

	// 以下測試
	public static void main(String[] args) throws IOException {
		Live_chatJDBCDAO dao = new Live_chatJDBCDAO();

////				新增
//
//		Date today = new Date();
//		Live_chatVO live_chatVO = new Live_chatVO();
//		live_chatVO.setHost_ID("M000001");
//		live_chatVO.setMem_ID("M000003");
//		live_chatVO.setChat_cont("收傳單");
//		live_chatVO.setChat_date(new Timestamp(today.getTime()));
//		dao.insert(live_chatVO);
//
//		System.out.println("成功新增");

////				條件查詢
//		List<Live_chatVO> list = dao.findByPrimaryKey("M000001");
//		for (Live_chatVO live_chatVO4 : list) {
//			System.out.print(live_chatVO4.getChat_ID() + ", ");
//			System.out.print(live_chatVO4.getHost_ID() + ", ");
//			System.out.print(live_chatVO4.getMem_ID() + ", ");
//			System.out.print(live_chatVO4.getChat_cont() + ", ");
//			System.out.println(live_chatVO4.getChat_date() + " ");
//			System.out.println("----------------------------");
//		}

////				查詢全部	
//		List<Live_chatVO> list1 = dao.getALL();
//		for (Live_chatVO live_chatVO4 : list1) {
//			System.out.print(live_chatVO4.getChat_ID() + ", ");
//			System.out.print(live_chatVO4.getHost_ID() + ", ");
//			System.out.print(live_chatVO4.getMem_ID() + ", ");
//			System.out.print(live_chatVO4.getChat_cont() + ", ");
//			System.out.println(live_chatVO4.getChat_date() + " ");
//			System.out.println("----------------------------");		
//		}
	}

}
