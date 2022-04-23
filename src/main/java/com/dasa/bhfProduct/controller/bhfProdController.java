package com.dasa.bhfProduct.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.bhfProduct.dao.bhfProdDAO;
import com.vertexid.utill.JSONView;

/**
 * @ ProdController.java
 * @ 2018. 01. 00.
 * @ 
 * @ 프로그램명: ProdController
 * 지점별 취급품목(상품) 관리
 */
@Controller
public class bhfProdController {
	private Logger logger = Logger.getLogger("dasa");
	
	@Autowired
	private HttpSession session;
	@Autowired
	private bhfProdDAO dao;
	
	private ModelAndView mv;
	private JSONObject jObj;
	private JSONArray jArr;
	private JSONObject response;
	
	private List<Map<String, Object>> resultList;
	private Map<String, Object> resultMap;
	
	@RequestMapping("/65/65-100") 
	public String prodMngMain(){
		return "/65/65-100";
	}
	
	@RequestMapping("/bhfProd/prodList") 
	public ModelAndView prodList(@RequestParam Map<String, Object> param) throws Exception{
		resultList = dao.prodList(param);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(resultList);
		jObj.put("resultList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	@RequestMapping("/bhfProd/prodUseAtUpt") 
	public ModelAndView prodUseAtUpt(@RequestParam Map<String, Object> param) throws Exception{
		String login_no = (String)session.getAttribute("login_no");
		param.put("regist_man", login_no);
		
		int cnt = dao.prodUseAtUpt(param);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt);
		return mv;
	}
}


