
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${type == '0'}">
	  <option value="">---请选择---</option>
	  <c:forEach items="${list}" var="score">
	          <option value="${score.cid}" <c:if test="${score.cid eq number}"> selected='true'</c:if> >${score.ruleName}</option>
	  </c:forEach>
</c:if>

