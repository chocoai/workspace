$(function(){
	//表单验证
	$("#formId").validate({
		onsubmit:true,// 是否在提交是验证
		rules : {
			ruleName:{
				required:true,
				isName:true,
				isScoreExistenceName:true,
            },
            trophy:{
                required:true,
                isInteger:true
            },
            certificate:{
            	required:true,
            	isInteger:true
            },
            goldMedal:{
            	required:true,
            	isInteger:true
            },
            silverMedal:{
            	required:true,
            	isInteger:true
            },
            bronzeMedal:{
            	required:true,
            	isInteger:true
            },
            firstScore:{
            	required:true,
            	isInteger:true
            },
            secondScore:{
            	required:true,
            	isInteger:true
            },
            thirdScore:{
            	required:true,
            	isInteger:true
            },
            fourthScore:{
            	required:true,
            	isInteger:true
            },
            fifthScore:{
            	required:true,
            	isInteger:true
            },
            sixthScore:{
            	required:true,
            	isInteger:true
            },
            seventhScore:{
            	required:true,
            	isInteger:true
            },
            eightScore:{
            	required:true,
            	isInteger:true
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
			ajaxSubmit($('#formId').serializeObject(),$("#contextPath").val() + '/scoreRecord/saveToUpdate');
		},
		invalidHandler: function(form, validator) {return false;}
	});
	//提交表单
	$("#submitBtn").click(function(){
		$("#formId").submit();
	})
})