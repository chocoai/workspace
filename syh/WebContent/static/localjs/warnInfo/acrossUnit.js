$(function() {
	$("#refreshTable").click(function(){
		var athleteName=$('#athleteName').val();
		var currentPage = $('#currentPage').val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/warnInfo/toAcrossUnitPage',
			data : {
				"athleteName":athleteName,
				"currentPage" : currentPage
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
				var warnInfoList="";
				$("#warnInfoList").html(warnInfoList);
				$("#pageID").html("");
				$("#noDataPage").addClass("hidden");
				
				if(data.pageInfo.pages>0){
					for (var i = 0; i < data.pageInfo.list.length; i++) {
						var bean = data.pageInfo.list[i];
						warnInfoList+='<tr>'+
							'<td>'+(i+1)+'</td>'+
							'<td>'+isStringNUll(bean.athleteName)+'</td>'+
							'<td>'+isStringNUll(bean.idCard)+'</td>'+
							'<td>'+isStringNUll(bean.unitName)+'</td>'+
							'<td>'+isStringNUll(bean.projectName)+'</td>'+
							'<td>'+isStringNUll(bean.className)+'</td>'+
							'</tr>'
					}
					$("#warnInfoList").html(warnInfoList);
					if(data.pageInfo.pages>1){
						$("#pageID").html(data.pageInfo.pageHtml);
					}
				}else{
					warnInfoList='<tr></tr>';
					$("#warnInfoList").html(warnInfoList);
					$("#noDataPage").removeClass("hidden");
				}
			}
		});
	});
})

