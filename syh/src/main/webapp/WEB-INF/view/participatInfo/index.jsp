<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>报名信息管理</li>
	<li class="active">参赛信息管理</li>
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
					<shiro:hasPermission name="/participatInfo/updateInfo">
					
						<a id="save" class="btn btn-success difbtn" href="javascript:;" role="button">
							<i class="icon-plus"></i> &nbsp;新增
						</a> 
						<a id="edit" class="btn btn-info difbtn" href="javascript:;" role="button"> 
							<i class="icon-edit"></i> &nbsp;编辑
						</a> 
					</shiro:hasPermission>
					<shiro:hasPermission name="/participatInfo/deleteInfo">
						<a id="deleteBtn" class="btn btn-warning difbtn" href="javascript:;" role="button"> 
							<i class="icon-minus"></i> &nbsp;删除
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
						<div class="panel-heading text-center font-wb" id="tableName">参赛信息</div>
						<div class="table-responsive">
							<input type="hidden" id="projectCid" value="${pCid}">
							<table class="table table-hover table-bordered table-striped">
								<thead>
									<tr>
										<th><input id="chooseAll" type="checkbox"></th>
										<th>项目名称</th>
										<th>赛制类别</th>
										<th>参赛人/队</th>
										<th>单位名称</th>
										<th>操作</th>
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
<!-- 运动员明细模态框 strat -->
<!-- Modal -->
<div class="modal fade" id="myDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">明细</h4>
      </div>
      <div class="modal-body">
        <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>姓名</th>
                  <th>性别</th>
                  <th>身份证号</th>
                  <th>出生日期</th>
                  <th>单位名称</th>
                </tr>
              </thead>
              <tbody id="infoList">
              </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<!-- 运动员明细模态框 end -->
<script src="${contextPath}/static/js/bootstrap-suggest.min.js"></script>
<script src="${contextPath}/static/localjs/participatInfo/index.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 
<script src="${contextPath}/static/localjs/treeSearch.js"></script>