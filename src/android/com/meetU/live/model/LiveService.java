package android.com.meetU.live.model;

import java.sql.Timestamp;
import java.util.List;

public class LiveService {
	private LiveDAO_interface dao;

	public LiveService() {
		dao = new LiveDAO();
	}

//  新增
	public LiveVO addLive(String host_ID, String live_name, Integer live_acc, Timestamp live_date,
			Integer live_status) {
		LiveVO liveVO = new LiveVO();

		liveVO.setHost_ID(host_ID);
		liveVO.setLive_name(live_name);
		liveVO.setLive_acc(live_acc);
//		liveVO.setLive_pic(live_pic);
		liveVO.setLive_date(live_date);
		liveVO.setLive_status(live_status);
		dao.insert(liveVO);

		return liveVO;
	}

//  修改
	public LiveVO updateLive(String host_ID, String live_name, Integer live_acc, byte[] live_pic, Timestamp live_date,
			Integer live_status) {
		LiveVO liveVO = new LiveVO();
		liveVO.setHost_ID(host_ID);
		liveVO.setLive_name(live_name);
		liveVO.setLive_acc(live_acc);
//		liveVO.setLive_pic(live_pic);
		liveVO.setLive_date(live_date);
		liveVO.setLive_status(live_status);
		dao.update(liveVO);

		return liveVO;
	}

//	刪除
	public void deleteLive(String host_ID) {
		LiveVO liveVO = new LiveVO();
		liveVO.setHost_ID(host_ID);
		dao.delete(liveVO);
	}

//	條件查詢
	public LiveVO getOneLive(String host_ID) {
		LiveVO liveVO = dao.findByPrimaryKey(host_ID);
		return liveVO;
	}

//	查詢全部
	public List<LiveVO> getAll() {
		List<LiveVO> list = dao.getALL();
		return list;

	}
	
	public byte[] getImage(String host_ID) {
		return dao.getImage(host_ID);
	}

}
