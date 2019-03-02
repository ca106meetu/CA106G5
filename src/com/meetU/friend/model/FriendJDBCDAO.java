package com.meetU.friend.model;

import java.sql.*;
import java.util.*;


public class FriendJDBCDAO implements FriendDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO FRIEND (MEM_ID, FRIEND_MEM_ID, RELATION_STATUS, FRIEND_INTIMATE) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM FRIEND";
	private static final String GET_PART_OF_ONE_STMT = 
		"SELECT * FROM FRIEND where MEM_ID = ? ";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM FRIEND where MEM_ID=? AND FRIEND_MEM_ID=?";
	private static final String DELETE = 
		"DELETE FROM FRIEND where MEM_ID=? AND FRIEND_MEM_ID=?";
	private static final String UPDATE = 
		"UPDATE FRIEND set RELATION_STATUS=?, FRIEND_INTIMATE=? where MEM_ID = ?AND FRIEND_MEM_ID=?";
	
	@Override
	public void insert(FriendVO friendVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, friendVO.getMem_ID());
			pstmt.setString(2, friendVO.getFriend_mem_ID());
			pstmt.setInt(3, friendVO.getRelation_status());
			pstmt.setInt(4, friendVO.getFriend_intimate());

			
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
	public void update(FriendVO friendVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, friendVO.getRelation_status());
			pstmt.setInt(2, friendVO.getFriend_intimate());
			pstmt.setString(3, friendVO.getMem_ID());
			pstmt.setString(4, friendVO.getFriend_mem_ID());

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
	public void delete(String mem_ID, String friend_mem_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, mem_ID);
			pstmt.setString(2, friend_mem_ID);
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
	public FriendVO findByPrimaryKey(String mem_ID, String friend_mem_ID) {
		FriendVO friendVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, mem_ID);
			pstmt.setString(2, friend_mem_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				friendVO = new FriendVO();
				friendVO.setMem_ID(rs.getString("mem_ID"));
				friendVO.setFriend_mem_ID(rs.getString("friend_mem_ID"));
				friendVO.setRelation_status(rs.getInt("relation_status"));
				friendVO.setFriend_intimate(rs.getInt("friend_intimate"));			
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
		return friendVO;
	}

	@Override
	public List<FriendVO> findByPartOfOnePrimaryKey(String mem_ID) {
		List<FriendVO> list = new ArrayList<>();
		FriendVO friendVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PART_OF_ONE_STMT);
			
			pstmt.setString(1, mem_ID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				friendVO = new FriendVO();
				friendVO.setMem_ID(rs.getString("mem_ID"));
				friendVO.setFriend_mem_ID(rs.getString("friend_mem_ID"));
				friendVO.setRelation_status(rs.getInt("relation_status"));
				friendVO.setFriend_intimate(rs.getInt("friend_intimate"));

				list.add(friendVO);
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
	
	@Override
	public List<FriendVO> getAll() {
		List<FriendVO> list = new ArrayList<>();
		FriendVO friendVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				friendVO = new FriendVO();
				friendVO.setMem_ID(rs.getString("mem_ID"));
				friendVO.setFriend_mem_ID(rs.getString("friend_mem_ID"));
				friendVO.setRelation_status(rs.getInt("relation_status"));
				friendVO.setFriend_intimate(rs.getInt("friend_intimate"));

				list.add(friendVO);
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
		
		FriendJDBCDAO dao = new FriendJDBCDAO();
		
		// 新增
		FriendVO friendVO1 = new FriendVO();
		friendVO1.setMem_ID("M000005");
		friendVO1.setFriend_mem_ID("M000001");
		friendVO1.setRelation_status(1);
		friendVO1.setFriend_intimate(10);
		dao.insert(friendVO1);
		
		//修改
		FriendVO friendVO2 = new FriendVO();
		friendVO2.setMem_ID("M000005");
		friendVO2.setFriend_mem_ID("M000002");
		friendVO2.setRelation_status(1);
		friendVO2.setFriend_intimate(100);
		dao.update(friendVO2);
		
		//刪除
		dao.delete("M000005","M000002");
		
		//查詢1
		FriendVO friendVO3 = dao.findByPrimaryKey("M000001", "M000004");
		System.out.println(friendVO3.getMem_ID() + ",");
		System.out.println(friendVO3.getFriend_mem_ID() + ",");
		System.out.println(friendVO3.getRelation_status() + ",");
		System.out.println(friendVO3.getFriend_intimate() + ",");
		System.out.println("----------------------------");
		
		//查詢全
		List<FriendVO> list = dao.getAll();
		for(FriendVO friendVO4 : list) {
			System.out.println(friendVO4.getMem_ID() + ",");
			System.out.println(friendVO4.getFriend_mem_ID() + ",");
			System.out.println(friendVO4.getRelation_status() + ",");
			System.out.println(friendVO4.getFriend_intimate() + ",");
			System.out.println("**----------------------------");
		}
		//查詢2
		List<FriendVO> list2 = dao.findByPartOfOnePrimaryKey("M000001");
		for(FriendVO friendVO5 : list2) {
			System.out.println(friendVO5.getMem_ID() + ",");
			System.out.println(friendVO5.getFriend_mem_ID() + ",");
			System.out.println(friendVO5.getRelation_status() + ",");
			System.out.println(friendVO5.getFriend_intimate() + ",");
			System.out.println("----------------------------");
		}
	}
    
}
