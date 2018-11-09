<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 start -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>成绩信息管理</li>
	<li class="active">成绩基本信息管理</li>
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
				<div class="col-md-5">
					<div class="input-group pull-right">
						     <a id="refreshTable" href="javascript:"></a>
	                         <input type="text" class="form-control" placeholder="单位名称/参赛人"  id="queryParam"> 
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
 										<th>成绩</th>
										<th>名次</th>
										<th>积分</th>
										<th>奖牌</th> 
										<th style="width: 16%;">操作</th>
									</tr>
								</thead>
								<tbody id="list">
									<tr></tr>
								</tbody>
								<tfoot>
									<tr id="noDataPage">
										<td colspan="9" align="center">暂无数据</td>
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




<%@include file="/WEB-INF/view/scoreManager/allotScoresModal.jsp"%>
<%@include file="/WEB-INF/view/scoreManager/viewScoresModal.jsp"%>
<%@include file="/WEB-INF/view/scoreManager/updateScoresModal.jsp"%>

<script src="${contextPath}/static/localjs/scoreManager/scoreBaseInfo/index.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%>
<script src="${contextPath}/static/localjs/treeSearch.js"></script>   
