package com.meetU.auth.model;

public class AuthVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String auth_ID;
	private String auth_name;
	

	public String getAuth_ID() {
		return auth_ID;
	}
	public void setAuth_ID(String auth_ID) {
		this.auth_ID = auth_ID;
	}
	public String getAuth_name() {
		return auth_name;
	}
	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}

}
