package com.meetU.orderMaster.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderMasterVO implements Serializable{

	private String order_ID;
	private String mem_ID;
	private Double price;
	private Timestamp order_date;
	private String tip;
	private String out_add;
	private String recipient;
	private String phone;
	private Timestamp out_date;
	private Integer out_status;
	private Integer order_status;
	
	public OrderMasterVO() {
		
	}
	public String getOrder_ID() {
		return order_ID;
	}
	public void setOrder_ID(String order_ID) {
		this.order_ID = order_ID;
	}
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getOut_add() {
		return out_add;
	}
	public void setOut_add(String out_add) {
		this.out_add = out_add;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getOut_date() {
		return out_date;
	}
	public void setOut_date(Timestamp out_date) {
		this.out_date = out_date;
	}
	public Integer getOut_status() {
		return out_status;
	}
	public void setOut_status(Integer out_status) {
		this.out_status = out_status;
	}
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	
	

}
