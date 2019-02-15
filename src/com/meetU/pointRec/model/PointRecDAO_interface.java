package com.meetU.pointRec.model;

import java.util.List;


public interface PointRecDAO_interface {
	public void insert(PointRecVO prVO);
    public void update(PointRecVO prVO);
    public void delete(String rec_ID);
    public PointRecVO findByPrimaryKey(String rec_ID);
    public List<PointRecVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 

}
