<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->

<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>系统配置管理</li>
	<li><a href="${contextPath}/System/sysDept">组织结构管理</a></li>
	<li class="active">部门信息</li>
</ol>

<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<div class="panel panel-default form-header">
		<div class="panel-heading">
				<h3 class="panel-title">部门信息</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<form class="form-horizontal">
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName1">上级部门</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="pareDeptName" value="${info.sysDept.deptName}" placeholder="请选择上级部门">
								<input type="hidden" value="${info.cid}" id="cid">
								<input type="hidden" value="${info.state}" id="state">
								<input type="hidden" value="${info.pareCdoe}" id="pareCdoe">
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName2">部门名称</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="deptName" value="${info.deptName}"
									placeholder="请输入部门简称">
							</div>
							<div class="col-md-1" style="padding-left:0;">
					        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
					        </div>
						</div>
					</div>
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName3">部门昵称</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="deptSname" value="${info.deptSname}"
									placeholder="请输入部门昵称">
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName4">排序号</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="orderNum" value="${info.orderNum}"
									placeholder="请输入排序号">
							</div>
						</div>
					</div>
					
					<div class="col-md-10 col-md-offset-1">
                       <div class="form-group">
                           <label class="col-md-2 control-label" for="xzInputName13">备注</label>
                           <div class="col-md-10" style="width: 79%;">
                               <textarea class="form-control" rows="3" id="backup">${info.backup}</textarea>
                           </div>
                       </div>
                     </div>
                      <div class="col-md-10 col-md-offset-1" style="width: 66%;margin-left: 22%;">
                             <div class="panel panel-default">
                             <div class="panel-heading">选择用户</div>
                              <fieldset>
                                <table class="table dchannel-table">
                                   <tbody>
                                 <tr class="item-default">
                                     <td align="right" style="width: 50%;">
                                           <select id="sel_all_area" multiple="multiple" size="10" style="width:100%;height:180px">
                                                   <c:forEach items="${listUser}" var="user" varStatus="loop">
                                                     <option value="${user.cid}">${user.userSname}</option>
                                                   </c:forEach>
                                            </select>
                                    </td>
                                     <td style="width: 50px;" valign="middle">
                                         <button type="button" class="btn btn-default btn-small" id="btn_select_all_area"><i class=" icon-double-angle-right"></i></button>
                                         <button type="button" class="btn btn-default btn-small" id="btn_choose_selected_area"><i class=" icon-angle-right"></i></button>
                                        <button type="button" class="btn btn-default btn-small" id="btn_remove_selected_area"><i class="icon-angle-left"></i></button>
                                         <button type="button" class="btn btn-default btn-small" id="btn_remove_all_area"><i class=" icon-double-angle-left"></i></button>
                                     </td>
                                     <td style="width: 50%;">
                                         <select id="sel_selected_areas"  multiple="multiple" size="10" style="width:100%;height:180px">
                                                   <c:forEach items="${deptUser}" var="user" varStatus="loop">
                                                     <option value="${user.cid}">${user.userSname}</option>
                                                   </c:forEach>
                                         </select>
                                     </td>
                                 </tr>
                                   </tbody>
                                </table>
                             </fieldset>
                             </div>
                           </div>
                     
                     
                     
					<div class="col-md-10 col-md-offset-1" style="width: 80%;">
						<button type="button" id="cancleBtnTo" class="btn btn-warning myformbtn pull-right">取消</button>
						<button type="button" id="submitBtn" class="btn btn-success myformbtn pull-right">保存</button>
					</div>
				</form>
			</div>

		</div>
	</div>
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/js/select.js"></script>
<script src="${contextPath}/static/localjs/stsyem/addInfoDept.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%></div>