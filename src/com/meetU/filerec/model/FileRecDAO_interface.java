package com.meetU.filerec.model;

import java.util.*;

public interface FileRecDAO_interface {

	public void insert(Live_likeVO filerecVO);

	public void update(Live_likeVO filerecVO);

	public void delete(Live_likeVO filerecVO);

	public Live_likeVO findByPrimaryKey(String file_ID);

	public List<Live_likeVO> getALL();

//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<LiveVO> getAll(Map<String, String[]> map); 

}
