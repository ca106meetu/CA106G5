package com.meetU.mem.model;

import java.sql.*;
import java.io.Serializable;

public class MemVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String mem_ID;
	private String mem_pw;
	private String mem_name;
	private String mem_acc;
	private String mem_nickname;
	private Date mem_bday;
	private String mem_email;
	private String mem_pho;
	private String mem_gend;
	private byte[] mem_pic; 
	private String mem_intro;
	private Integer mem_code;
	private Integer mem_state;
	private Date mem_date;
	private Timestamp mem_sign_day;
	private Integer mem_login_state;
	private String mem_address;
	private Timestamp last_pair;
	private String mem_hobby;
	private byte[] mem_QRCODE ;
	private Integer mem_get_point;
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_acc() {
		return mem_acc;
	}
	public void setMem_acc(String mem_acc) {
		this.mem_acc = mem_acc;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
	public Date getMem_bday() {
		return mem_bday;
	}
	public void setMem_bday(Date mem_bday) {
		this.mem_bday = mem_bday;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_pho() {
		return mem_pho;
	}
	public void setMem_pho(String mem_pho) {
		this.mem_pho = mem_pho;
	}
	public String getMem_gend() {
		return mem_gend;
	}
	public void setMem_gend(String mem_gend) {
		this.mem_gend = mem_gend;
	}
	public byte[] getMem_pic() {
		return mem_pic;
	}
	public void setMem_pic(byte[] mem_pic) {
		this.mem_pic = mem_pic;
	}
	public String getMem_intro() {
		return mem_intro;
	}
	public void setMem_intro(String mem_intro) {
		this.mem_intro = mem_intro;
	}
	public Integer getMem_code() {
		return mem_code;
	}
	public void setMem_code(Integer mem_code) {
		this.mem_code = mem_code;
	}
	public Integer getMem_state() {
		return mem_state;
	}
	public void setMem_state(Integer mem_state) {
		this.mem_state = mem_state;
	}
	public Date getMem_date() {
		return mem_date;
	}
	public void setMem_date(Date mem_date) {
		this.mem_date = mem_date;
	}
	public Timestamp getMem_sign_day() {
		return mem_sign_day;
	}
	public void setMem_sign_day(Timestamp mem_sign_day) {
		this.mem_sign_day = mem_sign_day;
	}
	public Integer getMem_login_state() {
		return mem_login_state;
	}
	public void setMem_login_state(Integer mem_login_state) {
		this.mem_login_state = mem_login_state;
	}
	public String getMem_address() {
		return mem_address;
	}
	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	}
	public Timestamp getLast_pair() {
		return last_pair;
	}
	public void setLast_pair(Timestamp last_pair) {
		this.last_pair = last_pair;
	}
	public String getMem_hobby() {
		return mem_hobby;
	}
	public void setMem_hobby(String mem_hobby) {
		this.mem_hobby = mem_hobby;
	}
	public byte[] getMem_QRCODE() {
		return mem_QRCODE;
	}
	public void setMem_QRCODE(byte[] mem_QRCODE) {
		this.mem_QRCODE = mem_QRCODE;
	}
	public Integer getMem_get_point() {
		return mem_get_point;
	}
	public void setMem_get_point(Integer mem_get_point) {
		this.mem_get_point = mem_get_point;
	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MemVO)) {
            return false;
        }
        return this.getMem_ID().equals(((MemVO) obj).getMem_ID());
    }	
}
