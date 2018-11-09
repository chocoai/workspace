<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<style type="text/css">
.progress{ height: 4px; font-size: 0; line-height: 4px; background: orange; width: 0;}
.num{ margin-left: 20px;}
#file-list img{cursor: pointer;}
</style>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/softBackDoor/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
	
    <div class="mgtb10 txq_txtbox clearfix">
        
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>软件列表</strong></div>

		<div class="clearfix mgtb10">
			<!-- <permission:button value="新增版本" name="button" method="add()"  style="btn_blue mgr10"></permission:button>
			<permission:button value="删除" name="button" method="del()"  style="btn_blue"></permission:button> -->
			<input type="button" onclick="add()" class="btn_blue mgr10" value="新增版本"/>
			<input type="button" onclick="del()" class="btn_blue" value="删除"/>
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="4%"><input type="checkbox" name="ckAll"></th>
				<th width="10%">软件</th>
				<th width="6%">版本</th>
				<th width="7%">下载</th>
				<th width="15%">md5</th>
				<th width="10%">创建时间</th>
				<th width="5%">更新状态</th>
				<th width="7%">线路</th>
				<th width="20%">操作</th>
		    </tr>
		    <c:forEach items="${list}" var="softBackDoor" varStatus="status">
			    <tr>
					<td><input type="checkbox" name="ckItm" value="${softBackDoor.id}"></td>
					<td>
						<c:if test="${softBackDoor.softType=='0' }">标准版</c:if>
						<c:if test="${softBackDoor.softType=='1' }">教师版</c:if>
						<c:if test="${softBackDoor.softType=='3' }">国际版</c:if>
						<c:if test="${softBackDoor.softType=='4' }">昌江版</c:if>
						<c:if test="${softBackDoor.softType=='5' }">立思辰版</c:if>
						<c:if test="${softBackDoor.softType=='6' }">辽宁移动版</c:if>
						<c:if test="${softBackDoor.softType=='7' }">成都佳发版</c:if>
						<c:if test="${softBackDoor.softType=='8' }">微课通</c:if>
						<c:if test="${softBackDoor.softType=='9' }">海康威视版</c:if>
						<c:if test="${softBackDoor.softType=='10' }">海信版</c:if>
					</td>
					<td>${softBackDoor.versionCode}</td>
					<td>
					<c:if test="${softBackDoor.bosStatus ==0 }">
						上传百度云中...
					</c:if>
					
					<c:if test="${softBackDoor.bosStatus ==1 }">
						<a href="${softBackDoor.downUrl}" target="_blank">下载</a>
					</c:if>
						
					
					</td>
					<td>${softBackDoor.md5}</td>
					<td><fmt:formatDate value="${softBackDoor.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<c:if test="${softBackDoor.status=='0' }">不可更新</c:if>
						<c:if test="${softBackDoor.status=='1' }">可更新</c:if>
					</td>
					
					<td>
						<c:if test="${softBackDoor.cbt =='0' }" >测试</c:if>
						<c:if test="${softBackDoor.cbt =='1' }"> 正式</c:if>
					</td>
					
					<td>
						<c:if test="${softBackDoor.status ==0 }">
							<permission:button value="开放客户端下载" name="a" method="updateStatus('${softBackDoor.id}','1')"  style=" mgr10"></permission:button>
						</c:if>
						<c:if test="${softBackDoor.status == 1 }">
							<permission:button value="关闭客户端下载" name="a" method="updateStatus('${softBackDoor.id}','0')"  style=" mgr10"></permission:button>
						</c:if>
						
						<c:if test="${softBackDoor.cbt == '0'}">
							<permission:button value="正式" name="a" method="updateCrb('${softBackDoor.id}','1')"  style=" mgr10"></permission:button>
						</c:if>
						
						<c:if test="${softBackDoor.cbt == '1'}">
							<permission:button value="测试" name="a" method="updateCrb('${softBackDoor.id}','0')"  style=" mgr10"></permission:button>
						</c:if>
						
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
<div class="popup jumpBox detail dis_none txq_main" style="width:700px;">
    <div class="tit"><span class="fl">查看版本</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="15%" align="right">软件名称：</td>
               <td width="35%" align="left"><span id="softName"></span></td>
               <td width="15%" align="right">软件版本：</td>
               <td width="35%" align="left"><span id="versionCode"></span></td>
           </tr>
           <tr>
           	   <td align="right">软件类型：</td>
               <td align="left"><span id="softType"></span></td>
               <td align="right">文件类型：</td>
               <td align="left"><span id="fileType"></span></td>
           </tr>
           <tr>
               <td align="right">强制升级：</td>
               <td align="left"><span id="forceUpdate"></span></td>
               <td align="right">有效状态：</td>
               <td align="left"><span id="status"></span></td>
           </tr>
           <tr>
               <td align="right">文件名称：</td>
               <td align="left" colspan="3"><span id="fileName"></span></td>
           </tr>
           <tr>
               <td align="right">版本说明：</td>
               <td align="left" colspan="3"><div style="height: 100px;overflow :auto" id="updateContent"></div></td>
           </tr>
           <tr>
               <td align="right">下载地址：</td>
               <td align="left" colspan="3"><span id="downloadUrl"></span></td>
           </tr>
       </table>
    </div>
