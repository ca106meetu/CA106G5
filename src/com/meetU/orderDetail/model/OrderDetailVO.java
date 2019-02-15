package com.meetU.orderDetail.model;

import java.io.Serializable;

public class OrderDetailVO implements Serializable{

	private String order_ID;
	private String prod_ID;
	private Integer quantity;
	private Double price;
	
	

	public String getOrder_ID() {
		return order_ID;
	}



	public void setOrder_ID(String order_ID) {
		this.order_ID = order_ID;
	}



	public String getProd_ID() {
		return prod_ID;
	}



	public void setProd_ID(String prod_ID) {
		this.prod_ID = prod_ID;
	}



	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public OrderDetailVO() {
	}

}
