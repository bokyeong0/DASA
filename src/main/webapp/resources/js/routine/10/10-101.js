/**
 * @프로그램명: 순방계획실적표
 * @작성일: 2018. 03
 * @작성자: k2s
 * @설명: 로딩 및 Event
 * @param 
 */

var planGrid10101;                                       //AUIGrid 생성 후 반환 ID
var columnLayout;                                        //AUIGrid 칼럼 설정
var footerLayout;                                        //AUIGrid 푸터 설정
var days  = ["일" ,"월" ,"화" ,"수" ,"목" ,"금" ,"토"];   //요일 설정

$(document).ready(function(){

	// 달력 이벤트
	$("#searchStartDate10101").winCal(
			{format: "yyyy-mm"
			,startView: 3 
			,minView: 3 
			,maxView: 3});

	// 소속가져오기
	fnGetCvsSearchComboBox("",1); 
	
	// 이벤트 등록
	fnSetEventComponent();	

	if(location.hostname == "127.0.0.1" || location.hostname == "localhost" || location.hostname == "27.125.31.149") {
		$("#searchStartDate10101").val("2017-06");	//조회일자
	} else {	
		$("#searchStartDate10101").val(fnGetToday10101());
	}
	
});//ready


function fnGetToday10101(){

	var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    var day = now.getDate();   // A20161129 k2s 일자
    var chan_val = year + '-' + mon;
    return chan_val;
}//fnGetToday10101

// cvs 체크리스트 헤더 조회
function fnGetCvsChkList(){	

	// 화면의 parameter 넘길 값
	var smNm  	  = $("#searchSmName10101").val();	  //매장명
	var emNm  	  = $("#searchEmName10101").val();	  //직원명
	var omCode    = $("#searchOmCode10101").val();	  //지점명
	var tmCode    = $("#searchTmCode10101").val();	  //팀명
	var searchYM  = $("#searchStartDate10101").val();  //시작일

	// 날짜 필수 체크
	if(searchYM == ""){
		alert("날짜는 필수 입력값 입니다.");
		return;
	}
	// 날짜 필수 체크
	/*
	if(omCode == ""){
		alert("지점을 선택하세요");
		return;
	}
	*/
	// 조회값
	var params = {"smNm"      : smNm
				 ,"emNm"      : emNm
				 ,"omCode"    : omCode
				 ,"tmCode"    : tmCode
				 ,"searchYM"  : searchYM.dateReplace()
		         };
	
	// 그리드 초기화
	planGrid10101 = AUIGrid.destroy("#planGrid10101", [], {});
	
	// ajax로 그리드 데이터 요청
	$.ajax({
		 url      : "/rndplan/rndPlanScheTable"
		,type     : "POST"
		,data     : {params : params}
		,dataType : "json"
		,global   : true
		,cache    : false
		,success  : function(data) {
			
			// 컬럼 레이아웃 생성
			createColumnLayout(data);
			
			// 그리드를 생성
			createGridData(data);
			
		},
		error : function() {
			// 그리드(planGrid10101) 초기화
			planGrid10101 = AUIGrid.destroy("#planGrid10101", [], {});
		}
	});
	
}//fnGetCvsChkList

