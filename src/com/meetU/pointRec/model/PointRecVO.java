package com.meetU.pointRec.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PointRecVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rec_ID;
	private String mem_ID;
	private Double amount;
	private Timestamp rec_date;

	public PointRecVO() {
		// TODO Auto-generated constructor stub
	}

	public String getRec_ID() {
		return rec_ID;
	}

	public void setRec_ID(String rec_ID) {
		this.rec_ID = rec_ID;
	}

	public String getMem_ID() {
		return mem_ID;
	}

	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Timestamp getRec_date() {
		return rec_date;
	}

	public void setRec_date(Timestamp rec_date) {
		this.rec_date = rec_date;
	}
	
	

}
