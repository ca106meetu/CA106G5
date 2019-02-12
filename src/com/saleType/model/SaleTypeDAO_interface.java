package com.saleType.model;

import java.util.List;

import com.product.model.ProductVO;

public interface SaleTypeDAO_interface {
	public void insert(SaleTypeVO stVO);
    public void update(SaleTypeVO stVO);
    public void delete(String saleTypeID);
    public SaleTypeVO findByPrimaryKey(String saleTypeID);
    public List<SaleTypeVO> getAll();
//
}
