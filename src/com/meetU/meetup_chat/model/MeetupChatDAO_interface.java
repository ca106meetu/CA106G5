package com.meetU.meetup_chat.model;

import java.util.List;

import com.meetU.meetup.model.MeetupVO;

public interface MeetupChatDAO_interface {
	public void insert(MeetupChatVO meetupChatVO);
	public void delete(String meetup_ID, String mem_ID);
	public List <MeetupChatVO> getAll(String meetup_ID);
}