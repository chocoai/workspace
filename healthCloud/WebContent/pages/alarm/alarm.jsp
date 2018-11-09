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
	<h4 class="modal-title" id="myModalLabel">告警详情</h4>
</div>

	<div class="modal-body">
		<table class="table table-hover table-bordered ">
			<tr>
				<td><b>会员姓名</b></td>
				<td>${alarm.realName }</td>
				<td><b>设备IMEI</b></td>
				<td>${alarm.imei }</td>
			</tr>
			<tr>
				<td><b>告警类型</b></td>
				<td>${alarm.alarmTypeView }</td>
				<td><b>告警时间</b></td>
				<td>${alarm.alarmTime }</td>
			</tr>
			<tr>
				<td colspan="4">${alarm.alarmContent }</td>
			</tr>
		</table>

	</div>

	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	</div>

