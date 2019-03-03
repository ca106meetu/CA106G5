package com.meetU.ad.model;

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

import com.meetU.filerec.model.FileRecJDBCDAO;
import com.meetU.filerec.model.FileRecVO;
import com.meetU.live.model.LiveVO;

public class AdJDBCDAO implements AdDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO AD (AD_ID, HOST_ID, AD_NAME, AD_CONT, AD_COST, APPLY_STATUS, AD_STAR, AD_END) VALUES ('AD'||LPAD(to_char(ad_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE AD set AD_NAME = ?, AD_CONT = ?, AD_COST = ?, APPLY_STATUS = ?, AD_STAR = ?, AD_END = ?  where AD_ID = ?";
	private static final String DELETE = "DELETE FROM AD where AD_ID = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM AD where AD_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM AD";

	public AdJDBCDAO() {

	}

	@Override
	public void insert(AdVO adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adVO.getHost_ID());
			pstmt.setString(2, adVO.getAd_name());
			pstmt.setBytes(3, adVO.getAd_cont());
			pstmt.setInt(4, adVO.getAd_cost());
			pstmt.setInt(5, adVO.getApply_status());
			pstmt.setDate(6, adVO.getAd_star());
			pstmt.setDate(7, adVO.getAd_end());

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
	public void update(AdVO adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adVO.getAd_name());
			pstmt.setBytes(2, adVO.getAd_cont());
			pstmt.setInt(3, adVO.getAd_cost());
			pstmt.setInt(4, adVO.getApply_status());
			pstmt.setDate(5, adVO.getAd_star());
			pstmt.setDate(6, adVO.getAd_end());
			pstmt.setString(7, adVO.getAd_ID());

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
	public void delete(AdVO adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, adVO.getAd_ID());

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

	@Override
	public AdVO findByPrimaryKey(String ad_ID) {
		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ad_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adVO = new AdVO();

				adVO.setAd_ID(rs.getString("AD_ID"));
				adVO.setHost_ID(rs.getString("HOST_ID"));
				adVO.setAd_name(rs.getString("AD_NAME"));
				adVO.setAd_cont(rs.getBytes("AD_CONT"));
				adVO.setAd_cost(rs.getInt("AD_COST"));
				adVO.setApply_status(rs.getInt("APPLY_STATUS"));
				adVO.setAd_star(rs.getDate("AD_STAR"));
				adVO.setAd_end(rs.getDate("AD_END"));
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
		return adVO;
	}

	@Override
	public List<AdVO> getALL() {
		List<AdVO> list = new ArrayList<>();
		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				adVO = new AdVO();

				adVO.setAd_ID(rs.getString("AD_ID"));
				adVO.setHost_ID(rs.getString("HOST_ID"));
				adVO.setAd_name(rs.getString("AD_NAME"));
				adVO.setAd_cont(rs.getBytes("AD_CONT"));
				adVO.setAd_cost(rs.getInt("AD_COST"));
				adVO.setApply_status(rs.getInt("APPLY_STATUS"));
				adVO.setAd_star(rs.getDate("AD_STAR"));
				adVO.setAd_end(rs.getDate("AD_END"));

				list.add(adVO);
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
		AdJDBCDAO dao = new AdJDBCDAO();

//				新增
		File pic = new File("WebContent/FrontEnd/live/pic/P01.jpg");
		FileInputStream fis = new FileInputStream(pic);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8000];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}

		AdVO adVO1 = new AdVO();
		adVO1.setHost_ID("M000006");
		adVO1.setAd_name("測試新增");
		adVO1.setAd_cont(baos.toByteArray());
		adVO1.setAd_cost(50007);
		adVO1.setApply_status(0);
		adVO1.setAd_star(java.sql.Date.valueOf("2019-04-01"));
		adVO1.setAd_end(java.sql.Date.valueOf("2019-04-02"));
		dao.insert(adVO1);

		System.out.println("成功新增");

//				修改
		AdVO adVO2 = new AdVO();
		adVO2.setAd_name("房間修改");
		adVO2.setAd_cont(null);
		adVO2.setAd_cost(50017);
		adVO2.setApply_status(1);
		adVO2.setAd_star(java.sql.Date.valueOf("2019-10-13"));
		adVO2.setAd_end(java.sql.Date.valueOf("2019-10-13"));
		adVO2.setAd_ID("AD000007");
		dao.update(adVO2);
		System.out.println("修改成功");

//				刪除
		AdVO adVO3 = new AdVO();
		adVO3.setAd_ID("AD000007");
		dao.delete(adVO3);
		System.out.println("刪除成功");

//				條件查詢
		AdVO adVO4 = dao.findByPrimaryKey("AD000006");
		System.out.print(adVO4.getAd_ID() + ", ");
		System.out.print(adVO4.getHost_ID() + ", ");
		System.out.print(adVO4.getAd_name() + ", ");
		System.out.print(adVO4.getAd_cont() + ", ");
		System.out.print(adVO4.getAd_cost() + ", ");
		System.out.print(adVO4.getApply_status() + ", ");
		System.out.print(adVO4.getAd_star() + ", ");
		System.out.println(adVO4.getAd_end() + " ");
		System.out.println("----------------------------");

//				查詢全部	
		List<AdVO> list = dao.getALL();
		for (AdVO adVO5 : list) {
			System.out.print(adVO5.getAd_ID() + ", ");
			System.out.print(adVO5.getHost_ID() + ", ");
			System.out.print(adVO5.getAd_name() + ", ");
			System.out.print(adVO5.getAd_cont() + ", ");
			System.out.print(adVO5.getAd_cost() + ", ");
			System.out.print(adVO5.getApply_status() + ", ");
			System.out.print(adVO5.getAd_star() + ", ");
			System.out.println(adVO5.getAd_end() + " ");
			System.out.println("----------------------------");
		}
	}
}
