package com.dasa.activity.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dasa.activity.vo.ActivityEventMonthVo;
import com.vertexid.vo.NaviVo;

public interface EventMonthDAO {


	public NaviVo emmListCnt(NaviVo naviVo) throws SQLException;

	public List<Map<String, Object>> emmList(NaviVo naviVo)throws SQLException;

	public int emmSave(Map<String, Object> param) throws SQLException;

	public Map<String, Object> emmView(Map<String, Object> param) throws SQLException;
	
	public int emmDel(Map<String, Object> param) throws SQLException;
	
	public int emmUpt(Map<String, Object> param) throws SQLException;
	
	public List<Map<String, Object>> emiSmList(Map<String, Object> param)throws SQLException;
	
	public NaviVo emiListCnt(NaviVo naviVo) throws SQLException;

	public List<Map<String, Object>> emiList(NaviVo naviVo)throws SQLException;
	
	public int m_fileUpload(ActivityEventMonthVo vo) throws SQLException;
	
	public int emiDel(Map<String, Object> param) throws SQLException;
}
