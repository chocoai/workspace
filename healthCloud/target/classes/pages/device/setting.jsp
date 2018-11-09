<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String contextPath = request.getContextPath();
%>
<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:device:list">
						<li><a href="<%=contextPath%>/device/list" target="_self"><span>终端列表</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:device:setting">
						<li class="active"><a href="<%=contextPath%>/devicesetting" target="_self"><span>终端设置</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:device:video:list">
						<li><a href="<%=contextPath%>/deviceVideo/list" target="_self"><span>视频设备管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:device:video:monitor">
						<li><a href="<%=contextPath%>/device/videoControl" target="_self"><span>视频监控</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;">
			<ul class="nav nav-tabs">
				<li role="presentation" class="active"><a href="#">终端设置</a></li>
			</ul>
			
			<div class="modal fade bs-example-modal" id="organizationTree" tabindex="-1" role="dialog">
				<div class="modal-dialog" style="width: 450px;" role="document">
					<div class="modal-content"></div>
				</div>
			</div>

			<div class="modal fade bs-example-modal" id="linkmanSetting" tabindex="-1" role="dialog">
				<div class="modal-dialog" style="width: 450px;" role="document">
					<div class="modal-content"></div>
				</div>
			</div>
			
			<div class="modal fade bs-example-modal" id="remindSetting" tabindex="-1" role="dialog">
				<div class="modal-dialog" style="width: 450px;" role="document">
					<div class="modal-content"></div>
				</div>
			</div>

			<div class="modal fade bs-example-modal" id="dsfSetting" tabindex="-1" role="dialog">
				<div class="modal-dialog" style="width: 450px;" role="document">
					<div class="modal-content"></div>
				</div>
			</div>
			
			<div class="modal fade bs-example-modal-lg" id="locationSetting" tabindex="-1" role="dialog">
				<div class="modal-dialog" style="width: 980px;" role="document">
					<div class="modal-content"></div>
				</div>
			</div>
			
			<div class="modal fade bs-example-modal" id="heartrateSetting" tabindex="-1" role="dialog">
				<div class="modal-dialog" style="width: 450px;" role="document">
					<div class="modal-content"></div>
				</div>
			</div>

			<table id="dataTable" class="table table-hover table-bordered" style="margin-top: 10px;">
				<thead>
					<tr>
						<th style="width: 10%;text-align: center;">序号</th>
						<th style="width: 10%;text-align: center;">选择</th>
						<th style="width: 60%;text-align: center;">命令名称</th>
						<th style="width: 20%;text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="text-align: center;">1</td>
						<td style="text-align: center;">
							<input type="checkbox" id="check_device" name="check_device" value="1">
						</td>
						<td style="text-align: center;">联系人设置</td>
						<td style="text-align: center;">
							<button type="button" class="btn btn-primary btn-xs"
								data-toggle="modal" data-target="#linkmanSetting"
								href="<%=contextPath%>/devicesetting/toLinkmanSetting?imei=${imei}">编辑参数</button>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">2</td>
						<td style="text-align: center;">
							<input type="checkbox" id="check_device" name="check_device" value="2">
						</td>
						<td style="text-align: center;">提醒设置</td>
						<td style="text-align: center;">
							<button type="button" class="btn btn-primary btn-xs"
								data-toggle="modal" data-target="#remindSetting"
								href="<%=contextPath%>/devicesetting/toRemindSetting?imei=${imei}">编辑参数</button>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">3</td>
						<td style="text-align: center;">
							<input type="checkbox" id="check_device" name="check_device" value="3">
						</td>
						<td style="text-align: center;">数据采集频率</td>
						<td style="text-align: center;">
							<button type="button" class="btn btn-primary btn-xs"
								data-toggle="modal" data-target="#dsfSetting"
								href="<%=contextPath%>/devicesetting/toDsfSetting?imei=${imei}">编辑参数</button>
						</td>
					</tr>
					
					<tr>
						<td style="text-align: center;">4</td>
						<td style="text-align: center;">
							<input type="checkbox" id="check_device" name="check_device" value="4">
						</td>
						<td style="text-align: center;">安全定位设置</td>
						<td style="text-align: center;">
							<button type="button" class="btn btn-primary btn-xs"
								data-toggle="modal" data-target="#locationSetting"
								href="<%=contextPath%>/devicesetting/toLocationSetting?imei=${imei}">编辑参数</button>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">
							<input type="checkbox" id="check_device" name="check_device" value="5">
						</td>
						<td style="text-align: center;">心率安全范围设置</td>
						<td style="text-align: center;">
							<button type="button" class="btn btn-primary btn-xs"
								data-toggle="modal" data-target="#heartrateSetting"
								href="<%=contextPath%>/devicesetting/toHeartrateSetting?imei=${imei}">编辑参数</button>
						</td>
					</tr>
				</tbody>
			</table>
			
			<ol class="breadcrumb" style="margin-top: 15px;">
				<div class="form-inline">
					<div class="form-group">
						<label for="">下发命令类别</label>&nbsp;
						<select id="sendType" name="sendType" class="form-control">
					      <option value="0">按终端下发</option>
						  <option value="1">按机构下发</option>
						</select>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<div id="orgDiv" class="form-group" style="display: none;">
						<label for="">选择机构</label>&nbsp; 
						<div class="input-group" >
							<input type="text" class="form-control" id="organizationName" value="${org.orgName }" placeholder="请选择所属机构" readonly="readonly">
							<input type="hidden" name="organizationId" id="organizationId" value="${org.orgId }">
							<span class="input-group-btn">
			  			        <button class="btn btn-default" id="orgBtn" type="button" data-toggle="modal" href="<%=contextPath%>/org/showOrgTree?orgId=${orgId }" data-target="#organizationTree"><i class="fa fa-search"></i></button>
			  			    </span>
		  			    </div>
					</div>
					&nbsp;&nbsp;
					<div id="deviceDiv" class="form-group" >
						<input type="text" class="form-control" style="width: 270px;" id="imei" name="imei" value="${imei }" placeholder="请输入IMEI号，多个IMEI号用逗号分开">
					</div>
					&nbsp;&nbsp;
					<button type="button" id="sendCmd" class="btn btn-info">给终端下发命令</button>							
				</div>
			</ol>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
	$("#sendType").change(function() {
		if ($(this).val() == 0) {
			$("#deviceDiv").show();
			$("#orgDiv").hide();
		}else{
			$("#orgDiv").show();
			$("#deviceDiv").hide();
		}	
	});
	
	$("#sendCmd").click(function() {
		var sendType = $("#sendType").val();  //0终端   1机构
		var organizationId = $("#organizationId").val(); 
		var imei = $("#imei").val(); 
		var chk_value =[]; 
		$('input[name="check_device"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
		}); 		
		var values = chk_value.join(','); 
		if(chk_value.length == 0){
			layer.msg('您没有选择下发命令名称！', { anim: 6 });
			return ;
		}
		if(sendType == 0 && imei == ''){
			layer.msg('请填写终端imei号！', { anim: 6 });
			return ;
		}
		if(sendType == 1 && organizationId == ''){
			layer.msg('请选择机构！', { anim: 6 });
			return ;
		}
		$.ajax({
			cache : true,
			type : "post",
			url : "<%=contextPath%>/devicesetting/settingAll",
			async : false,
			dataType : "json",
			data : {"sendType" : sendType ,"organizationId" : organizationId ,"imei":imei,"check_device" : values  },
			traditional : true,
			success : function(data) {
				var json = JSON.parse(data);  //
				if (json.success) {
					layer.msg(json.msg, { anim: 6 });
				}else{
					layer.msg(json.msg, { anim: 6 });
				}
			},
			error : function(data) {
				layer.msg('设置失败', { anim: 6 });
			}
		});
	});
	
</script>
</body>
</html>
