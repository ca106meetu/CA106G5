package com.meetU.memHobby.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meetU.empAuth.model.EmpAuthJDBCDAO;
import com.meetU.empAuth.model.EmpAuthVO;

public class MemHobbyJDBCDAO  implements MemHobbyDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";
	private static final String INSERT_STMT = 
		"INSERT INTO MEM_HOBBY (MEM_ID, HOBBY_ID) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM MEM_HOBBY";
	private static final String GET_PART_OF_ONE_STMT = 
		"SELECT * FROM MEM_HOBBY where MEM_ID = ? ";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM MEM_HOBBY where MEM_ID = ? AND HOBBY_ID = ?";
	private static final String DELETE = 
		"DELETE FROM MEM_HOBBY where MEM_ID = ? AND HOBBY_ID = ?";
	private static final String DELETE_HOBBYS = 
		"DELETE FROM MEM_HOBBY where MEM_ID = ?";
	private static final String UPDATE_STEP1 = 
		"SELECT * FROM MEM_HOBBY where MEM_ID = ?";
	private static final String UPDATE_STEP2 = 
		"DELETE FROM MEM_HOBBY where MEM_ID = ?";
	private static final String UPDATE_STEP3 = 
		"INSERT INTO MEM_HOBBY (MEM_ID, HOBBY_ID) VALUES (?, ?)";//??

	@Override
	public void insert(MemHobbyVO memHobbyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memHobbyVO.getMem_ID());
			pstmt.setString(2, memHobbyVO.getHobby_ID());
	
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
	public void update(MemHobbyVO memHobbyVO_old, MemHobbyVO memHobbyVO_new) {
		List<MemHobbyVO> list = new ArrayList<MemHobbyVO>();
		MemHobbyVO memHobbyVOtemp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STEP1);
			pstmt.setString(1, memHobbyVO_old.getMem_ID());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memHobbyVOtemp = new MemHobbyVO();
				memHobbyVOtemp.setMem_ID(rs.getString("mem_ID"));
				memHobbyVOtemp.setHobby_ID(rs.getString("hobby_ID"));
				list.add(memHobbyVOtemp);
			}
			
			for(MemHobbyVO memHobbyVO_each : list) {
				if(memHobbyVO_each.getHobby_ID().equals(memHobbyVO_old.getHobby_ID())) {
					memHobbyVO_each.setHobby_ID(memHobbyVO_new.getHobby_ID());
				}
			}
			
			pstmt = null;
			pstmt = con.prepareStatement(UPDATE_STEP2);
			pstmt.setString(1, memHobbyVO_old.getMem_ID());
			pstmt.executeUpdate();
			
			pstmt = null;
			for(MemHobbyVO memHobbyVO_each : list) {
				
				pstmt = con.prepareStatement(UPDATE_STEP3);
				pstmt.setString(1, memHobbyVO_each.getMem_ID());
				pstmt.setString(2, memHobbyVO_each.getHobby_ID());
				
				pstmt.executeUpdate();
			}
			
			
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
	public void delete(String mem_ID, String hobby_ID ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, mem_ID);
			pstmt.setString(2, hobby_ID);
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
	public void deleteHobbys(String mem_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_HOBBYS);
			
			pstmt.setString(1, mem_ID);
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
	public MemHobbyVO findByPrimaryKey(String mem_ID, String hobby_ID) {
		MemHobbyVO memHobbyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, mem_ID);
			pstmt.setString(2, hobby_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memHobbyVO = new MemHobbyVO();
				memHobbyVO.setMem_ID(rs.getString("mem_ID"));
				memHobbyVO.setHobby_ID(rs.getString("hobby_ID"));			
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
		return memHobbyVO;
	}
	@Override
	public List<MemHobbyVO> findByPartOfOnePrimaryKey(String mem_ID){
		List<MemHobbyVO> list = new ArrayList<>();
		MemHobbyVO memHobbyVO = null;
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
				memHobbyVO = new MemHobbyVO();
				memHobbyVO.setMem_ID(rs.getString("mem_ID"));
				memHobbyVO.setHobby_ID(rs.getString("hobby_ID"));
				
				list.add(memHobbyVO);
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
	public List<String> findByPartOfOnePrimaryKey2(String mem_ID) {
		List<String> list = new ArrayList<>();
		String auth_ID = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PART_OF_ONE_STMT);
			
			pstmt.setString(1, mem_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// MemHobbyVO 也稱為 Domain objects
				auth_ID = rs.getString("hobby_ID");
				
				list.add(auth_ID);
			}
			
			// Handle any driver errors
		}  catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} 
		catch (SQLException se) {
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
	
	@Override
	public List<MemHobbyVO> getAll() {
		List<MemHobbyVO> list = new ArrayList<>();
		MemHobbyVO memHobbyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memHobbyVO = new MemHobbyVO();
				memHobbyVO.setMem_ID(rs.getString("mem_ID"));
				memHobbyVO.setHobby_ID(rs.getString("hobby_ID"));
				
				list.add(memHobbyVO);
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
	public List<MemHobbyVO> insertHobbys(List<MemHobbyVO> listMemHobbyVO) {
		List<MemHobbyVO> listMemHobbyVO_new = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			for(MemHobbyVO memHobbyVO_each : listMemHobbyVO) {
				pstmt = null;
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, memHobbyVO_each.getMem_ID());
				pstmt.setString(2, memHobbyVO_each.getHobby_ID());
				
				pstmt.executeUpdate();
			}
			listMemHobbyVO_new.addAll(listMemHobbyVO);
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
		return listMemHobbyVO_new;
		
	}

	public static void main(String[] args) {
		
		MemHobbyJDBCDAO dao = new MemHobbyJDBCDAO();
		// 新增
//		MemHobbyVO memHobbyVO1 = new MemHobbyVO();
//		memHobbyVO1.setMem_ID("M000010");
//		memHobbyVO1.setHobby_ID("H00005");	
//		dao.insert(memHobbyVO1);
		
		//修改
		MemHobbyVO memHobbyVO2 = new MemHobbyVO();
//		memHobbyVO2.setMem_ID("M000002");	
//	    memHobbyVO2.setHobby_ID("H00010");
//	    dao.deleteHobbys(memHobbyVO2.getMem_ID());
	    
//	    List<MemHobbyVO> list = new ArrayList<>();
//	    Integer j;
//	    for(Integer i = 1; i < 6; i++) {
//	    	memHobbyVO2 = new MemHobbyVO();
//	    	memHobbyVO2.setMem_ID("M000002");
//	    	j = i ;
//	    	String s = j.toString(); 
//			memHobbyVO2.setHobby_ID("H0000"+s);
//			list.add(memHobbyVO2);
//		}	    
//	    dao.insertHobbys(list);
	    
//	    MemHobbyVO memHobbyVO3 = new MemHobbyVO();
//	    memHobbyVO3.setMem_ID("M000002");	
//	    memHobbyVO3.setHobby_ID("H00005");
//		dao.insert(memHobbyVO3);
		
//		//刪除
//		dao.delete("M000002", "H00005");
//		
//		//查詢1
//		MemHobbyVO memHobbyVO3 = dao.findByPrimaryKey("M000001","H00001");
//		
//		System.out.println(memHobbyVO3.getMem_ID() + ","); 
//		System.out.println(memHobbyVO3.getHobby_ID() + ","); 
//		System.out.println("----------------------------");
//		//查詢2
		List<String> list3 = dao.findByPartOfOnePrimaryKey2("M000001");
		for(String memHobbys_each : list3) {
			System.out.println(memHobbys_each + ","); 
			System.out.println("----------------------------");
		}
		
//		//查詢全
//		List<MemHobbyVO> list2 = dao.getAll();
//		
//		for(MemHobbyVO memHobbyVO4 : list2) {
//			System.out.println(memHobbyVO4.getMem_ID() + ","); 
//			System.out.println(memHobbyVO4.getHobby_ID() + ","); 
//			System.out.println("----------------------------");
//		}
		
	}

}
