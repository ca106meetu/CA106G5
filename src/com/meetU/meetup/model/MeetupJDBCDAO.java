package com.meetU.meetup.model;

import java.util.List;

import com.meetU.meetup.model.MeetupDAO_interface;
import com.meetU.meetup.model.MeetupVO;

public class MeetupJDBCDAO implements MeetupDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G5";
	String passwd = "123456";
	@Override
	public void insert(MeetupVO meetupVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(MeetupVO meetupVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer meetupID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MeetupVO findByPrimaryKey(MeetupVO meetupVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MeetupVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
