/***************************	用户验证 start	*****************************/
//资质信息页面验证规则
function val_license(status, elem) {
    var validate = [
        {
            name: 'name',
            rule: /^[\u4E00-\u9FA5]{2,20}$/,
            notice: '姓名必须为2-20个文字',
            notice_pp: '请填写真实的法人姓名'
        },
        {
            name: 'idcard',
            rule: /^((\d{15}|\d{17}[\dx])|([a-z]{1}\d{9}))$/i,
            notice: '请输入有效的身份证号',
            notice_pp: '请填写身份证号',
            ajax: PROJECT_CTX + '/check/idcard.html'
        },
        {
            name: 'front_image',
            rule: /^.+$/,
            notice: '请上传证件',
            notice_pp: '请上传证件'
        },
        {
            name: 'back_image',
            rule: /^.+$/,
            notice: '请上传证件',
            notice_pp: '请上传证件'
        },
        {
        	name: 'licence_image',
        	rule: /^.+$/,
        	notice: '请上传证件',
        	notice_pp: '请上传证件'
        },
        {
        	name: 'business_image',
        	rule: /^.+$/,
        	notice: '请上传证件',
        	notice_pp: '请上传证件'
        }
    ];
    if(status == 'focus'){
        focusInputs(validate, elem);
    }else if(status == 'blur'){
        blurInputs(validate, elem);
    }else{
        return checkFormInputs(validate);
    }
}
//资质信息个人页面验证规则
function license_person(status, elem) {
    var validate = [
        {
            name: 'mer_paper_no',
            rule: /^(\d{15}|\d{17}[\dx])$/i,
            notice: '请输入有效的身份证号',
            notice_pp: '请填写您的身份证号'
        }
    ];
    if(status == 'focus'){
        focusInputs(validate, elem);
    }else if(status == 'blur'){
        blurInputs(validate, elem);
    }else{
        return checkFormInputs(validate);
    }
}

//上传app验证
function val_apps(status, elem) {
    var validate = [
	    {
	    	name: 'app_url',
	    	rule: /^.+$/,
	    	notice: '请上传文件',
	    	notice_pp: '请上传文件'
	    },
        {
            name: 'md5',
            rule: /^.+$/,
            notice: '请上传的apk对应的MD5值',
            notice_pp: '请上传的apk对应的MD5值'
        },
        {
            name: 'package_name',
            rule: /^.+$/,
            notice: '请输入正确的包名',
            notice_pp: '请输入正确的包名'
        }
    ];

    if(status == 'focus'){
        focusInputs(validate, elem);
    }else if(status == 'blur'){
        blurInputs(validate, elem);
    }else{
        return checkFormInputs(validate);
    }
}


//上传skd验证
function val_sdks(status, elem) {
  var validate = [
	  {
		  name: 'name',
		  rule: /^.{2,20}$/,
		  notice: 'SDK名称必须为2-20个文字',
		  notice_pp: '请填写SDK名称'
	  },
      {
      	name: 'sdk_type',
      	rule: /^[0-9]*[1-9][0-9]*$/,
      	notice: '请选择产品类型',
      	notice_pp: '请选择产品类型'
      },
      {
		  name: 'version',
		  rule: /^.{2,20}$/,
		  notice: '请填写SDK版本',
		  notice_pp: '请填写SDK版本'
	  },
	  {
		  name: 'doc_url',
		  rule: /^.{2,20}$/,
		  notice: '请填写文档链接',
		  notice_pp: '请填写文档链接'
	  },
	  {
		  name: 'sdk_url',
		  rule: /^.+$/,
		  notice: '请填上传文件',
		  notice_pp: '请填上传文件'
	  },
      {
          name: 'md5',
          rule: /^.+$/,
          notice: '请上传的apk对应的MD5值',
          notice_pp: '请上传的apk对应的MD5值'
      },
      {
          name: 'remark',
          rule: /^[\s\S]{50,200}$/,
          notice: '介绍长度必须为50-200个文字',
          notice_pp: '请用50-200个文字介绍自己'
      }
  ];

  if(status == 'focus'){
      focusInputs(validate, elem);
  }else if(status == 'blur'){
      blurInputs(validate, elem);
  }else{
      return checkFormInputs(validate);
  }
}

