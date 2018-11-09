<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
<link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
	<img src="/images/home.png" width="19" height="24" />当前位置：
	<a href="/workbench.htm">工作台</a> >
	<a href="javascript:void(0)">个人密码修改</a>
</div>
    <div id="content">
    <div style="width:70%; min-width:980px;margin:20px auto;">
      <div class="tabPanel">
			<ul style="border-bottom: 0px solid #aaa;">
				<li onclick="javascript:getRevise();">修改个人资料</li>
				<li  class="hit" style="border-bottom: 0px solid #fff;">修改密码</li>
			</ul>
				<div class="pane" style="display:block;border: 1px solid #aaa;">
	        			<form action="/user/revisepassword.htm" method="post" id="revisepasswordForm">  
							   <input name="id" id="id" type="hidden" value="${u.id}" />
							    <div >
						       		 	<table border="1" cellspacing="1" cellpadding="1" class="input_table" >
											<tr>
												<td width="265" class="tab_title"><p>账号</p></td>
												<td colspan="4">${u.username}</td>
											</tr>
											<tr>
												<td class="tab_title red"><p>旧密码</p></td>
												<td colspan="4"><input name="oldPassword" type="password" class="text_a" style="width: 200px;"/></td>
											</tr>
											<tr>
												<td class="tab_title red"><p>新密码</p></td>
												<td colspan="4"><input name="newPassword" id="newPassword" type="password" class="text_a" style="width: 200px;"/></td>
											</tr>
											<tr>
												<td class="tab_title red"><p>确认新密码</p></td>
												<td colspan="4"><input name = "TonewPassword" TonewPassword="newPassword" type="password" class="text_a" style="width: 200px;"/></td>
											</tr>
											<tr> 
												<td colspan="5" style=" text-align:right;">
													<input type="button" name="button" id="button" value="返回" class="sub" onclick="history.back()"/> 
													<input type="submit" name="button2" id="button2" value="保存" class="sub"/>
												</td>
											</tr>
										</table>
						    </div>
						</form>
		   		 </div>
       </div>
      </div>
    </div>
<script>
	$().ready(function() {
		$("#revisepasswordForm").validate({
			debug: true, //调试模式取消submit的默认提交功能   
			submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form  
            		var s = $("#revisepasswordForm");
					var url = s[0].action;
					var data = s.serialize();
					$.ajax({
							type:"post",
							url:url,
							data:data,
							async: false, //关键，设置为同步
							dataType:'text',	//返回文本
							success:function(data){
								if(data){
									window.location.href= "/logout.htm";
								}
							}
					})
            },   
			rules : {  
	  	    		 'oldPassword' : { required : true,checkPassword:true } ,
	  	    		 'newPassword' : { required : true,password:true } ,
	  	    		 'TonewPassword' : { required : true} 
					}  
		})
	});
	function getRevise(){
		window.location.href= "/t_reso/revise.htm";
	}
</script>