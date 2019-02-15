package com.meetU.sale.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SaleVO implements Serializable{
	private String sale_ID;
	private String sale_type_ID;
	private String sale_name;
	private Timestamp start_date;
	private Timestamp end_date;
	private String sale_info;
	private byte[] sale_pic;
	
	public SaleVO() {
		
	}

	public String getSale_ID() {
		return sale_ID;
	}

	public void setSale_ID(String sale_ID) {
		this.sale_ID = sale_ID;
	}

	public String getSale_type_ID() {
		return sale_type_ID;
	}

	public void setSale_type_ID(String sale_type_ID) {
		this.sale_type_ID = sale_type_ID;
	}

	public String getSale_name() {
		return sale_name;
	}

	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}

	public Timestamp getStart_date() {
		return start_date;
	}

	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	public Timestamp getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}

	public String getSale_info() {
		return sale_info;
	}

	public void setSale_info(String sale_info) {
		this.sale_info = sale_info;
	}

	public byte[] getSale_pic() {
		return sale_pic;
	}

	public void setSale_pic(byte[] sale_pic) {
		this.sale_pic = sale_pic;
	}
	
	
	
		
}
