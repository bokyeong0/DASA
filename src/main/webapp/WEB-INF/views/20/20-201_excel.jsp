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
	
	var week = new Array('��', '��', 'ȭ', '��', '��', '��', '��');
	
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
				<th colspan=${ajax.last_day+5+1} style="text-align:left; font-size: 25px;">����ٹ����� (${ajax.yyyy}�� ${ajax.mm}��)</th>
				<th colspan=10 style="height: 130px;">
				</th>
			</tr>
			<tr>
				<td colspan=${ajax.last_day+20} style="text-align:left;">
				<c:choose>
					<c:when test="${ajax.bhf_name=='����'}">
						����  : ��ü
					</c:when>
					<c:otherwise>
						����  : ${ajax.bhf_name}
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr style="">
					<th scope="col" rowspan="2" style="text-align:center;"> </th>
					<th scope="col" rowspan="2" style="text-align:center;">����</th>
					<th scope="col" rowspan="2" style="text-align:center;">���</th>
					<th scope="col" rowspan="2" style="text-align:center;">����</th>
					<th scope="col" rowspan="2" style="text-align:center;">�Ի���</th>
					<th scope="col" rowspan="2" style="text-align:center;">����</th>
					
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
					
					<th scope="col" rowspan="2" style="text-align:center; width:70px;">���رٹ� �ϼ�</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">���� �ϼ�</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">���� �ٹ�</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">���� �ٹ�</th>
					<th scope="col" rowspan="2" style="text-align:center; width:55px;">�ð��� �ٹ�</th>
					<th scope="col" rowspan="2" style="text-align:center; width:55px;">�Ǳٹ� �ϼ�</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">��� �ϼ�</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">���� �ް�</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">�ϰ� �ް�</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">���� �ް�</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">����</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">����</th>
					<th scope="col" rowspan="2" style="text-align:center; width:55px;">���� �����</th>
					<th scope="col" rowspan="2" style="text-align:center; width:55px;">���� �����</th>
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
							    	<c:when test="${week eq 1}"><th scope="col" style="text-align:center; color:red">��</c:when>
							    	<c:when test="${week eq 2}"><th scope="col" style="text-align:center;">��</c:when>
							    	<c:when test="${week eq 3}"><th scope="col" style="text-align:center;">ȭ</c:when>
							    	<c:when test="${week eq 4}"><th scope="col" style="text-align:center;">��</c:when>
							    	<c:when test="${week eq 5}"><th scope="col" style="text-align:center;">��</c:when>
							    	<c:when test="${week eq 6}"><th scope="col" style="text-align:center;">��</c:when>
							    	<c:when test="${week eq 7}"><th scope="col" style="text-align:center; color:blue">��</c:when>
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
					<th scope="col" rowspan="2">���رٹ��ϼ�</th>
					<th scope="col" rowspan="2">�����ϼ�</th>
					<th scope="col" rowspan="2">���ϱٹ�</th>
					<th scope="col" rowspan="2">���ϱٹ�</th>
					<th scope="col" rowspan="2">�ð��ܱٹ�</th>
					<th scope="col" rowspan="2">�Ǳٹ��ϼ�</th>
					<th scope="col" rowspan="2" style="display: none;">�⺻�ð�</th>
					<th scope="col" rowspan="2">����ϼ�</th>
					<th scope="col" rowspan="2">�����ް�</th>
					<th scope="col" rowspan="2">�ϰ��ް�</th>
					<th scope="col" rowspan="2">�����ް�</th>
					<th scope="col" rowspan="2">����</th>
					<th scope="col" rowspan="2">����</th>
					<th scope="col" rowspan="2">���ر����</th>
					<th scope="col" rowspan="2">���ޱ����</th> --%>
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
						<c:when test="${fn:contains(list.ea_day_01, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_01, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_01}</td>
						</c:when>
						<c:when test="${list.ea_day_01.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_01}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_01}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_02, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_02, '�޿�')}">  
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_02}</td>
						</c:when>
						<c:when test="${list.ea_day_02.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_02}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_02}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_03, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_03, '�޿�')}">  
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_03}</td>
						</c:when>
						<c:when test="${list.ea_day_03.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_03}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_03}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_04, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_04, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_04}</td>
						</c:when>
						<c:when test="${list.ea_day_04.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_04}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_04}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_05, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_05, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_05}</td>
						</c:when>
						<c:when test="${list.ea_day_05.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_05}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_05}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_06, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_06, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_06}</td>
						</c:when>
						<c:when test="${list.ea_day_06.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_06}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_06}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_07, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_07, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_07}</td>
						</c:when>
						<c:when test="${list.ea_day_07.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_07}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_07}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_08, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_08, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_08}</td>
						</c:when>
						<c:when test="${list.ea_day_08.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_08}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_08}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_09, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_09, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_09}</td>
						</c:when>
						<c:when test="${list.ea_day_09.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_09}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_09}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_10, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_10, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_10}</td>
						</c:when>
						<c:when test="${list.ea_day_10.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_10}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_10}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_11, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_11, '�޿�')}">  
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_11}</td>
						</c:when>
						<c:when test="${list.ea_day_11.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_11}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_11}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_12, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_12, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_12}</td>
						</c:when>
						<c:when test="${list.ea_day_12.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_12}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_12}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_13, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_13, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_13}</td>
						</c:when>
						<c:when test="${list.ea_day_13.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_13}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_13}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_14, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_14, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_14}</td>
						</c:when>
						<c:when test="${list.ea_day_14.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_14}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_14}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_15, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_15, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_15}</td>
						</c:when>
						<c:when test="${list.ea_day_15.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_15}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_15}</td>
						</c:otherwise>
					</c:choose>
										
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_16, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_16, '�޿�')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_16}</td>
						</c:when>
						<c:when test="${list.ea_day_16.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_16}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_16}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_17, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_17, '�޿�')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_17}</td>
						</c:when>
						<c:when test="${list.ea_day_17.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_17}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_17}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_18, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_18, '�޿�')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_18}</td>
						</c:when>
						<c:when test="${list.ea_day_18.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_18}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_18}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_19, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_19, '�޿�')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_19}</td>
						</c:when>
						<c:when test="${list.ea_day_19.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_19}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_19}</td>
						</c:otherwise>
					</c:choose>
				 	
				 	<c:choose>
						<c:when test="${fn:contains(list.ea_day_20, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_20, '�޿�')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_20}</td>
						</c:when>
						<c:when test="${list.ea_day_20.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_20}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_20}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_21, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_21, '�޿�')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_21}</td>
						</c:when>
						<c:when test="${list.ea_day_21.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_21}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_21}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_22, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_22, '�޿�')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_22}</td>
						</c:when>
						<c:when test="${list.ea_day_22.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_22}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_22}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_23, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_23, '�޿�')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_23}</td>
						</c:when>
						<c:when test="${list.ea_day_23.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_23}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_23}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_24, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_24, '�޿�')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_24}</td>
						</c:when>
						<c:when test="${list.ea_day_24.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_24}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_24}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_25, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_25, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_25}</td>
						</c:when>
						<c:when test="${list.ea_day_25.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_25}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_25}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_26, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_26, '�޿�')}">
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_26}</td>
						</c:when>
						<c:when test="${list.ea_day_26.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_26}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_26}</td>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${fn:contains(list.ea_day_27, '����')}">
							<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('�ޱ�')}"> 
							<td style="text-align:center; background-color:#FFA251">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${fn:contains(list.ea_day_27, '�޿�')}"> 
							<td style="text-align:center; background-color:#FFA200">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('����')}"> 
							<td style="text-align:center; background-color:#FFFF51">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('����')}"> 
							<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('����')}"> 
							<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('����')}"> 
							<td style="text-align:center; background-color:#6975DF">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('����')}"> 
							<td style="text-align:center; background-color:#C57DFF">${list.ea_day_27}</td>
						</c:when>
						<c:when test="${list.ea_day_27.equals('�޹�')}"> 
							<td style="text-align:center; background-color:#f5f7f9">${list.ea_day_27}</td>
						</c:when>
						<c:otherwise>
						    <td style="text-align:center;">${list.ea_day_27}</td>
						</c:otherwise>
					</c:choose>
					
					<c:if test="${ajax.last_day >=28}">
						<%-- <td style="text-align:center;">${list.ea_day_28}</td> --%>
						<c:choose>
							<c:when test="${fn:contains(list.ea_day_28, '����')}">
								<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('�ޱ�')}"> 
								<td style="text-align:center; background-color:#FFA251">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${fn:contains(list.ea_day_28, '�޿�')}"> 
								<td style="text-align:center; background-color:#FFA200">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('����')}"> 
								<td style="text-align:center; background-color:#FFFF51">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('����')}"> 
								<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('����')}"> 
								<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('����')}"> 
								<td style="text-align:center; background-color:#6975DF">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('����')}"> 
								<td style="text-align:center; background-color:#C57DFF">${list.ea_day_28}</td>
							</c:when>
							<c:when test="${list.ea_day_28.equals('�޹�')}"> 
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
							<c:when test="${fn:contains(list.ea_day_29, '����')}">
								<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('�ޱ�')}"> 
								<td style="text-align:center; background-color:#FFA251">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${fn:contains(list.ea_day_29, '�޿�')}"> 
								<td style="text-align:center; background-color:#FFA200">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('����')}"> 
								<td style="text-align:center; background-color:#FFFF51">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('����')}"> 
								<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('����')}"> 
								<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('����')}"> 
								<td style="text-align:center; background-color:#6975DF">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('����')}"> 
								<td style="text-align:center; background-color:#C57DFF">${list.ea_day_29}</td>
							</c:when>
							<c:when test="${list.ea_day_29.equals('�޹�')}"> 
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
							<c:when test="${fn:contains(list.ea_day_30, '����')}">
								<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('�ޱ�')}"> 
								<td style="text-align:center; background-color:#FFA251">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${fn:contains(list.ea_day_30, '�޿�')}"> 
								<td style="text-align:center; background-color:#FFA200">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('����')}"> 
								<td style="text-align:center; background-color:#FFFF51">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('����')}"> 
								<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('����')}"> 
								<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('����')}"> 
								<td style="text-align:center; background-color:#6975DF">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('����')}"> 
								<td style="text-align:center; background-color:#C57DFF">${list.ea_day_30}</td>
							</c:when>
							<c:when test="${list.ea_day_30.equals('�޹�')}"> 
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
							<c:when test="${fn:contains(list.ea_day_31, '����')}">
								<td style="text-align:center; background-color:#FFC8C8">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('�ޱ�')}"> 
								<td style="text-align:center; background-color:#FFA251">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${fn:contains(list.ea_day_31, '�޿�')}"> 
								<td style="text-align:center; background-color:#FFA200">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('����')}"> 
								<td style="text-align:center; background-color:#FFFF51">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('����')}"> 
								<td style="text-align:center; background-color:#B0FAAC">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('����')}"> 
								<td style="text-align:center; background-color:#B2ACFA">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('����')}"> 
								<td style="text-align:center; background-color:#6975DF">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('����')}"> 
								<td style="text-align:center; background-color:#C57DFF">${list.ea_day_31}</td>
							</c:when>
							<c:when test="${list.ea_day_31.equals('�޹�')}"> 
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
					<td>�հ�</td>
					<td style="text-align:left;" colspan = ${ajax.last_day+6-1} class='txt_left'>${cnt}</td>
					<td style="text-align:right;" class='txt_right'></td><%-- ���رٹ��ϼ�--%>
					<td style="text-align:right;" class='txt_right'></td><%-- �����ϼ�--%>
					<td style="text-align:right;" class='txt_right'></td><%-- ���ϱٹ�--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${holiday_work}</fmt:formatNumber></td><%-- ���ϱٹ�--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${overtime_work}</fmt:formatNumber></td><%-- �ð��ܱٹ�--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${actual_work}</fmt:formatNumber></td><%-- �Ǳٹ��ϼ�--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${absent}</fmt:formatNumber></td><%-- ����ϼ�--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${annual_leave}</fmt:formatNumber></td><%-- ����--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${summer_leave}</fmt:formatNumber></td><%-- �ϰ�--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${cc_leave}</fmt:formatNumber></td><%-- ����--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${education}</fmt:formatNumber></td><%-- ����--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${sick_leave}</fmt:formatNumber></td><%-- ����--%>
					<td style="text-align:right;" class='txt_right'></td><%-- ���ر����--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${trans_fee}</fmt:formatNumber></td><%-- ���ݱ����--%>			
				</tr>
		</tbody>
	</table>
</body>
</html>

