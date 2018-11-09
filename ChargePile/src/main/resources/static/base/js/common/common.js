$(document).ready(function(){
	//全选方法
	$("#selectAll").click(function(){
		var selected = $(this).prop("checked");
		$.each($("input[name=selectId]"), function() {
			if(selected){
				$(this).prop({"checked" : "checked"});
			}else{
				$(this).removeAttr("checked");
			}
		});
	});
})

 //alert_box提示
function alert_sucess(str, status){
    var txt = str || '保存成功';
    var icon = icon || 'fa fa-check-circle-o fa-lg';
    $('#alert_box').remove();
    var html = ' <div id="alert_box"><div class="alert alert-success"><p><i class="' + icon +'"></i>' + txt + '</p></div></div>';
    $('body').append(html);
    $('#alert_box').animate({zIndex:10001},200,function(){
        window.setTimeout(function(){
            $('#alert_box').animate({opacity: 0},500);
            $('#alert_box').animate({top: -100},500);
        },1500);
    });
}
//alert_box提示
function alert_fail(str, status){
    var txt = str || '保存失败';
    var icon = icon || 'fa fa-times-circle-o fa-lg';
    $('#alert_box').remove();
    var html = ' <div id="alert_box"><div class="alert alert-danger"><p><i class="' + icon +'"></i>' + txt + '</p></div></div>';
    $('body').append(html);
    $('#alert_box').animate({zIndex:10001},200,function(){
        window.setTimeout(function(){
            $('#alert_box').animate({opacity: 0},500);
            $('#alert_box').animate({top: -100},500);
        },1500)
    });
}

/**
 * 删除提示框
 * @param title		提示框标题，默认为操作提示
 * @param message	提示信息
 * @param okFn		确认后执行的方法
 * @param cancelFn	取消后执行的方法
 * @param obj		确认和取消方法的参数
 */
function del_confirm(title,message,okFn,cancelFn,obj) {
	title = title || "操作提示";
	message = message || "确定操作吗?";
	title = "<i class='fa fa-exclamation-circle yellow'></i> "+title;
	bootbox.confirm({
        title: title,
        closeButton: false,
        size: 'small',
        buttons: {
            confirm: {
                label: '是的，我要删除',
                className: 'btn-danger'
            },
            cancel: {
                label: '取消',
                className: 'btn-default'
            }
        },
        message: message,
        callback: function(result){
          if(result){
        	 !okFn || okFn(obj);
          } else {
        	  !cancelFn || cancelFn(obj);
          }
        }
    })
}

/**
 * 通用提示框
 * @param title		提示框标题，默认为操作提示
 * @param message	提示信息默认为 确定操作吗
 * @param okFn		确认后执行的方法
 * @param cancelFn	取消后执行的方法
 * @param obj		确认和取消方法的参数
 */
function common_confirm(title,message,okFn,cancelFn,obj) {
	title = title || "操作提示";
	message = message || "确定操作吗?";
	title = "<i class='fa fa-exclamation-circle yellow'></i> "+title;
	bootbox.confirm({
        title: title,
        closeButton: false,
        size: 'small',
        buttons: {
            confirm: {
                label: '确定',
                className: 'btn-primary'
            },
            cancel: {
                label: '取消',
                className: 'btn-default'
            }
        },
        message: message,
        callback: function(result){
          if(result){
        	 !okFn || okFn(obj);
          } else {
        	  !cancelFn || cancelFn(obj);
          }
        }
    })
}


/**
 * 提示框
 * @param title		提示框标题，默认为操作提示
 * @param message	提示信息
 */
function bb_alert(title,message) {
	title = title || "操作提示";
	message = message || "确定操作吗?";
	title = "<i class='fa fa-exclamation-circle yellow'></i> "+title;
	bootbox.alert({  
		title: title,  
		closeButton: false,
        size: 'small',
        buttons: {  
           ok: {  
                label: '确认',  
                className: 'btn-success'  
            }  
        },  
        message: message
    });  
}

