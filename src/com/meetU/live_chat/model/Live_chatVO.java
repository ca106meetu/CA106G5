package com.meetU.live_chat.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Live_chatVO implements Serializable {

	private static final long serialVersionUID = -7051155640795167972L;
	private String chat_ID;
	private String host_ID;
	private String mem_ID;
	private String chat_cont;
	private Timestamp chat_date;

	public Live_chatVO() {

	}

	public String getChat_ID() {
		return chat_ID;
	}

	public void setChat_ID(String chat_ID) {
		this.chat_ID = chat_ID;
	}

	public String getHost_ID() {
		return host_ID;
	}

	public void setHost_ID(String host_ID) {
		this.host_ID = host_ID;
	}

	public String getMem_ID() {
		return mem_ID;
	}

	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}

	public String getChat_cont() {
		return chat_cont;
	}

	public void setChat_cont(String chat_cont) {
		this.chat_cont = chat_cont;
	}

	public Timestamp getChat_date() {
		return chat_date;
	}

	public void setChat_date(Timestamp chat_date) {
		this.chat_date = chat_date;
	}

}
