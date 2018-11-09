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
		$('#unitCid').val(keyword.id)
    });
	
	$("#submitBtn").click(function(){
		if(vilidata()){
				var pubrewInfo=new Object;
				pubrewInfo.companyCid=$('#unitCid').val();
		//		pubrewInfo.projectCid=$('#projectCid').val();
				pubrewInfo.infoType=$('#infoType').val();
				pubrewInfo.medalType=$('#medalType').val();
				pubrewInfo.medalCount=$('#medalCount').val();
				pubrewInfo.intrgralCount=$('#intrgralCount').val();
				if(pubrewInfo.intrgralCount==""){
					pubrewInfo.intrgralCount="0";
				}
				if(pubrewInfo.medalCount==""){
					pubrewInfo.medalCount="0";
				}
				pubrewInfo.reason=$('#reason').val();
				pubrewInfo.backup=$('#backup').val();
				var url=$("#contextPath").val() + "/pubRewInfo/doAddRewInfo";
				ajaxSubmit(pubrewInfo,url);
		}
		
	})
	
	function vilidata(){
		if($("#unitCid").val()==""){
			layer.tips("请选择单位","#unitName")
			return false;
		}else if($('#medalCount').val()!=""&&!isNumber($('#medalCount').val())){
			layer.tips("请输入正确的奖牌数量","#medalCount")
			$("#medalCount").focus();
			return false;
		}else if($('#intrgralCount').val()!=""&&!isNumber($('#intrgralCount').val())){
			layer.tips("请输入正确的积分数量","#intrgralCount");
			$("#intrgralCount").focus();
			return false;
		}else if($('#medalCount').val()==""&&$('#intrgralCount').val()==""){
			layer.tips("奖牌和积分请至少输入一项","#medalCount")
			$("#medalCount").focus();
			return false;
		}else{
			return true;
		}
	}
})