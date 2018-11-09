$(function(){
	$("#searchBTN").click(function(){
    	reload();
		searchFn();
	});
	 $("#resetBTN").on('click',function(){
	    	resetFn();
	    });
});

//查询方法
function searchFn(){
	var fromData = $("#queryform").serialize();
	$.ajax({
		   url: PROJECT_CTX+"/rsas/ajaxQuery.html",
		   type: "post",
		   data: fromData,
		   dataType:"json",
		   success: function(data){
			   var json = eval(data.message);
			   reloadFns(json);
		   }
		});
}

//状态更新
function updateFn(product_id,id,status){
	var jsonObj = {product_id:product_id,id:id,status:status}
	var message = "";
	if(status==2) {
		message = "确定要<span class='green'>上线</span>吗?"
	} else if(status==1) {
		message = "确定要<span class='yellow'>停用</span>吗?"
	} else if(status==3) {
		message = "确定要<span class='red'>下线</span>吗?"
	}
	common_confirm("密钥操作", message,updateFnOK,null,jsonObj)
}

//状态更新方法
function updateFnOK(jsonObj){
	var fromData = $("#queryform").serialize();//查询条件
	fromData += "&product_id="+jsonObj.product_id +"&id="+jsonObj.id +"&upstatus="+jsonObj.status;
	$.ajax({
		   url: PROJECT_CTX+"/rsas/ajaxUpdate.html",
		   type: "post",
//			   data: {"product_id":product_id,"id":id,"upstatus":status},//因为有查询条件也是stauts，故不能想同
		   data: fromData,//因为有查询条件也是stauts，故不能想同
		   dataType:"json",
		   success: function(data){
			   if(data.status=='0'){
				   alert_fail("更新失败");
			   }else{
				   alert_sucess("更新成功");
			   }
			   var json = eval(data.message);
			   reloadFns(json);
		   }
		});
}

/*
//状态更新
function updateFn(product_id,id,status){
	var fromData = $("#queryform").serialize();//查询条件
	fromData += "&product_id="+product_id +"&id="+id +"&upstatus="+status;
	if(confirm("确认修改？")){
		$.ajax({
			   url: PROJECT_CTX+"/rsas/ajaxUpdate.html",
			   type: "post",
//			   data: {"product_id":product_id,"id":id,"upstatus":status},//因为有查询条件也是stauts，故不能想同
			   data: fromData,//因为有查询条件也是stauts，故不能想同
			   dataType:"json",
			   success: function(data){
				   if(data.status=='0'){
					   alert_fail("更新失败");
				   }else{
					   alert_sucess("更新成功");
				   }
				   var json = eval(data.message);
				   reloadFns(json);
			   }
			});
	}
}
*/
function reloadFns(json){
	var objs = eval(json.items);
	var tab_rsas = $("#tab_rsas tbody");
	tab_rsas.html("");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		var id = obj.id;
		var name = obj.name;
		var product_id = obj.product_id;
		var product_code = obj.product_code;
		var rsa_pri_secret = obj.rsa_pri_secret;
		var rsa_pub_secret = obj.rsa_pub_secret;
		var rsa_version = obj.rsa_version;
		var create_time = obj.create_time;
		var status = obj.status;
		var abtn = "";
	
		abtn += getQueryCurd(PROJECT_CTX+"/rsas/view.html?op=detail&id="+id,"查看");
		if(status==1){
			status = "<span class='label label-success'>正常</span>";
			//abtn = "<a class='btn btn-danger' href='javascript:updateFn("+product_id+","+id+",3)'>下线</a>";
			abtn += getStopStatusCurd(null,"状态更新","false");
		}else if(status==2){
			status = "<span class='label label-warning'>可更新</span>";
			//abtn =  "<a class='btn btn-warning' href='javascript:updateFn("+product_id+","+id+",3)'>停用</a>";
			abtn += getStopStatusCurd("javascript:updateFn("+product_id+","+id+",3)","状态更新");
		}else if(status==3){
			status = "<span class='label label-danger'>下线</span>";
			//abtn =  "<a class='btn btn-success' href='javascript:updateFn("+product_id+","+id+",2)'>上线</a>";
			abtn += getUpStatusCurd("javascript:updateFn("+product_id+","+id+",2)","状态更新");
		} else {
			status = "<span class='label label-default'>未知</span>";
		}
		
		var tr = "<tr>";
		tr+="<td class='text-center'>"+name+"</td>";
		tr+="<td class='text-center'>"+product_code+"</td>";
		tr+="<td class='text-center'><div title='"+rsa_pub_secret+"'>"+rsa_pub_secret.substring(0,30)+"..."+"</div></td>";
		tr+="<td class='text-center'><div title='"+rsa_pri_secret+"'>"+rsa_pri_secret.substring(0,30)+"..."+"</div></td>";
		tr+="<td class='text-center'>"+rsa_version+"</td>";
		tr+="<td class='text-center'>"+create_time+"</td>";
		tr+="<td class='text-center'>"+status+"</td>";
		tr+="<td class='text-center'>"+abtn+"</td>";
		tr+="</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
}