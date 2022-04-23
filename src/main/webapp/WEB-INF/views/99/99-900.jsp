<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="/resources/smarteditor/js/service/HuskyEZCreator.js"></script> 
<script type="text/javascript" src="<%=jsPath%>/routine/99/99-900.js"></script>
<script type="text/javascript">
		
</script>
<style type="text/css">


</style>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="emmSavePop99900" style="display:none ;">
		<div class="popup" style="width: 1000px;">
			<div class="phead">
				<span id="emmInsPop">행사 등록</span>
				<a id="emmSavePopCloseX99900" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<table class="tbl_col">
					<colgroup>
						<col width="20%">
						<col width="*">
					</colgroup>
					<tr>
                  <th>제목</th>
                  <td>
                    <input id="emmTitle" type="text" style="width: 100%;" />
                  </td>
                </tr>
                <tr>
                	<th>이벤트 진행일자</th>
                	<td class="txt_left">
                		<input type="text" class="input-basic" id="emmDateFrom99900" size="10" style="width: 90px;" readonly="readonly">
						<span class="wave"></span>
						<input type="text" class="input-basic" id="emmDateTo99900" size="10" style="width: 90px;" readonly="readonly">
                	</td>
                <tr>
                  <th>내용</th>
                  <td><textarea id="emmContents" class="content-td" rows="15" cols="" style="width: 100%;"></textarea></td>
                </tr>
				</table>
			</div>
			<div class="pfooter">
				<button type="button" class="red auth-insert auth-update" id="emmSave">저장</button>
				<button type="button" class="red auth-insert auth-update" id="emmUptBtn" style="display:none">수정</button>
				<button type="button" class="white" id="emmSavePopClose99900">닫기</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	
	<div id="emmViewPop99900" style="display:none ;">
		<div class="popup" style="width: 1000px;">
			<div class="phead">
				<span>행사 보기</span>
				<a id="emmViewPopCloseX99900" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<table class="tbl_col">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr>
                  <th>제목</th>
                  <td id="emmViewTitle"></td>
                </tr>
                <tr>
                  <th>이벤트 진행일자</th>
                  <td class="txt_left" id="emmViewEmmDate99900"></td>
                </tr>
                <tr>
                  <th>내용</th>
                  <td id="emmViewContents" class="content-td"></td>
                </tr>
				</table>
			</div>
			<div class="pfooter">
				<button type="button" class="red auth-update" id="emmUptBtn99900">수정</button>
	            <button type="button"  class="gray auth-del" id="emmDel99900">삭제</button>
				<button type="button" class="white" id="emmViewPopClose99900">닫기</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	
	<section>
		<div class="page_nav">
			<h1>행사 및 매장 현황 관리</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>기준정보<span class="clamp fa"></span>행사 및 매장 현황 관리
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchEventName99900" type="text" placeholder="행사명">
						<label for="searchEventName99900"><i class="fa fa-home"></i></label> 
					</span>
				</div>
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchStartDate99900" type="text" readonly="readonly" placeholder="날짜선택" width="130px" value="">
						<label for="searchStartDate99900"><i class="fa fa-calendar"></i></label>
					</span> ~
					<span>
						<input class="input-icon" id="searchEndDate99900" type="text" readonly="readonly" placeholder="날짜선택" value="">
						<label for="searchEndDate99900"><i class="fa fa-calendar"></i></label>
					</span>
				</div>
			<div class="btn_gup">
				<button class="red" id="emmSearchBtn" >조회</button>
				<button class="gray" id="emmSearchResetBtn" >초기화</button>
				<button class="skyblue" id="emmCreateBtn" >생성</button>
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
			<tbody id="emmTbody99900" >
			</tbody>
		</table>
		<div id="emmNavi99900"  class="paginate"> 
		</div>
	</section>
	<!-- //UI Object -->
</article>
</body>
</html>
