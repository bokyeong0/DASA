/**
 * 
 */
function Receiver(element, option) {
//--- private variables & methods 
	var instance = this;
	var fnGetShowPopupBtn = option.fnGetShowPopupBtn;
	
	
//--- public variables & methods
	this.element = element;
	this.selectDiv = option.selectDiv; // 등록/수정화면 수신자
	this.receiverDiv = option.receiverDiv; // 상세보기화면 수신자
	
	//수신지점 선택관련
	this.omCode; // 지점
	this.omName; // 지점
	this.authFlag;
	this.fixRound = ""; // "":전체, "0000000006":고정, "0000000007":순회
	
	//전체/고정/순회 바꿀시 기존 호출된 트리 재호출을 위해 필요한 값
	this.tr_flag;
	this.tr_data;
	
	this.employeeArr = [];
	this.selectArr = [];
	this.receiverArr = [];

	this.cleanUp = function(){
		instance.tr_flag = "";
		instance.fixRound = "";
		instance.employeeArr = [];
		instance.selectArr = [];
		instance.element.find("#employeeList").html("");
		instance.element.find("#selectEmployeeList").html("");
		instance.element.find('input:radio[name="fixRound"][value="'+instance.fixRound+'"]').attr('checked', 'checked');
	}
	
	this.getShowPopupBtn = function() {
		return fnGetShowPopupBtn();
	}

	this.onTabOrganization = function() {
		Receiver.fnOnTabOrganization(instance);
	}
	
	this.onTabStore = function() {
		Receiver.fnOnTabStore(instance);
	}
	
	this.setReceiverList = function(defaultList) {
		Receiver.fnSetReceiverList(instance, defaultList);
	}
	
	this.setEnableModify = function(isEnableModify) {
		Receiver.fnSetEnableModify(instance, isEnableModify);
	}
	
	this.setAuthFlag = function(authFlag) {
		this.authFlag = authFlag;
	}


//--- instance initialization
	Receiver.fnInit(instance);
}

//--- static variables & methods
Receiver.fnInit = function(instance) {
	var element = instance.element;

	var html = '';
	html += '<div class="popup_overlay" style="z-index:999;"></div>';
	html += '<div class="popup" style="width: 600px;">';
	html += '	<div class="phead">';
	html += '		<span>수신자 선택</span>';
	html += '		<a id="receiverCloseX" class="phead-closex">닫기</a>';
	html += '	</div>';
	html += '	<div class="con">';
	html += '		<div class="tbl_tab">';
	html += '			<ul id="receiverTab">';
	html += '				<li id="receiverTab01"><a>조직도</a></li>';
	html += '				<li id="receiverTab02"><a>매장</a></li>';
	html += '			</ul>';
	html += '		</div>';
	html += '		<select id="organSelect" class="select-basic" style="display:inline-block; height:30px;"></select>';
	html += '		<div id="receiverGroup" style="display:inline-block; margin-left:20px; height:30px;">';
	html += '			<label class="receiver-label"><input class="receiver-radio" type="radio" name="fixRound" value="" checked="checked"/> 전체</label>';
	html += '			<label class="receiver-label"><input class="receiver-radio" type="radio" name="fixRound" value="0000000006"/> 고정</label>';
	html += '			<label class="receiver-label"><input class="receiver-radio" type="radio" name="fixRound" value="0000000007"/> 순회</label>';
	html += '			<label class="receiver-label" id="teamLeader"><input class="receiver-radio" type="radio" name="fixRound" value="0000000008"/> 팀장</label>';
	html += '		</div>';
	html += '		<div class="receiver-container">';
	html += '			<div class="receiver-sub-container">';
	html += '				<div id="organTree" class="panel-body mtree-wrap receiver-left-container" style="top:0;">';
	html += '				</div>';
	html += '				<div id="storeTree" class="panel-body mtree-wrap receiver-left-container" style="top:0;">';
	html += '				</div>';
	html += '				<select id="employeeList" class="container-select withbtn receiver-left-container" multiple="true" style="height:240px; bottom:0;"></select>';
	html += '			</div>';
	html += '			<div class="receiver-mid-container">';
	html += '				<div id="appendSel" class="receiver-arrow"><i class="fa fa-caret-right fa-lg"></i></div>';
	html += '				<div id="appendAll" class="receiver-arrow"><i class="fa fa-step-forward"></i></div>';
	html += '				<div id="removeSel" class="receiver-arrow"><i class="fa fa-caret-left fa-lg"></i></div>';
	html += '				<div id="removeAll" class="receiver-arrow"><i class="fa fa fa-step-backward"></i></div>';
	html += '			</div>';
	html += '			<div class="receiver-sub-container">';
//	html += '				<select id="selectEmployeeList" class="container-select withbtn_bottom" multiple="true" style="position:absolute; width:100%; height:100%;"></select>'; IE 수신자 추가 안되는 문제
	html += '				<select id="selectEmployeeList" class="container-select withbtn_bottom" multiple="true" style="width:100%; height:100%;"></select>';
	html += '			</div>';
	html += '		</div>';
	html += '	</div>';
	html += '	<div class="pfooter">';
	html += '		<button type="button" class="red" id="receiverAccept">적용</button>';
	html += '		<button type="button" class="white" id="receiverClose">닫기</button>';
	html += '	</div>';
	html += '</div>';
	element.html(html);
	
	Receiver.fnSetEvent(instance);
}

