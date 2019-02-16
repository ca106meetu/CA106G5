package com.meetU.meetup.model;

public class MeetupVO implements java.io.Serializable{
	private String meetup_ID;
	private String meetup_name;
	private String mem_ID;
	private String meetup_date;
	private String meetup_loc;
	private Integer meetup_status;
	private byte[] meetup_pic;
	private String meetup_info;
	
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
	public String getMeetup_date() {
		return meetup_date;
	}
	public void setMeetup_date(String meetup_date) {
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
}
