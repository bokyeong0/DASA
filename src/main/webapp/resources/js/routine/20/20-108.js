/**
 * @프로그램명: CVS 체크리스트 조회
 * @작성일: 2018. 03
 * @작성자: k2s
 * @설명: 로딩 및 Event
 * @param 
 */

// AUIGrid 생성 후 반환 ID
var cvsCheckGrid20108;


//AUIGrid 칼럼 설정
var columnLayout;

$(document).ready(function(){

	// 달력 이벤트
	$("#searchStartDate20108").winCal(baseOptions);
	$("#searchEndDate20108").winCal(baseOptions);

	// 소속가져오기
	fnGetCvsSearchComboBox("",1); 
	
	// 이벤트 등록
	fnSetEventComponent();	
	
	if(location.hostname == "127.0.0.1" || location.hostname == "localhost" || location.hostname == "27.125.31.149") {
		$("#searchStartDate20108").val("2017-07-01");	//시작일
		$("#searchEndDate20108").val("2017-07-08");	    //종료일
	}
});//ready


// cvs 체크리스트 헤더 조회
function fnGetCvsChkList(){	

	// 화면의 parameter 넘길 값
	var smNm  	  = $("#searchSmName20108").val();	    //매장명
	var emNm  	  = $("#searchEmName20108").val();	    //직원명
	var omCode    = $("#searchOmCode20108").val();	    //지점명
	var tmCode    = $("#searchTmCode20108").val();	    //팀명
	var startDate = $("#searchStartDate20108").val();	//시작일
	var endDate   = $("#searchEndDate20108").val();	    //종료일

	// 날짜 필수 체크
	if(startDate == "" || endDate == ""){
		alert("날짜는 필수 입력값 입니다.");
		return;
	}else{
		if(startDate.dateReplace() > endDate.dateReplace()){
			alert("시작일이 종료일보다 클수 없습니다.");
			return;
		}		
	}
	
	// 조회값
	var params = {"smNm"    	: smNm
				 ,"emNm"    	: emNm
				 ,"omCode"    	: omCode
				 ,"tmCode"    	: tmCode
				 ,"startDate"   : startDate.dateReplace()
				 ,"endDate"    	: endDate.dateReplace()
		         };
	
	// 그리드 초기화
	cvsCheckGrid20108 = AUIGrid.destroy("#cvsCheckGrid20108", [], {});
	
	// ajax로 그리드 데이터 요청
	$.ajax({
		url : "/display/cvsChkList",
		type : "POST",
		data : {
			params : params
		},
		dataType : "json",
		global:true,
		cache : false,
		success : function(data) {
			
			// 컬럼 레이아웃 생성
			createColumnLayout(data);
			
			// 그리드를 생성
			createGridData(data);
			
		},
		error : function() {
			// 그리드(cvsCheckGrid20108) 초기화
			cvsCheckGrid20108 = AUIGrid.destroy("#cvsCheckGrid20108", [], {});
		}
	});
	
}//fnGetCvsChkList


//1.컬럼 레이아웃 생성
function createColumnLayout(data){
	
	var itemList = data.columnArr;
	var itemVo1;
	var itemVo2;
	var child=[];
	var gridHeader = [];
		
	// 동적으로 아이템이 변경되므로 String형태가 아닌 Object 형태로 children에 넣을 속성들을 만듬
	if(itemList.length > 1){  //조회값의 길이가 구조적으로 최소 2개 이상임
		$target   = null;     //객체공유 용도
		var preNm = null;
		
		itemVo1 = itemList[0]; //중카테고리 항목
		itemVo2 = itemList[1]; //소카테고리 품목
		
		for(var key in itemVo1){
	   		var el   = {};
			var key2 = key.replace("drcc", "drcci");
			
   			if(preNm == null || preNm != itemVo1[key]) {
   				
	   			preNm         = itemVo1[key];
	   			el.headerText = itemVo1[key]; // headerText 생성
	   			
		   		if(itemVo1[key] == itemVo2[key2]) {
					if (key=="sm_nm") {
						el.style = "aui-grid-my-data";
						el.width = 150;
					}
		   			el.dataField = key;            //dataField 생성
		   			gridHeader.push(el);           //생성된 headerText와 dataField를 넣어줌
		   			
		   		}else {
		   			el.children = []; 
		   			gridHeader.push(el);
		   			$target     = el.children;
		   			
		   			var el2        = {};
		   			el2.headerText = itemVo2[key2];
		   			el2.dataField  = key2;
		   			el2.styleFunction = fnCellStyleFunction;   //커스텀 셀 스타일 정의 A20180226 색상구분 color-bad:불량, color-normal:보통
		   			$target.push(el2);
		   		}
		   		
	   		}else {
	   			el.headerText    = itemVo2[key2];
	   			el.dataField     = key2;
	   			el.styleFunction = fnCellStyleFunction;  //커스텀 셀 스타일 정의 A20180226 색상구분 color-bad:불량, color-normal:보통
   				$target.push(el);
   			}
	   	}

		// 컬럼레이아웃 생성
		columnLayout = gridHeader;
		
		// AUIGrid 그리드를 생성합니다.
		createAUIGrid(columnLayout);

	}else {
		alert("조회된 데이터가 없습니다.");
	}
}//createColumnLayout

