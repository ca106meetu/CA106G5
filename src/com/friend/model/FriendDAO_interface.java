package com.friend.model;

import java.util.List;

public interface FriendDAO_interface {
    public void insert(FriendVO friendVO);
    public void update(FriendVO friendVO);
    public void delete(String mem_id);
    public FriendVO findByPrimaryKey(String mem_id);
    public List<FriendVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
    //  public List<FriendVO> getAll(Map<String, String[]> map);
}
