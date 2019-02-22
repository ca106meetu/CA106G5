package com.meetU.post.model;

import java.sql.Timestamp;
import java.util.List;

public class PostService {

	private PostDAO_interface dao;
	
	public PostService() {
		dao = new PostDAO();
	}
	
	public PostVO addPost(String poster_ID, String mem_ID,String check_in_ID, 
			Timestamp publish_time,String post_content,Integer post_visb) {
		
		PostVO postVO = new PostVO();
		
		postVO.setPoster_ID(poster_ID);
		postVO.setMem_ID(mem_ID);
		postVO.setCheck_in_ID(check_in_ID);
		postVO.setPublish_time(publish_time);
		postVO.setPost_content(post_content);
		postVO.setPost_visb(post_visb);
		dao.insert(postVO);
		
		return postVO;
	}
	
	public PostVO updatePost(String poster_ID,String  mem_ID,String check_in_ID,String post_content,Timestamp publish_time,Integer post_like,Integer post_visb,String post_ID) {
		
		PostVO postVO = new PostVO();
		
		postVO.setPoster_ID(poster_ID);
		postVO.setMem_ID(mem_ID);
		postVO.setCheck_in_ID(check_in_ID);
		postVO.setPublish_time(publish_time);
		postVO.setPost_content(post_content);
		postVO.setPost_like(post_like);
		postVO.setPost_visb(post_visb);
		postVO.setPost_ID(post_ID);
		
		dao.update(postVO);
		
		return postVO;
	}
	
	public PostVO getOnePost(String post_ID) {
		return dao.findByPrimaryKey(post_ID);
	}
	
	public void deletePost(String post_ID) {
		dao.delete(post_ID);
	}
	
	public List<PostVO> getAll() {
		return dao.getAll();
	}
}
