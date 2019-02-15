package com.meetU.orderMaster.model;

import java.util.List;

import com.meetU.orderMaster.model.OrderMasterVO;

public interface OrderMasterDAO_interface {

	public void insert(OrderMasterVO omVO);
    public void update(OrderMasterVO omVO);
    public void delete(String order_ID);
    public OrderMasterVO findByPrimaryKey(String order_ID);
    public List<OrderMasterVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 
}
