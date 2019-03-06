package com.meetU.filerec.model;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import com.meetU.live.model.LiveVO;

public class FileRecService {
	private FileRecDAO_interface dao;

	public FileRecService() {
		dao = new FileRecDAO();
	}

//  新增
	public FileRecVO addFileRec(String host_ID, String file_name, String live_des, String file_cont,
			Timestamp file_date, Integer file_pop) {
		FileRecVO filerecVO = new FileRecVO();

		filerecVO.setHost_ID(host_ID);
		filerecVO.setFile_name(file_name);
		filerecVO.setLive_des(live_des);
		filerecVO.setFile_cont(file_cont);
		filerecVO.setFile_date(file_date);
		filerecVO.setFile_pop(file_pop);
		dao.insert(filerecVO);

		return filerecVO;
	}

//  修改
	public FileRecVO updateFileRec(String file_ID, String host_ID, String file_name, String live_des, String file_cont,
			Timestamp file_date, Integer file_pop) {
		FileRecVO filerecVO = new FileRecVO();
		filerecVO.setFile_ID(file_ID);
		filerecVO.setHost_ID(host_ID);
		filerecVO.setFile_name(file_name);
		filerecVO.setLive_des(live_des);
		filerecVO.setFile_cont(file_cont);
		filerecVO.setFile_date(file_date);
		filerecVO.setFile_pop(file_pop);
		dao.update(filerecVO);

		return filerecVO;
	}

//	刪除
	public void deleteFileRec(String file_ID) {
		FileRecVO filerecVO = new FileRecVO();
		filerecVO.setFile_ID(file_ID);
		dao.delete(filerecVO);
	}

//	條件查詢
	public List<FileRecVO> getOneFileRec(String host_ID) {
		List<FileRecVO> list = dao.findByPrimaryKey(host_ID);
		return list;
	}

//	查詢全部
	public List<FileRecVO> getAll() {
		List<FileRecVO> list = dao.getALL();
		return list;

	}

}
