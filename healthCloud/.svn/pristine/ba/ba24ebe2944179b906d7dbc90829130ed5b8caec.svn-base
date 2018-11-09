<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
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
						<li><a href="<%=contextPath%>/dict/list" target="_self"><span>数据字典管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="system:area:list">
						<li><a href="<%=contextPath%>/area/list" target="_self"><span>区域管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="app:version:list">
						<li class="active"><a href="<%=contextPath%>/version/list" target="_self"><span>版本管理</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;">
			<div class="col-md-12" style="padding-left: 0px;padding-right: 0px;margin-top: 5px;">
				<ol class="breadcrumb">
					<form id="versionForm" action="list" class="form-inline">
						<div class="form-group">
							<label for="">应用名称</label>
							<form:select id="cAppCode" path="cAppCode" name="cAppCode" items="${applicationScope.apply_name__}" itemLabel="value" itemValue="key" cssStyle="width: 160px;" class="form-control"/>
						</div>
						&nbsp;&nbsp;
						<button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
						
						<a class="btn btn-info" href="edit">+ 新增版本</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-info" id="batchDelete" >- 删除版本</button>&nbsp;&nbsp;&nbsp;&nbsp;
					</form>
				</ol>
			</div>
			
			<div class="col-md-12" style="padding-left: 0px;padding-right: 0px;margin-top: -15px;">
				<table id="dataTable" style="table-layout:fixed" class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th width="6%"><input type="checkbox" id="checkAll"> 选择</th>
							<th width="8%">应用名称</th>
							<th width="9%">版本编码</th>
							<th width="6%">版本号</th>
							<th>更新内容</th>
							<th width="15%">更新时间</th>
							<th width="8%">下载次数</th>
							<th width="9%">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${versionList }" var="version" varStatus="status">
							<tr>
								<td>
									<input type="checkbox" id="check_version" name="check_version" value="${version.kid}">
								</td>
								<td>${version.cAppCodeView }</td>
								<td>${version.cVersionDesc }</td>
								<td>${version.iVersionCode }</td>
								<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;" title="${version.cUpdateDesc }">${version.cUpdateDesc }</td>
								<td>${version.cUploadDate }</td>
								<td>${version.cExt01 }</td>
								<td>
									<a type="button" class="btn btn-primary btn-xs" href="edit?kid=${version.kid}">编辑</a>
									<button type="button" class="btn btn-warning btn-xs" onclick="del('${version.kid}')">删除</button>
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
	if ("${state}" == "SUCCESS") {
		layer.msg("提交成功");
	} else if ("${state}" == "FALSE") {
		layer.msg("提交失败");
	}else if("${state}" == "SAME"){
		layer.msg("重复保存请检查");
	}else if("${state}" == "NULLFILE"){
		layer.msg("apk不能为空");
	}

	$("#cAppCode").val("${cAppCode}");

	$(document).ready(function() {
		$('#dataTable').dataTable({
			"bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示
			"searching" : false, // 关闭Datatables的搜索功能
			"aLengthMenu" : [ 10, 20, 40 ], //更改显示记录数选项
			"lengthChange" : false,//禁用调整显示记录数选项
			"iDisplayLength" : 10, //默认显示的记录数
			"bAutoWidth" : true, //是否自适应宽度
			"bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
			"bPaginate" : true, //是否显示（应用）分页器  
			"bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
			"sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
			"bSort" : true, //是否启动各个字段的排序功能  
			"aaSorting" : [ [ 5, "desc" ] ], //默认的排序方式，第5列，升序排列  
			columnDefs : [ {
				orderable : false,//禁用排序
				targets : [ 0,1,2,3,4,6,7 ] //指定的列
			} ],
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
		if (!$("#check_version").checked) {
			$("#checkAll").prop("checked", false);
		}
		var ckbLength = $("input[type='checkbox'][id='check_version']").length;
		var checkedLengtn = $("input[type='checkbox'][id='check_version']:checked").length;
		if (checkedLengtn == ckbLength) {
			$("#checkAll").prop("checked", true);
		}
	});

	//单个删除
	function del(kid) {
		var kids = new Array();
		if (kid) {
			kids[0] = kid;
		} else {
			return;
		}
		del_com(kids);
	}

	//批量删除
	$("#batchDelete").click(function() {
		var kids = new Array();
		$("input[type='checkbox'][name='check_version']:checked").each(function(i) {
			kids[i] = $(this).val();
		});
		if (kids.length == 0) {
			layer.msg('请先选取要删除的记录', {anim : 6});
			return;
		}
		del_com(kids);
	});

	function del_com(kids) {
		layer.confirm('确认删除？', {
			btn : [ '确认', '取消' ]
		}, function() {
			$.ajax({
				cache : true,
				type : "post",
				url : 'batchDelete',
				async : false,
				data : {
					"kids" : kids
				},
				traditional : true,
				success : function(data) {
					layer.msg('删除成功', {
						anim : 6
					});
					setTimeout(function() { //使用  setTimeout（）方法设定定时1500毫秒
						$("#versionForm").submit();//页面刷新
					}, 1500);
				},
				error : function() {
					layer.msg('删除失败', {
						anim : 6
					});
				}
			});
		}, function() {
			return;
		});
	}
</script>
</body>
</html>