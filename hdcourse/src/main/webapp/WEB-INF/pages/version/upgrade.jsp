<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>软件版本升级页面</title>
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<style type="text/css">
.data_list thead,.txq_txtbox,#base_page
{
-moz-user-select: none;
}
.upgradeVersion{width:350px;}
.upgradeVersion input{margin-right: 5px;}
.upgradeVersion div{margin-left:20px;}
.upgradeVersion div span{display: inline-block;width:80px;height: 25px;}
.upgradeVersion p{height:25px;}
</style>
</head>
<body>
<div class="mg15 txq_main">
    <div class="mgtb10 txq_txtbox clearfix" onselectstart="return false">
        <p class="fl mgr10"><span>软件名称：</span><input id="softName" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10"><span>软件版本：</span><input id="versionCode" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10"><span>升级状态：</span>
	        <select id="isleveup" class="sel" style="width:150px;">
	        	<option value="">全部</option>
				<option value="1">正常升级</option>
				<option value="2">静默升级</option>
			</select>
		</p>
        <p class="fl mgr10"><span>指定状态：</span>
        	<select id="upgradeType" class="sel" style="width:150px;">
        		<option value="">全部</option>
				<option value="1">指定用户升级</option>
				<option value="2">全部用户升级</option>
			</select>
        </p>
        <p class="fl">
        <!-- <permission:button value="查询" name="button" method="search()" style="btn_blue"></permission:button> -->
        <input type="button" onclick="search()" class="btn_blue" value="查询"/>
        </p>
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>版本升级表</strong></div>
		<div class="clearfix mgtb10">
			<permission:button value="新增升级" name="button" method="addUpgrade()" style="btn_blue"></permission:button>
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<thead onselectstart="return false">
			<tr>
				<th width="4%">序号</th>
				<th width="6%">软件名称</th>
				
				<th width="6%">软件版本</th>
				
				<th width="8%">最新版本</th>
				<th width="10%">升级版本</th>
				<th width="6%">升级状态</th>
				<th width="6%">升级包</th>
				<th width="8%">指定用户升级</th>
				<th width="8%">全部用户升级</th>
				<th width="6%">有效状态</th>
				<th width="10%">文件上传进度</th>
				<th width="24%">操作</th>
		    </tr>
		    </thead>
		    <tbody id="dataList"></tbody>
		</table>
    </div>
	<div class="base_page clearfix" id="base_page" onselectstart="return false"></div>
</div>

<!-- 新增升级 -->
<div class="popup jumpBox add dis_none" style="width: 500px;">
    <div class="tit"><span class="fl">新增 </span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
    	<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
    		<tr>
               <td width="25%" align="right"><span style="color: red;">*</span>软件版本：</td>
               <td width="75%" align="left">
				<select name="softType" class="sel" style="width:150px;">
					<option value=""></option>
					<option value="0">标准版</option>
					<option value="1">教师版</option>
					<option value="3">国际版</option>
					<option value="4">昌江版</option>
					<option value="5">立思辰版</option>
					<option value="6">辽宁移动版</option>
					<option value="7">成都佳发版</option>
					<option value="8">微课通</option>
					<option value="9">海康威视版</option>
					<option value="10">海信版</option>
	        	</select>
               </td>
           </tr>
    		
    	
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>最新版本：</td>
               <td width="75%" align="left">
				<select name="newVersion" class="sel" style="width:150px;">
	        	</select>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>升级类型：</td>
               <td align="left">
               	<select name="isleveup" class="sel" style="width:150px;">
		        	<option value="1">正常升级</option>
		        	<option value="2">静默升级</option>
	        	</select>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>升级版本：</td>
               <td align="left">
	        	<div class="upgradeVersion">
               		<p><input type="checkbox" name="allVersion"/>全部版本</p>
               		<div>
               			
	               	</div>
               	</div>
               </td>
           </tr>
           <tr>
		      	<td colspan="2" style="height: 53px;">
		      		<span class="red" id="addMsg"></span><br><br>
		      		<input type="button" name="save" class="btn_blue" value="保存" />
		      		<span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span>
		      		<input type="button" name="close" class="btn_gray" value="取消" />
		      	</td>
           </tr>
       </table>
    </div>
</div>

