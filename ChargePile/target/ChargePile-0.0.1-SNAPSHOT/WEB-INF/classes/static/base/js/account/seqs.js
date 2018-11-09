$(function() {

	// 数据初始化;
	init();

	/**
	 * @see 上移
	 */
	$("#upBtn").click(function() {
		// 选中的值
		var cv = $("#c_redio").val();
		// 当前元素
		var thisObj = $("#" + cv);
		thisObj.children("label").addClass("red-bg txt-white-hover");
		// 上一个
		var prev = thisObj.prev();
		if (prev) {
			thisObj.insertBefore(prev);
		}
		// 表示选择的是子元素
		if (cv.indexOf("s") != -1) {
			init();
		}

	});

	/**
	 * @see 下移
	 */
	$("#downBtn").click(function() {
		// 选中的值
		var cv = $("#c_redio").val()
		// 当前元素
		var thisObj = $("#" + cv);
		thisObj.children("label").addClass("red-bg txt-white-hover");
		// 下一个
		var next = thisObj.next();
		if (next) {
			thisObj.insertAfter(next);
		}
		// 表示选择的是子元素
		if (cv.indexOf("s") != -1) {
			init();
		}
	});

	
	
	
	$("#updateSeqs").click(function() {
		updateFn();
	});

	
})

//菜单排序
function updateFn(){
	common_confirm("排序提示","您确认要重新排序吗？",updateFnOk,null,null);
}

//菜单排序方法
function updateFnOk(){
	var data = $("#myform").serialize();
	$.ajax({
		type : "post",
		url : PROJECT_CTX + "/seqs/ajaxSeqs.html",
		data : data,
		dataType : "json",
		success : function(data) {
			if (data.status == '1') {
				alert_sucess("修改成功,重新登录后生效")
			} else {
				alert_fail("修改失败")
			}
		}
	});
}


/**
 * @see 设置Seqs格式
 */
function init() {
	$.each($("input[class^=seqv#]"), function() {
		var s = $(this).attr("class").split("#")[1];
		var v = (s + "#").replace("s_", "");
		$.each($("." + s + " li"), function() {
			v += $(this).attr("id").replace("s_", "") + ",";
		});
		v = v.substring(0, v.length - 1);
		$(this).val(v);
	})

}

function redioFFn() {
	var fv = $('input[name="f_menus"]:checked').val()
	$("#c_redio").val(fv)
}

function redioSFn(id) {
	var fv = $("input[name=s_menus_" + id + "]:checked").val()
	$("#c_redio").val(fv)
}