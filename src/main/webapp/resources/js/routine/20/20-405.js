/**
 * @함수명: onLoad
 * @작성일: 2018. 03
 * @작성자: k2s
 * @설명: 로딩 및 Event
 * @param 
 */
var displyTrtPg   = 1;
var displayRowCnt = 100;  //한페이지에 표시할 행수

var trtGrid20405;          //AUIGrid 생성 후 반환 ID
var columnLayout;          //AUIGrid 칼럼 설정
var footerLayout           //AUIGrid 푸터 설정

var strFileName  = "";     //엑셀파일명
var strSheetName = "";     //엑셀파일시트명
var sortingInfo  = "";     //소팅 정보 보관 객체

$(document).ready(function(){
	$("#searchSmOdrTo20405").val("4");	//차수
	
	fnGetDisPlayTrtSearchComboBox();    //지점명조회
	fnGetDisPlayTrtCstmrGroupList();    //고객그룹

	$("#diplySearchBtn20405").click(function(){
		fnGetDiaryTrtItemGridList(1);
	});
	
	//엑셀다운로드
	$("#excelDownBtn20405").click(function(){
		
		if(confirm("데이터가 많아 최대 5분까지 소요 될수 있습니다.\n계속 진행하시겠습니까? ")){
			fnExcelDownload();  //엑셀파일다운로드
		}
	});
	
	//Grid엑셀다운로드
	$("#excelDownGridBtn20405").click(function(){
		fnGridExcelDownload();
	});
	
	//Grid(숨김컬럼제외)엑셀다운로드
	$("#excelDownGridAllBtn20405").click(function(){
		fnExportWithoutHiddenColumns();
	});		
	
	$("#searchOmCode20405").change(function(){		//지점명 클릭했을때 콤보박스 초기화
		fnGetDisPlayTrtCstmrGroupList();
		var listHtml = "<option value='' selected='selected'>선택</option>";
		$("#searchMeCode20405").html(listHtml);
	});
	
	$("#searchCgCode20405").change(function(){
		fnGetDisPlayTrtEntrpsList($(this).val());   //관리업체조회
	});
	
	$("#searchEmName20405").enterSearch("fnGetDiaryTrtItemGridList"); //엔터키 조회 이벤트
	
	//초기화
	$("#diplySearchResetBtn20405").click(function(){
		$("#searchOmCode20405").val("");    //지점
		$("#searchCgCode20405").val("");	//고객그룹
		$("#searchMeCode20405").val("");	//관리업체
		$("#searchEmName20405").val("");	//사원명
		$("#searchSmOdrFrom20405").val("");	//차수
		$("#searchSmOdrTo20405").val("4");	//차수
		$("#searchAddrCode120405").val("");	//시도
		$("#searchAddrCode220405").val("");	//구군
		$("input:checkbox[id=searchCodeAt20405]").attr("checked", false); //코드포함 엑셀다운로드(고객그룹,관리업체)		
		fnGetToday20405();                  //조회 연월 set
		fnGetDiaryTrtItemGridList(1);
	});
	
	fnGetCommonCodeComboBox20405("0000000107",$("#searchAddrCode120405"));
	
	$("#searchAddrCode120405").change(function(){
		fnGetCommonCodeComboBox20405($(this).val(),$("#searchAddrCode220405"));
	});
	
	//배치 수행
	$("#diplyBatchBtn20405").click(function(){
		if(confirm("데이터가 많아 최대 5분까지 소요될수 있습니다.\n계속진행하시겠습니까?")){
			fnGetBatchTrt();
		}
	});
	
	/* 조회 연월 추가 start!!! A20161230 kks */
	fnGetToday20405();   // 조회 연월 set
	/* 조회 연월 추가 end!!! A20161230 kks */
	
//	fnGetDiaryTrtItemGridList(displyTrtPg);	 // 자동 조회 displyTrtPg: 1
});//ready

function fnGetBatchTrt(){

	$.ajax({
		url : "/display/bach_trt",
		type : "POST",
		dataType : "json",
		cache : false,
		global:true,
		success : function(data) {
			if(data < 1 ){
				alert("실패 하였습니다.");
			}else{
				alert("완료되었습니다.");
			}
		}
	});
}//fnGetBatchTrt

