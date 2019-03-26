package com.meetU.meetup_mem.model;

import java.util.List;
import java.util.Set;

public interface MeetupMemDAO_interface {
	
	public void insert(MeetupMemVO meetupMemVO);
	public void update(MeetupMemVO meetupMemVO);
	public void delete(String meetup_ID, String mem_ID);
	public MeetupMemVO findByPrimaryKey(String meetup_ID, String mem_ID);
	public List <MeetupMemVO> findMyAllMeetup(String mem_ID);
	public List <MeetupMemVO> getAll(String meetup_ID);
	public Set<MeetupMemVO> findHostRate(String mem_ID);
}
