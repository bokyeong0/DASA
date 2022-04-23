<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="application/vnd.ms-excel;charset=euc-kr" pageEncoding="euc-kr"%>
<%
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
	String today = formatter.format(new java.util.Date());
	String file_name = "approval_" + today;
	String ExcelName  = new String(file_name.getBytes("8859_1"),"euc-kr");

	response.setHeader("Content-Disposition", "attachment; filename="+ExcelName+".xls"); 
    response.setHeader("Content-Description", "JSP Generated Data");
    response.setContentType("application/vnd.ms-excel");
    
	String linePath = request.getRealPath("/") + "/resources/images/line.JPG";
	//String linePath = "D:\\workspace-sts-dasa\\DASA\\src\\main\\webapp\\resources\\images\\line.JPG"; 

%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	console.log(<%=linePath%>);
</script>
<script type="text/javascript"></script>

</head>
<body>
	<table  border='1' id="tbl_excel_30400" style="font-size:15px;">
		<thead>
			<tr>
				<th scope="col" style="text-align:center;">NO</th>
				<th scope="col" style="text-align:center;">������ȣ</th>
				<th scope="col" style="text-align:center;">������</th>
				<th scope="col" style="text-align:center;">�����</th>
				<th scope="col" style="text-align:center;">��������</th>
				<th scope="col" style="text-align:center;">��������</th>
				<th scope="col" style="text-align:center;">�������</th>
				<th scope="col" style="text-align:center;">�������</th>
			</tr>
		</thead>
		<tbody id="tbodyExcel30400">
		<%--	<tr>
				<td >NO</td>
				<td >������ȣ</td>
				<td >������</td>
				<td >�����</td>
				<td >��������</td>
				<td >��������1</td>
				<td >��������2</td>
				<td >�������</td>
				<td >�������</td>
			</tr> --%>
			
			<c:set var="cnt" value="0" />
			
			<c:forEach var="list" items="${ajax.result}">
				<c:set var="cnt" value="${cnt+1}" />
				<c:set var="am_code" value="${list.am_code}" />
				<c:set var="om_nm" value="${list.om_nm}" />
				<c:set var="em_nm" value="${list.em_nm}" />
				<c:set var="ad_type_nm" value="${list.ad_type_nm}" />
				<c:set var="ad_date_from" value="${ist.ad_date_from}" />
				<c:set var="ad_date_to" value="${list.ad_date_to}" />
 			    <c:set var="am_approval_date" value="${list.am_approval_date}" /> 	
 			    
				<%-- (${fn:substring(${list.ad_date_from_hhmm},0,2)} : ${fn:substring(${list.ad_date_from_hhmm},2,4)} ~ ${fn:substring(${list.ad_date_to_hhmm},0,2)} : ${fn:substring(${list.ad_date_to_hhmm},2,4)}) --%> 
				
				<c:set var="f_string1" value="${list.ad_date_from_hhmm}"/>
				<c:set var="f_string2" value="${fn:substring(f_string1, 0, 2)}" />
				<c:set var="f_string3" value="${list.ad_date_from_hhmm}"/>
				<c:set var="f_string4" value="${fn:substring(f_string3, 2, 4)}" />
				
				<c:set var="t_string1" value="${list.ad_date_to_hhmm}"/>
				<c:set var="t_string2" value="${fn:substring(t_string1, 0, 2)}" />
				<c:set var="t_string3" value="${list.ad_date_to_hhmm}"/>
				<c:set var="t_string4" value="${fn:substring(t_string3, 2, 4)}" />
                
				<c:choose>
				<%-- M20170929 k2s ���Ͽ���ٹ�, ���Ͽ���ٹ� �����ٿ�ε�� �ð� ǥ�� ���� �ʵ��� ���� �ݿ�(�����������û 2017�� 09�� 29��) --%>
					<c:when test="${list.ad_type eq 59}">
						<c:set var="ad_date" value="${list.ad_date_from}" />
						<%-- <c:set var="ad_date" value="${list.ad_date_from} (${f_string2} : ${f_string4} ~ ${t_string2} : ${t_string4})" /> --%>
					</c:when>
					<c:when test="${list.ad_type eq 390}">
						<c:set var="ad_date" value="${list.ad_date_from}" />
						<%-- <c:set var="ad_date" value="${list.ad_date_from} (${f_string2} : ${f_string4} ~ ${t_string2} : ${t_string4})" /> --%>
					</c:when>
					<c:otherwise>
						<c:set var="ad_date" value="${list.ad_date_from} ~ ${list.ad_date_to}"/>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${list.am_status eq 63}">
						<c:set var="am_status" value="������" />
					</c:when>
					<c:when test="${list.am_status eq 64}">
						<c:set var="am_status" value="����Ϸ�" />
					</c:when>
					<c:otherwise>
						<c:set var="am_status" value="����ݷ�" />
					</c:otherwise>
				</c:choose>
				
				<tr>
					<td style="text-align:center;">${cnt}</td>
					<td style="text-align:center;">${list.am_code}</td>
					<td style="text-align:center;">${list.om_nm}</td>
					<td style="text-align:left;">${list.em_nm}</td>
					<td style="text-align:center;">${list.ad_type_nm}</td>
					<td style="text-align:left;">${ad_date}</td>
					<td style="text-align:right;">${list.am_approval_date}</td>
					<td style="text-align:right;">${am_status}</td>
				</tr>
				
			</c:forEach> 
		</tbody>
	</table>
</body>
</html>

