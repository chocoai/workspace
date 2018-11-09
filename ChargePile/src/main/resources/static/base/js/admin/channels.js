$(function() {
	$("#searchBTN").on('click', function() {
		reload();
		searchFn();
	});
	$("#resetBTN").on('click', function() {
		resetFn();
	});
	$("#updateChannel").on('click', function() {
		updateFn();
	});
});

/**
 * @see 修改
 */
function updateFn(){
	var fromData = $("#myform").serialize();
	var id = $("#pid").val();
	$.ajax({
		url : PROJECT_CTX + "/channels/ajaxUpdate.html",
		type : "post",
		data :fromData,
		dataType : "json",
		success : function(data) {
			if (data.status == '1') {
				alert_sucess("修改成功");
				go_sucess_url("/channels/update.html?op=detail&id="+id,1500);
			} else {
				alert_fail("修改失败");
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

// 删除产品
function deleteFn(product_id) {
	del_confirm("删除提示", "您确认要删除记录吗？", deleteFnOk, null, product_id);
}
/**
 * @see 删除产品
 * @param id
 */
function deleteFnOk(product_id) {
	$.ajax({
		url : PROJECT_CTX + "/channels/ajaxDelete.html",
		type : "post",
		data : {
			"selectId" : product_id
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

/*
 * 查询方法
 */
function searchFn() {
	var search_select = $("#search_select").val();
	var search_key = $("#search_key").val();

	if ($.trim(search_key) == '' && $.trim(search_select) != '') {
		alert("请输入关键字!")
		return;
	} else if ($.trim(search_key) != '' && $.trim(search_select) == '') {
		alert("请选择搜索条件!")
		return;
	} else {
		// 如果选择不是版本
		if ($.trim(search_select) != 2) {
			// 1 - 密钥, 2- 公司名称
			if (search_select == 1) {
				$("#app_secret").val(search_key);
				$("#company_name").val("");
			} else if (search_select == 3) {
				$("#app_secret").val("");
				$("#company_name").val(search_key);
			}
		} else {
			search_key = $.trim(search_key.toLowerCase());
			if (search_key != 'ios' && search_key != 'android') {
				alert("请输入正确的版本 ios 或 android")
				return;
			} else if (search_key == 'ios') {
				$("#os_type").val(1);
			} else {
				$("#os_type").val(2);
			}
		}
	}

	var fromData = $("#queryform").serialize();
	$.ajax({
		url : PROJECT_CTX + "/channels/ajaxQuery.html",
		type : "post",
		data : fromData,
		dataType : "json",
		success : function(data) {
			var json = eval(data.message);
			reloadFns(json);
		}
	});
}

function reloadFns(json) {
	var objs = eval(json.items);
	var tab_rsas = $("#tab_channels tbody");
	tab_rsas.html("");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		var id = obj.id;
		var name = obj.name;
		var os_type = obj.os_type;
		var code = obj.code;
		var app_secret = obj.app_secret;
		var company_name = obj.company_name;
		var status = obj.status;
		var abtn = "";

		if (os_type == 1) {
			os_type = "IOS";
		} else if (os_type == 2) {
			os_type = "Android";
		} else if (os_type == 3) {
			os_type = "IOS,Android";
		} else {
			os_type = "无";
		}

		if (updateCurd(PROJECT_CURD)) {
				abtn += getUpdateCurd(PROJECT_CTX + "/channels/update.html?id="
						+ id, "修改");
		}
		
		if (deleteCurd(PROJECT_CURD)) {
			if(status==0) {
				abtn += getDeleteCurd(null,"删除","false");
			}else {
				abtn += getDeleteCurd("javascript:deleteFn("+id+")", "删除");
			}
		}
		
		if(status==0){
			status = "<span class='label label-default'>已删除</span>";
		}else if(status==1){
			status = "<span class='label label-success'>正常</span>";
		}else if(status==2){
			status = "<span class='label label-warning'>待审核</span>";
		}else if(status==3){
			status = "<span class='label label-danger'>未通过</span>";
		} else {
			status = "<span class='label label-default'>未知</span>";
		}

		if (!company_name || company_name == '' || company_name == 'null') {
			company_name = "未知";
		}

		var tr = "<tr>";
		tr += "<td class='text-center'>" + id + "</td>";
		tr += "<td class='text-center'>" + name + "</td>";
		tr += "<td class='text-center'>" + code + "</td>";
		tr += "<td class='text-center'>" + app_secret + "</td>";
		tr += "<td class='text-center'>" + os_type + "</td>";
		tr += "<td class='text-center'>" + company_name + "</td>";
		tr += "<td class='text-center'>" + status + "</td>";
		tr += "<td class='text-center'>" + abtn + "</td>";
		tr += "</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
}

// 生成密钥
function insertFnRsa(id, reload) {
	var jsonObj = {
		id : id,
		reload : reload
	}
	common_confirm("密钥操作", "确定重新生成密钥吗?", insertFnRsaOk, null, jsonObj)
}
// 生成密钥方法
function insertFnRsaOk(jsonObj) {
	$.ajax({
		url : PROJECT_CTX + "/rsas/ajaxAdd.html",
		type : "post",
		data : {
			"product_id" : jsonObj.id
		},
		dataType : "json",
		success : function(data) {
			if (data.status == '0') {
				alert_fail("添加失败");
			} else {
				alert_sucess("添加成功");
			}
			if (jsonObj.reload == 'true') {
				reloadFn(data);
			}
		}
	});
}

// 更新产品密钥
function insertFnSecret(id) {
	common_confirm("产品密钥操作", "确定要更新产品密钥吗?", insertFnSecretOk, null, id)
}

function insertFnSecretOk(id) {
	$.ajax({
		url : PROJECT_CTX + "/secrets/ajaxUpSecret.html",
		type : "post",
		data : {
			"pid" : id
		},
		dataType : "json",
		success : function(data) {
			if (data.status == '0') {
				alert_fail("更新失败");
			} else {
				var msg = data.message;
				if (msg) {
					$("#span_app_secret").html(msg.app_secret);
					$("#span_app_secret_version").html(msg.version);
				}
				alert_sucess("更新成功");
			}
		}
	});
}

/**
 * @see 查询密钥
 */
function ajaxQSecret() {
	var id = $("#pid").val();
	$.ajax({
		url : PROJECT_CTX + "/secrets/ajaxQSecret.html",
		type : "post",
		data : {
			"pid" : id
		},
		dataType : "json",
		success : function(data) {
			var msg = data.message;
			if (msg) {
				$("#span_app_secret").html(msg.app_secret);
				$("#span_app_secret_version").html(msg.version);
			}
		}
	});
}

function ajaxCardInfo() {
	var id = $("#user_id").val();// user_id
	var op = $("#op").val();// user_id
	$.ajax({
		url : PROJECT_CTX + "/channels/ajaxCardInfo.html",
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			if(data.message.type_id == 1) {
				$("#label_company_name").text("个人姓名：");
			} else if (data.message.type_id == 2) {
				$("#label_company_name").text("公司名称：");
			}
			//默认资质图片
			var src = "";
			if(op=='detail') {
				//1-个人, 2-公司
				if (data.message.type_id == 1) {
					src = data.message.front_image;
					$("#input_company_name").text(data.message.name);
				} else if (data.message.type_id == 2) {
					src = data.message.business_image;
					$("#input_company_name").text(data.message.company_name);
				}
			}else {
				//1-个人, 2-公司
				if (data.message.type_id == 1) {
					src = data.message.front_image;
					$("#input_company_name").val(data.message.name);
				} else if (data.message.type_id == 2) {
					src = data.message.business_image;
					$("#input_company_name").val(data.message.company_name);
				}
			}
			// 1-审核通过 , 2-待审核 , 3-审核不通过
			if (src != 'null'&&src != '') {
				$("#a_business_image").attr({
					"href" : PROJECT_IMAGE_PREFIX + src
				});
				$("#img_business_image").attr({
					"src" : PROJECT_IMAGE_PREFIX + src
				});
			} else {
				if (data.message.type_id == 1) {
					src = "/img/shengfenzheng_demo_front.jpg";
				} else if (data.message.type_id == 2) {
					src = "/img/zhizhao.jpg";
				}
				$("#a_business_image").attr({
					"href" : PROJECT_CTX + src
				});
				$("#img_business_image").attr({
					"src" : PROJECT_CTX + src
				});
			}
		}
	});
}

/**
 * @see 取产品正常使用的rsa
 */
function ajaxRsa() {
	var id = $("#pid").val();// pid
	$.ajax({
		url : PROJECT_CTX + "/channels/ajaxRsa.html",
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			$("#rsa_pri_secret").val(data.message.rsa_pri_secret);
			$("#rsa_pub_secret").val(data.message.rsa_pub_secret);
		}
	});
}

/**
 * 获取旗下所有产品
 */
function ajaxProductInfo() {
	var id = $("#user_id").val();// user_id
	$.ajax({
		url : PROJECT_CTX + "/channels/ajaxProductInfo.html",
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			for (key in data.message) {
				var product = data.message[key];
				var text = "<a href=" + PROJECT_CTX
						+ "'/channels/update.html?op=detail&id=" + product.id
						+ "' style='cursor:pointer;'>" + product.name + "</a>";
				if (key != data.message.length - 1) {
					text += "&nbsp;|&nbsp;";
				}
				$("#product_name").append(text);

			}
		}
	});
}

