<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>慧教云</title>
<link href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/css/tianyu_terminal_base.css" rel="stylesheet" type="text/css" />
<link href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/css/tianyu_terminal.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${ctx}/js/layer/css/layer.css">
</head>
<body>
<div class="g_syshead">
    <div class="m_baseheader m_basewrap">
        <h1 class="logo fl"><span class="img"><img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/images/logo.png" alt="" /></span></h1>
    </div>
</div>
<div class="zy_indexBannerWrap"> 
    <!--banner 区域-->
    <div class="indexBanner m_basewrap">
    	<div class="py_tips"></div>
       
        <div class="py_m_login" id="m_login">
            <div class="item-newLogo-rect">
        		<p class="logoTips dis_none"><i class="ico1"></i><i class="ico2"></i><span>扫码登录更安全</span></p>
                <div class="passWord-case " name="LoginRect">
                <form id="loginForm" action="${ctx}/manage/user/login" method="post" >
                    <h4>管理员登录</h4>
                    <!-- <div class="err-tips " ><i class="ico"></i>用户名或登录密码不正确</div> -->
                    <ul class="inputlist clearfix">
                        <li class="u_inputtext">
                            <input name="account" class="text" type="text" value="" placeholder="用户名" />
                        </li>
                        <li class="u_inputpassword ">
                            <input name="password" class="text"  type="password" placeholder="密码" />
                        </li>
                        
                    </ul>
                    <div class="clearfix chklist2 mgt20">
                        <label class="sys_checkbox ">
                        <input type="checkbox" value="1" style="display: none;"/>
                            记住密码
                        </label>
                        <a href="" class="wql_forgetPwd fr">忘记密码？</a>
                    </div>
                    <input type="button" value="立即登录" class="m_btn submit" onclick="loginUser()" />
                </form>
                </div>
                
            </div>
        </div>
    </div>
</div>
<div class="g_sysfooter">
    <div class="m_basewrap m_basefooter clearfix">
        <div class="copyright fl">
            <p class="f14">技术运营支持：武汉天喻教育科技有限公司 客户服务热线：027-87785207</p>
            <p class="mgt10">©2016 www.wuhaneduyun.cn All rights reserved &nbsp;&nbsp;&nbsp;鄂ICP备05002327号-2</p>
        </div>
        <div class="bot_nav f14 fr"><a href="javascript:void(0);">站长统计</a>|<a href="javascript:void(0);">帮助中心</a></div>
    </div>
</div>
<!--//登陆 end--> 
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/js/jquery.js"></script> 
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/js/fun.js"></script> 
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/js/tianyu_terminal_base.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/localization/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/js/layer.js"></script>
<script type="text/javascript">
//登录浮层
$(function(){
    //复选框
    $('.chklist2').hcheckboxnew2(); 
	Login.init();
	$(document).off();
	
	


    function oneScreen(){
		var win_h = $(window).height();
		var body_h = $('body').height();
		var header_h = $('.g_syshead').outerHeight();
		var footer_h = $('.g_sysfooter').outerHeight();
        if(win_h > body_h && win_h > 500){
            var mainWrap_h = win_h - header_h - footer_h;
            $('.zy_indexBannerWrap').height(mainWrap_h);
        }else{
            $('.zy_indexBannerWrap').height(500);
        };
    };
    oneScreen();
    $(window).on('resize',function(){
        oneScreen();
    });

    if(!supportCss3('transform')){
        var loginH = $('.py_m_login').outerHeight();
        $('.py_m_login').css('marginTop',-loginH/2);
    };
    //是否支持css的某个属性
    function supportCss3(style) { 
        var prefix = ['webkit', 'Moz', 'ms', 'o'],i,humpString = [],
            htmlStyle = document.documentElement.style, 
        _toHumb = function (string) { 
            return string.replace(/-(\w)/g, function ($0, $1) { 
            return $1.toUpperCase(); 
        }); 
        }; 
         
        for (i in prefix) 
        humpString.push(_toHumb(prefix[i] + '-' + style)); 
         
        humpString.push(_toHumb(style)); 
         
        for (i in humpString) 
        if (humpString[i] in htmlStyle) return true; 
         
        return false; 
    }
})

function loginUser(){
	var account = $("input[name='account']").val();
    var password = $("input[name='password']").val();
    //$("#loginForm").submit();
    $("#loginForm").validate({
	rules : {
		account:{
			required : true,
			},
		password : {
			required : true,
			}
	},
	errorPlacement:function(error,element) {
    	layer.tips($(error).text(), element, {
    		tips: 2,
    		tipsMore: true
    	});
    }
	});
	if($("#loginForm").valid()){
		$.ajax({
	    	url:"${ctx}/manage/user/login",
	    	type:"POST",
	    	data:{
	    		account:account,
	    		password:password
	    	},
	    	async : false,
			dataType : 'json',
			success : function(data){
				if(data.result){
					window.location.href= "${ctx}/manage/user/index";
				}else{
					$("input[name='password']").val('');
					$("input[name='password']").focus();
					//$(".err-tips").css("display","block")
					//$(".err-tips").html("<i class=\"ico\"></i>"+data.message)
					//layer.msg(data.message, { anim: 6 });
					layer.tips(data.message, $("input[name='password']"), {
			    		tips: 2,
			    		tipsMore: true
			    	});
					return;
				}
			}
	    });
	};
}


</script>
</body>
</html>