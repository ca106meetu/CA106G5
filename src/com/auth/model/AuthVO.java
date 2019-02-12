package com.auth.model;

public class AuthVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String auth_id;
	private String auth_name;
	
	public String getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}
	public String getAuth_name() {
		return auth_name;
	}
	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}

}
