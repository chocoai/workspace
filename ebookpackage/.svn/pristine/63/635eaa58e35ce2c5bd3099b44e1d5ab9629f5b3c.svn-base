<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ page import="com.whty.common.util.SysConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>衍生产品管理页面</title>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<style type="text/css">
.progress{ height: 4px; font-size: 0; line-height: 4px; background: orange; width: 0;}
.num{ margin-left: 20px;}
#file-list img{cursor: pointer;}
</style>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/derivativeAppLine/list" method="post">
	
    <div class="mgtb10 txq_txtbox clearfix">
       
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>型号列表</strong></div>

		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="新增产品线" onclick="add()"/>&nbsp;&nbsp;
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="5%">衍生线路编号</th>
				<th width="5%">衍生线路名称</th>
				<th width="20%">备注</th>
		    </tr>
		    <c:if test="${fn:length(list) <1}">
		    	<tr><td colspan="6" align="center"><font color="red">暂无数据</font></td></tr>
		    </c:if>
		    <c:forEach items="${list}" var="pro" varStatus="status">
		    	<tr>
		    		<td align="center">${pro.id}</td>
		    		<td align="center">${pro.lineName}</td>
		    		<td align="center">${pro.description}</td>
		    	</tr>
		    </c:forEach>
		</table>
    </div>
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${not empty page.totalPage && page.totalPage > 1 }">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/derivativeAppLine/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<!--新增-->
<div class="popup jumpBox add dis_none">
	<form id="newForm">
	</form>
    <div class="tit"><span class="fl">新增线路</span><span class="close" name="close">X</span></div>
    <div class="cont">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>线路名称：</td>
               <td width="50%" align="left">
				<input type="text" id="lineName"  name ="lineName" class="inp focus"/>
				<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right">说明：</td>
               <td align="left">
               	<textarea id="description" name="description" class="inp focus" rows="5" cols="48" style="height: 85px;" placeholder="请输入描述......"></textarea>
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
<div class="popup jumpBox edit dis_none">
	<form id="editForm">
		<input type="hidden" name="id" value="">
	</form>
    <div class="tit"><span class="fl">编辑型号</span><span class="close" name="close">X</span></div>
    <div class="cont">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>终端型号：</td>
               <td width="50%" align="left">
				<input type="text" id="lineName" name="lineName" class="inp focus" disabled="disabled"/>
				<span class="red"></span>
               </td>
               
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>描述：</td>
               <td align="left">
               	<textarea id="description" name="description" class="inp focus" rows="5" cols="48" style="height: 85px;" placeholder="请输入描述......"></textarea>
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

<script type="text/javascript">
function search(){
	//resetPageParam();
	$("#pageForm").submit();
}



function add(){
	$(".add input[type='hidden']").prop("id",'');
	$(".add input[type='text']").prop("value",'');
	$(".add textarea[name='description']").val('');
	$(".add .red").text('');

	$(".add").jumpBox(true);
}

var modelCodeList = [];
function edit(id){
	$(".edit .red").text('');
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/flatModel/detail",
		data : {"id" : id},
		async : false,
		dataType : 'json',
		success : function(app) {
			if(app != null){
				$(".edit input[name='id']").val(app.id);
				$(".edit input[name='modelCode']").val(app.modelCode);
				$(".edit textarea[name='memo']").val(app.memo);
				$(".edit").jumpBox(true);
				//获取模板型号	
				
				modelCodeList = app.FlatModelIds.split(",");
				
				queryEditModelCode(id);
			}
		}
	});
}


$(document).ready(function(){ 
	
	$(".add input[name='save']").click(function(){
		var lineName =$(".add input[name='lineName']").val();
		var description=$(".add textarea[name='description']").val();
		

		if($.trim(lineName)==''){
			$(".add input[name='lineName']").siblings(".red").text("请填写线路名称");
			return;
		}
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/derivativeAppLine/save",
				data : {
					"lineName"  : lineName,
					"description"  : description
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					
					if(msg=='success'){
						$(".add").closeBox();
						$("#pageForm").submit();
					}
					if(msg=='fail'){
						$(".add").closeBox();
						$.alert("添加失败，存在相同的产品型号！");
					}
				}
			});
		}, 1);
		
	});
	
	
	$(".edit input[name='save']").click(function(){
		var id = $(".edit input[name='id']").val();
		var description=$(".edit textarea[name='description']").val();
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/derivativeAppLine/update",
				data : {
					"id"  : id,
					"memo"  : memo
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
</script>

</body>
</html>