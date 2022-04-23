package com.dasa.analysis.vo;

import java.util.List;
import java.util.Map;


public class AnalysisCvsVo {
	
	private String drc_innb;
	private String om_nm;
	private String em_nm;
	private String sm_nm;
	private String plan_de;
	private String cvs_status;
	
	private List<Map<String,String>>  headArr;
	private List<Map<String,String>>  bodyArr;
	
	public String getDrc_innb() {
		return drc_innb;
	}
	public void setDrc_innb(String drc_innb) {
		this.drc_innb = drc_innb;
	}
	public String getOm_nm() {
		return om_nm;
	}
	public void setOm_nm(String om_nm) {
		this.om_nm = om_nm;
	}
	public String getEm_nm() {
		return em_nm;
	}
	public void setEm_nm(String em_nm) {
		this.em_nm = em_nm;
	}
	public String getSm_nm() {
		return sm_nm;
	}
	public void setSm_nm(String sm_nm) {
		this.sm_nm = sm_nm;
	}
	public String getPlan_de() {
		return plan_de;
	}
	public void setPlan_de(String plan_de) {
		this.plan_de = plan_de;
	}
	public String getCvs_status() {
		return cvs_status;
	}
	public void setCvs_status(String cvs_status) {
		this.cvs_status = cvs_status;
	}
	
	public List<Map<String, String>> getHeadArr() {
		return headArr;
	}
	public void setHeadArr(List<Map<String, String>> headArr) {
		this.headArr = headArr;
	}
	public List<Map<String, String>> getBodyArr() {
		return bodyArr;
	}
	public void setBodyArr(List<Map<String, String>> bodyArr) {
		this.bodyArr = bodyArr;
	}
	@Override
	public String toString() {
		return "ActivityCvsVo [drc_innb=" + drc_innb + ", om_nm=" + om_nm
				+ ", em_nm=" + em_nm + ", sm_nm=" + sm_nm + ", plan_de="
				+ plan_de + ", cvs_status=" + cvs_status + ", columnArr="
				+ headArr + ", bodyArr=" + bodyArr + "]";
	}

	
}
