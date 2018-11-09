<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>白名单列表</title>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<script type="text/javascript" src="${ctx}/js/page.js"></script>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/browserWhite/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
    <div class="mgtb10 txq_txtbox clearfix">
        
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>白名单列表(版本号：${version })</strong></div>
		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="新增" onclick="add()"/>&nbsp;&nbsp;
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="5%">序号</th>
				<th width="5%">终端型号</th>
				<th width="10%">网址信息</th>
				
<!-- 				<th width="10%">创建时间</th>
				<th width="10%">更新时间</th>
				<th width="5%">状态</th> -->
				
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
					${status.index + 1}
					</td>
					<td>
					     ${obj.flatModelIds }
					</td>
					<td>
					     ${obj.address }
					</td>
					
			<%-- <td>
						<fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						<fmt:formatDate value="${obj.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						<c:if test="${obj.status eq 1}">开放</c:if>
						<c:if test="${obj.status eq 2}">关闭</c:if>
					</td> --%>
					
					<td>
					<%-- <a href="javascript:void(0);" class="mgr10" onclick="edit('${obj.id}')">编辑</a> --%>
					<a href="javascript:void(0);" class="mgr10" onclick="del('${obj.flatModelIds}','${obj.id}')">删除</a>
					
					<%-- <c:if test="${obj.status eq 1}">
						<a href="javascript:void(0);" class="mgr10" onclick="updateStatus('${obj.id}','2')">关闭</a>
					</c:if>
				    <c:if test="${obj.status eq 2}">
				    	<a href="javascript:void(0);" class="mgr10" onclick="updateStatus('${obj.id}','1')">开放</a>
				    </c:if> --%>
					
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
				action="${ctx}/manage/browserWhite/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<!--新增-->
<div class="popup jumpBox add dis_none txq_main" style="width:700px;margin-top:200px;">
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">新增</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="15%" align="right"><span style="color: red;">*</span>网址信息：</td>
               <td width="35%" align="left"  colspan="3">
				<input type="text" name="address" class="inp focus"/>
				<span class="red"></span>
               </td>
               
           </tr>
           
           <tr>
               <td align="right">支持型号：</td>
               <td align="left" colspan="3">
	        	<div class="upgradeVersion">
               		<p></p>
               		<div>
               			
	               	</div>
               	</div>
               </td>
           </tr>
           <tr>
               <td align="right">说明：</td>
               <td align="left" colspan="3">
               	<textarea name="memo" rows="5" cols="48" placeholder="请输入描述......"></textarea>
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
<div class="popup jumpBox edit dis_none txq_main" style="width:700px;margin-top:200px;">
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">编辑</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="15%" align="right"><span style="color: red;">*</span>网址信息：</td>
               <td width="35%" align="left" colspan="3">
				<input type="text" name="address" class="inp focus"/>
               </td>
                
           </tr>
           
           <tr>
           	  <td align="left" colspan="4">
           	  		<div name="areas">
           	  		</div>
           	  
           	  </td>
           </tr>
           
           <tr>
               <td align="right"><span style="color: red;">*</span>支持型号：</td>
               <td align="left" colspan="3">
	        	<div class="upgradeVersion">
               		<p></p>
               		<div>
               			
	               	</div>
               	</div>
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

function queryPlatformCode(){
	var htmlStr = '<option value="">全部</option>';
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/platformInfo/queryAllPlatformInfo",
		async : false,
		data: '',
		dataType : 'json',
		success : function(data) {
			if(data != null && data.list != null && data.list.length > 0){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i];
					htmlStr += '<option value="'+obj.platformCode+'">'+obj.platformName+'</option>';
				}
			}
		}
	});
	return htmlStr;
}


function editArea(){
	if($(".edit select[name='platformCode']").val()!=''){
		var text = $(".edit select[name='platformCode']").find("option:selected").text(); 
		var val = $(".edit select[name='platformCode']").val();
		var rand = Math.floor(Math.random () * 900) + 100;
		var html = "<span id='"+rand+"'><a href='javascript:void(0)' id='a"+rand+"'>x</a><input type='hidden' name='areaId' value='"+val+"'>"+text+"</span>";
		addBtnEvent("a"+rand);
		$(".edit div[name='areas']").append(html);
		return ;
	}
}


