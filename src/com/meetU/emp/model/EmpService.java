package com.meetU.emp.model;

import java.sql.*;
import java.util.*;

public class EmpService {
	
	private EmpDAO_interface dao;
	
	public EmpService() {
		dao = new EmpDAO();
	}
	public EmpVO addEmp(String emp_pw, String emp_name, java.sql.Date emp_bday,
			String emp_email, String emp_pho, String emp_gend, byte[] emp_pic,
			Integer emp_state, java.sql.Date emp_hday, String emp_address) {
		
		EmpVO empVO = new EmpVO();
		
		empVO.setEmp_pw(emp_pw);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_bday(emp_bday);
		empVO.setEmp_email(emp_email);
		empVO.setEmp_pho(emp_pho);
		empVO.setEmp_gend(emp_gend);
		empVO.setEmp_pic(emp_pic);
		empVO.setEmp_state(emp_state);
		empVO.setEmp_hday(emp_hday);
		empVO.setEmp_address(emp_address);
		
		dao.insert(empVO);
		
		return empVO;
	}
	
	public EmpVO updateEmp(String emp_pw, String emp_name, java.sql.Date emp_bday,
			String emp_email, String emp_pho, String emp_gend, byte[] emp_pic,
			Integer emp_state, java.sql.Date emp_hday, String emp_address, String emp_ID) {
		
		EmpVO empVO = new EmpVO();
		empVO.setEmp_pw(emp_pw);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_bday(emp_bday);
		empVO.setEmp_email(emp_email);
		empVO.setEmp_pho(emp_pho);
		empVO.setEmp_gend(emp_gend);
		empVO.setEmp_pic(emp_pic);
		empVO.setEmp_state(emp_state);
		empVO.setEmp_hday(emp_hday);
		empVO.setEmp_address(emp_address);
		empVO.setEmp_ID(emp_ID);
	
		dao.update(empVO);
		
		return empVO;
	}
	
	public void deleteEmp(String emp_ID) {
		dao.delete(emp_ID);
	}
	
	public EmpVO getOneEmp(String emp_ID) {
		return dao.findByPrimaryKey(emp_ID);
	}
	
	public List<EmpVO> getAll() {
		return dao.getAll();
	}
}
