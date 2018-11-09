$(document).ready(function() {
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
	var unitCids="";
    $("#unitName").change(function(){
        $("#sel_all_area").find("option").remove();
        var unitCid = $("#unitCid").val();
        var athleteType = $("#athleteType").val();
        if(unitCid != null && unitCid != ''){
        	unitCids +=$('#unitCid').val()+",";
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
			info.projectCid=$('#projectCid').val();
			info.exp4 = unitCids;
			info.exp5=array.toString();
			layer.confirm("确定提交吗？", {
				title: '提示信息',
				btn : [ '确定', '取消' ],
				icon: 3
			}, function() {
				if(array.length <2){
					layer.msg("团队项目请选2个人以上 ",{
						icon:2
					})
				}else{
					$.ajax({
						type : 'post',
						url : $("#contextPath").val() + "/athleteParticipat/warnTeamInfo",
						data : {
							"projectCid":$('#projectCid').val(),
							"unitCids":unitCids,
							"athletesName" : array.toString()
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
							var data = eval("("+data+")")
							warnState = "";
							var keys = "";
							var values = "";
							var name = "";
							for(var key in data){
								if(key != null || key != "" || key != undefined){
									keys += key+";";
									values += data[key].split(",")[1]+";";
									name = data[key].split(",")[0]
								}else{
									var url=$("#contextPath").val() + "/athleteParticipat/doTeamInfo";
									ajaxSubmit(info,url);
								}
							}
							layer.confirm(data[key].split(",")[0]+"  "+key+"是否继续提交？", {
								title: '提示信息',
								btn : [ '确定', '取消' ],
								icon: 3
							}, function(){
								var url=$("#contextPath").val() + "/athleteParticipat/doTeamInfo";
								info.warnState = values;
								ajaxSubmit(info,url);
							})
//							if(data == "nodata"){
//								if("nuptial" in data) {
//									layer.msg(data.nuptial.split(",")[0]+"已报名该项目", {
//										title: '提示信息',
//										icon: 2
//									},function(){
//										if("age" in data){
//											layer.confirm(data.age.split(",")[0]+"年龄不符合,是否继续提交？", {
//												title: '提示信息',
//												btn : [ '确定', '取消' ],
//												icon: 3
//											}, function(){
//												if(("personSinglenum" in data) || ("personTeamnum" in data)||("personTotnum" in data)){
//													layer.confirm(data.personSinglenum.split(",")[0]+"项目个数已达到预警值,是否继续提交？", {
//														title: '提示信息',
//														btn : [ '确定', '取消' ],
//														icon: 3
//													}, function(){
//														if(("unitSinglenum" in data) || ("unitTeamnum" in data)||("unitTotnum" in data)){
//															layer.confirm(data.unitSinglenum.split(",")[0]+"项目个数已达到预警值,是否继续提交？", {
//																title: '提示信息',
//																btn : [ '确定', '取消' ],
//																icon: 3
//															}, function(){
//																var url=$("#contextPath").val() + "/athleteParticipat/saveToUpdate";
//																info.warnState = "1,2,3,4";
//																ajaxSubmit(info,url);
//															})
//														}else{
//															var url=$("#contextPath").val() + "/athleteParticipat/saveToUpdate";
//															info.warnState = "1,2,3";
//															ajaxSubmit(info,url);
//														}
//													})
//												}else{
//													var url=$("#contextPath").val() + "/athleteParticipat/saveToUpdate";
//													info.warnState = "1,2";
//													ajaxSubmit(info,url);
//												}
//											})
//										}
//									})
//								}else if("age" in data){
//									layer.confirm(data.age.split(",")[0]+"年龄不符合,是否继续提交？", {
//										title: '提示信息',
//										btn : [ '确定', '取消' ],
//										icon: 3
//									}, function(){
//										if(("personSinglenum" in data) || ("personTeamnum" in data)||("personTotnum" in data)){
//											layer.confirm(data.personSinglenum.split(",")[0]+"项目个数已达到预警值,是否继续提交？", {
//												title: '提示信息',
//												btn : [ '确定', '取消' ],
//												icon: 3
//											}, function(){
//												if(("unitSinglenum" in data) || ("unitTeamnum" in data)||("unitTotnum" in data)){
//													layer.confirm(data.unitSinglenum.split(",")[0]+"项目个数已达到预警值,是否继续提交？", {
//														title: '提示信息',
//														btn : [ '确定', '取消' ],
//														icon: 3
//													}, function(){
//														var url=$("#contextPath").val() + "/athleteParticipat/saveToUpdate";
//														info.warnState = "2,3,4";
//														ajaxSubmit(info,url);
//													})
//												}else{
//													var url=$("#contextPath").val() + "/athleteParticipat/saveToUpdate";
//													info.warnState = "2,3";
//													ajaxSubmit(info,url);
//												}
//											})
//										}else{
//											var url=$("#contextPath").val() + "/athleteParticipat/saveToUpdate";
//											info.warnState = "2";
//											ajaxSubmit(info,url);
//										}
//									})
//								}else if(("personSinglenum" in data) || ("personTeamnum" in data)||("personTotnum" in data)){
//									layer.confirm(data.personSinglenum.split(",")[0]+"项目个数已达到预警值,是否继续提交？", {
//										title: '提示信息',
//										btn : [ '确定', '取消' ],
//										icon: 3
//									}, function(){
//										if(("unitSinglenum" in data) || ("unitTeamnum" in data)||("unitTotnum" in data)){
//											layer.confirm(data.unitSinglenum.split(",")[0]+"项目个数已达到预警值,是否继续提交？", {
//												title: '提示信息',
//												btn : [ '确定', '取消' ],
//												icon: 3
//											}, function(){
//												var url=$("#contextPath").val() + "/athleteParticipat/saveToUpdate";
//												info.warnState = "3,4";
//												ajaxSubmit(info,url);
//											})
//										}else{
//											var url=$("#contextPath").val() + "/athleteParticipat/saveToUpdate";
//											info.warnState = "3";
//											ajaxSubmit(info,url);
//										}
//									})
//								}else if(("unitSinglenum" in data) || ("unitTeamnum" in data)||("unitTotnum" in data)){
//									layer.confirm(data.unitSinglenum.split(",")[0]+"项目个数已达到预警值,是否继续提交？", {
//										title: '提示信息',
//										btn : [ '确定', '取消' ],
//										icon: 3
//									}, function(){
//										var url=$("#contextPath").val() + "/athleteParticipat/saveToUpdate";
//										info.warnState = "4";
//										ajaxSubmit(info,url);
//									})
//								}else{
//									var url=$("#contextPath").val() + "/athleteParticipat/saveToUpdate";
//									ajaxSubmit(info,url);
//								}
//							}else{
//								var url=$("#contextPath").val() + "/athleteParticipat/saveToUpdate";
//								ajaxSubmit(info,url);
//							}
//							var url=$("#contextPath").val() + "/athleteParticipat/doTeamInfo";
//							ajaxSubmit(info,url);
						}
					})
				}
			})
		}
	});
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
})

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