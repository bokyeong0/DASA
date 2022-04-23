package com.dasa.activity.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

import com.dasa.activity.vo.ActivityEvnMobileSaveVo;
import com.dasa.activity.vo.ActivityFixingEvnVo;
import com.dasa.activity.vo.ActivityFixingOddVo;
import com.dasa.activity.vo.ActivityFixingPogOptionVo;
import com.dasa.activity.vo.ActivityGridMobileVo;
import com.dasa.activity.vo.ActivityGridVo;
import com.dasa.activity.vo.ActivityFixingTrtVo;
import com.dasa.activity.vo.ActivityEmVo;
import com.dasa.activity.vo.ActivityFixingWorkVo;
import com.dasa.activity.vo.ActivityOddMobileVo;
import com.dasa.activity.vo.ActivityRndOddVo;
import com.dasa.activity.vo.ActivitySaveVo;
import com.dasa.activity.vo.ActivityTrtMobileVo;
import com.vertexid.vo.NaviVo;

public interface ActivityFixingDAO {


	public List<ActivityFixingPogOptionVo> prdOptionList(String optionCode)throws SQLException;

	public List<ActivityFixingPogOptionVo> entOptionList(String optionCode)throws SQLException;

	public int entOptionSave(ActivityFixingPogOptionVo vo)throws SQLException;

	public int prdOptionSave(ActivityFixingPogOptionVo vo)throws SQLException;

	public List<ActivityFixingPogOptionVo> fullOptionList(String optionCode)throws SQLException;

	//활동관리 고정여사원 활동상황 조회
	public ActivityEmVo getActivityFixingView(ActivityEmVo activityFixingVo)throws SQLException;

	//활동관리 고정여사원 전체행수조회
	public NaviVo getActivityFixingListCnt(NaviVo naviVo) throws SQLException;

	//활동관리 고정여사원 리스트 조회
	public List<ActivityEmVo> getActivityFixingList(NaviVo naviVo)throws SQLException;

	//활동관리 프로필 사진 적용
	public String saveActivityFixingEmpAmNo(ActivityEmVo activityFixingVo)throws SQLException;
	
	//pog 그리드
	public ActivityGridVo optionPoglist(ActivityGridVo vo)throws SQLException , Exception;
	//보조진열현황 그리드
	public ActivityGridVo optionBiglist(ActivityGridVo vo)throws SQLException,Exception  ;
	//PD매대진열현황 그리드
	public ActivityGridVo optionPdlist(ActivityGridVo vo) throws SQLException, Exception;
	//시황및 매장특이사항
	public List<ActivityFixingOddVo> optionOddlist(Map<String,String> map)throws SQLException;
	//모바일 - 시황및 매장특이사항
	public ActivityOddMobileVo m_optionOddlist(Map<String,String> map)throws SQLException ,Exception;
	//모바일 - 시황및 매장특이사항 저장
	@Transactional
	public int m_optionOddsave(ActivityFixingOddVo vo)throws SQLException,Exception;
	@Transactional
	public int m_optionOddupdate(ActivityFixingOddVo vo)throws SQLException,Exception;
	//행사매대
	public List<ActivityFixingEvnVo> optionEvnlist(Map<String, String> map)throws SQLException;
	//행사매대 해더
	public List<ActivityFixingEvnVo> optionEvnColumnlist(Map<String, String> map)throws SQLException;
	//근무계획
	public ActivityFixingWorkVo fixingWorklist(Map<String, String> map)throws SQLException;
	//모바일 - 근무계획
	public ActivityFixingWorkVo m_fixingWorklist(Map<String, String> map)throws SQLException;
	//근무계획 저장
	public int fixingWorkSave(ActivityFixingWorkVo vo)throws SQLException;
	
	//모바일 - 근무계획 저장
	@Transactional
	public int m_fixingWorkSave(ActivityFixingWorkVo vo)throws SQLException;

	public List<ActivityFixingTrtVo> fixingTrtlist(Map<String, String> map)throws SQLException;
	
	//모바일 - 취급현황품목 조회
	public ActivityTrtMobileVo m_fixingTrtlist(Map<String, String> map)throws SQLException,Exception;

	//모바일 - 휘급현황품목 저장
	@Transactional
	public int m_fixingTrtsave(ActivitySaveVo vo)throws SQLException, Exception;
//	//모바일 - 휘급현황품목 저장
//	public int m_fixingTrtsave(ActivityFixingTrtVo vo)throws SQLException, Exception;
	//모바일  POG및 현재 진열줄수
	public JSONObject fixingMPoglist(ActivityGridVo vo)throws SQLException, Exception;
	//모바일 보조진열현황
	public ActivityGridMobileVo fixingMobileBiglist(ActivityGridVo vo)throws SQLException, Exception;

	//모바일 PD매대현황
	public ActivityGridMobileVo fixingMobilePdlist(ActivityGridVo vo) throws SQLException, Exception ;

	public ModelAndView fixingMobileEvnlist(Map<String, String> map)throws SQLException, Exception ;

	@Transactional
	public int fixingMobileBigSave(ActivitySaveVo vo)throws SQLException, Exception;
	
	@Transactional
	public int fixingMobilePdSave(ActivitySaveVo vo)throws SQLException, Exception;
	
	@Transactional
	public int fixingMobilePogSave(ActivitySaveVo vo)throws SQLException, Exception;

	@Transactional
	public int fixingMobileEvnSave(ActivityEvnMobileSaveVo vo)throws SQLException, Exception;
	
	public int fixingMobileEvnDel(ActivityEvnMobileSaveVo vo)throws SQLException, Exception;

	public ActivityGridVo optionBigInfo(ActivityGridVo vo) throws SQLException;

	public int fixingMobileOddDel(Map<String, String> map)throws SQLException;

	@Transactional
	public int fixMobileInnbSearch(String dfop_innb)throws SQLException ,Exception;
	
	//매장시황 count A20161215 k2s
	public NaviVo fixOddMultiListCnt(NaviVo naviVo) throws SQLException;
	
	//매장시황 list A20161215 k2s
	public List<ActivityFixingOddVo>fixOddMultiList(NaviVo naviVo)throws SQLException;
	
	//동서식품 인터페이스 모바일 매장시황 count A20170823 k2s
	public NaviVo dsFixOddMultiListCnt(NaviVo naviVo)throws SQLException;
	
	//동서식품 인터페이스 모바일 매장시황 list A20170823 k2s
	public List<ActivityFixingOddVo>dsFixOddMultiList(NaviVo naviVo)throws SQLException;
	
}
