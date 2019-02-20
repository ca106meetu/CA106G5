package com.meetU.auth.model;

import java.util.*;

public interface AuthDAO_interface {
    public void insert(AuthVO authVO);
    public void update(AuthVO authVO);
    public void delete(String auth_ID);
    public AuthVO findByPrimaryKey(String auth_ID);
    public List<AuthVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<AuthVO> getAll(Map<String, String[]> map);
}
