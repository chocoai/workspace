<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码</title>
<link href="http://css.tianyuyun.cn/tianyu_edu_dev/area/888888/css/jx_zhushou.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
</head>
<body>
<div class="g_parseword">
  <div class="g_pw_header clearfix">
    <h1 class="m_logo fl">找回密码</h1>
    <a href="http://wpa.qq.com/msgrd?v=3&uin=3147516084&site=qq&menu=yes" target="_blank" class="m_qq fr">在线客服</a> </div>
  <div class="g_pw_content">
    <div class="step_box">
      <div class="m_step">
        <p class="step_now step_1"></p>
      </div>
      <ul class="m_step_list">
        <li class="acitve"><span class="num">1</span><em>输入帐号</em></li>
        <li><span class="num">2</span><em>验证密保</em></li>
        <li><span class="num">3</span><em>重置密码</em></li>
        <li><i class="num"><img src="http://css.tianyuyun.cn/tianyu_edu_dev/area/888888/images/jx_zhushou/step_wc.png" /></i><em>修改成功</em></li>
      </ul>
    </div>
    <div class="m_step1">
      <ul class="clearfix m_step_ul">
        <li>
          <label class="fl c555"><i class="red">*</i>账号：</label>
          <input type="text" id="account" class="inp fl" style="width:410px;"/>
          <span class="red u_no fl dis_none">请输入账号</span><span class="u_yes fl dis_none"></span></li>
        <li id="platform" class="dis_none">
          <input type="hidden" id=findPasswordUrl>
          <p class="u_tips red">您的账号在多个平台存在，请选择平台。</p>
          <div class="clearfix">
            <label class="fl c555"><i class="red">*</i>选择平台：</label>
            <dl class="u_choolse fl">
              <dt>&nbsp;</dt>
              <dd class="dis_none"><a href="javascript:(0)" >您的宠物叫什么</a></dd>
            </dl>
            <span class="red u_no fl">请输入平台</span><span class="u_yes fl dis_none"></span></div>
        </li>
        <li id="message" class="dis_none"><p class="u_tips red"></p></li>
        <li class="li_btn">
          <input type="button" value="下一步" onclick="queryPlatform()"/>
        </li>
      </ul>
      <p class="forget_ps"><i class="red">注意</i>：忘记账号，请联系<a href="http://wpa.qq.com/msgrd?v=3&uin=3147516084&site=qq&menu=yes" target="_blank">在线客服！</a></p>
    </div>
  </div>
  <div class="g_pw_footer"></div>
</div>
<script type="text/javascript">
var step = '0';

(function(){
	$('.u_choolse dt').click(function(){
		$(this).addClass('on');
		$(this).next('dd').show();
		return false;
	});
	$('.u_choolse dd a').live('click',function(){
		step = '1';
		$('.u_choolse dt').html($(this).html());
		$('#findPasswordUrl').val($(this).find('input').eq(0).val());
	});
	$(document).click(function(){
		$('.u_choolse dt').removeClass('on');
		$('.u_choolse dd').hide();
	});
	
	$("#account").blur(function(){
		if($(this).val()==''){
			$(this).next().show();
			$(this).next().next().hide();
		}else{
			$(this).next().hide();
			$(this).next().next().show();
		}
	});
	
	$("#account").change( function() {
		step = '0';
	});
})();

function queryPlatform(){
	if(step == '1'){
		if($('#findPasswordUrl').val()==''){
			return;
		}
		window.location.href=$('#findPasswordUrl').val();
	}
	$('#findPasswordUrl').val('');
	if($("#account").val()==''){
		$("#account").next().show();
		$("#account").next().next().hide();
		return;
	}else{
		$("#account").next().hide();
		$("#account").next().next().show();
	}
	$.ajax({
		type : "POST",
		url : "<%=request.getContextPath()%>/api/findPassword/queryAccountPlatformInfo",
		data : {"account" : $("#account").val()},
		async : false,
		dataType : 'json',
		success : function(msg) {
			if(msg != null){
				if(msg.result=='000001'){
					$("#message p").html("未查到平台信息");
					$("#message").show();
					$("#platform").hide();
				}else if(msg.result=='000000'){
					window.location.href=msg.data.eduyun_find_password;
				}else if(msg.result=='000002'){
					$("#message").hide();
					var html = '';
					for(var i=0;i<msg.data.length;i++){
						var obj = msg.data[i];
						html += '<a href="javascript:(0)"><input type="hidden" value="'+obj.eduyun_find_password+'"/>'+obj.platformName+'</a>';
					}
					$("#platform .u_choolse dd").html(html);
					$("#platform").show();
				}
			}
		}
	});
}
</script>
</body>
</html>