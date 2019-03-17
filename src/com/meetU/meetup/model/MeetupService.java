package com.meetU.meetup.model;

import java.sql.Date;
import java.util.List;

public class MeetupService {
	
	private MeetupDAO_interface dao;
	
	public MeetupService() {
		dao = new MeetupDAO();
	}
	
	public MeetupVO addMeetup(String meetup_name, String mem_ID, Date meetup_date, 
			String meetup_loc, Integer meetup_status, byte[] meetup_pic, String meetup_info) {
		//新增
		MeetupVO meetupVO1 = new MeetupVO();
		meetupVO1.setMeetup_name(meetup_name);
		meetupVO1.setMem_ID(mem_ID);
		meetupVO1.setMeetup_date(meetup_date);
		meetupVO1.setMeetup_loc(meetup_loc);
		meetupVO1.setMeetup_status(meetup_status);
		meetupVO1.setMeetup_pic(meetup_pic);
		meetupVO1.setMeetup_info(meetup_info);
		dao.insert(meetupVO1);
		return meetupVO1;
	}
	
	public MeetupVO updateMeetup(String meetup_ID, String meetup_name, Date meetup_date, 
			String meetup_loc, Integer meetup_status, byte[] meetup_pic,String meetup_info) {
		//update
		MeetupVO meetupVO2 = new MeetupVO();
		meetupVO2.setMeetup_ID(meetup_ID);
		meetupVO2.setMeetup_name(meetup_name);
		meetupVO2.setMeetup_date(meetup_date);
		meetupVO2.setMeetup_loc(meetup_loc);
		meetupVO2.setMeetup_status(meetup_status);
		meetupVO2.setMeetup_pic(meetup_pic);
		meetupVO2.setMeetup_info(meetup_info);
		dao.update(meetupVO2);
		return meetupVO2;
	}
	
	public void deleteMeetup(String meetup_ID) {
		dao.delete(meetup_ID);
	}

	public MeetupVO getOneMeetup(String meetup_ID) {
		return dao.findByPrimaryKey(meetup_ID);
	}

	public List<MeetupVO> getAll() {
		return dao.getAll();
	}
	
	public List<MeetupVO> getAllByHost(String mem_ID) {
		return dao.getHostAll(mem_ID);
	}
	
	public List<MeetupVO> getSearchName(String name) {
		return dao.getSearchName(name);
	}
	
	public List<MeetupVO> getSearchLoc(String location) {
		return dao.getSearchLoc(location);
	}
}
