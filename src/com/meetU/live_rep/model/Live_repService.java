package com.meetU.live_rep.model;

import java.sql.Timestamp;
import java.util.List;

import com.meetU.filerec.model.FileRecVO;

public class Live_repService {

	private Live_repDAO_interface dao;

	public Live_repService() {
		dao = new Live_repDAO();
	}

//  新增
	public Live_repVO addLive_rep(String host_ID, String mem_ID, String rep_cont, Timestamp rep_date,
			Integer rep_status, String rep_ans, Timestamp rep_ans_date) {

		Live_repVO live_repVO = new Live_repVO();

		live_repVO.setHost_ID(host_ID);
		live_repVO.setMem_ID(mem_ID);
		live_repVO.setRep_cont(rep_cont);
		live_repVO.setRep_date(rep_date);
		live_repVO.setRep_status(rep_status);
		live_repVO.setRep_ans(rep_ans);
		live_repVO.setRep_ans_date(rep_ans_date);

		dao.insert(live_repVO);

		return live_repVO;
	}

//  修改
	public Live_repVO updateLive_rep(String rep_ID, String host_ID, String mem_ID, String rep_cont, Timestamp rep_date,
			Integer rep_status, String rep_ans, Timestamp rep_ans_date) {

		Live_repVO live_repVO = new Live_repVO();

		live_repVO.setRep_ID(rep_ID);
		live_repVO.setHost_ID(host_ID);
		live_repVO.setMem_ID(mem_ID);
		live_repVO.setRep_cont(rep_cont);
		live_repVO.setRep_date(rep_date);
		live_repVO.setRep_status(rep_status);
		live_repVO.setRep_ans(rep_ans);
		live_repVO.setRep_ans_date(rep_ans_date);

		dao.update(live_repVO);

		return live_repVO;
	}

//	條件查詢
	public List<Live_repVO> getOneLive_rep(String mem_ID) {
		List<Live_repVO> list = dao.findByPrimaryKey(mem_ID);
		return list;
	}

//	查詢全部
	public List<Live_repVO> getAll() {
		List<Live_repVO> list = dao.getALL();
		return list;

	}

}
