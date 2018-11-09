//这是成绩录入管理的主页面js
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
			Error.displayError(data); 
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
	
	
	
	$('#changeTeam').on('hide.bs.modal', function () {
		$("#queryBtn").click()
	})
	
	$(document).on("focus","[name='unitName']",function(){
		var baiduBsSuggest = $(this).bsSuggest({
			allowNoKeyword : false,
			getDataMethod : "url",
	        showHeader:false,
	        effectiveFields: ["unitName"],
			idField:'cid',
			keyField:'unitName',
			url :$("#contextPath").val() + "/pubRewInfo/getUnit?str=",
			processData : function(json) {
				var i, len=0, data = {
					value : []
				};
				if (!json || !json || json.length === 0) {
					return false
				}
				len = json.length;
				jsonStr = "{'value':[";
				for (i = 0; i < len; i++) {
					data.value.push({
						cid:json[i].cid,
						unitName : json[i].unitName
					})
				}
				return data
			}
		}).on('onSetSelectValue', function (e, keyword, data) {
			$(this).parent().siblings().find("[name=unitCid]").val(keyword.id);
	    });
	})
	
	$("#projectCid").change(function() {
		$("#queryBtn").click();
	})  
	
	/**
	 * 修改按钮绑定事件
	 */
	$(document).on("click",".editAthlete",function(){
		if($(this).parent().parent().siblings().find(".editModel").length>0){
			layer.msg("请先保存上一修改",{
				icon:2
			})
		}else{
			oldAthleteName=$(this).parent().siblings().find("[name=athleteName]").val();
			oldUnitCid=$(this).next().next().val();
			oldAthleteCid=$(this).next().val();
			$(this).text("保存");
			$(this).removeClass("btn-warning")
			$(this).addClass("btn-success")
			$(this).parent().siblings().find("[name=athleteName]").removeAttr("readOnly")
			$(this).parent().siblings().find("[name=unitName]").removeAttr("readOnly")
			$(this).removeClass("editAthlete")
			$(this).addClass("editModel")
		}
	})
	
	/**
	 * 保存按钮绑定时间
	 */
	$(document).on("click",".editModel",function(){
		var newUnitCid=$(this).next().next().val();
		var newAthleteName=$(this).parent().siblings().find("[name=athleteName]").val();
		$(this).text("修改");
		$(this).removeClass("editModel");
		$(this).addClass("editAthlete");
		$(this).removeClass("btn-success");
		$(this).addClass("btn-warning");
		$(this).parent().siblings().find("[name=athleteName]").attr("readonly","readonly");
	    $(this).parent().siblings().find("[name=unitName]").attr("readonly","readonly");
	    if(!(oldUnitCid==newUnitCid&&oldAthleteName==newAthleteName)){  //进行了修改
	    	//var teamCid=$(this).prev().val();
	    	var teamCid=$("#changeTeamParticipatInfoId").val();//获取报名id
	    	var projectCid=$("#changeTeamProjectCid").val();
	    	$.ajax({
	    		type:"post",
				url: $("#contextPath").val() + '/inputScore/updateTeamAthlete',
				data :{
					teamCid:teamCid,
					projectCid:projectCid,
					oldAthleteCid:oldAthleteCid,
					oldUnitCid:oldUnitCid,
					newAthleteName:newAthleteName,
					newUnitCid:newUnitCid
				},
				dataType : "text",
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
					if(result=="failed"){
						layer.msg("保存失败",{
							icon:2
						})
					}else if(result=="success"){
						layer.msg("成功",{
							icon:1
						})
					}
				}
	    	})
	    }
	});
	
	//为变更按钮绑定事件
	$(document).on("click",".updateAthlete",function(){
		$("#changeTeamProjectCid").val($(this).siblings("[name=projectCid]").val());
		$("#changeTeamParticipatInfoId").val($(this).siblings("[name=teamCid]").val());
		var teamCid=$(this).siblings("[name=teamCid]").val();//获取团队Cid
		oldUnitCid="";
		oldAthleteName="";
		oldAthleteCid="";
		$.ajax({
			type:"post",
			url: $("#contextPath").val() + '/inputScore/getAthletes',
			data :{teamCid:teamCid},
			dataType : "json",
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
				if (data.list.length>0) {
					var ss="";
					for (var i = 0; i < data.list.length; i++) {
						var bean = data.list[i];
						ss+='<div class="form-group">\
							<div class="col-md-3">\
								<input type="hidden" value="'+bean.teamCid+'">\
								<button type="button" class="btn btn-warning myformbtn pull-right editAthlete">修改</button>\
								<input type="hidden" value="'+bean.athleteBaseInfo.cid+'">\
								<input type="hidden" name="unitCid" value="'+bean.athleteBaseInfo.unitCid+'">\
							</div>\
							<div class="col-md-4">\
								<input readOnly type="text" class="form-control" name="athleteName" \
									placeholder="请输入运动员姓名" value="'+bean.athleteBaseInfo.athleteName+'">\
							</div>\
							<div class="input-group col-md-4 ">\
								<input readOnly type="text" class="form-control" name="unitName"\
									placeholder="请输入单位名称" value="'+bean.athleteBaseInfo.unitInfo.unitName+'">\
								<div>\
									<ul  class="dropdown-menu dropdown-menu-right" role="menu">\
									</ul>\
								</div>\
							</div>\
						</div>'
					}
					$("#changeTeamAthlete").html(ss);
					$("#changeTeam").modal();
				}
			}
		})
	})
	
	//为录入成绩按钮绑定事件
	$(document).on("click",".inputScore",function(){
		var isTeam=$(this).siblings("[name=isTeam]").val();
		var projectName= $(this).siblings("[name=projectName]").val();
		var resultType=$(this).siblings("[name=resultType]").val();
		var resultUnit=$(this).siblings("[name=resultUnit]").val();
		var participatInfoId=$(this).siblings("[name=teamCid]").val();//获取报名信息id
//		var athleteCid=$(this).siblings("[name=athleteCid]").val();//获取运动员cid
		var athleteName= $(this).parent().siblings("[name=athleteName]").text();
		var unitName= $(this).parent().siblings("[name=unitName]").text();
		$("#isTeam").val(isTeam);    
//		$("#athleteCidModal").val(athleteCid); 
/*		$("#allotProjectCidModal").val(projectCid); 	*/	
		$("#athleteNameModal").val(athleteName); 
		$("#unitNameModal").val(unitName);   
		$("#projectNameModal").val(projectName); 
		$("#participatInfoIdModal").val(participatInfoId); 
		$("#resultTypeModal2").val(resultType); 
		
		$("#backup").val("");
		if(resultType==0){
			$("#resultType").addClass("hidden")
			$("#resultTypeModal").val("只录取名次")
		}else {
			$("#resultType").removeClass("hidden")
			if (resultType==1) {
				$("#resultTypeModal").val("时间下行(时间越少，成绩越高)")
			}else if (resultType==2) {
				$("#resultTypeModal").val("时间上行(时间越长，成绩越高)")
			}else if (resultType==3) {
				$("#resultTypeModal").val("数值上行(数值越大，成绩越高)/"+resultUnit)
			}else if (resultType==4) {
				$("#resultTypeModal").val("数值下行(数值越小，成绩越高)/"+resultUnit)
			}
		}
		$("#scores").val("");
		$("#ranking").val("");
		$("#scores").focus();
		$('#inputAthleteScore').modal();
	})
	
	//提交成绩
