package com.orderMaster.model;

import java.util.List;

import com.orderMaster.model.OrderMasterVO;

public interface OrderMasterDAO_interface {

	public void insert(OrderMasterVO omVO);
    public void update(OrderMasterVO omVO);
    public void delete(String orderID);
    public OrderMasterVO findByPrimaryKey(String orderID);
    public List<OrderMasterVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 
}
