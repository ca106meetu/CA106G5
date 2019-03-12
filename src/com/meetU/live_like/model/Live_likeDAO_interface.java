package com.meetU.live_like.model;

import java.util.*;

public interface Live_likeDAO_interface {

	public void insert(Live_likeVO live_likeVO);

//	public void update(Live_likeVO live_likeVO);  收藏不能修改,直接刪除再新增

	public void delete(Live_likeVO live_likeVO);

	public List<Live_likeVO> findByPrimaryKey(String mem_ID);
	
	public Live_likeVO findByPrimaryKey(String mem_ID,String host_ID);

	public List<Live_likeVO> getALL();

//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<LiveVO> getAll(Map<String, String[]> map); 

}