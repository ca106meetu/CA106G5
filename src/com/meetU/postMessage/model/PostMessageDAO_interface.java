package com.meetU.postMessage.model;

import java.util.List;

public interface PostMessageDAO_interface {
	public void insert(PostMessageVO PostMessageVO);
    public void update(PostMessageVO PostMessageVO);
    public void delete(String msg_ID);
    public PostMessageVO findByPrimaryKey(String msg_ID);
    public List<PostMessageVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<PostVO> getAll(Map<String, String[]> map); 
}
