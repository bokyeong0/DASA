<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%=jsPath%>/routine/20/20-407.js"></script>
<jsp:include page="../inc/auth.jsp" flush='true' />
<!-- 프로그램명: 관리업체별 PD매대현황 -->

<!-- AUIGrid 테마 CSS 파일입니다. 그리드 출력을 위해 꼭 삽입하십시오. -->
<!-- 원하는 테마가 있다면, 다른 파일로 교체 하십시오. -->
<link href="<%=cssPath%>/AUIGrid/AUIGrid_style.css" rel="stylesheet">

<!-- AUIGrid 라이센스 파일입니다. 그리드 출력을 위해 꼭 삽입하십시오. -->
<!-- M20171110 k2s AUIGrid License 개발/ 운영 변경 공통 적용 -->
<script type="text/javascript" src="<%=strAUIGridLicense%>"></script>

<!-- 실제적인 AUIGrid 라이브러리입니다. 그리드 출력을 위해 꼭 삽입하십시오.--> 
<script type="text/javascript" src="<%=jsPath%>/AUIGrid/AUIGrid.js"></script>

<!-- AUIGrid 메세지 파일 - 한국어 -->
<script type="text/javascript" src="<%=jsPath%>/AUIGrid/messages/AUIGrid.messages.kr.js"></script>

<!-- 브라우저 다운로딩 할 수 있는 JS 추가 -->
<script type="text/javascript" src="<%=jsPath%>/AUIGrid/FileSaver.min.js"></script>

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

/* 커스텀 summary total  스타일 */
.aui-grid-my-footer-sum-total {
	font-weight:bold;
	color:#4374D9;
	text-align:right;
}
.aui-grid-my-data {
text-align:left;
}
</style>

</head>

<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1>관리업체별 PD매대현황</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"> </span>분석통계<span class="clamp fa"> </span>관리업체별<span class="clamp fa"></span>PD매대현황(Grid Ver.)
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span><strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode20407" style="width: 100px;"><option value="">선택</option></select>
					</span>
					
					<span style="margin-left: 10px;"><strong>고객그룹</strong></span>
					<span>
						<select id="searchCgCode20407" style="width: 100px;"><option value="">선택</option></select>
<!-- 						<input id="searchCgName20407" type="text" maxlength="30"> -->
<!-- 						<label for="searchCgName20407"></label>  -->
					</span>
					
					<span style="margin-left: 10px;"><strong>관리업체</strong></span>
					<span>
						<select id="searchMeCode20407" style="width: 100px;"><option value="">선택</option></select>
<!-- 						<input id="searchEmName20407" type="text" maxlength="30"> -->
<!-- 						<label for="searchEmName20407"></label>  -->
					</span>
					
<!-- 					<span style="margin-left: 15px;"><strong>매장명</strong></span> -->
<!-- 					<span> -->
<!-- 						<input id="searchSmName20407" type="text" maxlength="30"> -->
<!-- 						<label for="searchSmName20407"></label>  -->
<!-- 					</span> -->
					
					<span style="margin-left: 10px;"><strong>차수</strong></span>
					<span> 
						<select id="searchSmOdrFrom20407" style="width: 40px;">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						</select>
					</span>
					~
					<span> 
						<select id="searchSmOdrTo20407" style="width: 40px;">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						</select>
					</span>
					
					<span style="margin-left: 10px;"><strong>사원명</strong></span>
					<span>
						<input id="searchEmName20407" type="text" maxlength="20">
						<label for="searchEmName20407"></label> 
					</span>
					
					<span style="margin-left: 10px;"><strong>시도</strong></span>
					<span> 
						<select id="searchAddrCode120407" >
						<option value="">선택</option>
						</select>
					</span>
					<span style="margin-left: 10px;"><strong>구군</strong></span>
					<span> 
						<select id="searchAddrCode220407">
						<option value="">선택</option>
						</select>
					</span>
					<span style="margin-left: 10px">
						<strong>연월</strong>
						<input type="text" class="input-basic" id="yy_mm_20407" size="20" style="width: 70px;" readonly="readonly" >
					</span>						
				</div>
			<div class="btn_gup">
			<!-- A20171226 k2s 코드포함 여부 추가 -->
				<span>
					<span class="inputCheck">
						<input id="searchCodeAt20407"  style="margin-top:4px;" type="checkbox" >
						<label for="searchCodeAt20407" style="margin-top:4px;"></label>
					</span>
					<strong>코드포함 엑셀다운로드(고객그룹,관리업체)</strong>
				</span>			
				<button class="red"                 id="diplySearchBtn20407">조회</button>
				<button class="gray"                id="diplySearchResetBtn20407">초기화</button>
				<button class="white auth-update"   id="diplyBatchBtn20407">배치</button>
				<button class="skyblue auth-down"   id="excelDownBtn20407"><i class="fa fa-file-excel-o fa-lg"></i> 엑셀</button>
				<button class="skyblue auth-down"   id="excelDownGridBtn20407"><i class="fa fa-file-excel-o fa-lg"></i> Grid엑셀(코드포함)</button>
				<button class="skyblue auth-down"   id="excelDownGridAllBtn20407"><i class="fa fa-file-excel-o fa-lg"></i> Grid엑셀(코드제외)</button>
				<span> 
					<select id="searchRowCnt20407" style="width: 110px;">
					     <option value="100">100 개씩보기</option>
					     <option value="200">200 개씩보기</option>
					     <option value="500">500 개씩보기</option>
					     <option value="9999999" selected>전체보기</option>
					</select>
				</span>				
				
			</div>
		</div>
	</section>

	<section>
	
		<!-- 그리드 생성 -->
		<div id="pdGrid20407" style="width:%; height:700px; margin:0 auto;"></div>
		</div>	
		<div id="displayPageNavi20407" class="paginate" >
		</div>
	
	</section>
</article>
</body>
</html>
