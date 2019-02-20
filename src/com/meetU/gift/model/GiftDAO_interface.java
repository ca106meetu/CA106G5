package com.meetU.gift.model;

import java.util.List;

public interface GiftDAO_interface {
    public void insert(GiftVO giftVO);
    public void update(GiftVO giftVO);
    public void delete(String gift_rec_ID);
    public GiftVO findByPrimaryKey(String gift_rec_ID);
    public List<GiftVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<GiftVO> getAll(Map<String, String[]> map);
}