function fnGetCommonCodeComboBox20405(c_parent_code, target){
	
	$.ajax({
		url : "/code/codeComboBox",
		data:{"c_parent_code":c_parent_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			var listHtml = "<option value='' selected='selected'>선택</option>";
			if(data!=null && data.length > 0){
				for(var i=0; i<data.length; i++){
					var codeVo = data[i];     
	        		listHtml += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
	        	}
			}else{
				
			}	
			target.html(listHtml);
	    }
	});
}//fnGetCommonCodeComboBox20405

function fnGetDisPlayTrtEntrpsList(cg_code){
	
	$.ajax({
		url : "/store/manageEntrpsList",
		data:{"cg_code":cg_code, "om_code": $("#searchOmCode20405").val()},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
		    	
			var listHtml = "<option value='' selected='selected'>선택</option>";
			if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					var manageEntrpsVo = data.result[i];     
	        		listHtml += "<option value='"+manageEntrpsVo.me_code+"'>"+manageEntrpsVo.me_nm + "</option>";
	        	}
				
			}	
		$("#searchMeCode20405").html(listHtml);
	    
	    }
	});
}//fnGetDisPlayTrtEntrpsList

function fnGetDisPlayTrtCstmrGroupList(){
	$.ajax({
		url : "/store/cstmrGroupList",
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
			$("#searchCgCode20405").html(listHtml);
	    
	    }
	});
}//fnGetDisPlayTrtCstmrGroupList

function fnGetDisPlayTrtSearchComboBox(){
	$.ajax({
		url : "/organization/auth",
	    data:{"om_code":"", "om_orgnzt_se": 1},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
			var listHtml = "";
			if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			listHtml += "<option value ='"+res.om_code+"'>"+ res.om_nm +"</option>";
	    		}
			}	
				$("#searchOmCode20405").html(listHtml);
	    }
	});
}//fnGetDisPlayTrtSearchComboBox

function fnGetDiaryTrtItemGridList(currPg){
	
	displyTrtPg    = currPg;
	displayRowCnt  = $("#searchRowCnt20405").val();    //한페이지에 몇개씩 보기	
	var om_code    = $("#searchOmCode20405").val();    //지점
	var cg_code    = $("#searchCgCode20405").val();	   //고객그룹
	var me_code    = $("#searchMeCode20405").val();	   //관리업체
	var em_nm      = $("#searchEmName20405").val();	   //사원명
	var sm_odr1    = $("#searchSmOdrFrom20405").val(); //차수
	var sm_odr2    = $("#searchSmOdrTo20405").val();   //차수
	var sm_area1   = $("#searchAddrCode120405").val(); //시도
	var sm_area2   = $("#searchAddrCode220405").val(); //구군  M20161102 k2s 변경 필요함 #searchAddrCode120405 -> #searchAddrCode220405
	var da_date    = $("#yy_mm_20405").val();          //연월 A20161230 kks

	if(sm_odr1 > sm_odr2) {
		alert("앞의 차수가 뒤의 차수 보다 클 수는 없습니다.");
		return;
	}
	var params = { "om_code"    : om_code
				  ,"cg_code"    : cg_code
				  ,"me_code"    : me_code
				  ,"em_nm"      : em_nm
				  ,"sm_odr1"    : sm_odr1
				  ,"sm_odr2"    : sm_odr2
				  ,"sm_area1"   : sm_area1
				  ,"sm_area2"   : sm_area2
				  ,"da_date"    : da_date          //A20161230 kks
				};
	var rowSize = displayRowCnt;                   //표시할 행수
	var fnName = "fnGetDiaryTrtItemGridList";      //페이징처리 함수명
	
	// 그리드(trtGrid20405) 초기화
	trtGrid20405 = AUIGrid.destroy("#trtGrid20405", [], {});	
	
	$.ajax({
		url : "/display/trtGridList",
		type : "POST",
		data : {
			fnName : fnName,
			params : params,
			rowSize : rowSize,
			currPg : currPg
		},
		dataType : "json",
		global:true,
		cache : false,
		success : function(data) {
			
			var headerArr = data.headerArr;               //헤더Data
			createColumnLayout(headerArr);                //헤더 레이아웃 set
			
			var bodyArr = data.bodyArr;                   //본문Data
			createGridData(bodyArr);           			  //그리드 생성
			
			var aaa = AUIGrid.getFooterData(trtGrid20405);
			var bbb = AUIGrid.getRowCount(trtGrid20405);
			
			$("#displayPageNavi20405").html(data.navi);
		}, 
		error : function() {
			// 그리드(trtGrid20405) 초기화
			trtGrid20405 = AUIGrid.destroy("#trtGrid20405", [], {});
		}
	});
}//fnGetDiaryTrtItemGridList

