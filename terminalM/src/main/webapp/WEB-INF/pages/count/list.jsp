<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto ni_g_app_user ">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="wql_g_nav fl mgt10">
			<div class="wql_nav01">
				<ul class="clearfix">
					<li class="wql_li on"><a href="list">数据总览</a></li>
					<li class="wql_li"><span class="wql_bl"></span><a href="teacherUseCount">教师使用统计</a></li>
					<li class="wql_li"><span class="wql_bl"></span><a href="deviceUseCount">设备使用统计</a></li>

				</ul>
			</div>
		</div>
	</div>
</div>

<div class="w1200 ni_g_bg_fff ni_g_tj ni_mglr_auto mgt30">

	<ul class="ni_ul clearfix">
		<li class="ni_li">
			<div class="ni_g_tj_lst t_c ni_i01">
				<div class="ni_img ni_mglr_auto"></div>
				<div class="ni_txt mgt20">设备总数</div>
				<div class="ni_num mgt10">${deviceCount}</div>
			</div>
		</li>
		<li class="ni_li">
			<div class="ni_g_tj_lst t_c ni_i02">
				<div class="ni_img ni_mglr_auto"></div>
				<div class="ni_txt mgt20">开机数</div>
				<div class="ni_num mgt10">2</div>
			</div>
		</li>
		<li class="ni_li">
			<div class="ni_g_tj_lst t_c ni_i03">
				<div class="ni_img ni_mglr_auto"></div>
				<div class="ni_txt mgt20">昨日开机率</div>
				<div class="ni_num mgt10">0%</div>
			</div>
		</li>
		<li class="ni_li">
			<div class="ni_g_tj_lst t_c ni_i04">
				<div class="ni_img ni_mglr_auto"></div>
				<div class="ni_txt mgt20">近30天开机率</div>
				<div class="ni_num mgt10">15.67%</div>
			</div>
		</li>
	</ul>
</div>

<!--人数，开机数，应用统计 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto ni_g_tj_3h1 ni_g_tab pdb30">
	<div class="ni_g_tit ni_g_tab_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">累计使用人数</a>
			<a class="pdlr5" href="javascript:;">设备开机数</a>
			<a class="pdlr5" href="javascript:;">应用统计</a>
		</div>
	</div>
	<div class="ni_g_cont ni_g_tab_cont">
		<div class="ni_tab hover">
			<div class="ni_g_echarts_tips clearfix">
				<span class="fl">单位：人</span>
				<span class="fr">教师总人数：<em id="teacherTotal">492</em>人</span>
			</div>
			<div class="ni_g_echarts ni_g_echarts1" id="ni_g_echarts1" style="height:300px;width:1200px"></div>
		</div>
		<div class="ni_tab">
			<div class="ni_g_echarts_tips clearfix">
				<span class="fl">单位：台</span>
				<span class="fr">设备总台数：<em id="deviceOffTotal">10</em>台</span>
			</div>
			<div class="ni_g_echarts ni_g_echarts2" id="ni_g_echarts2" style="height:300px;width:1200px"></div>
		</div>
		<div class="ni_tab">
			<div class="ni_g_echarts_tips clearfix">
				<span class="fl">单位：次</span>
				<span class="fr">应用统计总个数：<em id="appTotal"></em>个</span>
			</div>
			<div class="ni_g_echarts ni_g_echarts3" id="ni_g_echarts3" style="height:300px;width:1200px"></div>
		</div>
		
	</div>
</div>
<!--人数，开机数，应用统计 E-->
<!--使用时长统计 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto ni_g_tj_time pdb30">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">使用时长统计</a>
		</div>
		<div class="fr ni_g_time_area duk">
			<a class="hover" href="javascript:;" id="deviceUseTaking1" onclick="getUseTaking(1)">昨日</a>
			<a href="javascript:;" id="deviceUseTaking2" onclick="getUseTaking(2)">最近一周</a>
			<a href="javascript:;" id="deviceUseTaking3" onclick="getUseTaking(3)">最近一月</a>
		</div>
	</div>
	<div class="ni_g_cont clearfix mgt20 pdt20">
		<div class="fl ni_g_echarts ni_g_echarts4 mgl30" style="width:530px;height: 330px;" id="ni_g_echarts4"></div>
		<div class="fr ni_g_table01 mgr30 pdr15" style="width:574px;">
			<table class="t_c">
				<tr>
					<th>使用时长</th>
					<th>设备数</th>
					<th>时间占比</th>
				</tr>
				 <tbody class="deviceUseTakingCycle">
				 
				 </tbody>

			</table>
		</div>
	</div>
