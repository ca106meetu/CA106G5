package com.mem.model;

import java.util.List;

public interface MemDAO_interface {
    public void insert(MemVO memVO);
    public void update(MemVO memVO);
    public void delete(String mem_id);
    public MemVO findByPrimaryKey(String mem_id);
    public List<MemVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
    //  public List<MemVO> getAll(Map<String, String[]> map); 
}
