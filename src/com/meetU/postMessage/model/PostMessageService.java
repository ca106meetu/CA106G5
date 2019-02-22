package com.meetU.postMessage.model;

import java.sql.Timestamp;
import java.util.List;


public class PostMessageService {
	
	private PostMessageDAO_interface dao;
	
	public PostMessageService() {
		dao = new PostMessageDAO();
	}
	
	public PostMessageVO addMessage(String post_ID, String mem_ID,
			Timestamp publish_time,String msg_content,Integer msg_like) {
		
		PostMessageVO postMessageVO = new PostMessageVO();
		
		postMessageVO.setPost_ID(post_ID);
		postMessageVO.setMem_ID(mem_ID);
		postMessageVO.setPublish_time(publish_time);
		postMessageVO.setMsg_content(msg_content);
		postMessageVO.setMsg_like(msg_like);
		dao.insert(postMessageVO);
		
		return postMessageVO;
	}
	
	public PostMessageVO updateMessage(String msg_ID, String post_ID, String  mem_ID
			, String msg_content,Timestamp publish_time,Integer msg_like) {
		
		PostMessageVO postMessageVO = new PostMessageVO();
		
		postMessageVO.setMsg_ID(msg_ID);
		postMessageVO.setPost_ID(post_ID);
		postMessageVO.setMem_ID(mem_ID);
		postMessageVO.setMsg_content(msg_content);
		postMessageVO.setPublish_time(publish_time);
		postMessageVO.setMsg_like(msg_like);
		
		dao.update(postMessageVO);
		
		return postMessageVO;
	}
	
	public PostMessageVO getOneMessage(String msg_ID) {
		return dao.findByPrimaryKey(msg_ID);
	}
	
	public void deleteMessage(String msg_ID) {
		dao.delete(msg_ID);
	}
	
	public List<PostMessageVO> getAll() {
		return dao.getAll();
	}

}
