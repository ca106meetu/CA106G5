package com.meetU.orderMaster.model;

import java.util.List;
import java.util.Map;

import com.meetU.orderMaster.model.OrderMasterVO;

public interface OrderMasterDAO_interface {

	public void insert(OrderMasterVO omVO);
    public void update(OrderMasterVO omVO);
    public void delete(String order_ID);
    public OrderMasterVO findByPrimaryKey(String order_ID);
    public List<OrderMasterVO> getAll();
    public List<OrderMasterVO> getOmByMem(String mem_ID);
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<OrderMasterVO> getAll(Map<String, String[]> map); 
}
