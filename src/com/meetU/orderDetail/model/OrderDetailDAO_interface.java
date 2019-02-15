package com.meetU.orderDetail.model;

import java.util.List;

import com.meetU.orderMaster.model.OrderMasterVO;

public interface OrderDetailDAO_interface {

	public void insert(OrderDetailVO odVO);
    public void update(OrderDetailVO odVO);
    public OrderDetailVO findByPrimaryKey(String prodID, String orderID);
    public List<OrderDetailVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 
}
