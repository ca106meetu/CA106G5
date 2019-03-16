package com.meetU.memHobby.model;

import java.util.List;


public class MemHobbyService {
	private MemHobbyDAO_interface dao;
	
	public MemHobbyService() {
		dao = new MemHobbyDAO();
	}
	public MemHobbyVO addMemHobby(String mem_ID, String hobby_ID) {
		MemHobbyVO memHobbyVO = new MemHobbyVO();
		
		memHobbyVO.setMem_ID(mem_ID);
		memHobbyVO.setHobby_ID(hobby_ID);
		
		dao.insert(memHobbyVO);
		
		return memHobbyVO;
	}
	
	public MemHobbyVO updateMemHobby(String mem_ID, String hobby_ID) {
		MemHobbyVO memHobbyVO = new MemHobbyVO();
		
		memHobbyVO.setMem_ID(mem_ID);
		memHobbyVO.setHobby_ID(hobby_ID);
		
		return memHobbyVO;		
	}
	public List<MemHobbyVO> updateAllHobby(String mem_ID, List<MemHobbyVO> listMemHobbyVO){
		if((!listMemHobbyVO.isEmpty())&&(!mem_ID.trim().isEmpty())) {
			dao.deleteHobbys(mem_ID);
			return dao.insertHobbys(listMemHobbyVO);
		}else {
			return null;
		}
	}
	public void deleteMemHobby(String mem_ID, String hobby_ID) {
		dao.delete(mem_ID, hobby_ID);
	}
	
	public List<MemHobbyVO> getPartOfOneMemHobby(String mem_ID) {
		return dao.findByPartOfOnePrimaryKey(mem_ID);
	}
	public List<String> getPartOfOneMemHobby2(String mem_ID) {
		return dao.findByPartOfOnePrimaryKey2(mem_ID);
	}
	
	public MemHobbyVO getOneMemHobby(String mem_ID, String hobby_ID) {
		return dao.findByPrimaryKey(mem_ID, hobby_ID);
	}
	
	public List<MemHobbyVO> getAll(){
		return dao.getAll();
	}
}