</div>
<!--使用时长统计 E-->
<!--教师使用排名 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto ni_g_pm_tear pdb30">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">教师使用排名</a>
		</div>
		<div class="fr ni_g_time_area teacherUseTaking">
			<a class="hover" href="javascript:;" id="ni_g_gime_area1" onclick="getTeacher(1)">昨日</a>
			<a href="javascript:;" id="ni_g_gime_area2" onclick="getTeacher(2)">最近一周</a>
			<a href="javascript:;" id="ni_g_gime_area3" onclick="getTeacher(3)">最近一月</a>
		</div>
	</div>   
	<div class="ni_g_cont clearfix mgt20 pdt20">
		<ul class="ni_ul mglr20 ni_g_pm_list01">
			<li class="ni_li">
				<div class="ni_g_pm_tit ni_i01 mgl10" >
					前五名排行
				</div>
				<dl class="ni_dl mgt25" id="top5">

				</dl>
			</li>
			<li class="ni_li ni_li02">
				<div class="ni_g_pm_tit ni_i02 mgl10" >
					上升最快排名
				</div>
				<dl class="ni_dl mgt25" id="riseTop5">

				</dl>
			</li>
			<li class="ni_li ni_li02">
				<div class="ni_g_pm_tit ni_i03 mgl10" >
					下降最快排名
				</div>
				<dl class="ni_dl mgt25" id="fallTop5">

				</dl>
			</li>
		</ul>
	</div>
</div>
<!--教师使用排名 E-->
<!--学科排名 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto ni_g_pm_xk pdb30">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">学科</a>
		</div>
		<div class="fr ni_g_time_area useTaking">
			<a class="hover" href="javascript:;" id="useTaking1" onclick="getSubject(1)">昨日</a>
			<a href="javascript:;" id="useTaking2" onclick="getSubject(2)">最近一周</a>
			<a href="javascript:;" id="useTaking3" onclick="getSubject(3)">最近一月</a>
		</div>
	</div>
	<div class="ni_g_cont clearfix mgt20 pdt20">
		<div class="ni_g_echarts ni_g_echarts5 mglr20" id="ni_g_echarts5" style="height:370px;"></div>
		<div class="ni_g_table01 mglr20 mgt30">
			<table class="t_c">
				<tr>
					<th>学科</th>
					<th>使用总时长</th>
					<th>使用次数</th>
					<th>占比</th>
					<th>累计使用人数</th>
				</tr>
			<tbody id="subjectCount">
				
			</tbody>
			
			</table>
		</div>
	</div>
</div>

<!--应用使用情况 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto ni_g_app_user ">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">应用使用情况</a>
		</div>
		<div class="fr ni_g_time_area useQK">
			<a class="hover" href="javascript:;" id="useQK1" onclick="getSoftware(1)">昨日</a>
			<a href="javascript:;" id="useQK2" onclick="getSoftware(2)">最近一周</a>
			<a href="javascript:;" id="useQK3" onclick="getSoftware(3)">最近一月</a>
		</div>
	</div>
	<div class="ni_g_cont clearfix mgt20 pdt20">
		<ul class="ni_ul mglr20 ni_g_pm_list01">
			<li class="ni_li ni_li01 mgl30">
				<div class="ni_g_pm_tit ni_i01 mgl10">
					前五名排行
				</div>
				<dl class="ni_dl mgt25 topApp">
					
					
				</dl>
			</li>
			<li class="ni_li ni_li02">
				<div class="ni_g_pm_tit ni_i04 mgl10">
					其他应用
				</div>
				<div class="ni_g_black_a mgt25 qtApp">

				</div>
			</li>
		</ul>
	</div>
</div>
<script type="text/javascript" src="http://116.211.105.43:15022/ecspage/js/echarts.common.min.js"></script>
<script type="text/javascript" src="${ctx}/js/MyPageJs/page.js"></script>
<script type="text/javascript">
/* 切换tab S */
$(".ni_g_tab .ni_g_tab_tit a").on("click",function() {
		if (!($(this).hasClass("hover"))) {
			$(this).siblings().removeClass("hover");
			$(this).addClass("hover");
			$(this).parents(".ni_g_tab").find(".ni_g_tab_cont .ni_tab")
				.removeClass("hover").eq($(this).index()).addClass(
				"hover");
		}
	})