function addArea(){
	if($(".add select[name='platformCode']").val()!=''){
		var text = $(".add select[name='platformCode']").find("option:selected").text(); 
		var val = $(".add select[name='platformCode']").val();
		var rand = Math.floor(Math.random () * 900) + 100;
		var html = "<span id='"+rand+"'><a href='javascript:void(0)' id='a"+rand+"'>x</a><input type='hidden' name='areaId' value='"+val+"'>"+text+"</span>";
		addBtnEvent("a"+rand);
		$(".add div[name='areas']").append(html);
		return ;
	}
}

function addBtnEvent(id){	
	$("#"+id).live('click',function(){
		$("#"+id).parent().remove();
	});
}


var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'browse',
	url : '${ctx}/manage/whiteList/upload',
	flash_swf_url : '${ctx}/js/plupload-2.1.8/Moxie.swf',
	silverlight_xap_url : '${ctx}/js/plupload-2.1.8/Moxie.xap',
	multi_selection : false,
	chunk_size: '10mb', //分块大小
	filters : {
		mime_types: [
			{title : "apk files", extensions : "apk"},
			{title : "zip files", extensions : "zip"}
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
			$(".add input[name='pkg']").val(app.packageName);
			$(".add span[name='inter_version_code']").text(app.versionName);
			$(".add span[name='version_code']").text(app.versionCode);
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

var $addCkAll = $(".add input[name='allVersion']");
$addCkAll.bind("click",function() {
	$(".add input[name='modelCode']").prop("checked",this.checked);
});
var $editCkAll = $(".edit input[name='allVersion']");
$editCkAll.bind("click",function() {
	$(".edit input[name='modelCode']").prop("checked",this.checked);
});
$(".edit input[name='modelCode']").live("click",function() {
    var b=$(".edit input[name='modelCode']").filter(":checked").length==$(".edit input[name='modelCode']").length;
    $editCkAll.prop("checked",b?true:false);
});


function updateStatus(id,status){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/whiteList/updateStatus",
		data : {
			"id"  : id,
			"status"  : status
		},
		async : false,
		dataType : 'text',
		success : function(msg) {
			if(msg=='success'){
				//$(".edit").closeBox();
				$("#pageForm").submit();
			}
		}
	});
}

function getEditFlatModel(){
	var ids = "";
	$.each( $(".edit input[name='modelCode']").filter(":checked"), function(i, ckItm){
		ids += "," + ckItm.value;
	});
	if(ids != ""){
		return ids.substring(1);
	}
	return "";
}
function getFlatModel(){
	var ids = "";
	$.each( $(".add input[name='modelCode']").filter(":checked"), function(i, ckItm){
		ids += "," + ckItm.value;
	});

	if(ids != ""){
		return ids.substring(1);
	}
	
	return "";
}

function getEditSoftId(){
	var ids = "";
	$.each( $(".edit input[name='modelCode']").filter(":checked"), function(i, ckItm){
		ids += "," + ckItm.value;
	});
	if(ids != ""){
		return ids.substring(1);
	}
	return "";
}

function search(){
	$("#search_type").val(0);
	$("#pageForm").submit();
}

function add(){
	$(".add input[type='hidden']").prop("value",'');
	$(".add input[type='text']").prop("value",'');
	$(".add textarea[name='memo']").val('');
	$(".add .red").text('');
	
	$("#file-list").html('');
	
	$(".add div[name='areas']").children().remove();
	
	$.each(uploader.files, function (i, file) {
        uploader.files.splice(i, 1);
    });
	
	$(".add span[name='inter_version_code']").text('');
	$(".add span[name='version_code']").text('');
	
	
	$addCkAll.prop("checked",false);
	
	$(".add input[name='allVersion']").prop("checked",false);
	
	$(".add").jumpBox(true);
}


