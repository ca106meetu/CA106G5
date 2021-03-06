package android.com.meetU.live_like.model;

import java.util.List;


public class Live_likeService {
	private Live_likeDAO_interface dao;

	public Live_likeService() {
		dao = new Live_likeDAO();
	}

//  新增
	public boolean addLive_like(String mem_ID, String host_ID) {
		Live_likeVO live_likeVO = new Live_likeVO();

		live_likeVO.setMem_ID(mem_ID);
		live_likeVO.setHost_ID(host_ID);
		

		return dao.insert(live_likeVO);
	}

//	刪除
	public boolean deleteLive_like(String mem_ID, String host_ID) {
		Live_likeVO live_likeVO = new Live_likeVO();
		live_likeVO.setMem_ID(mem_ID);
		live_likeVO.setHost_ID(host_ID);
		return dao.delete(live_likeVO);
	}

//	條件查詢
	public List<Live_likeVO> getOneLive_like(String mem_ID) {
		List<Live_likeVO> list = dao.findByPrimaryKey(mem_ID);
		return list;
	}

//	查詢全部
	public List<Live_likeVO> getAll() {
		List<Live_likeVO> list = dao.getALL();
		return list;

	}

}