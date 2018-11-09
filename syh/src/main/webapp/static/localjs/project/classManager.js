var zTree={
	 setting : {
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
	},
	//分类选择树开始回调函数
	loadTree:function(){ 
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/classManager/queryClassToTree',
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
				$.fn.zTree.init($("#classTree"), zTree.setting, zNodes);
				zTree = $.fn.zTree.getZTreeObj("classTree");
				
				var pCid = $("#classCid").val();
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
	 } 
}; 

$(function() {
	//分类选择树开始
	zTree.loadTree();  
	//新增
    $("#save").click(function(){
    	var classCid = $("#classCid").val();
    	if(!classCid){
    		layer.msg('请选择选择一个分类节点');
    		return;
    	}
    	window.location.href=$("#contextPath").val()+"/classManager/updateInfo?classCid="+classCid;
    })
	
	//删除
	$("#deleteClassManager").click(function() {
		var chk_value = [];
		var index = null;
		$('input[name="cid"]:checked').each(function() {
			chk_value.push($(this).val());
		});
		if (chk_value.length == 0 || chk_value.length > 1) {
			layer.msg('请选择一条记录!');
		} else {
			$.ajax({
				type : 'POST',
				url : $("#contextPath").val()+'/classManager/delete',
				data : {
					'cid' : chk_value[0]
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
						icon : 1
					});
				},
				success : function(data) {
					Error.displayError(data);
					if (data.status == 'nodata') {
						layer.msg('请选择一条记录!', {
							icon : 2,
							time : 2000
						});	
					}else if(data.status == "failed") {
							layer.msg('系统异常', {
								icon : 2,
								time : 2000
						});	
					}else if(data.status == "noclass") {
						layer.msg('该分类下含有子分类，请先处理子节点', {
							icon : 2,
							time : 2000
						});	
					}else if(data.status == "noproject") {
						layer.msg('该分类下含有子竞赛项目，请先处理竞赛项目', {
							icon : 2,
							time : 2000
						});	
					}else{
						layer.msg('删除成功', {
							icon : 2,
							time : 2000
						});	
						self.location.href = "index?pCid="+$("#classCid").val();
					}	
				}
			});
		}
	});
	//分页查询
	$("#refreshTable").click(function(){
		var classCid=$('#classCid').val();	
		if(!classCid) return;
		var currentPage = $("#currentPage").val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/classManager/queryPage',
			data : {
				"classCid" : classCid,"currentPage" :currentPage
			},
			dataType : 'json',
			async: false,
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
				var classManagerList="";
				$("#classManagerList").html(classManagerList);
				$("#pageID").html("");
				$("#noDataPage").addClass("hidden");
				
				if(data.pageInfo.pages>0){
					for (var i = 0; i < data.pageInfo.list.length; i++) {
						   var bean = data.pageInfo.list[i];
						    classManagerList+='<tr>\
							<td><input type="checkbox" name="cid" value="'+bean.cid+'"></td>'+
							'<td>'+isStringNUll(bean.classifyName)+'</td>'+
							'<td>'+isStringNUll(bean.alias)+'</td>'+
							'<td>'+isStringNUll(bean.ageStart)+'</td>'+
							'<td>'+isStringNUll(bean.ageEnd)+'</td>'+
							'<td>'+
							'<a role="button"  href="javascript:" onclick="selectDetail('+bean.cid+')" class="btn btn-info btn-xs">详情</a>'+
							'</td>'+
							'</tr>'
					}
					$("#classManagerList").html(classManagerList);
					if(data.pageInfo.pages>1){
						$("#pageID").html(data.pageInfo.pageHtml);
					}
				}else{
					classManagerList='<tr></tr>';
					$("#classManagerList").html(classManagerList);
					$("#noDataPage").removeClass("hidden");
				}
			}
		});
	})
})
	
//详情页弹出框
function selectDetail(cid){
	$('#detailModal').modal();
	$.ajax({
		type : 'POST',
		url : $("#contextPath").val() + '/classManager/searchObjectByCid',
		data : {
			'cid' : cid
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
			$('#athletesNum').text(isStrNUll(data.athletesNum));
			$('#unitTeam').text(isStrNUll(data.unitTeam));
			$('#manNum').text(isStrNUll(data.manNum));
			$('#womanNum').text(isStrNUll(data.womanNum));
			$('#unitTeam').text(isStrNUll(data.unitTeam));
			$('#personTotnum').text(isStrNUll(data.personTotnum));
			$('#personSinglenum').text(isStrNUll(data.personSinglenum));
			$('#personTeamnum').text(isStrNUll(data.personTeamnum));
			$('#unitTotnum').text(isStrNUll(data.unitTotnum));
			$('#unitSinglenum').text(isStrNUll(data.unitSinglenum));
			$('#unitTeamnum').text(isStrNUll(data.unitTeamnum));
		}
	});
}
//树节点敲击事件
function zTreeOnClick(event, treeId, node){
	$("#classCid").val(node.id);
	
	$("#tableName").html(node.nodeValue);
	$('#currentPage').val("1")
	$("#refreshTable").click();
}
