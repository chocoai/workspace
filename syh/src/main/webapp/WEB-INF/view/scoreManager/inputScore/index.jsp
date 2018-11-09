<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>

<!-- 当前位置 start -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>成绩信息管理</li>
	<li class="active">成绩录入管理</li>
</ol>

<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<div class="row">
		<!-- 左侧列表组 strat -->
		<div class="col-md-3">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<i class="icon-th-large"></i> &nbsp;比赛项目列表
				</div>
				<!--树节点搜索  -->
				<div class="input-group bumen-search-box">
                     <input type="text"  id="key" class="empty form-control bumen-nobor" placeholder="Search..." onkeyup="callNumber()">  
                     <span class="input-group-addon bumen-addon">
                             <label id="number" class="bumen-bili-inner"></label>  
                     </span>
                     <span class="input-group-addon bumen-addon">
                         <label type="text"  class="bumen-bili" id="resultKey" onclick="changeFocus()"> 
                             <div class="bumen-updown">  
                                 <a id="clickUp" class="glyphicon glyphicon-menu-up bumen-up" onclick="clickUp()"></a>  
                                 <a id="clickDown" class="glyphicon glyphicon-menu-down bumen-down" onclick="clickDown()"></a>  
                             </div>  
                              
                         </label>  
                     </span>
                 </div>  
				<!-- 节点树 -->
				<input type="hidden" id="TreeID" value="projectTree" placeholder="js获取节点树id"/>
				<div id="menuContent" class="menuContent" >
					<ul id="projectTree" class="ztree" style="-moz-user-select: none;overflow-y: auto;height: 350px;overflow-x: auto;width: 230px;"></ul>
				</div>
			</div>
		</div>
		<!-- 左侧列表组 end -->

		<!-- 右侧列表组内容 strat -->
		<div class="col-md-9">
			<div class="row">
				<div class="col-md-6">
					<shiro:hasPermission name="/inputScore/addAthleteParticipat">
						<a class="btn btn-success difbtn" href="addAthleteParticipat"
							role="button"> <i class="icon-plus"></i> &nbsp;新增参赛信息
						</a>
					</shiro:hasPermission>	
					<a id="refreshTable" href="javascript:"></a>
				</div>
				<div class="col-md-5  pull-right">
					<div class="input-group pull-right">
                         <input type="text" class="form-control" placeholder="请输入单位名称/参赛人/队名称"  id="queryParam"> 
                         <span class="input-group-btn"> 
                         	<button type="button" class="btn btn-primary pull-right" id="queryBtn" >搜索</button> 
                         </span>
                     </div>
				</div>
			</div>

			<!-- 表格 strat -->
			<div class="col-md-12 nopad-lr">
				<div class="mytable">
					<div class="panel panel-default nomar-b">
						<div class="panel-heading text-center font-wb" id="tableName">成绩信息</div>
						<div class="table-responsive">
							<input type="hidden" id="projectCid">
							<input type="hidden" id="projectName">
							<table class="table table-hover table-bordered table-striped">
								<thead>
									<tr>
										<!-- <th><input id="chooseAll" type="checkbox"></th> -->
										<th>项目名称</th>
										<th>赛制类别</th>
										<th>参赛人/队</th>
										<th>单位名称</th>
										<th style="width: 16%;">操作</th>
									</tr>
								</thead>
								<tbody id="list">
									<tr></tr>
								</tbody>
								<tfoot>
									<tr id="noDataPage">
										<td colspan="7" align="center">暂无数据</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
					<!-- 分页 strat -->
					<div id ="pageID"></div>
					<!-- 分页 end -->
					
					
				</div>
			</div>
			<!-- 表格 end -->
		</div>
		<!-- 右侧列表组内容 end -->
	</div>
</div>
<!-- 中间内容 end -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="inputAthleteScore" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">比赛成绩录入</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="form-horizontal">
						<div class="col-md-11">
							<div class="form-group">
								<label class="col-md-3 control-label">项目名称</label>
								<div class="col-md-8">
									<input type="text" id="projectNameModal" class="form-control"
										readonly> <input type="hidden" id="projectCidModal"><input
										type="hidden" id="isTeam">
									<input type="hidden" id="participatInfoIdModal" class="form-control"> 
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<div class="form-group">
								<label class="col-md-3 control-label">参赛人/队名称</label>
								<div class="col-md-8">
									<input type="text" class="form-control" id="athleteNameModal"
										readonly> <input type="hidden" id="athleteCidModal">
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<div class="form-group">
								<label class="col-md-3 control-label">所属单位</label>
								<div class="col-md-8">
									<input type="text" class="form-control" id="unitNameModal"
										readonly>
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<div class="form-group">
								<label class="col-md-3 control-label">成绩单位</label>
								<div class="col-md-8">
									<input type="text" id="resultTypeModal" class="form-control"
										readonly> <input type="hidden">
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<input type="hidden" id="resultTypeModal2">
							<div class="form-group" id="resultType">
								<label class="col-md-3 control-label">成绩</label>
								<div class="col-md-8">
									<input type="text" class="form-control" id="scores"
										placeholder="请输入成绩">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">名次</label>
								<div class="col-md-8">
									<input type="text" class="form-control" id="ranking"
										placeholder="请输入名次">
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<div class="form-group">
								<label class="col-md-3 control-label">备注</label>
								<div class="col-md-8">
									<textarea class="form-control" rows="3" id="backup"></textarea>
								</div>
							</div>
						</div>
						<div class="col-md-10 col-md-offset-1">
							<button type="button" name="closeModal" data-dismiss="modal"
								class="btn btn-warning myformbtn pull-right">取消</button>
							<button type="button" id="submitBtn"
								class="btn btn-success myformbtn pull-right">保存</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="changeTeam" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">团队成员变更</h4>
			</div>
			<input type="hidden" id="changeTeamProjectCid">
			<input type="hidden" id="changeTeamParticipatInfoId">
			<div class="modal-body">
				<div class="row">
					<div class="form-horizontal">
						<div class="col-md-10">
							<div class="form-group">
								<label class="col-md-4 text-center">操作</label> <label
									class="col-md-5 ">运动员姓名</label> <label class="col-md-3 ">单位名称</label>
							</div>
						</div>
						<div class="col-md-12" id="changeTeamAthlete">
							<div class="form-group">
								<div class="col-md-3">
									<button type="button" id="cancleBtn"
										class="btn btn-warning myformbtn pull-right">修改</button>
								</div>
								<div class="col-md-4">
									<input type="text" class="form-control" placeholder="请输入运动员姓名">
								</div>
								<div class="input-group col-md-4 ">
									<input type="text" class="form-control" name="unitName"
										placeholder="请输入单位名称">
									<div>
										<ul class="dropdown-menu dropdown-menu-right" role="menu">
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

<%@include file="/WEB-INF/view/scoreManager/allotScoresModal.jsp"%>
<script src="${contextPath}/static/js/bootstrap-suggest.min.js"></script>
<script src="${contextPath}/static/localjs/scoreManager/inputScore/index.js"></script>
<script src="${contextPath}/static/localjs/treeSearch.js"></script>   
<%@include file="/WEB-INF/view/common/foot.jsp"%>

