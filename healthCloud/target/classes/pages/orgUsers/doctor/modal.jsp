<%@page language="java" pageEncoding="utf-8"%>
<style>
/*
        树形图背景
*/
#treeDemoModal.ztree {
	max-height: 200px;
	border: 1px solid #617775;
	background: #f0f6e4;
	overflow-y: scroll;
	overflow-x: auto;
}
</style>
<div class="modal fade" id="myModal" tablist="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="width: 700px; margin: auto">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">批量导入医师</h4>
			</div>
			<form class="form-horizontal" id="upload" method="post">
				<div class="modal-body" id="modalBody" >
				
					<div id="menuContentModal" class="menuContent" style="display: none; position: absolute; z-index: 999;">
						<ul id="treeDemoModal" class="ztree" style="margin-top: 0; width: 200px;"></ul>
					</div>
					
					<div class="form-group">
						<ol>
							<li>导入数据前请先下载模板，按模板规定的格式填写数据，否则无法导入；</li>
							<li>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件；</li>
							<li>导入前请按示例数据填写相应数据项，导入时请删除示例数据；</li>
						</ol>
					</div>					
					
					<table class="table table-bordered">
			        	<tr>
			        		<th>所属机构：</th>
			          		<td>
								<input id="citySelModal" type="text" class="form-control" readonly onclick="showMenuModal(); return false;" style="width: 200px;"/> 
								<input type="hidden" id="orgModal" name="orgId">
			           		</td>
			           		<th>角色分类：</th>
			           		<td>
								<select id="roleId" name="roleId" class="form-control">
									<c:forEach items="${role_list }" var="role" varStatus="status">
										<option value="${role.roleId }">${role.roleName }</option>
									</c:forEach>
								</select>
			           		</td>
			       		</tr>
			       		
			       		<tr>
			       			<th>上传excel文件：</th>
							<td colspan="3">
								<input type="file" id="file" name="file" style="display: none;" onchange="check()" 
									accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
								<div class="input-group">
									<input type="text" id="filePath" style="width:360px;" class="form-control" readonly="readonly" required="required"> 
									<span class="input-group-btn"> 
										<a class="btn btn-default" onclick="$('input[id=file]').click();">浏览</a>
									</span>
								</div> 
							</td>
						</tr>
						
			       		<tr>
			       			<th>模板文件下载：</th>
			       			<td colspan="3">
								<button type="button" class="btn btn-info" onclick="downloadTpl()" >点此处下载模板文件</button>
			       			</td>
			       		</tr>
			   		</table>
			   		
					<div class="modal-footer" style="text-align: center;">
						<button type="button" id="importBtn" class="btn btn-primary" onclick="importExcel('upload','orgModal')">导入</button> &nbsp;
						<button type="reset" class="btn btn-default">重置</button> &nbsp;
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script>

	$('input[id=file]').change(function() {
		$('#filePath').val($(this).val());
	});
	
	function downloadTpl() {
		window.location.href = "<%=contextPath%>/res/template/数据导入模板.xlsx";
	}

	$(function() {
		$("#myModal").modal({
			show : false,
			backdrop : false
		});
	});
	function check() {
	var aa = document.getElementById("file").value.toLowerCase().split('.');//以“.”分隔上传文件字符串
		if (aa[aa.length - 1] == 'xls' || aa[aa.length - 1] == 'xlsx') {//判断表格格式
			var excelSize = document.getElementById("file").files[0].size;
			if (excelSize > 1024 * 1024 * 5) {
				layer.msg("文件过大,请将大小控制在5M以内");
				$("#file").after($("#file").clone().val(""));
				$("#file").remove();
				return false;
			}
		} else {
			layer.msg('请选择格式为*.xls、*.xlsx');//jpg和jpeg格式是一样的只是系统Windows认jpg，Mac OS认jpeg，
			//二者区别自行百度
			$("#file").after($("#file").clone().val(""));
			$("#file").remove();
			return false;
		}
		return true;
	}
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
		var modalOffset = $("#modalBody").offset();
		var cityOffset = $("#citySelModal").offset();
		//这里因为是相对于模态框的body部分位移，所以这里在取值时需要减去模态框距离页面左边和上边的数值得到的才是模态框边界到input框的距离
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
		var zTree = $.fn.zTree.getZTreeObj("treeDemoModal"), nodes = zTree
				.getSelectedNodes(), v = "";
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
		var cityObj = $("#citySelModal");
		cityObj.attr("value", v);
		$("#orgModal").attr("value", orgId);
		hideMenuModal();
	}
	function onNodeCreatedModal(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		var nodes = zTree.getNodes();
		zTree.selectNode(nodes[0]);
		zTree.expandNode(nodes[0], true, false, false);
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
	
</script>