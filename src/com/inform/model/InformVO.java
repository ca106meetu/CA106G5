package com.inform.model;

import java.sql.Timestamp;

public class InformVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String inform_id;
	private Integer inform_status;
	private String mem_id;
	private String inform_content;
	private Timestamp inform_time;
	
	public String getInform_id() {
		return inform_id;
	}
	public void setInform_id(String inform_id) {
		this.inform_id = inform_id;
	}
	public Integer getInform_status() {
		return inform_status;
	}
	public void setInform_status(Integer inform_status) {
		this.inform_status = inform_status;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
