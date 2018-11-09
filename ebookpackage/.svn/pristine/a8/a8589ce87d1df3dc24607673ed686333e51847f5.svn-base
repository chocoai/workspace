<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ page import="com.whty.common.util.SysConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品管理页面</title>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<style type="text/css">
.progress{ height: 4px; font-size: 0; line-height: 4px; background: orange; width: 0;}
.num{ margin-left: 20px;}
#file-list img{cursor: pointer;}
</style>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/sys/product/listProduct" method="post">
	
    <div class="mgtb10 txq_txtbox clearfix">
        <p class="fl mgr10"><span>产品名称：</span><input name="productName" value="${productName}" type="text" style="width:150px;" class="inp focus"/></p>
        <input type="hidden" name="productType" value="${productType}">
        <input type="hidden" name="currentPage1">
        <p class="fl mgr10"><span>产品类型：</span>
        	<select  id="productTypeSelect" onchange="change()" class="sel" style="width:150px;">
        		<option value="">全部</option>
	        	<option value="1" <c:if test="${productType eq '1'}">selected="selected"</c:if>>IOS</option>
	        	<option value="2" <c:if test="${productType eq '2'}">selected="selected"</c:if>>ANDROID</option>
	        	<option value="3" <c:if test="${productType eq '3'}">selected="selected"</c:if>>WIN10</option>
	        	<option value="4" <c:if test="${productType eq '4'}">selected="selected"</c:if>>人教版android</option>
        	</select>
        </p>
        <p class="fl"><input type="button" id="seachBtn" class="btn_blue" value="查询"/></p>
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>产品列表</strong></div>

		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="新增产品" onclick="add()"/>&nbsp;&nbsp;
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="5%">序号</th>
				<th width="15%">产品名称</th>
				<th width="10%">产品类型</th>
				<th width="10%">产品编码</th>
				<th width="40%">产品描述</th>
				<th width="20%">操作</th>
		    </tr>
		    <c:if test="${fn:length(pageList.resultList) <1}">
		    	<tr><td colspan="6" align="center"><font color="red">暂无数据</font></td></tr>
		    </c:if>
		    <c:forEach items="${pageList.resultList}" var="pro" varStatus="status">
		    	<tr>
		    		<td align="center">${status.index + 1}</td>
		    		<td align="center">${pro.productName}</td>
		    		<td align="center">
		    			<c:if test="${pro.productType eq 1}">IOS</c:if>
		    			<c:if test="${pro.productType eq 2}">ANDROID</c:if>
		    			<c:if test="${pro.productType eq 3}">WIN10</c:if>
		    			<c:if test="${pro.productType eq 4}">人教Android</c:if>
		    			<c:if test="${pro.productType eq 5}">第三方android一个用</c:if>
		    		</td>
		    		<td align="center">${pro.productCode}</td>
		    		<td align="center">
		    			<c:if test="${fn:length(pro.description)<=30}">${pro.description }</c:if>
						<c:if test="${fn:length(pro.description)>30}">${fn:substring(pro.description,0,30)}...</c:if>
		    			</td>
		    		<td align="center">
		    			<a href="javascript:void(0);" proId="${pro.id }" id="edit">编辑</a>
		    			<a href="javascript:void(0);" proId="${pro.id }" id="detail">查看</a>
		    			<a href="javascript:void(0);" onclick="downloadQrCode('${pro.id }')">扫二维码下载</a>
		    			<a href="javascript:void(0);" proId="${pro.id }" id="delete">删除</a>
		    		</td>
		    		
		    	</tr>
		    </c:forEach>
		</table>
    </div>
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${not empty pageList.page.totalPage && pageList.page.totalPage > 1 }">
				<newTag:page totalPage="${pageList.page.totalPage}" formName="pageForm" 
				currentPage="${pageList.page.currentPage}" pageSize="${pageList.page.pageSize}"  totalRows="${pageList.page.totalRows}"
				action="${ctx}/sys/product/listProduct"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<!--详情-->
