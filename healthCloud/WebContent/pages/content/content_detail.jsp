<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/UMeditor/themes/default/css/umeditor.css">
<style type="text/css">
	.radioinput span { margin-right:10px; line-height:34px; vertical-align:middle;}
	.radioinput span label { margin-bottom:0;}
</style>

</head>
<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs" style="margin-bottom: 10px;">
			<li><a href="list?channelId=${channel.channelId}">内容列表</a></li>
			<li class="active"> <a href="#">内容详情</a></li>
		</ul>
		
		<div class="modal fade bs-example-modal-sm" id="channelTree" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
			<div class="modal-dialog" style="width: 450px;" role="document">
				<div class="modal-content"></div>
			</div>
		</div>
		
		<div class="col-sm-12">
			<form class="form-horizontal pull-left form-normallr" action="" id="contentForm" method="post" enctype="multipart/form-data">
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
	         			<button type="button" class="btn btn-info" onclick="javascript:window.history.back();">返回上一页</button>
					</th>
	         	</tr>
      		</table>
	    	</form>
    	</div>
	</div>
	
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
	
	um.setDisabled();
	
	$('input[type="text"]').attr('readonly','readonly');
	$('input[type="radio"]').attr('disabled','disabled');
	$('select').attr('disabled','disabled');
	$('textarea').attr('disabled','disabled');
	

</script>
</body>
</html>