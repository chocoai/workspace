/*
 * 通用js
 * Created By jimmy
 * Date: 2017-05-25
 * 注意需要先导入jquery
 */

//全局变量
var currentPage = $("#currentPage").val();
var totalPage = $("#totalPage").text();
var orgId = $("#org").val();
var reqType = $("#reqType").val();

// 左侧菜单栏目关闭和开启
$("#right").width(window.innerWidth- $("#left").width() - $("#openClose").width() -35);

$("#left").on('show.bs.collapse', 
	function () {
		$("#left").show();
		$("#left").width("130px");
		$("#right").width(window.innerWidth - $("#left").width() - $("#openClose").width() -35);
		$("#right").css('left','160px');
		$("#openClose").css('left','140px');
	}
);

$("#left").on('hide.bs.collapse', 
	function () {
		$("#left").hide();
		$("#left").width("0px");
		$("#right").width(window.innerWidth - $("#left").width() - $("#openClose").width() -25);
		$("#right").css('left','20px');
		$("#openClose").css('left','0px');
		
	}
);

// 分页样式的调整，表格自动补齐10行
function show(table_id, specialty) {
	var tb = document.getElementById(table_id);
	if (tb.rows.length < 11) {
		var blankSize = 11 - tb.rows.length;
		for (var i = 0; i < blankSize; i++) {
			tr = tb.insertRow(tb.rows.length);
			for (var x = 0; x < tb.rows[0].cells.length; x++) {
				tr.insertCell(x);
			}
			// tr.cells[0].innerHTML = tb.rows.length-1;
		}
	}
	// 选中职位
	$("#specialty option").each(function() {
		var op = $(this).text();
		if (specialty == op) {
			$(this).attr("selected", "selected");
		}
	});

}

// 导出表格
function exportExcel(formId, action) {
	$("#" + formId).attr("action", action);
	$("#" + formId).attr("method", "post");
	$("#" + formId).submit();
}

// 导入表格
function importExcel(formId, orgId) {
	layer.load();
	var formData = new FormData($("#" + formId)[0]);
	$.ajax({
		type : "post",
		url : "upload",
		data : formData,
		dataType : "text",
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		success : function(data) {
			layer.closeAll('loading');
			data = data.substring(1, data.length - 1);
			if (data.indexOf(",") > 0) {
				var result = data.split(",");
				layer.msg("共计添加成功" + result[0] + "条数据," + "添加失败" + result[1] + "条数据!");
				setTimeout(function() { // 使用 setTimeout（）方法设定定时2000毫秒
					window.location.reload();// 页面刷新
				}, 3000);
			} else {
				layer.msg("批量导入失败!", function() {
					layer.msg("数据不正确", {
						icon : 5
					});
				});
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.closeAll('loading');
			layer.msg("请下载模板文件并填充内容后上传", {
				icon : 5
			});
		}
	});
}

document.onkeydown = function(event) {
	var e = event || window.event;
	if (e && e.keyCode == 13) { // 回车键的键值为13
		sub(reqType, 1);
	}
};
// 分页
function sub(reqTypeValue, pageValue) {
	$("#reqType").val(reqTypeValue);
	$("#subForm").append("<input type='hidden' name='pageValue' value='" + pageValue + "'/>");
	$("#subForm").attr("action", "");
	$("#subForm").attr("method", "get");
	$("#subForm").submit();
}
