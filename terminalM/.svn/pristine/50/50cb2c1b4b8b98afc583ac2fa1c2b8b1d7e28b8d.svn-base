<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<style>
.panel{border:solid 1px #ddd;border-radius:4px;}
.panel-head{background-color:#fcfcfc;padding:10px 15px;border-radius:4px 4px 0 0;border-bottom:solid 1px #ddd;}
.panel-foot{background-color:#f5f5f5;padding:10px 15px;border-radius:0 0 4px 4px;border-top:solid 1px #ddd;}
.panel-body{padding:15px;}
.panel .bg-main,.panel .bg-sub,.panel .bg-dot,.panel .bg-red,.panel .bg-yellow,.panel .bg-green{color:#fff;}
.panel .panel-body + .table, .panel .panel-body + .list-group{border-top:solid 1px #ddd;}
.panel .list-group{border:none;border-radius:0;}
.content_first{
	margin-bottom: 10px;
}
.content_first:after{
	content:"";
	display: block;
	height: 0;
	clear: both;
}
.left_first{
	float: left;
	width: 60%;
	padding: 20px;
}
.right_first{
	float: left;
	width: 30%;
	border-left: 1px solid #ddd;
	padding: 20px;
}
.right_first>li,.left_first>li{
	line-height: 30px;
	font-size: 14px;
	margin-top: 10px;
}
.right_first>li>h3,.left_first>li>h3{
	font-size: 14px;
	font-weight: bold;
}
.right_first>li>span:nth-child(1),.left_first>li>span:nth-child(1){
	display: inline-block;
	width: 20%;
}
.right_first>li>span:nth-child(2),.left_first>li>span:nth-child(2){
	display: inline-block;
	width: 78%;
}
</style>

<div class="wql_g_content wql_bge7e9eb pdb30" >
	<div class="w1200">
		<div class="clearfix" style="text-align: center">
			<!-- 面包屑 S-->
			<div class="wql_g_mbx fl" >
				<div class="wql_mbx01">
					<span class="prev"><a href="" class="mgr5">欢迎登陆天喻终端管控平台</a></span>
				</div>
			</div>
			<!-- 面包屑 E-->
			<!-- <div class="fr lh50">
				<span class="">管理员姓名</span><span class="mgl20">管理员所属单位</span>
			</div> -->
		</div>

		<!-- 主体部分 S-->
		<div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
			<div class="wql_g_box pdtb30">


				<div class="panel admin-panel content_first">
					<div class="panel-head" style="text-align: center">
						<strong class="icon-reorder"> 登录信息</strong>
					</div>
					<ul class="left_first">
						<li><span style="vertical-align: top;">账户</span> <span>${loginUser.account }</span>
						</li>
						<li><span style="vertical-align: top;">姓名</span> <span>${loginUser.userName }</span>
						</li>
						<li><span style="vertical-align: top;">所属区域</span> <span>${loginUser.provinceName.areaName }-${loginUser.cityName.areaName }-${loginUser.areaName.areaName }</span>
						</li>
						<li><span style="vertical-align: top;">所属学校</span> <span> ${loginUser.school.name } </span>
						</li>
						<li><span style="vertical-align: top;">所属部门</span> <span>${loginUser.department }</span>
						</li>
						<li><span style="vertical-align: top;">角色名称</span> <span>${loginUser.role.roleName }</span></li>
						<li><span style="vertical-align: top;">当前状态</span> <span><c:if test="${loginUser.status==0 }">正常</c:if><c:if test="${loginUser.status==1 }">禁用</c:if></span>
						</li>
						<li><span style="vertical-align: top;">登录时间</span><span id="time"></li>
					</ul>
					<ul class="right_first">
						<li>
						<iframe width="400" scrolling="no" height="300" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=19&icon=1&py=wuhan&temp=1&num=5&site=12"></iframe>
						</li>
					</ul>
				</div>
				
			</div>
		</div>
		<!-- 主体部分 E-->

	</div>
</div>
<script type="text/javascript">    
window.onload=startTime();
function startTime(){    
  var today=new Date()    
  var week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");    
  var year=today.getFullYear()    
  var month=today.getMonth()+1    
  var date=today.getDate()    
  var day=today.getDay()    
  var h=today.getHours()    
  var m=today.getMinutes()    
  var s=today.getSeconds()    
  // add a zero in front of numbers<10    
  h=checkTime(h)    
  m=checkTime(m)    
  s=checkTime(s)    
  document.getElementById('time').innerHTML=" "+year+"年"+month+"月"+date+"日  "+week[day]+"  "+h+":"+m+":"+s+" "    
  t=setTimeout('startTime()',500)    
 }    
    
 function checkTime(i){    
 if (i<10)     
   {i="0" + i}    
   return i    
 }    
</script> 
