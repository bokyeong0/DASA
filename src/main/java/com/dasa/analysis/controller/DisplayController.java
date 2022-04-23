package com.dasa.analysis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.activity.dao.ActivityCvsDAO;
import com.dasa.activity.vo.ActivityCvsItemVo;
import com.dasa.activity.vo.ActivityCvsVo;
import com.dasa.analysis.dao.DisPlayDao;
import com.dasa.analysis.vo.AnalysisCvsVo;
import com.dasa.analysis.vo.DisPlayVo;
import com.dasa.analysis.vo.DisplayGridVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.ExcelManager;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.Navi;
import com.vertexid.utill.StringCheck;
import com.vertexid.vo.ExcelVo;
import com.vertexid.vo.NaviVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ DisplayController.java
 * @ 2015. 9. 18.
 * @
 * @ 프로그램명: DisplayController
 */
@Controller
public class DisplayController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private DisPlayDao dao;
	
	private ModelAndView mv;
	private JSONObject jObj;
	private JSONArray jArr;
	private JSONObject response;
	
	@Autowired
	private ExcelManager em;						// 파일관리자(엑셀)
	
	@Autowired
	private AttachManager am;						// 파일관리자(첨부파일)
	
	//private DisplayVo vo;
	//private List<DisplayVo> voList;
	private List<DisPlayVo> dpVoList;
	
	/**
	 * @메서드명: DisplayPg
	 * @작성일: 2014. 9. 30
	 * @작성자: 김진호
	 * @설명: 화면 이동
	 * @return "String"
	 */
	
	//옵션관리
	@RequestMapping("/20/20-300")
	public ModelAndView display300Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-300");
		return mv;
	}
	
	//통계 목록
	@RequestMapping("/display/list")
	public ModelAndView displayList(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		System.out.println("displayList ");
		dpVoList =  dao.displayList();
		jArr = JSONArray.fromObject(dpVoList);
		mv = new ModelAndView(new JSONView());
		System.out.println("rndSmList jArr : " + jArr);
		mv.addObject("ajax", jArr );
		return mv;
	}
	
	//통계 목록
	@RequestMapping("/display/prdlist")
	public ModelAndView displayPrdList(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		System.out.println("displayPrdList d_innb "+vo.toString());
		dpVoList =  dao.displayPrdList(vo);
		jArr = JSONArray.fromObject(dpVoList);
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdList jArr : " + jArr);
		mv.addObject("ajax", jArr );
		return mv;
	}
	
	//수집항목 아이템
	@RequestMapping("/display/prditemlist")
	public ModelAndView displayPrdItemList(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		System.out.println("displayPrdItemList d_innb "+vo.toString());
		dpVoList =  dao.displayPrdItemList(vo);
		jArr = JSONArray.fromObject(dpVoList);
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdItemList jArr : " + jArr);
		mv.addObject("ajax", jArr );
		return mv;
	}
	
	//사원별 진열률
	@RequestMapping("/20/20-101")
	public ModelAndView display5Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-101");
		return mv;
	}
    //사원별 입점품목
	@RequestMapping("/20/20-102")
	public ModelAndView display6Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-102");
		return mv;
	}
	//사원별 보조진열
	@RequestMapping("/20/20-103")
	public ModelAndView display7Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-103");
		return mv;
	}
	//관리 업체별 진열률
	@RequestMapping("/20/20-104")
	public ModelAndView display1Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-104");
		return mv;
	}
	//관리 업체별 취급률
	@RequestMapping("/20/20-105")
	public ModelAndView display2Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-105");
		return mv;
	}
    //관리 업체별 보조진열현황
	@RequestMapping("/20/20-106")
	public ModelAndView display3Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-106");
		return mv;
	}
	//관리 업체별 PD매대 설치현황
	@RequestMapping("/20/20-107")
	public ModelAndView display4Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-107");
		return mv;
	}
	//CVS 체크리스트
	@RequestMapping("/20/20-108")
	public ModelAndView display8Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-108");
		return mv;
	}
	
	//관리업체별 진열률현황(Grid버젼) A20180130 k2s
	@RequestMapping("/20/20-404")
	public ModelAndView display404Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-404");
		return mv;
	}
	
	
	//관리업체별 취급률현황(Grid버젼) A20180130 k2s
	@RequestMapping("/20/20-405")
	public ModelAndView display405Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-405");
		return mv;
	}
	
	//관리업체별 보조진열현황(Grid버젼) A20180130 k2s
	@RequestMapping("/20/20-406")
	public ModelAndView display406Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-406");
		return mv;
	}
	
	//관리 업체별 PD매대현황(Grid버젼) A20180124 k2s
	@RequestMapping("/20/20-407")
	public ModelAndView display407Pg() throws Exception {
		mv = new ModelAndView();
		mv.setViewName("/20/20-407");
		return mv;
	}
	
	//진열률 조회
	@RequestMapping("/display/arrlist")
	public ModelAndView displayArrList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		System.out.println("["+CommonUtil.getCurrentDateTime() + " Web:/display/arrlist]  : POG 및 현제 진열줄수 조회  base_de : "+naviVo.toString()+" cm_code : "+cm_code);
//		vo.setD_innb("4");
		
		naviVo =  dao.displayArrListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		DisplayGridVo result =  dao.displayArrList(naviVo);
