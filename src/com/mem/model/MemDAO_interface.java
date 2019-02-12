package com.mem.model;

import java.util.List;

public interface MemDAO_interface {
    public void insert(MemVO memVO);
    public void update(MemVO memVO);
    public void delete(String mem_id);
    public MemVO findByPrimaryKey(String mem_id);
    public List<MemVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<MemVO> getAll(Map<String, String[]> map); 
}
