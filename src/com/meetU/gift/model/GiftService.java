package com.meetU.gift.model;

import java.util.*;

public class GiftService {
	
	public GiftDAO_interface dao;
	
	public GiftService() {
		dao = new GiftDAO();
	}

	public GiftVO addGift(String recv_ID, String send_ID, String prod_ID, Integer gift_quantity ) {
		
		GiftVO giftVO = new GiftVO();
		
		giftVO.setRecv_ID(recv_ID);
		giftVO.setSend_ID(send_ID);
		giftVO.setProd_ID(prod_ID);
		giftVO.setGift_quantity(gift_quantity);
		dao.insert(giftVO);
		
		return giftVO;
	}
	
	public GiftVO updateGift(String recv_ID, String send_ID, String prod_ID, Integer gift_quantity, String gift_rec_ID) {
		
		GiftVO giftVO = new GiftVO();
		
		giftVO.setRecv_ID(recv_ID);
		giftVO.setSend_ID(send_ID);
		giftVO.setProd_ID(prod_ID);
		giftVO.setGift_quantity(gift_quantity);
		giftVO.setGift_rec_ID(gift_rec_ID);
		
		dao.update(giftVO);
		
		return giftVO;
	}
	
	public void deleteGift(String gift_rec_ID) {
		dao.delete(gift_rec_ID);
	}
	
	public GiftVO getOneGift(String gift_rec_ID) {
		return dao.findByPrimaryKey(gift_rec_ID);
	}
	
	public List<GiftVO> getAll() {
		return dao.getAll();
	}
	
}
