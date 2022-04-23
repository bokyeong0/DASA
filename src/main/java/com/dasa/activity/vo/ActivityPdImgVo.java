package com.dasa.activity.vo;

import org.springframework.web.multipart.MultipartFile;

public class ActivityPdImgVo {
	private String fix_photo_innb;
	private String rnd_photo_innb;
	private String cm_code;
	private String om_code;
	private String sm_code;
	private String pd_img_url;
    
    private String base_de;
    private String delete_at;
	private String regist_man;
	private String regist_de;
	private String updt_man;
	private String updt_de;
	
	private MultipartFile[] files;//첨부파일
	private MultipartFile file;
	private String em_no;
	private String paramArr1;

	private String em_nm;          // MD명
	private String om_nm;          // 지점명
	private String sm_nm;          // 매장명
	
	public String getFix_photo_innb() {
		return fix_photo_innb;
	}
	public void setFix_photo_innb(String fix_photo_innb) {
		this.fix_photo_innb = fix_photo_innb;
	}
	public String getRnd_photo_innb() {
		return rnd_photo_innb;
	}
	public void setRnd_photo_innb(String rnd_photo_innb) {
		this.rnd_photo_innb = rnd_photo_innb;
	}
	public String getParamArr1() {
		return paramArr1;
	}
	public void setParamArr1(String paramArr1) {
		this.paramArr1 = paramArr1;
	}
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
	public String getSm_code() {
		return sm_code;
	}
	public void setSm_code(String sm_code) {
		this.sm_code = sm_code;
	}
	public String getPd_img_url() {
		return pd_img_url;
	}
	public void setPd_img_url(String pd_img_url) {
		this.pd_img_url = pd_img_url;
	}
	public String getBase_de() {
		return base_de;
	}
	public void setBase_de(String base_de) {
		this.base_de = base_de;
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
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getEm_nm() {
		return em_nm;
	}
	public void setEm_nm(String em_nm) {
		this.em_nm = em_nm;
	}
	public String getOm_nm() {
		return om_nm;
	}
	public void setOm_nm(String om_nm) {
		this.om_nm = om_nm;
	}
	public String getSm_nm() {
		return sm_nm;
	}
	public void setSm_nm(String sm_nm) {
		this.sm_nm = sm_nm;
	}
}
