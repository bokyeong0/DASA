package com.dasa.activity.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.activity.dao.ActivityFixingDAO;
import com.dasa.activity.dao.ActivityTeamleaderDAO;
import com.dasa.activity.vo.ActivityEvnMobileSaveVo;
import com.dasa.activity.vo.ActivityEvnMobileVo;
import com.dasa.activity.vo.ActivityFixingEvnVo;
import com.dasa.activity.vo.ActivityFixingOddVo;
import com.dasa.activity.vo.ActivityFixingPogOptionVo;
import com.dasa.activity.vo.ActivityGridMobileVo;
import com.dasa.activity.vo.ActivityGridVo;
import com.dasa.activity.vo.ActivityFixingTrtVo;
import com.dasa.activity.vo.ActivityEmVo;
import com.dasa.activity.vo.ActivityFixingWorkVo;
import com.dasa.activity.vo.ActivityOddMobileVo;
import com.dasa.activity.vo.ActivityTeamleaderVo;
import com.dasa.activity.vo.ActivityTrtMobileVo;
import com.dasa.activity.vo.ActivitySaveVo;
import com.dasa.analysis.vo.AttandanceVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Encoder;
import com.vertexid.vo.NaviVo;

public class ActivityTeamleaderService extends SqlSessionDaoSupport implements ActivityTeamleaderDAO {
	
	@Override
	public NaviVo selectListCount(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("teamleader.selectListCount", naviVo) );
		return naviVo;
	}
	
	@Override
	public List<ActivityTeamleaderVo> selectList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("teamleader.selectList", naviVo);
	}
	
	@Override
	public List<ActivityTeamleaderVo> selectExcelList(ActivityTeamleaderVo vo) throws SQLException {
		return getSqlSession().selectList("teamleader.selectExcelList", vo);
	}

	@Override
	public NaviVo selectTeamLeaderMonthlyListCount(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow((Integer) getSqlSession().selectOne("teamleader.selectTeamLeaderMonthlyListCount", naviVo) );
		return naviVo;
	}

	@Override
	public List<ActivityTeamleaderVo> selectTeamLeaderMonthlyList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("teamleader.selectTeamLeaderMonthlyList", naviVo);
	}

	@Override
	public List<ActivityTeamleaderVo> selectTeamLeaderMonthlyExcelList(ActivityTeamleaderVo vo) throws SQLException {
		return getSqlSession().selectList("teamleader.selectTeamLeaderMonthlyExcelList", vo);
	}
	
	
}
