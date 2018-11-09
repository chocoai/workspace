<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>白名单列表</title>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<link rel="stylesheet" href="http://apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<style>
.select2-dropdown{
       z-index: 11111111111;
}
.ui-autocomplete{
    z-index: 11111111111;
    max-height: 100px;
    overflow-y: auto;
    /* 防止水平滚动条 */
    overflow-x: hidden;
}
 * html .ui-autocomplete {
    height: 100px;
 }
</style>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/schoolApp/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
    
    <div class="mgtb10">
		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="新增" onclick="add()"/>&nbsp;&nbsp;
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="5%">平台</th>
				<th width="5%">学校</th>
				<th width="10%">应用</th>
				<th width="10%">版本</th>
				<th width="10%">图标</th>
				<th width="10%">下载</th>
				<th width="10%">日期</th>
				<th width="5%">状态</th>
				<th width="5%">操作</th>
		    </tr>
		     <c:if test="${fn:length(list) < 1}">
		    	<tr>
		    		<td colspan="9" style="color: red">暂无数据</td>
		    	</tr>
		    </c:if>
		    <c:if test="${fn:length(list) >= 1}">
		    <c:forEach items="${list}" var="obj" varStatus="status">
			    <tr>
					<td>
						 ${obj.platformName }
					</td>
					<td>
					     ${obj.orgName}
					</td>
					<td>
					     ${obj.appName }
					</td>
					<td>
					     ${obj.versionCode }
					</td>
					<td>
						<img alt="" src="${obj.icon }">
					     
					</td>
					<td>
						<c:if test="${not empty obj.downUrl}">
							<a href="${obj.downUrl}" target="_blank">下载 </a>
						</c:if>
						
						<c:if test="${empty obj.downUrl}">
							上传云中
						</c:if>
					</td>
					<td>
					    <fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						<c:if test="${obj.status eq 1}">
							停用
						</c:if>	
						
						<c:if test="${obj.status eq 2}">
							启用
						</c:if>
					</td>
					<td>
					<%-- <a href="javascript:void(0);" class="mgr10" onclick="edit('${obj.id}')">编辑</a> --%>
					<c:if test="${obj.status eq 1}">
							<a href="javascript:void(0);" class="mgr10" onclick="updateStatus('${obj.id}','2')">启用</a>
					</c:if>	
						
					<c:if test="${obj.status eq 2}">
							<a href="javascript:void(0);" class="mgr10" onclick="updateStatus('${obj.id}','1')">停用</a>
					</c:if>
					
					<a href="javascript:void(0);" class="mgr10" onclick="del('${obj.id}')">删除</a>
					</td>					

			    </tr>
		    </c:forEach>
		    </c:if>
		</table>
    </div>
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${not empty page.totalPage && page.totalPage > 1 }">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/schoolApp/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<!--新增-->
<div class="popup jumpBox add dis_none txq_main" style="width:700px;margin-top:100px">
	<input type="hidden" name="id">
	<input type="hidden" name="md5">
	<input type="hidden" name="filePath">
	<input type="hidden" name="fileSize">
    <div class="tit"><span class="fl">新增</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
               <td width="15%" align="right"><span style="color: red;">*</span>平台：</td>
               <td width="35%" align="left"  colspan="3">
				<select id="addPlatformCode" name="platformCode" class="sel mgr10" style="width:150px;">
					<option value="">全部</option>
					<c:forEach items="${platformInfoList}" var="a" varStatus="st">
						<option value="${a.platformCode }" >${a.platformName }</option>
					</c:forEach>
				</select>
               </td>
               
           </tr>

			<tr>
               <td width="15%" align="right"><span style="color: red;">*</span>省份：</td>
               <td width="35%" align="left"  colspan="3">
					<select id="province" name="provincecode" class="sel mgr10" style="width:150px;">
						<option value="">全部</option>
						<c:forEach items="${provinceList}" var="a" varStatus="st">
							<option value="${a.areaCode }" >${a.areaName }</option>
						</c:forEach>
					</select>
               </td>
           </tr>
           
           <tr>
               <td width="15%" align="right"><span style="color: red;">*</span>地区：</td>
               <td width="35%" align="left"  colspan="3">
					<select id="city" name="citycode" class="sel mgr10" style="width:150px;">
						<option value="">全部</option>
					</select>
               </td>
           </tr>
		
			<tr>
               <td width="15%" align="right"><span style="color: red;">*</span>学校：</td>
               <td width="35%" align="left"  colspan="3">
					<input class="wql_inp" type="text" name="orgName"  id="orgName" style="width:395px;" >             
                    <div class="wql_tips" name="orgNameError"></div>
					<input name="orgaId" type="hidden" />
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
               <td width="15%" align="right"><span style="color: red;">*</span>应用</td>
               <td width="35%" align="left">
				<input type="text" name="appName" class="inp focus"/>
				<span class="red"></span>
				<img alt="" src="" name="icon">
               </td>
               
               <td  align="right"><span style="color: red;">*</span>包名：</td>
               <td align="left" >
               	<input type="text" name="packageName" class="inp focus"/>
				<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td  align="right"><span style="color: red;">*</span>内部版本号：</td>
               <td  align="left">
				<input type="text" name="interVersionCode" class="inp focus"/>
				<span class="red"></span>
               </td>
               
               <td  align="right"><span style="color: red;">*</span>外部版本号：</td>
               <td align="left" >
               	<input type="text" name="versionCode" class="inp focus"/>
				<span class="red"></span>
               </td>
           </tr>

           <tr>
               <td align="right">说明：</td>
               <td align="left" colspan="3">
               	<textarea name="description" rows="5" cols="48" placeholder="请输入描述......"></textarea>
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