/**
 * 提示框
 * @param title		提示框标题，默认为操作提示
 * @param message	提示信息
 */
function bb_dialog(title,message) {
	var title = title || "操作提示";
	title = "<i class='fa fa-exclamation-circle yellow'></i> "+title;
	bootbox.dialog({  
		title: title,  
		message: message,
		closeButton: false,
        //size: 'small',
        /*buttons: {
            Cancel: {
                label: "Cancel",
                        className: "btn-default",
                        callback: function () {
                    alert("Cancel");
                }
            }
            , OK: {
                label: "OK",
                        className: "btn-primary",
                        callback: function () {
                    alert("OK");
                }
            }
       } */
   });  
}

//设置全局方法，ajax超时后重新登录
//$.ajaxSetup({
//	complete:function (xhr,status){
//		//alert(PROJECT_CTX+"/index.html")
//		 if(xhr.getResponseHeader("sessionstatus")=="timeout"){
//	         alert("登录超时,请重新登入");
//	         location.href = PROJECT_CTX+"/index.html"; //如重定向到登陆页面   
//	         return ;
//	     };
//	}
//});

//打印数组,测试用
function printArray(arr){  
    for(var key in arr){  
        if(typeof(arr[key])=='array'||typeof(arr[key])=='object'){//递归调用    
            print_array(arr[key]);  
        }else{  
            document.write(key + ' = ' + arr[key] + '<br>');  
        }  
    }  
} 

//1111 查询-添加-修改-删除
function insertCurd(curdParam){
	return curdParam.substring(1,2)==1;
}

function updateCurd(curdParam){
	return curdParam.substring(2,3)==1;
}

function deleteCurd(curdParam){
	return curdParam.substring(3,4)==1;
}

/**
 * @see 查询操作模板
 * @param url
 * @param title
 * @returns {String}
 */
function getQueryCurd(url,title,status){
	var updateStr = "";
	status = status || "true" ;
	if (status=="false") {
		updateStr +="<a href='javascript:void(0)' class='table-link primary my-disabled' title='"+title+"'>";
		updateStr +=" <span class='fa-stack'>";
		updateStr +="   <i class='fa fa-square fa-stack-2x'></i>";
		updateStr +="   <i class='fa fa-search-plus fa-stack-1x fa-inverse'></i>";
		updateStr +=" </span>";
		updateStr +="</a>";
	} else {
		updateStr +="<a href="+url+" class='table-link primary' title='"+title+"'>";
		updateStr +=" <span class='fa-stack'>";
		updateStr +="   <i class='fa fa-square fa-stack-2x'></i>";
		updateStr +="   <i class='fa fa-search-plus fa-stack-1x fa-inverse'></i>";
		updateStr +=" </span>";
		updateStr +="</a>";
	}
	return updateStr;
}

/**
 * @see 修改操作模板
 * @param url
 * @param title
 * @returns {String}
 */
function getUpdateCurd(url,title,status){
	var updateStr = "";
	status = status || "true" ;
	if (status=="false") {
		updateStr +="<a href='javascript:void(0)' class='table-link my-disabled' title='"+title+"'>";
		updateStr +=" <span class='fa-stack'>";
		updateStr +="   <i class='fa fa-square fa-stack-2x'></i>";
		updateStr +="   <i class='fa fa-pencil fa-stack-1x fa-inverse'></i>";
		updateStr +=" </span>";
		updateStr +="</a>";
	} else {
		updateStr +="<a href="+url+" class='table-link ' title='"+title+"'>";
		updateStr +=" <span class='fa-stack'>";
		updateStr +="   <i class='fa fa-square fa-stack-2x'></i>";
		updateStr +="   <i class='fa fa-pencil fa-stack-1x fa-inverse'></i>";
		updateStr +=" </span>";
		updateStr +="</a>";
	}
	return updateStr;
}

/**
 * @see 警告操作模板
 * @param url
 * @param title
 * @returns {String}
 */
