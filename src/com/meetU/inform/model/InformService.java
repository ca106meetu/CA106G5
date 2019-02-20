package com.meetU.inform.model;

import java.sql.*;
import java.util.*;

public class InformService {

	public InformDAO_interface dao;
	
	public InformService() {
		dao = new InformDAO();
	}
	
	public InformVO addInform(Integer inform_status, String mem_ID, String inform_content) {
		
		InformVO informVO = new InformVO();
		
		informVO.setInform_status(inform_status);
		informVO.setMem_ID(mem_ID);
		informVO.setInform_content(inform_content);
		
		dao.insert(informVO);
		
		return informVO;
	}
	
	public InformVO updateInform(Integer inform_status, String mem_ID, String inform_content, String inform_ID) {
		
		InformVO informVO = new InformVO();
		informVO.setInform_status(inform_status);
		informVO.setMem_ID(mem_ID);
		informVO.setInform_content(inform_content);
		informVO.setInform_ID(inform_ID);
		
		dao.update(informVO);
		
		return informVO;
	}
	
	public void deleteInform(String inform_ID) {
		dao.delete(inform_ID);
	}
	
	public InformVO getOneInform(String inform_ID) {
		return dao.findByPrimaryKey(inform_ID);
	}
	
}
