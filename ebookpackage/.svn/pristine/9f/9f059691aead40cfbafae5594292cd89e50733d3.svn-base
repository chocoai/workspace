<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ page import="com.whty.common.util.SysConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品管理页面</title>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<style type="text/css">
.progress{ height: 4px; font-size: 0; line-height: 4px; background: orange; width: 0;}
.num{ margin-left: 20px;}
#file-list img{cursor: pointer;}
</style>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/flatModel/list" method="post">
	
    <div class="mgtb10 txq_txtbox clearfix">
       
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>型号列表</strong></div>

		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="新增型号" onclick="add()"/>&nbsp;&nbsp;
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="5%">平板型号</th>
				<th width="15%">备注</th>
				<th width="20%">操作</th>
		    </tr>
		    <c:if test="${fn:length(modelList) <1}">
		    	<tr><td colspan="6" align="center"><font color="red">暂无数据</font></td></tr>
		    </c:if>
		    <c:forEach items="${modelList}" var="pro" varStatus="status">
		    	<tr>
		    		<td align="center">${pro.modelCode}</td>
		    		<td align="center">${pro.memo}</td>
		    		<td align="center">
		    			<a href="javascript:void(0);" onclick="edit('${pro.id }')"  id="edit">编辑</a>
		    			<a href="javascript:void(0);" onclick="del('${pro.id }')" id="delete">删除</a>
		    		</td>
		    		
		    	</tr>
		    </c:forEach>
		</table>
    </div>
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${not empty page.totalPage && page.totalPage > 1 }">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/flatModel/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<!--新增-->
<div class="popup jumpBox add dis_none">
	<form id="newForm">
	</form>
    <div class="tit"><span class="fl">新增型号</span><span class="close" name="close">X</span></div>
    <div class="cont">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>终端型号：</td>
               <td width="50%" align="left">
				<input type="text" id="modelCode"  name ="modelCode" class="inp focus"/>
				<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right">说明：</td>
               <td align="left">
               	<textarea id="memo" name="memo" class="inp focus" rows="5" cols="48" style="height: 85px;" placeholder="请输入描述......"></textarea>
               </td>
           </tr>
           <tr>
           	<td colspan="2" style="height: 53px;">
           		<input type="button" name="save" class="btn_blue" value="保存" />
           		<%-- <span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span> --%>
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
				<input type="text" id="modelCode" name="modelCode" class="inp focus" disabled="disabled"/>
				<span class="red"></span>
               </td>
               
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>描述：</td>
               <td align="left">
               	<textarea id="memo" name="memo" class="inp focus" rows="5" cols="48" style="height: 85px;" placeholder="请输入产品描述......"></textarea>
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

function del(id){
	$.confirm("确定要删除该应用吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/flatModel/del",
			data : {
				"id"  : id
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
}

function add(){
	$(".add input[type='hidden']").prop("id",'');
	$(".add input[type='text']").prop("value",'');
	$(".add textarea[name='memo']").val('');
	$(".add .red").text('');

	$(".add").jumpBox(true);
}

var modelCodeList = [];
function edit(id){
	$(".edit .red").text('');
	$.ajax({
		type : "POST",
		url : "${ctx}//manage/flatModel/detail",
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


//获取模板型号
function queryEditModelCode(id){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/flatModel/queryFlatModelCode",
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
							if(obj.versionCode==modelCodeList[j]){
								htmlStr += '<input type="checkbox" name="version" checked="checked" value="'+obj.id+'"/>'+obj.versionCode;
								break;
							}
						}
						if(j==modelCodeList.length){
							htmlStr += '<input type="checkbox" name="version" value="'+obj.id+'"/>'+obj.model_code;
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


$(document).ready(function(){ 
	
	$(".add input[name='save']").click(function(){
		var modelCode=$(".add input[name='modelCode']").val();
		var memo=$(".add textarea[name='memo']").val();
		

		if($.trim(modelCode)==''){
			$(".add input[name='modelCode']").siblings(".red").text("请填写终端型号");
			return;
		}
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/flatModel/save",
				data : {
					"modelCode"  : modelCode,
					"memo"  : memo
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
		var memo=$(".edit textarea[name='memo']").val();
	
		//if($.trim(memo)==''){
		//	$(".edit textarea[name='memo']").siblings(".red").text("请填写应用描述");
		//	return;
		//}
		
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/flatModel/update",
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