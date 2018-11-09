<%@page language="java" pageEncoding="utf-8"%>

<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
	String contextPath = request.getContextPath();
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport"
		content="initial-scale=1.0, user-scalable=no, width=device-width">
	<title>个人设置</title>

	<jsp:include page="/pages/base/base.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid" style="margin-top: 20px; float: left;">
		<div style="float:left;width:150px;">
                <ul class="nav nav-pills nav-stacked" style="width:150px;text-align:center;">
                    <li><a href="<%=contextPath %>/personal/index">个人信息</a></li>
                    <li  class="active"><a href="<%=contextPath %>/pages/personal/updatePwd.jsp">修改密码</a></li>
                </ul>
        </div>
        <div style="float:left;width:600px;margin-left: 200px;">
        	<form id="pwdForm" class="form-horizontal"  action="<%=contextPath %>/personal/updatePwd" method="post"  >
				<div class="form-group">
					<label class="col-sm-2 control-label">旧密码:</label>
					<div class="col-sm-10">	
						<input type="password" required="required" name="oldPwd" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">新密码:</label>
					<div class="col-sm-10">	
						<input id="newPwd" required="required" name="newPwd" type="password" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">确认新密码:</label>
					<div class="col-sm-10">	
						<input id="newPwd2" required="required" name="newPwd2" type="password" class="form-control" onblur="cheackPwd()">
					</div>
				</div>
				<div class="form-actions">
					<input id="btnSubmit" class="btn btn-primary" value="保 存" type="submit">
				</div>
			</form>
		</div>
     </div>
     <script type="text/javascript">
	     function cheackPwd(){
	  		var pwd =document.getElementById('newPwd').value;
	  		var rpwd =document.getElementById('newPwd2').value;
	  		if(pwd==rpwd){
	  			return true;
	  			}
	  		else{
	  			layer.msg('您2次输入的密码不一致,请重新输入', {
	  			    time: 5000, //20s后自动关闭
	  			  });
	      		document.getElementById('newPwd2').value="";
	      		document.getElementById('newPwd').value="";
	  			return false;
	  			}
	  		}

	     $("#pwdForm").validate({
	    		rules : {
	    			newPwd : {
	    				minlength:8,
	    				maxlength:16
	    				},
	    		},
	    	    errorPlacement:function(error,element) {
	    	    	layer.tips($(error).text(), element, {
	    	    		tips: 3,
	    	    		tipsMore: true
	    	    	});
	    	    }
	    		})
     	if("${result}"!=null&&"${result}"!=""){
     		switch ("${result}") {
     		case "1":
     			layer.msg('旧密码不能为空', {
     			    time: 5000, //20s后自动关闭
     			  });
     			break;
     		case "2":
     			layer.msg('新密码不能为空', {
     			    time: 5000, //20s后自动关闭
     			  });
     			break;
     		case "3":
     			layer.msg('旧密码错误', {
     			    time: 5000, //20s后自动关闭
     			  });
     			break;
     		case "4":
     			layer.msg('修改成功', {
     			    time: 5000, //20s后自动关闭
     			  });
     			break;
     		}
     	}
     </script>
     
     <style type="text/css">
      .form-control { 
      	width: 50% ; }
      /* .form-actions {
	    padding: 19px 20px 20px;
	    margin-top: 20px;
	    margin-bottom: 20px;
	    background-color: #f5f5f5;
	    border-top: 1px solid #e5e5e5;
	    *zoom: 1;
	} */
	.form-horizontal .form-actions {
	    padding-left: 180px;
	}
	</style>
</body>
</html>