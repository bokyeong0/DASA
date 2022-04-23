<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="application/vnd.ms-excel;charset=euc-kr" pageEncoding="euc-kr"%>
<%
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
	String               today = formatter.format(new java.util.Date());
	String           file_name = "attandance_" + today;
	String           ExcelName = new String(file_name.getBytes("8859_1"),"euc-kr");

	response.setHeader("Content-Disposition", "attachment; filename="+ExcelName+".xls"); 
    response.setHeader("Content-Description", "JSP Generated Data");
    
	String linePath = request.getRealPath("/") + "/resources/images/line.JPG";
%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	console.log(<%=linePath%>);
</script>
<script type="text/javascript">
$(document).ready(function(){
	
	var week = new Array('ÀÏ', '¿ù', 'È­', '¼ö', '¸ñ', '±İ', 'Åä');
	
	var y = parseInt(${ajax.yyyy});
	var m = parseInt(${ajax.mm});
	var d ='';
    var $tds = $('#tbl_excel_20201 thead tr:eq(0)').find('th');
    
    for (var i = 5; i < $tds.length; i++){
	 d = $tds.eq(i).text();
	 day = new Date(y + '-' + m + '-' + d).getDay();
	 dayLabel = week[day];
	 $('#tbl_excel_20201 thead tr:eq(1) th:nth-child('+ (i-4) +')').text("gg");
	 
    }
});
</script>
</head>
<body>
	<table  border='1' id='tbl_excel_20201' style="font-size:15px;">
		<thead >
			<tr>
				<th colspan=${ajax.last_day+5+1} style="text-align:left; font-size: 25px;">»ç¿ø±Ù¹«½ÇÀû (${ajax.yyyy}³â ${ajax.mm}¿ù)</th>
				<th colspan=10 style="height: 130px;">
				</th>
			</tr>
			<tr>
				<td colspan=${ajax.last_day+20} style="text-align:left;">
				<c:choose>
					<c:when test="${ajax.bhf_name=='ÁöÁ¡'}">
						ÁöÁ¡  : ÀüÃ¼
					</c:when>
					<c:otherwise>
						ÁöÁ¡  : ${ajax.bhf_name}
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr style="">
					<th scope="col" rowspan="2" style="text-align:center;"> </th>
					<th scope="col" rowspan="2" style="text-align:center;">ÁöÁ¡</th>
					<th scope="col" rowspan="2" style="text-align:center;">»ç¹ø</th>
					<th scope="col" rowspan="2" style="text-align:center;">¼º¸í</th>
					<th scope="col" rowspan="2" style="text-align:center;">ÀÔ»çÀÏ</th>
					<th scope="col" rowspan="2" style="text-align:center;">Á÷¹«</th>
					
					<th scope="col" style="text-align:center;">01</th>
					<th scope="col" style="text-align:center;">02</th>
					<th scope="col" style="text-align:center;">03</th>
					<th scope="col" style="text-align:center;">04</th>
					<th scope="col" style="text-align:center;">05</th>
					<th scope="col" style="text-align:center;">06</th>
					<th scope="col" style="text-align:center;">07</th>
					<th scope="col" style="text-align:center;">08</th>
					<th scope="col" style="text-align:center;">09</th>
					<th scope="col" style="text-align:center;">10</th>
					<th scope="col" style="text-align:center;">11</th>
					<th scope="col" style="text-align:center;">12</th>
					<th scope="col" style="text-align:center;">13</th>
					<th scope="col" style="text-align:center;">14</th>
					<th scope="col" style="text-align:center;">15</th>					
					<th scope="col" style="text-align:center;">16</th>
					<th scope="col" style="text-align:center;">17</th>
					<th scope="col" style="text-align:center;">18</th>
					<th scope="col" style="text-align:center;">19</th>
					<th scope="col" style="text-align:center;">20</th>
					<th scope="col" style="text-align:center;">21</th>
					<th scope="col" style="text-align:center;">22</th>
					<th scope="col" style="text-align:center;">23</th>
					<th scope="col" style="text-align:center;">24</th>
					<th scope="col" style="text-align:center;">25</th>
					<th scope="col" style="text-align:center;">26</th>
					<th scope="col" style="text-align:center;">27</th>
					<c:if test="${ajax.last_day >=28}">
					<th scope="col" id='d28_20201' style="text-align:center;">28</th>
					</c:if>
					<c:if test="${ajax.last_day >=29}">
					<th scope="col" id='d29_20201' style="text-align:center;">29</th>
					</c:if>
					<c:if test="${ajax.last_day >=30}">
					<th scope="col" id='d30_20201' style="text-align:center;">30</th>
					</c:if>
					<c:if test="${ajax.last_day >=31}">
					<th scope="col" id='d31_20201' style="text-align:center;">31</th>
					</c:if>
					
					<th scope="col" rowspan="2" style="text-align:center; width:70px;">±âÁØ±Ù¹« ÀÏ¼ö</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">ÈŞÀÏ ÀÏ¼ö</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">ÆòÀÏ ±Ù¹«</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">ÈŞÀÏ ±Ù¹«</th>
					<th scope="col" rowspan="2" style="text-align:center; width:55px;">½Ã°£¿Ü ±Ù¹«</th>
					<th scope="col" rowspan="2" style="text-align:center; width:55px;">½Ç±Ù¹« ÀÏ¼ö</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">°á±Ù ÀÏ¼ö</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">¿¬Â÷ ÈŞ°¡</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">ÇÏ°è ÈŞ°¡</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">°æÁ¶ ÈŞ°¡</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">±³À°</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">º´°¡</th>
					<th scope="col" rowspan="2" style="text-align:center; width:55px;">±âÁØ ±³Åëºñ</th>
					<th scope="col" rowspan="2" style="text-align:center; width:55px;">Áö±Ş ±³Åëºñ</th>
				</tr>
				<tr>
					<c:set var="week" value="${iDayOfWeek_01}" />
 					<c:forEach var="d" begin="1" end="${ajax.last_day}" step="1">
 						<c:choose>		
	 						<c:when test="d eq 28 and d > ${ajax.last_day}"></c:when>
							<c:when test="d eq 29 and d > ${ajax.last_day}"></c:when>
							<c:when test="d eq 30 and d > ${ajax.last_day}"></c:when>
							<c:when test="d eq 31 and d > ${ajax.last_day}"></c:when>
							<c:otherwise>
												
						     	<c:choose>
							    	<c:when test="${week eq 1}"><th scope="col" style="text-align:center; color:red">ÀÏ</c:when>
							    	<c:when test="${week eq 2}"><th scope="col" style="text-align:center;">¿ù</c:when>
							    	<c:when test="${week eq 3}"><th scope="col" style="text-align:center;">È­</c:when>
							    	<c:when test="${week eq 4}"><th scope="col" style="text-align:center;">¼ö</c:when>
							    	<c:when test="${week eq 5}"><th scope="col" style="text-align:center;">¸ñ</c:when>
							    	<c:when test="${week eq 6}"><th scope="col" style="text-align:center;">±İ</c:when>
							    	<c:when test="${week eq 7}"><th scope="col" style="text-align:center; color:blue">Åä</c:when>
							    </c:choose>
								</th>
							</c:otherwise>
						</c:choose>
						<c:choose>
						    <c:when test="${week eq 7}">
							    	<c:set var="week" value="1" />
						    </c:when>
					    	<c:otherwise>
						    	<c:set var="week" value="${week+1}" />
						    </c:otherwise>
				    	</c:choose>  
					</c:forEach>
