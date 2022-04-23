
var gridMaster; // AUIGrid 생성 후 반환 ID
var gridDetail; // Details 그리드 생성 후 반환ID

var master = [];
master.push({"custId" : "고정MD", "optionType" : "0000000067"});
master.push({"custId" : "순회MD", "optionType" : "0000000072"});

var oiType; // 고정, 순회 optionType

/**
 * @함수명: ready
 * @작성일: 2018. 01. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	$("#optionPop65100").instancePopUp();
	
	// 이벤트 등록
	fnSetEventComponent();
});


/**
 * @함수명: fnSetEventComponent
 * @작성일: 2018. 01. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	fnGetFixSearchComboBox(); // 소속 지점 가져오기
	gridOnload(); // 그리드
	
	// 등록버튼
	$("#optionOpen65100").click(function(){
		
		if($("#searchBhf65100").val() == ""){
			alert("지점을 선택해주세요");
			return;
		}
		fnOptionPopClear(); // 입력폼 초기화
		$("#optionNickName65100").prop("readonly",false);
		$("#optionName65100").prop("readonly",true);
		fnGetTrtComboDepthList("",1);
		$("#optionPop65100").show();
	});
	
	// 입력폼 depth 콤보박스
	$(".trt-change-group").change(function(){
		var depth = $(this).data("trt-depth");
			if($(this).val() == ""){
				$("#optionName65100").val("");
				$("#optionParentCode65100").val("");
			}else{
				$("#optionName65100").val($(this).find("option:selected").text());
				$("#optionParentCode65100").val($(this).val());
			}
			
			fnGetTrtComboDepthList($(this).val(),depth+1);
	});
	
	// 닫기버튼
	$("#optionPopClose65100, #optionPopCloseX65100").click(function(){
		$("#optionPop65100").hide();
	});
	
	// 지점 콤보박스
	$("#searchBhf65100").change(function(){
		$("#omNm65100").html($("#searchBhf65100 option:selected").text());
		AUIGrid.selectRowsByRowId(gridMaster, "고정MD");
		prodList(oiType, gridDetail);
	});
	
	// 수정사항 적용 버튼
	$("#optionItemUpt65100").click(function(){
		// 수정된 행 아이템들(배열)
		var editedRowItems = AUIGrid.getEditedRowColumnItems(gridDetail);
		if(editedRowItems.length>0){
			bhfprodUseAtUpt(editedRowItems);
		}else{
			alert("수정할 상품이 없습니다.");
		}
	});
}

function gridOnload(){
//	AUIGrid.destroy("#grid_wrap65100");
//	AUIGrid.destroy("#detail_grid_wrap65100");
	// AUIGrid 그리드를 생성합니다.
	createAUIGrid();
	
	// Details 그리드를 생성합니다.
	createDetailAUIGrid();
	
	// 마스터 그리드
	AUIGrid.setGridData(gridMaster, master);
}

//지점 검색 콤보 박스
function fnGetFixSearchComboBox(){
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
				$("#searchBhf65100").html(listHtml);
				$("#omNm65100").html($("#searchBhf65100 option:selected").text());
	    }
	});
}

//Master 그리드 를 생성합니다.
function createAUIGrid() {
	
	// AUIGrid 칼럼 설정
	var columnLayout = [ {
			dataField : "custId",
			headerText : "MD별 취급품목 설정",
			width : 140
		}
	];
	
	// 그리드 속성 설정
	var gridPros = {
			
		// singleRow 선택모드
		selectionMode : "singleRow",
		
		rowIdField : "custId",

		showRowCheckColumn : false,
		
		showRowNumColumn : false
	};

	// 실제로 #grid_wrap 에 그리드 생성
	gridMaster = AUIGrid.create("#grid_wrap65100", columnLayout, gridPros);
	
	// 그리드 ready 이벤트 바인딩
	if(auth_flag >=2){ //팀장, 고정MD, 순회MD
		AUIGrid.bind(gridMaster, "ready", auiGridCompleteHandler);
	}
	
	// 선택 변경 이벤트 바인딩
	AUIGrid.bind(gridMaster, "selectionChange", auiGridSelectionChangeHandler);
}

//Details 그리드 생성
function createDetailAUIGrid() {
	
	// AUIGrid 칼럼 설정
	var columnLayout = [ {
			dataField : "oi_nick_nm",
			headerText : "약어",
			width : 160
		}, {
			dataField : "oi_nm",
			headerText : "항목명",
			width : 160
		}
	/*	, {
			dataField : "use_at",
			headerText : "사용여부",
			width: 90
		} */
		, {
			dataField : "use_at",
			headerText : "사용여부",
			width: 180,
			renderer : {
				type : "ImageRenderer",
				editable : true,
				imgHeight : 20,
				imgTableRef : {
					"Y" : "/resources/images/icon/green_check.png",
					"N" : "/resources/images/icon/gray_check.png",
					"default" : "/resources/images/icon/gray_check.png" //default
				}
			}
		} 
	];
	
	// 그리드 속성 설정
	var gridPros = {
		rowIdField : "oi_code",
		showRowCheckColumn : false
	};

	// 실제로 #detail_grid_wrap 에 그리드 생성
	gridDetail = AUIGrid.create("#detail_grid_wrap65100", columnLayout, gridPros);
	
	// 셀 클릭 이벤트 바인딩
	AUIGrid.bind(gridDetail, "cellClick", function(event) {
		if(event.columnIndex==2){ //사용여부 컬럼만 클릭가능
			var rowItem = AUIGrid.getItemByRowIndex(gridDetail, event.rowIndex);
		
			rowItem.use_at = (event.value == "Y") ? "N" : "Y";
			// row 데이터 업데이트
			AUIGrid.updateRow(gridDetail, rowItem, event.rowIndex);
		}
//		alert(" ( " + event.rowIndex + ",c " + event.columnIndex + ") :  " + event.value + " clicked!!");
	});
}

