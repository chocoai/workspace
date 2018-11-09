<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>应用管理页面</title>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<style type="text/css">
.progress{ height: 4px; font-size: 0; line-height: 4px; background: orange; width: 0;}
.num{ margin-left: 20px;}
#file-list img{cursor: pointer;}

.data_list thead,.txq_txtbox,#base_page
{
-moz-user-select: none;
}

</style>
</head>
<body style="height:auto !important;">
<input type="hidden" name="currPage" id="currPage" value="1">
<input type="hidden" name="totalPage" id="totalPage">
<input type="hidden" name="pageSize" id="pageSize" value="10">
<div class="mg15 txq_main">
	<!-- 查询条件 -->
    <div class="mgtb10 txq_txtbox clearfix" onselectstart="return false">
        <p class="fl mgr10"><span>应用名称：</span><input id="app_name" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10"><span>内部版本号：</span><input id="inter_version_code" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10"><span>外部版本号：</span><input id="version_code" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl"><input type="button" onclick="search()" class="btn_blue" value="查询"/></p>
    </div>
    <input type="hidden" name="productType" value="">
        
    <div class="mgtb10"  name="slide">
		<!-- <div class="base_title"><strong>应用列表</strong></div> -->
		
		<div class="left_titul" name="slideTag" onselectstart="return false">
            <p>
            	<c:forEach items="${derivativeAppLineList}" var="pro" varStatus="status">
            	 	<a href="#"  <c:if test="${status.index ==0}"> class="on"</c:if>  id="${pro.id}">${pro.lineName}</a>
            	</c:forEach>
            </p>
        </div>
        
        <c:forEach items="${derivativeAppLineList}" var="pro" varStatus="status">
        <div class="tab_new <c:if test="${status.index != 0}">dis_none</c:if>" name="slideCont">
        	<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="新增应用" onclick="add()"/>&nbsp;&nbsp;
			</div>
			<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
				<thead onselectstart="return false">
				<tr>
					<th width="4%">序号</th>
					<th width="10%">应用名称</th>
					<th width="10%">内部版本号</th>
					<th width="10%">外部版本号</th>
					<th width="15%">创建时间</th>
					<th width="15%">更新时间</th>
					<th width="8%">包名</th>
					<th width="8%">升级状态</th>
					<th width="20%">操作</th>
			    </tr>
			    </thead>
			    <tbody id="dataList_${pro.id}"></tbody>
			</table>
			<div class="base_page clearfix" id="base_page_${pro.id}" onselectstart="return false"></div>
        </div>
            	
        </c:forEach>
    </div>
</div>

<!--新增-->
<div class="popup jumpBox add dis_none" style="margin-top:200px;">
	<input type="hidden" name="file_path">
	<input type="hidden" name="old_file_name">
	<input type="hidden" name="new_file_name">
	<input type="hidden" name="file_size">
    <input type="hidden" name="apk_package_name">
    <div class="tit"><span class="fl">新增</span><span class="close" name="close">X</span></div>
    <div class="cont">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>应用名称：</td>
               <td width="75%" align="left" colspan="3">
				<input type="text" name="app_name" class="inp focus"/>
				<span class="red"></span>
               </td>
           </tr>
           
           <tr style="height: 60px;">
               <td align="right"><span style="color: red;">*</span>上传文件：</td>
               <td align="left" style="vertical-align: top;position:static;" colspan="3">
               	<input type="button" style="cursor: pointer;" value="选择文件..." id="browse" />
				<input type="button" style="cursor: pointer;" value="开始上传" id="upload-btn" />
				<ul id="file-list"></ul>
               	<span class="red"></span>
               </td>
           </tr>
           
           <tr class="version_code_2">
               <td align="right" width="25%">内部版本号：</td>
               <td align="left" width="25%"><span name="inter_version_code"></span></td>
               <td align="right" width="25%">外部版本号：</td>
               <td align="left" width="25%"><span name="version_code"></span></td>
           </tr>
           
            <tr class="apkPackageName">
               <td align="right"><span style="color: red;">*</span>应用包名：</td>
               <td align="left" colspan="3">
              		<span name="apkPackageName"></span>
               </td>
           </tr>  
	
		  <tr>
           	  <td align="right">平台：</td>
                 <td align="left" colspan="3">
	               	<select  name="platformCode" class="sel mgr10" style="width:150px;">
						
					</select>  
					
					
					<a href="javascript:void(0)" onclick="addArea()">添加</a>
               </td>
           </tr>
           
           <tr>
           	  <td align="left" colspan="4">
           	  		<div name="areas">
           	  		</div>
           	  
           	  </td>
           </tr>
           
           
           <tr>
               <td align="right"><span style="color: red;"></span>支持型号：</td>
               <td align="left" colspan="4">
	        	<div class="upgradeVersion">
               		<p></p>
               		<div>
               			
	               	</div>
               	</div>
               </td>
           </tr>


           <tr>
               <td align="right"><span style="color: red;">*</span>应用描述：</td>
               <td align="left" colspan="3">
               	<textarea name="description" rows="5" style="width: 70%" placeholder="请输入应用描述......"></textarea>
               	<span class="red"></span>
               </td>
           </tr>
           <tr>
           	<td colspan="4" style="height: 53px;">
           		<input type="button" name="save" class="btn_blue" value="保存" />
           		<%-- <span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span> --%>
           		<input type="button" name="close" class="btn_gray" value="取消" />
           	</td>
           </tr>
       </table>
    </div>
