<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 start -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>查询统计管理</li>
	<li class="active">青少年三榜查询</li>
</ol>
<!-- 当前位置 end -->
<!-- 中间内容 strat -->
<div class="content">
	<!-- 查询表单 start -->
	<shiro:hasPermission name="/queryTeenagersThreeList/exportExcel">
		<div style="padding: 15px 10px;border-top: 2px solid #347ab8;">
			<div class="row">
				<div class="col-md-12">
					<div class="form-inline">
						<div class="form-group">
	                    	<a class="btn btn-danger difbtn" id="exportExcel" href="javascript:" role="button">
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
      <div class="col-md-12">
          <ul class="nav nav-tabs mynav-tabs nomar_b20">
            <li role="presentation" class="active" ><a href="javaScript:">竞技体育(青少年类)金牌榜</a></li>
            <li role="presentation"><a href="javaScript:">竞技体育(青少年类)奖牌榜</a></li>
            <li role="presentation"><a href="javaScript:">竞技体育(青少年类)总分榜</a></li>
          </ul>
          <a id="queryBtn" href="javascript:"></a>
      </div>
   </div>
   
  <!-- 表格 strat -->
  <div class="mytable">
    <div class="panel panel-default">
    <div class="panel-heading text-center font-wb" id="tableTitle">竞技体育(青少年类)金牌榜</div>
    <div class="table-responsive">
      <table class="table table-hover table-bordered table-striped">
        <thead id="thead">
          <tr>
              <th>排名</th>
              <th>单位</th>
              <th>金牌<img src="${contextPath}/static/images/gold.png" style="margin-left: 6px;"></th>
              <th>银牌<img src="${contextPath}/static/images/silver.png" style="margin-left: 6px;"></th>
              <th>铜牌<img src="${contextPath}/static/images/bronze.png" style="margin-left: 6px;"></th>
              <th>奖牌</th>
              <th>输送奖励金牌</th>
          </tr>
        </thead>
        <tbody id="list">
        </tbody>
		<tfoot id="noDataPage" >
				<tr>
					<td align="center">暂无数据</td>
				</tr>
		</tfoot>
      </table>
    </div>
    </div>
  </div>
  <!-- 表格 end -->
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/queryStatistical/queryTeenageThreeList.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 