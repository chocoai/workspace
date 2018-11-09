$(document).ready(function(){
	
    //新增，修改用户
    $('#myform input,#myform textarea').off('focus').on('focus', function(){
    	val_user('focus',this);
    });
    $('#myform input,#myform textarea').off('blur').on('blur', function(){
    	val_user('blur',this);
    });
    
    $('#addUser').on('click', function(){
    	if(val_user()){
    		insertFn();
    	}
    });
	
    $('#updateUser').on('click', function(){
    	if(val_user()){
    		updateFn();
    	}
    });
    
    //个人中心
    $('#myinfo_form input,#myinfo_form textarea').off('focus').on('focus', function(){
        val_person('focus',this);
        
    });
    $('#myinfo_form input,#myinfo_form textarea').off('blur').on('blur', function(){
        val_person('blur',this);
    });
    
    $("#update_myinfo").on('click', function(){
    	if(val_person()){
    		updateFnInfo();
    	}
    });
    
    //修改密码
    $('#resetpwd_form input,#resetpwd_form textarea').off('focus').on('focus', function(){
        val_password('focus',this);
    });
    $('#resetpwd_form input,#resetpwd_form textarea').off('blur').on('blur', function(){
        val_password('blur',this);
    });
    $('#resetpwd').on('click', function(){
        if(val_password()){
        	resetpwd();
        }
    });
    
   
    $("#searchBTN").on('click',function(){
    	reload();
    	searchFn();
    });
    
    $("#resetBTN").on('click',function(){
    	resetFn();
    });
    
    
    
    if($("input[name=type_id]").val()=='2') {
    	$("#age").val(0);//年龄不能为空，否则后台http请求报错
    }
    
    $("input[name=type_id]").change(function(){
    	var v =$(this).val();
    	if(v=='2'){
    		setVal_company();
    		$("#age").val(0);//年龄不能为空，否则后台http请求报错
    		$("#div_company").show();
    		$("#div_person").hide();
    	}else{
    		setVal_person();
    		$("#age").val("");
    		$("#div_company").hide();
    		$("#div_person").show();
    		$("#company_name").val("");
    	}
    });
    
    changePhone();
    
});


//变更手机号
function changePhone() {
	//修改手机号
    $('#rebind_phone').on('click',function(){
    	rebindPhoneDialog();
    	//获取验证码
    	$('#send_code').off('click').on('click',function(){
    		if($(this).hasClass('my-disabled')){
    			return false;
    		}
    		sendSMSCode('#send_code', '#rebind_msg', "  ", 3);
    	});
    	checkRebindSMSCode();
    });
}

//解绑手机号短信验证
function checkRebindSMSCode() {
    $('#confrim_sms_code').on('click',function(){
		var $div = $(this).parent().parent();
    	var smsCode = $div.find('input').val();
    	var $pmsg = $('#rebind_msg');
    	
    	if(!/^\d{6}$/.test(smsCode)){
    		$pmsg.html('请输入正确的验证码');
    	} else {
    		$.ajax({
    			url: PROJECT_CTX+"/info/ajaxConfirmSMSCode.html",
    			type : 'post',
    			data : {'code': smsCode},
    			dataType : 'json',
    			success : function(data) {
    				if(data.status==1) {
    					var htmlRebindPhone ='<div class="" style="padding:0px 20px 10px;">'+
    					'<p class="red" id="rebind_msg"></p>'+
    					'<p>为了保证您的帐号安全，请绑定新手机号</p>'+
    					'<p><div class="input-group">'+
    					'<span class="input-group-addon"><i class="fa fa-phone  fa-lg green"></i></span>'+
    					'<input type="text" class="form-control" value="" placeholder="手机号"></div></p>'+
    					'<p><div class="input-group">'+
    					'<span class="input-group-addon"><i class="fa fa-lock fa-lg green"></i></span>'+
    					'<input type="text" class="form-control " maxlength="6" placeholder="短信验证码">'+
    					'<a class="btn btn-primary form-control-feedback" style="height: 30px;width:120px;" href="javascript:void(0);" id="send_code">获取验证码</a>'+
    					'</div></p>'+
    					'<p><button class="btn btn-success btn-block" id="submit_rebind_phone">确定</button></p>'+
    					'</div>';
    					$('.rebindPhone-modal .bootbox-body').html(htmlRebindPhone);
    					
    					rebindPhone();
    				}else {
    					$pmsg.html(data.message)
    				}
    			}
    		});
    	}
    });
}

