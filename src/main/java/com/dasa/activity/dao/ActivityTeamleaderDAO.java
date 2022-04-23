package com.dasa.activity.dao;

import java.sql.SQLException;
import java.util.List;

import com.dasa.activity.vo.ActivityTeamleaderVo;
import com.dasa.analysis.vo.AttandanceVo;
import com.vertexid.vo.NaviVo;

public interface ActivityTeamleaderDAO {

	public NaviVo selectListCount(NaviVo naviVo) throws SQLException;

	public List<ActivityTeamleaderVo> selectList(NaviVo naviVo) throws SQLException;
	
	public List<ActivityTeamleaderVo> selectExcelList(ActivityTeamleaderVo vo) throws SQLException;

	public NaviVo selectTeamLeaderMonthlyListCount(NaviVo naviVo) throws SQLException;
	
	public List<ActivityTeamleaderVo> selectTeamLeaderMonthlyList(NaviVo naviVo) throws SQLException;
	
	public List<ActivityTeamleaderVo> selectTeamLeaderMonthlyExcelList(ActivityTeamleaderVo vo) throws SQLException;
}
