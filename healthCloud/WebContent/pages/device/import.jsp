<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%String contextPath = request.getContextPath();%>
<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<jsp:include page="/pages/base/base.jsp"></jsp:include>

<style type="text/css">
#left,#openClose, #right {
	float: left;
}

#left {
    position:fixed;    
}

#openClose {
	width: 10px;
	cursor: pointer;
	position:fixed;
	left: 140px;
	top:45%;
}

#right {
    position:relative;
    left:160px;
}
#openClose.close {
	background-position: 1px center;
}

#openClose, #openClose.close {
	background: #fdfdfd url(<%=contextPath%>/res/img/openclose.png) no-repeat -29px center;
}

</style>

</head>
<body>
	<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
		
		<div class="row-fluid">
			<div id="left" class="collapse in" style="width: 130px;">
				<div id="secondMenu">
					<ul class="nav nav-pills nav-stacked text-center">
						<shiro:hasPermission name="submenu:device:list">
							<li class="active"><a href="<%=contextPath%>/device/list" target="_self"><span>终端列表</span></a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="submenu:device:setting">
							<li><a href="<%=contextPath%>/devicesetting" target="_self"><span>终端设置</span></a></li>
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
			<div id="openClose" class="close" data-toggle="collapse" data-target="#left">&nbsp;</div>
			<div id="right" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;">
				<ul class="nav nav-tabs">
					<li role="presentation"><a href="list">终端列表</a></li>
					<li role="presentation" class="active"><a href="">批量导入终端设备</a></li>
				</ul>
				<div class="modal fade bs-example-modal-sm" id="organizationTree"
					tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
					<div class="modal-dialog" style="width: 450px;" role="document">
						<div class="modal-content"></div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->

				<div class="col-md-12"
					style="padding-right: 0px; padding-top: 10px;">
					<form action="" id="deviceForm" method="post" enctype="multipart/form-data" class="form-horizontal">
						<div class="modal-body">
							<input type="hidden" name="deviceId" value="${device.deviceId}">
							<div class="form-group">
								<label class="col-sm-2 control-label">
									<span style="color: red;">* 请选择设备的所属机构</span>
								</label>
								<div class="col-sm-6">
									<div class="input-group">
										<input type="text" class="form-control" id="orgName" name="orgName" value="${org.orgName}" placeholder="请选择所属机构" readonly="readonly" > 
										<input type="hidden" name="orgId" id="orgId" value="${org.orgId}" /> 
										<span class="input-group-btn">
											<button class="btn btn-default" id="orgBtn" type="button" data-toggle="modal"
												href="/healthCloud/org/showOrgTree?orgId=${orgId }" data-target="#organizationTree">
												<i class="fa fa-search"></i>
											</button>
										</span>
									</div>
								</div>
								
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">
									<span style="color: red;">* 请上传Excel文件</span>
								</label>
								<div class="col-sm-6">
									<input type="file" id="deviceFile" name="deviceFile" style="display:none;"
										accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
									<div class="input-group"> 
										<input type="text" id="deviceFilePath" class="form-control" readonly="readonly" required="required">
										<span class="input-group-btn">
											<a class="btn btn-default" onclick="$('input[id=deviceFile]').click();">浏览</a>
										</span>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">
									<span style="color: red;">模板文件下载</span>
								</label>
								<div class="col-sm-6">
									<!-- <a href="javascript:;" onclick="downloadTpl()">点此处下载模板文件</a> -->
									<button type="button" class="btn btn-info" onclick="downloadTpl()" >点此处下载模板文件</button>
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-2 control-label"></label>
								<div class="col-sm-10">
									<button type="button" id="importBtn" class="btn btn-primary">导入</button>&nbsp;
									<button type="reset" class="btn btn-default" >重置</button>
								</div>
							</div>

							<div class="form-group">
								<fieldset class="imptips">
									<legend>&nbsp;&nbsp;&nbsp;&nbsp;重要说明：</legend>
									<ol>
										<li>导入数据前请先下载模板，按模板规定的格式填写数据，否则无法导入；</li>
										<li>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件；</li>
										<li>导入前请按示例数据填写相应数据项，导入时请删除示例数据；</li>
									</ol>
								</fieldset>
							</div>

						</div>
					</form>
				</div>

				<div class="col-md-12" style="padding-right: 0px; margin-top: -10px;"></div>


			</div>
		</div>
	</div>

	<script type="text/javascript">
	
		$("#right").width(window.innerWidth- $("#left").width() - $("#openClose").width() -60);
		
		$('#left').on('show.bs.collapse', 
			function () {
				$("#left").show();
				$("#left").width("130px");
				$("#right").width(window.innerWidth - $("#left").width() - $("#openClose").width() -60);
				$("#right").css('left','160px');
				$("#openClose").css('left','140px');
			}
		)
		
		$('#left').on('hide.bs.collapse', 
			function () {
				$("#left").hide();
				$("#left").width("0px");
				$("#right").width(window.innerWidth - $("#left").width() - $("#openClose").width() -45);
				$("#right").css('left','20px');
				$("#openClose").css('left','0px');
			}
		)

		$('input[id=deviceFile]').change(function() {
			$('#deviceFilePath').val($(this).val());
		});

		function downloadTpl() {
			window.location.href = "<%=contextPath%>/res/template/【模板】终端设备数据导入模板.xlsx";
		}
		

		function validateData() {
			return $("#deviceForm").validate({
				// debug : true,
				rules : {
					orgName : "required",
					deviceFile : {
						required : true,
						isExcel : true,
						fileSize : true
					}
				},
				errorPlacement : function(error, element) {
					layer.tips($(error).text(), element, {
						tips : 3,
						tipsMore : true
					});
				}
			}).form();

		}

		$(function() {
			$("#deviceForm").validate({
				// debug : true,
				rules : {
					orgName : "required",
					deviceFile : {
						required : true,
						isExcel : true,
						fileSize : true
					}
				},
				errorPlacement : function(error, element) {
					layer.tips($(error).text(), element, {
						tips : 3,
						tipsMore : true
					});
				}
			});

			$('#importBtn').click(function() {
				if (validateData()) {
					$('#deviceForm').ajaxSubmit({
						url : '<%=contextPath%>/device/batchImport',
						dataType : 'json',
						type : "post",
						success : function(data) {
							if (data.result) {
								layer.msg(data.msg, { anim: 6 });
								window.location.href = "<%=contextPath%>/device/list?orgId=" + $('#orgId').val();
							}else{
								layer.msg(data.msg, { anim: 6 });
							}
						},
						error : function(data) {
							layer.msg('批量导入终端失败', { anim: 6 });
						}
					});
				}
			});
		});
	</script>
</body>
</html>
