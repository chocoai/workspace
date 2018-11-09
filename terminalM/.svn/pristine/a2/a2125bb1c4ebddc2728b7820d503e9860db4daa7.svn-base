$(function(){
	$.ajax({
	 	type: "POST",
	   	url: "/teacher-assistant/manage/sysrole/queryCurrUserButtons",
	   	data: {"modular_id":$.cookie('modular_id')},
	   	async: false,
	   	dataType:'json',
	   	success: function(data){
	   		roleArr = data;
	   		if(data != null && data != "[]"){
	   			$("a").each(function(){
		   			var _aText =$(this).text();
		   			
		   			var flag = false;
		   			for (var i=0;i<data.length;i++){
		   				if(_aText==data[i].BUTTON_NAME){
		   					if(data[i].GRANTED=='0'){
		   						flag = true;
		   					}
		   					break;
		   				}
		   			}
		   			if(flag){
		   				$(this).hide();
		   			}
		   		});
				
		   		$("input[type='button']").each(function(){
		   			var _aText =$(this).val();
		   			
		   			var flag = false;
		   			for (var i=0;i<data.length;i++){
		   				if(_aText==data[i].BUTTON_NAME){
		   					if(data[i].GRANTED=='0'){
		   						flag = true;
		   					}
		   					break;
		   				}
		   			}
		   			if(flag){
		   				$(this).hide();
		   			}
		   		});
	   		}
	   	}
	});
});

var roleArr;

function loadRole(){
	if(roleArr != null && roleArr != "[]"){
		$("a").each(function(){
   			var _aText =$(this).text();
   			
   			var flag = false;
   			for (var i=0;i<roleArr.length;i++){
   				if(_aText==roleArr[i].BUTTON_NAME){
   					if(roleArr[i].GRANTED=='0'){
   						flag = true;
   					}
   					break;
   				}
   			}
   			if(flag){
   				$(this).hide();
   			}
   		});
		
   		$("input[type='button']").each(function(){
   			var _aText =$(this).val();
   			
   			var flag = false;
   			for (var i=0;i<roleArr.length;i++){
   				if(_aText==roleArr[i].BUTTON_NAME){
   					if(roleArr[i].GRANTED=='0'){
   						flag = true;
   					}
   					break;
   				}
   			}
   			if(flag){
   				$(this).hide();
   			}
   		});
	}
}