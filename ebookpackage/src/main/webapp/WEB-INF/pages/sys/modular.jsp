<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模块管理页面</title>
<link href="${ctx}/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
<!--树形菜单-开始-->
<script type="text/javascript" src="${ctx}/js/zTree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree/jquery.ztree.exedit-3.5.js"></script>
<SCRIPT type="text/javascript">
var setting = {
	view: {
		dblClickExpand: false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback: {
		onRightClick: OnRightClick,
		onClick: OnClick
	}
};

var zNodes;

function OnRightClick(event, treeId, treeNode) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zTree.cancelSelectedNode();
		showRMenu("root",treeNode, event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		zTree.selectNode(treeNode);
		showRMenu("node",treeNode, event.clientX, event.clientY);
		
		editModular(treeNode);
	}
}

function OnClick(event, treeId, treeNode) {
	editModular(treeNode);
};

function editModular(treeNode){
	currModular = modularMap[treeNode.id];
	$("#modularName").val(currModular.modularName);
	$("#modularPath").val(currModular.modularPath);
	$("#status").val(currModular.status);
	$("#modularSort").val(currModular.modularSort);
	$(".wd_list_ico").removeClass("enable");
	$(".wd_list_ico").addClass("disable");
	if(currModular.imgcss != ''){
		$("#"+currModular.imgcss).removeClass("disable");
		$("#"+currModular.imgcss).addClass("enable");
		imgcss=currModular.imgcss;
	}
	
}

function showRMenu(type,treeNode, x, y) {
	$("#rMenu ul").show();
	if (type=="root") {
		$("#m_del").hide();
		$("#m_add").text("新增父模块");
		$("#m_add").show();
	} else {
		$("#m_del").show();
		if(treeNode.isParent){
			$("#m_add").text("新增子模块");
			$("#m_add").show();
		}else{
			$("#m_add").hide();
		}
		
	}
	rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

	$("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}
function addTreeNode() {
	hideRMenu();
	var modularName = "新增模块";
	var parentId;
	var isParent;
	if (zTree.getSelectedNodes()[0]) {
		parentId = zTree.getSelectedNodes()[0].id;
		isParent = "1";
	} else {
		parentId = "0";
		isParent = "0";
	}
	
	$.ajax({
		type : "POST",
		url : "${ctx}/sys/modular/addModular",
		data : {
			"modularName" : modularName,
			"parentId" : parentId,
			"isParent" : isParent
		},
		async : false,
		dataType : 'json',
		success : function(modular) {
			if(modular != null){
				modularMap[modular.id] = modular;
				
				var newNode = {id : modular.id, pId : modular.parentId, name : modular.modularName, isParent : "0"==modular.isParent};
				if (zTree.getSelectedNodes()[0]) {
					zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
				} else {
					zTree.addNodes(null, newNode);
				}
				zTree.selectNode(newNode,false);
				editModular(newNode);
			}
		}
	});
}
function removeTreeNode() {
	hideRMenu();
	var nodes = zTree.getSelectedNodes();
	if (nodes && nodes.length>0) {
		var ids = nodes[0].id;
		if (nodes[0].children && nodes[0].children.length > 0) {
			var msg = "要删除的“"+nodes[0].name+"”是父模块，如果删除将连同子模块一起删掉。";
			$.confirm(msg,function(){
				zTree.removeNode(nodes[0]);
			});
			$.each(nodes[0].children,function(i,node){
				ids += ","+node.id;
			});
		} else {
			$.confirm("确定要删除“"+nodes[0].name+"”吗？",function(){
				zTree.removeNode(nodes[0]);
			});
		}
		
		$.ajax({
			type : "POST",
			url : "${ctx}/sys/modular/deleteModular",
			data : {
				"ids" : ids
			},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='success'){
				}
			}
		});
	}
}

var zTree, rMenu,imgcss;
$(document).ready(function(){
	loadTree();
	zTree = $.fn.zTree.getZTreeObj("tree");
	rMenu = $("#rMenu");
	
	$(".disable").bind("click",function(event){
		$(".wd_list_ico").removeClass("enable");
		$(".wd_list_ico").addClass("disable");
		$(this).removeClass("disable");
		$(this).addClass("enable");
		imgcss = $(this).attr("id");
	});
	
	$("#save").click(function(){
		if(currModular == null){
			$.alert("请选择相应的模块！");
			return;
		}
		var modularName = $("#modularName").val();
		if(modularName == ''){
			$.alert("请填写菜单模块名称！");
			return;
		}
		var modularSort = $("#modularSort").val();
		if(!(/\d+/.test(modularSort))){
			$.alert("排序只能为数字");
		}
		$.ajax({
			type : "POST",
			url : "${ctx}/sys/modular/updateModular",
			data : {
				"id" : currModular.id,
				"modularName" : modularName,
				"modularPath" : $("#modularPath").val(),
				"status" : $("#status").val(),
				"modularSort" : modularSort,
				"imgcss" : imgcss
			},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='success'){
					$.alert("保存成功！");
					loadTree();
				}
			}
		});
	});
});

