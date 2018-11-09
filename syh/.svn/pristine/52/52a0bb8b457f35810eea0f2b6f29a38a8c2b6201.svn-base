$(function() {
	var currentPage = $("#pageNo").val();
	if(!currentPage){
		currentPage=1;
	}
	$.ajax({
		type : 'POST',
		url : $("#contextPath").val() + '/unitInfo/queryPage',
		data : {
			"currentPage" :currentPage
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
			$("#unitList").html(list);
			if(data.pageInfo.pages>0){
				for (var i = 0; i < data.pageInfo.list.length; i++) {
				   var bean = data.pageInfo.list[i];
				   list +='<a href="javaScript:" id="unit_'+bean.cid+'" onclick="_unitclick('+bean.cid+')"  class="list-group-item">'+isStringNUll(bean.unitName)+'</a> '
				}
				$("#unitList").html(list);
				if(data.pageInfo.pages>1){
					$("#panelID").html(data.pageInfo.pageHtml1);
				}
			}else{
				$("#panelID").html("");
			}
		}
	});
	
	$("#refreshUnit").click(function(){
		var currentPage = $("#pageNo").val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/unitInfo/queryPage',
			data : {
				"currentPage" :currentPage
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
				$("#unitList").html(list);
				if(data.pageInfo.pages>0){
					for (var i = 0; i < data.pageInfo.list.length; i++) {
					   var bean = data.pageInfo.list[i];
					   list +='<a href="javaScript:" id="unit_'+bean.cid+'" onclick="_unitclick('+bean.cid+')"  class="list-group-item">'+isStringNUll(bean.unitName)+'</a> '
					}
					$("#unitList").html(list);
					if(data.pageInfo.pages>1){
						$("#panelID").html(data.pageInfo.pageHtml1);
					}
				}else{
					$("#panelID").html("");
				}
			}
		});
		
	});
	//模板
	$("#exportExcelBtn").click(function() {
		window.location.href="exportExcel";
	})
	$("#file1,#file2").click(function(){
		$('#file3').click();
	})
	$("#file3").change(function(){
		var path = $("#file3").val().split('\\');
		var info = path[path.length - 1];
		$("#file2,#file3").val(info);
	})
	//导入
	$("#importBtn").click(function() {
		var unitCid = $("#unitCid").val();
		if(!unitCid){
			layer.msg('请先选择单位');
		}else{
			$("#importUnitCid").val(unitCid);
			$("#importModal").modal();
		}
	})
	//导入提交
	$("#importSubmitBtn").click(function() {
		var unitCid = $("#importUnitCid").val();
		var file = $("#file2").val();
		
		if(!unitCid){
			layer.msg('请先选择单位');
		}else if(!file){
			layer.msg("请选择导入文件");
		}else{
			$("#importForm").submit();
			$("#importcloseBtn").click();
			$("#refreshTable").click();
			//var formData = new FormData($("#importForm")[0]); 
			//window.location.href="importExcel?formData="+formData;
//			$.ajax({
//				type : 'POST',
//				url : $("#contextPath").val() + '/athleteBaseInfo/importExcel',
//				data : formData,
//				dataType : 'html',
//				async: false,  
//		        cache: false,  
//		        contentType: false,  
//		        processData: false,  
//				timeout : 3000,
//				beforeSend : function() {
//					index = layer.load(0, {
//						shade : false
//					}); // 0代表加载的风格，支持0-2
//				},
//				complete : function() {
//					layer.close(index);
//				},
//				error : function() {
//					layer.msg('系统故障', {
//						icon : 2
//					});
//				},
//				success : function(data) {
//					if(data=="success"){
//						layer.msg('导入成功', {
//							icon : 1
//						});
//						$("#importcloseBtn").click();
//						$("#refreshTable").click();
//					}else if(data=="nodata"){
//						layer.msg('没有选择单位或没有选择导入文件', {
//							icon : 2
//						});
//					}else if(data=="noformat"){
//						layer.msg('文件不存在', {
//							icon : 2
//						});
//					}else if(data=="noIoEx"){
//						layer.msg('导入文件扫描失败', {
//							icon : 2
//						});
//					}else{
//						layer.msg('系统故障', {
//							icon : 2
//						});
//					}
//				}
//			})
		}
	})
	//移动
	$("#moveBtn").click(function() {
		var chk_value = [];
		var index = null;
		$('input[name="cid"]:checked').each(function() {
			chk_value.push($(this).val());
		});
		if (chk_value.length <1) {
			layer.msg('请至少选择一条记录');
		}else{
			
			$('#unitModal').modal();
			$("#ahtleteCids").val(chk_value);
			$("#unitBody").load(
					$("#contextPath").val() + '/unitInfo/queryType',{
						"isTeam":"0"
			});
		}
	})
	//移动提交
	$("#submitBtn").click(function(){
		var unitCid = $("#unitLoad").val();
		var chk_value = [];
		chk_value = $("#ahtleteCids").val(); 
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/athleteBaseInfo/moveAthlete',
			data : {
				"unitCid" :unitCid,"ahtleteCids" : chk_value
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
				if(data=="success"){
					layer.msg('移动成功', {
						icon : 2
					});
					$("#closeBtn").click();
					window.location.href=$("#contextPath").val() + '/athleteBaseInfo/index';
				}else if(data=="nodata"){
					layer.msg('没有选择运动员获单位', {
						icon : 2
					});
				}else{
					layer.msg('系统故障', {
						icon : 2
					});
				}
			}
		})
	})
	
	
	// 删除记录信息
	$("#deleteBtn").click(function() {
		var url = $("#contextPath").val() + '/athleteBaseInfo/deleteInfo';
		deleteInfo(url);
	})
	$("#save").click(function(){
		var unitCid = $("#unitCid").val();
		var unitName =$("#unit_"+unitCid).text();
		self.location.href="updateInfo?unitCid="+unitCid+"&unitName="+unitName;
	})
	$("#edit").click(function(){
		var chk_value = [];
		var index = null;
		$('input[name="cid"]:checked').each(function() {
			chk_value.push($(this).val());
		});
		if (chk_value.length != 1) {
			layer.msg('请选择一条记录');
		} else{
			self.location.href="updateInfo?cid="+chk_value[0];
		}
	})
	$("#refreshTable").click(function(){
		
		var info = new Object;
		info.athleteName = $("#athleteName").val();
		
		var unitInfo = new Object;
		unitInfo.cid = $("#unitCid").val();
		info.unitInfo = unitInfo;
		
		var currentPage = $("#currentPage").val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/athleteBaseInfo/queryPage',
			data : {
				"currentPage" :currentPage,jsonString : JSON.stringify(info)
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
					   var athletesTypeStr;
					   if(bean.athletesType==0){
						   athletesTypeStr  = "地方组";
					   }else if(bean.athletesType==1){
						   athletesTypeStr  = "企事业组";
					   }else{
						   athletesTypeStr  = "青少年";
					   }
						list += '<tr>\
							<td><input type="checkbox" onClick="click_input()"  name="cid" value="'
								+ bean.cid
								+ '"></td>'
								+ '<td>'
								+ isStringNUll(bean.athleteName)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(athletesTypeStr)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.alias)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.sexStr)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.unitInfo.unitName)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.nation)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.birthday)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.idCard)
								+ '</td>' + '</tr>'
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
						layer.msg('运动员存在参赛情况，请先删除参赛信息', {
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
function _unitclick(cid){
	$("#unitCid").val(cid);
	$("#refreshTable").click();
	
	$("#unitList a").removeClass("active");
	$("#unit_"+cid).addClass("active");
}

//重写反页
function _pageclick1(pageNo){
	$("#pageNo").val(pageNo); //设置当前页
	$("#refreshUnit").click();  //跳到
}
