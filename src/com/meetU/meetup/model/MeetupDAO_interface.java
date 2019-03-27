package com.meetU.meetup.model;

import java.util.List;
import java.util.Set;

public interface MeetupDAO_interface {
	
	public void insert(MeetupVO meetupVO);
	public void update(MeetupVO meetupVO);
	public void InvisibleUpdate(String meetup_ID); //讓meetup隱形
	
	public void delete(String meetup_ID);
	public MeetupVO findByPrimaryKey(String meetup_ID);
	public List <MeetupVO> getAll();
	public List <MeetupVO> getVisibleAll();
	public List <MeetupVO> getHostAll(String mem_ID);
	public List<MeetupVO> getSearchName(String name);
	public List<MeetupVO> getSearchLoc(String location);
	public Set<MeetupVO> homePG();
}
