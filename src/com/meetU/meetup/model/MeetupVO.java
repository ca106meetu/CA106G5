package com.meetU.meetup.model;

import java.sql.Date;
import java.sql.Timestamp;

public class MeetupVO implements java.io.Serializable{
	private String meetup_ID;
	private String meetup_name;
	private String mem_ID;
	private Timestamp meetup_date;
	private Date meetup_joindate;
	private String meetup_loc;
	private Integer meetup_status;
	private byte[] meetup_pic;
	private String meetup_info;
	private Integer meetup_minppl;
	private Integer meetup_maxppl;
	
	public String getMeetup_ID() {
		return meetup_ID;
	}
	public void setMeetup_ID(String meetup_ID) {
		this.meetup_ID = meetup_ID;
	}
	public String getMeetup_name() {
		return meetup_name;
	}
	public void setMeetup_name(String meetup_name) {
		this.meetup_name = meetup_name;
	}
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	public Timestamp getMeetup_date() {
		return meetup_date;
	}
	public void setMeetup_date(Timestamp meetup_date) {
		this.meetup_date = meetup_date;
	}
	public String getMeetup_loc() {
		return meetup_loc;
	}
	public void setMeetup_loc(String meetup_loc) {
		this.meetup_loc = meetup_loc;
	}
	public Integer getMeetup_status() {
		return meetup_status;
	}
	public void setMeetup_status(Integer meetup_status) {
		this.meetup_status = meetup_status;
	}
	public byte[] getMeetup_pic() {
		return meetup_pic;
	}
	public void setMeetup_pic(byte[] meetup_pic) {
		this.meetup_pic = meetup_pic;
	}
	public String getMeetup_info() {
		return meetup_info;
	}
	public void setMeetup_info(String meetup_info) {
		this.meetup_info = meetup_info;
	}
	public Integer getMeetup_minppl() {
		return meetup_minppl;
	}
	public void setMeetup_minppl(Integer meetup_minppl) {
		this.meetup_minppl = meetup_minppl;
	}
	public Integer getMeetup_maxppl() {
		return meetup_maxppl;
	}
	public void setMeetup_maxppl(Integer meetup_maxppl) {
		this.meetup_maxppl = meetup_maxppl;
	}
	public Date getMeetup_joindate() {
		return meetup_joindate;
	}
	public void setMeetup_joindate(Date meetup_joindate) {
		this.meetup_joindate = meetup_joindate;
	}
}
