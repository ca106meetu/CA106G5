package com.meetU.stick.model;

import java.util.List;

public interface StickDAO_interface {
	
	//public void insert(LiveVO liveVO);

	//public void update(LiveVO liveVO);

	//public void delete(LiveVO liveVO);

	public StickVO findByPrimaryKey(String stick_ID);

	public List<StickVO> getALL();

	//萬用複合查詢(傳入參數型態Map)(回傳 List)
	//public List<LiveVO> getAll(Map<String, String[]> map); 

}