//1.컬럼 레이아웃을 생성
function createColumnLayout(headerData){

	var headerList = headerData;
	var headerVo1;
	var gridHeader = [];
	var gridFooter = [{labelText     :"%"
				     ,positionField :"#base"
				     },{labelText   :"취급률"
			         ,positionField :"em_nm"
				     }];
	
	if (headerList.length < 1) {alert("조회된 데이터가 없습니다."); return;}  //조회값의 길이가 구조적으로 최소 2개 이상임
	
	//동적으로 아이템이 변경되므로 String형태가 아닌 Object 형태로 children에 넣을 속성들을 만듬
	$target     = null;     //객체공유 용도
	var sVal    = null;     //비교용도
	var sText   = [];       //그룹핑 합계 출력

	headerVo1 = headerList[0]; //大카테고리 항목
	
	for(var key in headerVo1) {
		
		var headerEl1 = {};
		var footerEl  = {};
		
		headerEl1.headerText = headerVo1[key];  //headerText 생성
		headerEl1.dataField  = key;             //dataField 생성
				
		//화면 숨김 컬럼 정의
		if (key=="da_innb"       ||key=="da_date" ||key=="cg_code" ||key=="me_code" ||key=="sm_area1_nm" 
			||key=="sm_area2_nm" ||key=="base_de" ||key=="em_no") {
			headerEl1.visible = false;
		}
		if (key=="me_nm" || key=="sm_nm") {
			headerEl1.style = "aui-grid-my-data";
		}
		if (key.indexOf("ent") < 0) {
			headerEl1.filter = {showIcon : true};
		}
		gridHeader.push(headerEl1);         //생성된 headerText와 dataField를 넣어줌
		/*
		headMap01.put("om_nm"                   ,"지점명");
		headMap01.put("da_innb"                 ,"식별자");
		headMap01.put("da_date"                 ,"적재일자");
		headMap01.put("cg_code"                 ,"고객그룹코드");
		headMap01.put("cg_nm"                   ,"고객그룹명");
		headMap01.put("me_code"                 ,"관리업체코드");
		headMap01.put("me_nm"                   ,"관리업체명");  
		headMap01.put("sm_code"                 ,"매장코드");
		headMap01.put("sm_nm"                   ,"매장명");  
		headMap01.put("em_nm"                   ,"사원명");  
		headMap01.put("em_no"                   ,"사원번호");
		headMap01.put("sm_odr"                  ,"차수"); 
		headMap01.put("base_de"                 ,"기준일자");
		headMap01.put("sm_area1_nm"             ,"권역1");
		headMap01.put("sm_area2_nm"             ,"권역2");			
		*/
		if (key.indexOf("ent") >= 0) {
			footerEl.dataField     = key;
			footerEl.positionField = key;
			footerEl.operation     = "AVG";
			footerEl.formatString  = "#,##0.##";
			footerEl.style         = "aui-grid-my-footer-sum-total";
			footerEl.postfix       = "%";
			footerEl.labelFunction = function(value, columnValues, footerValues) {
				var newValue = value *100;
				return newValue;
			};
			gridFooter.push(footerEl);          //footer set
			sText.push(key);                    //groupingSummary dataFields Create
		}
		
   			
	}//endfor in..
	
	//컬럼레이아웃 생성
	columnLayout = gridHeader;
	
	//푸터 설정
	footerLayout = gridFooter;

	//AUIGrid 그리드를 생성
	createAUIGrid(columnLayout, sText);

}//createColumnLayout

