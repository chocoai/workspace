
$(document).ready(function() {
	//placeholder兼容ie9以下版本
	$('input, textarea').placeholder();
    
	//注册验证
//    $('#regist_form input').off('blur').on('blur', function(){
//    	checkRegist('blur',this);
//    });
    
    /*$('#forgotPwd_form input').off('blur').on('blur', function(){
    	checkforgotPwd('blur',this);
    });*/
	
	$('#login').on('click', function(){
		if(checkLogin(null,null,"login_form")){
			login();
		}
	});
	
	$('#regist').on('click', function(){
		if(checkRegist(null,null,"regist_form")){
			regist();
		} 
	});
//	$('#forgotPwd').on('click', function(){
//		if(checkforgotPwd(null,null,"forgotPwd_form")){
//			forgotPwd();
//		} 
//	});
	
	$('#resetPwd').on('click', function(){
		if(checkresetPwd(null,null,"resetPwd_form")){
			resetPwd();
		} 
	});
	
	//tooltip提示，身份类型提示
	$('[data-toggle="tooltip"]').tooltip();
	
	
	sendCode();
	
});

//验证码
function changeValidateCode() {
	$("#validate_code").attr({src:PROJECT_CTX+"/createValidateCode.html?date=" + new Date().getTime()});
}

//登录绑定回车时间
$("#login_form #username,#login_form #password,#login_form #verifyCode").keydown(function(e){
	if(e.keyCode=="13"){
		if(checkLogin(null,null,"login_form")){
			$("#login").focus();
    	}
	}
});

//找回密码绑定回车时间
$("#forgotPwd_form #phone,#forgotPwd_form #verifyCode").keydown(function(e){
	if(e.keyCode=="13"){
		if(checkforgotPwd(null,null,"forgotPwd_form")){
			$("#forgotPwd").focus();
		}
	}
});

/**************		登录界面Start	****************/

function checkLogin(status, elem, form){
	var validate = 	{
     	'username': {
     		emptyTip:"请输入用户名",
     		type: "text",
     	},
     	'password': {
     		emptyTip: "请输入密码",
     		type: "text"
     	},
     	'verifyCode': {
     		emptyTip: "请输入验证码",
     		type: "text"
     	}
	};
	if(status == 'blur'){
        blurInputs(validate, elem);
    } else {
    	return checkFormInputs(validate,form);
    }
}

//用户登录
function login(){
	var data = $("#login_form").serialize();
//	alert(data);
	$.ajax({
	   url: PROJECT_CTX+"/logon.html",
	   type: "post",
	   data: data,
	   dataType:"json",
	   success: function(data,status,xhr){
		   if(data.status=="1") {
			   window.location = PROJECT_CTX+"/main.html"		   
		   }else{
			   changeValidateCode();
			   $("#error_msg").html(data.message).removeClass("hidden");
		   }
	   }
	});
}
/**************		登录界面End	****************/

/**************		注册界面Start	****************/
function sendCode() {
	$('#send_code').on('click',function(e){
		if($(this).hasClass('my-disabled')){
			return false;
		}
		sendMessage();
		return false;
	});
}
/*-----------------发送短信--------------------------*/  
function sendMessage() {  
    var phone=$.trim($("#phone").val());
    var type=$.trim($("#type").val());//验证类型
    var reg= /^1[3|5|7|8|][0-9]{9}$/;
    if(phone != ""){ 
    	if(reg.test(phone)){
    		sendSMSCode('#send_code', '#error_msg', phone, type);
    	} else {
    		$("#error_msg").removeClass("hidden").html("请输入正确的手机号！");  
    	}
    }else{  
    	$("#error_msg").removeClass("hidden").html("手机号码不能为空！");  
    }  
}  

function checkRegist(status, elem, form){
	var validate = 	{
     	'username': {
     		reg: /^.{2,20}$/,
     		errTip: '用户名格式不正确',
     		emptyTip:"请输入注册用户名",
     		type: "text",
     		ajax: PROJECT_CTX +"/check/userName.html",
     		ajaxErrTip:"用户已被使用"
     	},
     	'password': {
     		reg: /^.{6,20}$/,
     		errTip: '密码长度不能小于6位',
     		emptyTip: "请输入密码",
     		type: "text"
     	},
     	'verifyCode': {
     		emptyTip: "请输入验证码",
     		type: "text"
     	}
	};
	if(status == 'blur'){
        blurInputs(validate, elem);
    } else {
    	return checkFormInputs(validate,form);
    }
}

