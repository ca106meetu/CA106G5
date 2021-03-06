package android.com.meetU.meetup.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import android.com.meetU.meetup.model.MeetupVO;

public class MeetupService {
	
	private MeetupDAO_interface dao;
	
	public MeetupService() {
		dao = new MeetupDAO();
	}
	
	public MeetupVO addMeetup(String meetup_name, String mem_ID, Timestamp meetup_date, 
			String meetup_loc, Integer meetup_status, 
			String meetup_info, Integer meetup_minppl, Integer meetup_maxppl, Date meetup_joindate) {
		//新增
		MeetupVO meetupVO1 = new MeetupVO();
		meetupVO1.setMeetup_name(meetup_name);
		meetupVO1.setMem_ID(mem_ID);
		meetupVO1.setMeetup_date(meetup_date);
		meetupVO1.setMeetup_loc(meetup_loc);
		meetupVO1.setMeetup_status(meetup_status);
		meetupVO1.setMeetup_info(meetup_info);
		meetupVO1.setMeetup_minppl(meetup_minppl);
		meetupVO1.setMeetup_maxppl(meetup_maxppl);
		meetupVO1.setMeetup_joindate(meetup_joindate);
		dao.insert(meetupVO1);
		return meetupVO1;
	}
	
	public MeetupVO updateMeetup(String meetup_ID, String meetup_name, Timestamp meetup_date, 
			String meetup_loc, Integer meetup_status, String meetup_info, 
			Integer meetup_minppl, Integer meetup_maxppl, Date meetup_joindate) {
		//update
		MeetupVO meetupVO2 = new MeetupVO();
		meetupVO2.setMeetup_ID(meetup_ID);
		meetupVO2.setMeetup_name(meetup_name);
		meetupVO2.setMeetup_date(meetup_date);
		meetupVO2.setMeetup_loc(meetup_loc);
		meetupVO2.setMeetup_status(meetup_status);
		meetupVO2.setMeetup_info(meetup_info);
		meetupVO2.setMeetup_minppl(meetup_minppl);
		meetupVO2.setMeetup_maxppl(meetup_maxppl);
		meetupVO2.setMeetup_joindate(meetup_joindate);
		dao.update(meetupVO2);
		return meetupVO2;
	}
	
	public void deleteMeetup(String meetup_ID) {
		dao.delete(meetup_ID);
	}

	public MeetupVO getOneMeetup(String meetup_ID) {
		return dao.findByPrimaryKey(meetup_ID);
	}

	public List<MeetupVO> getAll() {
		return dao.getAll();
	}
	
	public List<MeetupVO> getHost(String mem_ID) {
		return dao.getHost(mem_ID);
	}
	
	public void InvisibleUpdate(String meetup_ID) {
		//Invisible
		dao.InvisibleUpdate(meetup_ID);
	}
	
	public List<MeetupVO> getVisibleAll() {
		return dao.getVisibleAll();
	}
	
	public byte[] getImage(String meetup_ID) {
		return dao.getImage(meetup_ID);
	}
	
	public boolean updateMeetupImage(String meetup_ID, byte[] meetup_image) {
		return dao.update_image(meetup_ID, meetup_image);
	}
}
