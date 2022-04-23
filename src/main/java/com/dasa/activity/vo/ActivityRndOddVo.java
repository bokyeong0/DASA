package com.dasa.activity.vo;

import org.springframework.web.multipart.MultipartFile;

public class ActivityRndOddVo {
	private String innb;
	private String main_innb;
	private String dro_innb;
	private String drop_innb;
	private String cm_code;
	private String om_code;
	private String sm_code;
    private String drop_flag;
    private String drop_img_url;
    private String drop_partclr_matter;
    
    private String base_de;
    private String delete_at;
	private String regist_man;
	private String regist_de;
	private String updt_man;
	private String updt_de;
	private String em_no;
	private String paramArr1;
	private MultipartFile[] files;//첨부파일
	
	private String md_nm;          //A20161209 k2s MD명
	private String om_nm;          //A20161209 k2s 지점명
	private String sm_nm;          //A20161209 k2s 매장명
	private String drop_flag_nm;   //A20161209 k2s 구분(당사, 경쟁사)
	private int    dang_cnt;       //A20170823 k2s 당사건수
	private int    ta_cnt;         //A20170823 k2s 경쟁사건수
	private int    sm_odr;         //A20170823 k2s 매장차수
	
	
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
	public String getMain_innb() {
		if(main_innb == null || main_innb.equals("")){
			main_innb = null;
		}
		return main_innb;
	}
	public void setMain_innb(String main_innb) {
		this.main_innb = main_innb;
	}
	public String getDro_innb() {
		return dro_innb;
	}
	public void setDro_innb(String dro_innb) {
		this.dro_innb = dro_innb;
	}
	public String getDrop_innb() {
		if(drop_innb == null || drop_innb.equals("")){
			drop_innb = null;
		}
		return drop_innb;
	}
	public void setDrop_innb(String drop_innb) {
		this.drop_innb = drop_innb;
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
	public String getDrop_flag() {
		return drop_flag;
	}
	public void setDrop_flag(String drop_flag) {
		this.drop_flag = drop_flag;
	}
	public String getDrop_img_url() {
		return drop_img_url;
	}
	public void setDrop_img_url(String drop_img_url) {
		this.drop_img_url = drop_img_url;
	}
	public String getDrop_partclr_matter() {
		return drop_partclr_matter;
	}
	public void setDrop_partclr_matter(String drop_partclr_matter) {
		this.drop_partclr_matter = drop_partclr_matter;
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
	public String getInnb() {
		return innb;
	}
	public void setInnb(String innb) {
		this.innb = innb;
	}
	
	//A20161209 k2s  start!!!
	public String getMd_nm() {
		return md_nm;
	}
	public void setMd_nm(String md_nm) {
		this.md_nm = md_nm;
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
	public String getDrop_flag_nm() {
		return drop_flag_nm;
	}
	public void setDrop_flag_nm(String drop_flag_nm) {
		this.drop_flag_nm = drop_flag_nm;
	}
	//A20161209 k2s  end!!!	
	
	
	//A20170825 k2s  start!!!	
	public int getDang_cnt() {
		return dang_cnt;
	}
	public void setDang_cnt(int dang_cnt) {
		this.dang_cnt = dang_cnt;
	}
	public int getTa_cnt() {
		return ta_cnt;
	}
	public void setTa_cnt(int ta_cnt) {
		this.ta_cnt = ta_cnt;
	}
	public int getSm_odr() {
		return sm_odr;
	}
	public void setSm_odr(int sm_odr) {
		this.sm_odr = sm_odr;
	}
	//A20170825 k2s  end!!!	
	
}
