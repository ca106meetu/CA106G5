package com.meetU.orderDetail.model;

import java.util.List;
import java.util.Vector;

import com.meetU.product.model.ProductVO;

public interface OrderDetailDAO_interface {

	public void insert(OrderDetailVO odVO);
    public void update(OrderDetailVO odVO);
    public void delete(String prodID, String orderID);
    public OrderDetailVO findByPrimaryKey(String prodID, String orderID);
    public List<OrderDetailVO> getAll();
    public void insertList(Vector<ProductVO> buyList);
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
