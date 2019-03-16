package com.meetU.memHobby.model;

import java.io.Serializable;

public class MemHobbyVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String mem_ID;
	private String hobby_ID;
	
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	public String getHobby_ID() {
		return hobby_ID;
	}
	public void setHobby_ID(String hobby_ID) {
		this.hobby_ID = hobby_ID;
	}
}
