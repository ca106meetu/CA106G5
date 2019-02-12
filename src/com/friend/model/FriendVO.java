package com.friend.model;

public class FriendVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String mem_id;
	private String friend_mem_id;
	private Integer relation_status;
	private Integer friend_intimate;

	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getFriend_mem_id() {
		return friend_mem_id;
	}
	public void setFriend_mem_id(String friend_mem_id) {
		this.friend_mem_id = friend_mem_id;
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
