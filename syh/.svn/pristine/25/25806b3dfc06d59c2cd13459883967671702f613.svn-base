<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
            
<ol class="breadcrumb location">
	  <span class="icon-home">&nbsp;&nbsp;</span>
	  <li>首页</li>
	  <li>查询统计管理</li>
	  <li class="active">项目信息统计查询</li>
</ol>

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
                <a id="queryBtn" href="javascript:"></a>
			    <a id="refreshTable" href="javascript:"></a> 
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
			<!-- 表格 strat -->
			<div class="col-md-12 nopad-lr">
					<div class="panel panel-default nomar-b">
						<div class="panel-heading text-center font-wb" id="tableName">项目分类信息</div>
						<div class="table-responsive">
							<input type="hidden" id="pCid" value="">
							<input type="hidden" id="projectName" value="">
							<table class="table table-hover table-bordered table-striped">
								<thead>
									<tr>
										    <th></th>
							                <th data-field="projectName">项目名称</th>
							                <th data-field="isTeam">是否团体</th>
							                <th data-field="scoreCard">计分记牌方式</th>
							                <th data-field="resultUnit">成绩单位</th>
							       <!--          <th data-field="finalistsNum">晋级决赛人数</th>
							                <th data-field="finalAdmission">决赛录取人数</th> -->
							                <th data-field="mastersLevel">健将标准</th>
							                <th data-field="firstLevel">一级标准</th>
							                <th data-field="secondLevel">二级标准</th>
							                <th data-field="threeLevel">三级标准</th>
							                <th data-field="gender">性别</th>
									</tr>
								</thead>
								<tbody id="projectManagerList">
									<tr></tr>
								</tbody>
								<tfoot>
									<tr id="noDataPage" class="hidden">
										<td colspan="10" align="center">暂无数据</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
					<!-- 分页 strat -->
					<div id ="pageID"></div>
					<!-- 分页 end -->
			</div>
			<!-- 表格 end -->
		</div>
		<!-- 右侧列表组内容 end -->
	</div>
</div>
<!-- 中间内容 end -->

<script src="${contextPath}/static/localjs/project/projectManager.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 
<script src="${contextPath}/static/localjs/treeSearch.js"></script>   