<!-- 编辑升级 -->
<div class="popup jumpBox edit dis_none" style="width: 500px;">
<input type="hidden" name="softId">
    <div class="tit"><span class="fl">编辑 </span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
    	<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right">最新版本：</td>
               <td width="75%" align="left"><span id="editNewVersionCode"></span></td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>升级类型：</td>
               <td align="left">
               	<select name="isleveup" class="sel" style="width:150px;">
		        	<option value="1">正常升级</option>
		        	<option value="2">静默升级</option>
	        	</select>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>升级版本：</td>
               <td align="left">
	        	<div class="upgradeVersion dis_none">
               		<p><input type="checkbox" name="allVersion"/>全部版本</p>
               		<div>
               			
	               	</div>
               	</div>
               </td>
           </tr>
           <tr>
		      	<td colspan="2" style="height: 53px;">
		      		<span class="red" id="editMsg"></span><br><br>
		      		<input type="button" name="save" class="btn_blue" value="保存" />
		      		<span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span>
		      		<input type="button" name="close" class="btn_gray" value="取消" />
		      	</td>
           </tr>
       </table>
    </div>
</div>

<!--指定用户升级-->
<div class="popup jumpBox specifyUserUpgrades dis_none" style="width: 820px;">
    <div class="tit"><span class="fl">普通用户列表</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
    </div>
</div>


<!-- 指定区域升级 -->
<div>
</div>

<SCRIPT type="text/javascript">
//tab
$("div[name='slide']").tabControl("*[name='slideTag'] a" , "*[name='slideCont']" , '.more');

var $addCkAll = $(".add input[name='allVersion']");
$addCkAll.bind("click",function() {
	$(".add input[name='version']").prop("checked",this.checked);
});
$(".add input[name='version']").live("click",function() {
    var b=$(".add input[name='version']").filter(":checked").length==$(".add input[name='version']").length;
    $addCkAll.prop("checked",b?true:false);
});

function getAddSoftId(){
	var ids = "";
	$.each( $(".add input[name='version']").filter(":checked"), function(i, ckItm){
		ids += "," + ckItm.value;
	});
	if(ids != ""){
		return ids.substring(1);
	}
	return "";
}

var $editCkAll = $(".edit input[name='allVersion']");
$editCkAll.bind("click",function() {
	$(".edit input[name='version']").prop("checked",this.checked);
});
$(".edit input[name='version']").live("click",function() {
    var b=$(".edit input[name='version']").filter(":checked").length==$(".edit input[name='version']").length;
    $editCkAll.prop("checked",b?true:false);
});

function getEditSoftId(){
	var ids = "";
	$.each( $(".edit input[name='version']").filter(":checked"), function(i, ckItm){
		ids += "," + ckItm.value;
	});
	if(ids != ""){
		return ids.substring(1);
	}
	return "";
}

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

$(function(){
	dataList();
	
	
	$(".add select[name='softType']").live("change",function(){
		if($(this).val() != ''){
			$("#addMsg").text('');
			queryNewVersioinCode();
		}else{
			$("#addMsg").text('请选择软件类型');
		}
	});
	
	$(".add select[name='newVersion']").live("change",function(){
		if($(this).val() != ''){
			$("#addMsg").text('');
			queryUpgradeVersionCode();
		}else{
			$("#addMsg").text('请选择最新版本');
		}
	});
	
	$(".add input[name='save']").click(function(){
		
		var softId = $(".add select[name='newVersion']").val();
		
		if(softId==''){
			$("#addMsg").text('请选择最新版本');
			return;
		}
		
		var upgradeSoftId = getAddSoftId();
		if(upgradeSoftId==''){
			$("#addMsg").text('请选择升级版本');
			return;
		}
		
		$("#addMsg").text('');
		
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/version/createUpgradeSoft",
			data : {
				"softId" : softId,
				"isleveup" : $(".add select[name='isleveup']").val(),
				"upgradeSoftId" : upgradeSoftId,
			},
			async : false,
			dataType : 'text',
			success : function(data) {
				if(data=="000000"){
					$(".add").closeBox();
					dataList();
				}
			}
		});
	});
	
	$(".edit input[name='save']").click(function(){
		
		var softId = $(".edit input[name='softId']").val();
		
		var upgradeSoftId = getEditSoftId();
		if(upgradeSoftId==''){
			$("#editMsg").text('请选择升级版本');
			return;
		}
		
		$("#editMsg").text('');
		
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/version/editUpgradeSoft",
			data : {
				"softId" : softId,
				"isleveup" : $(".edit select[name='isleveup']").val(),
				"upgradeSoftId" : upgradeSoftId,
			},
			async : false,
			dataType : 'text',
			success : function(data) {
				if(data=="000000"){
					$(".edit").closeBox();
					dataList();
				}
			}
		});
	});
});

