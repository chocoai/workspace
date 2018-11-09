$(function() {
	
	var queryCondition={
			"type":$("#select").val(),
			"order":2
		}
	$.ajax({
		type : 'POST',
		url : $("#contextPath").val() + '/common/query',
		data : queryCondition,
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
			$("#list").html(list);
			if(data.list.length>0){
				for (var i = 0; i < data.list.length; i++) {
					var bean = data.list[i];
					   list += '<tr>'
							+ '<td>'+ (i+1)+ '</td>'
							+ '<td>'+ isStrNUll(bean.unitName)+ '</td>'
							+ '<td>'+ fomatFloat(bean.intrgral,2)+ '</td>'
							+ '<td>'+ fomatFloat(bean.gold,2)+ '</td>'
							+ '<td>'+ fomatFloat(bean.silver,2)+ '</td>'
							+ '<td>'+ fomatFloat(bean.copper,2)+ '</td>'
							+ '<td>'+ fomatFloat(bean.total,2)+ '</td>'
							+'</tr>'
				}
				$("#list").html(list);
			}
		}
	});
	
	//查询刷新功能
	$("#select").change(function(){
		var queryCondition={
				"type":$("#select").val(),
				"order":2
			}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/common/query',
			data : queryCondition,
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
				$("#list").html(list);
				if(data.list.length>0){
					for (var i = 0; i < data.list.length; i++) {
						var bean = data.list[i];
						   list += '<tr>'
								+ '<td>'+ (i+1)+ '</td>'
								+ '<td>'+ isStrNUll(bean.unitName)+ '</td>'
								+ '<td>'+ fomatFloat(bean.intrgral,2)+ '</td>'
								+ '<td>'+ fomatFloat(bean.gold,2)+ '</td>'
								+ '<td>'+ fomatFloat(bean.silver,2)+ '</td>'
								+ '<td>'+ fomatFloat(bean.copper,2)+ '</td>'
								+ '<td>'+ fomatFloat(bean.total,2)+ '</td>'
								+'</tr>'
					}
					$("#list").html(list);
				}
			}
		});

	})
})