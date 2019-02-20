package com.meetU.ad.model;

import java.util.List;

public interface AdDAO_interface {

	public void insert(AdVO adVO);

	public void update(AdVO adVO);

	public void delete(AdVO adVO);

	public AdVO findByPrimaryKey(String ad_ID);

	public List<AdVO> getALL();

//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<LiveVO> getAll(Map<String, String[]> map); 

}
