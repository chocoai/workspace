<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->

<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>报名信息管理</li>
	<li>运动员参赛信息</li>
	<li class="active">新增团队参赛信息</li>
</ol>
<!-- 当前位置 end -->
<!-- 中间内容 strat -->
<div class="content">
  <div class="panel panel-default form-header">
    <div class="panel-heading">
		 <h3 class="panel-title">新增团队参赛信息</h3>
    </div>
    <div class="panel-body">
        <div class="row">
           <form class="form-horizontal">
           		<input type="hidden" id="unitCids">
               <div class="col-md-5 col-md-offset-1">
                 <div class="form-group">
                   <label class="col-md-4 control-label">比赛项目</label>
                   <div class="col-md-8">
                     <input type="text" class="form-control" id="projectName" placeholder="请输入比赛项目" >
                     <input type="hidden" id="projectCid">
                   </div>
                 </div>
               </div>
               <div class="col-md-5">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="athleteType">运动员类型</label>
                   <div class="col-md-8">
                      <select class="form-control" id="athleteType">
                      	  <option value="0">青少年类</option>
                      	  <option value="1">全民健身成人类(地方组)</option>
                      	  <option value="2">全民健身成人类(企事业组)</option>
                      	  <option value="3">全民健身成人类(其他企事业组)</option>
                      </select>
                   </div>
                 </div>
               </div>
               <div class="col-md-5 col-md-offset-1">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="unitName">单位名称</label>
                   <div class="col-md-8">
                      <div class="input-group">
						<input type="text" class="form-control" data-id=""
							id="unitName" placeholder="请输入单位名称"> <input type="hidden" id="unitCid">
						<div class="input-group-btn">
							<button style="height: 34.5px" type="button"
								class="btn btn-white dropdown-toggle" data-toggle="dropdown">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu dropdown-menu-right" role="menu">
							</ul>
						</div>
					</div>
                   </div>
                 </div>
               </div>
               <div class="col-md-10 col-md-offset-1">
                 <div class="panel panel-default">
                 <div class="panel-heading">选择运动员</div>
                  <fieldset>
                    <table class="table dchannel-table">
                       <tbody>
                      <tr class="item-default">
                          <td align="right" style="width: 50%;">
                              <select id="sel_all_area" multiple="multiple" size="10" style="width:100%;">
                              </select>
                         </td>
                          <td style="width: 50px;" valign="middle">
                              <button type="button" class="btn btn-default btn-small" id="btn_select_all_area"><i class=" icon-double-angle-right"></i></button>
                              <button type="button" class="btn btn-default btn-small" id="btn_choose_selected_area"><i class=" icon-angle-right"></i></button>
                             <button type="button" class="btn btn-default btn-small" id="btn_remove_selected_area"><i class="icon-angle-left"></i></button>
                              <button type="button" class="btn btn-default btn-small" id="btn_remove_all_area"><i class=" icon-double-angle-left"></i></button>
                          </td>
                          <td style="width: 50%;">
                              <select id="sel_selected_areas"  multiple="multiple" size="10" style="width:100%;">
                              </select>
                          </td>
                      </tr>
                       </tbody>
                    </table>
                 </fieldset>
                 </div>
               </div>
                <div class="col-md-10 col-md-offset-1">
                   <button id="cancleBtn" type="button" class="btn btn-warning myformbtn pull-right">取消</button>
                   <button id="submitBtn" type="button" class="btn btn-success myformbtn pull-right">保存</button>
               </div>
           </form>
        </div>
       
    </div>
  </div>
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/teamParticipat/addInfo.js"></script>
<script src="${contextPath}/static/js/bootstrap-suggest.min.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%></div>