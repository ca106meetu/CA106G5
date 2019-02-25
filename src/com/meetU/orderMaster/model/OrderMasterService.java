package com.meetU.orderMaster.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public class OrderMasterService {
	private OrderMasterDAO_interface dao;
	
	public OrderMasterService() {
		dao = new OrderMasterDAO();
	}
	
	public OrderMasterVO addOm(String mem_ID, Double price, Timestamp order_date,
			String tip, String out_add, String recipient, 
			String phone, Timestamp out_date, Integer out_status, Integer order_status) {
		
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
		dao.insert(omVO);
		
		return omVO;
	}
	
	public OrderMasterVO updateOm(String order_ID, String mem_ID, Double price, Timestamp order_date,
			String tip, String out_add, String recipient, 
			String phone, Timestamp out_date, Integer out_status, Integer order_status) {
		

		OrderMasterVO omVO = new OrderMasterVO();
	
		omVO.setOrder_ID(order_ID);
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
		dao.update(omVO);
		
		return omVO;
		
	}
	
	public void deleteOm(String order_ID) {
		dao.delete(order_ID);
	}
	
	public OrderMasterVO getOneOm(String order_ID) {
		return dao.findByPrimaryKey(order_ID);
	}
	
	public List<OrderMasterVO> getAll(){
		return dao.getAll();
	}

}
