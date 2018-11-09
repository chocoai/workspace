$(document).ready(function() {
	var teamCid = $('#teamCid').val();
	selectedAthletes(teamCid);
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
			return data
		}
	}).on('onSetSelectValue', function (e, keyword, data) {
		$('#unitCid').val(keyword.id)
    });
    $("#unitName").change(function(){
        $("#sel_all_area").find("option").remove();
        var unitCid = $("#unitCid").val();
        var athleteType = $("#athleteType").val();
        if(unitCid != null && unitCid != ''){
        	selectAthlete(athleteType,unitCid);
        }else {
        	var options = "";
        	$('#sel_all_area').append(options);
        }
    });
	$("#submitBtn").click(function(){
		if(vilidata()){
			var info=new Object;
			var array = new Array();
			var obj = document.getElementById('sel_selected_areas');
			var options = obj.options;
			for(var i=0,len=options.length;i<len;i++){
				var opt = options[i];
				array.push(opt.value);
			}
			info.teamCid=$('#teamCid').val();
			info.exp5=array.toString();
			var url=$("#contextPath").val() + "/athleteParticipat/editTeamInfo";
			ajaxSubmit(info,url);
		}
	})
})
function vilidata(){
	if($('#projectName').val().trim()==0){
		layer.msg("请选择比赛项目",{
			icon:2
		})
		return false;
	}else{
		return true;
	}
}

function selectAthlete(athleteType,unitCid){
	$.ajax({
		type:'post',
		url:$("#contextPath").val() + '/athleteBaseInfo/selectAthlete',
		data:{
			"athleteType":athleteType,
			"cid":unitCid
		},
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
			Error.displayError(data); 
			var options = "";
			for (var i = 0; i < data.athleteBaseInfoList.length; i++) {
				if(data.athleteBaseInfoList[i].alias != null && data.athleteBaseInfoList[i].alias != ""){
					options += "<option value="+data.athleteBaseInfoList[i].cid +">"+data.athleteBaseInfoList[i].athleteName+"("+data.athleteBaseInfoList[i].alias+","+data.athleteBaseInfoList[i].unitInfo.unitName+")"+"</option>";
				}else{
					options += "<option value="+data.athleteBaseInfoList[i].cid +">"+data.athleteBaseInfoList[i].athleteName+"</option>";
				}
			}
			$("#sel_all_area").find("option").remove();
			$('#sel_all_area').append(options);
		}
	})
}
function selectedAthletes(teamCid){
	$.ajax({
		type:'post',
		url:$("#contextPath").val() + '/athleteParticipat/selectAthletes',
		data:{
			"teamCid":teamCid
		},
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
			Error.displayError(data); 
			var options = "";
			for (var i = 0; i < data.athleteDetailList.length; i++) {
				if(data.athleteDetailList[i].athleteBaseInfo.alias != null && data.athleteDetailList[i].athleteBaseInfo.alias != ""){
					options += "<option value="+data.athleteDetailList[i].athleteCid +">"+data.athleteDetailList[i].athleteBaseInfo.athleteName+"("+data.athleteDetailList[i].athleteBaseInfo.alias+","+data.athleteDetailList[i].athleteBaseInfo.unitInfo.unitName+")"+"</option>";
				}else{
					options += "<option value="+data.athleteDetailList[i].athleteCid +">"+data.athleteDetailList[i].athleteName+"</option>";
				}
			}
			$("#sel_selected_areas").find("option").remove();
			$('#sel_selected_areas').append(options);
		}
	});
}