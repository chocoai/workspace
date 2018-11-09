<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<jsp:include page="/pages/base/base.jsp"></jsp:include>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<!-- tab strat -->
				<ul class="nav nav-tabs">
					<li><a href="list">当天最新健康数据</a></li>
					<li class="active"><a href="amlist">健康预警</a></li>
				</ul>
				<!-- tab end -->

				<div class="col-md-12" style="padding-left: 0px;padding-right: 0px;">
					<ol class="breadcrumb" style="margin-top: 15px;">
						<form id="subForm" class="form-inline" name="form" method="get">
							<div class="form-group">
								<label for="">所属机构</label>&nbsp;
								<div class="input-group">
									<input type="text" class="form-control" id="orgName" placeholder="请选择所属机构" readonly="readonly" value=${org.orgName }> 
									<input type="hidden" name="orgId" id="orgId" value="${org.orgId }"> 
									<span class="input-group-btn">
										<button type="button" class="btn btn-default" id="orgBtn" data-toggle="modal" data-target="#organizationTree"
											href="${pageContext.request.contextPath }/org/showOrgTree?orgId=${org.orgId }"  style="height: 34px;">
											<i class="fa fa-search"></i>
										</button>
									</span>
								</div>
							</div>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="form-group">
								<label for="name">姓名：</label> 
								<input type="text" class="form-control" id="realName" name="realName" value="${realName }" placeholder="请输入姓名">
							</div>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="form-group">
								<label for="idCard">身份证：</label>
								<input type="text" class="form-control" id="idCard" name="idCard" value="${idCard }" placeholder="请输入身份证号码"/>							
							</div>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="submit" class="btn btn-primary" onclick="sub()">查询</button>
						</form>
					</ol>
				</div>

				<div class="col-md-12" style="margin-top: -10px;padding-left: 0px;padding-right: 0px;">
					<table id="dataTable" class="table table-striped table-hover table-bordered" width="100%" style="text-align: center;">
						<thead>
							<tr>
								<th style="width: 10%;text-align: center;">所属机构</th>
								<th style="width: 8%;text-align: center;">姓名</th>
								<th style="width: 6%;text-align: center;">性别</th>
								<th style="width: 13%;text-align: center;">身份证号</th>
								<th style="width: 9%;text-align: center;">手机</th>
								<th style="width: 10%;text-align: center;">预警类型</th>
								<th style="text-align: center;">预警内容</th>
								<th style="width: 13%;text-align: center;">上传时间</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade bs-example-modal-sm" id="organizationTree" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
		<div class="modal-dialog" style="width: 450px;" role="document">
			<div class="modal-content"></div>
		</div>
	</div>

<script type="text/javascript">
	var allColumns = [{
		"data" : "orgName",
		"render" : function(data, type, row, meta) {
			if(row && row.orgName){
				return row.orgName;
			}
			return "";
		}
	},{
		"data" : "realName",
		"render" : function(data, type, row, meta) {
			if(row && row.realName){
				return row.realName;
			}
			return "";
		}
	},{
		"data" : "gender",
		"render" : function(data, type, row, meta) {
			if(row && row.gender){
				return row.gender;
			}
			return "";
		}
	},{
		"data" : "identityCard",
		"render" : function(data, type, row, meta) {
			if(row && row.identityCard){
				return row.identityCard;
			}
			return "";
		}
	},{
		"data" : "phoneNo",
		"render" : function(data, type, row, meta) {
			if(row && row.phoneNo){
				return row.phoneNo;
			}
			return "";
		}
	},{
		"data" : "dictKey",
		"render" : function(data, type, row, meta) {
			if(row && row.dictKey){
				return row.dictKey;
			}
			return "";
		}
	},{
		"data" : "alarmContent",
		"render" : function(data, type, row, meta) {
			if (type === "display") {
                if (data.length > 20) {
                    return '<span title="' + data + '">' + data.substr(0, 19) + '...</span>';
                } else {
                    return '<span title="' + data + '>' + data + '</span>';
                }
            }
            return data;
		}
	},{
		"data" : "createTime",
		"render" : function(data, type, row, meta) {
			if(row && row.createTime){
				return row.createTime;
			}
			return "";
		}
	}];
	  		
	$(document).ready(function() {
		$("#dataTable").dataTable({
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
					"orgId" : $("#orgId").val(),
					"realName" : $("#realName").val(),
					"idCard" : $("#idCard").val()
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
				"targets" : [ 0, 7 ]//指定的列
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
</script>
</body>
</html>