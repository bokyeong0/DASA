package com.dasa.activity.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;

import com.dasa.activity.vo.ActivityFixingOddVo;
import com.dasa.activity.vo.ActivityGridMobileVo;
import com.dasa.activity.vo.ActivityGridVo;
import com.dasa.activity.vo.ActivityFixingTrtVo;
import com.dasa.activity.vo.ActivityEmVo;
import com.dasa.activity.vo.ActivityOddMobileVo;
import com.dasa.activity.vo.ActivityRndMobileAttendVo;
import com.dasa.activity.vo.ActivityRndOddVo;
import com.dasa.activity.vo.ActivityRndPlanStrVo;
import com.dasa.activity.vo.ActivityRndPlanVo;
import com.dasa.activity.vo.ActivityRndTrtVo;
import com.dasa.activity.vo.ActivitySaveVo;
import com.dasa.activity.vo.ActivityTrtMobileVo;
import com.dasa.activity.vo.ActivityTestVo;
import com.vertexid.vo.NaviVo;

public interface ActivityRndDAO {

	

//	public List<ActivityFixingPogOptionVo> prdOptionList(String optionCode)throws SQLException;
//
//	public List<ActivityFixingPogOptionVo> entOptionList(String optionCode)throws SQLException;

//	public int entOptionSave(ActivityFixingPogOptionVo vo)throws SQLException;
//
//	public int prdOptionSave(ActivityFixingPogOptionVo vo)throws SQLException;

//	public List<ActivityFixingPogOptionVo> fullOptionList(String optionCode)throws SQLException;

	//활동관리 고정여사원 활동상황 조회
	public ActivityEmVo rndView(ActivityEmVo activityFixingVo)throws SQLException;

	//활동관리 고정여사원 전체행수조회
	public NaviVo rndListCnt(NaviVo naviVo) throws SQLException;

	//활동관리 고정여사원 리스트 조회
	public List<ActivityEmVo> rndList(NaviVo naviVo)throws SQLException;

	//활동관리 프로필 사진 적용
	public String rndSaveEmpAiNo(ActivityEmVo activityFixingVo)throws SQLException;
	
	//pog 그리드
//	public ActivityGridVo optionPoglist(ActivityGridVo vo)throws SQLException;
	
	//*****보조진열현황 그리드
	public ActivityGridVo rndBiglist(ActivityGridVo vo)throws SQLException;
	
	//PD매대진열현황 그리드
//	public ActivityGridVo optionPdlist(ActivityGridVo vo) throws SQLException;
	//시황및 매장특이사항
	public List<ActivityRndOddVo>rndOddlist(Map<String,String> map)throws SQLException;
//	//행사매대
//	public List<ActivityFixingEvnVo> optionEvnlist(Map<String, String> map)throws SQLException;
//	//행사매대 해더
//	public List<ActivityFixingEvnVo> optionEvnColumnlist(Map<String, String> map)throws SQLException;
	//근무계획
//	public ActivityFixingWorkVo fixingWorklist(Map<String, String> map)throws SQLException;
	//근무계획 저장
//	public int fixingWorkSave(ActivityFixingWorkVo vo)throws SQLException;

	public List<ActivityRndTrtVo> rndTrtlist(Map<String, String> map)throws SQLException;

	
	
	
	//RND
	public List<ActivityRndPlanStrVo> rndSmList(Map<String, String> map)throws SQLException;
	
	//비고
	//public ActivityGridVo rndAoaList(ActivityGridVo vo)throws SQLException;
	public List<ActivityRndPlanVo> rndAoaList(HashMap<String, String> map)throws SQLException;
	
	//현재진열줄수
	public ActivityGridVo rndCurrList(ActivityGridVo vo)throws SQLException;
	
	//Moblide 현재진열줄수
	public JSONObject rndMobileCurrList(ActivityGridVo vo)throws Exception;
	
	//Mobile CVS순회 업무일지 by zzz2613
	public JSONObject getCvsList(ActivityGridVo vo)throws Exception;

	//Moblide 보조진열현황 그리드
	public ActivityGridMobileVo rndMobileBiglist(ActivityGridVo vo)throws SQLException,Exception;
	
	//Moblide 취급품목현황
	public ActivityTrtMobileVo rndMobileTrtlist(Map<String, String> map)throws SQLException ,Exception;
	
	//Moblide 시황및 매장특이사항
	public ActivityOddMobileVo rndMobileOddlist(Map<String,String> map)throws SQLException,Exception;

	public int rndSaveTest(ActivityTestVo vo)throws SQLException ,Exception;

	@Transactional
	public int rndMobileTrtsave(ActivitySaveVo vo)throws SQLException ,Exception;

	@Transactional
	public int rndMobileCurrSave(ActivitySaveVo vo)throws SQLException ,Exception;

	@Transactional
	public int rndMobileBigSave(ActivitySaveVo vo)throws SQLException ,Exception;

	@Transactional
	public int rndMobileInnbSearch(String dro_innb)throws SQLException ,Exception;
	
	@Transactional
	public int rndMobileOddsave(ActivityRndOddVo vo)throws SQLException ,Exception;
	
	@Transactional
	public int rndMobileOddupdate(ActivityRndOddVo vo)throws SQLException ,Exception;

	@Transactional
	public int rndMobileCvsSave(ActivitySaveVo vo) throws SQLException, Exception;

	@Transactional
	public int rndMobileAttending(ActivityRndMobileAttendVo vo) throws SQLException,Exception;

	public int rndMobileOddDel(Map<String, String> map) throws SQLException,Exception;

	public ActivityGridVo rndPdlist(ActivityGridVo vo) throws SQLException ,Exception;

	public ActivityGridMobileVo rndMobilePdlist(ActivityGridVo vo) throws SQLException ,Exception;

	public int rndMobilePdSave(ActivitySaveVo vo) throws SQLException ,Exception;

	//매장시황 count A20161212 k2s
	public NaviVo rndOddMultiListCnt(NaviVo naviVo) throws SQLException;
	
	//매장시황 list A20161212 k2s
	public List<ActivityRndOddVo>rndOddMultiList(NaviVo naviVo)throws SQLException;
	
	//동서식품 인터페이스 모바일 매장시황 count A20170825 k2s
	public NaviVo dsRndOddMultiListCnt(NaviVo naviVo) throws SQLException;
	
	//동서식품 인터페이스 모바일 매장시황 list A20170825 k2s
	public List<ActivityRndOddVo>dsRndOddMultiList(NaviVo naviVo)throws SQLException;


}
