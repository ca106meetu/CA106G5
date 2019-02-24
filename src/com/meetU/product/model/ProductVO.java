package com.meetU.product.model;

import java.io.Serializable;

public class ProductVO implements Serializable{
	
	private Integer quantity;
	
	
	private String prod_ID;
	private String prod_name;
	private Double prod_price;
	private Integer prod_type;
	private Integer prod_stock;
	private byte[] prod_pic;
	private Integer prod_promt_status;
	private Integer prod_status;
	private String prod_info;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prod_ID == null) ? 0 : prod_ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductVO other = (ProductVO) obj;
		if (prod_ID == null) {
			if (other.prod_ID != null)
				return false;
		} else if (!prod_ID.equals(other.prod_ID))
			return false;
		return true;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductVO() {
		
	}
	
	public String getProd_ID() {
		return prod_ID;
	}
	public void setProd_ID(String prod_ID) {
		this.prod_ID = prod_ID;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public Double getProd_price() {
		return prod_price;
	}
	public void setProd_price(Double prod_price) {
		this.prod_price = prod_price;
	}
	public Integer getProd_type() {
		return prod_type;
	}
	public void setProd_type(Integer prod_type) {
		this.prod_type = prod_type;
	}
	public Integer getProd_stock() {
		return prod_stock;
	}
	public void setProd_stock(Integer prod_stock) {
		this.prod_stock = prod_stock;
	}
	public byte[] getProd_pic() {
		return prod_pic;
	}
	public void setProd_pic(byte[] prod_pic) {
		this.prod_pic = prod_pic;
	}
	public Integer getProd_promt_status() {
		return prod_promt_status;
	}
	public void setProd_promt_status(Integer prod_promt_status) {
		this.prod_promt_status = prod_promt_status;
	}
	public Integer getProd_status() {
		return prod_status;
	}
	public void setProd_status(Integer prod_status) {
		this.prod_status = prod_status;
	}
	public String getProd_info() {
		return prod_info;
	}
	public void setProd_info(String prod_info) {
		this.prod_info = prod_info;
	}
	
	
	

}
