package com.meetU.meetup_report.model;

import java.util.List;

import com.meetU.meetup.model.MeetupVO;

public interface MeetupRepDAO_interface {
	public void insert(MeetupRepVO meetupRepVO); //前端user
	public void update(MeetupRepVO meetupRepVO);
	public void invisible(String meetup_rep_ID);	//後台管理員;隱形被檢舉的meetup功能
	public MeetupRepVO findByPrimaryKey(String meetup_rep_ID); //後台管理員可依照meetup名稱/編號搜尋 ; TOGGLE/INCLUDE 顯示檢舉全文 
	public List <MeetupRepVO> getAll(); //後台管理員看到的MEETUP檢舉主頁面

}