</div>

<!--新增-->
<div class="popup jumpBox add dis_none txq_main" style="width:700px;">
	<input type="hidden" name="fileUrl">
	<input type="hidden" name="fileName">
	<input type="hidden" name="fileRename">
	<input type="hidden" name="fileSize">
    <div class="tit"><span class="fl">新增</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td align="right"><span style="color: red;">*</span>软件类型：</td>
               <td align="left">
               	<select name="softType" class="sel" style="width:100px;">
		        	<option value="0">标准版</option>
		        	<option value="1">教师版</option>
		        	<option value="2">在线安装包</option>
		        	<option value="3">国际版</option>
		        	<option value="4">昌江版</option>
		        	<option value="5">立思辰版</option>
		        	<option value="6">辽宁移动版</option>
		        	<option value="7">成都佳发版</option>
		        	<option value="8">微课通</option>
		        	<option value="9">海康威视版</option>
		        	<option value="10">海信</option>
	        	</select>
               </td>
               <td width="15%" align="right"><span style="color: red;">*</span>软件版本：</td>
               <td width="35%" align="left">
               	<input type="text" name="versionCode" class="inp focus" />
               </td>
           </tr>
           <tr>
           	   <td align="right"><span style="color: red;">*</span>补丁版本：</td>
               <td align="left" colspan="3">
               		<input type="text" name="zipCodeVersion" class="inp focus" />
               </td>
               
           </tr>
           
           <tr>
               <td align="right"><span style="color: red;">*</span>上传文件：</td>
               <td align="left" colspan="3">
               	<input type="button" style="cursor: pointer;" value="选择文件..." id="browse" />
				<input type="button" style="cursor: pointer;" value="开始上传" id="upload-btn" />
				<ul id="file-list"></ul>
               	<span class="red"></span>
               </td>
           </tr>
           
           <tr>
           	<td colspan="4" style="height: 53px;">
           		<input type="button" name="save" class="btn_blue" value="保存" />
           		<input type="button" name="close" class="btn_gray" value="取消" />
           		<span id="addMsg" class="red"></span>
           	</td>
           </tr>
       </table>
    </div>
</div>

<!--编辑-->
<div class="popup jumpBox edit dis_none txq_main" style="width:700px;">
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">编辑</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="15%" align="right"><span style="color: red;">*</span>软件名称：</td>
               <td width="35%" align="left">
				<input type="text" name="softName" class="inp focus"/>
               </td>
                <td width="15%" align="right"><span style="color: red;">*</span>软件版本：</td>
               <td width="35%" align="left">
               	<input type="text" name="versionCode" class="inp focus" onkeypress="return (/[\d.]/.test(String.fromCharCode(event.keyCode)))"/>
               </td>
           </tr>
           <tr>
           	   <td align="right">软件类型：</td>
               <td align="left"><span class="softType"></span></td>
               <td align="right">文件类型：</td>
               <td align="left"><span class="fileType"></span></td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>强制升级：</td>
               <td align="left">
               	<select name="forceUpdate" class="sel" style="width:150px;">
		        	<option value="0">否</option>
		        	<option value="1">是</option>
	        	</select>
               </td>
               <td align="right"><span style="color: red;">*</span>有效状态：</td>
               <td align="left">
               	<select name="status" class="sel" style="width:150px;">
		        	<option value="0">有效</option>
		        	<option value="1">无效</option>
	        	</select>
               </td>
           </tr>
           <tr>
               <td align="right">上传文件：</td>
               <td align="left" colspan="3"><span class="fileName"></span></td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>版本说明：</td>
               <td align="left" colspan="3">
               	<textarea name="updateContent" rows="5" cols="48" placeholder="请输入软件更新描述......"></textarea>
               	<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right">下载地址：</td>
               <td align="left" colspan="3">
               	<input type="text" name="downloadUrl" class="inp focus" style="width:450px;"/>
               </td>
           </tr>
           <tr>
           	<td colspan="4" style="height: 53px;">
           		<input type="button" name="save" class="btn_blue" value="保存" />
           		<input type="button" name="close" class="btn_gray" value="取消" />
           		<span id="editMsg" class="red"></span>
           	</td>
           </tr>
       </table>
    </div>
</div>

<script>

