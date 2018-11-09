<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<p class="site_syspage t_c">
<form method="post" id="pageForm">
	<span class="wql_paginal">每页<em>10</em>条
	</span> <span class="wql_allpage">共<em>${pages }</em>页
	</span> <span class="wql_paginal">去第</span> <input type="number" value="${pageNum }"
		id="vpageNum" min="1" max="${pages }"
		style="height: 20px; width: 40px; border-radius: 6px; padding: 0 10px; line-height: 34px">
	<span style="margin-left: 0px;">页</span><input type="button" value="Go"
		onclick="vgetPages()"
		style="margin-right: 10px; border: 1px solid #e3e3e3; padding: 0 10px; line-height: 20px; background: #fff; cursor: pointer;">
	<a href="#" class="prev" onclick="vprePage()"></a>
	<c:forEach var="s" begin="1" end="${pages }">
		<a <c:if test="${s==pageNum}">class="on"</c:if> href="#"
			onclick="vgetPage('${s}')">${s}</a>
	</c:forEach>
	<input type="hidden" name="pageValue" value="${pageNum }"> <a
		href="#" class="next" onclick="vnextPage()"></a> <span class="all">第${pageNum }
		页</span>
</form>
</p>
<script type="text/javascript">
var totalPage = "${pages }";
//某一页
function vgetPages(){
	var pageNum = $('#vpageNum').val();
	if(pageNum==null || pageNum==""){
		return alert("请输入");
	}
	if(pageNum>totalPage){
		return alert("请输入比"+totalPage+"小的值");
	}
	$('input[name="pageValue"]').val(pageNum);
	$("#pageForm").submit()
}
//某一页
function vgetPage(pageNum){
	$('input[name="pageValue"]').val(pageNum);
	$("#pageForm").submit()
}
//下一页
function vnextPage(){
	var pageNum = $('#vpageNum').val();
	if(pageNum>=totalPage){
		$(this).parent().addClass("disabled")
		return alert("已经是最后一页了");
	}else{
		$('input[name="pageValue"]').val(++pageNum);
		$("#pageForm").submit()
	}
}
//上一页
function vprePage(){
	var pageNum = $('#vpageNum').val();
	if(pageNum<=1){
		$(this).parent().addClass("disabled")
		return alert("已经是首页了");
	}else{
		$('input[name="pageValue"]').val(--pageNum);
		$("#pageForm").submit()
	}
}

</script>