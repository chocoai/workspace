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
		   url: PROJECT_CTX+"/users2/ajaxQueryremain.html",
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
	var tab_users = $("#userTableyhzx tbody");
	tab_users.html("");
	for (var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		var id = obj.id;
		var userID = obj.id;
		var nickname = obj.nickname;
		var sex =obj.sex;
		var city =obj.city;
		var province =obj.province;
		var country =obj.country;
		
		var income = obj.income ;
		var outcome = obj.outcome;
		var balance =obj.income - obj.outcome;
		
		var abtn = "";
		if(sex==1){
			sex = "男";
		}else if(sex==2){
			sex = "女";
		} else {
			sex = "未知";
		}
		//abtn += getUpdateCurd(PROJECT_CTX+"/yh.html?id="+id,"修改");
		var tr = "<tr>";
		tr+="<td class='text-center'><input type='checkbox' name='selectId' value="+id+"></td>";
		tr+="<td class='text-center'>"+userID+"</td>";
		tr+="<td class='text-center'>"+nickname+"</td>";
		tr+="<td class='text-center'>"+sex+"</td>";
		tr+="<td class='text-center'>"+city+"</td>";
		tr+="<td class='text-center'>"+province+"</td>";
		tr+="<td class='text-center'>"+country+"</td>";
		tr+="<td class='text-center'>"+income+".00</td>";
		tr+="<td class='text-center'>"+outcome+".00</td>";
		tr+="<td class='text-center'>"+balance+".00</td>";
		tr+="<td class='text-center'>"+abtn+"</td>";
		tr+="</tr>";
		tab_users.append(tr);
	}
	reloadPage(json);
}






