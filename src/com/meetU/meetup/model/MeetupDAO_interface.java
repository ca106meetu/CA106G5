package com.meetU.meetup.model;

import java.util.List;

public interface MeetupDAO_interface {
	
	public void insert(MeetupVO meetupVO);
	public void update(MeetupVO meetupVO);
	public void delete(String meetup_ID);
	public MeetupVO findByPrimaryKey(String meetup_ID);
	public List <MeetupVO> getAll();
	public List <MeetupVO> getHostAll(String mem_ID);
	public List<MeetupVO> getSearchName(String name);
	public List<MeetupVO> getSearchLoc(String location);
}
