
/* 切换tab S */
$(".ni_g_tab .ni_g_tab_tit a").on(
		"click",
		function() {
			if (!($(this).hasClass("hover"))) {
				$(this).siblings().removeClass("hover");
				$(this).addClass("hover");
				$(this).parents(".ni_g_tab").find(".ni_g_tab_cont .ni_tab")
						.removeClass("hover").eq($(this).index()).addClass(
								"hover");
			}
		})

var myChart1 = echarts.init($(".ni_g_echarts1")[0]);
var myChart2 = echarts.init($(".ni_g_echarts2")[0]);
var myChart3 = echarts.init($(".ni_g_echarts3")[0]);
var myChart4 = echarts.init($(".ni_g_echarts4")[0]);
var myChart5 = echarts.init($(".ni_g_echarts5")[0]);
option1 = {
	grid : {
		left : '4%',
		right : '4%',
		bottom : '3%',
		top : '4%',
		containLabel : true
	},
	yAxis : {
		name : '',
		nameTextStyle : {
			color : "#555555"
		},
		axisTick : {
			show : false
		},
		type : 'value',
		splitLine : {
			show : false
		},
		max : 100,
		axisLine : {
			lineStyle : {
				color : "#e2e2e2"
			}
		},
		axisLabel : {
			color : "#555555"
		}
	},
	xAxis : {
		type : 'category',
		axisTick : {
			show : false
		},
		data : [],
		axisLine : {
			lineStyle : {
				color : "#e2e2e2"
			}
		},
		axisLabel : {
			color : "#555555"
		}
	},
	animationDelay : function(idx) {
		return idx * 100;
	},
	series : [ {
		type : 'bar',
		stack : 'chart',
		barMaxWidth : 40,
		z : 3,
		label : {
			normal : {
				show : false

			}
		},
		itemStyle : {
			normal : {
				color : function(params) {
					// build a color map as your need.
					var colorList = [ '#f6acac', '#abecb0', '#ffe163',
							'#ffab67','cc6666','#FA8072','#E066FF','#551A8B','#54FF9F','#DB7093'];
					return colorList[params.dataIndex]
				},
			}
		},
		data : []
	} ]
};
option2 = {
	grid : {
		left : '4%',
		right : '4%',
		bottom : '3%',
		top : '4%',
		containLabel : true
	},
	yAxis : {
		name : '',
		nameTextStyle : {
			color : "#555555"
		},
		axisTick : {
			show : false
		},
		type : 'value',
		splitLine : {
			show : false
		},
		max : 100,
		axisLine : {
			lineStyle : {
				color : "#e2e2e2"
			}
		},
		axisLabel : {
			color : "#555555"
		}
	},
	xAxis : {
		type : 'category',
		axisTick : {
			show : false
		},
		data : [],
		axisLine : {
			lineStyle : {
				color : "#e2e2e2"
			}
		},
		axisLabel : {
			color : "#555555"
		}
	},
	animationDelay : function(idx) {
		return idx * 100;
	},
	series : [ {
		type : 'bar',
		stack : 'chart',
		barMaxWidth : 40,
		z : 3,
		label : {
			normal : {
				show : false
			}
		},
		itemStyle : {
			normal : {
				color : function(params) {
					// build a color map as your need.
					var colorList = [ '#f6acac', '#abecb0', '#ffe163',
										'#ffab67','cc6666','#FA8072','#E066FF','#551A8B','#54FF9F','#DB7093'];
					return colorList[params.dataIndex]
				},
			}
		},
		data : []
	} ]
};
option3 = {
	legend : {
		data : [],
		bottom : 0
	},
	grid : {
		left : '4%',
		right : '4%',
		bottom : '13%',
		top : '4%',
		containLabel : true
	},
	yAxis : {
		name : '',
		nameTextStyle : {
			color : "#555555"
		},
		axisTick : {
			show : false
		},
		type : 'value',
		splitLine : {
			show : false
		},
		max : 100,
		axisLine : {
			lineStyle : {
				color : "#e2e2e2"
			}
		},
		axisLabel : {
			color : "#555555"
		}
	},
	xAxis : {
		type : 'category',
		axisTick : {
			show : false
		},
		data : [],
		axisLine : {
			lineStyle : {
				color : "#e2e2e2"
			}
		},
		axisLabel : {
			color : "#555555"
		}
	},
	animationDelay : function(idx) {
		return idx * 100;
	},
	series : []
};
var colorList = [ '#abecb0', '#9babda', '#f6acac', '#FF00FF', '#9AFF9A',
		'#00FA9A', '#4169E1', '#00CED1', ' 	#B22222' ];

// 总和
var total = {
	value : '%',
}
var originalData = [ {
	value : 21,
	name : '1-10分钟'
}, {
	value : 30,
	name : '10-30分钟'
}, {
	value : 20,
	name : "30-60分钟"
}, {
	value : 20,
	name : "1-3小时"
}, {
	value : 20,
	name : "3-6小时"
}, {
	value : 20,
	name : "6-8小时"
}, {
	value : 20,
	name : "8小时以上"
} ];

