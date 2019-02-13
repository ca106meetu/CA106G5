package com.sale.model;

import java.sql.Timestamp;

public class SaleVO {
	private String saleID;
	private String saleTypeID;
	private String saleName;
	private Timestamp startDate;
	private Timestamp endDate;
	private String saleInfo;
	private byte[] salePic;
	public String getSaleID() {
		return saleID;
	}
	public void setSaleID(String saleID) {
		this.saleID = saleID;
	}
	public String getSaleTypeID() {
		return saleTypeID;
	}
	public void setSaleTypeID(String saleTypeID) {
		this.saleTypeID = saleTypeID;
	}
	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public String getSaleInfo() {
		return saleInfo;
	}
	public void setSaleInfo(String saleInfo) {
		this.saleInfo = saleInfo;
	}
	public byte[] getSalePic() {
		return salePic;
	}
	public void setSalePic(byte[] salePic) {
		this.salePic = salePic;
	}
	
		
}
