package com.meetU.product.model;

import java.util.*;

public interface ProductDAO_interface {
	
	
	public void insert(ProductVO productVO);
    public void update(ProductVO productVO);
    public void delete(String prod_ID);
    public ProductVO findByPrimaryKey(String prod_ID);
    public List<ProductVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 


}
