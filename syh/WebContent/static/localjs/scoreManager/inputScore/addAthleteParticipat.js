$(document).ready(function() {
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
			$(this).next().val(keyword.id);
	    });
	})
	
		//取消功能
	$("#cancleBtn2").click(function(){  //取消按钮
		self.location.href="index";
	});
	
    $("#projectCid").change(function(){
    	$("#allotProjectCidModal").val($(this).val());
    	var cid2=$(this).val();
		$.ajax({
			type : 'POST',
			url : $("#contextPath").val() + '/comRecord/getProjectInfo',
			data:{cid:cid2},
			dataType:'json',
			timeout:3000,
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
				var resultType="";
				$("#resultType").val(data.projectManager.resultType);
				if (data.projectManager.resultType==0) {
					resultType="只录取名次"
					$("#isInputScores").addClass("hidden")
				}else if (data.projectManager.resultType==1) {
					$("#isInputScores").removeClass("hidden")
					resultType="时间下行（用时越少，成绩越高）"
				}else if (data.projectManager.resultType==2) {
					$("#isInputScores").removeClass("hidden")
					resultType="时间上行（用时越长，成绩越高）"
				}else if (data.projectManager.resultType==3) {
					$("#isInputScores").removeClass("hidden")
					resultType="数值上行（数值越高，成绩越高）"
				}else if (data.projectManager.resultType==4) {
					$("#isInputScores").removeClass("hidden")
					resultType="数值下行（数值越低，成绩越高）"
				}
				$("#resultUnit").val(resultType+'/'+data.projectManager.resultUnit)
			}
		})
		if($("#projectIsTeam").val()==0){//如果是个人赛则隐藏添加运动员
			$("#addAthleteBtn").addClass("hidden");
			$(".minus").click();
    	}else{
    		$("#addAthleteBtn").removeClass("hidden");
    	}
    });
    
	$("#submitBtn").click(function(){
		if(vilidata()){
			var submitUrl=$("#contextPath").val() + '/inputScore/doAddAthlete';
			var athleteBaseInfos=new Array;//运动员集合
			var athleNames=document.getElementsByName("athleteName");
			var flag=true;
			for (var i = 0; i < athleNames.length; i++) {  //循环获得所有的参赛运动员
				var athleteBaseInfo=new Object;
				$athleteName=$(athleNames[i]);//转换为jquery对象
				athleteBaseInfo.athleteName=$athleteName.val();//运动员姓名
				athleteBaseInfo.sex=0;//性别暂时默认为0
				athleteBaseInfo.unitCid=$athleteName.parent().siblings().find("[name=unitName]").next().val();
				if (athleteBaseInfo.athleteName=="") { //如果用户名为空
					layer.tips("用户名不能为空",$athleteName)
					flag=false;
					$athleteName.focus();
				}else if (athleteBaseInfo.unitCid=="") {//如果得不到单位cid（单位名称输入错误）
					layer.tips("单位名称输入有误",$athleteName.parent().siblings().find("[name=unitName]"))
					flag=false;
					$athleteName.parent().siblings().find("[name=unitName]").focus();
				}else{
					flag=true;
					athleteBaseInfos.push(athleteBaseInfo);
				}
			}
			if($("#projectIsTeam").val()==1){
				if(athleNames.length<2){
					layer.msg("团队赛请至少输入2名运动员",{
						icon:2
					})
					$("#addAthleteBtn").click();
					flag=false;
				}
			}
			if (flag) {
				var participatDetail=new Object;
				var projectManager=new Object;
				participatDetail.projectCid=$("#projectCid").val();
				participatDetail.ranking=$("#ranking").val();
				participatDetail.scores=$("#scores").val();
				participatDetail.backup=$("#backUp").val();
				participatDetail.judgeRecord=0;
				participatDetail.judgeLevel=0;
				if($("#resultType").val()!=0){   //只有录入成绩时，才会成绩校验
					var athleteBaseInfo=new Object;
					athleteBaseInfo.athleteName=athleteBaseInfos[0].athleteName;
					participatDetail.athleteBaseInfo=athleteBaseInfo;
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
							participatDetail.judgeRecord=data.recordJudge;
							participatDetail.judgeLevel=data.levelJudge;
							if(participatDetail.judgeRecord==1){
								layer.confirm("该成绩已破纪录，是否刷新记录库？", {
									title: '提示信息',
									btn : [ '确定', '取消' ],
									icon: 3
								// 按钮
								},function(){  //当要刷新记录库的时候
									ajaxSubmitAddAthlete(athleteBaseInfos,participatDetail,submitUrl) //提交成绩
								},function(){//只录入成绩不刷新记录库
									participatDetail.judgeRecord=0;
									ajaxSubmitAddAthlete(athleteBaseInfos,participatDetail,submitUrl);  //提交成绩
								})
							}else{
								ajaxSubmitAddAthlete(athleteBaseInfos,participatDetail,submitUrl);
							}
						}
					})
				}else{
					ajaxSubmitAddAthlete(athleteBaseInfos,participatDetail,submitUrl);
				}
			}
		}
	});
	
	function ajaxSubmitAddAthlete(athleteBaseInfos,participatDetail,submitUrl){
		layer.confirm("确定提交吗？", {
			title: '提示信息',
			btn : [ '确定', '取消' ],
			icon: 3
		// 按钮
		}, function() {
			$.ajax({
				type : 'post',
				url : submitUrl,
				data : {
					JSONAtletes : JSON.stringify(athleteBaseInfos),
					JSONparticipatDetail : JSON.stringify(participatDetail)
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
					if (data.result == "failed") {
						layer.msg('失败', {
							icon : 2
						});
					} else if (data.result == "success") {
						layer.msg('成功', {
							icon : 1
						});
						if (data.isGroup==1) {//组合队伍参赛的时候
							$("#allotMedal").text("");
							$("#allotMedalCount").text("");
							$("#allotIntrgral").text("");
							var medalNum=data.participatDetail.medalNum;
							var medal=data.participatDetail.medal;
							if (medal==0 && medalNum>0) {
								$("#allotMedal").text("金牌");
								$("#allotMedalCount").text(1);
							}else if(medal==1 && medalNum>0){
								$("#allotMedal").text("银牌");
								$("#allotMedalCount").text(1);
							}else if(medal==2 && medalNum>0){
								$("#allotMedal").text("铜牌");
								$("#allotMedalCount").text(1);
							}else  {
								$("#allotMedal").text("")
								$("#allotMedalCount").text(0);
							}
							$("#allotIntrgral").text(data.totalScore);
							var ss=""
							$("#judgelist").html(ss);
							for (var i = 0; i < data.unitList.length; i++) {
								ss+='<tr>\
									<td>'+data.unitList[i].unitName+'</td>\
									<input type="hidden" name="participatInfoId" value="'+data.unitList[i].participatInfoId+'"/>\
									<input type="hidden" name="allotUnitCid" value="'+data.unitList[i].unitCid+'"/>\
									<td><input type="text" value='+ data.unitList[i].medalNum+'></td>\
									<td><input type="text" value='+data.unitList[i].intrgral+'></td>\
								</tr>'
							}
							$("#judgelist").html(ss);
							$("#allotReward").modal()
						}else {
							self.location.href = "index"
						}
					}
				}
			})
		})
	}
	
	function vilidata(){
		if($('#projectName').val().trim()==0){
			layer.msg("请选择比赛项目",{
				icon:2
			})
			$('#projectName').focus();
			return false;
		}else if ($("#resultType").val()!=0&&($("#scores").val()==null||$("#scores").val()=="")) {//如果该项目需要录入成绩的时候
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
	
	//添加一个运动员
	$("#addAthleteBtn").click(function() {
		if($("#projectIsTeam")==0){
			$(this).addClass("hidden")
		}else {
			var ss='<div class="col-md-10">\
				<div class="form-group">\
			<label class="col-md-3 control-label">运动员姓名</label>\
			<div class="col-md-3 ">\
				<input type="text" class="form-control" name="athleteName"\
					placeholder="输入运动员姓名">\
			</div>\
			<div class="col-md-1" style="padding-left:0;position:absolute;left:50%">\
	        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>\
	        </div>\
	        <div class="col-md-1" style="padding-left:0;position:absolute;left:100%">\
	        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>\
	        </div>\
			<label class="col-md-2 control-label" style="margin-left:-15px">单位名称</label>\
			<div class="input-group col-md-4" style="position:relative">\
				<input type="text"  class="form-control" name="unitName"\
					placeholder="输入单位名称">\
				<input type="hidden" name="unitCid"/> \
				<div>\
					<ul  class="dropdown-menu dropdown-menu-right" role="menu">\
					</ul>\
				</div>\
				<div class="col-md-1" style="position:absolute;left:300px">\
					<i class="icon-minus pull-right minus"></i>\
				</div>\
				</div>\
				</div>\
		 	</div>'
			$("#addAthleteRow").after(ss);
		}
	})
	
	$(document).on("click",".minus",function(){
		$(this).parent().parent().parent().remove();
	})
})
