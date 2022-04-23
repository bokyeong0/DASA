<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-601.js"></script>
<style type="text/css">
.fixed-table td{
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
}
.fixed-table{
border-bottom: 0; width:510px ;   max-width: 510px; position: absolute; background-color: #fff ;
}
.fixed-table tfoot > tr{
background-color:#F7F7F7;font-weight:bold; border-top:2px solid #ABABAB
}
</style>
</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1>MD팀장근태(New)</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>사원관리<span class="clamp fa"></span>MD팀장근태(New)
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
			<div class="input_group clear">								
				<span style="margin-left: 12px">
					<select class="select-basic"  id="om_code_10601"  name ="om_code_10601" style="width: 120px">
					</select> 
				</span>
				<span style="margin-left: 12px">
					<strong>연월</strong>
					<input type="text" class="input-basic" id="yy_mm_10601" size="20" style="width: 70px;" readonly="readonly" >
				</span>
				<span style="margin-left: 12px">
					<strong>사번</strong>
					<input type="text" class="input-basic" id="em_no_10601" size="20" style="width: 90px;" >
				</span>
				<span style="margin-left: 12px">
					<strong>사원명</strong>
					<input type="text" class="input-basic" id="em_nm_10601" size="20" style="width: 90px;" >
				</span>

			</div>
			<div class="btn_gup clear">
				<button type="button" class="red" id="btnSearch_10601" >
					조회
				</button>
				<button type="button" class="gray" id="btnClear_10601" >
					초기화
				</button>
				<button type="button" class="skyblue auth-down" id="btnExcel_10601">
					<i class="fa fa-file-excel-o fa-lg"></i> 엑셀
				</button>
				<button type="button" class="skyblue" id="btnPrint_10601" style="display: none;">
					인쇄
				</button>
			</div>
		</div>
	</section>
	<section>
	<div style="overflow: auto;">
	<!-- UI Object -->
		<table summary="MD팀장 출퇴근 리스트_left" class="tbl_row fixed-table"  id ="tbl10601_left" style="width: 222.4px;">
			<caption>MD팀장 출퇴근 리스트</caption>
				<colgroup >
					<col style="width: 50px;" >
					<col style="width: 75px;">
					<col style="width: 70px;">
				</colgroup>
			<thead>
				<tr style="height: 54.5px;">
					<th scope="col" rowspan="2" style="text-align:center;">No.</th>
					<th scope="col" rowspan="2" style="text-align:center;">사번</th>
					<th scope="col" rowspan="2" style="text-align:center;">성명</th>
				</tr>
				<tr>
				</tr>
			</thead>
			<tbody id="tbody10601_left">
			</tbody>
		</table>
		<!-- M20170829 k2s width 2900px -> 3000px -->
		<table summary="MD팀장 출퇴근 리스트_right" class="tbl_row"  id ="tbl10601_right" style="width: 3000px;  margin-left: 222.4px;">
			<caption>MD팀장 출퇴근 리스트</caption>
				<colgroup >
					<!-- 
					<col style="width: 50px;" >
					<col style="width: 70px;">
					<col style="width: 75px;">
					 -->
					 <!-- M20170829 k2s 팀장근태 퇴근으로 표기 되던 것을 퇴근시간으로 표기함 width 75px -> 80px 변경 -->
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 80px;">
					
					<col style="width: 80px;">
					
				</colgroup>
			<thead>
				<tr style="">
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
					<th scope="col" id='d28_10601' style="text-align:center;">28</th>
					<th scope="col" id='d29_10601' style="text-align:center;">29</th>
					<th scope="col" id='d30_10601' style="text-align:center;">30</th>
					<th scope="col" id='d31_10601' style="text-align:center;">31</th>
				</tr>
				<tr>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;" id='w28_10601'>28</th>
					<th scope="col" style="text-align:center;" id='w29_10601'>29</th>
					<th scope="col" style="text-align:center;" id='w30_10601'>30</th>
					<th scope="col" style="text-align:center;" id='w31_10601'>31</th>
				</tr>
			</thead>
			<tbody id="tbody10601_right">
			</tbody>
		</table>
	<!-- //UI Object -->
	</div>
	</section>
	<section id="navi10601" class="paginate"/>


</article>
</body>
</html>
