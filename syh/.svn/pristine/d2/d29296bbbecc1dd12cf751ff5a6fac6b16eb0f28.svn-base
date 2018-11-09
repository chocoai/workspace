$(function() {
	$("#deleteScoreRecord").click(function() {
		var chk_value = [];
		var index = null;
		$('input[name="cid"]:checked').each(function() {
			chk_value.push($(this).val());
		});
		if (chk_value.length == 0 || chk_value.length > 1) {
			layer.msg('请选择一条记录!');
		} else {
			$.ajax({
				type : 'POST',
				url : $("#contextPath").val()+'/projectManager/searchProjectByScoreRecord',
				data : {
					'cid' : chk_value.toString()
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
						icon : 1
					});
				},
				success : function(data) {
					if (data.status == 'nodata') {
						layer.msg('请选择一条记录!', {
							icon : 2,
							time : 1000
						});
					} else if (data.status == "noscore") {
						layer.msg('此方式已关联项目，请先删除所关联的项目', {
							icon : 2,
							time : 1000
						});
					} else if (data.status == "success") {
						layer.msg('删除成功', {
							icon : 2,
							time : 1000
						});
						$("#queryBtn").click();
					}else{
						layer.msg('系统故障', {
							icon : 2,
							time : 1000
						});
					}
				}
			});
		}
	});
	$("#refreshTable").click(function(){
		var findContent =$('#ruleName').val();
		var currentPage = $("#currentPage").val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/scoreRecord/queryPage',
			data : {
				"findContent" : findContent,"currentPage" :currentPage
			},
			async: false,
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
				var scoreRecordList="";
				$("#scoreRecordList").html(scoreRecordList);
				$("#pageID").html("");
				$("#noDataPage").addClass("hidden");
				if(data.pageInfo.pages>0){
					for (var i = 0; i < data.pageInfo.list.length; i++) {
						var bean = data.pageInfo.list[i];
						scoreRecordList+='<tr>\
							<td><input type="checkbox" name="cid" value="'+bean.cid+'"></td>'+
							'<td>'+isStringNUll(bean.ruleName)+'</td>'+
							'<td>'+isStringNUll(bean.trophy)+'</td>'+
							'<td>'+isStringNUll(bean.certificate)+'</td>'+
							'<td>'+isStringNUll(bean.goldMedal)+'</td>'+
							'<td>'+isStringNUll(bean.silverMedal)+'</td>'+
							'<td>'+isStringNUll(bean.bronzeMedal)+'</td>'+
							'<td>'+isStringNUll(bean.firstScore)+'</td>'+
							'<td>'+isStringNUll(bean.secondScore)+'</td>'+
							'<td>'+isStringNUll(bean.thirdScore)+'</td>'+
							'<td>'+isStringNUll(bean.fourthScore)+'</td>'+
					 		'<td>'+isStringNUll(bean.fifthScore)+'</td>'+
							'<td>'+isStringNUll(bean.sixthScore)+'</td>'+
							'<td>'+isStringNUll(bean.seventhScore)+'</td>'+
							'<td>'+isStringNUll(bean.eightScore)+'</td>'+
							'<td>'+isStringNUll(bean.orderNum)+'</td>'+
							'<td>'+isStringNUll(bean.backup)+'</td>'+
							'</tr>'
					}
					$("#scoreRecordList").html(scoreRecordList);
					if(data.pageInfo.pages>1){
						$("#pageID").html(data.pageInfo.pageHtml);
					}
				}else{
					$("#scoreRecordList").html('<tr></tr>');
					$("#noDataPage").removeClass("hidden");
				}
			}
		});
	});
})

