package com.meetU.filerec.model;

import java.util.*;

public interface FileRecDAO_interface {

	public void insert(FileRecVO filerecVO);

	public void update(FileRecVO filerecVO);

	public void delete(FileRecVO filerecVO);

	public FileRecVO findByPrimaryKey(String file_ID);

	public List<FileRecVO> getALL();

//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<LiveVO> getAll(Map<String, String[]> map); 

}
