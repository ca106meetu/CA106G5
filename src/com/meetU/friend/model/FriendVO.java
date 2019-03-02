package com.meetU.friend.model;

import java.io.Serializable;

public class FriendVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String mem_ID;
	private String friend_mem_ID;
	private Integer relation_status;
	private Integer friend_intimate;

	
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	public String getFriend_mem_ID() {
		return friend_mem_ID;
	}
	public void setFriend_mem_ID(String friend_mem_ID) {
		this.friend_mem_ID = friend_mem_ID;
	}
	public Integer getRelation_status() {
		return relation_status;
	}
	public void setRelation_status(Integer relation_status) {
		this.relation_status = relation_status;
	}
	public Integer getFriend_intimate() {
		return friend_intimate;
	}
	public void setFriend_intimate(Integer friend_intimate) {
		this.friend_intimate = friend_intimate;
	}
	
}
