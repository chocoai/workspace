<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->

<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>报名信息管理</li>
	<li><a href="${contextPath}/System/unitInfo">参赛单位信息管理</a></li>
	<li class="active">参赛单位信息</li>
</ol>

<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<div class="panel panel-default form-header">
		<div class="panel-heading">
			<h3 class="panel-title">参赛单位信息</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<form class="form-horizontal" id="formId">
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName1">单位名称</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="unitName" name="unitName" value="${info.unitName}" placeholder="请输入单位名称">
								<input type="hidden" value="${info.cid}" id="cid" name="cid">
								<input type="hidden" value="${info.unitName}" id="oldUnitName">
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName2">单位简称</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="abbreviation" name="abbreviation" value="${info.abbreviation}"
									placeholder="请输入单位简称">
							</div>
						</div>
					</div>
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName4">运动员数量</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="athleteNum" value="${info.athleteNum}"
									placeholder="请输入运动员数量">
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName5">联系人</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="unitContact" value="${info.unitContact}"
									placeholder="请输入单位联系人">
							</div>
						</div>
					</div>
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName6">联系电话</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="contactPhone" value="${info.contactPhone}"
									placeholder="请输入单位联系电话">
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName6">排序号</label>
							<div class="col-md-7"> 
								<input type="text" class="form-control" name="orderNum" value="${info.orderNum}"
									placeholder="请输入排序号">
							</div>
						</div>
					</div>
					<div class="col-md-10 col-md-offset-1">
                       <div class="form-group">
                           <label class="col-md-2 control-label" for="xzInputName13">备注</label>
                           <div class="col-md-10" style="width: 79%;">
                               <textarea class="form-control" rows="3" name="backup">${info.backup}</textarea>
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
<script src="${contextPath}/static/localjs/signUp/addInfo.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 