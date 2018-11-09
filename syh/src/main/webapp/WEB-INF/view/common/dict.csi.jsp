
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${type == '0'}">
	  <c:forEach items="${list}" var="dict">
	          <option value="${dict.itemValue}" <c:if test="${dict.itemValue eq number}"> selected='true'</c:if> >${dict.itemKey}</option>
	  </c:forEach>
</c:if>
<c:if test="${type == '1'}">
	  <c:forEach items="${list}" var="dict">
	          <option value="${dict.itemKey}" <c:if test="${dict.itemKey eq number}"> selected='true'</c:if> >${dict.itemKey}</option>
	  </c:forEach>
</c:if>
<c:if test="${type == '2'}">
	  <c:forEach items="${list}" var="dict">
		  <c:choose>
			   <c:when test="${other =='1' or other =='2'}"> 
				     <c:choose>
						   <c:when test="${dict.itemValue eq 2 or dict.itemValue eq 3}"> 
						    	<option value="${dict.itemValue}" <c:if test="${dict.itemValue eq number}"> selected='true'</c:if>>${dict.itemKey}</option>
						   </c:when>
						   <c:otherwise>
						   		<option value="${dict.itemValue}" disabled="disabled">${dict.itemKey}</option>
						   </c:otherwise>
					 </c:choose>
			   </c:when>
			   <c:otherwise>
			   		<c:choose>
							<c:when test="${dict.itemValue gt 3}"> 
						    	<option value="${dict.itemValue}" <c:if test="${dict.itemValue eq number}"> selected='true'</c:if>>${dict.itemKey}</option>
						   </c:when>
						   <c:otherwise>
						   		<option value="${dict.itemValue}" disabled="disabled">${dict.itemKey}</option>
						   </c:otherwise>
					 </c:choose>
			   </c:otherwise>
		 </c:choose>
	  
	  </c:forEach>
</c:if>