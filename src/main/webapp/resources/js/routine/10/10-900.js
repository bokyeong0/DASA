var cvsPg = 1;
var evtId = "";

/**
 * @함수명: ready
 * @작성일: 2017. 12. 00
 * @작성자: 
 * @설명: 로딩 및 Event - 행사 및 매장 현황
 * @param 
 */
$(document).ready(function(){
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#emiViewPop10900").instancePopUp();
	
	$("#searchStartDate10900").winCal(baseOptions);
	$("#searchEndDate10900").winCal(baseOptions);
	$("#emmSearchStartDate10900").winCal(baseOptions);
	$("#emmSearchEndDate10900").winCal(baseOptions);
	
	// 이벤트 등록
	fnSetEventComponent();
	
	fnGetEventList(cvsPg);
	
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
	$("#emiViewPopCloseX10900, #emiViewPopClose10900").click(function(){
		$("#emiViewPop10900").hide();
	});
	
//	fnGetDisPlayArrSearchComboBox(); 	//지점명조회
	
	$("#searchOmCode10900").change(function(){		//지점명 클릭했을때 콤보박스 초기화
		if($(this).val() != ""){
			fnGetDisPlayArrCstmrGroupList($(this).val());
		}else{
			$("#searchCgCode10900").html("<option value='' selected='selected'>선택</option>");
		}
		$("#searchMeCode10900").html("<option value='' selected='selected'>선택</option>");
		$("#searchSmCode10900").html("<option value='' selected='selected'>선택</option>");
	});
	
	$("#searchCgCode10900").change(function(){
		fnGetDisPlayArrEntrpsList($(this).val());	//관리업체조회
		$("#searchSmCode10900").html("<option value='' selected='selected'>선택</option>");
	});
	
	$("#searchMeCode10900").change(function(){
		fnGetWorkingStoreList($(this).val());	// 매장명 조회
	});
	
	/* 메인화면 */
	$("#emmSearchBtn10900").click(function(){ 
		fnGetEventList("1");
	});
	$("#emmSearchResetBtn10900").click(function(){
		$("#searchEventName10900").val("");	//검색어
		$("#emmSearchStartDate10900").val("");	//검색어
		$("#emmSearchEndDate10900").val("");	//검색어
		
		fnGetEventList("1");
	});
	
	/* 이벤트별 페이지 */
	$("#emiSearchBtn").click(function(){
		fnGetEventSelectList("1", evtId);
	});
	$("#emiSearchResetBtn").click(function(){
		$("#searchOmCode10900").val("");	//검색어
		$("#searchCgCode10900").val("");	//검색어
		$("#searchMeCode10900").val("");	//검색어
		$("#searchSmCode10900").val("");	//검색어
		$("#searchEventName10900").val("");	//검색어
		$("#searchStartDate10900").val("");	//검색어
		$("#searchEndDate10900").val("");	//검색어
		
		fnGetEventSelectList("1", evtId);
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
	var title  = $("#searchEventName10900").val();	//검색어
	var startDate  = $("#emmSearchStartDate10900").val();	//검색어
	var endDate  = $("#emmSearchEndDate10900").val();	//검색어
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
			$("#emmTbody10900").html(html);
			$("#emmTbody10900").find(".emm-view").click(function(){
				$("#searchCgCode10900").html("<option value='' selected='selected'>선택</option>");
				$("#searchMeCode10900").html("<option value='' selected='selected'>선택</option>");
				$("#searchSmCode10900").html("<option value='' selected='selected'>선택</option>");
				evtId = $(this).data("emm_innb");
				fnGetDisPlayArrSearchComboBox(); 	//지점명조회
				fnGetEventSelectList("1", $(this).data("emm_innb"));
			});
			
			$("#emmNavi10900").html(data.navi);
		}
	});
}

// 지점명조회
function fnGetDisPlayArrSearchComboBox(){
	$.ajax({
		url : "/organization/auth",
	    data:{"om_code":"", "om_orgnzt_se": 1},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
			var listHtml = ""; //"<option value='' selected='selected'>선택</option>";
			if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			listHtml += "<option value ='"+res.om_code+"'>"+ res.om_nm +"</option>";
	    		}
			}	
			$("#searchOmCode10900").html(listHtml);
			fnGetDisPlayArrCstmrGroupList($("#searchOmCode10900").val());
	    }
	});
}

// 고객그룹 조회
function fnGetDisPlayArrCstmrGroupList(omCode){
	$.ajax({
		url : "/store/cstmrGroupList",
//		data:{"omCode":omCode},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	
			var listHtml = "<option value='' selected='selected'>선택</option>";
	
			if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					var cstmrGroupVo = data.result[i];
	        		listHtml += "<option value='"+cstmrGroupVo.cg_code+"'>"+cstmrGroupVo.cg_nm + "</option>";
	        	}
			}	
			$("#searchCgCode10900").html(listHtml);
	    }
	});
}

