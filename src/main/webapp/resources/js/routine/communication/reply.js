/**
 * 
 */

function Reply(element, option) {
//--- private variables & methods
	var instance = this;
	var fnGetBmInnb = option.fnGetBmInnb;
	var fnOnSave = option.fnOnSave;
	var fnOnDelete = option.fnOnDelete;
	
//--- public variables & methods
	this.element = element;
	this.isShowButton = (option.isShowButton != undefined ? option.isShowButton : true);
	this.isShowWrite = (option.isShowWrite != undefined ? option.isShowWrite : true);

	this.cleanUp = function(){
		this.element.find("#replyCn").val(""); // .html .text 는 크롬에서 안먹음 --;;
		this.element.find("#replyList").html("");
	}

	this.getBmInnb = function() {
		if(fnGetBmInnb != undefined) return fnGetBmInnb();
		return "";
	}

	this.onSave = function(isSuccess) {
		if(fnOnSave != undefined) fnOnSave(isSuccess);
	}

	this.onDelete = function(isSuccess) {
		if(fnOnDelete != undefined) fnOnDelete(isSuccess);
	}
	
	this.setList = function(replyVoList) {
		Reply.fnSetList(instance, replyVoList);
	}

	Reply.fnInit(instance);
}

//--- static variables & methods
Reply.fnInit = function(instance) {
	var html = '';
	if(instance.isShowWrite) {
		html += '<div class="reply-write-container">';
		html += '	<textarea id="replyCn" class="tx-reply" rows="5" cols=""></textarea>';
		html += '	<button type="button" class="red replay-save" id="replySave">댓글<br>등록</button>';
		html += '</div>';
	}
	html += '<ul id="replyList" class="reply-list"></ul>';
	instance.element.html(html);

	Reply.fnSetEvent(instance);
}

Reply.fnSetEvent = function(instance) {
	instance.element.find("#replySave").click(function(){
		Reply.fnSaveReply(instance, instance.element.find("#replyCn"), instance.getBmInnb());
	});
}

Reply.fnSetList = function(instance, replyVoList) {
	instance.cleanUp();
	var switchColor = true; // :nth-child 쓰긴 애매하다..--;
	for (var i = 0; i < replyVoList.length; i++) {
		var replyVo = replyVoList[i];
		var otherReply = false;
		if(replyVo.br_depth == '1') {
			otherReply = true;
			switchColor = !switchColor;
		}
		var html = '<li class="'+(otherReply?'reply-first-item ':'')+(switchColor?'reply-other-color ':'')+'">';
		if(replyVo.br_depth != '1') {
			html += '<div class="reply-tail" style="width:'+(replyVo.br_depth-1)*40 +'px;">└</div>';
		}
		html += '<dl>';
		html += '<dt><p class="reply-name">'+replyVo.em_nm+'</p><p>'+replyVo.regist_de+'</p>';
		html += '</dt><dd class="tx-button"><pre>'+replyVo.br_cn+'</pre>';
		html += '</dd>';
		html += '</dl>';
		
		if(instance.isShowButton) {
			html += '<div class="reply-button-container">';
			if(replyVo.auth_reply == 'Y') {
				html += '<button type="button" class="skyblue reply-answer-btn" data-br_innb="'+replyVo.br_innb+'" data-bm_innb="'+replyVo.bm_innb+'" data-br_depth="'+replyVo.br_depth+'">답변</button>';
			}
			if(replyVo.auth_delete == 'Y') { //작성자 본인
				html += '<button type="button" class="gray reply-delete-btn" data-br_innb="'+replyVo.br_innb+'">삭제</button>';
			}else if(auth_flag<3){ //관리자, 팀장  
				html += '<button type="button" class="gray reply-delete-btn" data-br_innb="'+replyVo.br_innb+'">삭제</button>';
			}
			html += '</div>';				
		}
		html += '<div class="reply-answer-container" id="answerView'+replyVo.br_innb+'"></div>';
		html += '</li>';
		instance.element.find("#replyList").append(html);
	}
	instance.element.find(".reply-answer-btn").click(function(){
		Reply.fnShowAnswerView(instance, $(this));
	});
	instance.element.find(".reply-delete-btn").click(function(){
		Reply.fnDelReply(instance, $(this).attr('data-br_innb'));
	});
}

Reply.fnShowAnswerView = function(instance, element) {
	var brInnb = element.attr('data-br_innb');
	var bmInnb = element.attr('data-bm_innb');
	var brDepth = element.attr('data-br_depth');

	instance.element.find(".reply-answer-container").html("");
	var html = '<textarea id="replyAnswerCn" class="tx-reply" style="width:'+(660-brDepth*40)+'px; left:'+(brDepth*40)+'px;" rows="5" cols="" data-br_innb="'+brInnb+'"></textarea>';
	html += '<button type="button" class="red replay-save" id="replyAnswer">답변<br>등록</button>';
	var answerDiv = $("#answerView"+brInnb);
	answerDiv.html(html);
	answerDiv.show();
	
	instance.element.find("#replyAnswer").click(function(){
		Reply.fnSaveReply(instance, $("#replyAnswerCn"), bmInnb);
	});
}

Reply.fnSaveReply = function(instance, element, bmInnb){
	var brCn = $.trim(element.val());
	if(brCn == ""){
		alert("내용을 입력해주세요.");
		element.focus();
		return;
	}
	
	var saveData = {"bm_innb" : bmInnb, "br_cn" : brCn};
	var parentBrInnb = element.attr('data-br_innb');
	if(typeof(parentBrInnb) != 'undefined') {
		saveData["parent_br_innb"] = parentBrInnb;
	}
	
	var url = "/reply/save";
	$.ajaxSettings.traditional = false;
	$.ajax({
		global: true,	
		url : url,
		data: saveData,
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) {
			//console.log(data);
			if(data == -1){ // null 을 날렸는데 -1이라니.. --;
				alert("답변을 작성할 수 없는 댓글입니다.");
				instance.onSave(false);
				return;
			}
			if(data != ""){
				alert("정상적으로 등록되었습니다.");
				instance.onSave(true);
			}else{
				alert("저장실패.");
			}
		}
	}); 
}

Reply.fnDelReply = function(instance, brInnb) {
	if(!confirm("삭제하시겠습니까?"))return;
	
	$.ajax({
		global : true,
		url : "/reply/delete",
		data:{"br_innb" : brInnb},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) {
			//console.log(data);
			if(data != "" ){
				if(data.resultCnt[0] == 0){
					alert("이미 삭제되었거나 권한이 없습니다.");
					instance.onDelete(false);
					return;
				}
				alert("삭제되었습니다.");
				instance.onDelete(true);
			}else{
				alert("삭제오류!!");
			}
		}
	});
}

//--- instantiate a class method
$.fn.createReply = function(option){
	if(option.instance == undefined) {
		return new Reply($(this), option);
	}
	else {
		option.instance.element = $(this);
		option.instance.cleanUp();
		Reply.fnSetEvent(option.instance);
		return option.instance;
	}
};

