<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通用户管理页面</title>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/managerTaUser/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
	<%-- <input type="hidden" id="area_code" name="area_code" value="${area_code}"/> --%>
	<input type="hidden" id="orderField" name="orderField" value="${orderField}"/>
	<input type="hidden" id="orderState" name="orderState" value="${orderState}"/>
	
    <div class="mgtb10 txq_txtbox clearfix">
    	<div class="clearfix">
	    	<p class="fl mgr10"><span>学校：</span>
	        	<input id="org_name" name="org_name" value="${org_name }" type="text" style="width:150px;" class="inp focus" placeholder="请输入学校名"/>
	        </p>
	        <p class="fl mgr10"><span>用户类型：</span>
	        	<select name="user_type" class="sel" style="width:150px;" id="user_type">
	        		<option value="" <c:if test="${empty user_type}">selected="selected"</c:if>>全部</option>
		        	<option value="0" <c:if test="${user_type=='0'}">selected="selected"</c:if>>学生</option>
		        	<option value="1" <c:if test="${user_type=='1'}">selected="selected"</c:if>>老师</option>
		        	<option value="2" <c:if test="${user_type=='2'}">selected="selected"</c:if>>学校管理员</option>
		        	<option value="3" <c:if test="${user_type=='3'}">selected="selected"</c:if>>机构管理员</option>
		        	<option value="4" <c:if test="${user_type=='4'}">selected="selected"</c:if>>超级管理员</option>
		        	<option value="5" <c:if test="${user_type=='5'}">selected="selected"</c:if>>家长</option>
	        	</select>
	        </p>
	        <p class="fl mgr10"><span>用户名：</span><input id="user_account" name="user_account" value="${user_account }" type="text" style="width:150px;" class="inp focus" placeholder="请输入用户名"/></p>
	        <p class="fl mgr10"><span>用户来源：</span>
	        	<select id="platform_id" name="platform_id" class="sel" style="width:150px;">
					<option value="" <c:if test="${empty platform_id}">selected="selected"</c:if>>全部</option>
					<c:forEach items="${platformList}" var="p" varStatus="st">
					<option value="${p.platform_code }" <c:if test="${p.platform_code==platform_id}">selected="selected"</c:if>>${p.platform_name }</option>
					</c:forEach>
				</select>
	        </p>
	        <p class="fl"><input type="button" onclick="search()" class="btn_blue" value="查询"/></p>
        </div>
       
    </div>
    
    <div class="mgtb10">
		<div class="base_title"><strong>普通用户列表</strong></div>

		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="禁用" onclick="enableAndDisable('1')"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="启用" onclick="enableAndDisable('0')"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="删除" onclick="del()"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="导出" onclick="javascript:exportexcel();"/>
			<!-- 
			<input type="button" class="btn_blue" value="导出区域月报表" onclick="javascript:exportAreaCountExcel();"/>
			<input type="button" class="btn_blue" value="导出学校月报表" onclick="javascript:exportOrgCountExcel();"/>
			
			<input type="button" class="btn_blue" value="导出区域活跃度月报表" onclick="javascript:exportAreaActivityExcel();"/>
			<input type="button" class="btn_blue" value="导出学校活跃度月报表" onclick="javascript:exportOrgActivityExcel();"/>
		 	-->
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="4%"><input type="checkbox" name="ckAll"></th>
				<th width="12%">用户名</th>
				<th width="7%">姓名</th>
				<th width="5%">性别</th>
				<th width="18%" class="order" onclick="orderBy('org_id')">
					<span <c:if test="${orderField=='org_id' }">class="selected"</c:if>>
						学校
						<c:choose>
							<c:when test="${orderField=='org_id' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='org_id' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="6%">用户类型</th>
				<th width="8%" class="order" onclick="orderBy('platform_id')">
					<span <c:if test="${orderField=='platform_id' }">class="selected"</c:if>>
						用户来源
						<c:choose>
							<c:when test="${orderField=='platform_id' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='platform_id' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="14%" class="order" onclick="orderBy('create_time')">
					<span <c:if test="${orderField=='create_time' }">class="selected"</c:if>>
						创建时间
						<c:choose>
							<c:when test="${orderField=='create_time' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='create_time' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="14%" class="order" onclick="orderBy('last_time')">
					<span <c:if test="${orderField=='last_time' }">class="selected"</c:if>>
						最近登录时间
						<c:choose>
							<c:when test="${orderField=='last_time' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='last_time' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="8%" class="order" onclick="orderBy('login_count')">
					<span <c:if test="${orderField=='login_count' }">class="selected"</c:if>>
						登录次数
						<c:choose>
							<c:when test="${orderField=='login_count' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='login_count' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<!-- <th width="10%">操作</th> -->
		    </tr>
		    <c:if test="${fn:length(list) < 1}">
		    	<tr>
		    		<td colspan="11" style="color: red">暂无数据</td>
		    	</tr>
		    </c:if>
		    <c:if test="${fn:length(list) >= 1}">
		    	<c:forEach items="${list}" var="obj" varStatus="status">
			    <tr>
					<td><input type="checkbox" name="ckItm" value="${obj.id}"></td>
					<td><a href="javascript:void(0);" onclick="detail('${obj.id}')">${obj.user_account }</a></td>
					<td>${obj.real_name }</td>
					<td>
						<c:if test="${obj.gender=='0' }">女</c:if>
						<c:if test="${obj.gender=='1' }">男</c:if>
					</td>
					<td title="${obj.org_name }">
						<script type="text/javascript">
						var org_name = '<c:out value="${obj.org_name}"/>';
						if(org_name.length > 17){
							org_name = org_name.substring(0,17)+"...";
						}
						document.write(org_name);
						</script>
					</td>
					<td>
						<c:if test="${obj.user_type=='0' }">学生</c:if>
						<c:if test="${obj.user_type=='1' }">老师</c:if>
						<c:if test="${obj.user_type=='2' }">学校管理员</c:if>
						<c:if test="${obj.user_type=='3' }">机构管理员</c:if>
						<c:if test="${obj.user_type=='4' }">超级管理员</c:if>
						<c:if test="${obj.user_type=='5' }">家长</c:if>
					</td>
					<td>${obj.platformName }</td>
					<td><fmt:formatDate value="${obj.create_time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${obj.last_time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${obj.login_count }</td>
					<!-- <td>
						
						<c:if test="${obj.status=='0' }"><a href="javascript:void(0);" class="mgr10" onclick="enableAndDisable('1','${obj.id}')">禁用</a></c:if>
						<c:if test="${obj.status=='1' }"><a href="javascript:void(0);" class="mgr10" onclick="enableAndDisable('0','${obj.id}')">启用</a></c:if>
						 
					</td>-->
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
				action="${ctx}/managerTaUser/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<!--详情-->
