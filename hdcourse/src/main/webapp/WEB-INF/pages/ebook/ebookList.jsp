<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ page import="com.whty.common.util.SysConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通用户管理页面</title>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<style type="text/css">
.progress{ height: 4px; font-size: 0; line-height: 4px; background: orange; width: 0;}
.num{ margin-left: 20px;}
#file-list img{cursor: pointer;}
</style>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/ebooks/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
	
    <div class="mgtb10 txq_txtbox clearfix">
    	<p class="fl mgr10"><span>学段：</span>
        	<select name="period_id" id="period_id" class="sel" style="width:130px;" onchange="loadSubject(this.value)">
        		<%-- <option value="" <c:if test="${empty periodList}">selected="selected"</c:if>>全部</option> --%>
        		<option value="">全部</option>
        		<c:forEach items="${periodList}" var="period" varStatus="status">
        			<option value="${period.periodId}" <c:if test="${period.periodId == period_id}">selected="selected"</c:if>>${period.periodName}</option>
        		</c:forEach>
        	</select>
        </p>
        <p class="fl mgr10"><span>学科：</span>
        	<select name="subject_id" id="subject_id" class="sel" style="width:130px;" onchange="loadEdition(this.value)">
        		<option value="${subject_id}">${subject_name}</option>
        	</select>
        	<input type="hidden" name="subject_name" id="subject_name">
        </p>
        <p class="fl mgr10"><span>版本：</span>
        	<select name="edition_id" id="edition_id" class="sel" style="width:130px;" onchange="loadVolume(this.value)">
        		<option value="${edition_id}">${edition_name}</option>
        	</select>
        	<input type="hidden" name="edition_name" id="edition_name">
        </p>
        <p class="fl mgr10"><span>册别：</span>
        	<select name="volume_id" id="volume_id" class="sel" style="width:130px;">
        		<option value="${volume_id}">${volume_name}</option>
        	</select>
        	<input type="hidden" name="volume_name" id="volume_name">
        </p>
        <p class="fl mgr10"><span>名称：</span><input name="ebook_name" value="${ebook_name}" type="text" style="width:130px;" class="inp focus" placeholder="请输入名称"/></p>
        <p class="fl"><input type="button" onclick="search()" class="btn_blue" value="查询"/></p>
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>数字教材列表</strong></div>

		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="新增" onclick="add()"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="删除" onclick="del()"/>&nbsp;&nbsp;
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="4%"><input type="checkbox" name="ckAll"></th>
				<th width="16%">名称</th>
				<th width="13%">版本</th>
				<th width="5%">下载次数</th>
				<th width="15%">更新时间</th>
				<th width="10%">操作</th>
		    </tr>
		    <c:if test="${fn:length(ebooklist) < 1}">
		    	<tr>
		    		<td colspan="10" style="color: red">暂无数据</td>
		    	</tr>
		    </c:if>
		    <c:if test="${fn:length(ebooklist) >= 1}">
		    	<c:forEach items="${ebooklist}" var="obj" varStatus="status">
			    <tr>
					<td><input type="checkbox" name="ckItm" value="${obj.ebook_id}"></td>
					<td>${obj.ebook_name }</td>
					<td>${obj.edition_name }</td>
					<td>${obj.download_num }</td>
					<td><fmt:formatDate value="${obj.modify_time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<a href="javascript:void(0);" class="mgr10" onclick="detail('${obj.ebook_id}')">查看</a>
						<c:if test="${obj.status=='0' }"><a href="javascript:void(0);" class="mgr10" onclick="edit('${obj.ebook_id}')">编辑</a></c:if>
					</td>
			    </tr>
		    </c:forEach>
		    </c:if>
		</table>
    </div>
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${fn:length(ebooklist) > 0}">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/ebooks/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>
<!--新增  编辑-->
<div class="popup jumpBox add_edit dis_none" style="width: 600px;">
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">标题</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
            <tr>
               <td width="20%" align="right"><span style="color: red;">*</span>学段：</td>
               <td width="30%" align="left" style="padding: 0px">
               		<select name="period_id" id="period_id1" class="sel" style="width:110px;" onchange="selectSubject(this.value)">
        				<%-- <option value="" <c:if test="${empty periodList}">selected="selected"</c:if>>全部</option> --%>
        				<%-- <option>全部</option>
        				<c:forEach items="${periodList}" var="period" varStatus="status">
        					<option value="${period.periodId}">${period.periodName}</option>
        				</c:forEach> --%>
        			</select>
        			<span id="periodmsg" class="red"></span>
               </td>
               <td width="23%" align="right"><span style="color: red;">*</span>学科：</td>
               <td width="27%" align="left" style="padding: 0px;">
               		<select name="subject_id" id="subject_id1" class="sel" style="width:110px;" onchange="selectEdition(this.value)">
        			</select>
        			<span id="subjectmsg" class="red"></span>
               </td>
               
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>版本：</td>
               <td align="left" style="padding: 0px"">
               		<select name="edition_id" id="edition_id1" class="sel" style="width:110px;" onchange="selectVolume(this.value)">
        			</select>
        			<span id="editionmsg" class="red"></span>
               <td align="right"><span style="color: red;">*</span>册别：</td>
               <td align="left" style="padding: 0px"">
               		<select name="volume_id" id="volume_id1" class="sel" style="width:110px;" onchange="selectTextBook(this.value)">
        			</select>
        			<span id="volumemsg" class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>教材：</td>
               <td align="left" colspan="2" style="padding: 0px">
               		<select name="textbook_id" id="textBook_id1" class="sel" style="width:220px" onchange="selectTextBookName(this.value)">
	        		</select>
	        		<span id="textbookIdmsg" class="red"></span>
               </td>
               <td align="center" rowspan="4" style="padding: 0px;">
               	<div style="max-width: 156px;max-height: 208px;text-align: center;vertical-align: middle;" id="show_image">
               	
               	</div>
               </td>
           </tr>
           <tr>
               <td align="right">名称：</td>
               <td align="left" colspan="2" style="padding: 0px">
               		<input name="textbook_name" id="textbook_name" type="text" style="height: 100%;width:220px;" value=""/>
               		<span id="textbookNamemsg" class="red"></span>
               </td>
           </tr>
           <tr style="height: 68px;text-align: justify;">
				<input type="hidden" name="fileUrl">
				<input type="hidden" name="fileName">
				<input type="hidden" name="fileRename">
				<input type="hidden" name="fileSize">
               <td align="right"><span style="color: red;">*</span>上传电子书：</td>
               <td align="left" colspan="2" style="padding: 0px;vertical-align: top;">
               		<input type="button" style="cursor: pointer;" value="选择文件..." id="browse" />
					<input type="button" style="cursor: pointer;" value="开始上传" id="upload-btn" />
					<ul id="file-list" style="margin-top: 10px;"></ul>
               		<span class="red" id="filemsg"></span>
               </td>
           </tr>
           <tr style="height: 68px;">
           	   <input type="hidden" name="fileUrl1">
		       <input type="hidden" name="fileName1">
		       <input type="hidden" name="fileRename1">
		       <input type="hidden" name="ebook_id">
               <td align="right"><span style="color: red;">*</span>上传缩略图：</td>
               <td align="left" colspan="2" style="padding: 0px;vertical-align: top;">
               		<input type="button" style="cursor: pointer;" value="选择文件..." id="browse1" />
					<input type="button" style="cursor: pointer;" value="开始上传" id="upload-btn1" />
					<ul id="file-list1" style="margin-top: 10px;"></ul>
               		<span class="red" id="imgmsg"></span>
               </td>
           </tr>
           <tr>
               <td colspan="4" align="right" style="padding: 0px;text-align: center;">
               		<input type="button" name="save" class="btn_blue" value="保存" />
           		<span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span>
           		<input type="button" name="close" class="btn_gray" value="取消" />
               </td>
           </tr>
       </table>
    </div>
