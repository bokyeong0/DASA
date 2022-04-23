package com.dasa.bhfProduct.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.bhfProduct.dao.bhfProdDAO;


public class bhfProdService extends SqlSessionDaoSupport  implements bhfProdDAO {
	
	@Override
	public List<Map<String, Object>> prodList(Map<String, Object> param) throws SQLException {
		return getSqlSession().selectList("bhfProduct.bhfProdList", param);
	}

	@Override
	public int prodUseAtUpt(Map<String, Object> param) throws SQLException {
		int cnt = 0;
		String om_code = (String)param.get("om_code");
		
		JSONArray array = JSONArray.fromObject(param.get("editedRowItems")); 
		for(int i=0; i<array.size(); i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("om_code", om_code);
			
			JSONObject obj = (JSONObject)array.get(i);
			map.put("use_at", obj.get("use_at"));
			map.put("oi_code", obj.get("oi_code"));
			cnt += getSqlSession().update("bhfProduct.prodUseAtUpt",map);
		}
		return cnt;
	}
}
