<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<c:choose>
		<c:when test="${role.roleId != null}">
			<h4 class="modal-title" id="myModalLabel">编辑角色</h4>
		</c:when>
		<c:otherwise>
			<h4 class="modal-title" id="myModalLabel">新增角色</h4>
		</c:otherwise>
	</c:choose>
</div>

<form class="form-horizontal" action="add" id="roleForm" method="post">
	<div class="modal-body">
		<input type="hidden" name="roleId" value="${role.roleId}">
		<table class="table table-bordered">
        	<tr>
        		<th>
           			<label class="control-label"><span	style="color: red;">* 角色名称</span></label>
       			</th>
          		<td>
					<input type="text" class="form-control" id="roleName" name="roleName" placeholder="请输入角色名称" value="${role.roleName}" required="required">
           		</td>
           		<th>
					<label class="control-label">排序号</label>
           		</th>
           		<td>
					<input type="text" class="form-control" id="orderNum" digits="true" name="orderNum" value="${role.orderNum}">
           		</td>
       		</tr>
		
       		<tr>
        		<th>
           			<label class="control-label">角色分类</label>
       			</th>
          		<td colspan="3">
					<div class="radio">
						<c:forEach var="roles" items="${roleList }">
							<c:if test="${roles.status == 999 }">
	                			<input type="checkBox" name="childIds" value="${roles.roleId }" checked="checked" />${roles.roleName } &nbsp;&nbsp;
							</c:if>
							<c:if test="${roles.status != 999 }">
	                			<input type="checkBox" name="childIds" value="${roles.roleId }" />${roles.roleName } &nbsp;&nbsp;
							</c:if>
		           		</c:forEach>
           			</div>
           		</td>
       		</tr>
       		
       		<tr>
       			<th>
		       		<label class="control-label">角色描述</label>
       			</th>
       			<td colspan="3">
					<textarea class="form-control" id="roleDesc" name="roleDesc" placeholder="请输入角色描述信息" style="resize: none;">${role.roleDesc}</textarea>
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
		$("#roleForm").validate({
			rules : {
				roleName : "required",
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