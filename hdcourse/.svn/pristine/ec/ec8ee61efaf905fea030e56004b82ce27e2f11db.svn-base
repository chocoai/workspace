<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify/jquery.uploadify.min.js"></script>
<style type="text/css">
.progress{ height: 4px; font-size: 0; line-height: 4px; background: orange; width: 0;}
.num{ margin-left: 20px;}
#file-list img{cursor: pointer;}
</style>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/licenceCode/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
	
    <div class="mgtb10 txq_txtbox clearfix">
	    <p class="fl mgr10"><span>授权码：</span><input name="licence_code" value="${licence_code }" type="text" style="width:100px;" class="inp focus"/></p>
       	<p class="fl mgr10"><span>批号：</span><input name="batch_number" value="${batch_number }" type="text" style="width:100px;" class="inp focus"/></p>
       	<p class="fl mgr10"><span>合同编号：</span><input name="contract_number" value="${contract_numbercontract_number }" type="text" style="width:100px;" class="inp focus"/></p>
       	<p class="fl mgr10"><span>绑定状态：</span>
       		<select  name="status2" id="status2" class="sel" style="width:130px;">
       			<option value="" selected="selected">全部</option>
       			<option value="0" <c:if test="${status2=='0'}">selected="selected"</c:if>>未绑定</option>
       			<option value="1" <c:if test="${status2=='1'}">selected="selected"</c:if>>已绑定</option>
       			<option value="3" <c:if test="${status2=='3'}">selected="selected"</c:if>>注销</option>
       		</select>
       	</p>
        <p class="fl">
        <input type="button" onclick="search()" class="btn_blue" value="查询"/>
        </p>
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>软件列表</strong></div>

		<div class="clearfix mgtb10">
			<!-- <permission:button value="新增版本" name="button" method="add()"  style="btn_blue mgr10"></permission:button>
			<permission:button value="删除" name="button" method="del()"  style="btn_blue"></permission:button> -->
			<input type="button" onclick="cancel()" class="btn_blue mgr10" value="注销"/>			
			<input type="button" onclick="del()" class="btn_blue" value="删除"/>
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="4%"><input type="checkbox" name="ckAll"></th>
				<th width="10%">激活码</th>
				<th width="6%">连接平板数</th>
				<th width="6%">是否绑定</th>
				<th width="16%">授权期限</th>
				<th width="6%">有效期(天)</th>
				<th width="6%">录入时间</th>
				<th width="6%">互动课堂</th>
				<th width="6%">学生数码笔</th>
				<th width="6%">答题器</th>
				<th width="5%">创建ID</th>
				<th width="5%">修改ID</th>
				<th width="6%">合同编号</th>
				<th width="6%">批次号</th>
				<th width="6%">操作</th>
		    </tr>
		    <c:forEach items="${licenceCodeList}" var="g" varStatus="status">
			    <tr>
					<tr>
					<td><input type="checkbox" name="ckItm" value="${g.id}"></td>
					<td>${g.licence_code }</td>
					<td>${g.max_use_count }</td>
					<td>
						<c:if test="${g.status=='0' }">未绑定</c:if>
						<c:if test="${g.status=='1' }"><strong>已绑定</strong></c:if>
						<c:if test="${g.status=='3' }">已注销</c:if>
					</td> 
					<td>
						<c:if test="${empty g.start_time}">未绑定
						<fmt:formatDate value="${g.start_time }" pattern="yyyy-MM-dd" />  至  
	          			<c:choose>
		            		<c:when test="${empty g.end_time }">永久</c:when>
		            		<c:otherwise><fmt:formatDate value="${g.end_time }" pattern="yyyy-MM-dd" /></c:otherwise>
	            		</c:choose>
	            		</c:if>
					</td>
					<td>
					${g.limit_day}
					</td>
					<td>
						<fmt:formatDate value="${g.create_time }" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					
					<td>
						<c:if test="${empty g.ebp_auth }">是</c:if>
						<c:if test="${g.ebp_auth=='1' }">是</c:if>
						<c:if test="${g.ebp_auth=='0' }">否</c:if>
					</td>
					
					<td>
						<c:if test="${empty g.sdp_auth }">是</c:if>
						<c:if test="${g.sdp_auth=='1' }">是</c:if>
						<c:if test="${g.sdp_auth=='0' }">否</c:if>
					</td>
					
					<td>
						<c:if test="${empty g.am_auth }">是</c:if>
						<c:if test="${g.am_auth=='1' }">是</c:if>
						<c:if test="${g.am_auth=='0' }">否</c:if>
					</td>
					<td>${g.creator }</td>
					<td>${g.last_editor }</td>
					<td>
						<a href="javascript:void(0)" onclick="searchNumber('${g.contract_number}')">${g.contract_number}</a>
					</td>
					
					<td>
						<a href="javascript:void(0)" onclick="searchBatch('${g.batch_number}')">${g.batch_number}</a>
					</td>
					
					<td>
						<input type="button" style="font-size: 13px;width: 80px;height: 30px; background-color: #33cc33; border-radius: 5px;" value="激活码管理"  onclick="edit('${g.id}')">
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
				action="${ctx}/manage/licenceCode/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>


