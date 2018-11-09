<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->

<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>系统配置管理</li>
	<li><a href="${contextPath}/System/sysRole">角色授权管理</a></li>
	<li class="active">角色信息</li>
</ol>

<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<div class="panel panel-default form-header">
		<div class="panel-heading">
				<h3 class="panel-title">角色信息</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="form-horizontal">
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName1">角色名称</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="roleName" value="${info.roleName}" placeholder="请输入角色名称">
								<input type="hidden" value="${info.cid}" id="cid">
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName2">排序号</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="orderNum" value="${info.orderNum}"
									placeholder="请输入排序号">
							</div>
						</div>
					</div>
					<div class="col-md-5  col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label">状态</label>
							<div class="col-md-7">
								<div class="radio nopad-t pad-b7">
									<label> 
										<input type="radio" value="0" name="state"
											<c:if test="${info.state eq '0'}">
											checked="checked"
											</c:if>
										> 启用
									</label> 
									<label> 
										<input type="radio" value="-1" name="state"
											<c:if test="${info.state eq '-1'}">
											checked="checked"
											</c:if>
										> 禁用
									</label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-10 col-md-offset-1">
                       <div class="form-group">
                           <label class="col-md-2 control-label" for="xzInputName13">备注(角色职责)</label>
                           <div class="col-md-10"  style="width: 79%;">
                               <textarea class="form-control" rows="3" id="backup">${info.backup}</textarea>
                           </div>
                       </div>
                     </div>
					<div class="col-md-10 col-md-offset-1">
						<button type="button" id="cancleBtn" class="btn btn-warning myformbtn pull-right">取消</button>
						<button type="button" id="submitBtn" class="btn btn-success myformbtn pull-right">保存</button>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/stsyem/addInfoRole.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%></div>