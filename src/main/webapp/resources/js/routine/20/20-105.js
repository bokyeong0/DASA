/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
var displyTrtPg = 1;

$(document).ready(function(){
	$("#searchSmOdrTo20105").val("4");	//차수
	
	fnGetDisPlayTrtSearchComboBox();
	fnGetDisPlayTrtCstmrGroupList();

	$("#diplySearchBtn20105").click(function(){
		fnGetDiaryTrtItemList(1);
	});
	
	//엑셀다운로드
	$("#excelDownBtn20105").click(function(){
		if(confirm("데이터가 많아 최대 5분까지 소요 될수 있습니다.\n계속 진행하시겠습니까? ")){
			
			var om_code  = $("#searchOmCode20105").val(); 		//지점
			var cg_code  = $("#searchCgCode20105").val();		//고객그룹
			var me_code  = $("#searchMeCode20105").val();		//관리업체
			var em_nm    = $("#searchEmName20105").val();		//사원명
			var sm_odr1  = $("#searchSmOdrFrom20105").val();	//차수
			var sm_odr2  = $("#searchSmOdrTo20105").val();		//차수
			var sm_area1 = $("#searchAddrCode120105").val();	//시도
			var sm_area2 = $("#searchAddrCode120105").val();	//구군
			var da_date  = $("#yy_mm_20105").val();             //연월
			
			var code_at = ""; //A20171226 k2s 체크값
			
			if("checked" ==  $("input:checkbox[id=searchCodeAt20105]").attr("checked")) { 
				code_at = "Y";
			}
			console.log("code_at: ",code_at);			
			
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
		}
	});
	
	$("#searchOmCode20105").change(function(){		//지점명 클릭했을때 콤보박스 초기화
		fnGetDisPlayTrtCstmrGroupList();
		var listHtml = "<option value='' selected='selected'>선택</option>";
		$("#searchMeCode20105").html(listHtml);
	});
	
	$("#searchCgCode20105").change(function(){
		fnGetDisPlayTrtEntrpsList($(this).val());   //관리업체조회
	});
	
	$("#searchEmName20105").enterSearch("fnGetDiaryTrtItemList"); //엔터키 조회 이벤트
	
	//초기화
	$("#diplySearchResetBtn20105").click(function(){
		$("#searchOmCode20105").val("");    //지점
		$("#searchCgCode20105").val("");	//고객그룹
		$("#searchMeCode20105").val("");	//관리업체
		$("#searchEmName20105").val("");	//사원명
		$("#searchSmOdrFrom20105").val("");	//차수
		$("#searchSmOdrTo20105").val("4");	//차수
		$("#searchAddrCode120105").val("");	//시도
		$("#searchAddrCode220105").val("");	//구군
		$("input:checkbox[id=searchCodeAt20105]").attr("checked", false); //코드포함 엑셀다운로드(고객그룹,관리업체)		
		fnGetToday20105();                  //조회 연월 set
		fnGetDiaryTrtItemList(1);
	});
	
	fnGetCommonCodeComboBox20105("0000000107",$("#searchAddrCode120105"));
	
	$("#searchAddrCode120105").change(function(){
		fnGetCommonCodeComboBox20105($(this).val(),$("#searchAddrCode220105"));
	});
	
	//배치 수행
	$("#diplyBatchBtn20105").click(function(){
		if(confirm("데이터가 많아 최대 5분까지 소요될수 있습니다.\n계속진행하시겠습니까?")){
			fnGetBatchTrt();
		}
	});
	
	/* 조회 연월 추가 start!!! A20161230 kks */
	fnGetToday20105();   // 조회 연월 set
	/* 조회 연월 추가 end!!! A20161230 kks */
	
	fnGetDiaryTrtItemList(displyTrtPg);	 // 자동 조회 displyTrtPg: 1
});

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
}
function fnGetCommonCodeComboBox20105(c_parent_code, target){
	
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

function fnGetDisPlayTrtEntrpsList(cg_code){
	
	$.ajax({
		url : "/store/manageEntrpsList",
		data:{"cg_code":cg_code, "om_code": $("#searchOmCode20105").val()},
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
		$("#searchMeCode20105").html(listHtml);
	    
	    }
	});
}
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
			$("#searchCgCode20105").html(listHtml);
	    
	    }
	});
}
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
				$("#searchOmCode20105").html(listHtml);
	    }
	});
}
function fnGetDiaryTrtItemList(currPg){
	
	displyTrtPg    = currPg;
	var om_code    = $("#searchOmCode20105").val();    //지점
	var cg_code    = $("#searchCgCode20105").val();	   //고객그룹
	var me_code    = $("#searchMeCode20105").val();	   //관리업체
//	var sm_nm      = $("#searchSmName20105").val();	   //매장명
	var em_nm      = $("#searchEmName20105").val();	   //사원명
	var sm_odr1    = $("#searchSmOdrFrom20105").val(); //차수
	var sm_odr2    = $("#searchSmOdrTo20105").val();   //차수
	var sm_area1   = $("#searchAddrCode120105").val(); //시도
	var sm_area2   = $("#searchAddrCode220105").val(); //구군  M20161102 k2s 변경 필요함 #searchAddrCode120105 -> #searchAddrCode220105
//	var addr_code3 = $("#searchAddrCode3").val();	   //읍면동
	var da_date    = $("#yy_mm_20105").val();          //연월 A20161230 kks

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
				  ,"da_date"    : da_date          //A20161230 kks
		//		  ,"addr_code3" : addr_code3
				};
	var rowSize = 20;//표시할 행수
	var fnName = "fnGetDiaryTrtItemList";//페이징처리 함수명
	
	$.ajax({
		url : "/display/trtlist",
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
//			var headColSecond = "";	 
			var footCol = "";	 
			
			var columns = new Array(); 	
			var columnArr = data.columnArr;
			var totRow = data.totRow;
			headColFirst +='<tr id="headColFirstGgroup20105" >';
//			headColSecond +='<tr id="headColSecondGroup20105" >';
			footCol +='<tr style="background-color:#F7F7F7;font-weight:bold; border-top:2px solid #ABABAB">';
//			footCol +='<td colspan="5" style="font-weight:bold;text-align:center">입점률</td>';
			
			
			var headLen =columnArr.length;
//			var tlen = headLen * 2;
			var cols="";
//			cols+='<col width="100px;" style="min-width: 100px;" >';
//			cols+='<col width="120px;" style="min-width: 120px;"  >';
//			cols+='<col width="135px;" style="min-width: 135px;"  >';
//			cols+='<col width="60px;" style="min-width: 60px;"  >';
//			cols+='<col width="45px;" style="min-width: 45px;"  >';
			cols+='<col span="'+headLen+'" width="*" >';
//			var width = $("#displayTable20105").width();
//			$("#displayTable20105").css("width",((headLen*100)+450)+"px");
			$("#displayTable20105").css("width",((headLen*90))+"px");
			$("#trtColgroup20105").html(cols);
			
			
			var headColFixed ="";
			headColFixed +='<tr>';
			headColFixed +='	<th rowspan="2" style="max-width: 90px;" >고객그룹</th>';
			headColFixed +='	<th rowspan="2" style="max-width: 120px;" >관리업체</th>';
			headColFixed +='	<th rowspan="2" style="max-width: 135px;" >매장명</th>';
			headColFixed +='	<th rowspan="2" style="max-width: 60px;">사원명</th>';
			headColFixed +='	<th rowspan="2" style="max-width: 55px;">차수</th>';
			headColFixed +='</tr>';
			
//			var tempHeadCol ="";
			for (var i = 0; i < headLen; i++) {
				var colunm_id = columnArr[i].column_id;
				var group_id = columnArr[i].group_id;
				var group_nm = columnArr[i].group_nm;
//				var item_id = columnArr[i].item_id;
//				var item_nm = columnArr[i].item_nm;
				
				
//				console.log("tempHeadCol : " + tempHeadCol+ " group_id : " + group_id);
//				if(tempHeadCol != group_id){
					headColFirst +='	<th>'+group_nm+'</th>';
//				}
//				tempHeadCol = group_id;
				
//				headColSecond +='	<th class="child-group'+group_id+'"  data-group-id="'+group_id+'" >'+item_nm+'</th>';
				columns.push(colunm_id+"_"+group_id);
			}
			
			headColFirst +='</tr>';
//			headColSecond +='</tr>';
			
			
//			$("#trtThead20105").html(headColFirst + headColSecond);
			$("#fixedThead20105").html(headColFixed);
			$("#trtThead20105").html(headColFirst );
//			var firstGroupArr = $("#headColFirstGgroup20105 th.parent-group");
//			
//			for (var i = 0; i < firstGroupArr.length; i++) {
//				var node =firstGroupArr.eq(i);
//				var groupId = node.data("group-id");
//				var rowspan = $("#headColSecondGroup20105").find(".child-group"+groupId).length;
//				node.attr("colspan",rowspan);
//			}
			
			
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
							var sumval = sumArr[i][column];
							var innerSum = 0;
							if(sumval !== undefined){
								if(sumval > 0 ){
									//innerSum = parseInt((parseInt(sumval)/totRow).toFixed(2)*100);
									innerSum = (parseFloat((parseFloat(sumval)/totRow).toFixed(2)*100)).toFixed(0); //엑셀이랑 맞추기 위해 float로 계산
								}
							}
							footCol +='<td class="txt_right">'+innerSum+'%</td>';
						}
					}
					bodyRows +='</tr>';
					bodyRowsFixed +='</tr>';
				}
				$("#fixedTbody20105").html(bodyRowsFixed);
				$("#trtTbody20105").html(bodyRows);
				$("#trtTfoot20105").html(footCol);
//				var rateGroupArr = $("#displayTable20105").find(".main-group");
			}else{
				
				$("#trtTfoot20105").html("");
				$("#fixedTbody20105").html("<tr><td colspan='5' >조회 된 Data가 없습니다.</td></tr>");
				$("#trtTbody20105").html("<tr><td colspan='"+(5+headLen)+"' >내용이 없습니다.</td></tr>");
			}
			$("#displayPageNavi20105").html(data.navi);
		}
	});
}

/* fnGetToday20105() 조회 연월 set
 A20161230 kks*/
function fnGetToday20105(){

	var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);

    var chan_val = year + '-' + mon;
    
    $('#yy_mm_20105').val(chan_val);

	$("#yy_mm_20105").winCal(
			{format: "yyyy-mm", 
			startView: 3, 
			minView: 3, 
			maxView: 3});

    return true;
}