//2.AUIGrid 를 생성
function createAUIGrid(columnLayout, sText) {
	
	var auiGridProps = {editable             :false                      //에디터기능 사용
			           ,enableFilter         :true                       //필터기능 사용
			           ,selectionMode        :"multipleCells"            //
				       ,fixedColumnCount     :13                         //고정칼럼 카운트 지정
				   	   ,showFooter           :true                       //푸터 보이게 설정
				   	   ,useGroupingPanel     :true                       //그룹핑매널 사용
				   	   ,groupingFields       :["cg_nm"]                  //초기 cg_nm [고객그룹] 그룹핑 M20180226 k2s
 	                   ,groupingSummary      :{dataFields : sText}       //그룹핑 후 합계필드를 출력하도록 설정
// 	                   ,groupingSummary      :{dataFields : sText, labelTexts : ["전체 합계", "부분 합계", "_$except" ]}       //그룹핑 후 합계필드를 출력하도록 설정
	                   ,displayTreeOpen      :true                       //그룹핑 트리 열기
	                   ,showBranchOnGrouping :false                      //브랜치에 해당되는 행 출력 여부
	                   ,enableCellMerge      :true                       //셀병합 여부
	                   ,enableColumnResize   :true                       //칼럼 리사아징
                       ,rowStyleFunction     : function(rowIndex, item) {if(item._$isGroupSumField) { // 그룹핑으로 만들어진 합계 필드인지 여부
													                   			// 그룹핑을 더 많은 필드로 하여 depth 가 많아진 경우는 그에 맞게 스타일을 정의하십시오.
													                   			// 현재 3개의 스타일이 기본으로 정의됨.(AUIGrid_style.css)
													                   			switch(item._$depth) {  // 계층형의 depth 비교 연산
													                   			case 2:
													                   				return "aui-grid-row-depth1-style";
													                   			case 3:
													                   				return "aui-grid-row-depth2-style";
													                   			case 4:
													                   				return "aui-grid-row-depth3-style";
													                   			default:
													                   				return "aui-grid-row-depth-default-style";
													                   			}
													                   		}
													                   		
													                   		return null;
													                   	}	                   
	};

	//실제로 #trtGrid20405 에 그리드 생성
	trtGrid20405 = AUIGrid.create("#trtGrid20405", columnLayout, auiGridProps);
	
	//그리드 ready 이벤트 바인딩
	AUIGrid.bind(trtGrid20405, "ready", function(event) {

		//최초에 정렬된 채로 그리드 출력 시킴.
		AUIGrid.setSorting(trtGrid20405, [ {
				dataField : sText, sortType : -1
			}]);
	});

	//정렬 이벤트 바인딩
	AUIGrid.bind(trtGrid20405, "sorting", function(event) {

		//소팅 정보 보관.
		sortingInfo = event.sortingFields;
	});

	//그룹핑  이벤트  바인딩
	AUIGrid.bind(trtGrid20405, "grouping", function(event) {
		// 보관된 소팅 정보로 그룹핑 후 소팅 실시함
		if(typeof sortingInfo != "undefined") {
			AUIGrid.setSorting(trtGrid20405, sortingInfo);
		}
	});	
	
	//필터링 이벤트 바인딩
	AUIGrid.bind(trtGrid20405, "filtering", function(event) {
		var filterInfo = event.filterCache;
	});	
	
	//푸터 객체 세팅
	AUIGrid.setFooter(trtGrid20405, footerLayout);
};//createAUIGrid

//3.조회한 그리드 데이타를 생성
function createGridData(bodyData){	   

	var gridData = bodyData;
	
//	AUIGrid.showAjaxLoader(myGridID);     //ajax 요청 전 그리드에 로더 표시(AUIGrid ajax를 이용하는경우)        
//	AUIGrid.removeAjaxLoader(myGridID);   //ajax 요청 후 로더 제거(AUIGrid ajax를 이용하는경우)
	
	if(gridData.length > 0){
		AUIGrid.setGridData(trtGrid20405, gridData);  // 그리드에 데이터 세팅 data 는 JSON 을 파싱한 Array-Object 입니다.	
		
	}else{
		AUIGrid.setGridData(trtGrid20405, []);        // data가 없으면 no data 노출
	}
}//createGridData

/* fnGetToday20405() 조회 연월 set
 A20161230 kks*/
function fnGetToday20405(){

	var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);

    var chan_val = year + '-' + mon;
    
    $('#yy_mm_20405').val(chan_val);

	$("#yy_mm_20405").winCal(
			{format: "yyyy-mm", 
			startView: 3, 
			minView: 3, 
			maxView: 3});

    return true;
}//fnGetToday20405

