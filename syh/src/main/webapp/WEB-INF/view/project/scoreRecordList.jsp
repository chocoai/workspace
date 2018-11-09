<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp" %> 
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
  <span class="icon-home">&nbsp;&nbsp;</span>
  <li>首页</li>
  <li>项目信息管理</li>
  <li class="active">计分记牌配置</li>
</ol>
<!-- 当前位置 end -->
<!-- 中间内容 strat -->
<div class="content">
   <div class="row">
      <div class="col-md-12">
	      <shiro:hasPermission name="/scoreRecord/updateInfo">
	         <a class="btn btn-success difbtn" href="updateInfo" role="button">
	            <i class="icon-plus"></i> &nbsp;新增
	         </a>
	         <a id="updateInfo" class="btn btn-info difbtn" href="javascript:;" role="button">
	            <i class="icon-edit"></i> &nbsp;编辑
	         </a>
	      </shiro:hasPermission>
	      <shiro:hasPermission name="/scoreRecord/delete">
	         <a id="deleteScoreRecord" class="btn btn-warning difbtn" href="javascript:;" role="button">
	            <i class="icon-minus"></i> &nbsp;删除
	         </a>
	      </shiro:hasPermission>
      </div>
   </div>
   <!-- 查询表单 strat -->
   <div class="lookfor">
     <div class="row">
       <div class="col-md-12">
          <div class="form-inline">
            <div class="form-group">
              <label for="ruleName">方式名称</label>
              <input type="text" class="form-control" placeholder="请输入方式名称" id="ruleName">
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
       <table class="table table-hover table-bordered table-striped">
         <thead>
           <tr>
               <th></th>
               <th data-field="ruleName">方式名称</th>
               <th data-field="trophy">奖杯</th>
               <th data-field="certificate">证书</th>
               <th data-field="goldMedal">金牌</th>
               <th data-field="silverMedal">银牌</th>
               <th data-field="bronzeMedal">铜牌</th>
               <th data-field="firstScore">第一名得分</th>
               <th data-field="secondScore">第二名得分</th>
               <th data-field="thirdScore">第三名得分</th>
               <th data-field="fourthScore">第四名得分</th>
               <th data-field="fifthScore">第五名得分</th>
               <th data-field="sixthScore">第六名得分</th>
               <th data-field="seventhScore">第七名得分</th>
               <th data-field="eightScore">第八名得分</th>
               <th data-field="orderNum">排序号</th>
               <th data-field="backup">备注</th>
           </tr>
         </thead>
         <tbody id="scoreRecordList">
         </tbody>
         <tfoot>
			<tr id="noDataPage" class="hidden" >
				<td colspan="16" align="center">暂无数据</td>
			</tr>
		 </tfoot>
       </table>
     </div>
     <!-- 分页 strat -->
     <div id="pageID"></div>
     <!-- 分页 end -->
   </div>
   <!-- 表格 end -->

</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/project/scoreRecord.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp" %> 

