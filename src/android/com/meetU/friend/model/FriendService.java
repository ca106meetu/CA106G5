package android.com.meetU.friend.model;

import java.util.List;


public class FriendService {
	public FriendDAO_interface dao;
	
	public FriendService() {
		dao = new FriendDAO();
	}
	public boolean addFriend(String mem_ID, String friend_mem_ID, Integer relation_status, Integer friend_intimate) {
		
		FriendVO friendVO = new FriendVO();
		
		friendVO.setMem_ID(mem_ID);
		friendVO.setFriend_mem_ID(friend_mem_ID);
		friendVO.setRelation_status(relation_status);
		friendVO.setFriend_intimate(friend_intimate);
		
		return dao.insert(friendVO);
	}
	
	public FriendVO updateFriend(String mem_ID, String friend_mem_ID, Integer relation_status, Integer friend_intimate) {
		FriendVO friendVO = new FriendVO();
		
		friendVO.setMem_ID(mem_ID);
		friendVO.setFriend_mem_ID(friend_mem_ID);
		friendVO.setRelation_status(relation_status);
		friendVO.setFriend_intimate(friend_intimate);
		
		dao.update(friendVO);
		
		return friendVO;
	}
	
	public void deleteFriend(String mem_ID, String friend_mem_ID) {
		dao.delete(mem_ID, friend_mem_ID);
	}
	
	public List<FriendVO> getPartOfOneFriend(String mem_ID) {
		return dao.findByPartOfOnePrimaryKey(mem_ID);
	}
	
	public FriendVO getOneFriend(String mem_ID, String friend_mem_ID) {
		return dao.findByPrimaryKey(mem_ID, friend_mem_ID);
	}
	
	public List<FriendVO> getAll() {
		return dao.getAll();
	}
}
