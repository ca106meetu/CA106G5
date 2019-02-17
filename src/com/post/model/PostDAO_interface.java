package com.post.model;

import java.util.*;

public interface PostDAO_interface {
	public void insert(PostVO PostVO);
    public void update(PostVO PostVO);
    public void delete(String post_ID);
//    public PostVO findByPrimaryKey(String post_ID);
    public List<PostVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<PostVO> getAll(Map<String, String[]> map); 

}
