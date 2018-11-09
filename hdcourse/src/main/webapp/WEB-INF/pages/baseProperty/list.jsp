<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参数配置</title>
</head>
<body>
	<div class="mg15 txq_main">
		<form id="pageForm" name="pageForm" action="${ctx}/baseProperty/list" method="post">
			<input type="hidden" id="search_type" name="search_type" />

			<div class="mgtb10 txq_txtbox clearfix">
				<p class="fl mgr10">
					<span>属性key值：</span>
					<input id="property_key" name="property_key" value="${property_key }" type="text" style="width: 130px;" class="inp focus" placeholder="请输入属性key值" />
				</p>
				<p class="fl mgr10">
					<span>状态：</span> 
					<select class="sel" style="width: 130px;" id="status" name="status">
					    <option value=""
							<c:if test="${empty status}">selected="selected"</c:if>>全部</option>
						<option value="0"
							<c:if test="${status=='0'}">selected="selected"</c:if>>有效</option>
						<option value="1"
							<c:if test="${status=='1'}">selected="selected"</c:if>>无效</option>
					</select>
				</p>
				<p class="fl mgr10">
					<span>平台名称：</span>
					<!--<input id="platform_name" name="platform_name" value="${platform_name }" type="text" style="width: 130px;" class="inp focus" placeholder="请输入平台名称" />
					 platformList -->
					<select id="platform_code" name=platform_code value="${platform_code}" class="sel" style="width:150px;">
						<option value="">全部</option>
						<c:forEach items="${platformList}" var="p" varStatus="st">
							<option value="${p.platform_code }">${p.platform_name }</option>
						</c:forEach>
					</select>
				</p>
				<p class="fl mgr10">
					<span>类型名称：</span>
					<input id="property_type_name" name="property_type_name" value="${property_type_name }" type="text" style="width: 130px;" class="inp focus" placeholder="请输入类型名称" />
				</p>
				<p class="fl">
					<input type="button" onclick="search()" class="btn_blue" value="查询" />
				</p>
			</div>
			<div class="mgtb10">
				<div class="base_title">
					<strong>参数列表</strong>
				</div>

				<div class="clearfix mgtb10">
					<input type="button" class="btn_blue" value="新增" onclick="add()" />&nbsp;&nbsp;
					<input type="button" class="btn_blue" value="删除" onclick="del()" />&nbsp;&nbsp;
				</div>
				<table class="small_space data_list" width="100%"
					style="border-collapse: collapse;">
					<tr>
						<th width="4%"><input type="checkbox" name="ckAll"></th>
						<th width="10%">属性key值</th>
						<th width="17%">属性value值</th>
						<th width="8%">平台编码</th>
						<th width="10%">平台名称</th>
						<th width="10">类型名称</th>
						<th width="20%">描述</th>
						<th width="10%">修改时间</th>
						<th width="5%">状态</th>
						<th width="6%">操作</th>
					</tr>
					<c:if test="${fn:length(propertylist) < 1}">
						<tr>
							<td colspan="10" style="color: red">暂无数据</td>
						</tr>
					</c:if>
					<c:if test="${fn:length(propertylist) >= 1}">
						<c:forEach items="${propertylist}" var="obj" varStatus="status">
							<tr>
								<td><input type="checkbox" name="ckItm" value="${obj.id}"></td>
								<td>${obj.property_key }</td>
								<td title="${obj.property_value }">
									<c:if test="${fn:length(obj.property_value)<=20}">${obj.property_value }</c:if>
									<c:if test="${fn:length(obj.property_value)>20}">${fn:substring(obj.property_value,0,20)}...</c:if>
								</td>
								<td>${obj.platform_code }</td>
								<td>${obj.platform_name }</td>
								<td>${obj.property_type_name }</td>
								<td title="${obj.description }">
								   <c:if test="${fn:length(obj.description)<=20}">${obj.description }</c:if>
									<c:if test="${fn:length(obj.description)>20}">${fn:substring(obj.description,0,20)}...</c:if>
								</td>
								<td><fmt:formatDate value="${obj.update_time }"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
								    <c:if test="${obj.status=='0'}">有效</c:if>
								    <c:if test="${obj.status=='1'}">无效</c:if>
								</td>
								<td>
									<a href="javascript:void(0);" class="mgr10" onclick="edit('${obj.id}');">修改</a>
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
						currentPage="${page.currentPage}" pageSize="${page.pageSize}"
						totalRows="${page.totalRows}" action="${ctx}/baseProperty/list"></newTag:page>
				</c:if>
			</div>
		</form>
	</div>

	<!--新增  编辑-->
	<div class="popup jumpBox add_edit dis_none">
		<input type="hidden" name="id">
		<div class="tit">
			<span class="fl">标题</span><span class="close" name="close">X</span>
		</div>
		<div class="cont pd10">
			<table class="small_space data_list" style="width:100%; border-collapse: collapse;">
				<tr>
					<td width="25%" align="right"><span style="color: red;">*</span>属性key值：</td>
					<td width="75%" align="left">
						<input name="propertyKey" type="text" style="width: 180px;" class="inp focus"/>
						<span class="red"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>属性value值：</td>
					<td align="left">
						<input name="propertyValue" type="text" style="width: 350px;" class="inp focus"/>
						<span class="red"></span>
					</td>
	
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>状态：</td>
					<td align="left">
						<select name="status" class="sel" style="width: 180px">
							<option value="0" <c:if test="${status == '0'}">selected="selected"</c:if>>有效</option>
							<option value="1" <c:if test="${status == '1'}">selected="selected"</c:if>>无效</option>
						</select>
						<span class="red"></span>
					</td>
				</tr>
				<tr>
					<td align="right">描述：</td>
					<td align="left">
						<textarea name="description" rows="2" cols="45"></textarea>
						<span class="red"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>平台编码：</td>
					<td align="left">
						<input name="platformCode" type="text" style="width: 180px;" class="inp focus"/>
						<span class="red"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>平台名称：</td>
					<td align="left">
						<input name="platformName" type="text" style="width: 180px;" class="inp focus"/>
						<span class="red"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>类型名称：</td>
					<td align="left">
						<input name="propertyTypeName" type="text" style="width: 180px;" class="inp focus"/>
						<span class="red"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					    <input type="button" name="save" class="btn_blue" value="保存" />
						<span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span>
						<input type="button" name="close" class="btn_gray" value="取消" />
					</td>
				</tr>
			</table>
		</div>
	</div>

