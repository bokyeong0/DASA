package com.dasa.communication.vo;


public class ReplyVo {
	private String br_innb;
	private String bm_innb;
	private String parent_br_innb;
	private String br_depth;
	private String br_cn;
	private String delete_at;
	private String regist_man;
	private String regist_de;
	private String auth_delete;
	private String auth_reply;
	private String em_nm;
	private int auth_flag;
	
	public int getAuth_flag() {
		return auth_flag;
	}
	public void setAuth_flag(int auth_flag) {
		this.auth_flag = auth_flag;
	}
	public String getBr_innb() {
		return br_innb;
	}
	public void setBr_innb(String br_innb) {
		this.br_innb = br_innb;
	}
	public String getBm_innb() {
		return bm_innb;
	}
	public void setBm_innb(String bm_innb) {
		this.bm_innb = bm_innb;
	}
	public String getParent_br_innb() {
		return parent_br_innb;
	}
	public void setParent_br_innb(String parent_br_innb) {
		this.parent_br_innb = parent_br_innb;
	}
	public String getBr_depth() {
		return br_depth;
	}
	public void setBr_depth(String br_depth) {
		this.br_depth = br_depth;
	}
	public String getBr_cn() {
		return br_cn;
	}
	public void setBr_cn(String br_cn) {
		this.br_cn = br_cn;
	}
	public String getDelete_at() {
		return delete_at;
	}
	public void setDelete_at(String delete_at) {
		this.delete_at = delete_at;
	}
	public String getRegist_man() {
		return regist_man;
	}
	public void setRegist_man(String regist_man) {
		this.regist_man = regist_man;
	}
	public String getRegist_de() {
		return regist_de;
	}
	public void setRegist_de(String regist_de) {
		this.regist_de = regist_de;
	}
	public String getAuth_delete() {
		return auth_delete;
	}
	public void setAuth_delete(String auth_delete) {
		this.auth_delete = auth_delete;
	}
	public String getAuth_reply() {
		return auth_reply;
	}
	public void setAuth_reply(String auth_reply) {
		this.auth_reply = auth_reply;
	}
	
	public String getEm_nm() {
		return em_nm;
	}
	public void setEm_nm(String em_nm) {
		this.em_nm = em_nm;
	}
	@Override
	public String toString() {
		return "ReplyVo [br_innb=" + br_innb + ", bm_innb=" + bm_innb
				+ ", parent_br_innb=" + parent_br_innb + ", br_depth="
				+ br_depth + ", br_cn=" + br_cn + ", delete_at=" + delete_at
				+ ", regist_man=" + regist_man + ", regist_de=" + regist_de
				+ ", auth_delete=" + auth_delete + ", auth_reply=" + auth_reply
				+ ", em_nm=" + em_nm 
				+ "]";
	}
	
}