//		jVo = JSONObject.fromObject(JSONSerializer.toJSON(result));
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jObj.put("navi", navi);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}
	
	//보조진열 조회
	@RequestMapping("/display/biglist")
	public ModelAndView displayBigList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/display/biglist]  : 보조진열현황 조회  base_de : "+naviVo.toString()+" cm_code : "+cm_code);
//		vo.setD_innb("4");
		naviVo =  dao.displayBigListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		DisplayGridVo result =  dao.displayBigList(naviVo);
//		jVo = JSONObject.fromObject(JSONSerializer.toJSON(result));
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jObj.put("navi", navi);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}
	
	//Pd 조회
	@RequestMapping("/display/pdlist")
	public ModelAndView displayPdList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/display/pdlist]  : PD매대현황 조회  base_de : "+naviVo.toString()+" cm_code : "+cm_code);
//		vo.setD_innb("4");
		naviVo =  dao.displayPdListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		DisplayGridVo result =  dao.displayPdList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jObj.put("navi", navi);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}
	
	//취급품목
	@RequestMapping("/display/trtlist")
	public ModelAndView displayTrtList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/display/trtlist]  : 취급품목현황 조회  base_de : "+naviVo.toString()+" cm_code : "+cm_code);
		naviVo =  dao.displayTrtListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		DisplayGridVo result =  dao.displayTrtList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jObj.put("navi", navi);
		jObj.put("totRow", naviVo.getTotRow() );
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}
	
	////////////////////////////////////////////////////////통계 사원별 진열률 저장부분111111111111////////////////////////////////////////////////////////////////////////
	
	//사원별 진열률 그룹 저장
	@RequestMapping("/display/prdsave1")
	public ModelAndView displayPrdSave1(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		String login_no = (String)session.getAttribute("login_no");
		System.out.println("displayPrdSave : "+vo.toString());
		vo.setEm_no(login_no); 
		
		int cnt =  dao.displayPrdSave1(vo);
		
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdList jArr : " + jArr);
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	//관리업체별 진열률 그룹 아이템 저장
	@RequestMapping("/display/prditemsave1")
	public ModelAndView displayPrdItemSave1(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		String login_no = (String)session.getAttribute("login_no");
		System.out.println("displayPrdSave : "+vo.toString());
		vo.setEm_no(login_no); 
		int cnt =  dao.displayPrdItemSave1(vo);
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdList jArr : " + jArr);
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	////////////////////////////////////////////////////////통계 사원별 보조진열 저장부분333333333333333////////////////////////////////////////////////////////////////////////
	
	//사원별 보조진열 그룹 저장
	@RequestMapping("/display/prdsave3")
	public ModelAndView displayPrdSave3(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		String login_no = (String)session.getAttribute("login_no");
		System.out.println("displayPrdSave : "+vo.toString());
		vo.setEm_no(login_no); 
		
		int cnt =  dao.displayPrdSave3(vo);
		
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdList jArr : " + jArr);
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	//사원별 보조진열 그룹 아이템 저장
	@RequestMapping("/display/prditemsave3")
	public ModelAndView displayPrdItemSave3(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		String login_no = (String)session.getAttribute("login_no");
		System.out.println("displayPrdSave : "+vo.toString());
		vo.setEm_no(login_no); 
		int cnt =  dao.displayPrdItemSave3(vo);
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdList jArr : " + jArr);
		mv.addObject("ajax", cnt );
		return mv;
		
	}
	
	////////////////////////////////////////////////////////통계 항목 저장부분44444444444444////////////////////////////////////////////////////////////////////////
	
	//관리업체별 진열률 그룹 저장
	@RequestMapping("/display/prdsave4")
	public ModelAndView displayPrdSave4(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		String login_no = (String)session.getAttribute("login_no");
		System.out.println("displayPrdSave : "+vo.toString());
		vo.setEm_no(login_no); 
		
		int cnt =  dao.displayPrdSave4(vo);
		
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdList jArr : " + jArr);
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	//관리업체별 진열률 그룹 아이템 저장
	@RequestMapping("/display/prditemsave4")
	public ModelAndView displayPrdItemSave4(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		String login_no = (String)session.getAttribute("login_no");
		System.out.println("displayPrdSave : "+vo.toString());
		vo.setEm_no(login_no); 
		int cnt =  dao.displayPrdItemSave4(vo);
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdList jArr : " + jArr);
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	////////////////////////////////////////////////////////통계 항목 저장부분555555555555////////////////////////////////////////////////////////////////////////
	
	//관리업체별 취급품목 그룹 저장
	@RequestMapping("/display/prdsave5")
	public ModelAndView displayPrdSave5(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		String login_no = (String)session.getAttribute("login_no");
		System.out.println("displayPrdSave : "+vo.toString());
		vo.setEm_no(login_no); 
		
		int cnt =  dao.displayPrdSave5(vo);
		
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdList jArr : " + jArr);
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	////////////////////////////////////////////////////////통계 항목 저장부분66666666666666666666////////////////////////////////////////////////////////////////////////
	
	//관리업체별 진열률 그룹 저장
	@RequestMapping("/display/prdsave6")
	public ModelAndView displayPrdSave6(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		String login_no = (String)session.getAttribute("login_no");
		System.out.println("displayPrdSave : "+vo.toString());
		vo.setEm_no(login_no); 
		
		int cnt =  dao.displayPrdSave6(vo);
		
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdList jArr : " + jArr);
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	//관리업체별 진열률 그룹 아이템 저장
	@RequestMapping("/display/prditemsave6")
	public ModelAndView displayPrdItemSave6(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		String login_no = (String)session.getAttribute("login_no");
		System.out.println("displayPrdSave : "+vo.toString());
		vo.setEm_no(login_no); 
		int cnt =  dao.displayPrdItemSave6(vo);
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdList jArr : " + jArr);
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	////////////////////////////////////////////////////////통계 PD매대 저장부분/777777777777777777777///////////////////////////////////////////////////////////////////////
	
	//관리업체별 PD매대 그룹 저장
	@RequestMapping("/display/prdsave7")
	public ModelAndView displayPrdSave7(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		String login_no = (String)session.getAttribute("login_no");
		System.out.println("displayPrdSave : "+vo.toString());
		vo.setEm_no(login_no); 
		
		int cnt =  dao.displayPrdSave7(vo);
		
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdList jArr : " + jArr);
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	//관리업체별 PD매대 그룹 아이템 저장
	@RequestMapping("/display/prditemsave7")
	public ModelAndView displayPrdItemSave7(@ModelAttribute("vo") DisPlayVo vo) throws Exception {
		String login_no = (String)session.getAttribute("login_no");
		System.out.println("displayPrdSave : "+vo.toString());
		vo.setEm_no(login_no); 
		int cnt =  dao.displayPrdItemSave7(vo);
		mv = new ModelAndView(new JSONView());
		System.out.println("displayPrdList jArr : " + jArr);
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	////////////////////////////////////////////////다사 엑셀 다운로드start!!!//////////////////////////////////////////////////////////////
	
	@RequestMapping("/display/arrexcel")
	@ResponseBody
	/*A20170102 kks RequestParam da_date 추가*/
	/*A20171226 kks RequestParam code_at 추가*/
	public FileSystemResource displayArrExcelDownload(@RequestParam("om_code")  String om_code, @RequestParam("cg_code")  String cg_code,  @RequestParam("me_code") String me_code,
													  @RequestParam("em_nm")    String em_nm,   @RequestParam("sm_odr1")  String sm_odr1,  @RequestParam("sm_odr2") String sm_odr2,
													  @RequestParam("sm_area1") String sm_area1,@RequestParam("sm_area2") String sm_area2, @RequestParam("da_date") String da_date,
													  @RequestParam("code_at")  String code_at,
													  HttpServletResponse response)throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/display/arrexcel]  : 진열율 엑셀 다운");
		//System.out.println("사원명 ::" + em_nm);
		NaviVo naviVo = new NaviVo();
		
		Map<String,String> map = new HashMap<String, String>();

		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		
		map.put("om_code"  ,om_code);
		map.put("cg_code"  ,cg_code);
		map.put("me_code"  ,me_code);
		map.put("em_nm"    ,em_nm);
		map.put("sm_odr1"  ,sm_odr1);
		map.put("sm_odr2"  ,sm_odr2);
		map.put("sm_area1" ,sm_area1);
		map.put("sm_area2" ,sm_area2);
		map.put("da_date"  ,da_date);    /*A20170102 kks da_date 조회 조건 추가 */
		map.put("code_at"  ,code_at);    /*A20171226 kks code_at 조회 조건 추가 */
		naviVo.setParams(map);
		
		DisplayGridVo arrVo =  dao.displayExcelArrList(naviVo);
		ExcelVo excelVo =  new ExcelVo();
		excelVo.setBodyList(arrVo.getBodyArr());
		excelVo.setColumnList(arrVo.getColumnArr());
		excelVo.setColumnSecondList(arrVo.getColumnArr());  // 2016.8.3 추가 
		excelVo.setSumList(arrVo.getSumArr());
		excelVo.setFileName("진열율.xlsx");
		excelVo.setCodeAt((String)map.get("code_at"));   //A20171226 k2s
//		em = new ExcelManager(excelVo);
//		em.makeListExcel();
		return em.downloadFile(excelVo,response);
	}
	
	@RequestMapping("/display/bigexcel")
	@ResponseBody
	/*A20170102 kks RequestParam da_date 추가*/
	/*A20171226 kks RequestParam code_at 추가*/	
	public FileSystemResource displayBigExcelDownload(@RequestParam("om_code")  String om_code,  @RequestParam("cg_code")  String cg_code,  @RequestParam("me_code") String me_code,
													  @RequestParam("em_nm")    String em_nm,    @RequestParam("sm_odr1")  String sm_odr1,  @RequestParam("sm_odr2") String sm_odr2,
													  @RequestParam("sm_area1") String sm_area1, @RequestParam("sm_area2") String sm_area2, @RequestParam("da_date") String da_date,
													  @RequestParam("code_at")  String code_at,													  
													  HttpServletResponse response)throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/display/bigexcel]  : 보조진열률 엑셀 다운");
		
		NaviVo naviVo = new NaviVo();
		
		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("om_code"  ,om_code);
		map.put("cg_code"  ,cg_code);
		map.put("me_code"  ,me_code);
		map.put("em_nm"    ,em_nm);
		map.put("sm_odr1"  ,sm_odr1);
		map.put("sm_odr2"  ,sm_odr2);
		map.put("sm_area1" ,sm_area1);
		map.put("sm_area2" ,sm_area2);
		map.put("da_date"  ,da_date);    /*A20170102 kks da_date 조회 조건 추가 */
		map.put("code_at"  ,code_at);    /*A20171226 kks code_at 조회 조건 추가 */
		naviVo.setParams(map);
		
		DisplayGridVo bigVo =  dao.displayExcelBigList(naviVo); 
		ExcelVo excelVo =  new ExcelVo();
		excelVo.setBodyList(bigVo.getBodyArr());
		excelVo.setColumnList(bigVo.getColumnArr());
		excelVo.setColumnSecondList(bigVo.getColumnArr());
		excelVo.setSumList(bigVo.getSumArr());
		excelVo.setCodeAt((String)map.get("code_at"));   //A20171226 k2s
		excelVo.setFileName("보조진열.xlsx");
		return em.downloadFile(excelVo,response);
	}
	
	@RequestMapping("/display/pdexcel")
	@ResponseBody
	/*A20170102 kks RequestParam da_date 추가*/
	/*A20171226 kks RequestParam code_at 추가*/
	public FileSystemResource displayPdExcelDownload(@RequestParam("om_code")  String om_code, @RequestParam("cg_code")  String cg_code,  @RequestParam("me_code") String me_code,
													 @RequestParam("em_nm")    String em_nm,   @RequestParam("sm_odr1")  String sm_odr1,  @RequestParam("sm_odr2") String sm_odr2,
													 @RequestParam("sm_area1") String sm_area1,@RequestParam("sm_area2") String sm_area2, @RequestParam("da_date") String da_date,
													 @RequestParam("code_at")  String code_at,													 
													 HttpServletResponse response)throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/display/pdexcel]  : PD매대 엑셀 다운");
		
		NaviVo naviVo = new NaviVo();
		
		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("om_code"  ,om_code);
		map.put("cg_code"  ,cg_code);
		map.put("me_code"  ,me_code);
		map.put("em_nm"    ,em_nm);
		map.put("sm_odr1"  ,sm_odr1);
		map.put("sm_odr2"  ,sm_odr2);
		map.put("sm_area1" ,sm_area1);
		map.put("sm_area2" ,sm_area2);
		map.put("da_date"  ,da_date);    /*A20170102 kks da_date 조회 조건 추가 */
		map.put("code_at"  ,code_at);    /*A20171226 kks code_at 조회 조건 추가 */		
		naviVo.setParams(map);
		
		DisplayGridVo bigVo =  dao.displayExcelPdList(naviVo); 
		ExcelVo excelVo =  new ExcelVo();
		excelVo.setBodyList(bigVo.getBodyArr());
		excelVo.setColumnList(bigVo.getColumnArr());
		excelVo.setColumnSecondList(bigVo.getColumnArr());
		excelVo.setSumList(bigVo.getSumArr());
		excelVo.setCodeAt((String)map.get("code_at"));   //A20171226 k2s		
		excelVo.setFileName("PD매대.xlsx");
		return em.downloadFile(excelVo,response);
	}
	
	@RequestMapping("/display/trtexcel")
	@ResponseBody
	/*A20161230 kks RequestParam da_date 추가*/
	/*A20171226 kks RequestParam code_at 추가*/	
	public FileSystemResource displayTrtExcelDownload(@RequestParam("om_code")  String om_code,  @RequestParam("cg_code")  String cg_code,  @RequestParam("me_code") String me_code,
													  @RequestParam("em_nm")    String em_nm,    @RequestParam("sm_odr1")  String sm_odr1,  @RequestParam("sm_odr2") String sm_odr2,
													  @RequestParam("sm_area1") String sm_area1, @RequestParam("sm_area2") String sm_area2, @RequestParam("da_date") String da_date,
													  @RequestParam("code_at")  String code_at,
													  HttpServletResponse response)throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/display/trtexcel]  : 취급율 엑셀 다운");
		NaviVo naviVo = new NaviVo();
		System.out.println("관리업체별취급율 em_nm :" + em_nm);
		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("om_code"  ,om_code);
		map.put("cg_code"  ,cg_code);
		map.put("me_code"  ,me_code);
		map.put("em_nm"    ,em_nm);
		map.put("sm_odr1"  ,sm_odr1);
		map.put("sm_odr2"  ,sm_odr2);
		map.put("sm_area1" ,sm_area1);
		map.put("sm_area2" ,sm_area2);
		map.put("da_date"  ,da_date);    /*A20161230 kks da_date 조회 조건 추가 */
		map.put("code_at"  ,code_at);    /*A20171226 kks code_at 조회 조건 추가 */		
		naviVo.setParams(map);
		
		DisplayGridVo bigVo =  dao.displayExcelTrtList(naviVo); 
		ExcelVo excelVo =  new ExcelVo();
		excelVo.setBodyList(bigVo.getBodyArr());
		excelVo.setColumnList(bigVo.getColumnArr());
		excelVo.setSumList(bigVo.getSumArr());
		excelVo.setFileName("관리업체별취급율.xlsx");
		excelVo.setCodeAt((String)map.get("code_at"));   //A20171226 k2s		
		return em.downloadFile(excelVo,response);
	}
	
	////////////////////////////////////////////////다사 엑셀 다운로드end!!!//////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////다사 배치start!!!//////////////////////////////////////////////////////////////		
	
	@RequestMapping("/display/bach_arr")
	public ModelAndView bach_displayBatchArr() throws Exception {
		System.out.println("통계 배치 시작 >  현재 진열 줄수");
		int cnt = dao.displayBatchArr("4");
		System.out.println("등록성공 : " + cnt);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	@RequestMapping("/display/bach_trt")
	public ModelAndView bach_displayBatchTrt() throws Exception {
		System.out.println("통계 배치 시작 >  TRT 취급품목 현황 ");
		int cnt = dao.displayBatchTrt("5");
		System.out.println("등록성공 : " + cnt);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	@RequestMapping("/display/bach_big")
	public ModelAndView bach_displayBatchBig() throws Exception {
		System.out.println("통계 배치 시작 >  보조 진열 현황 ");
		int cnt = dao.displayBatchBig("6");
		System.out.println("등록성공 : " + cnt);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	@RequestMapping("/display/bach_pd")
	public ModelAndView bach_displayBatchPd() throws Exception {
		System.out.println("통계 배치 시작 >  PD매대 진열 현황 ");
		int cnt = dao.displayBatchPd("7");
		System.out.println("등록성공 : " + cnt);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	////////////////////////////////////////////////다사 배치end!!!//////////////////////////////////////////////////////////////			
	
	////////////////////////////////////////////////동서식품 인터페이스 start!!!/////////////////////////////////////////////////

	//동서식품 인터페이스 진열률 조회
	@RequestMapping("/ds_display/arrlist")
	public ModelAndView ds_displayArrList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + " Web:/ds_display/arrlist]  : 동서식품 통계 진열률 조회 : "+naviVo.toString());
		CommonUtil.setSessionVo(session);
//		naviVo.setCm_code("002");
		if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}
		naviVo =  dao.displayArrListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		DisplayGridVo result =  dao.displayArrList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jObj.put("navi", navi);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	//동서식품 인터페이스 보조진열 조회
	@RequestMapping("/ds_display/biglist")
	public ModelAndView ds_displayBigList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/ds_display/biglist]  : 동서식품 보조진열현황 조회 : "+naviVo.toString());
		CommonUtil.setSessionVo(session);
//		naviVo.setCm_code("002");
		if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}
		naviVo =  dao.displayBigListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		DisplayGridVo result =  dao.displayBigList(naviVo);
//			jVo = JSONObject.fromObject(JSONSerializer.toJSON(result));
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jObj.put("navi", navi);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	//동서식품 인터페이스 Pd 조회
	@RequestMapping("/ds_display/pdlist")
	public ModelAndView ds_displayPdList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/ds_display/pdlist] : 동서식품 PD매대현황 조회 : "+naviVo.toString());
		CommonUtil.setSessionVo(session);
//		naviVo.setCm_code("002");
		if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}
		naviVo =  dao.displayPdListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		DisplayGridVo result =  dao.displayPdList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jObj.put("navi", navi);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	//동서식품 인터페이스 취급품목
	@RequestMapping("/ds_display/trtlist")
	public ModelAndView ds_displayTrtList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/ds_display/trtlist] : 동서식품 취급품목현황 조회 : "+naviVo.toString());
		CommonUtil.setSessionVo(session);
