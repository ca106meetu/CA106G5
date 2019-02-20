package com.meetU.live_rep.model;

import java.util.List;

public interface Live_repDAO_interface {

	public void insert(Live_repVO live_repVO);

	public void update(Live_repVO live_repVO);

	public void delete(Live_repVO live_repVO);

	public Live_repVO findByPrimaryKey(String rep_ID);

	public List<Live_repVO> getALL();

//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<LiveVO> getAll(Map<String, String[]> map); 

}
