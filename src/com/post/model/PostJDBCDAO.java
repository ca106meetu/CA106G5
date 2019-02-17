package com.post.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.checkIn.model.CheckInJDBCDAO;


public class PostJDBCDAO implements PostDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO POST (POST_ID,POSTER_ID,MEM_ID,CHECK_IN_ID,POST_CONTENT,PUBLISH_TIME,POST_LIKE,POST_VISB) VALUES ('P'||LPAD(to_char(post_seq.NEXTVAL),6,'0'),?,?,?,?,CURRENT_TIMESTAMP,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT POST_ID,POSTER_ID,MEM_ID,CHECK_IN_ID,POST_CONTENT,PUBLISH_TIME,POST_LIKE,POST_VISB FROM POST ORDER BY PUBLISH_TIME DESC";
//	private static final String GET_ONE_STMT =
//			"SELECT POST_ID,POSTER_ID,MEM_ID,CHECK_IN_ID,POST_CONTENT,PUBLISH_TIME,POST_LIKE,POST_VISB FROM POST WHERE POST_ID = ?";
	private static final String DELETE =
			"DELETE FROM POST WHERE POST_ID = ?";
	private static final String UPDATE = 
			"UPDATE POST SET POST_CONTENT=?,POST_VISB=? WHERE POST_ID = ?";
	@Override
	public void insert(PostVO PostVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, PostVO.getPoster_ID());
			pstmt.setString(2, PostVO.getMem_ID());
			pstmt.setString(3, PostVO.getCheck_in_ID());
			pstmt.setString(4, PostVO.getPost_content());
			
			pstmt.setInt(5, PostVO.getPost_like());
			pstmt.setInt(6, PostVO.getPost_visb());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(PostVO PostVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, PostVO.getPost_content());
			pstmt.setInt(2, PostVO.getPost_visb());
			pstmt.setString(3, PostVO.getPost_ID());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, post_ID);
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

//	@Override
//	public PostVO findByPrimaryKey(String post_id) {
//		
//		return null;
//	}

	@Override
	public List<PostVO> getAll() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				postVO.setPublish_time(rs.getDate("PUBLISH_TIME"));
				postVO.setPost_like(rs.getInt("POST_LIKE"));
				postVO.setPost_visb(rs.getInt("POST_VISB"));
				
				list.add(postVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		
		//新增
//		PostVO postVO1 = new PostVO();
//		postVO1.setPoster_ID("M000002");
//		postVO1.setMem_ID("M000002");
//		postVO1.setCheck_in_ID(null);
//		postVO1.setPost_content("少年不識愁滋味，為賦新詞強說愁。 ");
//		postVO1.setPost_like(0);
//		postVO1.setPost_visb(2);
//		dao.insert(postVO1);

		
		//修改
//		PostVO postVO2 = new PostVO();
//		postVO2.setPost_ID("P000011");
//		postVO2.setPost_content("而今識盡愁滋味，卻道天涼好個秋。");
//		postVO2.setPost_visb(2);
//		dao.update(postVO2);
		
		//刪除
//		dao.delete("P000011");
		
		//查詢全部
		List<PostVO> list = dao.getAll();
		for(PostVO aPost : list) {
			System.out.print(aPost.getPost_ID()+",");
			System.out.print(aPost.getPoster_ID()+",");
			System.out.print(aPost.getMem_ID()+",");
			System.out.print(aPost.getCheck_in_ID()+",");
			System.out.print(aPost.getPost_content()+",");
			System.out.print(aPost.getPublish_time()+",");//timestamp處理
			System.out.print(aPost.getPost_like()+",");
			System.out.print(aPost.getPost_visb()+",");
			System.out.println();
		}
		
		
	}

}