//1.컬럼 레이아웃 생성
function createColumnLayout(data){
	
	var itemList = data.rndPlanScheTableVoList;
		
    columnLayout = [
     {dataField: "om_code"     ,headerText: "지점코드"       ,visible: false}
    ,{dataField: "om_nm"       ,headerText: "지점명"}        
    ,{dataField: "em_no"       ,headerText: "사원번호"       ,visible: false}
    ,{dataField: "em_nm"       ,headerText: "MD명"           ,width:   70}
    ,{dataField: "sm_code"     ,headerText: "매장코드"       ,visible: false}
    ,{dataField: "sm_nm"       ,headerText: "매장명"         ,width:   150, style: "aui-grid-my-data"}
    ,{dataField: "sm_odr"      ,headerText: "차수"           ,width:   50}
    ,{dataField: "me_code"     ,headerText: "공급처코드"     ,visible: false}
    ,{dataField: "me_nm"       ,headerText: "공급처명"       ,visible: true, width:   150, style: "aui-grid-my-data"}
    ,{dataField: "cm_code"     ,headerText: "회사코드"       ,visible: false}
    ,{dataField: "sm_sap_code" ,headerText: "SAP<br>매장코드",visible: false}
    ];
	
	var y = parseInt($("#searchStartDate10101").val().substring(0,4));
	var m = parseInt($("#searchStartDate10101").val().substring(5,7));	
	var lastDay = (new Date(y, m, 0)).getDate();                        //조회월의 마지막 일자
	console.log("searchStartDate10101 ",lastDay);
	
	var gridFooter = [{positionField :"#base", labelText :"∑"}
	                 ,{positionField :"sm_nm", style : "my-footer-style"
	                 , labelFunction : function(value, columnValues, footerValues) {
	                			return "일 순방 계획 :<br>일 순방 실적 :";}}
	                 ];
	
	for(var i=1; i<=lastDay; i++) {
		var footerEl   = {};
		var objDay     = {};
		var day        = new Date(y + '-' + m + '-' + i).getDay();
		
		var txtDay     = i < 10 ? "0" + i: i;
		
		objDay.headerText  = i; 
		objDay.headerStyle = fnHeaderStyle(day);
		objDay.children    = [{dataField     : "day_"+ txtDay
			                  ,headerText    : days[day]
		                      ,headerStyle   : fnHeaderStyle(day)
		                      ,styleFunction : fnCellStyleFunction
		                      ,labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
		                    	  
		                    	  var txt;
				                    	  switch (value){
				                    	   case "11": txt = "R"; return txt;
										   case "10": txt = "P"; return txt;
		                                  }
		                          }
	                          ,width: 40
		                      }
		];//second children
		
		columnLayout.push(objDay); //header set
		
		footerEl.dataField     = "day_"+ txtDay;
		footerEl.positionField = "day_"+ txtDay;
		footerEl.style         = "my-footer-style";
		footerEl.labelFunction = function(value, columnValues, footerValues) {
			var planCntVal = 0;
			var rndCntVal  = 0;
			for(var i=0, len=columnValues.length; i<len; i++) {
				val =  columnValues[i];
				
				if (val == "11") {
					rndCntVal++;
				}
				
				if (val.indexOf("1")==0) {
					planCntVal++;
				}

			}//endfor
			return planCntVal + "<br>" + rndCntVal;
			
		};//labelFunction
		gridFooter.push(footerEl);    //footer set
	}//endfor
	

	
	var objTot = {};
	objTot.headerText = "계획합계";
	objTot.dataField  = "plan_tot_result";
	objTot.expFunction = function ( rowIndex, columnIndex, item, dataField ) {
		var planCntVal = 0;
		var itemVo = item;
		for (var name in itemVo){
			if (itemVo[name]== "10" || itemVo[name]== "11"){
				planCntVal++;
			}
		}
		return planCntVal;
		
	}
	columnLayout.push(objTot);  //계획합계
	
	footerEl = {};
	footerEl.dataField     = "plan_tot_result";
	footerEl.positionField = "plan_tot_result";
	footerEl.style         = "my-footer-style";
	footerEl.formatString  = "#,##0.#";
	footerEl.labelFunction = function(value, columnValues, footerValues) {
		var planSumVal = 0;
		for(var i=0, len=columnValues.length; i<len; i++) {
			planSumVal+= columnValues[i];
		}
		return planSumVal;
	}
	gridFooter.push(footerEl);    //footer set
	
	var objTot = {};
	objTot.headerText = "순방합계";
	objTot.dataField  = "rnd_tot_result";
	objTot.expFunction = function ( rowIndex, columnIndex, item, dataField ) {
		var rndCntVal = 0;
		var itemVo = item;
		for (var name in itemVo){
			if (itemVo[name] == "11") {
				rndCntVal++;
			}
		}
		return rndCntVal;
		
	}
	objTot.headerStyle = "aui-grid-user-custom-column-6";
	objTot.styleFunction = fnCellStyleFunction;
	columnLayout.push(objTot);  //순방합계
	
	footerEl = {};
	footerEl.dataField     = "rnd_tot_result";
	footerEl.positionField = "rnd_tot_result";
	footerEl.style         = "my-footer-style";
	footerEl.formatString  = "#,##0.#";
	footerEl.labelFunction = function(value, columnValues, footerValues) {
		var rndSumVal = 0;
		for(var i=0, len=columnValues.length; i<len; i++) {
			rndSumVal+= columnValues[i];
		}
		return rndSumVal;
	}
	gridFooter.push(footerEl);    //footer set	
	
	/* M20180226 k2s 기능 삭제 주석처리
	var objTot = {};
	objTot.headerText = "총합계";
	objTot.dataField  = "tot_result";
	objTot.expFunction = function ( rowIndex, columnIndex, item, dataField ) {
		var totCntVal = 0;
		var itemVo = item;
		for (var name in itemVo){
			if (itemVo[name] == "10" || itemVo[name] == "11") {
				totCntVal++;
			}
		}
		return totCntVal;
		
	}
	columnLayout.push(objTot); //총합계
	
	footerEl = {};
	footerEl.dataField     = "tot_result";
	footerEl.positionField = "tot_result";
	footerEl.style         = "my-footer-style";
	footerEl.formatString  = "#,##0.#";
	footerEl.labelFunction = function(value, columnValues, footerValues) {
		var totSumVal = 0;
		for(var i=0, len=columnValues.length; i<len; i++) {
			totSumVal+= columnValues[i];
		}
		return totSumVal;
	}
	gridFooter.push(footerEl);    //footer set
	*/
	
	//푸터 설정
	footerLayout = gridFooter;

	// AUIGrid 그리드를 생성합니다.
	createAUIGrid(columnLayout);
	
}//createColumnLayout