//个人开发者页面验证规则
function val_person(status, elem) {
    var validate = [
        {
        	name: 'head_url',
        	rule: /^.+$/,
        	notice: '请上传图片',
        	notice_pp: '请上传图片'
        },
        {
            name: 'name',
            rule: /^[\u4E00-\u9FA5]{2,20}$/,
            notice: '姓名必须为2-20个文字',
            notice_pp: '请填写真实的姓名，方便联系'
        },
        {
            name: 'age',
            rule: /^([1-9]|[1-9][0-9]|1[01][0-9])$/,
            notice: '年龄格式不正确',
            notice_pp: '请输入实际年龄'
        },
        {
            name: 'email',
            rule: /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/,
            notice: '请输入有效的邮箱地址',
            notice_pp: '请填写个人的有效邮箱'
        },
        {
            name: 'phone',
            rule: /^1[3|5|7|8|][0-9]{9}$/,
            notice: '请输入有效的电话',
            notice_pp: '填写真实的手机号码，以便我们更好为您服务'
        },
        {
            name: 'remark',
            rule: /^[\s\S]{50,200}$/,
            notice: '介绍长度必须为50-200个文字',
            notice_pp: '请用50-200个文字介绍自己'
        },
    ];
    if(status == 'focus'){
        focusInputs(validate, elem);
    }else if(status == 'blur'){
        blurInputs(validate, elem);
    }else{
        return checkFormInputs(validate);
    }
}
//公司开发者页面验证规则
function val_company(status, elem) {
	 var validate = [
         {
         	name: 'head_url',
         	rule: /^.+$/,
         	notice: '请上传图片',
         	notice_pp: '请上传图片'
         },
         {
             name: 'company_name',
             rule: /^[\u4E00-\u9FA5]{2,20}$/,
             notice: '公司必须为2-20个文字',
             notice_pp: '请填写真实的公司，方便联系'
         },
         {
        	 name: 'name',
        	 rule: /^[\u4E00-\u9FA5]{2,20}$/,
        	 notice: '联系人必须为2-20个文字',
        	 notice_pp: '请填写真实的姓名，方便联系'
         },
         {
             name: 'email',
             rule: /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/,
             notice: '请输入有效的邮箱地址',
             notice_pp: '请填写个人的有效邮箱'
         },
         {
             name: 'phone',
             rule: /^1[3|5|7|8|][0-9]{9}$/,
             notice: '请输入有效的电话',
             notice_pp: '填写真实的手机号码，以便我们更好为您服务'
         },
         {
             name: 'remark',
             rule: /^[\s\S]{50,200}$/,
             notice: '介绍长度必须为50-200个文字',
             notice_pp: '请用50-200个文字介绍公司'
         },
     ];
    if(status == 'focus'){
        focusInputs(validate, elem);
    }else if(status == 'blur'){
        blurInputs(validate, elem);
    }else{
        return checkFormInputs(validate);
    }
}

//密码重置验证
function val_password(status, elem) {
    var validate = [
        {
            name: 'oldpassword',
            rule: /^.{6,20}$/,
            notice: '密码长度不能小于6位',
            notice_pp: '请输入原始密码',
            //ajax: location.origin + '/users/resetpwd.html'
        },
        {
            name: 'password',
            rule: /^.{6,20}$/,
            notice: '密码长度不能小于6位',
            notice_pp: '请输入新密码'
        },
        {
            name: 'repeatpassword',
            rule: /^.{6,20}$/,
            notice: '两次新密码不一样',
            notice_pp: '新再输入一次新密码'
        }
    ];
    if(status == 'focus'){
        focusInputs(validate, elem);
    }else if(status == 'blur'){
        blurInputs(validate, elem);
    }else{
        return checkFormInputs(validate);
    }
}

