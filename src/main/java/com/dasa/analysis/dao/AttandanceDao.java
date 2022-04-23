package com.dasa.analysis.dao;

import java.sql.SQLException;
import java.util.List;

import com.dasa.analysis.vo.AttandanceVo;
import com.vertexid.vo.NaviVo;

public interface AttandanceDao {
	
	/**
	 * 사원근무실적
	 * @return List<Attandance>
	 */
	public NaviVo selectListCount(NaviVo naviVo) throws SQLException;
	
	public List<AttandanceVo> selectList(NaviVo naviVo) throws SQLException;
	
	public List<AttandanceVo> selectAllList(AttandanceVo vo) throws SQLException;
	
	/**
	 * 사원근무실적(1일~마지막일)
	 * Create Date: 2017. 09. 21
	 * Editor: K2Ss
	 * @return List<Attandance>
	 */
	public NaviVo selectempMonthListCount(NaviVo naviVo) throws SQLException;
	
	public List<AttandanceVo> selectempMonthList(NaviVo naviVo) throws SQLException;
	
	public List<AttandanceVo> selectExcelExportEmpMonth(AttandanceVo vo) throws SQLException;
	
	public void excuteEmpMonthBatch(NaviVo naviVo, String str) throws SQLException;
	
}
