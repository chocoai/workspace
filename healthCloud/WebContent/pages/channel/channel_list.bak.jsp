<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<table id="myDataTable" class="table table-hover table-bordered" style="margin-top: 10px;">
	<thead>
		<tr>
			<th>栏目名称</th>
			<th>栏目描述</th>
			<th>栏目URL</th>
			<th>栏目打开方式</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${channelList }" var="channel">
			<tr>
				<td>${channel.channelName }</td>
				<td>${channel.channelDesc }</td>
				<td>${channel.channelUrl }</td>
				<td>${channel.openType }</td>
				
				<td>
					<button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#updateChannel" href="toEdit?channelId=${channel.channelId }">编辑</button>
					<button type="button" class="btn btn-warning btn-xs" onclick="showAlert(${channel.channelId })">删除</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script type="text/javascript">

$(document).ready(function() {
	
	$('#myDataTable').dataTable({
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
		"aaSorting" : [ [ 1, "asc" ] ], //默认的排序方式，第2列，升序排列  
		columnDefs:[{
			 orderable:false,//禁用排序
			 targets:[6]   //指定的列
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
			'<a id="addOrg" class="btn btn-primary btn-sm" data-toggle="modal" href="toAdd.do?parentId=${parentId}" data-target="#addChannel">新增栏目</a>');
});

</script>