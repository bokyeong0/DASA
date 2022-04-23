<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-920.js"></script>
<script type="text/javascript">
		
</script>
<style type="text/css">
.odd-img {
	cursor: pointer;
}

#activityRndPreView10920 {
  	cursor: move;
}

</style>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="activityRndPreView10920" class="pop-apn-pop layer-popup" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<div class="phead">
				<span id="">사진보기</span>
				<a id="activityRndPreViewCloseXBtn10920" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="option-group option-left-group" style="overflow: hidden;" >
					<div style="text-align: center;" >
						<a class="close" href="#"><img src="" id="rndPrevImg"  style="max-width: 100%;" ></a>
					</div>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<div id="loading" class="popup layer-popup" style="display: none ;">Loading...</div>
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1>PD매대 사진(순회MD)</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>활동관리<span class="clamp fa"></span>PD매대 사진(순회MD)
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchSmName10920" type="text" placeholder="매장명">
						<label for="searchSmName10920"><i class="fa fa-home"></i></label> 
					</span>
					<span>
						<input class="input-icon" id="searchEmName10920" type="text" placeholder="이름">
						<label for="searchEmName10920"><i class="fa fa-user"></i></label> 
					</span>
					<span> <strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode10920" class="select-basic"></select>
					</span>
					<span> <strong>팀명</strong></span>
					<span> 
						<select id="searchTmCode10920" class="select-basic"><option value=''>팀명</option></select>
					</span>
				</div>
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchStartDate10920" type="text" readonly="readonly" placeholder="날짜선택" width="130px" value="">
						<label for="searchStartDate10920"><i class="fa fa-calendar"></i></label>
					</span> ~
					<span>
						<input class="input-icon" id="searchEndDate10920" type="text" readonly="readonly" placeholder="날짜선택" value="">
						<label for="searchEndDate10920"><i class="fa fa-calendar"></i></label>
					</span>
				</div>
			
			<div class="btn_gup">
				<button class="red" id="rndPdImgSearchBtn" >조회</button>
				<button class="gray" id="rndPdImgSearchResetBtn" >초기화</button>
			</div>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="rnd PD매대 리스트" class="tbl_col">
			<caption>
				rnd PD매대 순회 목록
			</caption>
			<colgroup>
				<col width="5%"  >
				<col width="10%"  >
				<col width="10%"  >
				<col width="10%"  >
				<col width="*"  >
			</colgroup>
			<thead>
				<tr>
					<th scope="col">No</th>
					<th scope="col">지점명</th>
					<th scope="col">MD명</th>
					<th scope="col">매장명</th>
					<th scope="col">매대사진</th>
				</tr>
			</thead>
			<tbody id="rndPdImgTbody10920" >
			</tbody>
		</table>
		<div id="rndPdImgNavi10920"  class="paginate"> 
		</div>
	</section>
	<!-- //UI Object -->

</article>
</body>
</html>

