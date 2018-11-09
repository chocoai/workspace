$(function(){
	var submitUrl=$("#contextPath").val() + '/comRecord/doUpdateInfo';
	
	$( "#datepicker" ).datepicker({
    	todayBtn:"linked",
    	endDate:new Date(),
    	keyboardNavigation:!1,
    	forceParse:!1,
    	calendarWeeks:!0,
    	autoclose:!0
    });
	
	$("#cancleBtnTo").click(function(){
		layer.confirm("确定取消么？",{
			btn : [ '确定', '取消' ]// 按钮
		},function(){
			self.location.href = "index?pCid="+$("#projectCid").val();
		})
	})
	
	$("#submitBtn").click(function(){
		layer.confirm("确定提交么？",{
			btn : [ '确定', '取消' ]// 按钮
		},function(){
			var comRecord=new Object;
			comRecord.cid=$("#cid").val();
			comRecord.projectCid=$("#projectCid").val();
			comRecord.recordHolder=$("#recordHolder").val();
			comRecord.recordScore=$("#recordScore").val();
			comRecord.recordCreatetime=$("#datepicker").val();
			comRecord.backup=$("#backUp").val();
			$.ajax({
				type:'post',
				url:submitUrl,
				data:{
					jsonString:JSON.stringify(comRecord)
				},
				dataType:'html',
				timeout:3000,
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
					if (data=="success") {
						self.location.href = "index?pCid="+$("#projectCid").val();
					}else if (data=="failed") {
						layer.msg('系统异常', {
							icon : 1,
							time : 2000
						})
					}
				}
			})
		})
	})
})