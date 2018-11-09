$(function() {
		//验证码
	    $("#vcodeImg").on("click",'img',function(){
			var src = $("#contextPath").val()+'/open/getJPGCode?'  + Math.random();
			$(this).attr("src", src); 
		});
		//回车事件绑定
		document.onkeydown=function(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
			if(e && e.keyCode==13){ 
				$('#login').click();
			}
		}; 
	
		//登录操作
	    $('#login').click(function(){
	    	
	        var username = $('#username').val();
	        var password = $('#password').val();
	        var vcode = $('#vcode').val();
	        if(username == '') {
	        	//return layer.tips("请输入账号！","#username"),!1;
	        	return layer.msg('请输入账号！',function(){}),!1;
	        }
	        if(password == '') {
	        	return layer.msg('请输入密码！',function(){}),!1;
	        }
	        if(vcode.length !=4){
	    		return layer.msg('验证码的长度为4位！',function(){}),!1;
	    	}
            var password = MD5(password);
            var ersionNum = 45;
        	data = {password:password,userCode:username,vcode:vcode,ersionNum:ersionNum,rememberMe:$("#rememberMe").is(':checked')};
	        var load = layer.load();
	        $.post($("#contextPath").val()+"/login/login",data ,function(result){
	        	layer.close(load);
	    		if(result && result.status && result.status == 'login_success'){
	    			//登录返回
	    			window.location.href= $("#contextPath").val()+"/System/index";
	    		}else{
	    			$('#password').val('');
	    			$('#vcode').val('');
	    			$('#password').focus();
	    			$('#vcodeImg img').click();
	    			layer.msg(result.message);
	    			return;
	    		}
	        	
	    	},"json");
	        
	    });
});
