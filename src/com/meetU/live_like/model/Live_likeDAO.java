package com.meetU.live_like.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Live_likeDAO implements Live_likeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO LIVE_LIKE  (MEM_ID, HOST_ID) VALUES (?,?)";
	private static final String DELETE = "DELETE FROM LIVE_LIKE where MEM_ID = ? and HOST_ID= ? ";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE_LIKE where MEM_ID = ?";
	private static final String GET_ONE_STMT2 = "SELECT * FROM LIVE_LIKE where MEM_ID = ? and HOST_ID= ?";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE_LIKE";

	public Live_likeDAO() {

	}

	@Override
	public void insert(Live_likeVO live_likeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, live_likeVO.getMem_ID());
			pstmt.setString(2, live_likeVO.getHost_ID());

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
	public void delete(Live_likeVO live_likeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, live_likeVO.getMem_ID());
			pstmt.setString(2, live_likeVO.getHost_ID());

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
	public List<Live_likeVO> findByPrimaryKey(String mem_ID) {
		List<Live_likeVO> list = new ArrayList<>();
		Live_likeVO live_likeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				live_likeVO = new Live_likeVO();

				live_likeVO.setMem_ID(rs.getString("MEM_ID"));
				live_likeVO.setHost_ID(rs.getString("HOST_ID"));

				list.add(live_likeVO);

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
	
	@Override
	public Live_likeVO findByPrimaryKey(String mem_ID,String host_ID) {
		
		Live_likeVO live_likeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT2);

			pstmt.setString(1, mem_ID);
			pstmt.setString(2, host_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				live_likeVO = new Live_likeVO();

				live_likeVO.setMem_ID(rs.getString("MEM_ID"));
				live_likeVO.setHost_ID(rs.getString("HOST_ID"));

			

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
		return live_likeVO;
	}
	

	@Override
	public List<Live_likeVO> getALL() {
		List<Live_likeVO> list = new ArrayList<>();
		Live_likeVO live_likeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				live_likeVO = new Live_likeVO();

				live_likeVO.setMem_ID(rs.getString("MEM_ID"));
				live_likeVO.setHost_ID(rs.getString("HOST_ID"));

				list.add(live_likeVO);
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