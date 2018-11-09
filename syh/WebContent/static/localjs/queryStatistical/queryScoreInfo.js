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
			var zNodes = data;
			$.fn.zTree.init($("#projectTree"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("projectTree");
			 var nodes = zTree.getNodes();
			 for (var i = 0; i < nodes.length; i++) { //设置节点展开
                  if(nodes[i].parentId==null ){
                  	zTree.expandNode(nodes[i], true, false, false);
			 		}
	        }
		}
	});
	$("#exportExcel").click(function(){
		var tableHead = $("#tableTitle").text();
		window.location.href="exportExcel?projectCid="+$("#projectCid").val()+"&tableHead="+tableHead;
	})
	//查询刷新功能
	$("#refreshTable").click(function(){
		var queryCondition={
			"projectCid":$("#projectCid").val()
		}
		if(queryCondition.projectCid!=""){
			$.ajax({
				type : 'POST',
				url : $("#contextPath").val() + '/queryScoreInfo/queryProjectScore',
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
					var list = "";
					$("#tableList").html(list);
					if(data.resultType=='0'){//计名次
						$("#scores").addClass("hidden");
						$("#judgeLevel").addClass("hidden");
					}else{
						$("#scores").removeClass("hidden");
						$("#judgeLevel").removeClass("hidden");
					}
					$("#tableTitle").text(data.projectName);
					
					if(data.list!=null&&data.list.length>0){
						$("#noDataPage").addClass("hidden");
						$("#exportExcel").removeClass("hidden");
						
						for (var i = 0; i < data.list.length; i++) {
							var bean = data.list[i];
						 list  += '<tr>'
								+ '<td>'+ isStrNUll(bean.ranking)+ '</td>'
								+ '<td>'+ isStrNUll(bean.unitName)+ '</td>'
								+ '<td>'+ isStrNUll(bean.athleteName)+ '</td>'
								if(data.resultType!='0'){
									list  +=  '<td>'+ bean.scores + '</td>'
								}
						  list += '<td>'+ fomatFloat(bean.intrgral,2) + '</td>'
						  		if(data.resultType!='0'){
						  			list  +=  '<td>'+ isStrNUll(bean.backUp) + '</td>'
						  		}
						  list += '</tr>'
						}
						$("#tableList").html(list);
					}else{
						$("#noDataPage").removeClass("hidden");
						$("#exportExcel").addClass("hidden");
						
						var tfootPage = "";
						$("#tableList").html('<tr></tr>');
						if(data.resultType=='0'){
							tfootPage ='<tr>'
								+'<td colspan="4" align="center">暂无数据</td>'+
								'</tr>'
						}else{
							tfootPage ='<tr>'
								+'<td colspan="6" align="center">暂无数据</td>'+
								'</tr>'
						}
						$("#noDataPage").html(tfootPage);
					}
				}
			});
		}
	})
	
})

//树节点敲击事件
function zTreeOnClick(event, treeId, treeNode){
	var treeObj=$.fn.zTree.getZTreeObj("projectTree"),
    nodes=treeObj.getSelectedNodes(true);
	var isClass = nodes[0].isClass;
	if(isClass=='1'){
		$("#projectCid").val(nodes[0].id);
		$("#projectName").val(nodes[0].nodeValue);
		$("#refreshTable").click();
	}
}