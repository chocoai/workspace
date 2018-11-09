<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 start -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>成绩信息管理</li>
	<li class="active">竞赛项目记录库</li>
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
					<ul id="projectTree" class="ztree"></ul>
				</div>
			</div>
		</div>
		<!-- 左侧列表组 end -->

		<!-- 右侧列表组内容 strat -->
		<div class="col-md-9">
			<div class="row">
				<div class="col-md-6">
					 <shiro:hasPermission name="/comRecord/addInfo">
						<a class="btn btn-success difbtn" href="javascript:;" id="addInfo" target="_self"
							role="button"> <i class="icon-plus"></i> &nbsp;新增
						</a> 
					 </shiro:hasPermission>
					 <shiro:hasPermission name="/comRecord/updateInfo">
						<a class="btn btn-info difbtn" href="javascript:;" id="updateInfo"
							role="button"> <i class="icon-edit"></i> &nbsp;编辑
						</a> 
					 </shiro:hasPermission>
					 <shiro:hasPermission name="/comRecord/deleteInfo">
						<a id="deleteBtn" class="btn btn-warning difbtn" href="javascript:;" role="button"> 
							<i class="icon-minus"></i>&nbsp;删除
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
							<div class="panel-heading text-center font-wb" id="tableName">记录库</div>
							<div class="table-responsive">
								<input type="hidden" id="projectCid" value="${pCid }">
								<input type="hidden" id="projectName">
								<table class="table table-hover table-bordered table-striped">
									<colgroup>
							         	<col style="width:3%">
							         	<col>
							         	<col style="width:12%">
							         	<col style="width:10%">
							         	<col style="width:15%">
							         	<col style="width:25%">
						         	</colgroup>
									<thead>
										<tr>
											<th><input id="chooseAll" type="checkbox"></th>
											<th>项目名称</th>
											<th>纪录保持者</th>
											<th>纪录成绩</th>
											<th>纪录创造时间</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody id="list">
										<tr></tr>
									</tbody>
									<tfoot>
										<tr id="noDataPage">
											<td colspan="6" align="center">暂无数据</td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
						<!-- 分页 start -->
						<div id="pageID"></div>
						<!-- 分页 end -->
						</div>
				</div>
			</div>
			<!-- 表格 end -->
		</div>
		<!-- 右侧列表组内容 end -->
	</div>
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/comRecord/index.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 
<script src="${contextPath}/static/localjs/treeSearch.js"></script>