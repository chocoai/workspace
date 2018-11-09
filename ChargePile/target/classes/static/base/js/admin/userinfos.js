$(function() {
	ajaxUsers();
	ajaxProducts();
	ajaxCashs();
	$("#searchBTN").click(function() {
    	reload();
		searchFn();
	});

	// 密码修改
	$('#resetpwd_form input').off('focus').on('focus', function() {
		val_userinfos_password('focus', this);
	});
	$('#resetpwd_form input').off('blur').on('blur', function() {
		val_userinfos_password('blur', this);
	});

	// 修改密码
	$("#resetpwd").click(function() {
		if (val_userinfos_password()) {
			resetpwd();
		}
	});
	$("#resetBTN").on('click', function() {
		resetFn();
	});
	
	$("#updateUserinfo").on('click', function() {
		updateFn();
	});
});

function resetpwd() {
	var fromData = $("#resetpwd_form").serialize();
	$.ajax({
		url : PROJECT_CTX + "/userinfos/ajaxResetPwd.html",
		type : "post",
		data : fromData,
		dataType : "json",
		success : function(data) {
			if (data.status == "1") {
				alert_sucess("修改成功");
			} else {
				alert_fail("修改失败");
			}
		},
		error : function() {
			alert(123)
		}
	});
}

/**
 * @see 查询用户数
 */
function ajaxUsers() {
	var fromData = $("#queryform").serialize();
	$.ajax({
		url : PROJECT_CTX + "/userinfos/ajaxUsers.html",
		type : "post",
		data : fromData,
		dataType : "json",
		success : function(data) {
			var json = eval(data.message);
			var html = "";
			html += "<span class='headline'>" + (json.name ? json.name : "箩筐")
					+ "注册用户数</span>";
			html += "<span class='value'>" + (json.data ? json.data : "0")
					+ "</span>";
			$("#div_users").html(html);
		}
	});
}

/**
 * @see 查询产品数
 */
function ajaxProducts() {
	var fromData = $("#queryform").serialize();
	$.ajax({
		url : PROJECT_CTX + "/userinfos/ajaxProducts.html",
		type : "post",
		data : fromData,
		dataType : "json",
		success : function(data) {
			var json = eval(data.message);
			var html = "";
			html += "<span class='headline'>" + (json.name ? json.name : "产品数")
					+ "</span>";
			html += "<span class='value'>" + (json.data ? json.data : "0")
					+ "</span>";
			$("#div_products").html(html);
		}
	});
}

/**
 * @see 查询总收入
 */
function ajaxCashs() {
	var fromData = $("#queryform").serialize();
	$.ajax({
		url : PROJECT_CTX + "/userinfos/ajaxCashs.html",
		type : "post",
		data : fromData,
		dataType : "json",
		success : function(data) {
			var json = eval(data.message);
			var html = "";
			html += "<span class='headline'>"
					+ (json.name ? json.name : "收入流水") + "</span>";
			html += "<span class='value'>￥" + (json.data ? json.data : "0")
					+ "</span>";
			$("#div_cashs").html(html);
		}
	});
}

/**
 * @see 计算用户充值金额
 */
function ajaxUserCharge() {
	var id = $("#oid").val();
	$.ajax({
		url : PROJECT_CTX + "/charges/ajaxUserCharge.html",
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			var json = eval(data.message);
			var html = json.data;
			$("#span_charge").html(html);
		}
	});
}

/**
 * @see 计算用户消费金额
 */
function ajaxUserConsume() {
	var id = $("#oid").val();
	$.ajax({
		url : PROJECT_CTX + "/consumes/ajaxUserConsume.html",
		type : "post",
		data : {
			"lk_uid" : id
		},
		dataType : "json",
		success : function(data) {
			var json = eval(data.message);
			var html = json.data;
			$("#span_consume").html(html);
		}
	});
}

/**
 * @see 计算用户消费金额
 */
function ajaxUserProducts() {
	var id = $("#oid").val();
	$.ajax({
		url : PROJECT_CTX + "/userinfos/ajaxUserProducts.html",
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			var json = eval(data.message);
			var list = json.data;
			var html = "";
			if (list) {
				for ( var i = 0; i < list.length; i++) {
					html += list[i].name + ",";
				}
				html = html.substring(0, html.length - 1);
			}
			$("#span_pros").html(html);
		}
	});
}


//修改用户
function updateFn(){
	var data = $("#myform").serialize();
	var id = $("#oid").val()
	//alert(data)
	$.ajax({
	   url: PROJECT_CTX+"/userinfos/ajaxUpdate.html",
	   type: "post",
	   data: data,
	   dataType:"json",
	   success: function(data,status,xhr){
		   if(data.status=="1") {
			   alert_sucess("修改成功");
			   go_sucess_url("/userinfos/update.html?op=detail&id="+id,1500);
		   } else {
			   alert_fail("修改失败");
		   }
	   }
	});
}

// 查询明细列表
function searchFn() {
	var search_select = $("#search_select").val();
	var search_key = $("#search_key").val();
	
	if($.trim(search_key)=='' && $.trim(search_select)!='') {
		alert("请输入关键字!")
		return;
	}else if($.trim(search_key)!='' && $.trim(search_select)=='') {
		alert("请选择搜索条件!")
		return;
	}else {
		// 1 - 箩筐帐号, 2- 手机号
		if (search_select == 1) {
			if (search_key.length != 24) {
				alert("输入的箩筐帐号有误!");
				return;
			}
			$("#lk_uid").val(search_key);
			$("#phone").val("");
		} else if (search_select == 2) {
			$("#lk_uid").val("");
			$("#phone").val(search_key);
		}
	}
	
	//异步查询前loading，成功在显示数据
	var tab_rsas = $("#tab_userinfos tbody");
	tab_rsas.html("<tr><td colspan='7' class='text-center loading'></td></tr>");
	 
	
	var fromData = $("#queryform").serialize();
	$.ajax({
		url : PROJECT_CTX + "/userinfos/ajaxQuery.html",
		type : "post",
		data : fromData,
		dataType : "json",
		success : function(data) {
			ajaxUsers();
			ajaxProducts();
			ajaxCashs();
			var json = eval(data.message);
			reloadFns(json);
		}
	});
}

function reloadFns(json) {
	var objs = eval(json.items);
	var tab_rsas = $("#tab_userinfos tbody");
	tab_rsas.html("");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		var id = obj.id;
		var product_name = obj.product_name;
		var product_code = obj.product_code;
		var phone = obj.phone;
		var coins = obj.coins;
		var create_tm = obj.createTimeStr;
		var abtn = "";
		abtn += getUpdateCurd(PROJECT_CTX + "/userinfos/update.html?id=" + id,
				"查看");

		var tr = "<tr>";
		tr += "<td class='text-center'>" + id + "</td>";
		tr += "<td class='text-center'>" + product_name + "</td>";
		tr += "<td class='text-center'>" + product_code + "</td>";
		tr += "<td class='text-center'>" + phone + "</td>";
		tr += "<td class='text-center'>" + coins + "</td>";
		tr += "<td class='text-center'>" + create_tm + "</td>";
		tr += "<td class='text-center'>" + abtn + "</td>";
		tr += "</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
}