package com.dasa.mobile.vo;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

/**
 * @파일명: MobileVo.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 모바일 Vo
 */
public class MobileAttendingVo {

	private String em_no;//사원번호
	private int am_no;//첨부파일 고유번호
	private MultipartFile[] files;//첨부파일
	private String type;//유형
	private String flag;//구분
	
	public String getEm_no() {
		return em_no;
	}
	public int getAm_no() {
		return am_no;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public String getType() {
		return type;
	}
	public String getFlag() {
		return flag;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public void setAm_no(int am_no) {
		this.am_no = am_no;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "MobileAttendingVo [em_no=" + em_no + ", am_no=" + am_no + ", files=" + Arrays.toString(files)
				+ ", type=" + type + ", flag=" + flag + "]";
	}

}
