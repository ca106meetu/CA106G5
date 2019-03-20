package com.meetU.live_chat.model;

import java.sql.Timestamp;
import java.util.List;

import com.meetU.ad.model.AdVO;
import com.meetU.filerec.model.FileRecVO;
import com.meetU.live_like.model.Live_likeVO;

public class Live_chatService {
	private Live_chatDAO_interface dao;

	public Live_chatService() {
		dao = new Live_chatDAO();
	}

//  新增
	public Live_chatVO addLive_chat(String mem_ID, String host_ID, String chat_cont, Timestamp chat_date,String chat_type) {
		Live_chatVO live_chatVO = new Live_chatVO();

		live_chatVO.setHost_ID(host_ID);
		live_chatVO.setMem_ID(mem_ID);
		live_chatVO.setChat_cont(chat_cont);
		live_chatVO.setChat_date(chat_date);
		live_chatVO.setChat_type(chat_type);

		dao.insert(live_chatVO);
		return live_chatVO;
	}

//	條件查詢
	public List<Live_chatVO> getOneLive_chat(String host_ID) {
		List<Live_chatVO> list = dao.findByPrimaryKey(host_ID);
		return list;
	}

//	查詢全部
	public List<Live_chatVO> getAll() {
		List<Live_chatVO> list = dao.getALL();
		return list;
	}

}
