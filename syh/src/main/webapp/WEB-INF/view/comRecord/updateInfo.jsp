<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 start -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>成绩信息管理</li>
	<li><a href="${contextPath}/System/comRecord">竞赛项目记录库</a></li>
	<li class="active">新增竞赛项目记录</li>
</ol>

<!-- 当前位置 end -->

<!-- 中间内容 start -->
<div class="content">
	<div class="panel panel-default form-header">
		<div class="panel-heading">
			<h3 class="panel-title">新增竞赛项目记录</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="form-horizontal">
					<div class="col-md-11">
						<div class="form-group">
							<label class="col-md-2 control-label" for="xzydyName1">项目名称</label>
							<div class="col-md-9">
								<input type="text" class="form-control" value="${comRecord.projectManager.projectName}" id="projectName2" readonly>
								<input type="hidden" value="${comRecord.projectCid}" id="projectCid">
								<input type="hidden" value="${comRecord.cid}" id="cid">
							</div>
						</div>
					</div>
					<div class="col-md-11">
						<div class="form-group">
							<label class="col-md-2 control-label">成绩单位:</label> 
							<div class="col-md-9">
								<input type="text" class="form-control" id="resultUnit"
									<c:choose>  
					    				<c:when test="${comRecord.projectManager.resultType==0}">  
					      					value='只录取名次'
					   					</c:when> 
					   					<c:when test="${comRecord.projectManager.resultType==1}">  
					      					value='时间下行（时间越短成绩越高）'
					   					</c:when>
					   					<c:when test="${comRecord.projectManager.resultType==2}">  
					      					value='时间上行（时间越长成绩越高）'
					   					</c:when>
					   					<c:when test="${comRecord.projectManager.resultType==3}">  
					      					value='数值上行（数值越大成绩越高）'
					   					</c:when>
					   					<c:when test="${comRecord.projectManager.resultType==4}">  
					      					value='数值下行（数值越小成绩越高）'
					   					</c:when>
					   				</c:choose> 
								readonly>
							</div>
						</div>
					</div>
					<div class="col-md-11">
						<div class="form-group">
							<label class="col-md-2 control-label" for="recordHolder">最高记录保持者</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="recordHolder"
									value="${comRecord.recordHolder}">
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-11">
						<div class="form-group">
							<label class="col-md-2 control-label" for="xzydyName2">最高记录成绩</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="recordScore"
									value="${comRecord.recordScore}" name="recordScore" required>
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-11">
						<div class="form-group">
							<label class="col-md-2 control-label" for="recordCreatetime">最高记录创造时间</label>
							<div class="col-md-9">
								<input type="text" id="datepicker" name="datepicker" class="form-control" 
									value="${comRecord.recordCreatetime}">
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-11">
						<div class="form-group">
							<label class="col-md-2 control-label" for="backUp">备注</label>
							<div class="col-md-9">
								<textarea  class="form-control" rows="3" id="backUp">${comRecord.backup}</textarea>
							</div>
						</div>
					</div>
					<div class="col-md-10">
						<button type="button" id="cancleBtnTo" class="btn btn-warning myformbtn pull-right">取消</button>
						<button type="button" id="submitBtn" class="btn btn-success myformbtn pull-right">保存</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/comRecord/updateInfo.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%></div>