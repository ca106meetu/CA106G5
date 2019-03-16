package com.meetU.memHobby.model;

import java.util.List;

public interface MemHobbyDAO_interface {
	public void insert(MemHobbyVO MemHobbyVO);
    public void update(MemHobbyVO MemHobbyVO_old, MemHobbyVO MemHobbyVO_new);
    public void delete(String mem_ID,String hobby_ID);
    public void deleteHobbys(String mem_ID);
    public MemHobbyVO findByPrimaryKey(String mem_ID, String hobby_ID);
    
    public List<MemHobbyVO> findByPartOfOnePrimaryKey(String mem_ID);
    public List<String> findByPartOfOnePrimaryKey2(String mem_ID);
   
    public List<MemHobbyVO> getAll();
    public List<MemHobbyVO> insertHobbys(List<MemHobbyVO> listMemHobbyVO);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<MemHobbyVO> getAll(Map<String, String[]> map);
}
