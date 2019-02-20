package com.meetU.live.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class LiveJDBCDAO implements LiveDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO LIVE (HOST_ID, LIVE_NAME, LIVE_ACC, LIVE_PIC, LIVE_DATE, LIVE_STATUS) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE LIVE set LIVE_NAME = ?, LIVE_ACC = ?, LIVE_PIC = ?, LIVE_STATUS = ?  where HOST_ID = ?";
	private static final String DELETE = "DELETE FROM LIVE where HOST_ID = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE where HOST_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE";

	public LiveJDBCDAO() {
	}

//	新增
	@Override
	public void insert(LiveVO liveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, liveVO.getHost_ID());
			pstmt.setString(2, liveVO.getLive_name());
			pstmt.setInt(3, liveVO.getLive_acc());
			pstmt.setBytes(4, liveVO.getLive_pic());
			pstmt.setTimestamp(5, liveVO.getLive_date());
			pstmt.setInt(6, liveVO.getLive_status());

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

//	修改
	@Override
	public void update(LiveVO liveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, liveVO.getLive_name());
			pstmt.setInt(2, liveVO.getLive_acc());
			pstmt.setBytes(3, liveVO.getLive_pic());
			pstmt.setInt(4, liveVO.getLive_status());
			pstmt.setString(5, liveVO.getHost_ID());

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

//  刪除
	@Override
	public void delete(LiveVO liveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, liveVO.getHost_ID());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
	}

//  條件查詢
	@Override
	public LiveVO findByPrimaryKey(String host_ID) {
		LiveVO liveVO = null;
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
				liveVO = new LiveVO();

				liveVO.setHost_ID(rs.getString("HOST_ID"));
				liveVO.setLive_name(rs.getString("LIVE_NAME"));
				liveVO.setLive_acc(rs.getInt("LIVE_ACC"));
				liveVO.setLive_pic(rs.getBytes("LIVE_PIC"));
				liveVO.setLive_date(rs.getTimestamp("LIVE_DATE"));
				liveVO.setLive_status(rs.getInt("LIVE_STATUS"));
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
		return liveVO;
	}

//  查詢全部
	@Override
	public List<LiveVO> getALL() {
		List<LiveVO> list = new ArrayList<>();
		LiveVO liveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				liveVO = new LiveVO();

				liveVO.setHost_ID(rs.getString("HOST_ID"));
				liveVO.setLive_name(rs.getString("LIVE_NAME"));
				liveVO.setLive_acc(rs.getInt("LIVE_ACC"));
				liveVO.setLive_pic(rs.getBytes("LIVE_PIC"));
				liveVO.setLive_date(rs.getTimestamp("LIVE_DATE"));
				liveVO.setLive_status(rs.getInt("LIVE_STATUS"));

				list.add(liveVO);
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
		LiveJDBCDAO dao = new LiveJDBCDAO();

//		新增
		File pic = new File("WebContent/FrontEnd/live/pic/P01.jpg");
		FileInputStream fis = new FileInputStream(pic);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8000];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		Date today = new Date();
		LiveVO liveVO1 = new LiveVO();

		liveVO1.setHost_ID("M000006");
		liveVO1.setLive_name("測試房間新增");
		liveVO1.setLive_acc(100);
		liveVO1.setLive_pic(baos.toByteArray());
		liveVO1.setLive_date(new Timestamp(today.getTime()));
		liveVO1.setLive_status(1);
		dao.insert(liveVO1);
		baos.close();
		fis.close();
		System.out.println("成功新增");

//		修改
		LiveVO liveVO2 = new LiveVO();

		liveVO2.setLive_name("測試房間修改");
		liveVO2.setLive_acc(200);
		liveVO2.setLive_pic(null);
		liveVO2.setLive_status(0);
		liveVO2.setHost_ID("M000006");
		dao.update(liveVO2);
		System.out.println("修改成功");

//		刪除
		LiveVO liveVO3 = new LiveVO();

		liveVO3.setHost_ID("M000006");
		dao.delete(liveVO3);
		System.out.println("刪除成功");

//		條件查詢
		LiveVO liveVO4 = dao.findByPrimaryKey("M000005");
		System.out.println(liveVO4.getHost_ID() + ",");
		System.out.println(liveVO4.getLive_name() + ",");
		System.out.println(liveVO4.getLive_acc() + ",");
		System.out.println(liveVO4.getLive_pic() + ",");
		System.out.println(liveVO4.getLive_date() + ",");
		System.out.println(liveVO4.getLive_status());
		System.out.println("----------------------------");

//		查詢全部
		List<LiveVO> list = dao.getALL();
		for (LiveVO liveVO5 : list) {
			System.out.println(liveVO5.getHost_ID() + ",");
			System.out.println(liveVO5.getLive_name() + ",");
			System.out.println(liveVO5.getLive_acc() + ",");
			System.out.println(liveVO5.getLive_pic() + ",");
			System.out.println(liveVO5.getLive_date() + ",");
			System.out.println(liveVO5.getLive_status());
			System.out.println("----------------------------");
		}
	}
}
