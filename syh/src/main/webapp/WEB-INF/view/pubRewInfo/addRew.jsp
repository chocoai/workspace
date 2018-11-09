<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 start -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>奖惩信息管理</li>
	<li><a href="${contextPath}/System/pubRewInfo">参赛奖惩信息管理</a></li>
	<li class="active">奖励信息</li>
</ol>

<!-- 当前位置 end -->

<!-- 中间内容 start -->
<div class="content">
   <div class="row">
      <div class="col-md-12">
          <ul class="nav nav-tabs mynav-tabs nomar_b20">
	          <shiro:hasPermission name="/pubRewInfo/addRew">
	            	<li role="presentation"><a href="addRew">新增奖励信息</a></li>
	          </shiro:hasPermission>
	          <shiro:hasPermission name="/pubRewInfo/addPunish">
	            	<li role="presentation" class="active"><a href="addPunish">新增处罚信息</a></li>
	          </shiro:hasPermission>
          </ul>
      </div>
   </div>
	<div class="panel panel-default form-header">
		<div class="panel-heading">
			<h3 class="panel-title">新增奖励信息</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="form-horizontal">
<%-- 					<div class="col-md-10">
						<div class="form-group">
							<label class="col-md-3 control-label">群体类别</label>
							<div class="col-md-8">
								<select id="projectCid" class="js-example-basic-single form-control">
								<option value="0">请选择群体类别</option>
									<c:forEach var="classManager" items="${classManagers}">
										<option value="${classManager.cid}">${classManager.classifyName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div> --%>
					<div class="col-md-10">
						<div class="form-group">
							<label class="col-md-3 control-label" for="unitName">参赛单位</label>
							<div class="col-md-8">
								<div class="input-group">
									<input type="text" class="form-control" data-id=""
										id="unitName" placeholder="请输入参赛单位名称"> <input type="hidden" id="unitCid">
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
					<div class="col-md-10">
						<div class="form-group">
							<label class="col-md-3 control-label">类型</label>
							<div class="col-md-8">
								<select id="infoType" class="form-control">
									<option value="0">奖励</option>
							<!-- 	<option value="1">惩罚</option> -->
								</select>
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-10">
						<div class="form-group">
							<label class="col-md-3 control-label">奖牌</label>
							<div class="col-md-4">
								<select id="medalType" class="form-control">
									<option value="0">金牌</option>
			<!-- 						<option value="1">银牌</option>
									<option value="2">铜牌</option> -->
								</select>
							</div>
							<div class="col-md-4">
								<input type="text" id="medalCount" class="form-control" name="medalCount"
									placeholder="输入奖牌数量">
							</div>
						</div>
					</div>
					<div class="col-md-10">
						<div class="form-group">
							<label class="col-md-3 control-label" for="intrgralCount">积分</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="intrgralCount"
									placeholder="请输入奖励积分">
							</div>
						</div>
					</div>
					<div class="col-md-10">
						<div class="form-group">
							<label class="col-md-3 control-label" for="reason">原因</label>
							<div class="col-md-8">
								<textarea class="form-control" rows="3" id="reason"></textarea>
							</div>
						</div>
					</div>
					<div class="col-md-10">
						<div class="form-group">
							<label class="col-md-3 control-label" for="backup">备注</label>
							<div class="col-md-8">
								<textarea class="form-control" rows="3" id="backup"></textarea>
							</div>
						</div>
					</div>
					<div class="col-md-9">
						<button id="cancleBtn" type="button"
							class="btn btn-warning myformbtn pull-right">取消</button>
						<button id="submitBtn" type="button"
							class="btn btn-success myformbtn pull-right">提交</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/pubRewInfo/addRew.js?d=2"></script>
<script src="${contextPath}/static/js/bootstrap-suggest.min.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%></div>