//엑셀다운로드
function fnExcelDownload() {
	
	var om_code  = $("#searchOmCode20405").val(); 		//지점
	var cg_code  = $("#searchCgCode20405").val();		//고객그룹
	var me_code  = $("#searchMeCode20405").val();		//관리업체
	var em_nm    = $("#searchEmName20405").val();		//사원명
	var sm_odr1  = $("#searchSmOdrFrom20405").val();	//차수
	var sm_odr2  = $("#searchSmOdrTo20405").val();		//차수
	var sm_area1 = $("#searchAddrCode120405").val();	//시도
	var sm_area2 = $("#searchAddrCode120405").val();	//구군
	var da_date  = $("#yy_mm_20405").val();             //연월
	
	var code_at = ""; //A20171226 k2s 체크값
	
	if("checked" ==  $("input:checkbox[id=searchCodeAt20405]").attr("checked")) { 
		code_at = "Y";
	}
//	console.log("code_at: ",code_at);			
	
	location.href="/display/trtexcel?"    +
					"om_code="+om_code    +
					"&cg_code="+cg_code   +
					"&me_code="+me_code   +
					"&em_nm="+em_nm       +
					"&sm_odr1="+sm_odr1   +
					"&sm_odr2="+sm_odr2   +
					"&sm_area1="+sm_area1 +
					"&sm_area2="+sm_area2 +
					"&da_date="+da_date   + //A20161230 kks
					"&code_at="+code_at     //A20171226 k2s 코드포함 여부 추가							
					;
}//fnExcelDownload

//Grid 엑셀 내보내기
function fnGridExcelDownload() {
	var strFileName = "관리업체별 취급률현황";
	
	//FileSaver.js 로 로컬 다운로드가능 여부 확인
	if(!AUIGrid.isAvailableLocalDownload(trtGrid20405)) {
		alert("로컬 다운로드 불가능한 브라우저 입니다. \n서버 사이드로 전송하여 다운로드 처리하십시오.");
		return;
	}
	
	var rowCount = AUIGrid.getRowCount(trtGrid20405);
	
	var excelProps = {fileName           : strFileName	             //file명
			         ,sheetName          : strFileName+"(코드포함)"  //sheet명
			         ,fixedColumnCount   : 17                        //엑셀 상의 고정 칼럼 
    };
	
	// 10만행 보다 크다면 CSV로 다운로드 처리
	if(rowCount >= 100000) {
		if(confirm("10만행을 엑셀 또는 CSV 로 내보내기 합니다.\n확인 클릭 - 엑셀로 내보내기\n취소 클릭 - CSV로 내보내기")) {
			AUIGrid.exportToXlsx(trtGrid20405, excelProps);
			
		} else {
			AUIGrid.exportToCsv(trtGrid20405, excelProps);
			
		}
	}else{ // 10만행 보다 작은 경우
		AUIGrid.exportToXlsx(trtGrid20405, excelProps);
	}	
}//fnGridExcelDownload

//숨겨진 칼럼은 엑셀에 포함시키지 않기
function fnExportWithoutHiddenColumns() {
	var omNm  = $("#searchOmCode20405 option:selected").text(); 		//지점
	
	if (omNm == "" || omNm == undefined || omNm == null || omNm == "지점") {
		omNm = "전체지점"
	}
	strFileName  = omNm + " 관리업체별 취급률현황";
	strSheetName = "관리업체별 취급률현황( " +omNm+" )";
	
	var excelProps = {fileName           : strFileName	
		             ,sheetName          : strSheetName
		             //exceptColumnFields: ["da_innb", "da_date", "cg_code", "me_code", "sm_area1_nm", "sm_area2_nm", "base_de", "em_no"] // 엑셀로 내보내지 않기
		             //현재 그리드의 히든 처리된 칼럼의 dataField 들 얻어 똑같이 동기화 시키기
		             ,exceptColumnFields : AUIGrid.getHiddenColumnDataFields(trtGrid20405) //숨김 컬럼 제외
			         ,fixedColumnCount   : 9                                           //엑셀 상의 고정 칼럼		    
					 ,headers            : [ {text : "", height:20} // 행 빈줄
					                       , {text : strFileName, height:40, style : { fontSize:30, textAlign:"left", fontWeight:"bold", underline:true, background:"#DAD9FF"}}
					                       , {text : "", height:20} // 행 빈줄
					                       , {text : "", height:5, style : { background:"#555555"}} // 빈줄 색깔 경계 만듬
										   ]			         
	};
	
	AUIGrid.exportToXlsx(trtGrid20405, excelProps);
}//fnExportWithoutHiddenColumns