var emmPg = 1;

/**
 * @함수명: ready
 * @작성일: 2017. 12. 00
 * @작성자: 
 * @설명: 로딩 및 Event - 행사 및 매장 현황 관리
 * @param 
 */
$(document).ready(function(){
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#emmSavePop99900").instancePopUp();
	$("#emmViewPop99900").instancePopUp();
	
	$("#searchStartDate99900").winCal(baseOptions);
	$("#searchEndDate99900").winCal(baseOptions);
	
	$("#emmDateFrom99900").winCal(baseOptions);
	$("#emmDateTo99900").winCal(baseOptions);
	
	// 이벤트 등록
	fnSetEventComponent();
	
	fnGetEventList(emmPg);
});


/**
 * @함수명: fnSetEventComponent
 * @작성일: 2017. 12. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	// 닫기버튼
	$("#emmSavePopCloseX99900, #emmSavePopClose99900").click(function(){
		$("#emmSavePop99900").hide();
	});
	$("#emmViewPopCloseX99900, #emmViewPopClose99900").click(function(){
		$("#emmViewPop99900").hide();
	});
	$("#emmSearchBtn").click(function(){
		fnGetEventList("1");
	});
	$("#emmSearchResetBtn").click(function(){
		$("#searchEventName99900").val("");	//검색어
		$("#searchStartDate99900").val("");	//검색어
		$("#searchEndDate99900").val("");	//검색어
		fnGetEventList("1");
	});
	
	//생성 버튼 눌렀을때
	$("#emmCreateBtn").click(function(){
		
		$("#emmTitle").val('');
		$("#emmDateFrom99900").val('');
		$("#emmDateTo99900").val('');
		$("#emmContents").val('');
		$("#emmSavePop99900").show();
		
		// 에디터
		if(oEditors99900 == undefined) {
			fnInitSmartEditor();
		}else{
			oEditors99900.getById["emmContents"].exec("SET_IR", [""]); //내용초기화
		}
	});
	
	//저장
	$("#emmSave").click(function(){
		fnSaveEmm();
	});
	
	//수정창
	$("#emmUptBtn99900").click(function(){
		fnEmmUptPop($(this).val());
	});
	//수정
	$("#emmUptBtn").click(function(){
		fnEmmUpt($(this).val());
	});
	
	//삭제
	$("#emmDel99900").click(function(){
		fnEmmDel($(this).val());
	});
}

/**
 * @함수명: fnGetEventList
 * @작성일: 2017. 12. 00
 * @작성자: 
 * @설명: 목록 조회
 * @param 
 */
function fnGetEventList(currPg) {
	var title  = $("#searchEventName99900").val();	//검색어
	var startDate  = $("#searchStartDate99900").val();	//검색어
	var endDate  = $("#searchEndDate99900").val();	//검색어
	if(startDate.dateReplace() > endDate.dateReplace()){
		alert("시작일이 종료일보다 클수 없습니다.");
		 return;
	}
	var params = {
			"title"      : title,
			"startDate" : startDate.dateReplace(),
			"endDate"   : endDate.dateReplace()
	}; 
	var rowSize = 15;			
	var fnName = "fnGetEventList";
	$.ajax({
		url : "/eventMonth/emmList",
		type : "POST",
		dataType : "json",
		data:{"fnName":fnName
			, "params":params
			, "rowSize":rowSize
			, "currPg":currPg
		},
		cache : false,
		success : function(data) {
			var html = '';
			var emmLen = data.emmList.length;
			var fristNo = data.fristNo;
			
			if (emmLen > 0) {
				for (var i = 0; i < emmLen; i++) {
					var emm = data.emmList[i];
					html+='<tr>';
					html+='	<td>'+(fristNo-i)+'</td>';
					html+='<td class="txt_left">';
					html+='	<a class="emm-view" data-emm_innb="'+emm.emm_innb+'" href="#">'+emm.title+'</a>';
					html+='</td>';
					html+='	<td>'+emm.emm_date_from+'</td>';
					html+='	<td>'+emm.emm_date_to+'</td>';
					html+='	<td>'+emm.regist_de+'</td>';
					html+='</tr>';
				}
			} else {
				html += '<tr><td colspan="5">조회된 데이터가 없습니다.</td></tr>';
			}
			$("#emmTbody99900").html(html);
			$("#emmTbody99900").find(".emm-view").click(function(){
				fnViewEmm($(this).data("emm_innb"));
			});
			
			$("#emmNavi99900").html(data.navi);
		}
	});
}

// 에디터
var oEditors99900;
var sLang = "ko_KR";
function fnInitSmartEditor(content){
	oEditors99900 = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors99900,
		elPlaceHolder: "emmContents",
		sSkinURI: "/resources/smarteditor/SmartEditor2Skin.html",	
		htParams : {
			bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			//bSkipXssFilter : true,		// client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
			//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
			fOnBeforeUnload : function(){
//				alert("완료!");
			},
			I18N_LOCALE : sLang
		}, //boolean
		fOnAppLoad : function(){
			if(typeof(content) != 'undefined'){
				oEditors99900.getById["emmContents"].exec("SET_IR", [content]);
			}
		},
		fCreator: "createSEditor2"
	});
};

