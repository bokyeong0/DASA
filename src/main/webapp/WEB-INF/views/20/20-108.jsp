<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>

<!-- 화면과 연결된 스크립트 -->
<script type="text/javascript" src="<%=jsPath%>/routine/20/20-108.js"></script>
<jsp:include page="../inc/auth.jsp" flush='true' />


<!-- AUIGrid 테마 CSS 파일입니다. 그리드 출력을 위해 꼭 삽입하십시오. -->
<!-- 원하는 테마가 있다면, 다른 파일로 교체 하십시오. -->
<link href="<%=cssPath%>/AUIGrid/AUIGrid_style.css" rel="stylesheet">

<!-- AUIGrid 라이센스 파일입니다. 그리드 출력을 위해 꼭 삽입하십시오. -->
<!-- M20171110 k2s AUIGrid License 개발/ 운영 변경 공통 적용 -->
<script type="text/javascript" src="<%=strAUIGridLicense%>"></script>

<!-- 실제적인 AUIGrid 라이브러리입니다. 그리드 출력을 위해 꼭 삽입하십시오.--> 
<script type="text/javascript" src="<%=jsPath%>/AUIGrid/AUIGrid.js"></script>

<!-- 브라우저 다운로딩 할 수 있는 JS 추가 -->
<script type="text/javascript" src="<%=jsPath%>/AUIGrid/FileSaver.min.js"></script>

<style type="text/css">
.aui-grid-my-data {
text-align:left;
}

/* 커스텀 셀 스타일 정의 A20180226 k2s 색상구분 color-bad:불량, color-normal:보통 */
.color-bad {
color : #db3f47;
}
.color-normal {
color : #3b75d3;
}
</style>

</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1>CVS 체크리스트</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"> </span>분석통계<span class="clamp fa"> </span>CVS 체크리스트
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchSmName20108" type="text" placeholder="매장명">
						<label for="searchSmName20108"><i class="fa fa-home"></i></label> 
					</span>
					<span>
						<input class="input-icon" id="searchEmName20108" type="text" placeholder="이름">
						<label for="searchEmName20108"><i class="fa fa-user"></i></label> 
					</span>
					<span> <strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode20108" class="select-basic"></select>
					</span>
					<span> <strong>팀명</strong></span>
					<span> 
						<select id="searchTmCode20108" class="select-basic"><option value=''>팀명</option></select>
					</span>
				</div>
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchStartDate20108" type="text" readonly="readonly" placeholder="날짜선택" width="130px" value="">
						<label for="searchStartDate20108"><i class="fa fa-calendar"></i></label>
					</span> ~
					<span>
						<input class="input-icon" id="searchEndDate20108" type="text" readonly="readonly" placeholder="날짜선택" value="">
						<label for="searchEndDate20108"><i class="fa fa-calendar"></i></label>
					</span>
				</div>
			<div class="btn_gup">
				<button class="red" id="cvsSearchBtn20108" >조회</button>
				<button class="gray" id="cvsSearchResetBtn20108" >초기화</button>
				<button class="skyblue" id="excelDownBtn20108"><i class="fa fa-file-excel-o fa-lg"></i> 엑셀</button>
			</div>
		</div>
	</section>
	<section>		
		<!-- 에이유아이 그리드가 이곳에 생성됩니다. -->
		<div id="cvsCheckGrid20108" style="width:100%; height:700px; margin:0 auto;"></div>
		</div>	
	</section>
</article>
</body>
</html>
