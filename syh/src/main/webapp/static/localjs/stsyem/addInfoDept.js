$(function() {
	$("#submitBtn").click(function(){
		if(vilidata()){
			var info=new Object;
			info.cid = $('#cid').val();
			info.pareCdoe=$('#pareCdoe').val();
			info.deptName=$('#deptName').val();
			info.deptSname=$('#deptSname').val();
			info.deptEname=$('#deptEname').val();
			info.state=$('#state').val();
			info.orderNum=$('#orderNum').val();
			info.backup=$('#backup').val();
			
			var chk_value = [];
			$("#sel_selected_areas option").each(function() {
				chk_value.push($(this).val());
			}); 
			info.userCids = chk_value;
			var url=$("#contextPath").val() + "/sysDept/saveToUpdate";
			ajaxSubmit(info,url);
		}
	})
	
	$("#cancleBtnTo").click(function(){
		layer.confirm("确定取消么？",{
			btn : [ '确定', '取消' ]// 按钮
		},function(){
			self.location.href="index?pCid="+$("#pareCdoe").val();
		})
	})
})
function vilidata(){
	var isSumbit = false;
	if($('#pareCdoe').val().trim()==""){
		layer.tips("请选择上级部门","#pareDeptName")
	}else if($("#deptName").val().trim()==""){
		layer.tips("请输入部门名称","#deptName")
	}else if($('#orderNum').val().trim()!=""&&!isNum($('#orderNum').val())){
		layer.tips("请输入数字类型","#orderNum")
	}else{
		isSumbit = true;
	}
	return isSumbit;
}

//ajax提交修改
function ajaxSubmit(Object, url) {
	layer.confirm("确定提交吗？", {
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
				} else if (data == "noedit") {
					layer.msg('不需要修改', {
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
					self.location.href="index?pCid="+$("#pareCdoe").val();
				}
			}
		})
	})
}
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
$("#pareDeptName").focus(function() {
	$('#deptModal').modal();
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
			zTree = $.fn.zTree.getZTreeObj("tree");
		}
	});
});
//树节点敲击事件
function zTreeOnClick(event, treeId, node){
	$("#pareCdoe").val(node.id);
	$("#pareDeptName").val(node.nodeValue);
	$("#closeDeptModal").click();
}