$(document).ready(function(){
	var iconclick = false;
	var bidflag = true;
	var customerflag = true;
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
		if(!customerflag && !iconclick ){
			$("#customerid").children("a").css({color:"#333"});
			$("#customerid").children("ul").stop(true, true).slideUp("fast");
			customerflag = true;
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
			$("#customerid").children("a").css({color:"#333"});
			$("#customerid").children("ul").stop(true, true).slideUp("fast");
			customerflag = true;
		}else{
			iconclick = true;
			$(this).children("a").css({color:"#333"});
			$(this).children("ul").stop(true, true).slideUp("fast");
			bidflag = true;
		}
	});
	
	$("#customerid").click(function() {
		if(customerflag){
			iconclick = true;
			$(this).children("a").css({color:"#333"});
			if($(this).find("li").length > 0){
				$(this).children("ul").stop(true, true).slideDown(100);
			} 
			customerflag = false;
			
			$("#contractid").children("a").css({color:"#333"});
			$("#contractid").children("ul").stop(true, true).slideUp("fast");
			contractflag = true;
			$("#bidid").children("a").css({color:"#333"});
			$("#bidid").children("ul").stop(true, true).slideUp("fast");
			bidflag = true;
		}else{
			iconclick = true;
			$(this).children("a").css({color:"#333"});
			$(this).children("ul").stop(true, true).slideUp("fast");
			customerflag = true;
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
			$("#customerid").children("a").css({color:"#333"});
			$("#customerid").children("ul").stop(true, true).slideUp("fast");
			customerflag = true;
		}else{
			iconclick = true;
			$(this).children("a").css({color:"#333"});
			$(this).children("ul").stop(true, true).slideUp("fast");
			contractflag = true;
		}
	});
})

