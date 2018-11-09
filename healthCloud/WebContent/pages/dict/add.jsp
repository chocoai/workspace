<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<c:choose>
		<c:when test="${dict.dictId != null}">
			<h4 class="modal-title" id="myModalLabel">编辑数据字典</h4>
		</c:when>
		<c:otherwise>
			<h4 class="modal-title" id="myModalLabel">新增数据字典</h4>
		</c:otherwise>
	</c:choose>
</div>
<form class="form-horizontal" action="add" id="dictForm"  method="post" enctype="multipart/form-data">
	<div class="modal-body">
		<input type="hidden" name="dictId" value="${dict.dictId}">
		<table class="table table-bordered">
        	<tr>
        		<th>
       				<label class="control-label"><span style="color: red;">* 中文名称</span></label>
       			</th>
          		<td>
					<input type="hidden" name="parentId" value="${dict.parentId}">
					<input type="text" class="form-control" id="dictZhName" name="dictZhName" placeholder="请输入字典中文名称" value="${dict.dictZhName}" required="required">
           		</td>
           		<th>
          			<label class="control-label"><span style="color: red;">* 英文名称</span></label>
           		</th>
           		<td>
           			<input type="text" class="form-control" id="dictEnName" name="dictEnName" placeholder="请输入字典英文名称" value="${dict.dictEnName}" required="required">
           		</td>
       		</tr>
		
       		<tr>
        		<th>
           			<label class="control-label"><span style="color: red;">* 字典key</span></label>
       			</th>
          		<td>
					<input type="text" class="form-control" id="dictKey" name="dictKey" placeholder="请输入字典key" value="${dict.dictKey}" required="required">
           		</td>
           		<th>
					<label class="control-label"><span style="color: red;">* 字典值</span></label>
           		</th>
           		<td>
           			<input type="text" class="form-control" id="dictValue" name="dictValue" placeholder="请输入字典值" value="${dict.dictValue}" required="required">
           		</td>
       		</tr>
       		
       		<tr>
           		<th>
          			<label class="control-label">字典图标</label>
           		</th>
           		<td colspan="3">
      				<input type="file" id="dictIconFile" name="dictIconFile" accept="image/jpg,image/jpeg,image/png,image/gif" style="display: none;">
					<div class="input-group">
						<input type="text" id="dictIcon" name="dictIcon" value="${dict.dictIcon}" class="form-control" style="width:360px;" readonly="readonly"> 
						<span class="input-group-btn"> 
							<a class="btn btn-default" onclick="$('input[id=dictIconFile]').click();">浏览</a> 
							<c:if test="${not empty dict.dictIcon}">
								<a class="btn btn-default" href="${dict.dictIcon}" target="_blank">查看</a>
							</c:if>
						</span>
					</div>
           		</td>
       		</tr>
       		
       		<tr>
       			<th>
		       		<label class="control-label">字典描述</label>
			</div>
       			</th>
       			<td colspan="3">
					<textarea class="form-control" id="dictDesc" maxlength="256" name="dictDesc" placeholder="请输入字典描述信息"  style="resize: none;">${dict.dictDesc}</textarea>
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
	$('input[id=dictIconFile]').change(function() {
		$('#dictIcon').val($(this).val());
	});
</script>