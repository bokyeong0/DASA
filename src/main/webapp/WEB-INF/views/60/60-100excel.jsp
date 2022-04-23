<%@ page language="java" contentType="application/vnd.ms-excel;charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "com.vertexid.utill.CommonUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String fileName = "employeeList-" + CommonUtil.getCurrentDate() + ".xls";
response.setHeader("Content-Disposition", "attachment; filename="+fileName);
response.setHeader("Content-Description", "JSP Generated Data");
%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <table border="1">
        <thead>
            <tr>
                <th colspan="19">�����Ȳ</th>
            </tr>
            <tr>
                <th>No</th>
                <th>������</th>
                <th>����</th>
                <th>�̸�</th>
                <th>���̵�</th>
                <th>�Ի���</th>
                <th>�����</th>
                <th>��å��</th>
                <th>������</th>
                <th>�������</th>
                <th>�޷�������</th>
                <th>����</th>
                <th>�޴���ȭ</th>
                <th>��ȥ�����</th>
                <th>���ر����</th> 
                <th>��������</th>
                <th>�����ȣ</th>
                <th>�ּ�</th>
                <th>���ּ�</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="cnt" value="0" />
            <c:forEach var="employeeList" items="${ajax.employeeList}">
                <c:set var="cnt" value="${cnt+1}" />
                <tr>
                    <td>${cnt}</td>
                    <td>${employeeList.bhf_nm}</td>
                    <td>${employeeList.team_nm}</td>
                    <td>${employeeList.em_nm}</td>
                    <td>${employeeList.em_id}</td>
                    <td>${employeeList.em_ecny_de}</td>
                    <td>${employeeList.em_retire_de}</td>
                    <td>${employeeList.em_rspofc_nm}</td>
                    <td>${employeeList.em_dty_nm}</td>
                    <td>${employeeList.em_brthdy_nm}</td>
                    <td>${employeeList.em_cldr_nm}</td>
                    <td>${employeeList.em_sexdstn_nm}</td>
                    <td>${employeeList.em_mbtl_num}</td>
                    <td>${employeeList.em_mrnry_de_nm}</td>
                    <td>${employeeList.em_trans_fee}</td>
                    <td>${employeeList.use_at_nm}</td>
                    <td>${employeeList.em_zipcd}</td>
                    <td>${employeeList.em_adres}</td>
                    <td>${employeeList.em_dtadres}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
