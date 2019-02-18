package com.meetU.checkIn.model;

public class CheckInVO implements java.io.Serializable{
	private String check_in_id;
	private String check_in_name;
	private Double check_in_lnt;
	private Double check_in_lat;
	
	public String getCheck_in_id() {
		return check_in_id;
	}
	public void setCheck_in_id(String check_in_id) {
		this.check_in_id = check_in_id;
	}
	public String getCheck_in_name() {
		return check_in_name;
	}
	public void setCheck_in_name(String check_in_name) {
		this.check_in_name = check_in_name;
	}
	public Double getCheck_in_lnt() {
		return check_in_lnt;
	}
	public void setCheck_in_lnt(Double check_in_lnt) {
		this.check_in_lnt = check_in_lnt;
	}
	public Double getCheck_in_lat() {
		return check_in_lat;
	}
	public void setCheck_in_lat(Double check_in_lat) {
		this.check_in_lat = check_in_lat;
	}
	
	
}