//		naviVo.setCm_code("002");
		if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}
		naviVo =  dao.displayTrtListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		DisplayGridVo result =  dao.displayTrtList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jObj.put("navi", navi);
		jObj.put("totRow", naviVo.getTotRow() );
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	////////////////////////////////////////////////동서식품 인터페이스 end!!!/////////////////////////////////////////////////
	
	////////////////////////////////////////////////동서식품 엑셀 다운로드 start!!!//////////////////////////////////////////////////////////////
	
	@RequestMapping("/ds_display/arrexcel")
	@ResponseBody
	public FileSystemResource ds_displayArrExcelDownload(@RequestParam("cm_code") String cm_code, @RequestParam("om_code") String om_code,@RequestParam("cg_code") String cg_code,@RequestParam("me_code") String me_code,
														 @RequestParam("em_nm") String em_nm, @RequestParam("sm_odr") String sm_odr,@RequestParam("sm_area1") String sm_area1,
														 @RequestParam("sm_area2") String sm_area2, HttpServletResponse response)throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/ds_display/arrexcel]  : 동서식품 진열률 엑셀 다운");
		//System.out.println("한글em_nm UTF-8 : " + URLDecoder.decode(em_nm, "UTF-8"));
		//System.out.println("한글em_nm EUC-KR: " + URLDecoder.decode(em_nm, "EUC-KR"));
		CommonUtil.setSessionVo(session);
		NaviVo naviVo = new NaviVo();
		naviVo.setCm_code(cm_code);
		/*if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}*/
		Map<String,String> map = new HashMap<String, String>();
		System.out.println("=================================================================================================================");
		System.out.println("cm_code => "+ cm_code);
		System.out.println("om_code => "+ om_code);
		System.out.println("cg_code => "+ cg_code);
		System.out.println("me_code => "+ me_code);
		System.out.println("em_nm => "+ em_nm);
		System.out.println("sm_odr => "+ sm_odr);
		System.out.println("sm_area1 => "+ sm_area1);
		System.out.println("sm_area2 => "+ sm_area2);
		System.out.println("=================================================================================================================");
		map.put("om_code",om_code);
		map.put("cg_code", cg_code);
		map.put("me_code", me_code);
		map.put("em_nm", em_nm);
		map.put("sm_odr", sm_odr);
		map.put("sm_area1", sm_area1);
		map.put("sm_area2", sm_area2);
		naviVo.setParams(map);
