$(document).ready(function() {
	
	// 全选
	if ($("#checkedAll") != null) {
		$("#checkedAll").click(function() {
			$(".checkboxClass").each(function() {
				if ($(this).attr("checked") == false) {
					$(this).attr("checked", true);
				}
			});
		});
	}
	// 全不选
	if ($("#checkedNone") != null) {
		$("#checkedNone").click(function() {
			$(".checkboxClass").each(function() {
				if ($(this).attr("checked") == true) {
					$(this).attr("checked", false);
				}
			});
		});
	}
	// 反选
	if ($("#checkedReverse") != null) {
		$("#checkedReverse").click(function() {
			$(".checkboxClass").each(function() {
				if ($(this).attr("checked") == false) {
					$(this).attr("checked", true);
				} else {
					$(this).attr("checked", false);
				}
			});
		});
	}
});
