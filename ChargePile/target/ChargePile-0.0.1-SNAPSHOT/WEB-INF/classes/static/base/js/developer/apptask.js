$(document).ready(function(){
    //新增，修改用户
    $('#myform input,#myform textarea,#myform select').off('focus').on('focus', function(){
    	val_task('focus',this);
    });
    $('#myform input,#myform textarea,#myform select').off('blur').on('blur', function(){
    	val_task('blur',this);
    });
    
    $("#myform select").change(function(){
    	val_task('change',this);
    });
    
    $("#duration_total_num").off('focus').on('focus', function(){
    	removeSelfNotice($(this));
    });
    
    //新增按钮点击事件
    $('#addTask').on('click', function(){
    	if(val_task() && verifyTime() && veriftTaskNum()){
    		insertFn();
    	}
    });
    
    $("#searchBTN").on('click',function(){
    	reload();
    	searchFn();
    });
    
    $("#resetBTN").on('click',function(){
    	resetFn();
    });
});


function verifyTime(starttime,endtime){
	var startTime = $("[name='starttime']").val();
	var endTime = $("[name='endtime']").val();
	if(startTime == undefined || startTime=='' ||startTime == null){
		//alert("请选择开始日期");
		showNotice("starttime","请选择开始日期"," fa fa-times-circle error ");   //name, notice, status
		return false;
	}
	if(endTime == undefined || endTime=='' ||endTime == null){
		showNotice("endtime","请选择结束日期"," fa fa-times-circle error ");   //name, notice, status
		return false;
	}
	
	var ts_start = moment(startTime, "YYYY-MM-DD HH:mm:ss").format('X');
	var ts_end = moment(endTime, "YYYY-MM-DD HH:mm:ss").format('X');
	if(ts_end < ts_start){
		showNotice("endtime","结束时间必须大于开始时间"," fa fa-times-circle error ");   //name, notice, status
		return false;
	}
	//alert(ts);
	return true;
}

function veriftTaskNum(){
	/*var total_num = $('#total_num').val();
	var duration_total_num = $('#duration_total_num').val();
	if(Number(duration_total_num) > Number(total_num)){
		showNotice("duration_total_num","当前时间段投放数不能大于总投放数"," fa fa-times-circle error ");   //name, notice, status
		return false;
	}*/
	return true;
}

/*
 * 查询方法
 */
function searchFn(){
	$("#tab_apptask tbody").empty();
	$("#loading").show();
	setTimeout("loadListFn()",200);
}

function loadListFn(){
	var fromData = $("#queryform").serialize();
	$.ajax({
	   url: PROJECT_CTX+"/apptask/ajaxQuery.html",
	   type: "post",
	   data: fromData,
	   dataType:"json",
	   success: function(data){
		   var json = eval(data.message);
		   reloadFns(json);
	   }
	});
}


function insertFn() {
	var fromData = $("#myform").serialize();
	debugger;
	$.ajax({
		   url: PROJECT_CTX+"/apptask/ajaxAdd.html",
		   type: "post",
		   data: fromData,
		   dataType:"json",
		   success: function(data){
			   if(data.status=='0'){
				   alert_fail("添加失败");
			   }else if(data.status=='-1'){
				   alert_fail("投放数量超过了"+data.attach);
			   }else{
				   alert_sucess("添加成功");
				   go_sucess_url("/apptask/index.html",1500); 
			   }
		   }
		});
}

//更新任务时间
function updateFn() {
	var data = $("#myform").serialize();
	$.ajax({
	   url: PROJECT_CTX+"/apptask/ajaxUpdate.html",
	   type: "post",
	   data: data,
	   dataType:"json",
	   success: function(data,status,xhr){
		   if(data.status=="1") {
			   alert_sucess("修改成功");
			  // $("#status").html("<span class='label label-warning'>待审核</span>");
			   go_sucess_url("/app/index.html",1500); 
		   } else {
			   alert_fail("修改失败");
		   }
	   }
	});
}

function deleteFns(){
	var selectIds = "";
	$.each($("input[name=selectId]"),function(){
		if($(this).prop("checked")){
			selectIds +=$(this).val()+",";
		}
	});
	if($.trim(selectIds)==''){
		bb_alert(null,"请勾选需要删除的记录！");
		return;
	}
	deleteFn(selectIds);
}

//删除产品
function deleteFn(product_id){
	del_confirm("删除提示","您确认要删除记录吗？",deleteFnOk,null,product_id);
}

function deleteFnOk(product_id) {
	$.ajax({
		   url: PROJECT_CTX+"/app/ajaxDelete.html",
		   type: "post",
		   data: {"selectId":product_id},
		   dataType:"json",
		   success: function(data){
			   if(data.status=='1'){
				   alert_sucess("删除成功");
			   }else{
				   alert_fail("删除失败");
			   }
			   var json = eval(data.message);
			   reloadFns(json);
		   }
		});
}

function reloadFns(json){
	var objs = eval(json.items);
	var tab_rsas = $("#tab_apptask tbody");
	tab_rsas.html("");
	for (var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		var taskId = obj.id;
		var appid = obj.appid;
		var keyword = obj.keyword;
		var price = obj.price;
		var starttime = obj.starttime;
		var endtime = obj.endtime;
		var taskdesc = obj.taskdesc;
		var cur_total_num = obj.cur_total_num;
		var cur_remain_num = obj.cur_remain_num;
		var status = obj.status;
		
		var abtn = "";
		if(updateCurd(PROJECT_CURD)){
			abtn += getUpdateCurd(PROJECT_CTX+"/apptask/update.html?id="+taskId,"修改");
		}
		if(deleteCurd(PROJECT_CURD)){
			if(status !=0) {
				abtn += getDeleteCurd("javascript:deleteFn("+taskId+")","删除");
			} else {
				abtn += getDeleteCurd(null,"删除","false");
			}
		}

		if(status==0){
			status = "<span class='label label-default'>已过期</span>";
		}else if(status==1){
			status = "<span class='label label-success'>正常</span>";
		}
		
		var tr = "<tr>";
		//tr+="<td class='text-center'><input type='checkbox' name='selectId' value="+id+"></td>";
		tr+="<td class='text-center'>"+taskId+"</td>";
		tr+="<td class='text-center'>"+appid+"</td>";
		tr+="<td class='text-center'>"+keyword+"</td>";
		tr+="<td class='text-center'>"+price+"</td>";
		tr+="<td class='text-center'>"+starttime+"</td>";
		tr+="<td class='text-center'>"+endtime+"</td>";
		
		/*tr+="<td class='text-center'>"+app_secret+"</td>";*/
		var shortDesc = taskdesc;
		if(taskdesc == undefined || taskdesc == null){
			taskdesc = "none";
		}
		if(taskdesc.length > 10){
			shortDesc = taskdesc.substring(0, 10)+"...";
		}
		tr += "<td class='text-center' title='"+taskdesc+"'>"+shortDesc+"</td>";
		
		tr+="<td class='text-center'>"+cur_total_num+"</td>";
		tr+="<td class='text-center'>"+cur_remain_num+"</td>";
		
		tr+="<td class='text-center'>"+status+"</td>";
		
		tr+="<td class='text-center'>"+abtn+"</td>";
		tr+="</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
	$("#loading").hide();
}
