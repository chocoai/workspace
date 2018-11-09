<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>版本管理页面</title>
<style type="text/css">
	.button_soft_licence_list {font-size: 13px;width: 80px;height: 30px; background-color: #33cc33; border-radius: 5px; color: white}
	.button_soft_licence_detail{font-size: 13px;width: 80px;height: 30px; background-color: #0066cc; border-radius: 5px; color: white}
	.button_soft_licence_delete{font-size: 13px;width: 60px;height: 30px; background-color: red; border-radius: 5px; color: white}
	
</style>
</head>
<body>
<div class="mg15 txq_main">
<h2 class="adminTit bor_bottom"><em>授权单位列表</em><span class="fr"></span></h2>
	<div class="bo_title mgt15">
        <h3><i class="ico ico1"></i>数据指标</h3>
    </div>
    <div class="code_table data_index">
        <table class="mod_table t_c" width="100%" style="border-collapse:collapse;">
            <tr>
                <th width="50%" colspan="3">本月新增</th>
               
            </tr>
            <tr>
                <td width="16%">
                	<p>新增授权总数</p>
                    <p class="num"><span class="cf00">${currMonthAddCount}</span></p>
                    <p class="incr">授权总数<em>${licenceCount}</em><span class="fr">6<i class="up"></i></span></p>
                    <p class="info"></p>
                </td>
                <td width="16%">
                	<p>新增已使用授权数</p>
                    <p class="num"><span class="c00f">${currMonthUseCount}</span></p>
                    <p class="incr">已使用授权总数<em>${licenceUseCount}</em><span class="fr">10<i class="down"></i></span></p>
                    <p class="info"></p>
                </td>
                <td  width="16%">
                	<p>新增授权学校数</p>
                	<p class="num"><span class="c000">${currMonthAddOrg}</span></p>
                    <p class="incr">授权学校总数<em>${licenceOrgCount}</em><span class="fr">18<i class="down"></i></span></p>
                    <p class="info"></p>
                </td>
            </tr>
        </table>
    </div>
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/overseasLicenceOrg/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
    <div class="mgtb10 txq_txtbox clearfix">
       	<p class="fl mgr10"><span>学校：</span><input name="school_name" value="${school_name }" type="text" style="width:150px;" class="inp focus"/></p>
       	<p class="fl"><permission:button value="查询" name="button" method="search()"  style="btn_blue"></permission:button></p>
    </div>
    <div class="mgtb10">
		<div class="base_title">
			<strong>授权单位列表</strong>
		</div>
		
		<div class="clearfix mgtb10">
			<permission:button value="删除" name="button" method="delList()"  style="btn_blue mgr10"></permission:button> 
			<permission:button value="新增" name="button" method="add()"  style="btn_blue"></permission:button>
		</div>
		
		<table id="lfy_checkbox" class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="4%"><input type="checkbox" name="ckAll"></th>
				<th width="30%">授权学校</th>
				<th width="26%">创建时间</th>
				<th width="40%">操作</th>
		    </tr>
		    <c:if test="${fn:length(licenceOrgList)<1}">
		    	<tr>
		    		<td colspan="4" style="color: red">暂无数据</td>
		    	</tr>
		    </c:if>
		    <c:if test="${fn:length(licenceOrgList)>=1}">
		    	<c:forEach items="${licenceOrgList}" var="licenceOrg" varStatus="status">
			    	<tr>
						<td><input type="checkbox" name="id" value="${licenceOrg.id}"></td>
						<td>${licenceOrg.school_name }</td>
						<td><fmt:formatDate value="${licenceOrg.create_time }" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>	
							<permission:button value="授权管理" name="button" method="softLicenceList('${licenceOrg.id}','${licenceOrg.school_name}')"  style="button_soft_licence_list mgr10"></permission:button>
							<permission:button value="详细信息" name="button" method="detail('${licenceOrg.id}')"  style="button_soft_licence_detail mgr10"></permission:button>
							<permission:button value="删除" name="button" method="delOne('${licenceOrg.id}')"  style="button_soft_licence_delete"></permission:button>
						</td>
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
				action="${ctx}/manage/overseasLicenceOrg/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<!--详情 修改-->

<div class="popup jumpBox detail dis_none">
    <div class="tit"><span class="fl">修改增授权单位</span><span class="close" id="closeDetail" name="close">X</span></div>
    <div class="cont pd10">
    <form id="updateForm">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr bordercolor="white">
           		<td style="font-size: 14px;font-weight: bold;" colspan="4" align="left">联系人信息</td>
           </tr>
           <tr>
               <td width="90px" align="right"><span style="color: red;">*</span>姓名：</td>
               <td width="220px" align="left" style="padding: 0px">
               		<input type="hidden" id="id"/>
               		<input id="contacts_name" type="text" style="height: 100%;width: 55%;"/>
               		<span class="red"></span>
               </td>
               <td width="100" align="right">性别：</td>
               <td width="150px" align="left" style="padding: 0px">
               		<select id="contacts_sex" class="sel" style="width:160px">
		        		<option value="0" id="nan">男</option>
		        		<option value="1" id="nv">女</option>
	        		</select>
               </td>
               
           </tr>
           <tr>
               <td align="right">手机：</td>
               <td align="left" style="padding: 0px"><input id="contacts_cellphone" type="text" style="height: 100%;width: 55%;"/></td>
               <td align="right">电话：</td>
               <td align="left" style="padding: 0px"><input id="contacts_phone" type="text" style="height: 100%;width: 55%;"/></td>
           </tr>
           <tr>
               <td align="right">QQ：</td>
               <td align="left" style="padding: 0px"><input id="contacts_qq" type="text" style="height: 100%;width: 55%;"/></td>
               <td align="right">电子邮箱：</td>
               <td align="left" style="padding: 0px"><input id="contacts_email" type="text" style="height: 100%;width: 55%;"/></td>
           </tr>
           <tr>
               <td align="right">联系地址：</td>
               <td align="left" colspan="3" style="padding: 0px"><input id="contacts_address" type="text" style="height: 100%;width: 61%;"/></td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>授权学校：</td>
               <td align="left" colspan="3" style="padding: 0px"><input id="school_name" type="text" style="height: 100%;width: 61%;"/><span class="red"></span></td>
           </tr>
           <tr>
               <td align="right">学校地址：</td>
               <td align="left" colspan="3" style="padding: 0px"><input id="school_address" type="text" style="height: 100%;width: 61%;"/></td>
           </tr>
           <tr>
           		<td style="font-size: 14px;font-weight: bold;" colspan="4" align="left">经办人信息</td>
           </tr>
           <tr>
               <td width="90px" align="right"><span style="color: red;">*</span>真实姓名：</td>
               <td width="220px" align="left" style="padding: 0px"><input id="managers_name" type="text" style="height: 100%;width: 55%;" /><span class="red"></span></td>
               <td width="100px" align="right">手机号：</td>
               <td width="150px" align="left" style="padding: 0px"><input id="managers_cellphone" type="text" style="height: 100%;width: 55%;"/></td>
           </tr>
           <tr>
               <td align="right">电子邮箱：</td>
               <td align="left" style="padding: 0px"><input id="managers_email" type="text" style="height: 100%;width: 55%;"/></td>
               <td align="right"><span style="color: red;">*</span>合同编号：</td>
               <td align="left" style="padding: 0px"><input id="contract_number" type="text" style="height: 100%;width: 55%;"/><span class="red"></span></td>
           </tr>
           <tr>
           	   <td align="right">创建时间：</td>
               <td align="left" style="padding: 0px"><input type="text" style="height: 100%;width: 100%;" id="create_time" disabled="disabled"/></td>
               <td colspan="2" align="right" style="padding: 0px">
               		<input type="button" style="font-size: 13px;width: 60px;height: 30px; background-color: red; border-radius: 5px; color: white" value="取消" onclick="javascript:$('#closeDetail').click();"/>
               		<span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span>
               		<input id="updateSubmit" type="button" style="font-size: 13px;width: 60px;height: 30px; background-color: #0066cc; border-radius: 5px; color: white" value="保存"/>
               </td>
           </tr>
         	 
       </table>
    </form>
    </div>
</div>



<!--新增  -->
<div class="popup jumpBox add_edit dis_none">
    <div class="tit"><span class="fl">新增授权单位</span><span class="close" id="closeAdd" name="close">X</span></div>
    <div class="cont pd10">
    <form id="addForm">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr bordercolor="white">
           		<td style="font-size: 14px;font-weight: bold;" colspan="4" align="left">联系人信息</td>
           </tr>
           <tr>
               <td width="90px" align="right"><span style="color: red;">*</span>姓名：</td>
               <td width="220px" align="left" style="padding: 0px"><input name="contacts_name" type="text" style="height: 100%;width: 55%;"/><span class="red"></span></td>
               <td width="100" align="right">性别：</td>
               <td width="150px" align="left" style="padding: 0px">
               		<select name="contacts_sex" class="sel" style="width:160px">
		        		<option value="0" >男</option>
		        		<option value="1" >女</option>
	        		</select>
               </td>
               
           </tr>
           <tr>
               <td align="right">手机：</td>
               <td align="left" style="padding: 0px"><input name="contacts_cellphone" type="text" style="height: 100%;width: 55%;"/></td>
               <td align="right">电话：</td>
               <td align="left" style="padding: 0px"><input name="contacts_phone" type="text" style="height: 100%;width: 55%;"/></td>
           </tr>
           <tr>
               <td align="right">QQ：</td>
               <td align="left" style="padding: 0px"><input name="contacts_qq" type="text" style="height: 100%;width: 55%;"/></td>
               <td align="right">电子邮箱：</td>
               <td align="left" style="padding: 0px"><input name="contacts_email" type="text" style="height: 100%;width: 55%;"/></td>
           </tr>
           <tr>
               <td align="right">联系地址：</td>
               <td align="left" colspan="3" style="padding: 0px"><input name="contacts_address" type="text" style="height: 100%;width: 61%;"/></td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>授权学校：</td>
               <td align="left" colspan="3" style="padding: 0px"><input name="school_name" type="text" style="height: 100%;width: 61%;"/><span class="red"></span></td>
           </tr>
           <tr>
               <td align="right">学校地址：</td>
               <td align="left" colspan="3" style="padding: 0px"><input name="school_address" type="text" style="height: 100%;width: 61%;"/></td>
           </tr>
           <tr>
           		<td style="font-size: 14px;font-weight: bold;" colspan="4" align="left">经办人信息</td>
           </tr>
           <tr>
               <td width="90px" align="right"><span style="color: red;">*</span>真实姓名：</td>
               <td width="220px" align="left" style="padding: 0px"><input name="managers_name" type="text" style="height: 100%;width: 55%;" /><span class="red"></span></td>
               <td width="100" align="right">手机号：</td>
               <td width="150px" align="left" style="padding: 0px"><input name="managers_cellphone" type="text" style="height: 100%;width: 55%;"/></td>
           </tr>
           <tr>
               <td align="right">电子邮箱：</td>
               <td align="left" style="padding: 0px"><input name="managers_email" type="text" style="height: 100%;width: 55%;"/></td>
               <td align="right"><span style="color: red;">*</span>合同编号：</td>
               <td align="left" style="padding: 0px"><input name="contract_number" type="text" style="height: 100%;width: 55%;"/><span class="red"></span></td>
           </tr>
           <tr>
               <td colspan="4" align="right" style="padding: 0px">
               		<input type="button" style="font-size: 13px;width: 60px;height: 30px; background-color: red; border-radius: 5px; color: white" value="取消" onclick="javascript:$('#closeAdd').click();"/>
               		<span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span>
               		<input id="addSubmit" type="button" style="font-size: 13px;width: 60px;height: 30px; background-color: #0066cc; border-radius: 5px; color: white" value="保存"/>
               </td>
           </tr>
         	 
       </table>
    </form>
    </div>
</div>


<script type="text/javascript">
var $ckAll = $("input[name='ckAll']");
var $id = $("input[name='id']");
var len = $id.length;
$ckAll.click(function() {
    $id.prop("checked",this.checked);
});
$id.click(function() {
    var b=$id.filter(":checked").length==len;
    var flag=$ckAll.prop("checked",b?true:false);
});

function search(){
	$("#search_type").val(0);
	$("#pageForm").submit();
}

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
    var date = new Date();
    date.setTime(objDate.time);
    date.setHours(objDate.hours);
    date.setMinutes(objDate.minutes);
    date.setSeconds(objDate.seconds);
    return date.format(format);
}

function detail(id){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/overseasLicenceOrg/detail",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(licenceOrg) {
			if(licenceOrg != null && licenceOrg !=''){
				$("#id").val(licenceOrg.id);
				$("#school_name").val(licenceOrg.school_name);
				$("#school_address").val(licenceOrg.school_address);
				$("#contacts_name").val(licenceOrg.contacts_name);
				if(1==licenceOrg.contacts_sex){
					$("#nv").attr("selected",true); 
				}
				if(0==licenceOrg.contacts_sex){
					$("#nan").attr("selected",true); 
				}
				$("#contacts_sex").val(licenceOrg.contacts_sex);
				$("#contacts_cellphone").val(licenceOrg.contacts_cellphone);
				$("#contacts_phone").val(licenceOrg.contacts_phone);
				$("#contacts_qq").val(licenceOrg.contacts_qq);
				$("#contacts_email").val(licenceOrg.contacts_email);
				$("#contacts_address").val(licenceOrg.contacts_address);
				
				$("#managers_name").val(licenceOrg.managers_name);
				$("#managers_cellphone").val(licenceOrg.managers_cellphone);
				$("#managers_email").val(licenceOrg.managers_email);
				$("#contract_number").val(licenceOrg.contract_number);
				$("#create_time").val(toDate(licenceOrg.create_time,"yyyy-MM-dd hh:mm:ss"));
				$(".detail").jumpBox(true);
			}
		}
	});
}

