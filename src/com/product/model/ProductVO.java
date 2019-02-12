package com.product.model;

public class ProductVO {
	private String prodID;
	private String prodName;
	private Double prodPrice;
	private Integer prodType;
	private Integer prodStock;
	private byte[] prodPic;
	private Integer prodPromtStatus;
	private Integer prodStatus;
	private String prodInfo;
	
	
	
	public Integer getProdStock() {		
		return prodStock;
	}
	public void setProdStock(Integer prodStock) {
		this.prodStock = prodStock;
	}
	public String getProdID() {
		return prodID;
	}
	public void setProdID(String prodID) {
		this.prodID = prodID;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Double getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(Double prodPrice) {
		this.prodPrice = prodPrice;
	}
	public Integer getProdType() {
		return prodType;
	}
	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}
	public byte[] getProdPic() {
		return prodPic;
	}
	public void setProdPic(byte[] prodPic) {
		this.prodPic = prodPic;
	}
	public Integer getProdPromtStatus() {
		return prodPromtStatus;
	}
	public void setProdPromtStatus(Integer prodPromtStatus) {
		this.prodPromtStatus = prodPromtStatus;
	}
	public Integer getProdStatus() {
		return prodStatus;
	}
	public void setProdStatus(Integer prodStatus) {
		this.prodStatus = prodStatus;
	}
	public String getProdInfo() {
		return prodInfo;
	}
	public void setProdInfo(String prodInfo) {
		this.prodInfo = prodInfo;
	}
	

}
