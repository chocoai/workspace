<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->

<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>报名信息管理</li>
	<li><a href="${contextPath}/System/athleteBaseInfo">运动员基本信息管理</a></li>
	<li class="active">运动员基本信息</li>
</ol>

<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<div class="panel panel-default form-header">
		<div class="panel-heading">
			运动员基本信息</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<form class="form-horizontal" id="formId">
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzydyName1">姓名</label>
							<div class="col-md-7">
								<input type="hidden" value="${info.cid}" id="cid" name="cid">
								<input type="hidden"  id="oldAthleteName" value="${info.athleteName}">
								<input type="text" class="form-control" id="athleteName" name="athleteName" value="${info.athleteName}"
									placeholder="请输入运动员姓名">
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzydyName2">民族</label>
							<input type="hidden" id="nationclick" value="${info.nation}"/>
							<div class="col-md-7" >
								<select class="form-control" id="nation" name="nation"></select>
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzydyName3">简称</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="abbreviation" name="abbreviation"  value="${info.abbreviation}"
									placeholder="请输入运动员简称">
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzydyName4">别名</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="alias" value="${info.alias}"
									placeholder="请输入运动员别名">
							</div>
						</div>
					</div>
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzydyName7">证件号</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="idCard" name="idCard" value="${info.idCard}"
									placeholder="请输入证件号">
									
								<input type="hidden" value="${info.idCard}" id="oldIdCard">	
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
						<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzydyName8">出生日期</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="birthday" name="birthday" value="${info.birthday}"
									placeholder="请输入出生日期">
							</div>
						</div>
					</div>
					
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzydyName5">性别</label>
							<div class="col-md-7">
								<div class="radio-inline sex">
									<label><input type="radio" name="sex" value="0"
										<c:if test="${info.sex eq '0'}">
										checked="checked"
										</c:if>
									>男</label>
								</div>
								<div class="radio-inline sex"> 
									<label><input type="radio" name="sex" value="1"
										<c:if test="${info.sex eq '1'}">
										checked="checked"
										</c:if>
									>女</label>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzydyName6">单位名称</label>
							<div class="col-md-7">
								<div class="input-group">
									<input type="text" class="form-control" data-id="" name="unitName" id="unitName" value="${info.unitInfo.unitName}" placeholder="请输入单位名称"> 
									<input type="hidden" id="unitCid" name="unitCid" value="${info.unitCid}" >
									<input type="hidden" value="${info.unitCid}" id="oldUnitCid">
									<div class="input-group-btn">
										<button style="height: 34.5px" type="button"
											class="btn btn-white dropdown-toggle" data-toggle="dropdown">
											<span class="caret"></span>
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
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzydyName8">参加反兴奋剂培训</label>
							<div class="col-md-7">
								<div class="radio-inline sex">
									<label><input type="radio" name="isExamine" value="0" 
										<c:if test="${info.isExamine eq '0'}">
										checked="checked"
										</c:if>
									>是</label>
								</div>
								<div class="radio-inline sex">
									<label><input type="radio" name="isExamine" value="1"
										<c:if test="${info.isExamine eq '1'}">
										checked="checked"
										</c:if>
									>否</label>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-5 ">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzydyName7">运动员类型</label>
							<input type="hidden" id="athletesTypeclick" value="${info.athletesType}"/>
							<div class="col-md-7" >
								<select class="form-control" name="athletesType" id="athletesType"></select>
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>

					<div class="col-md-10 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-2 control-label" for="xzdwName6">排序号</label>
							<div class="col-md-10" style="width: 79%;"> 
								<input type="text" class="form-control" name="orderNum" value="${info.orderNum}"
									placeholder="请输入排序号">
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
<script src="${contextPath}/static/localjs/athleteBaseInfo/addInfo.js"></script>
<script src="${contextPath}/static/js/bootstrap-suggest.min.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 