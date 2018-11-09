<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>软件的组件管理页面</title>
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<style type="text/css">
.progress{ height: 4px; font-size: 0; line-height: 4px; background: orange; width: 0;}
.num{ margin-left: 20px;}
#file-list img{cursor: pointer;}
</style>
</head>
<body>
<div class="mg15 txq_main">
    <div class="mgtb10 txq_txtbox clearfix">
    	<input type="hidden" id="softId" value="${softId }">
        <p class="fl mgr10"><span>文件名称：</span><input id="fileName" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl">
        <!-- <permission:button value="查询" name="button" method="search()" style="btn_blue"></permission:button> -->
        <input type="button" onclick="search()" class="btn_blue" value="查询"/>
        </p>
    </div>
    <div class="mgtb10">
		<div class="base_title">
			<strong class="mgr10"><a href="${ctx}/manage/version/list" name="wddx" style="color: #497cc0;">返回上一级</a></strong>
			<strong class="mgr10">组件列表</strong><span class="red">（文件包含：installFile.7z，dotNetFx40.7z，vcredist.7z）</span>
		</div>
		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="新增" onclick="add()"/>
			<input type="button" class="btn_blue" value="删除" onclick="del()"/>
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<thead onselectstart="return false">
			<tr>
				<th width="5%"><input type="checkbox" name="ckAll"></th>
				<th width="15%">文件名称</th>
				<th width="10%">文件大小</th>
				<th width="15%">更新时间</th>
				<th width="45%">下载地址</th>
				<th width="10%">操作</th>
		    </tr>
		   </thead>
		    <tbody id="dataList"></tbody>
		</table>
    </div>
    <div class="base_page clearfix" id="base_page" onselectstart="return false"></div>
</div>

<!--新增-->
<div class="popup jumpBox add dis_none txq_main">
    <div class="tit"><span class="fl">新增</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>上传文件：</td>
               <td width="75%" align="left">
               	<input type="button" style="cursor: pointer;" value="选择文件..." id="browse" />
				<input type="button" style="cursor: pointer;" value="开始上传" id="upload-btn" />
				<ul id="file-list"></ul>
               	<span class="red"></span>
               </td>
           </tr>
           <tr>
           	<td colspan="2" style="height: 53px;">
           		<input type="button" name="save" class="btn_blue" value="保存" />
           		<input type="button" name="close" class="btn_gray" value="取消" />
           	</td>
           </tr>
       </table>
    </div>
</div>

<!--编辑-->
<div class="popup jumpBox edit dis_none txq_main" style="width:600px;">
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">编辑</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="20%" align="right">下载地址：</td>
               <td width="80%" align="left">
               		<input type="text" name="downloadUrl" class="inp focus" style="width:450px;"/>
               </td>
           </tr>
           <tr>
           	<td colspan="2" style="height: 53px;">
           		<input type="button" name="save" class="btn_blue" value="保存" />
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
$ckAll.bind("click",function() {
	$("input[name='ckItm']").prop("checked",this.checked);
});
$("input[name='ckItm']").live("click",function() {
    var b=$("input[name='ckItm']").filter(":checked").length==$("input[name='ckItm']").length;
    $ckAll.prop("checked",b?true:false);
});

function search(){
	resetPageParam();
	dataList();
}

function getCheckValue(){
	var ids = "";
	$.each( $("input[name='ckItm']"), function(i, ckItm){
		if(ckItm.checked){
			ids += "," + ckItm.value;
		}
	});
	if(ids != ""){
		return ids.substring(1);
	}
	return "";
}

var fileArr = new Array();//上传的文件列表
var uploadComplete = false;

