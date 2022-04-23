package com.dasa.activity.controller;

import java.net.URLDecoder;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.activity.dao.ActivityTeamleaderDAO;
import com.dasa.activity.vo.ActivityTeamleaderVo;
import com.vertexid.utill.AttachManager;
//import com.vertexid.utill.CryptoUtil;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.LunarToSolar;
import com.vertexid.utill.Navi;
import com.vertexid.vo.NaviVo;

@Controller
public class ActivityTeamleaderController {
	private ModelAndView mv;			//모델엔뷰
	
	private JSONObject jObj;				//json obj
	private JSONObject jVo;
	private JSONObject response;
	private JSONArray jArr;				//json 배열
	
	
	private ActivityTeamleaderVo vo;
	
	@Autowired
	private AttachManager attachManager;
	
	private List<ActivityTeamleaderVo> voList;
	
	@Autowired
	private ActivityTeamleaderDAO dao;
	
	@Autowired
	private HttpSession session;
//	
//	@Autowired
//	private AttachManager am;						// 파일관리자
	
	@RequestMapping("/10/10-600") 
	public String codePg(){
		return "10/10-600";
	}
	//A20171011 k2s
	@RequestMapping("/10/10-601")
	public String mdTeamLeaderUI(){
		return "10/10-601";
	}
	
	@RequestMapping("/activity/teamleader") 
	public ModelAndView fixingTrtlist(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		naviVo.setCm_code(login_cp_cd);
		
		naviVo = dao.selectListCount(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		voList =  dao.selectList(naviVo);

		jObj = new JSONObject();
		jArr = JSONArray.fromObject(voList);
		//System.out.println(jArr);
		
		jObj.put("result", jArr);
		jObj.put("navi",navi);
		//System.out.println("naviVo.getFirstRowNo() : " +  naviVo.getFirstRowNo());
		jObj.put("firstNo", naviVo.getFirstRowNo());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
			
		return mv;
	}
	
	/**
	 * @메서드명: attandanceAllExcelList
	 * @작성일: 2015. 10. 18
	 * @작성자: 최수영
	 * @설명: /store/excelExport
	 */
	@RequestMapping("/activity/teamleader/excelExport") 
	public ModelAndView attandanceAllList(ActivityTeamleaderVo p_vo) throws Exception {
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		p_vo.setCm_code(login_cp_cd);
		
		p_vo.setBhf_name(URLDecoder.decode(p_vo.getBhf_name(),"UTF-8"));
		p_vo.setEm_nm(URLDecoder.decode(p_vo.getEm_nm(),"UTF-8"));
		
		voList =  dao.selectExcelList(p_vo);
		
		//System.out.println(voList.size());
		jVo = new JSONObject();
		jArr = JSONArray.fromObject(voList);
		
		//금월
		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.YEAR,	Integer.parseInt(p_vo.getYy()));
		cal.set(Calendar.MONTH, Integer.parseInt(p_vo.getMm())-1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int iDayOfWeek_01 = cal.get(Calendar.DAY_OF_WEEK);
		
		//전월
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));  
		int p_pre_year = cal.get(Calendar.YEAR); // 전월
		int p_pre_month = cal.get(Calendar.MONTH)+1; // 전월
		cal.set(Calendar.DAY_OF_MONTH, 16);
		int iDayOfWeek_16 = cal.get(Calendar.DAY_OF_WEEK) ;	    

		
		jObj.put("result", jArr);
		jObj.put("yyyy", p_vo.getYy());
		jObj.put("mm", p_vo.getMm());
		jObj.put("bhf_name", p_vo.getBhf_name());
		
		//System.out.println("**** p_vo.getBhf_name() : " + p_vo.getBhf_name());
		jObj.put("last_day", p_vo.getLast_day());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("yyyy", p_vo.getPre_yy());
		mv.addObject("mm", p_vo.getPre_mm());
		mv.addObject("iDayOfWeek_16", iDayOfWeek_16);
		mv.addObject("iDayOfWeek_01", iDayOfWeek_01);
		mv.addObject("ajax", jObj);
		//System.out.println(jObj.toString());
		mv.setViewName("/10/10-600_excel");
		
