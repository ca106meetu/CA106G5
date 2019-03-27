package android.com.meetU.meetup_mem.model;

import java.util.List;

import android.com.meetU.meetup.model.MeetupVO;

public interface MeetupMemDAO_interface {
	
	public boolean insert(MeetupMemVO meetupMemVO);
	public void update(MeetupMemVO meetupMemVO);
	public boolean update_showup(String meetup_ID, String mem_ID);
	public boolean delete(String meetup_ID, String mem_ID);
	public MeetupMemVO findByPrimaryKey(String meetup_ID, String mem_ID);
	public List <MeetupMemVO> findMyAllMeetup(String mem_ID);
	public List <MeetupMemVO> getAll(String meetup_ID);
}
