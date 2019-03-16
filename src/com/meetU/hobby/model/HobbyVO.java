package com.meetU.hobby.model;

import java.io.Serializable;

public class HobbyVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String hobby_ID;
	private String hobby_name;
	
	public String getHobby_ID() {
		return hobby_ID;
	}
	public void setHobby_ID(String hobby_ID) {
		this.hobby_ID = hobby_ID;
	}
	public String getHobby_name() {
		return hobby_name;
	}
	public void setHobby_name(String hobby_name) {
		this.hobby_name = hobby_name;
	}
}
