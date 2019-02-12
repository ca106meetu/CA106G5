package com.meetup.model;

public class MeetupVO implements java.io.Serializable{
	private String meetupId;
	private String meetupName;
	private String memId;
	private String meetupDate;
	private String meetupLoc;
	private Integer meetupStatus;
	private byte[] meetupPic;
	private String meetupInfo;
	
	public String getMeetupId() {
		return meetupId;
	}
	public void setMeetupId(String meetupId) {
		this.meetupId = meetupId;
	}
	public String getMeetupName() {
		return meetupName;
	}
	public void setMeetupName(String meetupName) {
		this.meetupName = meetupName;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMeetupDate() {
		return meetupDate;
	}
	public void setMeetupDate(String meetupDate) {
		this.meetupDate = meetupDate;
	}
	public String getMeetupLoc() {
		return meetupLoc;
	}
	public void setMeetupLoc(String meetupLoc) {
		this.meetupLoc = meetupLoc;
	}
	public Integer getMeetupStatus() {
		return meetupStatus;
	}
	public void setMeetupStatus(Integer meetupStatus) {
		this.meetupStatus = meetupStatus;
	}
	public byte[] getMeetupPic() {
		return meetupPic;
	}
	public void setMeetupPic(byte[] meetupPic) {
		this.meetupPic = meetupPic;
	}
	public String getMeetupInfo() {
		return meetupInfo;
	}
	public void setMeetupInfo(String meetupInfo) {
		this.meetupInfo = meetupInfo;
	}	
}
