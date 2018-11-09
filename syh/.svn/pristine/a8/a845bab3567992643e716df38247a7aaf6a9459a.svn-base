$(function() {
	//查看运动员信息
	$(document).on("click",".detail",function(){
		$('#myModal').modal();
		var url = $("#contextPath").val() + '/athleteParticipat/selectAthletesCid';
		var teamCid = $(this).next().next().val();
		selectAthleteDetail(url,teamCid);
	})
	// 删除记录信息
	$("#deleteBtn").click(function() {
		var url = $("#contextPath").val() + '/athleteParticipat/deleteTeamInfo';
		deleteInfo(url);
	})
	//修改信息
	$("#updateInfos").click(function(){
		var chk_value = [];
		var index = null;
		$('input[name="cid"]:checked').each(function() {
			chk_value.push($(this).val());
		});
		if (chk_value.length != 1) {
			layer.msg('请选择一条记录');
		} else{
			self.location.href="updateInfos?cid="+chk_value
		}
	})
	$("#refreshTable").click(function(){
		var currentPage=$("#currentPage").val();
		var unitName=$('#unitName').val();
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/athleteParticipat/queryTeamPage',
			data : {
				"currentPage":currentPage,
				"unitName":unitName
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
				var teamParticipatList="";
				$("#teamParticipatList").html(teamParticipatList);
				$("#totalPage").text(data.pageInfo.pages);
				if(data.infoList.length==0){   //如果没有结果
					$("#pageInfo").hide();
					$("#noDataPage").removeClass("hidden");
				}else{    //查询得有列表结果
					$("#pageInfo").show();
					$("#noDataPage").addClass("hidden");
					if($("#totalPage").text()==1){
						$("#nextPageIco").addClass("disabled")
						$("#lastPageIco").addClass("disabled")
					}
					for (var i = 0; i < data.infoList.length; i++) {
						teamParticipatList+='<tr>\
							<td><input type="checkbox" name="cid" value="'+data.infoList[i].teamCid+","+data.infoList[i].unitCid+'"></td>'+
							'<td>'+isStringNUll(data.infoList[i].unitName)+'</td>'+
							'<td>'+isStringNUll(data.infoList[i].projectManager.projectName)+'</td>'+
							'<td>'+isStringNUll(data.infoList[i].projectManager.classManager.classifyName)+'</td>'+
							'<td>'+
							'<a role="button"  href="javascript:" class="btn btn-info detail">代表队明细</a>'+
							'<input type="hidden" value="'+isStringNUll(data.infoList[i].projectCid)+'"/>'+
							'<input type="hidden" value="'+isStringNUll(data.infoList[i].teamCid)+'"/>'+
							'</td>'+
							'</tr>'
					}
					$("#teamParticipatList").html(teamParticipatList);
				}
			}
		});
	})
})
function selectAthleteDetail(url,teamCid){
	$.ajax({
		type : 'POST',
		url : url,
		data : {
			"teamCid" : teamCid
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
			var list = "";
			$("#infoList").html(list);
			for (var i = 0; i < data.athleteBaseInfoList.length; i++) {
				list += '<tr>'
					+ '<td>'
					+ (i+1)
					+ '</td>'
					+ '<td>'
					+ isStringNUll(data.athleteBaseInfoList[i].athleteBaseInfo.athleteName)
					+ '</td>'
					+ '<td>'
					+ isStringNUll(data.athleteBaseInfoList[i].athleteBaseInfo.sexStr)
					+ '</td>'
					+ '<td>'
					+ isStringNUll(data.athleteBaseInfoList[i].athleteBaseInfo.idCard)
					+ '</td>' 
					+ '<td>'
					+ isStringNUll(data.athleteBaseInfoList[i].athleteBaseInfo.birthday)
					+ '</td>'
					+ '<td>'
					+ isStringNUll(data.athleteBaseInfoList[i].athleteBaseInfo.unitInfo.unitName)
					+ '</td>'
					+ '</tr>'
			}
			$("#infoList").html(list);
		}
	});
}