</div>

<!--详情-->
<div class="popup jumpBox detail dis_none" style="width: 600px;">
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">详情</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
            <tr>
               <td width="20%" align="right">学段：</td>
               <td width="30%" align="left" style="padding: 0px">
        			<span name="period_name"></span>
               </td>
               <td width="23%" align="right">学科：</td>
               <td width="27%" align="left" style="padding: 0px;">
        			<span name="subject_name"></span>
               </td>
               
           </tr>
           <tr>
               <td align="right">版本：</td>
               <td align="left" style="padding: 0px"">
        			<span name="edition_name"></span>
               <td align="right">册别：</td>
               <td align="left" style="padding: 0px"">
        			<span name="volume_name"></span>
               </td>
           </tr>
           <tr>
               <td align="right">教材：</td>
               <td align="left" colspan="2" style="padding: 0px">
	        		<span name="textbook_name"></span>
               </td>
               <td align="center" rowspan="5" style="padding: 0px;">
               	<div style="max-width: 156px;max-height: 208px;text-align: center;vertical-align: middle;" id="show_image2">
               	
               	</div>
               </td>
           </tr>
           <tr>
               <td align="right">名称：</td>
               <td align="left" colspan="2" style="padding: 0px">
               		<span name="ebook_name"></span>
               </td>
           </tr>
           <tr>
               <td align="right">大小：</td>
               <td align="left" colspan="2" style="padding: 0px">
               		<span name="file_size"></span>
               </td>
           </tr>
           <tr style="height: 48px;text-align: justify;">
               <td align="right">电子书：</td>
               <td align="left" colspan="2">
               	<a name="ebookFile" target="_blank">下载</a>
               </td>
           </tr>
           <tr style="height: 48px;">
               <td align="right">缩略图：</td>
               <td align="left" colspan="2">
               	<a name="imageFile" target="_blank">下载</a>
               </td>
           </tr>
           <tr>
               <td colspan="4" align="right" style="padding: 0px;text-align: center;">
           			<input type="button" name="close" class="btn_gray" value="关闭" />
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