function queryNewVersioinCode(){
	
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/queryAllVersionCode",
		data : {"softType":$(".add select[name='softType']").find("option:selected").val()},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null){
				if(data.list != null && data.list.length > 0){
					var htmlStr = '<option value=""></option>';
					for(var i=0;i<data.list.length;i++){
						var obj = data.list[i];
						htmlStr += '<option value="'+obj.id+'">'+obj.versionCode+'</option>';
					}
					$(".add select[name='newVersion']").html(htmlStr);
				}else{
					$(".add select[name='newVersion']").html('');
				}
			}
		}
	});
	
}

function loadList(){
	var softName = $("#softName").val();
	var versionCode = $("#versionCode").val();
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/upgradeList",
		data : {
			"softName" : softName,
			"versionCode" : versionCode,
			"isleveup": $("#isleveup").val(),
			"upgradeType": $("#upgradeType").val(),
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
						htmlStr += '<td>'+(i+1)+'</td>';
						htmlStr += '<td>'+obj.softName+'</td>';
						
						if(obj.softType=='0'){
							htmlStr += '<td>标准版</td>';
						}else if(obj.softType=='1'){
							htmlStr += '<td>教师版</td>';
						}else if(obj.softType=='3'){
							htmlStr += '<td>国际版</td>';
						}else if(obj.softType=='4'){
							htmlStr += '<td>昌江版</td>';
						}else if(obj.softType=='5'){
							htmlStr += '<td>立思辰版</td>';
						}else if(obj.softType=='6'){
							htmlStr += '<td>辽宁移动版</td>';
						}else if(obj.softType=='7'){
							htmlStr += '<td>成都佳发版</td>';
						}else if(obj.softType=='8'){
							htmlStr += '<td>微课通</td>';
						}else if(obj.softType=='9'){
							htmlStr += '<td>海康威视版</td>';
						}else if(obj.softType=='10'){
							htmlStr += '<td>海信版</td>';
						}
	
						htmlStr += '<td>'+obj.versionCode+'</td>';
						
						if(obj.upgradeVersionCode.length>30){
							htmlStr += '<td title="'+obj.upgradeVersionCode+'">'+obj.upgradeVersionCode.substring(0,30)+'...</td>';
						}else{
							htmlStr += '<td>'+obj.upgradeVersionCode+'</td>';
						}
						
						if(obj.isleveup=='1'){
							htmlStr += '<td>正常升级</td>';
						}else if(obj.isleveup=='2'){
							htmlStr += '<td>静默升级</td>';
						}else{
							htmlStr += '<td>不可升级</td>';
						}
						if(obj.upgradePackageNum==0){
							htmlStr += '<td>未生成</td>';
						}else{
							htmlStr += '<td>已生成</td>';
						}
						
						if(obj.userUpdate=='1'){
							htmlStr += '<td>是</td>';
						}else{
							htmlStr += '<td>否</td>';
						}
						
						if(obj.allUserUpgrade=='0'){
							htmlStr += '<td>否</td>';
						}else if(obj.allUserUpgrade=='1'){
							htmlStr += '<td>是</td>';
						}else{
							htmlStr += '<td></td>';
						}
						htmlStr += '<td>'+(obj.status=='0'?"有效":"无效")+'</td>';
						
						htmlStr += '<td>'+obj.fileOverNum+'/'+obj.fileNums+'</td>';
						//console.log(obj.baiduBosStatus);
						//if(obj.baiduBosStatus==0){
						//	htmlStr += '<td>未上传到百度bos</td>';
						//}else if(obj.baiduBosStatus==1){
						//	htmlStr += '<td>正在上传百度bos</td>';
						//}else if(obj.baiduBosStatus==2){
						//	htmlStr += '<td>已经上传到百度bos</td>';
						//}else{
						//	htmlStr += '<td>未上传到百度bos</td>';
						//}
						
						htmlStr +='<td>';

						htmlStr += '<permission:button value="生成升级包" name="a" method="createUpgradePackage(\\\''+obj.id+'\\\','+obj.upgradePackageNum+')"  style="mgr10"></permission:button>';
						htmlStr += '<permission:button value="上传到百度云" name="a" method="uploadBos(\\\''+obj.id+'\\\')"  style="mgr10"></permission:button>';

						htmlStr += '<permission:button value="编辑" name="a" method="editUpgrade(\\\''+obj.id+'\\\',\\\''+obj.versionCode+'\\\')"  style="mgr10"></permission:button>';
						htmlStr += '<permission:button value="删除" name="a" method="deleteUpgrade(\\\''+obj.id+'\\\')"  style="mgr10"></permission:button>';
						htmlStr += '<permission:button value="指定用户升级" name="a" method="specifyUserUpgrades(\\\''+obj.id+'\\\',\\\''+obj.userUpdateConflict+'\\\',\\\'' + obj.isleveup + '\\\')"  style="mgr10"></permission:button>';
						htmlStr += '<br>';
						htmlStr += '<permission:button value="指定区域升级" name="a" method="specifyAreaUpgrades(\\\''+obj.id+'\\\',\\\''+obj.userUpdateConflict+'\\\',\\\'' + obj.isleveup + '\\\')"  style="mgr10"></permission:button>';
						
						
						htmlStr += '<br>';
						htmlStr += '<permission:button value="全部用户升级" name="a" method="allUserUpgrades(\\\''+obj.id+'\\\',\\\'1\\\',\\\''+obj.allUserUpdateConflict+'\\\',\\\'' + obj.isleveup + '\\\')"  style="mgr10"></permission:button>';
						htmlStr += '<permission:button value="取消全部用户升级" name="a" method="allUserUpgrades(\\\''+obj.id+'\\\',\\\'0\\\')"  style="mgr10"></permission:button>';
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
				$("#dataList").html('<tr><td colspan="10" align="center"><font color="red">暂无数据</font></td></tr>');
				$("#base_page").html('');
			}
			
		}
	});
	
}

