package com.dasa.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.activity.dao.EventMonthDAO;
import com.dasa.activity.vo.ActivityEventMonthVo;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.Navi;
import com.vertexid.utill.StringCheck;
import com.vertexid.vo.MobileNaviVo;
import com.vertexid.vo.NaviVo;

@Controller
public class EventMonthController {
	private Logger logger = Logger.getLogger("dasa");
	
	private ModelAndView mv;			//모델엔뷰
	
	private JSONObject jObj;			//json obj
	private JSONObject jVo;
	private JSONArray jArr;				//json 배열
	private JSONObject response;
	
	private List<Map<String, Object>> resultList;
	private Map<String, Object> resultMap;
	
	@Autowired
	private EventMonthDAO dao;
	
	@Autowired
	private HttpSession session;
	
//	@Autowired
//	private AttachManager attachManager;							// 파일관리자
	
	@RequestMapping("/99/99-900") 
	public String codePg(){
		return "/99/99-900";
	}
	
	
	@RequestMapping("/eventMonth/emmList")
	public ModelAndView emmList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		System.out.println("[WEB] 행사 및 매장 현황 관리 리스트 ");

		System.out.println(naviVo.toString());
		naviVo = dao.emmListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		resultList =  dao.emmList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(resultList);
		jObj.put("navi", navi);
		jObj.put("fristNo", naviVo.getFirstRowNo());
		jObj.put("emmList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	@RequestMapping(value = "/eventMonth/m_getEventList", produces = "text/plain;charset=UTF-8") 
	public ModelAndView m_getEventList(@ModelAttribute("vo") MobileNaviVo vo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/eventMonth/m_getEventList");
        CommonUtil.setSessionVo(session);
		
        int auth_flag = vo.getAuth_flag();
        
      //Check Required Parameter
  		if (auth_flag < 0) {
  			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
  			return CommonUtil.setModelAndView(response);
  		}
        
      //Optional Parameter
  		String search_date_from = StringCheck.isNull(vo.getSearch_date_from(), ""); 
  		String search_date_to = StringCheck.isNull(vo.getSearch_date_to(), "");
  		String title = StringCheck.isNull(vo.getSearch_value(), ""); // 행사명
  		
  		search_date_from = search_date_from.replaceAll("-", "");
		search_date_to = search_date_to.replaceAll("-", "");
      		
		Map<String, String> param = new HashMap<String, String>();
		param.put("title", title);
		param.put("startDate", search_date_from);
		param.put("endDate", search_date_to);
		vo.setParams(param);
		
		vo =  (MobileNaviVo) dao.emmListCnt(vo);
		List<Map<String, Object>> emmList = dao.emmList(vo);
		
		if (emmList != null && emmList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_event = new JSONArray();
			
			//Response Body
			for (int i = 0; i < emmList.size(); i++) {
				Map<String, Object> smMap = emmList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("emm_innb", smMap.get("emm_innb").toString());
				bodyVo.put("title", smMap.get("title"));
				bodyVo.put("contents", smMap.get("contents"));
				bodyVo.put("note", smMap.get("note"));
				bodyVo.put("emm_date_from", smMap.get("emm_date_from"));
				bodyVo.put("emm_date_to", smMap.get("emm_date_to"));
				bodyVo.put("regist_man", smMap.get("regist_man"));
				bodyVo.put("regist_de", smMap.get("regist_de"));
				bodyVo.put("updt_man", smMap.get("updt_man"));
				bodyVo.put("updt_de", smMap.get("updt_de"));
				bodyVo.put("em_nm", smMap.get("em_nm"));
				body_event.add(bodyVo);
			}
			body.put("totRow", vo.getTotRow());
			body.put("eventList", body_event);

			response = CommonUtil.setSuccessResponse(body);
		}else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping("/eventMonth/emmSave")
	public ModelAndView emmSave(@RequestParam Map<String, Object> param) throws Exception{
		String login_no = (String)session.getAttribute("login_no");
		
		param.put("registMan", login_no); //사원번호
		
		int cnt = dao.emmSave(param);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt);
		return mv;
	}
	
	@RequestMapping("/eventMonth/emmView")
	public ModelAndView emmView(@RequestParam Map<String, Object> param) throws Exception{
		
		resultMap = dao.emmView(param);
		
		jObj = new JSONObject();
		jVo = JSONObject.fromObject(JSONSerializer.toJSON(resultMap));
		jObj.put("emmMap", jVo);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	@RequestMapping("/eventMonth/emmDel")
	public ModelAndView emmDel(@RequestParam Map<String, Object> param) throws Exception{
		String login_no = (String)session.getAttribute("login_no");
		
		param.put("updtMan", login_no); //사원번호
		
		int cnt = dao.emmDel(param);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt);
		return mv;
	}

	@RequestMapping("/eventMonth/emmUpt")
	public ModelAndView emmUpt(@RequestParam Map<String, Object> param) throws Exception{
		String login_no = (String)session.getAttribute("login_no");
		
		param.put("updtMan", login_no); //사원번호
		
		int cnt = dao.emmUpt(param);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt);
		return mv;
	}
	
