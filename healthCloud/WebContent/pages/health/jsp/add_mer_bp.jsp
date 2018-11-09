<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<jsp:include page="/pages/base/base.jsp"></jsp:include>

</head>
<body>
	<div class="container-fluid">
		<div class="row" style="padding-left: 180px; position: relative;">
			<div style="width:150px; position: absolute; left: 0; top: 0;">
				<ul class="nav nav-pills nav-stacked text-center">
					<li><a href="<%=contextPath%>/healthService/record?memberId=${member.memberId }" ><span>健康档案</span></a></li>
					<li><a href="<%=contextPath%>/healthService/data?memberId=${member.memberId }" ><span>健康数据</span></a></li>
					<li class="active"><a href="<%=contextPath%>/healthService/mer?memberId=${member.memberId }" ><span>体检报告</span></a></li>
					<li><a href="<%=contextPath%>/healthService/proposal?memberId=${member.memberId }" ><span>医师建议</span></a></li>
				</ul>
			</div>
			<div class="col-sm-12">
				<!-- tab strat -->
				<ul class="nav nav-tabs">
					<li><a href="<%=contextPath%>/healthService/toAddMerBmi?memberId=${member.memberId }&merId=${merId }">身高体重</a></li>
					<li><a href="<%=contextPath%>/healthService/toAddMerBodyFat?memberId=${member.memberId }&merId=${merId }">体脂</a></li>
					<li class="active"><a href="<%=contextPath%>/healthService/toAddMerBP?memberId=${member.memberId }&merId=${merId }">血压</a></li>
					<li><a href="<%=contextPath%>/healthService/toAddMerBG?memberId=${member.memberId }&merId=${merId }">血糖</a></li>
					
					<li><a href="<%=contextPath%>/healthService/toAddMerUA?memberId=${member.memberId }&merId=${merId }">尿酸</a></li>
					<li><a href="<%=contextPath%>/healthService/toAddMerCholesterol?memberId=${member.memberId }&merId=${merId }">胆固醇</a></li>
					<li><a href="<%=contextPath%>/healthService/toAddMerBO?memberId=${member.memberId }&merId=${merId }">血氧</a></li>
					<li><a href="<%=contextPath%>/healthService/toAddMerTemp?memberId=${member.memberId }&merId=${merId }">体表温度</a></li>
					
					<li><a href="<%=contextPath%>/healthService/toAddMerECG?memberId=${member.memberId }&merId=${merId }">心电测量</a></li>
					<li><a href="<%=contextPath%>/healthService/toAddMerWHR?memberId=${member.memberId }&merId=${merId }">腰臀比</a></li>
				</ul>
				<!-- tab end -->
				
				<div class="col-md-12">
					<h3 class="text-center">为${member.realName }录入体检报告数据(血压)</h3>
					<form action="addMerBP" id="merBPForm" method="post" class="form-horizontal">
						<div class="modal-body">
							<input type="hidden" name="memberId" value="${member.memberId }">
							<input type="hidden" name="cid" value="${bp.cid}">
							<input type="hidden" name="merId" value="${merId }">
							
							<div class="form-group">
								<label class="col-sm-2 control-label">舒张压</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="dbp" name="dbp" placeholder="" value="${bp.dbp}" >
								</div>
								<label class="col-sm-2 control-label">收缩压</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="sbp" name="sbp" placeholder="" value="${bp.sbp}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">脉搏</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="sphygmus" name="sphygmus" placeholder="" value="${bp.sphygmus}" >
								</div>
								<label class="col-sm-2 control-label">结论</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="conclusion" name="conclusion" placeholder="" value="${bp.conclusion}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">建议</label>
								<div class="col-sm-10">
									<textarea rows="3" cols="" class="form-control" id="advice"
										name="advice" placeholder="" style="resize: none;">${bp.advice}</textarea>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label"></label>
								<div class="col-sm-10">
									<button type="submit" class="btn btn-primary">保存</button>&nbsp;
									<button type="reset" class="btn btn-default" >重置</button>
								</div>
							</div>
						</div>
					</form>
					
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			$("#merBPForm").validate({
				rules : {
					dbp : {
						number : true
					},
					sbp : {
						number : true
					},

					sphygmus : {
						number : true
					}
				},
				errorPlacement : function(error, element) {
					layer.tips($(error).text(), element, {
						tips : 3,
						tipsMore : true
					});
				}
			});
		});
	</script>
</body>
</html>