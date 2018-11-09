<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@taglib uri="/WEB-INF/tld/pageTag.tld" prefix="newTag"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据清理</title>
</head>
<body>
<div class="mg15 txq_main">
	<input type="hidden" id="msg" value="${msg}"/>
    <form action="${ctx}/manage/cache/list" name="pageForm" method="post">
    <div class="mgtb10">
		<div class="base_title"><strong>操作集合</strong></div>
		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="缓存重置" onclick="reload()"/>&nbsp;&nbsp;
			<!-- <input type="button" class="btn_blue" value="清空Cms接口数据" onclick="clearCms()"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="重置参数配置数据" onclick="reloadBaseProperty()"/>&nbsp;&nbsp;
			 -->
			 
			 <input type="button" class="btn_blue" value="清理学生信息" onclick="clearClassStudent()"/>&nbsp;&nbsp;
			 <!--
			 <div>
			 	同步用户信息到web端，起始时间<input type="text" name="startTime" value="${startTime}"/><input type="button" class="btn_blue" value="开始" onclick="synUserToWeb()"/>&nbsp;&nbsp;
			 </div> -->
		</div>
		
    </div>
	</form>
</div>
<script>
$(document).ready(function(){
	if($("#msg").val()!=''){
		$.alert($("#msg").val());
	}
});
//重置缓存数据
function reload(){
	document.forms[0].action = "${ctx}/manage/cache/reload";
	document.forms[0].submit();
}
//清空Cms接口数据
function clearCms(){
	document.forms[0].action = "${ctx}/manage/cache/clearCms";
	document.forms[0].submit();
}
//重置参数配置数据
function reloadBaseProperty(){
	document.forms[0].action = "${ctx}/manage/cache/reloadBaseProperty";
	document.forms[0].submit();
}

//清理学生信息垃圾数据
function clearClassStudent(){
	$.confirm("确定要清理用户新建的学生信息表（EBP_CLASS_STUDENT）中的垃圾数据吗？",function(){
		document.forms[0].action = "${ctx}/manage/class/clearClassStudent";
		document.forms[0].submit();
	});
}
//同步用户信息到web端
function synUserToWeb(){
	$.confirm("确定要同步用户信息到web端吗？",function(){
		document.forms[0].action = "${ctx}/managerTaUser/synUserToWeb";
		document.forms[0].submit();
	});
}
</script>
</body>
</html>