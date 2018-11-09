$(document).ready(function() {
	$("#searchBTN").on('click', function() {
    	reload();
    	searchFn();
	});
	$("#resetBTN").on('click', function() {
		resetFn();
	});
});

/*
 * 查询方法
 */
function searchFn() {
	var fromData = $("#queryform").serialize();
	$.ajax({
		url : PROJECT_CTX + "/checkpros/ajaxQuery.html",
		type : "post",
		data : fromData,
		dataType : "json",
		success : function(data) {
			var json = eval(data.message);
			reloadFns(json);
		}
	});
}

// 审核提交的产品
function checkFn(id, status) {
	var jsonObj = {
		id : id,
		status : status
	}
	if (status == 1) {
		common_confirm("产品审核提示", "您确认要审核<span class='green'>通过</span>吗？",
				checkFns, null, jsonObj);
	} else if (status == 3) {
		common_confirm("产品审核提示", "您确认要审核<span class='red'>不通过</span>吗？",
				checkFns, null, jsonObj);
	}
}

/**
 * @see 检查产品编号
 */
function getProductCode() {
	var first = $.trim($("#first").val());
	var middle = $.trim($("#middle").val());
	var last = $.trim($("#last").val());
	var pcode = first + "-" + middle + "-" + last;
	
	if (pcode.length != 16) {
		alert_fail("产品编号错误,长度为16位!");
		return false;
	}
	return pcode;
}

/**
 * @see 自动生成产品Code
 * 格式 abcdef-1234-1234
 */
function autoCode(){
	$("#first").val(autoFirst(6));
	$("#middle").val(autoMiddle(4));
	$("#last").val(autoLast(4));
}

var ints = "0123456789";
var strs = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
var all = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
function autoFirst(len){
	var ret = "";
	for(var i = 0; i < len; i++){
		var r = Math.floor(Math.random()*all.length);
		var s = all.charAt(r);
		ret+=s;
	}
	return ret;
}

function autoMiddle(len){
	var ret = "";
	for(var i = 0; i < len; i++){
		var r = Math.floor(Math.random()*ints.length);
		var s = ints.charAt(r);
		ret+=s;
	}
	return ret;
}

function autoLast(len){
	var ret = "";
	for(var i = 0; i < len; i++){
		var r = Math.floor(Math.random()*ints.length);
		var s = ints.charAt(r);
		ret+=s;
	}
	return ret;
}


function checkFns(obj) {
//	var pcode = getProductCode();
//	if (pcode == false) {
//		return;
//	}
	$.ajax({
		url : PROJECT_CTX + "/checkpros/ajaxCheck.html",
		type : "post",
		data : {
			"id" : obj.id,
			"status" : obj.status
		},
		dataType : "json",
		success : function(data) {
			if (data.status == '1') {
				alert_sucess("审核成功");
				go_sucess_url("/checkpros/index.html", 1500);
			} else {
				alert_fail("审核失败");
			}
			var json = eval(data.message);
			reloadFns(json);
		}
	});
}

/*
 * 审核提交的产品
 */
function reloadFns(json) {
	var objs = eval(json.items);
	var tab_rsas = $("#tab_datas tbody");
	tab_rsas.html("");
	for ( var i = 0; i < objs.length; i++) {
		var index = i+1;
		var obj = objs[i];
		var id = obj.id;
		var name = obj.name;
		var code = obj.code;
		var app_key = obj.app_key;
		var app_secret = obj.app_secret;
		var create_time = obj.create_time;
		var status = obj.status;
		var abtn = "";

		if (status == 0) {
			status = "<span class='label label-default'>已删除</span>";
		} else if (status == 1) {
			status = "<span class='label label-success'>正常</span>";
		} else if (status == 2) {
			status = "<span class='label label-warning'>待审核</span>";
		} else if (status == 3) {
			status = "<span class='label label-danger'>未通过</span>";
		} else {
			status = "<span class='label label-default'>未知</span>";
		}

		if (updateCurd(PROJECT_CURD)) {
			abtn += getUpdateCurd(PROJECT_CTX + "/checkpros/check.html?id="
					+ id, "修改");
		}

		var tr = "<tr>";
		tr += "<td class='text-center'>"+index+"</td>";
		tr += "<td class='text-center'>" + name + "</td>";
		tr += "<td class='text-center'>" + code + "</td>";
		tr += "<td class='text-center'>" + app_key + "</td>";
		// tr+="<td class='text-center'>"+app_secret+"</td>";
		tr += "<td class='text-center'>" + create_time + "</td>";
		tr += "<td class='text-center'>" + status + "</td>";
		tr += "<td class='text-center'>" + abtn + "</td>";
		tr += "</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
}
