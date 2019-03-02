package com.meetU.orderDetail.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import com.meetU.orderDetail.model.OrderDetailVO;
import com.meetU.orderMaster.model.OrderMasterVO;
import com.meetU.product.model.ProductVO;

public class OrderDetailService {
	
	private OrderDetailDAO_interface dao;
	
	public OrderDetailService(){
		dao = new OrderDetailDAO();
	}
	
	
	
	
	public void insertOmOd(String mem_ID, Double price, Timestamp order_date,
			String tip, String out_add, String recipient, 
			String phone, Timestamp out_date, Integer out_status, Integer order_status,
			List<ProductVO> buyList) {
		
		OrderMasterVO omVO = new OrderMasterVO();
		
		omVO.setMem_ID(mem_ID);
		omVO.setPrice(price);
		omVO.setOrder_date(order_date);
		omVO.setTip(tip);
		omVO.setOut_add(out_add);
		omVO.setRecipient(recipient);
		omVO.setPhone(phone);
		omVO.setOut_date(out_date);
		omVO.setOut_status(out_status);
		omVO.setOrder_status(order_status);
		dao.insertList(omVO, buyList);
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