echarts.util.each(originalData, function(item, index) {
	item.itemStyle = {
		normal : {
			color : colorList[index]
		}
	};
});

option4 = {
	title : [ {
		left : '49%',
		top : '48%',
		textAlign : 'center',
		textBaseline : 'middle',
		textStyle : {
			color : '#78a2bc',
			fontWeight : 'normal',
			fontSize : 40
		}
	} ],
	series : [ {
		hoverAnimation : false, // 设置饼图默认的展开样式
		radius : [ 60, 120 ],
		name : 'pie',
		type : 'pie',
		selectedMode : 'single',
		selectedOffset : 16, // 选中是扇区偏移量
		clockwise : true,
		startAngle : 90,
		label : {
			normal : {
				textStyle : {
					fontSize : 14,
					color : '#999'
				}
			}
		},
		labelLine : {
			normal : {
				lineStyle : {
					color : '#999',

				}
			}
		},
		data : []
	} ]
};
option5 = {
	grid : {
		left : '2%',
		right : '2%',
		bottom : '3%',
		top : '2%',
		containLabel : true
	},
	xAxis : {
		nameTextStyle : {
			color : "#555555"
		},
		axisTick : {
			show : false
		},
		type : 'value',
		splitLine : {
			show : false
		},
		max : 100,
		axisLine : {
			show : false
		},
		axisLabel : {
			color : "#555555"
		}
	},
	yAxis : {
		type : 'category',
		axisTick : {
			show : false
		},
		data : [],
		axisLine : {
			show : false,
		},
		axisLabel : {
			color : "#555555"
		}
	},
	animationDelay : function(idx) {
		return idx * 100;
	},
	series : [ {
		type : 'bar',
		stack : 'chart',
		barMaxWidth : 20,
		z : 3,
		label : {
			normal : {
				show : true,
				position : 'right',
				formatter : '{c}%'
			}
		},
		itemStyle : {
			normal : {
				color : function(params) {
					// build a color map as your need.
					var colorList = [ '#abecb0', '#9babda', '#f6acac',
							'#FF00FF', '#9AFF9A', '#00FA9A', '#4169E1',
							'#00CED1', ' 	#B22222' ];
					return colorList[params.dataIndex]
				},
			}
		},
		data : []
	} ]
};

// 图标，累计使用人数
myChart1.setOption(option1);
myChart1.showLoading();
$.ajax({
	type : "post",
	async : true,
	url : "queryUseTotal",
	data : {},
	dataType : "json",
	success : function(result) {
		if (result) {
			var names = [];
			var nums = [];
			//var teacherTotal = 0;
			for (var i = 0; i < result.length; i++) {
				names.push(result[i].create_time);
				nums.push(result[i].count);
				//teacherTotal = teacherTotal + result[i].count;
			}
			myChart1.hideLoading(); // 隐藏加载动画
			myChart1.setOption({ // 加载数据图表
				xAxis : {
					data : names
				},
				series : [ {
					data : nums
				} ]
			});

			//$("#teacherTotal").text(teacherTotal);
		}

	},
	error : function(errorMsg) {
		// 请求失败时执行该函数
		myChart1.hideLoading();
	}
})
// 加载第二个图形
myChart2.setOption(option2);
myChart2.showLoading();
$.ajax({
	type : "post",
	async : true,
	url : "queryDeviceOffTotal",
	data : {},
	dataType : "json",
	success : function(result) {

		if (result) {
			var names = [];
			var nums = [];
			var deviceOffTotal = 0;		
			for (var i = 0; i < result.length; i++) {
				names.push(result[i].create_time);
				nums.push(result[i].count);
				deviceOffTotal = deviceOffTotal + result[i].count;
				
			}
			myChart2.hideLoading(); // 隐藏加载动画
			myChart2.setOption({ // 加载数据图表
				xAxis : {
					data : names
				},
				series : [ {
					data : nums
				} ]
			});
			//$("#deviceOffTotal").text(deviceOffTotal);
		}

	},
	error : function(errorMsg) {
		// 请求失败时执行该函数
		myChart2.hideLoading();
	}
})

myChart3.setOption(option3);// 应用统计加载
myChart3.showLoading();
$.ajax({
	type : "post",
	async : true,
	url : "queryAppTotal",
	data : {},
	dataType : "json",
	success : function(result) {
		console.log(result);
		console.log(result.legend)
		var appTotal = 0
		if (result) {
			myChart3.hideLoading();// 隐藏加载动画
			myChart3.setOption({// 加载数据图表
				legend : {
					data : result.legend,
					bottom : 0
				},
				xAxis : {
					data : result.xAxis
				},
				series : result.series
			});
			appTotal = result.series.length;
			$("#appTotal").text(appTotal);
		}
	},
	error : function(errorMsg) {
		// 请求失败时执行该函数
		myChart3.hideLoading();
	}
})

