package com.meetU.orderDetail.model;

import java.util.List;

import com.meetU.orderMaster.model.OrderMasterVO;

public interface OrderDetailDAO_interface {

	public void insert(OrderDetailVO odVO);
    public void update(OrderDetailVO odVO);
    public OrderDetailVO findByPrimaryKey(String prodID, String orderID);
    public List<OrderDetailVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 
}
