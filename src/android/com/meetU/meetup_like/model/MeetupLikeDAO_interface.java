package android.com.meetU.meetup_like.model;

import java.util.List;

import android.com.meetU.meetup_mem.model.MeetupMemVO;

public interface MeetupLikeDAO_interface {
	
	public boolean insert(MeetupLikeVO meetupLikeVO);
	public boolean delete(String meetup_ID, String mem_ID);
	public MeetupLikeVO findByPrimaryKey(String meetup_ID, String mem_ID);
	public List <MeetupLikeVO> getAll(String mem_ID);
}
