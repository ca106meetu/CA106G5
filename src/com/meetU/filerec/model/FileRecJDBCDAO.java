package com.meetU.filerec.model;

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

public class FileRecJDBCDAO implements FileRecDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO FILEREC (FILE_ID, HOST_ID, FILE_NAME, LIVE_DES, FILE_CONT, FILE_DATE, FILE_POP) VALUES ('FM'||LPAD(to_char(filer_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE FILEREC set FILE_NAME = ?, LIVE_DES = ?, FILE_CONT = ?, FILE_POP = ?  where FILE_ID = ?";
	private static final String DELETE = "DELETE FROM FILEREC where FILE_ID = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM FILEREC where FILE_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM FILEREC";

	public FileRecJDBCDAO() {

	}

	@Override
	public void insert(FileRecVO filerecVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, filerecVO.getHost_ID());
			pstmt.setString(2, filerecVO.getFile_name());
			pstmt.setString(3, filerecVO.getLive_des());
			pstmt.setString(4, filerecVO.getFile_cont());
			pstmt.setTimestamp(5, filerecVO.getFile_date());
			pstmt.setInt(6, filerecVO.getFile_pop());

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
	public void update(FileRecVO filerecVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, filerecVO.getFile_name());
			pstmt.setString(2, filerecVO.getLive_des());
			pstmt.setString(3, filerecVO.getFile_cont());
			pstmt.setInt(4, filerecVO.getFile_pop());
			pstmt.setString(5, filerecVO.getFile_ID());

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
	public void delete(FileRecVO filerecVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, filerecVO.getFile_ID());

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
	public FileRecVO findByPrimaryKey(String file_ID) {
		FileRecVO filerecVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, file_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				filerecVO = new FileRecVO();

				filerecVO.setFile_ID(rs.getString("FILE_ID"));
				filerecVO.setHost_ID(rs.getString("HOST_ID"));
				filerecVO.setFile_name(rs.getString("FILE_NAME"));
				filerecVO.setLive_des(rs.getString("LIVE_DES"));
				filerecVO.setFile_cont(rs.getString("FILE_CONT"));
				filerecVO.setFile_date(rs.getTimestamp("FILE_DATE"));
				filerecVO.setFile_pop(rs.getInt("FILE_POP"));
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
		return filerecVO;
	}

	@Override
	public List<FileRecVO> getALL() {
		List<FileRecVO> list = new ArrayList<>();
		FileRecVO filerecVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				filerecVO = new FileRecVO();

				filerecVO.setFile_ID(rs.getString("FILE_ID"));
				filerecVO.setHost_ID(rs.getString("HOST_ID"));
				filerecVO.setFile_name(rs.getString("FILE_NAME"));
				filerecVO.setLive_des(rs.getString("LIVE_DES"));
				filerecVO.setFile_cont(rs.getString("FILE_CONT"));
				filerecVO.setFile_date(rs.getTimestamp("FILE_DATE"));
				filerecVO.setFile_pop(rs.getInt("FILE_POP"));

				list.add(filerecVO);
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
		FileRecJDBCDAO dao = new FileRecJDBCDAO();

////			新增
//		Date today = new Date();
//		FileRecVO filerecVO1 = new FileRecVO();
//		filerecVO1.setHost_ID("M000003");
//		
//		filerecVO1.setFile_name("測試房間新增");
//		filerecVO1.setLive_des("測試房間新增");
//		filerecVO1.setFile_cont("測試房間新增");
//		filerecVO1.setFile_date(new Timestamp(today.getTime()));
//		filerecVO1.setFile_pop(100);
//		dao.insert(filerecVO1);
//
//		System.out.println("成功新增");

//			修改
		    FileRecVO filerecVO2 = new FileRecVO();
		    
//		    pstmt.setString(1, filerecVO.getFile_name());
//			pstmt.setString(2, filerecVO.getLive_des());
//			pstmt.setString(3, filerecVO.getFile_cont());
//			pstmt.setInt(4, filerecVO.getFile_pop());
//			pstmt.setString(5, filerecVO.getFile_ID());

			filerecVO2.setFile_name("測試房間修改");
			filerecVO2.setLive_des("測試房間修改");
			filerecVO2.setFile_cont("測試房間修改");
			filerecVO2.setFile_pop(888);
			filerecVO2.setFile_ID("FM000002");
			dao.update(filerecVO2);
			System.out.println("修改成功");
//
////			刪除
//			LiveVO liveVO3 = new LiveVO();
//
//			liveVO3.setHost_ID("M000006");
//			dao.delete(liveVO3);
//			System.out.println("刪除成功");
//
////			條件查詢
//			LiveVO liveVO4 = dao.findByPrimaryKey("M000005");
//			System.out.println(liveVO4.getHost_ID() + ",");
//			System.out.println(liveVO4.getLive_name() + ",");
//			System.out.println(liveVO4.getLive_acc() + ",");
//			System.out.println(liveVO4.getLive_pic() + ",");
//			System.out.println(liveVO4.getLive_date() + ",");
//			System.out.println(liveVO4.getLive_status());
//			System.out.println("----------------------------");
//
////			查詢全部
//			List<LiveVO> list = dao.getALL();
//			for (LiveVO liveVO5 : list) {
//				System.out.println(liveVO5.getHost_ID() + ",");
//				System.out.println(liveVO5.getLive_name() + ",");
//				System.out.println(liveVO5.getLive_acc() + ",");
//				System.out.println(liveVO5.getLive_pic() + ",");
//				System.out.println(liveVO5.getLive_date() + ",");
//				System.out.println(liveVO5.getLive_status());
//				System.out.println("----------------------------");
//			}
	}
}
