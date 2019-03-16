package com.meetU.memHobby.model;

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



public class MemHobbyDAO implements MemHobbyDAO_interface {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memHobbyVO.getMem_ID());
			pstmt.setString(2, memHobbyVO.getHobby_ID());

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
	public void update(MemHobbyVO memHobbyVO_old, MemHobbyVO memHobbyVO_new) {
		List<MemHobbyVO> list = new ArrayList<MemHobbyVO>();
		MemHobbyVO memHobbyVOtemp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
			
			
			for(MemHobbyVO memHobbyVO_each : list) {
				pstmt = null;
				pstmt = con.prepareStatement(UPDATE_STEP3);
				pstmt.setString(1, memHobbyVO_each.getMem_ID());
				pstmt.setString(2, memHobbyVO_each.getHobby_ID());
				
				pstmt.executeUpdate();
			}
			
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
	public void delete(String mem_ID, String hobby_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_ID);
			pstmt.setString(2, hobby_ID);

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
	public void deleteHobbys(String mem_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_HOBBYS);

			pstmt.setString(1, mem_ID);
			
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
	public List<MemHobbyVO> findByPartOfOnePrimaryKey(String mem_ID) {
		List<MemHobbyVO> list = new ArrayList<>();
		MemHobbyVO memHobbyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PART_OF_ONE_STMT);

			pstmt.setString(1, mem_ID);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemHobbyVO 也稱為 Domain objects
				memHobbyVO = new MemHobbyVO();
				memHobbyVO.setMem_ID(rs.getString("mem_ID"));
				memHobbyVO.setHobby_ID(rs.getString("hobby_ID"));
				
				list.add(memHobbyVO);
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
	@Override
	public List<String> findByPartOfOnePrimaryKey2(String mem_ID) {
		List<String> list = new ArrayList<>();
		String hobby_ID = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PART_OF_ONE_STMT);
			
			pstmt.setString(1, mem_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// MemHobbyVO 也稱為 Domain objects
				hobby_ID = rs.getString("hobby_ID");
				list.add(hobby_ID);
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
	
	@Override
	public MemHobbyVO findByPrimaryKey(String mem_ID, String hobby_ID) {
		MemHobbyVO memHobbyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_ID);
			pstmt.setString(2, hobby_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemHobbyVO 也稱為 Domain objects
				memHobbyVO = new MemHobbyVO();
				memHobbyVO.setMem_ID(rs.getString("mem_ID"));
				memHobbyVO.setHobby_ID(rs.getString("hobby_ID"));
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
		return memHobbyVO;
	}
	
	@Override
	public List<MemHobbyVO> getAll() {
		List<MemHobbyVO> list = new ArrayList<MemHobbyVO>();
		MemHobbyVO memHobbyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemHobbyVO 也稱為 Domain objects
				memHobbyVO = new MemHobbyVO();
				memHobbyVO.setMem_ID(rs.getString("Mem_ID"));
				memHobbyVO.setHobby_ID(rs.getString("hobby_ID"));

				list.add(memHobbyVO);
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
	@Override
	public List<MemHobbyVO> insertHobbys(List<MemHobbyVO> listMemHobbyVO) {
		List<MemHobbyVO> listMemHobbyVO_new = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
						
			
			for(MemHobbyVO memHobbyVO_each : listMemHobbyVO) {
				pstmt = null;
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, memHobbyVO_each.getMem_ID());
				pstmt.setString(2, memHobbyVO_each.getHobby_ID());
				
				pstmt.executeUpdate();
			}
			listMemHobbyVO_new.addAll(listMemHobbyVO);
			
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
		return listMemHobbyVO_new;
		
	}
}
