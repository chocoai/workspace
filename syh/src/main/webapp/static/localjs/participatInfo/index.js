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
	//查看运动员信息
	$(document).on("click",".detail",function(){
		$('#myDetailModal').modal();
		var url = $("#contextPath").val() + '/participatInfo/queryDetail';
		var athleteCid = $(this).next().next().val();
		queryDetail(url,athleteCid);
	})
	// 删除记录信息
	$("#deleteBtn").click(function() {
		var url = $("#contextPath").val() + '/participatInfo/deleteInfo';
		deleteParticpatInfo(url);
	})
	//新增
    $("#save").click(function(){
    	var projectCid = $("#projectCid").val();
    	if(!projectCid){
    		layer.msg('请在树节点中选择一个竞赛项目');	
    		return;
    	}
    	self.location.href="updateInfo?projectCid="+projectCid;
    })
    
	//修改信息
	$("#edit").click(function(){
		var chk_value = [];
		var index = null;
		$('input[name="cid"]:checked').each(function() {
			chk_value.push($(this).val());
		});
		if (chk_value.length != 1) {
			layer.msg('请选择一条记录');
		} else{
			self.location.href="updateInfo?cid="+chk_value
		}
	})
	//查询刷新功能
	$("#refreshTable").click(function(){
		var pCid = $("#projectCid").val();
		if(!pCid) return;
		var currentPage = $('#currentPage').val();
		if(!currentPage){
			currentPage=1;
		}
		var queryCondition={
			"currentPage":currentPage,
			"pCid":pCid,
			"param":$("#queryParam").val()
		}
		
		$.ajax({
				type : 'POST',
				url : $("#contextPath").val() + '/participatInfo/queryPage',
				data : queryCondition,
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
					$("#tableName").text(projectName);
					
					if(data.pageInfo.pages>0){
						$("#noDataPage").addClass("hidden");
						var projectName="";
						if(queryCondition.pCid){
							projectName = data.pageInfo.list[0].projectNames;
						}else{
							projectName = "项目分类信息";
						}
						for (var i = 0; i < data.pageInfo.list.length; i++) {
						    var bean = data.pageInfo.list[i];
							list += '<tr>'+
							'<td><input type="checkbox" onClick="click_input()" name="cid" value="'+bean.cid+'"></td>'+ 
							'<td name="athleteName">'+ isStringNUll(bean.projectNames)+'</td>'+ 
							'<td>'+ isStringNUll(bean.isindividualStr)+ '</td>'+ 
							'<td name="athleteName">'+ isStringNUll(bean.participatNames)+ '</td>'+ 
							'<td name="unitName">'+ isStringNUll(bean.unitNames)+ '</td>'+ 
							'<td>'+
							'<input type="hidden" value="'+bean.iscombinationTeam+'"/>'+
							'<a role="button"  href="javascript:" class="btn btn-info detail  btn-xs">明细</a>'+
							'<input type="hidden" value="'+isStringNUll(bean.pid)+'"/>'+
							'<input type="hidden" value="'+isStringNUll(bean.cid)+'"/>'+
							'</td>'+
							'</tr>'
						}
						$("#list").html(list);
						if(data.pageInfo.pages>1){
							$("#pageID").html(data.pageInfo.pageHtml);
						}
					}else{
						$("#list").html('<tr></tr>');
					}
				}
			})
	})
})
//ajax提交删除操作请求
function deleteParticpatInfo(url,node){
	var chk_value = [];
	var index = null;
	var chk_value = [];
	$('input[name="cid"]:checked').each(function() {
		chk_value.push($(this).val());
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
					console.log("处理结果" + data);
					if (data.state== 'success') {
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



function queryDetail(url,athleteCid){
	$.ajax({
		type : 'POST',
		url : url,
		data : {
			"cid" : athleteCid
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
			$("#infoList").html(list);
			if(data.state=="success"){
				for (var i = 0; i < data.list.length; i++) {
					var bean = data.list[i].athleteBaseInfo;
					var sexStr = "";
					if(bean.sex == 0){
						sexStr = "男";
					}else{
						sexStr = "女";
					}
					list += '<tr>'
						+ '<td>'+ (i+1)+ '</td>'
						+ '<td>'+ isStringNUll(bean.athleteName)+ '</td>'
						+ '<td>'+ isStringNUll(bean.sexStr)+ '</td>'
						+ '<td>'+ isStringNUll(bean.idCard)+ '</td>' 
						+ '<td>'+ isStringNUll(bean.birthday)+ '</td>'
						+ '<td>'+ isStringNUll(bean.unitInfo.unitName)+ '</td>'
						+ '</tr>'
				}
			}
			$("#infoList").html(list);
		}
	});
}
//树节点敲击事件
function zTreeOnClick(event, treeId, node){
	var isClass = node.isClass;
	if(isClass=='1'){
		$("#projectCid").val(node.id);
		$('#currentPage').val("1")
		$("#refreshTable").click();
	}
}