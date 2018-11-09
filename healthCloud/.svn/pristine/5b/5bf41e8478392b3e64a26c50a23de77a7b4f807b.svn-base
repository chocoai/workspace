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
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:mall:order:list">
						<li class="active"><a href="<%=contextPath%>/ywOrder/list" target="_self"><span>订单管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:mall:goods:list">
						<li><a href="<%=contextPath%>/goods/list" target="_self"><span>商品管理</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-left: 0px; padding-right: 0px;">
			<div class="col-md-12" style="padding-right: 0px; padding-top: 0px;">
				<ol class="breadcrumb">
					<form id="ywOrderForm" action="list" class="form-inline">
						<div class="form-group">
							<label for="">订单编号</label>&nbsp;
							<input type="text" class="form-control" style="width: 180px;" id="cext1" name="cext1" value="${cext1 }" placeholder="请输入订单编号">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">会员名称</label>&nbsp;
                               <input type="text" class="form-control" style="width: 180px;" id="orderName" name="orderName" value="${orderName }" placeholder="请输入会员名称">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">订单状态</label>&nbsp;
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
						<button type="submit" class="btn btn-primary">查询</button>
					</form>
				</ol>
			</div>

			<div class="col-md-12" style="padding-right: 0px; margin-top: -20px;">
				<table id="dataTable" class="table table-striped table-hover table-bordered" width="100%">
					<thead>
						<tr>
							<th style="width:5%;">序号</th>
							<th style="width:14%;">订单编号</th>
							<th style="width:9%;">会员名称</th>
							<th style="width:12%;">订单总价(元)</th>
							<th style="width:8%;">运费(元)</th>
							<th style="width:10%;">实际付款(元)</th>
                               <th style="width:9%;">订单状态</th>
                               <th style="width:15%;">下单时间</th>
							<th style="width:16%;">操作</th>
						</tr>
					</thead>
				</table>
			</div>
			
			<div class="modal" id="shipment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			    <div class="modal-dialog" style="width: 800px;" role="document">
			        <div class="modal-content"></div>
			    </div>
			</div>
			<!-- 退款弹出层 -->
          	<div class="modal" id="toRefund" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
             	<div class="modal-dialog" style="width:900px;height: 600px;" role="document">
                   	<div class="modal-content"></div>
          		</div>
          	</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">

	$(function(){
		if("${states}"=="SUCCESS"){
	        layer.msg("发货信息保存成功");
	    }else if("${states}"=="FALSE"){
            layer.msg("发货信息保存失败");
		}
		
		if("${state}" == "SUCCESS"){
			layer.msg("${msg}", { anim: 6 });
		}else if("${state}" == "FALSE"){
			layer.msg("${msg}", { anim: 6 });
		}
	});

    var allColumns = [{
    	"data" : "",
    	"render" : function(data, type, row, meta) {
   			return meta.row+1;
    	}
    },{
    	"data" : "cext1",
    	"render" : function(data, type, row, meta) {
    		if(row && row.cext1){
    			return row.cext1;
    		}
    		return "";
    	}
    },{
    	"data" : "memberName",
    	"render" : function(data, type, row, meta) {
    		if(row && row.memberName){
    			return row.memberName;
    		}
    		return "";
    	}
    },{
    	"data" : "orderAmount",
    	"render" : function(data, type, row, meta) {
    		if(row && row.orderAmount){
    			return row.orderAmount;
    		}
    		return "";
    	}
    },{
    	"data" : "freight",
    	"render" : function(data, type, row, meta) {
    		if(row && row.freight){
    			return row.freight;
    		}
    		return "";
    	}
    },{
    	"data" : "actualPayment",
    	"render" : function(data, type, row, meta) {
    		if(row && row.actualPayment){
    			return row.actualPayment;
    		}
    		return "";
    	}
    },{
    	"data" : "orderStatusView",
    	"render" : function(data, type, row, meta) {
    		if(row && row.orderStatusView){
    			return row.orderStatusView;
    		}
    		return "";
    	}
    },{
    	"data" : "createTime",
    	"render" : function(data, type, row, meta) {
    		if(row && row.createTime){
    			return row.createTime;
    		}
    		return "";
    	}
    },{
    	"data" : "",
    	"render" : function(data, type, row, meta) {
    		var htmlStr1 = "<a type='button' class='btn btn-primary btn-xs' href='show?orderId="+row.orderId+"'>订单详情</a>";
    		var htmlStr2 = "";
    		var htmlStr3 = "";
    		if(row.orderStatus == '0'){
    			htmlStr2 = "<button type='button' class='btn btn-danger btn-xs'>支付后发货</button>";
    		}else if(row.orderStatus == '1'){
    			htmlStr2 = "<button type='button' class='btn btn-info btn-xs' data-toggle='modal' data-target='#shipment' href='toShipment?orderId="+row.orderId+"'>发货</button>";
    		}else if(row.orderStatus == '2'){
    			htmlStr2 = "<button type='button' class='btn btn-info btn-xs' onclick='update("+row.orderId+",3);'>确认收货</button>";
    		}else if(row.orderStatus == '3'){
    			htmlStr2 = "<button type='button' class='btn btn-info btn-xs' onclick='update("+row.orderId+",4);'>交易完成</button>";
    		}
    		if(row.iext1 > 0){
    			htmlStr3 = "<button type='button' class='btn btn-primary btn-xs' data-toggle='modal' href='toGoodsRefund?orderId="+row.orderId+"' data-target='#toRefund'>退款</button>";
    		}
    		return htmlStr1 + "&nbsp;" + htmlStr2 + "&nbsp;" + htmlStr3;
    	}
    } ];

    $(document).ready(function() {
    	$("#dataTable").dataTable({
    		// DataTables - Features
    		"autoWidth" : true, // 控制Datatables是否自适应宽度,默认值true
    		"deferRender" : false, // 控制表格的延迟渲染，可以提高初始化的速度。默认值false
    		"info" : true, // 控制是否显示表格左下角的信息,默认值true
    		"lengthChange" : false, // 是否允许最终用户改变表格每页显示的记录数，默认值true
    		"ordering" : false, // 是否允许Datatables开启排序,默认值true
    		"paging" : true, // 是否允许表格分页，默认true
    		"processing" : true, // 是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个)默认值false
    		"scrollX" : false, // 是否允许水平滚动，默认值false
    		"scrollCollapse" : false, //当显示更少的记录时，是否允许表格减少高度, 默认值false
    		"searching" : false, // 此选项用来开启、关闭Datatables的搜索功能,默认值true
    		"serverSide" : true, // 是否开启服务器模式,默认值false 
    		"stateSave" : false, // 保存状态 - 在页面重新加载的时候恢复状态（页码等内容）,默认值false
    		// DataTables - Data
    		"ajax" : {
    			"url" : "pageList",
    			"type" : "POST",
    			"data" : {
    				"cext1" : $('#cext1').val(),
    				"orderName" : $('#orderName').val(),
    				"orderStatus" : $('#orderStatus').val()
    			},
    			"dataSrc" : "data"
    		},
    		// DataTables - Callbacks
    		// DataTables - Options
    		"destroy" : false, //销毁已经存在的Datatables实例并替换新的选项默认值false
    		"displayStart" : 0, //初始化显示的时候从第几条数据开始显示(一开始显示第几页)
    		"lengthMenu" : [ 10, 20, 40 ], // 定义在每页显示记录数的select中显示的选项
    		"orderClasses" : true, //高亮显示表格中排序的列,默认值： true
    		"orderMulti" : true, // 多列排序控制,默认值： true,用户按住shift点击表头，多列排序
    		"order" : [ [ 0, "asc" ] ], //表格在初始化的时候的排序，第2列，升序排列  
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
    		"columns" : allColumns,
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
    		},
    	});
    });

       function update(orderId,status){
       	layer.confirm("确认"+status==3?"会员已收货?":"订单已完成?", {btn: ['确认','取消']}, function(){
       		$.ajax({
       			cache : true,
       			type : "post",
       			url : 'updateOrderStatus',
       			async : false,
       			data : {
       				"orderId" : orderId,
       				"status" : status
       			},
       			traditional : true,
       			success : function(data) {
       				layer.msg('修改成功', { anim: 6 });
       				setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
       					$("#ywOrderForm").submit();
       				},1500);
       			},error : function() {
       				layer.msg('修改失败', { anim: 6 });
       			}
       		});
       	}, function(){
       		return;
       	});
       };
       
       //清空缓存,走后台
       $("body").on("hidden.bs.modal", ".modal", function () {
           $(this).removeData("bs.modal");
       });
	
</script>
</body>
</html>
