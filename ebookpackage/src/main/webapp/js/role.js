$(function(){
	$.ajax({
	 	type: "POST",
	   	url: "/ebookpackage/sys/role/queryCurrUserButtons",
	   	data: {"modular_id":$.cookie('modular_id')},
	   	async: false,
	   	dataType:'json',
	   	success: function(data){
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