<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<table id="dataTable" class="table table-hover table-bordered" style="margin-top: 10px;">
	<thead>
		<tr>
			<th style="width: 20%">机构名称</th>
			<!-- <th style="width: 10%">机构编码</th> -->
			<!-- <th style="width: 12%">上级机构</th> -->
			<th style="width: 20%">归属区域</th>
			<th style="width: 10%">负责人</th>
			<th style="width: 10%">手机号码</th>
			<th style="width: 10%">固话号码</th>
			<th style="width: 10%">传真</th>
			<th style="width: 10%">操作</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${orgList }" var="org">
			<tr>
				<td>${org.orgName }</td>
				<%-- <td>${org.orgCode }</td> --%>
				<%-- <td>${org.parentOrg.orgName }</td> --%>
				<td>${org.area }</td>
				<td>${org.linkman }</td>
				<td>${org.phoneNumberView }</td>
				<td>${org.telView }</td>
				<td>${org.faxView }</td>
				<td>
					<a class="btn btn-primary btn-xs" href="toEdit?orgId=${org.orgId }">编辑</a>
					<button type="button" class="btn btn-warning btn-xs" onclick="toDelete(${org.orgId })">删除</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script type="text/javascript">

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
		"searching" : true, // 此选项用来开启、关闭Datatables的搜索功能,默认值true
		//"serverSide" : true, // 是否开启服务器模式,默认值false 
		//"stateSave" : false, // 保存状态 - 在页面重新加载的时候恢复状态（页码等内容）,默认值false

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
			"targets" : [ 0, 1 ] //指定的列
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
	
	$(".col-sm-6").eq(0).wrapInner( '<a class="btn btn-primary btn-sm" href="toAdd.do?parentOrgId=${parentOrgId}">新增机构</a>');
	
});

</script>