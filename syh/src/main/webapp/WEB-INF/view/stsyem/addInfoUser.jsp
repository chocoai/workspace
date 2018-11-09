<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li class="noline">首页</li>
	<li>系统配置管理</li>
	<li><a href="${contextPath}/System/sysMuserInfo">系统用户管理</a></li>
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
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name1">姓名</label>
							<div class="col-md-7">
								<input type="hidden" value="${info.cid}" id="cid">
								<input type="text" class="form-control" id="userSname" value="${info.userSname }" placeholder="输入用户姓名">
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name2">昵称</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="petName" value="${info.petName }"
									placeholder="输入用户昵称">
							</div>
						</div>
					</div>
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name3">生日</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="birthday" value="${info.birthday }"
									placeholder="输入用户生日">
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label">性别</label>
							<div class="col-md-7">
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
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name5">账号</label>
							<div class="col-md-7">
                                <input type="hidden" id="oldUserCode" value="${info.sysUser.userCode }" >
                                <c:if test="${empty info.cid}">
                                 	<input type="text" class="form-control" id="userCode" value="${info.sysUser.userCode }" placeholder="输入用户账号">
                            	</c:if>
                                <c:if test="${not empty info.cid}">
									<div class="input-group">
										<input type="hidden" id="userCid" value="${info.sysUser.cid}">
                                    	<input type="text" class="form-control" disabled id="userCode" value="${info.sysUser.userCode }" placeholder="输入用户账号"> 
                                   		<span class="input-group-btn"><button id="isDisabled" type="button" class="btn btn-warning">修改账号</button></span>
                                	</div>
                               	</c:if>
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name7">账号密码</label>
							<div class="col-md-7">
								 <input type="hidden" id="oldPassword" value="${info.sysUser.password}">
								 <c:if test="${empty info.cid}">
									<input type="text" class="form-control" id="password" placeholder="输入账号密码">
								 </c:if>
								 <c:if test="${not empty info.cid}">
									<input type="text" class="form-control" id="password" placeholder="不输表示不修改密码">
								 </c:if>
							</div>
						</div>
					</div>
					<div class="col-md-5  col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzydyName6">角色</label>
							<div class="col-md-7">
								<div class="input-group">
									<input type="text" class="form-control" data-id="" id="roleName" value="${info.sysRole.roleName}" placeholder="请输入单位名称"> 
									<input type="hidden" id="roleCid" value="${info.sysRole.cid}" >
									<div class="input-group-btn">
										<button style="height: 34.5px" type="button"
											class="btn btn-white dropdown-toggle" data-toggle="dropdown">
										</button>
										<ul class="dropdown-menu dropdown-menu-right" role="menu">
										</ul>
									</div>
								</div>
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name7">排序号</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="orderNum" value="${info.orderNum }"
									placeholder="输入排序号">
							</div>
						</div>
					</div>
					<div class="col-md-10 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-2 control-label" for="Name9">备注</label>
							<div class="col-md-10" style="width: 79%;">
								<textarea class="form-control" rows="4" id="backup">${info.backup }</textarea>
							</div>
						</div>
					</div>
					<div class="col-md-10 col-md-offset-1">
						<button type="button" id="cancleBtn" class="btn btn-warning myformbtn pull-right">取消</button>
						<button type="button" id="submitBtn" class="btn btn-success myformbtn pull-right">保存</button>
					</div>
				</form>

			</div>
		</div>
	</div>
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/stsyem/addInfoUser.js"></script>
<script src="${contextPath}/static/js/bootstrap-suggest.min.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 