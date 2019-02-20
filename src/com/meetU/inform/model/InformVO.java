package com.meetU.inform.model;

import java.sql.Timestamp;

public class InformVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String inform_ID;
	private Integer inform_status;
	private String mem_ID;
	private String inform_content;
	private Timestamp inform_time;
	
	
	public String getInform_ID() {
		return inform_ID;
	}
	public void setInform_ID(String inform_ID) {
		this.inform_ID = inform_ID;
	}
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	public Integer getInform_status() {
		return inform_status;
	}
	public void setInform_status(Integer inform_status) {
		this.inform_status = inform_status;
	}

	public String getInform_content() {
		return inform_content;
	}
	public void setInform_content(String inform_content) {
		this.inform_content = inform_content;
	}
	public Timestamp getInform_time() {
		return inform_time;
	}
	public void setInform_time(Timestamp inform_time) {
		this.inform_time = inform_time;
	}

}