//箩筐账号密码修改
function val_userinfos_password(status, elem) {
    var validate = [
        {
            name: 'oldpassword',
            rule: /^.{6,20}$/,
            notice: '密码长度不能小于6位',
            notice_pp: '请输入原始密码',
            //ajax: location.origin + '/users/resetpwd.html'
        },
        {
            name: 'password',
            rule: /^.{6,20}$/,
            notice: '密码长度不能小于6位',
            notice_pp: '请输入新密码'
        },
        {
            name: 'repeatpassword',
            rule: /^.{6,20}$/,
            notice: '两次新密码不一样',
            notice_pp: '新再输入一次新密码'
        }
    ];
    if(status == 'focus'){
        focusInputs(validate, elem);
    }else if(status == 'blur'){
        blurInputs(validate, elem);
    }else{
        return checkFormInputs(validate);
    }
}

//添加用户
function val_user(status, elem) {
    var validate = [
        {
         	name: 'head_url',
         	rule: /^.+$/,
         	notice: '请上传图片',
         	notice_pp: '请上传图片'
         },
        {
            name: 'username',
            rule: /^.{2,20}$/,
            notice: '用户名必须为2-20内',
            notice_pp: '请填写用户名',
            ajax: PROJECT_CTX + '/check/userName.html'
        },
        {
            name: 'name',
            rule: /^[\u4E00-\u9FA5]{2,20}$/,
            notice: '姓名必须为2-20个文字',
            notice_pp: '请填写真实的姓名，方便联系'
        },
        {
            name: 'password',
            rule: /^.{6,20}$/,
            notice: '密码长度不能小于6位',
            notice_pp: '请输入新密码'
        },
        {
            name: 'repeatpassword',
            rule: /.{6,20}$/,
            notice: '密码长度不能小于6位',
            notice_pp: '请再次输入新密码'
        },
        {
            name: 'age',
            rule: /^([1-9]|[1-9][0-9]|1[01][0-9])$/,
            notice: '年龄格式不正确',
            notice_pp: '请输入实际年龄'
        },
        {
            name: 'email',
            rule: /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/,
            notice: '请输入有效的邮箱地址',
            notice_pp: '请填写个人的有效邮箱'
        },
        {
            name: 'phone',
            rule: /^1[3|5|7|8|][0-9]{9}$/,
            notice: '请输入有效的电话',
            notice_pp: '填写真实的手机号码，以便我们更好为您服务',
            ajax: PROJECT_CTX + '/check/phone.html'
        },
        {
            name: 'remark',
            rule: /^[\s\S]{20,200}$/,
            notice: '描述长度必须为20-200个文字',
            notice_pp: '请用20-200个文字描述'
        }
    ];
    if(status == 'focus'){
        focusInputs(validate, elem);
    }else if(status == 'blur'){
        blurInputs(validate, elem);
    }else{
        return checkFormInputs(validate);
    }
}

/***************************	用户验证 end	*****************************/

/***************************	权限验证 start	*****************************/

//权限验证，新增，修改
function val_menu(status, elem) {
    var validate = [
        {
            name: 'name',
            rule: /^.{2,10}$/,
            notice: '简称长度为2-10',
            notice_pp: '请填写简称',
            //ajax: PROJECT_CTX + '/menus/checkname.html'
        },
        {
            name: 'full_name',
            rule: /^.{2,20}$/,
            notice: '全称长度为2-20',
            notice_pp: '请填写全称'
        },
        {
            name: 'remark',
            rule: /^[\s\S]{10,100}$/,
            notice: '描述长度必须为10-100个文字',
            notice_pp: '请用10-100个文字描述'
        },
        {
            name: 'url',
            rule: /^.{1,100}$/,
            notice: '请输入有效的权限路径',
            notice_pp: '请填写权限路径'
        }
    ];
    if(status == 'focus'){
        focusInputs(validate, elem);
    }else if(status == 'blur'){
        blurInputs(validate, elem);
    }else{
        return checkFormInputs(validate);
    }
}

/***************************	权限验证 end	*****************************/

