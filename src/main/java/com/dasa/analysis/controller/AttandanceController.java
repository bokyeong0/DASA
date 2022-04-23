package com.dasa.analysis.controller;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.analysis.dao.AttandanceDao;
import com.dasa.analysis.vo.AttandanceVo;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.Navi;
import com.vertexid.vo.KeyValueVo;
import com.vertexid.vo.NaviVo;

/**
 * @ AttandanceController.java
 * @ 2015. 9. 18.
 * @
 * @ 프로그램명: AttandanceController
 */
@Controller
public class AttandanceController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private AttandanceDao dao;
	
	private ModelAndView mv;
	private JSONObject jsonObject;
	private JSONArray jsonArray;
	private JSONObject response;
	private String VAL_OM_CODE;
	
	private AttandanceVo vo;
	private List<AttandanceVo> voList;
	private List<KeyValueVo> autoCompList;
	
	/**
	 * @메서드명: attandancePg
	 * @작성일: 2014. 9. 30
	 * @작성자: 김진호
	 * @설명: 화면 이동
	 * @return "String"
	 */
	@RequestMapping("/20/20-200")
	public ModelAndView attandancePg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-200");
		return mv;
	}
	
	/**
	 * @메서드명: attandancePg201
	 * @작성일: 2017. 9. 20
	 * @작성자: k2s
	 * @설명: 화면 이동
	 * @return "String"
	 */
	@RequestMapping("/20/20-201")
	public ModelAndView attandancePg201() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-201");
		return mv;
	}
	
	
	/**
	 * @메서드명: attandanceAllExcelList
	 * @작성일: 2015. 10. 18
	 * @작성자: 최수영
	 * @설명: /store/excelExport
	 */
	@RequestMapping("/attandance/excelExport") 
	public ModelAndView attandanceAllList(AttandanceVo p_vo) throws Exception {
		System.out.println("\nAttandanceController.attandanceAllList "+ p_vo.toString());
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		
		p_vo.setBhf_name(URLDecoder.decode(p_vo.getBhf_name(),"UTF-8"));
		p_vo.setEa_em_nm(URLDecoder.decode(p_vo.getEa_em_nm(),"UTF-8"));
		p_vo.setCm_code(login_cp_cd);
		
		voList =  dao.selectAllList(p_vo);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(voList);
		
		//금월
		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.YEAR,	Integer.parseInt(p_vo.getEa_yy()));
		cal.set(Calendar.MONTH, Integer.parseInt(p_vo.getEa_mm())-1);
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
		
		jsonObject.put("result", jsonArray);
		jsonObject.put("yyyy", p_vo.getEa_yy());
		jsonObject.put("mm", p_vo.getEa_mm());
		jsonObject.put("bhf_name", p_vo.getBhf_name());
		jsonObject.put("last_day", p_vo.getLast_day());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("yyyy", p_vo.getEa_yy());
		mv.addObject("mm", p_vo.getEa_mm());
		mv.addObject("iDayOfWeek_16", iDayOfWeek_16);
		mv.addObject("iDayOfWeek_01", iDayOfWeek_01);
		mv.addObject("ajax", jsonObject);
		mv.setViewName("/20/20-200_excel");
		
		return mv;
	}
	/**
	 * @메서드명: list
	 * @작성일: 2015. 10. 18
	 * @작성자: 최수영
	 * @설명: /attandance/list
	 */
	@RequestMapping("/attandance/list") 
	public ModelAndView list(@ModelAttribute("vo") NaviVo naviVo) throws Exception {
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		//naviVo.setCm_code(login_cp_cd);
		
		naviVo = dao.selectListCount(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		voList =  dao.selectList(naviVo);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(voList);
		//System.out.println(jsonArray);
		
		jsonObject.put("result", jsonArray);
		jsonObject.put("navi",navi);
		//System.out.println("naviVo.getFirstRowNo() : " +  naviVo.getFirstRowNo());
		jsonObject.put("firstNo", naviVo.getFirstRowNo());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
			
		return mv;
	}
	
	/**
	 * @메서드명: list
	 * @작성일: 2017. 09. 21
	 * @작성자: K2S
	 * @설명: /attandance/empMonthList
	 */
	@RequestMapping("/attandance/empMonthList") 
	public ModelAndView empMonthList(@ModelAttribute("vo") NaviVo naviVo) throws Exception {
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		//naviVo.setCm_code(login_cp_cd);
		
		naviVo = dao.selectempMonthListCount(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		voList =  dao.selectempMonthList(naviVo);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(voList);
		
		jsonObject.put("result", jsonArray);
		jsonObject.put("navi",navi);
		jsonObject.put("firstNo", naviVo.getFirstRowNo());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}

	/**
	 * @메서드명: selectExcelExportEmpMonth
	 * @작성일: 2017. 09. 21
	 * @작성자: K2S
	 * @설명: /attandance/selectExcelExportEmpMonth
	 */
	@RequestMapping("/attandance/selectExcelExportEmpMonth") 
	public ModelAndView selectExcelExportEmpMonth(AttandanceVo p_vo) throws Exception {
		System.out.println("\nAttandanceController.selectExcelExportEmpMonth ===> Parameters\n "+ p_vo.toString());//syslog
		
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		
		p_vo.setBhf_name(URLDecoder.decode(p_vo.getBhf_name(),"UTF-8"));
		p_vo.setEa_em_nm(URLDecoder.decode(p_vo.getEa_em_nm(),"UTF-8"));
		p_vo.setCm_code(login_cp_cd);
		
		voList =  dao.selectExcelExportEmpMonth(p_vo);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(voList);
		
		//금월
		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.YEAR,	Integer.parseInt(p_vo.getEa_yy()));
		cal.set(Calendar.MONTH, Integer.parseInt(p_vo.getEa_mm())-1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		System.out.println("getActualMaximum "+cal.getActualMaximum(Calendar.DATE));
		
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int iDayOfWeek_01 = cal.get(Calendar.DAY_OF_WEEK);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		int iDayOfWeek_16 = cal.get(Calendar.DAY_OF_WEEK) ;	    		
		
		System.out.println("iDayOfWeek_01 "+iDayOfWeek_01);
		System.out.println("iDayOfWeek_16 "+iDayOfWeek_16);  
		
		jsonObject.put("result"  ,jsonArray);
		jsonObject.put("yyyy"    ,p_vo.getEa_yy());
		jsonObject.put("mm"      ,p_vo.getEa_mm());
		jsonObject.put("bhf_name",p_vo.getBhf_name());
		jsonObject.put("last_day",p_vo.getLast_day());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("yyyy" ,p_vo.getEa_yy());
		mv.addObject("mm"   ,p_vo.getEa_mm());
		mv.addObject("iDayOfWeek_16", iDayOfWeek_16);
		mv.addObject("iDayOfWeek_01", iDayOfWeek_01);
		mv.addObject("ajax", jsonObject);
		mv.setViewName("/20/20-201_excel");
		
		return mv;
	}
    
	
	/**
	 * @메서드명: list
	 * @작성일: 2017. 09. 21
	 * @작성자: K2S
	 * @설명: /attandance/excuteEmpMonthBatch
	 */
	@RequestMapping("/attandance/excuteEmpMonthBatch") 
	public ModelAndView excuteEmpMonthBatch(@ModelAttribute("vo") NaviVo naviVo) throws Exception {
		System.out.println("\nAttandanceController.excuteEmpMonthBatch ===> Parameters\n " + naviVo.toString()); //syslog
		CommonUtil.setSessionVo(session);
		
		String tDay = new SimpleDateFormat("yyyyMMdd").format(new Date());//주요행사일(현재날짜)	
		dao.excuteEmpMonthBatch(naviVo, tDay);   //배치수행
		int cnt = 1;
		System.out.println("등록성공 : " + cnt);   //syslog
		
		jsonObject = new JSONObject();
		jsonObject.put("cnt"      , cnt);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", CommonUtil.setSuccessResponse(jsonObject));		
		
		return mv;
	}	
}