<div class="popup jumpBox detail dis_none" style="width: 600px;">
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">详情</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
            <tr>
               <td width="15%" align="right">用户名：</td>
               <td width="18%" align="left" style="padding: 0px">
        			<span name="user_account"></span>
               </td>
               <td width="15%" align="right">姓名：</td>
               <td width="18%" align="left" style="padding: 0px;">
        			<span name="real_name"></span>
               </td>
               <td width="15%"  align="right">性别：</td>
               <td width="19%"  align="left" style="padding: 0px">
        			<span name="gender"></span>
        		</td>
           </tr>
           <tr>
               <td align="right">用户类型：</td>
               <td align="left" style="padding: 0px">
        			<span name="user_type"></span>
               </td>
               <td align="right">来源平台编码：</td>
               <td align="left" style="padding: 0px">
               		<span name="platform_id"></span>
               </td>
               <td align="right">来源平台名称：</td>
               <td align="left" style="padding: 0px">
        			<span name="platform_name"></span>
               </td>
           </tr>
           <tr>
               <td align="right">区域名称：</td>
               <td align="left" style="padding: 0px">
               		<span name="area_name"></span>
               </td>
               <td align="right">学校：</td>
               <td align="left" style="padding: 0px"">
        			<span name="org_name"></span>
               </td>
                <td align="right">创建时间：</td>
               <td align="left" style="padding: 0px">
               		<span name="create_time"></span>
               </td>
           </tr>
            <tr>
               <td align="right">开始使用时间：</td>
               <td align="left" style="padding: 0px">
        			<span name="use_time"></span>
               </td>
               <td align="right">最近使用时间：</td>
               <td align="left" style="padding: 0px">
        			<span name="last_time"></span>
               </td>
               <td align="right">登录次数：</td>
               <td align="left" style="padding: 0px">
        			<span name="login_count"></span>
               </td>
            </tr>
            <tr>
               <td align="right">手机号：</td>
               <td align="left" style="padding: 0px">
        			<span name="phone_number"></span>
               </td>
               <td align="right">邮箱：</td>
               <td align="left" style="padding: 0px"">
        			<span name="email"></span>
               </td>
               <td align="right">生日：</td>
               <td align="left" style="padding: 0px">
        			<span name="birthday"></span>
               </td>
           </tr>
           <tr>
               <td align="right">地址：</td>
               <td align="left" style="padding: 0px" colspan="5">
        			<span name="address"></span>
               </td>
           </tr>
           <tr>
               <td colspan="6" align="right" style="padding: 0px;text-align: center;">
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



