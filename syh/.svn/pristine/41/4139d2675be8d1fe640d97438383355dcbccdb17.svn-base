$(function(){
	//表单验证
	$("#formId").validate({
		onsubmit:true,// 是否在提交是验证
		rules : {
			unitName:{
				required:true,
				isName:true,
				isUnitExistenceName:true,
            },
            athleteNum:{
            	isInteger:true
            },
            contactPhone:{
            	isMobile:true
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
			ajaxSubmit($('#formId').serializeObject(),$("#contextPath").val() + '/unitInfo/saveToUpdate');
		},
		invalidHandler: function(form, validator) {return false;}
	});
	//提交表单
	$("#submitBtn").click(function(){
		$("#formId").submit();
	})
	//获得简称自动填充
	getPname("unitName","abbreviation");

})