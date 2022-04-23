package com.dasa.analysis.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.analysis.dao.DisPlayDao;
import com.dasa.analysis.vo.AnalysisCvsVo;
import com.dasa.analysis.vo.DisPlayColunmVo;
import com.dasa.analysis.vo.DisPlayVo;
import com.dasa.analysis.vo.DisplayGridVo;
import com.vertexid.utill.CommonUtil;
import com.vertexid.vo.NaviVo;

public class DisPlayService  extends SqlSessionDaoSupport implements DisPlayDao{

	@Override
	public List<DisPlayVo> displayList() throws SQLException {
		return getSqlSession().selectList("displayList");
	}

	@Override
	public List<DisPlayVo> displayPrdList(DisPlayVo vo) throws SQLException {
		return getSqlSession().selectList("displayPrdList",vo);
	}

	@Override
	public List<DisPlayVo> displayPrdItemList(DisPlayVo vo) throws SQLException {
//		System.out.println("vo : " + vo.toString());
		return getSqlSession().selectList("displayPrdItemList",vo);
	}

	
/////////////////////////////////////////////////////////////사원별 진열률111111111111111111111111/////////////////////////////////////////////////////////////////////
	@Override
	public int displayPrdSave1(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dp_innb").equals("")){
				map.put("dp_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			cnt = getSqlSession().insert("displayPrdSave", map);
		}
		return cnt;
	}
	
	@Override
	public int displayPrdItemSave1(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		List< String> columnArr =  new ArrayList<String>();
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dpi_innb").equals("")){
				map.put("dpi_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			map.put("colunm", vo.getColunm());
			cnt += getSqlSession().insert("displayPrdItemSave", map);
			if(map.get("collect_at").equals("Y") ){
				columnArr.add("ent_"+vo.getColunm()+"_"+map.get("oi_nick_name"));
			}
			
		}
		columnArr.add("rate_"+vo.getColunm()+"_DSF");
		for(String colunm : columnArr){
//			System.out.println("column : " + colunm);
			Map<String, String> columnVo = getSqlSession().selectOne("displayPrdItemColumnCheck1", colunm);
//			System.out.println("column : " + colunm);
//			System.out.println("columnVo : " + columnVo);
			if(columnVo == null ){
				Map<String,String> map = new HashMap<String, String>();
				map.put("colunm", colunm);
				cnt += getSqlSession().insert("displayPrdItemColumnAdd1", map);
//				System.out.println("cnt : " + cnt);
			}
		}
		return cnt;
	}
/////////////////////////////////////////////////////////////사원별 진열률111111111111111111111111/////////////////////////////////////////////////////////////////////	
	
	
/////////////////////////////////////////////////////////////사원별 보조진열3333333333333333333333/////////////////////////////////////////////////////////////////////
	@Override
	public int displayPrdSave3(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dp_innb").equals("")){
				map.put("dp_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			cnt = getSqlSession().insert("displayPrdSave", map);
		}
		return cnt;
	}
	
	
	@Override
	public int displayPrdItemSave3(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		List< String> columnArr =  new ArrayList<String>();
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dpi_innb").equals("")){
				map.put("dpi_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			map.put("colunm", vo.getColunm());
			cnt += getSqlSession().insert("displayPrdItemSave", map);
			if(map.get("collect_at").equals("Y") ){
				columnArr.add("ent_"+vo.getColunm()+"_"+map.get("oi_nick_name"));
			}
			
		}
		for(String colunm : columnArr){
//			System.out.println("column : " + colunm);
			Map<String, String> columnVo = getSqlSession().selectOne("displayPrdItemColumnCheck3", colunm);
//			System.out.println("column : " + colunm);
//			System.out.println("columnVo : " + columnVo);
			if(columnVo == null ){
				Map<String,String> map = new HashMap<String, String>();
				map.put("colunm", colunm);
				cnt += getSqlSession().insert("displayPrdItemColumnAdd3", map);
//				System.out.println("cnt : " + cnt);
			}
		}
		return cnt;
	}
/////////////////////////////////////////////////////////////사원별 보조진열3333333333333333333333/////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////////////////////관리업체별 진열률44444444444444444444/////////////////////////////////////////////////////////////////////
	@Override
	public int displayPrdSave4(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dp_innb").equals("")){
				map.put("dp_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			cnt = getSqlSession().insert("displayPrdSave", map);
		}
		return cnt;
	}
	// 신규
	@Override
	public int displayPrdItemSave4(DisPlayVo vo) throws SQLException {
		String table = "tb_display_arr";
		int cnt = 0;
		List< String> columnArr =  new ArrayList<String>();
		for(Map<String,String> map : vo.getPrdArr()){
			map.put("em_no", vo.getEm_no());
			
			if(map.get("dpi_innb") == null || map.get("dpi_innb").equals("")){
				map.put("dpi_innb",null);
				cnt = getSqlSession().insert("displayPrdItemSaveInsert", map);
//				System.out.println("dpi_innb : " + map.get("dpi_innb"));
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb")+"_"+map.get("dpi_innb"));
				}
			}else{
				cnt = getSqlSession().insert("displayPrdItemSaveUpdate", map);
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb")+"_"+map.get("dpi_innb"));
				}
			}
		}
