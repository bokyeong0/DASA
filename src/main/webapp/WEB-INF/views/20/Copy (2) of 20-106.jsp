<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/20/20-106.js"></script>
</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1 id="mainTitle10100_0000000063">관리업체별 보조진열현황</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"> </span>분석통계<span class="clamp fa"> </span>관리업체별<span class="clamp fa"></span>보조진열현황
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span><strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode20106" style="width: 100px;"><option value="">선택</option></select>
					</span>
					
					<span style="margin-left: 15px;"><strong>고객그룹</strong></span>
					<span>
						<select id="searchCgCode20106" style="width: 100px;"><option value="">선택</option></select>
<!-- 						<input id="searchCgName20106" type="text" maxlength="30"> -->
<!-- 						<label for="searchCgName20106"></label>  -->
					</span>
					
					<span style="margin-left: 15px;"><strong>관리업체</strong></span>
					<span>
						<select id="searchMeCode20106" style="width: 100px;"><option value="">선택</option></select>
<!-- 						<input id="searchEmName20106" type="text" maxlength="30"> -->
<!-- 						<label for="searchEmName20106"></label>  -->
					</span>
					
<!-- 					<span style="margin-left: 15px;"><strong>매장명</strong></span> -->
<!-- 					<span> -->
<!-- 						<input id="searchSmName20106" type="text" maxlength="30"> -->
<!-- 						<label for="searchSmName20106"></label>  -->
<!-- 					</span> -->
					
					<span style="margin-left: 15px;"><strong>차수</strong></span>
					<span> 
						<select id="searchSmOdr20106" style="width: 60px;">
						<option value="">전체</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						</select>
					</span>
					
					
					<span style="margin-left: 15px;"><strong>시도</strong></span>
					<span> 
						<select id="searchAddrCode120106" >
						<option value="">전체</option>
						</select>
					</span>
					<span style="margin-left: 15px;"><strong>구군</strong></span>
					<span> 
						<select id="searchAddrCode220106">
						<option value="">전체</option>
						</select>
					</span>
<!-- 					<span style="margin-left: 15px;"><strong>면동</strong></span> -->
<!-- 					<span>  -->
<!-- 						<select id="searchAddrCode3" style="width: 50px;"> -->
<!-- 						<option value="">전체</option> -->
<!-- 						</select> -->
<!-- 					</span> -->
				</div>
			<div class="btn_gup">
				<button class="red" id="diplySearchBtn20106">조회</button>
				<button class="gray" id="diplySearchResetBtn20106">초기화</button>
				<button class="skyblue" id="excelDownBtn20106"><i class="fa fa-file-excel-o fa-lg"></i> 엑셀</button>
			</div>
		</div>
	</section>

	<section>
	<div style="overflow: auto;">
	<!-- UI Object -->
			<table summary="보조진열현황 목록" class="tbl_col" style="border-bottom: 0;min-width:1518px !important " id="displayTable20106" >
				<caption>
					보조진열현황 목록
				</caption>
				<thead id="arrThead20106" >
				</thead>
				<tbody id="arrTbody20106">
				</tbody>
				<tfoot id="arrTfoot20106" >
					<tr style="background-color:#F7F7F7;font-weight:bold; border-top:2px solid #ABABAB">
						<td colspan="4" style="font-weight:bold;text-align:center">합계</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tfoot>
			</table>

	<!-- //UI Object -->
	</div>
	<div id="displayPageNavi20106" class="paginate" >
<!-- 		<ul class="pagination"> -->
<!-- 		  <li><a href="#"> <span><i class="fa fa-angle-double-left"></i></span> </a></li> -->
<!-- 		     <li><a class="page_on">1</a></li> -->
<!-- 		     <li><a href="#" onclick="javascript:fnGetCvsList(2)"> 2 </a></li>  -->
<!-- 		     <li><a href="#" onclick="javascript:fnGetCvsList(3)"> 3 </a></li>  -->
<!-- 		     <li><a href="#" onclick="javascript:fnGetCvsList(4)"> 4 </a></li>  -->
<!-- 		     <li><a href="#" onclick="javascript:fnGetCvsList(5)"> 5 </a></li>  -->
<!-- 		     <li><a href="#" onclick="javascript:fnGetCvsList(6)"> 6 </a></li>  -->
<!-- 		   <li><a href="#"> <span><i class="fa fa-angle-double-right"></i></span> </a></li> -->
<!-- 		</ul> -->
	</div>
	</section>
</article>
</body>
</html>
