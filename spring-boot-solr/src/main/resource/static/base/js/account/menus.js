$(function(){
	//表单验证事件
	$('#myform input,#myform textarea').off('focus').on('focus', function(){
		val_menu('focus',this);
    });
    $('#myform input,#myform textarea').off('blur').on('blur', function(){
    	val_menu('blur',this);
    });
    //添加权限
    $('#addMenu').on('click', function(){
    	if(val_menu()){
    		insertFn();
    	}
    });
    //修改权限
    $('#updateMenu').on('click', function(){
    	if(val_menu()){
    		updateFn();
    	}
    });
	
	$("#pid").change(function(){
		if($(this).val()==0){
			$("#icon_div").show();
			$("input[name=icon]").eq(0).prop({"checked":"checked"})
		}else{
			$("#icon_div").hide();
			//去掉选中按钮
			$.each($("input[name=icon]"),function(){
				$(this).removeAttr("checked")
			});
		}
	});
	
	$("#searchBTN").on('click',function(){
    	reload();
    	searchFn();
    });
    $("#resetBTN").on('click',function(){
    	resetFn();
    });
})

//查询方法
function searchFn(){
	var fromData = $("#queryform").serialize();
	$.ajax({
		   url: PROJECT_CTX+"/menus/ajaxQuery.html",
		   type: "post",
		   data: fromData,
		   dataType:"json",
		   success: function(data){
			   var json = eval(data.message);
			   reloadFns(json);
		   }
		});
}


//添加权限
function insertFn(){
	var data = $("#myform").serialize();
	$.ajax({
	   url: PROJECT_CTX+"/menus/ajaxAdd.html",
	   type: "post",
	   data: data,
	   dataType:"json",
	   success: function(data){
		   if(data.status=='0'){
			   alert_fail("添加失败");
		   }else{
			   alert_sucess("添加成功");
			   go_sucess_url("/menus/index.html",1500);
		   }
	   }
	});
}

//添加权限
function updateFn(){
	var data = $("#myform").serialize();
	$.ajax({
	   url: PROJECT_CTX+"/menus/ajaxUpdate.html",
	   type: "post",
	   data: data,
	   dataType:"json",
	   success: function(data){
		   if(data.status==0){
			   alert_fail("修改失败");
		   }else{
			   alert_sucess("修改成功");
			   go_sucess_url("/menus/index.html",1500);
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

function deleteFn(id){
	del_confirm("删除提示","您确认要删除记录吗？",deleteFnOk,null,id);
}

function deleteFnOk(id) {
	$.ajax({
		   url: PROJECT_CTX+"/menus/ajaxDelete.html",
		   type: "post",
		   data: {"selectId":id},
		   dataType: "json",
		   success: function(data){
			   if(data.status=="1"){
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
	var tab_rsas = $("#tab_datas tbody");
	tab_rsas.html("");
	for (var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		var id = obj.id;
		var pid = obj.pid;
		var url = obj.url;
		var name = obj.name;
		var full_name = obj.full_name;
		var icon = obj.icon;
		var create_time = obj.create_time;
		
		var abtn = "";
		
		if(updateCurd(PROJECT_CURD)){
			abtn += getUpdateCurd(PROJECT_CTX+"/menus/update.html?id="+id,"修改");
		}
		
		if(deleteCurd(PROJECT_CURD)){
			abtn += getDeleteCurd("javascript:deleteFn("+id+")","删除");
		}
		
		var tr = "<tr>";
		tr+="<td class='text-center'><input type='checkbox' name='selectId' value="+id+"></td>";
		tr+="<td class='text-center'>"+id+"</td>";
		tr+="<td class='text-center'>"+pid+"</td>";
		tr+="<td class='text-center'>"+url+"</td>";
		tr+="<td class='text-center'>"+name+"</td>";
		tr+="<td class='text-center'>"+full_name+"</td>";
		tr+="<td class='text-center'><i class='fa "+icon+"'/></td>";
		tr+="<td class='text-center'>"+create_time+"</td>";
		tr+="<td class='text-center'>"+abtn+"</td>";
		tr+="</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
}