<!--详情-->
<div class="popup jumpBox detail dis_none">
    <div class="tit"><span class="fl">编辑激活码</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right">激活码：</td>
               <td width="75%" align="left">
	               <input type="hidden" id="id"/>
	               <span id="licence_code"></span>
               </td>
           </tr>
           <tr>
               <td align="right">mac地址：</td>
               <td align="left"><span id="mac_card_info"></span></td>
           </tr>
           <tr>
               <td align="right">cpu信息：</td>
               <td align="left"><span id="cpu_info"></span></td>
           </tr>
           <tr>
               <td align="right">硬盘信息：</td>
               <td align="left"><span id="harddisk_info"></span></td>
           </tr>
           <tr>
               <td align="right">有效期：</td>
               <td align="left"><span id="limit_day"></span>
               <!-- <td align="left"><span id="start_time"></span> 至 <span id="end_time"></span></td> -->
           </tr>
           <tr>
               <td align="right">生成时间：</td>
               <td align="left"><span id="create_time"></span></td>
           </tr>
           <tr>
               <td align="right">状态：</td>
               <td align="left">
			               未绑定&nbsp;<input type="radio" onchange="selectNo(this.value)" name="status" id="radioBox_0" value="0"/>&nbsp;&nbsp;&nbsp;&nbsp;
			               绑定&nbsp;<input type="radio" onchange="selectNo(this.value)" name="status" id="radioBox_1" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;
			               注销&nbsp;<input type="radio" onchange="selectNo(this.value)" name="status" id="radioBox_3" value="3"/>
			        <span class="red"></span>
		      </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>接入平板数：</td>
               <td align="left">
	               <input type="text" name="max_use_count" value='' class="inp focus"/>
	               <span class="red"></span>
               </td>
           </tr>
           
           <tr>
               <td align="right"><span style="color: red;">*</span>授权产品：</td>
			   <td align="left">
               	<input type="checkbox" name="ebp_auth" class="inp focus"/>电子书包
               	<input type="checkbox" name="sdp_auth" class="inp focus"/>学生数码笔
               	<input type="checkbox" name="am_auth" class="inp focus"/>答题器
               	<span class="red"></span>
               </td>
           </tr>
           
           <tr>
           	<td colspan="4" style="height: 53px;">
           		<input type="button" name="update" class="btn_blue" value="保存" />
           		<span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span>
           		<input type="button" name="close" class="btn_gray" value="取消" />
           	</td>
           </tr>
       </table>
    </div>
</div>


