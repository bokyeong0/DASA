
var pg_10601 = 1;
var pg_all_10601 = 1;
var arr10601;
var ssdaArray =  new Array();
var fulldayArray = new Array();
/**
 * @함수명: onLoad
 * @작성일: 2017.10.11
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	$("#yy_mm_10601").winCal(  //달력control
			{format: "yyyy-mm", 
				startView: 3, 
				minView: 3, 
				maxView: 3});	
	
	fnMakeEvent_10601();	   //버튼 이벤트 등록
	
	cbo1_10601();              //지점콤보
	
	controlInit_10601();       //초기화
	
	//fnSelectList_10601(1);   //리스트 조회
	
	
});

/**
 * @함수명: fnMakeEventComponent_10601
 * @작성일: 2017.10.11
 * @작성자: k2s
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEvent_10601(){
	$('#yy_mm_10601').val(fnGetToday10601());
	
	//조회
	$("#btnSearch_10601").click(function(){
		fnSelectList_10601(1);
	});
	
	//초기화
	$("#btnClear_10601").click(function(){
		controlInit_10601();
	});
	
	//엑셀
	$("#btnExcel_10601").click(function(){
		
		var params ="";
		
		var y = parseInt($("#yy_mm_10601").val().substring(0,4));
		var m = parseInt($("#yy_mm_10601").val().substring(5,7));
		
		var lastDay = new Date(y, m, 0).getDate();
		
		params="bhf_code=" + $("#om_code_10601").val();
		params+="&bhf_name=" + encodeURI(encodeURIComponent($.trim($('#om_code_10601 option:selected').text())));
		params+="&yy=" + $('#yy_mm_10601').val().substring(0,4);
		params+="&mm=" + $("#yy_mm_10601").val().substring(5,7);
		params+="&yyyyMm=" + $('#yy_mm_10601').val().replace('-','');
		params+="&em_id=" + $('#em_no_10601').val();
		params+="&em_nm=" + encodeURI(encodeURIComponent($('#em_nm_10601').val()));
		params+="&last_day=" + lastDay;
		
		var address = "/activity/selectTeamLeaderMonthlyExcelList?" + params;
		location.href = address;
	});
	
	//인쇄
	$("#btnPrint_10601").click(function(){
	});
	
}

/**
 * @함수명: cbo1_10601
 * @작성일: 2017.10.11
 * @작성자: k2s
 * @설명: 지점 콤보 리스트
 * @param 
 */
function cbo1_10601(){
	$.ajax({
		url : "/organization/auth",
	    data:{"om_code":"", "om_orgnzt_se": "1"},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
	    	var listHtml = ""; //"<option value='' selected='selected'>선택</option>";
			if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			listHtml += "<option value ='"+res.om_code+"' data-om_innb ='"+res.om_code+"' >"+ res.om_nm +"</option>";
	    		}
			}

			$('#om_code_10601').html(listHtml);
	    }
	});
}

