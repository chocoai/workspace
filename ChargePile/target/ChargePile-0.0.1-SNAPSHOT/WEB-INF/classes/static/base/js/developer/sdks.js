$(function() {
	//新增，修改app
    $('#myform input,#myform textarea').off('focus').on('focus', function(){
    	val_sdks('focus',this);
    });
    $('#myform input,#myform textarea').off('blur').on('blur', function(){
    	val_sdks('blur',this);
    });
	
	$("#addSdks").click(function() {
		if(val_sdks()){
			insertFn();
		}
	});

	$("#updateSdks").click(function() {
		if(val_sdks()){
			updateFn();
		}
	});
	
	$("#searchBTN").on('click',function(){
    	reload();
    	searchFn();
	});
	 $("#resetBTN").on('click',function(){
	    	resetFn();
	    });
	//初始隐藏sdk,从6个sdk第开始隐藏    
	hideUI("android",6);
	hideUI("ios",6);
})

// 查询方法
function searchFn() {
	var fromData = $("#queryform").serialize();
	//alert(fromData)
	$.ajax({
		url : PROJECT_CTX + "/sdks/ajaxQuery.html",
		type : "post",
		data : fromData,
		dataType : "json",
		success : function(data) {
			var json = eval(data.message);
			reloadFns(json);
		}
	});
}

function updateFn() {
	var data = $("#myform").serialize();
	$.ajax({
		url : PROJECT_CTX + "/sdks/ajaxUpdate.html",
		type : "post",
		data : data,
		dataType : "json",
		success : function(data) {
			if (data.status == '1') {
				alert_sucess("修改成功");
				go_sucess_url("/sdks/index.html",1500); 
			} else if (data.status == '2') {
				alert_fail("修改失败,MD5值不对!");
			} else {
				alert_fail("修改失败");
			}
		}
	});
}

function insertFn() {
	var data = $("#myform").serialize();
	$.ajax({
		url : PROJECT_CTX + "/sdks/ajaxAdd.html",
		type : "post",
		data : data,
		dataType : "json",
		success : function(data) {
			if (data.status == '1') {
				alert_sucess("添加成功");
				go_sucess_url("/sdks/index.html",1500); 
			} else if (data.status == '2') {
				alert_fail("添加失败,MD5值不对!");
			} else {
				alert_fail("添加失败");
			}
		}
	});
}


function deleteFns() {
	var selectIds = "";
	$.each($("input[name=selectId]"), function() {
		if ($(this).prop("checked")) {
			selectIds += $(this).val() + ",";
		}
	});
	if ($.trim(selectIds) == '') {
		bb_alert(null, "请勾选需要删除的记录！");
		return;
	}
	deleteFn(selectIds);
}

//删除sdk
function deleteFn(selectId){
	del_confirm("删除提示","您确认要删除记录吗？",deleteFnOk,null,selectId);
}

function deleteFnOk(selectId) {
	$.ajax({
		url : PROJECT_CTX + "/sdks/ajaxDelete.html",
		type : "post",
		data : {
			"selectId" : selectId
		},
		dataType : "json",
		success : function(data) {
			if (data.status == '1') {
				alert_sucess("删除成功");
			} else {
				alert_fail("删除失败");
			}
			var json = eval(data.message);
			reloadFns(json);
		}
	});
}

function reloadFns(json) {
	var objs = eval(json.items);
	var tab_rsas = $("#tab_sdks tbody");
	tab_rsas.html("");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		var id = obj.id;
		var name = obj.name;
		var os_type = obj.os_type;
		var sdk_type = obj.sdk_type;
		var version = obj.version;
		var create_time = obj.create_time;
		var status = obj.status;
		var abtn = "";

		if (os_type == "1") {
			os_type = "IOS";
		} else if (os_type == "2") {
			os_type = "Android";
		}

		if (sdk_type == "1") {
			sdk_type = "软件";
		} else if (sdk_type == "2") {
			sdk_type = "游戏";
		} else if (sdk_type == "3") {
			sdk_type = "多媒体";
		}

		if (status == 0) {
			status = "<span class='label label-danger'>已删除</span>";
		} else if (status == 1) {
			status = "<span class='label label-success'>正常</span>";
		} else {
			status = "<span class='label label-default'>未知</span>";
		}

		if (updateCurd(PROJECT_CURD)) {
			abtn += getUpdateCurd(PROJECT_CTX + "/sdks/update.html?id=" + id,
					"修改");
		}

		if (deleteCurd(PROJECT_CURD)) {
			abtn += getDeleteCurd("javascript:deleteFn(" + id + ")", "删除");
		}

		var tr = "<tr>";
		tr += "<td class='text-center'><input type='checkbox' name='selectId' value="
				+ id + "></td>";
		tr += "<td class='text-center'>" + name + "</td>";
		tr += "<td class='text-center'>" + os_type + "</td>";
		tr += "<td class='text-center'>" + sdk_type + "</td>";
		tr += "<td class='text-center'>" + version + "</td>";
		tr += "<td class='text-center'>" + create_time + "</td>";
		tr += "<td class='text-center'>" + status + "</td>";
		tr += "<td class='text-center'>" + abtn + "</td>";
		tr += "</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
}

//显示更多sdk
function showmore(obj,id) {
	$(obj).hide();
	$(obj).next("a").show();
	showUI(id,6);
}

//收起显示的sdk
function hidemore(obj,id) {
	$(obj).hide();
	$(obj).prev("a").show();
	hideUI(id,6);
}

/**
 * 显示sdk
 * @param id 
 * @param sdkNum 隐藏序号,从第开始sdkNum隐藏
 */
function showUI(id,sdkNum) {
	$("#"+id).find("ul").each(function(index){
		if(index+1>sdkNum){
			$(this).show();
		}
	});
}

//隐藏sdk
function hideUI(id,sdkNum) {
	$("#"+id).find("ul").each(function(index){
		if(index+1>sdkNum){
			$(this).hide();
		}
	});
}


