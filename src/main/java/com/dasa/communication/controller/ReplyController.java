package com.dasa.communication.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.communication.dao.ReplyDAO;
import com.dasa.communication.vo.ReplyVo;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.StringCheck;

@Controller
public class ReplyController {
	@Autowired
	private HttpSession session;
	@Autowired
	private ReplyDAO dao;
	
	private ModelAndView mv;
	private JSONObject jo;
	private JSONArray ja;
	
	private JSONObject response;

	@RequestMapping("/reply/save") 
	public ModelAndView replySave(@ModelAttribute("vo") ReplyVo replyVo) throws Exception{
		String login_no = (String)session.getAttribute("login_no");
		
		replyVo.setRegist_man(login_no);
		
		String parent_br_innb = replyVo.getParent_br_innb(); 
		if(parent_br_innb != null)
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put("bm_innb", replyVo.getBm_innb());
			map.put("parent_br_innb", parent_br_innb);
			int replyCount = dao.replyCount(map);
			if(replyCount>0)
			{
				mv = new ModelAndView(new JSONView());
				mv.addObject("ajax", null);
				return mv;
			}
		}
		
		String bm_innb =  dao.replySave(replyVo);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", bm_innb);
		return mv;
	}
	
	@RequestMapping("/reply/m_save") 
	public ModelAndView m_replySave(@ModelAttribute("vo") ReplyVo replyVo) throws Exception{
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String login_no = replyVo.getRegist_man();
		String parent_br_innb = replyVo.getParent_br_innb(); 
		String bm_innb = replyVo.getBm_innb();
		
		replyVo.setRegist_man(login_no);
		
		//Check Required Parameter
		if (StringCheck.isNull(login_no) || StringCheck.isNull(bm_innb)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}else if(parent_br_innb != null) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("bm_innb", bm_innb);
			map.put("parent_br_innb", parent_br_innb);
			int replyCount = dao.replyCount(map);
			if(replyCount>0) {
				CommonUtil.makeMobileSaveRespons(response,0);
			}
		}
		
		String rtn_bm_innb =  dao.replySave(replyVo);
		return CommonUtil.makeMobileSaveRespons(response, Integer.parseInt(rtn_bm_innb));
	}
	
	@RequestMapping("/reply/delete") 
	public ModelAndView replyDelete(@ModelAttribute("vo") ReplyVo replyVo) throws Exception{
		String login_no = (String) session.getAttribute("login_no");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("br_innb", replyVo.getBr_innb());
		map.put("regist_man", login_no);
 		
		int resultCnt =  dao.replyDelete(map);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(resultCnt);
		
		jo.put("resultCnt", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/reply/m_delete") 
	public ModelAndView m_replyDelete(@ModelAttribute("vo") ReplyVo replyVo) throws Exception{
		CommonUtil.setSessionVo(session);
		
		String login_no = replyVo.getRegist_man();
		String br_innb = replyVo.getBr_innb();
		int auth_flag = replyVo.getAuth_flag();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("br_innb", br_innb);
		map.put("regist_man", login_no);
		map.put("auth_flag", String.valueOf(auth_flag));
		
		//Check Required Parameter
		if (StringCheck.isNull(br_innb) || StringCheck.isNull(login_no)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
 		
		int resultCnt =  dao.replyDelete(map);
		return CommonUtil.makeMobileSaveRespons(response,resultCnt);
	}
}
