<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>版本管理页面</title>
<script type="text/javascript" src="${ctx}/js/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify/jquery.uploadify.min.js"></script>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/version/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
	
    <div class="mgtb10 txq_txtbox clearfix">
        <p class="fl mgr10"><span>软件名称：</span><input name="softName" value="${softNameLike }" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10"><span>软件版本：</span><input name="versionCode" value="${versionCodeLike }" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10"><span>文件类型：</span>
        	<select name="fileType" class="sel" style="width:150px;">
	        	<option value="" <c:if test="${empty fileType}">selected="selected"</c:if>>全部</option>
	        	<option value="0" <c:if test="${fileType=='0'}">selected="selected"</c:if>>安装包</option>
	        	<option value="1" <c:if test="${fileType=='1'}">selected="selected"</c:if>>绿色包</option>
        	</select>
        </p>
        <p class="fl mgr10">
        	<span>更新时间：</span> 
            <input type="text" name="startTime" value="${startTime }" style="width: 100px;" readonly="readonly" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" id="d4311" class="Wdate">
            <span>至</span> 
            <input type="text" name="endTime" value="${endTime }" style="width: 100px;" readonly="readonly" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" id="d4312" class="Wdate">
        </p>
        <p class="fl"><input type="button" onclick="search()" class="btn_blue" value="查询"/></p>
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>软件列表</strong></div>

		<div class="clearfix mgtb10">
			<!-- <input type="button" class="btn_gray" value="全选"/>&nbsp;&nbsp; -->
			<input type="button" class="btn_blue" value="新增版本" onclick="add()"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="生成升级数据" onclick="upgrade()"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="删除" onclick="del()"/>
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="4%"><input type="checkbox" name="ckAll"></th>
				<th width="10%">软件名称</th>
				<th width="10%">软件版本</th>
				<th width="8%">软件类型</th>
				<th width="32%">版本说明</th>
				<th width="14%">更新时间</th>
				<th width="8%">强制更新</th>
				<th width="6%">状态</th>
				<th width="8%">操作</th>
		    </tr>
		    <c:forEach items="${versionList}" var="version" varStatus="status">
			    <tr>
					<td><input type="checkbox" name="ckItm" value="${version.id}"></td>
					<td>${version.softName }</td>
					<td>${version.versionCode }</td>
					<td>
						<c:if test="${version.fileType=='0' }">安装包</c:if>
						<c:if test="${version.fileType=='1' }">绿色包</c:if>
					</td>
					<td>
						<script type="text/javascript">
						var updateContent = '<c:out value="${version.updateContent}"/>';
						if(updateContent.length > 25){
							updateContent = updateContent.substring(0,25)+"...";
						}
						document.write(updateContent);
						</script>
					</td>
					<td><fmt:formatDate value="${version.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<c:if test="${version.forceUpdate=='0' }">否</c:if>
						<c:if test="${version.forceUpdate=='1' }">是</c:if>
					</td>
					<td>
						<c:if test="${version.isleveup=='0' }">不可升级</c:if>
						<c:if test="${version.isleveup=='1' }">可升级</c:if>
					</td>
					<td>
						<a href="javascript:void(0);" class="mgr10" onclick="detail('${version.id}')">查看</a>
						<a href="javascript:void(0);" class="mgr10" onclick="edit('${version.id}')">编辑</a>
					</td>
			    </tr>
		    </c:forEach>
		</table>
    </div>
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${not empty page.totalPage && page.totalPage >= 1 }">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/version/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<!--详情-->
<div class="popup jumpBox detail dis_none">
    <div class="tit"><span class="fl">查看版本</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right">软件名称：</td>
               <td width="75%" align="left"><span id="softName"></span></td>
           </tr>
           <tr>
               <td align="right">软件版本：</td>
               <td align="left"><span id="versionCode"></span></td>
           </tr>
           <tr>
               <td align="right">文件类型：</td>
               <td align="left"><span id="fileType"></span></td>
           </tr>
           <tr>
               <td align="right">是否强制升级：</td>
               <td align="left"><span id="forceUpdate"></span></td>
           </tr>
           <tr>
               <td align="right">文件名称：</td>
               <td align="left"><span id="fileName"></span></td>
           </tr>
           <tr>
               <td align="right">版本说明：</td>
               <td align="left"><div style="height: 100px;overflow :auto" id="updateContent"></div></td>
           </tr>
       </table>
    </div>
</div>

