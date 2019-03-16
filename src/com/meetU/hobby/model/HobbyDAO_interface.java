package com.meetU.hobby.model;

import java.util.List;

public interface HobbyDAO_interface {
	public void insert(HobbyVO hobbyVO);
    public void update(HobbyVO hobbyVO);
    public void delete(String hobby_ID);
    public HobbyVO findByPrimaryKey(String hobby_ID);
    public List<HobbyVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<HobbyVO> getAll(Map<String, String[]> map);
}
