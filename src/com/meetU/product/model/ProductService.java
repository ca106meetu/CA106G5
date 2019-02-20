package com.meetU.product.model;
import java.util.List;

public class ProductService {
	
	private ProductDAO_interface dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	
	public ProductVO addprod(String prod_name, Double prod_price, Integer prod_type,
			Integer prod_stock, byte[] prod_pic, Integer prod_promt_status, 
			Integer prod_status, String prod_info) {
		
		ProductVO prodVO = new ProductVO();
		
		prodVO.setProd_name(prod_name);
		prodVO.setProd_price(prod_price);
		prodVO.setProd_type(prod_type);
		prodVO.setProd_stock(prod_stock);
		prodVO.setProd_pic(prod_pic);
		prodVO.setProd_promt_status(prod_promt_status);
		prodVO.setProd_status(prod_status);
		prodVO.setProd_info(prod_info);
		dao.insert(prodVO);
		
		return prodVO;
	}
	
	public ProductVO updateProd(String prod_ID, String prod_name, Double prod_price, Integer prod_type,
			Integer prod_stock, byte[] prod_pic, Integer prod_promt_status, 
			Integer prod_status, String prod_info) {
		

		ProductVO prodVO = new ProductVO();
	
		prodVO.setProd_ID(prod_ID);
		prodVO.setProd_name(prod_name);
		prodVO.setProd_price(prod_price);
		prodVO.setProd_type(prod_type);
		prodVO.setProd_stock(prod_stock);
		prodVO.setProd_pic(prod_pic);
		prodVO.setProd_promt_status(prod_promt_status);
		prodVO.setProd_status(prod_status);
		prodVO.setProd_info(prod_info);
		dao.update(prodVO);
		
		return prodVO;
		
	}
	
	public void deleteProd(String prod_ID) {
		dao.delete(prod_ID);
		System.out.println(6666);
	}
	
	public ProductVO getOneProd(String prod_ID) {
		return dao.findByPrimaryKey(prod_ID);
	}
	
	public List<ProductVO> getAll(){
		return dao.getAll();
	}
	
	
	
	
	

}
