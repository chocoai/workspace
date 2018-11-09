<%@page import="com.yhcrt.healthcloud.health.entity.MedicalExaminationReport"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mer Detail</title>
</head>

<jsp:include page="/pages/base/base.jsp"></jsp:include>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="col-sm-10 col-sm-offset-1 mar-t10">
					<h3 class="text-center">${member.realName }体检报告</h3>
					<div class="scrollspy">
						<div class="tjbg-cell pad-t30">
							<div class="panel panel-warning">
								<div class="panel-heading" id="sgtz">
									<span class="font-s20">身高体重</span>
									&nbsp;&nbsp;&nbsp;测量时间：${mer.merTime }
								</div>
								<table class="table table-bordered mytable bg-orange">
									<tr>
										<th>身高（cm）</th>
										<td>${bmi.height }</td>
										<th>体重（kg）</th>
										<td>${bmi.weight }</td>
									</tr>
									<tr>
										<th>BMI</th>
										<td>${bmi.bmi }</td>
										<th>理想体重（kg）</th>
										<td>${bmi.idealWeight }</td>
									</tr>
								</table>
								<div class="panel-footer bg-orange font-s18">结论：${bmi.conclusion }</div>
							</div>
							<!-- bmi chart -->
							<div id="bmi" style="width: 80%; height: 600px;" align="center"></div>
							<script type="text/javascript">
							
								var myChart = echarts.init(document.getElementById('bmi'));
								
								var bims = new Array();
								var bimsTime =new Array();
								<c:forEach items="${merList}" var="mer">
								bims.push("${mer.bmi.bmi}");
								bimsTime.push("${mer.bmi.uploadTime}");
								</c:forEach>
								var option = {
									title : {
										text : 'BMI',
									},
									tooltip : {
										trigger : 'axis'
									},
									legend : {
										data : [ 'bmi' ]
									},
									toolbox : {
										show : true,
										feature : {
											mark : {
												show : true
											},
											dataView : {
												show : true,
												readOnly : false
											},
											magicType : {
												show : true,
												type : [ 'bar' ]
											},
											restore : {
												show : true
											},
											saveAsImage : {
												show : true
											}
										}
									},
									calculable : true,
									xAxis : [ {
										type : 'category',
										data : bimsTime
									} ],
									yAxis : [ {
										type : 'value'
									} ],
									series : [ {
										name : 'bmi',
										type : 'line',
										data : bims,
										barWidth : 20
									} ]
								};
								myChart.setOption(option);
							</script>
						</div>

						<div class="tjbg-cell pad-t30">
							<div class="panel panel-warning">
								<div class="panel-heading" id="tzcl">
									<span class="font-s20">体脂测量</span>
									&nbsp;&nbsp;&nbsp;测量时间：${mer.merTime }
								</div>
								<table class="table table-bordered mytable bg-orange">
									<tr>
										<th>体脂占比（%）</th>
										<td>${bodyFat.bfp }</td>
										<th>体脂脂肪（kg）</th>
										<td>${bodyFat.bf }</td>
										<th>非脂肪量（kg）</th>
										<td>${bodyFat.nonFat }</td>
										<th>体水占比（%）</th>
										<td>${bodyFat.bwp }</td>
									</tr>
									<tr>
										<th>水含量（kg）</th>
										<td>${bodyFat.bw }</td>
										<th>矿物质（kg）</th>
										<td>${bodyFat.mineral }</td>
										<th>蛋白质含量（kg）</th>
										<td>${bodyFat.protein }</td>
										<th>细胞內液（kg）</th>
										<td>${bodyFat.icw }</td>
									</tr>
									<tr>
										<th>细胞外液（kg）</th>
										<td>${bodyFat.ecw }</td>
										<th>细胞外液（kg）</th>
										<td>${bodyFat.ecw }</td>
										<th>肌肉量（kg）</th>
										<td>${bodyFat.muscleMass }</td>
										<th>脂肪调节（kg）</th>
										<td>${bodyFat.fatRegulation }</td>
									</tr>
									<tr>
										<th>体重调节（kg）</th>
										<td>${bodyFat.weightRegulation }</td>
										<th>肌肉调节（kg）</th>
										<td>${bodyFat.muscleRegulation }</td>
										<th>基础代谢（kcal）</th>
										<td>${bodyFat.bmr }</td>
										<th>内脏脂肪等级</th>
										<td>${bodyFat.visceralFatLevel }</td>
									</tr>
									<tr>
										<th>骨骼量（kg）</th>
										<td>${bodyFat.skeletalMass }</td>
										<th>肌肉率（%）</th>
										<td>${bodyFat.musclePercent }</td>
										<th>躯干肌肉量（kg）</th>
										<td>${bodyFat.trunkMuscle }</td>
										<th>躯干脂肪量（kg）</th>
										<td>${bodyFat.trunkFat }</td>
									</tr>
									<tr>
										<th>左臂肌肉量（kg）</th>
										<td>${bodyFat.leftArmMuscle }</td>
										<th>右臂肌肉量（kg）</th>
										<td>${bodyFat.rightArmMuscle }</td>
										<th>右腿肌肉量（kg）</th>
										<td>${bodyFat.rightLegMuscle }</td>
										<th>左臂脂肪量（kg）</th>
										<td>${bodyFat.leftArmFat }</td>
									</tr>
									<tr>
										<th>左腿脂肪量（kg）</th>
										<td>${bodyFat.leftLegFat }</td>
										<th>右臂脂肪量（kg）</th>
										<td>${bodyFat.rightArmFat }</td>
										<th>右腿脂肪量（kg）</th>
										<td>${bodyFat.rightLegFat }</td>
										<td></td>
										<td></td>
									</tr>
								</table>
								<div class="panel-footer bg-orange font-s18">结论：${bodyFat.conclusion }</div>
							</div>
							<!-- bodyFat chart -->
							<div id="bodyFat" style="width: 80%; height: 600px;"
								align="center"></div>
							<script type="text/javascript">
								var myChart = echarts.init(document.getElementById('bodyFat'));
								
								var bf = new Array();
								var bfTime =new Array();
								<c:forEach items="${merList}" var="mer">
								bf.push("${mer.bodyFat.bf}");
								bfTime.push("${mer.bodyFat.uploadTime}");
								</c:forEach>
								var option = {
									title : {
										text : '体脂肪量',
									},
									tooltip : {
										trigger : 'axis'
									},
									legend : {
										data : [ '体脂肪量' ]
									},
									toolbox : {
										show : true,
										feature : {
											mark : {
												show : true
											},
											dataView : {
												show : true,
												readOnly : false
											},
											magicType : {
												show : true,
												type : [ 'line' ]
											},
											restore : {
												show : true
											},
											saveAsImage : {
												show : true
											}
										}
									},
									calculable : true,
									xAxis : [ {
										type : 'category',
										data : bfTime
									} ],
									yAxis : [ {
										type : 'value'
									} ],
									series : [ {
										name : '体脂肪量',
										type : 'bar',
										data : bf ,
										barWidth : 20
									} ]
								};
								myChart.setOption(option);
							</script>
						</div>

						<div class="tjbg-cell pad-t30">
							<div class="panel panel-warning">
								<div class="panel-heading" id="xyacl">
									<span class="font-s20">血压测试</span>
									&nbsp;&nbsp;&nbsp;测量时间：${mer.merTime }
								</div>
								<table class="table table-bordered mytable bg-orange">
									<tr>
										<th>（收缩压）高压（mmHg）</th>
										<td>${pressure.sbp }</td>
										<th>（舒张压）低压（mmHg）</th>
										<td>${pressure.dbp }</td>
										<th>脉搏（次/分）</th>
										<td>${pressure.sphygmus }</td>
									</tr>
								</table>
								<div class="panel-footer bg-orange font-s18">结论：${pressure.conclusion }</div>
							</div>
							<!-- bloodPressure chart -->
							<div id="bloodPressure" style="width: 80%; height: 600px;"
								align="center"></div>
							<script type="text/javascript">
								var myChart = echarts.init(document.getElementById('bloodPressure'));
								var dbp = new Array();
								var sbp = new Array();
								var dTime =new Array();
								<c:forEach items="${merList }" var="mer">
								dbp.push("${mer.bloodPressure.dbp}");
								sbp.push("${mer.bloodPressure.sbp}");
								dTime.push("${mer.bloodPressure.uploadTime}");
								</c:forEach>
								var option = {
									title : {
										text : '血压',
									},
									tooltip : {
										trigger : 'axis'
									},
									legend : {
										data : [ '舒张压', '收缩压' ]
									},
									toolbox : {
										show : true,
										feature : {
											mark : {
												show : true
											},
											dataView : {
												show : true,
												readOnly : false
											},
											magicType : {
												show : true,
												type : [ 'bar' ]
											},
											restore : {
												show : true
											},
											saveAsImage : {
												show : true
											}
										}
									},
									calculable : true,
									xAxis : [ {
										type : 'category',
										data : dTime
									} ],
									yAxis : [ {
										type : 'value'
									} ],
									series : [
											{
												name : '舒张压',
												type : 'line',
												data : dbp,
												barWidth : 20
											},
											{
												name : '收缩压',
												type : 'line',
												data : sbp,
												barWidth : 20
											} ]
								};
								myChart.setOption(option);
							</script>
						</div>

						<div class="tjbg-cell pad-t30">
							<div class="panel panel-warning">
								<div class="panel-heading" id="xtcl">
									<span class="font-s20">血糖测量</span>
									&nbsp;&nbsp;&nbsp;测量时间：${mer.merTime }
								</div>
								<table class="table table-bordered mytable bg-orange">
									<tr>
										<th>血糖值(mmol/L)</th>
										<td>${glucose.glucoseValue }</td>
										<th></th>
										<td></td>
										<th></th>
										<td></td>
									</tr>
								</table>
								<div class="panel-footer bg-orange font-s18">结论：${glucose.conclusion }</div>
							</div>
							<!-- bloodGlucose chart -->
							<div id="bloodGlucose" style="width: 80%; height: 600px;"
								align="center"></div>
							<script type="text/javascript">
								var myChart = echarts.init(document
										.getElementById('bloodGlucose'));
								
								var glucoseValue = new Array();
								var glucoseValueTime =new Array();
								<c:forEach items="${merList }" var="mer">
								glucoseValue.push("${mer.merBloodGlucose.glucoseValue}");
								glucoseValueTime.push("${mer.merBloodGlucose.uploadTime}");
								</c:forEach>
								var option = {
									title : {
										text : '血糖',
									},
									tooltip : {
										trigger : 'axis'
									},
									legend : {
										data : [ '血糖' ]
									},
									toolbox : {
										show : true,
										feature : {
											mark : {
												show : true
											},
											dataView : {
												show : true,
												readOnly : false
											},
											magicType : {
												show : true,
												type : [ 'bar' ]
											},
											restore : {
												show : true
											},
											saveAsImage : {
												show : true
											}
										}
									},
									calculable : true,
									xAxis : [ {
										type : 'category',
										data : glucoseValueTime
									} ],
									yAxis : [ {
										type : 'value'
									} ],
									series : [ {
										name : '血糖',
										type : 'line',
										data : glucoseValue,
										barWidth : 20
									} ]
								};
								myChart.setOption(option);
							</script>
						</div>

						<div class="tjbg-cell pad-t30">
							<div class="panel panel-warning">
								<div class="panel-heading" id="nscl">
									<span class="font-s20">尿酸测量</span>
									&nbsp;&nbsp;&nbsp;测量时间：${mer.merTime }
								</div>
								<table class="table table-bordered mytable bg-orange">
									<tr>
										<th>尿酸值(mmol/L)</th>
										<td>${uricAcid.uaValue }</td>
										<th></th>
										<td></td>
										<th></th>
										<td></td>
									</tr>
								</table>
								<div class="panel-footer bg-orange font-s18">结论：${uricAcid.conclusion }</div>
							</div>
							<!-- uricAcid chart -->
							<div id="uricAcid" style="width: 80%; height: 600px;"
								align="center"></div>
							<script type="text/javascript">
								var myChart = echarts.init(document
										.getElementById('uricAcid'));
								
								var uaValue = new Array();
								var uaValueTime =new Array();
								<c:forEach items="${merList }" var="mer">
								uaValue.push("${mer.acid.uaValue}");
								uaValueTime.push("${mer.acid.uploadTime}");
								</c:forEach>
								var option = {
									title : {
										text : '尿酸',
									},
									tooltip : {
										trigger : 'axis'
									},
									legend : {
										data : [ '尿酸' ]
									},
									toolbox : {
										show : true,
										feature : {
											mark : {
												show : true
											},
											dataView : {
												show : true,
												readOnly : false
											},
											magicType : {
												show : true,
												type : [ 'bar' ]
											},
											restore : {
												show : true
											},
											saveAsImage : {
												show : true
											}
										}
									},
									calculable : true,
									xAxis : [ {
										type : 'category',
										data : uaValueTime
									} ],
									yAxis : [ {
										type : 'value'
									} ],
									series : [ {
										name : '尿酸',
										type : 'line',
										data : uaValue,
										barWidth : 20
									} ]
								};
								myChart.setOption(option);
							</script>
						</div>

						<div class="tjbg-cell pad-t30">
							<div class="panel panel-warning">
								<div class="panel-heading" id="dgccl">
									<span class="font-s20">胆固醇测量</span>
									&nbsp;&nbsp;&nbsp;测量时间：${mer.merTime }
								</div>
								<table class="table table-bordered mytable bg-orange">
									<tr>
										<th>胆固醇(mmol/L)</th>
										<td>${cholesterol.cholesterol }</td>
										<th></th>
										<td></td>
										<th></th>
										<td></td>
									</tr>
								</table>
								<div class="panel-footer bg-orange font-s18">结论：${cholesterol.conclusion }</div>
							</div>
							<!-- cholesterol chart -->
							<div id="cholesterol" style="width: 80%; height: 600px;"
								align="center"></div>
							<script type="text/javascript">
								var myChart = echarts.init(document
										.getElementById('cholesterol'));
								
								var cholesterol = new Array();
								var cholesterolTime =new Array();
								<c:forEach items="${merList }" var="mer">
								cholesterol.push("${mer.cholesterol.cholesterol}");
								cholesterolTime.push("${mer.cholesterol.uploadTime}");
								</c:forEach>
								var option = {
									title : {
										text : '胆固醇',
									},
									tooltip : {
										trigger : 'axis'
									},
									legend : {
										data : [ '胆固醇' ]
									},
									toolbox : {
										show : true,
										feature : {
											mark : {
												show : true
											},
											dataView : {
												show : true,
												readOnly : false
											},
											magicType : {
												show : true,
												type : [ 'bar' ]
											},
											restore : {
												show : true
											},
											saveAsImage : {
												show : true
											}
										}
									},
									calculable : true,
									xAxis : [ {
										type : 'category',
										data : cholesterolTime
									} ],
									yAxis : [ {
										type : 'value'
									} ],
									series : [ {
										name : '胆固醇',
										type : 'line',
										data : cholesterol,
										barWidth : 20
									} ]
								};
								myChart.setOption(option);
							</script>
						</div>

						<div class="tjbg-cell pad-t30">
							<div class="panel panel-warning">
								<div class="panel-heading" id="xyangcl">
									<span class="font-s20">血氧测量</span>
									&nbsp;&nbsp;&nbsp;测量时间：${mer.merTime }
								</div>
								<table class="table table-bordered mytable bg-orange">
									<tr>
										<th>血氧值（%）</th>
										<td>${oxygen.boValue }</td>
										<th></th>
										<td></td>
										<th></th>
										<td></td>
									</tr>
								</table>
								<div class="panel-footer bg-orange font-s18">结论：${oxygen.conclusion }</div>
							</div>
							<!-- oxygen chart -->
							<div id="oxygen" style="width: 80%; height: 600px;"
								align="center"></div>
							<script type="text/javascript">
								var myChart = echarts.init(document
										.getElementById('oxygen'));
								var boValue = new Array();
								var boValueTime =new Array();
								<c:forEach items="${merList }" var="mer">
								boValue.push("${mer.bloodOxygen.boValue}");
								boValueTime.push("${mer.bloodOxygen.uploadTime}");
								</c:forEach>
								var option = {
									title : {
										text : '血氧含量',
									},
									tooltip : {
										trigger : 'axis'
									},
									legend : {
										data : [ '血氧' ]
									},
									toolbox : {
										show : true,
										feature : {
											mark : {
												show : true
											},
											dataView : {
												show : true,
												readOnly : false
											},
											magicType : {
												show : true,
												type : [ 'line' ]
											},
											restore : {
												show : true
											},
											saveAsImage : {
												show : true
											}
										}
									},
									calculable : true,
									xAxis : [ {
										type : 'category',
										data : boValueTime
									} ],
									yAxis : [ {
										type : 'value'
									} ],
									series : [ {
										name : '血氧',
										type : 'bar',
										data : boValue,
										barWidth : 20
									} ]
								};
								myChart.setOption(option);
							</script>
						</div>

						<div class="tjbg-cell pad-t30">
							<div class="panel panel-warning">
								<div class="panel-heading" id="tbwd">
									<span class="font-s20">体表温度</span>
									&nbsp;&nbsp;&nbsp;测量时间：2017-03-31 17:29:29
								</div>
								<table class="table table-bordered mytable bg-orange">
									<tr>
										<th>体温（℃）</th>
										<td>${temperature.temperature }</td>
										<th></th>
										<th></th>
									</tr>
								</table>
								<div class="panel-footer bg-orange font-s18">结论：${temperature.conclusion }</div>
							</div>
							<!-- temperature chart -->
							<div id="temperature" style="width: 80%; height: 600px;"
								align="center"></div>
							<script type="text/javascript">
								var myChart = echarts.init(document
										.getElementById('temperature'));
								
								var temperature = new Array();
								var temperatureTime =new Array();
								<c:forEach items="${merList }" var="mer">
								temperature.push("${mer.merTemperature.temperature}");
								temperatureTime.push("${mer.merTemperature.uploadTime}");
								</c:forEach>
								var option = {
									title : {
										text : '体表温度',
									},
									tooltip : {
										trigger : 'axis'
									},
									legend : {
										data : [ '温度' ]
									},
									toolbox : {
										show : true,
										feature : {
											mark : {
												show : true
											},
											dataView : {
												show : true,
												readOnly : false
											},
											magicType : {
												show : true,
												type : [ 'bar' ]
											},
											restore : {
												show : true
											},
											saveAsImage : {
												show : true
											}
										}
									},
									calculable : true,
									xAxis : [ {
										type : 'category',
										data : temperatureTime
									} ],
									yAxis : [ {
										type : 'value'
									} ],
									series : [ {
										name : '温度',
										type : 'line',
										data : temperature,
										barWidth : 20
									} ]
								};
								myChart.setOption(option);
							</script>
						</div>

						<div class="tjbg-cell pad-t30">
							<div class="panel panel-warning">
								<div class="panel-heading" id="xdcl">
									<span class="font-s20">心电测量</span>
									&nbsp;&nbsp;&nbsp;测量时间：${mer.merTime }
								</div>
								<table class="table table-bordered mytable bg-orange">
									<tr>
										<th>心率值（次/分）</th>
										<td>${electrocardiogram.rateValue }</td>
										<th></th>
										<td></td>
										<th></th>
										<td></td>
									</tr>
								</table>
								<div class="panel-footer bg-orange font-s18">结论：${electrocardiogram.conclusion }</div>
							</div>
							<img src="${electrocardiogram.electrocardiogram }"
								class="img-responsive center-block">
						</div>

						<div class="tjbg-cell pad-t30">
							<div class="panel panel-warning">
								<div class="panel-heading" id="ytbcl">
									<span class="font-s20">腰臀比测量</span>
									&nbsp;&nbsp;&nbsp;测量时间：${mer.merTime }
								</div>
								<table class="table table-bordered mytable bg-orange">
									<tr>
										<th>腰围</th>
										<td>${waistHipRatio.waistline }</td>
										<th>臀围</th>
										<td>${waistHipRatio.hipline }</td>
										<th>腰臀比</th>
										<td>${waistHipRatio.waistHipRatio }</td>
									</tr>
								</table>
								<div class="panel-footer bg-orange font-s18">结论：${waistHipRatio.conclusion }</div>
							</div>
							<!-- waistHipRatio chart -->
							<div id="waistHipRatio" style="width: 80%; height: 600px;"
								align="center"></div>
							<script type="text/javascript">
								var myChart = echarts.init(document
										.getElementById('waistHipRatio'));
								
								var waistHipRatio = new Array();
								var waistHipRatioTime =new Array();
								<c:forEach items="${merList }" var="mer">
								waistHipRatio.push("${mer.hipRatio.waistHipRatio}");
								waistHipRatioTime.push("${mer.hipRatio.uploadTime}");
								</c:forEach>
								var option = {
									title : {
										text : '腰臀比',
									},
									tooltip : {
										trigger : 'axis'
									},
									legend : {
										data : [ '腰臀比' ]
									},
									toolbox : {
										show : true,
										feature : {
											mark : {
												show : true
											},
											dataView : {
												show : true,
												readOnly : false
											},
											magicType : {
												show : true,
												type : [ 'bar' ]
											},
											restore : {
												show : true
											},
											saveAsImage : {
												show : true
											}
										}
									},
									calculable : true,
									xAxis : [ {
										type : 'category',
										data : waistHipRatioTime
									} ],
									yAxis : [ {
										type : 'value'
									} ],
									series : [ {
										name : '腰臀比',
										type : 'line',
										data : waistHipRatio,
										barWidth : 20
									} ]
								};
								myChart.setOption(option);
							</script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<style type="text/css">
	/*返回顶部 20170613*/
	.gototop {
		position: fixed;
		width: 40px;
		height: 40px;
		background: #888;
		border-radius: 2px;
		right: 50px;
		bottom: 80px;
		text-align: center;
		line-height: 40px;
		font-size: 24px;
		color: #fff;
		cursor: pointer;
		display: none;
	}
	
	.gototop:hover {
		background: #337ab7;
	}
	</style>
	<!-- 返回顶部 strat -->
	<div class="gototop">
		<i class="fa fa-angle-up"></i>
	</div>
	<!-- 返回顶部 end -->
	<script type="text/javascript">
		$(function() {
			$(window).scroll(function() {
				if ($(window).scrollTop() >= 200) {
					$(".gototop").fadeIn();
				} else {
					$(".gototop").fadeOut();
				}
			})

			$(".gototop").click(function() {
				$("html,body").animate({
					scrollTop : '0px'
				}, 300);
			})
		})
	</script>

</body>
</html>