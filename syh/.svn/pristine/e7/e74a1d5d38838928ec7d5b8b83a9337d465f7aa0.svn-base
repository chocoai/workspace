<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<link href="${contextPath}/static/css/chosen/chosen.css" rel="stylesheet">
<!-- 当前位置 strat -->

<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>报名信息管理</li>
	<li><a href="${contextPath}/System/athleteParticipat">运动员参赛信息</a></li>
	<li class="active">参赛信息</li>
</ol>
<!-- 当前位置 end -->
<!-- 中间内容 strat -->
<div class="content">
  <div class="panel panel-default form-header">
    <div class="panel-heading">
		 <h3 class="panel-title">参赛信息</h3> 
    </div>
    <div class="panel-body">
        <div class="row">
           <form class="form-horizontal">
           		<input type="hidden" id="unitCids">
               <div class="col-md-5 col-md-offset-1">
                 <div class="form-group">
                   <label class="col-md-4 control-label">比赛项目</label>
                   <div class="col-md-7">
	                     <input type="text" class="form-control" id="projectName" value= "${info.projectManager.projectName}" disabled="disabled">
	                     <input type="hidden" id="projectCid" value= "${info.projectManager.cid}">
	                     <input type="hidden" id="projectIsSex" value= "${info.projectManager.gender}">
	                     <input type="hidden" id="projectIsTeam" value= "${info.projectManager.isTeam}">
	                     <input type="hidden" id="cid" value= "${info.cid}">
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			        </div>
                 </div>
               </div>
               <div class="col-md-5">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="athleteType">运动员类型</label>
                   <input type="hidden" id="athletesTypeclick" value="0"/>
                   <div class="col-md-7">
                      	<select class="form-control" name="athletesType" id="athletesType"></select>
                   </div>
                 </div>
               </div>
               
               <div class="col-md-5 col-md-offset-1">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="unitName">单位名称</label>
                   <input type="hidden" id="selectedUnit" value= "${info.unitCids}">
                   <div class="col-md-7" >
                   		<div class="collapse in">
	                   		 <select id="unitCid" data-placeholder="选择单位..." <c:if test="${info.projectManager.isTeam==1}">multiple="multiple"</c:if> class="chosen-select"  style="width:100%;" tabindex="2" name="unitCid">
								   <option value="">选择单位...</option>
								  <c:forEach items="${list}" var="unit">
								          <option value="${unit.cid}" hassubinfo="true" id="unit_${unit.cid}">${unit.unitName}</option>
								  </c:forEach>
							 </select>
                   		</div>
                   </div>
                  <!--  selected='selected'  -->
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			        </div>
                 </div>
               </div>
                <div class="col-md-5">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="athleteType">是否代表单位</label>
                   <div class="col-md-7">
                    	<div class="radio-inline sex">
						    <label><input type="radio" name="isperUnit" value="1" 
								<c:if test="${info.isperUnit eq '1'}">
								checked="checked"
								</c:if>
							>是</label>
						</div>
						<div class="radio-inline sex">
							<label><input type="radio" name="isperUnit" value="0"
								<c:if test="${info.isperUnit eq '0'}">
								checked="checked"
								</c:if>
							>否</label>
						</div>
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			        </div>
                 </div>
               </div>
               
                <div class="col-md-10 col-md-offset-1">
					  <div class="form-group">
							<label class="col-md-2 control-label" for="xzInputName13">选择运动员</label>
							<div class="col-md-1" style="width: 80%;">
								  <div class="panel panel-default">
							            <div class="panel-heading">注：${info.backup}</div>
							            <fieldset >  
										    <table class="table dchannel-table">
										       <tbody>
											          <tr class="item-default">
											             <td align="right" style="width: 50%;" >
									                		<select id="sel_all_area" size="10" style="width:100%;height:180px">
														    </select>
											             </td>
											             <td style="width: 50px;" valign="middle">
											                  <button type="button" class="btn btn-default btn-small" id="btn_choose_selected_area" style=" margin-top: 50px;"><i class=" icon-angle-right"></i></button>
											                  <button type="button" class="btn btn-default btn-small" id="btn_remove_selected_area"><i class="icon-angle-left"></i></button>
											              </td>
											              <td style="width: 50%;">
											                  <select id="sel_selected_areas"  size="10" style="width:100%;height:180px">
											                  		<c:forEach items="${info.participatDetails}" var="bean" varStatus="loop">
										                                <option value="${bean.athleteBaseInfo.cid}" unit_index="${bean.athleteBaseInfo.unitCid}" type_index="${bean.athleteBaseInfo.athletesType}" title="性别:${bean.athleteBaseInfo.sexStr}" class="athlete">
										                                	${bean.athleteBaseInfo.athleteName}<c:if test="${not empty bean.athleteBaseInfo.alias}">(${bean.athleteBaseInfo.alias})</c:if>/${bean.athleteBaseInfo.unitInfo.unitName}
										                                </option>
										                            </c:forEach>
											                  </select>
											              </td>
											          </tr>
										         </tbody>
										    </table>
										 </fieldset>
						          </div>
							</div>
					  </div>
				</div>
                
                
                
                
                <div class="col-md-10 col-md-offset-1" style="width: 80%;">
                   <button id="cancleBtnTo" type="button" class="btn btn-warning myformbtn pull-right">取消</button>
                   <button id="submitBtn" type="button" class="btn btn-success myformbtn pull-right">保存</button>
               </div>
           </form>
        </div>
       
    </div>
  </div>
</div>
<!-- 中间内容 end -->

<script src="${contextPath}/static/js/chosen/chosen.jquery.js"></script>
<script src="${contextPath}/static/localjs/participatInfo/addInfo.js"></script>
<script src="${contextPath}/static/js/bootstrap-suggest.min.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 