Receiver.fnSetEvent = function(instance) {
	var element = instance.element;
	
	element.instancePopUp();
	element.addClass('receiver-popup');
	
	instance.getShowPopupBtn().click(function() {
		instance.onTabOrganization();
		instance.selectArr = [];
		instance.element.find("#selectEmployeeList").html("");
		$.each(instance.receiverArr, function(index, item) {
			Receiver.fnValidateAppendItem(instance, item);
		});
		
		element.show();
	});

	//닫기
	element.find("#receiverCloseX, #receiverClose").click(function(){
		instance.cleanUp();
		element.hide();
	});
	
	//조직도탭
	element.find("#receiverTab01 a").click(function() {
		instance.element.find("#employeeList").html("<option>로딩중.</option>");
		instance.onTabOrganization();
		$("#teamLeader").show();
		return false;
	});
	
	//매장탭
	element.find("#receiverTab02 a").click(function() {
		instance.element.find("#employeeList").html("<option>로딩중.</option>");
		instance.onTabStore();
		instance.fixRound = "";
		instance.element.find('input:radio[name="fixRound"][value="'+instance.fixRound+'"]').attr('checked', 'checked');
		$("#teamLeader").hide();
		return false;
	});
	
	//수신지점
	element.find("#organSelect").change(function(){
		instance.element.find("#employeeList").html("<option>로딩중.</option>");
		instance.omCode = $(this).val();
		if($(this).val()=='000'){
			instance.omName = "";
		}else{
			instance.omName = $(this).find("option:selected").text();
		}
		Receiver.fnSetStoreTree(instance);
	});

	// 고정/순회
	element.find('input[type=radio][name=fixRound]').change(function() {
		instance.element.find("#employeeList").html("<option>로딩중.</option>");
		instance.fixRound = this.value;
		Receiver.fnSetEmployeeList(instance);
    });
	
	element.find("#appendSel").click(function(){
		Receiver.fnAppendSelectEmployeeList(instance, "appendSel");
	});

	element.find("#appendAll").click(function(){
		Receiver.fnAppendSelectEmployeeList(instance, "appendAll");	
	});
	
	element.find("#removeSel").click(function(){
		Receiver.fnRemoveSelectEmployeeList(instance, "removeSel");
	});

	element.find("#removeAll").click(function(){
		Receiver.fnRemoveSelectEmployeeList(instance, "removeAll");
	});

	// 적용
	element.find("#receiverAccept").click(function(){
		instance.selectDiv.html("");
		instance.receiverArr = [];
		$.each(instance.selectArr, function(index, item) {
			Receiver.fnPushReceiverItem(instance, item);
		});
		element.hide();
	});	
}

Receiver.fnOnTabOrganization = function (instance) {
	var element = instance.element;
	
	element.find("#receiverTab > li").removeClass();
	element.find("#receiverTab01").addClass('tab_on');
	element.find("#organTree").show();
	element.find("#organSelect").hide();
	element.find("#storeTree").hide();
	
	instance.tr_flag = "";
	
	Receiver.fnSetOrganTree(instance);
}

Receiver.fnOnTabStore = function(instance) {
	var element = instance.element;
	
	element.find("#receiverTab > li").removeClass();
	element.find("#receiverTab02").addClass('tab_on');
	element.find("#organTree").hide();
	element.find("#organSelect").show();
	element.find("#storeTree").show();

	instance.tr_flag = "";
	
	Receiver.fnSetOrganSelect(instance);
}

Receiver.fnSetOrganTree = function(instance) {
	var rootData = {
		key: '',
		dp_name: '조직전체',
		add_flag: 'or',
		depth: "0"
	};
	instance.element.find("#organTree").fTree({
		url : "/receiver/organTreeList",
		key : "key",
		val : "dp_name",
		p_key : "p_key",
		rootVal : rootData.dp_name,
		tagId : instance.element.attr('id')+"OrganTree",
		click : function(data){
			instance.element.find("#employeeList").html("<option>로딩중.</option>");
			if(data.depth == "0") {
				data = rootData;
			}
			else {
				data.dp_name = data.val;
				delete data.val;
				delete data.node;
			}
			Receiver.fnOnTreeItemClick(instance, data);
		},
		success : function(data){
			Receiver.fnOnTreeItemClick(instance, rootData);
		}
	});
}

