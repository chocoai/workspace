$(function(){
    
	$("#addAppUpload").click(function(){
		insertFn();
	});
	
    $("#searchBTN").on('click',function(){
    	reload();
    	searchFn();
    });
    $("#resetBTN").on('click',function(){
    	resetFn();
    });
});

function searchFn(){
	var fromData = $("#queryform").serialize();
	$.ajax({
		   url: PROJECT_CTX+"/checkapps/ajaxQuery.html",
		   type: "post",
		   data: fromData,
		   dataType:"json",
		   success: function(data){
			   var json = eval(data.message);
			   reloadFns(json);
		   }
		});
}


//审核提交的app
function checkFn(id,status){
	var jsonObj = {id:id,status:status}
	if(status==1) {
		common_confirm("产品审核提示","您确认要审核<span class='green'>通过</span>吗？",checkFns,null,jsonObj);
	} else if(status==3) {
		common_confirm("产品审核提示","您确认要审核<span class='red'>不通过</span>吗？",checkFns,null,jsonObj);
	}
}


function checkFns(obj){
	$.ajax({
		   url: PROJECT_CTX+"/checkapps/ajaxCheck.html",
		   type: "post",
		   data: {"id":obj.id,"status":obj.status},
		   dataType:"json",
		   success: function(data){
			   if(data.status=='1'){
				   alert_sucess("审核成功");
				   go_sucess_url("/checkapps/index.html",1500)
			   }else{
				   alert_fail("审核失败");
			   }
			   var json = eval(data.message);
			   reloadFns(json);
		   }
		});
}

/*
//审核app
function checkFn(id,status){
	if(confirm("确认审核？")){
		$.ajax({
			   url: PROJECT_CTX+"/checkapps/check.html",
			   type: "post",
			   data: {"id":id,"status":status},
			   dataType:"json",
			   success: function(data){
				   if(data.status=='1'){
					   alert_sucess("审核成功");
				   }else{
					   alert_fail("审核失败");
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
	var tab_rsas = $("#tab_datas tbody");
	tab_rsas.html("");
	for ( var i = 0; i < objs.length; i++) {
		var index = i+1;
		var obj = objs[i];
		var id = obj.id;
		var name = obj.name;
		var type = obj.os_type;
		var package_name = obj.package_name;
		var app_version = obj.app_version;
		var size = obj.size;
		var md5 = obj.md5;
		var create_time = obj.createTimeStr;
		var app_url = obj.app_url;
		var status = obj.status;
		var abtn = "";
		
//		abtn+="<a class='btn btn-warning' href='javascript:checkFn("+id+",1)'>通过</a> ";
//		abtn+="<a class='btn btn-danger' href='javascript:checkFn("+id+",3)'>不通过</a>";
    
		//if(insertCurd(PROJECT_CURD)){
		//abtn += getApproveCurd("javascript:checkFn("+id+",1)","通过");
		//abtn += getNotApproveCurd("javascript:checkFn("+id+",3)","不通过");
		//}
		if(updateCurd(PROJECT_CURD)){
			abtn += getUpdateCurd(PROJECT_CTX+"/checkapps/check.html?id="+id,"修改");
		}
		
		if(type=='1'){
			type = "<i class='fa fa-apple fa-2x color-ios'></i>";
		}else if(type=='2'){
			type = "<i class='fa fa-android fa-2x color-android'></i>";
		}
		
		if(status==1){
			status = "<span class='label label-success'>正常</span>";
		}else if(status==2){
			status = "<span class='label label-warning'>待审核</span>";
		}else if(status==3){
			status = "<span class='label label-danger'>未通过</span>";
		}else if(status==4){
			status = "<span class='label label-primary'>可更新</span>";
		} else if(status==5) {
			status = "<span class='label label-default'>下线</span>";
		} else {
			status = "<span class='label label-default'>未知</span>";
		}
		var tr = "<tr>";
		tr+="<td class='text-center'>"+index+"</td>";
		tr+="<td class='text-center'>"+name+"</td>";
		tr+="<td class='text-center'>"+type+"</td>";
		tr+="<td class='text-center'>"+package_name+"</td>";
		tr+="<td class='text-center'>"+app_version+"</td>";
		tr+="<td class='text-center'>"+size+" M</td>";
		tr+="<td class='text-center'>"+md5+"</td>";
		tr+="<td class='text-center'>"+create_time+"</td>";
		tr+="<td class='text-center'><a href="+PROJECT_IMAGE_PREFIX+""+app_url+" target='_black'>下载</a></td>";
		tr+="<td class='text-center'>"+status+"</td>";
		tr+="<td class='text-center'>"+abtn+"</td>";
		tr+="</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
}