</div>

<!--编辑-->
<div class="popup jumpBox edit dis_none" style="margin-top:200px;">
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">编辑</span><span class="close" name="close">X</span></div>
    <div class="cont">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>应用名称：</td>
               <td width="25%" align="left" >
				<input type="text" name="appName" class="inp focus"/>
				<span class="red"></span>
               </td>
               
               <td width="50%" align="left" colspan="2">
               		<p><a id="app_download_url" href="#" target="_blank">下载最新应用安装包</a></p>
               		<div class="mgt20" align="center" style="max-width: 96px;max-height: 96px;avertical-align: middle;" id="product_image">
               		</div>
               </td>
               
           </tr>
           
           <tr class="version_code_2">
               <td align="right" width="25%">内部版本号：</td>
               <td align="left" width="25%"><span name="interVersionCode"></span></td>
               <td align="right" width="25%">外部版本号：</td>
               <td align="left" width="25%"><span name="versionCode"></span></td>
           </tr>
           <tr class="version_code_3">
               <td align="right"><span style="color: red;">*</span>版本号：</td>
               <td align="left" colspan="3">
               	<input type="text" name="versionCode" class="inp focus"/>
				<span class="red"></span>
               </td>
           </tr>
           
           
           <tr>
           	  <td align="right">平台：</td>
                 <td align="left" colspan="3">
	               	<select  name="platformCode" class="sel mgr10" style="width:150px;">
						
					</select>  
					
					
					<a href="javascript:void(0)" onclick="editArea()">添加</a>
               </td>
           </tr>
           
           <tr>
           	  <td align="left" colspan="4">
           	  		<div name="areas">
           	  		</div>
           	  
           	  </td>
           </tr>
           
           
           <tr>
               <td align="right"><span style="color: red;"></span>支持型号：</td>
               <td align="left" colspan="3">
	        	<div class="upgradeVersion dis_none">
               		<p></p>
               		<div>
               			
	               	</div>
               	</div>
               </td>
           </tr>
           
           
           
           <tr>
               <td align="right"><span style="color: red;">*</span>应用描述：</td>
               <td align="left" colspan="3">
               	<textarea name="description" rows="5" cols="48" placeholder="请输入应用描述......"></textarea>
               	<span class="red"></span>
               </td>
           </tr>
           
           
           
           <tr>
           	<td colspan="4" style="height: 53px;">
           		<input type="button" name="save" class="btn_blue" value="保存" />
           		<%-- <span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span> --%>
           		<input type="button" name="close" class="btn_gray" value="取消" />
           	</td>
           </tr>
       </table>
    </div>
</div>

<!--详情-->
<div class="popup jumpBox detail dis_none">
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">详情</span><span class="close" name="close">X</span></div>
    <div class="cont">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right">应用名称：</td>
               <td width="75%" align="left" colspan="3"><span class="app_name"></span></td>
           </tr>
          
           <tr class="version_code_2">
               <td align="right" width="25%">内部版本号：</td>
               <td align="left" width="25%"><span class="inter_version_code"></span></td>
               <td align="right" width="25%">外部版本号：</td>
               <td align="left" width="25%"><span class="version_code"></span></td>
           </tr>

           <tr>
               <td align="right">应用描述：</td>
               <td align="left" colspan="3"><span class="description"></span></td>
           </tr>
           <tr>
           	<td colspan="4" style="height: 53px;">
           		<input type="button" name="close" class="btn_gray" value="关闭" />
           	</td>
           </tr>
       </table>
    </div>