//		naviVo.setCm_code("002");
		DisplayGridVo arrVo =  dao.displayExcelArrList(naviVo);
		ExcelVo excelVo =  new ExcelVo();
		excelVo.setBodyList(arrVo.getBodyArr());
		excelVo.setColumnList(arrVo.getColumnArr());
		excelVo.setSumList(arrVo.getSumArr());
		excelVo.setFileName("진열율.xlsx");
		//em = new ExcelManager(excelVo);
		//em.makeListExcel();
		return em.downloadFile(excelVo,response);
	}
	
	@RequestMapping("/ds_display/bigexcel")
	@ResponseBody
	public FileSystemResource ds_displayBigExcelDownload(@RequestParam("cm_code") String cm_code, @RequestParam("om_code") String om_code,@RequestParam("cg_code") String cg_code,@RequestParam("me_code") String me_code,
														 @RequestParam("em_nm") String em_nm, @RequestParam("sm_odr") String sm_odr,@RequestParam("sm_area1") String sm_area1,
														 @RequestParam("sm_area2") String sm_area2, HttpServletResponse response)throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/ds_display/bigexcel]  : 동서식품 보조진열률 엑셀 다운");
		CommonUtil.setSessionVo(session);
		
		NaviVo naviVo = new NaviVo();
		naviVo.setCm_code(cm_code);
		/*if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}*/
		Map<String,String> map = new HashMap<String, String>();
		System.out.println("=================================================================================================================");
		System.out.println("cm_code => "+ cm_code);
		System.out.println("om_code => "+ om_code);
		System.out.println("cg_code => "+ cg_code);
		System.out.println("me_code => "+ me_code);
		System.out.println("em_nm => "+ em_nm);
		System.out.println("sm_odr => "+ sm_odr);
		System.out.println("sm_area1 => "+ sm_area1);
		System.out.println("sm_area2 => "+ sm_area2);
		System.out.println("=================================================================================================================");
		map.put("om_code",om_code);
		map.put("cg_code", cg_code);
		map.put("me_code", me_code);
		map.put("em_nm", em_nm);
		map.put("sm_odr", sm_odr);
		map.put("sm_area1", sm_area1);
		map.put("sm_area2", sm_area2);
		naviVo.setParams(map);
