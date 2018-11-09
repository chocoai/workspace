<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>


<div class="w1200 ni_g_bg_fff mgt30 ni_mglr_auto pdb30 ">

	<form id="pageForm"  name="pageForm" action="deviceDetail.html" method="post">
		<input type="hidden" name="offStatus" value="${deviceInfo.onLineType}" />
		<input type="hidden" name="deviceId" value="${deviceInfo.id}" />
		<div class="ni_g_tit mglr20 pdlr10 clearfix">
			<div class="ni_g_tit fl">
				<a href="device.html">设备列表</a>&nbsp;&nbsp;<em>></em>&nbsp;&nbsp;<a class="hover">设备 ${deviceInfo.name}</a>
			</div>
			<div class="fr">
				
			</div>
		</div>
	</form>
	<div class="ni_g_cont clearfix mgt20">
		<div class="ni_g_kz_lr clearfix mglr40">
			<div class="fl ni_screen">
				<div class="ni_img">
					<img src="${imgPath}" alt="" />
				</div>
				<div class="ni_btn mgt15">
					<a id="shutdown" class="ni_i01 <c:if test="${deviceInfo.onLineType == 0 }">ni_no_at</c:if>" href="javascript:void(0)"><span>关机</span></a>
					<a id="restart" class="ni_i02 <c:if test="${deviceInfo.onLineType == 0 }">ni_no_at</c:if>" href="javascript:void(0)"><span>重启</span></a>
					<a id="lockScreen" class="ni_i03 <c:if test="${deviceInfo.onLineType == 0  }">ni_no_at</c:if>" href="javascript:void(0)"><span>锁屏</span></a>
					<a class="ni_i06 ni_last ni_fun_pop_wzgb <c:if test="${deviceInfo.onLineType == 0 }">ni_no_at</c:if>" href="javascript:;"><span>文字信息广播</span></a>
				</div>
			</div>
			<div class="fr ni_connect pdb10">
				<div class="ni_p clearfix">
					<div class="fl">
						<span class="ni_n">设备名称：</span>
						<span>${deviceInfo.deviceName}</span>
					</div>
					<div class="fr">
						
					</div>
				</div>
				
				<div class="ni_p">
					<span class="ni_n">学生信息: 
						${deviceInfo.schoolName}/${deviceInfo.studentName}
					</span>
				</div>
				
				
				<div class="ni_p clearfix">
					<div class="fr">
						<span class="ni_n">设备品牌：</span>
						<span>${deviceInfo.brandName}</span>
					</div>
					
					<div class="fl">
						<span class="ni_n">设备型号：</span>
						<span>${deviceInfo.modelName }</span>
					</div>
				</div>
				
				<div class="ni_p clearfix">
					<div class="fl">
						<span class="ni_n">系统版本：</span>
						<span>${deviceInfo.systemVersion}</span>
					</div>
					<div class="fr">
						<span class="ni_n">内       存：</span>
						<span>${deviceInfo.storage }</span>
					</div>
				</div>
				<div class="ni_p clearfix">
					<div class="fr">
						<span class="ni_n">分   辨  率：</span>
						<span>${deviceInfo.resolution}</span>
					</div>
					
					<div class="fl">
						<span class="ni_n">MAC地 址：</span>
						<span>${deviceInfo.mac }</span>
					</div>
				</div>
				
			</div>
		</div>

	</div>
</div>
<!--设备详细 E-->

<!--设备列表 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto pdb30 pdt30">
	<div class="ni_g_table01 mglr40 mgt10 chklist2 ni_g_dev_list_tb ">
		<table class="t_c">
			<thead>
				<tr>
					<th width="10%">星期</th>
					<th width="10%">日期</th>
					<th width="10%">学科</th>
					<th width="15%">授课教师</th>
					<th width="15%">有效时长</th>
					<th width="40%">截图</th>
				</tr>	
			</thead>
			<tbody>
				
					<tr>
						<td>
							
						</td>
						
						<td>
							
						</td>
						
						<td>
						
						</td>
						
						<td>
							
						</td>
						
						<td>
						
						</td>
						
						<td class="ni_fun_tab_img ni_g_tab_img" data-img="">
							
						
						</td>
						
					</tr>
				
			</tbody>
		</table>
	</div>
		
