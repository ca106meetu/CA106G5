package com.meetU.giftbox.model;

public class GiftboxVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String mem_ID;
	private String prod_ID;
	private Integer gift_quantity;

	
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	public String getProd_ID() {
		return prod_ID;
	}
	public void setProd_ID(String prod_ID) {
		this.prod_ID = prod_ID;
	}
	public Integer getGift_quantity() {
		return gift_quantity;
	}
	public void setGift_quantity(Integer gift_quantity) {
		this.gift_quantity = gift_quantity;
	}

}
