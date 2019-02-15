package com.meetU.saleDetail.model;

import java.io.Serializable;

public class SaleDetailVO implements Serializable{

	private String prod_ID;
	private String sale_ID;
	private Double promt_price;
	
	public SaleDetailVO() {
	}

	public String getProd_ID() {
		return prod_ID;
	}

	public void setProd_ID(String prod_ID) {
		this.prod_ID = prod_ID;
	}

	public String getSale_ID() {
		return sale_ID;
	}

	public void setSale_ID(String sale_ID) {
		this.sale_ID = sale_ID;
	}

	public Double getPromt_price() {
		return promt_price;
	}

	public void setPromt_price(Double promt_price) {
		this.promt_price = promt_price;
	}
	
	
	

}
