package com.dasa.communication.vo;

public class ReceiverVo {
	private String cm_code;
	private String om_code;
	private String tm_code;
	private String em_code;
	private String em_dty_code; //0000000007 순회, 0000000006 고정
	private String cg_code;
	private String me_code;
	private String sm_code;	

	private String key;
	private String p_key;
	private String dp_name;
	private String depth;
	private String add_flag; // 'om':조직, 'tm':팀, 'cg':고객관리, 'me':관리업체, 'sm':매장, 'em':직원
	private String em_id;  //A20180109 k2s 로그인id 추가 다우마케팅 여부 확인

	public String getCm_code() {
		return cm_code;
	}
	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
	}
	public String getOm_code() {
		return om_code;
	}
	public void setOm_code(String om_code) {
		this.om_code = om_code;
	}
	public String getTm_code() {
		return tm_code;
	}
	public void setTm_code(String tm_code) {
		this.tm_code = tm_code;
	}
	public String getEm_code() {
		return em_code;
	}
	public void setEm_code(String em_code) {
		this.em_code = em_code;
	}
	public String getEm_dty_code() {
		return em_dty_code;
	}
	public void setEm_dty_code(String em_dty_code) {
		this.em_dty_code = em_dty_code;
	}
	public String getCg_code() {
		return cg_code;
	}
	public void setCg_code(String cg_code) {
		this.cg_code = cg_code;
	}
	public String getMe_code() {
		return me_code;
	}
	public void setMe_code(String me_code) {
		this.me_code = me_code;
	}
	public String getSm_code() {
		return sm_code;
	}
	public void setSm_code(String sm_code) {
		this.sm_code = sm_code;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDp_name() {
		return dp_name;
	}
	public void setDp_name(String dp_name) {
		this.dp_name = dp_name;
	}
	public String getP_key() {
		return p_key;
	}
	public void setP_key(String p_key) {
		this.p_key = p_key;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getAdd_flag() {
		return add_flag;
	}
	public void setAdd_flag(String add_flag) {
		this.add_flag = add_flag;
	}
	
	public String getEm_id() { 
		return em_id;
	}
	public void setEm_id(String em_id) {
		this.em_id = em_id;
	}
	@Override
	public String toString() {
		return "ReceiverVo [cm_code=" + cm_code + ", om_code=" + om_code
				+ ", tm_code=" + tm_code + ", em_code=" + em_code
				+ ", em_dty_code=" + em_dty_code + ", cg_code=" + cg_code
				+ ", me_code=" + me_code + ", sm_code=" + sm_code + ", key="
				+ key + ", p_key=" + p_key + ", dp_name=" + dp_name
				+ ", depth=" + depth + ", add_flag=" + add_flag + ", em_id="
				+ em_id + "]";
	}

	
}
