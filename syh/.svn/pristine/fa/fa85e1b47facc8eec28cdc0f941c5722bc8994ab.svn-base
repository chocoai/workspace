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
	//分类选择树开始
	$.ajax({
		type : 'POST',
		url : $("#contextPath").val() + '/common/getTree',
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
			Error.displayError(data);
			var zNodes = data;
			$.fn.zTree.init($("#projectTree"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("projectTree");
			var pCid = $("#projectCid").val();
			if(pCid){
		       var node = zTree.getNodeByParam("id",pCid);  
		       zTree.selectNode(node,true);//指定选中ID的节点  
		       zTree.expandNode(node, true, false);//指定选中ID节点展开
			}else{
				var nodes = zTree.getNodes();
				zTree.expandNode(nodes[0], true, false, false);
			}
		}
	});
	//新增
    $("#addInfo").click(function(){
    	var projectCid = $("#projectCid").val();
    	var projectName = $("#projectName").val();
    	self.location.href="addInfo?projectCid="+projectCid+"&projectName="+projectName;
    })
    
	$("#deleteBtn").click(function(){
		var url=$("#contextPath").val() + '/comRecord/deleteInfo';
		deleteInfoCom(url);
	});
	
	$("#projectCid").change(function() {
		$("#queryBtn").click();
	})

	$("#refreshTable").click(function(){
		var comRecord = new Object;
		comRecord.projectCid = $("#projectCid").val();
		var currentPage = $("#currentPage").val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/comRecord/queryPage',
			data : {
				jsonString : JSON.stringify(comRecord),"currentPage" :currentPage
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
				Error.displayError(data);
				var list = "";
				$("#list").html(list);
				$("#pageID").html("");
				$("#noDataPage").removeClass("hidden");
				if(data.pageInfo.pages>0){
					$("#noDataPage").addClass("hidden");
					for (var i = 0; i < data.pageInfo.list.length; i++) {
					   var bean = data.pageInfo.list[i];
						list += '<tr>\
						<td><input onClick="click_input()"  type="checkbox" name="cid" value="'
								+ bean.cid
								+ '"></td>'
								+ '<td>'+ bean.projectManager.projectName+ '</td>'
								+ '<td>'+ bean.recordHolder+ '</td>'
								+ '<td>'+ bean.recordScore+ '</td>'
								+ '<td>'+ bean.recordCreatetime+ '</td>'
								+ '<td>'+ bean.backup+ '</td>'
								+ '</tr>'
					}
					$("#list").html(list);
					if(data.pageInfo.pages>1){
						$("#pageID").html(data.pageInfo.pageHtml);
					}
				}else{
					$("#list").html('<tr></tr>');
				}
			}
		});
	})
})

//树节点敲击事件
function zTreeOnClick(event, treeId, node){
	var isClass = node.isClass;
	if(isClass=='1'){
		$("#projectCid").val(node.id);
		$("#projectName").val(node.nodeValue);
		$('#currentPage').val("1")
		$("#refreshTable").click();
	}
}
//ajax提交删除操作请求
function deleteInfoCom(url){
	var chk_value = [];
	var index = null;
	var chk_value = [];
	$('input[name="cid"]:checked').each(function() {
		if($(this).val()==''){
			 $("#table_dict").jqGrid("delRowData", 0);
		}else{
			chk_value.push($(this).val());
		}
	});

	if (chk_value.length == 0) {
		layer.msg('请选择一条记录');
	} else {
		layer.confirm('确定删除所选记录？', {
			btn : [ '确定', '取消' ]
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
					Error.displayError(data);
					console.log("处理结果" + data);
					if (data== 'success') {
						layer.msg('删除成功', {
							icon : 1,
							time : 2000
						});
						$("#refreshTable").click();
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