<script>
		//tab
		$("div[name='slide']").tabControl("*[name='slideTag'] a","*[name='slideCont']", '.more');
		//弹出层
		$('*[name="roleChoose"]').each(function() {
			$(this).click(function() {
				$(".jumpBox").jumpBox(true);
			});
		});

		var $ckAll = $("input[name='ckAll']");
		var $ckItm = $("input[name='ckItm']");
		var len = $ckItm.length;
		$ckAll.click(function() {
			$ckItm.prop("checked", this.checked);
		});
		$ckItm.click(function() {
			var b = $ckItm.filter(":checked").length == len;
			var flag = $ckAll.prop("checked", b ? true : false);
		});
		
		
		//查询
		function search() {
			$("#search_type").val(0);
			$("#pageForm").submit();
		}

		function getCheckValue() {
			var ids = "";
			$.each($ckItm, function(i, ckItm) {
				if (ckItm.checked) {
					ids += "," + ckItm.value;
				}
			});
			if (ids != "") {
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
		 $(".add_edit").jumpBox(true);
		 } 

		//删除
		function del() {
			var ids = getCheckValue();
			if (ids == "") {
				$.alert("请至少选择一项！");
				return;
			}
			$.confirm("确定要删除选中的数字教材信息吗？", function() {
				$.ajax({
					type : "POST",
					url : "${ctx}/baseProperty/delete",
					data : {
						"ids" : ids
					},
					async : false,
					dataType : 'text',
					success : function(msg) {
						if (msg == 'success') {
							$("#pageForm").submit();
						}
					}
				});
			});
		}
		
		function edit(id){
			emptyAddEdit();
			$(".add_edit .fl").text("修改");
			$.ajax({
				   type: "POST",
				   url: "${ctx}/baseProperty/viewForEdit",
				   data: {"id":id},
				   async: true,
				   dataType:'json',
				   success: function(msg){
					   $(".add_edit input[name='id']").val(msg.id);
						$(".add_edit input[name='propertyKey']").val(msg.property_key);
						$(".add_edit input[name='propertyValue']").val(msg.property_value);
						$(".add_edit select[name='status']").val(msg.status);
						$(".add_edit textarea[name='description']").val(msg.description);
						$(".add_edit input[name='platformCode']").val(msg.platform_code);
						$(".add_edit input[name='platformName']").val(msg.platform_name);
						$(".add_edit input[name='propertyTypeName']").val(msg.property_type_name);
						$(".add_edit").jumpBox(true);
				   }
			});
		}
		
		$(document).ready(function(){ 
			jsSelectItemByValue(document.getElementById("platform_code"),'${platform_code}');
			$(".add_edit input[name='save']").click(function(){
				var id=$(".add_edit input[name='id']").val();
				var propertyKey=$(".add_edit input[name='propertyKey']").val();
				var propertyValue=$(".add_edit input[name='propertyValue']").val();
				var status=$(".add_edit select[name='status']").val();
				var description=$(".add_edit textarea[name='description']").val();
				var platformCode=$(".add_edit input[name='platformCode']").val();
				var platformName=$(".add_edit input[name='platformName']").val();
				var propertyTypeName=$(".add_edit input[name='propertyTypeName']").val();
				
				if($.trim(propertyKey)==''){
					$(".add_edit input[name='propertyKey']").siblings(".red").text("请填写属性key值");
					return;
				}
				if($.trim(propertyValue)==''){
					$(".add_edit input[name='propertyValue']").siblings(".red").text("请填写属性value值");
					return;
				}
				if($.trim(status)==''){
					$(".add_edit select[name='status']").siblings(".red").text("请输入状态");
					return;
				}
				if($.trim(platformCode)==''){
					$(".add_edit input[name='platformCode']").siblings(".red").text("请输入平台编码");
					return;
				}
				if($.trim(platformName)==''){
					$(".add_edit input[name='platformName']").siblings(".red").text("请输入平台名称");
					return;
				}
				if($.trim(propertyTypeName)==''){
					$(".add_edit input[name='propertyTypeName']").siblings(".red").text("请输入类型名称");
					return;
				}
				
				if(description.length > 130){
					$(".add_edit textarea[name='description']").siblings(".red").text("描述限制长度为130");
					return;
				}
				
				$(this).hide();
				$(this).next().show();
				$(this).next().next().hide();
				
				setTimeout(function(){
					$.ajax({
						type : "POST",
						url : "${ctx}/baseProperty/save",
						data : {
							"id" : id,
							"property_key"  : propertyKey,
							"property_value"  : propertyValue,
							"status"  : status,
							"description"  : description,
							"platform_code"  : platformCode,
							"platform_name"  : platformName,
							"property_type_name" : propertyTypeName
						},
						async : false,
						dataType : 'text',
						success : function(msg) {
							if(msg=='success'){
								$(".add_edit").closeBox();
								var txt = "";
								if(id==''){
									txt = "属性新增完成，是否刷新列表？";
								}else{
									txt = "属性编辑完成，是否刷新列表？";
								}
								$.confirm(txt,function(){
									$("#pageForm").submit();
								});
							}
						}
					});
				}, 1);
				
			});
		});
		//控制select初始值
		function jsSelectItemByValue(objSelect,objItemText) {  
		    for(var i=0;i<objSelect.options.length;i++) {  
		        if(objSelect.options[i].value == objItemText) {  
		            objSelect.options[i].selected = true;  
		            break;  
		        }  
		    }  
		}
	</script>
</body>
</html>