var ni_g_echarts1 = echarts.init(document.getElementById('ni_g_echarts1'));
var ni_g_echarts2 = echarts.init(document.getElementById('ni_g_echarts2'));
var ni_g_echarts3 = echarts.init(document.getElementById('ni_g_echarts3'));
var ni_g_echarts4 = echarts.init(document.getElementById('ni_g_echarts4'));
var ni_g_echarts5 = echarts.init(document.getElementById('ni_g_echarts5'));
var count = [];
var time = [];
<c:forEach items="${tDataGatherLogs }" var="tDataGatherLog">
count.push("${tDataGatherLog.userCount}");
time.push("${tDataGatherLog.createTimeStr}");
</c:forEach>
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
			axisLine : {
				lineStyle : {
					color : "#e2e2e2"
				}
			},
			axisLabel : {
				color : "#555555"
			},
		},
		xAxis : {
			type : 'category',
			axisTick : {
				show : false
			},
			data : time,
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
					show : true

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
					label: {  
	                    show: true,//是否展示  
	                    textStyle: {  
	                        fontWeight:'bolder',  
	                        fontSize : '12',  
	                        fontFamily : '微软雅黑',
	                        color:"#000000"
	                    }  
	                } 
				}
			},
			data : count
		} ]
	};
ni_g_echarts1.setOption(option1);

var count2 = [];
var time2 = [];
<c:forEach items="${devices }" var="tDataGatherLog">
count2.push("${tDataGatherLog.userCount}");
time2.push("${tDataGatherLog.createTimeStr}");
</c:forEach>
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
        data :time2,
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
                show : true
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
                label: {
                    show: true,//是否展示
                    textStyle: {
                        fontWeight:'bolder',
                        fontSize : '12',
                        fontFamily : '微软雅黑',
                        color:"#000000"
                    }
                }
            }
        },
        data : count2
    } ]
};
ni_g_echarts2.setOption(option2);

var useCount = [];
var softwareName = [];
<c:forEach items="${softwares}" var="tDataGatherLog">
useCount.push("${tDataGatherLog.userCount}");
softwareName.push("${tDataGatherLog.softwareName}");
</c:forEach>
option3 = {
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
        data :softwareName,
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
                show : true
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
                label: {
                    show: true,//是否展示
                    textStyle: {
                        fontWeight:'bolder',
                        fontSize : '12',
                        fontFamily : '微软雅黑',
                        color:"#000000"
                    }
                }
            }
        },
        data : useCount,
    } ]
};
ni_g_echarts3.setOption(option3);