function getWarningCurd(url,title){
	var updateStr = "";
	updateStr +="<a href="+url+" class='table-link warning' title='"+title+"'>";
	updateStr +=" <span class='fa-stack'>";
	updateStr +="   <i class='fa fa-warning fa-stack-2x'></i>";
	updateStr +="   <i class='fa fa-pencil fa-stack-1x fa-inverse'></i>";
	updateStr +=" </span>";
	updateStr +="</a>";
	return updateStr;
}

/**
 * @see 删除操作模板
 * @param url
 * @param title
 */
function getDeleteCurd(url,title,status){
	var deleteStr = "";
	status = status || "true" ;
	if (status=="false") {
		deleteStr +="<a href='javascript:void(0)' class='table-link danger my-disabled' title='"+title+"'>";
		deleteStr +=" <span class='fa-stack '>";
		deleteStr +="   <i class='fa fa-square fa-stack-2x'></i>";
		deleteStr +="   <i class='fa fa-trash-o fa-stack-1x fa-inverse'></i>";
		deleteStr +=" </span>";
		deleteStr +="</a>"
	} else {
		deleteStr +="<a href='"+url+"' class='table-link danger' title='"+title+"'>";
		deleteStr +=" <span class='fa-stack '>";
		deleteStr +="   <i class='fa fa-square fa-stack-2x'></i>";
		deleteStr +="   <i class='fa fa-trash-o fa-stack-1x fa-inverse'></i>";
		deleteStr +=" </span>";
		deleteStr +="</a>"
	}
	return deleteStr;
}

/**
 * @see 审核通过模板
 * @param url
 * @param title
 */
function getApproveCurd(url,title,status){
	var deleteStr = "";
	status = status || "true" ;
	if (status=="false") {
		deleteStr +="<a href='javascript:void(0)' class='table-link my-disabled' title='"+title+"'>";
		deleteStr +=" <span class='fa-stack '>";
		deleteStr +="   <i class='fa fa-square fa-stack-2x'></i>";
		deleteStr +="   <i class='fa fa-check fa-stack-1x fa-inverse'></i>";
		deleteStr +=" </span>";
		deleteStr +="</a>"
	} else {
		deleteStr +="<a href='"+url+"' class='table-link' title='"+title+"'>";
		deleteStr +=" <span class='fa-stack '>";
		deleteStr +="   <i class='fa fa-square fa-stack-2x'></i>";
		deleteStr +="   <i class='fa fa-check fa-stack-1x fa-inverse'></i>";
		deleteStr +=" </span>";
		deleteStr +="</a>"
	}
	
	return deleteStr;
}

/**
 * @see 审核不通过模板
 * @param url
 * @param title
 */
function getNotApproveCurd(url,title,status){
	var deleteStr = "";
	status = status || "true" ;
	if (status=="false") {
		deleteStr +="<a href='"+url+"' class='table-link danger my-disabled' title='"+title+"'>";
	} else {
		deleteStr +="<a href='"+url+"' class='table-link danger' title='"+title+"'>";
	}
	
	deleteStr +="<a href='"+url+"' class='table-link danger' title='"+title+"'>";
	deleteStr +=" <span class='fa-stack '>";
	deleteStr +="   <i class='fa fa-square fa-stack-2x'></i>";
	deleteStr +="   <i class='fa fa-times fa-stack-1x fa-inverse'></i>";
	deleteStr +=" </span>";
	deleteStr +="</a>"
	return deleteStr;
}

