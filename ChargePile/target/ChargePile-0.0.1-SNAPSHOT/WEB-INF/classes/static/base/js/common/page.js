/**
 * @重置
 */
function reload() {
	$("#p").val(1);
	$("#n").val(8);
}

/**
 * @see 翻页操作
 * @param op
 */
function goPage(op) {
	var pageSize = $("#n").val();
	var currPage = $("#p").val();
	var totalPage = $("#totalPage").val();
	if ('first' == op) {
		currPage = 1;
	} else if ('up' == op) {
		currPage = parseInt(currPage) - 1;
		if (currPage <= 0) {
			currPage = 1;
		}
	} else if ('next' == op) {
		currPage = parseInt(currPage) + 1;
		if (currPage >= totalPage) {
			currPage = totalPage;
		}
	} else if ('last' == op) {
		currPage = totalPage;
	}
	$("#p").val(currPage);
	searchFn();
}

/**
 * @see 刷新页面显示
 * 
 * @param currPage
 * @param totalPage
 * @param totalCount
 */
function reloadPage(json) {
	$("#span_currPage").html(json.currPage);
	$("#span_totalPage").html(json.totalPage);
	$("#span_totalCount").html(json.totalCount);
	$("#n").val(json.pageSize);
	$("#totalPage").val(json.totalPage);
	// alert(currPage +" - "+ totalPage +" - "+totalCount+" - "+pageSize)
	// 如果当前页大于1
	if (json.currPage > 1) {
		$("#li_up").html("<a href=javascript:goPage('up') >上一页</a>");
	} else {
		$("#li_up").html("");
	}
	// 如果当前页小于总页数
	if (json.currPage < json.totalPage) {
		$("#li_next").html("<a href=javascript:goPage('next') >下一页</a>");
	} else {
		$("#li_next").html("");
	}
}