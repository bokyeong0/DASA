package com.dasa.activity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.activity.dao.ActivityRndDAO;
//import com.dasa.activity.vo.ActivityFixingOddVo;
import com.dasa.activity.vo.ActivityGridMobileVo;
import com.dasa.activity.vo.ActivityGridVo;
//import com.dasa.activity.vo.ActivityFixingTrtVo;
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
import com.dasa.analysis.vo.DisplayGridVo;
import com.dasa.mobile.vo.MobileAttendingVo;
import com.dasa.mobile.vo.MobileVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.ExcelManager;
import com.vertexid.utill.ImgAttachManager;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.Navi;
import com.vertexid.utill.StringCheck;
import com.vertexid.vo.ExcelVo;
import com.vertexid.vo.NaviVo;

@Controller
public class ActivityRndController {
	
	private ModelAndView mv;				//모델엔뷰
	private JSONObject response;			//json response
	private JSONObject jObj;				//json obj
	private JSONObject jVo;
	private JSONArray jArr;					//json 배열
	
	
	private ActivityEmVo activityRndVo;
	private ActivityTrtMobileVo rndTrtMVo;
	private ActivityOddMobileVo rndOddMVo;
	
	private List<ActivityRndPlanVo> rndPlanVoList;
	private List<ActivityEmVo> activityRndVoList; 
	private List<ActivityRndOddVo> activityRndOddVoList;
	private List<ActivityRndTrtVo> activityFixingTrtVoList;
	private List<ActivityRndPlanStrVo> smVoList;
	
	@Autowired
	private ExcelManager em;						// 파일관리자
	
	@Autowired
	private ImgAttachManager iam;
	
	@Autowired
	private ActivityRndDAO dao;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/10/10-300") 
	public String rndPg(){
		return "10/10-300";
	}
	
	//A20161209 k2s
	@RequestMapping("/10/10-800") 
	public String rndOddPg(){
		return "10/10-800";
	}
	
	@RequestMapping("/m_rnd_attending")
	public ModelAndView m_rnd_fileUpload(@ModelAttribute("vo") MobileAttendingVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]모바일 출근 사진업로드 : " + "/rnd/m_attending am_no : "+ reqMobileVo.getAm_no()+", em_no"+reqMobileVo.getEm_no());
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String req_type = reqMobileVo.getType();//유형
		String req_em_no = reqMobileVo.getEm_no();//사원번호
		int req_am_no = reqMobileVo.getAm_no();//첨부파일 고유번호
		int req_files_length = reqMobileVo.getFiles().length;//첨부파일 개수
		//System.out.println("req_type=" + req_type);
		//System.out.println("req_em_no=" + req_em_no);
		//System.out.println("req_am_no=" + req_am_no);
		//System.out.println("req_files_length=" + req_files_length);
		
		//Check Required Parameter
		if (StringCheck.isNull(req_type) || StringCheck.isNull(req_em_no) || req_files_length == 0) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
//		ImgAttachManager iam =  new  ImgAttachManager();
		int res_am_no = iam.updateFile(reqMobileVo.getFiles(), req_am_no, req_em_no, req_type);
		//System.out.println("res_am_no=" + res_am_no);
		
		if (res_am_no > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_am_no = new JSONArray();

			JSONObject bodyVo = new JSONObject();
			bodyVo.put("am_no", res_am_no);
			body_am_no.add(bodyVo);
			body.put("am_no", body_am_no);
			System.out.println("[" + CommonUtil.getCurrentDateTime() + "]모바일 파일업로드 return am_no : " +res_am_no);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			System.out.println("[" + CommonUtil.getCurrentDateTime() + "]모바일 파일업로드 실패 am_no : " +res_am_no);
			response = CommonUtil.setFailResponse(Constant.FILE_UPLOAD_FAILED_CD, Constant.FILE_UPLOAD_FAILED_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	//업무일지 Tab 매장 목록
	@RequestMapping("/rnd/smList") 
	public ModelAndView rndSmList(@RequestParam("em_no") String emNo, @RequestParam("base_de") String baseDe ) throws Exception{
//		String cm_code = (String)session.getAttribute("login_cp_cd");
//		String om_code = (String)session.getAttribute("login_bhf_cd");
//		String login_no = (String)session.getAttribute("login_no");
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/rnd/smList]  : 업무일지 Tab 매장 목록 em_no : "+emNo+",  base_de : "+baseDe);
		Map<String,String> map = new HashMap<String, String>();
		map.put("em_no", emNo);
		map.put("plan_de", baseDe);
		smVoList =  dao.rndSmList(map);
		jArr = JSONArray.fromObject(smVoList);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jArr );
		return mv;
	}
	//Moblide 업무일지 Tab 매장 목록
	@RequestMapping("/rnd/m_smList") 
	public ModelAndView rndMSmList(@RequestParam("em_no") String emNo, @RequestParam("base_de") String baseDe ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_smList]  : 업무일지 매장 목록 em_no : "+emNo+",  base_de : "+baseDe);
		CommonUtil.setSessionVo(session);
		Map<String,String> map = new HashMap<String, String>();
		map.put("em_no", emNo);
		map.put("plan_de", baseDe);
		smVoList =  dao.rndSmList(map);
		
