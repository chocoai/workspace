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
<script type="text/javascript" src="<%=contextPath%>/res/js/bootstrap-select.js"></script> 
<style>
.form-group .bootstrap-select {
	float: left !important;
	width: 200px !important;
	display: inline-block !important;
}
.form-group .dropdown-menu {
	width: 200px !important;
	max-height: 250px !important;
}
</style>
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
							<li><a href="<%=contextPath%>/workOrder/list" target="_self"><span>工单管理</span></a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="submenu:service:list">
							<li class="active"><a href="<%=contextPath%>/service/list" target="_self"><span>服务管理</span></a></li>
						</shiro:hasPermission>
					</ul>
				</div>
			</div>
			<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
			<div id="right" style="padding-left: 0px; padding-right: 0px;">
				<div class="col-md-12" style="padding-right: 0px;padding-top: 0px;padding-left: 0px;">
					<a class="btn btn-info btn-sm" href="edit">+ 新增服务</a>&nbsp;&nbsp;
					<button class="btn btn-info btn-sm" id="batchDelete">- 删除服务</button>&nbsp;&nbsp;
						
					<ol class="breadcrumb" style="margin-top: 10px;">
						<form id="serviceForm" action="list" class="form-inline">
							<div class="form-group">
								<label for="" style="float:left; margin:0 6px 0 0; line-height:34px;">所属服务商</label> 
								<select id="providerId" name="providerId" class="selectpicker show-tick form-control" data-live-search="true">
								   <option value="">--请选择--</option>
								   <c:forEach items="${providerList }" var="list">
								       <option value="${list.providerId}" <c:if test="${service.providerId == list.providerId}">selected = "selected"</c:if> >${list.providerName}</option> 
								   </c:forEach>
								</select>
							</div>
							&nbsp;
							<div class="form-group">
								<label for="">服务名称</label> 
								<input type="text" class="form-control" style="width: 120px;" id="serviceName" name="serviceName" value="${service.serviceName }" placeholder="请输入服务名称">
							</div>
							&nbsp;
							<div class="form-group">
								<label for="">服务类别</label>
								<form:select id="serviceCategory" path="service.serviceCategory" onchange="getServiceTypeItem();" name="serviceCategory" items="${applicationScope.service_category__}" itemLabel="value" itemValue="key" cssStyle="" class="form-control"/>
							</div>
							<div class="form-group" id="is_show">
								<label for="">服务类型</label> 
								<select id="serviceType" name="serviceType" class="form-control" >
								   <c:forEach items="${serviceTypeList }" var="serviceType1">
								       <option value="${serviceType1.dictValue}" <c:if test="${service.serviceType==serviceType1.dictValue}">selected = "selected"</c:if> >${serviceType1.dictKey}</option> 
								   </c:forEach>
								</select>
							</div>
							&nbsp;
							<div class="form-group">
								<label for="">服务营业状态</label>
								<select id="serviceStatus" name="serviceStatus" class="form-control">
									<option value="">全部</option>
									<option value="1">营业</option>
									<option value="0">停业</option>
								</select>
							</div>
							&nbsp;
							<button type="submit" class="btn btn-primary">查询</button>
						</form>
					</ol>
				</div>
				
				
				<div class="col-md-12" style="padding-left: 0px;padding-right: 0px;margin-top: -20px;">
					<table id="dataTable" class="table table-striped table-hover table-bordered">
						<thead>
							<tr>
								<th style="width: 6%;"><input type="checkbox" id="checkAll"> 选择</th>
								<th style="width: 10%;">服务名称</th>
								<th style="width: 9%;">服务类别</th>
								<th style="width: 9%;">服务类型</th>
								<th style="width: 15%;">服务供应商</th>
								<th style="width: 9%;">服务单价(元)</th>
								<th style="width: 9%;">计价单位</th>
								<!-- <th style="width: 20%;">服务简介</th> -->
								<th style="width: 10%;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${serviceList }" var="service" varStatus="status">
								<tr>
									<td style="text-align: center;">
										<input type="checkbox" id="check_service" name="check_service" value="${service.serviceId}">
									</td>
									<td>${service.serviceName }</td>
									<td>${service.categoryName }</td>
									<td>${service.typeName }</td>
									<td>${service.provider.providerName }</td>
									<td>${service.unitPrice }</td>
									<td>${service.cext1 }</td>
									<%-- <td>${service.serviceIntro }</td> --%>
									<td>
										<a type="button" class="btn btn-primary btn-xs" href="edit?serviceId=${service.serviceId}">编辑</a>
										<button type="button" class="btn btn-danger btn-xs" onclick="toDelete(${service.serviceId})">删除</button> 
										<c:if test="${service.serviceStatus == '0' }">
											<button type="button" class="btn btn-info btn-xs" onclick="putAway('1', ${service.serviceId})">上架</button>
										</c:if>
										<c:if test="${service.serviceStatus == '1' }">
											<button type="button" class="btn btn-warning btn-xs" onclick="soldOut('0', ${service.serviceId})">下架</button>
										</c:if>
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
		if("${service.serviceCategory}"){
	    	$("#is_show").show();
    	}else{
    		$("#is_show").hide();
    	}
		$("#serviceStatus").val("${service.serviceStatus}");
		
		if("${state}"=="SUCCESS"){
	        layer.msg("提交成功");
	    }else if("${state}"=="FALSE"){
            layer.msg("提交失败");
		}
	});
	
	function putAway(serviceStatus, serviceId){
    	if(serviceStatus == "正常"){
    		layer.msg("该商品已上架");
    	}else{
   			layer.confirm('您确认要上架该商品吗？', {btn: ['确认','取消']}, function(){
				$.ajax({
					cache : true,
					type : "post",
					url : 'changeServiceStatus',
					async : true,
					data : {
						"serviceId" : serviceId,
						"serviceStatus" : serviceStatus
					},
					traditional : true,
					success : function(data) {
						layer.msg('上架成功', { anim: 6 });
						setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
							$("#serviceForm").submit();
						},2000);
					},
					error : function() {
						layer.msg('上架失败', { anim: 6 });
					}
				});
			}, function(){
				return;
			});
		}
	}
		
	function soldOut(serviceStatus, serviceId){
		if(serviceStatus == "已下架"){
			layer.msg("该商品已下架");
		}else{
			layer.confirm('您确认要下架该商品吗？', {btn: ['确认','取消']}, function(){
				$.ajax({
					cache : true,
					type : "post",
					url : 'changeServiceStatus',
					async : true,
					data : {
    					"serviceId" : serviceId,
    					"serviceStatus" : serviceStatus
					},
					traditional : true,
					success : function(data) {
    					layer.msg('下架成功', { anim: 6 });
    					setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
    						$("#serviceForm").submit();
        				},2000);
					},
					error : function() {
    					layer.msg('下架失败', { anim: 6 });
					}
				});
    		}, function(){
    			return;
			});
		}
	}


	function toDelete(serviceId) {
		//询问框
		layer.confirm('确认删除该服务吗？删除后该服务将不可用，您可以下架该服务，在再次启用时上架即可', {
		  btn: ['确认','取消'] //按钮
		}, function(){
			window.location.href = "delete?serviceId=" + serviceId;
		}, function(){
		  return;
		});
	}

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

			// DataTables - Data

			// DataTables - Callbacks

			// DataTables - Options
			"destroy" : false, //销毁已经存在的Datatables实例并替换新的选项默认值false
			"displayStart" : 0, //初始化显示的时候从第几条数据开始显示(一开始显示第几页)
			"lengthMenu" : [ 10, 20, 40 ], // 定义在每页显示记录数的select中显示的选项
			"orderClasses" : true, //高亮显示表格中排序的列,默认值： true
			"orderMulti" : true, // 多列排序控制,默认值： true,用户按住shift点击表头，多列排序
			"order" : [ [ 4, "desc" ] ], //表格在初始化的时候的排序，第2列，升序排列  
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
				"targets" : [ 0, 7 ] //指定的列
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
	$('#checkAll').click(function() {
		if ($("#checkAll").prop("checked")) {
			$(':checkbox').prop("checked", true);
		} else {
			$(':checkbox').prop("checked", false);
		}
	}); 

	//子复选框的事件
	$(":checkbox").click(function() {
		if (!$("#check_service").checked) {
			$("#checkAll").prop("checked",false);
		}
		var ckbLength = $("input[type='checkbox'][id='check_service']").length;
		var checkedLengtn = $("input[type='checkbox'][id='check_service']:checked").length;
		if (checkedLengtn == ckbLength) {
			$("#checkAll").prop("checked",true);
		}
	});

	$("#batchDelete").click(function(){
		var serviceIds = new Array();
		$("input[type='checkbox'][name='check_service']:checked").each(function(i) {
			serviceIds[i] = $(this).val();
		});
		if(serviceIds.length == 0){
			layer.msg('请先选取要删除的服务', {anim: 6});
			return;
		}

		layer.confirm('确认删除这些服务吗？删除后这些服务将不可用，您可以下架这些服务，在再次启用时上架即可', {btn: ['确认','取消']}, function(){
			$.ajax({
				cache : true,
				type : "post",
				url : 'batchDelete',
				async : false,
				data : {
					"serviceIds" : serviceIds
				},
				traditional : true,
				success : function(data) {
					layer.msg('删除成功', { anim: 6 });
					setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
						$("#serviceForm").submit();
					},2000);
				},
				error : function() {
					layer.msg('删除失败', { anim: 6 });
				}
			});
		}, function(){
			return;
		});
	});
	
	//二级联动查询
    function getServiceTypeItem(){
    	var value  = $("#serviceCategory").val();
    	if(value){
	    	$("#is_show").show();
    	}else{
    		$("#is_show").hide();
    	}
    	$.ajax({
			cache : true,
            type : "post",
            url : '<%=contextPath%>/dict/getTypeItem',
            data : { parentValue : value, type : "service_category" },
            async : false,
            dataType : "json",
            traditional : true,
            success : function(data) {
            	 $("#serviceType").empty();
            	for(var i=0;i<data.length;i++){
            		 $("#serviceType").append("<option value='"+data[i].dictValue+"'>"+data[i].dictKey+"</option>"); // 1.为Select追加一个Option(下拉项)    
            	}
          	},
            error : function() {
            	layer.msg('服务类型加载失败', { anim: 6 });
          	}
        });
    }
	
</script>
</body>
</html>
