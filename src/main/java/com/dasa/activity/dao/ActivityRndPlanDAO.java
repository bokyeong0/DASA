package com.dasa.activity.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dasa.activity.vo.ActivityRndPlanDayVo;
import com.dasa.activity.vo.ActivityRndPlanScheTableVo;
import com.dasa.activity.vo.ActivityRndPlanScheVo;
import com.dasa.activity.vo.ActivityRndPlanStrVo;
import com.dasa.activity.vo.ActivityRndPlanVo;
import com.vertexid.vo.NaviVo;

public interface ActivityRndPlanDAO {
	public List<ActivityRndPlanVo> getEmpListByTeam(Map<String, String> map)throws SQLException;
	
	public List<ActivityRndPlanVo> getActivityRndPlanHeaderList(Map<String, String> map)throws SQLException;

	public List<ActivityRndPlanVo> rndPlanList(HashMap<String, String> map)throws SQLException;

	public List<ActivityRndPlanStrVo> rndPlanStrList(String emNo)throws SQLException;

	public List<ActivityRndPlanStrVo> rndPlanStrSelectList(Map<String, String> map)throws SQLException;

	public List<ActivityRndPlanDayVo> rndPlanDayList(Map<String, String> map)throws SQLException;

	public int rndPlanSave(ActivityRndPlanDayVo vo)throws SQLException;

	public int rndPlanMatterSave(ActivityRndPlanDayVo vo)throws SQLException;

	public int rndMobilePlanSave(ActivityRndPlanDayVo vo)throws SQLException ,Exception;

	public List<ActivityRndPlanScheVo> rndPlanScheList(HashMap<String, String> map)throws SQLException;

	public int rndMobilePlanItemDelete(ActivityRndPlanDayVo vo)throws SQLException;
	
	public List<ActivityRndPlanScheTableVo> rndPlanScheTable(NaviVo naviVo)throws SQLException;
}
