$(function() {
	$("#pwd").blur(function(){
		var pwd = $("#pwd").val();
		if(pwd=="" || pwd ==null){
			return;
		}else{
			isPwd(pwd);
		}
	})
	$("#pwd1").blur(function(){
		var pwd1 = $("#pwd1").val();
		var reg = /^[0-9a-zA-Z]+$/g;
		if(pwd1=="" || pwd1 ==null){
			return;
		}else if(!reg.test(pwd1)){
			layer.tips("密码只能由英文字母或数字组成","#pwd1")
			$("#pwd1").focus();
		}
//		else if(pwd1.length < 6){	
//			layer.tips("密码不能小于6位数","#pwd1")
//			$("#pwd1").focus();
//		}
	})
	$("#pwd2").blur(function(){
		var pwd1 = $("#pwd1").val();
		var pwd2 = $("#pwd2").val();
		if(pwd2=="" || pwd2 ==null){
			return;
		}else if(pwd1!= pwd2){	
			layer.tips("两次密码不一致","#pwd2")
			$("#pwd2").focus();
		}
	})
	
	
	
	
	$("#submitBtn").click(function(){
		var pwd = $("#pwd").val();
		var pwd1 = $("#pwd1").val();
		var pwd2 = $("#pwd2").val();
		if(validata(pwd,pwd1,pwd2)){
			layer.confirm("确定修改吗？", {
				title: '提示信息',
				btn : [ '确定', '取消' ],
				icon: 3
			// 按钮
			}, function() {
				$.ajax({
					type : 'post',
					url : $("#contextPath").val() + "/user/modifyPwd",
					data : {
						"pwd":MD5(pwd),"pwd1":MD5(pwd1),"pwd2":MD5(pwd2)
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
							layer.msg('网路异常', {
								icon : 2
							});
						} else if (data == "errorPwd") {
							layer.msg('初始密码错误', {
								icon : 2
							});
						} else if (data == "errortwo") {
							layer.msg('两次密码不一致', {
								icon : 2
							});
						} else if (data == "success") {
							layer.msg('成功', {
								icon : 1
							});
							location.href = $("#contextPath").val() + "/open/logout";
						}
					}
				})
			})
		}
	})
})

//验证初始密码
function isPwd(pwd){
	$.ajax({
		type : 'POST',
		url : $("#contextPath").val() + '/user/isPwd',
		data : {
			"pwd" : MD5(pwd)
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
			if (data == "errorPwd") {
				layer.tips("初始密码不正确","#pwd")
				$("#pwd").focus();
				$("#pwd").val("");	
			}
		}
	});
}

function validata(pwd,pwd1,pwd2){
	var reg = /^[0-9a-zA-Z]+$/g;
	var isSumbit = false;
	if(pwd==""|| pwd ==null){
		layer.tips("请输入初始密码","#pwd")
		$("#pwd").focus();
	}else if(pwd1==""|| pwd1 ==null){
		layer.tips("请输入新密码","#pwd1")
		$("#pwd1").focus();
	}else if(!reg.test(pwd1)){
		layer.tips("密码只能由英文字母或数字组成","#pwd1")
		$("#pwd1").focus();
//	}else if(pwd1.length < 6){	
//		layer.tips("密码不能小于6位数","#pwd1")
//		$("#pwd1").focus();
	}else if(pwd2=="" || pwd2 ==null){
		layer.tips("确认新密码","#pwd2")
		$("#pwd2").focus();
	}else if(pwd1!= pwd2){	
		layer.tips("两次密码不一致","#pwd2")
		$("#pwd2").focus();
	}else{
		isSumbit = true;
	}
	return isSumbit;
}