//查询
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
//状态修改
function enableAndDisable(status,id){
	var ids;
	if(id == null){
		ids = getCheckValue();
	}else{
		ids = id;
	}
	if(ids==""){
		$.alert("请至少选择一项！");
		return;
	}else{
		$.confirm("确认要进行该操作吗？",function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/managerTaUser/enableAndDisable",
				data : {"ids" : ids,"status" : status},
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
}
//删除
function del(){
	var ids = getCheckValue();
	if(ids==""){
		$.alert("请至少选择一项！");
		return;
	}
	$.confirm("确定要删除选中的用户信息吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/managerTaUser/delete",
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
//导入
function exportexcel(){
	var provinceCode = $("#province").val();
	var cityCode = $("#city").val();
	var areaCode = $("#area").val();
	var org_name =  $("#org_name").val();
	var user_type = $("#user_type").val();
	var status = $("#status").val();
	var user_account = $("#user_account").val();
	var platform_id = $("#platform_id").val();
	window.location.href='${ctx}/managerTaUser/exportexcel?provinceCode='+provinceCode+'&cityCode='+cityCode+'&areaCode='+areaCode
			+'&org_name='+org_name+'&user_type='+user_type+'&status='+status+'&user_account='+user_account+'&platform_id='+platform_id;
}

function exportAreaCountExcel(){
	window.location.href='${ctx}/managerTaUser/exportAreaCountExcel';
}


function exportOrgCountExcel(){
	window.location.href='${ctx}/managerTaUser/exportOrgCuntExcel';
}


function exportAreaActivityExcel(){
	window.location.href='${ctx}/managerTaUser/exportAreaActivityExcel';
}


function exportOrgActivityExcel(){
	window.location.href='${ctx}/managerTaUser/exportOrgActivityExcel';
}


function detail(id){
	$.ajax({
		type : "POST",
		url : "${ctx}/managerTaUser/detail",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null){
				var obj = data.user;
				$(".detail span[name='user_account']").text(obj.user_account);
				$(".detail span[name='real_name']").text(obj.real_name);
				if(obj.gender=='0'){
					$(".detail span[name='gender']").text('女');
				}else if(obj.gender=='1'){
					$(".detail span[name='gender']").text('男');
				}
				
				var user_type;
				if(obj.user_type=='0'){
					user_type='学生';
				}else if(obj.user_type=='1'){
					user_type='老师';
				}else if(obj.user_type=='2'){
					user_type='学校管理员';
				}else if(obj.user_type=='3'){
					user_type='机构管理员';
				}else if(obj.user_type=='4'){
					user_type='超级管理员';
				}else if(obj.user_type=='5'){
					user_type='家长';
				}
				
				$(".detail span[name='user_type']").text(user_type);
				$(".detail span[name='platform_id']").text(obj.platform_id);
				$(".detail span[name='platform_name']").text(obj.platform_name);
				$(".detail span[name='area_name']").text(obj.area_name);
				$(".detail span[name='org_name']").text(obj.org_name);
				$(".detail span[name='create_time']").text(data.create_time);
				$(".detail span[name='use_time']").text(data.use_time);
				$(".detail span[name='last_time']").text(data.last_time);
				$(".detail span[name='login_count']").text(obj.login_count);
				$(".detail span[name='phone_number']").text(obj.phone_number);
				$(".detail span[name='email']").text(obj.email);
				$(".detail span[name='birthday']").text(obj.birthday);
				$(".detail span[name='address']").text(obj.address);
				
				$(".detail").jumpBox(true);
			}
		}
	});
}

$(function(){
	var province = '${provinceCode}';
	
	if(province==''){
		$('#province').html(queryArea(1));	
	}
	
	$("#province").bind("change", function(){
		$('#area').html('<option value="">全部</option>');
		if($("#province").val()==''){
			$('#city').html('<option value="">全部</option>');
			return;	
		}
		
		$('#city').html(queryArea(2,$("#province").val()));
	});
	
	$("#city").bind("change", function(){
		if($("#city").val()==''){
			$('#org').html('<option value="">全部</option>');
			return;		
		}
		$('#org').html(queryOrg($("#city").val()));
	}); 
});


function queryArea(levelId,parentId){
	var htmlStr = '<option value="">全部</option>';
	$.ajax({
		type : "POST",
		url : "${ctx}/baseProperty/queryArea",
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

function orderBy(field){
	if(field==$('#orderField').val()){
		$('#orderState').val($('#orderState').val()=='desc'?'asc':'desc');
	}else{
		$('#orderField').val(field);
		$('#orderState').val('desc');
	}
	$("#search_type").val(0);
	$("#pageForm").submit();
}
//区域下拉框area_code_sel
/* function areaCode(){
	$.ajax({
		type : "POST",
		url : "${ctx}/orgManage/areaList",
		data : {},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null){
				var htmlSel = '<option value="" >全部</option>';
				var areaVal = $("#area_code").val();
				for(var i=0;i<data.length;i++){
					if(areaVal == data[i].organization_code){
						htmlSel += '<option value="'+data[i].organization_code+'" selected="selected">'+data[i].organization_name+'</option>';
					}else{
						htmlSel += '<option value="'+data[i].organization_code+'" >'+data[i].organization_name+'</option>';
					}
				}
				$("#area_code_sel").html(htmlSel);
			}
		}
	});
}

areaCode();
function areaSel(){
	$("#area_code").val($("#area_code_sel").val());
} */
</script>
</body>
</html>