//绑定手机号
function rebindPhone(){
	var $div = $('#submit_rebind_phone').parent().parent();
	var $phone = $div.find('input').eq(0);
	var $smsCode = $div.find('input').eq(1);
	var $pmsg = $('#rebind_msg');
	
	$('#send_code').off('click').on('click',function(){
		if($(this).hasClass('my-disabled')){
			return false;
		}
		var phone = $phone.val();
		if(!/^1[3|5|7|8|][0-9]{9}$/.test(phone)) {
			$pmsg.html('请输入正确的手机号');
		} else {
			sendSMSCode('#send_code', '#rebind_msg', phone, 1);
		}
	});
	
	$('#submit_rebind_phone').on('click',function(){
		var phone = $phone.val();
		var smsCode = $smsCode.val();
		if(!/^1[3|5|7|8|][0-9]{9}$/.test(phone)) {
			$pmsg.html('请输入正确的手机号');
			return ;
		}
		if(!/^\d{6}$/.test(smsCode)){
    		$pmsg.html('请输入正确的验证码');
    		return ;
    	} 
		$.ajax({
			url : PROJECT_CTX+"/info/ajaxRebindPhone.html",
			type : 'post',
			data : {'phone':phone, 'code':smsCode},
			dataType : 'json',
			success : function(data){
				if(data.status==1) {
					var htmlRebindPhone ='<div class="layout-center" style="padding:0px 20px 10px;">'+
						'<h2><p class="green" style="">恭喜您！手机号绑定成功!</p></h2>'+
						'<p><button class="btn btn-success btn-block" onclick="closeBootbox();">确定</button></p>'+
						'</div>';
					$('.rebindPhone-modal .bootbox-body').html(htmlRebindPhone);
					$('#phone').val(data.message);
					//bootbox.hideAll();
				} else {
					$pmsg.html(data.message)
				}
			}
		});
	});
}

function closeBootbox() {
	bootbox.hideAll()
}
//个人验证设置
function setVal_person() {
	 //个人中心
    $('#myinfo_form input,#myinfo_form textarea').off('focus').on('focus', function(){
        val_person('focus',this);
        
    });
    $('#myinfo_form input,#myinfo_form textarea').off('blur').on('blur', function(){
        val_person('blur',this);
    });
    
    $("#update_myinfo").on('click', function(){
    	if(val_person()){
    		updateFnInfo();
    	}
    });
}
//公司验证设置
function setVal_company() {
	//个人中心
	$('#myinfo_form input,#myinfo_form textarea').off('focus').on('focus', function(){
		val_company('focus',this);
		
	});
	$('#myinfo_form input,#myinfo_form textarea').off('blur').on('blur', function(){
		val_company('blur',this);
	});
	
	$("#update_myinfo").on('click', function(){
		if(val_company()){
			updateFnInfo();
		}
	});
}

/*
 * 查询方法
 */
function searchFn(){
	var fromData = $("#queryform").serialize();
	$.ajax({
		   url: PROJECT_CTX+"/users/ajaxQuery.html",
		   type: "post",
		   data: fromData,
		   dataType:"json",
		   success: function(data){
			   var json = eval(data.message);
			   reloadFns(json);
		   }
		});
}



//用户状态
function statusSelect(status) {
	var $select = $("select[name='status']");
	$select.val(status);
}
//地址
function addressSelect(address) {
	var addressAry = address.split(",");
	if(addressAry.length!=0){
		$("#s1").val(addressAry[0]);
		change(1);
		$("#s2").val(addressAry[1]);
		change(2);
		$("#s3").val(addressAry[2]);
	}
	
}
//设置隐藏地址
function getAddress(){
	var str = $("#s1").val()+","+$("#s2").val()+","+$("#s3").val();
	$("#address").val(str);
}


//用户管理界面-多选删除
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