//用户注册
function regist(){
	var data = $("#regist_form").serialize();
	//alert(data);
	$.ajax({
	   url: PROJECT_CTX+"/_regist.html",
	   type: "post",
	   data: data,
	   dataType:"json",
	   success: function(data,status,xhr){
		   if(data.status=="1") {
			   var messagetxt ="<div class='layout-center'>"+
			   "	<h1>恭喜您，注册成功 ！</h1>"+
			   "	<p class='help-block' style='margin-bottom:50px'>补全信息后，即可创建广告</p>"+
			   "	<p class='label label-primary  label-large' style='margin:30px;'>"+
			   "		<span id='sect'>5</span>秒后讲自动跳转到箩筐管理后台 "+
			   "	</p>"+
			   "	<p style='margin-top:20px'><a class='btn btn-success' href= '"+PROJECT_CTX +"/info/myinfo.html'>进入管理后台</a></p>"+
			   "</div>"; 
				bb_dialog("账号注册", messagetxt);
				//跳转倒计时
				var timer = window.setInterval("timego('/info/myinfo.html')",1000);
			   //window.location = PROJECT_CTX+"/main.html"		   
		   }else {
			   changeValidateCode();
			   $("#error_msg").html(data.message).removeClass("hidden");
		   }
	   }
	});
}
//注册成功跳转时间
var sect = 5;
function timego(url){
    sect--;
    $("#sect").html(sect);
    if(sect==0){
        location.href= PROJECT_CTX + url;
        return false;
    }
}

/**************		注册界面End	****************/

/**************		找回密码页面Start	****************/
function checkforgotPwd(status, elem, form){
	var validate = 	{
     	'phone': {
     		reg: /^1[3|5|7|8|][0-9]{9}$/,
     		errTip: '请输入有效的手机号',
     		emptyTip: "请输入手机号",
     		type: "text"
     	},
     	'code': {
     		emptyTip: "请输入临时密码",
     		type: "text"
     	},
     	'verifyCode': {
     		emptyTip: "请输入验证码",
     		type: "text"
     	}
	};
	if(status == 'blur'){
        blurInputs(validate, elem);
    } else {
    	return checkFormInputs(validate,form);
    }
}

function forgotPwd() {
//	var type=$("#type").val();//验证类型
//	var phone = $("#phone").val();
	var data = $("#forgotPwd_form").serialize();
	$.ajax({
		type: "post",
		url: PROJECT_CTX +"/pwd/forgotPwd.html",
		data:data,
		dataType:"json",
		success: function(data){
			console.log(data)
			if(data.status==1) {
//				var obj = data.message;
//				$("#error_msg").html(obj.id).removeClass("hidden");
				
			} else {
				$("#error_msg").html(data.message).removeClass("hidden");
				 changeValidateCode();
			}
		}
	});
}

function checkresetPwd(status, elem, form){
	var validate = 	{
			'password': {
	     		reg: /^.{6,20}$/,
	     		errTip: '密码长度不能小于6位',
	     		emptyTip: "请输入密码",
	     		type: "text"
	     	},
	     	'repeatpassword': {
	     		reg: /^.{6,20}$/,
	     		errTip: '密码长度不能小于6位',
	     		emptyTip: "请输入密码",
	     		type: "text"
	     	},
	};
	if(status == 'blur'){
		blurInputs(validate, elem);
	} else {
		return checkFormInputs(validate,form);
	}
}

