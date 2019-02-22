package com.meetU.postMessage.model;

import java.sql.Date;
import java.sql.Timestamp;

public class PostMessageVO implements java.io.Serializable{
	private String msg_ID;
	private String post_ID;
	private String mem_ID;
	private String msg_content;
	private Timestamp publish_time;
	private Integer msg_like;
	
	public String getMsg_ID() {
		return msg_ID;
	}
	public void setMsg_ID(String msg_ID) {
		this.msg_ID = msg_ID;
	}
	public String getPost_ID() {
		return post_ID;
	}
	public void setPost_ID(String post_ID) {
		this.post_ID = post_ID;
	}
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public Timestamp getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(Timestamp publish_time) {
		this.publish_time = publish_time;
	}
	public Integer getMsg_like() {
		return msg_like;
	}
	public void setMsg_like(Integer msg_like) {
		this.msg_like = msg_like;
	}
	
	
}
