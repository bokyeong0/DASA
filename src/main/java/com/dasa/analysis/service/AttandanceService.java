package com.dasa.analysis.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.analysis.dao.AttandanceDao;
import com.dasa.analysis.vo.AttandanceVo;
import com.vertexid.vo.NaviVo;

public class AttandanceService extends SqlSessionDaoSupport implements AttandanceDao {
	@Override
	public NaviVo selectListCount(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("attandance.selectListCount", naviVo) );
		return naviVo;
	}
	
	@Override
	public List<AttandanceVo> selectList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("attandance.selectList", naviVo);
	}
	
	@Override
	public List<AttandanceVo> selectAllList(AttandanceVo vo) throws SQLException {
		return getSqlSession().selectList("attandance.selectAllList", vo);
	}

	@Override
	public NaviVo selectempMonthListCount(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("attandance.selectempMonthListCount", naviVo) );
		return naviVo;
	}

	@Override
	public List<AttandanceVo> selectempMonthList(NaviVo naviVo)  throws SQLException {
		return getSqlSession().selectList("attandance.selectempMonthList", naviVo);
	}

	@Override
	public List<AttandanceVo> selectExcelExportEmpMonth(AttandanceVo vo) throws SQLException {
		return getSqlSession().selectList("attandance.selectExcelExportEmpMonth", vo);
	}

	@Override
	public void excuteEmpMonthBatch(NaviVo naviVo, String str) throws SQLException {
		getSqlSession().update("attandance.sp_summary_emp_attandance_month", str);
		return;
		
	}
	
	
	
}