<div class="popup jumpBox detail dis_none">
    <div class="tit"><span class="fl">查看产品</span><span class="close" name="close">X</span></div>
    <div class="cont">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
               <td width="25%" align="right">产品名称：</td>
               <td width="50%" align="left" id="productName">
               </td>
               <td width="25%" align="left" rowspan="6">
               		<p><a id="app_download_url" href="#" target="_blank">下载最新应用安装包</a></p>
               		<%-- <img id="qrCodePath" style="max-height:110px;max-width:110px;" src="${qrCodeUrl}"> --%>
               		<div class="mgt20" align="center" style="max-width: 96px;max-height: 96px;avertical-align: middle;" id="product_image">
               		</div>
               </td>
           </tr>
            <tr>
               <td align="right">产品编码：</td>
               <td align="left" id="productCode">
               </td>
           </tr>
           <tr>
               <td align="right">产品类型：</td>
               <td align="left" id="productType">
               </td>
           </tr>
           <tr>
               <td align="right">有效状态：</td>
               <td align="left" id="status">
               </td>
           </tr>
           <tr>
               <td align="right">创建时间：</td>
               <td align="left" id="createTime">
               </td>
           </tr>
           <tr>
               <td align="right">更新时间：</td>
               <td align="left" id="updateTime">
               </td>
           </tr>
           <!-- <tr>
               <td align="right">下载地址：</td>
               <td align="left">
               		<img id="qrCodePath" style="max-height:80px;max-width:80px;" src="">
               </td>
           </tr> -->
           <tr>
               <td align="right">产品描述：</td>
               <td align="left" height="100px;" colspan="2">
               	<textarea id="description" readonly="readonly" class="inp focus" rows="5" cols="54" style="height: 85px;" placeholder="请输入产品描述......"></textarea>
               </td>
           </tr>
       </table>
    </div>
</div>

<!--二维码下载-->
<div class="popup jumpBox download dis_none" style="width: 220px;">
    <div class="tit"><span class="fl">扫二维码下载</span><span class="close" name="close">X</span></div>
    <div class="cont">
		
    </div>
</div>


<!--新增-->
<div class="popup jumpBox add dis_none">
	<form id="newForm">
		<input type="hidden" name="productName" value="">
		<input type="hidden" name="productCode" value="">
		<input type="hidden" name="productType" value="">
		<input type="hidden" name="description" value="">
		<input type="hidden" name="icoPath">
		<input type="hidden" name="icoOldName">
		<input type="hidden" name="icoNewName">
	</form>
    <div class="tit"><span class="fl">新增产品</span><span class="close" name="close">X</span></div>
    <div class="cont">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>产品名称：</td>
               <td width="50%" align="left">
				<input type="text" id="productName" class="inp focus"/>
				<span class="red"></span>
               </td>
               <td width="25%" rowspan="3" style="padding: 0px;">
               	<div align="center" style="max-width: 96px;max-height: 96px;avertical-align: middle;" id="show_image">
               	</div>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>产品编码：</td>
               <td align="left">
               	<input type="text" id="productCode" class="inp focus"/>
				<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right">产品类型：</td>
               <td align="left">
               	<select  id="productTypeSelect" class="sel" style="width:150px;">
	        		<option></option>
		        	<option value="1">IOS</option>
		        	<option value="2">ANDROID</option>
		        	<option value="3">WIN10</option>
		        	<option value="4">人教版ANDROID</option>
		        	<option value="5">第三方android应用</option>
        		</select>
               </td>
           </tr>
           <tr style="height: 68px;">
               <td align="right">产品图标：</td>
               <td align="left" style="padding: 0px;vertical-align: top;" colspan="2">
               		<input type="button" style="cursor: pointer;" value="选择文件..." id="browse" />
					<input type="button" style="cursor: pointer;" value="开始上传" id="upload-btn" />
					<ul id="file-list" style="margin-top: 10px;"></ul>
               		<span class="red" id="imgmsg"></span>
               </td>
           </tr>
           <tr>
               <td align="right">产品描述：</td>
               <td align="left" height="100px;" colspan="2">
               	<textarea id="description" class="inp focus" rows="5" cols="48" style="height: 85px;" placeholder="请输入产品描述......"></textarea>
               </td>
           </tr>
           <tr>
           	<td colspan="3" style="height: 53px;">
           		<input type="button" name="save" class="btn_blue" value="保存" />
           		<%-- <span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span> --%>
           		<input type="button" name="close" class="btn_gray" value="取消" />
           	</td>
           </tr>
       </table>
    </div>