function add(){
	$(".add_edit").jumpBox(true);
}
//修改
$("#updateSubmit").click(function(){
	var id = $("#id").val();
	var contacts_name=$("#contacts_name").val();
	var contacts_sex = $("#contacts_sex").val();
	var contacts_cellphone = $("#contacts_cellphone").val();
	var contacts_phone = $("#contacts_phone").val();
	var contacts_qq = $("#contacts_qq").val();
	var contacts_email = $("#contacts_email").val();
	var contacts_address = $("#contacts_address").val();
	var school_name=$("#school_name").val();
	var school_address = $("#school_address").val();
	var managers_cellphone = $("#managers_cellphone").val();
	var managers_email = $("#managers_email").val();
	var managers_name=$("#managers_name").val();
	var contract_number=$("#contract_number").val();
	
	if($.trim(contacts_name)==''){
		$("#contacts_name").siblings(".red").text("请填写姓名");
		return;
	}
	if($.trim(school_name)==''){
		$("#school_name").siblings(".red").text("请填写授权学校");
		return;
	}
	if($.trim(managers_name)==''){
		$("#managers_name").siblings(".red").text("请填写姓名");
		return;
	}
	if($.trim(contract_number)==''){
		$("#contract_number").siblings(".red").text("请填写编号");
		return;
	}
	
	$(this).hide();
	$(this).next().show();
	$(this).next().next().hide();
	
	setTimeout(function(){
		$.ajax({
			type : 'POST',
			url : '${ctx}/manage/overseasLicenceOrg/update',
			data : {
				"id":id,
				"contacts_name":contacts_name,
				"contacts_sex":contacts_sex,
				"contacts_cellphone":contacts_cellphone,
				"contacts_phone":contacts_phone,
				"contacts_qq":contacts_qq,
				"contacts_email":contacts_email,
				"contacts_address":contacts_address,
				"school_name":school_name,
				"school_address":school_address,
				"managers_cellphone":managers_cellphone,
				"managers_email":managers_email,
				"managers_name":managers_name,
				"contract_number":contract_number
			},
			async : false,
			success : function(msg) {
				if(msg=='success'){
					$(".detail").closeBox();
					var txt = "机构信息修改完成，是否刷新列表？";
					$.confirm(txt,function(){
						$("#pageForm").submit();
					});
				}
			},
			error:function(){
				alert("修改失败！");
				window.location.href='${ctx}/manage/overseasLicenceOrg/list';
			}
		});
	},1);
});
//新增
$("#addSubmit").click(function(){
	var contacts_name=$(".add_edit input[name='contacts_name']").val();
	var school_name=$(".add_edit input[name='school_name']").val();
	var managers_name=$(".add_edit input[name='managers_name']").val();
	var contract_number=$(".add_edit input[name='contract_number']").val();
	
	if($.trim(contacts_name)==''){
		$(".add_edit input[name='contacts_name']").siblings(".red").text("请填写姓名");
		return;
	}
	if($.trim(school_name)==''){
		$(".add_edit input[name='school_name']").siblings(".red").text("请填写授权学校");
		return;
	}
	if($.trim(managers_name)==''){
		$(".add_edit input[name='managers_name']").siblings(".red").text("请填写姓名");
		return;
	}
	if($.trim(contract_number)==''){
		$(".add_edit input[name='contract_number']").siblings(".red").text("请填写编号");
		return;
	}
	
	$(this).hide();
	$(this).next().show();
	$(this).next().next().hide();
	
	setTimeout(function(){
		$.ajax({
			type : 'POST',
			url : '${ctx}/manage/overseasLicenceOrg/save',
			data : $("#addForm").serialize(),
			async : false,
			success : function(msg) {
				if(msg=='success'){
					$(".add_edit").closeBox();
					var txt = "机构信息新增完成，是否刷新列表？";
					$.confirm(txt,function(){
						window.location.href='${ctx}/manage/overseasLicenceOrg/list';
					});
				}else{
					alert("合同号已经存在不能重复！");
				}
			},
			error:function(){
				alert("新增失败！");
				window.location.href='${ctx}/manage/overseasLicenceOrg/list';
			}
		});
	},1);
});
function softLicenceList(orgId,orgName)
{
	window.location.href='${ctx}/manage/overseasLicenceOrg/softLicenceList?ORG_ID='+orgId+'&SCHOOL_NAME='+orgName;
}

//批量删除
function getCheckValue(){
	var ids = "";
	$.each( $id, function(i, id){
		if(id.checked){
			ids += "," + id.value;
		}
	});
	if(ids != ""){
		return ids.substring(1);
	}
	return "";
}
function delList(){
	var ids = getCheckValue();
	if(ids==""){
		$.alert("请至少选择一项！");
		return;
	}
	$.confirm("确定要删除选中机构授权信息吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/overseasLicenceOrg/delete",
			data : {"ids" : ids},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='success'){
					window.location.href='${ctx}/manage/overseasLicenceOrg/list';
				}
			}
		});
	});
}
//单独删除
function delOne(id){
	$.confirm("确定要删除选中机构授权信息吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/overseasLicenceOrg/delete",
			data : {"ids" : id},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='success'){
					window.location.href='${ctx}/manage/overseasLicenceOrg/list';
				}
			}
		});
	});
}
</script>
</body>
</html>