<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->

<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>报名信息管理</li>
    <li class="active">运动员参赛信息</li>
</ol>
<!-- 当前位置 end -->
<!-- 中间内容 strat -->
<div class="content">
   <div class="row">
   	  <div class="col-md-12">
          <ul class="nav nav-tabs mynav-tabs">
            <li role="presentation"><a href="index">个人赛</a></li>
            <li role="presentation" class="active"><a href="teamIndex">团体赛</a></li>
          </ul>
      </div>
      <div class="col-md-12">
         <a class="btn btn-success difbtn" href="addTeamInfo" role="button">
            <i class="icon-plus"></i> &nbsp;新增
         </a>
         <a id="updateInfos" class="btn btn-info difbtn" href="javascript:;" role="button"> 
			<i class="icon-edit"></i> &nbsp;编辑
		 </a> 
		 <a id="deleteBtn" class="btn btn-warning difbtn" href="javascript:;" role="button"> 
			<i class="icon-minus"></i> &nbsp;删除
		 </a> 
<!--          <a class="btn btn-danger difbtn" href="#" role="button"> -->
<!--             <i class="icon-cloud-download"></i> &nbsp;导入excel -->
<!--          </a> -->
      </div>
   </div>
   <!-- 查询表单 strat -->
	<div class="lookfor">
	  <div class="row">
	    <div class="col-md-12">
	       <div class="form-inline">
	       	 <div class="form-group">
               <label for="unitName">单位名称</label>
               <input type="text" class="form-control" placeholder="请输入单位名称" id="unitName">
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
	           <th><input id="chooseAll" type="checkbox"></th>
               <th>单位名称</th>
               <th>项目名称</th>
               <th>分类名称</th>
               <th>代表队信息</th>
	        </tr>
	      </thead>
	      <tbody id="teamParticipatList">
	      </tbody>
	      <tfoot>
			<tr id="noDataPage" <c:if test="${list.size()>0}">class="hidden"</c:if>>
				<td colspan="6" align="center">暂无数据</td>
			</tr>
		  </tfoot>
	    </table>
	  </div>
	  <!-- 分页 strat -->
	  <%@include file="/WEB-INF/view/common/pageInfo.jsp"%>
	  <!-- 分页 end -->
	</div>
<!-- 表格 end -->
</div>
<!-- 中间内容 end -->
<!-- 运动员明细模态框 strat -->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
<script src="${contextPath}/static/localjs/teamParticipat/index.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%>