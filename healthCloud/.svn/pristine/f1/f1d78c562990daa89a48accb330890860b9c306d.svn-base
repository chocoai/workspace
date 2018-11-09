<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/res/css/bootstrap-select.css">    
<style>
.form-group .bootstrap-select {
	float:left !important;
	width:200px !important;
	display:inline-block !important;
}
.form-group .dropdown-menu {
	width:200px !important;
	max-height: 250px !important;
}
</style>
<script type="text/javascript" src="<%=contextPath%>/res/js/bootstrap-select.js"></script> 
<script type="text/javascript">  
	$(window).on("load", function () {  
	    $(".selectpicker").selectpicker({  
	        "selectedText": "cat"  
	    });  
	});  
</script>
</head>
<body>
	<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
		<div class="row-fluid" >
			<div id="left" class="collapse in" style="width: 130px;">
				<div id="secondMenu">
					<ul class="nav nav-pills nav-stacked text-center">
						<shiro:hasPermission name="submenu:workOrder:list">
							<li class="active"><a href="<%=contextPath%>/workOrder/list" target="_self"><span>工单管理</span></a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="submenu:service:list">
							<li><a href="<%=contextPath%>/service/list" target="_self"><span>服务管理</span></a></li>
						</shiro:hasPermission>
					</ul>
				</div>
			</div>
			<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
			<div id="right" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;">
                <!-- 分配人员 -->
                <div class="modal fade bs-example-modal-sm" id="toAssign" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" style="width:800px;" role="document">
                        <div class="modal-content"></div>
                    </div>
                </div>
                
                <!-- 退款弹出层 -->
                <div class="modal fade bs-example-modal-sm" id="toRefund" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" style="width:550px;" role="document">
                        <div class="modal-content"></div>
                    </div>
                </div>

				<div class="col-md-12" style="padding-right: 0px;padding-top: 0px;padding-left: 0px;">
                    <button class="btn btn-info btn-sm" id="batchAssign">- 批量分配</button>&nbsp;&nbsp;
					<input id="assignBtn" type="hidden" data-toggle="modal" href="#" data-target="#toAssign">
					<ol class="breadcrumb" style="margin-top: 10px;">
						<form id="workOrderForm" action="list"  class="form-inline">
                            <div class="form-group">
                                <label style="float:left; margin:0 6px 0 0; line-height:34px;">所属服务商</label>&nbsp;
								<select id="providerId" name="providerId" class="selectpicker show-tick form-control" data-live-search="true">
								   <option value="">--请选择--</option>
								   <c:forEach items="${providerList }" var="list">
								       <option value="${list.providerId}" <c:if test="${providerId == list.providerId}">selected = "selected"</c:if> >${list.providerName}</option> 
								   </c:forEach>
								</select>
                            </div>
                            &nbsp;&nbsp;
							<div class="form-group">
								<label for="">工单编号</label>&nbsp; 
								<input type="text" class="form-control" style="width: 180px;" id="cext1" name="cext1" value="${cext1 }" placeholder="请输入工单编号">
							</div>
							&nbsp;&nbsp;
                            <div class="form-group">
                                <label for="">工单状态</label>&nbsp;
                                <select id="orderStatus" name="orderStatus" class="form-control">
                                    <option value="">全部</option>
                                    <c:forEach items="${orderType}" var="entry">
                                    	<c:choose>
											<c:when test="${orderStatus==entry.key}">
												<option value="${entry.key}" selected="selected" />${entry.value}
											</c:when>
											<c:otherwise>
												<option value="${entry.key}" />${entry.value}
											</c:otherwise>
										</c:choose>
                                    </c:forEach>
                                </select>
                            </div>
							&nbsp;&nbsp;
							<button type="submit" class="btn btn-primary"> 查询  </button>							
						</form>
					</ol>
				</div>

				<div class="col-md-12" style="padding-left: 0px;padding-right: 0px;margin-top: -20px;">
					<table id="dataTable" class="table table-striped table-hover table-bordered">
						<thead>
							<tr>
								<th><input type="checkbox" id="checkAll">  全选</th>
								<th>工单编号</th>
								<th>服务供应商</th>
								<th>订购服务</th>
								<th>订购用户</th>
								<th>服务对象</th>
								<th>服务费用</th>
								<th>工单状态</th>
								<th style="width:20%;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${workOrderList }" var="workOrder" varStatus="status">
								<tr>
									<td style="text-align: center;">
                                        <c:choose>
                                            <c:when test="${workOrder.orderStatus == '1'}">
                                                <input type="checkbox" id="check_workOrder" name="check_workOrder" value="${workOrder.orderId}">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="checkbox" disabled="disabled">
                                            </c:otherwise>
                                        </c:choose>
									</td>
									<td>${workOrder.cext1 }</td>
									<td>${workOrder.providerName }</td>
									<td>${workOrder.serviceName }</td>
									<td>${workOrder.memberName }</td>
									<td>${workOrder.serviceObject }</td>
									<td>${workOrder.serviceFee }</td>
									<td>${workOrder.orderStatusView }</td>
									<td>
	                                    <a type="button" class="btn btn-info btn-xs" href="show?orderId=${workOrder.orderId }">查看详情</a>
						                <c:choose>
						                    <c:when test="${workOrder.orderStatus == '1'}">
                                                <button class="btn btn-primary btn-xs" type="button" data-toggle="modal" href="toAssign?orderId=${workOrder.orderId}" data-target="#toAssign">分配人员</button>
						                    </c:when>
						                    <c:when test="${workOrder.orderStatus == '3'}">
	                                        	<button type="button" class="btn btn-info btn-xs" onclick="completed(${workOrder.orderId})">交易完成</button>
						                    </c:when>
						                    <c:when test="${workOrder.orderStatus == '5'}">
	                                        	<button class="btn btn-primary btn-xs" type="button" data-toggle="modal" href="toServiceRefund?orderId=${workOrder.orderId}" data-target="#toRefund">申请退款</button>
						                    </c:when>
						                </c:choose>
	                                    <a type="button" class="btn btn-info btn-xs" href="showLog?orderId=${workOrder.orderId }">查看日志</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#orderStatus").val("${orderStatus}");
	    $("#payStatus").val("${payStatus}");
	    
		if("${state}" == "SUCCESS"){
			layer.msg("${msg}", { anim: 6 });
		}else if("${state}" == "FALSE"){
			layer.msg("${msg}", { anim: 6 });
		}
	});
	
	$(document).ready(function() {
		
		$('#dataTable').dataTable({
			// DataTables - Features
			"autoWidth" : true, // 控制Datatables是否自适应宽度,默认值true
			"deferRender" : false, // 控制表格的延迟渲染，可以提高初始化的速度。默认值false
			"info" : true, // 控制是否显示表格左下角的信息,默认值true
			"lengthChange" : false, // 是否允许最终用户改变表格每页显示的记录数，默认值true
			"ordering" : false, // 是否允许Datatables开启排序,默认值true
			"paging" : true, // 是否允许表格分页，默认true
			"processing" : true, // 是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个)默认值false
			"scrollX" : false, // 是否允许水平滚动，默认值false
			//"scrollY": "200px", // 控制表格的垂直滚动。 Vertical scrolling 强制DataTable为指定的高度，并且会允许任何超出当前视口的数据进行滚动
			"scrollCollapse" : false, //当显示更少的记录时，是否允许表格减少高度, 默认值false
			"searching" : false, // 此选项用来开启、关闭Datatables的搜索功能,默认值true
			"stateSave" : false, // 保存状态 - 在页面重新加载的时候恢复状态（页码等内容）,默认值false
			"destroy" : false, //销毁已经存在的Datatables实例并替换新的选项默认值false
			"displayStart" : 0, //初始化显示的时候从第几条数据开始显示(一开始显示第几页)
			"lengthMenu" : [ 10, 20, 40 ], // 定义在每页显示记录数的select中显示的选项
			"orderClasses" : true, //高亮显示表格中排序的列,默认值： true
			"orderMulti" : true, // 多列排序控制,默认值： true,用户按住shift点击表头，多列排序
			"order" : [ [ 5, "desc" ] ], //表格在初始化的时候的排序，第2列，升序排列  
			"pageLength" : 10, // 改变初始化页长度（每页多少条数据）,默认值:10
			"pagingType" : "full_numbers", // 分页按钮显示选项,full_numbers 首页，尾页，上一页和下一页四个按钮,加上数字按钮
			"renderer" : "bootstrap", // 显示组件渲染器类型
			"search" : {
				"caseInsensitive" : false, //在搜索或者过滤时，是否大小写敏感,默认值true
				"regex" : false, // 允许或者禁止对在搜索字符串中出现的正则表达式字符强制编码, 默认值false
				"smart" : true, // 允许或者禁止DataTables的只能过滤（搜索）功能, 默认值true
			},
			"columnDefs" : [ {
				"orderable" : false,//禁用排序
				"targets" : [ 0, 8 ] //指定的列
			} ],

			"language" : { //国际化配置  
				"processing" : "正在获取数据，请稍后...",
				"lengthMenu" : "显示 _MENU_ 条",
				"zeroRecords" : "没有您要搜索的内容",
				"info" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
				"infoEmpty" : "记录数为0",
				"infoFiltered" : "(全部记录数 _MAX_ 条)",
				"loadingRecords" : "数据正在加载中，请稍后...",
				"emptyTable" : "没有搜索到相关内容",
				"search" : "搜索&nbsp;&nbsp;",
				"url" : "",
				"paginate" : {
					"first" : "第一页",
					"previous" : "上一页",
					"next" : "下一页",
					"last" : "最后一页"
				},
				"aria" : {
					"sortAscending" : ": 以升序排列此列",
					"sortDescending" : ": 以降序排列此列"
				}
			}
		});
	});
	
	//全选、取消全选的事件 
	$("#checkAll").click(function() {
		if ($("#checkAll").prop("checked")) {
			$("input[type='checkbox'][id='check_workOrder']").prop("checked", true);
		} else {
			$(":checkbox").prop("checked", false);
		}
	});	
	
	//子复选框的事件
	$(":checkbox").click(function() {
		if (!$("#check_workOrder").checked) {
			$("#checkAll").prop("checked",false);
		}
		var ckbLength = $("input[type='checkbox'][id='check_workOrder']").length;
		var checkedLengtn = $("input[type='checkbox'][id='check_workOrder']:checked").length;
		if (checkedLengtn == ckbLength) {
			$("#checkAll").prop("checked",true);
		}
	});
	
	$("#batchAssign").click(function(){
		var orderIds = new Array();
		$("input[type='checkbox'][name='check_workOrder']:checked").each(function(i) {
			orderIds[i] = parseInt($(this).val());
		});
		if(orderIds.length == 0){
			layer.msg('请先选取要分配的工单', { anim: 6 });
			return;
		}
		layer.confirm('确认分配这些工单吗？', { btn: ['确认','取消'] }, function(index){
			//通过button发起请求
			$("#assignBtn").attr("href","toAssign?orderIds="+orderIds);
			$("#assignBtn").click();
			layer.close(index);
		}, function(){
			return;
		});
	});
       
	function completed(orderId){
        layer.confirm('是否确定工单已完成？', {btn: ['确认','取消']}, function(){
        	$.ajax({
        		cache : true,
        		type : "get",
        		url : 'completed',
        		async : true,
        		data : {
        			"orderId" : orderId,
        		},
        		success : function(data) {
        			layer.msg('审核成功', { anim: 6 });
        			setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
        				$("#workOrderForm").submit();
        			},2000);
        		},
        		error : function() {
        			layer.msg('审核失败', { anim: 6 });
        		}
        	});
        }, function(){
        	return;
        });
	}
	
	//清空缓存,走后台
	$("body").on("hidden.bs.modal", ".modal", function () {
	    $(this).removeData("bs.modal");
	});
</script>
</body>
</html>
