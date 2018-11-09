<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
String path  = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>

<title>来电信息</title>
<jsp:include page="/pages/base/base.jsp"></jsp:include>

<style>
#contentType, #contentTpl, #contentMobileTpl {
	display: block;
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}
</style>

</head>
<body>
	<div class="container-fluid" id="tabId">
		<ul class="nav nav-tabs" >
			<li class="active"><a href="#">来电信息</a></li>
			<li ><a href="#" >服务派单</a></li>
		</ul>

		<div class="modal fade bs-example-modal-sm" id="organizationTree" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
			<div class="modal-dialog" style="width: 450px;" role="document">
				<div class="modal-content"></div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>

		<div class="row-fluid">
			<div class="col-sm-12">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">所属机构</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="orgName" name="orgName" value="${member.org.orgName}" readonly="readonly">
							</div>
							<label class="col-sm-2 control-label">来电类型</label>
							<div class="col-sm-4">
								<select id="memberType" name="memberType" class="form-control" readonly="readonly">
									<c:if test="${member.memberType == 0}">
										<option value="0">普通会员</option>
									</c:if>
									<c:if test="${member.memberType == 1}">
										<option value="1">VIP会员</option>
									</c:if>
									<c:if test="${member.memberType == 9}">
										<option value="9">未知来电</option>
									</c:if>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">来电姓名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="realName" name="realName" value="${member.realName}" readonly="readonly">
							</div>
							<label class="col-sm-2 control-label">性别</label>
							<div class="col-sm-4">
								<select id="gender" name="gender" class="form-control" readonly="readonly">
									<c:if test="${member.gender == 1}">
										<option value="1">男</option>
									</c:if>
									<c:if test="${member.gender == 0}">
										<option value="0">女</option>
									</c:if>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">昵称</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="nickName" name="nickName" value="${member.nickName}" readonly="readonly">
							</div>
							<label class="col-sm-2 control-label">登录账号</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="userCode" name="userCode" value="${member.user.userCode}" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">手机号码</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="phoneNo" name="phoneNo" value="${member.phoneNo}" readonly="readonly">
							</div>
							<label class="col-sm-2 control-label">身份证号</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="identityCard" name="identityCard" value="${member.identityCard}" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">员工姓名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="empName" name="empName" value="${member.empName}" readonly="readonly">
							</div>
							<label class="col-sm-2 control-label">医师姓名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="docName" name="docName" value="${member.docName}" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">电子邮箱</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="email" name="email" value="${member.email}" readonly="readonly">
							</div>
							<label class="col-sm-2 control-label">状态</label>
							<div class="col-sm-4">
								<select id="status" name="status" class="form-control" readonly="readonly">
									<c:if test="${member.status == 0}">
										<option value="0">正常</option>
									</c:if>
									<c:if test="${member.status == 1}">
										<option value="1">禁用</option>
									</c:if>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">备注</label>
							<div class="col-sm-10">
								<textarea class="form-control" id="remark" name="remark" style="resize: none;"  rows="" cols="" readonly="readonly">${member.remark}</textarea>
							</div>
						</div>
					</div>
			</div>
			
			  <div class="col-sm-12" style="display: none;">
				<form action="<%=path%>/member/creatOrderFromTel" id="orderForm"  method="post" class="form-horizontal" >
					<div class="modal-body">
					<input type="hidden" class="form-control"  name="memberId" value="${member.memberId}" >
						<div class="form-group">
							<label class="col-sm-2 control-label">服务分类</label>
							<div class="col-sm-4">
								<form:select id="serviceCategory" path="service.serviceCategory" onchange="getServiceTypeItem();" name="serviceCategory" items="${applicationScope.service_category__}" itemLabel="value" itemValue="key" cssStyle="" class="form-control"/>
							</div>
							<label class="col-sm-2 control-label">服务类型</label>
							<div class="col-sm-4">
								<select id="serviceType" name="serviceType" onchange="getServiceItem();" class="form-control" >
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">服务名称</label>
							<div class="col-sm-4">
								<select id="serviceId" name="serviceId" onchange="setPrice();" class="form-control" >
								</select>
							</div>
							<label class="col-sm-2 control-label">服务价格</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="servicePrice"  name="servicePrice" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">服务对象</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="serviceObject" value="${member.realName}" >
							</div>
							<label class="col-sm-2 control-label">预约时间</label>
							<div class="col-sm-4">
								<input type="text" class="form-control"  name="startTime" id="serviceStime" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">手机号码</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="phoneNo" name="phoneNo" value="${member.phoneNo}" readonly="readonly">
							</div>
							<label class="col-sm-2 control-label">联系地址</label>
							<div class="col-sm-4">
								<input type="text" class="form-control"  name="address" >
							</div>
						</div>
	
						<div class="form-group">
							<label class="col-sm-2 control-label">备注</label>
							<div class="col-sm-10">
								<textarea class="form-control"  name="remark" style="resize: none;"  rows="" cols="" ></textarea>
							</div>
						</div>
					<div class="col-sm-10 col-sm-offset-2">
						<input type="button"  class="btn btn-primary" onclick="saveOrder();" value="保存">
					</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
 <script type="text/javascript">
 	//tab
 	$("#tabId ul li").click(function(){
 		$(this).addClass("active").siblings().removeClass("active");
 		var index=$("#tabId ul li").index(this);
 		$(".row-fluid .col-sm-12").eq(index).show().siblings().hide();
 	});
		
 	//二级联动查询
    function getServiceTypeItem(){
    	var value  = $("#serviceCategory").val();
    	$.ajax({
			cache : true,
            type : "post",
            url : '<%=path%>/member/getTypeItem',
            data : { parentValue : value, type : "service_category" },
            async : true,
            dataType : "json",
            traditional : true,
            success : function(data) {
            	 $("#serviceId").empty();
            	 $("#serviceType").empty();
            	 $("#serviceType").append("<option value=''></option>");
            	for(var i=0;i<data.length;i++){
            		 $("#serviceType").append("<option value='"+data[i].dictValue+"'>"+data[i].dictKey+"</option>"); // 1.为Select追加一个Option(下拉项)    
            	}
          	},
            error :  function (XMLHttpRequest, textStatus, errorThrown) {
            	layer.msg(textStatus, { anim: 6 });
          	}
        });
    }
		
		
 	function setPrice(){
 		var serviceText  = $("#serviceId option:selected").text();
 		var price = serviceText.substring(serviceText.indexOf("(")+1,serviceText.indexOf("_"));
 		$("#servicePrice").val(price);
 	}
	
	function getServiceItem(){
    	var serviceType  = $("#serviceType").val();
     	$.ajax({
			cache : true,
            type : "post",
            url : '<%=path%>/member/getServiceItem',
            data : { serviceType : serviceType },
            async : true,
            dataType : "json",
            traditional : true,
            success : function(data) {
            	$("#serviceId").empty();
            	$("#serviceId").append("<option value=''></option>");
            	for(var i=0;i<data.length;i++){
            		 $("#serviceId").append("<option value="+data[i].serviceId+">"+data[i].serviceName+"("+data[i].servicePrice+data[i].serviceUnit+"_"+data[i].serviceOrg+")</option>"); // 1.为Select追加一个Option(下拉项)    
            	}
          	},
            error :  function (XMLHttpRequest, textStatus, errorThrown) {
            	layer.msg(textStatus, { anim: 6 });
          	}
        }); 
	}
	function saveOrder(){
     	$.ajax({
			cache : true,
            type : "post",
            url : '<%=path%>/member/creatOrderFromTel',
            data :  $('#orderForm').serialize(),
            async : true,
            dataType : "json",
            traditional : true,
            success : function(data) {
            	layer.msg("派单成功！");
            	window.close();
          	},
            error :  function (XMLHttpRequest, textStatus, errorThrown) {
            	layer.msg(textStatus, { anim: 6 });
          	}
        }); 
	}
	
	$('#serviceStime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		format: 'yyyy-mm-dd',
		startView: 2,
		minView: 0,
		maxView: 4,
		minView: 'month',
		forceParse: 0
    });
		
</script>
</html>