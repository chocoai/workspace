<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<title>普通用户管理页面</title>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/baiduFile/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
	<%-- <input type="hidden" id="area_code" name="area_code" value="${area_code}"/> --%>

    <div class="mgtb10 txq_txtbox clearfix">
    	<div class="clearfix">
	    	
        </div>
       
    </div>
    
    <div class="mgtb10">
		<div class="base_title"><strong>百度文件列表</strong></div>

		<div class="clearfix mgtb10">
			<!-- 
			<input type="button" class="btn_blue" value="导出区域月报表" onclick="javascript:exportAreaCountExcel();"/>
			<input type="button" class="btn_blue" value="导出学校月报表" onclick="javascript:exportOrgCountExcel();"/>
			
			<input type="button" class="btn_blue" value="导出区域活跃度月报表" onclick="javascript:exportAreaActivityExcel();"/>
			<input type="button" class="btn_blue" value="导出学校活跃度月报表" onclick="javascript:exportOrgActivityExcel();"/>
		 	-->
		 	<input type="button" onclick="add()" class="btn_blue mgr10" value="上传文件"/>
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="12%">文件名</th>
				<th width="5%">下载地址</th>
				<th width="5%">md5</th>
				<th width="5%">文件大小</th>
				<th width="5%">状态</th>
				<th width="5%">描述</th>
				<th width="5%">日期</th>
				<!-- <th width="10%">操作</th> -->
		    </tr>
		    <c:if test="${fn:length(list) < 1}">
		    	<tr>
		    		<td colspan="7" style="color: red">暂无数据</td>
		    	</tr>
		    </c:if>
		    <c:if test="${fn:length(list) >= 1}">
		    	<c:forEach items="${list}" var="obj" varStatus="status">
			    <tr>
					<td>${obj.fileName}</td>
					<td>${obj.downUrl}</td>
					<td>${obj.md5}</td>
					<td>${obj.fileSize}</td>
					<td >
						<c:if test="${obj.status=='0' }">上传中</c:if>
						<c:if test="${obj.status=='1' }">上传完成</c:if>
					</td>
					<td>${obj.description}</td>
					<td><fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			    </tr>
		    </c:forEach>
		    </c:if>
		</table>
    </div>
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${not empty page.totalPage && page.totalPage >= 1 }">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/baiduFile/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<!-- 新增-->
<div class="popup jumpBox add dis_none" style="width: 600px;">
	<input type="hidden" name="fileUrl">
	<input type="hidden" name="fileName">
	<input type="hidden" name="fileRename">
	<input type="hidden" name="fileSize">
	
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">详情</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
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
               <td align="right"><span style="color: red;">*</span>文件说明</td>
               <td align="left" colspan="3">
               		<textarea name="description" rows="5" cols="48" placeholder="请输入软件描述......"></textarea>
               </td>
           </tr>
           <tr>
               <td colspan="6" align="right" style="padding: 0px;text-align: center;">
               		<input type="button" name="save" class="btn_blue" value="保存" />
           			<input type="button" name="close" class="btn_gray" value="取消" />
               </td>
           </tr>
       </table>
    </div>
</div>

<script>

var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'browse',
	url : '${ctx}/manage/baiduFile/uploadInfo',
	flash_swf_url : '${ctx}/js/plupload-2.1.8/Moxie.swf',
	silverlight_xap_url : '${ctx}/js/plupload-2.1.8/Moxie.xap',
	multi_selection : false,
	chunk_size: '10mb', //分块大小
	filters : {
		mime_types: [
			
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
			
			console.log(soft)
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

function add(){
	$(".add").jumpBox(true);
}
$(function(){
	
	
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
		
		var description = $.trim($(".add textarea[name='description']").val());
		
		//console.log(fileUrl)
		
		if(fileUrl==''){
			alert("请上传文件");
			return false;
		}
		
	
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/baiduFile/save",
				data : {
					"fileUrl"  : fileUrl,
					"fileName"  : fileName,
					"fileRename"  : fileRename,
					"description"  : description
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
	
	
});

</script>
</body>
</html>