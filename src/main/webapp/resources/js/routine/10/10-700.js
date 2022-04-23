var fixOddPg = 1;
var currBaseDate10700 = "";
/**
 * @함수명: ready
 * @작성일: 2016. 12. 15
 * @작성자: 
 * @설명: 로딩 및 Event - 매장시황(고정MD)
 * @param 
 */
$(document).ready(function(){
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#activityFixPreView10700").instancePopUp();
	
	/*M20180118 k2s 일자조회 깨짐 현상으로 위치 이동 처리 함 사용하지 않은 달력 부분 주석 처리 함*/
//	$("#searchBaseDate10700").winCal(baseOptions);
	/* add 20170120 kks start!!! */
	//DB SYSDATE
	var sysdate = getSysDate(2);
	currBaseDate10700 = sysdate;
	
	//오늘날짜의 기준일자 설정
	currBaseDate10700 = sysdate.setBaseDate("first");
	//검색조건에 기준일자 등록
	$("#selectBaseDate").val(currBaseDate10700);
	
	/* add 20170120 kks end!!! */
	
	fnGetfixOddSearchComboBox("",1); // 소속가져오기
	
	// 이벤트 등록
	fnSetEventComponent();
	
	//fnGetfixOddList(fixOddPg); //자동조회
});


/**
 * @함수명: fnSetEventComponent
 * @작성일: 2016. 12. 15
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	//오늘일자
	var now = new Date();
    var yy = now.getFullYear();
    var mm = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    var dd = (now.getDate())>9 ? ''+(now.getDate()) : '0'+(now.getDate());  //M20170103 kks
    var toDate = yy+'-'+mm+'-'+dd;
    
//    $("#searchBaseDate10700").val(toDate);
    
	// 닫기버튼
	$("#fixOddPopCloseX10700, #fixOddPopClose10700").click(function(){
		$("#fixOddPop10700").hide();
	});
	
	$("#fixOddSearchBtn").click(function(){
		fnGetfixOddList("1");
	});
	
	$("#fixOddSearchResetBtn").click(function(){
		$("#searchSmName10700").val("");	//검색어
		$("#searchEmName10700").val("");	//검색어
		$("#searchOmCode10700").val("");	//검색어
		$("#searchTmCode10700").html("<option value=''>팀명</option>");	//검색어
//		$("#searchBaseDate10700").val(toDate);	//검색어
		fnGetfixOddList("1");
	});
	
	$("#activityFixPreViewCloseXBtn").click(function(){
		$("#activityFixPreView10700").hide();
	});	
	
	var prevFirst =  $("#selectBaseDate").val().setBaseDate("first");
	var prevLast = prevFirst.setBaseDate("last");
	$("#diaryDate").html(prevFirst+" ~ "+prevLast);
	
	if(currBaseDate10700 == prevFirst){
		$("#diaryNext").addClass("last-base-date");
	}else{
		$("#diaryNext").removeClass("last-base-date");
	}
	
	//이전업무일지 add 20170120
	$("#diaryPrev").click(function(){
			var baseDate  =  $("#selectBaseDate").val().setBaseDate("prev");
			var prevFirst = baseDate;
			var prevLast  = baseDate.setBaseDate("last");
			$("#selectBaseDate").val(baseDate);
			$("#diaryDate").html(prevFirst+" ~ "+prevLast);
			$("#diaryNext").removeClass("last-base-date");
			console.log($("#selectBaseDate").val());   //log
			fnGetfixOddList("1");  //자동조회
	});	
	
	//다음업무일지  add 20170120
	$("#diaryNext").click(function(){
			if(currBaseDate10700 == $("#selectBaseDate").val()){
				alert("마지막 데이터 입니다.");
				return;
			}
			var baseDate =  $("#selectBaseDate").val().setBaseDate("next");
			var nextFirst = baseDate;
			var nextLast = baseDate.setBaseDate("last");
			$("#selectBaseDate").val(baseDate);
			$("#diaryDate").html(nextFirst+" ~ "+nextLast);
			
			if(currBaseDate10700 == $("#selectBaseDate").val()){
				$(this).addClass("last-base-date");
			}else{
				$(this).removeClass("last-base-date");
			}
			console.log($("#selectBaseDate").val());  //log
			fnGetfixOddList("1");  //자동조회
	});	
	
	//오늘일자 add 20170120
	$("#diaryDate").click(function(){
		//DB SYSDATE
		var sysdate = getSysDate(2);
		currBaseDate10700 = sysdate;
		
		//오늘날짜의 기준일자 설정
		currBaseDate10700 = sysdate.setBaseDate("first");
		//검색조건에 기준일자 등록
		$("#selectBaseDate").val(currBaseDate10700);	
		
		var prevFirst =  $("#selectBaseDate").val().setBaseDate("first");
		var prevLast = prevFirst.setBaseDate("last");
		$("#diaryDate").html(prevFirst+" ~ "+prevLast);
		
		if(currBaseDate10700 == prevFirst){
			$("#diaryNext").addClass("last-base-date");
		}else{
			$("#diaryNext").removeClass("last-base-date");
		}
		
		console.log($("#selectBaseDate").val());  //log
		fnGetfixOddList("1");  //자동조회		
	});
	
}



/**
 * @함수명: fnGetfixOddList
 * @작성일: 2016. 12. 15
 * @작성자: 
 * @설명: 매장시황(고정MD) 조회
 * @param 
 */