</div>

<script type="text/javascript">

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



var $addCkAll = $(".add input[name='allVersion']");
$addCkAll.bind("click",function() {
	$(".add input[name='modelCode']").removeAttr("checked");
	//$(".add input[name='modelCode']").prop("checked",this.checked);
});
$(".add input[name='modelCode']").live("click",function() {
    var b=$(".edit input[name='modelCode']").filter(":checked").length==$(".edit input[name='modelCode']").length;
    
    if(b){
    	$addCkAll.removeAttr("checked");
    }
});



var $editCkAll = $(".edit input[name='allVersion']");
$editCkAll.bind("click",function() {
	$(".edit input[name='modelCode']").removeAttr("checked");
	//$(".edit input[name='modelCode']").prop("checked",this.checked);
});
$(".edit input[name='modelCode']").live("click",function() {
    var b=$(".edit input[name='modelCode']").filter(":checked").length==$(".edit input[name='modelCode']").length;
    
    if(b){
    	$editCkAll.removeAttr("checked");
    }
});



//tab
$("div[name='slide']").tabControl("*[name='slideTag'] a" , "*[name='slideCont']" , '.more');

$("*[name='slideTag'] a").click(function(){
	$('#app_name').val('');
	$('#version_code').val('');
	$('#inter_version_code').val('');
	
	
	
	search();
	
});

var productData;

function search(){
	resetPageParam();
	loadList();
}

function loadList(){
	var derivativeType = $("*[name='slideTag'] a.on").attr("id");
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/derivativeApp/queryProduct",
		data : {
			"derivativeType" : derivativeType,
			"app_name" : $('#app_name').val(),
			"version_code" : $('#version_code').val(),
			"inter_version_code" : $('#inter_version_code').val(),
			"currPage" : $('#currPage').val(),
			"pageSize" : $('#pageSize').val()
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			var htmlStr = '';

			if(data != null){
				if(data.list != null && data.list.length > 0){
					dataArray = data.list;
					for(var i=0;i<data.list.length;i++){
						var obj = data.list[i];
						htmlStr += '<tr>';
						htmlStr += '<td>'+(i+1)+'</td>';
						htmlStr += '<td>'+obj.appName+'</td>';
						
						htmlStr += '<td>'+obj.interVersionCode+'</td>';
						
						
						htmlStr += '<td>'+obj.versionCode+'</td>';
						htmlStr += '<td>'+obj.createTimeString+'</td>';
						htmlStr += '<td>'+obj.updateTimeString+'</td>';
						
						htmlStr += '<td>'+obj.apkPackageName+'</td>';
						if(obj.canUpdate == "0"){
							htmlStr += '<td>不可升级</td>';
						}else if(obj.canUpdate == "1"){
							htmlStr += '<td>全部用户升级</td>';
						}else if(obj.canUpdate == "2"){
							htmlStr += '<td>根据参数升级</td>';
						}else{
							htmlStr += '<td></td>';
						}
						htmlStr += '<td>';
						
						htmlStr += '<a href="javascript:void(0);" class="mgr10" onclick="openUpdate(\''+obj.id+'\',\'1\')">开放更新</a>';
						htmlStr += '<a href="javascript:void(0);" class="mgr10" onclick="openUpdate(\''+obj.id+'\',\'2\')">内部更新</a>';
						
						htmlStr += '<a href="javascript:void(0);" class="mgr10" onclick="edit(\''+obj.id+'\')">编辑</a>';
						htmlStr += '<a href="javascript:void(0);" onclick="del(\''+obj.id+'\')">删除</a>';
						htmlStr += '</td></tr>';
					}
				}
				if(data.page != null){
					setPage($("#base_page_"+derivativeType),data.page);
				}
			}
		
			if(htmlStr != ''){
				$("#dataList_"+derivativeType).html(htmlStr);
			}else{
				$("#dataList_"+derivativeType).html('<tr><td colspan="9" align="center"><font color="red">暂无数据</font></td></tr>');
				$("#base_page_"+derivativeType).html('');
			}
		}
	});
}

