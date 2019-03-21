package com.meetU.meetup_like.model;

import java.util.List;
import java.util.Set;

public interface MeetupLikeDAO_interface {
	
	public void insert(MeetupLikeVO meetupLikeVO);
	public void delete(String meetup_ID, String mem_ID);
	public MeetupLikeVO findByPrimaryKey(String meetup_ID, String mem_ID);
	public List <MeetupLikeVO> getAll(String mem_ID);
	public Set <MeetupLikeVO> LikeByWho(String meetup_ID);
}
