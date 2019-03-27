package android.com.meetU.meetup.model;

import java.util.List;

public interface MeetupDAO_interface {
	
	public void insert(MeetupVO meetupVO);
	public boolean update(MeetupVO meetupVO);
	public void InvisibleUpdate(String meetup_ID);
	
	public void delete(String meetup_ID);
	public MeetupVO findByPrimaryKey(String meetup_ID);
	public List <MeetupVO> getAll();
	public List<MeetupVO> getVisibleAll();
	public List <MeetupVO> getHost(String mem_ID);
	public boolean update_image(String meetup_ID,byte[] meetup_image);
	public byte[] getImage(String meetup_ID);
	
	
}