		//System.out.println("iDayOfWeek_16 :" + iDayOfWeek_16);
		//System.out.println("iDayOfWeek_01 :" + iDayOfWeek_01);
			
		return mv;
	}
	
	
	@RequestMapping("/activity/util") 
	public ModelAndView lunarToSolar(@RequestParam("fullday") String fullday) throws Exception{
		//System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/activity/util]  : 공휴일조회  fullday : "+fullday);
	    
		boolean flag = LunarToSolar.isHoliday(fullday);
		
		jVo = new JSONObject();
		jArr = JSONArray.fromObject(flag);
		
		jVo.put("result", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jVo); 
		
		return mv;
	}
	
	/**
	 * @메서드명: selectTeadmLeaderMonthlyList
	 * @작성일: 2017. 10. 11
	 * @작성자: k2s
	 * @설명: /store/excelExport
	 */
	@RequestMapping("/activity/selectTeadmLeaderMonthlyList") 
	public ModelAndView selectTeamLeaderMonthlyList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		naviVo.setCm_code(login_cp_cd);
		
		naviVo      = dao.selectTeamLeaderMonthlyListCount(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		voList      =  dao.selectTeamLeaderMonthlyList(naviVo);

		jObj = new JSONObject();
		jArr = JSONArray.fromObject(voList);
		
		jObj.put("result" ,jArr);
		jObj.put("navi"   ,navi);
		jObj.put("firstNo",naviVo.getFirstRowNo());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
			
		return mv;
	}
	
	/**
	 * @메서드명: selectTeamLeaderMonthlyExcelList
	 * @작성일: 2015. 10. 18
	 * @작성자: k2s
	 * @설명: MD팀장근태(New엑셀다운로드)
	 */
	@RequestMapping("/activity/selectTeamLeaderMonthlyExcelList") 
	public ModelAndView selectTeamLeaderMonthlyExcelList(ActivityTeamleaderVo p_vo) throws Exception {
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		p_vo.setCm_code(login_cp_cd);
		
		p_vo.setBhf_name(URLDecoder.decode(p_vo.getBhf_name(),"UTF-8"));
		p_vo.setEm_nm(URLDecoder.decode(p_vo.getEm_nm(),"UTF-8"));
		
		voList =  dao.selectTeamLeaderMonthlyExcelList(p_vo);
		
		jVo = new JSONObject();
		jArr = JSONArray.fromObject(voList);
		
		//금월
		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.YEAR,	Integer.parseInt(p_vo.getYy()));
		cal.set(Calendar.MONTH, Integer.parseInt(p_vo.getMm())-1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		System.out.println("getActualMaximum "+cal.getActualMaximum(Calendar.DATE));
		
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int iDayOfWeek_01 = cal.get(Calendar.DAY_OF_WEEK);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		int iDayOfWeek_16 = cal.get(Calendar.DAY_OF_WEEK) ;	    		
		
		System.out.println("iDayOfWeek_01 "+iDayOfWeek_01);
		System.out.println("iDayOfWeek_16 "+iDayOfWeek_16);      
		
		jObj.put("result"   ,jArr);
		jObj.put("yyyy"     ,p_vo.getYy());
		jObj.put("mm"       ,p_vo.getMm());
		jObj.put("bhf_name" ,p_vo.getBhf_name());
		jObj.put("last_day" ,p_vo.getLast_day());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("yyyy"          ,p_vo.getYy());
		mv.addObject("mm"            ,p_vo.getMm());
		mv.addObject("iDayOfWeek_16" ,iDayOfWeek_16);
		mv.addObject("iDayOfWeek_01" ,iDayOfWeek_01);
		mv.addObject("ajax"          ,jObj);
		mv.setViewName("/10/10-601_excel");
		
		return mv;
	}
		
		
}
