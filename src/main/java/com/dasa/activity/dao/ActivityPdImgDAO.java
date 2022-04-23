package com.dasa.activity.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.dasa.activity.vo.ActivityPdImgVo;
import com.vertexid.vo.NaviVo;

public interface ActivityPdImgDAO {

	/* 고정 */
	public NaviVo fixCnt(NaviVo naviVo) throws SQLException;
	public List<Map<String, Object>> fixList(NaviVo naviVo) throws SQLException;
	@Transactional
	public int m_fixPdImgSave(ActivityPdImgVo vo)throws SQLException,Exception;
	
	/* 순회 */
	public NaviVo rndCnt(NaviVo naviVo) throws SQLException;
	public List<Map<String, Object>> rndList(NaviVo naviVo) throws SQLException;
	@Transactional
	public int m_rndPdImgSave(ActivityPdImgVo vo)throws SQLException,Exception;
	
}