<script src="http://apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script>

function updateStatus(id,status){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/schoolApp/updateStatus",
		data : {"id" : id,"status" : status},
		async : false,
		dataType : 'text',
		success : function(data) {
			$("#pageForm").submit();
		}
	});
}

function del(id){
	
	$.confirm("确定要删除选中的版本信息吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/schoolApp/delete",
			data : {"id" : id},
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


function queryArea(levelId,parentId){
	var htmlStr = '<option value="">全部</option>';
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/area/queryArea",
		data : {"levelId" : levelId,"parentId" : parentId},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null && data.list != null && data.list.length > 0){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i];
					htmlStr += '<option value="'+obj.areaCode+'">'+obj.areaName+'</option>';
				}
			}
		}
	});
	return htmlStr;
}

var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'browse',
	url : '${ctx}/manage/schoolApp/analysisApk',
	flash_swf_url : '${ctx}/js/plupload-2.1.8/Moxie.swf',
	silverlight_xap_url : '${ctx}/js/plupload-2.1.8/Moxie.xap',
	multi_selection : false,
	chunk_size: '10mb', //分块大小
	filters : {
		mime_types: [
			{title : "apk files", extensions : "apk"}
		]
	},
	
	init: {
		FilesAdded :  function(uploader, files) {
			if(uploader.files.length == 2){
				uploader.files.splice(uploader.files.length-1, 1);
				return;
			}
			plupload.each(files, function(file) {
				$(".add input[name='file_size']").val(file.size);
				var html = '<li id="' + file.id +'"><img style="cursor: pointer;" src="${ctx}/js/uploadify/images/cancel.png" border="0"><span class="file-name">' + file.name + '(' + plupload.formatSize(file.size) + ')</span><span class="num"></span></li>';
				$(html).appendTo('#file-list');
			});
		},
		UploadProgress: function(uploader, file) {
			//$(file.id+' .progress').css('width',file.percent + '%');//控制进度条
			$('#'+file.id+' .num').html(file.percent + '%');
		},
		FileUploaded:function(uploader,file,responseObject){
			var app = eval('('+responseObject.response+')');
			$(".add img[name='icon']").attr("src",app.iconPath);
			$(".add input[name='appName']").val(app.label);
			$(".add input[name='interVersionCode']").val(app.versionCode);
			$(".add input[name='versionCode']").val(app.version);
			$(".add input[name='packageName']").val(app.packageName);
			$(".add input[name='md5']").val(app.md5);
			$(".add input[name='fileSize']").val(app.fileSize);
			$(".add input[name='filePath']").val(app.filePath);
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



$(function(){
	$("input[name='orgName']").autocomplete({
		
	    source: function(request, response ){
	  	  var platformCode = $("select[name='platformCode']").val();
	      var province = $("#province").val();
	      var city = $("#city").val();
	  	  $.ajax({  
	        url : "getAllSchool",  
	        type : "post",  
	        dataType : "json",  
	        data : {
	      	  "orgName":$("#orgName").val(),
	      	  "platformCode":platformCode,
	      	  "province": province,
	      	  "city": city
	        },
	        success: function( data ) {  

	             response( $.map( data, function( item ) {
	          	   	 console.log(item);
	                   return {
	                   label: item.text,
	                   value: item.text,
	                   id:item.id//选中后，填充到id里面的值  
	                   }  
	        }));  
	       }  
	  });
	    },
	    scrollHeight: 300,
			scroll: true,
			minLength: 2,
	select: function( event, ui ) {  
		 $("input[name='orgaId']").val(ui.item.id);
		 
		 console.log($("input[name='orgaId']").val())
	} 
	});


	$("#province").bind("change", function(){
		$('#city').html('<option value="">全部</option>');
		if($("#province").val()==''){
			$('#city').html('<option value="">全部</option>');
			return;	
		}
		$('#city').html(queryArea(2,$("#province").val()));
	});

	$("#city").bind("change", function(){
		$('#area').html('<option value="">全部</option>');
		if($("#city").val()==''){
			$('#area').html('<option value="">全部</option>');
			return;	
		}
		$('#area').html(queryArea(3,$("#city").val()));
	});
	
	
	
	$(".add input[name='save']").click(function(){
		var filePath=$(".add input[name='filePath']").val();
		var appName=$(".add input[name='appName']").val();
		var versionCode=$(".add input[name='versionCode']").val();
		var interVersionCode=$(".add input[name='interVersionCode']").val();
		var packageName=$(".add input[name='packageName']").val();
		var fileSize=$(".add input[name='fileSize']").val();
		var iconPath = $(".add img[name='icon']").attr("src");
		var orgId = $(".add input[name='orgaId']").val();
		var orgName = $(".add input[name='orgName']").val();
		var platformCode =$(".add select[name='platformCode']").val();
		var description=$(".add textarea[name='description']").val();
		var md5 = $(".add input[name='md5']").val();
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/schoolApp/save",
				data : {
					"filePath" : filePath,
					"appName"  : appName,
					"versionCode"  : versionCode,
					"interVersionCode"  : interVersionCode,
					"packageName"  : packageName,
					"fileSize"  : fileSize,
					"iconPath"  : iconPath,
					"orgId" : orgId,
					"orgName" : orgName,
					"platformCode" : platformCode,
					"description" : description,
					"md5" : md5
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



//新增产品弹出框
function add(){
	$(".add input[type='hidden']").prop("value",'');
	$(".add input[type='text']").prop("value",'');
	$(".add select[name='province']").val('0');
	
	$(".add select[name='platformCode']").val('');
	$(".add select[name='provincecode']").val('');
	$(".add select[name='citycode']").val('');
	
	$(".add textarea[name='description']").val('');
	$(".add .red").text('');
	
	$("#file-list").html('');
	$.each(uploader.files, function (i, file) {
        uploader.files.splice(i, 1);
    });
	$(".add").jumpBox(true);
}




</script>
</body>
</html>