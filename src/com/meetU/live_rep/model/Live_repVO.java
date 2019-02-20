package com.meetU.live_rep.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Live_repVO implements Serializable {

	private static final long serialVersionUID = 5059624854099057176L;
	private String rep_ID;
	private String host_ID;
	private String men_ID;
	private String rep_cont;
	private Timestamp rep_date;
	private Integer rep_status;
	private String rep_ans;
	private Timestamp rep_ans_date;

	public Live_repVO() {

	}

	public String getRep_ID() {
		return rep_ID;
	}

	public void setRep_ID(String rep_ID) {
		this.rep_ID = rep_ID;
	}

	public String getHost_ID() {
		return host_ID;
	}

	public void setHost_ID(String host_ID) {
		this.host_ID = host_ID;
	}

	public String getMen_ID() {
		return men_ID;
	}

	public void setMen_ID(String men_ID) {
		this.men_ID = men_ID;
	}

	public String getRep_cont() {
		return rep_cont;
	}

	public void setRep_cont(String rep_cont) {
		this.rep_cont = rep_cont;
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
