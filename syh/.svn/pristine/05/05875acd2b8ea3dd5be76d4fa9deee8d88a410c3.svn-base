<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li class="noline">首页</li>
	<li class="active">用户信息</li>
</ol>
<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<div class="panel panel-default form-header">
		<div class="panel-heading">
			<h3 class="panel-title">用户信息</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<form class="form-horizontal">
					<div class="col-md-5  col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name5">
							<span class="highredcolor">*</span>账号</label>
							<div class="col-md-8">
                                 	<input type="text" disabled class="form-control" id="userCode" value="${info.sysUser.userCode }" placeholder="输入用户账号">
							</div>
						</div>
					</div>
				
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name1">
							<span class="highredcolor">*</span>姓名</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="userSname" value="${info.userSname }" placeholder="输入用户姓名">
							</div>
						</div>
					</div>
					<div class="col-md-5  col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name2">
							<span class="highredcolor">*</span>昵称</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="petName" value="${info.petName }"
									placeholder="输入用户昵称">
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name3"><span
								class="highredcolor">*</span>生日</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="birthday" value="${info.birthday }"
									placeholder="输入用户生日">
							</div>
						</div>
					</div>
					<div class="col-md-5  col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label">性别</label>
							<div class="col-md-8">
								<div class="radio nopad-t pad-b7">
									<label> 
										<input type="radio" value="0" name="userSex"
											<c:if test="${info.userSex eq '0'}">
											checked="checked"
											</c:if>
										> 男
									</label> 
									<label> 
										<input type="radio" value="-1" name="userSex"
											<c:if test="${info.userSex eq '-1'}">
											checked="checked"
											</c:if>
										> 女
									</label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-10 col-md-offset-1">
						<button type="button" id="submitBtn" class="btn btn-success myformbtn pull-right">保存</button>
					</div>
				</form>

			</div>
		</div>
	</div>
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/user/indexUser.js"></script>