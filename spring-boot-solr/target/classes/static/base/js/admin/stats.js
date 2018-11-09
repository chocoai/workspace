$(function() {
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
		url : PROJECT_CTX + "/register/ajaxQuery.html",
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
	var tab_rsas = $("#tab_datas tbody");
	tab_rsas.html("");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		var id = obj.id;
		var name = obj.name;
		var product_code = obj.product_code;
		var temp_num = obj.temp_num;
		var register_num = obj.register_num;
		var device_num = obj.device_num;

		var tr = "<tr>";
		tr += "<td class='text-center'>" + name + "</td>";
		tr += "<td class='text-center'>" + product_code + "</td>";
		tr += "<td class='text-center'>" + temp_num + "</td>";
		tr += "<td class='text-center'>" + register_num + "</td>";
		tr += "<td class='text-center'>" + device_num + "</td>";
		tr += "</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
}