	@RequestMapping("/10/10-900") 
	public String emiMain(){
		return "/10/10-900";
	}
	
	@RequestMapping("/eventMonth/getWorkingStoreList")
	public ModelAndView getWorkingStoreList(@RequestParam Map<String, Object> param) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/eventMonth/getWorkingStoreList");
		
		String emNo = (String)session.getAttribute("login_no");
		String auth_flag = (String)session.getAttribute("auth_flag"); //권한코드
		//1 관리자, 2 팀장, 3 고정, 4 순회
		if(Integer.parseInt(auth_flag) < 3) {
			emNo = null;	
		}		
		
		param.put("emNo", emNo);
		resultList = dao.emiSmList(param);

		jObj = new JSONObject();
		jArr = JSONArray.fromObject(resultList);
		jObj.put("resultList", jArr);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	@RequestMapping("/eventMonth/m_getWorkingStoreList")
	public ModelAndView m_getWorkingStoreList(@ModelAttribute("vo") MobileNaviVo reqMobileNaviVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/eventMonth/m_getWorkingStoreList");
		CommonUtil.setSessionVo(session);
		
		//Optional Parameter
		String emNo = reqMobileNaviVo.getEm_no(); //사원번호
		String meCode = reqMobileNaviVo.getMe_code(); //관리업체코드
		String cgCode = reqMobileNaviVo.getCg_code(); //고객그룹코드
		String omCode = reqMobileNaviVo.getOm_code(); //지점코드
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("emNo", emNo);
		param.put("meCode", meCode);
		param.put("cgCode", cgCode);
		param.put("omCode", omCode);
		resultList = dao.emiSmList(param);
		
		if(resultList != null && resultList.size() > 0){
			JSONObject body = new JSONObject();
			JSONArray body_result = new JSONArray();
			
			//Response Body
			for (int i = 0; i < resultList.size(); i++) {
				Map<String, Object> map = resultList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("em_no", map.get("em_no"));
				bodyVo.put("em_nm", map.get("em_nm"));
				bodyVo.put("em_dty_code", map.get("em_dty_code"));
				bodyVo.put("em_dty_nm", map.get("em_dty_nm"));
				bodyVo.put("cg_code", map.get("cg_code"));
				bodyVo.put("cg_nm", map.get("cg_nm"));
				bodyVo.put("me_code", map.get("me_code"));
				bodyVo.put("me_nm", map.get("me_nm"));
				bodyVo.put("sm_code", map.get("sm_code"));
				bodyVo.put("sm_nm", map.get("sm_nm"));
				body_result.add(bodyVo);
			}
			body.put("result", body_result);
			
			response = CommonUtil.setSuccessResponse(body);
		}else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping("/eventMonth/emiList")
	public ModelAndView emiList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		System.out.println("[WEB] 행사 및 매장 현황 리스트 ");

		System.out.println(naviVo.toString());
		naviVo = dao.emiListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		resultList =  dao.emiList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(resultList);
		jObj.put("navi", navi);
		jObj.put("fristNo", naviVo.getFirstRowNo());
		jObj.put("emiList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	@RequestMapping("/eventMonth/m_fileUpload")
	public ModelAndView m_fileUpload(@ModelAttribute("vo") ActivityEventMonthVo vo) throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/eventMonth/m_fileUpload]  : 이벤트 사진 올리기 em_no : "+vo.getEm_no()+", "
				+ " emm_innb : "+vo.getEmm_innb()+",  om_code : "+vo.getOm_code()+", sm_code : "+vo.getSm_code() +" note : "+vo.getNote());
		
		CommonUtil.setSessionVo(session);
		
		String emm_innb = vo.getEmm_innb();
		String om_code = vo.getOm_code();
		String sm_code = vo.getSm_code();
		String note = vo.getNote();
		String em_no = vo.getEm_no();
		
		//Check Required Parameter
		if (StringCheck.isNull(emm_innb) || StringCheck.isNull(om_code) || StringCheck.isNull(sm_code) || StringCheck.isNull(em_no)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt = 0;
		cnt =  dao.m_fileUpload(vo);
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}

}
