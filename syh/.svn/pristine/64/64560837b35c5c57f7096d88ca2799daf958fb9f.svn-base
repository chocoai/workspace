<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->

<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>报名信息管理</li>
    <li class="active">参赛项目个数</li>
</ol>
<!-- 当前位置 end -->
<!-- 中间内容 strat -->
<div class="content">
   <div class="row">
      <div class="col-md-12">
          <ul class="nav nav-tabs mynav-tabs nomar_b20">
            <li role="presentation"><a href="index">参赛年龄</a></li>
             <li role="presentation" ><a href="toSumAge">年龄总和</a></li>
            <li role="presentation" ><a href="toUnitNum">单位项目个数</a></li>
            <li role="presentation" class="active"><a href="toPersonNum">个人项目个数</a></li>
            <li role="presentation"><a href="toNuptial">重名参赛</a></li>
            <li role="presentation"><a href="toAcrossUnit">跨参赛单位参赛</a></li>
          </ul>
      </div>
   </div>
   <!-- 查询表单 strat -->
  <div class="lookfor">
     <div class="row">
       <div class="col-md-12">
          <div class="form-inline">
            <div class="form-group">
              <label for="athleteName">运动员姓名</label>
              <input type="text" class="form-control" placeholder="请输入运动员姓名" id="athleteName">
            </div>
			<button id="queryBtn" type="submit" class="btn btn-primary pull-right">
				<span class="icon-search"></span>&nbsp;&nbsp;查询
			</button>
			<a id="refreshTable" href="javascript:"></a>
          </div>
       </div>
     </div>
   </div>
   <!-- 查询表单 end -->
   <!-- 表格 strat -->
   <div class="mytable">
     <div class="table-responsive">
     	<h4>个人项目个数预警</h4>
       <table class="table table-hover table-bordered table-striped">
         <thead>
           <tr>
               <th>序号</th>
               <th>姓名</th>
               <th>证件号</th>
               <th>单位名称</th>
               <th>分类名称</th>
               <th>项目名称</th>
               <th>参赛总数限制</th>
               <th>单项参赛数限制</th>
               <th>团体参赛数限制</th>
           </tr>
         </thead>
         <tbody id="warnInfoList">
			<tr></tr>
		 </tbody>
		 <tfoot>
			<tr id="noDataPage" class="hidden">
				<td colspan="9" align="center">暂无数据</td>
			</tr>
		 </tfoot>
       </table>
     </div>
    <!-- 分页 strat -->
	<div id ="pageID"></div>
	<!-- 分页 end -->
   </div>
   <!-- 表格 end -->
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/warnInfo/personNum.js"></script>
<script src="${contextPath}/static/js/bootstrap-suggest.min.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%>