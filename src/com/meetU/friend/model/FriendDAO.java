package com.meetU.friend.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class FriendDAO implements FriendDAO_interface{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA106G5DB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		private static final String INSERT_STMT = 
			"INSERT INTO FRIEND (MEM_ID, FRIEND_MEM_ID, RELATION_STATUS, FRIEND_INTIMATE) VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM FRIEND";
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, friendVO.getMem_ID());
				pstmt.setString(2, friendVO.getFriend_mem_ID());
				pstmt.setInt(3, friendVO.getRelation_status());
				pstmt.setInt(4, friendVO.getFriend_intimate());

				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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
		public void update(FriendVO friendVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, friendVO.getRelation_status());
				pstmt.setInt(2, friendVO.getFriend_intimate());
				pstmt.setString(3, friendVO.getMem_ID());
				pstmt.setString(4, friendVO.getFriend_mem_ID());

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, mem_ID);
				pstmt.setString(2, friend_mem_ID);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, mem_ID);
				pstmt.setString(2, friend_mem_ID);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// informVO 也稱為 Domain objects
					friendVO = new FriendVO();
					friendVO.setMem_ID(rs.getString("mem_ID"));
					friendVO.setFriend_mem_ID(rs.getString("friend_mem_ID"));
					friendVO.setRelation_status(rs.getInt("relation_status"));
					friendVO.setFriend_intimate(rs.getInt("friend_intimate"));			
				
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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
		public List<FriendVO> getAll() {
			List<FriendVO> list = new ArrayList<FriendVO>();
			FriendVO friendVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// friendVO 也稱為 Domain objects
					friendVO = new FriendVO();
					friendVO.setMem_ID(rs.getString("mem_ID"));
					friendVO.setFriend_mem_ID(rs.getString("friend_mem_ID"));
					friendVO.setRelation_status(rs.getInt("relation_status"));
					friendVO.setFriend_intimate(rs.getInt("friend_intimate"));

					list.add(friendVO);
					// Store the row in the list
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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
