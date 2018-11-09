<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<c:choose>
		<c:when test="${not empty channel.channelId }">
			<h4 class="modal-title" id="myModalLabel">编辑栏目</h4>
		</c:when>
		<c:otherwise>
			<h4 class="modal-title" id="myModalLabel">新增栏目</h4>
		</c:otherwise>
	</c:choose>
</div>
<form class="form-horizontal" action="add" id="channelForm" method="post">
	<div class="modal-body">
		<input type="hidden" id="channelId" name="channelId" value="${channel.channelId }" />
		<table class="table table-bordered">
        	<tr>
        		<th>
       				<label class="control-label">上级栏目</label>
       			</th>
          		<td>
					<input type="hidden" name="parentId" value="${parentChannel.channelId}">
					<input type="text" class="form-control" value="${parentChannel.channelName}" readonly="readonly"> 
           		</td>
           		<th>
          			<label class="control-label"><span style="color: red;">* 栏目名称</span></label>
           		</th>
           		<td>
           			<input type="text" class="form-control" id="channelName" name="channelName" placeholder="" value="${channel.channelName}" required="required">
           		</td>
       		</tr>
     
       		<tr>
        		<th>
       				<label class="control-label"><span style="color: red;">* 栏目url</span></label>
       			</th>
          		<td>
					<input type="text" class="form-control" id="channelUrl" name="channelUrl" placeholder="" value="${channel.channelUrl}"  required="required">
           		</td>
           		<th>
          			<label class="control-label">打开方式</label>
           		</th>
           		<td>
           			<form:select class="form-control" id="openType" path="channel.openType" items="${applicationScope.open_type_}" itemLabel="value" itemValue="key" />
           		</td>
       		</tr>
       		
       		<tr>
        		<th>
       				<label class="control-label">栏目模板</label>
       			</th>
          		<td>
					<form:select class="form-control" id="channelTpl" path="channel.channelTpl" items="${applicationScope.channel_tpl_}" itemLabel="value" itemValue="key" />
           		</td>
           		<th>
          			<label class="control-label">内容模板</label>
           		</th>
           		<td>
           			<form:select class="form-control" id="contentTpl" path="channel.contentTpl" items="${applicationScope.content_tpl_}" itemLabel="value" itemValue="key" />
           		</td>
       		</tr>
       		
       		<tr>
        		<th>
       				<label class="control-label">排序号</label>
       			</th>
          		<td>
					<input type="text" class="form-control" id="orderNum" name="orderNum" value="${channel.orderNum}" >
           		</td>
           		<th>
          			<label class="control-label">允许评论</label>
           		</th>
           		<td>
           			<form:select class="form-control" id="allowComment" path="channel.allowComment" items="${applicationScope.allow_comment_}" itemLabel="value" itemValue="key"/>
           		</td>
       		</tr>

			<tr>
        		<th>
       				<label class="control-label">栏目描述</label>
       			</th>
          		<td colspan="3">
					<textarea class="form-control" id="" name="channelDesc" style="resize: none;">${channel.channelDesc}</textarea>
           		</td>
       		</tr>
       		
       		<tr>
        		<th colspan="5">
        			<button type="submit" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</th>
        	</tr>
   		</table>
	</div>
</form>
<script type="text/javascript">
	$(function() {
		$("#channelForm").validate({
			rules : {
				channelName : {
                    required : true,
                    isNotEmpty : true
                },
				channelUrl : {
                    required : true,
                    isNotEmpty : true
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