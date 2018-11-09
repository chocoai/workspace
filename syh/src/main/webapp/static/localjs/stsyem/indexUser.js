$(function() {
	// 删除记录信息
	$("#deleteBtn").click(function() {
		var url = $("#contextPath").val() + '/sysMuserInfo/deleteInfo';
		deleteInfo(url);
	})

	$("#refreshTable").click(function(){
		var findContent = $("#findContent").val();
		var currentPage = $("#currentPage").val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/sysMuserInfo/queryPage',
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
				var list = "";
				$("#list").html(list);
				$("#pageID").html("");
				$("#noDataPage").addClass("hidden");
				if(data.pageInfo.pages>0){
					for (var i = 0; i < data.pageInfo.list.length; i++) {
					   var bean = data.pageInfo.list[i];
						list += '<tr>\
							<td><input type="checkbox" name="cid" value="'
								+ bean.cid
								+ '"></td>'
								+ '<td>'
								+ isStringNUll(bean.userSname)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.sysUser.userCode)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.petName)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.sysRole.roleName)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.stateStr)
								+ '</td>'
								+ '<td>'
								+ isStringNUll(bean.backup)
								+ '</td>'
								+ '<td>'
								if(bean.state== 0){
									list +=  '<button id="updateByCidSates" type="button" class="btn btn-danger btn-xs" onclick="updateByCidSates('+bean.cid+',-1)" style="margin-right: 5px;">禁用</button >' 
								}else{
									list +=  '<button id="updateByCidSates" type="button" class="btn btn-warning btn-xs" onclick="updateByCidSates('+bean.cid+',0)" style="margin-right: 5px;">激活</button >' 
								}
									list += '</td>' + '</tr>'
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
	// 禁用激活功能
	function updateByCidSates(id,states){
		$.ajax({
			type : 'post',
			url :$("#contextPath").val() + '/sysMuserInfo/updateByCidSates',
			data : {
				"id" : id,"states" :states
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
				console.log("处理结果" + data);
				if (data == "nodata") {
					layer.msg('没有数据要处理', {
						icon : 2
					});
				} else if (data == "failed") {
					layer.msg('失败', {
						icon : 2
					});
				} else if (data == "success") {
					layer.msg('成功', {
						icon : 1
					});
					$("#refreshTable").click();
				}
			}
		})
	}
