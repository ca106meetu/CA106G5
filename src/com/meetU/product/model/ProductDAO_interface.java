package com.meetU.product.model;

import java.util.*;

public interface ProductDAO_interface {
	
	
	public void insert(ProductVO productVO);
    public void update(ProductVO productVO);
    public void delete(String prod_ID);
    public ProductVO findByPrimaryKey(String prod_ID);
    public List<ProductVO> getAll();
    public List<ProductVO> getSome();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 


}
