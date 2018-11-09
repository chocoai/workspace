//这是成绩综合查询的主页面js
$(function() {
	$("#projectCid").change(function() {
		$("#queryBtn").click();
	})
	//为查看成绩绑定事件
	$(document).on("click",".viewScores",function(){
		var isTeam=$(this).prev().val();
		var resultType=$(this).prev().prev().val();
		var resultUnit=$(this).prev().prev().prev().val();
		var projectCid=$(this).next().val();//获取项目cid
		var athleteCid=$(this).next().next().val();//获取运动员cid
		var athleteName= $(this).parent().siblings("[name=athleteName]").text();
		var projectName= $(this).parent().siblings("[name=projectName]").text();
		var unitName= $(this).parent().siblings("[name=unitName]").text();
		var medalNum= $(this).parent().siblings("[name=medalNum]").text();
		var scores=$(this).parent().siblings("[name=scores]").text();
		var ranking=$(this).parent().siblings("[name=ranking]").text();
		var medal=$(this).parent().siblings("[name=medal]").text();
		var intrgral=$(this).parent().siblings("[name=intrgral]").text();
		var medalNum=$(this).parent().siblings("[name=medalNum]").text();
		var backup=$(this).parent().siblings("[name=backup]").text();
		$("#viewAthleteCid").val(athleteCid);
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
	
	//查询刷新功能
	$("#refreshTable").click(function(){
		var currentPage = $('#currentPage').val();
		if(!currentPage){
			currentPage=1;
		}
		var queryCondition={
			"projectCid":$("#projectCid").val(),
			"currentPage":currentPage,
			"queryUnitName":$("#queryUnitName").val(),
			"queryAthleteName":$("#queryAthleteName").val(),
			"queryRange":1  //查询录入成绩的（即有名次的）
		}
		if (true||queryCondition.projectCid!=""||queryCondition.queryUnitName!=""||queryCondition.queryAthleteName!="") {
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
					var list = "";
					$("#list").html(list);
					$("#pageID").html("");
					$("#noDataPage").addClass("hidden");
					if(data.pageInfo.pages>0){
						for (var i = 0; i < data.pageInfo.list.length; i++) {
						    var bean = data.pageInfo.list[i];
							var type="",athleteName="",unitName="",teamCid="";
							if (bean.projectManager.isTeam==0) {
								type="个人赛";
								athleteName=bean.athleteBaseInfo.athleteName;
								unitName=bean.athleteBaseInfo.unitInfo.unitName;
							}else{
								type="团队赛";
								athleteName=bean.teamAthleteName;
								unitName+=bean.teamUnitName;
								teamCid=bean.athleteCid;
							}
							if (bean.gold==0&&bean.silver==0&&data.copper==0) {
								medal="无"
							}else if (bean.gold>=1) {
								medal="金牌"
								medalNum=bean.gold
							}else if (bean.silver>=1) {
								medal="银牌"
								medalNum=bean.silver	
							}else if (bean.copper>=3) {
								medal="铜牌"
								medalNum=bean.copper	
							}
							list += '<tr>\
							<td name="projectName">'+ bean.projectManager.projectName+ '</td>'
								+ '<td>'+ type+ '</td>'
								+ '<td name="athleteName">'+ athleteName+ '</td>'
								+ '<td name="unitName">'+ unitName+ '</td>'
								+ '<td name="scores">'+ bean.scores+ '</td>'
								+ '<td name="ranking">'+ bean.ranking+ '</td>'
								+ '<td name="intrgral">'+ bean.intrgral+ '</td>'
								+ '<td name="medal">'+ medal+ '</td>'
								+ '<td name="medalNum" class="hidden">'+ medalNum+ '</td>'
								+ '<td name="backup" class="hidden">'+ bean.backup+ '</td>'
								+ '<td><input type="hidden" value="'+bean.projectManager.resultUnit
								+'"/><input type="hidden" value="'
								+bean.projectManager.resultType
								+'"/><input type="hidden" value="'
								+bean.projectManager.isTeam
								+'"/><a role="button"  href="javascript:" \
								class="btn btn-success viewScores">查看</a><input type="hidden" value="'
								+bean.projectCid
								+'"/><input type="hidden" value="'+
								bean.athleteCid
								+'"/></td>'
								+ '</tr>'
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
		}
	})
})