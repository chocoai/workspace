<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->

<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>报名信息管理</li>
	<li><a href="${contextPath}/System/inputScore">运动员参赛信息</a></li>
	<li class="active">新增参赛信息</li>
</ol>
<!-- 当前位置 end -->

<!-- 中间内容 start -->
<div class="content">
	<div class="panel panel-default form-header">
		<div class="panel-heading">
			<h3 class="panel-title">新增参赛信息</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="form-horizontal">
					<div class="col-md-10">
						<div class="form-group" style="position:relative">
							<label class="col-md-3 control-label" for="xzydyName1">项目名称</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="请输入项目名称" id="projectName">
								<input type="hidden" id="projectCid">
								<input type="hidden" id="projectIsTeam">
							</div>
							<div class="col-md-1" style="padding-left:0;position:absolute;left:100%">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-10">
						<div class="form-group">
							<label class="col-md-3 control-label">成绩单位</label> 
							<div class="col-md-9">
								<input type="text" class="form-control" id="resultUnit"
									placeholder="请先选择比赛项目" readonly>
								<input type="hidden" id="resultType">
							</div>
						</div>
					</div>
					<div class="col-md-10"  id="addAthleteRow">
						<div class="form-group">
							<label class="col-md-3 control-label">运动员姓名</label>
							<div class="col-md-3 ">
								<input type="text" class="form-control" name="athleteName"
									placeholder="输入运动员姓名">
							</div>
							<div class="col-md-1" style="padding-left:0;position:absolute;left:50%">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
					        <div class="col-md-1" style="padding-left:0;position:absolute;left:100%">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
							<label class="col-md-2 control-label" style="margin-left:-15px">单位名称</label>
							<div class="input-group col-md-4" style="position:relative">
								<input type="text"  class="form-control" name="unitName"
									placeholder="输入单位名称">
								<input type="hidden" name="unitCid"/> 
								<div>
									<ul  class="dropdown-menu dropdown-menu-right" role="menu">
									</ul>
								</div>
								<div class="col-md-1" style="position:absolute;left:100%">
									<i id="addAthleteBtn" class="icon-plus pull-right add "></i>
							 	</div>
							</div>
						 </div>
					</div>
					<div class="col-md-10"  id="addAthleteRow">
						<div class="form-group">
							<label class="col-md-3 control-label">名次</label>
							<div class="col-md-3 ">
								<input type="text" class="form-control" id="ranking"
									placeholder="输入运动员名次">
							</div>
							<div class="col-md-1" style="padding-left:0;position:absolute;left:50%">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
							<div id="isInputScores" class="hidden">
							<label class="col-md-2 control-label" style="margin-left:-15px">成绩</label>
							<div  class="input-group col-md-4 ">
								<input type="text"  class="form-control" id="scores"
									placeholder="运动员成绩">
							</div>
							<div class="col-md-1" style="padding-left:0;position:absolute;right:-80px;top:0px">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
							</div>
						 </div>
					</div>
					<div class="col-md-10">
						<div class="form-group">
							<label class="col-md-3 control-label" for="backUp">备注</label>
							<div class="col-md-9">
								<textarea class="form-control" rows="3" id="backUp"></textarea>
							</div>
						</div>
					</div>
					<div class="col-md-10">
						<button type="button" id="cancleBtn" class="btn btn-warning myformbtn pull-right">取消</button>
						<button type="button" id="submitBtn" class="btn btn-success myformbtn pull-right">保存</button>
						<input type="hidden" id="backIndex" value="1" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 中间内容 end -->
<%@include file="/WEB-INF/view/scoreManager/allotScoresModal.jsp"%>
<script src="${contextPath}/static/localjs/scoreManager/inputScore/addAthleteParticipat.js?a=1"></script>
<script src="${contextPath}/static/js/bootstrap-suggest.min.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%>
