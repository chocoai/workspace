$(function() {
	var type = 0;
	$(".col-md-12 ul li").click(function(i){
		type = $(this).index();
		$(".col-md-12 ul li").removeClass("active");
		$(this).addClass("active");
		
		$("#queryBtn").click();
	});
	
	$("#exportExcel").click(function(){
		var tableHead = $(".col-md-12 ul li").eq(type).text()
		window.location.href="exportExcel?type="+type+"&order=1&tableHead="+tableHead;
	})
	
	//查询刷新功能
	$("#queryBtn").click(function(){
		var tableTitle = $(".col-md-12 ul li").eq(type).text();
		$("#tableTitle").text(tableTitle);
		var queryCondition={
			"type":type,
			"order":1
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/queryDetailList/query',
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
				$("#unitDetail").html("");
				$("#detailList").html("");
				if(!data.project){
					$("#tfootPage").removeClass("hidden");
					$("#exportExcelBtn").addClass("hidden");
					return;
				}
				//初始化数据
				$("#tfootPage").addClass("hidden");
				$("#exportExcelBtn").removeClass("hidden");
				var tr1 = "<tr><th rowspan='2' style='vertical-align: middle;'>项目</th>";
				var tr2 = "<tr>";
				var tr3 = "";
				var tr4 = "<tr><th>合计</th>";
				for (var i = 0; i <data.unit.length; i++) {
					var bean = data.unit[i];
					tr1 += "<th colspan='5'>"+bean.unitName+"</th>";
					tr2 += "<th>金</th><th>银</th><th>铜</th><th>总分</th><th>奖牌</th>"
					tr4 += "<th>"+isStrNUll0(bean.gold)+"</th><th>"+isStrNUll0(bean.silver)+"</th><th>"+isStrNUll0(bean.copper)+"</th><th>"+isStrNUll0(bean.intrgral)+"</th><th>"+isStrNUll0(bean.total)+"</th>"
				}
				tr1 += "</tr>";
				tr2 += "</tr>";
				tr4 += "</tr>";
				for (var j = 0; j <data.project.length; j++) {
					tr3 += "<tr><td>"+data.project[j].projectName+"</td>";
					for (var i = 0; i <data.unit.length; i++) {
						var goldid = data.unit[i].unitCid+"_gold_"+data.project[j].projectCid;
						var silverid = data.unit[i].unitCid+"_silver_"+data.project[j].projectCid;
						var copperid = data.unit[i].unitCid+"_copper_"+data.project[j].projectCid;
						var intrgralid = data.unit[i].unitCid+"_intrgral_"+data.project[j].projectCid;
						var totalid = data.unit[i].unitCid+"_total_"+data.project[j].projectCid;;
						tr3 += "<td id='"+goldid+"'>0.00</td><td id='"+silverid+"'>0.00</td><td id='"+copperid+"'>0.00</td><td id='"+intrgralid+"'>0.00</td><td id='"+totalid+"'>0.00</td>"
					}
					tr3 += "</tr>";
				}
				$("#unitDetail").html(tr1+tr2);
				$("#detailList").html(tr3+tr4);
				
				//加载数据
				for (var k = 0; k <data.rows.length; k++) {
					var bean =data.rows[k];
					var goldid = bean.unitCid+"_gold_"+bean.classCid;
					var silverid = bean.unitCid+"_silver_"+bean.classCid;
					var copperid = bean.unitCid+"_copper_"+bean.classCid;
					var intrgralid = bean.unitCid+"_intrgral_"+bean.classCid;
					var totalid = bean.unitCid+"_total_"+bean.classCid;
					
					$("#"+goldid).text(isStrNUll0(bean.gold));
					$("#"+silverid).text(isStrNUll0(bean.silver));
					$("#"+copperid).text(isStrNUll0(bean.copper));
					$("#"+intrgralid).text(isStrNUll0(bean.intrgral));
					$("#"+totalid).text(isStrNUll0(bean.total));
				}
				
			}
		});
	})
})