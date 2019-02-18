package com.meetU.checkIn.model;

import java.util.List;


public interface CheckInDAO_interface {
	public void insert(CheckInVO CheckInVO);
    public void update(CheckInVO CheckInVO);
    public void delete(String CheckIn_id);
    public CheckInVO findByPrimaryKey(String CheckIn_id);
    public List<CheckInVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<CheckInVO> getAll(Map<String, String[]> map); 
}