function specifyAreaUpgrades(id,userUpdateConflict,isleveup){
	window.location.href='${ctx}/manage/version/specifyAreaUpgrades?softId='+id+'&userUpdateConflict='+userUpdateConflict+'&isleveup='+isleveup;
}

function addUpgrade(){
	$(".add .upgradeVersion").hide();
	$("#addMsg").text('');
	$addCkAll.prop("checked",false);
	$(".add").jumpBox(true);
	
	
	
}


function queryUpgradeVersionCode(){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/queryUpgradeVersionCode",
		data : {
			"versionCode":$(".add select[name='newVersion'] option").filter(":selected").text(),
			"softType":$(".add select[name='softType']").find("option:selected").val()
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
						htmlStr += '<input type="checkbox" name="version" value="'+obj.id+'"/>'+obj.versionCode;
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

var upgradeSoftList = [];
function editUpgrade(softId,versionCode){
	$(".edit .upgradeVersion").hide();
	$("#editMsg").text('');
	$editCkAll.prop("checked",false);
	$(".edit input[name='softId']").val(softId);
	$("#editNewVersionCode").text(versionCode);
	$(".edit").jumpBox(true);
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/queryUpgradeSoftDetail",
		data : {
			"softId":softId
		},
		async : false,
		dataType : 'json',
		success : function(soft) {
			if(soft != null){
				$(".edit select[name='isleveup']").val(soft.isleveup);
			
				upgradeSoftList = soft.upgradeVersionCode.split("；");
				
				queryEditUpgradeVersionCode();
			}
		}
	});
}

