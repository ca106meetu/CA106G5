package com.meetU.empAuth.model;

import java.util.List;

public interface EmpAuthDAO_interface {
    public void insert(EmpAuthVO empAuthVO);
    public void update(EmpAuthVO empAuthVO);
    public void delete(String emp_ID,String auth_ID);
    public EmpAuthVO findByPrimaryKey(String emp_ID, String auth_ID);
    public List<EmpAuthVO> findByPartOfOnePrimaryKey(String emp_ID);
    public List<EmpAuthVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<EmpAuthVO> getAll(Map<String, String[]> map);	
}