// 저장
function fnSaveEmm(){
	var emmTitle = $.trim($("#emmTitle").val());
	if(emmTitle == ""){
		alert("제목을 입력해주세요.");
		$("#emmTitle").focus();
		return;
	}else if(emmTitle.length>40){
		alert("제목의 최대글자수(40자)를 초과하였습니다.");
		$("#emmTitle").focus();
		return;
	}
	
	var emmDateFrom  = $("#emmDateFrom99900").val();	// 이벤트 시작날짜
	var emmDateTo  = $("#emmDateTo99900").val();	// 이벤트 종료날짜
	if(emmDateFrom.dateReplace() > emmDateTo.dateReplace()){
		alert("시작일이 종료일보다 클수 없습니다.");
		 return;
	}
	if(emmDateFrom == "" || emmDateTo == ""){
		alert("이벤트 진행일자를 입력해주세요.");
		$("#emmDateFrom99900").focus();
		return;
	}

	oEditors99900.getById["emmContents"].exec("UPDATE_CONTENTS_FIELD", []);
	var emmContents = $.trim($("#emmContents").val());
	if(emmContents == "" || emmContents == "<p><br></p>"){
		alert("내용을 입력해주세요.");
		$("#emmContents").focus();
		return;
	}
	
	var url = "/eventMonth/emmSave";
	var param = {
			"emmTitle" : emmTitle
			, "emmContents" : emmContents
			, "emmDateFrom" : emmDateFrom.dateReplace()
			, "emmDateTo"   : emmDateTo.dateReplace()
	}
//	$.ajaxSettings.traditional = false;
	$.ajax({
		global: true,	
		url : url,
		data:param,
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(result) {
			if(result>0){
				alert("정상적으로 등록되었습니다.");
				$("#emmSavePop99900").hide();
				fnGetEventList("1");
			}else {
				alert("저장 실패했습니다.");
			}
		}
	});
}
	
// 상세보기
function fnViewEmm(emm_innb){
	$.ajax({
		url : "/eventMonth/emmView",
		data:{"emmInnb" : emm_innb},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(result) { 
			var emm = result.emmMap;
			$("#emmViewTitle").html(emm.title);
			$("#emmViewEmmDate99900").html(emm.emm_date_from+" ~ "+emm.emm_date_to);
			$("#emmViewContents").html(emm.contents);
			$("#emmUptBtn99900").val(emm.emm_innb);
			$("#emmUptBtn").val(emm.emm_innb);
			$("#emmDel99900").val(emm.emm_innb);
			$("#emmViewPop99900").show();
		}
	});
}

// 수정창
function fnEmmUptPop(emm_innb){
	$("#emmInsPop").text("행사 수정");
	
	$.ajax({
		url : "/eventMonth/emmView",
		data:{"emmInnb" : emm_innb},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(result) {
			$("#emmSave").hide();
			$("#emmUptBtn").show();
			var emm = result.emmMap;
			$("#emmTitle").val(emm.title);
			$("#emmDateFrom99900").val(emm.emm_date_from);
			$("#emmDateTo99900").val(emm.emm_date_to);
			$("#emmContents").val(emm.contents);
			
			// 에디터 수정창
			if(oEditors99900 == undefined) {
				fnInitSmartEditor(emm.contents);
			}else{
				oEditors99900.getById["emmContents"].exec("SET_IR", [emm.contents]); //내용초기화
			}
			
			$("#emmViewPop99900").hide();
			$("#emmSavePop99900").show();
		}
	});
}

// 수정
function fnEmmUpt(emm_innb){
	var emmTitle = $.trim($("#emmTitle").val());
	if(emmTitle == ""){
		alert("제목을 입력해주세요.");
		$("#emmTitle").focus();
		return;
	}
	
	var emmDateFrom  = $("#emmDateFrom99900").val();	// 이벤트 시작날짜
	var emmDateTo  = $("#emmDateTo99900").val();	// 이벤트 종료날짜
	if(emmDateFrom.dateReplace() > emmDateTo.dateReplace()){
		alert("시작일이 종료일보다 클수 없습니다.");
		 return;
	}
	if(emmDateFrom == "" || emmDateTo == ""){
		alert("이벤트 진행일자를 입력해주세요.");
		$("#emmDateFrom99900").focus();
		return;
	}

	oEditors99900.getById["emmContents"].exec("UPDATE_CONTENTS_FIELD", []);
	var emmContents = $.trim($("#emmContents").val());
	if(emmContents == "" || emmContents == "<p><br></p>"){
		alert("내용을 입력해주세요.");
		$("#emmContents").focus();
		return;
	}
	var url = "/eventMonth/emmUpt";
	var param = {
			"emmInnb" : emm_innb
			, "title" : emmTitle
			, "contents" : emmContents
	}
//	$.ajaxSettings.traditional = false;
	$.ajax({
		global: true,	
		url : url,
		data:param,
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(result) {
			if(result>0){
				alert("정상적으로 수정되었습니다.");
				$("#emmSavePop99900").hide();
				fnGetEventList("1");
			}else {
				alert("수정 실패했습니다.");
			}
		}
	});
}

// 삭제
function fnEmmDel(emm_innb){
	var url = "/eventMonth/emmDel";
	var param = {
			"emmInnb" : emm_innb
	}
	$.ajax({
		global: true,	
		url : url,
		data:param,
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(result) {
			if(result>0){
				alert("정상적으로 삭제되었습니다.");
				$("#emmViewPop99900").hide();
				fnGetEventList("1");
			}else {
				alert("삭제 실패했습니다.");
			}
		}
	});
}