<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开机日志</title>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<style type="text/css">
.progress{ height: 4px; font-size: 0; line-height: 4px; background: orange; width: 0;}
.num{ margin-left: 20px;}
#file-list img{cursor: pointer;}
</style>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/widget/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
	
    <div class="mgtb10 txq_txtbox clearfix">
        <p class="fl mgr10"><span>机器信息：</span><input name="machineInfo" value="${machineInfo}" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10"><span>模块：</span><input name="widgetName" value="${widgetName}" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10">
        	<span>更新时间：</span> 
            <input type="text" name="startTime" value="${startTime }" style="width: 150px;" readonly="readonly" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" id="d4311" class="Wdate">
            <span>至</span> 
            <input type="text" name="endTime" value="${endTime }" style="width: 150px;" readonly="readonly" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" id="d4312" class="Wdate">
        </p>
        <p class="fl">
        <input type="button" onclick="search()" class="btn_blue" value="查询"/>
        </p>
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>日志列表</strong></div>

		<div class="clearfix mgtb10">
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="2%">使用次数</th>
				<th width="6%">机器信息</th>
				<th width="6%">操作系统</th>
				<th width="6%">模块</th>
				<th width="6%">描述</th>
				<th width="6%">时间</th>
		    </tr>
		    <c:forEach items="${widgetList}" var="widget" varStatus="status">
			    <tr>
			    	<td>${widget.USE_COUNT}</td>
					<td>${widget.MACHINE_INFO}</td>
					<td>${widget.OPERATION_SYSTEM_INFO}</td>
					<td>${widget.WIDGET_NAME}</td>
					<td>${widget.DESCRIPTION}</td>
					<td><fmt:formatDate value="${widget.CREATE_TIME}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			    </tr>
		    </c:forEach>
		</table>
    </div>
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${not empty page.totalPage && page.totalPage >= 1 }">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/widget/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>





<script>
//tab
$("div[name='slide']").tabControl("*[name='slideTag'] a" , "*[name='slideCont']" , '.more');
//弹出层
$('*[name="roleChoose"]').each(function(){$(this).click(function(){$(".jumpBox").jumpBox(true);});});

var $ckAll = $("input[name='ckAll']");
var $ckItm = $("input[name='ckItm']");
var len = $ckItm.length;
$ckAll.click(function() {
    $ckItm.prop("checked",this.checked);
});
$ckItm.click(function() {
    var b=$ckItm.filter(":checked").length==len;
    var flag=$ckAll.prop("checked",b?true:false);
});

function search(){
	$("#search_type").val(0);
	$("#pageForm").submit();
}


uploader.init();//初始化




