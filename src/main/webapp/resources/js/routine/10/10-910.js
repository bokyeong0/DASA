var fixPdImgPg = 1;
var currBaseDate10910 = "";
/**
 * @함수명: ready
 * @작성일: 2018. 03. 07
 * @작성자: 
 * @설명: 로딩 및 Event - PD매대 사진(고정MD)
 * @param 
 */
$(document).ready(function(){
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#activityFixPreView10910").instancePopUp();
	
	//DB SYSDATE
	var sysdate = getSysDate(2);
	currBaseDate10910 = sysdate;
	
	//오늘날짜의 기준일자 설정
	currBaseDate10910 = sysdate.setBaseDate("first");
	//검색조건에 기준일자 등록
	$("#selectBaseDate10910").val(currBaseDate10910);
	
	fnGetfixPdImgSearchComboBox("",1); // 소속가져오기
	
	// 이벤트 등록
	fnSetEventComponent();
	
	//fnGetfixPdImgList(fixPdImgPg); //자동조회
});

function fnSetEventComponent(){
	//오늘일자
	var now = new Date();
    var yy = now.getFullYear();
    var mm = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    var dd = (now.getDate())>9 ? ''+(now.getDate()) : '0'+(now.getDate());  //M20170103 kks
    var toDate = yy+'-'+mm+'-'+dd;
    
	// 조회
	$("#fixPdImgSearchBtn").click(function(){
		fnGetfixPdImgList("1");
	});
	
	$("#fixPdImgSearchResetBtn").click(function(){
		$("#searchSmName10910").val("");	//검색어
		$("#searchEmName10910").val("");	//검색어
		$("#searchOmCode10910").val("");	//검색어
		$("#searchTmCode10910").html("<option value=''>팀명</option>");	//검색어
//		$("#searchBaseDate10910").val(toDate);	//검색어
		fnGetfixPdImgList("1");
	});
	// 닫기버튼
	$("#activityFixPreViewCloseXBtn10910").click(function(){
		$("#activityFixPreView10910").hide();
	});	
	
	var prevFirst =  $("#selectBaseDate10910").val().setBaseDate("first");
	var prevLast = prevFirst.setBaseDate("last");
	$("#diaryDate10910").html(prevFirst+" ~ "+prevLast);
	
	if(currBaseDate10910 == prevFirst){
		$("#diaryNext10910").addClass("last-base-date");
	}else{
		$("#diaryNext10910").removeClass("last-base-date");
	}
	
	//이전업무일지 add 20170120
	$("#diaryPrev10910").click(function(){
			var baseDate  =  $("#selectBaseDate10910").val().setBaseDate("prev");
			var prevFirst = baseDate;
			var prevLast  = baseDate.setBaseDate("last");
			$("#selectBaseDate10910").val(baseDate);
			$("#diaryDate10910").html(prevFirst+" ~ "+prevLast);
			$("#diaryNext10910").removeClass("last-base-date");
//			console.log($("#selectBaseDate10910").val());   //log
			fnGetfixPdImgList("1");  //자동조회
	});	
	
	//다음업무일지  add 20170120
	$("#diaryNext10910").click(function(){
			if(currBaseDate10910 == $("#selectBaseDate10910").val()){
				alert("마지막 데이터 입니다.");
				return;
			}
			var baseDate =  $("#selectBaseDate10910").val().setBaseDate("next");
			var nextFirst = baseDate;
			var nextLast = baseDate.setBaseDate("last");
			$("#selectBaseDate10910").val(baseDate);
			$("#diaryDate10910").html(nextFirst+" ~ "+nextLast);
			
			if(currBaseDate10910 == $("#selectBaseDate10910").val()){
				$(this).addClass("last-base-date");
			}else{
				$(this).removeClass("last-base-date");
			}
//			console.log($("#selectBaseDate10910").val());  //log
			fnGetfixPdImgList("1");  //자동조회
	});	
	
	//오늘일자 add 20170120
	$("#diaryDate10910").click(function(){
		//DB SYSDATE
		var sysdate = getSysDate(2);
		currBaseDate10910 = sysdate;
		
		//오늘날짜의 기준일자 설정
		currBaseDate10910 = sysdate.setBaseDate("first");
		//검색조건에 기준일자 등록
		$("#selectBaseDate10910").val(currBaseDate10910);	
		
		var prevFirst =  $("#selectBaseDate10910").val().setBaseDate("first");
		var prevLast = prevFirst.setBaseDate("last");
		$("#diaryDate10910").html(prevFirst+" ~ "+prevLast);
		
		if(currBaseDate10910 == prevFirst){
			$("#diaryNext10910").addClass("last-base-date");
		}else{
			$("#diaryNext10910").removeClass("last-base-date");
		}
		
//		console.log($("#selectBaseDate10910").val());  //log
		fnGetfixPdImgList("1");  //자동조회		
	});
	
}


