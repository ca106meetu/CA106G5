package android.com.meetU.live.model;

import java.util.*;

public interface LiveDAO_interface {

	public void insert(LiveVO liveVO);

	public void update(LiveVO liveVO);

	public void delete(LiveVO liveVO);

	public LiveVO findByPrimaryKey(String host_ID);

	public List<LiveVO> getALL();
	
	public byte[] getImage(String host_ID);

//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<LiveVO> getAll(Map<String, String[]> map); 

}
