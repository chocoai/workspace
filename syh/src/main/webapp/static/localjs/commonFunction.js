/**
 * 全局ajax
 */
var Error = function () {
    return {
        // 初始化各个函数及对象
        init: function () {
        },
        // 显示或者记录错误
        displayError: function(data) {
        	
            if (data.states&&data.states==405) {//登录超时或没有登录
            	layer.msg(data.message,function(){
            		self.location.href = $("#contextPath").val() + "/login/login";
            	});
            } else if (data.states&&data.states==404) {// 无权限
            	layer.msg(data.message, function(){});
            	return;
            }
            console.log(data.message);
        }
    };
}();

$(document).ready(function(){ 
    Error.init();
});


//ajax提交新增及修改
function ajaxSubmit(Object, url) {
	layer.confirm("确定提交吗？", {
		title: '提示信息',
		btn : [ '确定', '取消' ],
		icon: 3
	// 按钮
	}, function() {
		$.ajax({
			type : 'post',
			url : url,
			data : {
				jsonString : JSON.stringify(Object)
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
					self.location.href = "index"
				}
			}
		})
	})
}

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
			title: '提示信息',
			btn : [ '确定', '取消' ],
			icon: 3
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
							time : 1000
						});
						$("#queryBtn").click();
					} else if (data== "failed") {
						layer.msg('删除失败', {
							icon : 2,
							time : 1000
						});
					}else if(data=="nodata"){
						layer.msg('没有数据要处理', {
							icon : 2,
							time : 1000
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

function allotReward(Object){
	if($("#isTeam").val()==1){//当该项比赛为团队赛时
		$.ajax({
			type:"post",
			data:{
				participatId:Object.participatId
			},
			url:$("#contextPath").val() + '/inputScore/judgeAllotReward',
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
				if (data.result== "groupUnit") {//当为组合队时
					$("#allotMedal").text("");
					$("#allotMedalCount").text("");
					$("#allotIntrgral").text("");
					var medal = data.scoreInfo.medal;
					var medalNum = data.scoreInfo.medalNum;
					if (medal=='0') {
						$("#allotMedal").text("金牌");
						$("#allotMedalCount").text(medalNum);
					}else if(medal=='1'){
						$("#allotMedal").text("银牌");
						$("#allotMedalCount").text(medalNum);
					}else if(medal=='2'){
						$("#allotMedal").text("铜牌");
						$("#allotMedalCount").text(medalNum);
					}else {
						$("#allotMedal").text("")
						$("#allotMedalCount").text(0);
					}
					$("#allotIntrgral").text(data.scoreInfo.intrgral);
					var ss=""
					$("#judgelist").html(ss);
					for (var i = 0; i < data.units.length; i++) {
						ss+='<tr>\
							<td>'+data.units[i].unitName+'</td>\
							<input type="hidden" value="'+data.scoreInfo.participatId+'"/>\
							<input type="hidden" name="allotUnitCid" value="'+data.units[i].unitCid+'"/>\
							<td><input type="text" value='+ data.units[i].medalNum+'></td>\
							<td><input type="text" value='+data.units[i].intrgral+'></td>\
						</tr>'
					}
					$("#judgelist").html(ss);
					$("#allotReward").modal()
				} 
			}
		})
	}
}

//处理模态窗口的AJAX提交事件(录成绩用)
function ajaxSubmitModal(Object, url) {
	layer.confirm("确定提交吗？", {
		title: '提示信息',
		btn : [ '确定', '取消' ],
		icon: 3
	// 按钮
	}, function() {
		$.ajax({
			type : 'post',
			url : url,
			data : {
				jsonString : JSON.stringify(Object)
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
					$("[name=closeModal]").click();
					allotReward(Object);  //如果是团队赛的话，对团队进行验证，若为组合队伍，则对奖励进行分配
					$("#refreshTable").click();
				}
			}
		})
	})
}


//处理模态窗口的AJAX提交事件
/*function ajaxSubmitModal(Object, url) {
	layer.confirm("确定提交吗？", {
		title: '提示信息',
		btn : [ '确定', '取消' ],
		icon: 3
	// 按钮
	}, function() {
		$.ajax({
			type : 'post',
			url : url,
			data : {
				jsonString : JSON.stringify(Object)
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
					$("[name=closeModal]").click();
					$("#refreshTable").click();
				}
			}
		})
	})
}*/