myChart4.setOption(option4);
myChart4.showLoading();
$.ajax({
	type : "post",
	async : true,
	url : "deviceCycleCount",
	data : {
		searchType : 1
	},
	dataType : "json",
	success : function(result) {
		if (result) {
			myChart4.hideLoading();// 隐藏加载动画
			var seriesData = []

			var htmlStr = '';

			for (var i = 0; i < result.length; i++) {
				var o = new Object();// 示例初始化一个Object
				console.log(result[i].deviceCount);
				console.log(result[i].cycleName);
				o.value = result[i].deviceCount;
				o.name = result[i].cycleName;
				seriesData.push(o);

				htmlStr += '<tr>';
				htmlStr += '<td>' + result[i].cycleName + '</td>';
				htmlStr += '<td>' + result[i].deviceCount + '</td>';
				htmlStr += '<td>' + result[i].rate + '%</td></tr>';

			}
			$(".deviceUseTakingCycle").html(htmlStr);
			myChart4.setOption({// 加载数据图表
				series : [ {
					data : seriesData
				} ]
			});
		}
	},
	error : function(errorMsg) {
		// 请求失败时执行该函数
		myChart4.hideLoading();
	}
})

myChart5.setOption(option5);
myChart5.showLoading();

$.ajax({
	type : "post",
	async : true,
	url : "teacherUseRanking",
	data : {
		searchType : 1
	},
	dataType : "json",
	success : function(result) {
		if (result) {
			myChart5.hideLoading();// 隐藏加载动画
			myChart5.setOption({// 加载数据图表
				yAxis : {
					data : result.yAxis
				},
				series : [ {
					data : result.seriesData
				} ]
			});

			var subjectlst = result.subjectlst;
			var htmlStr = '';
			for (var i = 0; i < subjectlst.length; i++) {
				var useTaking = formatSeconds(subjectlst[i].use_taking);
				htmlStr += '<tr>';
				htmlStr += '<td>' + subjectlst[i].subject_name + '</td>';
				htmlStr += '<td>' + useTaking + '</td>';
				htmlStr += '<td>' + subjectlst[i].use_count + '</td>';
				htmlStr += '<td>' + subjectlst[i].rate + '%</td>';
				htmlStr += '<td>' + subjectlst[i].user_use_count + '</td>';
				htmlStr += '</tr>';
			}
			console.log(htmlStr)
			$("#subjectCount").html(htmlStr);

		}
	},
	error : function(errorMsg) {
		myChart5.hideLoading();
	}
})

$.ajax({
	type : "post",
	async : true,
	url : "teacherUseTaking",
	data : {
		searchType : 1
	},
	dataType : "json",
	success : function(result) {
		if (result != null) {
			var top5 = result.top5;
			var riseTop5 = result.riseTop5;
			var fallTop5 = result.fallTop5;

			var top5Html = '';
			var riseTop5Html = '';
			var fallTop5Html = '';
			console.log(top5); 
			for (var i = 0; i < top5.length; i++) {
				if (i < 5) {
					var useTaking = formatSeconds(top5[i].use_taking);
					var upOrDown = ''
					
					if(top5[i].rank>=0){
						upOrDown = 'ni_up';
					}else{
						upOrDown = 'ni_down';
					}
						
					top5Html += '<dd class="ni_dd clearfix ni_dd01">';
					top5Html += '<div class="ni_num fl t_c">'+(i+1)+'</div>';
					top5Html += '<div class="ni_name fl mgl25">'+ top5[i].user_name + '</div>';
					top5Html += '<div class="ni_st fr '+upOrDown+' mgr15">' + top5[i].rank + '</div>';
					top5Html += '<div class="ni_time fr mgr15">' + useTaking + '</div>';
					top5Html += '</dd>';
				}
			}
			console.log(top5Html); 
			for (var j = 0; j < riseTop5.length; j++) {
				if (j < 5) {
					var useTaking = formatSeconds(riseTop5[j].use_taking);
					var upOrDown = ''
						
					if(riseTop5[j].rank>=0){
						upOrDown = 'ni_up';
					}else{
						upOrDown = 'ni_down';
					
					}
					riseTop5Html += '<dd class="ni_dd clearfix ni_dd01">';
					riseTop5Html += '<div class="ni_num fl t_c">'+(j+1)+'</div>';
					riseTop5Html += '<div class="ni_name fl mgl25">'+ riseTop5[j].user_name + '</div>';
					riseTop5Html += '<div class="ni_st fr '+upOrDown+' mgr15">' + riseTop5[j].rank + '</div>';
					riseTop5Html += '<div class="ni_time fr mgr15">' + useTaking + '</div>';
					riseTop5Html += '</dd>';
				}
			}

			for (var k = 0; k < fallTop5.length; k++) {
				if (k < 5) {
					var useTaking = formatSeconds(fallTop5[k].use_taking);
					var upOrDown = ''
						
					if(fallTop5[k].rank>=0){
						upOrDown = 'ni_up';
					}else{
						upOrDown = 'ni_down';
						
					}
					fallTop5Html += '<dd class="ni_dd clearfix ni_dd01">';
					fallTop5Html += '<div class="ni_num fl t_c">'+(k+1)+'</div>';
					fallTop5Html += '<div class="ni_name fl mgl25">'+ fallTop5[k].user_name + '</div>';
					fallTop5Html += '<div class="ni_st fr '+upOrDown+' mgr15">' + fallTop5[k].rank + '</div>';
					fallTop5Html += '<div class="ni_time fr mgr15">' + useTaking + '</div>';
					fallTop5Html += '</dd>';
				}
			}
			$("#top5").html(top5Html);
			$("#riseTop5").html(riseTop5Html);
			$("#fallTop5").html(fallTop5Html);	
		}
	},
	error : function(errorMsg) {

	}
});

