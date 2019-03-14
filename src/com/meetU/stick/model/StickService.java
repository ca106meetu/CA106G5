package com.meetU.stick.model;

import java.util.List;

public class StickService {

	private StickDAO_interface dao;

	public StickService() {
		dao = new StickDAO();
	}

//	條件查詢
	public StickVO getOneStick(String stick_ID) {
		StickVO stickVO = dao.findByPrimaryKey(stick_ID);
		return stickVO;
	}

//	查詢全部
	public List<StickVO> getAll() {
		List<StickVO> list = dao.getALL();
		return list;

	}

}