/* var dataArray = eval('('+'${listJson}'+')'); */
//查询
function search(){
	$("#subject_name").val($("#subject_id option:selected").text());
	$("#edition_name").val($("#edition_id option:selected").text());
	$("#volume_name").val($("#volume_id option:selected").text());
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

function emptyAddEdit(){
	$(".add_edit input[type='hidden']").prop("value",'');
	$(".add_edit input[type='text']").prop("value",'');
	$(".add_edit .red").text('');
	$(".add_edit input[name='save']").show();
	$(".add_edit input[name='save']").next().hide();
	$(".add_edit input[name='save']").next().next().show();
}
function add(){
	emptyAddEdit();
	$(".add_edit .fl").text("新增");
	var sel = "<option value=''>全部</option><c:forEach items='${periodList}' var='period' varStatus='status'><option value='${period.periodId}'>${period.periodName}</option></c:forEach>";
	$(sel).appendTo("#period_id1");
	$(".add_edit").jumpBox(true);
}

//删除
function del(){
	var ids = getCheckValue();
	if(ids==""){
		$.alert("请至少选择一项！");
		return;
	}
	$.confirm("确定要删除选中的数字教材信息吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/ebooks/delete",
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
<script type="text/javascript">
var edition_id;
var subject_id;
var volume_id;
$(document).ready(function(){
	edition_id = $("#edition_id").val();
	subject_id = $("#subject_id").val();
	volume_id = $("#volume_id").val();
	if($("#period_id").val()!=null&&$("#period_id").val()!=''){
		loadSubject($("#period_id").val());
	}
});
//查询加载学科
var loadSubject = function(periodId) {
	//清空原有数据
	$("#subject_id").html("");
	$("#edition_id").html("");
	$("#volume_id").html("");
	$.ajax({
		url : '${ctx}/manage/ebooks/getSubject',
		data : {
			"periodId" : periodId
		},
		type : 'post',
		dataType : 'json',
		success : function(data) {
			var opt = "<option value=''>全部</option>";
			for (i = 0; i < data.length; i++) {
				if(subject_id == data[i].subjectId){
					opt = opt + '<option value="'+data[i].subjectId+'" selected="selected">'
					+ data[i].subjectName + '</option>';
					loadEdition(subject_id);
				}else{
					opt = opt + '<option value="'+data[i].subjectId+'">'
						+ data[i].subjectName + '</option>';
				}
			}
			$("#subject_id").append(opt);
		},
		error : function() {
		}
	});
};
//查询加载版本
var loadEdition = function(subjectId) {
	var periodId = $("#period_id").find("option:selected").val();
	//清空原有数据
	$("#edition_id").html("");
	$("#volume_id").html("");

	$.ajax({
		url : '${ctx}/manage/ebooks/getEdition',
		data : {
			"periodId" : periodId,
			"subjectId" : subjectId
		},
		type : 'post',
		dataType : 'json',
		success : function(data) {
			var opt = "<option value=''>全部</option>";
			for (i = 0; i < data.length; i++) {
				if(edition_id == data[i].editionId){
					opt = opt + '<option  value="'+data[i].editionId+'" selected="selected">'
					+ data[i].editionName + '</option>';
					loadVolume(edition_id);
				}else{
					opt = opt + '<option  value="'+data[i].editionId+'">'
					+ data[i].editionName + '</option>';
				}
			}
			$("#edition_id").append(opt);
		},
		error : function() {
		}
	});
}
//查询加载册别
var loadVolume = function(editionId) {
	var periodId = $("#period_id").find("option:selected").val();
	var subjectId = $("#subject_id").find("option:selected").val();
	//清空原有数据
	$("#volume_id").html("");
	$.ajax({
		url : '${ctx}/manage/ebooks/getVolume',
		data : {
			"periodId" : periodId,
			"subjectId" : subjectId,
			"editionId" : editionId
		},
		type : 'post',
		dataType : 'json',
		success : function(data) {
			var opt = "<option value=''>全部</option>";
			for (i = 0; i < data.length; i++) {
				if(volume_id == data[i].volumeId){
					opt = opt + '<option  value="'+data[i].volumeId+'" selected="selected">'
					+ data[i].volumeName + '</a>';
				}else{
					opt = opt + '<option  value="'+data[i].volumeId+'">'
					+ data[i].volumeName + '</a>';
				}
			}
			$("#volume_id").append(opt);
		},
		error : function() {
		}
	});
}
//新增修改加载学段
/* var selectPeriod = function(){
	$("#period_id1").html("")
	$.ajax({
		url : '${ctx}/manage/ebooks/getPeriod',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			var opt = "<option>全部</option>";
			for (i = 0; i < data.length; i++) {
				// <c:if test="${subject_id==data[i].subjectId}">selected="selected"</c:if>
				opt = opt + '<option value="'+data[i].periodId+'">'
						+ data[i].periodName + '</option>';
			}
			$("#period_id1").append(opt);
		},
		error : function() {
		}
	});
} */

//新增修改加载学科
var selectSubject = function(periodId) {
	/* var opt = "<option></option>";
	if(0==document.getElementById("subject_id1").selectedIndex){
		var opt = "";
	} */
	//清空原有数据
	$("#subject_id1").html("")
	$("#edition_id1").html("");
	$("#volume_id1").html("");
	$.ajax({
		url : '${ctx}/manage/ebooks/getSubject',
		data : {
			"periodId" : periodId
		},
		type : 'post',
		dataType : 'json',
		success : function(data) {
			var opt = "<option value=''>全部</option>";
			for (i = 0; i < data.length; i++) {
				// <c:if test="${subject_id==data[i].subjectId}">selected="selected"</c:if>
				opt = opt + '<option value="'+data[i].subjectId+'">'
						+ data[i].subjectName + '</option>';
			}
			$("#subject_id1").append(opt);
		},
		error : function() {
		}
	});
};
//新增修改加载版本
var selectEdition = function(subjectId) {
	var periodId = $("#period_id1").find("option:selected").val();
	//清空原有数据
	$("#edition_id1").html("");
	$("#volume_id1").html("");

	$.ajax({
		url : '${ctx}/manage/ebooks/getEdition',
		data : {
			"periodId" : periodId,
			"subjectId" : subjectId
		},
		type : 'post',
		dataType : 'json',
		success : function(data) {
			var opt = "<option value=''>全部</option>";
			for (i = 0; i < data.length; i++) {
				// <c:if test="${edition_id==data[i].editionId}">selected="selected"</c:if>
				opt = opt + '<option  value="'+data[i].editionId+'">'
						+ data[i].editionName + '</option>';
			}
			$("#edition_id1").append(opt);
		},
		error : function() {
		}
	});
}
//新增修改加载册别
var selectVolume = function(editionId) {
	var periodId = $("#period_id1").find("option:selected").val();
	var subjectId = $("#subject_id1").find("option:selected").val();
	//清空原有数据
	$("#volume_id1").html("");
	$.ajax({
		url : '${ctx}/manage/ebooks/getVolume',
		data : {
			"periodId" : periodId,
			"subjectId" : subjectId,
			"editionId" : editionId
		},
		type : 'post',
		dataType : 'json',
		success : function(data) {
			var opt = "<option value=''>全部</option>";
			for (i = 0; i < data.length; i++) {
				//<c:if test="${volumen_id==data[i].volumeId}">selected="selected"</c:if>
				opt = opt + '<option  value="'+data[i].volumeId+'">'
						+ data[i].volumeName + '</a>';
			}
			$("#volume_id1").append(opt);
		},
		error : function() {
		}
	});
}
//新增修改加载教材
var selectTextBook = function(volumeId) {
	var periodId = $("#period_id1").find("option:selected").val();
	var subjectId = $("#subject_id1").find("option:selected").val();
	var editionId = $("#edition_id1").find("option:selected").val();
	//清空原有数据
	$("#textBook_id1").html("");
	$.ajax({
		url : '${ctx}/manage/ebooks/getTextBook',
		data : {
			"periodId" : periodId,
			"subjectId" : subjectId,
			"editionId" : editionId,
			"volumeId" : volumeId
		},
		type : 'post',
		dataType : 'json',
		success : function(data) {
			var opt = "<option value=''>全部</option>";
			for (i = 0; i < data.length; i++) {
				//<c:if test="${volumen_id==data[i].volumeId}">selected="selected"</c:if>
				opt = opt + '<option  value="'+data[i].textbookId+'">'
						+ data[i].textbookName + '</a>';
			}
			$("#textBook_id1").append(opt);
		},
		error : function() {
		}
	});
}
//教材名称的填充
var selectTextBookName = function(textbookId) {
	var textbookName = $("#textBook_id1").find("option:selected").text();
	$("#textbook_name").val(textbookName);
}
//文件上传
var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'browse',
	url : '${ctx}/manage/ebooks/uploadInfo1',
	flash_swf_url : '${ctx}/js/plupload-2.1.8/Moxie.swf',
	silverlight_xap_url : '${ctx}/js/plupload-2.1.8/Moxie.xap',
	multi_selection : false,
	
	filters : {
		mime_types: [
			{title : "exe files", extensions : "exe"},
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
				$("#filemsg").text("");
				$("#file-list").html('');
				$(".add_edit input[name='fileSize']").val(file.size);
				var html = '<li id="' + file.id +'"><img style="cursor: pointer;" src="${ctx}/js/uploadify/images/cancel.png" border="0"><span class="file-name">' + file.name + '(' + plupload.formatSize(file.size) + ')</span><span class="num"></span></li>';
				$(html).appendTo('#file-list');
			});
		},
		UploadProgress: function(uploader, file) {
			$('#'+file.id+' .num').html(file.percent + '%');
		},
		FileUploaded:function(uploader,file,responseObject){
			var soft = eval('('+responseObject.response+')');
			$(".add_edit input[name='fileUrl']").val(soft.fileUrl);
			$(".add_edit input[name='fileName']").val(soft.fileName);
			$(".add_edit input[name='fileRename']").val(soft.fileRename);
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

//文件上传按钮
$('#upload-btn').click(function(){
	uploader.start(); //开始上传
});

// 图片上传上传
var uploader1 = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'browse1',
	url : '${ctx}/manage/ebooks/uploadInfo1',
	flash_swf_url : '${ctx}/js/plupload-2.1.8/Moxie.swf',
	silverlight_xap_url : '${ctx}/js/plupload-2.1.8/Moxie.xap',
	multi_selection : false,
	
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
				$("#file-list1").html('');
				var html = '<li id="' + file.id +'"><img style="cursor: pointer;" src="${ctx}/js/uploadify/images/cancel.png" border="0"><span class="file-name">' + file.name + '(' + plupload.formatSize(file.size) + ')</span><span class="num"></span></li>';
				$(html).appendTo('#file-list1');
			});
		},
		UploadProgress: function(uploader, file) {
			$('#'+file.id+' .num').html(file.percent + '%');
		},
		FileUploaded:function(uploader,file,responseObject){
			var soft = eval('('+responseObject.response+')');
			$(".add_edit input[name='fileUrl1']").val(soft.fileUrl);
			$(".add_edit input[name='fileName1']").val(soft.fileName);
			$(".add_edit input[name='fileRename1']").val(soft.fileRename);
			//显示图片
			$("#show_image").html('<image src="<%=SysConfig.getStrValue("file_path_http")%>'+soft.fileUrl+'" style="max-width:156px;max-height:208px;"/>');
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

$(document).ready(function(){ 
	//文件上传
	$("#browse").click(function(){
		if(uploader.files.length == 1){
			$("#browse").siblings(".red").text("只能上传一个文件");
		}
	});
	//图片上传
	$("#browse1").click(function(){
		if(uploader1.files.length == 1){
			$("#browse1").siblings(".red").text("只能上传一个图片");
		}
	});
	
	//先暂停上传，后清空上传列表
	$(".add_edit *[name='close']").click(function(){
		if("新增" == $(".add_edit .fl").text()){
			//如果正在上传，就先停止上传后删除上传队列；否则，删除队列同时删除文件
			var fileUrl=$(".add_edit input[name='fileUrl']").val();
			//str = fileUrl.substring(0,fileUrl.lastIndexOf('\\'));
			var fileUrl1=$(".add_edit input[name='fileUrl1']").val();
			//str1 = fileUrl1.substring(0,fileUrl1.lastIndexOf('\\'));
			if(uploader.total.uploaded==0){
				uploader.stop();
			}
			
			if(uploader1.total.uploaded==0){
				uploader1.stop();
			}
				
			if(fileUrl != '' || fileUrl1 != ''){
				$.ajax({
					type : "POST",
					url : "${ctx}/manage/ebooks/deleteDisableFile",
					data : {"fileUrl" : fileUrl, "fileUrl1" : fileUrl1},
					async : false,
					dataType : 'text',
					success : function(msg) {
						$("#show_image").html('');
					}
				});
			}
			
			$("#file-list").html('');
			$("#file-list1").html('');
			$.each(uploader.files, function (i, file) {
		        uploader.files.splice(i, 1);
		    });
			$.each(uploader1.files, function (i, file) {
				uploader1.files.splice(i, 1);
		    });
		}
		window.location.href="${ctx}/manage/ebooks/list";
	});
	
	//删除文件
	$('#file-list img').live("click",function(){
		//如果正在上传，就先停止上传后删除上传队列；否则，删除队列同时删除文件
		var fileUrl=$(".add_edit input[name='fileUrl']").val();
		var fileRename=$(".add_edit input[name='fileRename']").val();
		//str = fileUrl.substring(0,fileUrl.lastIndexOf('\\'));
		if(uploader.total.uploaded==0){
			uploader.stop();
		}
		if(fileUrl != ''){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/ebooks/deleteDisableFile",
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
		$(".add_edit input[name='fileUrl']").prop("value",'');
		$(".add_edit input[name='fileName']").prop("value",'');
		$(".add_edit input[name='fileRename']").prop("value",'');
	});
	
	//删除图片
	$('#file-list1 img').live("click",function(){
		//如果正在上传，就先停止上传后删除上传队列；否则，删除队列同时删除文件
		var fileUrl1=$(".add_edit input[name='fileUrl1']").val();
		//str1 = fileUrl1.substring(0,fileUrl1.lastIndexOf('\\'));
		if(uploader1.total.uploaded==0){
			uploader1.stop();
		}
		if(fileUrl1 != ''){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/ebooks/deleteDisableFile",
				data : {"fileUrl1" : fileUrl1},
				async : false,
				dataType : 'text',
				success : function(msg) {
					$("#show_image").html('');
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
		$(".add_edit input[name='fileUrl1']").prop("value",'');
		$(".add_edit input[name='fileName1']").prop("value",'');
		$(".add_edit input[name='fileRename1']").prop("value",'');
	});
	$(".add_edit input[name='save']").click(function(){
		var ebook_id = $(".add_edit input[name='ebook_id']").val();
		var ebook_name=$(".add_edit input[name='textbook_name']").val();
		var period_id=$("#period_id1").val();
		var subject_id = $("#subject_id1").val();
		var edition_id = $("#edition_id1").val();
		var volume_id = $("#volume_id1").val();
		var textBook_id = $("#textBook_id1").val();
		var fileUrl = $(".add_edit input[name='fileUrl']").val();
		var fileUrl1 = $(".add_edit input[name='fileUrl1']").val();
		var fileName=$(".add_edit input[name='fileName']").val();
		var fileName1=$(".add_edit input[name='fileName1']").val();
		var fileSize=$(".add_edit input[name='fileSize']").val();
		
		if($.trim(period_id)==''){
			$("#periodmsg").text("请选择学段");
			return;
		}
		if($.trim(subject_id)==''){
			$("#subjectmsg").text("请选择学科");
			return;
		}
		if($.trim(edition_id)==''){
			$("#editionmsg").text("请选择版本");
			return;
		}
		if($.trim(volume_id)==''){
			$("#volumemsg").text("请选择册别");
			return;
		}
		if($.trim(textBook_id)==''){
			$("#textbookIdmsg").text("请选择教材");
			return;
		}
		if($.trim(ebook_name)==''){
			$("#textbookNamemsg").text("请填写名称");
			return;
		}
		if($.trim(fileUrl)==''){
			$("#filemsg").text("请选择上传文件并等待上传完成");
			return;
		}
		if($.trim(fileUrl1)==''){
			$("#imgmsg").text("请选择上传图片并等待上传完成");
			return;
		}
		$(this).hide();
		$(this).next().show();
		$(this).next().next().hide();
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/ebooks/save",
				data : {
					"ebook_id" : ebook_id,
					"ebook_name"  : ebook_name,
					"period_id"  : period_id,
					"subject_id"  : subject_id,
					"edition_id"  : edition_id,
					"volume_id"  : volume_id,
					"textbook_id"  : textBook_id,
					"fileUrl" : fileUrl,
					"fileUrl1"  : fileUrl1,
					"fileName"  : fileName,
					"fileName1"  : fileName1,
					"fileSize" : fileSize
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					$("#pageForm").submit();
				}
			});
		}, 1);
		
	});
});
//编辑
function edit(id){
	emptyAddEdit();
	$(".add_edit .fl").text("编辑");
	$(".add_edit").jumpBox(true);
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/ebooks/queryById",
		data : {"ebook_id" : id},
		async : false,
		dataType : 'json',
		success : function(ebookVO) {
			if(ebookVO != null && ebookVO !=''){
				$(".add_edit input[name='ebook_id']").val(ebookVO.ebook_id);
				$(".add_edit input[name='textbook_name']").val(ebookVO.ebook_name);
				//学段
				document.getElementById("period_id1").remove(document.getElementById("period_id1").selectedIndex);
				document.getElementById("period_id1").options.add(new Option(ebookVO.period_name,ebookVO.period_id));
				var sel = "<c:forEach items='${periodList}' var='period' varStatus='status'><option value='${period.periodId}' <c:if test='${period.periodId ==ebookVO.period_id}'>selected='selected'</c:if>>${period.periodName}</option></c:forEach>";
				$(sel).appendTo("#period_id1");
				
				/* alert(ebookVO.period_name+"----------"+ebookVO.period_id); */
				//学科
				document.getElementById("subject_id1").remove(document.getElementById("subject_id1").selectedIndex);
				document.getElementById("subject_id1").options.add(new Option(ebookVO.subject_name,ebookVO.subject_id));
				//版本
				document.getElementById("edition_id1").remove(document.getElementById("edition_id1").selectedIndex);
				document.getElementById("edition_id1").options.add(new Option(ebookVO.edition_name,ebookVO.edition_id));
				//册别
				document.getElementById("volume_id1").remove(document.getElementById("volume_id1").selectedIndex);
				document.getElementById("volume_id1").options.add(new Option(ebookVO.volume_name,ebookVO.volume_id));
				//教材
				document.getElementById("textBook_id1").remove(document.getElementById("textBook_id1").selectedIndex);
				document.getElementById("textBook_id1").options.add(new Option(ebookVO.textbook_name,ebookVO.textbook_id));
				//电子书
				$(".add_edit input[name='fileUrl']").prop("value",ebookVO.file_path);
				$(".add_edit input[name='fileSize']").prop("value",ebookVO.file_size);
				if(null !=ebookVO.file_path && '' != ebookVO.file_path){
					/* var html = '<li><img src="${ctx}/js/uploadify/images/cancel.png" style="cursor: pointer;" border="0" id="f1"><span class="file-name">' + ebookVO.file_path.substring(ebookVO.file_path.lastIndexOf("\\")+1)+'('+ plupload.formatSize(ebookVO.file_size) +')</span><span class="num"></span></li>'; */
					var html = '<li><img src="${ctx}/js/uploadify/images/cancel.png" style="cursor: pointer;" border="0" id="f1"><span class="file-name">' + ebookVO.f_path+'('+ plupload.formatSize(ebookVO.file_size) +')</span><span class="num"></span></li>';
					$(html).appendTo('#file-list');
				}
				
				//图片
				$(".add_edit input[name='fileUrl1']").prop("value",ebookVO.image_path);
				if(null != ebookVO.image_path && '' != ebookVO.image_path){
					/* var html = '<li><img src="${ctx}/js/uploadify/images/cancel.png" style="cursor: pointer;" border="0" id="f2"><span class="file-name">' + ebookVO.image_path.substring(ebookVO.image_path.lastIndexOf("\\")+1) + '</span><span class="num"></span></li>'; */
					var html = '<li><img src="${ctx}/js/uploadify/images/cancel.png" style="cursor: pointer;" border="0" id="f2"><span class="file-name">' + ebookVO.i_path + '</span><span class="num"></span></li>';
					$(html).appendTo('#file-list1');
					$("#show_image").html('<image src="<%=SysConfig.getStrValue("file_path_http")%>'+ebookVO.image_path+'" style="max-width:156px;max-height:208px;"/>');
				}
				
				
				$(".add_edit").jumpBox(true);
			}
		}
	});
}

