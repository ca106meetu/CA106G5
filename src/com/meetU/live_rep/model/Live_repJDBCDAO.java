package com.meetU.live_rep.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.meetU.ad.model.AdVO;
import com.meetU.live.model.LiveJDBCDAO;
import com.meetU.live.model.LiveVO;
import com.meetU.live_like.model.Live_likeVO;

public class Live_repJDBCDAO implements Live_repDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO LIVE_REP (REP_ID, HOST_ID, MEM_ID, REP_CONT, REP_DATE, REP_STATUS, REP_ANS, REP_ANS_DATE) VALUES ('LR'||LPAD(to_char(live_rep_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE LIVE_REP set REP_STATUS = ?,REP_ANS = ?,REP_ANS_DATE =? where REP_ID=?";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE_REP where MEM_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE_REP";

	public Live_repJDBCDAO() {

	}

	@Override
	public void insert(Live_repVO live_repVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, live_repVO.getHost_ID());
			pstmt.setString(2, live_repVO.getMem_ID());
			pstmt.setString(3, live_repVO.getRep_cont());
			pstmt.setTimestamp(4, live_repVO.getRep_date());
			pstmt.setInt(5, live_repVO.getRep_status());
			pstmt.setString(6, live_repVO.getRep_ans());
			pstmt.setTimestamp(7, live_repVO.getRep_ans_date());

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
	public void update(Live_repVO live_repVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, live_repVO.getRep_status());
			pstmt.setString(2, live_repVO.getRep_ans());
			pstmt.setTimestamp(3, live_repVO.getRep_ans_date());
			pstmt.setString(4, live_repVO.getRep_ID());

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
	public List<Live_repVO> findByPrimaryKey(String mem_ID) {
		List<Live_repVO> list = new ArrayList<>();
		Live_repVO live_repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, mem_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				live_repVO = new Live_repVO();

				live_repVO.setRep_ID(rs.getString("REP_ID"));
				live_repVO.setHost_ID(rs.getString("HOST_ID"));
				live_repVO.setMem_ID(rs.getString("MEM_ID"));
				live_repVO.setRep_cont(rs.getString("REP_CONT"));
				live_repVO.setRep_date(rs.getTimestamp("REP_DATE"));
				live_repVO.setRep_status(rs.getInt("REP_STATUS"));
				live_repVO.setRep_ans(rs.getString("REP_ANS"));
				live_repVO.setRep_ans_date(rs.getTimestamp("REP_ANS_DATE"));

				list.add(live_repVO);

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
	public List<Live_repVO> getALL() {
		List<Live_repVO> list = new ArrayList<>();
		Live_repVO live_repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				live_repVO = new Live_repVO();

				live_repVO.setRep_ID(rs.getString("REP_ID"));
				live_repVO.setHost_ID(rs.getString("HOST_ID"));
				live_repVO.setMem_ID(rs.getString("MEM_ID"));
				live_repVO.setRep_cont(rs.getString("REP_CONT"));
				live_repVO.setRep_date(rs.getTimestamp("REP_DATE"));
				live_repVO.setRep_status(rs.getInt("REP_STATUS"));
				live_repVO.setRep_ans(rs.getString("REP_ANS"));
				live_repVO.setRep_ans_date(rs.getTimestamp("REP_ANS_DATE"));

				list.add(live_repVO);

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
		Live_repJDBCDAO dao = new Live_repJDBCDAO();

////		新增尚未
//
//		Date today = new Date();
//		Live_repVO live_repVO1 = new Live_repVO();
//
//		live_repVO1.setHost_ID("M000002");
//		live_repVO1.setMem_ID("M000004");
//		live_repVO1.setRep_cont("測試新增");
//		live_repVO1.setRep_date(new Timestamp(today.getTime()));
//		live_repVO1.setRep_status(0);
//		live_repVO1.setRep_ans("測試回覆訊息");
//		live_repVO1.setRep_ans_date(new Timestamp(today.getTime()));
//		dao.insert(live_repVO1);
//		System.out.println("成功新增");
//
////		修改尚未
//		Date today1 = new Date();
//		Live_repVO live_repVO2 = new Live_repVO();
//
//		live_repVO2.setRep_status(1);
//		live_repVO2.setRep_ans("測試修改");
//		live_repVO2.setRep_ans_date(new Timestamp(today1.getTime()));
//		live_repVO2.setRep_ID("LR000007");
//		dao.update(live_repVO2);
//		System.out.println("修改成功");

//		條件查詢
		List<Live_repVO> list = dao.findByPrimaryKey("M000004");
		for (Live_repVO live_repVO4 : list) {
			System.out.print(live_repVO4.getRep_ID() + ",");
			System.out.print(live_repVO4.getHost_ID() + ",");
			System.out.print(live_repVO4.getMem_ID() + ",");
			System.out.print(live_repVO4.getRep_cont() + ",");
			System.out.print(live_repVO4.getRep_date() + ",");
			System.out.print(live_repVO4.getRep_status() + ",");
			System.out.print(live_repVO4.getRep_ans() + ",");
			System.out.println(live_repVO4.getRep_ans_date());
			System.out.println("----------------------------");
		}

////		查詢全部
//		List<Live_repVO> list1 = dao.getALL();
//		for (Live_repVO live_repVO5 : list1) {
//			System.out.print(live_repVO5.getRep_ID() + ",");
//			System.out.print(live_repVO5.getHost_ID() + ",");
//			System.out.print(live_repVO5.getMem_ID() + ",");
//			System.out.print(live_repVO5.getRep_cont() + ",");
//			System.out.print(live_repVO5.getRep_date() + ",");
//			System.out.print(live_repVO5.getRep_status() + ",");
//			System.out.print(live_repVO5.getRep_ans() + ",");
//			System.out.println(live_repVO5.getRep_ans_date());
//			System.out.println("----------------------------");
//		}

	}
}
