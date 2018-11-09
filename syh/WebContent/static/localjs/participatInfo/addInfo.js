$(document).ready(function() {
	var param = {
			'itemType': 'athletesType',
			"number":$("#athletesTypeclick").val(),
			"type":"0"
			}
	loaddict(param,'athletesType')	
	/****************************************************双向选择下来框*************************************************/
    var j_all_area = $("#sel_all_area"),j_selected_areas = $("#sel_selected_areas");
    
    //元素右移
    $("#btn_choose_selected_area").click(function(){
         
         j_all_area.find("option:selected").each(function(){
        	var athleteCids = [];
        	var isTeam = $("#projectIsTeam").val();
     		$('#sel_selected_areas option').each(function () {
     			athleteCids.push($(this).val());
     		});
        	 
        	if(isTeam==0&athleteCids.length>0){
        		layer.tips("个人赛项目请选1个人","#sel_selected_areas")
        	}else{
        		if(isVilidataAthlete($(this).val())){
        			$(this).appendTo(j_selected_areas);
        		}
        	}
        	 
         });
         return false;
    });
    //元素左移
     $("#btn_remove_selected_area").click(function(){
         j_selected_areas.find("option:selected").each(function(){
             $(this).appendTo(j_all_area);
         });
         return false;
     });
     //双击
     $(document).on("dblclick",".athlete",function(){
    	var id = $(this).parent().attr("id");
    	if(id=='sel_all_area'){
    		$("#btn_choose_selected_area").click();
    	}else{
    		$("#btn_remove_selected_area").click();
    	}
     })
     
     
	/****************************************************编辑时加载选中的单位和运动员*************************************************/
	var cid = $("#cid").val();
	if(cid){
		var unitCidStr = $("#selectedUnit").val();
		var unitCids= [];
		if(unitCidStr){
			unitCids=  unitCidStr.split(',')
			$.each(unitCids, function(i, val) {  
				$("#unit_"+val).attr("selected","selected");
			}); 
		}
		var athleteCids1 = [];
		$('#sel_selected_areas option').each(function () {
			athleteCids1.push($(this).val());
		});
		$.ajax({
			type : 'post',
			url : $("#contextPath").val() + '/athleteBaseInfo/selectAthlete',
			data :{
				athleteType : $("#athleteType").val(),
				athleteCids:athleteCids1,	
				unitCids:unitCids,
				isSex:$("#projectIsSex").val()
			},
			traditional:true,
			dataType : 'json',
			timeout : 3000,
			success : function(data) {
				var list = "";
				if(data.list.length>0){
					for (var i = 0; i < data.list.length; i++) {
						var bean = data.list[i];
						list += '<option value="'+bean.cid+'" unit_index="'+bean.unitCid+'" title="性别:'+bean.sexStr+'" class="athlete">'
						if(bean.alias){
							list += bean.athleteName+'('+bean.alias+')/'+bean.unitInfo.unitName
						}else{
							list += bean.athleteName+'/'+bean.unitInfo.unitName
						}
						list += '</option>';
					}
					var type =$("#sel_selected_areas option:first").attr("type_index");
					$("#athletesType").find("option[value='"+type+"']").attr("selected",true);
				}
				$("#sel_all_area").html(list);
			}
		})	
	}
	
	/****************************************************单位下拉框*************************************************/
	$("#unitCid").chosen({
	    no_results_text: "没有找到结果！",//搜索无结果时显示的提示
	    search_contains:true,   //关键字模糊搜索，设置为false，则只从开头开始匹配
	    allow_single_deselect:true, //是否允许取消选择
	    disable_search_threshold:0 //少于 0 项时隐藏搜索框
	})
	
	//根据选择的单位来加载运动员
	$("#unitCid").chosen().on("change", function (e, params) {
		if(params.selected){//判断是否新增
			loadAthlete();	
		}else{//取消时删除两边 相应的用户
			$("#sel_all_area option[unit_index='"+params.deselected+"']").remove();
			$("#sel_selected_areas option[unit_index='"+params.deselected+"']").remove();
		}
	});

	
	/****************************************************点击运动员类型加载运动员*************************************************/
	$("#athletesType").on("change", function () {
		loadAthlete();
	})
	//取消功能
	$("#cancleBtnTo").click(function(){  //取消按钮
		layer.confirm("确定取消吗？",{
			title:'提示信息',
			btn : [ '确定', '取消' ], // 按钮
			icon: 3
		},function(){
			self.location.href = "index?pCid="+$('#projectCid').val()
		})
		
	})
	/****************************************************提交*************************************************/

	$("#submitBtn").click(function(){
		var isTeam = $("#projectIsTeam").val();
		var projectCid = $('#projectCid').val();
		var athleteCids = [];
		$('#sel_selected_areas option').each(function () {
			athleteCids.push($(this).val());
		});
		var cid = $("#cid").val();
		unitCids = $("#unitCid").val();
		
		
		if(projectCid==0){
			layer.tips("请选择项目","#projectName")
			return;
		}else if(unitCids==""){
			layer.tips("请选择单位","#unitCid")
			return;
		}else if(athleteCids==0){
			layer.tips("请选择参赛运动员","#sel_selected_areas")
		}else if(isTeam==0&athleteCids.length>1){
			layer.tips("个人赛项目请选1个人","#sel_selected_areas")
		}else if(isTeam==1&athleteCids.length==1){
			layer.tips("该项目为团体赛制至少选择两人","#sel_selected_areas")
		}else if(isVilidataTeam(projectCid,athleteCids)){
			ajaxSubmitInfo(projectCid,unitCids,athleteCids,isTeam);
			
		}		
	});
})

