package com.meetU.live_chat.model;

import java.util.List;

public interface Live_chatDAO_interface {

	public void insert(Live_chatVO live_chatVO);

//	public void update(Live_chatVO live_chatVO);

//	public void delete(Live_chatVO live_chatVO);

	public List<Live_chatVO> findByPrimaryKey(String host_ID);

	public List<Live_chatVO> getALL();

//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<LiveVO> getAll(Map<String, String[]> map); 

}
