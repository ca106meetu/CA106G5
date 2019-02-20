package com.meetU.inform.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InformJDBCDAO implements InformDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO INFORM (INFORM_ID, INFORM_STATUS, MEM_ID, INFORM_CONTENT, INFORM_TIME) VALUES ('INF'||LPAD(to_char(inform_seq.NEXTVAL), 6, '0'), ?, ?, ?, current_timestamp)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM INFORM";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM INFORM where INFORM_ID = ?";
	private static final String DELETE = 
		"DELETE FROM INFORM where INFORM_ID = ?";
	private static final String UPDATE = 
		"UPDATE INFORM set INFORM_STATUS=?, MEM_ID=?, INFORM_CONTENT=?, INFORM_TIME=current_timestamp where INFORM_ID = ?";
	
	@Override
	public void insert(InformVO informVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, informVO.getInform_status());
			pstmt.setString(2, informVO.getMem_ID());
			pstmt.setString(3, informVO.getInform_content());
	
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(InformVO informVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, informVO.getInform_status());
			pstmt.setString(2, informVO.getMem_ID());
			pstmt.setString(3, informVO.getInform_content());
			pstmt.setString(4, informVO.getInform_ID());
	
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String inform_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, inform_ID);
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public InformVO findByPrimaryKey(String inform_ID) {
		InformVO informVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, inform_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				informVO = new InformVO();
				informVO.setInform_ID(rs.getString("inform_ID"));
				informVO.setInform_status(rs.getInt("inform_status"));
				informVO.setMem_ID(rs.getString("mem_ID"));
				informVO.setInform_content(rs.getString("inform_content"));
				informVO.setInform_time(rs.getTimestamp("inform_time"));
			}
						
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return informVO;
	}

	@Override
	public List<InformVO> getAll() {
		List<InformVO> list = new ArrayList<>();
		InformVO informVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				informVO = new InformVO();
				informVO.setInform_ID(rs.getString("inform_ID"));
				informVO.setInform_status(rs.getInt("inform_status"));
				informVO.setMem_ID(rs.getString("mem_ID"));
				informVO.setInform_content(rs.getString("inform_content"));
				informVO.setInform_time(rs.getTimestamp("inform_time"));
				
				list.add(informVO);
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public static void main(String[] args) {
		
		InformJDBCDAO dao = new InformJDBCDAO();
		
		// 新增
		InformVO informVO1 = new InformVO();
		informVO1.setMem_ID("M000003");
		informVO1.setInform_status(3);
		informVO1.setInform_content("疼疼疼疼疼疼疼疼疼疼");
		dao.insert(informVO1);
		
		//修改
		InformVO informVO2 = new InformVO();
		informVO2.setInform_ID("INF000002");
		informVO2.setMem_ID("M000003");
		informVO2.setInform_status(4);
		informVO2.setInform_content("疼疼寫輪眼");
		dao.update(informVO2);
		
		//刪除
		dao.delete("INF000006");
		
		//查詢1
		InformVO informVO3 = dao.findByPrimaryKey("INF000001");
		System.out.println(informVO3.getInform_ID() + ",");
		System.out.println(informVO3.getInform_status() + ",");
		System.out.println(informVO3.getMem_ID() + ",");
		System.out.println(informVO3.getInform_content() + ",");
		System.out.println(informVO3.getInform_time() + ",");
		System.out.println("----------------------------");
		
		//查詢全
		List<InformVO> list = dao.getAll();
		for( InformVO informVO4 : list) {
			System.out.println(informVO4.getInform_ID() + ",");
			System.out.println(informVO4.getInform_status() + ",");
			System.out.println(informVO4.getMem_ID() + ",");
			System.out.println(informVO4.getInform_content() + ",");
			System.out.println(informVO4.getInform_time() + ",");
			System.out.println("----------------------------");
		}
	}
}
