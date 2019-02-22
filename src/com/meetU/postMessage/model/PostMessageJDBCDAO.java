package com.meetU.postMessage.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meetU.post.model.PostJDBCDAO;
import com.meetU.post.model.PostVO;

public class PostMessageJDBCDAO implements PostMessageDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO POST_MESSAGE (MSG_ID,POST_ID,MEM_ID,MSG_CONTENT,PUBLISH_TIME,MSG_LIKE) VALUES ('PM'||LPAD(to_char(post_seq.NEXTVAL),6,'0'),?,?,?,?,0)";
	private static final String GET_ALL_STMT =
			"SELECT MSG_ID,POST_ID,MEM_ID,MSG_CONTENT,PUBLISH_TIME,MSG_LIKE FROM POST_MESSAGE ORDER BY MSG_ID";
	private static final String GET_ONE_STMT =
			"SELECT MSG_ID,MSG_ID,POST_ID,MEM_ID,MSG_CONTENT,PUBLISH_TIME,MSG_LIKE FROM POST_MESSAGE WHERE MSG_ID = ?";
	private static final String DELETE =
			"DELETE FROM POST_MESSAGE WHERE MSG_ID = ?";
	private static final String UPDATE = 
			"UPDATE POST_MESSAGE SET POST_ID=?,MEM_ID=?,MSG_CONTENT=?,PUBLISH_TIME=?,MSG_LIKE=? WHERE MSG_ID = ?";

	@Override
	public void insert(PostMessageVO postMessageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, postMessageVO.getPost_ID());
			pstmt.setString(2, postMessageVO.getMem_ID());
			pstmt.setString(3, postMessageVO.getMsg_content());
			pstmt.setTimestamp(4, postMessageVO.getPublish_time());
			
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
	public void update(PostMessageVO postMessageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,postMessageVO.getPost_ID());
			pstmt.setString(2,postMessageVO.getMem_ID());
			pstmt.setString(3, postMessageVO.getMsg_content());
			pstmt.setTimestamp(4, postMessageVO.getPublish_time());
			pstmt.setInt(5, postMessageVO.getMsg_like());
			pstmt.setString(6, postMessageVO.getMsg_ID());
			
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
	public void delete(String msg_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, msg_ID);
			
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
	public PostMessageVO findByPrimaryKey(String msg_ID) {
		PostMessageVO postMessageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, msg_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				postMessageVO = new PostMessageVO();
				postMessageVO.setMsg_ID(rs.getString("MSG_ID"));
				postMessageVO.setPost_ID(rs.getString("POST_ID"));
				postMessageVO.setMem_ID(rs.getString("MEM_ID"));
				postMessageVO.setMsg_content(rs.getString("MSG_CONTENT"));
				postMessageVO.setPublish_time(rs.getTimestamp("PUBLISH_TIME"));
				postMessageVO.setMsg_like(rs.getInt("MSG_LIKE"));
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
		return postMessageVO;
	}

	@Override
	public List<PostMessageVO> getAll() {
		List<PostMessageVO> list = new ArrayList<PostMessageVO>();
		PostMessageVO postMessageVO = null;
		
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
				postMessageVO = new PostMessageVO();
				postMessageVO.setMsg_ID(rs.getString("MSG_ID"));
				postMessageVO.setPost_ID(rs.getString("POST_ID"));
				postMessageVO.setMem_ID(rs.getString("MEM_ID"));
				postMessageVO.setMsg_content(rs.getString("MSG_CONTENT"));
				postMessageVO.setPublish_time(rs.getTimestamp("PUBLISH_TIME"));
				postMessageVO.setMsg_like(rs.getInt("MSG_LIKE"));
				
				list.add(postMessageVO); // Store the row in the list
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
		PostMessageJDBCDAO dao = new PostMessageJDBCDAO();
		java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		
		//新增
//		PostMessageVO postMessageVO1 = new PostMessageVO();
//		postMessageVO1.setPost_ID("P000002");
//		postMessageVO1.setMem_ID("M000002");
//		postMessageVO1.setMsg_content("你好~~~ ");
//		postMessageVO1.setPublish_time(ts);
//		postMessageVO1.setMsg_like(0);
//		dao.insert(postMessageVO1);

		
		//修改
//		PostMessageVO postMessageVO2 = new PostMessageVO();
//		
//		postMessageVO2.setMsg_ID("PM000001");
//		postMessageVO2.setPost_ID("P000001");
//		postMessageVO2.setMem_ID("M000001");
//		postMessageVO2.setMsg_content("而今識盡愁滋味");
//		postMessageVO2.setPublish_time(ts);
//		postMessageVO2.setMsg_like(0);
//		dao.update(postMessageVO2);
		
		//刪除
//		dao.delete("PM000011");
		
		//查一筆
//		PostMessageVO PostMessageVO3 = new PostMessageVO();
//		PostMessageVO3 = dao.findByPrimaryKey("PM000001");
//		System.out.print(PostMessageVO3.getMsg_ID()+",");
//		System.out.print(PostMessageVO3.getPost_ID()+",");
//		System.out.print(PostMessageVO3.getMem_ID()+",");
//		System.out.print(PostMessageVO3.getMsg_content()+",");
//		System.out.print(PostMessageVO3.getPublish_time()+",");
//		System.out.print(PostMessageVO3.getMsg_like());
//		System.out.println();
		
		
		//查全部
		List<PostMessageVO> list = dao.getAll();
		for(PostMessageVO aPost : list) {
			System.out.print(aPost.getMsg_ID()+",");
			System.out.print(aPost.getPost_ID()+",");
			System.out.print(aPost.getMem_ID()+",");
			System.out.print(aPost.getMsg_content()+",");
			System.out.print(aPost.getPublish_time()+",");
			System.out.print(aPost.getMsg_like());
			System.out.println();
		}
	}

}
