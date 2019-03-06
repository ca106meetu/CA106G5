package com.meetU.meetup_report.model;

import java.sql.Date;
import java.util.List;

import com.meetU.meetup.model.MeetupVO;

public class MeetupRepService {
	
	private MeetupRepDAO_interface dao;
	
	public MeetupRepService() {
		dao = new MeetupRepDAO();
	}
	
	public MeetupRepVO addMeetupRep(String meetup_ID, String mem_ID, 
			String rep_content, Integer rep_status) {
		
		MeetupRepVO meetupRepVO = new MeetupRepVO();
		meetupRepVO.setMeetup_ID(meetup_ID);
		meetupRepVO.setMem_ID(mem_ID);
		meetupRepVO.setRep_content(rep_content);
		meetupRepVO.setRep_status(rep_status);
		dao.insert(meetupRepVO);
		return meetupRepVO;
	}
	
	public MeetupRepVO updateMeetupRep(Integer rep_status, 
			String rep_ans, String meetup_rep_ID) {	
		MeetupRepVO meetupRepVO = new MeetupRepVO();
		meetupRepVO.setRep_status(rep_status);
		meetupRepVO.setRep_ans(rep_ans);
		meetupRepVO.setMeetup_rep_ID(meetup_rep_ID);
		dao.update(meetupRepVO);
		return meetupRepVO;
	}
	
	
	public void deleteMeetupRep(String meetup_rep_ID) {
		dao.invisible(meetup_rep_ID);
	}

	public MeetupRepVO getOneMeetupRep(String meetup_rep_ID) {
		return dao.findByPrimaryKey(meetup_rep_ID);
	}

	public List<MeetupRepVO> getAll() {
		return dao.getAll();
	}	
}