//上线
function getUpStatusCurd(url,title,status){
	var statusStr = "";
	status = status || "true" ;
	if (status=="false") {
		statusStr +="<div class='dropdown my-disabled'>";
		statusStr +="<a href='javascript:void(0)' class='table-link warning' title='"+title+"'>";
		statusStr +=" <span class='fa-stack '>";
		statusStr +="   <i class='fa fa-square fa-stack-2x'></i>";
		statusStr +="   <i class='fa fa-legal fa-stack-1x fa-inverse'></i>";
		statusStr +=" </span>";
		statusStr +="</a>"
		statusStr +="</div>	"
	} else {
		statusStr +="<div class='dropdown '>";
		statusStr +="<a href='javascript:void(0)' class='table-link warning dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' title='"+title+"'>";
		statusStr +=" <span class='fa-stack '>";
		statusStr +="   <i class='fa fa-square fa-stack-2x'></i>";
		statusStr +="   <i class='fa fa-legal fa-stack-1x fa-inverse'></i>";
		statusStr +=" </span>";
		statusStr +="</a>"
		statusStr +="<ul class='dropdown-menu '>"
		statusStr +="<li><a class='green' href='"+url+"'><i class='fa fa-level-up'></i>上线</a></li>"
		statusStr +="<li class='disabled'><a href='javascript:void(0)'><i class='fa fa-level-down'></i>停用</a></li>"
		statusStr +="</ul>"
		statusStr +="</div>	"
	}
	return statusStr;
}

//审核下拉按钮
function getCheckCinanceCurd(url1,url2,title){	
	var statusStr = "";
	statusStr +="<div class='dropdown'>";
	statusStr +="<a href='javascript:void(0)' class='table-link warning dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' title='"+title+"'>";
	statusStr +=" <span class='fa-stack '>";
	statusStr +="   <i class='fa fa-square fa-stack-2x'></i>";
	statusStr +="   <i class='fa fa-legal fa-stack-1x fa-inverse'></i>";
	statusStr +=" </span>";
	statusStr +="</a>"
	statusStr +="<ul class='dropdown-menu '>"
	statusStr +="<li><a class='green' href='"+url1+"'><i class='fa fa-level-up'></i>通过</a></li>"
	statusStr +="<li><a class='red' href='"+url2+"'><i class='fa fa-level-down'></i>不通过</a></li>"
	statusStr +="</ul>"
	statusStr +="</div>	"
	return statusStr;
}

//打款按钮
function getPostMoneyCurd(url,title) {
	var postStr = "";
	postStr +="<a href='"+url+"' class='table-link primary' title='"+title+"'>";
	postStr +=" <span class='fa-stack '>";
	postStr +="   <i class='fa fa-square fa-stack-2x'></i>";
	postStr +="   <i class='fa fa-credit-card fa-stack-1x fa-inverse'></i>";
	postStr +=" </span>";
	postStr +="</a>"
	return postStr;
}

//停用
function getStopStatusCurd(url,title,status){
	var statusStr = "";
	status = status || "true" ;
	if (status=="false") {
		statusStr +="<div class='dropdown'>";
		statusStr +="<a href='javascript:void(0)' class='table-link warning my-disabled' title='"+title+"'>";
		statusStr +=" <span class='fa-stack '>";
		statusStr +="   <i class='fa fa-square fa-stack-2x'></i>";
		statusStr +="   <i class='fa fa-legal fa-stack-1x fa-inverse'></i>";
		statusStr +=" </span>";
		statusStr +="</a>"
		statusStr +="</div>	"
	} else {
		statusStr +="<div class='dropdown'>";
		statusStr +="<a href='javascript:void(0)' class='table-link warning dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' title='"+title+"'>";
		statusStr +=" <span class='fa-stack '>";
		statusStr +="   <i class='fa fa-square fa-stack-2x'></i>";
		statusStr +="   <i class='fa fa-legal fa-stack-1x fa-inverse'></i>";
		statusStr +=" </span>";
		statusStr +="</a>"
		statusStr +="<ul class='dropdown-menu '>"
		statusStr +="<li class='disabled'><a href='javascript:void(0)'><i class='fa fa-level-up'></i>上线</a></li>"
		statusStr +="<li><a  class='red' href='"+url+"'><i class='fa fa-level-down'></i>停用</a></li>"
		statusStr +="</ul>"
		statusStr +="</div>	"
	}
	return statusStr;
}

