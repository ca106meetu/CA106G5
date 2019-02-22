package com.meetU.ad.model;

import java.io.Serializable;
import java.sql.Date;

public class AdVO implements Serializable {

	private static final long serialVersionUID = -8741467386379886503L;
	private String ad_ID;
	private String host_ID;
	private String ad_name;
	private byte[] ad_cont;
	private Integer ad_cost;
	private Integer apply_status;
	private Date ad_star;
	private Date ad_end;

	public AdVO() {

	}

	public String getAd_ID() {
		return ad_ID;
	}

	public void setAd_ID(String ad_ID) {
		this.ad_ID = ad_ID;
	}

	public String getHost_ID() {
		return host_ID;
	}

	public void setHost_ID(String host_ID) {
		this.host_ID = host_ID;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public byte[] getAd_cont() {
		return ad_cont;
	}

	public void setAd_cont(byte[] ad_cont) {
		this.ad_cont = ad_cont;
	}

	public Integer getAd_cost() {
		return ad_cost;
	}

	public void setAd_cost(Integer ad_cost) {
		this.ad_cost = ad_cost;
	}

	public Integer getApply_status() {
		return apply_status;
	}

	public void setApply_status(Integer apply_status) {
		this.apply_status = apply_status;
	}

	public Date getAd_star() {
		return ad_star;
	}

	public void setAd_star(Date ad_star) {
		this.ad_star = ad_star;
	}

	public Date getAd_end() {
		return ad_end;
	}

	public void setAd_end(Date ad_end) {
		this.ad_end = ad_end;
	}

}
