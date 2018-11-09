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
					<li class="active"><a href="<%=contextPath%>/healthService/toAddMerBodyFat?memberId=${member.memberId }&merId=${merId }">体脂</a></li>
					<li><a href="<%=contextPath%>/healthService/toAddMerBP?memberId=${member.memberId }&merId=${merId }">血压</a></li>
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
					<h3 class="text-center">为${member.realName }录入体检报告数据(体脂)</h3>
					<form action="addMerBodyFat" id="merBodyFatForm" method="post" class="form-horizontal">
						<div class="modal-body">
							<input type="hidden" name="memberId" value="${member.memberId }">
							<input type="hidden" name="cid" value="${bodyFat.cid}">
							<input type="hidden" name="merId" value="${merId }">
							<div class="form-group">
								<label class="col-sm-2 control-label">体脂占比(%)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="bfp" name="bfp" placeholder="" value="${bodyFat.bfp}" >
								</div>
								<label class="col-sm-2 control-label">体脂肪量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="bf" name="bf" placeholder="" value="${bodyFat.bf}" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">非脂肪量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="nonFat" name="nonFat" placeholder="" value="${bodyFat.nonFat}" >
								</div>
								<label class="col-sm-2 control-label">体水占比(%)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="bwp" name="bwp" placeholder="" value="${bodyFat.bwp}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">水含量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="bw" name="bw" placeholder="" value="${bodyFat.bw}" >
								</div>
								<label class="col-sm-2 control-label">矿物质(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="mineral" name="mineral" placeholder="" value="${bodyFat.mineral}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">蛋白质含量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="protein" name="protein" placeholder="" value="${bodyFat.protein}" >
								</div>
								<label class="col-sm-2 control-label">细胞內液(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="icw" name="icw" placeholder="" value="${bodyFat.icw}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">细胞外液(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="ecw" name="ecw" placeholder="" value="${bodyFat.ecw}" >
								</div>
								<label class="col-sm-2 control-label">肌肉量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="muscleMass" name="muscleMass" placeholder="" value="${bodyFat.muscleMass}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">脂肪调节(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="fatRegulation" name="fatRegulation" placeholder="" value="${bodyFat.fatRegulation}" >
								</div>
								<label class="col-sm-2 control-label">体重调节(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="weightRegulation" name="weightRegulation" placeholder="" value="${bodyFat.weightRegulation}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">肌肉调节(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="muscleRegulation" name="muscleRegulation" placeholder="" value="${bodyFat.muscleRegulation}" >
								</div>
								<label class="col-sm-2 control-label">基础代谢(kcal)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="bmr" name="bmr" placeholder="" value="${bodyFat.bmr}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">内脏脂肪等级</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="visceralFatLevel" name="visceralFatLevel" placeholder="" value="${bodyFat.visceralFatLevel}" >
								</div>
								<label class="col-sm-2 control-label">骨骼量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="skeletalMass" name="skeletalMass" placeholder="" value="${bodyFat.skeletalMass}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">肌肉率(%)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="musclePercent" name="musclePercent" placeholder="" value="${bodyFat.musclePercent}" >
								</div>
								<label class="col-sm-2 control-label">躯干肌肉量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="trunkMuscle" name="trunkMuscle" placeholder="" value="${bodyFat.trunkMuscle}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">躯干脂肪量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="trunkFat" name="trunkFat" placeholder="" value="${bodyFat.trunkFat}" >
								</div>
								<label class="col-sm-2 control-label">左臂肌肉量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="leftArmMuscle" name="leftArmMuscle" placeholder="" value="${bodyFat.leftArmMuscle}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">右臂肌肉量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="rightArmMuscle" name="rightArmMuscle" placeholder="" value="${bodyFat.rightArmMuscle}" >
								</div>
								<label class="col-sm-2 control-label">左腿肌肉量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="leftLegMuscle" name="leftLegMuscle" placeholder="" value="${bodyFat.leftLegMuscle}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">右腿肌肉量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="rightLegMuscle" name="rightLegMuscle" placeholder="" value="${bodyFat.rightLegMuscle}" >
								</div>
								<label class="col-sm-2 control-label">左臂脂肪量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="leftArmFat" name="leftArmFat" placeholder="" value="${bodyFat.leftArmFat}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">右臂脂肪量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="rightArmFat" name="rightArmFat" placeholder="" value="${bodyFat.rightArmFat}" >
								</div>
								<label class="col-sm-2 control-label">左腿脂肪量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="leftLegFat" name="leftLegFat" placeholder="" value="${bodyFat.leftLegFat}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">右腿脂肪量(Kg)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="rightLegFat" name="rightLegFat" placeholder="" value="${bodyFat.rightLegFat}" >
								</div>
								<label class="col-sm-2 control-label">结论</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="conclusion" name="conclusion" placeholder="" value="${bodyFat.conclusion}" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">建议</label>
								<div class="col-sm-10">
									<textarea rows="3" cols="" class="form-control" id="advice"
										name="advice" placeholder="" style="resize: none;">${bodyFat.advice}</textarea>
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
			$("#merBodyFatForm").validate({
				rules : {
					bfp : {
						number : true
					},
					bf : {
						number : true
					},
					nonFat : {
						number : true
					},
					bwp : {
						number : true
					},

					bw : {
						number : true
					},
					mineral : {
						number : true
					},
					protein : {
						number : true
					},
					icw : {
						number : true
					},

					ecw : {
						number : true
					},
					muscleMass : {
						number : true
					},
					fatRegulation : {
						number : true
					},
					weightRegulation : {
						number : true
					},
					muscleRegulation : {
						number : true
					},

					bmr : {
						number : true
					},

					visceralFatLevel : {
						number : true
					},

					skeletalMass : {
						number : true
					},

					musclePercent : {
						number : true
					},

					trunkMuscle : {
						number : true
					},

					trunkFat : {
						number : true
					},

					leftArmMuscle : {
						number : true
					},

					rightArmMuscle : {
						number : true
					},

					leftLegMuscle : {
						number : true
					},

					rightLegMuscle : {
						number : true
					},

					leftArmFat : {
						number : true
					},

					rightArmFat : {
						number : true
					},

					leftLegFat : {
						number : true
					},

					rightLegFat : {
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