var modelCodeList = [];
function edit(id){
	modelCodeList=[];
	$(".edit .red").text('');
	$editCkAll.prop("checked",false);
	var productType = $("*[name='slideTag'] a.on").attr("id");
	if(productType=='2'){
		$(".edit .version_code_2").show();
		$(".edit .version_code_3").hide();
	}else if(productType=='3'){
		$(".edit .version_code_3").show();
		$(".edit .version_code_2").hide();
	}
	
	$(".edit div[name='areas']").children().remove();
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/platformInfo/whiteListPlatformInfo",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i];
					
					var text = obj.platform_name;
					var val = obj.platform_code;
					
					var rand = Math.floor(Math.random () * 900) + 100;
					var html = "<span id='"+rand+"'><a href='javascript:void(0)' id='a"+rand+"'>x</a><input type='hidden' name='areaId' value='"+val+"'>"+text+"</span>";
					
					addBtnEvent("a"+rand);
					$(".edit div[name='areas']").append(html);
				}
			}
		}
	});
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/whiteList/detail",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(app) {
			if(app != null){
				$(".edit input[name='id']").val(app.id);
				$(".edit input[name='name']").val(app.name);
				$(".edit span[name='inter_version_code']").text(app.interVersionCode);
				$(".edit span[name='pkg']").text(app.pkg);
				$(".edit span[name='version_code']").text(app.versionCode);
				$(".edit textarea[name='memo']").val(app.memo);
				
				$(".edit").jumpBox(true);
				
				if(typeof(app.flatModelIds)!="undefined"){
					modelCodeList = app.flatModelIds.split(",");
				} 
				
				
				
				queryEditModelCode(app.id);
				
			}
		}
	});
}

//获取平板型号
function queryEditModelCode(id){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/app/getAllFlatModel",
		data : {
			"ebpAppId":id
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null){
				if(data.list != null && data.list.length > 0){
					var htmlStr = '';
					for(var i=0;i<data.list.length;i++){
						var obj = data.list[i];
						
						htmlStr += '<span>';
						for(var j=0;j<modelCodeList.length;j++){
							if(obj.id==modelCodeList[j]){								
								htmlStr += '<input type="checkbox" name="modelCode" checked="checked" value="'+obj.id+'"/>'+obj.modelCode;
								break;
							}
						}
						if(j==modelCodeList.length){
							htmlStr += '<input type="checkbox" name="modelCode" value="'+obj.id+'"/>'+obj.modelCode;
						}
						
               			htmlStr += '</span>';
					}
					$(".edit .upgradeVersion div").html(htmlStr);
					$(".edit .upgradeVersion").show();
					
					var b=$(".edit input[name='modelCode']").filter(":checked").length==$(".edit input[name='modelCode']").length;
				    $editCkAll.prop("checked",b?true:false);
				}else{
					$(".edit .upgradeVersion").hide();
					$(".edit .upgradeVersion div").html('');
				}
			}
		}
	});
}

//获取所有平板型号
function queryFlatModelCode(){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/app/getAllFlatModel",
		data : {
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null){
				if(data.list != null && data.list.length > 0){
					var htmlStr = '';
					for(var i=0;i<data.list.length;i++){
						var obj = data.list[i];
						htmlStr += '<span>';
						htmlStr += '<input type="checkbox" name="modelCode" value="'+obj.id+'"/>'+obj.modelCode;
               			htmlStr += '</span>';
					}
					$(".add .upgradeVersion div").html(htmlStr);
					$(".add .upgradeVersion").show();
				}else{
					$(".add .upgradeVersion").hide();
					$(".add .upgradeVersion div").html('');
				}
			}
		}
	});
}