$
		.ajax({
			type : "post",
			async : true,
			url : "appUseInfo",
			data : {
				searchType : 1
			},
			dataType : "json",
			success : function(result) {
				var htmlStr = '';
				var otherName = '';
				for (var i = 0; i < result.length; i++) {
					var appName = cutstr(result[i].app_name, 16);
					var count = result[i].count;
					if(i > 13){
						break;
					}
					if (i < 5) {
						htmlStr += '<dd class="ni_dd clearfix ni_dd01">';
						htmlStr += '<div class="ni_num fl t_c">' + (i + 1)
								+ '</div>';
						htmlStr += '<div class="ni_name fl mgl25" title=\"'
								+ result[i].app_name + '\">' + appName
								+ '</div>';
						htmlStr += '<div class="ni_time fr mgr15">' + count
								+ '次</div>';
						htmlStr += '</dd>';

					} else {
						otherName += '<a title=\"' + result[i].app_name + '\">'
								+ appName + '</a>';
					}
					

				}
				$(".topApp").html(htmlStr);
				$(".qtApp").html(otherName);
			},
			error : function(errorMsg) {

			}
		})

$.ajax({
	type : "post",
	async : true,
	url : "appUseInfo",
	data : {
		searchType : 1
	},
	dataType : "json",
	success : function(result) {
		var top5 = '';
		var riseTop5 = '';
		var fallTop5 = '';
		for (var i = 0; i < result.length; i++) {

		}
		$(".top5").html(top5);
		$(".riseTop5").html(riseTop5);
		$(".fallTop5").html(fallTop5);
	},
	error : function(errorMsg) {

	}
})

function cutstr(str, len) {
	var str_length = 0;
	var str_len = 0;
	str_cut = new String();
	str_len = str.length;
	for (var i = 0; i < str_len; i++) {
		a = str.charAt(i);
		str_length++;
		if (escape(a).length > 4) {
			// 中文字符的长度经编码之后大于4
			str_length++;
		}
		str_cut = str_cut.concat(a);
		if (str_length >= len) {
			str_cut = str_cut.concat("...");
			return str_cut;
		}
	}
	// 如果给定字符串小于指定长度，则返回源字符串；
	if (str_length < len) {
		return str;
	}
}