</div>

<!--编辑-->
<div class="popup jumpBox edit dis_none">
	<form id="editForm">
		<input type="hidden" name="id" value="">
		<input type="hidden" name="productName" value="">
		<input type="hidden" name="productCode" value="">
		<input type="hidden" name="description" value="">
		<input type="hidden" name="icoPath">
		<input type="hidden" name="icoOldName">
		<input type="hidden" name="icoNewName">
	</form>
    <div class="tit"><span class="fl">编辑产品</span><span class="close" name="close">X</span></div>
    <div class="cont">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>产品名称：</td>
               <td width="50%" align="left">
				<input type="text" id="productName" class="inp focus"/>
				<span class="red"></span>
               </td>
               <td width="25%" rowspan="3" style="padding: 0px;">
               	<div align="center" style="max-width: 96px;max-height: 96px;avertical-align: middle;" id="show_image1">
               	</div>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>产品编码：</td>
               <td align="left">
               	<input type="text" id="productCode" class="inp focus"/>
				<span class="red"></span>
               </td>
           </tr>
           <tr style="height: 68px;">
               <td align="right">产品图标：</td>
               <td align="left" style="padding: 0px;vertical-align: top;">
               		<input type="button" style="cursor: pointer;" value="选择文件..." id="browse1" />
					<input type="button" style="cursor: pointer;" value="开始上传" id="upload-btn1" />
					<ul id="file-list1" style="margin-top: 10px;"></ul>
               		<span class="red" id="imgmsg1"></span>
               </td>
           </tr>
           <tr>
               <td align="right">产品描述：</td>
               <td align="left" height="100px;" colspan="2">
               	<textarea id="description" class="inp focus" rows="5" cols="48" style="height: 85px;" placeholder="请输入产品描述......"></textarea>
               </td>
           </tr>
           <tr>
           	<td colspan="3" style="height: 53px;">
           		<input type="button" name="save" class="btn_blue" value="保存" />
           		<%-- <span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span> --%>
           		<input type="button" name="close" class="btn_gray" value="取消" />
           	</td>
           </tr>
       </table>
    </div>
</div>

<script type="text/javascript">
var change = function(){
	var selValue = $("#productTypeSelect").val();
	$("input[name='productType']").val(selValue);
}

//新增产品弹出框
function add(){
	$(".add").jumpBox(true);
}

function downloadQrCode(id){
	$.ajax({
		url : "${ctx}/sys/product/loadProductById",
		data : {"id":id},
		dataType : "json",
		type : "post",
		success : function(pro){
			if(pro.qrCodeUrl != '' && pro.qrCodeUrl != null){
				$(".download .cont").html('<image src="'+pro.qrCodeUrl+'" style="max-width:200px;max-height:200px;"/>');
			}else{
				$(".download .cont").html('');
			}
			$(".download").jumpBox(true);
		}
	});
}

