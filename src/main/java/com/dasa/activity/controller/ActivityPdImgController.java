package com.dasa.activity.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.activity.dao.ActivityPdImgDAO;
import com.dasa.activity.vo.ActivityPdImgVo;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.Navi;
import com.vertexid.utill.StringCheck;
import com.vertexid.vo.NaviVo;

@Controller
public class ActivityPdImgController {
	private Logger logger = Logger.getLogger("dasa");
	
	private ModelAndView mv;			//모델엔뷰
	
	private JSONObject jObj;			//json obj
	private JSONObject jVo;
	private JSONArray jArr;				//json 배열
	private JSONObject response;
	
	private List<Map<String, Object>> resultList;
	private Map<String, Object> resultMap;
	
	@Autowired
	private ActivityPdImgDAO dao;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/10/10-910") 
	public String fixPdImgMain(){
		return "/10/10-910";
	}
	
	@RequestMapping("/10/10-920") 
	public String rndPdImgMain(){
		return "/10/10-920";
	}
	
	@RequestMapping("/pdImg/fixList")
	public ModelAndView fixList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		logger.debug("[WEB] PD매대 이미지리스트_고정");
		
		naviVo = dao.fixCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		resultList =  dao.fixList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(resultList);
		jObj.put("navi", navi);
		jObj.put("firstNo", naviVo.getFirstRowNo());
		jObj.put("pdImgFixList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	@RequestMapping("/pdImg/rndList")
	public ModelAndView rndList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		logger.debug("[WEB] PD매대 이미지리스트_순회");
		
		naviVo = dao.rndCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		resultList =  dao.rndList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(resultList);
		jObj.put("navi", navi);
		jObj.put("firstNo", naviVo.getFirstRowNo());
		jObj.put("pdImgRndList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}

	@RequestMapping("/pdImg/m_fixsave") 
	public ModelAndView m_fixPdImgSave(@ModelAttribute("vo") ActivityPdImgVo vo) throws Exception{
		logger.debug("["+CommonUtil.getCurrentDateTime() + "Mobile:/pdImg/m_fixsave]  : PD매대 이미지 저장 em_no : "+vo.getEm_no() +", base_de : "+vo.getBase_de() +", ParamArr1 : "+vo.getParamArr1());
		CommonUtil.setSessionVo(session);
		
		String em_no = vo.getEm_no();
		String base_de = vo.getBase_de();
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no) || StringCheck.isNull(base_de)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}

		int cnt = 0;
		cnt =  dao.m_fixPdImgSave(vo);
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	
	@RequestMapping("/pdImg/m_rndsave") 
	public ModelAndView m_rndPdImgSave(@ModelAttribute("vo") ActivityPdImgVo vo) throws Exception{
		logger.debug("["+CommonUtil.getCurrentDateTime() + "Mobile:/pdImg/m_rndsave]  : PD매대 이미지 저장 em_no : "+vo.getEm_no() +", base_de : "+vo.getBase_de() +", ParamArr1 : "+vo.getParamArr1());
		CommonUtil.setSessionVo(session);
		
		String em_no = vo.getEm_no();
		String base_de = vo.getBase_de();
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no) || StringCheck.isNull(base_de)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt = 0;
		cnt =  dao.m_rndPdImgSave(vo);
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
}
