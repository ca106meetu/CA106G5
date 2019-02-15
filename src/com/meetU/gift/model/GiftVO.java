package com.meetU.gift.model;

public class GiftVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String gift_rec_ID;
	private String recv_ID;
	private String send_ID;
	private String prod_ID;
	private Integer gift_quantity;
	
	
	public String getGift_rec_ID() {
		return gift_rec_ID;
	}
	public void setGift_rec_ID(String gift_rec_ID) {
		this.gift_rec_ID = gift_rec_ID;
	}
	public String getRecv_ID() {
		return recv_ID;
	}
	public void setRecv_ID(String recv_ID) {
		this.recv_ID = recv_ID;
	}
	public String getSend_ID() {
		return send_ID;
	}
	public void setSend_ID(String send_ID) {
		this.send_ID = send_ID;
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
