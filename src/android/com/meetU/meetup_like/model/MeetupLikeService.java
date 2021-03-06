package android.com.meetU.meetup_like.model;

import java.util.List;

import android.com.meetU.meetup.model.MeetupVO;

public class MeetupLikeService {
	
	private MeetupLikeDAO_interface dao;
	
	public MeetupLikeService() {
		dao=new MeetupLikeDAO();
	}
	
	public boolean addMeetupLike(String meetup_ID, String mem_ID) {
		
		MeetupLikeVO meetupLikeVO = new MeetupLikeVO();
		meetupLikeVO.setMeetup_ID(meetup_ID);
		meetupLikeVO.setMem_ID(mem_ID);
		
		return dao.insert(meetupLikeVO);
	}
	
	public boolean deleteMeetupLike(String meetup_ID, String mem_ID) {
		return dao.delete(meetup_ID, mem_ID);
	}
	
	public MeetupLikeVO getOneMeetupLike(String meetup_ID, String mem_ID) {
		return dao.findByPrimaryKey(meetup_ID,mem_ID);
	}
	
	public List<MeetupLikeVO> getAll(String mem_ID) {
		return dao.getAll(mem_ID);
	}
}
