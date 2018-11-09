$(function() {
	var order = 0;
	$(".col-md-12 ul li").click(function(i){
		order = $(this).index();
		$(".col-md-12 ul li").removeClass("active");
		$(this).addClass("active");
		
		$("#queryBtn").click();
	});
	
	$("#exportExcel").click(function(){
		var tableHead = $(".col-md-12 ul li").eq(order).text()
		window.location.href="exportExcel?type=2&order="+order+"&tableHead="+tableHead;
	})
	
	//查询刷新功能
	$("#queryBtn").click(function(){
		var tableTitle = $(".col-md-12 ul li").eq(order).text();
		$("#tableTitle").text(tableTitle);
		var	 thead=	  '<tr>'
					+ '<th>排名</th>'
					+ '<th>单位</th>'
					if(order=='1'){
					   thead += '<th>奖牌</th>'
					}
					 if(order=='2'){
						 thead += '<th>总分</th>'
					 }
					 thead +=  '<th>金牌<img src="'+$("#contextPath").val() + '/static/images/gold.png" style="margin-left: 6px;"></th>'
						     + '<th>银牌<img src="'+$("#contextPath").val() + '/static/images/silver.png" style="margin-left: 6px;"></th>'
					         + '<th>铜牌<img src="'+$("#contextPath").val() + '/static/images/bronze.png" style="margin-left: 6px;"></th>'
					if(order!='1'){
						   thead += '<th>奖牌</th>'
					}
					if(order=='0'){
						thead += '<th>输送奖励金牌</th>'
					}
					thead += '</tr>'
		$("#thead").html(thead);
					
		var queryCondition={
			"type":2,
			"order":order
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/queryTeenagersThreeList/query',
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
				Error.displayError(data); 
				var list = "";
				$("#list").html(list);
				if(data.list.length>0){
					$("#noDataPage").html("");
					for (var i = 0; i < data.list.length; i++) {
						var bean = data.list[i];
						   list += '<tr>'
								+ '<td>'+ (i+1)+ '</td>'
								+ '<td>'+ isStrNUll(bean.unitName)+ '</td>'
								if(order=='1'){
									list+= '<td>'+ fomatFloat(bean.total,2)+ '</td>'
								}
								if(order=='2'){
									list+= '<td>'+ fomatFloat(bean.intrgral,2)+ '</td>'
								}
						 list +=  '<td>'+ fomatFloat(bean.gold,2)+ '</td>'
								+ '<td>'+ fomatFloat(bean.silver,2)+ '</td>'
								+ '<td>'+ fomatFloat(bean.copper,2)+ '</td>'
								if(order!='1'){
									list+= '<td>'+ fomatFloat(bean.total,2)+ '</td>'
								}
								if(order=='0'){
									list+= '<td>'+ fomatFloat(bean.carryGold,2)+ '</td>'
								}
								list+='</tr>'
					}
					$("#list").html(list);
				}else{
					var tfootPage = "";
					$("#list").html('<tr></tr>');
					if(order==1){
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