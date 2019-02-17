package com.meetU.saleType.model;

import java.util.List;

import com.meetU.product.model.ProductVO;

public interface SaleTypeDAO_interface {
	public void insert(SaleTypeVO stVO);
    public void update(SaleTypeVO stVO);
    public void delete(String saleTypeID);
    public SaleTypeVO findByPrimaryKey(String saleTypeID);
    public List<SaleTypeVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
