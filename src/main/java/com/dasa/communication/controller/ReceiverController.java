package com.dasa.communication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.communication.dao.ReceiverDAO;
import com.dasa.communication.vo.NoticeVo;
import com.dasa.communication.vo.ReceiverVo;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.StringCheck;

@Controller
public class ReceiverController {
	@Autowired
	private HttpSession session;
	@Autowired
	private ReceiverDAO dao;
	
	private ModelAndView mv;
	private JSONObject jo;
	private JSONArray ja;
	
	@RequestMapping("/receiver/organTreeList") 
	public ModelAndView getOrganTreeList() throws Exception{
        //String login_team_cd = (String)session.getAttribute("login_team_cd"); //팀코드
		String cm_code = (String) session.getAttribute("login_cp_cd"); // 회사코드
		int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); // 권한코드
		String em_id = (String)session.getAttribute("login_id"); //A20180109 k2s 로그인id 추가 다우마케팅 여부 확인

		Map<String, String> map = new HashMap<String, String>();
		map.put("cm_code", cm_code);

		if(auth_flag == 2) {
			String om_code = (String) session.getAttribute("login_bhf_cd"); // 지점코드
			map.put("om_code", om_code);
		}
		map.put("em_id", em_id);                 //A20180109 k2s 로그인id 추가 다우마케팅 여부 확인
		
		List<ReceiverVo> receiverList =  dao.getOrganTreeList(map);
		jo = new JSONObject();
		ja = JSONArray.fromObject(receiverList);
		jo.put("result", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;
	}

	@RequestMapping("/receiver/storeTreeList") 
	public ModelAndView getStoreTreeList(@ModelAttribute("vo") ReceiverVo receiverVo) throws Exception{
		String cm_code = (String) session.getAttribute("login_cp_cd"); // 회사코드

		Map<String, String> map = new HashMap<String, String>();
		map.put("cm_code", cm_code);
		String om_code = receiverVo.getOm_code(); 
		if(om_code != null) {
			map.put("om_code", om_code);
		}
		
		List<ReceiverVo> receiverList = dao.getStoreTreeList(map);
		jo = new JSONObject();
		ja = JSONArray.fromObject(receiverList);
		jo.put("result", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;
	}
	
	@RequestMapping("/receiver/employeeList") 
	public ModelAndView getEmployeeList(@ModelAttribute("vo") ReceiverVo receiverVo) throws Exception{
		String cm_code = (String) session.getAttribute("login_cp_cd"); // 회사코드
		String em_id = (String)session.getAttribute("login_id"); //A20180109 k2s 로그인id 추가 다우마케팅 여부 확인

		receiverVo.setCm_code(cm_code);
		if(receiverVo.getAdd_flag().equals("or") || receiverVo.getAdd_flag().equals("st_or")) {
			int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); // 권한코드
			if(auth_flag == 2) {
				String om_code = (String) session.getAttribute("login_bhf_cd"); // 지점코드
				receiverVo.setOm_code(om_code);
			}
		}
		receiverVo.setEm_id(em_id); //A20180109 k2s 로그인id 추가 다우마케팅 여부 확인

		List<ReceiverVo> receiverList = dao.getEmployeeList(receiverVo);
		jo = new JSONObject();
		ja = JSONArray.fromObject(receiverList);
		jo.put("result", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;
	}
	
}
