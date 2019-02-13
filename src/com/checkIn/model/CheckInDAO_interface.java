package com.checkIn.model;

import java.util.List;


public interface CheckInDAO_interface {
	public void insert(CheckInVO CheckInVO);
    public void update(CheckInVO CheckInVO);
    public void delete(String CheckIn_id);
    public CheckInVO findByPrimaryKey(String CheckIn_id);
    public List<CheckInVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<CheckInVO> getAll(Map<String, String[]> map); 
}
