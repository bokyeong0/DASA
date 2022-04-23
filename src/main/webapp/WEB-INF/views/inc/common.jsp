<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String root    = request.getContextPath();
    String strUrl  = request.getRequestURL().toString();
	String imgPath = root+"/resources/images";
	String jsPath  = root+"/resources/js";
	String cssPath = root+"/resources/css";
	/* A20171110 k2s AUIGrid License 개발/ 운영 변경 적용 */
	String strAUIGridLicense = jsPath+"/AUIGrid/AUIGridLicense_dev.js";
/* 	String strAUIGridLicense = jsPath+"/AUIGrid/AUIGridLicense_real.js"; */
%>