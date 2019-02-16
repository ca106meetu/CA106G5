package com.meetU.meetup.model;

import java.util.List;

import com.meetU.meetup.model.MeetupVO;

public interface MeetupDAO_interface {
	
	public void insert(MeetupVO meetupVO);
	public void update(MeetupVO meetupVO);
	public void delete(Integer meetupID);
	public MeetupVO findByPrimaryKey(MeetupVO meetupVO);
	public List <MeetupVO> getAll();
}
