<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/UMeditor/themes/default/css/umeditor.css">
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
<style type="text/css">
	.radioinput span { margin-right:10px; line-height:34px; vertical-align:middle;}
	.radioinput span label { margin-bottom:0;}
</style>
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px; overflow-x: hidden;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="cms:content:list">
						<li class="active"><a href="<%=contextPath%>/content/list" target="_self"><span>内容管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="cms:channel:list">
						<li><a href="<%=contextPath%>/channel/list" target="_self"><span>栏目管理</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-left: 0px; padding-right: 0px;">
			<ul class="nav nav-tabs">
				<li><a href="list?channelId=${channel.channelId}">内容列表</a></li>
				<li class="active">
					<c:choose>
						<c:when test="${not empty content.contentId }">
							<a href="#">内容编辑</a>
						</c:when>
						<c:otherwise>
							<a href="#">内容发布</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
			
			<div class="modal fade bs-example-modal-sm" id="channelTree" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
				<div class="modal-dialog" style="width: 450px;" role="document">
					<div class="modal-content"></div>
				</div>
			</div>
			
			<div class="col-sm-12" style="padding-right: 0px;padding-top: 0px;padding-left: 0px;">
				<form class="form-horizontal pull-left form-normallr" action="add" id="contentForm" method="post" enctype="multipart/form-data">
		    	<input type="hidden" id="contentId" name="contentId" value="${content.contentId}">
		      	<div class="form-gd-hd">内容基本信息</div>
	      		<table class="table table-bordered">
	          		<tr>
	          			<th><span style="color: red;">* 标题：</span></th>
	               		<td colspan="3">
							<input type="text" class="form-control" id="title" name="title" value="${content.title}" required="required">
	               		</td>
	       			</tr>
	          		
	          		<tr>
	               		<th>副标题：</th>
	               		<td colspan="3">
							<input type="text" class="form-control" id="subtitle" name="subtitle" value="${content.subtitle}">
	               		</td>
	          		</tr>
	          		
	          		<tr>
	          			<th><span style="color: red;">* 所属栏目：</span></th>
	               		<td>
	              			<input type="text" class="form-control" value="${channel.channelName}" readonly="readonly">
							<input type="hidden" id="channelId" name="channelId" value="${channel.channelId}">
	               		</td>
	               		<th><span style="color: red;">* 作者：</span></th>
	               		<td>
	               			<input type="text" class="form-control" id="author" name="author" value="${content.author}" required="required">
	               		</td>
	          		</tr>
	          		
			       	<tr>
			       		<th>内容标题图：</th>
	               		<td>
							<input type="file" id="titleImgFile" name="titleImgFile" accept="image/jpg,image/jpeg,image/png,image/gif" style="display:none">
							<div class="input-group">
								<input type="text" id="titleImg" value="${content.titleImg}" class="form-control" readonly="readonly"> 
								<span class="input-group-btn"> 
									<a class="btn btn-default" onclick="$('input[id=titleImgFile]').click();">浏览</a> 
									<c:if test="${not empty content.titleImg}">
										<a class="btn btn-default" href="${content.titleImg}" target="_blank">查看</a>
									</c:if>
								</span>
							</div>
						</td>
		           		<th><span style="color: red;">* 内容类型：</span></th>
		                <td>
							<form:select class="form-control" id="contentType" path="content.contentType" items="${applicationScope.content_type__}" itemLabel="value" itemValue="key" cssStyle=""/>
						</td>
					</tr>
	          		
			       	<tr>
		           		<th>来源：</th>
		                <td>
		                	<input type="text" class="form-control" id="origin" name="origin" value="${content.origin}">
						</td>
						<th>转载网址：</th>
	               		<td>
	               			<input type="text" class="form-control" id="fromUrl" name="fromUrl" value="${content.fromUrl}">
	               		</td>
			      	</tr>
	      		</table>
		      	
		      	<div class="form-gd-hd">内容其他信息</div>
	      		<table class="table table-bordered">
	          		<tr>
	               		<th>标签：</th>
	               		<td>
	               			<input type="text" class="form-control" id="tag" name="tag" value="${content.tag}">
	               		</td>
	               		<th>关键字：</th>
	               		<td>
	               			<input type="text" class="form-control" id="keyword" name="keyword" value="${content.keyword}">
	               		</td>
	          		</tr>
	          		
	          		<tr>
	               		<th>内容模板：</th>
	               		<td>
							<form:select class="form-control" id="contentTpl" path="content.contentTpl" items="${applicationScope.content_tpl__}" itemLabel="value" itemValue="key" cssStyle=""/>
	               		</td>
	               		<th>内容手机模板：</th>
	               		<td>
							<form:select class="form-control" id="contentMobileTpl" path="content.contentMobileTpl" items="${applicationScope.content_mobile_tpl__}" itemLabel="value" itemValue="key" cssStyle=""/>
	               		</td>
	          		</tr>
	          		
	          		<tr>
	               		<th>置顶等级：</th>
	               		<td>
	               			<input type="text" class="form-control" id="topLevel" name="topLevel" value="${content.topLevel}">
	               		</td>
	                	<th>排序号：</th>
	                	<td>
	                		<input type="text" class="form-control" id="orderNum" name="orderNum" value="${content.orderNum}">
	                	</td>
		         	</tr>
							
		         	<tr>
	                	<th>是否推荐：</th>
	                	<td class="radioinput">
	                		<form:radiobuttons id="isRecommend" path="content.isRecommend" items="${applicationScope.is_recommend_}" itemLabel="value" itemValue="key" cssClass=""/>
	                	</td>
	                	<th>是否允许评论：</th>
	                	<td class="radioinput">
	                		<form:radiobuttons id="allowComment" path="content.allowComment" items="${applicationScope.allow_comment_}" itemLabel="value" itemValue="key" cssClass=""/>
	                	</td>
		         	</tr>
		         	
		         	<tr>
	                	<th>是否推荐小程序：</th>
	                	<td class="radioinput">
							<form:radiobuttons id="isSmallpro" path="content.isSmallpro" items="${applicationScope.is_smallpro_}" itemLabel="value" itemValue="key" cssClass=""/>
	                	</td>
		         	</tr>
		         	
		         	<tr>
	                	<th>内容摘要：</th>
	                	<td colspan="3">
	                		<textarea rows="3" class="form-control" name="description" id="description" style="resize: none;" placeholder="请输入内容摘要">${content.description }</textarea>
	                	</td>
		         	</tr>
		         	
		         	<tr>
	                	<th>正文内容：</th>
	                	<td colspan="3">
	                		<input type="hidden" id="content" name="content" value="">
							<script type="text/plain" id="myEditor" style="width: 100%; height: 240px;">${content.content}</script>
	                	</td>
		         	</tr>
		         	
		         	<tr>
		         		<th colspan="5">
		         			<button type="submit" class="btn btn-primary">保存</button> &nbsp;
							<button type="reset" class="btn btn-default">重置</button>
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
    var upload_url = "<%=contextPath%>/upload/img?module_code=cms";
    var param = "";
</script>

<script type="text/javascript" src="<%=contextPath%>/res/plugins/UMeditor/third-party/template.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/UMeditor/umeditor.config.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/UMeditor/umeditor.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/UMeditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">

	var um = UM.getEditor('myEditor');
	$("#content").val(um.getContent());
	
	um.addListener('blur',function(){
        $("#content").val(um.getContent());
    });
	
	$('input[id=titleImgFile]').change(function() { 
		$('#titleImg').val($(this).val()); 
	});
	
	$(function() {
		$("#contentForm").validate({
			rules : {
				title : {
                    required : true,
                    isNotEmpty : true
                },
				author : {
                    required : true,
                    isNotEmpty : true
                },
				contentType : "required",
				topLevel : {
					digits : true
				},
				orderNum : {
					digits : true
				}
			},

			errorPlacement : function(error, element) {
				layer.tips($(error).text(), element, {
					tips : 3,
					tipsMore : true
				});
			}
		});
	});
</script>
</body>
</html>