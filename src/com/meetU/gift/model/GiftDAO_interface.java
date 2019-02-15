package com.meetU.gift.model;

import java.util.List;

public interface GiftDAO_interface {
    public void insert(GiftVO giftVO);
    public void update(GiftVO giftVO);
    public void delete(String gift_rec_id);
    public GiftVO findByPrimaryKey(String gift_rec_id);
    public List<GiftVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
    //  public List<GiftVO> getAll(Map<String, String[]> map);
}
