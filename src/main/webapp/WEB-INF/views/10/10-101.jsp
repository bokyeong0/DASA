<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>

<!-- 프로그램명: 순방계획실적표 -->
<!-- AUIGrid 테마 CSS 파일입니다. 그리드 출력을 위해 꼭 삽입하십시오. -->
<!-- 원하는 테마가 있다면, 다른 파일로 교체 하십시오. -->
<link href="<%=cssPath%>/AUIGrid/AUIGrid_style.css" rel="stylesheet">

<jsp:include page="../inc/auth.jsp" flush='true' />
<!-- 화면과 연결된 스크립트 -->
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-101.js"></script>

<!-- AUIGrid 라이센스 파일입니다. 그리드 출력을 위해 꼭 삽입하십시오. -->
<!-- M20171116 k2s AUIGrid License 개발/ 운영 변경 공통 적용 -->
<script type="text/javascript" src="<%=strAUIGridLicense%>"></script>

<!-- 실제적인 AUIGrid 라이브러리입니다. 그리드 출력을 위해 꼭 삽입하십시오.--> 
<script type="text/javascript" src="<%=jsPath%>/AUIGrid/AUIGrid.js"></script>
<!-- AUIGrid 메세지 파일 - 한국어 -->
<script type="text/javascript" src="<%=jsPath%>/AUIGrid/messages/AUIGrid.messages.kr.js"></script>

<!-- 브라우저 다운로딩 할 수 있는 JS 추가 -->
<script type="text/javascript" src="<%=jsPath%>/AUIGrid/FileSaver.min.js"></script>

<style type="text/css">
/* 커스텀 셀 스타일 정의 */
.mycustom-r {
background : #53C14B;
color:#ea0707;
}
.mycustom-p {
background : #FFF29E;
color:#000;
}

/* 커스텀 칼럼 스타일 정의 */
.aui-grid-user-custom-column-0 {
    color: #ea0707;
	background:#ffffee;
	font-weight: bold;
}
/* 커스텀 칼럼 스타일 정의 */
.aui-grid-user-custom-column-6 {
    color: #072cea;
	background:#ffffee;
	font-weight: bold;
}
.aui-grid-my-data {
text-align:left;
}

.nav_u {
	display:inline-block;
}

.nav_u li {
	display: inline;
	white-space: nowrap;
	text-align:right;
}

.legend {
	display:inline-block;
	text-align:center;
	width:30px;
	margin: 0.5em;
	padding: 0.25em;
}
/* 기본 푸터 스타일 재정의 */
.aui-grid-default-footer {
	text-align:right;
}
.my-footer-style {
	text-align:center;
	color:#00aa00;
}

</style>

</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1>순방계획실적표</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"> </span>활동관리<span class="clamp fa"> </span>순방계획실적표
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchSmName10101" type="text" placeholder="매장명">
						<label for="searchSmName10101"><i class="fa fa-home"></i></label> 
					</span>
					<span>
						<input class="input-icon" id="searchEmName10101" type="text" placeholder="이름">
						<label for="searchEmName10101"><i class="fa fa-user"></i></label> 
					</span>
					<span> <strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode10101" class="select-basic"></select>
					</span>
					<span> <strong>팀명</strong></span>
					<span> 
						<select id="searchTmCode10101" class="select-basic"><option value=''>팀명</option></select>
					</span>
				</div>
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchStartDate10101" type="text" readonly="readonly" placeholder="날짜선택" width="130px" value="">
						<label for="searchStartDate10101"><i class="fa fa-calendar"></i></label>
					</span>
				</div>
			<div class="btn_gup">
				<ul class="nav_u">
					<li><span class="legend mycustom-p">P</span><span>계획</span></li>
					<li><span class="legend mycustom-r">R</span><span>순방</span></li>
			    </ul>
				<button class="red" id="cvsSearchBtn" >조회</button>
				<button class="gray" id="cvsSearchResetBtn" >초기화</button>
				<button class="skyblue" id="excelDownBtn10101"><i class="fa fa-file-excel-o fa-lg"></i> 엑셀</button>
				<button class="skyblue" id="excelDownTitleBtn10101"><i class="fa fa-file-excel-o fa-lg"></i> 엑셀(타이틀)</button>
			</div>
		</div>
	</section>
	<section>		
		<!-- 에이유아이 그리드가 이곳에 생성됩니다. -->
		<div id="planGrid10101" style="width:100%; height:700px; margin:0 auto;"></div>
		</div>	
	</section>
</article>
</body>
</html>