//2.AUIGrid 생성
function createAUIGrid(columnLayout) {
	
	var auiGridProps = {editable             : true
			           ,selectionMode        : "multipleCells"
					   ,fixedColumnCount     : 11                         //고정칼럼 카운트 지정
					   ,useGroupingPanel     : false                      //그룹핑매널 사용
					   ,displayTreeOpen      : true                       //그룹핑 트리 열기
	                   ,showBranchOnGrouping : false                      //브랜치에 해당되는 행 출력 여부
				   	   ,groupingFields       :["em_nm"]                   //차례로 em_nm  순으로 그룹핑
                       ,enableCellMerge      : true                       //셀병합 여부
                       ,enableColumnResize   : true                       //칼럼 리사아징	
				   	   ,showFooter           : true                       //푸터 보이게 설정
				   	   ,footerHeight         : 60                         //푸터 높이				   	   
	};
	
	// 실제로 #planGrid10101 에 그리드 생성
	planGrid10101 = AUIGrid.create("#planGrid10101", columnLayout, auiGridProps);
	
	//푸터 객체 세팅
	AUIGrid.setFooter(planGrid10101, footerLayout);
	
};//createAUIGrid

//3.그리드 Data 생성
function createGridData(data){	   

	if(data.rndPlanScheTableVoList.length > 0){
		
		// 그리드에 데이터 세팅
		// data 는 JSON 을 파싱한 Array-Object 입니다.
		AUIGrid.setGridData(planGrid10101, data.rndPlanScheTableVoList);	
		
	}else{
		// data가 없으면 no data 노출
		AUIGrid.setGridData(planGrid10101, []);
	}
	
}//createGridData

function fnHeaderStyle(day) {
//	console.log("fnHeaderStyle day: ", day);
	var tt;
		if (day==0) {
			tt = "aui-grid-user-custom-column-0"
		}
		
		if (day==6) {
			tt = "aui-grid-user-custom-column-6"
		}
	return tt;
	
}

//셀스타일 함수 정의
function fnCellStyleFunction(rowIndex, columnIndex, value, headerText, item, dataField) {
	/*
	console.log("cellStyleFunction value: ", value);
	console.log("cellStyleFunction dataField: ", dataField);
	*/
	if(value == "11") {
		return "mycustom-r";
	} else
	if(value == "10") {
		return "mycustom-p";
	}
	
	if (dataField=="rnd_tot_result") {
		return "aui-grid-user-custom-column-6"
	}
};


