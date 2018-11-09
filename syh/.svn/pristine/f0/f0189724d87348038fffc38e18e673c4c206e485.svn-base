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
	//加载权限资源树
	$.ajax({
		type : 'POST',
		url : $("#contextPath").val() + '/sysRes/queryToTree',
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
			$.fn.zTree.init($("#resTree"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("resTree");
			var Cid = $("#pareId").val();
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
	
	//新增功能
	$("#save").click(function(){
		var chk_value = $("#pareId").val();
		if (chk_value=='') {
			layer.msg('请选择一个节点');
		} else{
			var resName = $("#resName").val();
			self.location.href="updateInfo?cid="+chk_value+"&resName="+resName
		}
	})
	// 删除记录信息
	$("#deleteBtn").click(function() {
		var url = $("#contextPath").val() + '/sysRes/deleteInfo';
		deleteResInfo(url);
	})
	
	
	$("#refreshTable").click(function(){
		var pid = $("#pareId").val();
		var currentPage = $("#currentPage").val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/sysRes/queryPage',
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
						list += '<tr>\
								<td><input type="checkbox" name="cid" value="'+ bean.cid+ '"></td>'
							 + '<td>'+ isStringNUll(bean.resName)+ '</td>'
							 + '<td>'+ isStringNUll(bean.resUrl)+ '</td>'
							 + '<td>'+ isStringNUll(bean.backup)+ '</td>' 
								 list +=  '</tr>'
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
		$("#pareId").val(node.id);
		$("#pareName").html(node.nodeValue);
		$("#resName").val(node.nodeValue);
		$("#refreshTable").click();
	}

//ajax提交删除操作请求
function deleteResInfo(url){
	var chk_value = [];
	var index = null;
	$('input[name="cid"]:checked').each(function() {
		chk_value.push($(this).val());
	});
	if (chk_value.length == 0) {
		layer.msg('请至少选择一条记录');
	} else {
		layer.confirm('确定删除所选记录？', {
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
					cids : chk_value
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
						self.location.href="index?pCid="+$("#pareId").val();
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