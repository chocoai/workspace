$(document).ready(function(){
    //新增，修改用户
    $('#myform input,#myform textarea').off('focus').on('focus', function(){
    	val_products('focus',this);
    });
    $('#myform input,#myform textarea').off('blur').on('blur', function(){
    	val_products('blur',this);
    });
    
    $('#addProducts').on('click', function(){
    	if(val_products()){
    		insertFn();
    	}
    });
    

    $('#updateProducts').on('click', function(){
    	if(val_products()){
    		updateFn();
    	}
    });

    $("#addAppUpload").on('click',function(){
    	insertFnUpload();
    });
    
    $("#searchBTN").on('click',function(){
    	reload();
    	searchFn();
    });
    
    $("#resetBTN").on('click',function(){
    	resetFn();
    });
});


/*
 * 查询方法
 */
function searchFn(){
	$("#tab_products tbody").empty();
	$("#loading").show();
	setTimeout("loadListFn()",100);
}

function loadListFn(){
	var fromData = $("#queryform").serialize();
	$.ajax({
	   url: PROJECT_CTX+"/app/ajaxQuery.html",
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
	$.ajax({
		   url: PROJECT_CTX+"/app/ajaxAdd.html",
		   type: "post",
		   data: fromData,
		   dataType:"json",
		   success: function(data){
			   if(data.status=='0'){
				   alert_fail("添加失败");
			   }else{
				   alert_sucess("添加成功");
				   go_sucess_url("/app/index.html",1500); 
			   }
		   }
		});
}

//更新产品
function updateFn() {
	var data = $("#myform").serialize();
	$.ajax({
	   url: PROJECT_CTX+"/app/ajaxUpdate.html",
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
	var tab_rsas = $("#tab_products tbody");
	tab_rsas.html("");
	for (var i = 0; i < objs.length; i++) {
		var index = i+1;
		var obj = objs[i];
		var id = obj.id;
		var name = obj.name;
		var status = obj.status;
		var total_num = obj.total_num;
		var remain_num = obj.remain_num;
		var abtn = "";
		
		if(updateCurd(PROJECT_CURD)){
			abtn += getUpdateCurd(PROJECT_CTX+"/app/update.html?id="+id,"修改");
		}
		
		if(deleteCurd(PROJECT_CURD)){
			if(status !=0) {
				abtn += getDeleteCurd("javascript:deleteFn("+id+")","删除");
			} else {
				abtn += getDeleteCurd(null,"删除","false");
			}
		}

		if(status==0){
			status = "<span class='label label-default'>已删除</span>";
		}else if(status==1){
			status = "<span class='label label-success'>正常</span>";
		}else if(status==2){
			status = "<span class='label label-warning'>待审核</span>";
		}else if(status==3){
			status = "<span class='label label-danger'>未通过</span>";
		} else {
			status = "<span class='label label-default'>未知</span>";
		}
		
		var tr = "<tr>";
		//tr+="<td class='text-center'><input type='checkbox' name='selectId' value="+id+"></td>";
		tr+="<td class='text-center'>"+index+"</td>";
		tr+="<td class='text-center'>"+name+"</td>";
		tr+="<td class='text-center'>"+id+"</td>";
		/*tr+="<td class='text-center'>"+app_key+"</td>";*/
		/*tr+="<td class='text-center'>"+app_secret+"</td>";*/
		
		var icon_url = PROJECT_CTX+obj.app_icon;
	
		
		tr += "<td class='text-center'><img alt='' src='"+icon_url+"'/></td>";
		
		var url = obj.download_url;
		
		var shortUrl = url;
		
		if(url.length > 13){
			shortUrl = url.substring(0, 13)+"...";
		}
		tr += "<td class='text-center' title='"+url+"'>"+shortUrl+"</td>";
		
		tr += "<td class='text-center'>"+total_num+"</td>";
		tr += "<td class='text-center'>"+remain_num+"</td>";
		
		var appType = "";
		if(i ==0 || i == 1){
			appType = "软件";
		} else if(i == 2){
			appType = "游戏";
		} else if(i == 3){
			appType = "理财";
		} else if(i == 4) {
			appType = "社交";
		} else if(i == 5){
			appType = "生活";
		}else {
			appType = "影音";
		}
		tr += "<td class='text-center'>"+appType+"</td>";
		
		/*tr+="<td class='text-center'>"+create_time+"</td>";*/
		tr+="<td class='text-center'>"+status+"</td>";
		tr+="<td class='text-center'>"+abtn+"</td>";
		tr+="</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
	$("#loading").hide();
}