//图片上传上传
var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'browse',
	url : '${ctx}/sys/product/uploadInfo',
	flash_swf_url : '${ctx}/js/plupload-2.1.8/Moxie.swf',
	silverlight_xap_url : '${ctx}/js/plupload-2.1.8/Moxie.xap',
	multi_selection : false,
	chunk_size: '10mb', //分块大小
	filters : {
		mime_types: [
			{title : "gif files", extensions : "gif"},
			{title : "jpg files", extensions : "jpg"},
			{title : "png files", extensions : "png"}
		]
	},
	
	init: {
		FilesAdded :  function(uploader, files) {
			if(uploader.files.length == 2){
				uploader.files.splice(uploader.files.length-1, 1);
				return;
			}
			plupload.each(files, function(file) {
				$("#imgmsg").text("");
				$("#file-list").html('');
				var html = '<li id="' + file.id +'"><img style="cursor: pointer;" src="${ctx}/js/uploadify/images/cancel.png" border="0"><span class="file-name">' + file.name + '</span><span class="num"></span></li>';
				$(html).appendTo('#file-list');
			});
		},
		UploadProgress: function(uploader, file) {
			$('#'+file.id+' .num').html(file.percent + '%');
		},
		FileUploaded:function(uploader,file,responseObject){
			var product = eval('('+responseObject.response+')');
			$(".add input[name='icoPath']").val(product.icoPath);
			$(".add input[name='icoOldName']").val(product.icoOldName);
			$(".add input[name='icoNewName']").val(product.icoNewName);
			//显示图片
			$("#show_image").html('<image src="<%=SysConfig.getStrValue("file_path_http")%>'+product.icoPath+'" style="max-width:96px;max-height:96px;"/>');
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

//图片上传按钮
$('#upload-btn').click(function(){
	uploader.start(); //开始上传
});

//图片编辑
var uploader1 = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'browse1',
	url : '${ctx}/sys/product/uploadInfo',
	flash_swf_url : '${ctx}/js/plupload-2.1.8/Moxie.swf',
	silverlight_xap_url : '${ctx}/js/plupload-2.1.8/Moxie.xap',
	multi_selection : false,
	chunk_size: '10mb', //分块大小
	filters : {
		mime_types: [
			{title : "gif files", extensions : "gif"},
			{title : "jpg files", extensions : "jpg"},
			{title : "png files", extensions : "png"}
		]
	},
	
	init: {
		FilesAdded :  function(uploader, files) {
			if(uploader.files.length == 2){
				uploader.files.splice(uploader.files.length-1, 1);
				return;
			}
			plupload.each(files, function(file) {
				$("#imgmsg1").text("");
				$("#file-list1").html('');
				var html = '<li id="' + file.id +'"><img style="cursor: pointer;" src="${ctx}/js/uploadify/images/cancel.png" border="0"><span class="file-name">' + file.name + '</span><span class="num"></span></li>';
				$(html).appendTo('#file-list1');
			});
		},
		UploadProgress: function(uploader, file) {
			$('#'+file.id+' .num').html(file.percent + '%');
		},
		FileUploaded:function(uploader,file,responseObject){
			var product = eval('('+responseObject.response+')');
			$(".edit input[name='icoPath']").val(product.icoPath);
			$(".edit input[name='icoOldName']").val(product.icoOldName);
			$(".edit input[name='icoNewName']").val(product.icoNewName);
			//显示图片
			$("#show_image1").html('<image src="<%=SysConfig.getStrValue("file_path_http")%>'+product.icoPath+'" style="max-width:96px;max-height:96px;"/>');
		},
		UploadComplete : function(uploader,files){
			
		},
		FilesRemoved : function(uploader,files){
			
		},
		Error: function(uploader, err) {
			
		}
	}
});
uploader1.init();//初始化

//图片上传按钮
$('#upload-btn1').click(function(){
	uploader1.start(); //开始上传
});

