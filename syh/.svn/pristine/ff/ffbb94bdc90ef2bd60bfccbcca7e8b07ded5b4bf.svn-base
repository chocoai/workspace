$(function() {
	var type = 0;
	var order = 0;
	var index = 0;
	$(".col-md-12 ul li").click(function(i){
		index = $(this).index();
		if(index==0){
			type = 0;
			order = 0;	
		}else if(index==1){
			type = 0;
			order = 2;	
		}else if(index==2){
			type = 1;
			order = 0;	
		}else{
			type = 1;
			order = 2;	
		}
		$(".col-md-12 ul li").removeClass("active");
		$(this).addClass("active");
		
		$("#queryBtn").click();
	});
	
	$("#exportExcel").click(function(){
		var tableHead = $("#tableTitle").text();
		window.location.href=$("#contextPath").val() +"/queryTeenagersThreeList/exportExcel?type="+type+"&order="+order+"&tableHead="+tableHead;
	})
	
	//查询刷新功能
	$("#queryBtn").click(function(){
		var tableTitle = $(".col-md-12 ul li").eq(index).text();
		$("#tableTitle").text("全名健身成年人类"+tableTitle);
		var	 thead=	  '<tr>'
					+ '<th>排名</th>'
					+ '<th>单位</th>'
					 if(order=='2'){
						 thead += '<th>总分</th>'
					 }
					 thead +=  '<th>金牌<img src="'+$("#contextPath").val() + '/static/images/gold.png" style="margin-left: 6px;"></th>'
						     + '<th>银牌<img src="'+$("#contextPath").val() + '/static/images/silver.png" style="margin-left: 6px;"></th>'
					         + '<th>铜牌<img src="'+$("#contextPath").val() + '/static/images/bronze.png" style="margin-left: 6px;"></th>'
						     + '<th>奖牌</th>'
					thead += '</tr>'
		$("#thead").html(thead);
					
		var queryCondition={
			"type":type,
			"order":order
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/queryGroupThreeList/query',
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
								if(order=='2'){
									list+= '<td>'+ fomatFloat(bean.intrgral,2)+ '</td>'
								}
						 list +=  '<td>'+ fomatFloat(bean.gold,2)+ '</td>'
								+ '<td>'+ fomatFloat(bean.silver,2)+ '</td>'
								+ '<td>'+ fomatFloat(bean.copper,2)+ '</td>'
								+ '<td>'+ fomatFloat(bean.total,2)+ '</td>'
								list+='</tr>'
					}
					$("#list").html(list);
					$("#noDataPage tr").addClass("hidden");
				}else{
					var tfootPage = "";
					$("#list").html('<tr></tr>');
					if(order==0){
						tfootPage ='<tr>'
							+'<td colspan="6" align="center">暂无数据</td>'+
							'</tr>'
					}else{
						tfootPage ='<tr>'
							+'<td colspan="7" align="center">暂无数据</td>'+
							'</tr>'
					}
					$("#noDataPage").html(tfootPage);
				}
			}
		});
	})
})