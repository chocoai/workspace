<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发送消息页面</title>
<script charset="utf-8" src="${ctx}/js/kindeditor-4.1.10/kindeditor.js"></script>
<link rel="stylesheet" href="${ctx}/js/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet" href="${ctx}/js/kindeditor-4.1.10/plugins/code/prettify.css" />

<script charset="utf-8" src="${ctx}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8" src="${ctx}/js/kindeditor-4.1.10/plugins/code/prettify.js"></script>

<style type="text/css">
table tr td{padding-top: 10px;}
.platform{width:650px;}
.platform input{margin-right: 5px;}
.platform div{margin-left:20px;}
.platform div span{display: inline-block;width:200px;height: 25px;}
.platform p{height:25px;}
</style>
</head>
<body>
<div class="mg15 txq_main">
   	 <div class="mgtb10">
    	<div class="base_title">
			<strong><a class="mgr10" href="javascript:window.location.href='${ctx}/manage/message/list';" name="wddx" style="color: #497cc0;">返回上一级</a>发送通知</strong>
		</div>
        <table border="0" width="100%" style="font-size: 12px;">
         	<tr>
               <td width="10%" align="right">发件人：</td>
               <td width="90%">
               	<span>${sessionScope.sysUser.user_name}</span>
               </td>
             </tr>
             <tr>
               <td align="right"><em class="red">*</em>主题：</td>
               <td>
               	<input type="text" name="theme" class="inp focus" style="width:340px;"/>
               	<span class="red"></span>
               </td>
             </tr>
             <tr>
               <td align="right" style="vertical-align:top;"><em class="red">*</em>产品：</td>
               <td>
               	<div class="platform">
               		<p><input type="checkbox" name="allProduct"/>全部产品</p>
               		<div>
	               		<c:forEach var="obj" items="${productList}">
	               		<span>
	               			<input type="checkbox" name="product" id="${obj.ID}" value="${obj.PRODUCT_NAME}"/>
	               			${obj.PRODUCT_NAME }
	               		</span>
		               	</c:forEach>
	               	</div>
               	</div>
               </td>
             </tr>
             <tr>
               <td align="right"><em class="red">*</em>通知类型：</td>
               <td>
               <c:forEach var="type" items="${messageTypeList}" varStatus="status">
               	<label class="mgr10">
               		<input type="radio" name="messageType" <c:if test="${status.index==0}">checked="checked"</c:if> value="${type.ID }">${type.NAME }</label>
               	</c:forEach>
               	<span class="red"></span>
               </td>
             </tr>
             <tr>
               <td align="right"><em class="red">*</em>发布时间：</td>
               <td>
               <input type="text" name="releaseTime" style="width: 200px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" class="Wdate">
               	<span class="red"></span>
               </td>
             </tr>
             <tr>
             	<td colspan="2">
             		<div class="editor">
			        	<textarea name="content1"
							style="width: 530px; height: 300px; visibility: hidden;"></textarea>
					</div>
             	</td>
             </tr>
             <tr>
             	<td colspan="2"><input type="button" id="save" class="btn_blue" value="发送" /></td>
             </tr>
		</table>
	</div>
</div>

<script type="text/javascript">
var $ckAll = $("input[name='allProduct']");
var $ckItm = $("input[name='product']");
var len = $ckItm.length;
$ckAll.click(function() {
    $ckItm.prop("checked",this.checked);
});
$ckItm.click(function() {
    var b=$ckItm.filter(":checked").length==len;
    var flag=$ckAll.prop("checked",b?true:false);
});

function getCheckId(){
	if($ckAll.attr('checked')=='checked'){
		return "all";
	}
	var ids = "";
	$.each( $ckItm, function(i, ckItm){
		if(ckItm.checked){
			ids += "," + ckItm.id;
		}
	});
	if(ids != ""){
		return ids.substring(1);
	}
	return "";
}

function getCheckValue(){
	if($ckAll.attr('checked')=='checked'){
		return "全部产品";
	}
	var ids = "";
	$.each( $ckItm, function(i, ckItm){
		if(ckItm.checked){
			ids += "," + ckItm.value;
		}
	});
	if(ids != ""){
		return ids.substring(1);
	}
	return "";
}

KindEditor.ready(function(K) {
	var editor1 = K.create('textarea[name="content1"]', {
		cssPath : '${ctx}/js/kindeditor-4.1.10/plugins/code/prettify.css',
		//uploadJson : '${ctx}/manage/message/fileUpload',
		//fileManagerJson : '${ctx}/manage/message/fileManager',
		//allowFileManager : true,
		//allowImageUpload : true,
		resizeType : 1,
		items : [
				'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'cut', 'copy', 'paste',
		 		'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
		 		'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
		 		'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
		 		'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
		 		'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', //'image', 'multiimage',
		 		 'table', 'hr', 'emoticons', //'baidumap', 
		 		 'pagebreak',
		 		'anchor', 'link', 'unlink'
		 	]
	});
	prettyPrint();
	
	$("input[name='theme']").blur(function(){
		if($(this).val().length>30){
			$.alert("主题长度不能大于30个字！");
		}
	});
	
	$("#save").click(function(){
		editor1.sync();
		var html = editor1.html();
		
		var theme = $("input[name='theme']").val();
		if(theme=='' || theme==null){
			$.alert("主题不能为空！");
			return;
		}else if(theme.length >30){
			$.alert("主题长度不能大于30个字！");
			return;
		}
		
		var receiveObjId = getCheckId();
		var receiveObjName = getCheckValue();
		if(receiveObjId == ""){
			$.alert("请选择产品！");
			return;
		}
		
		var releaseTime = $("input[name='releaseTime']").val();
		
		if(releaseTime == ''){
			$.alert("发布时间不能为空！");
			return;
		}
		
		if(html == ''){
			$.alert("消息内容不能为空！");
			return;
		}
		
		var messageTypeId = $("input[name='messageType']:checked").val();
		
		$(this).attr("disabled","disabled");
		$.ajax({
			url : "${ctx}/manage/message/send",
			type : 'post',
			async: false,
			data : {
				"content" : html,
				"theme" : theme,
				"receiveObjType" : '0',
				"receiveObjId" : receiveObjId,
				"receiveObjName" : receiveObjName,
				"messageTypeId" : messageTypeId,
				"releaseTimeStr" : releaseTime
			},
			dataType : 'text',
			success : function(text) {
				$.alert("发送成功");
				
				//清空内容
				$("input[name='theme']").val('');
				$("input[name='releaseTime']").val('');
				editor1.html('');
				$("#save").removeAttr("disabled");
				$ckAll.prop("checked",false);
				$ckItm.prop("checked",false);
				$("input[name='messageType']:first").attr("checked",true);
			},
			error : function() {
				$.alert("发送失败");
				$("#save").removeAttr("disabled");
			}
		});
		
	});
	
});
</script>
</body>
</html>