function fnGetfixPdImgList(currPg) {
	var smNm      = $("#searchSmName10910").val();	  //검색어
	var emNm      = $("#searchEmName10910").val();	  //검색어
	var omCode    = $("#searchOmCode10910").val();	  //검색어
	var tmCode    = $("#searchTmCode10910").val();	  //검색어
	var baseDate  = $("#selectBaseDate10910").val();  //검색어
	
	var params = {
			"smNm"      : smNm,
			"emNm"      : emNm,
			"omCode" 	: omCode,
			"tmCode" 	: tmCode,
			"baseDate"  : baseDate.dateReplace()
	}; 
	var rowSize = 30;			
	var fnName = "fnGetfixPdImgList";
	$.ajax({
		url      : "/pdImg/fixList",
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
			var fixPdImgLen    = data.pdImgFixList.length;
			
			var firstNo      = data.firstNo;
            
            if(fixPdImgLen > 0 ){
            	for (var i = 0; i < fixPdImgLen; i++) {
            		var pdImgFix = data.pdImgFixList[i];
            		var sm_code = pdImgFix.sm_code;
            		
        			html+='<tr>';
        			html+='<td>'+(firstNo-i)+'</td>';
        			html+='<td class="fixPd_om_nm">'+pdImgFix.om_nm+'</td>';
        			html+='<td class="fixPd_md_nm">'+pdImgFix.em_nm+'</td>';
        			html+='<td class="fixPd_sm_nm">'+pdImgFix.sm_nm+'</td>';
        			html+="<td><img src='"+pdImgFix.fix_pd_img_url+"' class='fixPd-img' /></td>";
        			html+='</tr>';
            	}
            	
            }else{
            	html += '<tr><td colspan="5"><b>조회된 데이터가 없습니다.<b></td></tr>';
			}
            $("#fixPdImgTbody10910").html(html);
            $("#fixPdImgNavi10910").html(data.navi);
			
//            $(".fixPd_om_nm").each(function () {
//            	var rows = $(".fixPd_om_nm:contains('" + $(this).text() + "')");
//            	if (rows.length > 1) {
//            		rows.eq(0).attr("rowspan", rows.length);
//            		rows.not(":eq(0)").remove();
//            	} 
//            });
//            $(".fixPd_md_nm").each(function () {
//            	var rows = $(".fixPd_md_nm:contains('" + $(this).text() + "')");
//            	if (rows.length > 1) {
//            		rows.eq(0).attr("rowspan", rows.length);
//            		rows.not(":eq(0)").remove();
//            	} 
//            });			
//            $(".fixPd_sm_nm").each(function () {
//            	var rows = $(".fixPd_sm_nm:contains('" + $(this).text() + "')");
//            	if (rows.length > 1) {
//            		rows.eq(0).attr("rowspan", rows.length);
//            		rows.not(":eq(0)").remove();
//            	} 
//            });			
            
        	$("#fixPdImgTbody10910").find(".fixPd-img").click(function(){
        		var src = $(this).attr("src");
        		openLayer('activityFixPreView10910', src);
        	});
		}
	});
}

//검색 콤보 박스
function fnGetfixPdImgSearchComboBox(om_code ,om_orgnzt_se){
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
				$("#searchOmCode10910").html(listHtml);
				$("#searchOmCode10910").change(function(){
					fnGetfixPdImgSearchComboBox($(this).val(), 2);
				});
				if(parseInt(data.auth_flag) > 1){
					fnGetfixPdImgSearchComboBox($("#searchOmCode10910").val() ,2);
				}
			}else if(om_orgnzt_se==2){
				$("#searchTmCode10910").html(listHtml);
			}
	    }
	});
}
//팝업레이어 드래그 
$( function() {
	$("#activityFixPreView10910").draggable();
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