function ajaxSubmitInfo(projectCid,unitCids,athleteCids,isTeam){
	layer.confirm("确定提交吗？",{
		title:'提示信息',
		btn : [ '确定', '取消' ], // 按钮
		icon: 3
	},function(){
		var isperUnit = $("input[name='isperUnit']:checked").val();
		var cid = $('#cid').val();
		$.ajax({
			type:'post',
			url:$("#contextPath").val() + '/participatInfo/saveToUpdate',
			data:{
				"cid":cid,
				"isperUnit":isperUnit,
				"projectCid":projectCid,
				"unitCids":unitCids,
				"athleteCids" : athleteCids,
				"isTeam" : isTeam
			},
			traditional:true,
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
				if(data=='nounit'){
					layer.tips("请选择单位","#unitCid")
				}else if(data=='noathlete'){
					layer.tips("请选择参赛运动员","#sel_selected_areas")
				}else if(data=='success'){
					self.location.href = "index?pCid="+projectCid
				}else{
					layer.msg('数据异常');
				}
			}
		})
	})
}
//加载运动员
function loadAthlete(unitCids,athleteCids){
	var unitCids = $("#unitCid").val();
	var athleteCids = [];
	$('#sel_selected_areas option').each(function () {
		athleteCids.push($(this).val());
	});
	var athleteType = $("#athletesType").val();
	var isSex = $("#projectIsSex").val();
	$.ajax({
		type : 'post',
		url : $("#contextPath").val() + '/athleteBaseInfo/selectAthlete',
		data :{
			athleteType : athleteType,
			athleteCids:athleteCids,	
			unitCids:unitCids,
			isSex:isSex
		},
		traditional:true,
		dataType : 'json',
		timeout : 3000,
		success : function(data) {
			var list = "";
			if(data.list.length>0){
				for (var i = 0; i < data.list.length; i++) {
					var bean = data.list[i];
					list += '<option value="'+bean.cid+'" unit_index="'+bean.unitCid+'" title="性别:'+bean.sexStr+'" class="athlete">'
					if(bean.alias){
						list += bean.athleteName+'('+bean.alias+')/'+bean.unitInfo.unitName
					}else{
						list += bean.athleteName+'/'+bean.unitInfo.unitName
					}
					list += '</option>';
				}
			}
			$("#sel_all_area").html(list);
		}
	})
	var isTeam = $("#projectIsTeam").val();
	if(isTeam==0||isTeam == '0'){
		$("#sel_selected_areas option").remove();
	}
	
}

