package com.meetU.emp.model;

import java.util.List;

public interface EmpDAO_interface {
    public void insert(EmpVO empVO);
    public void update(EmpVO empVO);
    public void delete(String emp_id);
    public EmpVO findByPrimaryKey(String emp_id);
    public List<EmpVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
    //  public List<EmpVO> getAll(Map<String, String[]> map);
}
