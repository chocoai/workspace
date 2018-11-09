//这是成绩基本信息管理的主页面js
$(function(){
	
	var zTree;
	var setting = {
		check : {
			enable : false
		},
		callback: {
			onClick: zTreeOnClick
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
	//分类选择树开始
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
			$.fn.zTree.init($("#projectTree"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("projectTree");
			 var nodes = zTree.getNodes();
			 for (var i = 0; i < nodes.length; i++) { //设置节点展开
                   if(nodes[i].parentId==null ){
                   	zTree.expandNode(nodes[i], true, false, false);
			 		}
	        }
		}
	});
	//树节点敲击事件
	function zTreeOnClick(event, treeId, node){
		var isClass = node.isClass;
		if(isClass=='1'){
			$("#projectCid").val(node.id);
			$("#projectName").val(node.nodeValue);
			$('#currentPage').val("1")
			$("#refreshTable").click();
		}
	}
	
	
	
	
	
	
	$("#projectCid").change(function() {
		$("#queryBtn").click();
	})
	//为查看成绩绑定事件
	$(document).on("click",".viewScores",function(){
		var isTeam=$(this).prev().val();
		var resultType=$(this).prev().prev().val();
		var resultUnit=$(this).prev().prev().prev().val();
		var projectCid=$(this).next().val();//获取项目cid
		var participatId=$(this).next().next().val();//报名id
		var athleteName= $(this).parent().siblings("[name=athleteName]").text();
		var projectName= $(this).parent().siblings("[name=projectName]").text();
		var unitName= $(this).parent().siblings("[name=unitName]").text();
		var medalNum= $(this).parent().siblings("[name=medalNum]").text();
		var scores=$(this).parent().siblings("[name=scores]").text();
		var ranking=$(this).parent().siblings("[name=ranking]").text();
		var medal=$(this).parent().siblings("[name=medal]").text();
		var intrgral=$(this).parent().siblings("[name=viewIntrgral]").text();
		var medalNum=$(this).parent().siblings("[name=viewMedalNum]").text();
		var backup=$(this).parent().siblings("[name=backup]").text();
		$("#viewParticipatId").val(participatId);
		$("#viewProjectCid").val(projectCid);
		$("#viewAthleteName").val(athleteName);
		$("#viewUnitName").val(unitName);
		$("#viewProjectName").val(projectName);
		$("#viewScores").val(scores);
		$("#viewRanking").val(ranking);
		$("#viewMedal").val(medal);
		$("#viewMedalNum").val(medalNum);
		$("#viewIntrgral").val(intrgral);
		$("#viewBackup").val(backup);
		if(resultType==0){  //如果只录入名次则不显示成绩
			$("#showScores").addClass("hidden")
			$("#viewResultTypeVal").val("只录取名次")
		}else {
			$("#showScores").removeClass("hidden")
			if (resultType==1) {
				$("#viewResultTypeVal").val("时间下行(时间越少，成绩越高)")
			}else if (resultType==2) {
				$("#viewResultTypeVal").val("时间上行(时间越长，成绩越高)")
			}else if (resultType==3) {
				$("#viewResultTypeVal").val("数值上行(数值越大，成绩越高)/"+resultUnit)
			}else if (resultType==4) {
				$("#viewResultTypeVal").val("数值下行(数值越小，成绩越高)/"+resultUnit)
			}
		}
		$('#viewAthleteScore').modal();
	})
	
	//为修改成绩绑定事件
	$(document).on("click",".updateScores",function(){
		var isTeam=$(this).prev().prev().val();
		$("#isTeam").val(isTeam);
		var resultType=$(this).prev().prev().prev().val();
		var resultUnit=$(this).prev().prev().prev().prev().val();
		var projectCid=$(this).next().val();//获取项目cid
		$("#allotProjectCidModal").val(projectCid);
		var participatId=$(this).next().next().val();//报名id
		var athleteName= $(this).parent().siblings("[name=athleteName]").text();
		var projectName= $(this).parent().siblings("[name=projectName]").text();
		var unitName= $(this).parent().siblings("[name=unitName]").text();
		var scores=$(this).parent().siblings("[name=scores]").text();
		var ranking=$(this).parent().siblings("[name=ranking]").text();
		var intrgral=$(this).parent().siblings("[name=intrgral]").text();
		var backup=$(this).parent().siblings("[name=backup]").text();
		var medal=$(this).parent().siblings("[name=medal]").text();
		var medalNum= $(this).parent().siblings("[name=medalNum]").text();
		if (medal=="无") {
			$("#updateMedal").val("0");
			$("#updateMedalNum").val(0);
		}else if (medal=="金牌") {
			$("#updateMedal").val('1');
			$("#updateMedalNum").val(medalNum);
		}else if (medal=="银牌") {
			$("#updateMedal").val('2');
			$("#updateMedalNum").val(medalNum);
		}else if (medal=="铜牌") {
			$("#updateMedal").val('3');
			$("#updateMedalNum").val(medalNum);
		}
		$("#updateParticipatId").val(participatId);
		$("#updateProjectCid").val(projectCid);
		$("#updateAthleteName").val(athleteName);
		$("#updateUnitName").val(unitName);
		$("#updateProjectName").val(projectName);
		$("#updateScores").val(scores);
		$("#updateRanking").val(ranking);
		$("#updateIntrgral").val(intrgral);
		$("#updateBackup").val(backup);
		$("#updateResultType").val(resultType)
		if(resultType==0){
			$("#showUpdateScores").addClass("hidden")
			$("#updateResultTypeVal").val("只录取名次")
		}else {
			$("#showUpdateScores").removeClass("hidden")
			if (resultType==1) {
				$("#updateResultTypeVal").val("时间下行(时间越少，成绩越高)")
			}else if (resultType==2) {
				$("#updateResultTypeVal").val("时间上行(时间越长，成绩越高)")
			}else if (resultType==3) {
				$("#updateResultTypeVal").val("数值上行(数值越大，成绩越高)/"+resultUnit)
			}else if (resultType==4) {
				$("#updateResultTypeVal").val("数值下行(数值越小，成绩越高)/"+resultUnit)
			}
		}
		$('#updateAthleteScore').modal();
	})
	
	//查询刷新功能
	$("#refreshTable").click(function(){
		var pCid = $("#projectCid").val();
		if(!pCid) return;
		var currentPage = $('#currentPage').val();
		if(!currentPage){
			currentPage=1;
		}
		var queryCondition={
			"currentPage":currentPage,
			"pCid":pCid,
			"queryParam":$("#queryParam").val()
		}
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/inputScore/queryPageReport',
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
				$("#pageID").html("");
				$("#pageID").html("");
				$("#noDataPage").removeClass("hidden");
				if(data.pageInfo.pages>0){
					$("#noDataPage").addClass("hidden");
					for (var i = 0; i < data.pageInfo.list.length; i++) {
					    var bean = data.pageInfo.list[i];
						var type="",athleteName="",unitName="",teamCid="",medal="";
						if (bean.projectManager.isTeam==0) {
							type="个人赛";
							athleteName=bean.participatNames;
							unitName=bean.unitNames;
							teamCid=bean.cid;
						}else{
							type="团队赛";
							athleteName=bean.participatNames;
							unitName+=bean.unitNames;
							teamCid=bean.cid;
						}
						if (bean.scoreInfo.medal==0) {
							medal="金牌";
						}else if (bean.scoreInfo.medal==1) {
							medal="银牌";	
						}else if (bean.scoreInfo.medal==2) {
							medal="铜牌";
						}else {
							medal="无";
						}
						list += '<tr>' + 
						'<td name="projectName">'
							+ bean.projectManager.projectName
							+ '</td>'
							+ '<td>'
							+ type
							+ '</td>'
							+ '<td name="athleteName">'
							+ athleteName
							+ '</td>'
							+ '<td name="unitName">'
							+ unitName
							+ '</td>'
							+ '<td name="scores">'
							+ isStringNUll(bean.scoreInfo.scores)
							+ '</td>'
							+ '<td name="ranking">'
							+ isStringNUll(bean.scoreInfo.ranking)
							+ '</td>'
							+ '<td name="intrgral">'
							+ isStringNUll(bean.scoreInfo.intrgral)
							+ '</td>'
							+ '<td name="medal">'
							+ medal
							+ '</td>'
							+ '<td name="medalNum" class="hidden">'
							+ isStringNUll(bean.scoreInfo.medalNum)
							+ '</td>'
							+ '<td name="viewIntrgral" class="hidden">'
							+ isStringNUll(bean.scoreInfo.viewIntrgral)
							+ '</td>'
							+ '<td name="viewMedalNum" class="hidden">'
							+ isStringNUll(bean.scoreInfo.viewMedalNum)
							+ '</td>'
							+ '<td name="backup" class="hidden">'
							+ isStringNUll(bean.backup)
							+ '</td>'
							+ '<td><input type="hidden" value="'
							+bean.projectManager.resultUnit
							+'"/><input type="hidden" value="'
							+bean.projectManager.resultType
							+'"/><input type="hidden" value="'
							+bean.projectManager.isTeam
							+'"/><a role="button"  href="javascript:" \
							class="btn btn-success btn-xs viewScores btn-xs ">查看</a> <a role="button"  href="javascript:" \
							class="btn btn-warning btn-xs updateScores btn-xs">修改成绩</a><input type="hidden" value="'
							+bean.pid
							+'"/><input type="hidden" value="'+
							bean.cid
							+'"/></td>'
							+ '</tr>' ;
					}
					$("#list").html(list);
					if(data.pageInfo.pages>1){
						$("#pageID").html(data.pageInfo.pageHtml);
					}
				}else{
					$("#list").html('<tr></tr>');
				}
			}
		});
	})
	
	$("#submitBtn").click(function(){
		if(validate()){
			var submitUrl=$("#contextPath").val() + '/scoreBaseInfo/doUpdateInfo';
			var participatDetail=new Object;
			var projectManager=new Object;
			var athleteBaseInfo=new Object;
			//获取参赛队名
			athleteBaseInfo.athleteName=$("#updateAthleteName").val();
			participatDetail.athleteBaseInfo=athleteBaseInfo;
			//获取成绩类型
			projectManager.resultType=$("#updateResultType").val();
			//获取成绩主键
			participatDetail.participatId=$("#updateParticipatId").val();
		//	participatDetail.projectCid=$("#updateProjectCid").val();
			//获取分数
			participatDetail.scores=$("#updateScores").val();
			//获取名次
			participatDetail.ranking=$("#updateRanking").val();
			//获取备注
			participatDetail.backup=$("#updateBackup").val();
			//获取奖牌
			if ($("#updateMedal").val()==1) { //选择金牌
				participatDetail.medal=0;
				participatDetail.medalNum=$("#updateMedalNum").val();
			}else if ($("#updateMedal").val()==2) {//银牌
				participatDetail.medal=1;
				participatDetail.medalNum=$("#updateMedalNum").val();
			}else if ($("#updateMedal").val()==3) {  //选择铜牌
				participatDetail.medal=2;
				participatDetail.medalNum=$("#updateMedalNum").val();
			}
			//获取积分
			participatDetail.intrgral=$("#updateIntrgral").val();
			participatDetail.judgeRecord=0;
			participatDetail.judgeLevel=0;
			$.ajax({
				type : 'post',
				url : $("#contextPath").val() + '/inputScore/isRanking',
				data : {
					participatId:participatDetail.participatId,rangking:participatDetail.ranking,pCid:$("#projectCid").val()
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
					if(data.states&&data.states=='rankingInput'){
						layer.confirm("该名次已被录入是否确定录入?", {
							title: '提示信息',
							btn : [ '确定', '取消' ],
							icon: 3
						// 按钮
						},function(){
							inputScore(projectManager,participatDetail, submitUrl)	
						})
					}else{
						inputScore(projectManager,participatDetail, submitUrl)	
					}
				}
			})
		}
	})
	
})
//验证
function validate(){
	if ($("#updateResultType").val()!=0&&($("#updateScores").val()==null||$("#updateScores").val()=="")) {//如果该项目需要录入成绩的时候
		layer.tips('请输入成绩', '#updateScores');
		$("#updateScores").focus();
	}else if ($("#updateRanking").val()==null||$("#updateRanking").val()=="") {
		layer.tips('请输入名次', '#updateRanking');
		$("#updateRanking").focus();
	}else if (!IsIntegerPositive($("#updateRanking").val())) {
		layer.tips('名次输入有误,请重新输入', '#updateRanking');
		$("#updateRanking").focus();
	}else if (!IsIntegerDecimal($("#updateIntrgral").val())) {
		layer.tips('积分输入有误,请重新输入', '#updateIntrgral');
		$("#updateIntrgral").focus();
	}else{
		return true
	}
}
//提交
function inputScore(projectManager,participatDetail, submitUrl){
	if(projectManager.resultType!=0){   //只有录入成绩时，才会成绩校验
		$.ajax({
			type : 'post',
			url : $("#contextPath").val() + '/inputScore/doScoreJudge',
			data : {
				jsonString : JSON.stringify(participatDetail)
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
				if(data.states&&data.states=='participatId'){
					layer.msg('请重新选择选择修改的参赛信息', {
						icon : 2
					});
					return;
				}
				participatDetail.judgeRecord=data.recordJudge;
				participatDetail.judgeLevel=data.levelJudge;
				if(participatDetail.judgeRecord==1){
					layer.confirm("该成绩已破纪录，是否刷新记录库？", {
						title: '提示信息',
						btn : [ '确定', '取消' ],
						icon: 3
					// 按钮
					},function(){//当要刷新记录库的时候
						ajaxSubmitModal(participatDetail, submitUrl);  //提交成绩
					},function(){//只录入成绩不刷新记录库
						participatDetail.judgeRecord=0;
						ajaxSubmitModal(participatDetail, submitUrl);  //提交成绩
					})
				}else{
					ajaxSubmitModal(participatDetail, submitUrl);
				}
			}
		})
	}else{
		ajaxSubmitModal(participatDetail, submitUrl);
	}
}