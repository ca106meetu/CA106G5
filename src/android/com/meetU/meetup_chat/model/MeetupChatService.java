package android.com.meetU.meetup_chat.model;

import java.util.List;

public class MeetupChatService {
	
	private MeetupChatDAO_interface dao;
	
	public MeetupChatService() {
		dao = new MeetupChatDAO();
	}
	
	public MeetupChatVO addMeetupChat(String meetup_ID, String mem_ID, String Chat_msg) {
		
		MeetupChatVO meetupChatVO = new MeetupChatVO();
		meetupChatVO.setMeetup_ID(meetup_ID);
		meetupChatVO.setMem_ID(mem_ID);
		meetupChatVO.setChat_msg(Chat_msg);
		dao.insert(meetupChatVO);
		return meetupChatVO;
	}
	
	public List<MeetupChatVO> getAll(String meetup_ID){
		return dao.getAll(meetup_ID);
	}
}
