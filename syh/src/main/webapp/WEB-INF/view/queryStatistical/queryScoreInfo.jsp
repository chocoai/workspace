<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 start -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
    <li>首页</li>
    <li>查询统计管理</li>
    <li class="active">成绩信息统计查询</li>
</ol>
<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<shiro:hasPermission name="/queryScoreInfo/exportExcel">
		<!-- 查询表单 start -->
		<div class="lookfor">
			<div class="row">
				<div class="col-md-12">
					<div class="form-inline">
						<div class="form-group">
	                    	<a class="btn btn-danger difbtn hidden" id="exportExcel" href="javascript:" role="button">
	           					<i class="icon-cloud-upload"></i> &nbsp;导出Excel
	         				</a>   
	                    </div>
					</div>
				</div>
			</div>
		</div>
		<!-- 查询表单 end -->
	</shiro:hasPermission>

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
				<a id="refreshTable" href="javascript:"></a>
			</div>
		</div>
		<!-- 左侧列表组 end -->

		<!-- 右侧列表组内容 strat -->
		<div class="col-md-9">
			<!-- 表格 strat -->
			<div class="col-md-12 nopad-lr">
					<div class="panel panel-default nomar-b">
						<div class="panel-heading text-center font-wb" id="tableTitle">请选择比赛项目</div>
						<div class="table-responsive">
							<input type="hidden" id="projectCid">
							<input type="hidden" id="projectName">
							<table class="table table-hover table-bordered table-striped">
								<thead>
									<tr>
										<th>名次</th>
										<th>单位</th>
										<th>姓名</th>
										<th id="scores">成绩</th>
										<th>得分</th>
										<th  id="judgeLevel">备注</th>
									</tr>
								</thead>
								<tbody id="tableList">
										<tr ></tr>
								</tbody>
								<tfoot id="noDataPage" >
										<tr >
											<td colspan="6" align="center">暂无数据</td>
										</tr>
								</tfoot>
							</table>
						</div>
					</div>
			</div>
			<!-- 表格 end -->
		</div>
		<!-- 右侧列表组内容 end -->
	</div>
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/js/bootstrap-suggest.min.js"></script>
<script src="${contextPath}/static/localjs/queryStatistical/queryScoreInfo.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 
<script src="${contextPath}/static/localjs/treeSearch.js"></script>