//验证单个运动员
function isVilidataAthlete(athleteCid){
	var cid = $("#cid").val();
	var projectCid = $('#projectCid').val();
	var isTeam = $("#projectIsTeam").val();
	var isVilidataAthlete;
	
	$.ajax({
		type : 'post',
		url : $("#contextPath").val() + "/participatInfo/isVilidataAthlete",
		data : {
			"projectCid":projectCid,
			"athleteCid" : athleteCid,
			"isTeam" : isTeam,
			"cid":cid
		},
		traditional:true,
		async:false, 
		dataType : 'json',
		timeout : 3000,
		success : function(data) {
			if(data.states=='noathlete'){
				layer.tips("请选择参赛运动员","#sel_selected_areas")
			}else if(data.states=='failed'){
				layer.msg('系统异常');
			}else if(data.states=='projectError'){
				layer.msg('该运动员已经参赛！不能重复参赛');
			}else if(data.states=='sexError'){
				layer.msg('该运动员性别不符合项目的要求');
			}else if(data.states=='nobirthday'){
				layer.msg('该运动员的年龄没有录入');
			}else if(data.states=='teamManAgeStart'){
				layer.msg('参与该团体项目的男运动员出生范围：'+data.param);
			}else if(data.states=='teamWomanAgeStart'){
				layer.msg('参与该团体项目的女运动员出生范围：'+data.param);
			}else if(data.states=='groupManAgeStart'){
				layer.msg('该男性运动员年龄不符合项目要求');
			}else if(data.states=='groupWomanAgeStart'){
				layer.msg('该女性运动员年龄不符合项目要求');
			}else if(data.states=='personToError'){
				layer.msg('该运动员个人参赛总数已达到限制');
			}else if(data.states=='personError'&&isTeam==0){
				layer.msg('该运动员个人单项参赛数已达到限制');
			}else if(data.states=='personError'&&isTeam==1){
				layer.msg('该运动员个人团体参赛数已达到限制');
			}else if(data.state=='unitNumError'){
				layer.msg('该运动员所在单位参赛总数已达到限制');
			}else if(data.states=='unitError'&&isTeam==0){
				layer.msg('该运动员所在单位单项参赛数已达到限制');
			}else if(data.states=='unitError'&&isTeam==1){
				layer.msg('该运动员所在单位团体参赛数已达到限制');
			}else{
				isVilidataAthlete =  true;
			}
		}
	})			
	return isVilidataAthlete;
}

//验证多个运动员综合限制
function isVilidataTeam(projectCid,athleteCids){
	
	var isVilidataTeam;
	
	var cid = $("#cid").val();
	$.ajax({
		type : 'post',
		url : $("#contextPath").val() + "/participatInfo/isVilidataTeam",
		data : {
			"projectCid":projectCid,
			"athleteCids" : athleteCids,
			"cid":cid
		},
		traditional:true,
		async:false, 
		dataType : 'json',
		timeout : 3000,
		success : function(data) {
			if(data.states=='noathlete'){
				layer.tips("请选择参赛运动员","#sel_selected_areas")
			}else if(data.states=='failed'){
				layer.msg('系统异常');
			}else if(data.states=='teamSumError'){
				layer.msg('该团体项目人数需达到【'+data.param+'】才能参加');
			}else if(data.states=='teamManSumError'){
				layer.msg('该团体项目的男性运动员不得少于【'+data.param+'】人');
			}else if(data.states=='teamWomanSumError'){
				layer.msg('该团体项目的女性运动员不得少于【'+data.param+'】人');
			}else if(data.states=='nobirthday'){
				layer.msg('该运动员的年龄没有录入');
			}else if(data.states=='ageSumError'){
				layer.msg('该运动员的年龄之和不符合项目要求');
			}else{
				isVilidataTeam =  true;
			}
		}
	})			
	return isVilidataTeam;
}
