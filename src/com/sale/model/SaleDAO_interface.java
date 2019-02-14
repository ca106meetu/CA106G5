package com.sale.model;

import java.util.List;

import com.saleType.model.SaleTypeVO;

public interface SaleDAO_interface {
	
	public void insert(SaleVO sVO);
    public void update(SaleVO sVO);
    public SaleVO findByPrimaryKey(String saleID);
    public List<SaleVO> getAll();

}