/***************************	角色验证 start	*****************************/
//角色验证，新增，修改
function val_roles(status, elem) {
    var validate = [
        {
            name: 'name',
            rule: /^.{2,10}$/,
            notice: '角色名称长度为2-10',
            notice_pp: '请填写角色名称',
            //ajax: PROJECT_CTX + '/roles/checkname.html'
        },
        {
            name: 'remark',
            rule: /^[\s\S]{10,100}$/,
            notice: '描述长度必须为10-100个文字',
            notice_pp: '请用10-100个文字描述'
        },
        {
            name: 'url',
            rule: /^.{1,100}$/,
            notice: '请输入有效的权限路径',
            notice_pp: '请填写权限路径'
        }
    ];
    if(status == 'focus'){
        focusInputs(validate, elem);
    }else if(status == 'blur'){
        blurInputs(validate, elem);
    }else{
        return checkFormInputs(validate);
    }
}

/***************************	角色验证 end	*****************************/


/***************************	新增app验证 start	*****************************/
//角色验证，新增，修改
function val_products(status, elem) {
    var validate = [
        {
            name: 'name',
            rule: /^.{2,10}$/,
            notice: '产品名称长度为2-10',
            notice_pp: '请填写产品名称',
            //ajax: PROJECT_CTX + '/roles/checkname.html'
        },
        {
        	name: 'app_type',
        	rule: /^[0-9]*[1-9][0-9]*$/,
        	notice: '请选择产品类型',
        	notice_pp: '请选择'
        },
        {
        	name: 'app_image',
        	rule: /^.+$/,
        	notice: '请上传图片',
        	notice_pp: '请上传图片'
        },
        {
        	name: 'percent',
        	rule: /^(0|[1-9][0-9]|100)$/,
        	notice: '请输入兑换比例',
        	notice_pp: '请输入兑换比例'
        },
        {
            name: 'remark',
            rule: /^[\s\S]{10,100}$/,
            notice: '描述长度必须为10-100个文字',
            notice_pp: '请用10-100个文字描述'
        }
    ];
    if(status == 'focus'){
        focusInputs(validate, elem);
    }else if(status == 'blur'){
        blurInputs(validate, elem);
    }else{
        return checkFormInputs(validate);
    }
}

/***************************	产品验证 end	*****************************/


/***************************	新增task验证 start	*****************************/
//角色验证，新增，修改
function val_task(status, elem) {
  var validate = [
      {
          name: 'appid',
          rule: /^.{2,10}$/,
          notice: '请选择应用',
          notice_pp: '请选择产品',
          //ajax: PROJECT_CTX + '/roles/checkname.html'
      }
  ];
  if(status == 'focus'){
      focusInputs(validate, elem);
  }else if(status == 'blur'){
      blurInputs(validate, elem);
  }else{
      return checkFormInputs(validate);
  }
}
/***************************	task验证 end	*****************************/



//清除pop提示信息
function removePopNotice() {
    $('.formpop').each(function(){
        $(this).remove();
    });
}
//清除表单中的提示信息
function removeNotice() {
    $('.formcheck').each(function(){
        $(this).remove();
    });
    $('.errorinput').each(function(){
        $(this).removeClass('errorinput');
    });
}
//清除表单中的错误提示信息
function removeErrorNotice() {
    $('i.error').each(function(){
        $(this).remove();
    });
    $('.errorinput').each(function(){
        $(this).removeClass('errorinput');
    });
}
//清除本身元素后面的提示信息
function removeSelfNotice(elem) {
    $(elem).parent().find('i').remove();
}

//显示pop提示信息
function showPopNotice(name, notice, status) {
    var noticeHtml = '';
    var input = null;
    noticeHtml += '<i class="formcheck formpop fa fa-exclamation-circle ' + status + '">&nbsp;';
    noticeHtml += notice;
    noticeHtml += '</i>';

    input = $('[name="' + name + '"]');
    input.parent().append(noticeHtml);
    input.removeClass('errorinput');
}
//显示验证提示信息
function showNotice(name, notice, status) {
    var noticeHtml = '';
    var input = null;
    noticeHtml += '<i class="formcheck' + status + '">&nbsp;';
    noticeHtml += notice;
    noticeHtml += '</i>';

    input = $('[name="' + name + '"]');
    input.parent().append(noticeHtml);

    if(status == ' fa fa-times-circle error'){
        input.addClass('errorinput');
    }else{
        input.removeClass('errorinput');
    }
}


