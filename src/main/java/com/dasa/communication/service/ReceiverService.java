package com.dasa.communication.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.communication.dao.ReceiverDAO;
import com.dasa.communication.vo.ReceiverVo;

public class ReceiverService extends SqlSessionDaoSupport implements ReceiverDAO {
	@Override
	public List<ReceiverVo> getOrganTreeList(Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("receiver.organTreeList", map);
	}

	@Override
	public List<ReceiverVo> getStoreTreeList(Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("receiver.storeTreeList", map);
	}

	@Override
	public List<ReceiverVo> getEmployeeList(ReceiverVo receiverVo) throws SQLException {
		return getSqlSession().selectList("receiver.employeeList", receiverVo);
	}
	
	@Override
	public List<ReceiverVo> receiverList(String bmInnb) throws SQLException { // 171020 kmh
		return getSqlSession().selectList("receiver.receiverList", bmInnb);
	}
}
