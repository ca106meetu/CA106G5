package com.meetU.mem.model;

import java.util.List;

public interface MemDAO_interface {
    public void insert(MemVO memVO);
    public void regInsert(String mem_pw, String mem_acc, String mem_email, String mem_address);
    public void update(MemVO memVO);
    public void delete(String mem_ID);
    public MemVO findByPrimaryKey(String mem_ID);
    public MemVO findByACC(String mem_acc, String mem_pw);
    public boolean findByMEM_ACC(String mem_acc);
    public boolean findByMEM_EMAIL(String mem_email);
    public List<MemVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<MemVO> getAll(Map<String, String[]> map); 
	public static void main(String[] args) {
		//REG_INSERT_STMT
	}
}
