package com.meetU.meetup_chat.model;

import java.sql.Timestamp;

public class MeetupChatVO implements java.io.Serializable{
	private String chat_ID;
	private String meetup_ID;
	private String mem_ID;
	private Timestamp chat_msg_time;
	private String chat_msg;
	
	public String getChat_ID() {
		return chat_ID;
	}
	public void setChat_ID(String chat_ID) {
		this.chat_ID = chat_ID;
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
	public Timestamp getChat_msg_time() {
		return chat_msg_time;
	}
	public void setChat_msg_time(Timestamp chat_msg_time) {
		this.chat_msg_time = chat_msg_time;
	}
	public String getChat_msg() {
		return chat_msg;
	}
	public void setChat_msg(String chat_msg) {
		this.chat_msg = chat_msg;
	}
}
