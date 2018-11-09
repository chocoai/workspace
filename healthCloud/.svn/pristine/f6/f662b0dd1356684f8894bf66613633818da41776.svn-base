<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<% String contextPath = request.getContextPath(); %>
<script src="<%=contextPath%>/res/js/multiselect.min.js" type="text/javascript"></script>
<style>
/*
        树形图背景
*/
#treeModal.ztree {
	max-height: 200px;
	border: 1px solid #617775;
	background: #f0f6e4;
	overflow-y: scroll;
	overflow-x: auto;
}
</style>
</head>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">员工【${emp.realName }】分配会员</h4>
</div>
	
<form class="form-inline">
	<div class="modal-body" id="manBody" >
		<div id="menuModal" class="menuContent" style="display: none; position: absolute; z-index: 999;">
			<ul id="treeModal" class="ztree" style="margin-top: 0; width: 150px;"></ul>
		</div>
		<div class="breadcrumb">
			<div class="form-group">
				<label for="">所属机构</label>&nbsp;
				<input id="orgModelService" type="text" class="form-control" readonly onclick="showModal(); return false;" style="width: 150px;"/> 
				<input type="hidden" id="orgIdModal" name="orgId">
			</div>
			&nbsp;&nbsp;
			<div class="form-group">
				<label for="">姓名：</label>
				<input type="text" class="form-control" style="width: 100px;" id="realName" placeholder="请输入姓名">
			</div>
			&nbsp;&nbsp;
			<div class="form-group">
				<label for="">手机号：</label> 
				<input type="text" class="form-control" style="width: 120px;" id="phoneNo" placeholder="请输入手机号">
			</div>
			&nbsp;&nbsp;
			<input type="hidden" id="empId" value="${emp.empId }">
			<a type="button" class="btn btn-info" onclick="select();">查询</a>
		</div>
		
		<div class="row" style="margin-bottom: 10px;">
			<div class="col-md-5">
			<label class="col-md-2">未分配会员</label>
				<select id="from" class="js-multiselect form-control" multiple="multiple" style="width: 220px; height: 190px;">
					<c:forEach items="${fromList }" var="member">
				       <option value="${member.memberId}">${empty member.realName ? "未填" : member.realName} / ${member.phoneNo }</option> 
				   </c:forEach>
				</select>
			</div>
			
			<div class="col-md-2">
				<button type="button" id="js_right_All_1" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
				<button type="button" id="js_right_Selected_1" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
				<button type="button" id="js_left_Selected_1" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
				<button type="button" id="js_left_All_1" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
				<button type="button" class="btn btn-block" onclick="save();">保存</button>
			</div>
			
			<div class="col-md-5">
			<label class="col-md-2">已分配会员</label>
				<select id="to" class="form-control" multiple="multiple" style="width: 220px; height: 190px;">
					<c:forEach items="${toList }" var="member">
				       <option value="${member.memberId}">${empty member.realName ? "未填" : member.realName } / ${member.phoneNo }</option> 
				   </c:forEach>
				</select>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">

	$(document).ready(function($) {
		$(".js-multiselect").multiselect({
			right: "#to",
			rightAll: "#js_right_All_1",
			rightSelected: "#js_right_Selected_1",
			leftSelected: "#js_left_Selected_1",
			leftAll: "#js_left_All_1"
		});
	});
	
	function select(){
		
		var ids = [];
		$("#to option").each(function() {
			ids.push($(this).val());
        });
		
		var param = {
			empId : $("#empId").val(),
			orgId : $("#orgIdModal").val(),
			realName : $("#realName").val(),
			phoneNo : $("#phoneNo").val(),
			ids : ids
		};
		
		$.ajax({
			cache : true,
			type: "post",
         	url: "queryMan",
         	async : false,
         	data: param,
         	traditional : true,
         	success: function(data){
         		var json = JSON.parse(data);
         		$("#from").html("");
                for (var i = 0; i < json.length; i++) {
                	var name;
                	if(json[i].realName){
                		name = json[i].realName;
                	}else{
                		name = "未填";
                	}
                	$("#from").append("<option value='" + json[i].memberId + "'>" + name + ' / ' + json[i].phoneNo +"</option>");  
                }  
                $("#from").multiselect("destroy").multiselect({  
                    includeSelectAllOption : true,   
                    numberDisplayed : 10
                });
         	},
         	error:function (XMLHttpRequest, textStatus, errorThrown){
            	layer.msg("请求失败!", {icon: 5});
           	}
		});
	}
	
	function save(){
		var ids = [];
		$("#to option").each(function() {
			ids.push($(this).val());
        });
		
		layer.msg('确认保存?', { time: 0, btn: ['确定', '取消'], yes: function(index){
			$.ajax({
				cache : true,
				type: "post",
	         	url: "saveBatch",
	         	async : false,
	         	data: {
	         		"ids":ids,
	         		"empId":"${emp.empId }"
	         	},
	         	traditional : true,
	         	success: function(data){
	         		if(data==1){
	         			layer.msg("保存成功");
	               	}else{
	                 	layer.msg("保存失败");
	                 }
	         		setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
	         			$("#subForm").submit();
	               	},2000);
	         	},
	         	error:function (XMLHttpRequest, textStatus, errorThrown){
	            	layer.msg("请求失败!", {icon: 5});
	           	}
			});
   		}});
	}
	
	//显示机构树
	var zTreeObjModal;
	var settingModal = {
		async : {
			autoParam : [],
			contentType : "application/json",
			dataType : "json",
			enable : true,
			url : "/healthCloud/org/getTreeByOrgId?orgId=${emp.orgId}",
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
			onNodeCreated : onNodeModal,
			onClick : onCkModal
		}
	};
	
	$(document).ready(function() {
		zTreeObjModal = $.fn.zTree.init($("#treeModal"), settingModal);
	});
	
	function showModal() {
		var cityObj = $("#orgModelService");
		var modalOffset = $("#manBody").offset();
		var cityOffset = $("#orgModelService").offset();
		//这里因为是相对于模态框的body部分位移，所以这里在取值时需要减去模态框距离页面左边和上边的数值得到的才是模态框边界到input框的距离
		var left = cityOffset.left - modalOffset.left;
		var top = cityOffset.top - modalOffset.top;
		$("#menuModal").css({
			left : left + "px",
			top : top + cityObj.outerHeight() + "px"
		}).slideDown("fast");
		$("body").bind("mousedown", onBodyModal);
	}
	function hideModal() {
		$("#menuModal").fadeOut("fast");
		$("body").unbind("mousedown", onBodyModal);
	}
	function onCkModal(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeModal"), nodes = zTree.getSelectedNodes(), v = "";
		var orgId;
		nodes.sort(function compare(a, b) {
			return a.id - b.id;
		});
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";
			orgId = nodes[i].id;
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		var cityObj = $("#orgModelService");
		cityObj.attr("value", v);
		$("#orgIdModal").attr("value", orgId);
		hideModal();
	}
	function onNodeModal(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		var nodes = zTree.getNodes();
		zTree.selectNode(nodes[0]);
		zTree.expandNode(nodes[0], true, false, false);
		if ($("#orgModelService").val() == "") {
			onCkModal(e, treeId, treeNode);
		}
	}
	function onBodyModal(event) {
		if (!(event.target.id == "orgModelService" || event.target.id == "menuModal" 
				|| $(event.target).parents("#menuModal").length > 0)) {
			hideModal();
		}
	}

</script>