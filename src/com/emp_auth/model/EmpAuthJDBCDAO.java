package com.emp_auth.model;

import java.sql.*;
import java.util.*;

public class EmpAuthJDBCDAO implements EmpAuthDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO EMPAUTH (EMP_ID, AUTH_ID) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM EMPAUTH";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM EMPAUTH where EMP_ID = ? AND AUTH_ID = ?";
	private static final String DELETE = 
		"DELETE FROM EMPAUTH where EMP_ID = ? AND AUTH_ID = ?";
	private static final String UPDATE = 
		"UPDATE EMPAUTH set AUTH_ID=? where EMP_ID=?";
	@Override
	public void insert(EmpAuthVO empAuthVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(EmpAuthVO empAuthVO) {
		
		
	}
	@Override
	public void delete(String emp_id, String auth_id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public EmpAuthVO findByPrimaryKey(String emp_id, String auth_id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<EmpAuthVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