$(function(){
	//文件上传
	$("#browse").click(function(){
		if(uploader.files.length == 1){
			$("#browse").siblings(".red").text("只能上传一个文件");
		}
	});
	
	//文件上传
	$("#browse1").click(function(){
		if(uploader1.files.length == 1){
			$("#browse1").siblings(".red").text("只能上传一个文件");
		}
	});
	
	//先暂停上传，后清空上传列表
	$(".add *[name='close']").click(function(){
		//如果正在上传，就先停止上传后删除上传队列；否则，删除队列同时删除文件
		var icoPath=$(".add input[name='icoPath']").val();
		if(uploader.total.uploaded==0){
			uploader.stop();
		}
			
		if(icoPath != '' ){
			$.ajax({
				type : "POST",
				url : "${ctx}/sys/product/deleteDisableFile",
				data : {"icoPath" : icoPath},
				async : false,
				dataType : 'text',
				success : function(msg) {
					$("#show_image").html('');
				}
			});
		}
		
		$("#file-list").html('');
		$.each(uploader.files, function (i, file) {
	        uploader.files.splice(i, 1);
	    });
	});
	
	//删除图片
	$('#file-list img').live("click",function(){
		//如果正在上传，就先停止上传后删除上传队列；否则，删除队列同时删除文件
		var icoPath=$(".add input[name='icoPath']").val();
		if(uploader.total.uploaded==0){
			uploader.stop();
		}
		if(icoPath != ''){
			$.ajax({
				type : "POST",
				url : "${ctx}/sys/product/deleteDisableFile",
				data : {"icoPath" : icoPath},
				async : false,
				dataType : 'text',
				success : function(msg) {
					$("#show_image").html('');
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
		$(".add input[name='icoPath']").val('');
		$(".add input[name='icoOldName']").val('');
		$(".add input[name='icoNewName']").val('');
	});
	
	//删除图片
	$('#file-list1 img').live("click",function(){
		//如果正在上传，就先停止上传后删除上传队列；否则，删除队列同时删除文件
		var icoPath=$(".edit input[name='icoPath']").val();
		if(uploader1.total.uploaded==0){
			uploader1.stop();
		}
		if(icoPath != ''){
			$.ajax({
				type : "POST",
				url : "${ctx}/sys/product/deleteDisableFile",
				data : {"icoPath" : icoPath},
				async : false,
				dataType : 'text',
				success : function(msg) {
					$("#show_image1").html('');
				}
			});
		}
		
		
		var fileid = $(this).parent().attr("id");
		$.each(uploader1.files, function (i, file) {
	        if(file.id===fileid){
	        	uploader1.files.splice(i, 1);
	        }
	    });
		
		$(this).parent().remove();
		$(".edit input[name='icoPath']").val('');
		$(".edit input[name='icoOldName']").val('');
		$(".edit input[name='icoNewName']").val('');
	});
	
	//查看产品
	$("#detail").live("click",function(){
		$(".detail").jumpBox(true);
		var id = $(this).attr("proId");
		$.ajax({
			url : "${ctx}/sys/product/loadProductById",
			data : {"id":id},
			dataType : "json",
			type : "post",
			success : function(pro){
				$(".detail #productName").text(pro.productName);
				$(".detail #productCode").text(pro.productCode);
				if(pro.productType == 1){
					$(".detail #productType").text("IOS");
				}
				if(pro.productType == 2){
					$(".detail #productType").text("ANDROID");
				}
				if(pro.productType == 3){
					$(".detail #productType").text("WIN10");
				}
				if(pro.productType == 4){
					$(".detail #productType").text("人教Android");
				}
				if(pro.status == 0){
					$(".detail #status").text("有效");
				}
				if(pro.status == 1){
					$(".detail #status").text("无效");
				}
				$(".detail #description").val(pro.description);
				//$(".detail #qrCodePath").attr("src",pro.qrCodeUrl);
				if(pro.icoPath != '' && pro.icoPath != null){
					$("#product_image").html('<image src="<%=SysConfig.getStrValue("file_path_http")%>'+pro.icoPath+'" style="max-width:96px;max-height:96px;"/>');
				}else{
					$("#product_image").html('');
				}
				$(".detail #app_download_url").attr("href",pro.app_download_url);
				var updaTime = new Date(pro.updateTime);
				$(".detail #updateTime").text(updaTime.toLocaleString());
				var creaTime = new Date(pro.createTime);
				$(".detail #createTime").text(creaTime.toLocaleString());
			},
			error : function(){}
		});
	});
	function querySubmit(){
		$("#pageForm").submit();
	}
	//逻辑删除产品
	$("#delete").live("click",function(){
		var id = $(this).attr("proId");
		$.confirm("确定要删除？",function(){
			$.ajax({
				url : "${ctx}/sys/product/deleteProduct",
				data : {"id":id},
				dataType : "json",
				type : "post",
				success : function(data){
					if(data.status == "success"){
						$.alert("删除成功！",function(){
							querySubmit();
						});
					}else{
						$.alert("删除失败！");
					}
				},
				error : function(){}
			});
		});
	});
	//编辑产品弹出框
	$("#edit").live('click',function(){
		var prodId = $(this).attr("proId");
		$.ajax({
			url : "${ctx}/sys/product/loadProductById",
			data : {"id":prodId},
			dataType : "json",
			type : "post",
			success : function(data){
				$(".edit #editForm input[name='id']").val(data.id);
				$(".edit #productName").val(data.productName);
				$(".edit #productCode").val(data.productCode);
				$(".edit #description").val(data.description);
				
				$(".edit input[name='icoPath']").val(data.icoPath);
				$(".edit input[name='icoOldName']").val(data.icoOldName);
				$(".edit input[name='icoNewName']").val(data.icoNewName);
				
				//图片
				if(null != data.icoPath && '' != data.icoPath){
					var html = '<li><img src="${ctx}/js/uploadify/images/cancel.png" style="cursor: pointer;" border="0" id="f2"><span class="file-name">' + data.icoOldName +'</span><span class="num"></span></li>';
					$('#file-list1').html(html);
					$("#show_image1").html('<image src="<%=SysConfig.getStrValue("file_path_http")%>'+data.icoPath+'" style="max-width:96px;max-height:96px;"/>');
				}
				
				$(".edit").jumpBox(true);
				
			},
			error : function(){}
		});
	});
	
	//编辑产品
	$(".edit input[name='save']").click(function(){
		
		var productName = $(".edit #productName").val().trim();
		var productCode = $(".edit #productCode").val().trim();
		var description = $(".edit #description").val().trim();
		var id = $(".edit #editForm").find("input[name='id']").val();
		if(productName == null || productName ==""){
			$(".edit #productName").siblings(".red").text("请填写产品名称");
			return;
		};
		if(productCode == null || productCode ==""){
			$(".edit #productCode").siblings(".red").text("请填写产品编码");
			return;
		};
		$(".edit #productName").siblings(".red").text("");
		$(".edit #productCode").siblings(".red").text("");
		
		$.ajax({
			url : "${ctx}/sys/product/editProduct",
			data : {"id":id,
				"productName":productName,
				"productCode":productCode,
				"description":description,
				"icoPath":$(".edit input[name='icoPath']").val(),
				"icoOldName":$(".edit input[name='icoOldName']").val(),
				"icoNewName":$(".edit input[name='icoNewName']").val()},
			dataType : "json",
			type : "post",
			success : function(data){
				$(".edit").closeBox();
				$.alert(data.info,function(){
					querySubmit();
				});
			},
			error : function(){}
		});
		
	});
	//新增产品
	$(".add input[name='save']").click(function(){
		
		var productName = $(".add #productName").val().trim();
		var productCode = $(".add #productCode").val().trim();
		var productType = $(".add #productTypeSelect").val();
		var description = $(".add #description").val().trim();
		
		if(productName == null || productName ==""){
			$(".add #productName").siblings(".red").text("请填写产品名称");
			return;
		};
		if(productCode == null || productCode ==""){
			$(".add #productCode").siblings(".red").text("请填写产品编码");
			return;
		};
		$(".add #productName").siblings(".red").text("");
		$(".add #productCode").siblings(".red").text("");
		
		$(".add #newForm").find("input[name='productName']").val(productName);
		$(".add #newForm").find("input[name='productCode']").val(productCode);
		$(".add #newForm").find("input[name='productType']").val(parseInt(productType));
		$(".add #newForm").find("input[name='description']").val(description);
		
		var d = $(".add #newForm").serialize();
		
		$.ajax({
			url : "${ctx}/sys/product/addProduct",
			data : d,
			dataType : "json",
			type : "post",
			success : function(data){
				$(".add").closeBox();
				$.alert(data.info,function(){
					querySubmit();
				});
			},
			error : function(){}
		});
		
	});
	
	
	$("#seachBtn").click(function(){
		$("input[name='currentPage1']").val("1");
		$("#pageForm").submit();
		$("input[name='currentPage1']").val(null);
	});
});

</script>

</body>
</html>