<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<link href="<%=cssPath%>/AUIGrid/AUIGrid_style.css" rel="stylesheet"> 
<script type="text/javascript" src="<%=strAUIGridLicense%>"></script> 
<script type="text/javascript" src="<%=jsPath%>/AUIGrid/AUIGrid.js"></script>  
<script type="text/javascript" src="<%=jsPath%>/routine/65/65-100.js"></script>
</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1>지점별 취급품목 관리 <select id="searchBhf65100" class="select-basic"><option value="" selected="selected">지점</option></select></h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>취급품목 관리<span class="clamp fa"></span>지점별 취급품목 관리
			</p>
		</div>
	</section>
	
	<section>
		<!-- 그리드 생성 -->
		<div>
			<!-- Master Grid -->
			<div style="float:left; margin-left:100px;">
				<div style="width:143px; margin:10px auto;"><h4><p id="omNm65100">지점 선택</p></h4></div>
				<div id="grid_wrap65100" style="width:143px; height:80px; margin:0 auto;"></div>
			</div>
			<!-- Details Grid -->
			<div style="width:555px; margin-left:350px;">
				<div class="searchbox" >
					<h4><p id="detail_info65100" style="float:left;">상품 선택</p></h4>
					<div class="btn_gup">
						<button class="red auth-update" id="optionItemUpt65100" >수정사항 적용</button>
					</div>
				</div>
				<div id="detail_grid_wrap65100" style="width:555px; height:700px; margin:0 auto;"></div>
			</div>
		</div>
	</section>
	<!-- //UI Object -->
</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