//		columnArr.add("etc_"+vo.getDp_innb()+"_DSF");
		columnArr.add("rate_"+vo.getDp_innb()+"_DSF");
		for(String colunm : columnArr){
			Map<String,String> map = new HashMap<String, String>();
			map.put("colunm", colunm);
			map.put("table", table);
			Map<String, String> columnVo = getSqlSession().selectOne("displayColumnCheck", map);
//			System.out.println("columnVo : " + columnVo);
			if(columnVo == null ){
				cnt += getSqlSession().insert("displayPrdItemColumnAdd", map);
//				System.out.println("cnt : " + cnt);
			}
		}
		return cnt;
	}
/////////////////////////////////////////////////////////////관리업체별 진열률44444444444444444444/////////////////////////////////////////////////////////////////////
	
	
	
/////////////////////////////////////////////////////////////관리업체별 보조진열현황666666666666666666666666/////////////////////////////////////////////////////////////////////
	@Override
	public int displayPrdSave6(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dp_innb").equals("")){
				map.put("dp_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			cnt = getSqlSession().insert("displayPrdSave", map);
		}
		return cnt;
	}
	
	
	@Override
	public int displayPrdItemSave6(DisPlayVo vo) throws SQLException {
		String table = "tb_display_big";
		int cnt = 0;
		List< String> columnArr =  new ArrayList<String>();
		for(Map<String,String> map : vo.getPrdArr()){
			map.put("em_no", vo.getEm_no());
			
			if(map.get("dpi_innb") == null || map.get("dpi_innb").equals("")){
				map.put("dpi_innb",null);
				cnt = getSqlSession().insert("displayPrdItemSaveInsert", map);
//				System.out.println("dpi_innb : " + map.get("dpi_innb"));
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb")+"_"+map.get("dpi_innb"));
				}
			}else{
				cnt = getSqlSession().insert("displayPrdItemSaveUpdate", map);
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb")+"_"+map.get("dpi_innb"));
				}
			}
		}
		for(String colunm : columnArr){
			Map<String,String> map = new HashMap<String, String>();
			map.put("colunm", colunm);
			map.put("table", table);
			Map<String, String> columnVo = getSqlSession().selectOne("displayColumnCheck", map);
//			System.out.println("columnVo : " + columnVo);
			if(columnVo == null ){
				cnt += getSqlSession().insert("displayPrdItemColumnAdd", map);
			}
		}
//		System.out.println("cnt : " + cnt);
		return cnt;
	}
/////////////////////////////////////////////////////////////관리업체별 보조진열현황666666666666666666666666/////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////////////////////관리업체별 PD매대설치현황7777777777777777777777/////////////////////////////////////////////////////////////////////	
	
	@Override
	public int displayPrdSave7(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dp_innb").equals("")){
				map.put("dp_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			cnt = getSqlSession().insert("displayPrdSave", map);
		}
		return cnt;
	}
	
	@Override
	public int displayPrdItemSave7(DisPlayVo vo) throws SQLException {
		String table = "tb_display_pd";
		int cnt = 0;
		List< String> columnArr =  new ArrayList<String>();
		for(Map<String,String> map : vo.getPrdArr()){
			map.put("em_no", vo.getEm_no());
			
			if(map.get("dpi_innb") == null || map.get("dpi_innb").equals("")){
				map.put("dpi_innb",null);
				cnt = getSqlSession().insert("displayPrdItemSaveInsert", map);
//				System.out.println("dpi_innb : " + map.get("dpi_innb"));
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb")+"_"+map.get("dpi_innb"));
				}
			}else{
				cnt = getSqlSession().insert("displayPrdItemSaveUpdate", map);
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb")+"_"+map.get("dpi_innb"));
				}
			}
		}
		for(String colunm : columnArr){
			Map<String,String> map = new HashMap<String, String>();
			map.put("colunm", colunm);
			map.put("table", table);
			Map<String, String> columnVo = getSqlSession().selectOne("displayColumnCheck", map);
//			System.out.println("columnVo : " + columnVo);
			if(columnVo == null ){
				cnt += getSqlSession().insert("displayPrdItemColumnAdd", map);
//				System.out.println("cnt : " + cnt);
			}
		}
		return cnt;
	}
