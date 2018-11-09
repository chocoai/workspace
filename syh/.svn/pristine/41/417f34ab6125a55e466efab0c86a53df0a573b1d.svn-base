<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- 分页 strat -->
<div id="pageInfo" class="pagination page-notop page">
    <span id="firstPageIco" class="disabled"><a id="firstPage" href="javascript:" aria-label="firstPage"><i class="icon-step-backward"></i></a></span>
    <span id="prePageIco" class="disabled"><a id="prePage" href="javascript:" aria-label="Previous"><i class="icon-backward"></i></a></span>
    <span class="lrline">
        <input id="currentPage" type="text" value="${pageInfo.pageNum}" name="currentPage" class="page-input">共 <em id="totalPage">${pageInfo.pages}</em> 页
    </span>
    <span id="nextPageIco" <c:if test="${pageInfo.pages<=1}">class="disabled"</c:if> ><a id="nextPage" href="javascript:" aria-label="Next"><i class="icon-forward"></i></a></span>
    <span id="lastPageIco" <c:if test="${pageInfo.pages<=1}">class="disabled"</c:if> ><a id="lastPage" href="javascript:" aria-label="LastPage"><i class="icon-step-forward"></i></a></span>
</div>
<!-- 分页 end -->