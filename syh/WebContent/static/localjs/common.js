//通用功能
$(function(){
	//取消功能
	$("#cancleBtn").click(function(){  //取消按钮
		layer.confirm("确定取消吗？",{
			title:'提示信息',
			btn : [ '确定', '取消' ], // 按钮
			icon: 3
		},function(){
			self.location.href="index"
		})
		
	})
	//回车事件绑定
	document.onkeydown=function(event){
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if(e && e.keyCode==13){ 
			var inputPage = $("#currentPage").val();
			var totalPage = $("#totalPage").val();
			if (inputPage&&!IsIntegerPositive(inputPage)) {
				layer.tips("您的输入有误，请重新输入","#currentPage")
			} else if (inputPage&&inputPage > $("#totalPage").text()) {
				layer.tips("您输入的页数超出总页数，请重新输入","#currentPage")
			}else{
				$('#queryBtn').click();
			}
		}
	}; 
	//搜索时翻页
	$(".input-group,.form-group input[type='text']").change(function(){
		$("#currentPage").val(1);
	});
	
	// 查询功能
	$("#queryBtn").click(function() {
		if($("#currentPage").val()==""){  
			$("#currentPage").val(1);
		}
		$(".input-group,.form-group input[type='text']").change();
		$("#refreshTable").click();
	})
	
	$("#queryBtn").click();  //每次进入页面后执行查询实现ajax局部刷新的效果
	
	//重置查询条件
	$("#refreshBtn").click(function(){
		$("#projectName").val("");
		$("#projectCid").val("");
		$("#queryUnitName").val("");
		$("#queryAthleteName").val("");
		$("#currentPage").val(1);
		currentPage=parseInt($("#currentPage").val());
		$("#queryBtn").click();
	})
	
	//更新修改功能
	$("#updateInfo").click(function(){
		var chk_value = [];
		var index = null;
		$('input[name="cid"]:checked').each(function() {
			chk_value.push($(this).val());
		});
		if (chk_value.length != 1) {
			layer.msg('请选择一条记录');
		} else{
			self.location.href="updateInfo?cid="+chk_value[0]
		}
	})
	
	// 全选功能
	$("#chooseAll").click(function() {
		if (this.checked) {
			$("#list :checkbox").prop("checked", true);
		} else {
			$("#list :checkbox").prop("checked", false);
		}
	});
	/**
	 * 项目树结构开始
	 */
	var zTree;
	var setting = {
		check : {
			enable : false
		},
		data : {
			simpleData : {
				enable : true, // 设置是否使用简单数据模式(Array)
				idKey : "id", // 设置节点唯一标识属性名称
				pIdKey : "parentId" // 设置父节点唯一标识属性名称
			},
			key : {
				name : "nodeValue",// zTree 节点数据保存节点名称的属性名称
			}
		},
		edit : {
			enable : false
		},
		view : {
			showIcon : false
		}
	};
	
	$("#projectName").focus(function() {
		$('#myModal').modal();
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/common/getTree',
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
				var zNodes = data;
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				zTree = $.fn.zTree.getZTreeObj("treeDemo");
			}
		});
	});
	
	$("#selectProject").click(function(event,treeId,treeNode){
		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
        nodes=treeObj.getSelectedNodes(true);
		var isClass = nodes[0].isClass;
		if(isClass=='1' || isClass==1){
			$("#projectName").val(nodes[0].nodeValue);
			$("#projectIsTeam").val(nodes[0].isTeam);
			$("#projectCid").val(nodes[0].id);
			$("#projectCid").change();
			$("#closeProject").click();
			$("#projectCid").click();
			$("#projectIsTeam").click();
		}else{
			layer.msg('请选择一个比赛项目');
		}
	});
	/**
	 * 项目树结构结束
	 */
	
	/**
	 * 分类选择树开始
	 */
	$("#className").focus(function() {
		$('#classModal').modal();
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/classManager/queryClassToTree',
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
				var zNodes = data;
				$.fn.zTree.init($("#tree"), setting, zNodes);
				zTree = $.fn.zTree.getZTreeObj("tree");
			}
		});
	});
	
	$("#selectClass").click(function(event,treeId,treeNode){
		var treeObj=$.fn.zTree.getZTreeObj("tree"),
        nodes=treeObj.getSelectedNodes(true);
		$("#className").val(nodes[0].nodeValue);
		$("#classCid").val(nodes[0].id);
		$("#PclassType").val(nodes[0].classType);
		$("#closeClass").click();
		$("#classCid").change();
	});
	//分类选择树结束
	
	/**
	 * 分类选择树开始
	 */
	$("#classNameNode").focus(function() {
		$('#myNodeModal').modal();
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/classManager/queryClassToTree',
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
				var zNodes = data;
				$.fn.zTree.init($("#nodeTree"), setting, zNodes);
				zTree = $.fn.zTree.getZTreeObj("nodeTree");
			}
		});
	});
	
	$("#selectNOdeClass").click(function(event,treeId,treeNode){
		var treeObj=$.fn.zTree.getZTreeObj("nodeTree"),
        nodes=treeObj.getSelectedNodes(true);
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/classManager/queryIsNode',
			data:{
				"classCidNode" :nodes[0].id
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
				if(data.states == "nodata"){
					layer.msg('请选择一个项目分类');
				}else if(data.states == "failed"){
					layer.msg('请选择一个不含项目分类的分类');
				}else if(data.states == "success"){
					$("#classNameNode").val(nodes[0].nodeValue);
					$("#pCid").val(nodes[0].id);
					$("#closeNOdeClass").click();
					$("#classCidNode").change();
					//验证项目是否重复
					var name = $("#name").val();
					var pCid = $("#pCid").val();
					if(name&&pCid){
						isProjectManagerName(name,nodes[0].id,"classNameNode")
					}
				}
			}
		});
	});
	//分类选择树结束
	
	/**
	 * 分页功能开始
	 */
	var currentPage = parseInt($("#currentPage").val());
	//下一页功能
	$("#nextPage").click(function() { 
		if (currentPage<parseInt($("#totalPage").text())) {  //如果当前页数不是最后一页则点击进入下一页
			currentPage+=1;
			$("#currentPage").val(currentPage);
			$("#refreshTable").click();
		}
		if(currentPage>1){			//如果当前页数大于1，则启用上一页及首页
			$("#prePageIco").removeClass("disabled");
			$("#firstPageIco").removeClass("disabled");
		}
		if (currentPage == parseInt($("#totalPage").text())) {		//如果当前页数最最后一页，则将下一页级最后一页取消
			$("#nextPageIco").addClass("disabled")
			$("#lastPageIco").addClass("disabled")
		}
	})
	//分配奖励提交
