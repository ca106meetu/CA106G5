package com.meetU.saleDetail.model;

import java.util.List;

import com.meetU.product.model.ProductVO;

public interface SaleDetailDAO_interface {
	public void insert(SaleDetailVO sdVO);
    public void update(SaleDetailVO sdVO);
    public void delete(String prod_ID, String sale_ID);
    public SaleDetailVO findByPrimaryKey(String prod_ID, String sale_ID);
    public List<SaleDetailVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);  

}
