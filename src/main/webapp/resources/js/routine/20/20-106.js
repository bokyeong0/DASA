/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
var displyBigPg = 1;

$(document).ready(function(){
	$("#searchSmOdrTo20106").val("4");	//차수
	
	fnGetDisPlayBigSearchComboBox();
	fnGetDisPlayBigCstmrGroupList();
	

	$("#diplySearchBtn20106").click(function(){
		fnGetDiaryBigItemList(1);
	});

	//엑셀다운로드
	$("#excelDownBtn20106").click(function(){
		if(confirm("데이터가 많아 최대 5분까지 소요 될수 있습니다.\n계속 진행하시겠습니까? ")){
			var om_code  = $("#searchOmCode20106").val(); 		//지점
			var cg_code  = $("#searchCgCode20106").val();		//고객그룹
			var me_code  = $("#searchMeCode20106").val();		//관리업체
			var em_nm    = $("#searchEmName20106").val();		//사원명
			var sm_odr1  = $("#searchSmOdrFrom20106").val();	//차수
			var sm_odr2  = $("#searchSmOdrTo20106").val();		//차수
			var sm_area1 = $("#searchAddrCode120106").val();	//시도
			var sm_area2 = $("#searchAddrCode220106").val();	//구군
			var da_date  = $("#yy_mm_20106").val();             //연월
			
			var code_at = ""; //A20171226 k2s 체크값
			
			if("checked" ==  $("input:checkbox[id=searchCodeAt20106]").attr("checked")) { 
				code_at = "Y";
			}
			console.log("code_at: ",code_at);
			
			location.href="/display/bigexcel?"    +
							"om_code="+om_code    +
							"&cg_code="+cg_code   +
							"&me_code="+me_code   +
							"&em_nm="+em_nm       +
							"&sm_odr1="+sm_odr1   +
							"&sm_odr2="+sm_odr2   +
							"&sm_area1="+sm_area1 +
							"&sm_area2="+sm_area2 +
							"&da_date="+da_date   + //A20170102 kks
							"&code_at="+code_at     //A20171226 k2s 코드포함 여부 추가						
							;
		}
	});
	
	$("#searchOmCode20106").change(function(){		//지점명 클릭했을때 콤보박스 초기화
		fnGetDisPlayBigCstmrGroupList();
		var listHtml = "<option value='' selected='selected'>선택</option>";
		$("#searchMeCode20106").html(listHtml);
	});	
	
	
	$("#searchCgCode20106").change(function(){      //관리업체조회
		fnGetDisPlayBigEntrpsList($(this).val());
	});
	
	$("#searchEmName20106").enterSearch("fnGetDiaryBigItemList"); //엔터키 조회 이벤트
	
	//초기화
	$("#diplySearchResetBtn20106").click(function(){
		$("#searchOmCode20106").val("");    //지점
		$("#searchCgCode20106").val("");	//고객그룹
		$("#searchMeCode20106").val("");	//관리업체
		$("#searchEmName20106").val("");	//사원명
		$("#searchSmOdrFrom20106").val("");	//차수
		$("#searchSmOdrTo20106").val("4");	//차수
		$("#searchAddrCode120106").val("");	//시도
		$("#searchAddrCode220106").val("");	//구군
		$("input:checkbox[id=searchCodeAt20106]").attr("checked", false); //코드포함 엑셀다운로드(고객그룹,관리업체)		
		fnGetToday20106();                  //조회 연월 set
		fnGetDiaryBigItemList(1);
	});
	
	fnGetCommonCodeComboBox20106("0000000107",$("#searchAddrCode120106"));
	
	$("#searchAddrCode120106").change(function(){
		fnGetCommonCodeComboBox20106($(this).val(),$("#searchAddrCode220106"));
	});
	
	//배치 수행
	$("#diplyBatchBtn20106").click(function(){
		if(confirm("데이터가 많아 최대 5분까지 소요될수 있습니다.\n계속진행하시겠습니까?")){
			fnGetBatchBig();
		}
	});
	
	/* 조회 연월 추가 start!!! A20170102 kks */
	fnGetToday20106();   // 조회 연월 set
	
	$("#yy_mm_20106").winCal(
			{format: "yyyy-mm", 
			startView: 3, 
			minView: 3, 
			maxView: 3});
	
	/* 조회 연월 추가 end!!! A20170102 kks */	
	
	fnGetDiaryBigItemList(displyBigPg);	// 자동 조회 displyBigPg: 1
});

