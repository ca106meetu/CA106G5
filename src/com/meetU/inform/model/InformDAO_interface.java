package com.meetU.inform.model;

import java.util.List;

public interface InformDAO_interface {
    public void insert(InformVO informVO);
    public void update(InformVO informVO);
    public void delete(String inform_id);
    public InformVO findByPrimaryKey(String inform_id);
    public List<InformVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<InformVO> getAll(Map<String, String[]> map); 
}