/////////////////////////////////////////////////////////////관리업체별 PD매대설치현황7777777777777777777777/////////////////////////////////////////////////////////////////////	

	
	// 취급품목
	@Override
	public int displayPrdSave5(DisPlayVo vo) throws SQLException {
		String table = "tb_display_trt";
		int cnt = 0;
		List< String> columnArr =  new ArrayList<String>();
		for(Map<String,String> map : vo.getPrdArr()){
			map.put("em_no", vo.getEm_no());
			
			if(map.get("dp_innb") == null || map.get("dp_innb").equals("")){
				map.put("dp_innb",null);
				cnt = getSqlSession().insert("displayPrdSaveInsert", map);
//				System.out.println("dp_innb : " + map.get("dp_innb"));
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb"));
				}
			}else{
				cnt = getSqlSession().insert("displayPrdSaveUpdate", map);
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb"));
				}
			}
		}
		
		for(String colunm : columnArr){
//			System.out.println("column : " + colunm);
			Map<String,String> map = new HashMap<String, String>();
			map.put("colunm", colunm);
			map.put("table", table);
			Map<String, String> columnVo = getSqlSession().selectOne("displayColumnCheck", map);
			if(columnVo == null ){
				cnt += getSqlSession().insert("displayPrdItemColumnAdd", map);
//				System.out.println("cnt : " + cnt);
			}
		}
		return cnt;
	}

	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	@Override
	public int displayBatchArr(String type) throws SQLException ,ParseException{
		int cnt = 1;
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", type);
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		String tempGroupId = "";
		for (DisPlayColunmVo vo : displayList) {
			String groupId = vo.getGroup_id();
			String groupNm = vo.getGroup_nm();
			String itemId = vo.getItem_id();
			String itemNm = vo.getItem_nm();
			
			if(!tempGroupId.equals(groupId)){
				Map<String,String> mapRate =  new HashMap<String, String>();
				mapRate.put("group_id", groupId);
				mapRate.put("item_id", "DSF");
				mapRate.put("group_nm", groupNm);
				mapRate.put("item_nm", "DSF");
				mapRate.put("colunm_id", "rate");
				columnArr.add(mapRate);
//				Map<String,String> mapEtc =  new HashMap<String, String>();
//				mapEtc.put("group_id", groupId);
//				mapEtc.put("item_id", "DSF");
//				mapEtc.put("group_nm", groupNm);
//				mapEtc.put("item_nm", "DSF");
//				mapEtc.put("colunm_id", "etc");
//				columnArr.add(mapEtc);
			}
			tempGroupId  = groupId;
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
//		System.out.println("columnArr : " + columnArr );
		
		
		DisPlayVo vo = new DisPlayVo();
		vo.setPrdArr(columnArr);
		vo.setD_innb(type);
		vo.setBase_de(CommonUtil.getSysDe());
		
		
		int delCnt = getSqlSession().delete("displayDeleteListArr", CommonUtil.getSysDe());
//		System.out.println("Arr 삭제 : " + delCnt);
		
		
		cnt +=  getSqlSession().insert("displayInsertListArr", vo);
//		List<String> omList = new ArrayList<String>();
//		omList = getSqlSession().selectList("displayOmList");
//		for (String om_code : omList) {
////			System.out.println("진열률 지점별 배치 om_code : " +om_code);
//			vo.setOm_code(om_code);
//			try {
//				cnt +=  getSqlSession().insert("displayInsertListArr", vo);
//			} catch (Exception e) {
//				cnt = 0;
//				e.printStackTrace();			
//			}
//		}
		
		return cnt;
	}

	@Override
	public int displayBatchPd(String type) throws SQLException,ParseException {
		int cnt = 1;
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", type);
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
//		String tempGroupId = "";
		for (DisPlayColunmVo vo : displayList) {
			String groupId = vo.getGroup_id();
			String groupNm = vo.getGroup_nm();
			String itemId = vo.getItem_id();
			String itemNm = vo.getItem_nm();
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
		DisPlayVo vo = new DisPlayVo();
		vo.setPrdArr(columnArr);
		vo.setD_innb(type);
		vo.setBase_de(CommonUtil.getSysDe());
		
		int delCnt = getSqlSession().delete("displayDeleteListPd", CommonUtil.getSysDe());
//		System.out.println("PD 삭제 : " + delCnt);
		
		cnt +=  getSqlSession().insert("displayInsertListPd", vo);
//		List<String> omList = new ArrayList<String>();
//		omList = getSqlSession().selectList("displayOmList");
//		for (String om_code : omList) {
////			System.out.println("PD매대 지점별 배치 om_code : " +om_code);
//			vo.setOm_code(om_code);
//			try {
//				cnt +=  getSqlSession().insert("displayInsertListPd", vo);
//			} catch (Exception e) {
//				cnt = 0;
//				e.printStackTrace();			
//			}
//		}
		
		return cnt;
	}

	@Override
	public int displayBatchBig(String type) throws SQLException,ParseException {
		int cnt = 1;
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", type);
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		for (DisPlayColunmVo vo : displayList) {
			String groupId = vo.getGroup_id();
			String groupNm = vo.getGroup_nm();
			String itemId = vo.getItem_id();
			String itemNm = vo.getItem_nm();
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
		
		DisPlayVo vo = new DisPlayVo();
		vo.setPrdArr(columnArr);
		vo.setD_innb(type);
		vo.setBase_de(CommonUtil.getSysDe());
		
		int delCnt = getSqlSession().delete("displayDeleteListBig", CommonUtil.getSysDe());
//		System.out.println("Big 삭제 : " + delCnt);

		
		
		cnt +=  getSqlSession().insert("displayInsertListBig", vo);
//		List<String> omList = new ArrayList<String>();
//		omList = getSqlSession().selectList("displayOmList");
//		for (String om_code : omList) {
////			System.out.println("진열률 지점별 배치 om_code : " +om_code);
//			vo.setOm_code(om_code);
//			try {
//				cnt +=  getSqlSession().insert("displayInsertListBig", vo);
//			} catch (Exception e) {
//				cnt = 0;
//				e.printStackTrace();			
//			}
//		}
		
//		System.out.println("insertcnt : " + cnt );
		return cnt;
	}

	@Override
	public int displayBatchTrt(String type) throws SQLException,ParseException {
		int cnt = 1;
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayMainColumnList", type);
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		for (DisPlayColunmVo vo : displayList) {
			String groupId = vo.getGroup_id();
			String groupNm = vo.getGroup_nm();
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("group_nm", groupNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
		
		DisPlayVo vo = new DisPlayVo();
		vo.setPrdArr(columnArr);
		vo.setD_innb(type);
		vo.setBase_de(CommonUtil.getSysDe());
		
		int delCnt = getSqlSession().delete("displayDeleteListTrt", CommonUtil.getSysDe());
		
		cnt +=  getSqlSession().insert("displayInsertListTrt", vo);
		
		return cnt;
	}

	@Override
	public DisplayGridVo displayArrList(NaviVo naviVo) throws SQLException {
		
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", "4" );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		String tempGroupId = "";
		String tempGroupNm= "";
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId  = colvo.getItem_id();
			String itemNm  = colvo.getItem_nm();
			
			if(i > 0  && !tempGroupId.equals(groupId)){
				Map<String,String> mapEtc =  new HashMap<String, String>();
				mapEtc.put("group_id", tempGroupId);
				mapEtc.put("item_id", "DSF");
				mapEtc.put("group_nm", tempGroupNm);
				mapEtc.put("item_nm", "DSF");
				mapEtc.put("colunm_id", "rate");
				columnArr.add(mapEtc);
			}
			tempGroupId  = groupId;
			tempGroupNm  = groupNm;
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
		Map<String,String> mapRate =  new HashMap<String, String>();
		mapRate.put("group_id", tempGroupId);
		mapRate.put("item_id", "DSF");
		mapRate.put("group_nm", tempGroupNm);
		mapRate.put("item_nm", "DSF");
		mapRate.put("colunm_id", "rate");
		columnArr.add(mapRate);
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		vo.setCm_code(naviVo.getCm_code());
		
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayArrBodyList", vo );
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayArrSumList", vo );
		
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}
	
	@Override
	public DisplayGridVo displayExcelArrList(NaviVo naviVo) throws SQLException {
		
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", "4" );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		String tempGroupId = "";
		String tempGroupNm= "";
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);

			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId = colvo.getItem_id();
			String itemNm = colvo.getItem_nm();
			
			if(i > 0  && !tempGroupId.equals(groupId)){
				Map<String,String> mapEtc =  new HashMap<String, String>();
				mapEtc.put("group_id", tempGroupId);
				mapEtc.put("item_id", "DSF");
				mapEtc.put("group_nm", tempGroupNm);
				//mapEtc.put("item_nm", "DSF");
				mapEtc.put("item_nm", "진열율");
				mapEtc.put("colunm_id", "rate");
				mapEtc.put("cellId", "rate_"+groupId+"_DSF");
				mapEtc.put("cellName", "진열율");
				columnArr.add(mapEtc);
			}
			tempGroupId  = groupId;
			tempGroupNm  = groupNm;
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			map.put("cellId", "ent_"+groupId+"_"+itemId);
			map.put("cellName", itemNm);
			columnArr.add(map);
		}
		Map<String,String> mapRate =  new HashMap<String, String>();
		mapRate.put("group_id", tempGroupId);
		mapRate.put("item_id", "DSF");
		mapRate.put("group_nm", tempGroupNm);
		mapRate.put("item_nm", "진열율");
		mapRate.put("colunm_id", "rate");
		mapRate.put("cellId", "rate_"+tempGroupId+"_DSF");
		mapRate.put("cellName", "진열율");
		columnArr.add(mapRate);
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		vo.setCm_code(naviVo.getCm_code());
		
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayArrBodyList_excel", vo );
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayArrSumList", vo );
		
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}
	
	@Override
	public NaviVo displayArrListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("displayArrListCnt", naviVo) );
		return naviVo;
	}

	@Override
	public NaviVo displayBigListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("displayBigListCnt", naviVo) );
		return naviVo;
	}

	
	@Override
	public DisplayGridVo displayBigList(NaviVo naviVo) throws SQLException {
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", "6" );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId  = colvo.getItem_id();
			String itemNm  = colvo.getItem_nm();
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		vo.setCm_code(naviVo.getCm_code());
		
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayBigBodyList", vo );
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayBigSumList", vo );
		
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}

	@Override
	public NaviVo displayPdListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("displayPdListCnt", naviVo) );
		return naviVo;
	}

	@Override
	public DisplayGridVo displayPdList(NaviVo naviVo) throws SQLException {
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", "7" );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId  = colvo.getItem_id();
			String itemNm  = colvo.getItem_nm();
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		vo.setCm_code(naviVo.getCm_code());
		
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayPdBodyList", vo );
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayPdSumList", vo );
		
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}

	@Override
	public NaviVo displayTrtListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("displayTrtListCnt", naviVo) );
		return naviVo;
	}

	@Override
	public DisplayGridVo displayTrtList(NaviVo naviVo) throws SQLException {
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayMainColumnList", "5" );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId  = colvo.getItem_id();
			String itemNm  = colvo.getItem_nm();
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("column_id", "ent");
			columnArr.add(map);
		}
		
		DisplayGridVo vo =  new DisplayGridVo();
		vo.setCm_code(naviVo.getCm_code());
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayTrtBodyList", vo );
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayTrtSumList", vo );
		
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}

	@Override
	public DisplayGridVo displayExcelBigList(NaviVo naviVo) throws SQLException {
		
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", "6" );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId  = colvo.getItem_id();
			String itemNm  = colvo.getItem_nm();
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			map.put("cellId", "ent_"+groupId+"_"+itemId);
			map.put("cellName", itemNm);
			columnArr.add(map);
		}
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setCm_code(naviVo.getCm_code());
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayBigBodyList_excel", vo );
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayBigSumList", vo );
		
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}

	@Override
	public DisplayGridVo displayExcelPdList(NaviVo naviVo) throws SQLException {
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", "7" );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId  = colvo.getItem_id();
			String itemNm  = colvo.getItem_nm();
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			map.put("cellId", "ent_"+groupId+"_"+itemId);
			map.put("cellName", itemNm);
			columnArr.add(map);
		}
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setCm_code(naviVo.getCm_code());
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayPdBodyList_excel", vo );
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayPdSumList", vo );
		
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}

	@Override
	public DisplayGridVo displayExcelTrtList(NaviVo naviVo) throws SQLException {
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayMainColumnList", "5" );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId = colvo.getItem_id();
			String itemNm = colvo.getItem_nm();
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			//map.put("cellId", "ent_"+groupId+"_"+itemId);
			map.put("cellId", "ent_"+groupId);
			map.put("cellName", itemNm);
			columnArr.add(map);
		}
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		
		vo.setCm_code(naviVo.getCm_code());
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayTrtBodyList_excel", vo );
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayTrtSumList", vo );
		
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}
	
	@Override
	public AnalysisCvsVo cvsChkList(NaviVo naviVo) throws SQLException {
		// 순서대로 넣고 순서대로 출력 위한 LinkedHashMap 사용
		Map<String, String> map =  new LinkedHashMap<String, String>();
		
        /* localhost(127.0.0.1) test용
        map.put("frDt", "20170701");
		map.put("toDt", "20170731");
		*/

		List<Map<String, String>> mapBody= getSqlSession().selectList("display.cvsCheckList100", naviVo); //sql
		
		List<Map<String, String>> headArr = new ArrayList<Map<String, String>>();
		List<Map<String, String>> bodyArr = new ArrayList<Map<String, String>>();
		AnalysisCvsVo       analysisCvsVo = new AnalysisCvsVo();
		
		for (int i = 0; i < mapBody.size(); i++) {

			mapBody.get(i).get("om_code");
			mapBody.get(i).get("regist_man");
			mapBody.get(i).get("drcc_c_code_nm");
			mapBody.get(i).get("drcci_c_code_nm");
			mapBody.get(i).get("drcci_state");
			
			if(i==0) {
				String[] strHead1Arry =  mapBody.get(i).get("drcc_c_code_nm").toString().split("\\|\\|");
				
				map = new LinkedHashMap<String, String>();
				map.put("base_de" ,"일자") ;
				map.put("sm_nm"   ,"매장명");
				map.put("sm_code" ,"매장코드");
				map.put("em_nm"   ,"MD명");
				
				for (int j = 0; j < strHead1Arry.length; j++) {
					String string = strHead1Arry[j];
					map.put("drcc_c_code_nm"+j, string);
					
				}
				headArr.add(map);
				analysisCvsVo.setHeadArr(headArr);
				
				String[] strHead2Arry =  mapBody.get(i).get("drcci_c_code_nm").toString().split("\\|\\|");

				map = new LinkedHashMap<String, String>();
				map.put("base_de" ,"일자");
				map.put("sm_nm"   ,"매장명");
				map.put("sm_code" ,"매장코드");
				map.put("em_nm"   ,"MD명");
				
				for (int j = 0; j < strHead2Arry.length; j++) {
					String string = strHead2Arry[j];
					map.put("drcci_c_code_nm"+j, string);
					
				}
				headArr.add(map);
				analysisCvsVo.setHeadArr(headArr);
			}
			
			String[] strBodyArry  =  mapBody.get(i).get("drcci_state").toString().split("\\|\\|");

			map = new LinkedHashMap<String, String>();
			map.put("base_de" ,mapBody.get(i).get("base_de"));
			map.put("sm_nm"   ,mapBody.get(i).get("sm_nm"));
			map.put("sm_code" ,mapBody.get(i).get("sm_code"));
			map.put("em_nm"   ,mapBody.get(i).get("regist_man"));

			for (int j = 0; j < strBodyArry.length; j++) {
				String string = strBodyArry[j];
				map.put("drcci_c_code_nm"+j, string);
			}			
			bodyArr.add(map);
			analysisCvsVo.setBodyArr(bodyArr);
			
		}//forend
		
		return analysisCvsVo;
		
	}//cvsChkList
	
	@Override
	//관리 업체별 PD매대현황(Grid버젼) 조회 A20180124 k2s
	public DisplayGridVo displayPdGridList(NaviVo naviVo) throws SQLException {
		
		//PD매대 item(7) column select
		List<DisPlayColunmVo>   disPlayColunmVoList = getSqlSession().selectList("displayColumnList", "7" );  
		
		List< Map<String,String>>         columnArr = new ArrayList<Map<String,String>>();
		List< Map<String,String>>         headerArr = new ArrayList<Map<String,String>>();
		DisplayGridVo                 displayGridVo = new DisplayGridVo(); 
		
		displayGridVo.setPagingEnd(naviVo.getPagingEnd());
		displayGridVo.setParams   (naviVo.getParams());
		displayGridVo.setCm_code  (naviVo.getCm_code());
		
		String groupId = null;
		String groupNm = null;
		String itemId  = null;
		String itemNm  = null;
		
		Map<String,String> headMap01 =  new LinkedHashMap<String, String>();
		Map<String,String> headMap02 =  new LinkedHashMap<String, String>();
		
		for (int i = 0; i < disPlayColunmVoList.size(); i++) {
			
			DisPlayColunmVo disPlayColunmVo = disPlayColunmVoList.get(i);
			
			groupId = disPlayColunmVo.getGroup_id();
			groupNm = disPlayColunmVo.getGroup_nm();
			itemId  = disPlayColunmVo.getItem_id();
			itemNm  = disPlayColunmVo.getItem_nm();
			
			Map<String,String>  colMap =  new LinkedHashMap<String, String>();
			
			/*A20180124 k2s start!!*/
			
			if (i==0) {
				headMap01.put("om_nm"                   ,"지점명");
				headMap01.put("tm_nm"                   ,"팀명");
				headMap01.put("da_innb"                 ,"식별자");
				headMap01.put("da_date"                 ,"적재일자");
				headMap01.put("cg_code"                 ,"고객그룹코드");
				headMap01.put("cg_nm"                   ,"고객그룹명");
				headMap01.put("me_code"                 ,"관리업체코드");
				headMap01.put("me_nm"                   ,"관리업체명");  
				headMap01.put("sm_code"                 ,"매장코드");
				headMap01.put("sm_nm"                   ,"매장명");  
				headMap01.put("em_nm"                   ,"사원명");  
				headMap01.put("em_no"                   ,"사원번호");
				headMap01.put("sm_odr"                  ,"차수"); 
				headMap01.put("base_de"                 ,"기준일자");
				headMap01.put("sm_area1_nm"             ,"권역1");
				headMap01.put("sm_area2_nm"             ,"권역2");
			
				headMap02.putAll(headMap01);  			//headMap01 to headMap02 set
			}
			
			headMap01.put("ent_"+groupId+"_"+itemId ,groupNm);
			headMap02.put("ent_"+groupId+"_"+itemId ,itemNm);
			
			/*A20180124 k2s end!!*/
			
			colMap.put("group_id"                ,groupId);
			colMap.put("group_nm"                ,groupNm);
			colMap.put("item_id"                 ,itemId);
			colMap.put("item_nm"                 ,itemNm);
			colMap.put("colunm_id"               ,"ent");
			columnArr.add(colMap);
		}
		
		headerArr.add(headMap01);   // headMap01 to headerArr set
		headerArr.add(headMap02);   // headMap02 to headerArr set
		
		//header1, header2 DisplayGridVo set
		displayGridVo.setHeaderArr(headerArr);      //header set
		displayGridVo.setColumnArr(columnArr);      //column set
		
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayPdBodyGridList", displayGridVo );  //M20180125 k2s displayPdBodyGridList 신규생성
		displayGridVo.setBodyArr(mapBody);
		
		return displayGridVo;
	}//displayPdGridList
	
	@Override
	//관리업체별 보조진열현황 조회 A20180130 k2s
	public DisplayGridVo displayBigGridList(NaviVo naviVo) throws SQLException {
		
		//보조진열 item(6) column select
		List<DisPlayColunmVo> disPlayColunmVoList = getSqlSession().selectList("displayColumnList", "6" );
		
		List< Map<String,String>>         columnArr = new ArrayList<Map<String,String>>();
		List< Map<String,String>>         headerArr = new ArrayList<Map<String,String>>();
		DisplayGridVo                 displayGridVo = new DisplayGridVo(); 
		
		displayGridVo.setPagingEnd(naviVo.getPagingEnd());
		displayGridVo.setParams   (naviVo.getParams());
		displayGridVo.setCm_code  (naviVo.getCm_code());

		String groupId = null;
		String groupNm = null;
		String itemId  = null;
		String itemNm  = null;		
		
		Map<String,String> headMap01 =  new LinkedHashMap<String, String>();
		Map<String,String> headMap02 =  new LinkedHashMap<String, String>();		
		
		for (int i = 0; i < disPlayColunmVoList.size(); i++) {
			
			DisPlayColunmVo disPlayColunmVo = disPlayColunmVoList.get(i);
			
			groupId = disPlayColunmVo.getGroup_id();
			groupNm = disPlayColunmVo.getGroup_nm();
			itemId  = disPlayColunmVo.getItem_id();
			itemNm  = disPlayColunmVo.getItem_nm();
			
			Map<String,String>  colMap =  new LinkedHashMap<String, String>();
			
			/*A20180124 k2s start!!*/
			
			if (i==0) {
				headMap01.put("om_nm"                   ,"지점명");
				headMap01.put("tm_nm"                   ,"팀명");
				headMap01.put("da_innb"                 ,"식별자");
				headMap01.put("da_date"                 ,"적재일자");
				headMap01.put("cg_code"                 ,"고객그룹코드");
				headMap01.put("cg_nm"                   ,"고객그룹명");
				headMap01.put("me_code"                 ,"관리업체코드");
				headMap01.put("me_nm"                   ,"관리업체명");  
				headMap01.put("sm_code"                 ,"매장코드");
				headMap01.put("sm_nm"                   ,"매장명");  
				headMap01.put("em_nm"                   ,"사원명");  
				headMap01.put("em_no"                   ,"사원번호");
				headMap01.put("sm_odr"                  ,"차수"); 
				headMap01.put("base_de"                 ,"기준일자");
				headMap01.put("sm_area1_nm"             ,"권역1");
				headMap01.put("sm_area2_nm"             ,"권역2");
			
				headMap02.putAll(headMap01);  			//headMap01 to headMap02 set
			}
			
			headMap01.put("ent_"+groupId+"_"+itemId ,groupNm);
			headMap02.put("ent_"+groupId+"_"+itemId ,itemNm);
			
			/*A20180124 k2s end!!*/
			
			colMap.put("group_id"                ,groupId);
			colMap.put("group_nm"                ,groupNm);
			colMap.put("item_id"                 ,itemId);
			colMap.put("item_nm"                 ,itemNm);
			colMap.put("colunm_id"               ,"ent");
			columnArr.add(colMap);
		}

		headerArr.add(headMap01);   // headMap01 to headerArr set
		headerArr.add(headMap02);   // headMap02 to headerArr set		
		
		//header1, header2 DisplayGridVo set
		displayGridVo.setHeaderArr(headerArr);      //header set
		displayGridVo.setColumnArr(columnArr);      //column set
		
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayBigBodyGridList", displayGridVo );  //M20180130 k2s displayBigBodyGridList 신규생성
		displayGridVo.setBodyArr(mapBody);
		
		return displayGridVo;
	}//displayBigGridList

	@Override
	//관리업체별 진열률현황 조회 A20180130 k2s	
	public DisplayGridVo displayArrGridList(NaviVo naviVo) throws SQLException {
		
		//진열품목 item(4) column select
		List<DisPlayColunmVo> disPlayColunmVoList = getSqlSession().selectList("displayColumnList", "4" );
		
		List< Map<String,String>>         columnArr = new ArrayList<Map<String,String>>();
		List< Map<String,String>>         headerArr = new ArrayList<Map<String,String>>();
		DisplayGridVo                 displayGridVo = new DisplayGridVo(); 
		
		displayGridVo.setPagingEnd(naviVo.getPagingEnd());
		displayGridVo.setParams   (naviVo.getParams());
		displayGridVo.setCm_code  (naviVo.getCm_code());
		
		String groupId = null;
		String groupNm = null;
		String itemId  = null;
		String itemNm  = null;
		
		Map<String,String> headMap01 =  new LinkedHashMap<String, String>();
		Map<String,String> headMap02 =  new LinkedHashMap<String, String>();
		
		String tempGroupId = "";
		String tempGroupNm= "";
		Map<String,String> mapRate =  new HashMap<String, String>();
		
		for (int i = 0; i < disPlayColunmVoList.size(); i++) {
			
			DisPlayColunmVo disPlayColunmVo = disPlayColunmVoList.get(i);
			
			groupId = disPlayColunmVo.getGroup_id();
			groupNm = disPlayColunmVo.getGroup_nm();
			itemId  = disPlayColunmVo.getItem_id();
			itemNm  = disPlayColunmVo.getItem_nm();
			
			Map<String,String>  colMap =  new LinkedHashMap<String, String>();
			
			/*A20180124 k2s start!!*/
			
			if (i==0) {
				headMap01.put("om_nm"                   ,"지점명");
				headMap01.put("tm_nm"                   ,"팀명");
				headMap01.put("da_innb"                 ,"식별자");
				headMap01.put("da_date"                 ,"적재일자");
				headMap01.put("cg_code"                 ,"고객그룹코드");
				headMap01.put("cg_nm"                   ,"고객그룹명");
				headMap01.put("me_code"                 ,"관리업체코드");
				headMap01.put("me_nm"                   ,"관리업체명");  
				headMap01.put("sm_code"                 ,"매장코드");
				headMap01.put("sm_nm"                   ,"매장명");  
				headMap01.put("em_nm"                   ,"사원명");  
				headMap01.put("em_no"                   ,"사원번호");
				headMap01.put("sm_odr"                  ,"차수"); 
				headMap01.put("base_de"                 ,"기준일자");
				headMap01.put("sm_area1_nm"             ,"권역1");
				headMap01.put("sm_area2_nm"             ,"권역2");
			
				headMap02.putAll(headMap01);  			//headMap01 to headMap02 set
			}
			
			
			/*A20180124 k2s end!!*/
			
			colMap.put("group_id"                ,groupId);
			colMap.put("group_nm"                ,groupNm);
			colMap.put("item_id"                 ,itemId);
			colMap.put("item_nm"                 ,itemNm);
			colMap.put("colunm_id"               ,"ent");
			columnArr.add(colMap);
			
			/* rate start*/
			if(i > 0  && !tempGroupId.equals(groupId)){
				mapRate =  new HashMap<String, String>();
				mapRate.put("group_id"  ,tempGroupId);
				mapRate.put("item_id"   ,"DSF");
				mapRate.put("group_nm"  ,tempGroupNm);
				mapRate.put("item_nm"   ,"DSF");
				mapRate.put("colunm_id" ,"rate");
				columnArr.add(mapRate);
				
				headMap01.put("rate_"+tempGroupId+"_DSF" ,tempGroupNm);
				headMap02.put("rate_"+tempGroupId+"_DSF" ,"진열률");
			}
			tempGroupId  = groupId;
			tempGroupNm  = groupNm;
			/* rate end */
			
			headMap01.put("ent_"+groupId+"_"+itemId ,groupNm);
			headMap02.put("ent_"+groupId+"_"+itemId ,itemNm);
		}
		mapRate =  new HashMap<String, String>();
		mapRate.put("group_id"  ,tempGroupId);
		mapRate.put("item_id"   ,"DSF");
		mapRate.put("group_nm"  ,tempGroupNm);
		mapRate.put("item_nm"   ,"DSF");
		mapRate.put("colunm_id" ,"rate");
		columnArr.add(mapRate);
		
		headMap01.put("rate_"+tempGroupId+"_DSF" ,tempGroupNm);
		headMap02.put("rate_"+tempGroupId+"_DSF" ,"진열률");
		
		headerArr.add(headMap01);   // headMap01 to headerArr set
		headerArr.add(headMap02);   // headMap02 to headerArr set
		
		//header1, header2 DisplayGridVo set
		displayGridVo.setHeaderArr(headerArr);      //header set
//		System.out.println("\n##### DisPlayService.displayArrGridList setHeaderArr ##### "+displayGridVo.getHeaderArr()+" #####");		  //M20180206 k2s		
		
		displayGridVo.setColumnArr(columnArr);      //column set
		
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayArrBodyGridList", displayGridVo );  //M20180130 k2s displayArrBodyGridList 신규생성
		displayGridVo.setBodyArr(mapBody);
//		System.out.println("\n##### DisPlayService.displayArrGridList displayGridVo.getBodyArr().get(0) ##### "+displayGridVo.getBodyArr().get(0)+" #####");		
		
		return displayGridVo;
	}
	
	@Override
	//관리업체별 취급률현황 조회 A20180130 k2s	
	public DisplayGridVo displayTrtGridList(NaviVo naviVo) throws SQLException {
		
		//취급품목 item(5) column select
		List<DisPlayColunmVo> disPlayColunmVoList = getSqlSession().selectList("displayMainColumnList", "5" );
		
		List< Map<String,String>>         columnArr = new ArrayList<Map<String,String>>();
		List< Map<String,String>>         headerArr = new ArrayList<Map<String,String>>();
		DisplayGridVo                 displayGridVo = new DisplayGridVo();
		
		displayGridVo.setPagingEnd(naviVo.getPagingEnd());
		displayGridVo.setParams   (naviVo.getParams());
		displayGridVo.setCm_code  (naviVo.getCm_code());
		
		String groupId = null;
		String groupNm = null;
		
		Map<String,String> headMap =  new LinkedHashMap<String, String>();
		
		for (int i = 0; i < disPlayColunmVoList.size(); i++) {
			
			DisPlayColunmVo disPlayColunmVo = disPlayColunmVoList.get(i);
			
			groupId = disPlayColunmVo.getGroup_id();
			groupNm = disPlayColunmVo.getGroup_nm();
			
			Map<String,String>  colMap =  new LinkedHashMap<String, String>();
			
			/*A20180124 k2s start!!*/
			
			if (i==0) {
				headMap.put("om_nm"                   ,"지점명");
				headMap.put("tm_nm"                   ,"팀명");
				headMap.put("da_innb"                 ,"식별자");
				headMap.put("da_date"                 ,"적재일자");
				headMap.put("cg_code"                 ,"고객그룹코드");
				headMap.put("cg_nm"                   ,"고객그룹명");
				headMap.put("me_code"                 ,"관리업체코드");
				headMap.put("me_nm"                   ,"관리업체명");  
				headMap.put("sm_code"                 ,"매장코드");
				headMap.put("sm_nm"                   ,"매장명");  
				headMap.put("em_nm"                   ,"사원명");  
				headMap.put("em_no"                   ,"사원번호");
				headMap.put("sm_odr"                  ,"차수"); 
				headMap.put("base_de"                 ,"기준일자");
				headMap.put("sm_area1_nm"             ,"권역1");
				headMap.put("sm_area2_nm"             ,"권역2");
			
			}
			
			headMap.put("ent_"+groupId ,groupNm);
			
			/*A20180124 k2s end!!*/
			
			colMap.put("group_id"                ,groupId);
			colMap.put("group_nm"                ,groupNm);
			colMap.put("colunm_id"               ,"ent");
			columnArr.add(colMap);
		}
		
		headerArr.add(headMap);   // headMap to headerArr set
		
		//header1, header2 DisplayGridVo set
		displayGridVo.setHeaderArr(headerArr);      //header set
		displayGridVo.setColumnArr(columnArr);      //column set
		
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayTrtBodyGridList", displayGridVo );  //M20180130 k2s displayTrtBodyGridList 신규생성
		displayGridVo.setBodyArr(mapBody);
		
		return displayGridVo;	
	}	
	
    //별도의 스태틱 함수로 구현
    public static List<String> sortByValue(final Map<String, ?> map) {

            List<String> list = new ArrayList<String>();

            list.addAll(map.keySet());

            Collections.sort(list,new Comparator<Object>() {

                public int compare(Object o1,Object o2) {

                    Object v1 = String.valueOf(map.get(o1));
                    Object v2 = String.valueOf(map.get(o2));

                    Comparable<Object> comparable = (Comparable<Object>) v2;
					return comparable.compareTo(v1);
                }
            });

            Collections.reverse(list); // 주석시 오름차순

            return list;

        }	
}