//开放某个应用的升级
function openUpdate(id,canUpdate){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/derivativeApp/openUpdate",
		data : {"id":id,"canUpdate":canUpdate},
		async : false,
		dataType : 'text',
		success : function(data) {
			if(data=='success'){
				$.alert("操作成功！",loadList);
			}else{
				$.alert("操作失败,请重试！");
			}
		}
	});
}





//开放某个应用的升级
function zipOpenUpdate(id,derivativeType,canUpdate){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/app/zipOpenUpdate",
		data : {"id":id,"derivativeType":derivativeType,"canUpdate":canUpdate},
		async : false,
		dataType : 'text',
		success : function(data) {
			if(data=='success'){
				$.alert("操作成功！",loadList);
			}else{
				$.alert("操作失败,请重试！");
			}
		}
	});
}

var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'browse',
	url : '${ctx}/manage/derivativeApp/upload',
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
			$(".add input[name='file_path']").val(app.file_path);
			$(".add span[name='inter_version_code']").text(app.inter_version_code);
			$(".add span[name='version_code']").text(app.version_code);
			$(".add span[name='apkPackageName']").text(app.apk_package_name);
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
	$(".add input[type='hidden']").prop("value",'');
	$(".add input[type='text']").prop("value",'');
	$(".add select[name='status']").val('0');
	$(".add textarea[name='description']").val('');
	$(".add .red").text('');
	
	$("#file-list").html('');
	
	$.each(uploader.files, function (i, file) {
        uploader.files.splice(i, 1);
    });
	
	$(".add span[name='inter_version_code']").text('');
	$(".add span[name='version_code']").text('');
	var htmlSel = '';
	$(".add").jumpBox(true);
}
var modelCodeList = [];
function edit(id){
	modelCodeList=[];
	$(".edit .red").text('');
	$editCkAll.prop("checked",false);
	var derivativeType = $("*[name='slideTag'] a.on").attr("id");
	
	
	$(".edit div[name='areas']").children().remove();
	
	
	var flatMode = getEditFlatModel();
	
	//if(flatMode==''){
	//	$("#addMsg").text('请选择支持的型号');
	//	return;
	//}
	
	var platformCode="";
	$(".edit div[name='areas']").find("input[name='areaId']").each(function(i,domEle){
		var a = $(domEle).val();
		//alert(this.val());
		
		platformCode = platformCode+","+a;
	})
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/platformInfo/derivativeEbpAppPlatformInfo",
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
		url : "${ctx}/manage/derivativeApp/detail",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(app) {
			if(app != null){
				$(".edit input[name='id']").val(app.id);
				$(".edit input[name='appName']").val(app.appName);
				$(".edit span[name='interVersionCode']").text(app.interVersionCode);
				$(".edit span[name='versionCode']").text(app.versionCode);
				$(".edit input[name='versionCode']").val(app.versionCode);
				$(".edit textarea[name='description']").val(app.description);
				$(".edit #app_download_url").attr("href",app.baiduBosUrl);
				$("#product_image").html('<image src="'+app.iconUrl+'" style="max-width:96px;max-height:96px;"/>');
				$(".edit").jumpBox(true);
				
				if(typeof(app.flat_model_ids)!="undefined"){
					modelCodeList = app.flat_model_ids.split(",");
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
		url : "${ctx}/manage/flatModel/getAllFlatModel",
		data : {
			"id":id
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

function detail(id){
	var productType = $("*[name='slideTag'] a.on").attr("id");
	if(productType=='2'){
		$(".detail .version_code_2").show();
		$(".detail .version_code_3").hide();
	}else if(productType=='3'){
		$(".detail .version_code_3").show();
		$(".detail .version_code_2").hide();
	}
	
	$(".edit div[name='areas']").children().remove();
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/app/detail",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(app) {
			if(app != null){
				$(".detail .app_name").text(app.app_name);
				$(".detail .inter_version_code").text(app.inter_version_code);
				$(".detail .version_code").text(app.version_code);
				$(".detail .versionCode").text(app.version_code);
				$(".detail .description").text(app.description);
				$(".detail .product_id").text(app.product_name);
				$(".detail .old_file_name").text(app.old_file_name+"("+plupload.formatSize(app.file_size)+")");
				
				$(".detail").jumpBox(true);
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



$(document).ready(function(){ 
	loadList();
	
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
		var filePath=$(".add input[name='file_path']").val();
		
		var appName=$(".add input[name='app_name']").val();
		var fileSize=$(".add input[name='file_size']").val();		
		var description=$(".add textarea[name='description']").val();
		
		var versionCode = $(".add span[name='version_code']").text();
		var interVersionCode = $(".add span[name='inter_version_code']").text();
		
		if(versionCode == null || versionCode == ''){
			versionCode = $(".add input[name='versionCode']").val();
			inter_versionCode = versionCode;
		}
		
		if($.trim(appName)==''){
			$(".add input[name='appName']").siblings(".red").text("请填写应用名称");
			return;
		}
		if($.trim(description)==''){
			$(".add textarea[name='description']").siblings(".red").text("请填写应用描述");
			return;
		}
		if(versionCode == null || versionCode == ''){
			$(".add input[name='versionCode']").siblings(".red").text("请填写版本号");
			return;
		}
		if($.trim(filePath)==''){
			$("#browse").siblings(".red").text("请选择上传文件并等待上传完成");
			return;
		}
		
		
		var derivativeType = $("*[name='slideTag'] a.on").attr("id");
		
		$addCkAll.prop("checked",false);
		
		$(".add input[name='allVersion']").prop("checked",false);
		
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
				url : "${ctx}/manage/derivativeApp/save",
				data : {
					"derivativeType":derivativeType,
					"tmpFilePath"  : filePath,
					"appName"  : appName,
					"description"  : description,
					"fileSize" : fileSize,
					"interVersionCode" : interVersionCode,
					"versionCode" : versionCode,
					"apkPackageName" : $(".add span[name='apkPackageName']").text(),
					"flat_model_ids":flatMode,
					"platform_codes":platformCode
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='success'){
						$(".add").closeBox();
						loadList();
					}
				}
			});
		}, 1);
		
	});
	
	$(".edit input[name='save']").click(function(){
		var id = $(".edit input[name='id']").val();
		var appName=$(".edit input[name='appName']").val();
		
		var description=$(".edit textarea[name='description']").val();
		
		var derivativeType = $("*[name='slideTag'] a.on").attr("id");
		
		if($.trim(app_name)==''){
			$(".edit input[name='appName']").siblings(".red").text("请填写应用名称");
			return;
		}
		if($.trim(description)==''){
			$(".edit textarea[name='description']").siblings(".red").text("请填写应用描述");
			return;
		}
		
		var flatMode = getEditFlatModel();
		
		var platformCode="";
		
		$(".edit div[name='areas']").find("input[name='areaId']").each(function(i,domEle){
			var a = $(domEle).val();
			//alert(this.val());
			
			platformCode = platformCode+","+a;
		})
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/derivativeApp/update",
				data : {
					"id"  : id,
					"appName"  : appName,
					"derivativeType"  : derivativeType,
					"description"  : description,
					"flat_model_ids":flatMode,
					"platform_codes":platformCode
					//"inter_version_code" : inter_version_code,
					//"version_code" : version_code,
					//"flat_model_ids":flatMode,
					//"platform_codes":platformCode
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='success'){
						$(".edit").closeBox();
						loadList();
					}
				}
			});
		}, 1);
		
	});
	
	
});

function del(id){
	$.confirm("确定要删除该应用吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/derivativeApp/delete",
			data : {
				"id"  : id
			},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='success'){
					loadList();
				}
			}
		});
	});
	
}

var h=true;
function unzip(id){
	$.alert("正在解压中，请耐心等待...");
	if(h){
		h=false;
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/app/unzip",
			data : {"id" : id},
			async : true,
			dataType : 'text',
			success : function(msg) {
				h=true;
				$.alertClose();
				if(msg=='success'){
					$.alert("解压成功！");
				}else if(msg=='error'){
					$.alert("解压失败！");
				}
			}
		});
	}
	
}

function upgrade(id,productId){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/app/createUpgradeFile",
		data : {
			"id"  : id,
			"productId"  : productId
		},
		async : false,
		dataType : 'text',
		success : function(msg) {
			if(msg=='success'){
				$.alert("升级数据生成完成！",loadList);
			}
		}
	});
}

</script>

</body>
</html>