<script>
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
//注销
function cancel(){
	var ids = "";
	$.each( $ckItm, function(i, ckItm){
		if(ckItm.checked){
			ids += "," + ckItm.value;
		}
	});

	if(ids != ""){
		ids = ids.substring(1);
	}

	$.confirm("确定要注销吗?注销后将不能恢复！",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/licenceCode/cancel",
			data : {"ids" : ids},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='000000'){
					$("#pageForm").submit();
				}
			}
		});
	});
	
}
function edit(id){
	$(".detail .red").text('');
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/softLicence/detailEbpSoftLicence",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(soft) {
			if(soft != null && soft !=''){
				$("#id").val(soft.id);
				$("#licence_code").text(soft.licence_code);
				$("#mac_card_info").text(soft.mac_card_info);
				$("#cpu_info").text(soft.cpu_info);
				$("#harddisk_info").text(soft.harddisk_info);
				$("#create_time").text(toDate(soft.create_time,"yyyy-MM-dd hh:mm:ss"));
				$("#limit_day").text(soft.limit_day);
				$("#start_time").text(toDate(soft.start_time,"yyyy-MM-dd"));
				$("#end_time").text(soft.end_time ? toDate(soft.end_time,"yyyy-MM-dd"):'永久');
				$(".detail input[name='max_use_count']").val(soft.max_use_count);
				//$("input[name='status']").eq(soft.status).attr("checked","checked");
				
				
				if(soft.ebp_auth=='1'){
					$(".detail input[name='ebp_auth']").attr("checked","checked");
				}else if(soft.ebp_auth=='0'){
					$(".detail input[name='ebp_auth']").attr("checked",false);
				}else{
					$(".detail input[name='ebp_auth']").attr("checked","checked");
				}
				
				if(soft.sdp_auth=='1') {
					$(".detail input[name='sdp_auth']").attr("checked","checked");
				} else if(soft.sdp_auth=='0') {
					$(".detail input[name='sdp_auth']").attr("checked",false);
				} else {
					$(".detail input[name='sdp_auth']").attr("checked","checked");
				}
				
				if(soft.am_auth=='1') {
					$(".detail input[name='am_auth']").attr("checked","checked");
				} else if(soft.am_auth=='0') {
					$(".detail input[name='am_auth']").attr("checked",false);
				} else {
					$(".detail input[name='am_auth']").attr("checked","checked");
				}
				
				
				document.getElementById('radioBox_'+soft.status).checked=true;
				$(".detail").jumpBox(true);
			}
		}
	});
}
function searchNumber(contract_number){
	$("input[name='contract_number']").val(contract_number);
	$("#pageForm").submit();
}
	
	function search(){
		$("#pageForm").submit();
	}
	
	function searchBatch(batchNumber){
		$("input[name='batch_number']").val(batchNumber);
		$("#pageForm").submit();
	}
	
	//转化JSON日期格式
	Date.prototype.format = function(format) {
	    var o = {
	        "M+" : this.getMonth() + 1,
	        "d+" : this.getDate(),
	        "h+" : this.getHours(),
	        "m+" : this.getMinutes(),
	        "s+" : this.getSeconds(),
	        "q+" : Math.floor((this.getMonth() + 3) / 3),
	        "S" : this.getMilliseconds()
	    };
	    if (/(y+)/.test(format)) {
	            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4- RegExp.$1.length));
	        }
	    for (var k in o) {
	        if (new RegExp("(" + k + ")").test(format)){
	            format = format.replace(RegExp.$1, RegExp.$1.length == 1? o[k]:("00" + o[k]).substr(("" + o[k]).length));
	        }
	    }
	    return format;
	    
	};
	//转化JSON日期格式
	function toDate(objDate, format) {
		if(!objDate) return "";
	    var date = new Date();
	    date.setTime(objDate.time);
	    date.setHours(objDate.hours);
	    date.setMinutes(objDate.minutes);
	    date.setSeconds(objDate.seconds);
	    return date.format(format);
	}
	function GetUrlParaString(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
	    var r = window.location.search.substr(1).match(reg);
	    if (r!=null) return decodeURI(r[2]); return null;
	 }
	function getRadioSelectVal(name){  
		var temp = document.getElementsByName(name);  
		var intHot ="";
		for(var i=0;i<temp.length;i++)  
		{     
			if(temp[i].checked)
			{
				intHot = temp[i].value;
				break; 
			}
		} 
		return intHot;
	}
	
	var idss;
	$(".add_edit input[name='save']").click(function(){
		debugger
		var createCount=$(".add_edit input[name='createCount']").val();
		var maxUseCount=$(".add_edit input[name='max_use_count']").val();
		var startTime=$(".add_edit input[name='start_time']").val();
		var endTime=$(".add_edit input[name='end_time']").val();
		var orgId='';
		
		var ebpAuth;
		var sdpAuth;
		var amAuth;
		
		if($(".add_edit input[name='ebp_auth']").attr("checked")){
			ebpAuth=1;
		}else{
			ebpAuth=0;
		}
		
		if($(".add_edit input[name='sdp_auth']").attr("checked")){
			sdpAuth=1;
		}else{
			sdpAuth=0;
		}
		
		if($(".add_edit input[name='am_auth']").attr("checked")){
			amAuth=1;
		}else{
			amAuth=0;
		}

		
		if($.trim(createCount)==''){
			$(".add_edit input[name='createCount']").siblings(".red").text("请填生成数量");
			return;
		}
		if($.trim(maxUseCount)==''){
			$(".add_edit input[name='max_use_count']").siblings(".red").text("请填写接入平板数");
			return;
		}
//		if(limit_day==0 ){
//			$(".add_edit input[name='start_time']").siblings(".red").text("请填写授权有效期");
//			return;
//		}
		
		$(this).hide();
		$(this).next().show();
		$(this).next().next().hide();
		
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/softLicence/saveEbpSoftLicence",
				data : {
					"createCount" : createCount,
					"org_id" : orgId,
					"max_use_count"  : maxUseCount,
					"ebp_auth": ebpAuth,
					"sdp_auth": sdpAuth,
					"am_auth": amAuth
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					
					var url="exportSearchExcel2?ids="+msg;
					document.getElementById("ifile").src=url;
					$(".add_edit").closeBox();
					
					
					
					//$("#pageForm").submit();
					//if(msg=='success'){
						//$(".add_edit").closeBox();
						//var txt = "激活码新增完成，是否刷新列表并下载授权码？";
						//$.confirm(txt,function(){
							
						//	$("#pageForm").submit();
						//});
					//}
				}
			});
			
		}, 1);
		
		//setTimeout(function(){
		//	$("#pageForm").submit();
		//}, 5000);
		
		//alert(1)
		//alert(idss)
		//window.location.href='${ctx}/manage/licenceOrg/exportExcel?ids='+ids;
	});
	
	$(".detail input[name='update']").click(function(){
		var id = $("#id").val();
		var maxUseCount=$(".detail input[name='max_use_count']").val();
		var status=getRadioSelectVal("status");
		if($.trim(maxUseCount)==''){
			$(".detail input[name='max_use_count']").siblings(".red").text("请填写接入平板数");
			return;
		}
		$(this).hide();
		$(this).next().show();
		$(this).next().next().hide();
		
		var ebpAuth=0;
		var sdpAuth=0;
		var amAuth=0;
		
		if($(".detail input[name='ebp_auth']").attr("checked")){
			ebpAuth=1;
		}
		
		if($(".detail input[name='sdp_auth']").attr("checked")){
			sdpAuth=1;
		}
		
		if($(".detail input[name='am_auth']").attr("checked")){
			amAuth=1;
		}
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/softLicence/saveEbpSoftLicence",
				data : {
					"id" : id,
					"status" : status,
					"max_use_count"  : maxUseCount,
					"ebp_auth": ebpAuth,
					"sdp_auth": sdpAuth,
					"am_auth": amAuth
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='success'){
						$(".detail").closeBox();
						var txt = "激活码修改完成，是否刷新列表？";
						$.confirm(txt,function(){
							$("#pageForm").submit();
						});
					}
				}
			});
		}, 1);

	});
	

</script>
</body>
</html>