//警告提示信息
function alert_msg_warning(id,msg) {
	var warningStr = "";
	warningStr += "<div class='row'>";
	warningStr += "<div class='col-md-12'>";
	warningStr += "<div class='alert alert-warning alert-dismissible' role='alert'>";
	warningStr += "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>";
	warningStr += msg;
	warningStr += "</div>";
	warningStr += "</div>";
	warningStr += "</div>";
//    return warningStr;
	$("#"+id).append(warningStr);
}

//重置查询条件
function resetFn(){
	$("#searchDiv input").each(function(){
		$(this).val("");
	}); 
	$("#searchDiv #reportrange span").text("");
	//clas名称
	var config = ['.chosen-select','.chosen-select-multiple']
	for (var selector in config) {
		//chosen 重置
		$(config[selector]).chosen("reset");
	}
}

/**
 * 页面操作成功跳转页面
 * @param url 跳转地址
 * @param time 延迟跳转时间，默认1000ms
 */
function go_sucess_url(url,time){
	var delaytime = time || 1000;
	window.setTimeout(function(){
		location.href= PROJECT_CTX + url;
	},delaytime);
}


/**
 * 页面操作失败跳转页面
 * @param url 跳转地址
 * @param time 延迟跳转时间，默认1000ms
 */
function go_fail_url(url,time){
	var delaytime = time || 1000;
	window.setTimeout(function(){
		location.href= PROJECT_CTX + url;
	},delaytime);
}

/**
 * 获取select选中对应的文本值，赋值到label
 * @param selectId select的id
 * @param val 初始值
 * @param labelId 用来显示文本值的label的id
 */
function getSelectValue(selectId, val, labelId) {
	var $selectId = $(selectId);
	$selectId.val(val);
	var selectTxt = $selectId.find("option:selected").text();
	$(labelId).text(selectTxt);
}


//游览器判断
function getBrowserInfo() {
	var agent = navigator.userAgent.toLowerCase() ;
  	var regStr_ie = /msie [\d.]+;/gi ;
  	var regStr_ff = /firefox\/[\d.]+/gi
 	var regStr_chrome = /chrome\/[\d.]+/gi ;
 	var regStr_saf = /safari\/[\d.]+/gi ;
 	
  	//IE
	if(agent.indexOf("msie") > 0) {
		return agent.match(regStr_ie) ;
 	}
  	
	//firefox
	if(agent.indexOf("firefox") > 0) {
		return agent.match(regStr_ff) ;
	}
	
	//Chrome
	if(agent.indexOf("chrome") > 0) {
	  	return agent.match(regStr_chrome) ;
	}
	
	//Safari
	if(agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0) {
	  	return agent.match(regStr_saf) ;
	}
}

/*
 * js如何判断IE浏览器的版本包括IE11
 * 	if (browserMatch.browser){  
 *	  browser = browserMatch.browser;  
 *	  version = browserMatch.version;  
 *	} 
 */
function getBrowserInfo(){  
	var userAgent = navigator.userAgent.toLowerCase(),   
	rMsie = /(msie\s|trident.*rv:)([\w.]+)/,   
	rFirefox = /(firefox)\/([\w.]+)/,   
	rOpera = /(opera).+version\/([\w.]+)/,   
	rChrome = /(chrome)\/([\w.]+)/,   
	rSafari = /version\/([\w.]+).*(safari)/;  
	var browser;  
	var version;  
	
	var match = rMsie.exec(userAgent);  
	if(match != null){  
		return { browser : "IE", version : match[2] || "0" };  
	}  
	
	var match = rFirefox.exec(userAgent);  
	if (match != null) {  
		return { browser : match[1] || "", version : match[2] || "0" };  
	}  
	
	var match = rOpera.exec(userAgent);  
	if (match != null) {  
		return { browser : match[1] || "", version : match[2] || "0" };  
	}  
	
	var match = rChrome.exec(userAgent);  
	if (match != null) {  
		return { browser : match[1] || "", version : match[2] || "0" };  
	}  
	
	var match = rSafari.exec(userAgent);  
	if (match != null) {  
		return { browser : match[2] || "", version : match[1] || "0" };  
	}  
	
	if (match != null) {  
		return { browser : "", version : "0" };  
	}  
}  

