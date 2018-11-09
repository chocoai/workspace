var isUserCode = false;
$(function() {
	$( "#birthday").datepicker({
    	todayBtn:"linked",
    	keyboardNavigation:!1,
    	forceParse:!1,
    	calendarWeeks:!0,
    	autoclose:!0,
    	endDate : new Date() 
    });
	var baiduBsSuggest = $("#roleName").bsSuggest({
		allowNoKeyword : false,
		getDataMethod : "url",
        showHeader:false,
        effectiveFields: ["roleName"],
		idField:'cid',
		keyField:'roleName',
		url :$("#contextPath").val() + "/sysRole/getRole?str=",
		processData : function(json) {
			var i, len=0, data = {
				value : []
			};
			if (!json || !json || json.length === 0) {
				return false
			}
			len = json.length;
			jsonStr = "{'value':[";
			for (i = 0; i < len; i++) {
				data.value.push({
					cid:json[i].cid,
					roleName : json[i].roleName
				})
			}
			return data
		}
	}).on('onSetSelectValue', function (e, keyword, data) {
		$('#roleCid').val(keyword.id)
    });
	$("#userCode").blur(function(){
		var userCode = $("#userCode").val().trim();
		var oldUserCode = $("#oldUserCode").val().trim();
		if(!userCode || (oldUserCode && oldUserCode == userCode)){
			return;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/sysMuserInfo/isUserCode',
			data : {
				"userCode" :userCode
			},
			dataType : 'html',
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
				if (data == "true") {
					layer.tips("该账号已存在","#userCode")
					isUserCode =false;
				}else{
					isUserCode =true;
				}
			}
		});
	})
	
	//修改用户账号
	$("#isDisabled").click(function(){
		var idDisabled = $("#userCode").attr('disabled');
		if(idDisabled == 'disabled'){
			$("#isDisabled").text("保存");
			$("#userCode").removeAttr("disabled");
		}else{
			$("#userCode").blur();
			if(isUserCode){
				var info=new Object;
				var cid =  $("#userCid").val();
				info.cid = cid;
				info.userCode = $("#userCode").val();
				var url=$("#contextPath").val() + "/sysMuserInfo/doUser";
				ajaxUpdate(info,url,cid)
			}
		}
		
	});
	$("#submitBtn").click(function(){
		if(vilidata()){
			var info=new Object;
			var user=new Object;
			var role=new Object;
			info.cid = $('#cid').val();
			info.userSname=$('#userSname').val();
			info.petName=$('#petName').val();
			info.birthday=$('#birthday').val();
			info.userSex=$(":radio[name='userSex']:checked").val();
			info.orderNum=$('#orderNum').val();
			info.backup=$('#backup').val();
			
			role.cid=$('#roleCid').val();
			role.roleName=$('#roleName').val();
			info.sysRole=role;
			
			user.userCode=$('#userCode').val();
			user.password=$('#password').val();
			info.sysUser=user;
			var url=$("#contextPath").val() + "/sysMuserInfo/saveToUpdate";
			ajaxSubmit(info,url);
		}
		
	})
	
	$("#cancleBtn").click(function(){
		layer.confirm("确定取消新增信息么？",{
			btn : [ '确定', '取消' ]// 按钮
		},function(){
			self.location.href="index"
		})
	})
})
function vilidata(){
	var isSumbit = false;
	var idDisabled = $("#userCode").attr('disabled');
	if($('#userSname').val().trim()==0){
		layer.tips("请输入用户姓名","#userSname")
	}else if($("#userCode").val().trim()==""){
		layer.tips("请输入用户账号","#userCode")
	}else if(idDisabled != 'disabled'&&!isUserCode){
		layer.tips("该账号已存在","#userCode")
	}else if($("#oldPassword").val().trim()=="" && $("#password").val().trim()==""){
		layer.tips("请设置密码","#password")
	}else if($('#roleCid').val().trim()==""){
		layer.tips("请选择角色","#roleName")
	}else if($('#orderNum').val().trim()!=""&&!isNum($('#orderNum').val())){
		layer.tips("请输入数字类型","#orderNum")
	}else{
		isSumbit = true;
	}
	return isSumbit;
}
//ajax提交修改
function ajaxUpdate(Object, url,cid) {
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
				} else if (data == "noedit") {
					layer.msg('不需要修改', {
						icon : 2
					});
					$("#isDisabled").text("修改账号");
					$("#userCode").attr("disabled","disabled");
				} else if (data == "failed") {
					layer.msg('失败', {
						icon : 2
					});
				} else if (data == "success") {
					layer.msg('成功', {
						icon : 1
					});
					self.location.href = "updateInfo?cid="+cid;
				}
			}
		})
	})
}