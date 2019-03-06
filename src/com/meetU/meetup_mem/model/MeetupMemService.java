package com.meetU.meetup_mem.model;

import java.sql.Date;
import java.util.List;

public class MeetupMemService {
private MeetupMemDAO_interface dao;
	
	public MeetupMemService() {
		dao = new MeetupMemDAO();
	}
	
	public MeetupMemVO addMeetupMem(String meetup_ID, String mem_ID) {
		//新增
		MeetupMemVO meetupMemVO1 = new MeetupMemVO();
		meetupMemVO1.setMeetup_ID(meetup_ID);
		meetupMemVO1.setMem_ID(mem_ID);
		dao.insert(meetupMemVO1);
		return meetupMemVO1;
	}
	
	public MeetupMemVO updateMeetupMem(String meetup_ID, String mem_ID, 
			Integer meetup_rate, String meetup_comment) {
		//update
		MeetupMemVO meetupMemVO2 = new MeetupMemVO();
		meetupMemVO2.setMeetup_ID(meetup_ID);
		meetupMemVO2.setMem_ID(mem_ID);
		meetupMemVO2.setMeetup_rate(meetup_rate);
		meetupMemVO2.setMeetup_comment(meetup_comment);
		dao.update(meetupMemVO2);
		return meetupMemVO2;
	}
	
	public void deleteMeetupMem(String meetup_ID, String mem_ID) {
		dao.delete(meetup_ID, mem_ID);
	}

	public MeetupMemVO getOneMeetupMem(String meetup_ID, String mem_ID) {
		return dao.findByPrimaryKey(meetup_ID, mem_ID);
	}

	public List<MeetupMemVO> getAll(String meetup_ID) {
		return dao.getAll(meetup_ID);
	}

	public List<MeetupMemVO> getMyAllMeetup(String mem_ID) {
		return dao.findMyAllMeetup(mem_ID);
	}
}