function updateStatus(softId,status){
	setTimeout(function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/softBackDoor/updateStatus",
			data : {
				"id":softId,
				"status":status
			},
			async : false,
			dataType : 'text',
			success : function(msg) {
				$.alert("上传云中，请耐心等待！");
				$("#pageForm").submit();
			}
		});
	},10);
	
}

function updateCrb(softId,cbt){
	setTimeout(function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/softBackDoor/updateCrb",
			data : {
				"id":softId,
				"cbt":cbt
			},
			async : false,
			dataType : 'text',
			success : function(msg) {
				$.alert("上传云中，请耐心等待！");
				$("#pageForm").submit();
			}
		});
	},10);
	
}

function fileUploadBos(softId){
	setTimeout(function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/version/fileUploadBos",
			data : {
				"id":softId
			},
			async : false,
			dataType : 'text',
			success : function(msg) {
				$.alert("上传云中，请耐心等待！");
				$("#pageForm").submit();
			}
		});
	},10);
}

function uploadBos(softId){
	setTimeout(function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/version/uploadBos",
			data : {
				"id":softId
			},
			async : false,
			dataType : 'text',
			success : function(msg) {
				
				$.alert("上传云中，请耐心等待！");
				$("#pageForm").submit();
				//if(msg=='000000'){
				//	$.alertClose();
				//	dataList();
				//}else{
				//	$.alert("操作失败！");
				//}
			}
		});
	},10);
}

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

var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'browse',
	url : '${ctx}/manage/version/uploadInfo1',
	flash_swf_url : '${ctx}/js/plupload-2.1.8/Moxie.swf',
	silverlight_xap_url : '${ctx}/js/plupload-2.1.8/Moxie.xap',
	multi_selection : false,
	chunk_size: '10mb', //分块大小
	filters : {
		mime_types: [
			{title : "exe files", extensions : "exe"},
			{title : "zip files", extensions : "zip"}
		],
		prevent_duplicates : true
	},
	
	init: {
		FilesAdded :  function(uploader, files) {
			if(uploader.files.length == 2){
				uploader.files.splice(uploader.files.length-1, 1);
				return;
			}
			plupload.each(files, function(file) {
				var fileType = $(".add select[name='fileType']").val();
				var filename = file.name;
				var suffix = filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
				if(fileType == "0" && suffix != "exe"){
					$("#browse").siblings(".red").text("请选择后缀为“.exe”的安装包");
					uploader.files.splice(uploader.files.length-1, 1);
					return;
				}else if(fileType=="1" && suffix != "zip"){
					$("#browse").siblings(".red").text("请选择后缀为“.zip”的绿色包");
					uploader.files.splice(uploader.files.length-1, 1);
					return;
				}else{
					$("#browse").siblings(".red").text("");
				}
				$(".add input[name='fileSize']").val(file.size);
				var html = '<li id="' + file.id +'"><img style="cursor: pointer;" src="${ctx}/js/uploadify/images/cancel.png" border="0"><span class="file-name">' + filename + '(' + plupload.formatSize(file.size) + ')</span><span class="num"></span></li>';
				$(html).appendTo('#file-list');
			});
		},
		UploadProgress: function(uploader, file) {
			//$(file.id+' .progress').css('width',file.percent + '%');//控制进度条
			$('#'+file.id+' .num').html(file.percent + '%');
		},
		FileUploaded:function(uploader,file,responseObject){
			var soft = eval('('+responseObject.response+')');
			$(".add input[name='fileUrl']").val(soft.fileUrl);
			$(".add input[name='fileName']").val(soft.fileName);
			$(".add input[name='fileRename']").val(soft.fileRename);
		},
		UploadComplete : function(uploader,files){
			
		},
		FilesRemoved : function(uploader,files){
			
		},
		Error: function(uploader, err) {
			
		}
	}
});
uploader.init();//初始化

//上传按钮
$('#upload-btn').click(function(){
	uploader.start(); //开始上传
});

function detail(id){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/detail",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(soft) {
			if(soft != null){
				$("#softName").text(soft.softName);
				$("#versionCode").text(soft.versionCode);
				var softTypeName = '';
				if(soft.softType=='0'){
					softTypeName='标准版';
				}else if(soft.softType=='1'){
					softTypeName='教师版';
				}else if(soft.softType=='3'){
					softTypeName='国际版';
				}else if(soft.softType=='4'){
					softTypeName='昌江版';
				}else if(soft.softType=='5'){
					softTypeName='立思辰版';
				}else if(soft.softType=='6'){
					softTypeName='辽宁移动版';
				}else if(soft.softType=='7'){
					softTypeName='成都佳发版';
				}else if(soft.softType=='8'){
					softTypeName='微课通';
				}else if(soft.softType=='9'){
					softTypeName='海康威视版';
				}else if(soft.softType=='10'){
					softTypeName='海信';
				}
				
				
				$("#softType").text(softTypeName);
				$("#fileType").text(soft.fileType=='0'?'安装包':'绿色包');
				$("#forceUpdate").text(soft.forceUpdate=='0'?'否':'是');
				$("#fileName").text(soft.fileName+"("+plupload.formatSize(soft.fileSize)+")");
				$("#updateContent").text(soft.updateContent);
				$("#downloadUrl").text(soft.downloadUrl);
				$("#status").text(soft.status=='0'?'有效':'无效');
				$(".detail").jumpBox(true);
			}
		}
	});
}

