$(function(){
	var submitUrl=$("#contextPath").val() + '/comRecord/doAddInfo';
	
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
	
	$("#projectCid").change(function(){
		var cid2=$(this).val();
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/comRecord/getProjectInfo',
			data:{cid:cid2},
			dataType:'json',
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
				var resultType="";
				if (data.projectManager.resultType==0) {
					resultType="只录取名次"
				}else if (data.projectManager.resultType==1) {
					resultType="时间下行（用时越少，成绩越高）"
				}else if (data.projectManager.resultType==2) {
					resultType="时间上行（用时越长，成绩越高）"
				}else if (data.projectManager.resultType==3) {
					resultType="数值上行（数值越高，成绩越高）"
				}else if (data.projectManager.resultType==4) {
					resultType="数值下行（数值越低，成绩越高）"
				}
				$("#resultUnit").val(resultType+'/'+data.projectManager.resultUnit)
				if(!data.isOnly){//如果所选择项目已存在竞赛项目记录
					layer.confirm('已存在竞赛记录，是否继续添加？', {
						title:'提示信息',
						btn : [ '确定', '取消' ]// 按钮
					},function(){//点击确定执行方法
						submitUrl=$("#contextPath").val() + '/comRecord/refreshInfo';
						layer.closeAll('dialog')
					},function(){//点击取消执行方法
						window.location.reload()
					})
				}else{
					submitUrl=$("#contextPath").val() + '/comRecord/doAddInfo';
				}
			}
		})
	});
	
	$("#submitBtn").click(function(){
		if(validata()){
			var comRecord=new Object;
			comRecord.projectCid=$("#projectCid").val();
			comRecord.recordHolder=$("#recordHolder").val();
			comRecord.recordScore=$("#recordScore").val();
			comRecord.recordCreatetime=$("#datepicker").val();
			comRecord.backup=$("#backUp").val();
			ajaxSubmitCom(comRecord,submitUrl);
		}
	})
	
	function validata(){
		if( $("#projectCid").val()==null ||$("#projectCid").val()==""){
			layer.msg('请选择比赛项目', {
				icon : 2
			});
			$("#projectName").focus();
			return false;
		}else if($("#recordHolder").val()==""){
			layer.tips("请输入最高纪录保持者","#recordHolder")
			$("#recordHolder").focus();
			return false;
		}else if($("#recordScore").val()==""){
			layer.tips("请输入最高纪录成绩","#recordScore")
			$("#recordScore").focus();
			return false;
		}else if($("#datepicker").val()==""){
			layer.tips("请选择时间","#datepicker")
			$("#datepicker").focus();
			return false;
		}else{
			if(!IsDateTime($("#datepicker").val())){
				layer.tips("请输入正确的时间","#datepicker")
				$("#datepicker").focus();
				return false;
			}
			return true;
		}
	}
})
//ajax提交修改
function ajaxSubmitCom(Object, url) {
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
				if (data== 'success') {
					layer.msg('保存成功', {
						icon : 1,
						time : 2000
					});
					self.location.href = "index?pCid="+$("#projectCid").val();
				}else if(data== 'nodata'){
					layer.msg('没有数据需要处理', {
						icon : 1,
						time : 2000
					});
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
