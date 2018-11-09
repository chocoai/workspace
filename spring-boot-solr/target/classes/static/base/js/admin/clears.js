/**
 * @see 取产品正常使用的rsa
 */
function ajaxClear() {
	$.ajax({
		url : PROJECT_CTX + "/clears/ajaxClear.html",
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data.status == '1') {
				alert("清空成功")
			} else {
				alert("清空失败")
			}
		}
	});
}
