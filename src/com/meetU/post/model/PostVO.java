package com.meetU.post.model;

import java.sql.Date;

public class PostVO implements java.io.Serializable{
	private String post_ID;
	private String poster_ID;
	private String mem_ID; 
	private String check_in_ID;
	private String post_content;
	private Date publish_time;
	private Integer post_like;
	private Integer post_visb;
	
	public String getPost_ID() {
		return post_ID;
	}
	public void setPost_ID(String post_ID) {
		this.post_ID = post_ID;
	}
	public String getPoster_ID() {
		return poster_ID;
	}
	public void setPoster_ID(String poster_ID) {
		this.poster_ID = poster_ID;
	}
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	public String getCheck_in_ID() {
		return check_in_ID;
	}
	public void setCheck_in_ID(String check_in_ID) {
		this.check_in_ID = check_in_ID;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public Date getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}
	public Integer getPost_like() {
		return post_like;
	}
	public void setPost_like(Integer post_like) {
		this.post_like = post_like;
	}
	public Integer getPost_visb() {
		return post_visb;
	}
	public void setPost_visb(Integer post_visb) {
		this.post_visb = post_visb;
	}
	
}
