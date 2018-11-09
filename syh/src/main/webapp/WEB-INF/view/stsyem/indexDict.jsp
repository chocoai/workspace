<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>系统配置管理</li>
	<li class="active">数据字典管理</li>
</ol>
 <!-- 当前位置 end -->

            <!-- 中间内容 strat -->
            <div class="content">
              <div class="row">
                   <!-- Tab strat -->
                   <div class="col-md-12">
                       <ul class="nav nav-tabs mynav-tabs">
                         <li role="presentation" class="active"><a href="${contextPath}/sysDict/index ">字典列表</a></li>
                         <li role="presentation" ><a href="${contextPath}/sysDict/indexType ">类别管理</a></li>
                       </ul>
                   </div>
                   <!-- Tab end -->
                   <!-- 左侧列表组 strat -->
                   <div class="col-md-2">
                     <div class="panel panel-default">
                       <!-- Default panel contents -->
                       <div class="panel-heading"><i class="icon-th-large"></i> &nbsp;所有类别</div>
                       <!-- List group -->
                       <ul class="list-group">
	                       	<c:forEach items="${list}" var="dict">
	                          <a href="javascript:;" onclick="loadNode('${dict.itemValue}')" id="${dict.itemValue}_node" class="list-group-item">${dict.itemKey }</a>
	                       	</c:forEach>
                       </ul>
                     </div>
                   </div>
                   <!-- 左侧列表组 end -->

                   <!-- 右侧列表组内容 strat -->
                   <div class="col-md-10">
                     <div class="row">
                      <div class="col-md-12">
                         <a class="btn btn-success difbtn" href="javaScript:;" role="button" id="saveDict">
                            <i class="icon-plus"></i> &nbsp;新增
                         </a>
                         <a class="btn btn-warning difbtn" href="javaScript:;" role="button" id="editDict">
                            <i class="icon-minus"></i> &nbsp;编辑
                         </a>
                         <a class="btn btn-warning difbtn" href="javaScript:;" role="button" id="deleteDict">
                            <i class="icon-minus"></i> &nbsp;删除
                         </a>
                         <a class="btn btn-info difbtn" href="javaScript:;" role="button" id="saveDictTo">
                           	<i class="icon-minus"></i> &nbsp;保存
                         </a>
                         <input type="hidden" id="nodeName" value="${firstNode}">
                      </div>
                     </div>

                      <!-- 表格 strat -->
                      <div class="col-md-12 nopad-lr">
                         <div class="mytable">
                           <div class="panel panel-default nomar-b">
                           <input type="hidden" id="itemValue" >
                             <div class="table-responsive">
	                                <div class="jqGrid_wrapper">
			                            <table id="table_dict"></table>
			                            <div id="pager_list"></div>
			                        </div>
                             </div>
                           </div>
                         </div>
                      </div>
                      <!-- 表格 end -->
                   </div>
                   <!-- 右侧列表组内容 end -->
              </div>
            </div>
            <!-- 中间内容 end -->


<script src="${contextPath}/static/localjs/stsyem/indexDict.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script>
<!-- jqGrid -->
<link href="${contextPath}/static/jqGrid/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="${contextPath}/static/jqGrid/css/ui.jqgridffe4.css?0820" rel="stylesheet">

<script src="${contextPath}/static/jqGrid/js/jquery.peity.min.js"></script>
<script src="${contextPath}/static/jqGrid/js/grid.locale-cnffe4.js?0820"></script>
<script src="${contextPath}/static/jqGrid/js/jquery.jqGrid.minffe4.js?0820"></script>
<script src="${contextPath}/static/jqGrid/js/content.min.js?v=1.0.0"></script> 
<script src="${contextPath}/static/jqGrid/js/jquery.ui.resizable.js"></script> 
