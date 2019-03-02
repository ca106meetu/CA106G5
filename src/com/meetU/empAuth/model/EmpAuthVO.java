package com.meetU.empAuth.model;

import java.io.Serializable;

public class EmpAuthVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String emp_ID;
	private String auth_ID;
	public String getEmp_ID() {
		return emp_ID;
	}
	public void setEmp_ID(String emp_ID) {
		this.emp_ID = emp_ID;
	}
	public String getAuth_ID() {
		return auth_ID;
	}
	public void setAuth_ID(String auth_ID) {
		this.auth_ID = auth_ID;
	}
	

}
