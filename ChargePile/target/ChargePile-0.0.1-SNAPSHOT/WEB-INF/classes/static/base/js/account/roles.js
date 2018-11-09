$(function(){
	//加载权限列表
	ajaxMenu();
	 //新增，修改角色
    $('#myform input,#myform textarea').off('focus').on('focus', function(){
    	val_roles('focus',this);
    });
    $('#myform input,#myform textarea').off('blur').on('blur', function(){
    	val_roles('blur',this);
    });
    
    $('#addRole').on('click', function(){
    	if(val_roles()&&isCheckedCheckbox()){
    		insertFn();
    	}
    });
	
    $('#updateRole').on('click', function(){
    	if(val_roles()&&isCheckedCheckbox()){
    		updateFn();
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

//判断checkbox是否勾选
function isCheckedCheckbox() {
	var $checkbocObj = $("[name='menusIds']:checkbox");
	if(!$checkbocObj.is(":checked")) {
		//alert($checkbocObj.eq(0).attr("id"));
		var noticeHtml = '<i class="formcheck fa fa-times-circle error"> 请选择权限</i>';
		$("#menus").parent().append(noticeHtml);
		return false;
	}
	return true;
	
}

function ajaxMenu(){
	$.ajax({ 
		url: PROJECT_CTX + "/roles/ajaxMenu.html", 
		success: function(data){
			var json = eval(data);
			var jsonLen = json.length;
			var menus =	$("#menus");
			var html = "";
			var parentHtml = "";
			parentHtml+="<ul>";
			for(var i = 0; i < jsonLen; i++){
				var obj = json[i];
				var jsonIcon = obj.icon;
				var sons = obj.sons;
				var sonsLen = sons.length;
				
				parentHtml+="<li>";
				parentHtml += "<input type='checkbox' name='menusIds' value='"+obj.id+"' id='p_"+obj.id+"'>"+obj.name;
				var sonsHtml ="";
				sonsHtml+="<ul style='width:200px;'>";
				for(var j = 0; j < sonsLen; j++){
					var sonObj = sons[j];
					sonsHtml+="<li>";
					sonsHtml +=  "<input type='checkbox' name='menusIds' value='"+sonObj.id+"' id='p_"+obj.id+"_s_"+sonObj.id+"'>"+sonObj.name;
					sonsHtml +=  "<div style='margin-left: 15px;display:block;width:200px;'>";
					sonsHtml +=  " <input type='checkbox' id='p_"+obj.id+"_s_"+sonObj.id+"_100' name='operation"+sonObj.id+"' value='100' > 新增";
					sonsHtml +=  " <input type='checkbox' id='p_"+obj.id+"_s_"+sonObj.id+"_10' name='operation"+sonObj.id+"' value='10'> 修改";
					sonsHtml +=  " <input type='checkbox' id='p_"+obj.id+"_s_"+sonObj.id+"_1' name='operation"+sonObj.id+"' value='1'> 删除";
					sonsHtml +=  "</div>";
					sonsHtml+="</li>";
				}
				sonsHtml+="</ul>";
				parentHtml+=(sonsHtml);
				parentHtml+="</li>";
			}
			parentHtml+="</ul>";
			html += (parentHtml);
			menus.html(html);
			
			setClick();
			
		}
	});
}


function setClick(){
	//默认选中
	var role_menusIdsV = $("#role_menusIds").val();
	if(role_menusIdsV){
		var role_menusIds = role_menusIdsV.split(",");
		for(var i = 0; i < role_menusIds.length; i++){
			$("#"+role_menusIds[i]).prop({"checked":"checked"});
		}
	}
	var menuCurd = $("#menuCurd").val();
	if(menuCurd){
		var role_menusIds = menuCurd.split(",");
		for(var i = 0; i < role_menusIds.length; i++){
			$("#"+role_menusIds[i]).prop({"checked":"checked"});
		}
	}
	/**
	 * 目录结构
	 * 
	 * --root [p_id]
	 * 	  |---li [p_id_s_sid]
	 * 			|---div
	 * 				|--- input , input ,input [p_id_s_sid_v]
	 * --root [p_id]
	 * 	  |---li [p_id_s_sid]
	 * 			|---div
	 * 				|--- input , input ,input [p_id_s_sid_v]
	 */
	//增加 - 修改- 删除 添加点击事件
	$.each($("input[name^=operation]"),function(){
    	$(this).click(function(){
    		var id = $(this).attr("id");
    		var liId = id.substring(0,id.lastIndexOf("_"));
    		var rootId = liId.substring(0,liId.lastIndexOf("s")-1);
    		//如果是选中,将li,root选中
    		if($(this).prop("checked")){
    			$("#"+liId).prop({"checked":"checked"});
    			$("#"+rootId).prop({"checked":"checked"});
    		}else{
    			//如果是取消,先判断li下面是否还有选中
    			var fli = false;
    			$.each($("input[id^="+liId+"_]"),function(){
    				if($(this).prop("checked")){
    					fli = true;
    				}
    			});
    			if(!fli){
    				//如果li下没有选中,则取消li的选中,并判断root是否还有选中
    				$("#"+liId).prop({"checked":""});
    				var froot = false;
    				$.each($("input[id^="+rootId+"_]"),function(){
        				if($(this).prop("checked")){
        					froot = true;
        				}
        			});
    				if(!froot){
    					//如果root下没有选中,则取消root的选中
    					$("#"+rootId).prop({"checked":""});
    				}
    			}
    		}
    	});
    });
	
	//权限添加点击事件
	$.each($("input[name^=menusIds]"),function(){
		$(this).click(function(){
			var liId = $(this).attr("id");
			var rootId = liId.substring(0,liId.lastIndexOf("s")-1);
			//如果选中,则将div-input,root选中
			if($(this).prop("checked")){
    			$("#"+rootId).prop({"checked":"checked"});
    			$.each($("input[id^="+liId+"]"),function(){
    				var thisId = $(this).attr("id");
    				var thisRootId = thisId.substring(0,thisId.lastIndexOf("s")-1);
    				if(liId==thisRootId){
    					$(this).prop({"checked":"checked"});
    				}
    			});
    		}else{
    			//如果是取消,则将div-input取消,并判断root下是否还有被选中的.没有则取消root选中
    			$.each($("input[id^="+liId+"]"),function(){
    				var thisId = $(this).attr("id");
    				var thisRootId = thisId.substring(0,thisId.lastIndexOf("s")-1);
    				if(liId==thisRootId){
    					$(this).prop({"checked":""});
    				}
    			});
    			var froot = false;
    			$.each($("input[id^="+rootId+"_]"),function(){
    				if($(this).prop("checked")){
    					froot = true;
    				}
    			});
    			if(!froot){
					//如果root下没有选中,则取消root的选中
					$("#"+rootId).prop({"checked":""});
				}
    		}
		});
	});
}

//查询方法
function searchFn(){
	var fromData = $("#queryform").serialize();
	$.ajax({
		   url: PROJECT_CTX+"/roles/ajaxQuery.html",
		   type: "post",
		   data: fromData,
		   dataType:"json",
		   success: function(data){
			   var json = eval(data.message);
			   reloadFns(json);
		   }
		});
}

//添加角色
function insertFn(){
	var data = $("#myform").serialize();
	$.ajax({
	   type: "post",
	   url: PROJECT_CTX+"/roles/ajaxAdd.html",
	   data: data,
	   dataType:"json",
	   success: function(data){
		   if(data.status=='1') {
				alert_sucess("添加成功");
				go_sucess_url("/roles/index.html",1500); 
			}else if(data.status=='0') {
				alert_fail("添加失败");
			} 
	   }
	});
}

//添加角色
function updateFn(){
	var data = $("#myform").serialize();
	$.ajax({
		type: "post",
		url: PROJECT_CTX+"/roles/ajaxUpdate.html",
		data: data,
		dataType:"json",
		success: function(data){
			if(data.status==1) {
				alert_sucess("修改成功");
				go_sucess_url("/roles/index.html",1500); 
			}else if(data.status==0) {
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

function deleteFn(id){
	del_confirm("删除提示","您确认要删除记录吗？",deleteFnOk,null,id);
}

function deleteFnOk(id) {
	$.ajax({
		   url: PROJECT_CTX+"/roles/ajaxDelete.html",
		   type: "post",
		   data: {"selectId":id},
		   dataType:"json",
		   success: function(data){
			   if(data.status=='0'){
				   alert_fail("删除失败");
			   }else{
				   alert_sucess("删除成功");
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
		var name = obj.name;
		var create_time = obj.create_time;
		var abtn = "";
		
		if(updateCurd(PROJECT_CURD)){
			abtn += getUpdateCurd(PROJECT_CTX+"/roles/update.html?id="+id,"修改");
		}
		
		if(deleteCurd(PROJECT_CURD)){
			abtn += getDeleteCurd("javascript:deleteFn("+id+")","删除");
		}
		
		var tr = "<tr>";
		tr+="<td class='text-center'><input type='checkbox' name='selectId' value="+id+"></td>";
		tr+="<td class='text-center'>"+id+"</td>";
		tr+="<td class='text-center'>"+name+"</td>";
		tr+="<td class='text-center'>"+create_time+"</td>";
		tr+="<td class='text-center'>"+abtn+"</td>";
		tr+="</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
}