Receiver.fnSetOrganSelect = function(instance) { // 매장탭 선택
	$.ajax({
		url : "/receiver/employeeList",
		data: {'add_flag' : "st_or"},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
			var listHtml = "<option value ='000' selected='selected'>전체</option>";
			instance.omCode = "000";
			instance.omName = "";
			
			if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];
	    			listHtml += "<option value ='"+res.om_code+"' >"+ res.dp_name +"</option>";
//	    			listHtml += "<option value ='"+res.om_code+"' "+(i==0?"selected='selected'":"")+">"+ res.dp_name +"</option>";
//	    			if(i==0) {
//	    				instance.omCode = res.om_code;
//	    				instance.omName = res.dp_name;
//	    			}
	    		}
	    		
	    		instance.element.find("#organSelect").html(listHtml);
	    		Receiver.fnSetStoreTree(instance);
			}	
	    }
	});
}

Receiver.fnSetStoreTree = function(instance) {
	var rootData = {
			key: instance.omCode,
			dp_name: instance.omName+" 전체매장",
			add_flag: 'st',
			depth: "0"
		};
	instance.element.find("#storeTree").fTree({
		url : "/receiver/storeTreeList",
		data : {"om_code" : instance.omCode },
		key : "key",
		val : "dp_name",
		p_key : "p_key",
		rootKey : rootData.key,
		rootVal : rootData.dp_name,
		tagId : instance.element.attr('id')+"StoreTree",
		click : function(data){
			instance.element.find("#employeeList").html("<option>로딩중.</option>");
			if(data.depth == "0") {
				data = rootData;
			}
			else {
				data.dp_name = data.val;
				delete data.val;
				delete data.node;
			}
			Receiver.fnOnTreeItemClick(instance, data);
		},
		success : function(data){
			Receiver.fnOnTreeItemClick(instance, rootData);
		}
	});
}

Receiver.fnOnTreeItemClick = function(instance, data) {
	instance.tr_flag = data.add_flag;
	instance.tr_data = Receiver.fnCreateItem(instance, data);
	Receiver.fnSetEmployeeList(instance);
}

Receiver.fnCreateItem = function(instance, data) {
	var item = $.extend({},data); //복사.. 그냥 data를 바로 받아서 사용하면 값이 유지안됨 
	switch(data.add_flag)
	{
	case 'or':
		break;
	case 'om':
		item.om_code = data.key;
		break;
	case 'tm':
		item.om_code = data.p_key;
		item.tm_code = data.key;
		break;
	case 'st':
		item.om_code = instance.omCode;
		break;
	case 'cg':
		item.om_code = instance.omCode;
		item.cg_code = data.key;
		break;
	case 'me':
		item.om_code = instance.omCode;
		item.cg_code = data.p_key;
		item.me_code = data.key;
		break;
	case 'sm':
		item.om_code = instance.omCode;
		item.sm_code = data.key;
		break;
	}
	return item;
}

Receiver.fnSetEmployeeList = function(instance) {
	instance.tr_data.em_dty_code = instance.fixRound;
	$.ajax({
		url : "/receiver/employeeList",
		data: instance.tr_data,
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			var listHtml = "";
			if(data.result !=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					var vo = data.result[i];

					if(vo.add_flag != "em") {
						if(vo.em_dty_code == "0000000006") {
							vo.dp_name += "_[고정]";
						}
						if(vo.em_dty_code == "0000000007") {
							vo.dp_name += "_[순회]";
						}
						if(vo.em_dty_code == "0000000008") {
							vo.dp_name += "_[팀장]";
						}
					}
					listHtml += "<option value='"+vo.key+"' class=notice-view >"+ vo.dp_name +"</option>";
				}	
			}
			instance.employeeArr = data.result.slice(0); //복사.. 그냥 instance.employeeArr = data.result 했을때는 값이 유지안됨
			instance.element.find("#employeeList").html(listHtml);
	    }
	});
}

