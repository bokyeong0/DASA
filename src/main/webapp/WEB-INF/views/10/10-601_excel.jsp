<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="application/vnd.ms-excel;charset=euc-kr" pageEncoding="euc-kr"%>
<%
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
	String today = formatter.format(new java.util.Date());
	String file_name = "teamleader_" + today;
	String ExcelName  = new String(file_name.getBytes("8859_1"),"euc-kr");

	response.setHeader("Content-Disposition", "attachment; filename="+ExcelName+".xls"); 
    response.setHeader("Content-Description", "JSP Generated Data");
    
	//String linePath = request.getContextPath() + "/resources/images/line.JPG";
	/* String linePath = "D:\\workspace-sts-dasa\\DASA\\src\\main\\webapp\\resources\\images\\line.JPG"; */

%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	
	var week = new Array('일', '월', '화', '수', '목', '금', '토');
	
	var y = parseInt(${ajax.yyyy});
	var m = parseInt(${ajax.mm});
	var d ='';
    var $tds = $('#tbl_excel_10601 thead tr:eq(0)').find('th');
    
    for (var i = 3; i < $tds.length; i++){
	 d = $tds.eq(i).text();
	 day = new Date(y + '-' + m + '-' + d).getDay();
	 dayLabel = week[day];
	 $('#tbl_excel_10601 thead tr:eq(1) th:nth-child('+ (i-2) +')').text("");
	 
    }
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">

</head>
<body>
	<table  border='1' id='tbl_excel_10601' style="font-size:15px;">
		<thead>
			<tr>
				<th colspan=${ajax.last_day+3+1} style="text-align:left; font-size: 25px;">팀장 (${ajax.yyyy}년 ${ajax.mm}월)</th>
				<th colspan=${ajax.last_day+3+1} style="height: 50px;">
				</th>
			</tr>
			<tr>
				<td colspan=${ajax.last_day+20} style="text-align:left;">
				<c:choose>
					<c:when test="${ajax.bhf_name=='지점'}">
						지점  : 전체
					</c:when>
					<c:otherwise>
						지점  : ${ajax.bhf_name}
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr style="">
					<th scope="col" rowspan="2" style="text-align:center;"> </th>
					<th scope="col" rowspan="2" style="text-align:center;">사번</th>
					<th scope="col" rowspan="2" style="text-align:center;">성명</th>

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
					<th scope="col" id='d28_10601' style="text-align:center;">28</th>
					</c:if>
					<c:if test="${ajax.last_day >=29}">
					<th scope="col" id='d29_10601' style="text-align:center;">29</th>
					</c:if>
					<c:if test="${ajax.last_day >=30}">
					<th scope="col" id='d30_10601' style="text-align:center;">30</th>
					</c:if>
					<c:if test="${ajax.last_day >=31}">
					<th scope="col" id='d31_10601' style="text-align:center;">31</th>
					</c:if>					
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
							    	<c:when test="${week eq 1}"><th scope="col" style="text-align:center; color:red">일</c:when>
							    	<c:when test="${week eq 2}"><th scope="col" style="text-align:center;">월</c:when>
							    	<c:when test="${week eq 3}"><th scope="col" style="text-align:center;">화</c:when>
							    	<c:when test="${week eq 4}"><th scope="col" style="text-align:center;">수</c:when>
							    	<c:when test="${week eq 5}"><th scope="col" style="text-align:center;">목</c:when>
							    	<c:when test="${week eq 6}"><th scope="col" style="text-align:center;">금</c:when>
							    	<c:when test="${week eq 7}"><th scope="col" style="text-align:center; color:blue">토</c:when>
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

				</tr>
		</thead>
		<tbody id='tbodyExcel10601' >
			<c:set var="cnt" value="0" />
			<c:forEach var="list" items="${ajax.result}">
				<c:set var="cnt" value="${cnt+1}" />
				<tr>
					<td style="text-align:center;">${cnt}</td>
					<td style="text-align:center;">${list.em_id}</td>
					<td style="text-align:left;">${list.em_nm}</td>
					<td style="text-align:center;">${list.day_01}</td>
					<td style="text-align:center;">${list.day_02}</td>
					<td style="text-align:center;">${list.day_03}</td>
					<td style="text-align:center;">${list.day_04}</td>
					<td style="text-align:center;">${list.day_05}</td>
					<td style="text-align:center;">${list.day_06}</td>
					<td style="text-align:center;">${list.day_07}</td>
					<td style="text-align:center;">${list.day_08}</td>
					<td style="text-align:center;">${list.day_09}</td>
					<td style="text-align:center;">${list.day_10}</td>
					<td style="text-align:center;">${list.day_11}</td>
					<td style="text-align:center;">${list.day_12}</td>
					<td style="text-align:center;">${list.day_13}</td>
					<td style="text-align:center;">${list.day_14}</td>
					<td style="text-align:center;">${list.day_15}</td>
					<td style="text-align:center;">${list.day_16}</td>
					<td style="text-align:center;">${list.day_17}</td>
					<td style="text-align:center;">${list.day_18}</td>
					<td style="text-align:center;">${list.day_19}</td>
					<td style="text-align:center;">${list.day_20}</td>
					<td style="text-align:center;">${list.day_21}</td>
					<td style="text-align:center;">${list.day_22}</td>
					<td style="text-align:center;">${list.day_23}</td>
					<td style="text-align:center;">${list.day_24}</td>
					<td style="text-align:center;">${list.day_25}</td>
					<td style="text-align:center;">${list.day_26}</td>
					<td style="text-align:center;">${list.day_27}</td>
					<c:if test="${ajax.last_day >=28}">
					<td style="text-align:center;">${list.day_28}</td>
					</c:if>
					<c:if test="${ajax.last_day >=29}">
					<td style="text-align:center;">${list.day_29}</td>
					</c:if>
					<c:if test="${ajax.last_day >=30}">
					<td style="text-align:center;">${list.day_30}</td>
					</c:if>
					<c:if test="${ajax.last_day >=31}">
					<td style="text-align:center;">${list.day_31}</td>
					</c:if>					
				</tr>
			</c:forEach>
				<tr>
					<td>합계</td>
					<td style="text-align:left;" colspan = ${ajax.last_day+20-1} class='txt_left'>${cnt}</td>
				</tr>
		</tbody>
	</table>
</body>
</html>

