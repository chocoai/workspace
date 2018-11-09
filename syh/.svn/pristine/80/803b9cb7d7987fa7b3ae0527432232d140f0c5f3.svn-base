$(function() {
	//单位名称首字母搜索
	var baiduBsSuggest = $("#unitName").bsSuggest({
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
			return data;
		}
	}).on('onSetSelectValue', function (e, keyword, data) {
		$('#unitCid').val(keyword.id);
		var projectCid=$('#projectCid').val();
		if( projectCid!=''){
			$.ajax({
				type : 'post',
				url : $("#contextPath").val() + "/pubRewInfo/getParticipatInfo?str=",
				data : {
					projectCid : projectCid,
					unitCid : keyword.id
				},
				dataType : 'json',
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
					var participatSelect = $('#participatId');
					participatSelect.empty(); 
					var list = data.participatInfo;
					
					for(var i=0;i<list.length;i++ ){
						participatSelect.append($("<option value='"+list[i].participatId+"'>单位:("+list[i].unitName+"),人员:("+list[i].athleteName+")</option>"));
					}
				}
			})
		}
    });
	
	$("#projectCid").change(function() {
		var companyCid=$('#unitCid').val();
		var projectCid=$('#projectCid').val();
		if(companyCid){
			$.ajax({
				type : 'post',
				url : $("#contextPath").val() + "/pubRewInfo/getParticipatInfo?str=",
				data : {
					projectCid : projectCid,
					unitCid : companyCid
				},
				dataType : 'json',
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
					var participatSelect = $('#participatId');
					participatSelect.empty(); 
					var list = data.participatInfo;
					
					for(var i=0;i<list.length;i++ ){
						participatSelect.append($("<option value='"+list[i].participatId+"'>单位:("+list[i].unitName+"),人员:("+list[i].athleteName+")</option>"));
					}
				}
			})
		}

	});  
	
	$("#submitBtn").click(function(){
		if(vilidata()){
				var pubrewInfo=new Object;
				pubrewInfo.companyCid=$('#unitCid').val();
				pubrewInfo.participatId=$('#participatId').val();
				pubrewInfo.projectCid=$('#projectCid').val();
				pubrewInfo.infoType=$('#infoType').val();
				
				pubrewInfo.isMedal=$("#isMedal").get(0).checked;
				pubrewInfo.isIntrgral=$("#isIntrgral").get(0).checked;
				pubrewInfo.isScores=$("#isScores").get(0).checked;
				pubrewInfo.isRanking=$("#isRanking").get(0).checked;
	
				pubrewInfo.reason=$('#reason').val();
				pubrewInfo.backup=$('#backup').val();
				var url=$("#contextPath").val() + "/pubRewInfo/doAddPunishInfo";
				ajaxSubmit(pubrewInfo,url);
		}
	})
	
	function vilidata(){
		var ismedal = $("#isMedal").get(0).checked;
		var isIntrgral = $("#isIntrgral").get(0).checked;
		var isScores = $("#isScores").get(0).checked;
		var isRanking = $("#isRanking").get(0).checked;
		if(!ismedal && !isIntrgral && !isScores && !isRanking){
			layer.tips("请至少选择一项取消","#isIntrgral");
			return false;
		}else if($("#unitCid").val()==""){
			layer.tips("请选择单位","#unitName")
			return false;
		}
/*		else if($('#medalCount').val()!=""&&!isNumber($('#medalCount').val())){
			layer.tips("请输入正确的奖牌数量","#medalCount")
			$("#medalCount").focus();
			return false;
		}else if($('#intrgralCount').val()!=""&&!isNumber($('#intrgralCount').val())){
			layer.tips("请输入正确的积分数量","#intrgralCount");
			$("#intrgralCount").focus();
			return false;
		}
		else if($('#medalCount').val()==""&&$('#intrgralCount').val()==""){
			layer.tips("奖牌和积分请至少输入一项","#medalCount")
			$("#medalCount").focus();
			return false;
		}*/
		else if($("#participatId").val()=="" || $("#participatId").val()==null){
			layer.tips("请选择参赛信息","#participatId")
			return false;
		}else{
			return true;
		}
	}
})