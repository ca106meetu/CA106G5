package com.meetU.pointRec.model;

import java.sql.Timestamp;
import java.util.List;


public class PrService {
	
	PointRecDAO_interface dao;
	
	public PrService() {
		dao = new PointRecDAO();
	}
	
	
	public PointRecVO addRc(String mem_ID, Double amount, Timestamp rec_date) {
		
		PointRecVO prVO = new PointRecVO();
		
		prVO.setMem_ID(mem_ID);
		prVO.setAmount(amount);
		prVO.setRec_date(rec_date);
		dao.insert(prVO);
		
		return prVO;
	}
	
	public PointRecVO updateRc(String rec_ID, String mem_ID, Double amount, Timestamp rec_date) {
		
		PointRecVO prVO = new PointRecVO();
		
		prVO.setRec_ID(rec_ID);
		prVO.setMem_ID(mem_ID);
		prVO.setAmount(amount);
		prVO.setRec_date(rec_date);
		dao.update(prVO);
		
		return prVO;
	}
	
	
	public void deletePr(String rec_ID) {
		dao.delete(rec_ID);
	}
	
	public PointRecVO getOnePr(String rec_ID) {
		return dao.findByPrimaryKey(rec_ID);
	}
	
	public List<PointRecVO> getAll(){
		return dao.getAll();
	}
	
	public List<PointRecVO> getPrByMem(String mem_ID){
		return dao.getPrByMem(mem_ID);
	} 
	
	
	

}
