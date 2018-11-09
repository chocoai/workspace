$(function() {
	var zTree;
	var setting = {
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
		check : {
            enable : true,
        },
		view: {
			selectedMulti: false
		},
	};
	// 加载权限树
	$("#roleResBut").click(function() {
		$('#roleResModal').modal();
		var roleCid =$("#roleResBut").val();
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/sysRes/queryToCheckedTree?roleCid='+roleCid,
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
				$.fn.zTree.init($("#roleResTree"), setting, zNodes);
				zTree = $.fn.zTree.getZTreeObj("tree");
			}
		});
	})
	
	//关闭模态框
	$("#submitRoleResModal").click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("roleResTree");
		var roleCid =$("#roleResBut").val();
		
		var resCids = new Array();
		var nodes= treeObj.getCheckedNodes(true)
		for (var i = 0; i < nodes.length; i++) {
		resCids.push(nodes[i].id);
	 	};
		 layer.confirm("确定提交吗？", {
				title: '提示信息',
				btn : [ '确定', '取消' ],
				icon: 3
			// 按钮
			}, function() {
				$.ajax({
					type : 'post',
					url : $("#contextPath").val() + "/sysRole/roleTask",
					traditional:true,  //必须加！！！！！！需要这个值为true进行阻止深度序列化。
					data : {
						"roleCid":roleCid,"resCids": resCids
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
						if (data == "nodata") {
							layer.msg('没有数据要处理', {
								icon : 2
							});
						} else if (data == "failed") {
							layer.msg('失败', {
								icon : 2
							});
						} else if (data == "success") {
							layer.msg('成功', {
								icon : 1
							});
						$("#closeRoleResModal").click();
						}
					}
				})
			})
	})
	
	// 删除记录信息
	$("#deleteBtn").click(function() {
		var url = $("#contextPath").val() + '/sysRole/deleteInfo';
		deleteRoleInfo(url);
	})
	$("#refreshTable").click(function(){
		var findContent = $("#findContent").val();
		var currentPage = $("#currentPage").val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/sysRole/queryPage',
			data : {
				"findContent" : findContent,"currentPage" :currentPage
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
							<td><input type="checkbox" name="cid" value="'
								+ bean.cid
								+ '"></td>'
								+ '<td>'
								+ isStringNUll(bean.roleName)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.stateStr)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.backup)
								+ '</td>'
								+ '<td>'
								if(bean.state== 0){
									list +=  '<button id="updateByCidSates" type="button" class="btn btn-danger btn-xs" onclick="updateByCidSates('+bean.cid+',-1)" style="margin-right: 5px;">禁用</button >' 
								}else{
									list +=  '<button id="updateByCidSates" type="button" class="btn btn-warning btn-xs" onclick="updateByCidSates('+bean.cid+',0)" style="margin-right: 5px;">激活</button >' 
								}
									list += '<button onclick="roleRes('+bean.cid+')" type="button" class="btn btn-info btn-xs">授权</button >'
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
    
	function roleRes(roleCid){
		$("#roleResBut").val(roleCid);
		$("#roleResBut").click();
	}

	// 禁用激活功能
	function updateByCidSates(id,states){
		$.ajax({
			type : 'post',
			url :$("#contextPath").val() + '/sysRole/updateByCidSates',
			data : {
				"id" : id,"states" :states
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
				if (data == "nodata") {
					layer.msg('没有数据要处理', {
						icon : 2
					});
				} else if (data == "failed") {
					layer.msg('失败', {
						icon : 2
					});
				} else if (data == "success") {
					layer.msg('成功', {
						icon : 1
					});
					$("#refreshTable").click();
				}
			}
		})
	}
	//ajax提交删除操作请求
	function deleteRoleInfo(url){
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
							$("#queryBtn").click();
						} else if (data== "failed") {
							layer.msg('删除失败', {
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