</div>

<div class="ni_g_pop_txt_udp dis_none pdb20">
	<div class="ni_tit pdtb5">输入广播消息</div>
	<textarea class="inp" name="sendMessage" id="sendMessage" style="width:80%;height:120px;margin:0 auto;display:block;"></textarea>
	<div class="ni_d_lr radiolist mgt15">
		
	</div>
	<div class="ni_d_lr radiolist mgt15">
		<span>连续时间：</span>
		<input type="text" class="inp t_c" id="delayTime" name="delayTime" style="width:40px;ver"
		onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
		
		 />
		秒
	</div>
</div>


<!--明细数据 E-->
<div class="ni_g_pop_show_imgs_list dis_none">
	<div class="ni_img_box">
		<div class="ni_prev"></div>
		<div class="ni_next"></div>
		<div class="ni_list">
			<ul class="ni_ul">
			</ul>
		</div>
		<div class="ni_del clearfix mgt25">
			<span class="fl ni_time"></span>
			<span class="fr ni_num">第<em class="ni_now">1</em>张，共<em class="ni_all">10</em>张</span>
		</div>
		
	</div>
</div>


<script>
var offStatus =  $("input[name='offStatus']").val();
var deviceId= $("input[name='deviceId']").val();


function modifyStatus(){

	$("#shutdown").addClass("ni_no_at");
	$("#restart").addClass("ni_no_at");
	$("#lockScreen").addClass("ni_no_at");
	//$(".ni_fun_pop_wzgb").addClass("ni_no_at");
	offStatus = "0";
	console.log(offStatus)
}

if (offStatus == "1") {
	$("#shutdown").bind("click", function() {
		$.ajax({
			type : "post",
			async : true,
			url : "../monitor/shutDown",
			data : {
				deviceId:deviceId
			},
			dataType : "json",
			success : function(result) {
				modifyStatus()
			},
			error : function(errorMsg) {
				modifyStatus()
			}
		})
	});

	$("#restart").bind("click", function() {
		$.ajax({
			type : "post",
			async : true,
			url : "../monitor/restart",
			data : {
				deviceId : deviceId
			},
			dataType : "json",
			success : function(result) {
				modifyStatus()
			},
			error : function(errorMsg) {
				modifyStatus()
			}
		})
	});

	$("#lockScreen").bind("click", function() {
		$.ajax({
			type : "post",
			async : true,
			url : "../monitor/lockScreen",
			data : {
				deviceId:deviceId
			},
			dataType : "json",
			success : function(result) {
				modifyStatus()
			},
			error : function(errorMsg) {
				modifyStatus()
			}
		})
	});

	
}
$(function(){
	$('.ni_fun_pop_wzgb').on('click', function() { // 甯﹀彇娑堬紝纭畾鎸夐挳鐨�
		var dialog = art.dialog({
			title : '文字广播消息',
			content : $(".ni_g_pop_txt_udp")[0],
			width : '500px',
			ok : function() {
				var sendText = $("#sendMessage").val();
				var delayTime = $("#delayTime").val();
				
				
	
				console.log(sendText);
				console.log(delayTime);
				$.ajax({
					type : "post",
					async : true,
					url : "../monitor/batchSendMessage",
					data : {
						sendText : sendText,
						excuteTime : delayTime,
						deviceId : deviceId
					},
					dataType : "json",
					success : function(result) {
						this.close();
					},
					error : function(errorMsg) {
	
					}
				})
			},
			padding : 0,
			cancelVal : '取消',
			cancel : true,
			maskClick : true
		});
	});
})
</script>