function add(){
	$(".add input[type='hidden']").prop("value",'');
	$(".add input[type='text']").prop("value",'');
	$(".add select[name='softType']").val('');
	$(".add select[name='fileType']").val('');
	$(".add select[name='status']").val('0');
	$(".add select[name='forceUpdate']").val('0');
	$(".add textarea[name='updateContent']").val('');
	$(".add .red").text('');
	$("#addMsg").text('');
	
	$(".add").jumpBox(true);
}

function edit(id){
	$(".edit .red").text('');
	$("#editMsg").text('');
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/detail",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(soft) {
			if(soft != null){
				$(".edit input[name='id']").val(soft.id);
				$(".edit input[name='softName']").val(soft.softName);
				$(".edit input[name='versionCode']").val(soft.versionCode);
				$(".edit select[name='forceUpdate']").val(soft.forceUpdate);
				$(".edit textarea[name='updateContent']").val(soft.updateContent);
				$(".edit select[name='status']").val(soft.status);
				
				$(".edit .fileName").text(soft.fileName+"("+plupload.formatSize(soft.fileSize)+")");
				var softTypeName = '';
				if(soft.softType=='0'){
					softTypeName='标准版';
				}else if(soft.softType=='1'){
					softTypeName='教师版';
				}else if(soft.softType=='2'){
					softTypeName='在线安装包';
				}else if(soft.softType=='3'){
					softTypeName='国际版';
				}else if(soft.softType=='4'){
					softTypeName='昌江版';
				}else if(soft.softType=='5'){
					softTypeName='立思辰版';
				}else if(soft.softType=='6'){
					softTypeName='辽宁移动版';
				}else if(soft.softType=='7'){
					softTypeName='成都佳发版';
				}else if(soft.softType=='8'){
					softTypeName='微课通';
				}else if(soft.softType=='9'){
					softTypeName='海康威视版';
				}else if(soft.softType=='10'){
					softTypeName='海信版';
				}
				
				
				
				$(".edit .softType").text(softTypeName);
				$(".edit .fileType").text(soft.fileType=='0'?'安装包':'绿色包');
				
				$(".edit input[name='downloadUrl']").val(soft.downloadUrl);
				
				$(".edit").jumpBox(true);
			}
		}
	});
}

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
		var versionCode=$.trim($(".add input[name='versionCode']").val())
		var fileSize=$(".add input[name='fileSize']").val();
		var softType=$(".add select[name='softType']").val();
		
		var fileType=$(".add select[name='fileType']").val();
		
		var forceUpdate=$(".add select[name='forceUpdate']").val();
		
		var status=$(".add select[name='status']").val();
		
		
		var zipCodeVersion = $(".add input[name='zipCodeVersion']").val();
		
		console.log(1)
		
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
		
		console.log(1)
		
		if(fileUrl==''){
			$("#addMsg").text("请选择上传文件并等待上传完成");
			return;
		}
		
		console.log(1)
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/softBackDoor/add",
				data : {
					"fileUrl"  : fileUrl,
					"versionCode"  : versionCode,
					"softType"  : softType,
					"zipCodeVersion" : zipCodeVersion
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

var h=true;
function unzip(id){
	$.alert("正在解压中，请耐心等待...");
	if(h){
		h=false;
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/version/unzip",
			data : {"id" : id},
			async : true,
			dataType : 'text',
			success : function(msg) {
				h=true;
				$.alertClose();
				if(msg=='success'){
					$.alert("解压成功！",function(){
						$("#pageForm").submit();
					});
				}else if(msg=='error'){
					$.alert("解压失败！");
				}
			}
		});
	}
	
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
			url : "${ctx}/manage/softBackDoor/delete",
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

function putTmpfs(id){
	var text = '确定要把文件放入内存吗？';
	$.confirm(text,function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/version/putTmpfs",
			data : {"softId" : id},
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
//
function openDownload(id,softType){
	var text = '确定要开放该版本的下载？';
	$.confirm(text,function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/version/openDownload",
			data : {"id" : id,"softType":softType},
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

function softComponentList(id)
{
	window.location.href='${ctx}/manage/softComponent/list?id='+id;
}
</script>
</body>
</html>