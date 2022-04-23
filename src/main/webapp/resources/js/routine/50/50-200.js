/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	//팝업창등록(필수)
	$("#tempPop50100").instancePopUp();
	
	//input 달력
	$("#searchToDate50100").winCal(baseOptions);	
	$("#searchFromDate50100").winCal(baseOptions);
	
	// 이벤트 등록
	fnSetEventComponent();	
});




/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	
	
	
	$("#tempOpen50100").click(function(){
		$("#tempPop50100").show();
	});
	$("#tempClose50100, #tempCloseX50100").click(function(){
		$("#tempPop50100").hide();
	});
}

