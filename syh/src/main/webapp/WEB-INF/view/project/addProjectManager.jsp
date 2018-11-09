<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp" %> 
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
  <span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
    <li>项目信息管理</li>
    <li><a href="${contextPath}/System/projectManager">竞赛项目管理</a></li>
    <li class="active">项目信息</li>
</ol>
<!-- 当前位置 end -->
<!-- 中间内容 strat -->
<div class="content">
  <div class="panel panel-default form-header">
	    <div class="panel-heading">
	      <h3 class="panel-title">
	      			<a href="javaScript:void(0)" onClick="$('#panel-body_collapse').click()">比赛项目信息</a>
					<a data-toggle="collapse" href="#panel-body" id="panel-body_collapse"></a>
	      </h3>
	    </div>
		<div class="panel-body  collapse in" id="panel-body">
			<form class="form-horizontal" id="formId">
				<div class="row collapse in" id="row">
					<div class="form-horizontal">
						 <div class="col-md-5 col-md-offset-1">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="projectName">项目名称</label>
			                   <div class="col-md-7" style="padding-right:5px;">
			                      <input type="text" class="form-control" name="projectName" value="${projectManager.projectName }" placeholder="请输入项目名称" >
			                      <input type="hidden" value="${projectManager.cid}" id="cid" name="cid">
			                      <input type="hidden" value="${projectManager.projectName}" id="oldProjectName">
			                   </div>
			                   <div class="col-md-1" style="padding-left:0;">
						        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
						        </div>
			                 </div>
			               </div>
			               <div class="col-md-5">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="pCid">分类名称</label>
			                   <div class="col-md-7" style="padding-right:5px;">
			                      <input type="text" class="form-control" id="classNameNode" name="classNameNode"  value="${projectManager.classManager.classifyName }" placeholder="请输入分类名称">
			                      <input type="hidden" id="pCid" value="${projectManager.pCid}" name="pCid">
			                      <input type="hidden" value="${projectManager.pCid}" id="oldPCid">
			                   </div>
			                   <div class="col-md-1" style="padding-left:0;">
						        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
						        </div>
			                 </div>
			               </div>
			               <div class="col-md-5 col-md-offset-1">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="alias">项目别名</label>
			                   <div class="col-md-7" style="padding-right:5px;">
			                      <input type="text" class="form-control" id="alias" name="alias" value="${projectManager.alias}" placeholder="请输入项目别名">
			                   </div>
			                 </div>
			               </div>
			               <div class="col-md-5">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="isTeam">是否团体</label>
			                   <div class="col-md-7" style="padding-right:5px;">
			                       <div class="radio-inline sex">
			                         	<label><input type="radio" name="isTeam""  value="0" <c:if test="${projectManager.isTeam eq '0'}">checked="checked"</c:if>>个人赛</label>
			                      </div>
			                      <div class="radio-inline sex">
			                         	<label><input type="radio" name="isTeam""  value="1" <c:if test="${projectManager.isTeam eq '1'}">checked="checked"</c:if>>团体赛</label>
			                      </div>
			                   </div>
			                   <div class="col-md-1" style="padding-left:0;">
						        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
						        </div>
			                 </div>
			               </div>
			               <div class="col-md-5 col-md-offset-1">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="scoreCard">计分记牌方式</label>
			                   <input type="hidden" id="scoreRecordclick" value="${projectManager.scoreCard}"/>
			                   <div class="col-md-7" style="padding-right:5px;">
			                      <select class="form-control" id="scoreCard" name="scoreCard"></select>
			                   </div>
			                   <div class="col-md-1" style="padding-left:0;">
						        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
						        </div>
			                 </div>
			               </div>
			               <div class="col-md-5">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="resultType">成绩类型</label>
			                   <div class="col-md-7" style="padding-right:5px;">
			                      <select class="form-control" id="resultType" name="resultType" value="${projectManager.resultType}">
			                      	  <option value="0" <c:if test="${'0' eq projectManager.resultType}">selected</c:if>>只录取名次</option>
			                      	  <option value="1" <c:if test="${'1' eq projectManager.resultType}">selected</c:if>>时间下行(比赛时间越短成绩越好)</option>
			                      	  <option value="2" <c:if test="${'2' eq projectManager.resultType}">selected</c:if>>时间上行(比赛时间越长成绩越好)</option>
			                      	  <option value="3" <c:if test="${'3' eq projectManager.resultType}">selected</c:if>>数值上行(比赛数值越高成绩越好)</option>
			                      	  <option value="4" <c:if test="${'4' eq projectManager.resultType}">selected</c:if>>数值下行(比赛数值越低成绩越好)</option>
			                      </select>
			                   </div>
			                   <div class="col-md-1" style="padding-left:0;">
						        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
						        </div>
			                 </div>
			               </div>
			               <div class="col-md-5 col-md-offset-1">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="resultUnit">成绩单位</label>
			                    <input type="hidden" id="resultUnitclick" value="${projectManager.resultUnit}"/>
			                   <div class="col-md-7" style="padding-right:5px;">
			                    	<select class="form-control" id="resultUnit" name="resultUnit"></select>
			                       <%--  <input type="text" class="form-control" id="resultUnit" value="${projectManager.resultUnit}" placeholder="请输入成绩单位"> --%>
			                   </div>
			                   <div class="col-md-1" style="padding-left:0;">
						        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
						       </div>
			                 </div>
			               </div>
			               <div class="col-md-5">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="mastersLevel">健将标准</label>
			                   <div class="col-md-7" style="padding-right:5px;" name="masterValue">
			                      <input type="text" class="form-control" name="mastersLevel" id="mastersLevel" value="${projectManager.mastersLevel}" 
			                      >
			                   </div>
			                 </div>
			               </div>
			               <div class="col-md-5 col-md-offset-1">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="finalAdmission">晋级决赛人数</label>
			                   <div class="col-md-7" style="padding-right:5px;">
			                      <input type="text" class="form-control" name="finalAdmission" value="${projectManager.finalAdmission}" placeholder="请输入晋级决赛人数">
			                   </div>
			                 </div>
			               </div>
			               <div class="col-md-5">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="firstLevel">一级标准</label>
			                   <div class="col-md-7" style="padding-right:5px;" name="masterValue">
			                      <input type="text" class="form-control" name="firstLevel" id="firstLevel" value="${projectManager.firstLevel}"
			                      >
			                   </div>
			                 </div>
			               </div>
			               <div class="col-md-5 col-md-offset-1">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="finalistsNum">决赛录取人数</label>
			                   <div class="col-md-7" style="padding-right:5px;">
			                      <input type="text" class="form-control" name="finalistsNum" value="${projectManager.finalistsNum}" placeholder="请输入决赛录取人数">
			                   </div>
			                 </div>
			               </div>
			               <div class="col-md-5">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="secondLevel">二级标准</label>
			                   <div class="col-md-7" style="padding-right:5px;" name="masterValue">
			                      <input type="text" class="form-control" name="secondLevel" id="secondLevel" value="${projectManager.secondLevel}" 
			                     >
			                   </div>
			                 </div>
			               </div>
			               <div class="col-md-5 col-md-offset-1">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="gender">性别</label>
			                   <div class="col-md-7" style="padding-right:5px;">
			                   	  <input type="hidden" id="gender" value="${projectManager.gender}">
			                      <div class="radio-inline sex">
			                         <label><input type="radio" name="gender"  value="0" <c:if test="${projectManager.gender eq '0'}">checked="checked"</c:if>>男</label>
			                      </div>
			                      <div class="radio-inline sex">
			                         <label><input type="radio" name="gender"  value="1" <c:if test="${projectManager.gender eq '1'}">checked="checked"</c:if>>女</label>
			                      </div>
			                      <div class="radio-inline sex">
			                         <label><input type="radio" name="gender"  value="2" <c:if test="${projectManager.gender eq '2'}">checked="checked"</c:if>>混合</label>
			                      </div>
			                   </div>
			                    <div class="col-md-1" style="padding-left:0;">
						        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
						        </div>
			                 </div>
			               </div>
			               <div class="col-md-5">
			                 <div class="form-group">
			                   <label class="col-md-4 control-label" for="threeLevel">三级标准</label>
			                   <div class="col-md-7" style="padding-right:5px;" name="masterValue">
			                      <input type="text" class="form-control" name="threeLevel" id="threeLevel" value="${projectManager.threeLevel}" 
			                      >
			                   </div>
			                 </div>
			               </div>
			               <div class="col-md-5 col-md-offset-1">
				                 <div class="form-group">
				                   <label class="col-md-4 control-label" for="orderNum">排序号</label>
				                   <div class="col-md-7" style="padding-right:5px;">
				                      <input type="text" class="form-control" name="orderNum" value="${projectManager.orderNum}" placeholder="请输入排序号" onkeyup="value=value.replace(/[^\d]/g,'')">
				                   </div>
				                 </div>
				           </div>
				           <div class="col-md-10 col-md-offset-1">
				                 <div class="form-group">
				                     <label class="col-md-2 control-label" for="backup">备注</label>
				                     <div class="col-md-10" style="width: 80%;">
				                         <textarea class="form-control" rows="3" name="backup">${projectManager.backup }</textarea>
				                     </div>
				                 </div>
				           </div>
					</div>
				</div>
				<div class="col-md-10 col-md-offset-1" style="padding-right: 40px;">
					<button id="cancleBtnTo" type="button"
						class="btn btn-warning myformbtn pull-right">取消</button>
					<button id="submitBtn" type="button"
						class="btn btn-success myformbtn pull-right">保存</button>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- 中间内容 end -->
<link href="${contextPath}/static/css/style.onoffswitch.css" rel="stylesheet">
<script src="${contextPath}/static/localjs/project/addProjectManager.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp" %> 
