<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
    <li>首页</li>
    <li>项目信息管理</li>
    <li class="active">项目分类管理</li>
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
					<i class="icon-th-large"></i> &nbsp;项目分类列表
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
				<input type="hidden" id="TreeID" value="classTree"  placeholder="js获取节点树id"/>
 				<div id="menuContent" class="menuContent" >
					<ul id="classTree" class="ztree" style="-moz-user-select: none;overflow-y: auto;height: 350px;overflow-x: auto;width: 230px;"></ul>
				</div>
			</div>
		</div>
		<!-- 左侧列表组 end -->

		<!-- 右侧列表组内容 strat -->
		<div class="col-md-9">
			<div class="row">
				<div class="col-md-12">
					<shiro:hasPermission name="/classManager/updateInfo"> 
						<a id="save" class="btn btn-success difbtn" href="javascript:;" role="button">
							<i class="icon-plus"></i> &nbsp;新增
						</a> 
						<a id="updateInfo" class="btn btn-info difbtn" href="javascript:;" role="button"> 
							<i class="icon-edit"></i> &nbsp;编辑
						</a> 
					</shiro:hasPermission> 
					<shiro:hasPermission name="/classManager/delete"> 
						<a id="deleteClassManager" class="btn btn-warning difbtn" href="javascript:;" role="button"> 
							<i class="icon-minus"></i> &nbsp;删除
						</a> 
					</shiro:hasPermission> 
					<a id="queryBtn" href="javascript:"></a>
					<a id="refreshTable" href="javascript:"></a>
				</div>
			</div>

			<!-- 表格 strat -->
			<div class="col-md-12 nopad-lr">
				<div class="mytable">
					<div class="panel panel-default nomar-b">
						<div class="panel-heading text-center font-wb" id="tableName">项目分类信息</div>
						<div class="table-responsive">
							<input type="hidden" id="classCid" value="${pCid}">
							<table class="table table-hover table-bordered table-striped">
								<thead>
									<tr>
										   <th></th>
							               <th data-field="classifyName">分类名称</th>
							               <th data-field="alias">别名</th>
							               <th data-field="ageStart">年龄上限</th>
							               <th data-field="ageStart">年龄下限</th>
							               <th data-field="operation">操作</th>
									</tr>
								</thead>
								<tbody id="classManagerList">
									<tr></tr>
								</tbody>
								<tfoot>
									<tr id="noDataPage" class="hidden">
										<td colspan="8" align="center">暂无数据</td>
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

<!-- 详情模态框 strat -->
<!-- Modal -->
<div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">项目分类详情</h4>
      </div>
      <div class="modal-body">
       	  <div class="content" style="padding-top: 10px;">
              <div class="panel panel-default form-header">
                <div class="panel-heading">
                  <h3 class="panel-title">项目分类详情</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                       <form class="form-horizontal">
                           <div class="col-md-5 col-md-offset-1">
                             <div class="form-group">
                               <label class="col-md-5 control-label">运动员总数:</label>
                               <label id="athletesNum"class="col-md-7 control-label sure-text pad-b7" >${classManager.athletesNum }</label>
                             </div>
                           </div>
                           <div class="col-md-5">
                              <div class="form-group">
                                <label class="col-md-5 control-label">参赛单位队数:</label>
                                <label id="unitTeam" class="col-md-7 control-label sure-text pad-b7">${classManager.unitTeam }</label>
                              </div>
                           </div>
                           <div class="col-md-5 col-md-offset-1">
                              <div class="form-group">
                                <label class="col-md-5 control-label">男运动员人数:</label>
                                <label id="manNum" class="col-md-7 control-label sure-text pad-b7">${classManager.manNum }</label>
                              </div>
                           </div>
                           <div class="col-md-5">
                             <div class="form-group">
                               <label class="col-md-5 control-label">女运动员总数:</label>
                               <label id="womanNum" class="col-md-7 control-label sure-text pad-b7">${classManager.womanNum }</label>
                             </div>
                           </div>
                           <div class="col-md-5 col-md-offset-1">
                             <div class="form-group">
                               <label class="col-md-5 control-label">个人参赛总数:</label>
                               <label id="personTotnum" class="col-md-7 control-label sure-text pad-b7">${classManager.personTotnum }</label>
                             </div>
                           </div>
                           <div class="col-md-5">
                              <div class="form-group">
                                <label class="col-md-5 control-label">个人单项参赛数:</label>
                                <label id="personSinglenum" class="col-md-7 control-label sure-text pad-b7">${classManager.personSinglenum }</label>
                              </div>
                           </div>
                           <div class="col-md-5 col-md-offset-1">
                             <div class="form-group">
                               <label class="col-md-5 control-label">个人团体参赛数:</label>
                               <label id="personTeamnum" class="col-md-7 control-label sure-text pad-b7">${classManager.personTeamnum }</label>
                             </div>
                           </div>
                           <div class="col-md-5">
                              <div class="form-group">
                                <label class="col-md-5 control-label">单位参赛总数:</label>
                                <label id="unitTotnum" class="col-md-7 control-label sure-text pad-b7">${classManager.unitTotnum }</label>
                              </div>
                           </div>
                           <div class="col-md-5 col-md-offset-1">
                             <div class="form-group">
                               <label class="col-md-5 control-label">单位单项参赛数:</label>
                               <label id="unitSinglenum" class="col-md-7 control-label sure-text pad-b7">${classManager.unitSinglenum }</label>
                             </div>
                           </div>
                           <div class="col-md-5">
                              <div class="form-group">
                                <label class="col-md-5 control-label">单位团体参赛数:</label>
                                <label id="unitTeamnum" class="col-md-7 control-label sure-text pad-b7">${classManager.unitTeamnum }</label>
                              </div>
                           </div>
                       </form>
                    </div>
                </div>
              </div>
            </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<!-- 详情模态框 end -->

<script src="${contextPath}/static/localjs/project/classManager.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script>  
<script src="${contextPath}/static/localjs/treeSearch.js"></script>  