		return CommonUtil.makeMobileRespons(smVoList,"sm_list");
	}
	
	//비고 아코디언
	@RequestMapping("/rnd/aoalist") 
	public ModelAndView rndAoaList(@RequestParam("base_de") String base_de ,@RequestParam("em_no") String em_no) throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
	 
		rndPlanVoList = dao.rndAoaList(map);
		
		jVo = new JSONObject();
		jArr = JSONArray.fromObject(rndPlanVoList);
		jVo.put("result", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jVo );
		return mv;
	}
		
	//현재진열줄수아코디언
	@RequestMapping("/rnd/currlist") 
	public ModelAndView rndCurrList(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception{
		ActivityGridVo result =  dao.rndCurrList(vo);
		jVo = JSONObject.fromObject(JSONSerializer.toJSON(result));
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jVo );
		return mv;
	}
	
	//Moblide 현재진열줄수아코디언
	@RequestMapping("/rnd/m_currlist") 
	public ModelAndView rndMCurrList(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception{
		CommonUtil.setSessionVo(session);
		//check Param
		if (StringCheck.isNull(vo.getEm_no()) || StringCheck.isNull(vo.getBase_de())|| StringCheck.isNull(vo.getSm_code())) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		response =  dao.rndMobileCurrList(vo);
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping("/rnd/m_currsave") 
	public ModelAndView rndMCurrSave(@ModelAttribute("vo") ActivitySaveVo vo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_currsave]  : 현제진열 줄수 저장 em_no : "+vo.getEm_no()+",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		CommonUtil.setSessionVo(session);
		
		if (  StringCheck.isNull(vo.getBase_de())
				|| StringCheck.isNull(vo.getCm_code())
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getSm_code())
				|| StringCheck.isNull(vo.getEm_no())
				|| StringCheck.isNull(vo.getParamArr1())
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt =  dao.rndMobileCurrSave(vo);
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	
	
	//Mobile CVS순회 업무일지 by zzz2613
	@RequestMapping("/rnd/m_getCvsList") 
	public ModelAndView m_getCvsList(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_getCvsList]  : CVS 순회 조회 em_no : "+vo.getEm_no()+",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		CommonUtil.setSessionVo(session);
		
		//Check Required Parameter
		if (StringCheck.isNull(vo.getEm_no()) || StringCheck.isNull(vo.getBase_de())|| StringCheck.isNull(vo.getSm_code())) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		response =  dao.getCvsList(vo);
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping("/rnd/m_cvssave") 
	public ModelAndView rndMobileCvsSave(@ModelAttribute("vo") ActivitySaveVo vo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_cvssave]  : CVS 저장 em_no : "+vo.getEm_no()+",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		CommonUtil.setSessionVo(session);
		
		if (  StringCheck.isNull(vo.getBase_de())
				|| StringCheck.isNull(vo.getCm_code())
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getSm_code())
				|| StringCheck.isNull(vo.getEm_no())
				|| StringCheck.isNull(vo.getParamArr1())
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt =  dao.rndMobileCvsSave(vo);
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	
	
	//Big 아코디언
	@RequestMapping("/rnd/biglist") 
	public ModelAndView rndBigList(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception{
		
		ActivityGridVo result =  dao.rndBiglist(vo);
		jVo = JSONObject.fromObject(JSONSerializer.toJSON(result));
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jVo );
		return mv;
	}
	//Mobile Big 아코디언
	@RequestMapping("/rnd/m_biglist") 
	public ModelAndView rndMBigList(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_biglist]  : 보조진열조회 em_no : "+vo.getEm_no()+",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		CommonUtil.setSessionVo(session);
		
		if (StringCheck.isNull(vo.getEm_no()) || StringCheck.isNull(vo.getBase_de())|| StringCheck.isNull(vo.getSm_code())) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		ActivityGridMobileVo bigVo =  dao.rndMobileBiglist(vo);
		
		
		return CommonUtil.makeMobileRespons(bigVo,"big_list");
	}
	
	
	@RequestMapping("/rnd/m_bigsave") 
	public ModelAndView rndMobileBigSave(@ModelAttribute("vo") ActivitySaveVo vo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_bigsave]  : 보조진열저장 em_no : "+vo.getEm_no()+",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		CommonUtil.setSessionVo(session);
		
		if ( StringCheck.isNull(vo.getBase_de())
				|| StringCheck.isNull(vo.getCm_code())
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getSm_code())
				|| StringCheck.isNull(vo.getEm_no())
				|| (StringCheck.isNull(vo.getParamArr1()) && StringCheck.isNull(vo.getParamArr2()))
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt =  dao.rndMobileBigSave(vo);
		
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	
	//odd 아코디언
	@RequestMapping("/rnd/oddlist") 
	public ModelAndView rndOddlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no , @RequestParam("sm_code") String sm_code ) throws Exception{
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		map.put("sm_code", sm_code);
		jObj = new JSONObject();
		activityRndOddVoList =  dao.rndOddlist(map);
		if(activityRndOddVoList != null && activityRndOddVoList.size() == 1){
			if(activityRndOddVoList.get(0) == null){
				activityRndOddVoList.clear();
			}
		}
		jArr = JSONArray.fromObject(activityRndOddVoList);
		jObj.put("oddList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}
	
	//Mobile odd 아코디언
	@RequestMapping("/rnd/m_oddlist") 
	public ModelAndView rndMobileOddlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no , @RequestParam("sm_code") String sm_code ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_oddlist]  : 취급품목 조회 em_no : "+em_no+",  base_de : "+base_de+",  sm_code : "+sm_code);
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		map.put("sm_code", sm_code);
		
		CommonUtil.setSessionVo(session);
		
		if (StringCheck.isNull(em_no) || StringCheck.isNull(base_de)|| StringCheck.isNull(sm_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		rndOddMVo =  dao.rndMobileOddlist(map);
		
		
		return CommonUtil.makeMobileRespons(rndOddMVo,"big_list");
	}
	
	
	//모바일 - odd 아코디언 저장
	@RequestMapping("/rnd/m_oddsave") 
	public ModelAndView rndMobileOddsave(@ModelAttribute("vo") ActivityRndOddVo vo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_oddsave]  : 시황및 특이사항 저장 em_no : "+vo.getEm_no()+", "
				+ " base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code()+", innb : "+vo.getInnb() +", ParamArr1 : "+vo.getParamArr1());
		
		CommonUtil.setSessionVo(session);
		
		String em_no = vo.getEm_no();
		String base_de = vo.getBase_de();
		String innb = vo.getInnb();
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no) || StringCheck.isNull(base_de)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt = 0;
		int cnt_innb = 0;
		
		cnt_innb = dao.rndMobileInnbSearch(innb);
		
		if(cnt_innb > 0){
			System.out.println("업데이트할게요");
			cnt =  dao.rndMobileOddupdate(vo);//업데이트
		}else{
			System.out.println("저장입니다.");
			cnt =  dao.rndMobileOddsave(vo);//기존 저장
		}
		
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
		
		
	}
	//모바일 - odd 아코디언 삭제
	@RequestMapping("/rnd/m_odddel") 
	public ModelAndView rndMobileOddDel(@RequestParam("drop_innb") String drop_innb) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_oddlist]  : 시황 및특이사항 삭제  drop_innb : "+drop_innb);
		CommonUtil.setSessionVo(session);
		
