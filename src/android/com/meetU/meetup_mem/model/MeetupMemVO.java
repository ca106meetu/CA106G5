package android.com.meetU.meetup_mem.model;

import java.sql.Date;

public class MeetupMemVO implements java.io.Serializable{
	private String meetup_ID;
	private String mem_ID;
	private Integer mem_showup;
	private Integer meetup_rate;
	private String meetup_comment;
	
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
	public Integer getMem_showup() {
		return mem_showup;
	}
	public void setMem_showup(Integer mem_showup) {
		this.mem_showup = mem_showup;
	}
	public Integer getMeetup_rate() {
		return meetup_rate;
	}
	public void setMeetup_rate(Integer meetup_rate) {
		this.meetup_rate = meetup_rate;
	}
	public String getMeetup_comment() {
		return meetup_comment;
	}
	public void setMeetup_comment(String meetup_comment) {
		this.meetup_comment = meetup_comment;
	}	
}
