<%@page import="com.yhcrt.healthcloud.health.entity.HdBloodGlucose"%>
<%@page import="com.yhcrt.healthcloud.health.entity.HdBloodPressure"%>
<%@page import="com.yhcrt.healthcloud.health.entity.HdPulse"%>
<%@page import="com.yhcrt.healthcloud.health.entity.HdSleep"%>
<%@page import="com.yhcrt.healthcloud.health.entity.HdStep"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Health Data</title>
</head>

<jsp:include page="/pages/base/base.jsp"></jsp:include>

<style>
	.charts{
		margin : auto;
		margin-top : 150px;
		margin-bottom : 200px;
	}
	.chartsBottom{
		margin : auto;
		margin-bottom : 100px;
	}
</style>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2">
				<ul class="nav nav-pills nav-stacked text-center">
					<li><a href="${pageContext.request.contextPath }/healthService/record?memberId=${memberId }" ><span>健康档案</span></a></li>
					<li class="active"><a href="${pageContext.request.contextPath }/healthService/data?memberId=${memberId }" ><span>健康数据</span></a></li>
					<li><a class="active" href="${pageContext.request.contextPath }/healthService/mer?memberId=${memberId }" ><span>体检报告</span></a></li>
					<li><a href="${pageContext.request.contextPath }/healthService/proposal?memberId=${memberId }" ><span>医师建议</span></a></li>
				</ul>
			</div>
			<div class="col-sm-10">
				<!-- tab strat -->
				<ul class="nav nav-tabs">
					<li><a
						href="${pageContext.request.contextPath }/healthService/record?memberId=${memberId }">健康档案</a></li>
					<li class="active"><a
						href="${pageContext.request.contextPath }/healthService/data?memberId=${memberId }">健康数据</a></li>
					<li><a
						href="${pageContext.request.contextPath }/healthService/mer?memberId=${memberId }">体检报告</a></li>
					<li><a
						href="${pageContext.request.contextPath }/healthService/proposal?memberId=${memberId }">医师建议</a></li>
				</ul>
				<!-- tab end -->

				<div class="mar-t10">
					<!-- 医师建议表格 strat -->
					<table class="table table-bordered table-striped mytable">
						<thead>
							<tr>
								<th>检测指标</th>
								<th>最高</th>
								<th>最低</th>
								<th>平均</th>
								<th>详情</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">运动量(计步)</th>
								<td>${stepMax }</td>
								<td>${stepMin }</td>
								<td>${stepAve }</td>
								<td><a href="#step">查看</a></td>
							</tr>
							<tr>
								<th scope="row">深度睡眠(小时)</th>
								<td>${sleepMax }</td>
								<td>${sleepMin }</td>
								<td>${sleepAve }</td>
								<td><a href="#sleep">查看</a></td>
							</tr>
							<tr>
								<th scope="row">心率(次/分)</th>
								<td>${pulseMax }</td>
								<td>${pulseMin }</td>
								<td>${pulseAve }</td>
								<td><a href="#pulse">查看</a></td>
							</tr>
							<tr>
								<th scope="row">收缩压(mmHg)</th>
								<td>${sbpMax }</td>
								<td>${sbpMin }</td>
								<td>${sbpAve }</td>
								<td><a href="#pressure">查看</a></td>
							</tr>
							<tr>
								<th scope="row">舒张压(mmHg)</th>
								<td>${dbpMax }</td>
								<td>${dbpMin }</td>
								<td>${dbpAve }</td>
								<td><a href="#pressure">查看</a></td>
							</tr>
							<tr>
								<th scope="row">血糖(mmol/L)</th>
								<td>${glucoseMax }</td>
								<td>${glucoseMin }</td>
								<td>${glucoseAve }</td>
								<td><a href="#glucose">查看</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- step chart -->
	<div class="charts" id="step" style="width: 80%; height: 600px;" align="center"></div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('step'));

		var step = new Array();
        var hsUploadTime = new Array();
        <c:forEach items="${stepList}" var="step">
	        step.push("${step.stepCount}"); 
	        hsUploadTime.push("${step.uploadTime}");
		</c:forEach>
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '活动量',
				subtext : '单位： 步'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '步数' ]
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : true
					},
					magicType : {
						show : true,
						type : [ 'line' , 'bar' ]
					},
					saveAsImage : {
						show : true
					}
				}
			},
			dataZoom : {
				show : true,
				start : 0,
				end : 100
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				data : hsUploadTime
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				name : '步数',
				type : 'bar',
				data : step,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				},
				//itemStyle:{  
                //    normal:{color:'#086133'}  
                //} 
			} ]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>


	<!-- sleep chart -->
	<div class="charts" id="sleep" style="width: 80%; height: 600px;" align="center"></div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('sleep'));

		var sleep = new Array();
        var spUploadTime = new Array();
        <c:forEach items="${sleepList}" var="sleep">
        sleep.push("${sleep.deepSleepDuration}"); 
        spUploadTime.push("${sleep.uploadTime}");
		</c:forEach>
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '深度睡眠时间',
				subtext : '单位： 小时'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '深度睡眠时间' ]
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
						type : [ 'line' , 'bar' ]
					},
					saveAsImage : {
						show : true
					}
				}
			},
			dataZoom : {
				show : true,
				start : 0,
				end : 100
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				data : spUploadTime
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				name : '深度睡眠时间',
				type : 'bar',
				data : sleep,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			} ]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>


	<!-- pulse chart -->
	<div class="charts" id="pulse" style="width: 80%; height: 600px;" align="center"></div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('pulse'));
		var pulse = new Array();
        var hpUploadTime = new Array();
        <c:forEach items="${pulseList}" var="pulse">
        pulse.push("${pulse.pulse}"); 
        hpUploadTime.push("${pulse.uploadTime}");
		</c:forEach>
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '心率',
				subtext : '单位： 次/分'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '心率' ]
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
						type : [ 'line' , 'bar' ]
					},
					saveAsImage : {
						show : true
					}
				}
			},
			dataZoom : {
				show : true,
				start : 0,
				end : 100
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				data : hpUploadTime
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				name : '心率',
				type : 'line',
				data : pulse,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			} ]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>


	<!-- pressure chart -->
	<div class="charts" id="pressure" style="width: 80%; height: 600px;" align="center"></div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('pressure'));
		var sbp = new Array();
        var dbp = new Array();
        var bpUploadTime = new Array();
        <c:forEach items="${pressureList}" var="pressure">
        sbp.push("${pressure.sbp}"); 
        dbp.push("${pressure.dbp}");
        bpUploadTime.push("${pressure.bpUploadTime}");
		</c:forEach>
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '血压',
				subtext : '单位： mmHg'
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
						type : [ 'line' , 'bar' ]
					},
					saveAsImage : {
						show : true
					}
				}
			},
			dataZoom : {
				show : true,
				start : 0,
				end : 100
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				data : bpUploadTime
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [
					{
						name : '舒张压',
						type : 'line',
						data : dbp,
						markPoint : {
							data : [ {
								type : 'max',
								name : '最大值'
							}, {
								type : 'min',
								name : '最小值'
							} ]
						},
						markLine : {
							data : [ {
								type : 'average',
								name : '平均值'
							} ]
						}
					},
					{
						name : '收缩压',
						type : 'line',
						data : sbp,
						markPoint : {
							data : [ {
								type : 'max',
								name : '最大值'
							}, {
								type : 'min',
								name : '最小值'
							} ]
						},
						markLine : {
							data : [ {
								type : 'average',
								name : '平均值'
							} ]
						}
					} ]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>



	<!-- glucose chart -->
	<div class="chartsBottom" id="glucose" style="width: 80%; height: 600px;" align="center"></div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('glucose'));
		var bgUploadTime = new Array();
		//凌晨血糖
		var baValue1 = new Array();
		//空腹血糖
		var baValue2 = new Array();
		//早餐后血糖
		var baValue3 = new Array();
		//午餐血糖
		var baValue4 = new Array();
		//午餐后血糖
		var baValue5 = new Array();
		//晚餐血糖
		var baValue6 = new Array();
		//晚餐后血糖
		var baValue7 = new Array();
		//睡前
		var baValue8 = new Array();
        <c:forEach items="${glucoseList}" var="glucose">
        <c:if test="${glucose.bgType ==0}">
        baValue1.push("${glucose.bgValue}"); 
        </c:if>
        <c:if test="${glucose.bgType ==1}">
        baValue2.push("${glucose.bgValue}"); 
        </c:if>
        <c:if test="${glucose.bgType ==2}">
        baValue3.push("${glucose.bgValue}"); 
        </c:if>
        <c:if test="${glucose.bgType ==3}">
        baValue4.push("${glucose.bgValue}"); 
        </c:if>
        <c:if test="${glucose.bgType ==4}">
        baValue5.push("${glucose.bgValue}"); 
        </c:if>
        <c:if test="${glucose.bgType ==5}">
        baValue6.push("${glucose.bgValue}"); 
        </c:if>
        <c:if test="${glucose.bgType ==6}">
        baValue7.push("${glucose.bgValue}"); 
        </c:if>
        <c:if test="${glucose.bgType ==7}">
        baValue8.push("${glucose.bgValue}"); 
        </c:if>
        bgUploadTime.push("${glucose.uploadTime}");
		</c:forEach>
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '血糖',
				subtext : '单位： mmol/L'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : ['凌晨血糖','空腹血糖','早餐后血糖','午餐前血糖','午餐后血糖','晚餐前血糖','晚餐后血糖','睡前血糖']
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
						type : [ 'line' , 'bar' ]
					},
					saveAsImage : {
						show : true
					}
				}
			},
			dataZoom : {
				show : true,
				start : 0,
				end : 100
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				data : bgUploadTime
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				name : "凌晨血糖",
				type : 'line',
				data : baValue1,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			},
			{
				name : '空腹血糖',
				type : 'line',
				data : baValue2,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			},
			{
				name : '早餐后血糖',
				type : 'line',
				data : baValue3,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			},
			{
				name : '午餐前血糖',
				type : 'line',
				data : baValue4,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			},
			{
				name : '午餐后血糖',
				type : 'line',
				data : baValue5,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			},
			{
				name : '晚餐前血糖',
				type : 'line',
				data : baValue6,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			},
			{
				name : '晚餐后血糖',
				type : 'line',
				data : baValue7,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			},
			{
				name : '睡前血糖',
				type : 'line',
				data : baValue8,
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			}
			]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>

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