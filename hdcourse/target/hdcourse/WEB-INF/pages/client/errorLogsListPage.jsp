<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@taglib uri="/WEB-INF/tld/pageTag.tld" prefix="newTag"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误日志列表页</title>
</head>
<body>
<div class="mg15 txq_main">
	<input type="hidden" id="msg" value="${msg}"/>
	<form action="${ctx}/manage/clientErrorLogs/errorLogsListPage" name="pageForm" method="post">
	<input type="hidden" id="sel_error_id" name="sel_error_id"/>
	<input type="hidden" id="search_type" name="search_type"/>
	
    <div class="mgtb10 txq_txtbox clearfix">
        <p class="fl mgr10"><span>类型：</span><select id="log_type" name="log_type" value="${log_type}" class="sel" style="width:150px;"><option value="">全部</option><option value="0">教学助手</option><option value="1">电子书包</option></select></p>
        <p class="fl mgr10"><span>软件版本：</span><input name="version_code" value="${version_code}" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10"><span>错误信息：</span><input name="log_content" value="${log_content}" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10"><span>解决状态：</span><select id="status" name="status" value="${status}" class="sel" style="width:150px;"><option value="">全部</option><option value="0">未解决</option><option value="1">已解决</option></select></p>
        <!-- <p class="fl mgr10">
        	<span>创建时间：</span> 
            <input type="text" style="width: 100px;" readonly="readonly" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" id="d4311" class="Wdate">
            <span>至</span> 
            <input type="text" style="width: 100px;" readonly="readonly" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" id="d4312" class="Wdate">
        </p> -->
        <p class="fl"><input type="button" onclick="search()" class="btn_blue" value="查询" /></p>
    </div>
    
    <div class="mgtb10">
		<div class="base_title"><strong>错误日志列表</strong></div>

		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="批量解决" onclick="batch_solve()"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="导出错误日志" onclick="exportexcel()"/>&nbsp;&nbsp;
		</div>
		
		<table class="small_space data_list txq_ckall" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="4%"><input type="checkbox" name="ckAll"></th>
				<th width="8%">类型</th>
				<th width="8%">软件版本</th>
				<th width="8%">系统信息</th>
				<th width="25%">错误信息</th>
				<th width="8%">用户姓名</th>
				<th width="10%">ip地址</th>
				<th width="11%">创建时间</th>
				<th width="11%">解决时间</th>
				<th>操作</th>
		    </tr>
		    <c:forEach items="${errorLogsList}" var="log" varStatus="status">
			    <tr>
					<td><input type="checkbox" name="ckItm" id="${log.id}"/></td>
					<td><c:if test="${log.log_type eq 0}">教学助手</c:if>
						<c:if test="${log.log_type eq 1}">电子书包</c:if></td><!-- 日志类型（0：教学助手，1：电子书包） -->
					<td>${log.version_code }</td>
					<td>
						<c:if test="${fn:length(log.system_info)<=20}">${log.system_info }</c:if>
						<c:if test="${fn:length(log.system_info)>20}">${fn:substring(log.system_info,0,20)}...</c:if>
					</td>
					<td><c:if test="${fn:length(log.log_content)<=50}">${log.log_content }</c:if>
						<c:if test="${fn:length(log.log_content)>50}">${fn:substring(log.log_content,0,50)}...</c:if>
					</td>
					<td>${log.user_name }</td>
					<td>${log.ip_address }</td>
					<td><fmt:formatDate value="${log.create_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${log.solve_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><a href="javascript:void(0)" class="mgr10" onclick="view('${log.id}')">查看</a>
						<a href="javascript:void(0)" class="mgr10" onclick="solve('${log.id}')">解决</a></td>
			    </tr>
		    </c:forEach>
		    <c:if test="${fn:length(errorLogsList)<1}"><tr><td colspan="10"><font  style="color:red">暂无数据</font></td></tr></c:if>
		</table>
    </div>
	<div class="base_page clearfix">
		 <!-- 分页 -->
		<c:if test="${not empty page.totalPage && page.totalPage >= 1 }">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/clientErrorLogs/errorLogsListPage"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<!--#弹窗层 查看-->
<div class="popup jumpBox dis_none _view" style="width:600px; height:400px;">
    <div class="tit"><span class="fl">查看</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
        <textarea disabled="disabled" class="inp" style="width:580px; height:337px;" id="dialog_v_cont"></textarea>
    </div>
</div>
<div class="dis_none" id="screen"></div>

