$(document).ready(function(){
	

    
   
    $("#searchBTN").on('click',function(){
    	reload();
    	searchFn();
    });
    
    $("#resetBTN").on('click',function(){
    	resetFn();
    	searchFn();
    });
    

    
});







/*
 * 查询方法
 */
function searchFn(){
	var fromData = $("#queryform").serialize();
	$.ajax({
		   url: PROJECT_CTX+"/swjl/ajaxQueryswzx.html",
		   type: "post",
		   data: fromData,
		   dataType:"json",
		   success: function(data){
			   var json = eval(data.message);
			  // var objs = eval(json.items);
			   reloadFns(json);
		   }
		});
}





function reloadFns(json){
	var objs = eval(json.items);
	var tab_users = $("#swzxTable tbody");
	tab_users.html("");
	for (var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		var id = obj.id;
		var taskid = obj.taskid;
		var nickname = obj.nickname;
		var userid =obj.userid;
		var appid =obj.appid;
		var province =obj.province;
		var keyword =obj.keyword;
		
		var createtime= obj.createtime;
		var status = obj.status;
		
		
		var abtn = "";

		if(status==-1){
			status = "<span class='label label-success'>作废</span>";
		}else if(status==0){
			status = "<span class='label label-warning'>等待复制</span>";
		} else if(status==1){
			status = "<span class='label label-danger'>等待下载</span>";
		} else if(status==2) {
			status = "<span class='label label-default'>等待打开</span>";
		}else{
			status = "<span class='label label-default'>成功</span>";
		}
		//abtn += getUpdateCurd(PROJECT_CTX+"/yh.html?id="+id,"修改");
		var tr = "<tr>";
		tr+="<td class='text-center'><input type='checkbox' name='selectId' value="+id+"></td>";
		tr+="<td class='text-center'>"+taskid+"</td>";
		tr+="<td class='text-center'>"+nickname+"</td>";
		tr+="<td class='text-center'>"+userid+"</td>";
		tr+="<td class='text-center'>"+appid+"</td>";
		tr+="<td class='text-center'>"+keyword+"</td>";
		tr+="<td class='text-center'>"+createtime+"</td>";
		tr+="<td class='text-center'>"+status+"</td>";
	
		tr+="<td class='text-center'>"+abtn+"</td>";
		tr+="</tr>";
		tab_users.append(tr);
	}
	reloadPage(json);
}





