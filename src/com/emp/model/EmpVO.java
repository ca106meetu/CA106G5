package com.emp.model;

import java.sql.Date;

public class EmpVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String emp_id;
	private String emp_pw;
	private String emp_name;
	private Date emp_bday;
	private String emp_email;
	private String emp_pho;
	private String emp_gend;
	private byte[] emp_pic;
	private Integer emp_state;
	private Date emp_hday;
	private String emp_address;
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_pw() {
		return emp_pw;
	}
	public void setEmp_pw(String emp_pw) {
		this.emp_pw = emp_pw;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public Date getEmp_bday() {
		return emp_bday;
	}
	public void setEmp_bday(Date emp_bday) {
		this.emp_bday = emp_bday;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public String getEmp_pho() {
		return emp_pho;
	}
	public void setEmp_pho(String emp_pho) {
		this.emp_pho = emp_pho;
	}
	public String getEmp_gend() {
		return emp_gend;
	}
	public void setEmp_gend(String emp_gend) {
		this.emp_gend = emp_gend;
	}
	public byte[] getEmp_pic() {
		return emp_pic;
	}
	public void setEmp_pic(byte[] emp_pic) {
		this.emp_pic = emp_pic;
	}
	public Integer getEmp_state() {
		return emp_state;
	}
	public void setEmp_state(Integer emp_state) {
		this.emp_state = emp_state;
	}
	public Date getEmp_hday() {
		return emp_hday;
	}
	public void setEmp_hday(Date emp_hday) {
		this.emp_hday = emp_hday;
	}
	public String getEmp_address() {
		return emp_address;
	}
	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}
	
	
}
