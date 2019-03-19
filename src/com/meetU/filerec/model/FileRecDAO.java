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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FileRecDAO implements FileRecDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO FILEREC (FILE_ID, HOST_ID, FILE_NAME, LIVE_DES, FILE_CONT, FILE_DATE, FILE_POP) VALUES ('FM'||LPAD(to_char(filer_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE FILEREC set FILE_NAME = ?, LIVE_DES = ?, FILE_CONT = ?, FILE_POP = ?  where FILE_ID = ?";
	private static final String DELETE = "DELETE FROM FILEREC where FILE_ID = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM FILEREC where HOST_ID = ?";
	private static final String GET_ONE_STMT2 = "SELECT * FROM FILEREC where HOST_ID = ? and FILE_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM FILEREC";

	public FileRecDAO() {

	}

	@Override
	public void insert(FileRecVO filerecVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, filerecVO.getHost_ID());
			pstmt.setString(2, filerecVO.getFile_name());
			pstmt.setString(3, filerecVO.getLive_des());
			pstmt.setString(4, filerecVO.getFile_cont());
			pstmt.setTimestamp(5, filerecVO.getFile_date());
			pstmt.setInt(6, filerecVO.getFile_pop());

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
	public void update(FileRecVO filerecVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, filerecVO.getFile_name());
			pstmt.setString(2, filerecVO.getLive_des());
			pstmt.setString(3, filerecVO.getFile_cont());
			pstmt.setInt(4, filerecVO.getFile_pop());
			pstmt.setString(5, filerecVO.getFile_ID());

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
	public void delete(FileRecVO filerecVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, filerecVO.getFile_ID());

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
	public List<FileRecVO> findByPrimaryKey(String host_ID) {
		List<FileRecVO> list = new ArrayList<>();
		FileRecVO filerecVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, host_ID);

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
	public FileRecVO findByPrimaryKey(String file_ID,String host_ID) {

		FileRecVO filerecVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT2);
			pstmt.setString(1, host_ID);
			pstmt.setString(2, file_ID);
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
			con = ds.getConnection();
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