function fnGetfixOddList(currPg) {
	var smNm      = $("#searchSmName10700").val();	  //검색어
	var emNm      = $("#searchEmName10700").val();	  //검색어
	var omCode    = $("#searchOmCode10700").val();	  //검색어
	var tmCode    = $("#searchTmCode10700").val();	  //검색어
	var baseDate  = $("#selectBaseDate").val();  //검색어
	
/*	M20170414 kks 지점선택없이 수행 가능하도록 처리 함
 * if(omCode == null || omCode == "") {
		alert("지점을 선택 후 수행 하셔야 합니다.!!!");
		return false;
	}*/
	var params = {
			"smNm"      : smNm,
			"emNm"      : emNm,
			"omCode" 	: omCode,
			"tmCode" 	: tmCode,
			"baseDate"  : baseDate.dateReplace()
	}; 
	
	var rowSize = 30;			
	var fnName = "fnGetfixOddList";
	$.ajax({
		url      : "/fixing/fixOddMultiList",
		type     : "POST",
		dataType : "json",
		data:{"fnName" :fnName
			, "params" :params
			, "rowSize":rowSize
			, "currPg" :currPg
		},
		cache   : false,
		success : function(data) {
			
			var html         = '';
			var fixOddLen    = data.fixOddList.length;
			var firstNo      = data.firstNo;
            var tsm_code     = "";
            var ssm_code     = "";
            var rowCnt       = 0;
            var temp_sm_code = "";
            
            if(fixOddLen > 0 ){
            	for (var i = 0; i < fixOddLen; i++) {
            		var fixOddVo = data.fixOddList[i];
            		var sm_code = fixOddVo.sm_code;
            		
            		if(temp_sm_code != sm_code){
            			html+='<tr>';
            			html+='<td>'+(firstNo-i)+'</td>';
            			html+='<td class="om_nm">'+fixOddVo.om_nm+'</td>';
            			html+='<td class="md_nm">'+fixOddVo.md_nm+'</td>';
            			html+='<td class="sm_nm">'+fixOddVo.sm_nm+'</td>';
            			html+='<td class="odd-inner-tb">';
            			html+='<table>                ';
            			html+='<colgroup>                             ';
            			html+='	<col width="10%" >                   ';
            			html+='	<col width="18%" >                   ';
            			html+='	<col width="18%" >                   ';
            			html+='	<col width="18%" >                   ';
            			html+='	<col width="18%" >                   ';
            			html+='	<col width="18%" >                   ';
            			html+='</colgroup>                            ';
            			html+='	<tbody>                              ';
            			html+='		<tr class="odd-tr img-dang-'+sm_code+'" ><th rowspan="2" >당사</th></tr> ';
            			html+='		<tr class="odd-tr etc-dang-'+sm_code+'" ></tr>  ';
            			html+='		<tr class="odd-tr img-ta-'+sm_code+'" ><th rowspan="2" >경쟁사</th></tr> ';
            			html+='		<tr class="odd-tr etc-ta-'+sm_code+'" ></tr>  ';
            			html+='	</tbody>                             ';
            			html+='</table>                               ';
            			html+='</td>';
            			html+='</tr>';
            			temp_sm_code = sm_code;
            		}
            	}
            	$("#fixOddTbody10700").html(html);
            	$("#fixOddNavi10700").html(data.navi);
            	
            	for (var i = 0; i < fixOddLen; i++) {
            		var fixOddVo = data.fixOddList[i];
            		if(fixOddVo.dfop_flag == "1"){
            			$("#fixOddTbody10700").find(".img-dang-"+fixOddVo.sm_code).append('<td class="odd-td"><img width="160px" src="'+fixOddVo.dfop_img_url+'" class="odd-img" ></br>일시: '+fixOddVo.updt_de+'</td>');
            			$("#fixOddTbody10700").find(".etc-dang-"+fixOddVo.sm_code).append('<td class="odd-td enpre">'+fixOddVo.dfop_partclr_matter+'</td>');
            		}else if(fixOddVo.dfop_flag == "2"){
            			$("#fixOddTbody10700").find(".img-ta-"+fixOddVo.sm_code).append('<td class="odd-td" ><img width="160px" src="'+fixOddVo.dfop_img_url+'" class="odd-img" ></br>일시: '+fixOddVo.updt_de+'</td>');
            			$("#fixOddTbody10700").find(".etc-ta-"+fixOddVo.sm_code).append('<td class="odd-td enpre">'+fixOddVo.dfop_partclr_matter+'</td>');
            		}
            	}
            	$("#fixOddTbody10700").find("tr.odd-tr").each(function(){
            		var tdArr = $(this).children("td.odd-td");
            		 var addCnt = 5-(tdArr.length);
            		 for( var i = 0 ; i < addCnt ; i++){
            			 $(this).append("<td></td>");
            		 }
            	});
            	
            }else{
            	html += '<tr><td colspan="5"><b>조회된 데이터가 없습니다.<b></td></tr>';
            	$("#fixOddTbody10700").html(html);
            	$("#fixOddNavi10700").html(data.navi);
			}
			
            $(".om_nm").each(function () {
            	var rows = $(".om_nm:contains('" + $(this).text() + "')");
            	if (rows.length > 1) {
            		rows.eq(0).attr("rowspan", rows.length);
            		rows.not(":eq(0)").remove();
            	} 
            });
            $(".md_nm").each(function () {
            	var rows = $(".md_nm:contains('" + $(this).text() + "')");
            	if (rows.length > 1) {
            		rows.eq(0).attr("rowspan", rows.length);
            		rows.not(":eq(0)").remove();
            	} 
            });			
            $(".sm_nm").each(function () {
            	var rows = $(".sm_nm:contains('" + $(this).text() + "')");
            	if (rows.length > 1) {
            		rows.eq(0).attr("rowspan", rows.length);
            		rows.not(":eq(0)").remove();
            	} 
            });			
            
        	$("#fixOddTbody10700").find(".odd-img").click(function(){
        		var src = $(this).attr("src");
        		openLayer('activityFixPreView10700', src);
        		/*
        		$("#activityFixPreView10700").show();
        		$("#activityFixPreView10700").center();
        		$("#fixPrevImg").attr("src",src);*/
        	});
		}
	});
}

