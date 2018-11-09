$(function() {
	var zTree;
	var setting = {
		check : {
			enable : false
		},
		callback: {
			onClick: zTreeOnClick
		},
		data : {
			simpleData : {
				enable : true, // 设置是否使用简单数据模式(Array)
				idKey : "id", // 设置节点唯一标识属性名称
				pIdKey : "parentId" // 设置父节点唯一标识属性名称
			},
			key : {
				name : "nodeValue",// zTree 节点数据保存节点名称的属性名称
			}
		},
		edit : {
			enable : false
		},

		view : {
			showIcon : false
		}
	};
	//加载部门资源树
	$.ajax({
		type : 'POST',
		url : $("#contextPath").val() + '/sysDept/queryToTree',
		dataType : 'json',
		timeout : 3000,
		beforeSend : function() {
			index = layer.load(0, {
				shade : false
			}); // 0代表加载的风格，支持0-2
		},
		complete : function() {
			layer.close(index);
		},
		error : function() {
			layer.msg('系统故障', {
				icon : 2
			});
		},
		success : function(data) {
			var zNodes = data;
			$.fn.zTree.init($("#deptTree"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("deptTree");
			var Cid = $("#pareCdoe").val();
			if(Cid){
		       var node = zTree.getNodeByParam("id",Cid);  
		       zTree.selectNode(node,true);//指定选中ID的节点  
		       zTree.expandNode(node, true, false);//指定选中ID节点展开
			}else{
				var nodes = zTree.getNodes();
				zTree.expandNode(nodes[0], true, false, false);
			}
		}
	});
	// 删除记录信息
	$("#deleteBtn").click(function() {
		var url = $("#contextPath").val() + '/sysDept/deleteInfo';
		deleteDeptInfo(url);
	})
	//新增功能
	$("#save").click(function(){
		var chk_value = $("#pareCdoe").val();
		if (chk_value=='') {
			layer.msg('请选择一个部门节点');
		} else{
			var deptName = $("#deptName").val();
			self.location.href="updateInfo?cid="+chk_value+"&deptName="+deptName
		}
	})
	//编辑功能
	$("#edit").click(function(){
		var chk_value = $("#pareCdoe").val();
		if (chk_value=='') {
			layer.msg('请选择一个部门节点');
		}else if(chk_value==0){
			layer.msg('根节点不可编辑');
		} else{
			self.location.href="updateInfo?cid="+chk_value
		}
	})

	$("#refreshTable").click(function(){
		var pid = $("#pareCdoe").val();
		var currentPage = $("#currentPage").val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/sysDept/queryPage',
			data : {
				"pid" :pid,"currentPage" :currentPage
			},
			dataType : 'json',
			timeout : 3000,
			beforeSend : function() {
				index = layer.load(0, {
					shade : false
				}); // 0代表加载的风格，支持0-2
			},
			complete : function() {
				layer.close(index);
			},
			error : function() {
				layer.msg('系统故障', {
					icon : 2
				});
			},
			success : function(data) {
				var list = "";
				$("#list").html(list);
				$("#pageID").html("");
				$("#noDataPage").addClass("hidden");
				if(data.pageInfo.pages>0){
					for (var i = 0; i < data.pageInfo.list.length; i++) {
					   var bean = data.pageInfo.list[i];
						list += '<tr>\<td>'
								+ isStringNUll(bean.userSname)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.sysUser.userCode)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.sysRole.roleName)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.stateStr)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.sysRole.backup)
								+ '</td>' + '</tr>'
					}
					$("#list").html(list);
					if(data.pageInfo.pages>1){
						$("#pageID").html(data.pageInfo.pageHtml);
					}
				}else{
					$("#list").html('<tr></tr>');
					$("#noDataPage").removeClass("hidden");
				}
			}
		});
	})
})

//树节点敲击事件
function zTreeOnClick(event, treeId, node){
	$("#pareCdoe").val(node.id);
	$("#deptPareName").text(node.nodeValue);
	$("#deptName").val(node.nodeValue);
	$("#refreshTable").click();
}
//ajax提交删除操作请求
function deleteDeptInfo(url){
	var pareCdoe = $("#pareCdoe").val();
	if (!pareCdoe) {
		layer.msg('请选择要删除的部门节点');
	} else {
		layer.confirm('删除部门将解除部门用户的关系，是否确认删除？？', {
			title: '提示信息',
			btn : [ '确定', '取消' ],
			icon: 3
		// 按钮
		}, function() {
			$.ajax({
				type : 'POST',
				url :url,
				traditional : true,
				data : {
					cid : pareCdoe
				},
				dataType : 'html',
				timeout : 3000,
				beforeSend : function() {
					index = layer.load(0, {
						shade : false
					}); // 0代表加载的风格，支持0-2
				},
				complete : function() {
					layer.close(index);
				},
				error : function() {
					layer.msg('系统故障', {
						icon : 2
					});
				},
				success : function(data) {
					console.log("处理结果" + data);
					if (data== 'success') {
						layer.msg('删除成功', {
							icon : 1,
							time : 1000
						});
						self.location.href="index";
					} else if (data== "failed") {
						layer.msg('删除失败', {
							icon : 2,
							time : 1000
						});
					} else if (data== "nodel") {
						layer.msg('含有子节点，请先处理子节点数据', {
							icon : 2,
							time : 1000
						});
					}else if(data=="nodata"){
						layer.msg('没有数据要处理', {
							icon : 2,
							time : 1000
						});
					}
				}
			});
		}, function() {
			layer.msg('已取消', {
				time : 1000
			// 1s后自动关闭
			});
		});
	}
}