function fnSelectList_10601(currPg){
	
//	getInputDayLabel_10601();  //header
 
	var y = parseInt($("#yy_mm_10601").val().substring(0,4));
	var m = parseInt($("#yy_mm_10601").val().substring(5,7));
	
	var strYyMm = $.trim($("#yy_mm_10601").val().substring(0,4)) + $.trim($("#yy_mm_10601").val().substring(5,7));
	
	var lastDay = (new Date(y ,m ,0)).getDate();
	console.log("lastDay:  "+lastDay);
	
	pg_10601 = currPg;//현재페이지 리로드
	var fnName = "fnSelectList_10601";//페이징처리 함수명
	var rowSize = 20;//표시할 행수	
	var params = {
				em_id: $.trim($("#em_no_10601").val()),
		    	bhf_code: $.trim($("#om_code_10601").val()),
		    	em_nm: $.trim($("#em_nm_10601").val()),
		    	yyyyMm: strYyMm
			};
    	
	$.ajax({
		url : "/activity/selectTeadmLeaderMonthlyList",
		data:{
			  //하단 페이징 처리
			  "fnName" : fnName,
			  "params" : params,
			  "rowSize" : rowSize,
			  "currPg" : currPg,
			  },
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			var l_listHtml ="";
			var r_listHtml ="";
			if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					var vo = data.result[i];     
					
					l_listHtml += "<tr style='height :29px;'>";
					l_listHtml += "	<td class='txt_center' data-code=''>"+(parseInt(data.firstNo)-i)+"</td>";
					l_listHtml += "	<td data-code=''>"+ vo.em_id +"</td>";
					l_listHtml += "	<td data-code=''>"+ vo.em_nm +"</td>";	
					l_listHtml += "</tr>";
					
					r_listHtml += "<tr class='child-td-pink' id="+vo.em_no+" style='height: 29px;'>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_01+"'>"+ vo.day_01 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_02+"'>"+ vo.day_02 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_03+"'>"+ vo.day_03 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_04+"'>"+ vo.day_04 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_05+"'>"+ vo.day_05 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_06+"'>"+ vo.day_06 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_07+"'>"+ vo.day_07 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_08+"'>"+ vo.day_08 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_09+"'>"+ vo.day_09 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_10+"'>"+ vo.day_10 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_11+"'>"+ vo.day_11 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_12+"'>"+ vo.day_12 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_13+"'>"+ vo.day_13 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_14+"'>"+ vo.day_14 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_15+"'>"+ vo.day_15 +"</td>";					
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_16+"'>"+ vo.day_16 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_17+"'>"+ vo.day_17 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_18+"'>"+ vo.day_18 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_19+"'>"+ vo.day_19 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_20+"'>"+ vo.day_20 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_21+"'>"+ vo.day_21 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_22+"'>"+ vo.day_22 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_23+"'>"+ vo.day_23 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_24+"'>"+ vo.day_24 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_25+"'>"+ vo.day_25 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_26+"'>"+ vo.day_26 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_27+"'>"+ vo.day_27 +"</td>";
					
					if (lastDay == 28) {
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_28+"' >"+ vo.day_28 +"</td>";
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_29+"' style=display:none>"+ vo.day_29 +"</td>";
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_30+"' style=display:none>"+ vo.day_30 +"</td>";
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_31+"' style=display:none>"+ vo.day_31 +"</td>";
					}else if (lastDay == 29) {
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_28+"' >"+ vo.day_28 +"</td>";
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_29+"' >"+ vo.day_29 +"</td>";
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_30+"' style=display:none>"+ vo.day_30 +"</td>";
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_31+"' style=display:none>"+ vo.day_31 +"</td>";
					}else if (lastDay == 30) {
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_28+"' >"+ vo.day_28 +"</td>";
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_29+"' >"+ vo.day_29 +"</td>";
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_30+"' >"+ vo.day_30 +"</td>";
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_31+"' style=display:none>"+ vo.day_31 +"</td>";
					}else if (lastDay == 31) {
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_28+"' >"+ vo.day_28 +"</td>";
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_29+"' >"+ vo.day_29 +"</td>";
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_30+"' >"+ vo.day_30 +"</td>";
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_31+"' >"+ vo.day_31 +"</td>";
					}
					
					r_listHtml += "</tr>";
	        	}
			}else{
				l_listHtml += "<tr>";
				l_listHtml += "	<td class = 'txt_center' colspan='"+ (3) + "' >조회된 내용이 없습니다.</td>";
				l_listHtml += "</tr>";
				
				r_listHtml += "<tr>";
				r_listHtml += "	<td class = 'txt_center' colspan='"+ (lastDay+15) + "' >조회된 내용이 없습니다.</td>";
				r_listHtml += "</tr>";
			}	
			
			$("#tbody10601_left").html(l_listHtml);
			$("#tbody10601_right").html(r_listHtml);
			/*M20170829 k2s 퇴근의 경우 빨간색 문자로 대체*/
			$("#tbody10601_right").find("td.txt_center:contains('퇴근')").each(function(){
				var text = $(this).text().split(" ");
				$(this).html("<i style='color:#2f878a;'>"+text[0]+"</i> <i style='color:#E63E5D;'>"+text[1].replace("퇴근","")+"<i>");
			});
			
			headerControl_10601();  //header
			
			if(data!=null && data.result.length > 0){
				setColor_10601();       //색상 set
			}
			 
			$("#tbody10601_right").find("tr.child-td-pink").each(function(){
				
				for (var i = 0; i < ssdaArray.length; i++) {
					var target = $(this).find("td:nth-child("+ssdaArray[i]+")");//.eq(ssdaArray[i]);
					if(target.text() == ""){
						target.text("휴무");
						target.css("color", '#2f878a');
						target.css("background-color", '#f5f7f9');
					}
				}
				for (var i = 0; i < fulldayArray.length; i++) {
					var target = $(this).find("td:nth-child("+fulldayArray[i]+")");
					if(target.text() == ""){
						target.text("휴무");
						target.css("color", '#2f878a');
						target.css("background-color", '#f5f7f9');
					}
				}
			});			
			
			$("#navi10601").html(data.navi);
	    }
	});
}

