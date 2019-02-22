package com.meetU.post.model;

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


public class PostDAO implements PostDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	
	private static final String INSERT_STMT = 
			"INSERT INTO POST (POST_ID,POSTER_ID,MEM_ID,CHECK_IN_ID,POST_CONTENT,PUBLISH_TIME,POST_LIKE,POST_VISB) VALUES ('P'||LPAD(to_char(post_seq.NEXTVAL),6,'0'),?,?,?,?,?,0,?)";
	private static final String GET_ALL_STMT =
			"SELECT POST_ID,POSTER_ID,MEM_ID,CHECK_IN_ID,POST_CONTENT,PUBLISH_TIME,POST_LIKE,POST_VISB FROM POST ORDER BY POST_ID";
	private static final String GET_ONE_STMT =
			"SELECT POST_ID,POSTER_ID,MEM_ID,CHECK_IN_ID,POST_CONTENT,PUBLISH_TIME,POST_LIKE,POST_VISB FROM POST WHERE POST_ID = ?";
	private static final String DELETE =
			"DELETE FROM POST WHERE POST_ID = ?";
	private static final String UPDATE = 
			"UPDATE POST SET POSTER_ID=?,MEM_ID=?,CHECK_IN_ID=?,POST_CONTENT=?,PUBLISH_TIME=?,POST_LIKE=?,POST_VISB=? WHERE POST_ID = ?";
	@Override
	public void insert(PostVO postVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, postVO.getPoster_ID());
			pstmt.setString(2, postVO.getMem_ID());
			pstmt.setString(3, postVO.getCheck_in_ID());
			pstmt.setString(4, postVO.getPost_content());
			pstmt.setTimestamp(5, postVO.getPublish_time());
			pstmt.setInt(6, postVO.getPost_visb());
			
			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch(SQLException se) {
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
	public void update(PostVO postVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,postVO.getPoster_ID());
			pstmt.setString(2,postVO.getMem_ID());
			pstmt.setString(3, postVO.getCheck_in_ID());
			pstmt.setString(4, postVO.getPost_content());
			pstmt.setTimestamp(5, postVO.getPublish_time());
			pstmt.setInt(6, postVO.getPost_like());
			pstmt.setInt(7, postVO.getPost_visb());
			pstmt.setString(8, postVO.getPost_ID());
			
			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch(SQLException se) {
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
	public void delete(String post_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, post_ID);
			
			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch(SQLException se) {
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
	public PostVO findByPrimaryKey(String post_ID) {
		
		PostVO postVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, post_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				postVO = new PostVO();
				postVO.setPost_ID(rs.getString("POST_ID"));
				postVO.setPoster_ID(rs.getString("POSTER_ID"));
				postVO.setMem_ID(rs.getString("MEM_ID"));
				postVO.setCheck_in_ID(rs.getString("CHECK_IN_ID"));
				postVO.setPost_content(rs.getString("POST_CONTENT"));
				postVO.setPublish_time(rs.getTimestamp("PUBLISH_TIME"));
				postVO.setPost_like(rs.getInt("POST_LIKE"));
				postVO.setPost_visb(rs.getInt("POST_VISB"));
			}

			// Handle any SQL errors
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
		return postVO;
	}

	@Override
	public List<PostVO> getAll() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				postVO = new PostVO();
				postVO.setPost_ID(rs.getString("POST_ID"));
				postVO.setPoster_ID(rs.getString("POSTER_ID"));
				postVO.setMem_ID(rs.getString("MEM_ID"));
				postVO.setCheck_in_ID(rs.getString("CHECK_IN_ID"));
				postVO.setPost_content(rs.getString("POST_CONTENT"));
				postVO.setPublish_time(rs.getTimestamp("PUBLISH_TIME"));
				postVO.setPost_like(rs.getInt("POST_LIKE"));
				postVO.setPost_visb(rs.getInt("POST_VISB"));
				
				list.add(postVO); // Store the row in the list
			}

			// Handle any SQL errors
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
	
	public static void main(String[] args) {
		PostJDBCDAO dao = new PostJDBCDAO();
		java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		//新增
//		PostVO postVO1 = new PostVO();
//		postVO1.setPoster_ID("M000002");
//		postVO1.setMem_ID("M000002");
//		postVO1.setCheck_in_ID(null);
//		postVO1.setPost_content("少年不識愁滋味，為賦新詞強說愁。 ");
//		postVO1.setPublish_time(ts);
//		postVO1.setPost_like(0);
//		postVO1.setPost_visb(2);
//		dao.insert(postVO1);

		
		//修改
		PostVO postVO2 = new PostVO();
		
		postVO2.setPost_ID("P000001");
		postVO2.setPoster_ID("M000001");
		postVO2.setMem_ID("M000001");
		postVO2.setCheck_in_ID(null);
		postVO2.setPost_content("而今識盡愁滋味");
		postVO2.setPublish_time(ts);
		postVO2.setPost_like(0);
		postVO2.setPost_visb(2);
		dao.update(postVO2);
		
		//刪除
//		dao.delete("P000011");
		
		//查一筆
		PostVO postVO3 = new PostVO();
		postVO3 = dao.findByPrimaryKey("P000002");
		System.out.print(postVO3.getPost_ID()+",");
		System.out.print(postVO3.getPoster_ID()+",");
		System.out.print(postVO3.getMem_ID()+",");
		System.out.print(postVO3.getCheck_in_ID()+",");
		System.out.print(postVO3.getPost_content()+",");
		System.out.print(postVO3.getPublish_time()+",");
		System.out.print(postVO3.getPost_like()+",");
		System.out.print(postVO3.getPost_visb()+",");
		System.out.println();
		
		
		//查全部
		List<PostVO> list = dao.getAll();
		for(PostVO aPost : list) {
			System.out.print(aPost.getPost_ID()+",");
			System.out.print(aPost.getPoster_ID()+",");
			System.out.print(aPost.getMem_ID()+",");
			System.out.print(aPost.getCheck_in_ID()+",");
			System.out.print(aPost.getPost_content()+",");
			System.out.print(aPost.getPublish_time()+",");
			System.out.print(aPost.getPost_like()+",");
			System.out.print(aPost.getPost_visb()+",");
			System.out.println();
		}
		
		
	}

}
