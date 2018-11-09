$(function() {
	//$("#companyform").hide();
	//$("#personform").hide();
	// 资质信息
	$('#license_form input,#myform textarea').off('focus').on('focus',
		function() {
			val_license('focus', this);
		});
	$('#license_form input,#myform textarea').off('blur').on('blur',
		function() {
			val_license('blur', this);
		});
	$('#upload_license').on('click', function() {
		if(val_license()) {
			if(cards_status==3) {
				updateFn();
			} else {
				insertFn();
			}
		}
	});
	
	//搜索查询
	$("#searchBTN").on('click',function(){
    	reload();
		searchFn();
	});
    
    $("#resetBTN").on('click',function(){
    	resetFn();
    });
    //页签
    $("#tab-cards .nav li").on('click',function(){
    	$("#tab-cards .nav li").each(function(){
    		if($(this).attr("class")=="active") {
    			$(this).removeClass("active");
    		} 
    	});
    	$(this).addClass("active");
    	var hrefstr = $(this).children().attr("href");
    	var tab_th = $("#tab_datas thead th ");
    	if(hrefstr=="#person"){
    		reloadtab_thead(1);
//    		$("#companyform").hide();
//    		$("#personform").show();
    		$("#type_id").val(1);//个人
    		searchFn();
    	} else if(hrefstr=="#company") {
    		reloadtab_thead(2);
//    		$("#personform").hide();
//    		$("#companyform").show();
    		$("#type_id").val(2);//公司
    		searchFn();
    	}
    	
    });
})

function valImg() {
	
}

function searchFn(){
	var fromData = $("#queryform").serialize();
	//alert(fromData)
	$.ajax({
		   url: PROJECT_CTX+"/checkcards/ajaxQuery.html",
		   type: "post",
		   data: fromData,
		   dataType:"json",
		   success: function(data){
			   var json = eval(data.message);
			   reloadFns(json);
		   }
		});
}

//添加个人或者公司资质
function insertFn() {
	var data = $("#license_form").serialize();
	//alert(data);
	$.ajax({
		url : PROJECT_CTX + "/cards/ajaxAdd.html",
		type : "post",
		data : data,
		dataType : "json",
		success : function(data, status, xhr) {
			if (data.status == "1") {
				alert_sucess("申请成功,等待审核！");
				go_sucess_url("/capital/license.html",1500);
				//$("#status").html("<span class='label label-warning'>待审核</span>");
				
			} else {
				alert_fail("申请失败！");
			}
		}
	});
}

//添加个人或者公司资质
function updateFn() {
	var data = $("#license_form").serialize();
	// alert(data);
	$.ajax({
		url : PROJECT_CTX + "/cards/ajaxUpdate.html",
		type : "post",
		data : data,
		dataType : "json",
		success : function(data, status, xhr) {
			if (data.status == "1") {
				alert_sucess("申请成功,等待审核！");
				go_sucess_url("/capital/license.html",1500); 
				//$("#status").html("<span class='label label-warning'>待审核</span>");
			} else {
				alert_fail("申请失败！");
			}
		}
	});
}

//审核提交的app
function checkFn(id,user_id,status){
	var jsonObj = {id:id,user_id:user_id,status:status};
	if(status==1) {
		common_confirm("资质审核提示","您确认要审核<span class='green'>通过</span>吗？",checkFns,null,jsonObj);
	} else if(status==3) {
		common_confirm("资质审核提示","您确认要审核<span class='red'>不通过</span>吗？",checkFns,null,jsonObj);
	}
}

//审核
function checkFns(obj){
	$.ajax({
		   url: PROJECT_CTX+"/checkcards/ajaxCheck.html",
		   type: "post",
		   data: {"id":obj.id,"user_id":obj.user_id,"status":obj.status},
		   dataType:"json",
		   success: function(data){
			   if(data.status=='1'){
				   alert_sucess("审核成功");
				   if(obj.status==1) {
					   $("#status").html("<span class='label label-success'>通过</span>");
					   
				   } else if(obj.status==3) {
					   $("#status").html(" <span class='label label-danger'>未通过</span>");
				   }
			   }else{
				   alert_fail("审核失败");
			   }
			   //var json = eval(data.message);
			   //reloadFns(json);
		   }
		});
}

function reloadtab_thead(type_id){
	var tab_thead = $("#queryform #tab_datas thead");
	tab_thead.html("");
	var thead_tr ="<tr>";
	thead_tr += "<th class='hidden'><input type='hidden' id='type_id' name='type_id' value='1'></th>";
	thead_tr += "<th class='text-center'><span>序号</span></th>";
	thead_tr += "<th class='text-center'><span>用户帐号</span></th>";
	if(type_id==1) {
		thead_tr += "<th class='text-center'><span>用户类型</span></th>";
		thead_tr += "<th class='text-center'><span>认证名称</span></th>";
		thead_tr += "<th class='text-center'><span>认证编号</span></th>";
	}else if(type_id==2) {
		thead_tr += "<th class='text-center'><span>公司名称</span></th>";
	}
	thead_tr += " <th class='text-center'><span>创建时间</span></th>";
	thead_tr += "<th class='text-center'><span>状态</span></th>";
	thead_tr += "<th class='text-center'><span>操作</span></th>";
	thead_tr += "</tr>";
	tab_thead.append(thead_tr);
}

function reloadFns(json){
	var objs = eval(json.items);
	var tab_users = $("#queryform #tab_datas tbody");
	tab_users.html("");
	for (var i = 0; i < objs.length; i++) {
		var index = i+1;
		var obj = objs[i];
		var type_id = obj.type_id;
		var id = obj.id;
		var username = obj.username;
		var role_name = obj.role_name;
		if(type_id==1) {//个人
			var type = obj.type;
			var name = obj.name;
			var idcard = obj.idcard;
		}else if(type_id==2) {
			var name = obj.company_name;
		}
		var create_time = obj.create_time;
		var status = obj.status;
		var abtn = "";
		
		if(type==1){
			type = "中国大陆";
		}else if(type==2){
			type = "中国港澳台";
		} else if(type==3){
			type = "其他";
		}
		
		if(status==1){
			status = "<span class='label label-success'>通过</span>";
		}else if(status==2){
			status = "<span class='label label-warning'>待审核</span>";
		} else if(status==3){
			status = "<span class='label label-danger'>未通过</span>";
		} else {
			status = "<span class='label label-default'>未知</span>";
		}
		
		if(updateCurd(PROJECT_CURD)){
			abtn += getUpdateCurd(PROJECT_CTX+"/checkcards/check.html?id="+id,"修改");
		}
			
		var tr = "<tr>";
		tr+="<td class='text-center'>"+index+"</td>";
		tr+="<td class='text-center'>"+username+"</td>";
		if(type_id==1) {
			tr+="<td class='text-center'>"+type+"</td>";
		}
		tr+="<td class='text-center'>"+name+"</td>";
		if(type_id==1) {
			tr+="<td class='text-center'>"+idcard+"</td>";
		}
		tr+="<td class='text-center'>"+create_time+"</td>";
		tr+="<td class='text-center'>"+status+"</td>";
		tr+="<td class='text-center'>"+abtn+"</td>";
		tr+="</tr>";
		tab_users.append(tr);
	}
	reloadPage(json);
}
