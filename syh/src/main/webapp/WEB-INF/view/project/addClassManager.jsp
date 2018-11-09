<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<ol class="breadcrumb location" style="background-color: #f5f5f5;padding: 8px 15px;margin-bottom: 20px;">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>项目信息管理</li>
	<li><a href="${contextPath}/System/classManager">项目分类管理</a></li>
	<li class="active">项目分类信息</li>
</ol>
<!-- 当前位置 end -->
<!-- 中间内容 strat -->
<div class="content">
	<div class="panel panel-default form-header">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a href="javaScript:void(0)" onClick="$('#panel-body_collapse').click()">项目分类信息</a>
				<a data-toggle="collapse" href="#panel-body" id="panel-body_collapse"></a>
				<a data-toggle="collapse" href="#row" id="row_collapse"></a>
				<a data-toggle="collapse" href="#row1" id="row1_collapse"></a>
				<a data-toggle="collapse" href="#row2" id="row2_collapse"></a>
			</h3>
		</div>
		<div class="panel-body  collapse in" id="panel-body">
			<form class="form-horizontal" id="formId">
				<div class="row collapse in" id="row">
					<div class="form-horizontal">
						<div class="col-md-5 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-4 control-label" for="classifyName">项目分类名称</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" id="classifyName" name="classifyName" value="${classManager.classifyName}" placeholder="请输入项目分类名称"> 
									<input type="hidden" value="${classManager.cid}" id="cid" name="cid"> 
									<input type="hidden" value="${classManager.classifyName}" id="oldClassifyName">
								</div>
								<div class="col-md-1" style="padding-left: 0;">
									<span style="color: red; font-size: 20px; line-height: 34px;">*</span>
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="col-md-4 control-label" for="className">上级分类名称</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" id="className" name="className" value="${classManager.manager.classifyName}" disabled="disabled"> 
									
									<input type="hidden" value="${classManager.classCid}" id="classCid" name="classCid">
									<input type="hidden" value="${classManager.classCid}" id="oldClassCid">
								</div>
								<div class="col-md-1" style="padding-left: 0;">
									<span style="color: red; font-size: 20px; line-height: 34px;">*</span>
								</div>
							</div>
						</div>
						<div class="col-md-5 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-4 control-label" for="alias">项目分类别名</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" name="alias"
										value="${classManager.alias}" placeholder="请输入项目分类别名">
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="col-md-4 control-label" for="alias">是否添加限制</label>
		                        <div class="col-md-7" style="padding-top: 7px;">
		                            <div class="onoffswitch">
		                                <input type="checkbox" class="onoffswitch-checkbox" checked="checked" id="limit">
		                                <label class="onoffswitch-label" for="limit">
		                                    <span class="onoffswitch-inner" onClick="$('#row1_collapse').click()"></span>
		                                    <span class="onoffswitch-switch" onClick="$('#row1_collapse').click()"></span>
		                                </label>
		                            </div>
		                        </div>
				              </div>
						</div>
					</div>
				</div>
				<div class="row  collapse" id="row1">
					<div class="form-horizontal">
					
						<div class="col-md-5 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-4 control-label" for="athletesNum">运动员总数</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" id="athletesNum"
										name="athletesNum" value="${classManager.athletesNum}"
										placeholder="请输入运动员总数"
										onkeyup="value=value.replace(/[^\d]/g,'')">
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="col-md-4 control-label" for="personTotnum" style="margin-right: 14px;">男女运动员人数</label>
								<div class="col-md-7 input-daterange input-group" style="width: 54%;">
	                                <span class="input-group-addon">男</span>
	                                <input type="text" class="input-sm form-control" id="manNum" name="manNum" value="${classManager.manNum}" onkeyup="value=value.replace(/[^\d]/g,'')" style="height: 34px;"/>
	                                <span class="input-group-addon">女</span>
	                                <input type="text" class="input-sm form-control" id="womanNum" name="womanNum" value="${classManager.womanNum}" onkeyup="value=value.replace(/[^\d]/g,'')" style="height: 34px;"/>
                            	</div>
							</div>
						</div>
						<div class="col-md-5 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-4 control-label" for="ageSum">年龄相加之和</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" id="ageSum"
										name="ageSum" value="${classManager.ageSum}"
										placeholder="请输入年龄相加之和"
										onkeyup="value=value.replace(/[^\d]/g,'')">
								</div>
							</div>
						</div>
						<div class="col-md-5">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="isCrossGroup">是否可跨组参赛</label>
			                   <div class="col-md-7" style="padding-right:5px;">
			                       <div class="radio-inline sex">
			                         	<label><input type="radio" name="isCrossGroup"  value="0" <c:if test="${projectManager.isCrossGroup eq '0'}">checked="checked"</c:if>>是</label>
			                      </div>
			                      <div class="radio-inline sex">
			                         	<label><input type="radio" name="isCrossGroup"  value="1" <c:if test="${projectManager.isCrossGroup eq '1'}">checked="checked"</c:if>>否</label>
			                      </div>
			                   </div>
			                   <div class="col-md-1" style="padding-left:0;">
						        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
						        </div>
			                 </div>
			               </div>
						<div class="col-md-5 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-4 control-label" for="personTotnum" style="margin-right: 2px;">组内男性年龄范围</label>
							
								<div class="col-md-7 input-daterange input-group">
	                                <input type="text" class="input-sm form-control" name="groupManAgeStart" id="groupManAgeStart" value="${classManager.groupManAgeStart}" readonly style="width: 90%;float: right;height: 34px;"/>
	                                <span class="input-group-addon" style="background-color: #fff;">到</span>
	                                <input type="text" class="input-sm form-control" name="groupManAgeEnd" id="groupManAgeEnd" value="${classManager.groupManAgeEnd}" readonly style="width: 93%;height: 34px;"/>
                            	</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="col-md-4 control-label" for="personTotnum" style="margin-right: 2px;">组内女性年龄范围</label>
							
								<div class="col-md-7 input-daterange input-group">
	                                <input type="text" class="input-sm form-control" name="groupWomanAgeStart" id="groupWomanAgeStart" value="${classManager.groupWomanAgeStart}" readonly style="width: 90%;float: right;height: 34px;"/>
	                                <span class="input-group-addon" style="background-color: #fff;">到</span>
	                                <input type="text" class="input-sm form-control" name="groupWomanAgeEnd" id="groupWomanAgeEnd" value="${classManager.groupWomanAgeEnd}" readonly style="width: 93%;height: 34px;"/>
                            	</div>
							</div>
						</div>
						
						<div class="col-md-5 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-4 control-label" for="personTotnum">个人参赛总数</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" name="personTotnum"
										value="${classManager.personTotnum}" placeholder="请输入个人参赛总数"
										onkeyup="value=value.replace(/[^\d]/g,'')">
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="col-md-4 control-label" for="unitTotnum">单位参赛总数</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" name="unitTotnum"
										value="${classManager.unitTotnum}" placeholder="请输入单位参赛总数"
										onkeyup="value=value.replace(/[^\d]/g,'')">
								</div>
							</div>
						</div>
						<div class="col-md-5 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-4 control-label" for="personSinglenum">个人单项参赛数</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" name="personSinglenum"
										value="${classManager.personSinglenum}"
										placeholder="请输入个人单项参赛数"
										onkeyup="value=value.replace(/[^\d]/g,'')">
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="col-md-4 control-label" for="unitSinglenum">单位单项参赛数</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" name="unitSinglenum"
										value="${classManager.unitSinglenum}" placeholder="请输入单位单项参赛数"
										onkeyup="value=value.replace(/[^\d]/g,'')">
								</div>
							</div>
						</div>
						<div class="col-md-5 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-4 control-label" for="personTeamnum">个人团体参赛数</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" name="personTeamnum"
										value="${classManager.personTeamnum}" placeholder="请输入个人团体参赛数"
										onkeyup="value=value.replace(/[^\d]/g,'')">
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="col-md-4 control-label" for="unitTeamnum">单位团体参赛数</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" name="unitTeamnum"
										value="${classManager.unitTeamnum}" placeholder="请输入单位团体参赛数"
										onkeyup="value=value.replace(/[^\d]/g,'')">
								</div>
							</div>
						</div>
						<div class="col-md-5 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-4 control-label" for="unitTeam">单位参赛队数</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" name="unitTeam"
										value="${classManager.unitTeam}" placeholder="请输入单位参赛队数"
										onkeyup="value=value.replace(/[^\d]/g,'')">
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="col-md-4 control-label" for="orderNum">排序号</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" name="orderNum"
										value="${classManager.orderNum}" placeholder="请输入排序号"
										onkeyup="value=value.replace(/[^\d]/g,'')">
								</div>
							</div>
						</div>
						
						<!-- 团体赛的特殊限制 -->
						<div class="col-md-5 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-4 control-label" for="personTotnum">团体项目人数</label>
								<div class="col-md-7" style="padding-right: 5px;">
									<input type="text" class="form-control" name="teamSum" value="${classManager.teamSum}" placeholder="请输入团体项目人数"
										onkeyup="value=value.replace(/[^\d]/g,'')">
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="col-md-4 control-label" for="personTotnum" style="margin-right: 14px;">团体项目男女运动员人数(至少)</label>
								<div class="col-md-7 input-daterange input-group" style="width: 54%;">
	                                <span class="input-group-addon">男</span>
	                                <input type="text" class="input-sm form-control" style="height: 34px;" name="teamManSum" value="${classManager.teamManSum}" onkeyup="value=value.replace(/[^\d]/g,'')"/>
	                                <span class="input-group-addon">女</span>
	                                <input type="text" class="input-sm form-control" style="height: 34px;" name="teamWomanSum" value="${classManager.teamWomanSum}" onkeyup="value=value.replace(/[^\d]/g,'')"/>
                            	</div>
							</div>
						</div>
						<div class="col-md-5 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-4 control-label" for="personTotnum" style="margin-right: 2px;">团体项目男性年龄范围</label>
							
								<div class="col-md-7 input-daterange input-group">
	                                <input type="text" class="input-sm form-control" name="teamManAgeStart" id="teamManAgeStart" value="${classManager.teamManAgeStart}" readonly style="width: 90%;float: right;height: 34px;"/>
	                                <span class="input-group-addon" style="background-color: #fff;">到</span>
	                                <input type="text" class="input-sm form-control" name="teamManAgeEnd" id="teamManAgeEnd" value="${classManager.teamManAgeEnd}" readonly style="width: 93%;height: 34px;"/>
                            	</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="col-md-4 control-label" for="personTotnum" style="margin-right: 2px;">团体项目女性年龄范围</label>
							
								<div class="col-md-7 input-daterange input-group">
	                                <input type="text" class="input-sm form-control" name="teamWomanAgeStart" id="teamWomanAgeStart" value="${classManager.teamWomanAgeStart}" readonly style="width: 90%;float: right;height: 34px;"/>
	                                <span class="input-group-addon" style="background-color: #fff;">到</span>
	                                <input type="text" class="input-sm form-control" name="teamWomanAgeEnd" id="teamWomanAgeEnd" value="${classManager.teamWomanAgeEnd}" readonly style="width: 93%;height: 34px;"/>
                            	</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row collapse in" id="row2">
					<div class="form-horizontal">
						<div class="col-md-5 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-4 control-label" for="xzydyName7">分类类型</label>
								<input type="hidden" id="classTypeclick" value="${classManager.classType}"/>
								<input type="hidden" id="PclassType" value="${classManager.manager.classType}"/>
								<div class="col-md-7" style="padding-right: 5px;">
								  <select class="form-control" name="classType" id="classType" <c:if test="${not empty classManager.cid}">disabled="disabled"</c:if>></select>
								</div>
								<div class="col-md-1" style="padding-left:0;">
						        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
						        </div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="col-md-4 control-label" for="xzydyName7">项目分类人群</label>
								<input type="hidden" id="exp1click" value="${classManager.exp1}"/>
								<div class="col-md-7" style="padding-right: 5px;">
								  <select class="form-control" name="exp1" id="exp1">
								  </select>
								</div>
								<div class="col-md-1" style="padding-left:0;">
						        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
						        </div>
							</div>
						</div>
<%-- 						<div class="col-md-5">
							<div class="form-group">
								<label class="col-md-4 control-label" for="xzydyName7">项目分类人群</label>
								<input type="hidden" id="exp1click" value="${classManager.exp1}"/>
								<div class="col-md-7" style="padding-right: 5px;">
								  <select class="form-control" name="exp1" id="exp1"></select>
								</div>
								<div class="col-md-1" style="padding-left:0;">
						        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
						        </div>
							</div>
						</div> --%>
						<div class="col-md-10 col-md-offset-1">
							<div class="form-group">
								<label class="col-md-2 control-label" for="xzInputName13">特殊要求</label>
								<div class="col-md-10" style="width: 80%;">
									<textarea class="form-control" rows="3" name="backup">${classManager.backup}</textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-10 col-md-offset-1" style="padding-right: 40px;">
					<button id="cancleBtnTo" type="button"
						class="btn btn-warning myformbtn pull-right">取消</button>
					<button id="submitBtn" type="button"
						class="btn btn-success myformbtn pull-right">保存</button>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- 中间内容 end -->
<link href="${contextPath}/static/css/style.onoffswitch.css" rel="stylesheet">
<script src="${contextPath}/static/localjs/project/addClassManager.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%>