<!--新增  编辑-->
<div class="popup jumpBox add_edit dis_none">
	<input type="hidden" name="id">
	<input type="hidden" name="fileUrl">
	<input type="hidden" name="fileName">
	<input type="hidden" name="fileRename">
    <div class="tit"><span class="fl">标题</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>软件名称：</td>
               <td width="75%" align="left">
				<input type="text" name="softName" class="inp focus"/>
				<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>软件版本：</td>
               <td align="left">
               	<input type="text" name="versionCode" class="inp focus"/>
               	<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>文件类型：</td>
               <td align="left">
               	<select name="fileType" class="sel" style="width:150px;">
		        	<option value="">全部</option>
		        	<option value="0">安装包</option>
		        	<option value="1">绿色包</option>
	        	</select>
	        	<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>是否强制升级：</td>
               <td align="left">
               	<select name="forceUpdate" class="sel" style="width:150px;">
		        	<option value="0">否</option>
		        	<option value="1">是</option>
	        	</select>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>上传文件：</td>
               <td align="left">
               	<input type="file" class="file" id="uploadFile" name="uploadFile.file" />
               	<span id="fileName"></span>
               	<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>版本说明：</td>
               <td align="left">
               	<textarea name="updateContent" rows="5" cols="48" placeholder="请输入软件更新描述......"></textarea>
               	<span class="red"></span>
               </td>
           </tr>
           <tr>
           	<td colspan="2" style="height: 53px;">
           		<input type="button" name="save" class="btn_blue" value="保存" />
           		<span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span>
           		<input type="button" name="close" class="btn_gray" value="取消" />
           	</td>
           </tr>
       </table>
    </div>
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

function getCheckValue(){
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

function detail(id){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/detail",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(soft) {
			if(soft != null && soft !=''){
				$("#softName").text(soft.softName);
				$("#versionCode").text(soft.versionCode);
				$("#fileType").text(soft.fileType=='0'?'安装包':'绿色包');
				$("#forceUpdate").text(soft.forceUpdate=='0'?'否':'是');
				$("#fileName").text(soft.fileName);
				$("#updateContent").text(soft.updateContent);
				$(".detail").jumpBox(true);
			}
		}
	});
}

function emptyAddEdit(){
	$(".add_edit input[type='hidden']").prop("value",'');
	$(".add_edit input[type='text']").prop("value",'');
	$(".add_edit select[name='fileType']").val('');
	$(".add_edit select[name='forceUpdate']").val('0');
	$(".add_edit textarea[name='updateContent']").val('');
	$(".add_edit .red").text('');
	$('#uploadFileQueue').empty();
	
	$(".add_edit input[name='save']").show();
	$(".add_edit input[name='save']").next().hide();
	$(".add_edit input[name='save']").next().next().show();
}

function add(){
	emptyAddEdit();
	$(".add_edit .fl").text("新增软件");
	$(".add_edit").jumpBox(true);
}

function edit(id){
	emptyAddEdit();
	$(".add_edit .fl").text("编辑软件");
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/detail",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(soft) {
			if(soft != null && soft !=''){
				$(".add_edit input[name='id']").val(soft.id);
				$(".add_edit input[name='fileUrl']").val(soft.fileUrl);
				$(".add_edit input[name='fileName']").val(soft.fileName);
				$(".add_edit input[name='fileRename']").val(soft.fileRename);
				$(".add_edit input[name='softName']").val(soft.softName);
				$(".add_edit input[name='versionCode']").val(soft.versionCode);
				$(".add_edit select[name='fileType']").val(soft.fileType);
				$(".add_edit select[name='forceUpdate']").val(soft.forceUpdate);
				$(".add_edit textarea[name='updateContent']").val(soft.updateContent);
				$("#fileName").text(soft.fileName);
				$(".add_edit").jumpBox(true);
			}
		}
	});
}