var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'browse',
	url : '${ctx}/manage/softComponent/uploadInfo',
	flash_swf_url : '${ctx}/js/plupload-2.1.8/Moxie.swf',
	silverlight_xap_url : '${ctx}/js/plupload-2.1.8/Moxie.xap',
	multi_selection : true,
	chunk_size: '10mb', //分块大小
	filters : {
		mime_types: [
			{title : "Zip files", extensions : "zip,rar,7z"}
		],
		prevent_duplicates : true
	},
	
	init: {
		FilesAdded :  function(uploader, files) {
			plupload.each(files, function(file) {
				var html = '<li id="' + file.id +'"><img style="cursor: pointer;" src="${ctx}/js/uploadify/images/cancel.png" border="0"><span class="file-name">' + file.name + '(' + plupload.formatSize(file.size) + ')</span><span class="num"></span></li>';
				$(html).appendTo('#file-list');
			});
			
			$("#browse").siblings(".red").text("");
		},
		UploadProgress: function(uploader, file) {
			//$(file.id+' .progress').css('width',file.percent + '%');//控制进度条
			$('#'+file.id+' .num').html(file.percent + '%');
		},
		FileUploaded:function(uploader,file,responseObject){
			var softComponent = eval('('+responseObject.response+')');
			softComponent.softId = $("#softId").val();
			fileArr.push(softComponent);
		},
		UploadComplete : function(uploader,files){
			uploadComplete = true;
		},
		FilesRemoved : function(uploader,files){
			$.each(files,function(i,file){
				$("#"+file.id).remove();
			});
			$("#browse").siblings(".red").text("");
			
			if(uploader.files.length==0){
				uploadComplete = false;
			}
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
	uploadComplete = false;
	uploader.splice(0, uploader.files.length);
	fileArr = new Array();
	
	$(".add").jumpBox(true);
}

$(document).ready(function(){
	
	dataList();
	
	//先暂停上传，后清空上传列表
	$(".add *[name='close']").click(function(){
		//如果正在上传，就先停止上传后删除上传队列；否则，删除队列同时删除文件
		if(uploader.total.uploaded==0){
			uploader.stop();
		}else if(uploadComplete == true){
			
			var fileUrls = '';
			$.each(fileArr,function(i,obj){
				fileUrls += ',' + obj.fileUrl;
			});
			
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/softComponent/deleteDisableFile",
				data : {"fileUrl" : fileUrls.substring(1)},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='000000'){
						fileArr = new Array();
					}
				}
			});
		}
		
		uploader.splice(0, uploader.files.length);
	});
	
	//删除文件
	$('#file-list img').live("click",function(){
		var index = $(this).parent().index()
		//如果正在上传，就先停止上传后删除上传队列；否则，删除队列同时删除文件
		if(uploader.total.uploaded==0){
			uploader.stop();
		}else if(uploadComplete == true){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/softComponent/deleteDisableFile",
				data : {"fileUrl" : fileArr[index].fileUrl},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='000000'){
						fileArr.remove(index);
					}
				}
			});
		}
		
		var fileid = $(this).parent().attr("id");
		
		uploader.removeFile(uploader.getFile(fileid));
		
	});
	
	$(".add input[name='save']").click(function(){
		if(uploadComplete == false){
			$("#browse").siblings(".red").text("请选择上传文件并等待上传完成");
			return;
		}else{
			$("#browse").siblings(".red").text("");
		}
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/softComponent/createSoftComponent",
				data : {
					"softComponents"  : JSON.stringify(fileArr)
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='000000'){
						$(".add").closeBox();
						dataList();
					}
				}
			});
		}, 1);
		
	});
	
	$(".edit input[name='save']").click(function(){
		var downloadUrl=$(".edit input[name='downloadUrl']").val();
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/softComponent/editSoftComponent",
				data : {
					"id"  : $(".edit input[name='id']").val(),
					"downloadUrl"  : downloadUrl
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='000000'){
						$(".edit").closeBox();
						dataList();
					}
				}
			});
		}, 1);
		
	});
	
});

function loadList(){
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/softComponent/queryPage",
		data : {
			"softId" : $('#softId').val(),
			"fileName" : $('#fileName').val(),
			"currPage" : $('#currPage').val(),
			"pageSize" : $('#pageSize').val()
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			var htmlStr = '';
			if(data != null){
				if(data.list != null && data.list.length > 0){
					for(var i=0;i<data.list.length;i++){
						var obj = data.list[i];
						htmlStr += '<tr>';
						htmlStr += '<td><input type="checkbox" name="ckItm" value="'+obj.id+'"></td>';
						htmlStr += '<td>'+obj.fileName+'</td>';
						htmlStr += '<td>'+plupload.formatSize(obj.fileSize)+'</td>';
						htmlStr += '<td>'+new Date(obj.updateTime.time).format("yyyy-MM-dd hh:mm:ss")+'</td>';
						htmlStr += '<td>'+obj.downloadUrl+'</td>';
						htmlStr += '<td>';
						htmlStr += '<a href="javascript:void(0);" onclick="edit(\''+obj.id+'\')">编辑</a>';
						htmlStr += '</td>';
						htmlStr += '</tr>';
					}
				}
				if(data.page != null){
					setPage($("#base_page"),data.page);
				}
			}
			
			if(htmlStr != ''){
				$("#dataList").html(htmlStr);
			}else{
				$("#dataList").html('<tr><td colspan="6" align="center"><font color="red">暂无数据</font></td></tr>');
				$("#base_page").html('');
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
	$.confirm("确定要删除选中的组件信息吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/softComponent/deleteSoftComponent",
			data : {"ids" : ids},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='000000'){
					dataList();
				}
			}
		});
	});
	
	$ckAll.prop("checked",false);
}

function edit(id){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/softComponent/querySoftComponentDetail",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(softComponent) {
			if(softComponent != null){
				$(".edit input[name='id']").val(softComponent.id);
				$(".edit input[name='downloadUrl']").val(softComponent.downloadUrl);
				
				$(".edit").jumpBox(true);
			}
		}
	});
}

</script>
</body>
</html>