Receiver.fnAppendSelectEmployeeList = function(instance, buttonId) {
	if(buttonId == "appendSel") {
		var selectVar = instance.element.find('#employeeList').val();
		
		if(selectVar == null) {
			alert("왼쪽 하단 리스트에서 추가하실 수신자를 선택해 주세요. (Ctrl키나 Shift키를 조합하여 클릭하시면 복수선택도 가능합니다.)");
			return;
		}

		$.each(String(selectVar).split(','), function( index, key ) {
			$.each(instance.employeeArr, function( index, item ) {
				if(item.key == key) {
					if(instance.tr_data.add_flag=='or'){
						item.om_code = instance.employeeArr[index].om_code;
					}else{
						item.om_code = instance.omCode;
					}
					Receiver.fnValidateAppendItem(instance, item);
					return false;
				}
			});
		});
	}
	else if(buttonId == "appendAll") {
		if(instance.authFlag != 1 && instance.tr_data.add_flag == 'or') {
			alert("조직전체에 대한 권한이 없습니다.");
			return;
		} else if(instance.tr_data.key == "000"){
			alert("전체매장은 추가할 수 없습니다.");
			return;
		}
		var item = Receiver.fnCreateItem(instance, instance.tr_data);
		var key = '' + item.add_flag;
		if(item.add_flag == 'me') {
			key += item.p_key;
		}
		key += item.key;
		if(item.add_flag != 'em') {
			key += instance.fixRound;
		}
		item.key = key;
		
		if(instance.fixRound == "0000000006") {
			item.dp_name += "_[고정]";
		}
		if(instance.fixRound == "0000000007") {
			item.dp_name += "_[순회]";
		}
		if(instance.fixRound == "0000000008") {
			item.dp_name += "_[팀장]";
		}
		Receiver.fnValidateAppendItem(instance, item);
	}
	
	instance.element.find("#employeeList > option:selected").removeAttr('selected');
}

Receiver.fnValidateAppendItem = function(instance, data) {
	var isExistItem = false;
	$.each(instance.selectArr, function( index, item ) {
		if(item.key == data.key) {
			isExistItem = true;
			return false;
		}
	});
	
	if(isExistItem) {
		alert("중복된 수신자가 존재합니다.");
		return;
	}
	
	//console.log(data.key);
	var op = $("<option value='"+data.key+"' class=notice-view >"+ data.dp_name +"</option>");
	
	instance.element.find("#selectEmployeeList").append(op);
	instance.selectArr.push($.extend({},data));
}

Receiver.fnRemoveSelectEmployeeList = function(instance, buttonId) {
	if(buttonId == "removeSel") {
		var selectVar = instance.element.find('#selectEmployeeList').val();
		
		if(selectVar == null) {
			alert("오른쪽 수신자목록에서 제외하실 수신자를  선택해 주세요.");
			return;
		}

		$.each(String(selectVar).split(','), function( index, key ) {
			$.each(instance.selectArr, function( index, item ) {
				if(item.key == key) {
					instance.selectArr.splice(index, 1);
					return false;
				}
			});
		});
		
		instance.element.find('#selectEmployeeList > option:selected').remove();
	}
	else if(buttonId == "removeAll") {
		instance.selectArr = [];
		instance.element.find('#selectEmployeeList > option').remove();
	}
}

Receiver.fnSetReceiverList = function(instance, defaultList) {
	instance.selectDiv.html("");
	instance.receiverDiv.html("");
	instance.receiverArr = [];
	$.each(defaultList, function(index, item) {
		Receiver.fnPushReceiverItem(instance, item);
	});
}

Receiver.fnPushReceiverItem = function(instance, item) {
	instance.receiverArr.push($.extend({}, item));
	var span = $('<span data-code="' + item.key + '" >' + item.dp_name + '</span>');
	var a = $('<a href="#"/>');
	a.click(function (){
		var key = $(this).parent().attr("data-code");
		//console.log("key : " + key);
		$.each(instance.receiverArr, function(index, item) {
			if(item.key == key) {
				//console.log("find : " + key);
				instance.receiverArr.splice(index, 1);
				return false;
			}
		});
		$(this).parent().remove();
	});
	span.append(a);
	instance.selectDiv.append(span);
	span = $('<span data-code="' + item.key + '" >' + item.dp_name + '</span>');
	instance.receiverDiv.append(span);
}

Receiver.fnSetEnableModify = function(instance, isEnableModify) {
	if(isEnableModify) {
		instance.getShowPopupBtn().show();
		instance.selectDiv.find('a').show();
	}
	else {
		instance.getShowPopupBtn().hide();
		instance.selectDiv.find('a').hide();
	}
}


//--- instantiate a class method 
$.fn.createReceiverPopUp = function(option) {
	if(option.instance == undefined) {
		return new Receiver($(this), option);
	}
	else {
		option.instance.element = $(this);
		option.instance.cleanUp();
		Receiver.fnSetEvent(option.instance);
		return option.instance;
	}
};