/**
 * 短信发送验证码
 * @param sendId 验证码按钮id，需要带'#'
 * @param msgId 提示信息id
 * @param phone 手机号
 * @param type 验证码类型
 */
function sendSMSCode(sendId, msgId, phone, type){
	var $sendId = $(sendId);
	var $msgId = $(msgId);
	var count = 60; //间隔函数，1秒执行  
	var curCount;//当前剩余秒数 
//	param = param || "";
    curCount = count;  
	//设置button效果，开始计时  
	//$("#error_msg").html("").addClass("hidden");  
    var InterValObj ='';
    if(InterValObj !='') {
    	window.clearInterval(InterValObj);//停止计时器之前调用的定时器  
    }
	$.ajax({  
		type: "post", 
		url: PROJECT_CTX + "/sms/sendSmsWeb.html", 
		data: {phone:phone, type:type},  
		dataType: "json", 
		beforeSend: function() {
//			$sendId.off('click');
			$sendId.addClass("my-disabled"); 
			$sendId.html("短信发送中..."); 
		},
		success: function(data,status,xhr){
			if(data.state=="1") {
				//测试，正式使用必须注释掉
				// $("#code").val(data.desc);
				//当返回成功才开始倒计时
//				$sendId.addClass("disabled");
				$sendId.html("重新获取(" + curCount +"s)");  
				InterValObj = window.setInterval(function(){
					if (curCount == 0) {                  
						window.clearInterval(InterValObj);//停止计时器  
//						func();
						$sendId.removeClass("my-disabled");//启用按钮  
						$sendId.html("重新获取");  
					}  
					else {  
						curCount--;  
						$sendId.html("重新获取(" + curCount +"s)");  
					}  
				}, 1000);
				$msgId.removeClass("hidden").html(data.desc);
			}else{
//				func();
				$sendId.removeClass("my-disabled");//启用按钮  
				$sendId.html("重新获取");  
				$msgId.html(data.desc).removeClass("hidden");
			}
		}
	});  
}

function delayFunc(func,time){
	var delaytime = time || 1000;
	window.setTimeout(function(){func();},delaytime);
}

/*
 * 上传图像模态框
 */
function myPic(options){
    var messagetxt ="<div id='def-mypic'>";
    for(i=1;i<15;i++){
    	messagetxt += "<a><img src='"+ options.ctx + "/img/mypic/" + i + ".png'></a>" ;
    }
    messagetxt += "</div>";
    bootbox.dialog({
        title: "设置头像",
        closeButton: true,
//	   size: 'small',
        message: messagetxt,
        buttons: {
            Cancel: {
                label: "取消",
                className: "btn-default"
            }
            , upload: {
                label: "自定义图像",
                className: "btn-primary mypic-upload pull-left"
            }
            , OK: {
                label: "保存头像",
                className: "btn-success",
                callback: function () {
                	var vimg = '';
                	$.each($('#def-mypic a'), function(){
                		//如果选择就获取图片src
                		if($(this).attr('class')==='bk'){
                			vimg = $(this).find('img').attr('src');
                			return false; 
                		}
                	});
                	if(vimg!='') {
                		$(options.uploader).find('.filelist').find('li').html('<img src='+ vimg +'>');
                		$(options.setValId).val(vimg);
                		//bb_alert(null,"上传成功");
                	}
                }
            }
        }
    });
    //图片点击事件
    $('#def-mypic a').on('click',function(){
        $.each($('#def-mypic a'),function(){
            $(this).removeClass('bk');
        })
       $(this).addClass('bk');
    })
    //设置plupload上传id
    $('.mypic-upload').attr('id',options.browse_button);
}

