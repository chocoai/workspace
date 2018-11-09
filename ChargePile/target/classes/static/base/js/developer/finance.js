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
	$("#tab_finance tbody").empty();
	$("#loading").show();
	setTimeout("loadListFn()",300);
}

function loadListFn(){
	var fromData = $("#queryform").serialize();
	$.ajax({
	   url: PROJECT_CTX+"/finance/ajaxQuery.html",
	   type: "post",
	   data: fromData,
	   dataType:"json",
	   success: function(data){
		   var json = eval(data.message);
		   reloadFns(json);
	   }
	});
}

function checkFn(fid,status){
	var message = "";
	if(status === 1){
		message = "您的操作将导致该提现申请成功通过，确定么？";
	} else if(status === 2){
		message = "您的操作将导致该提现申请不被通过，确认么？";
	}
	var obj = {
		fid:fid,
		status:status
	}
	common_confirm("审核提示",message,checkFnOk,null,obj);
}

function batchCheckFn(status){
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
	alert(selectIds);
	checkFn(selectIds,status)
}

function checkFnOk(obj){
	var status = $("#select_status").val() | 0;
	var fromData = $("#queryform").serialize();
	console.log(obj);
	$.ajax({
	   url: PROJECT_CTX+"/finance/ajaxCheck.html?fid="+obj.fid+"&s="+obj.status+"&status="+status,
	   type: "post",
	   data: fromData,
	   dataType:"json",
	   success: function(data){
		   if(data.status=='1'){
			   alert_sucess("操作成功");
		   }else{
			   alert_fail("操作失败");
		   }
		   var json = eval(data.message);
		   reloadFns(json);
	   }
	});
}

function postMoneyFn(fid){
	var message = "您将进行打款操作，涉及金钱转账,是否继续？";
	common_confirm("转账提示",message,postMoneyFnOk,null,fid);
}

function postMoneyFnOk(fid){
	var status = $("#select_status").val();
	var fromData = $("#queryform").serialize();
	$.ajax({
	   url: PROJECT_CTX+"/finance/ajaxPostMoney.html?fid="+fid+"&status="+status,
	   type: "post",
	   data: fromData,
	   dataType:"json",
	   success: function(data){
		   if(data.status=='1'){
			   alert_sucess("打款完成");
		   }else if(data.status=='2'){
			   alert_sucess("部分打款完成");
		   }else{
			   alert_fail("打款失败");
		   }
		   var json = eval(data.message);
		   reloadFns(json);
	   }
	});
}

function reloadFns(json){
	var objs = eval(json.items);
	var tab_rsas = $("#tab_finance tbody");
	
	tab_rsas.html("");
	for (var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		var fid = obj.id;
		var nickname = obj.nickname;
		var paytype = obj.paytype;
		var amount = obj.amount;
		var applytime = obj.applytime;
		var paytime = !obj.o_paytime ? "" : obj.o_paytime;
		var billno = obj.billno ;
		var orderid = !obj.o_billno ? "" : obj.o_billno ;
		var payamount = obj.payamount ;
		var fee = obj.fee;
		var status = obj.status;
		var statusStr = "";
		if(status==0){
			statusStr = "<span class='label label-primary'>审核中</span>";
		}else if(status==1){
			statusStr = "<span class='label label-info'>审核通过</span>";
		}else if(status==2){
			statusStr = "<span class='label label-warning'>审核不通过</span>";
		}else if(status==3){
			statusStr = "<span class='label label-success'>打款成功</span>";
		}else if(status==4){
			statusStr = "<span class='label label-default'>打款失败</span>";
		}
		
		var abtn = "";
		
			if(status == 0){
				abtn += getCheckCinanceCurd("javascript:checkFn("+fid+",1)","javascript:checkFn("+fid+",2)","审核",status);
			}
			
			if(status == 1){
				//审核通过，显示打款按钮
				abtn += getPostMoneyCurd("javascript:postMoneyFn("+fid+")","打款") 
			}
		
		
		var tr = "<tr>";
		var ul="<ul class='c_ul'>";
		ul+="<li>";
		ul+="<input type='checkbox' name='selectId' value="+fid+">"
		ul+="</li>"
		ul+="<li>";
		ul+=fid;
		ul+="</li>";
		ul+="</ul>";
		tr+="<td class='text-center'>"+ul+"</td>";
		tr+="<td class='text-center'>"+nickname+"</td>";
		var payName = "";
		if(paytype == 1){
			payName = "支付宝";
		}else {
			payName = "微信钱包";
		}
		tr+="<td class='text-center'>"+payName+"</td>";
		tr+="<td class='text-center'>"+amount+"</td>";
		tr+="<td class='text-center'>"+applytime+"</td>";
		tr+="<td class='text-center'>"+paytime+"</td>";
		tr+="<td class='text-center'>"+billno+"</td>";
		tr+="<td class='text-center'>"+orderid+"</td>";
		tr+="<td class='text-center'>"+payamount+"</td>";
		tr+="<td class='text-center'>"+fee+"</td>";
		tr+="<td class='text-center'>"+statusStr+"</td>";
		tr+="<td class='text-center'>"+abtn+"</td>";
		tr+="</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
	var status = $("#select_status").val();
    if((status==0 ||status ==1) && status != ''){
	   if(status==0){
		   $("#btn_status_0").show();
		   $("#btn_status_1").hide();
	   }else{
		   $("#btn_status_0").hide();
		   $("#btn_status_1").show();
	   }
	   $("input[type='checkbox']").show();
    } else {
	   $("input[type='checkbox']").hide();
	   $("#btn_status_0").hide();
	   $("#btn_status_1").hide();
    }
	$("#loading").hide();
}
