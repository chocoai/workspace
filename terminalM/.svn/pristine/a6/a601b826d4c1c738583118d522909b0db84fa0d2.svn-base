function myPage(totalPage,currentPage){
	var pageHtml ='';

	pageHtml +='<p class=\"site_syspage t_c\">';
	pageHtml +='<span class=\"wql_paginal\">每页<em>10</em>条</span>'
	pageHtml +='<span class=\"wql_allpage\">共<em>'+totalPage+'</em>页</span>'
	pageHtml +='<span class=\"wql_paginal\">去第</span>'
	pageHtml +='<input type=\"number\" value=\"'+currentPage+'\" id=\"pageNum\" min=\"1\" max=\"'+totalPage+'\" style = \"height: 20px;width: 40px;border-radius: 6px;padding: 0 10px;line-height: 34px\">'
	pageHtml +='<span style=\"margin-left:0px;\">页</span>'
	pageHtml +='<input type=\"button\" value=\"Go\" onclick=\"getPages()\" style=\"margin-right:10px;border: 1px solid #e3e3e3;padding: 0 10px;line-height: 20px;background: #fff;cursor: pointer;\">'
	pageHtml +='<a href=\"#\" class=\"prev\" onclick=\"prePage()"></a>'
					for(var i=0;i<totalPage;i++){
						if((i+1)==currentPage){
							pageHtml +='<a class=\"on\" href=\"#\" onclick=\"getPage('+(i+1)+')\">'+(i+1)+'</a>'
						}else{
							pageHtml +='<a href=\"#\"  onclick=\"getPage('+(i+1)+')\">'+(i+1)+'</a>'
						}
					}
	pageHtml +='<a href=\"#\" class=\"next\" onclick=\"nextPage()\"></a>'
	pageHtml +='<span class=\"all\">第 '+currentPage+' 页</span>'
	pageHtml +='</p>';
	
	$(".mgt40").html(pageHtml);
}

//上一页
function prePage(){
	if(currentPage<=1){
		$(this).parent().addClass("disabled");
		return alert("已经是首页了");
	}else{
		loadList(--currentPage);
	}
}
//下一页
function nextPage(){
	if(currentPage>=totalPage){
		$(this).parent().addClass("disabled");
		return alert("已经是最后一页了");
	}else{
		loadList(++currentPage);
	}
}
//某一页
function getPage(i){
	loadList(i);
}
function getPages(){
	var pageNum = $("#pageNum").val();
	var re = /^[0-9]+.?[0-9]*/;//判断字符串是否为数字//判断正整数/[1−9]+[0−9]∗]∗/ 
	if (!re.test(pageNum)) { 
		return alert("请输入数字");
	} 
	if(pageNum==null || pageNum==""){
		return alert("请输入");
	}else if(pageNum>totalPage){
		return alert("请输入比"+totalPage+"小的值");
	}else{
		loadList(pageNum);
	}
}