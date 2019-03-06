package com.meetU.meetup_report.model;

import java.sql.Date;
import java.sql.Timestamp;

public class MeetupRepVO implements java.io.Serializable{
	private String meetup_rep_ID;
	private String meetup_ID;
	private String mem_ID;
	private String rep_content;
	private Timestamp rep_date;
	private Integer rep_status;
	private String rep_ans;
	private Timestamp rep_ans_date;
	
	public String getMeetup_rep_ID() {
		return meetup_rep_ID;
	}
	public void setMeetup_rep_ID(String meetup_rep_ID) {
		this.meetup_rep_ID = meetup_rep_ID;
	}
	public String getMeetup_ID() {
		return meetup_ID;
	}
	public void setMeetup_ID(String meetup_ID) {
		this.meetup_ID = meetup_ID;
	}
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	public String getRep_content() {
		return rep_content;
	}
	public void setRep_content(String rep_content) {
		this.rep_content = rep_content;
	}
	public Timestamp getRep_date() {
		return rep_date;
	}
	public void setRep_date(Timestamp rep_date) {
		this.rep_date = rep_date;
	}
	public Integer getRep_status() {
		return rep_status;
	}
	public void setRep_status(Integer rep_status) {
		this.rep_status = rep_status;
	}
	public String getRep_ans() {
		return rep_ans;
	}
	public void setRep_ans(String rep_ans) {
		this.rep_ans = rep_ans;
	}
	public Timestamp getRep_ans_date() {
		return rep_ans_date;
	}
	public void setRep_ans_date(Timestamp rep_ans_date) {
		this.rep_ans_date = rep_ans_date;
	}	
}
