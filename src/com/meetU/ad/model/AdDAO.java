package com.meetU.ad.model;

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

public class AdDAO implements AdDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO AD (AD_ID, HOST_ID, AD_NAME, AD_CONT, AD_COST, APPLY_STATUS, AD_STAR, AD_END) VALUES ('AD'||LPAD(to_char(ad_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE AD set AD_NAME = ?, AD_CONT = ?, AD_COST = ?, APPLY_STATUS = ?, AD_STAR = ?, AD_END = ?  where AD_ID = ?";
	private static final String DELETE = "DELETE FROM AD where AD_ID = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM AD where AD_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM AD";

	public AdDAO() {

	}

	@Override
	public void insert(AdVO adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adVO.getHost_ID());
			pstmt.setString(2, adVO.getAd_name());
			pstmt.setBytes(3, adVO.getAd_cont());
			pstmt.setInt(4, adVO.getAd_cost());
			pstmt.setInt(5, adVO.getApply_status());
			pstmt.setDate(6, adVO.getAd_star());
			pstmt.setDate(7, adVO.getAd_end());

			pstmt.executeUpdate();

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adVO.getAd_name());
			pstmt.setBytes(2, adVO.getAd_cont());
			pstmt.setInt(3, adVO.getAd_cost());
			pstmt.setInt(4, adVO.getApply_status());
			pstmt.setDate(5, adVO.getAd_star());
			pstmt.setDate(6, adVO.getAd_end());
			pstmt.setString(7, adVO.getAd_ID());

			pstmt.executeUpdate();

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, adVO.getAd_ID());

			pstmt.executeUpdate();

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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
}
