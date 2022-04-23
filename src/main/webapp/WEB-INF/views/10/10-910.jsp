<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-910.js"></script>
<script type="text/javascript">
		
</script>
<style type="text/css">
#activityFixPreView10910 {
  	cursor: move;
}
/* A20170120 kks 검색조건 줄 맞춤 별도 정의 함 */
.dateweekbox { display: inline-block; vertical-align: bottom; margin-bottom: 2px; width: 240px; }

</style>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="activityFixPreView10910" class="pop-apn-pop layer-popup" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<div class="phead">
				<span id="">사진보기</span>
				<a id="activityFixPreViewCloseXBtn10910" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="option-group option-left-group" style="overflow: hidden;" >
					<div style="text-align: center;" >
						<a class="close" href="#"><img src="" id="fixPrevImg"  style="  max-width: 100%;" ></a>
					</div>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1>PD매대 사진(고정MD)</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>활동관리<span class="clamp fa"></span>PD매대 사진(고정MD)
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchSmName10910" type="text" placeholder="매장명">
						<label for="searchSmName10910"><i class="fa fa-home"></i></label> 
					</span>
					<span>
						<input class="input-icon" id="searchEmName10910" type="text" placeholder="이름">
						<label for="searchEmName10910"><i class="fa fa-user"></i></label> 
					</span>
					<span> <strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode10910" class="select-basic"></select>
					</span>
					<span> <strong>팀명</strong></span>
					<span> 
						<select id="searchTmCode10910" class="select-basic"><option value=''>팀명</option></select>
					</span>
				</div>
				<div class="btn_gup">
					<button class="red" id="fixPdImgSearchBtn" >조회</button>
					<button class="gray" id="fixPdImgSearchResetBtn" >초기화</button>
				</div>

				<!-- A20170120 kks 검색조건 추가 -->
				<div class="dateweekbox">
					<input type="hidden" id="selectBaseDate10910" value="" >
					<ul class="dateweek">
						<li>
							<a id="diaryPrev10910" ><span><i class="fa fa-angle-left" ></i></span></a>
						</li>
						<li>
							<a class="cell_active" ><b id="diaryDate10910" ></b></a>
						</li>
						<li>
							<a id="diaryNext10910" > <span><i class="fa fa-angle-right" ></i></span></a>
						</li>
					</ul>
				</div>			
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="PD매대 고정 리스트" class="tbl_col">
			<caption>
				PD매대 고정 리스트
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
			<tbody id="fixPdImgTbody10910" >
			</tbody>
		</table>
		<div id="fixPdImgNavi10910"  class="paginate"> 
		</div>
	</section>
	<!-- //UI Object -->
</article>
</body>
</html>

