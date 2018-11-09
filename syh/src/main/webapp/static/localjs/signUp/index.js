$(function() {
	// 删除记录信息
	$("#deleteBtn").click(function() {
		var url = $("#contextPath").val() + '/unitInfo/deleteInfo';
		deleteInfo(url);
	})
	$("#updateInfo").click(function(){
		var chk_value = [];
		var index = null;
		$('input[name="cid"]:checked').each(function() {
			chk_value.push($(this).val());
		});
		if (chk_value.length != 1) {
			layer.msg('请选择一条记录');
		} else{
			self.location.href="get?cid="+chk_value[0]
		}
	})
	$("#refreshTable").click(function(){
		var findContent = $("#findContent").val();
		var currentPage = $("#currentPage").val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/unitInfo/queryPage',
			data : {
				"findContent" : findContent,"currentPage" :currentPage
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
				var list = "";
				$("#list").html(list);
				$("#pageID").html("");
				$("#noDataPage").addClass("hidden");
				if(data.pageInfo.pages>0){
					for (var i = 0; i < data.pageInfo.list.length; i++) {
					   var bean = data.pageInfo.list[i];
					   list +='<tr>\
							   <td><input onClick="click_input()" type="checkbox" name="cid" value="'+ bean.cid+ '"></td>'
							+ '<td>'+ isStringNUll(bean.unitName)+ '</td>'
							+ '<td>'+ isStringNUll(bean.abbreviation)+ '</td>'
							+ '<td>'+ isStringNUll(bean.athleteNum)+ '</td>'
							+ '<td>'+ isStringNUll(bean.unitContact)+ '</td>'
							+ '<td>'+ isStringNUll(bean.contactPhone)+ '</td>' 
							+ '</tr>'
					}
					$("#list").html(list);
					if(data.pageInfo.pages>1){
						$("#pageID").html(data.pageInfo.pageHtml);
					}
				}else{
					$("#list").html('<tr></tr>');
					$("#noDataPage").removeClass("hidden");
				}
			}
		});
	})
})

//ajax提交删除操作请求
function deleteInfo(url){
	var chk_value = [];
	var index = null;
	$('input[name="cid"]:checked').each(function() {
		chk_value.push($(this).val());
	});
	if (chk_value.length == 0) {
		layer.msg('请至少选择一条记录');
	} else {
		layer.confirm('确定删除所选记录？', {
			btn : [ '确定', '取消' ]
		// 按钮
		}, function() {
			$.ajax({
				type : 'POST',
				url :url,
				traditional : true,
				data : {
					cids : chk_value
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
					console.log("处理结果" + data);
					if (data== 'success') {
						layer.msg('删除成功', {
							icon : 1,
							time : 2000
						});
						$("#queryBtn").click();
					} else if (data== "failed") {
						layer.msg('删除失败', {
							icon : 2,
							time : 2000
						});
					}else if(data=="nodata"){
						layer.msg('没有数据要处理', {
							icon : 2,
							time : 2000
						});
					}else if(data=="nodelete"){
						layer.msg('该部门和运动员有关联，不可删除', {
							icon : 2,
							time : 2000
						});
					}
				}
			});
		}, function() {
			layer.msg('已取消', {
				time : 1000
			// 1s后自动关闭
			});
		});
	}
}