/**
 * @함수명: getCommonCode_106015
 * @작성일: 2017.10.11
 * @작성자: k2s
 * @설명: 공통코드
 * @param 
 */
function setColor_10601(){
	
	$.ajax({
		url : "/code/codeComboBox",
		data:{"c_parent_code":"0000000058"},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			if(data!=null && data.length > 0){
				arr10601 = new Array(data.length);
				for(var i=0; i<data.length; i++){
					var codeVo = data[i];     
					arr10601[i] = new Array(codeVo.c_ext1, codeVo.c_ext2);
				}
			}
	    }
	});
	
	//색상set
	$('#tbody10601_right tr').each(function(index) {
		 var $tds = $(this).find('td');
		 for (var i = 0; i < $tds.length; i++){
			 var tmp = $tds.eq(i).data("code");
			 var tmp2 = $tds.eq(i).text();
			 if(tmp!=''){
				 for (var j = 0; j < arr10601.length; j++) {
					if(tmp.substring(0,2)==arr10601[j][0]){
						$tds.eq(i).css("background-color", arr10601[j][1]);
					}
				 }
			 }
			 /*else{
				 $tds.eq(i).css("background-color",'#ffffff');
			 }*/
			 if(tmp2=='휴'){
				 $tds.eq(i).css("background-color",'#f5f7f9');
			 }
		 }
	});
}
/**
 * @함수명: fnSelectList_10601
 * @작성일: 2017.10.11
 * @작성자: k2s
 * @설명: 리스트 조회
 * @param 
 */
