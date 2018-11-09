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
					<shiro:hasPermission name="system:role:list">
						<li><a href="<%=contextPath%>/role/list" target="_self"><span>角色管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="system:resource:list">
						<li><a href="<%=contextPath%>/res/list" target="_self"><span>权限资源管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="system:dict:list">
						<li class="active"><a href="<%=contextPath%>/dict/list" target="_self"><span>数据字典管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="system:area:list">
						<li><a href="<%=contextPath%>/area/list" target="_self"><span>区域管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="app:version:list">
						<li><a href="<%=contextPath%>/version/list" target="_self"><span>版本管理</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;">
			<ol class="breadcrumb">
				<li><a href="#">系统管理</a></li>
				<li><a href="#">数据字典管理</a></li>
			</ol>

			<div class="modal fade bs-example-modal-lg" id="addDict" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" style="width: 700px;" role="document">
					<div class="modal-content "></div>
				</div>
			</div>

			<div class="modal fade bs-example-modal-lg" id="updateDict" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" style="width: 700px;" role="document">
					<div class="modal-content "></div>
				</div>
			</div>

			<div class="col-md-12" style="padding-right: 0px;padding-left: 0px;">
				<table id="dataTable" class="table table-striped table-hover table-bordered" style="margin-top: 10px;">
					<thead>
						<tr>
							<th>字典中文名</th>
							<th>字典英文名</th>
							<th>字典key</th>
							<th>字典值</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${dictList }" var="dict" varStatus="status">
							<tr>
								<td>${dict.dictZhName }</td>
								<td>${dict.dictEnName }</td>
								<td>${dict.dictKey }</td>
								<td>${dict.dictValue }</td>
								<td>
									<button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#updateDict" href="toEdit?dictId=${dict.dictId}">编辑</button>
									<button type="button" class="btn btn-warning btn-xs" onclick="toDelete(${dict.dictId})">删除</button>
									<button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#updateDict" href="toAdd?parentId=${dict.dictId}">新增子字典</button>
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
	function toDelete(dictId) {
		//询问框
		layer.confirm('您确认删除此字典项吗？删除后引用此字典项的数据项将不可用！', {
		  btn: ['确认','取消'] //按钮
		}, function(){
			window.location.href = "delete?dictId=" + dictId;
		}, function(){
		  return;
		});
	}

	$("#updateDict").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
	});

	$("#addDict").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
	});
	
	$(document).ready(function() {
		$('#dataTable').dataTable({
			"bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示
			"aLengthMenu" : [ 10, 20, 40 ], //更改显示记录数选项
			"lengthChange": false,//禁用调整显示记录数选项
			"iDisplayLength" : 10, //默认显示的记录数
			"bAutoWidth" : true, //是否自适应宽度
			"bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
			"bPaginate" : true, //是否显示（应用）分页器  
			"bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
			"sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
			"bSort" : true, //是否启动各个字段的排序功能  
			"aaSorting" : [ [1, "asc" ] ], //默认的排序方式，第2列，升序排列  
			columnDefs:[{
				 orderable:false,//禁用排序
				 targets:[4]   //指定的列
			 }],
			"bFilter" : true, //是否启动过滤、搜索功能  
			"oLanguage" : { //国际化配置  
				"sProcessing" : "正在获取数据，请稍后...",
				"sLengthMenu" : "显示 _MENU_ 条",
				"sZeroRecords" : "没有您要搜索的内容",
				"sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
				"sInfoEmpty" : "记录数为0",
				"sInfoFiltered" : "(全部记录数 _MAX_ 条)",
				"sInfoPostFix" : "",
				"sSearch" : "搜索&nbsp;&nbsp;",
				"sUrl" : "",
				"oPaginate" : {
					"sFirst" : "第一页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "最后一页"
				}
			}

		});
		
		$(".col-sm-6").eq(0).wrapInner(
				'<button class="btn btn-primary btn-sm" data-toggle="modal" href="toAdd" data-target="#addDict">新增数据字典</button>&nbsp;&nbsp;&nbsp;'+
				'<button id="btnReload" class="btn btn-info btn-sm" onclick="reloadDict()">重新加载数据字典</button>');
	});
	
	function reloadDict(){
		$('#btnReload').attr({"disabled":"disabled"});
		var url = "reload";
		$.ajax({
			url: url, 
			data:{},
			dataType:"json",
			cache:false,
			async:false,
			success : function(data){
				$('#btnReload').removeAttr("disabled");
				var flag = data.result;
	       		if(flag){
	       			layer.msg('数据字典重载成功', { anim: 6 });
	       		}else{
	       			layer.msg('重载失败', { anim: 6 });
	       		}
			}
		});
	}
</script>
</body>
</html>
