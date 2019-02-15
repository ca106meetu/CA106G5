package com.meetU.saleType.model;

import java.io.Serializable;

public class SaleTypeVO implements Serializable{

	private String sale_type_ID;
	private String sale_type_name;
	
	

	public String getSale_type_ID() {
		return sale_type_ID;
	}



	public void setSale_type_ID(String sale_type_ID) {
		this.sale_type_ID = sale_type_ID;
	}



	public String getSale_type_name() {
		return sale_type_name;
	}



	public void setSale_type_name(String sale_type_name) {
		this.sale_type_name = sale_type_name;
	}



	public SaleTypeVO() {
	}

}