// 이벤트 함수
function fnSetEventComponent(){
	
	// 조회버튼
	$("#cvsSearchBtn").click(function(){
		
		// 조회
		fnGetCvsChkList();	
	});
		
	// 초기화버튼
	$("#cvsSearchResetBtn").click(function(){
		$("#searchSmName10101").val("");	//검색어
		$("#searchEmName10101").val("");	//검색어
		$("#searchOmCode10101").val("");	//검색어
		$("#searchTmCode10101").html("<option value=''>팀명</option>");	//검색어
		$("#searchStartDate10101").val("");	//검색어
	});
	
	// 엑셀 다운로드 버튼
	$("#excelDownBtn10101").click(function(){
		
		//FileSaver.js 로 로컬 다운로드가능 여부 확인
		
		if(!AUIGrid.isAvailableLocalDownload(planGrid10101)) {
			alert("로컬 다운로드 불가능한 브라우저 입니다. 서버 사이드로 전송하여 다운로드 처리하십시오.");
			return;
		}
		
		var rowCount = AUIGrid.getRowCount(planGrid10101);
		
		// 10만행 보다 크다면 CSV로 다운로드 처리
		if(rowCount >= 100000) {
			if(confirm("10만행을 엑셀 또는 CSV 로 내보내기 합니다.\r\n확인 클릭 - 엑셀로 내보내기\r\n취소 클릭 - CSV로 내보내기")) {
				AUIGrid.exportToXlsx(planGrid10101, {
					fileName : "순방계획실적표"
				});
			} else {
				AUIGrid.exportToCsv(planGrid10101, {
					fileName : "순방계획실적표"
				});
			}
		}else{ // 10만행 보다 작은 경우
			AUIGrid.exportToXlsx(planGrid10101, {fileName : "순방계획실적표"});
		}
	});
	
	$("#excelDownTitleBtn10101").click(function(){
		fnExportWithoutHiddenColumns();   //엑셀파일다운로드 (타이틀)
	});
	
}//fnSetEventComponent


//검색 콤보 박스
function fnGetCvsSearchComboBox(om_code ,om_orgnzt_se) {
	$.ajax({url      : "/organization/auth"
	       ,data     :{"om_code":om_code, "om_orgnzt_se": om_orgnzt_se}
	       ,type     : "POST"
	       ,dataType : "json"
	       ,async    : false
	       ,cache    : false 
	       ,success  : function(data) {
								var listHtml = "";
								if( data.result.length > 0){
						    		for(var i=0; i<data.result.length; i++){
						    			var res = data.result[i];     
						    			listHtml += "<option value ='"+res.om_code+"'>"+ res.om_nm +"</option>";
						    		}
								}
								if(om_orgnzt_se==1){
									$("#searchOmCode10101").html(listHtml);
									$("#searchOmCode10101").change(function(){
										fnGetCvsSearchComboBox($(this).val(), 2);
									});
									if(parseInt(data.auth_flag) > 1){
										fnGetCvsSearchComboBox($("#searchOmCode10101").val() ,2);
									}
								}else if(om_orgnzt_se==2){
									$("#searchTmCode10101").html(listHtml);
								}
	                 }
	});
}//fnGetCvsSearchComboBox


//숨겨진 칼럼은 엑셀에 포함시키지 않기
function fnExportWithoutHiddenColumns() {
	var omNm = $("#searchOmCode10101 option:selected").text(); 		//지점
	var strY = $("#searchStartDate10101").val().substring(0,4);
	var strM = $("#searchStartDate10101").val().substring(5,7);
	
	if (omNm == "" || omNm == undefined || omNm == null || omNm == "지점") {
		omNm = "전체지점"
	}
	strFileName  = omNm + " "+strY+"년 "+ strM+"월 "+" 순방 계획 실적표";
	strSheetName = "순방계획실적표( " +omNm+" )";
	
	var excelProps = {fileName           : strFileName	
		             ,sheetName          : strSheetName
		             //현재 그리드의 히든 처리된 칼럼의 dataField 들 얻어 똑같이 동기화 시키기
		             ,exceptColumnFields : AUIGrid.getHiddenColumnDataFields(planGrid10101)         //숨김 컬럼 제외
			         ,fixedColumnCount   : 6                                                        //엑셀 상의 고정 칼럼		    
					 ,headers            : [ {text : "", height:20}                                 //행 빈줄
					                       , {text : strFileName, height:50, style : { fontSize:30, textAlign:"left", fontWeight:"bold", underline:true, background:"#ffffee", color:"#072cea"}}
					                       , {text : "P:계획 / R:순방" , height:20, style : { fontSize:15, textAlign:"left", fontWeight:"bold", underline:false, color:"#ea0707"}}
					                       , {text : "", height:20}                                 //행 빈줄
					                       , {text : "", height:5, style : { background:"#555555"}} //빈줄 색깔 경계 만듬
										   ]			         
	};
	
	AUIGrid.exportToXlsx(planGrid10101, excelProps);
}//fnExportWithoutHiddenColumns