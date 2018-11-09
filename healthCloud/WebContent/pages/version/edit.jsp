<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<% String contextPath = request.getContextPath(); %>
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/res/plugins/webuploader/webuploader.js"></script>
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="system:role:list">
						<li><a href="<%=contextPath%>/role/list" target="_self"><span>角色管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="system:resource:list">
						<li><a href="<%=contextPath%>/res/list" target="_self"><span>权限资源管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="system:dict:list">
						<li><a href="<%=contextPath%>/dict/list" target="_self"><span>数据字典管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="system:area:list">
						<li><a href="<%=contextPath%>/area/list" target="_self"><span>区域管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="app:version:list">
						<li class="active"><a href="<%=contextPath%>/version/list" target="_self"><span>版本管理</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;">
			
			<ul class="nav nav-tabs" style="margin-bottom: 10px;">
				<li><a href="list">版本列表</a></li>
				<li class="active">
					<c:choose>
						<c:when test="${version.kid != null }">
							<a href="edit?kid=${version.kid }">编辑版本</a>
						</c:when>
						<c:otherwise>
							<a href="edit">新增版本</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		
			<div class="col-sm-12" style="padding-right: 0px;padding-top: 0px;padding-left: 0px;">
				<form class="form-horizontal pull-left form-normallr" id="versionForm" method="post" action="save" enctype="multipart/form-data">
			    	<c:choose>
						<c:when test="${version.kid!=null }">
							<input type="hidden" name="editType" value="edit">
							<input type="hidden" id="kid" name="kid" value="${version.kid }">
						</c:when>
						<c:otherwise>
							<input type="hidden" name="editType" value="new">
							<input type="hidden" id="kid" name="kid" value="${kid }">
						</c:otherwise>
					</c:choose>
					
			      	<div class="form-gd-hd">版本基本信息</div>
		      		<table class="table table-bordered">
		          		<tr>
		          			<th>
		          				<label class="control-label"><span style="color: red;">* 应用名称：</span></label>
		          			</th>
		               		<td>
		                       	<form:select class="form-control" id="cAppCode" path="version.cAppCode" name="cAppCode" items="${applicationScope.apply_name__}" itemLabel="value" itemValue="key" />
		               		</td>
		               		<th>
								<label class="control-label"><span style="color: red;">* 版本编码：</span></label>
		               		</th>
		               		<td>
								<input id="cVersionDesc" type="text" class="form-control" name="cVersionDesc" value="${version.cVersionDesc }" required="required" />
		               		</td>
		          		</tr>
		          		
				       	<tr>
				       		<th>
								<label class="control-label"><span style="color: red;">* 版本号：</span></label>
							</th>
		               		<td>
								<input id="iVersionCode" type="text" class="form-control" name="iVersionCode" value="${version.iVersionCode }" required="required" />
							</td>
			           		<th>
								<label class="control-label"><span style="color: red;">* 上传apk：</span></label>
							</th>
			                <td>
			                	<input type="file" id="apkFile" name="apkFile" accept=".apk" onchange="updateFile();" style="display: none">
								<div class="input-group">
									<input type="text" id="apkFileName" value="${version.cDownloadUrl }" class="form-control" readonly="readonly" required="required">
									<span class="input-group-btn"> 
										<a class="btn btn-default" onclick="$('input[id=apkFile]').click();">浏览</a> 
									</span>
								</div>
							</td>
						</tr>
		          		
			         	<tr>
		                	<th>
		          				<label class="control-label" for="cUpdateDesc"><span style="color: red;">* 更新内容：</span></label>
		                	</th>
		                	<td colspan="3">
								<textarea rows="3" class="form-control" name="cUpdateDesc" id="cUpdateDesc" style="resize: none;" required="required">${version.cUpdateDesc }</textarea>
		                	</td>
			         	</tr>
			         	
			         	<tr>
			         		<th colspan="5">
			         			<input type="submit" id="submitBtn" class="btn btn-primary" value="提交">
			                 	<input type="reset" class="btn btn-default">
							</th>
			         	</tr>
		      		</table>
			    </form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
	$("#cAppCode").val("${version.cAppCode}");

	$("input[id='apkFile']").change(function() { 
		$("#apkFileName").val($(this).val()); 
	});

	function updateFile(){
	    var aa=document.getElementById("apkFile").value.toLowerCase().split("."); //以“.”分隔上传文件字符串
	    if(aa[aa.length-1]!="apk"){
	    	layer.msg("请选择格式为apk的文件");
	        $("#apkFile").after($("#apkFile").clone().val("")); 
	        $("#apkFile").remove();
	           return false;
	    }
	    return true;
	}

</script>
</body>
</html>