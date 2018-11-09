<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<a href="/project/myProjectList.htm">我的项目</a> >
<a >进度图</a>
</div>
<link rel="stylesheet" href="/gantt/css/style.css" />

<script src="/gantt/js/jquery.fn.gantt.js" charset ="GB2312"></script>

<script src="/gantt/js/jquery.cookie.js"></script> 

<script >
$(function() {
			"use strict";
			$(".gantt").gantt({
/***				source: [{
					name: "咨询任务书",
					desc: "第一步",
	
					values: [{
						from: "/Date(1320192000000)/",
						to: "/Date(1320292000000)/",
						label: "Requirement Gathering",
						customClass: "ganttRed"
					}]
				},{
					name: "接收资料登记",
					desc: "第二步",

					values: [{
						from: "/Date(1320292000000)/",
						to: "/Date(1320392000000)/",
						label: "Scoping",
						customClass: "ganttRed"
					}]
				},{
					name: "任务下达",
					desc: "第三步",
		
					values: [{
						from: "/Date(1320392000000)/",
						to: "/Date(1320492000000)/",
						label: "Development",
						customClass: "ganttGreen"
					}]
				},{
					name: "整理资料清单",
					desc: "第四步",
					desc2: "第一步",
					values: [{
						from: "/Date(1320492000000)/",
						to: "/Date(1320592000000)/",
						label: "Showcasing",
						customClass: "ganttBlue"
					}]
				}],   ****/
				source : ${source}  ,
				months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"], //月份显示的语言
				dow: ["日", '一', "二", "三", "四", "五", "六"],	 //星期显示的语言
				scale : "days",
				navigate: "scroll",
				minScale : "days",
				maxScale: "weeks",
				itemsPerPage: 18
			});


		});
</script>
<div class="gantt"></div>