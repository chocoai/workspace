<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>系统用户管理</li>
	<li class="active">系统信息</li>
</ol>
<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<div class="panel panel-default form-header">
		<div class="panel-heading">
			<h3 class="panel-title">系统信息</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<form class="form-horizontal">
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name1">
							<span class="highredcolor"></span>站点名称</label>
							<div class="col-md-8">
								<input type="hidden" value="${info.cid}" id="cid">
								<input type="text" class="form-control" id="siteName" value="${info.siteName }" placeholder="输入站点名称">
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name2">
							<span class="highredcolor"></span>站点简称</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="sitePame" value="${info.sitePame }"
									placeholder="站点简称">
							</div>
						</div>
					</div>
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name3"><span
								class="highredcolor"></span>站点地址</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="siteUrl" value="${info.siteUrl }"
									placeholder="输入站点地址">
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="Name3"><span
								class="highredcolor"></span>LOGO地址</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="logoUrl" value="${info.logoUrl }"
									placeholder="输入LOGO地址">
							</div>
						</div>
					</div>
					<div class="col-md-10 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-2 control-label" for="Name9">脚本文本</label>
							<div class="col-md-10">
								<textarea class="form-control" rows="4" id="boottomText">${info.boottomText }</textarea>
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
<script src="${contextPath}/static/localjs/stsyem/indexConfig.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 