function resetPwd() {
//	var type=$("#type").val();//验证类型
//	var phone = $("#phone").val();
	var data = $("#resetPwd_form").serialize();
	$.ajax({
		type: "post",
		url: PROJECT_CTX +"/passport/ajaxResetpwd.html",
		data:data,
		dataType:"json",
		success: function(data){
			//console.log(data)
			if(data.status==1) {
				
				$("#error_msg").html("").addClass("hidden");
				var messagetxt ="<div class='layout-center'>"+
				   "	<h1>恭喜您，密码修改成功 ！</h1>"+
				   "	<p class='label label-primary  label-large' style='margin:30px;'>"+
				   "		<span id='sect'>5</span>秒后讲自动跳转到登录页面 "+
				   "	</p>"+
				   "	<p style='margin-top:20px'><a class='btn btn-success' href= '"+PROJECT_CTX +"/login.html'>登录账号</a></p>"+
				   "</div>"; 
					bb_dialog("找回密码", messagetxt);
					//跳转倒计时
					var timer = window.setInterval("timego('/login.html')",1000);
//				var txt = "恭喜您，密码已修改成功，<br>请及时<a href='"+PROJECT_CTX+"/login.html'>登录账号</a>!";
//				bb_alert("找回密码", txt);
			} else {
				$("#error_msg").html(data.message).removeClass("hidden");
				changeValidateCode();
			}
		}
	});
}

/**************		找回密码页面End	****************/

function blurInputs(validate, elem) {
	var $em = $("#error_msg");
	$elem = $(elem);
	$.each(validate,function(vname,value){
		var name = $elem.attr("name");
		if(vname == name) {
			var reg = value.reg;
            var url = value.ajax;//失去焦点发送ajax请求地址
            var val = $elem.val();
            var emptyTip = value.emptyTip;
            var errTip = value.errTip;
            var ajaxErrTip = value.ajaxErrTip;
            if(val) {
            	if(url) {
            		if(reg) {
            			if(reg.test(val)){
//            				var dstr = "{"+name+":'"+val+"'}";
//            				var djson = eval('(' + dstr + ')'); 
//            				var ajaxId = djson.id;
//                			var ajaxDate = {"ajaxId":ajaxId,"ajaxVal":val}
            				var ajaxDate = {"ajaxVal":val}; 
            				ajaxcheck(url,ajaxDate,ajaxErrTip); 
            			} else {
            				$em.html(errTip).removeClass("hidden");
            				return;
            			}
            		}
            	} else {
					if(reg) {
						if(!reg.test(val)){
							$em.html(errTip).removeClass("hidden");
							return;
						}
            		}
            	}
            	
            } else {
        		$em.html(emptyTip).removeClass("hidden");
        		return;
            }
            
		}
	});
}

function checkFormInputs(validate, form) {
	if(form) {
		form = '#'+form;
	} else {
		form = 'form';
	}
	var $em = $("#error_msg");
	var inputdata = $(form).serializeArray();
//	console.log(inputdata);
	var success = true;
	$.each(inputdata,function(index,obj) {
		var iName = obj.name;
        var iVal = obj.value;
        $elem = $("input[name='"+obj.name+"']");
        $.each(validate,function(vname,value){
    		if(vname == iName) {
    			var reg = value.reg;
    			var url = value.ajax;//失去焦点发送ajax请求地址
    			var emptyTip = value.emptyTip;
    			var errTip = value.errTip;
    			var ajaxErrTip = value.ajaxErrTip;
    			if(iVal) {
    				if(url) {
    					if(reg) {
    						if(reg.test(iVal)){
//    							var dstr = "{"+iName+":'"+iVal+"'}";
//    							var djson = eval('(' + dstr + ')'); 
    							var ajaxDate = {"ajaxVal":iVal}; 
    							ajaxcheck(url,ajaxDate,ajaxErrTip); 
    						} else {
    							$elem.focus();
    							$em.html(errTip).removeClass("hidden");
    							success = false;
    							return false;
    						}
    					}
    				} else {
    					if(reg) {
    						if(!reg.test(iVal)){
    							$elem.focus();
    							$em.html(errTip).removeClass("hidden");
    							success = false;
    							return false;
    						}
    					}
    				}
    				
    			} else {
    				$elem.focus();
    				$em.html(emptyTip).removeClass("hidden");
    				success = false;
    				return false;
    			}
    		}
    	});
        if(!success) {
        	return false;
        }
	});
	
	 if(success) {
     	return true;
     } else {
    	 return false;
     }
	
}

function ajaxcheck(url,djson,ajaxErrTip) {
	$.ajax({
		type: "post",
		url: PROJECT_CTX + url,
		data: djson,
		dataType: "json",
		success: function(data) {
			if(data.status==1) {
//				$("#error_msg").html("").addClass("hidden");
			} else {
				$("#error_msg").html(ajaxErrTip).removeClass("hidden");
			}
		}
	});
}