//用户管理界面-删除
function deleteFn(user_id){
	del_confirm("删除提示","您确认要删除记录吗？",deleteFnOk,null,user_id);
}
//用户管理界面-删除
function deleteFnOk(user_id){
	$.ajax({
		   url: PROJECT_CTX+"/users/ajaxDelete.html",
		   type: "post",
		   data: {"selectId":user_id},
		   dataType: "json",
		   success: function(data){
			   if(data.status=="1"){
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
	var tab_users = $("#userTable tbody");
	tab_users.html("");
	for (var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		var id = obj.id;
		var username = obj.username;
		var role_name = obj.role_name;
		var name = obj.name;
		var sex = obj.sex;
		var phone = obj.phone;
		var status = obj.status;
		var abtn = "";
		
		if(sex==0){
			sex = "男";
		}else if(sex==1){
			sex = "女";
		} else {
			sex = "未知";
		}
		
		if(status==1){
			status = "<span class='label label-success'>激活</span>";
		}else if(status==2){
			status = "<span class='label label-warning'>冻结</span>";
		} else if(status==3){
			status = "<span class='label label-danger'>下线</span>";
		} else {
			status = "<span class='label label-default'>未知</span>";
		}
//		abtn += getQueryCurd(PROJECT_CTX+"/users/update.html?id="+id+"&op=detail","查询");
		if(updateCurd(PROJECT_CURD)){
			abtn += getUpdateCurd(PROJECT_CTX+"/users/update.html?id="+id,"修改");
		}
		
		if(deleteCurd(PROJECT_CURD)){
			abtn += getDeleteCurd("javascript:deleteFn("+id+")","删除");
		}
			
		var tr = "<tr>";
		tr+="<td class='text-center'><input type='checkbox' name='selectId' value="+id+"></td>";
		tr+="<td class='text-center'>"+username+"</td>";
		tr+="<td class='text-center'>"+role_name+"</td>";
		tr+="<td class='text-center'>"+name+"</td>";
		tr+="<td class='text-center'>"+sex+"</td>";
		tr+="<td class='text-center'>"+phone+"</td>";
		tr+="<td class='text-center'>"+status+"</td>";
		tr+="<td class='text-center'>"+abtn+"</td>";
		tr+="</tr>";
		tab_users.append(tr);
	}
	reloadPage(json);
}

// 添加用户
function insertFn(){
	var data = $("#myform").serialize();
	$.ajax({
	   url: PROJECT_CTX+"/users/ajaxAdd.html",
	   type: "post",
	   data: data,
	   dataType:"json",
	   success: function(data,status,xhr){
		   if(data.status=="1") {
			   alert_sucess("添加成功");
			   go_sucess_url("/users/index.html",1500); 
		   } else {
			   alert_fail("添加失败");
		   }
	   }
	});
}

//修改用户
function updateFn(){
	var data = $("#myform").serialize();
	$.ajax({
	   url: PROJECT_CTX+"/users/ajaxUpdate.html",
	   type: "post",
	   data: data,
	   dataType:"json",
	   success: function(data,status,xhr){
		   if(data.status=="1") {
			   alert_sucess("修改成功");
			   go_sucess_url("/users/index.html",1500);
		   } else {
			   alert_fail("修改失败");
		   }
	   }
	});
}

//修改密码
function resetpwd(){
	var data = $("#resetpwd_form").serialize();
	$.ajax({
	   url: PROJECT_CTX+"/users/ajaxResetpwd.html",
	   type: "post",
	   data: data,
	   dataType:"json",
	   success: function(data,status,xhr){
		   if(data.status=="1") {
			   alert_sucess(data.message);
		   } else {
			   alert_fail(data.message);
		   }
	   }
	});
}

function updateFnInfo(){
	var data = $("#myinfo_form").serialize();
	$.ajax({
	   url: PROJECT_CTX+"/info/ajaxUpdate.html",
	   type: "post",
	   data: data,
	   dataType:"json",
	   success: function(data,status,xhr){
		   if(data.status=="1") {
			   alert_sucess("修改成功");
			   go_sucess_url("/capital/license.html",1500);
		   } else {
			   alert_fail("修改失败");
		   }
	   }
	});
}

//确认手机号弹窗口
function confirmPhoneDialog(){
    var messagetxt ='<div class="" style="padding:0px 20px 10px;">'+
            '<p class="red"></p>'+
            '<p>为了保证您的帐号安全，绑定手机号前请先进行安全验证</p>'+
            '<p>您的当前密保手机是：'+$('#phone').val()+'</p>'+
            '<p><div class="input-group"><span class="input-group-addon"><i class="fa fa-phone"></i></span><input type="text" class="form-control" value=""></div></p>'+
            '<p><button class="btn btn-success btn-block" id="confirm_phone">确定</button></p>'+
            '</div>';
    bootbox.dialog({
        title: "绑定手机号",
        className : 'rebindPhone-modal',
        closeButton: true,
//	    size: 'small',
        message: messagetxt
    });
    $('.rebindPhone-modal .modal-dialog').css('max-width','450px');
    return bootbox;
}

//确认手机号弹窗口
function rebindPhoneDialog(){
	var messagetxt ='<div class="" style="padding:0px 20px 10px;">'+
		'<p class="red" id="rebind_msg"></p>'+
		'<p>为了保证您的帐号安全，绑定手机号前请先进行安全验证</p>'+
		'<p>您的当前密保手机是：'+$('#phone').val()+'</p>'+
//  		'<p><div class="input-group"><span class="input-group-addon"><i class="fa fa-phone"></i></span><input type="text" class="form-control" value=""></div></p>'+
		
		'<p><div class="input-group">'+
		'<span class="input-group-addon"><i class="fa fa-lock fa-lg green"></i></span>'+
		'<input type="text" class="form-control " maxlength="6" placeholder="短信验证码">'+
		'<a class="btn btn-primary form-control-feedback" style="height: 30px;width:120px;" href="javascript:void(0);" id="send_code">获取验证码</a>'+
		'</div></p>'+
		'<p><button class="btn btn-success btn-block" id="confrim_sms_code">确定</button></p>'+
		'</div>';

		bootbox.dialog({
			title: "绑定手机号",
			className : 'rebindPhone-modal',
			closeButton: true,
//			size: 'small',
			message: messagetxt
		});
		$('.rebindPhone-modal .modal-dialog').css('max-width','450px');
}