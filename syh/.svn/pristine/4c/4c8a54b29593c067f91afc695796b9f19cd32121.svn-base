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
			var zNodes = data;
			$.fn.zTree.init($("#projectTree"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("projectTree");
			var pCid = $("#pCid").val();
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
    $("#save").click(function(){
    	var pCid = $("#pCid").val();
    	var projectName = $("#projectName").val();
    	window.location.href=$("#contextPath").val()+"/projectManager/updateInfo?pCid="+pCid+"&projectName="+projectName;
    })
    
	$("#deleteProjectManager").click(function() {
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
				url : $("#contextPath").val()+'/projectManager/delete',
				data : {
					'cid' : chk_value.toString()
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
					if (data.status == 'success') {
						layer.msg('删除成功', {
							icon : 1
						});
						$("#refreshTable").click();
					}else if(data.status == 'noparticipat'){
						layer.msg('此项目已关联运动员参赛信息，请先删除所关联的运动员参赛信息', {
							icon : 1
						});
					}else if(data.status == 'failed'){
						layer.msg('系统异常', {
							icon : 1
						});
					}
				}
			});
		}
	});
	$("#refreshTable").click(function(){

		var pCid=$('#pCid').val();
		if(!pCid) return;
		var currentPage = $("#currentPage").val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/projectManager/queryPage',
			data : {
				"pCid" : pCid,"currentPage" :currentPage
			},
			async: false,
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
				var projectManagerList="";
				$("#projectManagerList").html(projectManagerList);
				$("#pageID").html("");
				$("#noDataPage").addClass("hidden");
				
				if(data.pageInfo.pages>0){
					for (var i = 0; i < data.pageInfo.list.length; i++) {
						var bean = data.pageInfo.list[i];
						projectManagerList+='<tr>\
							<td><input type="checkbox" name="cid" value="'+bean.cid+'"></td>'+
							'<td>'+isStringNUll(bean.projectName)+'</td>'+
							'<td>'+isStringNUll(bean.gameType)+'</td>'+
							'<td>'+isStringNUll(bean.scoreRecord.ruleName)+'</td>'+
							'<td>'+isStringNUll(bean.resultUnit)+'</td>'+
					/*		'<td>'+isStringNUll(bean.finalistsNum)+'</td>'+
							'<td>'+isStringNUll(bean.finalAdmission)+'</td>'+*/
							'<td>'+isStringNUll(bean.mastersLevel)+'</td>'+
							'<td>'+isStringNUll(bean.firstLevel)+'</td>'+
							'<td>'+isStringNUll(bean.secondLevel)+'</td>'+
							'<td>'+isStringNUll(bean.threeLevel)+'</td>'+
							'<td>'+isStringNUll(bean.sex)+'</td>'+
							'</tr>'
					}
					$("#projectManagerList").html(projectManagerList);
					if(data.pageInfo.pages>1){
						$("#pageID").html(data.pageInfo.pageHtml);
					}
					
				}else{
					projectManagerList='<tr></tr>';
					$("#projectManagerList").html(projectManagerList);
					$("#noDataPage").removeClass("hidden");
				}
			}
		});
	});
})

//树节点敲击事件
function zTreeOnClick(event, treeId, node){
	var isClass = node.isTeam;	
	if(isClass=='0'){
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/classManager/queryIsNode',
			data:{
				"classCidNode" :node.id
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
				if(data.states == "success"){
					$("#pCid").val(node.id);
					$("#projectName").val(node.nodeValue);
					
					$("#tableName").html(data.className);
					$('#currentPage').val("1")
					$("#refreshTable").click();
				}
			}
		});
	}
}