package com.meetU.giftbox.model;

import java.util.*;

public class GiftboxService {
	
	private GiftboxDAO_interface dao;
	
	public GiftboxService() {
		dao = new GiftboxDAO();
	}
	public GiftboxVO addGiftbox(String mem_ID, String prod_ID, Integer gift_quantity) {
		GiftboxVO giftboxVO = new GiftboxVO();
		
		giftboxVO.setMem_ID(mem_ID);
		giftboxVO.setProd_ID(prod_ID);
		giftboxVO.setGift_quantity(gift_quantity);
		
		dao.insert(giftboxVO);
		
		return giftboxVO;
	}
	
	public GiftboxVO updateGiftbox(String mem_ID, String prod_ID, Integer gift_quantity) {
		GiftboxVO giftboxVO = new GiftboxVO();
		
		giftboxVO.setMem_ID(mem_ID);
		giftboxVO.setProd_ID(prod_ID);
		giftboxVO.setGift_quantity(gift_quantity);
		
		return giftboxVO;		
	}
	
	public void deleteGiftbox(String mem_ID, String prod_ID) {
		dao.delete(mem_ID, prod_ID);
	}
	
	public GiftboxVO getOneGiftbox(String mem_ID, String prod_ID) {
		return dao.findByPrimaryKey(mem_ID, prod_ID);
	}
	
	public List<GiftboxVO> getAll(){
		return dao.getAll();
	}
	
}
