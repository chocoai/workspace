$(document).ready(function(){
	var iconclick = false;
	var bidflag = true;
	var contractflag = true;
	document.onclick = function() {
		
		if(!bidflag && !iconclick){
			$("#bidid").children("a").css({color:"#333"});
			$("#bidid").children("ul").stop(true, true).slideUp("fast");
			bidflag = true;
		}
		if(!contractflag && !iconclick ){
			$("#contractid").children("a").css({color:"#333"});
			$("#contractid").children("ul").stop(true, true).slideUp("fast");
			contractflag = true;
		}
		 iconclick = false;
	}
	$("#bidid").click(function() {
		if(bidflag){
			iconclick = true;
			$(this).children("a").css({color:"#333"});
			if($(this).find("li").length > 0){
				$(this).children("ul").stop(true, true).slideDown(100);
			} 
			bidflag = false;
			
			$("#contractid").children("a").css({color:"#333"});
			$("#contractid").children("ul").stop(true, true).slideUp("fast");
			contractflag = true;
		}else{
			iconclick = true;
			$(this).children("a").css({color:"#333"});
			$(this).children("ul").stop(true, true).slideUp("fast");
			bidflag = true;
		}
	});


	$("#contractid").click(function() {
		if(contractflag){
			iconclick = true;
			$(this).children("a").css({color:"#333"});
			if($(this).find("li").length > 0){
				$(this).children("ul").stop(true, true).slideDown(100);
			} 
			contractflag = false;
			
			$("#bidid").children("a").css({color:"#333"});
			$("#bidid").children("ul").stop(true, true).slideUp("fast");
			bidflag = true;
		}else{
			iconclick = true;
			$(this).children("a").css({color:"#333"});
			$(this).children("ul").stop(true, true).slideUp("fast");
			contractflag = true;
		}
	});
})