function fnSelectListA_10601(){
	$.ajax({
		url : "/activity/teamleader",
	    data:{em_no: $("#em_no").val(),
	    	bhf_code: $("#om_code_10601").val(),
	    	em_nm: $("#em_nm_10601").val(),
	    	yy: $("#yy_mm_10601").val().substring(0,4),
	    	mm: $("#yy_mm_10601").val().substring(5,7)},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
	    	
		var listHtml = "";
		
		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var res = data.result[i];     
				listHtml += "<tr>";
        		listHtml += "<td>" + res.cm_code + "</td>";
    			listHtml += "<td class='txt_left'>" + res.cm_nm + "</td>";
    			listHtml += "<td>" + res.cm_sort_ordr + "</td>";
    			listHtml += "<td>" + res.use_at + "</td>";
    			listHtml += "<td>";
    			listHtml += "</td>";
    			listHtml += "</tr>";
        	}
		}else{
			
		}	
		$("#tbody10601").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: controlInit_10601
 * @작성일: 2017.10.11
 * @작성자: k2s
 * @설명: 화면 조회 조건 초기화
 * @param 
 */
function controlInit_10601(){
	
	$('#om_code_10601').val('');
	$('#em_nm_10601').val('');
	$('#em_no_10601').val('');
	$('#yy_mm_10601').val(fnGetToday10601());
	
	headerControl_10601();
	
	$('#em_nm_10601').focus();
	
	//fnSelectList_10601(1);
}

function headerControl_10601(){
	var y = parseInt($("#yy_mm_10601").val().substring(0,4));
	var m = parseInt($("#yy_mm_10601").val().substring(5,7));
	var lastDay = (new Date(y ,m ,0)).getDate();
	console.log("headerControl_10601 "+lastDay);
	
	if (lastDay < 28) $("#d28_10601, #w28_10601").css("display", 'none'); else $("#d28_10601, #w28_10601").css("display", '');
	if (lastDay < 29) $("#d29_10601, #w29_10601").css("display", 'none'); else $("#d29_10601, #w29_10601").css("display", '');
	if (lastDay < 30) $("#d30_10601, #w30_10601").css("display", 'none'); else $("#d30_10601, #w30_10601").css("display", '');
	if (lastDay < 31) $("#d31_10601, #w31_10601").css("display", 'none'); else $("#d31_10601, #w31_10601").css("display", '');
	
	getInputDayLabel_10601();
}


function getInputDayLabel_10601() {
	
	var week = new Array('일', '월', '화', '수', '목', '금', '토');
	
	var y = parseInt($("#yy_mm_10601").val().substring(0,4));
	var m = parseInt($("#yy_mm_10601").val().substring(5,7));
	var d ='';
	var $tds = $('#tbl10601_right thead tr:eq(0)').find('th');
	var fullday = ""; 
	
	/*
	var user_date = new Date(y,m, 1);
	var date = user_date.setDate(user_date.getDate()-1); 
	var month = user_date.getMonth();
	var year = user_date.getFullYear();
	
	if(month<10) month = "0" + month; 
	if(date<10) date = "0" + date; 
	var result = year + "-" + month + "-" + date; 
	 */
    ssdaArray = [];
    fulldayArray = [];
    
	for (var i = 0; i < $tds.length; i++){
		d = $tds.eq(i).text();
		day = new Date(y + '-' + m + '-' + d).getDay();
		fullday = (y) +''+ (m < 10 ? "0" + m: m)+''+ (d);0

		dayLabel = week[day];
		console.log("dayLabel ===>"+ dayLabel);
		$('#tbl10601_right thead tr:eq(1) th:nth-child('+ (i+1) +')').text(dayLabel);

		if(day==0){ //일
			$('#tbl10601_right thead tr:eq(1) th:nth-child('+ (i+1) +')').css('color','red');
			ssdaArray.push((i+1)+"");
		}else if(day==6){//토
			$('#tbl10601_right thead tr:eq(1) th:nth-child('+ (i+1) +')').css('color','blue');
			ssdaArray.push((i+1)+"");
		}else{
			$('#tbl10601_right thead tr:eq(1) th:nth-child('+ (i+1) +')').css('color','#4c4c4c');
		}
		
		console.log("ssdaArray ===>"+ ssdaArray);

		//fnLunarToSolar(fullday, (i+1));

	}
}

function fnLunarToSolar(fullday, step){
	$.ajax({
		url : "/activity/util",
	    data:{"fullday": fullday},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    async : false,
	    success : function(data) {
	    	if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					var res = data.result[i];     
					 if(res){  
						 $('#tbl10601_right thead tr:eq(1) th:nth-child('+ (step) +')').css('color','red');
						 fulldayArray.push(step);
						 return true;
				    }else{
				    	return false;
				    }
	        	}
			} 
	    }
	});
	    
}

function fnGetToday10601(){

	var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    
    /* test후 삭제
    var day = now.getDate();   // A20161129 k2s 일자
    if (mon == '12' && day > '15') {
    	year= now.getFullYear()+1;
    	mon = '01';
    }  // A20161129 k2s 12월인 경우 다음년도 설정 현재월16일~ 익월15일 익월 근태반영
    
    if (year== now.getFullYear()) {
	    if (day > '15') {
	    	mon = (now.getMonth()+2)>9 ? ''+(now.getMonth()+2) : '0'+(now.getMonth()+2);
	    }  
    } // A20161129 k2s 현재월16일~ 익월15일 익월 근태반영
    */
    
    var chan_val = year + '-' + mon;

    return chan_val;
}

