//奖惩主页面的js
$(function() {
	// 删除记录信息
	$("#deleleBtn").click(function() {
		var url = $("#contextPath").val() + '/pubRewInfo/deleteInfo';
		deleteInfo(url);
	})
	
//	$("#projectCid").change(function() {
//		$("#queryBtn").click()
//	})

	$("#refreshTable").click(function(){
		var unitName = $("#unitNameId").val();
		var projectName = $("#projectNameId").val();
		var currentPage = $("#currentPage").val();
		if(!currentPage){
			currentPage=1;
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/pubRewInfo/queryPage',
			data : {
				currentPage : currentPage,
				projectName : projectName,
				unitName : unitName
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
				var pubRewInfoList = "";
				$("#list").html(pubRewInfoList);
				$("#pageID").html("");
				$("#noDataPage").addClass("hidden");
				if(data.pageInfo.pages>0){
					for (var i = 0; i < data.pageInfo.list.length; i++) {
					    var bean = data.pageInfo.list[i];
						var infoType=bean.infoType==0?"奖励":"惩罚";
						var medalType="";
						if(bean.medalType==0){
							medalType="金牌"
						}else if(bean.medalType==1){
							medalType="银牌"
						}else {
							medalType="铜牌"
						}
						if (bean.medalCount==0) {
							medalType="无"
						}
						pubRewInfoList += '<tr>\
							<td><input onClick="click_input()" type="checkbox" name="cid" value="'+ bean.cid+ '"></td>'
								+ '<td>'+ isStringNUll(bean.projectManager.projectName)+ '</td>'
								+ '<td>'+ isStringNUll(bean.unitInfo.unitName)+ '</td>'
								+ '<td>'+ isStringNUll(infoType)+ '</td>'
								+ '<td>'+ isStringNUll(medalType)+ '</td>'
								+ '<td>'+ isStringNUll(bean.medalCount)+ '</td>'
								+ '<td>'+ isStringNUll(bean.intrgralCount)+ '</td>'
								+ '<td>'+ isStringNUll(bean.reason)+ '</td>'
								+ '<td>'+ isStringNUll(bean.backup)+ '</td>' 
								+ '</tr>'
					}
					$("#list").html(pubRewInfoList);
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