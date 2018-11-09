<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<c:choose>
		<c:when test="${area.areaId != null}">
			<h4 class="modal-title" id="myModalLabel">编辑区域</h4>
		</c:when>
		<c:otherwise>
			<h4 class="modal-title" id="myModalLabel">新增区域</h4>
		</c:otherwise>
	</c:choose>
</div>

<form class="form-horizontal" action="add" id="areaForm" method="post">
	<div class="modal-body">
		<input type="hidden" name="areaId" value="${area.areaId}">
		<table class="table table-bordered">
        	<tr>
        		<th>
       				<label class="control-label">上级区域</label>
       			</th>
          		<td>
					<input type="text" class="form-control" value="${parentArea.areaName}" readonly="readonly"> 
					<input type="hidden" name="parentId" value="${parentArea.areaId}">
           		</td>
           		<th>
          			<label class="control-label"><span style="color: red;">* 区域名称</span></label>
           		</th>
           		<td>
           			<input type="text" class="form-control" id="areaName" name="areaName" value="${area.areaName}" placeholder="请输入区域名称"  required="required">
           		</td>
       		</tr>
		
       		<tr>
        		<th>
       				<label class="control-label">区域类型</label>
       			</th>
          		<td>
					<form:select class="form-control" id="areaType" path="area.areaType" items="${applicationScope.area_type__}" itemLabel="value" itemValue="key"/>
           		</td>
           		<th>
          			<label class="control-label"><span style="color: red;">* 区域编码</span></label>
           		</th>
           		<td>
           			<input type="text" class="form-control" id="areaCode" name="areaCode" value="${area.areaCode}" placeholder="请参考民政部行政区划编码" >
           		</td>
       		</tr>
       		
       		<tr>
        		<th>
        			<label class="control-label">所属区域</label>
       			</th>
          		<td>
					<form:select class="form-control" id="region" path="area.region" items="${applicationScope.region__}" itemLabel="value" itemValue="key" />
           		</td>
           		<th>
          			<label class="control-label">排序号</label>
           		</th>
           		<td>
           			<input type="text" class="form-control" id="orderNum" name="orderNum" value="${area.orderNum}" digits="true">
           		</td>
       		</tr>
       		
       		<tr>
       			<th>
		       		<label class="control-label">区域描述</label>
       			</th>
       			<td colspan="3">
					<textarea class="form-control" id="areaDesc" name="areaDesc" style="resize: none;" >${area.areaDesc}</textarea>
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
		$("#areaForm").validate({
			rules : {
				areaName : "required",
				areaCode : "required",
				orderNum : {
					digits : true
				}
			},
			errorPlacement:function(error,element) {
				layer.tips($(error).text(), element, {
					tips: 3,
					tipsMore: true
				});
			}
		});
	});
</script>