package com.meetU.giftbox.model;

import java.util.List;

public interface GiftboxDAO_interface {
    public void insert(GiftboxVO giftboxVO);
    public void update(GiftboxVO giftboxVO);
    public void delete(String mem_id);
    public GiftboxVO findByPrimaryKey(String mem_id);
    public List<GiftboxVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<GiftboxVO> getAll(Map<String, String[]> map);
}
