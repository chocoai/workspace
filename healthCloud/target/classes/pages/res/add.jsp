<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style type="text/css">
	.radioinput span { margin-right:10px; line-height:34px; vertical-align:middle;}
	.radioinput span label { margin-bottom:0;}
</style>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">编辑权限资源</h4>
</div>

<form class="form-horizontal" action="add" id="resForm" method="post">
	<div class="modal-body">
		<input type="hidden" name="resId" value="${res.resId}"> 
		<input type="hidden" name="resCode" value="${res.resCode}">
		<table class="table table-bordered">
        	<tr>
        		<th>
       				<label class="control-label">上级资源</label>
       			</th>
          		<td>
					<input type="text" class="form-control" value="${parentRes.resName}" readonly="readonly"> 
					<input type="hidden" name="parentResId" value="${parentRes.resId}">
           		</td>
           		<th>
          			<label class="control-label"><span style="color: red;">* 资源名称</span></label>
           		</th>
           		<td>
           			<input type="text" class="form-control" id="resName" name="resName" placeholder="输入资源名称" value="${res.resName}" required="required">
           		</td>
       		</tr>
		
       		<tr>
           		<th>
           			<label class="control-label">资源地址</label>
           		</th>
           		<td>
					<input type="text" class="form-control" id="resUrl" name="resUrl" value="${res.resUrl}" >
           		</td>
           		<th>
           			<label class="control-label"><span style="color: red;">* 权限编码</span></label>
           		</th>
           		<td>
					<input type="text" class="form-control" id="permission" name="permission" value="${res.permission}">
           		</td>
       		</tr>
       		
       		<tr>
       			<th><label class="control-label">资源类型</label></th>
        		<td class="radioinput">
                	<form:radiobuttons id="resType" path="res.resType" items="${applicationScope.res_type_}" itemLabel="value" itemValue="key" cssClass=""/>
                </td>
           		<th>
          			<label class="control-label">全局资源</label>
           		</th>
           		<td class="radioinput">
                	<form:radiobuttons id="isGlobal" path="res.isGlobal" items="${applicationScope.is_global_}" itemLabel="value" itemValue="key" cssClass=""/>
                </td>
           		
       		</tr>
       		
       		
       		<tr>
       			<th>
		       		<label class="control-label">备注</label>
       			</th>
       			<td colspan="3">
					<textarea class="form-control" id="remark" name="remark" style="resize: none;">${res.remark}</textarea>
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
		$("#resForm").validate({
			rules : {
				resName : "required",
				permission : "required",
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