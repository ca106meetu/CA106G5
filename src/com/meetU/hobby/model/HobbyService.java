package com.meetU.hobby.model;

import java.util.*;

public class HobbyService {
private HobbyDAO_interface dao;
	
	public HobbyService() {
		dao = new HobbyDAO();
	}
	public HobbyVO addHobby(String hobby_name) {
		HobbyVO hobbyVO = new HobbyVO();
		
		hobbyVO.setHobby_name(hobby_name);
		
		dao.insert(hobbyVO);
		
		return hobbyVO;
	}

	public HobbyVO updateHobby(String hobby_ID, String hobby_name) {
		
		HobbyVO hobbyVO = new HobbyVO();
		
		hobbyVO.setHobby_ID(hobby_ID);
		hobbyVO.setHobby_name(hobby_name);
		
		dao.update(hobbyVO);
		
		return hobbyVO;
	}
	public void deletehobby(String hobby_ID) {
		dao.delete(hobby_ID);
	}
	
	public HobbyVO getOneHobby(String hobby_ID) {
		return dao.findByPrimaryKey(hobby_ID);
	}
	
	public List<HobbyVO> getAll(){
		return dao.getAll();
	}
}