//그리드 ready 이벤트 핸들러
function auiGridCompleteHandler(event) {
	
	// 마스터 그리드가 로딩 완료된 시점에 고객 ID 가 cust0 인 곳에 셀렉션 지정
	AUIGrid.selectRowsByRowId(gridMaster, "고정MD");
	
	document.getElementById("detail_info65100").innerHTML = "<h1>고정MD 상품</h1>";
	
	oiType = "0000000067";
	
	// 고객 ID cust3 으로 초기 디테일 그리드 작성 
	prodList(oiType, gridDetail);
}

//Details 데이터 요청 지연 타임아웃
var timerId = null;

//마스터 그리드선택 변경 이벤트 핸들러
//마스터 그리드에서 행을 선택한 경우 해당 행의 고객 ID(custId) 에 맞는
//데이터를 요청하여 디테일 그리드에 표시합니다.
function auiGridSelectionChangeHandler(event) { 

	AUIGrid.setGridData(gridDetail, ""); // 화면 변경을 위해 빈화면 출력
	
	// 200ms 보다 빠르게 그리드 선택자가 변경된다면 데이터 요청 안함
	if(timerId) {
		clearTimeout(timerId);
	}
	
	timerId = setTimeout(function() {
		var selectedItems = event.selectedItems;
		if(selectedItems.length <= 0)
			return;
		
		var rowItem = selectedItems[0].item; // 행 아이템들
		
		var optionType = rowItem.optionType; // 선택한 행의 고객 ID 값...custId 를 파라메터로 Get 요청하십시오.
		oiType = optionType;
		
		// 디테일 정보 표시
		document.getElementById("detail_info65100").innerHTML = "<h1>"+rowItem.custId + " 상품</h1>";
		
		// rowId 에 맞는 디테일 데이터 요청 후 디테일 그리드에 삽입
		prodList(optionType, gridDetail);
1	}, 200);  // 현재 200ms 민감도....환경에 맞게 조절하세요.
};


//데이터 요청 Ajax
function prodList(optionType, gridId) {
	
	// ajax (XMLHttpRequest) 로 그리드 데이터 요청
	$.ajax({
		url : "/bhfProd/prodList",
		data : {"oi_type" : optionType,
				 "om_code" : $("#searchBhf65100 option:selected").val()},
		type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			// 그리드 데이터
			var gridData = data.resultList;
			
			// 그리드에 데이터 세팅
			AUIGrid.setGridData(gridId, gridData);
		},
		onError : function(status, e) {
			alert("데이터 요청에 실패하였습니다.\r status : " + status);
		}
	});
}

// 입력 폼 초기화
function fnOptionPopClear(){
	$("#optionCode65100").val("");
	$("#optionParentCode65100").val("");
	$("#optionCode65100").val("");
	$("#optionName65100").val("");
	$("#optionNickName65100").val("");
	$("#optionOrder65100").val("");
	$("#optionDefaultY65100").prop("checked", true);
	$("#optionUseY65100").prop("checked", true);
}

/**
 * @함수명: fnGetTrtComboDepthList
 * @설명: 콤보 Depth 
 * @99-500 에서 복사
 * @param 
 */
function fnGetTrtComboDepthList(pm_code, pm_dp){
	$.ajax({
		url : "/product/depthList",
	    data:{"pm_code":pm_code, "pm_dp": pm_dp},
	    type : "POST",
	    dataType : "json", 
	    async: false,
	    cache : false ,
	    success : function(data) {
			var listHtml = "";
			if(data.result != null && data.result.length > 0){
	    		listHtml = "<option value ='' > 선택 </option>";
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			listHtml += "<option value ='"+res.pm_code+"'>" + res.pm_nm +"</option>";
	    		}
			}else{
				listHtml += "<option value =''> 선택 </option>";
			}	
			
			$(".trt-change-group[data-trt-depth='"+pm_dp+"']").html(listHtml);
			for (var i = 0; i < (5-pm_dp); i++) {
				$(".trt-change-group[data-trt-depth='"+(pm_dp+(i+1))+"']").html("<option value =''> 선택 </option>");
			}
	    }
	});
}

function bhfprodUseAtUpt(editedRowItems){
	var om_code = $("#searchBhf65100 option:selected").val();
	var param = {
			"om_code" : om_code
			, "editedRowItems" : JSON.stringify(editedRowItems)
		};
	$.ajax({
		url : "/bhfProd/prodUseAtUpt",
		data : param,
		type : "POST",		
		dataType : "json",
		cache : false,
		beforeSend : function(){
			console.log(param);
		},
		success : function(result) {
			console.log(result);
			if(result > 0){
				alert("수정 되었습니다.");
				prodList(oiType, gridDetail);
			}
		}
	});
}