$(document).ready(function(){ 
	queryFlatModelCode();
	
	
	$(".add select[name='platformCode']").html(queryPlatformCode());	

	$(".edit select[name='platformCode']").html(queryPlatformCode());	
	
	$("#browse").click(function(){
		if(uploader.files.length == 1){
			$("#browse").siblings(".red").text("只能上传一个文件");
		}
	});
	//先暂停上传，后清空上传列表
	$(".add *[name='close']").click(function(){
		//如果正在上传，就先停止上传后删除上传队列；否则，删除队列同时删除文件
		var file_path=$(".add input[name='file_path']").val();
		if(uploader.total.uploaded==0){
			uploader.stop();
		}else if(file_path != ''){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/app/deleteDisableFile",
				data : {"file_path" : file_path},
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
	
	//先暂停上传，后清空上传列表
	$(".add *[name='close']").click(function(){
		//如果正在上传，就先停止上传后删除上传队列；否则，删除队列同时删除文件
		var file_path=$(".add input[name='file_path']").val();
		if(uploader.total.uploaded==0){
			uploader.stop();
		}else if(file_path != ''){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/app/deleteDisableFile",
				data : {"file_path" : file_path},
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
		var file_path=$(".add input[name='file_path']").val();
		if(uploader.total.uploaded==0){
			uploader.stop();
		}else if(file_path != ''){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/app/deleteDisableFile",
				data : {"file_path" : file_path},
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
		$(".add span[name='inter_version_code']").text('');
		$(".add span[name='version_code']").text('');
	});
	
	$(".add input[name='save']").click(function(){
		var address=$(".add input[name='address']").val();
		var memo=$(".add textarea[name='memo']").val();
		//var pkg=$(".add input[name='pkg']").val();
		
		//var version_code = $(".add span[name='version_code']").text();
		//var inter_version_code = $(".add span[name='inter_version_code']").text();
		
		//if(version_code == null || version_code == ''){
		//	version_code = $(".add input[name='versionCode']").val();
		//	inter_version_code = version_code;
		//}
		
		if(address==''){
			$(".add input[name='address']").siblings(".red").text("请填写网址信息");
			return ;
		}
		
		
	
		//if(pkg==''){
		//	$(".add input[name='pkg']").siblings(".red").text("请填写软件包");
		//	return;
		//}
	
		//if(memo==''){
		//	$(".add textarea[name='memo']").siblings(".red").text("请填写应用描述");
			//return;
		//}
		//if(version_code == null || version_code == ''){
		//	$(".add input[name='versionCode']").siblings(".red").text("请填写版本号");
		//	return;
		//}
		//if($.trim(file_path)==''){
		//	$("#browse").siblings(".red").text("请选择上传文件并等待上传完成");
		//	return;
		//}
	
		var flatMode = getFlatModel();
	
		
		var platformCode="";
		$(".add div[name='areas']").find("input[name='areaId']").each(function(i,domEle){
			var a = $(domEle).val();
			//alert(this.val());
			
			platformCode = platformCode+","+a;
		})


	
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/browserWhite/save",
				data : {
					"address"  : address,
					"memo"  : memo,
					"flatModelIds":flatMode
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
		var id = $(".edit input[name='id']").val();
		var address=$(".edit input[name='address']").val();
		var memo=$(".edit textarea[name='memo']").val();
		
		
		//if($.trim(memo)==''){
		//	$(".edit textarea[name='memo']").siblings(".red").text("请填写应用描述");
		//	return;
		//}
		
		
		var flatMode = getEditFlatModel();
		
		var platformCode="";
		$(".edit div[name='areas']").find("input[name='areaId']").each(function(i,domEle){
			var a = $(domEle).val();
			//alert(this.val());
			
			platformCode = platformCode+","+a;
		})
		
		//if(flatMode==''){
		//	$("#addMsg").text('请选择支持的型号');
		//	return;
		//}
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/whiteList/update",
				data : {
					"id"  : id,
					"name"  : name,
					"memo"  : memo,
					"flatModelIds":flatMode,
					"platform_codes":platformCode
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
		}, 1);
		
	});
});

function del(flatModelIds,id){
	console.log("id:"+id);
	$.confirm("确定要删除该白名单吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/browserWhite/delete",
			data : {
				"id"  : id,
				"flatModelIds":flatModelIds 
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
	
};
</script>
</body>
</html>