$(function() {
	$("#submitBtn").click(function(){
		if(vilidata()){
			var info=new Object;
			info.cid = $('#cid').val();
			info.pareId=$('#pareId').val();
			info.resName=$('#resName').val();
			info.resUrl=$("#resUrl").val();
			info.state=$('#state').val();
			info.orderNum=$('#orderNum').val();
			info.backup=$('#backup').val();
			
			var url=$("#contextPath").val() + "/sysRes/saveToUpdate";
			ajaxSubmitRes(info,url);
		}
		
	});
	$("#cancleBtnTo").click(function(){
		layer.confirm("确定取消么？",{
			btn : [ '确定', '取消' ]// 按钮
		},function(){
			self.location.href="index?pCid="+$("#pareId").val();
		})
	})
})

function vilidata(){
	var isSumbit = false;
	if($('#pareId').val().trim()==0){
		layer.tips("请选择上级资源","#pareName")
	}else if($('#resName').val().trim()==0){
		layer.tips("请输入权限名称","#resName")
	}else if($('#orderNum').val().trim()!=""&&!isNum($('#orderNum').val())){
		layer.tips("请输入数字类型","#orderNum")
	}else{
		isSumbit =  true;
	}
	return isSumbit;
}

/**
 * 项目树结构开始
 */
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
//模态框弹出
$("#pareName").focus(function() {
	$('#resModal').modal();
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
			zTree = $.fn.zTree.getZTreeObj("tree");
		}
	});
});

//树节点敲击事件
function zTreeOnClick(event, treeId, node){
	$("#pareId").val(node.id);
	$("#pareName").val(node.nodeValue);
	$("#closeResModal").click();
}

//ajax提交修改
function ajaxSubmitRes(Object, url) {
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
					self.location.href="index?pCid="+$("#pareId").val();
				}
			}
		})
	})
}