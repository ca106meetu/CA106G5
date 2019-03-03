package com.meetU.live_like.model;

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

public class Live_likeJDBCDAO implements Live_likeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO LIVE_LIKE  (MEM_ID, HOST_ID) VALUES (?,?)";
	private static final String DELETE = "DELETE FROM LIVE_LIKE where MEM_ID = ? and HOST_ID= ? ";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE_LIKE where MEM_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE_LIKE";

	public Live_likeJDBCDAO() {

	}

	@Override
	public void insert(Live_likeVO live_likeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, live_likeVO.getMem_ID());
			pstmt.setString(2, live_likeVO.getHost_ID());

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
	public void delete(Live_likeVO live_likeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, live_likeVO.getMem_ID());
			pstmt.setString(2, live_likeVO.getHost_ID());

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
	public List<Live_likeVO> findByPrimaryKey(String mem_ID) {
		List<Live_likeVO> list = new ArrayList<>();
		Live_likeVO live_likeVO = null;
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
				live_likeVO = new Live_likeVO();

				live_likeVO.setMem_ID(rs.getString("MEM_ID"));
				live_likeVO.setHost_ID(rs.getString("HOST_ID"));

				list.add(live_likeVO);

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
	public List<Live_likeVO> getALL() {
		List<Live_likeVO> list = new ArrayList<>();
		Live_likeVO live_likeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				live_likeVO = new Live_likeVO();

				live_likeVO.setMem_ID(rs.getString("MEM_ID"));
				live_likeVO.setHost_ID(rs.getString("HOST_ID"));

				list.add(live_likeVO);
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
		Live_likeJDBCDAO dao = new Live_likeJDBCDAO();

//				新增
		Live_likeVO live_likeVO1 = new Live_likeVO();
		live_likeVO1.setMem_ID("M000002");
		live_likeVO1.setHost_ID("M000005");
		dao.insert(live_likeVO1);

		System.out.println("成功新增");

//				刪除
		Live_likeVO live_likeVO3 = new Live_likeVO();
		live_likeVO3.setMem_ID("M000002");
		live_likeVO3.setHost_ID("M000005");
		dao.delete(live_likeVO3);

		System.out.println("刪除成功");

//				條件查詢
		List<Live_likeVO> list = dao.findByPrimaryKey("M000001");
		for (Live_likeVO live_likeVO4 : list) {
			System.out.print(live_likeVO4.getMem_ID() + ", ");
			System.out.println(live_likeVO4.getHost_ID() + " ");
			System.out.println("----------------------------");
		}

//				查詢全部	
		List<Live_likeVO> list2 = dao.getALL();
		for (Live_likeVO live_likeVO5 : list2) {
			System.out.print(live_likeVO5.getMem_ID() + ", ");
			System.out.println(live_likeVO5.getHost_ID() + " ");
			System.out.println("----------------------------");
		}
	}

}
