package com.meetU.filerec.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class FileRecVO implements Serializable {

	private static final long serialVersionUID = 7775131585995756035L;
	private String file_ID;
	private String host_ID;
	private String file_name;
	private String live_des;
	private String file_cont;
	private Timestamp file_date;
	private Integer file_pop;

	public FileRecVO() {

	}

	public String getFile_ID() {
		return file_ID;
	}

	public void setFile_ID(String file_ID) {
		this.file_ID = file_ID;
	}

	public String getHost_ID() {
		return host_ID;
	}

	public void setHost_ID(String host_ID) {
		this.host_ID = host_ID;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getLive_des() {
		return live_des;
	}

	public void setLive_des(String live_des) {
		this.live_des = live_des;
	}

	public String getFile_cont() {
		return file_cont;
	}

	public void setFile_cont(String file_cont) {
		this.file_cont = file_cont;
	}

	public Timestamp getFile_date() {
		return file_date;
	}

	public void setFile_date(Timestamp file_date) {
		this.file_date = file_date;
	}

	public Integer getFile_pop() {
		return file_pop;
	}

	public void setFile_pop(Integer file_pop) {
		this.file_pop = file_pop;
	}

}