//focus的提示
function focusInputs(validate, elem) {
	for(valKey in validate) {
        if(validate[valKey].name == elem.name) {
        	if($(elem).attr('readonly') == 'readonly'){
        		continue;
        	}
            removeSelfNotice(elem);
            showPopNotice(elem.name, validate[valKey].notice_pp, 'info');
        }
    }
}

//blur的提示
function blurInputs(validate, elem) {
    removePopNotice();
    for(valKey in validate) {
    	var name = elem.name;
        if(validate[valKey].name == name) {
            var rule = validate[valKey].rule;
            var url = validate[valKey].ajax;//失去焦点发送ajax请求地址
            var val = $(elem).val();
            if($(elem).attr('readonly') == 'readonly'){
        		continue;
        	}
            //如果是nomust，当未输入值，则不验证，
            if($(elem).attr('data-must') == 'nomust'){
                if(val==null||val.length==0) {
                	continue;
                }
            }
//            if($(elem).attr('data-must') == 'repeat'){
//                var thisVal = $(elem).val();
//                var prevVal = $(elem).parent().prev().find('input').val();
//                if(thisVal != prevVal){
//                    $(elem).parent().find('i').remove();
//                    showNotice(elem.name, validate[valKey].notice, ' fa fa-times-circle error');
//                    return false;
//                }
//            }
            if(url){
                if(rule.test(val)){
                	var djson = getFormInputs();
//                	var data = $("form").serialize();
                	var ajaxId = djson.id;
                	var ajaxDate = {"ajaxId":ajaxId,"ajaxVal":val}
//                	console.log(data)
//                	var dstr = "{"+name+":'"+val+"'}";
//        			var djson = eval('(' + dstr + ')'); 
                	//alert(data)
//                	printArray(djson)
//                	checkusername(val);
                	$.ajax({
                		type:"post",
                        url: url,
                        data: ajaxDate,
                        dataType: 'json',
                        success: function(json){
                            $(elem).parent().find('i').remove();
                            if(json.status == 1){//名字可以使用
                                showNotice(name, '　', ' fa fa-check-circle true');
                            }else{
                                showNotice(name, json.message, ' fa fa-times-circle error');
                            }
                        },
                        error: function(){
                            $(elem).parent().find('i').remove();
                            showNotice(name, '服务器繁忙！', ' fa fa-times-circle error');
                        }
                    })
                }else{
                    showNotice(name, validate[valKey].notice, ' fa fa-times-circle error');
                }
            }else{
                if(!rule.test(elem.value)) {
                    showNotice(name, validate[valKey].notice, ' fa fa-times-circle error');
                    return;
                }else{
                    showNotice(name, '　', ' fa fa-check-circle true');
                }
            }

            if(elem.name == 'password'){
                var thisVal = $(elem).val();
                var prevVal = $('[name="repeatpassword"]:input').val();
                if(prevVal&&thisVal != prevVal){
                    $('[name="repeatpassword"]:input').parent().find('i').remove();
                    showNotice('repeatpassword', '两次输入的密码不一致', ' fa fa-times-circle error');
                    return ;
                }
            }

            if(elem.name == 'repeatpassword'){
                var thisVal = $(elem).val();
                var prevVal = $('[name="password"]:input').val();
                if(thisVal != prevVal){
                    $(elem).parent().find('i').remove();
                    showNotice(elem.name, '两次输入的密码不一致', ' fa fa-times-circle error');
                    return ;
                }
            }

        }
    }
}

//检查表单中的input
function checkFormInputs(validate) {
    var rule = null;
    var inputName = '';
    var inputValue = '';
    var data = getFormInputs();
    removeNotice();
    var result = checkFormLocal(validate, data);
    return result;
}

