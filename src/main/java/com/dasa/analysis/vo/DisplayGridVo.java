package com.dasa.analysis.vo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DisplayGridVo {
	
//	private String d_innb;
	private String cm_code;
//	private String cg_nm;
//	private String me_nm;
//	private String sm_nm;
//	private String sm_odr;
//	private String addr_code1;
//	private String addr_code2;
//	private String addr_code3;
	
	private String drcc_c_code;
	private String drcci_c_code;
	private String drcc_c_code_nm;
	private String drcci_c_code_nm;
	private String drcci_innb;
	private String drcci_state;
	
	private String pagingEnd;
	private Map<String, String> params;
	
	private List<Map<String,String>>  columnArr;
	private List<Map<String,String>>  bodyArr;
	private List<Map<String,String>>  sumArr;
	private List<Map<String,String>>  headerArr;
	
	public String getCm_code() {
		return cm_code;
	}
	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
	}
	public String getPagingEnd() {
		return pagingEnd;
	}
	public void setPagingEnd(String pagingEnd) {
		this.pagingEnd = pagingEnd;
	}
	public String getDrcc_c_code() {
		return drcc_c_code;
	}
	public void setDrcc_c_code(String drcc_c_code) {
		this.drcc_c_code = drcc_c_code;
	}
	public String getDrcci_c_code() {
		return drcci_c_code;
	}
	public void setDrcci_c_code(String drcci_c_code) {
		this.drcci_c_code = drcci_c_code;
	}
	public String getDrcci_state() {
		return drcci_state;
	}
	public void setDrcci_state(String drcci_state) {
		this.drcci_state = drcci_state;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public List<Map<String, String>> getColumnArr() {
		return columnArr;
	}
	public void setColumnArr(List<Map<String, String>> columnArr) {
		this.columnArr = columnArr;
	}
	public List<Map<String, String>> getBodyArr() {
		return bodyArr;
	}
	public void setBodyArr(List<Map<String, String>> bodyArr) {
		this.bodyArr = bodyArr;
	}
	
	public List<Map<String, String>> getSumArr() {
		return sumArr;
	}
	public void setSumArr(List<Map<String, String>> sumArr) {
		this.sumArr = sumArr;
	}
	public String getDrcc_c_code_nm() {
		return drcc_c_code_nm;
	}
	public void setDrcc_c_code_nm(String drcc_c_code_nm) {
		this.drcc_c_code_nm = drcc_c_code_nm;
	}
	public String getDrcci_c_code_nm() {
		return drcci_c_code_nm;
	}
	public void setDrcci_c_code_nm(String drcci_c_code_nm) {
		this.drcci_c_code_nm = drcci_c_code_nm;
	}
	public String getDrcci_innb() {
		return drcci_innb;
	}
	public void setDrcci_innb(String drcci_innb) {
		this.drcci_innb = drcci_innb;
	}
	public List<Map<String, String>> getHeaderArr() {
		return headerArr;
	}
	public void setHeaderArr(List<Map<String, String>> headerArr) {
		this.headerArr = headerArr;
	}
	@Override
	public String toString() {
		return "DisplayGridVo [cm_code=" + cm_code + ", drcc_c_code="
				+ drcc_c_code + ", drcci_c_code=" + drcci_c_code
				+ ", drcc_c_code_nm=" + drcc_c_code_nm + ", drcci_c_code_nm="
				+ drcci_c_code_nm + ", drcci_innb=" + drcci_innb
				+ ", drcci_state=" + drcci_state + ", pagingEnd=" + pagingEnd
				+ ", params=" + params + ", columnArr=" + columnArr
				+ ", bodyArr=" + bodyArr + ", sumArr=" + sumArr
				+ ", headerArr=" + headerArr + "]";
	}


	
	
}