// 관리업체 조회
function fnGetDisPlayArrEntrpsList(cgCode){
	$.ajax({
		url : "/eventMonth/getWorkingStoreList",
		data:{"cgCode":cgCode, "omCode": $("#searchOmCode10900").val()},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
		    	
	    	var listHtml = "<option value='' selected='selected'>선택</option>";
//	    	if(auth_flag == 3){ //고정
//				listHtml = "";
//			}
			if(data!=null && data.resultList.length > 0){
				for(var i=0; i<data.resultList.length; i++){
					var resultMap = data.resultList[i];     
	        		listHtml += "<option value='"+resultMap.me_code+"'>"+resultMap.me_nm + "</option>";
	        	}
				
			}	
		$("#searchMeCode10900").html(listHtml);
//		if(auth_flag == 3){ //고정
//			fnGetWorkingStoreList($("#searchMeCode10900").val());
//		}
	    }
	});
}

// 매장명
function fnGetWorkingStoreList(meCode) {
	$.ajax({
		url : "/eventMonth/getWorkingStoreList",
		data:{"meCode":meCode, "omCode": $("#searchOmCode10900").val(), "cgCode": $("#searchCgCode10900").val()},
	    dataType : "json",
	    type : "POST",
	    async : true,
	    cache : false,
	    success : function(data) {
	    	var html = "<option value='' selected='selected'>선택</option>";
//	    	if(auth_flag == 3){ //고정
//				html = "";
//			}
			if (data.resultList.length > 0) {
				for (var i = 0; i < data.resultList.length; i++) {
					var resultMap = data.resultList[i];
					html += "<option value='"+resultMap.sm_code+"'>"+resultMap.sm_nm + "</option>";
	        	}
			} 
			
			$("#searchSmCode10900").html(html);
	    }
	});
}
/**
 * @함수명: fnGetEventList
 * @작성일: 2017. 12. 00
 * @작성자: 
 * @설명: 이벤트별 목록 조회
 * @param 
 */
function fnGetEventSelectList(currPg, emm_innb) {
	var emmInnb = emm_innb	//검색어 : 이벤트 번호
	var omCode = $("#searchOmCode10900").val();	//검색어 : 지점코드
	var cgCode = $("#searchCgCode10900").val();	//검색어 : 고객그룹 코드
	var meCode = $("#searchMeCode10900").val();	//검색어 : 관리업체 코드
	var smCode = $("#searchSmCode10900").val();	//검색어 : 매장코드
//	var title  = $("#searchEventName10900").val();	//검색어 : 제목
	var startDate  = $("#searchStartDate10900").val();	//검색어 : 시작날짜
	var endDate  = $("#searchEndDate10900").val();	//검색어 : 종료날짜
	if(startDate.dateReplace() > endDate.dateReplace()){
		alert("시작일이 종료일보다 클수 없습니다.");
		 return;
	}
	
	var params = {
			"emmInnb" : emmInnb,
			"omCode" : omCode,
			"cgCode" : cgCode,
			"meCode" : meCode,
			"smCode" : smCode,
			"startDate" : startDate.dateReplace(),
			"endDate"   : endDate.dateReplace(),
			"loginCpCd" : login_cp_cd
	}; 
	var rowSize = 15;			
	var fnName = "fnGetEventSelectList";
	$.ajax({
		url : "/eventMonth/emiList",
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
			var emiLen = data.emiList.length;
			var fristNo = data.fristNo;
			
			if (emiLen > 0) {
				for (var i = 0; i < emiLen; i++) {
					var emi = data.emiList[i];
					html+='<tr>';
					html+='	<td>'+(fristNo-i)+'</td>';
//					html+='<td class="txt_left">';
//					html+='	<a class="emi-view" data-emi_innb="'+emi.emi_innb+'" href="#">'+emi.title+'</a>';
//					html+='</td>';
					html+='	<td>'+emi.title+'</td>';
					html+='	<td>'+emi.om_nm+'</td>';
					html+='	<td>'+emi.cg_nm+'</td>';
					html+='	<td>'+emi.me_nm+'</td>';
					html+='	<td>'+emi.sm_nm+'</td>';
					html+="	<td><img src='"+emi.emi_img_url+"'/></td>";
					html+='	<td>'+emi.updt_de+'</td>';
					html+='</tr>';
				}
			} else {
				html += '<tr><td colspan="8">조회된 데이터가 없습니다.</td></tr>';
			}
			$("#emiTbody10900").html(html);
//			$("#emmTbody99900").find(".emi-view").click(function(){
//				fnViewEmm($(this).data("emm_innb"));
//			});
			
			$("#emiNavi10900").html(data.navi);
			$("#emiViewPop10900").show();
		}
	});
}

