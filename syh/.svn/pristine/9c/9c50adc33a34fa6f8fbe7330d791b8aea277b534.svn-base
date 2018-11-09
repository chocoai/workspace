$(document).ready(function() {
	//加载成绩单位
//	$("#resultUnit").load(
//			$("#contextPath").val() + '/sysDict/queryType',{
//				'itemType': 'achievementUnit',
//				"number":$("#resultUnitclick").val(),
//				"type":"1"
//	})
	var param = {
			'itemType': 'achievementUnit',
			"number":$("#resultUnitclick").val(),
			"type":"1"
			}
	loaddict(param,'resultUnit')
	//加载计分记牌方式
	$("#scoreCard").load(
			$("#contextPath").val() + '/scoreRecord/dropDown',{
				"number":$("#scoreRecordclick").val(),
				"type":"0"
	})
	//加载健将级别
	loadresult();
	
	//切换成绩类型
	$('#resultType').change(function(){
		loadresult()
	});
	//取消功能
	$("#cancleBtnTo").click(function(){  //取消按钮
		layer.confirm("确定取消吗？",{
			title:'提示信息',
			btn : [ '确定', '取消' ], // 按钮
			icon: 3
		},function(){
			self.location.href = "index?pCid="+$("#pCid").val()+"&pName="+$("#classNameNode").val();
		})
	})
	//表单验证
	$("#formId").validate({
		onsubmit:true,// 是否在提交是验证
		rules : {
			projectName:{
				required:true,
				isName:true,
				isProjectExistenceName:true,
            },
            classNameNode:{
            	required:true
            },
            isTeam:{
                required:true
            },
            gender:{
            	required:true
            },
            scoreCard:{
            	required:true
            },
            resultType:{
            	required:true
            },
            resultUnit:{
            	required:true
            },
            finalAdmission:{
            	isInteger:true
            },
            
            mastersLevel:{
            	masterValue:true
            },
            firstLevel:{
            	masterValue:true
            },
            secondLevel:{
            	masterValue:true
            },
            
            threeLevel:{
            	masterValue:true
            },
            orderNum:{
            	isInteger:true
            },
				
		},
        errorPlacement:function(error,element) {
        	layer.tips($(error).text(), element, {
        		tipsMore: true
        	});
        },
		submitHandler: function(form) { //验证通过之后回调
			ajaxFormSubmit($('#formId').serializeObject(),$("#contextPath").val() + '/projectManager/saveToUpdate');
		},
		invalidHandler: function(form, validator) {return false;}
	});
	//提交表单
	$("#submitBtn").click(function(){
		$("#formId").submit();
	})
})
function loadresult(){
	var result = $('#resultType').val();
	if(result == '1' || result == '2'){
		$(".col-md-7[name='masterValue'] input[type='text']").each(function(){
		      $(this).attr("disabled",false);
		      $(this).attr("placeholder","格式:秒.毫秒");
	    });
	}else if(result == '3' || result == '4'){
		$(".col-md-7[name='masterValue'] input[type='text']").each(function(){
			  $(this).attr("disabled",false);
			  $(this).attr("placeholder","格式为正整数或小数");
		});
	}else{
		$(".col-md-7[name='masterValue'] input[type='text']").each(function(){
		      $(this).attr("disabled",true);
		      $(this).val("");
		      $(this).attr("placeholder","");
	     }); 
	}
}

//ajax提交修改
function ajaxFormSubmit(Object, url) {
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
				Error.displayError(data);
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
					self.location.href = "index?pCid="+$("#pCid").val()+"&pName="+$("#classNameNode").val();
				}
			}
		})
	})
}