getTeacher(1);
//教师使用排名
function getTeacher(timeType){
    $(".teacherUseTaking a").removeClass("hover");
    $("#ni_g_gime_area"+timeType).addClass("hover");
    $.ajax({
        type : "post",
        async : true,
        url : "getTeacherRank",
        data : {
            timeType : timeType
        },
        dataType : "json",
        success : function(result) {
            if (result != null) {
                var top5 = result.top;
                var top5Html = '';
                var riseTop5Html = '';
                var fallTop5Html = '';
                for (var i = 0; i < top5.length; i++) {
                    if (i < 5) {
                        var upOrDown = ''
                        if(top5[i].rank>=0){
                            upOrDown = 'ni_up';
                        }else{
                            upOrDown = 'ni_down';
                        }
                        top5Html += '<dd class="ni_dd clearfix ni_dd01">';
                        top5Html += '<div class="ni_num fl t_c">'+(i+1)+'</div>';
                        top5Html += '<div class="ni_name fl mgl25">'+ top5[i].userAccount + '</div>';
                        top5Html += '<div class="ni_st fr '+upOrDown+' mgr15">' + top5[i].rank + '</div>';
                        top5Html += '<div class="ni_time fr mgr15">' + top5[i].createTimeStr + '</div>';
                        top5Html += '</dd>';
                    }
                }
                top5.sort(function(a,b){
                    return b.rank-a.rank;
                });
                for (var j = 0; j < top5.length; j++) {
                    if (j < 5) {
                        var upOrDown = ''
                        if(top5[j].rank>=0){
                            upOrDown = 'ni_up';
                        }else{
                            upOrDown = 'ni_down';
                        }
                        riseTop5Html += '<dd class="ni_dd clearfix ni_dd01">';
                        riseTop5Html += '<div class="ni_num fl t_c">'+(j+1)+'</div>';
                        riseTop5Html += '<div class="ni_name fl mgl25">'+ top5[j].userAccount + '</div>';
                        riseTop5Html += '<div class="ni_st fr '+upOrDown+' mgr15">' + top5[j].rank + '</div>';
                        riseTop5Html += '<div class="ni_time fr mgr15">' + top5[j].createTimeStr + '</div>';
                        riseTop5Html += '</dd>';
                    }
                }
                top5.sort(function(a,b){
                    return a.rank-b.rank;
                });
                for (var i = 0; i < top5.length; i++) {
                    if (i < 5) {
                        var upOrDown = ''
                        if(top5[i].rank>=0){
                            upOrDown = 'ni_up';
                        }else{
                            upOrDown = 'ni_down';
                        }
                        fallTop5Html += '<dd class="ni_dd clearfix ni_dd01">';
                        fallTop5Html += '<div class="ni_num fl t_c">'+(i+1)+'</div>';
                        fallTop5Html += '<div class="ni_name fl mgl25">'+ top5[i].userAccount + '</div>';
                        fallTop5Html += '<div class="ni_st fr '+upOrDown+' mgr15">' + top5[i].rank + '</div>';
                        fallTop5Html += '<div class="ni_time fr mgr15">' + top5[i].createTimeStr + '</div>';
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
}

//使用时长
getUseTaking(1);
function getUseTaking(timeType){
    $(".duk a").removeClass("hover");
    $("#deviceUseTaking"+timeType).addClass("hover");
    $.ajax({
        type : "post",
        url : "getUseTaking",
        data : {
            timeType : timeType
        },
        dataType : 'json',
        success : function(result) {
            var seriesData = []
            var htmlStr = '';
            var totalDevice = parseInt("0");
            for (var i = 0; i < result.length; i++) {
                var o = new Object();// 示例初始化一个Object
                o.value = result[i].deviceCount;
                totalDevice = totalDevice + parseInt(o.value.toString());
                o.name = result[i].cycleName;
                seriesData.push(o);

                htmlStr += '<tr>';
                htmlStr += '<td>'+ result[i].cycleName+ '</td>';
                htmlStr += '<td>'+ result[i].deviceCount+ '</td>';
                htmlStr += '<td>'+ result[i].rate+ '%</td></tr>';
            }

            if(totalDevice==0){
                var da = new Object();
                seriesData = []
                da.value= Number("100");
                da.name="无设备";
                seriesData.push(da);
            }

            $(".deviceUseTakingCycle").html(htmlStr);
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
                    data : seriesData
                } ]
            };
            ni_g_echarts4.setOption(option4);
        },
		error:function (XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown)
        }
    })
}

//学科排名
getSubject(1)
function getSubject(timeType){
    $(".useTaking a").removeClass("hover");
    $("#useTaking"+timeType).addClass("hover");
    $.ajax({
		type:"post",
		url:"getSubjectRank",
		data:{
            timeType:timeType
		},
        async : true,
		datatype:"json",
		success:function (data) {
            var x5 = [];
            var y5 = [];
            var htmlStr = '';
            if(data!=null){
                var subject = data.subject;
                for (var i = 0; i < subject.length; i++) {
                    x5.push(subject[i].percent);
                    y5.push(subject[i].subjectId);
                    htmlStr += '<tr>';
                    htmlStr += '<td>' + subject[i].subjectId + '</td>';
                    htmlStr += '<td>' + subject[i].createTimeStr + '</td>';
                    htmlStr += '<td>' + subject[i].userCount + '</td>';
                    htmlStr += '<td>' + subject[i].percent + '%</td>';
                    htmlStr += '<td>' + subject[i].rank+ '</td>';
                    htmlStr += '</tr>';
				}
			}
            $("#subjectCount").html(htmlStr);

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
                    data : y5,
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
                        },
                        label: {
                            show: true,//是否展示
                            textStyle: {
                                fontWeight:'bolder',
                                fontSize : '12',
                                fontFamily : '微软雅黑',
                            }
                        }
                    },

                    data : x5
                } ]
            };
            ni_g_echarts5.setOption(option5);
        }
	})
}

//应用排名
getSoftware(1)
function getSoftware(timeType){
    $(".useQK a").removeClass("hover");
    $("#useQK"+timeType).addClass("hover");
	$.ajax({
        type:"post",
        url:"getSoftware",
        data:{
            timeType:timeType
        },
        async : true,
        datatype:"json",
        success:function (result) {
            $(".topApp").html('');
            $(".qtApp").html('');
                var htmlStr = '';
                var otherName = '';
                var softwares = result.software;
                for (var i = 0; i < softwares.length; i++) {
                    if( i >13 ){
                        break;
                    }
                    if (i < 5) {
                        htmlStr += '<dd class="ni_dd clearfix ni_dd01">';
                        htmlStr += '<div class="ni_num fl t_c">' + (i + 1)  + '</div>';
                        htmlStr += '<div class="ni_name fl mgl25" title=\"' + softwares[i].softwareName + '\">'  + softwares[i].softwareName  + '</div>';
                        htmlStr += '<div class="ni_time fr mgr15">'  + softwares[i].userCount + '次</div>';
                        htmlStr += '</dd>';
                    } else {
                        otherName += '<a title=\"' + softwares[i].softwareName + '\">'  + softwares[i].softwareName  + '</a>';
                    }
                }
                $(".topApp").html(htmlStr);
                $(".qtApp").html(otherName);
            }
	})
}
</script>