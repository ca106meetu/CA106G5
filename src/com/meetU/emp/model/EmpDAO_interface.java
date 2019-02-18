package com.meetU.emp.model;

import java.util.List;

public interface EmpDAO_interface {
    public void insert(EmpVO empVO);
    public void update(EmpVO empVO);
    public void delete(String emp_id);
    public EmpVO findByPrimaryKey(String emp_id);
    public List<EmpVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<EmpVO> getAll(Map<String, String[]> map);
}