/*	$("#submitBtn").click(function(){
		if(validate()){//验证成功后执行
			var submitUrl=$("#contextPath").val() + '/inputScore/doUpdateInfo';
			var athleteParticipat=new Object;  //创建一条参赛记录
			var projectManager=new Object;    //创建项目
			var athleteBaseInfo=new Object;   //创建运动员基本信息
			athleteBaseInfo.athleteName=$("#athleteNameModal").val();
			athleteParticipat.athleteBaseInfo=athleteBaseInfo;
			projectManager.resultType=$("#resultTypeModal2").val();
			athleteParticipat.athleteCid=$("#athleteCidModal").val();
			athleteParticipat.projectCid=$("#projectCidModal").val();
			athleteParticipat.scores=$("#scores").val();
			athleteParticipat.ranking=$("#ranking").val();
			athleteParticipat.backup=$("#backup").val();
			athleteParticipat.judgeRecord=0;
			athleteParticipat.judgeLevel=0;
			if(projectManager.resultType!=0){   //只有录入成绩时，才会成绩校验
				$.ajax({
					type : 'post',
					url : $("#contextPath").val() + '/inputScore/doScoreJudge',
					data : {
						jsonString : JSON.stringify(athleteParticipat)
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
						athleteParticipat.judgeRecord=data.recordJudge;
						athleteParticipat.judgeLevel=data.levelJudge;
						if(athleteParticipat.judgeRecord==1){
							layer.confirm("该成绩已破纪录，是否刷新记录库？", {
								title: '提示信息',
								btn : [ '确定', '取消' ],
								icon: 3
							// 按钮
							},function(){  //当要刷新记录库的时候
								ajaxSubmitModal(athleteParticipat, submitUrl);  //提交成绩
							},function(){//只录入成绩不刷新记录库
								athleteParticipat.judgeRecord=0;
								ajaxSubmitModal(athleteParticipat, submitUrl);  //提交成绩
							})
						}else{
							ajaxSubmitModal(athleteParticipat, submitUrl);
						}
					}
				})
			}else{
				ajaxSubmitModal(athleteParticipat, submitUrl);
			}
		}
	})*/
	$("#submitBtn").click(function(){
		if(validate()){//验证成功后执行
			var submitUrl=$("#contextPath").val() + '/inputScore/doUpdateInfo';
			var participatDetail=new Object;  //创建一条参赛明细记录
			var projectManager=new Object;    //创建项目
			var athleteBaseInfo=new Object;   //创建运动员基本信息
			athleteBaseInfo.athleteName=$("#athleteNameModal").val();
			participatDetail.athleteBaseInfo=athleteBaseInfo;
			projectManager.resultType=$("#resultTypeModal2").val();
//			participatDetail.athleteId=$("#athleteCidModal").val(); 
			participatDetail.participatId=$("#participatInfoIdModal").val();  //参赛id
			participatDetail.scores=$("#scores").val();
			participatDetail.ranking=$("#ranking").val();
			participatDetail.backup=$("#backup").val();
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
					Error.displayError(data); 
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
		//当查询条件不为空时执行查询
		$.ajax({
				type : 'POST',
				url : $("#contextPath").val() + '/inputScore/queryPage',
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
					$("#pageID").html("");
					$("#noDataPage").removeClass("hidden");
					$("#tableName").text(projectName);
					
					if(data.pageInfo.pages>0){
						$("#noDataPage").addClass("hidden");
						var projectName="";
						if(queryCondition.pCid){
							projectName = data.pageInfo.list[0].projectNames;
						}else{
							projectName = "项目分类信息";
						}
						for (var i = 0; i < data.pageInfo.list.length; i++) {
						    var bean = data.pageInfo.list[i];
							var type="",athleteName="",unitName="",teamCid="",backup="";
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
							if(bean.backup!=null){
								backup=bean.backup;
							}
							list += '<tr>'+
							'<td name="athleteName">'+ isStringNUll(bean.projectNames)+'</td>'+ 
							'<td>'+ isStringNUll(bean.isindividualStr)+ '</td>'+ 
							'<td name="athleteName">'+ isStringNUll(bean.participatNames)+ '</td>'+ 
							'<td name="unitName">'+ isStringNUll(bean.unitNames)+ '</td>'+ 
							'<td>'+
							'<input type="hidden" name="projectName" value="'+bean.projectManager.projectName+'"/>' +
						    '<input type="hidden" name="resultUnit" value="'+bean.projectManager.resultUnit+'"/>' +
						    '<input type="hidden" name="resultType" value="'+bean.projectManager.resultType+'"/>' +
						    '<input type="hidden" name="isTeam" value="'+bean.projectManager.isTeam+'"/>' +
						    '<input type="hidden" name="teamCid" value="'+teamCid+'"/>' +
						    '<input type="hidden" name="projectCid" value="'+bean.projectManager.cid+'"/>' +
/*						    '<input type="hidden" name="athleteCid" value="'+bean.cid+'"/>' + */
							'<a role="button"  href="javascript:" ' + 
								'class="btn btn-info inputScore btn-xs">成绩录入</a> ' ;
								if(bean.projectManager.isTeam==1){  //为团队赛添加运动员变更功能
									list+='<a role="button"  href="javascript:" \
										class="btn btn-info updateAthlete btn-xs">变更</a>'
								}
							list+='</td></tr>'
						}
						$("#list").html(list);
						if(data.pageInfo.pages>1){
							$("#pageID").html(data.pageInfo.pageHtml);
						}
					}else{
						$("#list").html('<tr></tr>');
					}
				}
			})
	})
	
	
})

function validate(){
	if ($("#resultTypeModal2").val()!=0&&($("#scores").val()==null||$("#scores").val()=="")) {//如果该项目需要录入成绩的时候
		layer.tips('请输入成绩', '#scores');
		$("#scores").focus();
	}else if ($("#ranking").val()==null||$("#ranking").val()=="") {
		layer.tips('请输入名次', '#ranking');
		$("#ranking").focus();
	}else if (!IsIntegerPositive($("#ranking").val())) {
		layer.tips('名次输入有误,请重新输入', '#ranking');
		$("#ranking").focus();
	}else{
		return true
	}
}

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
				Error.displayError(data); 
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