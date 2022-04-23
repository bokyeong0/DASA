<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-800.js"></script>
<script type="text/javascript">
		
</script>
<style type="text/css">
#rndOddTbody10800 .odd-inner-tb{
	padding: 0;
	border: none;
}
#rndOddTbody10800 .odd-inner-tb > table {
width: 100%;
}

#rndOddTbody10800 .odd-inner-tb > table th , #rndOddTbody10800 .odd-inner-tb > table td{
/* 	border-right:  none; */
	min-height: 200px;
}

.odd-img {
	cursor: pointer;
}

#activityRndPreView10800 {
  	cursor: move;
}

ol {
    list-style: none;
    margin: 0;
    padding: 0px;
    overflow: hidden;
    border-top: 1px solid #dcdcdc;
}

ol > li:nth-child(1){
    float: left;
    width: 10%;
    border-right: 1px solid #e5e5e5;
    height: 180px;
    min-height: 180px;
    text-align: center;
    font-weight: bold;
    background: #F3F3F3;
    /* vertical-align: middle; */
}

ol > li:nth-child(1) > span{
    /* vertical-align: middle; */
    text-align: center;
    line-height: 180px;
    color: #666;
}

ol > li{
    float: left;
    width: 18%;
    border-right: 1px solid #e5e5e5;
    height: 180px;
    min-height: 180px;
}

ol > li:last-child{
    float: left;
    width: 18%;
    /*border-right: 1px solid #dce0e0;*/	
    height: 180px;
    min-height: 180px;
}

.content_div{
    border-bottom: 1px solid #dcdcdc;
}

.content_div > div > img {
    width: 100%;
    height: 100%;
}

</style>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="activityRndPreView10800" class="pop-apn-pop layer-popup" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<div class="phead">
				<span id="">사진보기</span>
				<a id="activityRndPreViewCloseXBtn" class="phead-closex">닫기</a>
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
			<h1>매장시황(순회MD)</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>활동관리<span class="clamp fa"></span>매장시황(순회MD)
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchSmName10800" type="text" placeholder="매장명">
						<label for="searchSmName10800"><i class="fa fa-home"></i></label> 
					</span>
					<span>
						<input class="input-icon" id="searchEmName10800" type="text" placeholder="이름">
						<label for="searchEmName10800"><i class="fa fa-user"></i></label> 
					</span>
					<span> <strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode10800" class="select-basic"></select>
					</span>
					<span> <strong>팀명</strong></span>
					<span> 
						<select id="searchTmCode10800" class="select-basic"><option value=''>팀명</option></select>
					</span>
				</div>
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchBaseDate10800" type="text" readonly="readonly" placeholder="날짜선택" width="130px" value="">
						<label for="searchBaseDate10800"><i class="fa fa-calendar"></i></label>
					</span>
				</div>
			
			<div class="btn_gup">
				<button class="red" id="rndOddSearchBtn" >조회</button>
				<button class="gray" id="rndOddSearchResetBtn" >초기화</button>
			</div>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="rndOdd 리스트" class="tbl_col">
			<caption>
				rndOdd 순회 목록
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
			<tbody id="rndOddTbody10800" >
			</tbody>
		</table>
		<div id="rndOddNavi10800"  class="paginate"> 
		</div>
	</section>
	<!-- //UI Object -->

</article>
</body>
</html>

