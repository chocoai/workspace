<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#submitChangePwd").click(function(){
		var oldPassword =$("#oldPassword").val();
		var newPassword1 =$("#newPassword1").val();
		var newPassword2 =$("#newPassword2").val();
		var account = '${sessionScope.sysUser.account}';
		var password = '${sessionScope.sysUser.password}';
		var id = '${sessionScope.sysUser.id}';
		if(oldPassword == ''){
			$("#oldPassword").siblings(".red").text("旧密码为空");
			return false;
		}
		if(newPassword1==''){
			$("#newPassword1").siblings(".red").text("新密码为空");
			return false;
		}
		if(newPassword2==''){
			$("#newPassword2").siblings(".red").text("确认密码为空");
			return false;
		}
		if(newPassword1.length<6){
			$("#newPassword1").siblings(".red").text("新密码最短6位");
			return false;
		}
		if(newPassword1.length>20){
			$("#newPassword1").siblings(".red").text("新密码最长20位");
			return false;
		}
		if(newPassword2.length<6){
			$("#newPassword2").siblings(".red").text("确认密码最短6位");
			return false;
		}
		if(newPassword2.length>20){
			$("#newPassword2").siblings(".red").text("确认密码最长20位");
			return false;
		}
		if(newPassword1!=newPassword2){
			$("#newPassword2").siblings(".red").text("新密码和确认密码不一致");
			return false;
		}
		
		if(password!=oldPassword){
			$("#oldPassword").siblings(".red").text("旧密码不正确");
			return false;
		}
		
		$.ajax({
			type: "POST",
			url: "/ebookpackage/sys/user/changePassword",
			data: {"id":id,"newPassword":newPassword1},
			async: false,
			dataType:'text',
			success: function(msg){
				if(msg=='success'){
					emptyChangePwd();
					$(".changePwd").closeBox();
					$.alert("密码修改成功",function(){
						window.location.reload();
					});
				}
			}
		});
	});
	
	$(".close").click(emptyChangePwd);
});

function emptyChangePwd(){
	$("#oldPassword").val('');
	$("#newPassword1").val('');
	$("#newPassword2").val('');
	$(".red").text('');
}
</script>
<div class="popup dis_none changePwd">
    <div class="tit"><span class="fl">修改密码</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
        <table class="small_space data_list" width="100%" style="border-collapse:collapse;">
            <tr>
                <td width="30%" align="right"><span style="color: red;">*</span>旧密码</td>
                <td width="70%" align="left">
                	<input id="oldPassword" type="password" style="width:150px;" class="inp focus"/>
                	<span class="red"></span>
                </td>
            </tr>
            <tr>
                <td align="right"><span style="color: red;">*</span>新密码</td>
                <td align="left">
                	<input id="newPassword1" type="password" style="width:150px;" class="inp focus" placeholder="最短6位，最长20位"/>
                	<span class="red"></span>
                </td>
            </tr>
            <tr>
                <td align="right"><span style="color: red;">*</span>确认密码</td>
                <td align="left">
                	<input id="newPassword2" type="password" style="width:150px;" class="inp focus" placeholder="最短6位，最长20位"/>
                	<span class="red"></span>
                </td>
            </tr>
            <tr>
            	<td colspan="2">
					<input type="button" class="btn_blue" id="submitChangePwd" value="确定"/>
					<input type="button" class="btn_gray close" name="close" value="取消"/>
            	</td>
            </tr>
        </table>
    </div>
</div>