$(document).ready(function(){ 
	$("#uploadFile").uploadify({
        'uploader'     : '${ctx}/js/uploadify/uploadify.swf',//设置uploadify路径
        'script'       : '${ctx}/manage/version/uploadInfo;jsessionid=<%=session.getId()%>',//请求响应的Action,加jsessionid修正在firefox下文件上传报http error
        'scriptData'   : {}, 
       	'cancelImg'    : '${ctx}/js/uploadify/images/cancel.png',//设置  取消按钮图片的路径  
        'buttonText'   : '上传附件',
        'buttonImg'    : '${ctx}/js/uploadify/images/upload.jpg',
        'width'      : 75,
        'height'     : 25,
        'folder'       : '',//设置上传文件后保存的路径
        'fileExt'     : '*.exe;*.zip',
        'fileDesc'      : '附件格式:*.exe;*.zip',
        'fileDataName' : 'uploadFile.file',//上传文件的文件
        'auto'         : true,//设置是否自动上传  
        'fileSizeLimit'    : 0, //单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
        'multi'        : false,//设置是否多文件上传  
        'removeCompleted': false,//是否完成后移除序列，默认为true
        'onComplete' :function(event,queueId,fileObj,response){
          $("#cancel").show();
          $("#uploadFile").siblings(".red").text("");
          var obj = eval("(" + response+ ")");
          $(".add_edit input[name='fileUrl']").val(obj.fileUrl);
          $(".add_edit input[name='fileName']").val(obj.fileName);
          $(".add_edit input[name='fileRename']").val(obj.fileRename);
        }, 
        //onAllComplete:function(event,data){
        
        //},
        onError: function(event, queueID, fileObj) {
        	
        },
        onCancel: function(event, queueID, fileObj){
        	
        },
        onSelect:function(file){
        	$("#fileName").text("");
        }
	});
	
	$(".add_edit input[name='save']").click(function(){
		var id=$(".add_edit input[name='id']").val();
		var fileUrl=$(".add_edit input[name='fileUrl']").val();
		var fileName=$(".add_edit input[name='fileName']").val();
		var fileRename=$(".add_edit input[name='fileRename']").val();
		var softName=$(".add_edit input[name='softName']").val();
		var versionCode=$(".add_edit input[name='versionCode']").val();
		var fileType=$(".add_edit select[name='fileType']").val();
		var updateContent=$(".add_edit textarea[name='updateContent']").val();
		
		var forceUpdate=$(".add_edit select[name='forceUpdate']").val();
		
		if($.trim(softName)==''){
			$(".add_edit input[name='softName']").siblings(".red").text("请填写软件名称");
			return;
		}
		if($.trim(versionCode)==''){
			$(".add_edit input[name='versionCode']").siblings(".red").text("请填写软件版本");
			return;
		}
		if(fileType==''){
			$(".add_edit select[name='fileType']").siblings(".red").text("请选择文件类型");
			return;
		}
		if($.trim(updateContent)==''){
			$(".add_edit textarea[name='updateContent']").siblings(".red").text("请填写版本说明");
			return;
		}
		if(fileUrl==''){
			$("#uploadFile").siblings(".red").text("请选择上传文件并等待上传完成");
			return;
		}
		
		$(this).hide();
		$(this).next().show();
		$(this).next().next().hide();
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/version/save",
				data : {
					"id" : id,
					"fileUrl"  : fileUrl,
					"fileName"  : fileName,
					"fileRename"  : fileRename,
					"softName"  : softName,
					"versionCode"  : versionCode,
					"fileType"  : fileType,
					"forceUpdate" : forceUpdate,
					"updateContent"  : updateContent
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='success'){
						$(".add_edit").closeBox();
						var txt = "";
						if(id==''){
							txt = "版本新增完成，是否刷新列表？";
						}else{
							txt = "版本编辑完成，是否刷新列表？";
						}
						$.confirm(txt,function(){
							$("#pageForm").submit();
						});
					}
				}
			});
		}, 1);
		
	});
});

function upgrade(){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/queryNew",
		async : false,
		dataType : 'json',
		success : function(soft) {
			if(soft != null && soft !=''){
				$.confirm("生成升级到版本"+soft.versionCode+"的升级文件?",function(){
					$.ajax({
						type : "POST",
						url : "${ctx}/manage/version/createUpgradeFile",
						data : {
							"softId" : soft.id,
							"versionCode"  : soft.versionCode
						},
						async : false,
						dataType : 'text',
						success : function(msg) {
							if(msg=='success'){
								$("#pageForm").submit();
							}
						}
					});
				});
			}else{
				$.alert("没有查询到最新版本的绿色软件");
			}
		}
	});
}

function del(){
	var ids = getCheckValue();
	if(ids==""){
		$.alert("请至少选择一项！");
		return;
	}
	$.confirm("确定要删除选中的版本信息吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/version/deleteSoft",
			data : {"ids" : ids},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='success'){
					$("#pageForm").submit();
				}
			}
		});
	});
}
</script>
</body>
</html>