function fnGetBatchBig(){

	$.ajax({
		url : "/display/bach_big",
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
}
function fnGetCommonCodeComboBox20106(c_parent_code, target){
	
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
}

function fnGetDisPlayBigEntrpsList(cg_code){
	
	$.ajax({
		url : "/store/manageEntrpsList",
		data:{"cg_code":cg_code, "om_code": $("#searchOmCode20106").val()},
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
		$("#searchMeCode20106").html(listHtml);
	    
	    }
	});
}
function fnGetDisPlayBigCstmrGroupList(){
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
			$("#searchCgCode20106").html(listHtml);
	    
	    }
	});
}
function fnGetDisPlayBigSearchComboBox(){
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
				$("#searchOmCode20106").html(listHtml);
	    }
	});
}
function fnGetDiaryBigItemList(currPg){
	
	displyBigPg    = currPg;
	var om_code    = $("#searchOmCode20106").val();     //지점
	var cg_code    = $("#searchCgCode20106").val();	    //고객그룹
	var me_code    = $("#searchMeCode20106").val();	    //관리업체
	var em_nm      = $("#searchEmName20106").val();	    //사원명
//	var sm_nm      = $("#searchSmName20106").val();	    //매장명
	var sm_odr1    = $("#searchSmOdrFrom20106").val();	//차수
	var sm_odr2    = $("#searchSmOdrTo20106").val();	//차수
	var sm_area1   = $("#searchAddrCode120106").val();	//시도
	var sm_area2   = $("#searchAddrCode220106").val();	//구군
//	var addr_code3 = $("#searchAddrCode3").val();		//읍면동
	var da_date    = $("#yy_mm_20106").val();           //연월 A20170102 kks	
	
	if(sm_odr1 > sm_odr2) {
		alert("앞의 차수가 뒤의 차수 보다 클 수는 없습니다.");
		return;
	}
	var params = { "om_code"    : om_code
				  ,"cg_code"    : cg_code
				  ,"me_code"    : me_code
		//		  ,"sm_nm"      : sm_nm
				  ,"em_nm"      : em_nm
				  ,"sm_odr1"    : sm_odr1
				  ,"sm_odr2"    : sm_odr2
				  ,"sm_area1"   : sm_area1
				  ,"sm_area2"   : sm_area2
				  ,"da_date"    : da_date     //A20170102 kks
		//		  ,"addr_code3" : addr_code3
				};
	var rowSize = 20;//표시할 행수
	var fnName = "fnGetDiaryBigItemList";//페이징처리 함수명
	
	$.ajax({
		url : "/display/biglist",
		type : "POST",
		data : {
			fnName : fnName,
			params : params,
			rowSize : rowSize,
			currPg : currPg
		},
//		data :searchData ,
		dataType : "json",
		global:true,
		cache : false,
		success : function(data) {
			
			var headColFirst = "";
			var headColSecond = "";	 
			var footCol = "";	 
			
			var columns = new Array(); 	
			var columnArr = data.columnArr;
			headColFirst +='<tr id="headColFirstGgroup20106" >';
			headColSecond +='<tr id="headColSecondGroup20106" >';
			footCol +='<tr style="background-color:#F7F7F7;font-weight:bold; border-top:2px solid #ABABAB">';
			
			
			var headLen =columnArr.length;
//			var tlen = headLen * 2;
			var cols="";
			cols+='<col span="'+headLen+'" width="*" >';
			
			$("#displayTable20106").css("width",((headLen*83))+"px");
			$("#bigColgroup20106").html(cols);
			
			var headColFixed ="";
			headColFixed +='<tr style="height: 58px;">';
			headColFixed +='	<th rowspan="2" style="max-width: 90px;" >고객그룹</th>';
			headColFixed +='	<th rowspan="2" style="max-width: 120px;" >관리업체</th>';
			headColFixed +='	<th rowspan="2" style="max-width: 135px;" >매장명</th>';
			headColFixed +='	<th rowspan="2" style="max-width: 60px;">사원명</th>';
			headColFixed +='	<th rowspan="2" style="max-width: 55px;">차수</th>';
			headColFixed +='</tr>';
			
			
			var tempHeadCol ="";
			for (var i = 0; i < headLen; i++) {
				var colunm_id = columnArr[i].colunm_id;
				var group_id = columnArr[i].group_id;
				var group_nm = columnArr[i].group_nm;
				var item_id = columnArr[i].item_id;
				var item_nm = columnArr[i].item_nm;
				
				
				if(tempHeadCol != group_id){
					headColFirst +='	<th colspan="" class="parent-group" data-group-id="'+group_id+'" >'+group_nm+'</th>';
				}
				tempHeadCol = group_id;
				
				headColSecond +='	<th class="child-group'+group_id+'"  data-group-id="'+group_id+'" >'+item_nm+'</th>';
				columns.push(colunm_id+"_"+group_id+"_"+item_id);
			}
			
			headColFirst +='</tr>';
			headColSecond +='</tr>';
			
			
			$("#fixedThead20106").html(headColFixed);
			$("#bigThead20106").html(headColFirst + headColSecond);
			var firstGroupArr = $("#headColFirstGgroup20106 th.parent-group");
			
			for (var i = 0; i < firstGroupArr.length; i++) {
				var node =firstGroupArr.eq(i);
				var groupId = node.data("group-id");
				var rowspan = $("#headColSecondGroup20106").find(".child-group"+groupId).length;
				node.attr("colspan",rowspan);
			}
			
			if(data.bodyArr.length > 0){
				var bodyRows = "<tr>"; 
				var bodyRowsFixed = "<tr>"; 
				var bodyArr = data.bodyArr;
				var sumArr = data.sumArr;
				
				
				for (var i = 0; i < bodyArr.length; i++) {
					bodyRowsFixed +='<td class="txt_left">'+bodyArr[i].cg_nm+'</td>';
					bodyRowsFixed +='<td class="txt_left">'+bodyArr[i].me_nm+'</td>';
					bodyRowsFixed +='<td class="txt_left">'+bodyArr[i].sm_nm+'</td>';
					bodyRowsFixed +='<td >'+bodyArr[i].em_nm+'</td>';
					bodyRowsFixed +='<td >'+bodyArr[i].sm_odr+'</td>';
					
					
					for (var f = 0; f < columns.length; f++) {
						var column = columns[f];
						var entVal =bodyArr[i][column];
						if(entVal === undefined){
							entVal = "0";
						}
						bodyRows +='<td class="txt_right" >'+entVal+'</td>';
						if(i == 0){
							var sumVal =sumArr[i][column];
							if(sumVal === undefined){
								sumVal = "0";
							}
							footCol +='<td class="txt_right">'+(sumVal+"").comma()+'</td>';
						}
					}
					bodyRows +='</tr>';
					bodyRowsFixed +='</tr>';
				}
				$("#fixedTbody20106").html(bodyRowsFixed);
				$("#bigTbody20106").html(bodyRows);
				$("#bigTfoot20106").html(footCol);
//				var rateGroupArr = $("#displayTable20106").find(".main-group");
			}else{
				$("#bigTfoot20106").html("");
				$("#fixedTbody20106").html("<tr><td colspan='5' >조회 된 Data가 없습니다.</td></tr>");
				$("#bigTbody20106").html("<tr><td colspan='"+(5+headLen)+"' >내용이 없습니다.</td></tr>");
			}
			$("#displayPageNavi20106").html(data.navi);
		}
	});
}

/* fnGetToday20106() 조회 연월 set
A20170102 kks*/
function fnGetToday20106(){

	var now = new Date();
   var year= now.getFullYear();
   var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);

   var chan_val = year + '-' + mon;
   
   $('#yy_mm_20106').val(chan_val);

   return true;
}