//2.Grid 생성
function createAUIGrid(columnLayout) {
	
	var auiGridProps = {editable           : false
			           ,enableFilter       : false
			           ,selectionMode      : "multipleCells"
		               ,fixedColumnCount   : 4                         //고정칼럼 카운트 지정
	                   ,enableColumnResize :true                       //칼럼 리사아징
                   
	};
	
	// 실제로 #cvsCheckGrid20108 에 그리드 생성
	cvsCheckGrid20108 = AUIGrid.create("#cvsCheckGrid20108", columnLayout, auiGridProps);
	
	
};//createAUIGrid

//3.Grid Data 생성
function createGridData(data){	   

	var gridData = data.dataArr;
	
	if(gridData.length > 0){
		// 그리드에 데이터 세팅 data 는 JSON 을 파싱한 Array-Object 입니다.
		AUIGrid.setGridData(cvsCheckGrid20108, gridData);	
		
	}else{
		// data가 없으면 no data 노출
		AUIGrid.setGridData(cvsCheckGrid20108, []);
	}
	
    //현재 그리드의 값을 모두 조사하여 최적의 칼럼 사이즈를 찾아 그리드 크기에 맞추기
	//현재 출력된 칼럼들의 값을 모두 조사하여 최적의 칼럼 사이즈를 찾아 배열로 반환.
	//만약 칼럼 사이즈들의 총합이 그리드 크기보다 작다면, 나머지 값들을 나눠 가져 그리드 크기에 맞추기
	var colSizeList = AUIGrid.getFitColumnSizeList(cvsCheckGrid20108, true);
	
	//구해진 칼럼 사이즈를 적용 시킴.
	AUIGrid.setColumnSizeList(cvsCheckGrid20108, colSizeList);
	
}//createGridData

// 이벤트 함수
function fnSetEventComponent(){
	
	// 조회버튼
	$("#cvsSearchBtn20108").click(function(){
		fnGetCvsChkList();	  // 조회
	});
		
	// 초기화버튼
	$("#cvsSearchResetBtn20108").click(function(){
		$("#searchSmName20108").val("");	//검색어
		$("#searchEmName20108").val("");	//검색어
		$("#searchOmCode20108").val("");	//검색어
		$("#searchTmCode20108").html("<option value=''>팀명</option>");	//검색어
		$("#searchStartDate20108").val("");	//검색어
		$("#searchEndDate20108").val("");	//검색어
	});
	
	// 엑셀 다운로드 버튼
	$("#excelDownBtn20108").click(function(){
		var strFileName = "CVS 체크리스트";
		
		//FileSaver.js 로 로컬 다운로드가능 여부 확인
		if(!AUIGrid.isAvailableLocalDownload(cvsCheckGrid20108)) {
			alert("로컬 다운로드 불가능한 브라우저 입니다. \n서버 사이드로 전송하여 다운로드 처리하십시오.");
			return;
		}
		
		var rowCount = AUIGrid.getRowCount(cvsCheckGrid20108);
		
		// 10만행 보다 크다면 CSV로 다운로드 처리
		if(rowCount >= 100000) {
			if(confirm("10만행을 엑셀 또는 CSV 로 내보내기 합니다.\r\n확인 클릭 - 엑셀로 내보내기\r\n취소 클릭 - CSV로 내보내기")) {
				AUIGrid.exportToXlsx(cvsCheckGrid20108, {fileName : strFileName});
				
			} else {
				AUIGrid.exportToCsv(cvsCheckGrid20108, {fileName : strFileName});
				
			}
		}else{ // 10만행 보다 작은 경우
			AUIGrid.exportToXlsx(cvsCheckGrid20108, {fileName : strFileName});
		}
	});
}

//셀스타일 함수 정의 (커스텀 셀 스타일 정의 A20180226 k2s 색상구분 color-bad:불량, color-normal:보통)
function fnCellStyleFunction(rowIndex, columnIndex, value, headerText, item, dataField) {
	if(value == "불량") {
		return "color-bad";
	} else
	if(value == "보통") {
		return "color-normal";
	}

};

//검색 콤보 박스
function fnGetCvsSearchComboBox(om_code ,om_orgnzt_se){
	$.ajax({
		url : "/organization/auth",
	    data:{"om_code":om_code, "om_orgnzt_se": om_orgnzt_se},
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
			if(om_orgnzt_se==1){
				$("#searchOmCode20108").html(listHtml);
				$("#searchOmCode20108").change(function(){
					fnGetCvsSearchComboBox($(this).val(), 2);
				});
				if(parseInt(data.auth_flag) > 1){
					fnGetCvsSearchComboBox($("#searchOmCode20108").val() ,2);
				}
			}else if(om_orgnzt_se==2){
				$("#searchTmCode20108").html(listHtml);
			}
	    }
	});
}