/**
 * @함수명: fnGetCodeList
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 코드목록 조회
 * @param 
 */
function fnGetCodeView(optionCode) {
	$.ajax({
		url : "/code/codeView",
		type : "POST",
		dataType : "json",
		data : {"c_code" : optionCode},
		cache : false,
		success : function(data) {
			$("#optionCode").val(data.c_code);
			$("#optionTypeName").val(data.c_parent_code_name);
			$("#cName").val(data.c_name);
			$("#cDesc").val(data.c_desc);
			$("#cOrder").val(data.c_order);
			$("#cExt1").val(data.c_ext1);
			$("#cExt2").val(data.c_ext2);
			$("#cExt3").val(data.c_ext3);
			$("input:radio[name=cSystemCode]:input[value="+ data.c_is_system_code +"]").prop("checked", true);
			$("input:radio[name=optionUse]:input[value="+ data.c_is_use +"]").prop("checked", true);
			$("#optionTypeName").html(data.c_parent_name_list);
			$("#optionType").val(data.c_parent_code);
			$("#optionTitle").text("공통코드 수정");
			$("#optionPop10700").show();
		}
	});
}

//검색 콤보 박스
function fnGetfixOddSearchComboBox(om_code ,om_orgnzt_se){
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
				$("#searchOmCode10700").html(listHtml);
				$("#searchOmCode10700").change(function(){
					fnGetfixOddSearchComboBox($(this).val(), 2);
				});
				if(parseInt(data.auth_flag) > 1){
					fnGetfixOddSearchComboBox($("#searchOmCode10700").val() ,2);
				}
			}else if(om_orgnzt_se==2){
				$("#searchTmCode10700").html(listHtml);
			}
	    }
	});
}
//팝업레이어 드래그 A20161215 k2s
$( function() {
	$("#activityFixPreView10700").draggable();
} );	

//팝업레이어 위치 조절
function openLayer(targetID, paramSrc){
	var $layer = $('#'+targetID);
	var $close = $layer.find('.close');
	var src    = paramSrc;
	
	if(!$layer.is(':visible')){
		$layer.show();
		$layer.center();
		if (src != null || src != "") {
			$("#fixPrevImg").attr("src",src);
		}
	}

	$close.bind('click',function(){
		if($layer.is(':visible')){
			$layer.hide();
		}
		return false;
	});
}
//팝업레이어 위치 조절(중앙)
jQuery.fn.center = function () {
    this.css("position","absolute");
    this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + $(window).scrollTop()) + "px");
    this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) + $(window).scrollLeft()) + "px");
    return this;
}

