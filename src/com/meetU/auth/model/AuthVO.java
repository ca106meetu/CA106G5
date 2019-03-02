package com.meetU.auth.model;

import java.io.Serializable;

public class AuthVO implements Serializable{

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
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuthVO)) {
            return false;
        }
        return this.getAuth_ID().equals(((AuthVO) obj).getAuth_ID());
    }

}
