package com.meetU.meetup_like.model;

import java.util.List;
import java.util.Set;

public class MeetupLikeService {
	
	private MeetupLikeDAO_interface dao;
	
	public MeetupLikeService() {
		dao=new MeetupLikeDAO();
	}
	
	public MeetupLikeVO addMeetupLike(String meetup_ID, String mem_ID) {
		
		MeetupLikeVO meetupLikeVO = new MeetupLikeVO();
		meetupLikeVO.setMeetup_ID(meetup_ID);
		meetupLikeVO.setMem_ID(mem_ID);
		dao.insert(meetupLikeVO);
		return meetupLikeVO;
	}
	
	public void deleteMeetupLike(String meetup_ID, String mem_ID) {
		dao.delete(meetup_ID, mem_ID);
	}
	
	public MeetupLikeVO getOneMeetupLike(String meetup_ID, String mem_ID) {
		return dao.findByPrimaryKey(meetup_ID,mem_ID);
	}
	
	public List<MeetupLikeVO> getAll(String mem_ID) {
		return dao.getAll(mem_ID);
	}
	
	public Set <MeetupLikeVO> LikeByWho(String meetup_ID) {
		return dao.LikeByWho(meetup_ID);
	}
}
