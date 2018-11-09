$(document).ready(function() {
	var node = $("#nodeName").val();
	$("#"+node+"_node").addClass("active");
	$.jgrid.defaults.styleUI = "Bootstrap";
	$("#table_dict").jqGrid({
		caption : "数据字典",
		url : $("#contextPath").val() + "/sysDict/queryPage?itemType="+node,
		 mtype: 'POST', 
		datatype : "json",
		height : 260,
		autowidth : true,
		shrinkToFit : true,
		rowNum : 5,
		rowList : [ 5, 10],
		shrinkToFit:true,//显示滚动条
		autoScroll: true,//显示滚动条
		sortname : 'orderNum', 
		sortorder : "desc",   
		pager : "#pager_list",
		pagerpos:"left",
		multiselect: true,//复选框  
		colNames:["序号","字典文本","字典值","父节点","排序号"],
		colModel:[
		          {name : 'cid',index : 'cid',width : 55,key:true, hidden: true }, 
		          {name : 'itemKey',index : 'invdate',width : 90,editable : true}, 
		          {name : 'itemValue',index : 'name',width : 100,editable : true},
		          {name : 'itemType',index : 'amount',width : 80}, 
		          {name : 'orderNum',index : 'tax',width : 80,editable : true,}
		         ],
        loadComplete: function (xhr) {  //隐藏滚动条
        	 $("#table_dict").closest(".ui-jqgrid-bdiv").css({ 
        		 'overflow-y' : 'hidden','overflow-x':'hidden' });  
        },
        gridComplete: function() {
            var rowIds = $("#table_dict").jqGrid('getDataIDs');
            for(var k=0; k<rowIds.length; k++) {
               var curRowData = jQuery("#table_dict").jqGrid('getRowData', rowIds[k]);
               var curChk = $("#"+rowIds[k]+"").find(":checkbox");
               curChk.attr('name', 'cid');   //给每一个checkbox赋名字
               curChk.attr('value', curRowData['cid']);   //给checkbox赋值
               curChk.attr('title', curRowData['itemKey'] );   //给checkbox赋予额外的属性值
               //curChk.attr('checked', 'true');   //设置所有checkbox被选中
        }
        }
		      
	});
	$("#table_dict").setSelection(4, true);
	var width = $(".jqGrid_wrapper").width();
	$("#table_dict").setGridWidth(width)
   //新增
   $("#saveDict").click(function() { 
	   var item= $("input[name='itemValue']").length;
	   
	   if(item<=0){
		   var newid = 0;
		   var rowData = {
				   cid: "",
				   itemKey: "",
				   itemValue: '',
				   itemType: $("#nodeName").val(),
				   orderNum: '0'
		   };
		   $('#table_dict').jqGrid('addRowData', newid, rowData, 'first');//插入行
		   $('#table_dict').editRow(newid);
	   }else{
		   layer.msg('你有记录没提交'); 
	   }
   });
   //编辑
   $("#editDict").click(function() { 
	   var item= $("input[name='itemValue']").length;
	   if(item <=0){
		   var ids= $("#table_dict").jqGrid('getGridParam', 'selarrrow');
		   if(ids && ids.length == 1){
			   $('#table_dict').editRow(ids[0]);
		   }else{
			   layer.msg('请选择一条记录'); 
		   }
	   }else{
		   layer.msg('你有记录没提交'); 
	   }
   });
   //保存
   $("#saveDictTo").click(function() { 
		var itemKey = $("input[name='itemKey']").val()	
	    var itemValue =$("input[name='itemValue']").val();
		var cids = $("#table_dict").jqGrid('getGridParam', 'selarrrow');
		var node = $("#nodeName").val();
    	if(cids.length==0 || cids.length>1){
			layer.msg("请选择一条记录",{
				icon:2
			})
			return false;
		}else if(itemKey.trim()==0){
			layer.msg("请输入字典文本值",{
				icon:2
			})
			return false;
		}else if(itemValue.trim()==""){
			layer.msg("请输入字典值",{
				icon:2
			})
			return false;
		}else if(itemValue.trim()==node){
			layer.msg("字典值不能和类型值一样",{
				icon:2
			})
			return false;
		}else if(itemValue.trim()=="rootNode"){
			layer.msg("字典值不能为根节点",{
				icon:2
			})
			return false;
		}else{
			var cid ="";
			if(cids[0]&&cids[0]!=0){
				cid = cids[0];
			}
			var info=new Object;
			info.cid = cid;
			info.itemKey=itemKey;
			info.itemType=node;
			info.itemValue=itemValue;
			info.orderNum=$("input[name='orderNum']").val();
			var url=$("#contextPath").val() + "/sysDict/saveToUpdate";
			ajaxSubmit(info,url,node);
		}
	   
   });
   $("#deleteDict").click(function() {
		var url = $("#contextPath").val() + '/sysDict/deleteInfo';
		deleteInfo(url,$("#nodeName").val());

   });
   
});
//切换节点列表
function loadNode(itemValue){
	 $(".col-md-2 ul a").removeClass("active");
	 $("#"+itemValue+"_node").addClass("active");
	 $("#nodeName").val(itemValue);
	 $("#table_dict").clearGridData();
	 $("#table_dict").jqGrid("setGridParam",
            {url:$("#contextPath").val() + "/sysDict/queryPage?itemType="+itemValue,page:1, datatype:"json"
	 }).trigger("reloadGrid");
}
//ajax提交删除操作请求
function deleteInfo(url,node){
	var chk_value = [];
	var index = null;	var chk_value = [];
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
					console.log("处理结果" + data);
					if (data== 'success') {
						layer.msg('删除成功', {
							icon : 1,
							time : 2000
						});
						loadNode(node);
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
//ajax提交修改
function ajaxSubmit(Object, url,urlTo) {
	layer.confirm("确定修改吗？", {
		title: '提示信息',
		btn : [ '确定', '取消' ],
		icon: 3
	// 按钮
	}, function() {
		$.ajax({
			type : 'post',
			url : url,
			data : {
				jsonString : JSON.stringify(Object)
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
					layer.msg('保存成功', {
						icon : 1,
						time : 2000
					});
					loadNode(urlTo);
				}else if(data== 'nodata'){
					layer.msg('没有数据需要处理', {
						icon : 1,
						time : 2000
					});
				}else if(data== 'itemKey'){
					layer.msg('显示值格式不正确', {
						icon : 1,
						time : 2000
					});
				}else if(data== 'itemValue'){
					layer.msg('隐藏值式不正确', {
						icon : 1,
						time : 2000
					});
				}else if(data== 'empty'){
					layer.msg('字典值已存在', {
						icon : 1,
						time : 2000
					})
				}else{
					layer.msg('系统异常', {
						icon : 1,
						time : 2000
					})
				}
			}
		})
	})
}
