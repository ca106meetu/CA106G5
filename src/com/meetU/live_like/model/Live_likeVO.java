package com.meetU.live_like.model;

import java.io.Serializable;

public class Live_likeVO implements Serializable {

	private static final long serialVersionUID = -7678454388068807649L;
	private String mem_ID;
	private String host_ID;

	public Live_likeVO() {

	}

	public String getMem_ID() {
		return mem_ID;
	}

	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}

	public String getHost_ID() {
		return host_ID;
	}

	public void setHost_ID(String host_ID) {
		this.host_ID = host_ID;
	}

}
