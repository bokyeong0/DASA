package com.dasa.communication.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dasa.communication.vo.ReceiverVo;


public interface ReceiverDAO {
	public List<ReceiverVo> getOrganTreeList(Map<String, String> map)  throws SQLException;

	public List<ReceiverVo> getStoreTreeList(Map<String, String> map)  throws SQLException;

	public List<ReceiverVo> getEmployeeList(ReceiverVo receiverVo)  throws SQLException;
	
	public List<ReceiverVo> receiverList(String bmInnb) throws SQLException;
	

}
