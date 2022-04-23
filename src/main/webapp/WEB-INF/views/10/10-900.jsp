<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="/resources/smarteditor/js/service/HuskyEZCreator.js"></script> 
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-900.js"></script>
<script type="text/javascript">
		
</script>
<style type="text/css">


</style>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="emiViewPop10900" style="display:none ;">
		<div class="popup" style="width: 1000px;">
			<div class="phead">
				<span>행사 보기</span>
				<a id="emiViewPopCloseX10900" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<section>
				<div class="searchbox">
						<div class="input_group clear">
							<span><strong>지점명</strong></span>
							<span> 
								<select id="searchOmCode10900" style="width: 100px;"><option value="00001">동부지점</option></select>
							</span>
							
							<span style="margin-left: 15px;"><strong>고객그룹</strong></span>
							<span>
								<select id="searchCgCode10900" style="width: 100px;"><option value="">선택</option></select>
							</span>
							
							<span style="margin-left: 15px;"><strong>관리업체</strong></span>
							<span>
								<select id="searchMeCode10900" style="width: 150px;"><option value="">선택</option></select>
							</span>
							
							<span style="margin-left: 15px;"><strong>매장명</strong></span>
							<span>
								<select id="searchSmCode10900" style="width: 150px;"><option value="">선택</option></select>
							</span>
						</div>
						<div class="input_group clear">
							<span>
								<input class="input-icon" id="searchStartDate10900" type="text" readonly="readonly" placeholder="날짜선택" width="130px" value="">
								<label for="searchStartDate10900"><i class="fa fa-calendar"></i></label>
							</span> ~
							<span>
								<input class="input-icon" id="searchEndDate10900" type="text" readonly="readonly" placeholder="날짜선택" value="">
								<label for="searchEndDate10900"><i class="fa fa-calendar"></i></label>
							</span>
						</div>
					<div class="btn_gup">
						<button class="red" id="emiSearchBtn" >조회</button>
						<button class="gray" id="emiSearchResetBtn" >초기화</button>
					</div>
				</div>
			</section>
			<section>
			<!-- UI Object -->
				<table summary="행사 및 매장 현황 리스트" class="tbl_col">
					<caption>
						행사 및 매장 현황 목록
					</caption>
					<colgroup>
						<col width="5%"  >
						<col width="20%"  >
						<col width="10%"  >
						<col width="10%"  >
						<col width="10%"  >
						<col width="10%"  >
						<col width="20%"  >
						<col width="15%"  >
					</colgroup>
					<thead>
						<tr>
							<th scope="col">No</th>
							<th scope="col">행사명</th>
							<th scope="col">지점명</th>
							<th scope="col">고객그룹</th>
							<th scope="col">관리업체</th>
							<th scope="col">매장명</th>
							<th scope="col">사진</th>
							<th scope="col">업데이트일자</th>
						</tr>
					</thead>
					<tbody id="emiTbody10900" >
					</tbody>
				</table>
				<div id="emiNavi10900"  class="paginate"> 
				</div>
			</section>
			</div>
			<div class="pfooter">
				<button type="button" class="white" id="emiViewPopClose10900">닫기</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	
	<section>
		<div class="page_nav">
			<h1>행사 및 매장 현황</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>활동관리<span class="clamp fa"></span>행사 및 매장 현황
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchEventName10900" type="text" placeholder="행사명">
						<label for="searchEventName10900"><i class="fa fa-home"></i></label> 
					</span>
					<span>
						<input class="input-icon" id="emmSearchStartDate10900" type="text" readonly="readonly" placeholder="날짜선택" width="130px" value="">
						<label for="emmSearchStartDate10900"><i class="fa fa-calendar"></i></label>
					</span> ~
					<span>
						<input class="input-icon" id="emmSearchEndDate10900" type="text" readonly="readonly" placeholder="날짜선택" value="">
						<label for="emmSearchEndDate10900"><i class="fa fa-calendar"></i></label>
					</span>
				</div>
			<div class="btn_gup">
				<button class="red" id="emmSearchBtn10900" >조회</button>
				<button class="gray" id="emmSearchResetBtn10900" >초기화</button>
			</div>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="행사 및 매장 현황 리스트" class="tbl_col">
			<caption>
				행사 및 매장 현황 목록
			</caption>
			<colgroup>
				<col width="5%"  >
				<col width="50%"  >
				<col width="15%"  >
				<col width="15%"  >
				<col width="15%"  >
			</colgroup>
			<thead>
				<tr>
					<th scope="col">No</th>
					<th scope="col">행사 및 매장 현황 목록</th>
					<th scope="col">이벤트 시작일자</th>
					<th scope="col">이벤트 종료일자</th>
					<th scope="col">등록일</th>
				</tr>
			</thead>
			<tbody id="emmTbody10900" >
			</tbody>
		</table>
		<div id="emmNavi10900"  class="paginate"> 
		</div>
	</section>
	<!-- //UI Object -->
</article>
</body>
</html>
