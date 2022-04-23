var rndPdImgPg = 1;

/**
 * @함수명: ready
 * @작성일: 2018. 03. 07
 * @작성자: 
 * @설명: 로딩 및 Event - PD매대 사진(순회MD)
 * @param 
 */
$(document).ready(function(){
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#activityRndPreView10920").instancePopUp();
	
	$("#searchStartDate10920").winCal(baseOptions);
	$("#searchEndDate10920").winCal(baseOptions);
	
	fnGetrndPdImgSearchComboBox("",1); // 소속가져오기
	
	// 이벤트 등록
	fnSetEventComponent();
	
	fnGetRndPdImgList(rndPdImgPg); //자동조회
});

function fnSetEventComponent(){

	$("#rndPdImgSearchBtn").click(function(){
		fnGetRndPdImgList("1");
	});
	$("#rndPdImgSearchResetBtn").click(function(){
		$("#searchSmName10920").val("");	//검색어
		$("#searchEmName10920").val("");	//검색어
		$("#searchOmCode10920").val("");	//검색어
		$("#searchTmCode10920").html("<option value=''>팀명</option>");	//검색어
		$("#searchStartDate10920").val("");	//검색어
		$("#searchEndDate10920").val("");	//검색어
		fnGetRndPdImgList("1");
	});
	
	// 닫기버튼
	$("#activityRndPreViewCloseXBtn10920").click(function(){
		$("#activityRndPreView10920").hide();
	});	
	
}

/**
 * @함수명: fnGetRndPdImgList
 * @작성일: 2018. 03. 07
 * @작성자: 
 * @설명: 매장시황(순회MD) 조회
 * @param 
 */
function fnGetRndPdImgList(currPg) {
	var smNm      = $("#searchSmName10920").val();	  //검색어
	var emNm      = $("#searchEmName10920").val();	  //검색어
	var omCode    = $("#searchOmCode10920").val();	  //검색어
	var tmCode    = $("#searchTmCode10920").val();	  //검색어
	var startDate  = $("#searchStartDate10920").val();  //검색어
	var endDate  = $("#searchEndDate10920").val();  //검색어
	var params = {
			"smNm"      : smNm,
			"emNm"      : emNm,
			"omCode" 	: omCode,
			"tmCode" 	: tmCode,
			"startDate"  : startDate.dateReplace(),
			"endDate"  : endDate.dateReplace()
	}; 
	var rowSize = 30;			
	var fnName = "fnGetRndPdImgList";
	$.ajax({
		url      : "/pdImg/rndList",
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
			var rndPdImgLen    = data.pdImgRndList.length;
			var firstNo      = data.firstNo;
            
            if(rndPdImgLen > 0 ){
            	for (var i = 0; i < rndPdImgLen; i++) {
            		var pdImgRnd = data.pdImgRndList[i];
            		var sm_code = pdImgRnd.sm_code;
            		
        			html+='<tr>';
        			html+='<td>'+(firstNo-i)+'</td>';
        			html+='<td class="rndPd_om_nm">'+pdImgRnd.om_nm+'</td>';
        			html+='<td class="rndPd_md_nm">'+pdImgRnd.em_nm+'</td>';
        			html+='<td class="rndPd_sm_nm">'+pdImgRnd.sm_nm+'</td>';
        			html+="<td><img src='"+pdImgRnd.rnd_pd_img_url+"' class='rndPd-img' /></td>";
        			html+='</tr>';
            	}
            	$("#rndPdImgTbody10920").html(html);
            	$("#rndPdImgNavi10920").html(data.navi);
            	
            }else{
            	html += '<tr><td colspan="5"><b>조회된 데이터가 없습니다.<b></td></tr>';
            	$("#rndPdImgTbody10920").html(html);
            	$("#rndPdImgNavi10920").html(data.navi);
			}
            
        	$("#rndPdImgTbody10920").find(".rndPd-img").click(function(){
        		var src = $(this).attr("src");
        		openLayer('activityRndPreView10920', src);
        	});
		}
	});
}

//검색 콤보 박스
function fnGetrndPdImgSearchComboBox(om_code ,om_orgnzt_se){
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
				$("#searchOmCode10920").html(listHtml);
				$("#searchOmCode10920").change(function(){
					fnGetrndPdImgSearchComboBox($(this).val(), 2);
				});
				if(parseInt(data.auth_flag) > 1){
					fnGetrndPdImgSearchComboBox($("#searchOmCode10920").val() ,2);
				}
			}else if(om_orgnzt_se==2){
				$("#searchTmCode10920").html(listHtml);
			}
	    }
	});
}
//팝업레이어 드래그 A20161215 k2s
$( function() {
	$("#activityRndPreView10920").draggable();
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
