package com.meetU.ad.model;

import java.sql.Date;
import java.util.List;

public class AdService {
	private AdDAO_interface dao;

	public AdService() {
		dao = new AdDAO();
	}

//  新增
	public AdVO addAd(String host_ID, String ad_name, byte[] ad_cont, Integer ad_cost, Integer apply_status,
			Date ad_star, Date ad_end) {
		AdVO adVO = new AdVO();

		adVO.setHost_ID(host_ID);
		adVO.setAd_name(ad_name);
		adVO.setAd_cont(ad_cont);
		adVO.setAd_cost(ad_cost);
		adVO.setApply_status(apply_status);
		adVO.setAd_star(ad_star);
		adVO.setAd_end(ad_end);
		dao.insert(adVO);

		return adVO;
	}

//  修改
	public AdVO updateAd(String ad_ID, String host_ID, String ad_name, byte[] ad_cont, Integer ad_cost,
			Integer apply_status, Date ad_star, Date ad_end) {
		AdVO adVO = new AdVO();

		adVO.setAd_ID(ad_ID);
		adVO.setHost_ID(host_ID);
		adVO.setAd_name(ad_name);
		adVO.setAd_cont(ad_cont);
		adVO.setAd_cost(ad_cost);
		adVO.setApply_status(apply_status);
		adVO.setAd_star(ad_star);
		adVO.setAd_end(ad_end);
		dao.update(adVO);

		return adVO;
	}

//	刪除
	public void deleteAd(String ad_ID) {
		AdVO adVO = new AdVO();
		adVO.setAd_ID(ad_ID);
		dao.delete(adVO);
	}

//	條件查詢
	public AdVO getOneAd(String ad_ID) {
		AdVO adVO = dao.findByPrimaryKey(ad_ID);
		return adVO;
	}

//	查詢全部
	public List<AdVO> getAll() {
		List<AdVO> list = dao.getALL();
		return list;
	}
}
