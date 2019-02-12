package com.gift.model;

public class GiftVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String gift_rec_id;
	private String recv_id;
	private String send_id;
	private String prod_id;
	private Integer gift_quantity;
	
	public String getGift_rec_id() {
		return gift_rec_id;
	}
	public void setGift_rec_id(String gift_rec_id) {
		this.gift_rec_id = gift_rec_id;
	}
	public String getRecv_id() {
		return recv_id;
	}
	public void setRecv_id(String recv_id) {
		this.recv_id = recv_id;
	}
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
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
