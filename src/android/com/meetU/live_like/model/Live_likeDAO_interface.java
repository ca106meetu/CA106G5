package android.com.meetU.live_like.model;

import java.util.*;

public interface Live_likeDAO_interface {

	public boolean insert(Live_likeVO live_likeVO);

	public boolean delete(Live_likeVO live_likeVO);

	public List<Live_likeVO> findByPrimaryKey(String mem_ID);

	public List<Live_likeVO> getALL();


}