<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/20/20-105.js"></script>
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
			<h1>관리업체별 취급율</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"> </span>분석통계<span class="clamp fa"> </span>관리업체별<span class="clamp fa"></span>취급율
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span><strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode20105" style="width: 100px;"><option value="">선택</option></select>
					</span>
					
					<span style="margin-left: 10px;"><strong>고객그룹</strong></span>
					<span>
						<select id="searchCgCode20105" style="width: 100px;"><option value="">선택</option></select>
<!-- 						<input id="searchCgName20105" type="text" maxlength="30"> -->
<!-- 						<label for="searchCgName20105"></label>  -->
					</span>
					
					<span style="margin-left: 10px;"><strong>관리업체</strong></span>
					<span>
						<select id="searchMeCode20105" style="width: 100px;"><option value="">선택</option></select>
<!-- 						<input id="searchEmName20105" type="text" maxlength="30"> -->
<!-- 						<label for="searchEmName20105"></label>  -->
					</span>
					
<!-- 					<span style="margin-left: 15px;"><strong>매장명</strong></span> -->
<!-- 					<span> -->
<!-- 						<input id="searchSmName20105" type="text" maxlength="30"> -->
<!-- 						<label for="searchSmName20105"></label>  -->
<!-- 					</span> -->
					
					<span style="margin-left: 10px;"><strong>차수</strong></span>
					<span> 
						<select id="searchSmOdrFrom20105" style="width: 40px;">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						</select>
					</span>
					~
					<span> 
						<select id="searchSmOdrTo20105" style="width: 40px;">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						</select>
					</span>
					
					<span style="margin-left: 10px;"><strong>사원명</strong></span>
					<span>
						<input id="searchEmName20105" type="text" maxlength="20">
						<label for="searchEmName20105"></label> 
					</span>
					
					<span style="margin-left: 10px;"><strong>시도</strong></span>
					<span> 
						<select id="searchAddrCode120105" >
						<option value="">선택</option>
						</select>
					</span>
					<span style="margin-left: 10px;"><strong>구군</strong></span>
					<span> 
						<select id="searchAddrCode220105">
						<option value="">선택</option>
						</select>
					</span>
					<span style="margin-left: 10px">
						<strong>연월</strong>
						<input type="text" class="input-basic" id="yy_mm_20105" size="20" style="width: 70px;" readonly="readonly" >
					</span>					
				</div>
			<div class="btn_gup">
			<!-- A20171226 k2s 코드포함 여부 추가 -->
				<span>
					<span class="inputCheck">
						<input id="searchCodeAt20105"  style="margin-top:4px;" type="checkbox" >
						<label for="searchCodeAt20105" style="margin-top:4px;"></label>
					</span>
					<strong>코드포함 엑셀다운로드(고객그룹,관리업체)</strong>
				</span>				
				<button class="red"     id="diplySearchBtn20105">조회</button>
				<button class="gray"    id="diplySearchResetBtn20105">초기화</button>
				<button class="white"   id="diplyBatchBtn20105">배치</button>
				<button class="skyblue" id="excelDownBtn20105"><i class="fa fa-file-excel-o fa-lg"></i> 엑셀</button>
			</div>
		</div>
	</section>

	<section>
	<div style="overflow: auto;">
	<!-- UI Object -->
			<table class="tbl_col fixed-table">
				<thead id="fixedThead20105" >
<!-- 					<tr  style="height: 58px;" > -->
<!-- 						<th style="max-width: 90px;" >고객그룹</th> -->
<!-- 						<th style="max-width: 120px;" >관리업체</th> -->
<!-- 						<th style="max-width: 135px;" >매장명</th> -->
<!-- 						<th style="max-width: 60px;">사원명</th> -->
<!-- 						<th style="max-width: 55px;">차수</th> -->
<!-- 					</tr> -->
				</thead>
				<tbody id="fixedTbody20105">
				</tbody>
				<tfoot>
				<tr>
					<td colspan="5" style="font-weight:bold;text-align:center">취급율</td>
				</tr>
				</tfoot>
			</table>
			<table summary="PD매대 목록" class="tbl_col" style="border-bottom: 0;min-width:100%; margin-left: 509px ;"  id="displayTable20105" >
				<colgroup id="trtColgroup20105" >
				</colgroup>
				<caption>
					보조진열현황 목록
				</caption>
				<thead id="trtThead20105" >
				</thead>
				<tbody id="trtTbody20105">
				</tbody>
				<tfoot id="trtTfoot20105" >
					<tr style="background-color:#F7F7F7;font-weight:bold; border-top:2px solid #ABABAB">
						<td colspan="5" style="font-weight:bold;text-align:center">취급률</td>
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
	<div id="displayPageNavi20105" class="paginate" >
	</div>
	</section>
</article>
</body>
</html>