//		naviVo.setCm_code("002");
		DisplayGridVo bigVo =  dao.displayExcelBigList(naviVo); 
		ExcelVo excelVo =  new ExcelVo();
		excelVo.setBodyList(bigVo.getBodyArr());
		excelVo.setColumnList(bigVo.getColumnArr());
		excelVo.setColumnSecondList(bigVo.getColumnArr());
		excelVo.setSumList(bigVo.getSumArr());
		excelVo.setFileName("보조진열.xlsx");
		return em.downloadFile(excelVo,response);
	}
	
	@RequestMapping("/ds_display/pdexcel")
	@ResponseBody
	public FileSystemResource ds_displayPdExcelDownload(@RequestParam("cm_code") String cm_code, @RequestParam("om_code") String om_code,@RequestParam("cg_code") String cg_code,@RequestParam("me_code") String me_code,
														@RequestParam("em_nm") String em_nm, @RequestParam("sm_odr") String sm_odr,@RequestParam("sm_area1") String sm_area1,
														@RequestParam("sm_area2") String sm_area2, HttpServletResponse response)throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/ds_display/pdexcel]  : 동서식품 PD매대 엑셀 다운");
		CommonUtil.setSessionVo(session);
		
		NaviVo naviVo = new NaviVo();
		naviVo.setCm_code(cm_code);
		/*if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}*/
		Map<String,String> map = new HashMap<String, String>();
		System.out.println("=================================================================================================================");
		System.out.println("cm_code => "+ cm_code);
		System.out.println("om_code => "+ om_code);
		System.out.println("cg_code => "+ cg_code);
		System.out.println("me_code => "+ me_code);
		System.out.println("em_nm => "+ em_nm);
		System.out.println("sm_odr => "+ sm_odr);
		System.out.println("sm_area1 => "+ sm_area1);
		System.out.println("sm_area2 => "+ sm_area2);
		System.out.println("=================================================================================================================");
		map.put("om_code",om_code);
		map.put("cg_code", cg_code);
		map.put("me_code", me_code);
		map.put("em_nm", em_nm);
		map.put("sm_odr", sm_odr);
		map.put("sm_area1", sm_area1);
		map.put("sm_area2", sm_area2);
		naviVo.setParams(map);
		DisplayGridVo bigVo =  dao.displayExcelPdList(naviVo); 
		ExcelVo excelVo =  new ExcelVo();
		excelVo.setBodyList(bigVo.getBodyArr());
		excelVo.setColumnList(bigVo.getColumnArr());
		excelVo.setColumnSecondList(bigVo.getColumnArr());
		excelVo.setSumList(bigVo.getSumArr());
		excelVo.setFileName("PD매대.xlsx");
		return em.downloadFile(excelVo,response);
	}
	@RequestMapping("/ds_display/trtexcel")
	@ResponseBody
	public FileSystemResource ds_displayTrtExcelDownload(@RequestParam("cm_code") String cm_code, @RequestParam("om_code") String om_code,@RequestParam("cg_code") String cg_code,@RequestParam("me_code") String me_code,
														 @RequestParam("em_nm") String em_nm, @RequestParam("sm_odr") String sm_odr,@RequestParam("sm_area1") String sm_area1,
														 @RequestParam("sm_area2") String sm_area2, HttpServletResponse response)throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/ds_display/pdexcel]  : 동서식품 취급률 엑셀 다운");
		 
		CommonUtil.setSessionVo(session);
		
		NaviVo naviVo = new NaviVo();
		naviVo.setCm_code(cm_code);
		/*if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}*/
		Map<String,String> map = new HashMap<String, String>();
		System.out.println("=================================================================================================================");
		System.out.println("cm_code => "+ cm_code);
		System.out.println("om_code => "+ om_code);
		System.out.println("cg_code => "+ cg_code);
		System.out.println("me_code => "+ me_code);
		System.out.println("em_nm => "+ em_nm);
		System.out.println("sm_odr => "+ sm_odr);
		System.out.println("sm_area1 => "+ sm_area1);
		System.out.println("sm_area2 => "+ sm_area2);
		System.out.println("=================================================================================================================");
		map.put("om_code",om_code);
		map.put("cg_code", cg_code);
		map.put("me_code", me_code);
		map.put("em_nm", em_nm);
		map.put("sm_odr", sm_odr);
		map.put("sm_area1", sm_area1);
		map.put("sm_area2", sm_area2);
		naviVo.setParams(map);
		DisplayGridVo bigVo =  dao.displayExcelTrtList(naviVo); 
		ExcelVo excelVo =  new ExcelVo();
		excelVo.setBodyList(bigVo.getBodyArr());
		excelVo.setColumnList(bigVo.getColumnArr());
		excelVo.setSumList(bigVo.getSumArr());
		excelVo.setFileName("취급율.xlsx");
		return em.downloadFile(excelVo,response);
	}
	////////////////////////////////////////////////동서식품 엑셀 다운로드 end!!!//////////////////////////////////////////////////////////////	
	
	////////////////////////////////////////////////모바일 동서식품 인터페이스start!!!/////////////////////////////////////////////////
	
	//동서식품 인터페이스 진열률 조회
	@RequestMapping("/ds_display/m_arrlist")
	public ModelAndView ds_displayMobileArrList(@ModelAttribute("vo") NaviVo naviVo, @RequestParam("strParams") String strParams ) throws Exception{
		naviVo.setParams(CommonUtil.stringToObj(strParams));
		System.out.println("["+CommonUtil.getCurrentDateTime() + " Web:/ds_display/m_arrlist] 모바일 : 동서식품 통계 진열률 조회 : "+naviVo.toString());
		CommonUtil.setSessionVo(session);
		if ( naviVo.getCurrPg() ==0 || naviVo.getRowSize() == 0 || StringCheck.isNull(strParams)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
//		naviVo.setCm_code("002");
		if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}
		naviVo =  dao.displayArrListCnt(naviVo);
		DisplayGridVo result =  dao.displayArrList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jObj.put("totRow", naviVo.getTotRow() );
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax",CommonUtil.setSuccessResponse(jObj));
		return mv;
	}
	
	//동서식품 인터페이스 보조진열 조회
	@RequestMapping("/ds_display/m_biglist")
	public ModelAndView ds_displayMobileBigList(@ModelAttribute("vo") NaviVo naviVo, @RequestParam("strParams") String strParams ) throws Exception{
		naviVo.setParams(CommonUtil.stringToObj(strParams));
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/ds_display/m_biglist] 모바일 : 동서식품 보조진열현황 조회 : "+naviVo.toString());
		CommonUtil.setSessionVo(session);
		if ( naviVo.getCurrPg() ==0 || naviVo.getRowSize() == 0 || StringCheck.isNull(strParams)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
//		naviVo.setCm_code("002");
		if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}
		naviVo =  dao.displayBigListCnt(naviVo);
		DisplayGridVo result =  dao.displayBigList(naviVo);
		//jVo = JSONObject.fromObject(JSONSerializer.toJSON(result));
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jObj.put("totRow", naviVo.getTotRow() );
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax",CommonUtil.setSuccessResponse(jObj));
		return mv;
	}
	
	//동서식품 인터페이스 Pd 조회
	@RequestMapping("/ds_display/m_pdlist")
	public ModelAndView ds_displayMobilePdList(@ModelAttribute("vo") NaviVo naviVo, @RequestParam("strParams") String strParams ) throws Exception{
		naviVo.setParams(CommonUtil.stringToObj(strParams));
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/ds_display/m_pdlist] 모바일 : 동서식품 PD매대현황 조회 : "+naviVo.toString());
		CommonUtil.setSessionVo(session);
		if ( naviVo.getCurrPg() ==0 || naviVo.getRowSize() == 0 || StringCheck.isNull(strParams)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
//		naviVo.setCm_code("002");
		if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}
		naviVo =  dao.displayPdListCnt(naviVo);
		DisplayGridVo result =  dao.displayPdList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jObj.put("totRow", naviVo.getTotRow() );
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax",CommonUtil.setSuccessResponse(jObj));
		return mv;
	}
	
	//동서식품 인터페이스 취급품목
	@RequestMapping("/ds_display/m_trtlist")
	public ModelAndView ds_displayMobileTrtList(@ModelAttribute("vo") NaviVo naviVo, @RequestParam("strParams") String strParams ) throws Exception{
		naviVo.setParams(CommonUtil.stringToObj(strParams));
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/ds_display/m_trtlist] 모바일 : 동서식품 취급품목현황 조회 : "+naviVo.toString());
		CommonUtil.setSessionVo(session);
		if ( naviVo.getCurrPg() ==0 || naviVo.getRowSize() == 0 || StringCheck.isNull(strParams)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
//		naviVo.setCm_code("002");
		if(naviVo.getCm_code() == null || naviVo.getCm_code() == ""){
			naviVo.setCm_code("002");
		}
		naviVo =  dao.displayTrtListCnt(naviVo);
		DisplayGridVo result =  dao.displayTrtList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jObj.put("totRow", naviVo.getTotRow() );
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax",CommonUtil.setSuccessResponse(jObj));
		return mv;
	}
	
	////////////////////////////////////////////////모바일 동서식품 인터페이스end!!!/////////////////////////////////////////////////	
	
	
	// CVS 체크리스트 조회
	@RequestMapping("/display/cvsChkList")
	public ModelAndView displaycvsChkList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{		
		
		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		
		// cvs 아이템 조회
		AnalysisCvsVo analysisCvsVo = dao.cvsChkList(naviVo);
		
		jObj = new JSONObject();		
		jArr = JSONArray.fromObject(analysisCvsVo.getHeadArr());	
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(analysisCvsVo.getBodyArr());	
		jObj.put("dataArr", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);		
		
		return mv;
	}

	//관리 업체별 PD매대현황(Grid버젼) 조회 A20180124 k2s
	@RequestMapping("/display/pdGridList")
	public ModelAndView displayPdGridList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/display/pdGridList]  : PD매대현황(Grid Ver.) 조회  base_de : "+naviVo.toString()+" cm_code : "+cm_code);
		
		naviVo               =  dao.displayPdListCnt(naviVo);   //관리 업체별 PD매대현황(Grid버젼) Count 조회
		String          navi = new Navi(naviVo).getPageNavi();
		
		DisplayGridVo result =  dao.displayPdGridList(naviVo);  //관리 업체별 PD매대현황(Grid버젼) 내용 조회
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jArr = JSONArray.fromObject(result.getHeaderArr());
		jObj.put("headerArr", jArr);
		jObj.put("navi", navi);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}

	//관리업체별 보조진열현황(Grid버젼) 조회 A20180130 k2s
	@RequestMapping("/display/bigGridList")
	public ModelAndView displayBigGridList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/display/bigGridList]  : 관리업체별 보조진열현황(Grid Ver.) 조회  base_de : "+naviVo.toString()+" cm_code : "+cm_code);
		
		naviVo               =  dao.displayBigListCnt(naviVo);   //관리업체별 보조진열현황(Grid버젼) Count 조회
		String          navi = new Navi(naviVo).getPageNavi();
		
		DisplayGridVo result =  dao.displayBigGridList(naviVo);  //관리업체별 보조진열현황(Grid버젼) 내용 조회
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jArr = JSONArray.fromObject(result.getHeaderArr());
		jObj.put("headerArr", jArr);		
		jObj.put("navi", navi);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}
	
	//관리업체별 진열률현황 조회(Grid버젼) 조회 A20180130 k2s
	@RequestMapping("/display/arrGridList")
	public ModelAndView displayArrGridList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		System.out.println("["+CommonUtil.getCurrentDateTime() + " Web:/display/arrGridList]  : 관리업체별 진열률현황(Grid Ver.) 조회  base_de : "+naviVo.toString()+" cm_code : "+cm_code);
		
		naviVo               =  dao.displayArrListCnt(naviVo);    //관리업체별 진열률현황(Grid버젼) Count 조회
		String          navi = new Navi(naviVo).getPageNavi();
		
		DisplayGridVo result =  dao.displayArrGridList(naviVo);   //관리업체별 진열률현황(Grid버젼) 내용 조회
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jArr = JSONArray.fromObject(result.getHeaderArr());
		jObj.put("headerArr", jArr);		
		jObj.put("navi", navi);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}	

	//관리업체별 취급률현황 조회(Grid버젼) 조회 A20180130 k2s
	@RequestMapping("/display/trtGridList")
	public ModelAndView displayTrtGridList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		String cm_code = (String)session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/display/trtGridList]  : 취급률현황현황(Grid Ver.) 조회  base_de : "+naviVo.toString()+" cm_code : "+cm_code);
		
		naviVo               =  dao.displayTrtListCnt(naviVo);
		String          navi = new Navi(naviVo).getPageNavi();
		
		DisplayGridVo result =  dao.displayTrtGridList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(result.getColumnArr());
		jObj.put("columnArr", jArr);
		jArr = JSONArray.fromObject(result.getBodyArr());
		jObj.put("bodyArr", jArr);
		jArr = JSONArray.fromObject(result.getSumArr());
		jObj.put("sumArr", jArr);
		jArr = JSONArray.fromObject(result.getHeaderArr());
		jObj.put("headerArr", jArr);		
		jObj.put("navi", navi);
		jObj.put("totRow", naviVo.getTotRow() );
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}	
}


