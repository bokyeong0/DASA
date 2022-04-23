var rndOddPg = 1;

/**
 * @함수명: ready
 * @작성일: 2016. 12. 09
 * @작성자: 
 * @설명: 로딩 및 Event - 매장시황(순회MD)
 * @param 
 */
$(document).ready(function(){
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#rndOddPop10800").instancePopUp();
	
	$("#searchBaseDate10800").winCal(baseOptions);
	
	fnGetrndOddSearchComboBox("",1); // 소속가져오기
	
	// 이벤트 등록
	fnSetEventComponent();
	
	//fnGetrndOddList(rndOddPg); //자동조회
});


/**
 * @함수명: fnSetEventComponent
 * @작성일: 2016. 12. 09
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
    
    $("#searchBaseDate10800").val(toDate);

	$("#rndOddSearchBtn").click(function(){
		fnGetrndOddList("1");
	});
	$("#rndOddSearchResetBtn").click(function(){
		$("#searchSmName10800").val("");	//검색어
		$("#searchEmName10800").val("");	//검색어
		$("#searchOmCode10800").val("");	//검색어
		$("#searchTmCode10800").html("<option value=''>팀명</option>");	//검색어
		$("#searchBaseDate10800").val(toDate);	//검색어
		fnGetrndOddList("1");
	});
	
	// 닫기버튼
	$("#activityRndPreViewCloseXBtn").click(function(){
		$("#activityRndPreView10800").hide();
	});	
	
}



/**
 * @함수명: fnGetrndOddList
 * @작성일: 2016. 12. 12
 * @작성자: 
 * @설명: 매장시황(순회MD) 조회
 * @param 
 */
function fnGetrndOddList(currPg) {
	var smNm      = $("#searchSmName10800").val();	  //검색어
	var emNm      = $("#searchEmName10800").val();	  //검색어
	var omCode    = $("#searchOmCode10800").val();	  //검색어
	var tmCode    = $("#searchTmCode10800").val();	  //검색어
	var baseDate  = $("#searchBaseDate10800").val();  //검색어
	var params = {
			"smNm"      : smNm,
			"emNm"      : emNm,
			"omCode" 	: omCode,
			"tmCode" 	: tmCode,
			"baseDate"  : baseDate.dateReplace()
	}; 
	var rowSize = 30;			
	var fnName = "fnGetrndOddList";
	$.ajax({
		url      : "/rnd/rndOddMultiList",
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
			var rndOddLen    = data.rndOddList.length;
			var firstNo      = data.firstNo;
            var tsm_code     = "";
            var ssm_code     = "";
            var rowCnt       = 0;
            var temp_sm_code = "";
            
            if(rndOddLen > 0 ){
            	for (var i = 0; i < rndOddLen; i++) {
            		var rndOddVo = data.rndOddList[i];
            		var sm_code = rndOddVo.sm_code;
            		
            		if(temp_sm_code != sm_code){
            			html+='<tr>';
            			html+='<td>'+(firstNo-i)+'</td>';
            			html+='<td class="om_nm">'+rndOddVo.om_nm+'</td>';
            			html+='<td class="md_nm">'+rndOddVo.md_nm+'</td>';
            			html+='<td class="sm_nm">'+rndOddVo.sm_nm+'</td>';
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
            	$("#rndOddTbody10800").html(html);
            	$("#rndOddNavi10800").html(data.navi);
            	
            	for (var i = 0; i < rndOddLen; i++) {
            		var rndOddVo = data.rndOddList[i];
            		if(rndOddVo.drop_flag == "1"){
            			$("#rndOddTbody10800").find(".img-dang-"+rndOddVo.sm_code).append('<td class="odd-td"><img width="160px" src="http://www.dspams.co.kr'+rndOddVo.drop_img_url+'" class="odd-img" ></br>일시: '+rndOddVo.updt_de+'</td>');
            			$("#rndOddTbody10800").find(".etc-dang-"+rndOddVo.sm_code).append('<td class="odd-td enpre">'+rndOddVo.drop_partclr_matter+'</td>');
            		}else if(rndOddVo.drop_flag == "2"){
            			$("#rndOddTbody10800").find(".img-ta-"+rndOddVo.sm_code).append('<td class="odd-td" ><img width="160px" src="http://www.dspams.co.kr'+rndOddVo.drop_img_url+'" class="odd-img" ></br>일시: '+rndOddVo.updt_de+'</td>');
            			$("#rndOddTbody10800").find(".etc-ta-"+rndOddVo.sm_code).append('<td class="odd-td enpre">'+rndOddVo.drop_partclr_matter+'</td>');
            		}
            	}
            	$("#rndOddTbody10800").find("tr.odd-tr").each(function(){
            		var tdArr = $(this).children("td.odd-td");
            		 var addCnt = 5-(tdArr.length);
            		 for( var i = 0 ; i < addCnt ; i++){
            			 $(this).append("<td></td>");
            		 }
            	});
            	
            }else{
            	html += '<tr><td colspan="5"><b>조회된 데이터가 없습니다.<b></td></tr>';
            	$("#rndOddTbody10800").html(html);
            	$("#rndOddNavi10800").html(data.navi);
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
            
        	$("#rndOddTbody10800").find(".odd-img").click(function(){
        		var src = $(this).attr("src");
        		openLayer('activityRndPreView10800', src);
        		/*
        		$("#activityRndPreView10800").show();
        		$("#activityRndPreView10800").center();
        		$("#rndPrevImg").attr("src",src);*/
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
			$("#optionPop10800").show();
		}
	});
}

//검색 콤보 박스
function fnGetrndOddSearchComboBox(om_code ,om_orgnzt_se){
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
				$("#searchOmCode10800").html(listHtml);
				$("#searchOmCode10800").change(function(){
					fnGetrndOddSearchComboBox($(this).val(), 2);
				});
				if(parseInt(data.auth_flag) > 1){
					fnGetrndOddSearchComboBox($("#searchOmCode10800").val() ,2);
				}
			}else if(om_orgnzt_se==2){
				$("#searchTmCode10800").html(listHtml);
			}
	    }
	});
}
//팝업레이어 드래그 A20161215 k2s
$( function() {
	$("#activityRndPreView10800").draggable();
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
			$("#rndPrevImg").attr("src",src);
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
