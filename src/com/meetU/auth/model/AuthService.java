package com.meetU.auth.model;

import java.util.*;

public class AuthService {
	
	private AuthDAO_interface dao;
	
	public AuthService() {
		dao = new AuthDAO();
	}
	public AuthVO addAuth(String auth_name) {
		AuthVO authVO = new AuthVO();
		
		authVO.setAuth_name(auth_name);
		
		dao.insert(authVO);
		
		return authVO;
	}

	public AuthVO updateAuth(String auth_ID, String auth_name) {
		
		AuthVO authVO = new AuthVO();
		
		authVO.setAuth_ID(auth_ID);
		authVO.setAuth_name(auth_name);
		
		dao.update(authVO);
		
		return authVO;
	}
	public void deleteAuth(String auth_ID) {
		dao.delete(auth_ID);
	}
	
	public AuthVO getOneAuth(String auth_ID) {
		return dao.findByPrimaryKey(auth_ID);
	}
	
	public List<AuthVO> getAll(){
		return dao.getAll();
	}
}
