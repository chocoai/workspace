$(function(){
//	$("#exp1").load(
//			$("#contextPath").val() + '/sysDict/queryType?'+ Math.random(),{
//				'itemType': 'athletesType',
//				"number":$("#exp1click").val(),
//				"type":"0"
//	});
//	$("#classType").load(
//			$("#contextPath").val() + '/sysDict/queryType?'+ Math.random(),{
//				'itemType': 'classType',
//				"number":$("#classTypeclick").val(),
//				"other":$("#PclassType").val(),
//				"type":"2"
//	});
	var param = {
			'itemType': 'athletesType',
			"number":$("#exp1click").val(),
			"type":"0"
			}
	loaddict(param,'exp1')
	
	var param1 = {
		'itemType': 'classType',
		"number":$("#classTypeclick").val(),
		"other":$("#PclassType").val(),
		"type":"2"
	}
	loaddict(param1,'classType')

	$('#classifyName').focus();

	//取消功能
	$("#cancleBtnTo").click(function(){  //取消按钮
		layer.confirm("确定取消吗？",{
			title:'提示信息',
			btn : [ '确定', '取消' ], // 按钮
			icon: 3
		},function(){
			self.location.href = "index?pCid="+$("#classCid").val();
		})
	})
	
	//开始时间
    $('#ageStart').datepicker({  
	    todayBtn : "linked",  
	    autoclose : true,  
	    todayHighlight : true,  
	    endDate : new Date() 
	}).on('changeDate',function(e){  
	    var startTime = e.date;  
	    $('#ageEnd').datepicker('setStartDate',startTime);  
	});  
	//结束时间  
	$('#ageEnd').datepicker({  
	    todayBtn : "linked",  
	    autoclose : true,  
	    todayHighlight : true,
	   // endDate : new Date()
	}).on('changeDate',function(e){  
	    var endTime = e.date;  
	    $('#ageStart').datepicker('setEndDate',endTime);  
	});
	
	//团体项目女性年龄范围
	$('#teamWomanAgeStart').datepicker({  
		todayBtn : "linked",  
		autoclose : true,  
		todayHighlight : true,  
		endDate : new Date() 
	}).on('changeDate',function(e){  
		var startTime = e.date;  
		$('#teamWomanAgeEnd').datepicker('setStartDate',startTime);  
	});  
	//结束时间  
	$('#teamWomanAgeEnd').datepicker({  
		todayBtn : "linked",  
		autoclose : true,  
		todayHighlight : true,
		endDate : new Date()
	}).on('changeDate',function(e){  
		var endTime = e.date;  
		$('#teamWomanAgeStart').datepicker('setEndDate',endTime);  
	});
	
	//团体项目男性年龄范围
	$('#teamManAgeStart').datepicker({  
		todayBtn : "linked",  
		autoclose : true,  
		todayHighlight : true,  
		endDate : new Date() 
	}).on('changeDate',function(e){  
		var startTime = e.date;  
		$('#teamManAgeEnd').datepicker('setStartDate',startTime);  
	});  
	//结束时间  
	$('#teamManAgeEnd').datepicker({  
		todayBtn : "linked",  
		autoclose : true,  
		todayHighlight : true,
		endDate : new Date()
	}).on('changeDate',function(e){  
		var endTime = e.date;  
		$('#teamManAgeStart').datepicker('setEndDate',endTime);  
	});
	
	var athletesNum=document.getElementById("athletesNum");
	athletesNum.onkeyup=function(){
		if(athletesNum.value){
			document.getElementById("manNum").value = athletesNum.value/2;
			document.getElementById("womanNum").value = athletesNum.value/2;
		}
	}
	//表单验证
	$("#formId").validate({
		onsubmit:true,// 是否在提交是验证
		rules : {
			classifyName:{
				required:true,
			//	isName:true,
				isClassExistenceName:true
            },
			exp1:{
				required:true
            },
            className:{
            	required:true
            },
            classType:{
            	required:true
            },
            athletesNum:{
            	isInteger:true,
            	isSum:true
            },
            manNum:{
            	isInteger:true
            },
            womanNum:{
            	isInteger:true
            },
            unitTeam:{
            	isInteger:true
            },
            ageSum:{
            	isInteger:true
            },
            personTotnum:{
            	isInteger:true
            },
            unitTotnum:{
            	isInteger:true
            },
            personSinglenum:{
            	isInteger:true
            },
            unitSinglenum:{
            	isInteger:true
            },
            personTeamnum:{
            	isInteger:true
            },
            unitTeamnum:{
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
			ajaxClassFormSubmit($('#formId').serializeObject(),$("#contextPath").val() + '/classManager/saveToUpdate');
		},
		invalidHandler: function(form, validator) {return false;}
	});
	//提交表单
	$("#submitBtn").click(function(){
		
		$("#formId").submit();
	})
})
//ajax提交修改
function ajaxClassFormSubmit(Object, url) {
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
					self.location.href = "index?pCid="+$("#classCid").val();
				}
			}
		})
	})
}