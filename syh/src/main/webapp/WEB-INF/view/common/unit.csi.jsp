
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${type == '0'}">
	<select id="unitLoad" data-placeholder="请选择单位" class="chosen-select" style="width:100%;" tabindex="2" >
		  <c:forEach items="${list}" var="unit">
		          <option value="${unit.cid}" hassubinfo="true" >${unit.unitName}</option>
		  </c:forEach>
	</select>	  
</c:if>
<!-- 1： 多选 -->
<c:if test="${type != '0'}">
	<select id="unitLoad" data-placeholder="请选择单位" class="chosen-select"  style="width:100%;" tabindex="2" >
		  <c:forEach items="${list}" var="unit">
		          <option value="${unit.cid}" hassubinfo="true" >${unit.unitName}</option>
		  </c:forEach>
	</select>	  
</c:if>

<script type="text/javascript">
	$("#unitLoad").chosen({
		no_results_text: "没有找到结果！",//搜索无结果时显示的提示
		search_contains:true,   //关键字模糊搜索，设置为false，则只从开头开始匹配
		allow_single_deselect:true, //是否允许取消选择
		disable_search_threshold:0 //少于 0 项时隐藏搜索框
	})
    document.getElementById("unitLoad_chosen").style.width="269px"
</script>