function queryEditUpgradeVersionCode(){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/queryUpgradeVersionCode",
		data : {
			"versionCode":$("#editNewVersionCode").text()
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
						for(var j=0;j<upgradeSoftList.length;j++){
							if(obj.versionCode==upgradeSoftList[j]){
								htmlStr += '<input type="checkbox" name="version" checked="checked" value="'+obj.id+'"/>'+obj.versionCode;
								break;
							}
						}
						if(j==upgradeSoftList.length){
							htmlStr += '<input type="checkbox" name="version" value="'+obj.id+'"/>'+obj.versionCode;
						}
						
               			htmlStr += '</span>';
					}
					$(".edit .upgradeVersion div").html(htmlStr);
					$(".edit .upgradeVersion").show();
					
					var b=$(".edit input[name='version']").filter(":checked").length==$(".edit input[name='version']").length;
				    $editCkAll.prop("checked",b?true:false);
				}else{
					$(".edit .upgradeVersion").hide();
					$(".edit .upgradeVersion div").html('');
				}
			}
		}
	});
}

function deleteUpgrade(softId){
	$.confirm("确定要删除选择的数据吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/version/deleteUpgradeSoft",
			data : {"softId":softId},
			async : false,
			dataType : 'text',
			success : function(data) {
				dataList();
			}
		});
	});
}

var softId;
function specifyUserUpgrades(id,userUpdateConflict,isleveup){
	if(userUpdateConflict != ''){
		var text = "以下版本已经存在指定用户的"+(isleveup=='1'?"正常升级":"静默升级")+"，是否取消之前的指定继续操作？<br>"+userUpdateConflict;
		$.confirm(text,function(){
			softId = id;
			$(".specifyUserUpgrades .cont").html('<iframe id="searchList" src="${ctx}/manage/version/initSetUserUpgrade?softId='+id+'" width="100%" height="380" frameborder="0"></iframe>');
			$(".specifyUserUpgrades").jumpBox(true);
		});
	}else{
		softId = id;
		$(".specifyUserUpgrades .cont").html('<iframe id="searchList" src="${ctx}/manage/version/initSetUserUpgrade?softId='+id+'" width="100%" height="380" frameborder="0"></iframe>');
		$(".specifyUserUpgrades").jumpBox(true);
	}
}
/**
 * allUserIds:列表当前页中所有用户的ID
 * userIds：列表当前页中选中的用户ID
 */
function setUserUpgrade(allIds,ids){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/setUserUpgrade",
		data : {"softId" : softId,
				"allIds" : allIds,
				"ids" : ids},
		async : false,
		dataType : 'text',
		success : function(msg) {
			if(msg=='success'){
				$(".specifyUserUpgrades").closeBox();
				dataList();
			}
		}
	});
}

function allUserUpgrades(id,allUserUpgrade,allUserUpdateConflict,isleveup){
	if(allUserUpgrade=='1' && allUserUpdateConflict != ''){
		var text = "以下版本已经存在全部用户的"+(isleveup=='1'?"正常升级":"静默升级")+"，是否取消之前的指定继续操作？<br>"+allUserUpdateConflict;
		$.confirm(text,function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/version/setAllUserUpgrade",
				data : {"id" : id,"allUserUpgrade":allUserUpgrade},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='success'){
						dataList();
					}
				}
			});
		});
	}else{
		var text = '确定要标记为全部用户可以升级吗？';
		if(allUserUpgrade=='0'){
			text = '确定要取消全部用户升级吗？';
		}
		$.confirm(text,function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/version/setAllUserUpgrade",
				data : {"id" : id,"allUserUpgrade":allUserUpgrade},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='success'){
						dataList();
					}
				}
			});
		});
	}
}

function uploadBos(softId){
	setTimeout(function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/version/generateFiles",
			data : {
				"id":softId
			},
			async : false,
			dataType : 'text',
			success : function(msg) {
				
				$.alert("上传云中，请耐心等待！");
				
				//if(msg=='000000'){
				////	$.alertClose();
				//	dataList();
				//}else{
				//	$.alert("操作失败！");
				//}
			}
		});
	},10);
}

function createUpgradePackage(softId,upgradePackageNum){
	if(upgradePackageNum > 0){
		$.alert("已经生成升级包！");
		return;
	}
	$.alert("正在生成升级包，请稍后...");
	setTimeout(function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/version/createUpgradePackage",
			data : {
				"softId":softId
			},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='000000'){
					$.alertClose();
					dataList();
				}else{
					$.alert("操作失败！");
				}
			}
		});
	},10);
	
}
</script>
</body>
</html>