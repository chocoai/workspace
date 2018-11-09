
$(function(){
	$("#searchBTN").click(function(){
    	reload();
		searchFn();
	})
    $("#resetBTN").on('click',function(){
    	resetFn();
    });
})


function searchFn(){
	var search_select = $("#search_select").val();
	var search_key = $("#search_key").val();
	
	if($.trim(search_key)=='' && $.trim(search_select)!='') {
		alert("请输入关键字!")
		return;
	}else if($.trim(search_key)!='' && $.trim(search_select)=='') {
		alert("请选择搜索条件!")
		return;
	}else {
		// 1 - 箩筐帐号, 2- 手机号
		if (search_select == 1) {
			if (search_key.length != 24) {
				alert("输入的箩筐帐号有误!");
				return;
			}
			$("#lk_uid").val(search_key);
			$("#phone").val("");
		} else if (search_select == 2) {
			$("#lk_uid").val("");
			$("#phone").val(search_key);
		}
	}
	var fromData = $("#myform").serialize();
	$.ajax({
		   url: PROJECT_CTX+"/charges/ajaxQuery.html",
		   type: "post",
		   data: fromData,
		   dataType:"json",
		   success: function(data){
			   var json = eval(data.message);
			   reloadFns(json);
		   }
		});
}


function reloadFns(json){
	var objs = eval(json.items);
	var tab_rsas = $("#tab_charges tbody");
	tab_rsas.html("");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		var id = obj.id;
		var product_name = obj.product_name;
		var order_no = obj.order_no;
		var lk_uid = obj.lk_uid;
		var phone = obj.phone;
		var money = obj.money;
		var create_tm = obj.createTimeStr;
		var status = obj.status;
		
		if(status==0){
			status ="<span class='label label-warning'>未完成</span>";
		}else if(status==1){
			status = "<span class='label label-success'>正常</span>";
		}else if(status==2){
			status = "<span class='label label-danger'>失败</span>";
		} else {
			status = "<span class='label label-default'>未知</span>";
		}
		
		var tr = "<tr>";
		tr+="<td class='text-center'>"+lk_uid+"</td>";
		tr+="<td class='text-center'>"+phone+"</td>";
		tr+="<td class='text-center'>"+product_name+"</td>";
		tr+="<td class='text-center'>"+order_no+"</td>";
		tr+="<td class='text-center'>"+money+"</td>";
		tr+="<td class='text-center'>"+create_tm+"</td>";
		tr+="<td class='text-center'>"+status+"</td>";
		tr+="</tr>";
		tab_rsas.append(tr);
	}
	reloadPage(json);
}