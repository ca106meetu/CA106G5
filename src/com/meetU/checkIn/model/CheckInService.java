package com.meetU.checkIn.model;

import java.sql.Timestamp;
import java.util.List;


public class CheckInService {
private CheckInDAO_interface dao;
	
	public CheckInService() {
		dao = new CheckInDAO();
	}
	
	public CheckInVO addCheckIn(String check_in_name,Double check_in_lat,Double check_in_lnt) {
		
		CheckInVO checkInVO = new CheckInVO();
		
		checkInVO.setCheck_in_name(check_in_name);
		checkInVO.setCheck_in_lat(check_in_lat);
		checkInVO.setCheck_in_lnt(check_in_lnt);
		dao.insert(checkInVO);
		
		return checkInVO;
	}
	
	public CheckInVO updateCheckIn(String check_in_ID,String check_in_name,Double check_in_lat,Double check_in_lnt) {
		
		CheckInVO checkInVO = new CheckInVO();
		
		checkInVO.setCheck_in_ID(check_in_ID);
		checkInVO.setCheck_in_name(check_in_name);
		checkInVO.setCheck_in_lat(check_in_lat);
		checkInVO.setCheck_in_lnt(check_in_lnt);
		
		dao.update(checkInVO);
		
		return checkInVO;
	}
	
	public CheckInVO getOneCheckIn(String check_in_ID) {
		return dao.findByPrimaryKey(check_in_ID);
	}
	
	public void deleteCheckIn(String check_in_ID) {
		dao.delete(check_in_ID);
	}
	
	public List<CheckInVO> getAll() {
		return dao.getAll();
	}
}