//		String login_no = (String)session.getAttribute("login_no");
		Map<String,String> map =  new HashMap<String, String>();
//		map.put("em_no", login_no);
		map.put("drop_innb", drop_innb);
		
		//Check Required Parameter
		if (StringCheck.isNull(drop_innb)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt = 0;
		cnt =  dao.rndMobileOddDel(map);
		
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
		
	}
	
	
	//Pd아코디언
	@RequestMapping("/rnd/pdlist") 
	public ModelAndView rndPdlist(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/rnd/pdlist]  : PD 매대 조회  base_de : "+vo.getBase_de());
		ActivityGridVo result =  dao.rndPdlist(vo);
		jVo = JSONObject.fromObject(JSONSerializer.toJSON(result));
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jVo );
		return mv;
	}
	//모바일Pd아코디언
	@RequestMapping("/rnd/m_pdlist") 
	public ModelAndView rndMobilePdlist(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception{
		
		CommonUtil.setSessionVo(session);
		
		if (StringCheck.isNull(vo.getEm_no()) || StringCheck.isNull(vo.getBase_de())) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		ActivityGridMobileVo pdVo =  dao.rndMobilePdlist(vo);
		
		return CommonUtil.makeMobileRespons(pdVo,"pd_list");
	}
	
	//모바일Pd매대 현황 저징
	@RequestMapping("/rnd/m_pdsave") 
	public ModelAndView rndMobilePdSave(@ModelAttribute("vo") ActivitySaveVo vo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_pdsave]  : PD매대 저장 em_no : "+vo.getEm_no() +",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		CommonUtil.setSessionVo(session);
		if (  StringCheck.isNull(vo.getBase_de())
				|| StringCheck.isNull(vo.getCm_code())
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getSm_code())
				|| StringCheck.isNull(vo.getEm_no())
				|| (StringCheck.isNull(vo.getParamArr1()) && StringCheck.isNull(vo.getParamArr2()))
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt =  dao.rndMobilePdSave(vo);
		
		
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}


	
	
	//취급품목현황
	@RequestMapping("/rnd/trtlist") 
	public ModelAndView rndTrtlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no , @RequestParam("sm_code") String sm_code ) throws Exception{
		
		String login_no = (String)session.getAttribute("login_no");
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		map.put("sm_code", sm_code);
		map.put("login_no", login_no);
		
		activityFixingTrtVoList =  dao.rndTrtlist(map);
		jArr = JSONArray.fromObject(activityFixingTrtVoList);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jArr );
		return mv;
	}
	
	
	//Mobile 취급품목현황
	@RequestMapping("/rnd/m_trtlist") 
	public ModelAndView rndMTrtlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no , @RequestParam("sm_code") String sm_code ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_trtlist]  : 취급품목현황 조회 em_no : "+em_no+",  base_de : "+base_de+",  sm_code : "+sm_code);
		CommonUtil.setSessionVo(session);
		
		if (StringCheck.isNull(em_no) || StringCheck.isNull(base_de)|| StringCheck.isNull(sm_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		String login_no = (String)session.getAttribute("login_no");
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		map.put("sm_code", sm_code);
		map.put("login_no", login_no);
		
		rndTrtMVo =  dao.rndMobileTrtlist(map);
		return CommonUtil.makeMobileRespons(rndTrtMVo,"trt_list");
	}
	
	
	@RequestMapping("/rnd/m_trtsave") 
	public ModelAndView m_TndTrtsave(@ModelAttribute("vo") ActivitySaveVo vo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_trtsave]  : 취급품목저장 em_no : "+vo.getEm_no()+",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		
		CommonUtil.setSessionVo(session);
		if ( StringCheck.isNull(vo.getBase_de())
				|| StringCheck.isNull(vo.getCm_code())
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getSm_code())
				|| StringCheck.isNull(vo.getEm_no())
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		int cnt =  dao.rndMobileTrtsave(vo);
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	
	// 저장 테스트
	@RequestMapping("/rnd/test") 
	public ModelAndView getActivityTestRndView(@ModelAttribute("vo") ActivityTestVo vo) throws Exception{
		
		
		int cnt =  dao.rndSaveTest(vo);
		
		jVo = new JSONObject();
		jVo.put("11", cnt);
		
		return CommonUtil.setModelAndView(CommonUtil.setSuccessResponse(jVo));
	}
	
	//순회여사원 아코디언 전체조보
	@RequestMapping("/rnd/view") 
	public ModelAndView rndView(@ModelAttribute("vo") ActivityEmVo vo) throws Exception{
		activityRndVo = dao.rndView(vo);
		
		jObj = new JSONObject();
		jVo = JSONObject.fromObject(JSONSerializer.toJSON(activityRndVo));
		jObj.put("activityRndVo", jVo);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	//순회 여사원 목록
	@RequestMapping("/rnd/list") 
	public ModelAndView getActivityRndList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		naviVo = dao.rndListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); //권한코드
		String login_no = (String)session.getAttribute("login_no"); //권한코드
		naviVo.setAuth_flag(auth_flag);
		naviVo.setEm_no(login_no);
		activityRndVoList = dao.rndList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(activityRndVoList);
		jObj.put("activityRndList", jArr);
		jObj.put("navi",navi);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	// 
	@RequestMapping("/rnd/rndSaveEmpAiNo") 
	public ModelAndView rndSaveEmpAiNo(@ModelAttribute("vo") ActivityEmVo saveActivityFixingVo) throws Exception{
		String login_no = (String) session.getAttribute("login_no");//로그인 유저의 사원번호
		saveActivityFixingVo.setRegist_man(login_no);
		saveActivityFixingVo.setUpdt_man(login_no);
		String result = dao.rndSaveEmpAiNo(saveActivityFixingVo);
		
		jObj = new JSONObject();
		jObj.put("result", result);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	// 모바일 순회여사원  근퇴 처리!!!
	@RequestMapping("/rnd/m_attending")
	public ModelAndView rndMobileAttending(@ModelAttribute("vo") ActivityRndMobileAttendVo vo) throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rnd/m_attending]  : 출근 퇴근 em_no : "+vo.getEm_no()+",  plan_de : "+vo.getPlan_de()+",  prdi_sm_code : "+vo.getPrdi_sm_code()+", am_no"+vo.getAm_no());
		
		CommonUtil.setSessionVo(session);
		
		response = new JSONObject();
		
		if (       StringCheck.isNull(vo.getPrdi_sm_code())
				|| StringCheck.isNull(vo.getPlan_de())
				|| StringCheck.isNull(vo.getCm_code()) 
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getEm_no())
				|| StringCheck.isNull(vo.getAm_no())
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		int cnt  = dao.rndMobileAttending(vo);			//  출근 근테 등록
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}	
	
	@RequestMapping("/rnd/excel") 
	@ResponseBody
	public FileSystemResource rndExcelDownload(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no , @RequestParam("sm_code") String sm_code ,HttpServletResponse httpresponse) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/rnd/excel]  : 순회 엑셀 다운");
		
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		map.put("sm_code", sm_code);
		
		List<List<Map<String, String>>> columnLists = new ArrayList<List<Map<String,String>>>(); 
		List<List<Map<String, String>>> bodyLists  = new ArrayList<List<Map<String,String>>>();
		
		ActivityGridVo vo = new ActivityGridVo();
		vo.setBase_de(base_de);
		vo.setEm_no(em_no);
		vo.setSm_code(sm_code); 
		
		
		String[] sheets = {"현재진열줄수","보조진열현황","PD매대현황","취급품목현황"};
