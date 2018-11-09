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
	$("#currPage").val(cPage);
	$("#totalPage").val(tPage);
	$("#pageSize").val(pSize);
	
	$(obj).append('<div class="base_page clearfix">');
	$(obj).append('<p class="fl"><span>共<strong>'+tRows+'</strong>条记录</span> <span>每页 <select name="pagesizeSelect"><option '+(pSize==10?selected="selected":'')+'>10</option><option '+(pSize==20?selected="selected":'')+'>20</option><option '+(pSize==30?selected="selected":'')+'>30</option></select> 条 </span> </p>');
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
		$.alert('当前所在页就是第' + thePage + '页!');
		return false;
	}
	if (parseInt(thePage) <= 0) {
		return false;
	}
	$('#currPage').val(thePage);
	loadList();
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
		loadList();
	});
});
