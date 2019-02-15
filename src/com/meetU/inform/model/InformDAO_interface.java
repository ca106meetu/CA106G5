package com.meetU.inform.model;

import java.util.List;

public interface InformDAO_interface {
    public void insert(InformVO informVO);
    public void update(InformVO informVO);
    public void delete(String inform_id);
    public InformVO findByPrimaryKey(String inform_id);
    public List<InformVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
    //  public List<InformVO> getAll(Map<String, String[]> map); 
}