<%-- 
					<th scope="col" rowspan="2">±âÁØ±Ù¹«ÀÏ¼ö</th>
					<th scope="col" rowspan="2">ÈŞÀÏÀÏ¼ö</th>
					<th scope="col" rowspan="2">ÆòÀÏ±Ù¹«</th>
					<th scope="col" rowspan="2">ÈŞÀÏ±Ù¹«</th>
					<th scope="col" rowspan="2">½Ã°£¿Ü±Ù¹«</th>
					<th scope="col" rowspan="2">½Ç±Ù¹«ÀÏ¼ö</th>
					<th scope="col" rowspan="2" style="display: none;">±âº»½Ã°£</th>
					<th scope="col" rowspan="2">°á±ÙÀÏ¼ö</th>
					<th scope="col" rowspan="2">¿¬Â÷ÈŞ°¡</th>
					<th scope="col" rowspan="2">ÇÏ°èÈŞ°¡</th>
					<th scope="col" rowspan="2">°æÁ¶ÈŞ°¡</th>
					<th scope="col" rowspan="2">±³À°</th>
					<th scope="col" rowspan="2">º´°¡</th>
					<th scope="col" rowspan="2">±âÁØ±³Åëºñ</th>
					<th scope="col" rowspan="2">Áö±Ş±³Åëºñ</th> --%>
				</tr>
		</thead>
		<tbody id='tbodyExcel20201' >
			<c:set var="cnt" value="0" />
			
			<c:set var="holiday_work" value="0" />
			<c:set var="overtime_work" value="0" />
			<c:set var="actual_work" value="0" />
			<c:set var="absent" value="0" />
			<c:set var="annual_leave" value="0" />
			<c:set var="summer_leave" value="0" />
			<c:set var="cc_leave" value="0" />
			<c:set var="education" value="0" />
			<c:set var="sick_leave" value="0" />
			<c:set var="trans_fee" value="0" />
			<c:set var="t_trans_fee" value="0" />
			
			<c:forEach var="list" items="${ajax.result}">
				<c:set var="cnt" value="${cnt+1}" />
				<c:set var="holiday_work" value="${holiday_work + list.ea_holidays_work}" />
				<c:set var="overtime_work" value="${overtime_work + list.ea_work_overtime}" />
				<c:set var="actual_work" value="${actual_work + list.ea_actual_work_days}" />
				<c:set var="absent" value="${absent + list.ea_absent_days}" />
				<c:set var="annual_leave" value="${annual_leave + list.ea_annual_leave}" />
				<c:set var="summer_leave" value="${summer_leave + list.ea_summer_holidays}" />
 			    <c:set var="cc_leave" value="${cc_leave + list.ea_cc_leave}" /> 			   
				<c:set var="education" value="${education + list.ea_education}" />
				<c:set var="sick_leave" value="${sick_leave + list.ea_sick_leave}" />
				<c:set var="t_trans_fee" value="${fn:trim(fn:replace(list.ea_trans_fee, ',', ''))}"/>
				<fmt:parseNumber type="number" var="tt_trans_fee">${t_trans_fee}</fmt:parseNumber>
				<c:set var="trans_fee" value="${trans_fee + tt_trans_fee}" />
					
				<tr>
					<td style="text-align:center;">${cnt}</td>
					<td style="text-align:center;">${list.bhf_name}</td>
					<td style="text-align:center;">${list.ea_em_no}</td>
					<td style="text-align:left;">${list.ea_em_nm}</td>
					<td style="text-align:center;">${list.em_ecny_de}</td>
					<td style="text-align:left;">${list.dty_name}</td>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_01, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_01, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_01}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_01}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_02, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_02, 'ÈŞ¿¬')}">  
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_02}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_02}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_03, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_03, 'ÈŞ¿¬')}">  
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_03}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_03}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_04, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_04, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_04}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_04}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_05, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_05, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_05}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_05}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_06, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_06, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_06}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_06}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_07, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_07, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_07}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_07}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_08, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_08, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_08}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_08}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_09, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_09, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_09}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_09}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_10, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_10, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_10}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_10}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_11, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_11, 'ÈŞ¿¬')}">  
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_11}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_11}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_12, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_12, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_12}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_12}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_13, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_13, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_13}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_13}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_14, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_14, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_14}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_14}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_15, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_15, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_15}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_15}</td>
						</c:otherwise>
					</c:choose>
										
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_16, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_16, 'ÈŞ¿¬')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_16}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_16}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_17, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_17, 'ÈŞ¿¬')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_17}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_17}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_18, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_18, 'ÈŞ¿¬')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_18}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_18}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_19, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_19, 'ÈŞ¿¬')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_19}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_19}</td>
						</c:otherwise>
					</c:choose>
				 	
				 	<c:choose>
						<c:when test="${fn:contains(list.ea_day_20, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_20, 'ÈŞ¿¬')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_20}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_20}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_21, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_21, 'ÈŞ¿¬')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_21}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_21}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_22, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_22, 'ÈŞ¿¬')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_22}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_22}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_23, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_23, 'ÈŞ¿¬')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_23}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_23}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_24, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_24, 'ÈŞ¿¬')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_24}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_24}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_25, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_25, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_25}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_25}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_26, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_26, 'ÈŞ¿¬')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_26}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_26}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_27, '¿¬Àå')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('ÈŞ±Ù')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_27, 'ÈŞ¿¬')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('¿¬ÈŞ')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('ÇÏÈŞ')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('°æÁ¶')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('±³À°')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('º´°¡')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('ÈŞ¹«')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_27}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_27}</td>
						</c:otherwise>
					</c:choose>
					
					<c:if test="${ajax.last_day >=28}">
						<%-- <td style="text-align:center;">${list.ea_day_28}</td> --%>
						<c:choose>
							<c:when test="${fn:contains(list.ea_day_28, '¿¬Àå')}">
								<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('ÈŞ±Ù')}"> 
								<td style="text-align:center; background-color:#FFA251">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${fn:contains(list.ea_day_28, 'ÈŞ¿¬')}"> 
								<td style="text-align:center; background-color:#FFA200">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('¿¬ÈŞ')}"> 
								<td style="text-align:center; background-color:#FFFF51">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('ÇÏÈŞ')}"> 
								<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('°æÁ¶')}"> 
								<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('±³À°')}"> 
								<td style="text-align:center; background-color:#6975DF">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('º´°¡')}"> 
								<td style="text-align:center; background-color:#C57DFF">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('ÈŞ¹«')}"> 
								<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_28}</td>
							</c:when>
							<c:otherwise>
							    <td style="text-align:center;">${list.ea_day_28}</td>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${ajax.last_day >=29}">
						<%-- <td style="text-align:center;">${list.ea_day_29}</td> --%>
						<c:choose>
							<c:when test="${fn:contains(list.ea_day_29, '¿¬Àå')}">
								<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('ÈŞ±Ù')}"> 
								<td style="text-align:center; background-color:#FFA251">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${fn:contains(list.ea_day_29, 'ÈŞ¿¬')}"> 
								<td style="text-align:center; background-color:#FFA200">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('¿¬ÈŞ')}"> 
								<td style="text-align:center; background-color:#FFFF51">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('ÇÏÈŞ')}"> 
								<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('°æÁ¶')}"> 
								<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('±³À°')}"> 
								<td style="text-align:center; background-color:#6975DF">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('º´°¡')}"> 
								<td style="text-align:center; background-color:#C57DFF">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('ÈŞ¹«')}"> 
								<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_29}</td>
							</c:when>
							<c:otherwise>
							    <td style="text-align:center;">${list.ea_day_29}</td>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${ajax.last_day >=30}">
						<%-- <td style="text-align:center;">${list.ea_day_30}</td> --%>
						<c:choose>
							<c:when test="${fn:contains(list.ea_day_30, '¿¬Àå')}">
								<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('ÈŞ±Ù')}"> 
								<td style="text-align:center; background-color:#FFA251">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${fn:contains(list.ea_day_30, 'ÈŞ¿¬')}"> 
								<td style="text-align:center; background-color:#FFA200">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('¿¬ÈŞ')}"> 
								<td style="text-align:center; background-color:#FFFF51">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('ÇÏÈŞ')}"> 
								<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('°æÁ¶')}"> 
								<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('±³À°')}"> 
								<td style="text-align:center; background-color:#6975DF">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('º´°¡')}"> 
								<td style="text-align:center; background-color:#C57DFF">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('ÈŞ¹«')}"> 
								<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_30}</td>
							</c:when>
							<c:otherwise>
							    <td style="text-align:center;">${list.ea_day_30}</td>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${ajax.last_day >=31}">
						<%-- <td style="text-align:center;">${list.ea_day_31}</td> --%>
						<c:choose>
							<c:when test="${fn:contains(list.ea_day_31, '¿¬Àå')}">
								<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('ÈŞ±Ù')}"> 
								<td style="text-align:center; background-color:#FFA251">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${fn:contains(list.ea_day_31, 'ÈŞ¿¬')}"> 
								<td style="text-align:center; background-color:#FFA200">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('¿¬ÈŞ')}"> 
								<td style="text-align:center; background-color:#FFFF51">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('ÇÏÈŞ')}"> 
								<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('°æÁ¶')}"> 
								<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('±³À°')}"> 
								<td style="text-align:center; background-color:#6975DF">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('º´°¡')}"> 
								<td style="text-align:center; background-color:#C57DFF">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('ÈŞ¹«')}"> 
								<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_31}</td>
							</c:when>
							<c:otherwise>
							    <td style="text-align:center;">${list.ea_day_31}</td>
							</c:otherwise>
						</c:choose>
					</c:if>
					
					<td style="text-align:right;">${list.ea_std_work_days}</td>
					<td style="text-align:right;">${list.ea_holidays}</td>
					<td style="text-align:right;">${list.ea_weekdays_work}</td>
					<td style="text-align:right;">${list.ea_holidays_work}</td>
					<td style="text-align:right;">${list.ea_work_overtime}</td>
					<td style="text-align:right;">${list.ea_actual_work_days}</td>
					<td style="text-align:right;">${list.ea_absent_days}</td>
					<td style="text-align:right;">${list.ea_annual_leave}</td>
					<td style="text-align:right;">${list.ea_summer_holidays}</td>
					<td style="text-align:right;">${list.ea_cc_leave}</td>
					<td style="text-align:right;">${list.ea_education}</td>
					<td style="text-align:right;">${list.ea_sick_leave}</td>
					<td style="text-align:right;">${list.ea_std_trans_fee}</td>
					<td style="text-align:right;">${list.ea_trans_fee}</td>
					
				</tr>
			</c:forEach>
				<tr>
					<td>ÇÕ°è</td>
					<td style="text-align:left;" colspan = ${ajax.last_day+6-1} class='txt_left'>${cnt}</td>
					<td style="text-align:right;" class='txt_right'></td><%-- ±âÁØ±Ù¹«ÀÏ¼ö--%>
					<td style="text-align:right;" class='txt_right'></td><%-- ÈŞÀÏÀÏ¼ö--%>
					<td style="text-align:right;" class='txt_right'></td><%-- ÆòÀÏ±Ù¹«--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${holiday_work}</fmt:formatNumber></td><%-- ÈŞÀÏ±Ù¹«--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${overtime_work}</fmt:formatNumber></td><%-- ½Ã°£¿Ü±Ù¹«--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${actual_work}</fmt:formatNumber></td><%-- ½Ç±Ù¹«ÀÏ¼ö--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${absent}</fmt:formatNumber></td><%-- °á±ÙÀÏ¼ö--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${annual_leave}</fmt:formatNumber></td><%-- ¿¬Â÷--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${summer_leave}</fmt:formatNumber></td><%-- ÇÏ°è--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${cc_leave}</fmt:formatNumber></td><%-- °æÁ¶--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${education}</fmt:formatNumber></td><%-- ±³À°--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${sick_leave}</fmt:formatNumber></td><%-- º´°¡--%>
					<td style="text-align:right;" class='txt_right'></td><%-- ±âÁØ±³Åëºñ--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${trans_fee}</fmt:formatNumber></td><%-- Áö±İ±³Åëºñ--%>			
				</tr>
		</tbody>
	</table>
</body>
</html>

