package com.dasa.bhfProduct.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface bhfProdDAO {
	
	public List<Map<String, Object>> prodList(Map<String, Object> param) throws SQLException;
	public int prodUseAtUpt(Map<String, Object> param)throws SQLException;
}
