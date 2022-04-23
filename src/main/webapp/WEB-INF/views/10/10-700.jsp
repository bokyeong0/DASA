<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-700.js"></script>
<script type="text/javascript">
		
</script>
<style type="text/css">
#fixOddTbody10700 .odd-inner-tb{
	padding: 0;
	border: none;
}
#fixOddTbody10700 .odd-inner-tb > table {
width: 100%;
}

#fixOddTbody10700 .odd-inner-tb > table th , #fixOddTbody10700 .odd-inner-tb > table td{
/* 	border-right:  none; */
	min-height: 200px;
}

.odd-img {
	cursor: pointer;
}

#activityFixPreView10700 {
  	cursor: move;
}
/* A20170120 kks 검색조건 줄 맞춤 별도 정의 함 */
.dateweekbox { display: inline-block; vertical-align: bottom; margin-bottom: 2px; width: 240px; }

</style>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="activityFixPreView10700" class="pop-apn-pop layer-popup" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<div class="phead">
				<span id="">사진보기</span>
				<a id="activityFixPreViewCloseXBtn" class="phead-closex">닫기</a>
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
			<h1>매장시황(고정MD)</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>활동관리<span class="clamp fa"></span>매장시황(고정MD)
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchSmName10700" type="text" placeholder="매장명">
						<label for="searchSmName10700"><i class="fa fa-home"></i></label> 
					</span>
					<span>
						<input class="input-icon" id="searchEmName10700" type="text" placeholder="이름">
						<label for="searchEmName10700"><i class="fa fa-user"></i></label> 
					</span>
					<span> <strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode10700" class="select-basic"></select>
					</span>
					<span> <strong>팀명</strong></span>
					<span> 
						<select id="searchTmCode10700" class="select-basic"><option value=''>팀명</option></select>
					</span>
				</div>
<!-- 				<div class="input_group clear"> M20170120 kks 달력검색 조회 조건 변경 주석처리 결정 확인 후 삭제 할 것
					<span>
						<input class="input-icon" id="searchBaseDate10700" type="text" readonly="readonly" placeholder="날짜선택" width="130px" value="">
						<label for="searchBaseDate10700"><i class="fa fa-calendar"></i></label>
					</span>
				</div> -->
				
				<div class="btn_gup">
					<button class="red" id="fixOddSearchBtn" >조회</button>
					<button class="gray" id="fixOddSearchResetBtn" >초기화</button>
				</div>

				<!-- A20170120 kks 검색조건 추가 -->
				<div class="dateweekbox">
					<input type="hidden" id="selectBaseDate" value="" >
					<ul class="dateweek">
						<li>
							<a id="diaryPrev" ><span><i class="fa fa-angle-left" ></i></span></a>
						</li>
						<li>
							<a class="cell_active" ><b id="diaryDate" ></b></a>
						</li>
						<li>
							<a id="diaryNext" > <span><i class="fa fa-angle-right" ></i></span></a>
						</li>
					</ul>
				</div>			
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="fixOdd 리스트" class="tbl_col">
			<caption>
				fixOdd 고정 목록
			</caption>
			<colgroup>
				<col width="5%"  >
				<col width="5%"  >
				<col width="5%"  >
				<col width="10%"  >
				<col width="*"  >
<!-- 				<col width="20%"  > -->
<!-- 				<col width="%"  >				 -->
			</colgroup>
			<thead>
				<tr>
					<th scope="col">No</th>
					<th scope="col">지점명</th>
					<th scope="col">MD명</th>
					<th scope="col">매장명</th>
					<th scope="col">구분</th>
<!-- 					<th scope="col">사진위치</th> -->
<!-- 					<th scope="col">특이사항</th> -->
				</tr>
			</thead>
			<tbody id="fixOddTbody10700" >
			</tbody>
		</table>
		<div id="fixOddNavi10700"  class="paginate"> 
		</div>
	</section>
	<!-- //UI Object -->
</article>
</body>
</html>

