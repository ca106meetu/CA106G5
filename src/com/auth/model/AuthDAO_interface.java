package com.auth.model;

import java.util.List;

public interface AuthDAO_interface {
    public void insert(AuthVO authVO);
    public void update(AuthVO authVO);
    public void delete(String auth_id);
    public AuthVO findByPrimaryKey(String auth_id);
    public List<AuthVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
    //  public List<AuthVO> getAll(Map<String, String[]> map);
}
