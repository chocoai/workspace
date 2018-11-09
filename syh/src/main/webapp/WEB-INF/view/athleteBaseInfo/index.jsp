<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<style>
.list-group-item{
padding: 8px 15px;
}
.panel-footer ul.pager li a.active{
z-index: 2;
color: #fff;
background-color: #337ab7;
border-color: #337ab7;
}
</style>
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>报名信息管理</li>
	<li class="active">运动员基本信息管理</li>
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
					<i class="icon-th-large"></i> &nbsp;单位列表
				</div>
		<!-- 		<div class="pad-lrtb5">
					<input type="text" class="form-control mar-lrtb5"
						placeholder="请输入单位名称/简称...">
				</div> -->
				<!-- List group -->
				<input type="hidden" id="pageNo">
				<input type="hidden" id="unitCid">
				<a id="refreshUnit" href="javascript:"></a>
				<div class="list-group" id="unitList"></div>
				<div class="panel-footer" id="panelID">
				</div>
			</div>
		</div>
		<!-- 左侧列表组 end -->

		<!-- 右侧列表组内容 strat -->
		<div class="col-md-9">
			<div class="row">
				<div class="col-md-6">
					<shiro:hasPermission name="/athleteBaseInfo/updateInfo">
						<a class="btn btn-success difbtn" href="javascript:;" role="button" id="save"> <i class="icon-plus"></i>
							&nbsp;新增
						</a> <a id="edit" class="btn btn-info difbtn" href="javascript:;"
							role="button"> <i class="icon-edit"></i> &nbsp;编辑
						</a> 
					</shiro:hasPermission>
					<shiro:hasPermission name="/athleteBaseInfo/deleteInfo">
						<a id="deleteBtn" class="btn btn-warning difbtn"
							href="javascript:;" role="button"> <i class="icon-minus"></i>
							&nbsp;删除
						</a> 
					</shiro:hasPermission>
					<a id="refreshTable" href="javascript:"></a>
				</div>
				<div class="col-md-6  pull-right">
					<div class="input-group pull-right">
						<input type="text" class="form-control" placeholder="请输入运动员名称/简称" id="athleteName"> 
						<span class="input-group-btn">
							<button type="button" class="btn btn-primary pull-right" id="queryBtn">搜索</button>
						</span>
					</div>
				</div>
			</div>

			<!-- 表格 strat -->
	<div class="mytable">
		<div class="table-responsive">
			<table class="table table-hover table-bordered table-striped">
				<thead>
					<tr>
						<th><input type="checkbox" id ="chooseAll"></th>
						<th>姓名</th>
						<th>运动员类型</th>
						<th>别名</th>
						<th>性别</th>
						<th>单位名称</th>
						<th>民族</th>
						<th>出生日期</th>
						<th>证件号</th>
					</tr>
				</thead>
				<tbody id ="list">
				</tbody>
				<tfoot>
					<tr id="noDataPage" class="hidden">
						<td colspan="9" align="center">暂无数据</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!-- 分页 start -->
		<div id="pageID"></div>
		<!-- 分页 end -->
	</div>
	<!-- 表格 end -->

</div>
<!-- 中间内容 end -->
 
<!-- 底部固定 strat -->
<div class="col-md-12 col-sm-12 col-xs-12 mybottom-box">
    <div class="col-md-offset-3 col-md-9 col-sm-offset-3 col-sm-9 col-lg-offset-2 col-lg-10 col-xs-12">
       <div class="col-md-9 pad-l10 col-sm-12 col-xs-12">
         <shiro:hasPermission name="/athleteBaseInfo/exportExcel">
	         <a id="exportExcelBtn" class="btn btn-warning difbtn" href="javascript:;" role="button"> 
	         	<i class="icon-minus"></i>&nbsp;模板
			 </a> 
		 </shiro:hasPermission>
         <shiro:hasPermission name="/athleteBaseInfo/importExcel">
	         <a id="importBtn" class="btn btn-warning difbtn" href="javascript:;" role="button"> 
	         	<i class="icon-minus"></i>&nbsp;导入
			 </a> 
		 </shiro:hasPermission>
         <shiro:hasPermission name="/athleteBaseInfo/moveAthlete">
	         <a id="moveBtn" class="btn btn-warning difbtn" href="javascript:;" role="button"> 
	         	<i class="icon-minus"></i>&nbsp;移动
			 </a> 
		 </shiro:hasPermission>
       </div>
    </div>
