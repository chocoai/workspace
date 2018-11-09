<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 start -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>奖惩信息管理</li>
	<li><a href="${contextPath}/System/pubRewInfo">参赛奖惩信息管理</a></li>
	<li class="active">惩罚信息</li>
</ol>

<!-- 当前位置 end -->

<!-- 中间内容 start -->
<div class="content">
   <div class="row">
      <div class="col-md-12">
          <ul class="nav nav-tabs mynav-tabs nomar_b20">
	          <shiro:hasPermission name="/pubRewInfo/addRew">
	            	<li role="presentation" class="active"><a  href="addRew">新增奖励信息</a></li>
	          </shiro:hasPermission>
	          <shiro:hasPermission name="/pubRewInfo/addPunish">
	            	<li role="presentation" ><a href="addPunish">新增处罚信息</a></li>
	          </shiro:hasPermission>
          </ul>
      </div>
   </div>
	<div class="panel panel-default form-header">
		<div class="panel-heading">
			<h3 class="panel-title">新增惩罚信息</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="form-horizontal">
 					<div class="col-md-10">
						<div class="form-group">
							<label class="col-md-3 control-label">项目名称</label>
							<div class="col-md-8">
								<div class="input-group">
									<input type="text" class="form-control" data-id=""
										id="projectName" placeholder="请输入项目名称" readonly="readonly"> <input type="hidden" id="projectCid">
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
							<label class="col-md-3 control-label" for="unitName">报名信息</label>
							<div class="col-md-8">
							    <select id="participatId" name="participatId"  class="js-example-basic-single form-control">
					
								</select>
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
							<!-- 		<option value="0">奖励</option> -->
							 	<option value="1">惩罚</option> 
								</select>
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-10">
						<div class="form-group">
							<label class="col-md-3 control-label">取消奖牌</label>
							<div class="col-md-3" style="margin-top: 5px;">
                                 <input type="checkbox" style="zoom:180%;" id="isMedal"  checked="checked" name="isMedal" >
							</div>
							<label class="col-md-2 control-label" for="intrgralCount">取消积分</label>
							<div class="col-md-2" style="margin-top: 5px;">
                                 <input type="checkbox" style="zoom:180%;" id="isIntrgral" checked="checked"  name="isIntrgral" >
							</div>
<!-- 							<div class="col-md-4">
								<input type="text" id="medalCount" class="form-control" name="medalCount"
									placeholder="输入奖牌数量">
							</div> -->
						</div>
					</div>
					<div class="col-md-10">
						<div class="form-group">
							<label class="col-md-3 control-label">取消成绩</label>
							<div class="col-md-3" style="margin-top: 5px;">
                                 <input type="checkbox" style="zoom:180%;" id="isScores"  checked="checked" name="isScores" >
							</div>
							<label class="col-md-2 control-label" for="intrgralCount">取消名次</label>
							<div class="col-md-2" style="margin-top: 5px;">
                                 <input type="checkbox" style="zoom:180%;" id="isRanking" checked="checked"  name="isRanking" >
							</div>
						</div>
					</div>
					<div class="col-md-10">
						<div class="form-group">
<!-- 							<div class="col-md-8">
								<input type="text" class="form-control" id="intrgralCount"
									placeholder="请输入奖励积分">
							</div> -->
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
<script src="${contextPath}/static/localjs/pubRewInfo/addPunish.js?q=1"></script>
<script src="${contextPath}/static/js/bootstrap-suggest.min.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%></div>