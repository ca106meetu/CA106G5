package com.meetU.orderDetail.model;

import java.sql.Timestamp;
import java.util.List;

import com.meetU.orderDetail.model.OrderDetailVO;

public class OrderDetailService {
	
	private OrderDetailDAO_interface dao;
	
	public OrderDetailService(){
		dao = new OrderDetailDAO();
	}
	
	public OrderDetailVO addOd(String prod_ID, String order_ID, Integer quantity, Double price) {
		
		OrderDetailVO odVO = new OrderDetailVO();
		
		odVO.setProd_ID(prod_ID);
		odVO.setOrder_ID(order_ID);
		odVO.setPrice(price);
		odVO.setPrice(price);
		dao.insert(odVO);
		
		return odVO;
	}
	
	public OrderDetailVO updateOd(String prod_ID, String order_ID, Integer quantity, Double price) {
		

		OrderDetailVO odVO = new OrderDetailVO();
	
		odVO.setProd_ID(prod_ID);
		odVO.setOrder_ID(order_ID);
		odVO.setPrice(price);
		odVO.setPrice(price);
		dao.update(odVO);
		
		return odVO;
		
	}
	
	public void deleteOd(String prod_ID, String order_ID) {
		dao.delete(prod_ID, order_ID);
	}
	
	public OrderDetailVO getOneOd(String prod_ID, String order_ID) {
		return dao.findByPrimaryKey(prod_ID, order_ID);
	}
	
	public List<OrderDetailVO> getAll(){
		return dao.getAll();
	}
	

}
