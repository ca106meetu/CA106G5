package com.giftbox.model;

public class GiftboxVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String mem_id;
	private String prod_id;
	private Integer gift_quantity;
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public Integer getGift_quantity() {
		return gift_quantity;
	}
	public void setGift_quantity(Integer gift_quantity) {
		this.gift_quantity = gift_quantity;
	}

}
