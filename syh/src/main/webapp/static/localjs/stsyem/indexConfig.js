$(function() {
	
	$("#submitBtn").click(function(){
			var info=new Object;
			info.cid = $('#cid').val();
			info.siteName=$('#siteName').val();
			info.sitePame=$('#sitePame').val();
			info.siteUrl=$('#siteUrl').val();
			info.logoUrl=$('#logoUrl').val();
			info.boottomText=$('#boottomText').val();
			var url=$("#contextPath").val() + "/sysConfig/saveToUpdate";
			ajaxSubmit(info,url);
	})
})

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
					self.location.href = "index";
				}
			}
		})
	})
}