//查看
function detail(id){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/ebooks/queryById",
		data : {"ebook_id" : id},
		async : false,
		dataType : 'json',
		success : function(ebookVO) {
			if(ebookVO != null && ebookVO !=''){
				$(".detail span[name='textbook_name']").text(ebookVO.textbook_name);
				//学段
				$(".detail span[name='period_name']").text(ebookVO.period_name);
				//学科
				$(".detail span[name='subject_name']").text(ebookVO.subject_name);
				//版本
				$(".detail span[name='edition_name']").text(ebookVO.edition_name);
				//册别
				$(".detail span[name='volume_name']").text(ebookVO.volume_name);
				//教材
				$(".detail span[name='ebook_name']").text(ebookVO.ebook_name);
				//大小
				var fileSize = '0B';
				if((ebookVO.file_size/1024)<1024){
					fileSize = Math.round(ebookVO.file_size/1024* 100)/100+'KB';
				}else {
				   if((ebookVO.file_size/(1024*1024))<1024){
					   fileSize = Math.round(ebookVO.file_size/1024/1024* 100)/100+'MB';
				   }else{
					   fileSize = Math.round(ebookVO.file_size/1024/1024/1024* 100)/100+'GB';
				   }
				   
			    }
				$(".detail span[name='file_size']").text(fileSize);
				//电子书
				$(".detail a[name='ebookFile']").attr("href","${ctx}/manage/ebooks/downloadEbook/"+ebookVO.ebook_id);
				//图片
				$(".detail a[name='imageFile']").attr("href","${ctx}/manage/ebooks/downloadImage/"+ebookVO.ebook_id);
				
				$("#show_image2").html('<image src="<%=SysConfig.getStrValue("file_path_http")%>'+ebookVO.image_path+'" style="max-width:156px;max-height:208px;"/>');
				
				$(".detail").jumpBox(true);
			}
		}
	});
}
</script>
</body>
</html>