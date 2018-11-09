<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<table id="dataTable" class="table table-striped table-hover table-bordered" style="table-layout:fixed;margin-top: 10px;">
	<thead>
		<tr>
			<th style="width:7%;"><input type="checkbox" id="checkAll"> 选择</th>
			<th>内容标题</th>
			<th style="width:12%;">作者</th>
			<th style="width:16%;">发布时间</th>
			<th style="width:15%;">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${contentList }" var="content">
			<tr>
				<td style="text-align: center;">
					<input type="checkbox" id="check_content" name="check_content" value="${content.contentId}">
				</td>
				<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;" title="${content.title}">
					<a href="javascript:viewDetail(${content.contentId });">${content.title}</a>
				</td>
				<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;" title="${content.author}">${content.author }</td>
				<td>${content.releaseTime }</td>
				<td>
					<button type="button" class="btn btn-primary btn-xs" onclick="toEdit(${content.contentId })">编辑</button>
					<button type="button" class="btn btn-warning btn-xs" onclick="javascript:toArchive(${content.contentId });">归档</button>
					<button type="button" class="btn btn-danger btn-xs" onclick="javascript:toDelete(${content.contentId });">删除</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script type="text/javascript">

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
	if (!$("#check_content").checked) {
			$("#checkAll").prop("checked",false);
	}
	var ckbLength = $("input[type='checkbox'][id='check_content']").length;
	var checkedLengtn = $("input[type='checkbox'][id='check_content']:checked").length;
	if (checkedLengtn == ckbLength) {
		$("#checkAll").prop("checked",true);
	}
});

function toEdit(contentId){
	window.location.href = "toEdit?contentId=" + contentId;
}

function toArchive(contentId){
	//询问框
	layer.confirm('您确认要归档此内容吗？', {
	  btn: ['确认','取消'] //按钮
	}, function(){
		window.location.href = "archive?contentId=" + contentId;
	}, function(){
	  return;
	});
}

function toDelete(contentId){
	//询问框
	layer.confirm('您确认要删除此内容吗？', {
	  btn: ['确认','取消'] //按钮
	}, function(){
		window.location.href = "delete?contentId=" + contentId;
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
		"ordering" : true, // 是否允许Datatables开启排序,默认值true
		"paging" : true, // 是否允许表格分页，默认true
		"processing" : true, // 是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个)默认值false
		"scrollX" : false, // 是否允许水平滚动，默认值false
		//"scrollY": "200px", // 控制表格的垂直滚动。 Vertical scrolling 强制DataTable为指定的高度，并且会允许任何超出当前视口的数据进行滚动
		"scrollCollapse" : false, //当显示更少的记录时，是否允许表格减少高度, 默认值false
		"searching" : true, // 此选项用来开启、关闭Datatables的搜索功能,默认值true
		"stateSave" : false, // 保存状态 - 在页面重新加载的时候恢复状态（页码等内容）,默认值false

		// DataTables - Data

		// DataTables - Callbacks

		// DataTables - Options
		"destroy" : false, //销毁已经存在的Datatables实例并替换新的选项默认值false
		"displayStart" : 0, //初始化显示的时候从第几条数据开始显示(一开始显示第几页)
		"lengthMenu" : [ 10, 20, 40 ], // 定义在每页显示记录数的select中显示的选项
		"orderClasses" : true, //高亮显示表格中排序的列,默认值： true
		"orderMulti" : true, // 多列排序控制,默认值： true,用户按住shift点击表头，多列排序
		"order" : [ [ 3, "desc" ] ], //表格在初始化的时候的排序，第2列，升序排列  
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
			"targets" : [ 0,2,4 ] //指定的列
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
	
	$(".col-sm-6").eq(0).wrapInner(
			'<button id="addContent" class="btn btn-primary btn-sm">发布内容</button>&nbsp;&nbsp;&nbsp;'+
			'<button class="btn btn-warning btn-sm" id="batchArchive">批量归档</button>&nbsp;&nbsp;&nbsp;'+
			'<button class="btn btn-danger btn-sm" id="batchDelete">批量删除</button>&nbsp;&nbsp;&nbsp;'+
			'<button class="btn btn-info btn-sm" id="getArchived">已归档内容</button>');
	
	
	$("#addContent").click(function(){
		window.location.href = "toAdd?channelId=${channelId}";
	});
	
	
	$("#batchArchive").click(function(){
		var contentIds = new Array()
		$("input[type='checkbox'][name='check_content']:checked").each(function(i) {
			contentIds[i] = $(this).val();
		});
		if(contentIds.length == 0){
			layer.msg('请先选取要归档的内容', { anim: 6 });
			return;
		}
		
		layer.confirm('您确认要归档这些内容吗？', { btn: ['确认','取消'] }, 
			function(){
				$.ajax({
					cache : true,
					type : "post",
					url : 'batchArchive',
					async : false,
					data : {
						"contentIds" : contentIds
					},
					traditional : true,
					success : function(data) {
						layer.msg('归档成功', { anim: 6 });
						// window.location.reload();
						reloadTable();
					},
					error : function() {
						layer.msg('归档失败', { anim: 6 });
					}
				})
				
			}, 
			function(){
				return;
			}
		);
	});
	
	$("#getArchived").click(function(){
		getArchived();
	});

	$("#batchDelete").click(function(){
		var contentIds = new Array()
		$("input[type='checkbox'][name='check_content']:checked").each(function(i) {
			contentIds[i] = $(this).val();
		});
		if(contentIds.length == 0){
			layer.msg('请先选取要删除的内容', { anim: 6 });
			return;
		}
		
		layer.confirm('您确认要删除这些内容吗？', { btn: ['确认','取消'] }, 
			function(){
				$.ajax({
					cache : true,
					type : "post",
					url : 'batchDelete',
					async : false,
					data : {
						"contentIds" : contentIds
					},
					traditional : true,
					success : function(data) {
						layer.msg('删除成功', { anim: 6 });
						// window.location.reload();
						reloadTable();
					},
					error : function() {
						layer.msg('删除失败', { anim: 6 });
					}
				})
				
			}, 
			function(){
				return;
			}
		);
	});
	
});

</script>