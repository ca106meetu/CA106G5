package com.meetU.empAuth.model;

import java.util.*;


public class EmpAuthService {
	private EmpAuthDAO_interface dao;
	
	public EmpAuthService() {
		dao = new EmpAuthDAO();
	}
	public EmpAuthVO addEmpAuth(String emp_ID, String auth_ID) {
		EmpAuthVO empAuthVO = new EmpAuthVO();
		
		empAuthVO.setEmp_ID(emp_ID);
		empAuthVO.setAuth_ID(auth_ID);
		
		dao.insert(empAuthVO);
		
		return empAuthVO;
	}
	
	public EmpAuthVO updateEmpAuth(String emp_ID, String auth_ID) {
		EmpAuthVO empAuthVO = new EmpAuthVO();
		
		empAuthVO.setEmp_ID(emp_ID);
		empAuthVO.setAuth_ID(auth_ID);
		
		return empAuthVO;		
	}
	public List<EmpAuthVO> updateAllAuth(String emp_ID, List<EmpAuthVO> listEmpAuthVO){
		if((!listEmpAuthVO.isEmpty())&&(!emp_ID.trim().isEmpty())) {
			dao.deleteAuths(emp_ID);
			return dao.insertAuths(listEmpAuthVO);
		}else {
			return null;
		}
	}
	public void deleteEmpAuth(String emp_ID, String auth_ID) {
		dao.delete(emp_ID, auth_ID);
	}
	
	public List<EmpAuthVO> getPartOfOneEmpAuth(String emp_ID) {
		return dao.findByPartOfOnePrimaryKey(emp_ID);
	}
	
	public EmpAuthVO getOneEmpAuth(String emp_ID, String auth_ID) {
		return dao.findByPrimaryKey(emp_ID, auth_ID);
	}
	
	public List<EmpAuthVO> getAll(){
		return dao.getAll();
	}
	
}
