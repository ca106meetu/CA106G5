package com.meetU.sale.model;

import java.util.List;

import com.meetU.saleType.model.SaleTypeVO;

public interface SaleDAO_interface {
	
	public void insert(SaleVO sVO);
    public void update(SaleVO sVO);
    public SaleVO findByPrimaryKey(String saleID);
    public List<SaleVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 

}
