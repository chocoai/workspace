<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp" %> 
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
  <span class="icon-home">&nbsp;&nbsp;</span>
  <li>首页</li>
  <li>项目信息管理</li>
  <li><a href="${contextPath}/System/scoreRecord">计分记牌配置</a></li>
  <li class="active">计分记牌配置</li>
</ol>
<!-- 当前位置 end -->
<!-- 中间内容 strat -->
<div class="content">
  <div class="panel panel-default form-header">
    <div class="panel-heading">
      <h3 class="panel-title">计分记牌配置</h3>
    </div>
    <div class="panel-body">
        <div class="row">
            <form class="form-horizontal" id="formId">
               <div class="col-md-5 col-md-offset-1">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="ruleName">方式名称</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="ruleName" name="ruleName" value="${scoreRecord.ruleName }" placeholder="请输入方式名称">
                      <input type="hidden" value="${scoreRecord.ruleName}" id="clickRuleName">
                      <input type="hidden" value="${scoreRecord.cid}" id="cid" name="cid">
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="trophy">奖杯个数</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="trophy" name="trophy" value="${scoreRecord.trophy }" placeholder="请输入奖杯个数" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5 col-md-offset-1">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="certificate">证书</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="certificate" name="certificate" value="${scoreRecord.certificate }" placeholder="请输入证书数" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="goldMedal">金牌</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="goldMedal" name="goldMedal" value="${scoreRecord.goldMedal }" placeholder="请输入金牌数" onkeyup="value=value.replace(/[^\d]/g,'')"> 
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5 col-md-offset-1">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="silverMedal">银牌</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="silverMedal" name="silverMedal" value="${scoreRecord.silverMedal }" placeholder="请输入银牌数" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="bronzeMedal">铜牌</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="bronzeMedal" name="bronzeMedal" value="${scoreRecord.bronzeMedal }" placeholder="请输入铜牌数" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5 col-md-offset-1">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="firstScore">第一名得分</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="firstScore" name="firstScore" value="${scoreRecord.firstScore }" placeholder="请输入第一名得分" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="secondScore">第二名得分</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="secondScore" name="secondScore" value="${scoreRecord.secondScore }" placeholder="请输入第二名得分" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5 col-md-offset-1">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="thirdScore">第三名得分</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="thirdScore" name="thirdScore" value="${scoreRecord.thirdScore }" placeholder="请输入第三名得分" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="fourthScore">第四名得分</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="fourthScore" name="fourthScore" value="${scoreRecord.fourthScore }" placeholder="请输入第四名得分" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5 col-md-offset-1">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="fifthScore">第五名得分</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="fifthScore" name="fifthScore" value="${scoreRecord.fifthScore }" placeholder="请输入第五名得分" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="sixthScore">第六名得分</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="sixthScore" name="sixthScore" value="${scoreRecord.sixthScore }" placeholder="请输入第六名得分" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                   <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5 col-md-offset-1">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="seventhScore">第七名得分</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="seventhScore" name="seventhScore" value="${scoreRecord.seventhScore }" placeholder="请输入第七名得分" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                  <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="eightScore">第八名得分</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="eightScore" name="eightScore" value="${scoreRecord.eightScore }" placeholder="请输入第八名得分" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                  <div class="col-md-1" style="padding-left:0;">
			        	<span style="color: red;font-size: 20px;line-height: 34px;">*</span>
			       </div>
                 </div>
               </div>
               <div class="col-md-5 col-md-offset-1">
                 <div class="form-group">
                   <label class="col-md-4 control-label" for="orderNum">排序号</label>
                   <div class="col-md-7" style="padding-right:5px;">
                      <input type="text" class="form-control" id="orderNum" name="orderNum" value="${scoreRecord.orderNum }" placeholder="请输入排序号" onkeyup="value=value.replace(/[^\d]/g,'')">
                   </div>
                 </div>
               </div>
               <div class="col-md-5">
                 <div class="form-group">
                     <label class="col-md-4 control-label" for="backup">备注</label>
                     <div class="col-md-7" style="padding-right:5px;">
                         <textarea class="form-control" rows="3" id="backup"  name="backup">${scoreRecord.backup }</textarea>
                     </div>
                 </div>
               </div>
               <div class="col-md-10 col-md-offset-1" style="padding-right:40px;">
                   <button id="cancleBtn" type="button" class="btn btn-warning myformbtn pull-right">取消</button>
                   <button id="submitBtn" type="button" class="btn btn-success myformbtn pull-right">保存</button>
               </div>
           </form>
        </div>
    </div>
  </div>
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/project/addScoreRecord.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp" %> 