$(document)
		.ready(
				function() {
					$("#useQK1")
							.bind(
									"click",
									function() {
										$(".useQK a").removeClass("hover");
										$
												.ajax({
													type : "post",
													async : true,
													url : "appUseInfo",
													data : {
														searchType : 1
													},
													dataType : "json",
													success : function(result) {
														$(".topApp").html('');
														$(".qtApp").html('');
														$("#useQK1").addClass(
																"hover");
														if (result) {
															var htmlStr = '';
															var otherName = '';
															for (var i = 0; i < result.length; i++) {
																var appName = cutstr(
																		result[i].app_name,
																		16);
																var count = result[i].count;
																
																if( i >13 ){
																	break;
																}
																
																if (i < 5) {
																	htmlStr += '<dd class="ni_dd clearfix ni_dd01">';
																	htmlStr += '<div class="ni_num fl t_c">'
																			+ (i + 1)
																			+ '</div>';
																	htmlStr += '<div class="ni_name fl mgl25" title=\"'
																			+ result[i].app_name
																			+ '\">'
																			+ appName
																			+ '</div>';
																	htmlStr += '<div class="ni_time fr mgr15">'
																			+ count
																			+ '次</div>';
																	htmlStr += '</dd>';
																} else {
																	otherName += '<a title=\"'
																			+ result[i].app_name
																			+ '\">'
																			+ appName
																			+ '</a>';
																}

															}
															$(".topApp").html(
																	htmlStr);
															$(".qtApp").html(
																	otherName);
														}
													},
													error : function(errorMsg) {
													}
												})
									});

					$("#useQK2")
							.bind(
									"click",
									function() {
										$(".useQK a").removeClass("hover");
										$
												.ajax({
													type : "post",
													async : true,
													url : "appUseInfo",
													data : {
														searchType : 2
													},
													dataType : "json",
													success : function(result) {
														$(".topApp").html('');
														$(".qtApp").html('');
														if (result) {
															var htmlStr = '';
															var otherName = '';
															$("#useQK2")
																	.addClass(
																			"hover");
															for (var i = 0; i < result.length; i++) {
																var appName = cutstr(
																		result[i].app_name,
																		16);
																var count = result[i].count;
																
																if( i >13 ){
																	break;
																}
																
																if (i < 5) {
																	htmlStr += '<dd class="ni_dd clearfix ni_dd01">';
																	htmlStr += '<div class="ni_num fl t_c">'
																			+ (i + 1)
																			+ '</div>';
																	htmlStr += '<div class="ni_name fl mgl25" title=\"'
																			+ result[i].app_name
																			+ '\">'
																			+ appName
																			+ '</div>';
																	htmlStr += '<div class="ni_time fr mgr15">'
																			+ count
																			+ '次</div>';
																	htmlStr += '</dd>';

																} else {
																	otherName += '<a title=\"'
																			+ result[i].app_name
																			+ '\">'
																			+ appName
																			+ '</a>';
																}

															}
															$(".topApp").html(
																	htmlStr);
															$(".qtApp").html(
																	otherName);
														}

													},
													error : function(errorMsg) {
														// 请求失败时执行该函数
														alert("图表请求数据失败!");
														myChart5.hideLoading();
													}
												})
									});

					$("#useQK3")
							.bind(
									"click",
									function() {
										$(".useQK a").removeClass("hover");
										$
												.ajax({
													type : "post",
													async : true,
													url : "appUseInfo",
													data : {
														searchType : 3
													},
													dataType : "json",
													success : function(result) {
														$(".topApp").html('');
														$(".qtApp").html('');
														if (result) {
															var htmlStr = '';
															var otherName = '';
															$("#useQK3")
																	.addClass(
																			"hover");
															for (var i = 0; i < result.length; i++) {
																var appName = cutstr(
																		result[i].app_name,
																		16);
																var count = result[i].count;
																
																if( i >13 ){
																	break;
																}
																
																if (i < 5) {
																	htmlStr += '<dd class="ni_dd clearfix ni_dd01">';
																	htmlStr += '<div class="ni_num fl t_c">'
																			+ (i + 1)
																			+ '</div>';
																	htmlStr += '<div class="ni_name fl mgl25" title=\"'
																			+ result[i].app_name
																			+ '\">'
																			+ appName
																			+ '</div>';
																	htmlStr += '<div class="ni_time fr mgr15">'
																			+ count
																			+ '次</div>';
																	htmlStr += '</dd>';

																} else {
																	otherName += '<a title=\"'
																			+ result[i].app_name
																			+ '\">'
																			+ appName
																			+ '</a>';
																}

															}
															$(".topApp").html(
																	htmlStr);
															$(".qtApp").html(
																	otherName);
														}

													},
													error : function(errorMsg) {

													}
												})
									});

					$("#useTaking1")
							.bind(
									"click",
									function() {
										$(".useTaking a").removeClass("hover");

										$
												.ajax({
													type : "post",
													async : true,
													url : "teacherUseRanking",
													data : {
														searchType : 1
													},
													dataType : "json",
													success : function(result) {
														$("#useTaking1")
																.addClass(
																		"hover");
														if (result) {
															myChart5
																	.hideLoading();// 隐藏加载动画
															myChart5
																	.setOption({// 加载数据图表
																		yAxis : {
																			data : result.yAxis
																		},
																		series : [ {
																			data : result.seriesData
																		} ]
																	});

															var subjectlst = result.subjectlst;
															var htmlStr = '';
															for (var i = 0; i < subjectlst.length; i++) {
																var useTaking = formatSeconds(subjectlst[i].use_taking);
																htmlStr += '<tr>';
																htmlStr += '<td>'
																		+ subjectlst[i].subject_name
																		+ '</td>';
																htmlStr += '<td>'
																		+ useTaking
																		+ '</td>';
																htmlStr += '<td>'
																		+ subjectlst[i].use_count
																		+ '</td>';
																htmlStr += '<td>'
																		+ subjectlst[i].rate
																		+ '%</td>';
																htmlStr += '<td>'
																		+ subjectlst[i].user_use_count
																		+ '</td>';
																htmlStr += '</tr>';
															}
															console
																	.log(htmlStr)
															$("#subjectCount")
																	.html(
																			htmlStr);
														}
													},
													error : function(errorMsg) {
														// 请求失败时执行该函数
														alert("图表请求数据失败!");
														myChart5.hideLoading();
													}
												})
									});
					$("#useTaking2")
							.bind(
									"click",
									function() {
										$(".useTaking a").removeClass("hover");
										$
												.ajax({
													type : "post",
													async : true,
													url : "teacherUseRanking",
													data : {
														searchType : 2
													},
													dataType : "json",
													success : function(result) {
														$("#useTaking2")
																.addClass(
																		"hover");
														if (result) {
															myChart5
																	.hideLoading();// 隐藏加载动画
															myChart5
																	.setOption({// 加载数据图表
																		yAxis : {
																			data : result.yAxis
																		},
																		series : [ {
																			data : result.seriesData
																		} ]
																	});

															var subjectlst = result.subjectlst;
															var htmlStr = '';
															for (var i = 0; i < subjectlst.length; i++) {
																var useTaking = formatSeconds(subjectlst[i].use_taking);
																htmlStr += '<tr>';
																htmlStr += '<td>'
																		+ subjectlst[i].subject_name
																		+ '</td>';
																htmlStr += '<td>'
																		+ useTaking
																		+ '</td>';
																htmlStr += '<td>'
																		+ subjectlst[i].use_count
																		+ '</td>';
																htmlStr += '<td>'
																		+ subjectlst[i].rate
																		+ '%</td>';
																htmlStr += '<td>'
																		+ subjectlst[i].user_use_count
																		+ '</td>';
																htmlStr += '</tr>';
															}
															console
																	.log(htmlStr)
															$("#subjectCount")
																	.html(
																			htmlStr);
														}
													},
													error : function(errorMsg) {
														// 请求失败时执行该函数
														alert("图表请求数据失败!");
														myChart3.hideLoading();
													}
												})
									});

					$("#useTaking3")
							.bind(
									"click",
									function() {
										$(".useTaking a").removeClass("hover");
										$
												.ajax({
													type : "post",
													async : true,
													url : "teacherUseRanking",
													data : {
														searchType : 3
													},
													dataType : "json",
													success : function(result) {
														$("#useTaking3")
																.addClass(
																		"hover");
														if (result) {
															myChart5
																	.hideLoading();// 隐藏加载动画
															myChart5
																	.setOption({// 加载数据图表
																		yAxis : {
																			data : result.yAxis
																		},
																		series : [ {
																			data : result.seriesData
																		} ]
																	});

															var subjectlst = result.subjectlst;
															var htmlStr = '';
															
															for (var i = 0; i < subjectlst.length; i++) {
																var useTaking = formatSeconds(subjectlst[i].use_taking);
																htmlStr += '<tr>';
																htmlStr += '<td>'
																		+ subjectlst[i].subject_name
																		+ '</td>';
																htmlStr += '<td>'
																		+ useTaking
																		+ '</td>';
																htmlStr += '<td>'
																		+ subjectlst[i].use_count
																		+ '</td>';
																htmlStr += '<td>'
																		+ subjectlst[i].rate
																		+ '%</td>';
																htmlStr += '<td>'
																		+ subjectlst[i].user_use_count
																		+ '</td>';
																htmlStr += '</tr>';
															}
															console
																	.log(htmlStr)
															$("#subjectCount")
																	.html(
																			htmlStr);
														}
													},
													error : function(errorMsg) {
														// 请求失败时执行该函数
														alert("图表请求数据失败!");
														myChart3.hideLoading();
													}
												})
									});

					$("#deviceUseTaking1")
							.bind(
									"click",
									function() {
										$(".duk a").removeClass("hover");
										$
												.ajax({
													type : "post",
													async : true,
													url : "deviceCycleCount",
													data : {
														searchType : 2
													},
													dataType : "json",
													success : function(result) {
														$("#deviceUseTaking1")
																.addClass(
																		"hover");
														if (result) {
															myChart4
																	.hideLoading();// 隐藏加载动画
															var seriesData = []

															var htmlStr = '';

															for (var i = 0; i < result.length; i++) {
																var o = new Object();// 示例初始化一个Object
																console
																		.log(result[i].deviceCount);
																console
																		.log(result[i].cycleName);
																o.value = result[i].deviceCount;
																o.name = result[i].cycleName;
																seriesData
																		.push(o);

																htmlStr += '<tr>';
																htmlStr += '<td>'
																		+ result[i].cycleName
																		+ '</td>';
																htmlStr += '<td>'
																		+ result[i].deviceCount
																		+ '</td>';
																htmlStr += '<td>'
																		+ result[i].rate
																		+ '%</td></tr>';

															}
															$(
																	".deviceUseTakingCycle")
																	.html(
																			htmlStr);
															myChart4
																	.setOption({// 加载数据图表
																		series : [ {
																			data : seriesData
																		} ]
																	});
														}
													},
													error : function(errorMsg) {
														// 请求失败时执行该函数
														alert("图表请求数据失败!");
														myChart4.hideLoading();
													}
												})
									});

					$("#deviceUseTaking2")
							.bind(
									"click",
									function() {
										$(".duk a").removeClass("hover");
										$
												.ajax({
													type : "post",
													async : true,
													url : "deviceCycleCount",
													data : {
														searchType : 2
													},
													dataType : "json",
													success : function(result) {
														$("#deviceUseTaking2")
																.addClass(
																		"hover");
														if (result) {
															myChart4
																	.hideLoading();// 隐藏加载动画
															var seriesData = []

															var htmlStr = '';

															for (var i = 0; i < result.length; i++) {
																var o = new Object();// 示例初始化一个Object
																console
																		.log(result[i].deviceCount);
																console
																		.log(result[i].cycleName);
																o.value = result[i].deviceCount;
																o.name = result[i].cycleName;
																seriesData
																		.push(o);

																htmlStr += '<tr>';
																htmlStr += '<td>'
																		+ result[i].cycleName
																		+ '</td>';
																htmlStr += '<td>'
																		+ result[i].deviceCount
																		+ '</td>';
																htmlStr += '<td>'
																		+ result[i].rate
																		+ '%</td></tr>';

															}
															$(
																	".deviceUseTakingCycle")
																	.html(
																			htmlStr);
															myChart4
																	.setOption({// 加载数据图表
																		series : [ {
																			data : seriesData
																		} ]
																	});
														}
													},
													error : function(errorMsg) {
														// 请求失败时执行该函数
														alert("图表请求数据失败!");
														myChart4.hideLoading();
													}
												})
									});

					$("#deviceUseTaking3").bind("click", function() {
						$(".duk a").removeClass("hover");
						$.ajax({
							type : "post",
							async : true,
							url : "deviceCycleCount",
							data : {
								searchType : 2
							},
							dataType : "json",
							success : function(result) {
								$("#deviceUseTaking3").addClass("hover");
								if (result) {
									myChart4.hideLoading();// 隐藏加载动画
									myChart4.setOption({// 加载数据图表
										yAxis : {
											data : result.yAxis
										},
										series : [ {
											data : result.seriesData
										} ]
									});
								}
							},
							error : function(errorMsg) {
								// 请求失败时执行该函数
								alert("图表请求数据失败!");
								myChart4.hideLoading();
							}
						})
					});

					
					
$("#ni_g_gime_area1").bind("click", function() {
		$(".teacherUseTaking a").removeClass("hover");
		$.ajax({
			type : "post",
			async : true,
			url : "teacherUseTaking",
			data : {
				searchType : 1
			},
			dataType : "json",
			success : function(result) {
				$("#ni_g_gime_area1").addClass("hover");
				if (result != null) {
					var top5 = result.top5;
					var riseTop5 = result.riseTop5;
					var fallTop5 = result.fallTop5;

					var top5Html = '';
					var riseTop5Html = '';
					var fallTop5Html = '';
					
					for (var i = 0; i < top5.length; i++) {
						if (i < 5) {
							var useTaking = formatSeconds(top5[i].use_taking);
							top5Html += '<div class="ni_num fl t_c">'+(i+1)+'</div>';
							top5Html += '<div class="ni_name fl mgl25">'+ top5[i].user_name + '</div>';
							top5Html += '<div class="ni_st fr ni_up mgr15">' + top5[i].rank + '</div>';
							top5Html += '<div class="ni_time fr mgr15">' + useTaking + '</div>';
						}
					}

					for (var j = 0; j < riseTop5.length; j++) {
						if (i < 5) {
							var useTaking = formatSeconds(riseTop5[j].use_taking);
							riseTop5Html += '<div class="ni_num fl t_c">'+(j+1)+'</div>';
							riseTop5Html += '<div class="ni_name fl mgl25">'+ riseTop5[j].user_name + '</div>';
							riseTop5Html += '<div class="ni_st fr ni_up mgr15">' + riseTop5[j].rank + '</div>';
							riseTop5Html += '<div class="ni_time fr mgr15">' + useTaking + '</div>';
						}
					}

					for (var k = 0; k < fallTop5.length; k++) {
						if (k < 5) {
							var useTaking = formatSeconds(fallTop5[k].use_taking);
							fallTop5Html += '<div class="ni_num fl t_c">'+(k+1)+'</div>';
							fallTop5Html += '<div class="ni_name fl mgl25">'+ fallTop5[k].user_name + '</div>';
							fallTop5Html += '<div class="ni_st fr ni_up mgr15">' + fallTop5[k].rank + '</div>';
							fallTop5Html += '<div class="ni_time fr mgr15">' + useTaking + '</div>';
						}
					}
					$(".top5").html(top5Html);
					$(".riseTop5").html(riseTop5Html);
					$(".fallTop5").html(fallTop5Html);	
				}
			},
			error : function(errorMsg) {
							
			}
		})
});		
					
$("#ni_g_gime_area2").bind("click", function() {
	$(".teacherUseTaking a").removeClass("hover");
	$.ajax({
		type : "post",
		async : true,
		url : "teacherUseTaking",
		data : {
			searchType : 2
		},
		dataType : "json",
		success : function(result) {
			$("#ni_g_gime_area2").addClass("hover");
			if (result != null) {
				var top5 = result.top5;
				var riseTop5 = result.riseTop5;
				var fallTop5 = result.fallTop5;

				var top5Html = '';
				var riseTop5Html = '';
				var fallTop5Html = '';
				
				for (var i = 0; i < top5.length; i++) {
					if (i < 5) {
						var useTaking = formatSeconds(top5[i].use_taking);
						top5Html += '<div class="ni_num fl t_c">'+(i+1)+'</div>';
						top5Html += '<div class="ni_name fl mgl25">'+ top5[i].user_name + '</div>';
						top5Html += '<div class="ni_st fr ni_up mgr15">' + top5[i].rank + '</div>';
						top5Html += '<div class="ni_time fr mgr15">' + useTaking + '</div>';
					}
				}

				for (var j = 0; j < riseTop5.length; j++) {
					if (i < 5) {
						var useTaking = formatSeconds(riseTop5[j].use_taking);
						riseTop5Html += '<div class="ni_num fl t_c">'+(j+1)+'</div>';
						riseTop5Html += '<div class="ni_name fl mgl25">'+ riseTop5[j].user_name + '</div>';
						riseTop5Html += '<div class="ni_st fr ni_up mgr15">' + riseTop5[j].rank + '</div>';
						riseTop5Html += '<div class="ni_time fr mgr15">' + useTaking + '</div>';
					}
				}

				for (var k = 0; k < fallTop5.length; k++) {
					if (k < 5) {
						var useTaking = formatSeconds(fallTop5[k].use_taking);
						fallTop5Html += '<div class="ni_num fl t_c">'+(k+1)+'</div>';
						fallTop5Html += '<div class="ni_name fl mgl25">'+ fallTop5[k].user_name + '</div>';
						fallTop5Html += '<div class="ni_st fr ni_up mgr15">' + fallTop5[k].rank + '</div>';
						fallTop5Html += '<div class="ni_time fr mgr15">' + useTaking + '</div>';
					}
				}
				$(".top5").html(top5Html);
				$(".riseTop5").html(riseTop5Html);
				$(".fallTop5").html(fallTop5Html);	
			}
		},
		error : function(errorMsg) {
						
		}
	})
});


$("#ni_g_gime_area3").bind("click", function() {
	$(".teacherUseTaking a").removeClass("hover");
	$.ajax({
		type : "post",
		async : true,
		url : "teacherUseTaking",
		data : {
			searchType : 3
		},
		dataType : "json",
		success : function(result) {
			$("#ni_g_gime_area3").addClass("hover");
			if (result != null) {
				var top5 = result.top5;
				var riseTop5 = result.riseTop5;
				var fallTop5 = result.fallTop5;

				var top5Html = '';
				var riseTop5Html = '';
				var fallTop5Html = '';
				
				for (var i = 0; i < top5.length; i++) {
					if (i < 5) {
						var useTaking = formatSeconds(top5[i].use_taking);
						top5Html += '<div class="ni_num fl t_c">'+(i+1)+'</div>';
						top5Html += '<div class="ni_name fl mgl25">'+ top5[i].user_name + '</div>';
						top5Html += '<div class="ni_st fr ni_up mgr15">' + top5[i].rank + '</div>';
						top5Html += '<div class="ni_time fr mgr15">' + useTaking + '</div>';
					}
				}

				for (var j = 0; j < riseTop5.length; j++) {
					if (i < 5) {
						var useTaking = formatSeconds(riseTop5[j].use_taking);
						riseTop5Html += '<div class="ni_num fl t_c">'+(j+1)+'</div>';
						riseTop5Html += '<div class="ni_name fl mgl25">'+ riseTop5[j].user_name + '</div>';
						riseTop5Html += '<div class="ni_st fr ni_up mgr15">' + riseTop5[j].rank + '</div>';
						riseTop5Html += '<div class="ni_time fr mgr15">' + useTaking + '</div>';
					}
				}

				for (var k = 0; k < fallTop5.length; k++) {
					if (k < 5) {
						var useTaking = formatSeconds(fallTop5[k].use_taking);
						fallTop5Html += '<div class="ni_num fl t_c">'+(k+1)+'</div>';
						fallTop5Html += '<div class="ni_name fl mgl25">'+ fallTop5[k].user_name + '</div>';
						fallTop5Html += '<div class="ni_st fr ni_up mgr15">' + fallTop5[k].rank + '</div>';
						fallTop5Html += '<div class="ni_time fr mgr15">' + useTaking + '</div>';
					}
				}
				$(".top5").html(top5Html);
				$(".riseTop5").html(riseTop5Html);
				$(".fallTop5").html(fallTop5Html);	
			}
		},
		error : function(errorMsg) {
						
		}
	})
});
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
});