//		String[] sheets = {"현재진열줄수","보조진열현황","PD매대현황","취급품목현황","시황 및 매장특이사항"};
		
		String[] matters = {"","","",""};
		
		ExcelVo excelVo =  new ExcelVo();
		ActivityGridVo result = new ActivityGridVo();
		excelVo.setSheetNames(sheets);
		
		
		
		result =  dao.rndCurrList(vo);
		columnLists.add(result.getColumnArr());
		bodyLists.add(result.getBodyArr());
		matters[0] = result.getPartclr_matter();
		
		result =  dao.rndBiglist(vo);
		columnLists.add(result.getColumnArr());
		bodyLists.add(result.getBodyArr());
		matters[1] = result.getPartclr_matter();
		
		result =  dao.rndPdlist(vo);
		columnLists.add(result.getColumnArr());
		bodyLists.add(result.getBodyArr());
		matters[2] = result.getPartclr_matter();
		
		List<ActivityRndTrtVo> rndList=  dao.rndTrtlist(map);
		matters[3] = result.getPartclr_matter();
		
//		List<ActivityRndOddVo> oddList= dao.rndOddlist(map);
		
		
		
		excelVo.setBodyLists(bodyLists);
		excelVo.setColumnLists(columnLists);
		excelVo.setRndTrtList(rndList);
