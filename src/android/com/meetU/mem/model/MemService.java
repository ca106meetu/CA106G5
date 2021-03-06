package android.com.meetU.mem.model;

import java.sql.*;
import java.util.*;

public class MemService {

	private MemDAO_interface dao;
	public MemService() {
		dao = new MemDAO();
	}
	public boolean addMem(String mem_pw, String mem_acc, String mem_email, Integer mem_code, Integer mem_state, java.sql.Date mem_date, String mem_address ) {
		
		MemVO memVO = new MemVO();
		
		memVO.setMem_pw(mem_pw);
		memVO.setMem_acc(mem_acc);
		memVO.setMem_email(mem_email);
		memVO.setMem_code(mem_code);
		memVO.setMem_state(mem_state);
		memVO.setMem_date(mem_date);
		memVO.setMem_address(mem_address);
		
		return dao.insert(memVO);
	}
	
	public MemVO updateMem(String mem_pw, String mem_name, String mem_acc, String mem_nickname, java.sql.Date mem_bday,
			String mem_email, String mem_pho, String mem_gend, byte[] mem_pic, String mem_intro,
			Integer mem_code, Integer mem_state, java.sql.Date mem_date, java.sql.Timestamp mem_sign_day, Integer mem_login_state,
			String mem_address, java.sql.Timestamp last_pair, String mem_hobby,byte[] mem_QRCODE,Integer mem_get_point, 
			String mem_ID) {
		
		MemVO memVO = new MemVO();
		memVO.setMem_pw(mem_pw);
		memVO.setMem_name(mem_name);
		memVO.setMem_acc(mem_acc);
		memVO.setMem_nickname(mem_nickname);
		memVO.setMem_bday(mem_bday);
		memVO.setMem_email(mem_email);
		memVO.setMem_pho(mem_pho);
		memVO.setMem_gend(mem_gend);
//		memVO.setMem_pic(mem_pic);
		memVO.setMem_intro(mem_intro);
		
		memVO.setMem_code(mem_code);
		memVO.setMem_state(mem_state);
		memVO.setMem_date(mem_date);
		memVO.setMem_sign_day(mem_sign_day);
		memVO.setMem_login_state(mem_login_state);
		memVO.setMem_address(mem_address);
		memVO.setLast_pair(last_pair);
		memVO.setMem_hobby(mem_hobby);
//		memVO.setMem_QRCODE(mem_QRCODE);
		memVO.setMem_get_point(mem_get_point);
		memVO.setMem_ID(mem_ID);
		
		dao.update(memVO);
		
		return memVO;
	}
	public void deleteMem(String mem_ID) {
		dao.delete(mem_ID);
	}
	
	public MemVO getOneMem(String mem_acc) {
		return dao.findByAccount(mem_acc);
	}
	
	public MemVO findByID(String mem_ID) {
		return dao.findByID(mem_ID);
	}
	
	public List<MemVO> getAll() {
		return dao.getAll();
	}
	
	public boolean isMem(String mem_acc,String mem_pw) {
		
		return dao.isMem(mem_acc, mem_pw);
	}
	
	public boolean isAccountExist(String mem_acc) {
		return dao.isAccountExist(mem_acc);
	}
	
	public byte[] getImage(String mem_acc) {
		return dao.getImage(mem_acc);
	}
	
	public byte[] getQRcode(String mem_ID) {
		return dao.getQRcode(mem_ID);
	}
	
	
}