<!--#弹窗层 解决提示-->
<div class="popup jumpBox dis_none _jjtj">
    <div class="tit"><span class="fl">提示</span><span class="close" name="close">X</span></div>
    <div class="cont pd10" style="text-align:center">
        <div style="width:498px; height:120px;font-size: 14px; color: #a82929;padding-top: 68px;">
        	<p id="dialog_cont" style="font:24"></p>
        </div>
	    <div id="dialog_oper">
	    	<input type="button" class="btn_blue" value="确定" onclick="toSolve()"/>&nbsp;&nbsp;
	    	<input type="button" class="btn_gray" value="取消" name="close"/>
	    </div>
    </div>
</div>

<!--#弹窗层 状态提示-->
<div class="popup jumpBox dis_none _tj">
    <div class="tit"><span class="fl">提示</span><span class="close" name="close">X</span></div>
    <div class="cont pd10" style="text-align:center">
        <div style="width:498px; height:120px;font-size: 14px; color: #a82929;padding-top: 68px;">
        	<p id="dialog_ij_cont" style="font:24">${msg}</p>
        </div>
	    <div id="dialog_oper">
	    	<input type="button" class="btn_blue" value="确定" name="close"/>
	    </div>
    </div>
</div>

<script>
//tab
$("div[name='slide']").tabControl("*[name='slideTag'] a" , "*[name='slideCont']" , '.more');
//弹出层
$('*[name="roleChoose"]').each(function(){$(this).click(function(){$(".jumpBox").jumpBox(true);});});

var $ckAll = $(".txq_ckall input[name='ckAll']");
var $ckItm = $(".txq_ckall input[name='ckItm']");
var len = $ckItm.length;
$ckAll.click(function() {
    $ckItm.prop('checked',this.checked);
});
$ckItm.click(function() {
    var b=$ckItm.filter(":checked").length==len;
    var flag=$ckAll.prop("checked",b?true:false);
});

//隔行变色
/*$(document).ready(function(){
	$(".data_list tr td").mouseover(function(){
		$(this).parent().find("td").css("background-color","#d5f4fe");
	});
});
*/$(document).ready(function(){ 
	$(".data_list tr td").mouseout(function(){
		var bgc = $(this).parent().attr("bg");
		$(this).parent().find("td").css("background-color",bgc);
	});
});
$(document).ready(function(){
	var color="#f6f7f6"
	$(".data_list tr:even td").css("background-color",color);  //改变偶数行背景色
	$(".data_list tr:even").attr("bg",color);
	$(".data_list tr:odd").attr("bg","#fff");
	if($("#msg").val()!=''){
		$("._tj").jumpBox(true);
	}
	jsSelectItemByValue(document.getElementById("log_type"),'${log_type}');
	jsSelectItemByValue(document.getElementById("status"),'${status}');
});
//查看
function view(i){
	$.ajax({
		   type: "POST",
		   url: "${ctx}/manage/clientErrorLogs/view",
		   data: {"id":i},
		   async: true,
		   dataType:'json',
		   success: function(msg){
			   //console.log(msg);
			   $("#dialog_v_cont").html(msg.log_content);
			   $("._view").jumpBox(true);
		   }
	});
	
}
//解决
function solve(i){
	$("#sel_error_id").val(i+",");
	$("#dialog_cont").html("您确定要解决？");
	$("._jjtj").jumpBox(true);
}
function toSolve(){
	document.forms[0].action = "${ctx}/manage/clientErrorLogs/solve";
	document.forms[0].submit();
}
//批量解决
function batch_solve(){
	//先检查是否有选中数据
	if($ckItm.filter(":checked").length<1){
		$("#dialog_ij_cont").html("请先选择数据！");
		$("._tj").jumpBox(true);
		return false;
	}else{
	    //去后台解决
	    $("#sel_error_id").val("");
		for(var i=0;i<$ckItm.filter(":checked").length;i++){
			$("#sel_error_id").val($("#sel_error_id").val()+$ckItm.filter(":checked")[i].id+",");
		}
		toSolve();
	}
}
//查询
function search(){
	$("#search_type").val(0);
	document.forms[0].action = "${ctx}/manage/clientErrorLogs/errorLogsListPage";
	document.forms[0].submit();
}
//控制select初始值
function jsSelectItemByValue(objSelect,objItemText) {  
    for(var i=0;i<objSelect.options.length;i++) {  
        if(objSelect.options[i].value == objItemText) {  
            objSelect.options[i].selected = true;  
            break;  
        }  
    }  
} 

function exportexcel(){
	var log_type = $('#log_type').val();
	var version_code = $('input[name="version_code"]').val();
	var log_content = $('input[name="log_content"]').val();
	var status = $('#status').val();
	window.location.href='${ctx}/manage/clientErrorLogs/exportexcel?log_type='+log_type+'&version_code='+version_code+'&log_content='+log_content+'&status='+status;
}
</script>
</body>
</html>