/*	$("#submitAllot").click(function() {
		var unitCids=document.getElementsByName("allotUnitCid");
		var teamparticipats=new Array;  //参赛单位的列表
		var totalIntrgral=0,totalMedalCount=0;  //分配好的金牌总数、积分总数
		for (var i = 0; i < unitCids.length; i++) {
			var $unitCid=$(unitCids[i]);  //单位的cid
			var teamparticipat=new Object;
			teamparticipat.unitCid=$unitCid.val();
			teamparticipat.teamCid=$unitCid.prev().val()//团队的cid
			teamparticipat.projectCid=$("#allotProjectCidModal").val();//项目cid;
			teamparticipat.gold="0";
			teamparticipat.silver="0";
			teamparticipat.copper="0";
			if($("#allotMedal").text()=="金牌"){
				teamparticipat.gold=$unitCid.next().find("input").val();
			}else if ($("#allotMedal").text()=="银牌") {
				teamparticipat.silver=$unitCid.next().find("input").val();
			}else if($("#allotMedal").text()=="铜牌"){
				teamparticipat.copper=$unitCid.next().find("input").val();
			}
			totalMedalCount+=parseFloat($unitCid.next().find("input").val());
			teamparticipat.intrgral=$unitCid.next().next().find("input").val();
			totalIntrgral+=parseFloat(teamparticipat.intrgral);
			teamparticipats.push(teamparticipat);
		}
		if (totalIntrgral!=$("#allotIntrgral").text()) {  //如果积分分配不正确
			layer.msg('输入的积分与待分配积分不一致', {
				icon : 2
			});
		}else if (totalMedalCount!=$("#allotMedalCount").text()) {  //奖牌分配不正确
			layer.msg('输入的奖牌数量与待分配奖牌数量不一致', {
				icon : 2
			});
		}else{
			$.ajax({
				type:"post",
				url: $("#contextPath").val() + '/inputScore/doAllotReward',
				data:{
					jsonString:JSON.stringify(teamparticipats)
				},
				dataType:'html',
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
				success : function(result) {
					if(result=="success"){
						layer.msg('成功', {
							icon : 1
						});
					}else{
						layer.msg('系统故障', {
							icon : 2
						});
					}
					$("[name=closeModal]").click();
					if($("#backIndex").val()==1){
						self.location.href = "index"
					}
				}
			})
		}
	})*/
	$("#submitAllot").click(function() {
		var unitCids=document.getElementsByName("allotUnitCid");
		var infojsonArray=new Array;  //参赛单位的列表
		var totalIntrgral=0,totalMedalCount=0;  //分配好的金牌总数、积分总数
		for (var i = 0; i < unitCids.length; i++) {
			var $unitCid=$(unitCids[i]);  //单位的cid
			var infojson=new Object;
			infojson.unitCid=$unitCid.val();
			infojson.participatInfoId=$unitCid.prev().val()//报名信息id
		//	teamparticipat.projectCid=$("#allotProjectCidModal").val();//项目cid;
			infojson.gold="0";
			infojson.silver="0";
			infojson.copper="0";
			if($("#allotMedal").text()=="金牌"){
				infojson.gold=$unitCid.next().find("input").val();
				if(infojson.gold=='0'){  //特殊处理，后台发现是0会认为没有奖牌
					infojson.gold = '0.0';
				}
			}else if ($("#allotMedal").text()=="银牌") {
				infojson.silver=$unitCid.next().find("input").val();
				if(infojson.silver=='0'){  //特殊处理，后台发现是0会认为没有奖牌
					infojson.silver = '0.0';
				}
			}else if($("#allotMedal").text()=="铜牌"){
				infojson.copper=$unitCid.next().find("input").val();
				if(infojson.copper=='0'){  //特殊处理，后台发现是0会认为没有奖牌
					infojson.copper = '0.0';
				}
			}
			totalMedalCount+=parseFloat($unitCid.next().find("input").val());
			infojson.intrgral=$unitCid.next().next().find("input").val();
			totalIntrgral+=parseFloat(infojson.intrgral);
			infojsonArray.push(infojson);
		}
		if (totalIntrgral!=$("#allotIntrgral").text()) {  //如果积分分配不正确
			layer.msg('输入的积分与待分配积分不一致', {
				icon : 2
			});
		}else if (totalMedalCount!=$("#allotMedalCount").text()) {  //奖牌分配不正确
			layer.msg('输入的奖牌数量与待分配奖牌数量不一致', {
				icon : 2
			});
		}else{
			$.ajax({
				type:"post",
				url: $("#contextPath").val() + '/inputScore/doAllotReward',
				data:{
					jsonString:JSON.stringify(infojsonArray)
				},
				dataType:'html',
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
				success : function(result) {
					if(result=="success"){
						layer.msg('成功', {
							icon : 1
						});
					}else{
						layer.msg('系统故障', {
							icon : 2
						});
					}
					$("[name=closeModal]").click();
					if($("#backIndex").val()==1){
						self.location.href = "index"
					}
				}
			})
		}
	})
})
//重写反页
function _pageclick(currentPage){
	$("#currentPage").val(currentPage); //设置当前页
	$("#refreshTable").click();  //跳到
}
//反选
function click_input(){
    var chsub = $("#list :checkbox").length; 
    var checkedsub = $("#list :checkbox:checked").length;
    if (checkedsub == chsub) {  
        $("#chooseAll").prop("checked", true); 
    }else{
    	$("#chooseAll").prop("checked", false); 
    } 
}
//获得简称自动填充
function getPname(obj1,obj2){
	var o=document.getElementById(obj1);
	var b=document.getElementById(obj2);
	o.onkeyup=function(){
		$.ajax({
            url: $("#contextPath").val() + '/common/getPname',
            data: {"name" : o.value},
            async: false, //关键，设置为同步
            type: 'POST',
            dataType : 'html',
            timeout : 5000,
            success: function (data) {
            	b.value=data;
            },
        });
	}
}

function loaddict(param,id){
	$.ajax({
		url: $("#contextPath").val() + '/sysDict/queryType',
		data: param,
		async: false, //关键，设置为同步
		type: 'POST',
		dataType : 'html',
		timeout : 5000,
		success: function (data) {
			$("#"+id).html(data);
		},
		error : function() {
			layer.msg('系统故障', {
				icon : 2
			});
		},
	});
}
