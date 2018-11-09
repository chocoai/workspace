$(function() {
	$( "#birthday").datepicker({
    	todayBtn:"linked",
    	keyboardNavigation:!1,
    	forceParse:!1,
    	calendarWeeks:!0,
    	autoclose:!0
    });
	$("#submitBtn").click(function(){
		if(vilidata()){
			var info=new Object;
			info.userSname=$('#userSname').val();
			info.petName=$('#petName').val();
			info.birthday=$('#birthday').val();
			info.userSex=$(":radio[name='userSex']:checked").val();
			var url=$("#contextPath").val() + "/user/updateByUser";
			ajaxSubmit(info,url);
		}
		
	})
})
function vilidata(){
	if($('#userSname').val().trim()==0){
		layer.msg("请输入用户姓名",{
			icon:2
		})
		return false;
	}else{
		return true;
	}
}
//ajax提交修改
function ajaxSubmit(Object, url) {
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
					self.location.href = "indexUser";
				}
			}
		})
	})
}