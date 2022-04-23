package com.dasa.activity.vo;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class ActivityEventMonthVo {
	private String emm_innb;
	private String om_code;
	private String sm_code;
    private String emi_img_url;
    private String note;
    private String delete_at;
	private String regist_man;
	private String regist_de;
	private String updt_man;
	private String updt_de;
	private String em_no;
	private String paramArr1;
	private MultipartFile[] files;//첨부파일
	
	public String getEmm_innb() {
		return emm_innb;
	}
	public void setEmm_innb(String emm_innb) {
		this.emm_innb = emm_innb;
	}
	public String getOm_code() {
		return om_code;
	}
	public void setOm_code(String om_code) {
		this.om_code = om_code;
	}
	public String getSm_code() {
		return sm_code;
	}
	public void setSm_code(String sm_code) {
		this.sm_code = sm_code;
	}
	public String getEmi_img_url() {
		return emi_img_url;
	}
	public void setEmi_img_url(String emi_img_url) {
		this.emi_img_url = emi_img_url;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public String getParamArr1() {
		return paramArr1;
	}
	public void setParamArr1(String paramArr1) {
		this.paramArr1 = paramArr1;
	}
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
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
	public String getUpdt_man() {
		return updt_man;
	}
	public void setUpdt_man(String updt_man) {
		this.updt_man = updt_man;
	}
	public String getUpdt_de() {
		return updt_de;
	}
	public void setUpdt_de(String updt_de) {
		this.updt_de = updt_de;
	}
	
	
	@Override
	public String toString() {
		return "ActivityEventMonthVo [emm_innb=" + emm_innb + ", om_code="
				+ om_code + ", sm_code=" + sm_code + ", emi_img_url="
				+ emi_img_url + ", note=" + note + ", delete_at=" + delete_at
				+ ", regist_man=" + regist_man + ", regist_de=" + regist_de
				+ ", updt_man=" + updt_man + ", updt_de=" + updt_de
				+ ", em_no=" + em_no + ", paramArr1=" + paramArr1 + ", files="
				+ Arrays.toString(files) + "]";
	}
	
	
}