$(document).ready(function(){ 
	$("#browse").click(function(){
		if(uploader.files.length == 1){
			$("#browse").siblings(".red").text("只能上传一个文件");
		}
	});
	
	//先暂停上传，后清空上传列表
	$(".add *[name='close']").click(function(){
		//如果正在上传，就先停止上传后删除上传队列；否则，删除队列同时删除文件
		var fileUrl=$(".add input[name='fileUrl']").val();
		if(uploader.total.uploaded==0){
			uploader.stop();
		}else if(fileUrl != ''){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/version/deleteDisableFile",
				data : {"fileUrl" : fileUrl},
				async : false,
				dataType : 'text',
				success : function(msg) {
					
				}
			});
		}
		
		$("#file-list").html('');
		
		$.each(uploader.files, function (i, file) {
	        uploader.files.splice(i, 1);
	    });
	});
	
	//删除文件
	$('#file-list img').live("click",function(){
		//如果正在上传，就先停止上传后删除上传队列；否则，删除队列同时删除文件
		var fileUrl=$(".add input[name='fileUrl']").val();
		if(uploader.total.uploaded==0){
			uploader.stop();
		}else if(fileUrl != ''){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/version/deleteDisableFile",
				data : {"fileUrl" : fileUrl},
				async : false,
				dataType : 'text',
				success : function(msg) {
					
				}
			});
		}
		
		
		var fileid = $(this).parent().attr("id");
		$.each(uploader.files, function (i, file) {
	        if(file.id===fileid){
	        	uploader.files.splice(i, 1);
	        }
	    });
		
		$(this).parent().remove();
		$(".add input[type='hidden']").prop("value",'');
		$("#browse").siblings(".red").text("");
	});
	
	$(".add input[name='save']").click(function(){
		var fileUrl=$.trim($(".add input[name='fileUrl']").val());
		var fileName=$(".add input[name='fileName']").val();
		var fileRename=$(".add input[name='fileRename']").val();
		var softName=$.trim($(".add input[name='softName']").val());
		var versionCode=$.trim($(".add input[name='versionCode']").val());
		var fileSize=$(".add input[name='fileSize']").val();
		var softType=$(".add select[name='softType']").val();
		var fileType=$(".add select[name='fileType']").val();
		var updateContent=$.trim($(".add textarea[name='updateContent']").val());
		var forceUpdate=$(".add select[name='forceUpdate']").val();
		var status=$(".add select[name='status']").val();
		
		var downloadUrl=$(".add input[name='downloadUrl']").val();
		
		if(softName==''){
			$("#addMsg").text("请填写软件名称");
			return;
		}else if(softName.length>50){
			$("#addMsg").text("软件名称长度不能大于50");
			return;
		}
		
		if(versionCode==''){
			$("#addMsg").text("请填写软件版本");
			return;
		}else if(versionCode.length>20){
			$("#addMsg").text("软件版本长度不能大于20");
			return;
		}else if(/^[(\d|\.)]+$/.test(versionCode)==false){
			$("#addMsg").text("软件版本只能为点符分隔的数字");
			return;
		}
		
		if(updateContent==''){
			$("#addMsg").text("请填写版本说明");
			return;
		}
		
		if(fileUrl==''){
			$("#addMsg").text("请选择上传文件并等待上传完成");
			return;
		}
		
		$("#addMsg").text("");
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/version/add",
				data : {
					"fileUrl"  : fileUrl,
					"fileName"  : fileName,
					"fileRename"  : fileRename,
					"softName"  : softName,
					"versionCode"  : versionCode,
					"softType"  : softType,
					"fileType"  : fileType,
					"forceUpdate" : forceUpdate,
					"updateContent"  : updateContent,
					"fileSize" : fileSize,
					"status" : status,
					"downloadUrl":downloadUrl
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='success'){
						$(".add").closeBox();
						$("#pageForm").submit();
					}
				}
			});
		}, 1);
		
	});
	
	$(".edit input[name='save']").click(function(){
		var id=$(".edit input[name='id']").val();
		var softName=$.trim($(".edit input[name='softName']").val());
		var versionCode=$.trim($(".edit input[name='versionCode']").val());
		var updateContent=$.trim($(".edit textarea[name='updateContent']").val());
		var forceUpdate=$(".edit select[name='forceUpdate']").val();
		var status=$(".edit select[name='status']").val();
		
		var downloadUrl=$(".edit input[name='downloadUrl']").val();
		
		if(softName==''){
			$("#editMsg").text("请填写软件名称");
			return;
		}else if(softName.length>50){
			$("#editMsg").text("软件名称长度不能大于50");
			return;
		}
		
		if(versionCode==''){
			$("#editMsg").text("请填写软件版本");
			return;
		}else if(versionCode.length>20){
			$("#editMsg").text("软件版本长度不能大于20");
			return;
		}else if(/^[(\d|\.)]+$/.test(versionCode)==false){
			$("#editMsg").text("软件版本只能为点符分隔的数字");
			return;
		}
		
		if(updateContent==''){
			$("#editMsg").text("请填写版本说明");
			return;
		}
		
		$("#editMsg").text("");
		
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/version/edit",
			data : {
				"id" : id,
				"softName"  : softName,
				"versionCode"  : versionCode,
				"forceUpdate" : forceUpdate,
				"updateContent"  : updateContent,
				"status" : status,
				"downloadUrl":downloadUrl
			},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='success'){
					$(".edit").closeBox();
					$("#pageForm").submit();
				}
			}
		});
		
	});
});

</script>
</body>
</html>