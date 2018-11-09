<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<% String contextPath = request.getContextPath(); %>
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
<style>
#treeDemoModal.ztree {
	max-height: 200px;
	border: 1px solid #617775;
	background: #f0f6e4;
	overflow-y: scroll;
	overflow-x: auto;
}
</style>
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:member:list">
						<li><a href="<%=contextPath%>/member/list?status=0" target="_self"><span>会员列表</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:member:disable:list">
						<li class="active"><a href="<%=contextPath%>/member/list?status=1" target="_self"><span>禁用会员列表</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:member:unbind:list">
						<li><a href="<%=contextPath%>/member/unbandList" target="_self"><span>未绑定会员列表</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-left: 0px; padding-right: 0px;">
			<form id="subForm" class="form-inline">
				<ol id="subDiv" class="breadcrumb">
					<div id="menuContentModal" class="menuContent" style="display: none; position: absolute; z-index: 999;">
						<ul id="treeDemoModal" class="ztree" style="margin-top: 0; width: 200px;"></ul>
					</div>
					<input type="hidden" id="status" name="status" value="1">
					<div class="form-group">
						<label for="">所属机构</label>&nbsp;
						<input id="citySelModal" type="text" class="form-control" readonly onclick="showMenuModal(); return false;" style="width: 200px;" value="${org.orgName }"> 
						<input type="hidden" id="orgModal" name="orgId" value="${org.orgId }">
					</div>
					&nbsp;&nbsp;
					<div class="form-group">
						<label for="">IMEI：</label>&nbsp; 
						<input type="text" class="form-control" style="width: 150px;" id="imei" name="imei" value="${imei }" placeholder="请输入IMEI查询">
					</div>
					&nbsp;&nbsp;
					<div class="form-group">
						<label for="">姓名：</label>&nbsp; 
						<input type="text" class="form-control" style="width: 130px;" id="realName" name="realName" value="${realName }" placeholder="请输入姓名">
					</div>
					&nbsp;&nbsp;
					<div class="form-group">
						<label for="">身份证号：</label>&nbsp; 
						<input type="text" class="form-control" style="width: 180px;" id="identity_card" name="identity_card" value="${identity_card }" placeholder="请输入身份证号" >
					</div>
					&nbsp;&nbsp;
					<button type="submit" class="btn btn-primary" >查询</button>
				</ol>
				<div class="table-responsive mar-t10" style="padding-right: 0px;margin-top: -10px;">
					<table id="table" class="table table-bordered table-striped" style="text-align: center;">
						<thead>
							<tr>
								<th>序号</th>
								<th>所属机构</th>
								<th>姓名</th>
								<th>性别</th>
								<th>身份证号</th>
								<th>手机</th>
								<th>会员信息</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="member" items="${member_list}" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td>${member.org==null?"":member.org.orgName }</td>
									<td>${member.realName }</td>
									<td>
										<c:choose>
											<c:when test="${member.gender ==1 }">男</c:when>
											<c:otherwise>女</c:otherwise>
										</c:choose>
									</td>
									<td>${member.identityCard }</td>
									<td>${member.phoneNo }</td>
									<td><a href="memberInformation?memberId=${member.memberId }">会员详情</a></td>
									<td><a class="btn btn-info btn-xs" href="javascript:;" onclick="recover(${member.memberId })">恢复</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
	//显示机构树
	var zTreeObjModal;
	var settingModal = {
		async : {
			autoParam : [],
			contentType : "application/json",
			dataType : "json",
			enable : true,
			url : "/healthCloud/org/getOrgTree",
			type : "post"
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : null
			}
		},
		view : {
			dblClickExpand : true,
			showLine : true,
			selectedMulti : false
		},
		callback : {
			onNodeCreated : onNodeCreatedModal,
			onClick : onClickModal
		}
	};
	
	$(document).ready(function() {
		zTreeObjModal = $.fn.zTree.init($("#treeDemoModal"), settingModal);
	});
	
	function showMenuModal() {
		var cityObj = $("#citySelModal");
		var modalOffset = $("#right").offset();
		var cityOffset = $("#citySelModal").offset();
		var left = cityOffset.left - modalOffset.left;
		var top = cityOffset.top - modalOffset.top;
		$("#menuContentModal").css({
			left : left + "px",
			top : top + cityObj.outerHeight() + "px"
		}).slideDown("fast");
		$("body").bind("mousedown", onBodyDownModal);
	}
	function hideMenuModal() {
		$("#menuContentModal").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDownModal);
	}
	function onClickModal(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemoModal"), nodes = zTree.getSelectedNodes(), v = "", orgId = "";
		nodes.sort(function compare(a, b) {
			return a.id - b.id;
		});
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";
			orgId = nodes[i].id;
		}
		if (v.length > 0){
			v = v.substring(0, v.length - 1);
		}
		var cityObj = $("#citySelModal");
		cityObj.attr("value", v);
		$("#orgModal").attr("value", orgId);
		hideMenuModal();
	}
	function onNodeCreatedModal(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		var nodes = zTree.getNodes();
		nodes = zTree.getNodeByParam("id", $("#orgModal").val() , null);
		zTree.selectNode(nodes, false , false);
		zTree.expandNode(nodes, true, false, false);
		if ($("#citySelModal").val() == "") {
			onClickModal(e, treeId, treeNode);
		}
	}
	function onBodyDownModal(event) {
		if (!(event.target.id == "citySelModal"
				|| event.target.id == "menuContentModal" || $(event.target)
				.parents("#menuContentModal").length > 0)) {
			hideMenuModal();
		}
	}

	$(document).ready(function() {
		$("#table").dataTable({
			"bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示
			"searching": false, // 关闭Datatables的搜索功能
			"aLengthMenu" : [ 10, 20, 40 ], //更改显示记录数选项
			"lengthChange": false,//禁用调整显示记录数选项
			"iDisplayLength" : 10, //默认显示的记录数
			"bAutoWidth" : true, //是否自适应宽度
			"bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
			"bPaginate" : true, //是否显示（应用）分页器  
			"bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
			"sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
			"bSort" : true, //是否启动各个字段的排序功能  
			"aaSorting" : [ [0, "asc" ] ], //默认的排序方式，第2列，升序排列  
			columnDefs:[{
				 orderable:false,//禁用排序
				 targets:[0]   //指定的列
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
	});
	
	function recover(memberId){
    	layer.msg('该操作将使会员账号可用，是否继续?', {time: 0, btn: ['继续', '点错了'], yes: function(index){
    		layer.close(index);
	        $.ajax({
	        	type: "post",
	        	url: "recover",
	        	data: {memberId:memberId},
	        	dataType: "json",
	        	success: function(data){
	        		layer.msg(data);
	        		setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
	        			$("#subForm").submit();
	        		},1000);
	        	},
	        	error:function (XMLHttpRequest, textStatus, errorThrown){
	        		layer.msg("请求失败!", {icon: 5});
	        	}
	       	});
	    }});
	}

</script>
</html>