var modularMap,currModular;
function loadTree() {
	$("#tree").html("加载中...");
	$.ajax({
		type : "POST",
		url : "${ctx}/sys/modular/modularTree",
		data : {},
		async : false,
		dataType : 'json',
		success : function(msg) {
			if (msg == null || msg.zNodes == '[]' || msg.zNodes.length < 1) {
				$("#tree").html("没有数据！");
			} else {
				zNodes = msg.zNodes;
				$.fn.zTree.init($("#tree"), setting, zNodes);
				
				modularMap = msg.modularMap;
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#tree").html('查询失败');
		}
	});

}
</SCRIPT>
<style type="text/css">
div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
div#rMenu ul li{
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #DFDFDF;
}
.enable{border: 2px solid #be0106;}
.disable{border: 2px solid #ccc;}
</style>
</head>
<body>
<div class="mg15 txq_main">
	<div class="fl" style="width: 25%;border: 2px solid #dadada;">
		<div class="scroll-pane" style="width: 100%;height:450px;overflow: auto;">
             <ul id="tree" class="ztree" style="height: 97%;"></ul>
		</div>
	</div>
   	<div class="fr" style="width: 73%">
       	<div class="base_title"><strong>菜单信息</strong></div>
         	<table border="0" width="100%">
             <tr>
               <td width="20%" align="right"><em class="red">*</em>模块名称：</td>
               <td width="80%">
               	<input type="text" id="modularName" class="inp focus" style="width:340px;"/>
               	<span class="red"></span>
               </td>
             </tr>
             <tr>
               <td align="right">模块访问路径：</td>
               <td>
               	<input type="text" id="modularPath" class="inp focus" style="width:340px;"/>
               </td>
             </tr>
             <tr>
               <td align="right">排序：</td>
               <td><input type="text" id="modularSort" class="inp focus" style="width:50px;"/></td>
             </tr>
             <tr>
               <td align="right">是否有效：</td>
               <td><select id="status" class="sel" style="width:50px;"><option value="0">是</option><option value="1">否</option></select></td>
             </tr>
             <tr>
               <td align="right" rowspan="2" >模块图标：</td>
               <td style="height: 80px;">
	               <div>
		               <span style="position: relative;margin-top: 5px;cursor: pointer;"><i id="wd_list_ico1" class="wd_list_ico wd_list_ico1 disable"></i></span>
		               <span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico2" class="wd_list_ico wd_list_ico2 disable"></i></span>
		               <span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico3" class="wd_list_ico wd_list_ico3 disable"></i></span>
		               <span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico4" class="wd_list_ico wd_list_ico4 disable"></i></span>
		               <span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico5" class="wd_list_ico wd_list_ico5 disable"></i></span>
		               <span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico6" class="wd_list_ico wd_list_ico6 disable"></i></span>
		               <span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico7" class="wd_list_ico wd_list_ico7 disable"></i></span>
		               <span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico8" class="wd_list_ico wd_list_ico8 disable"></i></span>
		               <span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico9" class="wd_list_ico wd_list_ico9 disable"></i></span>
	               </div>
               </td>
             </tr>
             <tr>
             	<td>
             		<div>
	               		<span style="position: relative;margin-top: 5px;cursor: pointer;"><i id="wd_list_ico10" class="wd_list_ico wd_list_ico10 disable"></i></span>
	               		<span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico11" class="wd_list_ico wd_list_ico11 disable"></i></span>
	               		<span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico12" class="wd_list_ico wd_list_ico12 disable"></i></span>
	               		<span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico13" class="wd_list_ico wd_list_ico13 disable"></i></span>
	               		<span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico14" class="wd_list_ico wd_list_ico14 disable"></i></span>
	               		<span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico15" class="wd_list_ico wd_list_ico15 disable"></i></span>
	               		<span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico16" class="wd_list_ico wd_list_ico16 disable"></i></span>
	               		<span style="position: relative;margin-top: 5px;margin-left:30px;cursor: pointer;"><i id="wd_list_ico17" class="wd_list_ico wd_list_ico17 disable"></i></span>
	               </div>
             	</td>
             </tr>
             <tr>
             	<td></td>
             	<td><input type="button" id="save" class="btn_blue" value="保存" /></td>
             </tr>
		</table>
	</div>
</div>
<div id="rMenu" style="top: 142px; left: 104px; visibility: hidden;">
	<ul>
		<li id="m_add" onclick="addTreeNode();">增加模块</li>
		<li id="m_del" onclick="removeTreeNode();" style="display: list-item;">删除模块</li>
	</ul>
</div>
</body>
</html>