//		excelVo.setRndOddList(oddList);
		excelVo.setFileName("업무일지.xlsx");
		excelVo.setMatters(matters);
		
		return em.downloadFileRnd(excelVo,httpresponse);
	}
	// 동서식품
	//odd 아코디언
	@RequestMapping("/ds_rnd/oddlist") 
	public ModelAndView ds_rndOddlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no , @RequestParam("sm_code") String sm_code ) throws Exception{
		CommonUtil.setSessionVo(session);
		System.out.println("["+CommonUtil.getCurrentDateTime() + " WEB :/ds_rnd/oddlist]  :동서식품  시황및 매장 조회 em_no : "+em_no+",  base_de : "+base_de+",  sm_code : "+sm_code);
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		map.put("sm_code", sm_code);
		jObj = new JSONObject();
		activityRndOddVoList =  dao.rndOddlist(map);
		if(activityRndOddVoList != null && activityRndOddVoList.size() == 1){
			if(activityRndOddVoList.get(0) == null){
				activityRndOddVoList.clear();
			}
		}
		jArr = JSONArray.fromObject(activityRndOddVoList);
		jObj.put("oddList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}
	
	//Mobile odd 아코디언
	@RequestMapping("/ds_rnd/m_oddlist") 
	public ModelAndView ds_rndMobileOddlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no , @RequestParam("sm_code") String sm_code ) throws Exception{
		CommonUtil.setSessionVo(session);
		System.out.println("["+CommonUtil.getCurrentDateTime() + " Mobile:/ds_rnd/m_oddlist]  : 동서식품 시황및 매장 조회 em_no : "+em_no+",  base_de : "+base_de+",  sm_code : "+sm_code);
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		map.put("sm_code", sm_code);
		
		
		if (StringCheck.isNull(em_no) || StringCheck.isNull(base_de)|| StringCheck.isNull(sm_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		rndOddMVo =  dao.rndMobileOddlist(map);
		
		
		return CommonUtil.makeMobileRespons(rndOddMVo,"odd_list");
	}

	//매장시황 A20161212 k2s
	@RequestMapping("/rnd/rndOddMultiList") 
	public ModelAndView rndOddMultiList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		System.out.println("[WEB] 매장시황(순회MD) 리스트 ");
		CommonUtil.setSessionVo(session);    //A20170822 DSIS연동을 위한 추가 사항
		
		System.out.println(naviVo.toString());
		
		naviVo      = dao.rndOddMultiListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
	
		System.out.println("\n##### " + "dao.rndOddMultiList !!!!!" + " #####");
		
		activityRndOddVoList =  dao.rndOddMultiList(naviVo);
		if(activityRndOddVoList != null && activityRndOddVoList.size() == 1){
			if(activityRndOddVoList.get(0) == null){
				activityRndOddVoList.clear();
			}
		}
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(activityRndOddVoList);
		jObj.put("navi"      , navi);
		jObj.put("firstNo"   , naviVo.getFirstRowNo());
		jObj.put("rndOddList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}
	
	//동서식품 인터페이스 모바일 매장시황 A20170825 k2s
	@RequestMapping("/ds_rnd/m_rndOddMultiList") 
	public ModelAndView dsRndOddMultiList(@ModelAttribute("vo") NaviVo naviVo, @RequestParam("strParams") String strParams) throws Exception{
		naviVo.setParams(CommonUtil.stringToObj(strParams));
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/ds_rnd/m_rndOddMultiList] 모바일 : 동서식품 모바일 순회MD매장시황 조회 : "+naviVo.toString());		
		CommonUtil.setSessionVo(session);
		if ( naviVo.getCurrPg() ==0 || naviVo.getRowSize() == 0 || StringCheck.isNull(strParams)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}		
		naviVo      = dao.dsRndOddMultiListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		
		System.out.println("\n##### " + "dao.dsRndOddMultiList !!!!!" + " #####");
		
		activityRndOddVoList =  dao.dsRndOddMultiList(naviVo);
		if(activityRndOddVoList != null && activityRndOddVoList.size() == 1){
			if(activityRndOddVoList.get(0) == null){
				activityRndOddVoList.clear();
			}
		}
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(activityRndOddVoList);
		jObj.put("rndOddList", jArr);
		jObj.put("totRow",    naviVo.getTotRow());
		jObj.put("navi",      navi);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax",CommonUtil.setSuccessResponse(jObj));
		return mv;
	}
}