//表单的本地验证
function checkFormLocal(validate, data) {
    for(key in data) {
        inputName = key;
        inputValue = data[key];
        //alert(inputName+"---"+inputValue)
        for(valKey in validate) {
            if(validate[valKey].name == inputName) {
            	if($('[name="' + validate[valKey].name + '"]').attr('readonly') == 'readonly'){
            		continue;
            	}
                if($('[name="' + validate[valKey].name + '"]').attr('data-must') == 'nomust'){
                	if(!$('[name="' + validate[valKey].name + '"]').val()) {
                		continue;
                	}
                }

                if($('[name="' + validate[valKey].name + '"]').attr('data-must') == 'repeat'){
                    var thisVal = $('[name="' + validate[valKey].name + '"]').val();
                    var prevVal = $('[name="' + validate[valKey].name + '"]').parent().prev().find('input').val();
                    if(thisVal != prevVal){
                    	$('[name="' + validate[valKey].name + '"]').parent().find('i').remove();
                        showNotice(inputName, validate[valKey].notice, ' fa fa-times-circle error');
                        return false;
                    }
                }
                
                
                //alert($('[name="' + validate[valKey].name + '"]').attr('name'))
                if($('[name="' + validate[valKey].name + '"]').attr('name') == 'password'){
                	var thisVal = $('[name="' + validate[valKey].name + '"]').val();
                    var prevVal = $('[name="repeatpassword"]:input').val();
                    if(prevVal&&thisVal != prevVal){
                        $('[name="repeatpassword"]:input').parent().find('i').remove();
                        showNotice('repeatpassword', '两次输入的密码不一致', ' fa fa-times-circle error');
                        return ;
                    }
                }

                rule = validate[valKey].rule;
                url = validate[valKey].ajax;
                //alert(inputValue);
                if(!rule.test(inputValue)) {
                	showNotice(inputName, validate[valKey].notice, ' fa fa-times-circle error');
                    return false;
                } else {
                	if(url){
                		var djson = getFormInputs();
//                		var dstr = "{"+inputName+":'"+inputValue+"'}";
//            			var djson = eval('(' + dstr + ')'); 
                		var ajaxId = djson.id;
                    	var ajaxDate = {"ajaxId":ajaxId,"ajaxVal":inputValue}
                		if(!checkAjaxForm(url,ajaxDate,inputName)){
                    		return false;
                    	}
                	} 
                }
            }
        }
    }
    return true;
}

//提交时候验证同步ajax
function checkAjaxForm(url,data,name) {
	var result=false;
	//alert(name);
	$.ajax({
		async:false,
        url: url,
        data: data,
        dataType: 'json',
        success: function(json){
        	 //$(elem).parent().find('i').remove();
        	//删除之前的showNotice
        	$('[name="' + name + '"]').parent().find('i').remove();
            if(json.status == 1){//名字可以使用
                //showNotice(name, '　', ' fa fa-check-circle true');
                result = true;
            }else{
                showNotice(name, json.message, ' fa fa-times-circle error');
                result = false;
            }
        },
        error: function(){
            showNotice(name, '服务器繁忙！', ' fa fa-times-circle error');
            result = false;
        }
    })
    return result;
}


//获取表单中input集合
function getFormInputs(form) {
    if(!form) form = 'form';
    var data = {};
    var name = '';
    var value = '';

   /* $(form).find('input[name][type="text"],input[name][type="password"]').each(function(){
        name = $(this).attr('name');
        value = getInputValue(this);
        data[name] = value;
    })*/
    $(form).find('input[name][name!=""]').each(function(){
        name = $(this).attr('name');
        value = getInputValue(this);
        data[name] = value;
    })
    $(form).find('textarea[name][name!=""]').each(function(){
        name = $(this).attr('name');
        value = getInputValue(this);
        data[name] = value;
    })
    $(form).find('select[name][name!=""]').each(function(){
        name = $(this).attr('name');
        value = getInputValue(this);
        data[name] = value;
    })
    //printArray(data)
    return data;
}


//获取input的值
function getInputValue(input) {
    var input = $(input);
    var type = input.attr('type');
    var value = null;
    if(type == 'checkbox') {
        value = input.attr('checked') ? 1 : 0;
    } else {
        value = input.val();
    }
    return value;
}




