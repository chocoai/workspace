$(function() {
//	$("#nation").load(
//			$("#contextPath").val() + '/sysDict/queryType',{
//				'itemType': 'nation',
//				"number":$("#nationclick").val(),
//				"type":"1"
//	});
	
//	$("#athletesType").load(
//			$("#contextPath").val() + '/sysDict/queryType',{
//				'itemType': 'athletesType',
//				"number":$("#athletesTypeclick").val(),
//				"type":"0"
//	});
	var param = {
			'itemType': 'nation',
			"number":$("#nationclick").val(),
			"type":"1"
			}
	loaddict(param,'nation')
	
	var param1 = {
		'itemType': 'athletesType',
		"number":$("#athletesTypeclick").val(),
		"type":"0"
	}
	loaddict(param1,'athletesType')
	
	

	
	//判断运动员是否重名
	$("#athleteName").blur(function(){
		var cid = $("#cid").val().trim();
		var athleteName = $("#athleteName").val().trim();
		var oldAthleteName = $("#oldAthleteName").val();
		
		if(!cid||(cid&&(oldAthleteName!=athleteName))){
			$.ajax({
				url: $("#contextPath").val() + '/athleteBaseInfo/isName',
				data: {"name" : athleteName},
				async: false, //关键，设置为同步
				type: 'POST',
				dataType : 'html',
				timeout : 5000,
				success: function (data) {
					if(data=="true"){
						layer.tips('该运动员存在重名情况，请设置别名',"#athleteName");
					}
				},
				error : function() {
					layer.msg('系统故障', {
						icon : 2
					});
				},
			});
		}	
	});
	//判断运动员是否录入
	$("#idCard").blur(function(){
		var cid = $("#cid").val().trim();
		var idCard = $("#idCard").val().trim();
		var unitCid = $("#unitCid").val().trim();
		var oldIdCard = $("#oldIdCard").val().trim();
		var oldUnitCid = $("#oldUnitCid").val().trim();
		
		if(idCard){
			if(IsIDCard(idCard)){
				var tmpStr = "";
				if(idCard.length==15){
					tmpStr = idCard.substring(6, 12);
					tmpStr = "19" + tmpStr;
				}else{
					tmpStr = idCard.substring(6, 14);
				}
				tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)
				$("#birthday").val(tmpStr);
			}
		}
		var isValidation = false;
		if(idCard && unitCid){
			if(cid){//编辑
				if(idCard!=oldIdCard){//身份证号不改变
					isValidation = true;
				}
			}else{//新增
				isValidation = true;
			}
		}
		if(isValidation){
			$.ajax({
				type : 'POST',
				url : $("#contextPath").val() + '/athleteBaseInfo/isManyUnit',
				data : {
					"IDcard" : idCard,"unitCid":unitCid
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
					if (data == "input") {
						layer.tips('该单位下该运动员已经录入，不可重复录入',"#idCard");
						$("#unitName").val("");
						$("#unitCid").val("");
					}else if(data == "true"){
						layer.confirm("该运动员存在跨单位情况，是否确认录入？",{
							btn : [ '确定', '取消' ]// 按钮
						},function(){
							$(".layui-layer-close").click();
						},function(){
							$("#unitName").val("");
							$("#unitCid").val("");
						})
					}
				}
			});
		}
	});
	$( "#birthday").datepicker({
    	todayBtn:"linked",
    	keyboardNavigation:!1,
    	forceParse:!1,
    	calendarWeeks:!0,
    	autoclose:!0,
    	endDate : new Date() 
    });
	//单位的控件
	$(document).on("focus","[name='unitName']",function(){
		var baiduBsSuggest = $(this).bsSuggest({
			allowNoKeyword : false,
			getDataMethod : "url",
	        showHeader:false,
	        effectiveFields: ["unitName"],
			idField:'cid',
			keyField:'unitName',
			url :$("#contextPath").val() + "/pubRewInfo/getUnit?str=",
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
						unitName : json[i].unitName
					})
				}
				return data
			}
		}).on('onSetSelectValue', function (e, keyword, data) {
			$(this).next().val(keyword.id);
			$("#idCard").blur();
	    });
	})
	
	//表单验证
	$("#formId").validate({
		onsubmit:true,// 是否在提交是验证
		rules : {
			athleteName:{
				required:true,
				isName:true
            },
            nation:{
                required:true
            },
            idCard:{
            	required:true,
            	isDigit:true
            },
            unitName:{
            	required:true
            },
            athletesType:{
            	required:true
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
			ajaxSubmit($('#formId').serializeObject(),$("#contextPath").val() + '/athleteBaseInfo/saveToUpdate');
		},
		invalidHandler: function(form, validator) {return false;}
	});
	//提交表单
	$("#submitBtn").click(function(){
		$("#formId").submit();
	})
	
	//获得简称自动填充
	getPname("athleteName","abbreviation");
})

