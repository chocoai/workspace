var currentPage=1;
var thePage=0;
var lastPage=20;
var showAll = 8;
var beforeCurrent=1;
var afterCurrent=3;

function setPage(obj,page){
	initPage(obj,page.currentPage,page.totalPage,page.pageSize,page.totalRows);
}

/**
 * 加载分页
 * @param obj 加载分页的容器
 * @param cPage 当前页
 * @param tPage 总页码
 * @param pSize 每页大小
 * @param tRows 总记录数
 */
function initPage(obj,cPage,tPage,pSize,tRows){
	currentPage = cPage;
	lastPage = tPage;
	var start=1;
	var end=tPage;
	
	$(obj).html('');
	$(obj).append('<input type="hidden" name="currPage" id="currPage" value="'+cPage+'">');
	$(obj).append('<input type="hidden" name="totalPage" id="totalPage" value="'+tPage+'">');
	$(obj).append('<input type="hidden" name="pageSize" id="pageSize" value="'+pSize+'">');
	
	$(obj).append('<div class="base_page clearfix">');
	
	var toPageHtml = '';
	toPageHtml += '<p class="fl">';
	toPageHtml += '<span>共<strong>'+tRows+'</strong>条记录</span>';
	toPageHtml += '<span>每页 <select name="pagesizeSelect"><option '+(pSize==10?selected="selected":'')+'>10</option><option '+(pSize==20?selected="selected":'')+'>20</option><option '+(pSize==30?selected="selected":'')+'>30</option></select> 条 </span>';
	toPageHtml += '<span>去第</span>';
	toPageHtml += '<input id="toPageNum" type="text" onkeypress="return IsNum(event)"/>';
	toPageHtml += '<span>页</span><input id="confTurnPage" type="button" value="GO" onclick="toPage(' + tPage + ')"/>';
	toPageHtml += '</p>';
	
	$(obj).append(toPageHtml);
	
	$(obj).append('<a href="javascript:void(0);" class="btn" onclick="gotoFirstPage();">首页</a>');
	$(obj).append('<a class="btn" href="javascript:void(0);" onclick="gotoPrePage();">上一页</a>');
	
	if(tPage>showAll){
		//总页数大于showAll页
		if(cPage<showAll){
			end=showAll;
		}else{
			for(var i=1;i<=2;i++){
				$(obj).append('<a href="javascript:void(0);" onclick="gotoPage('+i+');">'+i+'</a>');
			}
			$(obj).append('<span>... </span>');
			start=cPage-beforeCurrent;
			end=cPage+afterCurrent;
			if(end>=tPage){
				end=tPage;
				start=tPage-afterCurrent-beforeCurrent;
			}
		}
	}
	appendItems(obj,start,end,cPage);
	
	$(obj).append('<a class="btn" href="javascript:void(0);" onclick="gotoNextPage();">下一页</a>');
	$(obj).append('<a href="javascript:void(0);" class="btn" onclick="goToLastPage();">末页</a>');
	$(obj).append('</div>');
	
}

function appendItems(obj,start, end,cPage) {
	for(var i=start;i<=end;i++){
		if(i==cPage){
			$(obj).append('<span class="on">'+i+'</span>');
		}else{
			$(obj).append('<a href="javascript:void(0);" onclick="gotoPage('+i+');">'+i+'</a>');
		}
	}
}

function resetPageParam(){
	$('#currPage').val('1');
	$('#totalPage').val('0');
}
function gotoPage(thePage) {
	if (thePage == currentPage) {
		return false;
	}
	if (parseInt(thePage) <= 0) {
		return false;
	}
	$('#currPage').val(thePage);
	dataList();
}
function gotoPrePage() {
	if (currentPage == '1') {
		$.alert('当前所在页是第1页!');
		return false;
	}
	if (currentPage == '0') {
		$.alert('当前所在页是第1页!');
		return false;
	}
	thePage = parseInt(currentPage) - 1;
	gotoPage(thePage);
}
function gotoNextPage() {
	if (currentPage == lastPage) {
		$.alert('当前所在页是最后一页!');
		return false;
	}
	thePage = parseInt(currentPage) + 1;
	gotoPage(thePage);
}

function goToLastPage() {
	if (currentPage == lastPage) {
		$.alert('当前所在页就是最后一页!');
		return false;
	}
	thePage = lastPage;
	gotoPage(lastPage);
}
function gotoFirstPage() {
	if (currentPage == '1') {
		$.alert('当前所在页是第1页!');
		return false;
	}
	if (currentPage == '0') {
		$.alert('当前所在页是第1页!');
		return false;
	}
	thePage = 1;
	gotoPage(thePage);
}

function toPage(totalPage) {
	var pageNum = $("#toPageNum").val();
	if(pageNum == ''){return;}
	var re = /^[0-9]+$/;
	if (!re.test(pageNum)) {
		$("#toPageNum").val('');return;
	}
	if(pageNum > totalPage){
		pageNum = totalPage;
	}
	if(pageNum < 1){
		pageNum = 1;
	}
	thePage = pageNum;
	gotoPage(thePage);
}
function IsNum(e) {
    var k = window.event ? e.keyCode : e.which;
    if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {
    } else {
        if (window.event) {
            window.event.returnValue = false;
        }else {
            e.preventDefault(); //for firefox 
        }
    }
}

function dataList(){
	loadList();
//  按钮权限已调整，不需要重新加载
//	loadRole();
}

$(function(){
	$("select[name='pagesizeSelect']").live("change",function(){
		$("#pageSize").val($(this).val());
		resetPageParam();
		dataList();
	});
});
