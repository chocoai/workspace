<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">紧急联系人列表</h4>
</div>

	<div class="modal-body">
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th><b>姓名</b></th>
					<th><b>联系电话</b></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${linkmans }" var="person" varStatus="status">
				<tr>
					<td>${person.lankman}</td>
					<td>${person.phoneNum}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>

	</div>

	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	</div>