</div>
<!-- 底部固定 end -->
<link href="${contextPath}/static/css/chosen/chosen.css" rel="stylesheet">

<!-- 单位列表模态框 strat -->
<!-- Modal -->
<div class="modal fade" id="unitModal" tabindex="-1" role="dialog" aria-labelledby="unitModal">
  <div class="modal-dialog modal-lg" role="document" style="width: 300px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">单位列表</h4>
      </div>
      
      <div class="modal-body" id="unitBody" ></div>

	  <input type="hidden" id="ahtleteCids">
      <div class="modal-footer">
        	<button type="button" id="submitBtn" class="btn btn-success myformbtn pull-right">保存</button>
        	<button type="button" id="closeBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<!-- 单位列表模态框模态框 end -->
<!-- 导入模态框 strat -->
<!-- Modal -->
<div class="modal fade" id="importModal" tabindex="-1" role="dialog" aria-labelledby="importModal">
  <div class="modal-dialog modal-lg" role="document" style="width: 500px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">导入<code>(只支持xlsx.xls格式)</code></h4>
      </div>
      <!-- <div class="modal-body">
			<div class="row">
				<div class="form-horizontal">
      				<div class="col-md-11">
							<div class="input-group m-b">
								<span class="input-group-btn">
					               <button type="button" class="btn btn-primary" id="file1">选择单位</button> 
					            </span>
            					<input type="text" class="form-control">
							</div>
					</div>
      				<div class="col-md-11">
							<div class="input-group m-b">
								<span class="input-group-btn">
					               <button type="button" class="btn btn-primary" id="file1">选择文件</button> 
					            </span>
            					<input type="text" class="form-control" id="file2">
            					<input type="file" style="display: none;" id="file3" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" >
							</div>
					</div>
				</div>
			</div>
		</div> -->
      
      
      
       <div class="input-group m-b modal-body">
      		<span class="input-group-btn">
               <button type="button" class="btn btn-primary" id="file1">选择文件</button> 
            </span>
            <input type="text" class="form-control" id="file2">
            <form id= "importForm"  method="post" enctype="multipart/form-data"  action="importExcel">
            	<input type="hidden" id="importUnitCid" name="unitCid">
            	<input type="file" style="display: none;" id="file3" name="file"  accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" >
            </form>
      </div>
       <div class="input-group m-b modal-body">
       		<div class="alert alert-warning" >注意事项：
	      		<ol>
	               <li>导入文件只支持Execl文件</li>
	               <li>导入数据的Execl文件严格遵守模板格式填写数据</li>
	               <li>导入数据将按单位分批导入，需要优先选择单位</li>
	               <li>目前Execl文件导入功能只支持IE、火狐、谷歌浏览器</li>
	           </ol>
	           <span class="text-danger">注意：如果IE浏览器出现无法导入问题可以修改浏览器的安全级别</span><br/>
	           <span class="text-warning">方式：工具->Internet选项->安全->自定义级别->找到"其他"中的"将本地文件上传至服务器时包含本地目录路径，选中"启用"即可</span>
      		</div>
      </div>
      
      
      <div class="modal-footer">
        	<button type="button" id="importSubmitBtn" class="btn btn-success myformbtn pull-right">导入</button>
        	<button type="button" id="importcloseBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<!-- 导入模态框模态框 end -->
<script src="${contextPath}/static/js/bootstrap-prettyfile.js"></script>
<script src="${contextPath}/static/js/chosen/chosen.jquery.js"></script>
<script src="${contextPath}/static/localjs/athleteBaseInfo/index.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script>
