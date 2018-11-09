<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>


<div class="w1200 ni_g_bg_fff mgt30 ni_mglr_auto pdb30 ">

	<form id="pageForm"  name="pageForm" action="deviceDetail.html" method="post">
		<input type="hidden" name="offStatus" value="${deviceInfo.onLineType}" />
		<input type="hidden" name="deviceId" value="${deviceInfo.id}" />
		<div class="ni_g_tit mglr20 pdlr10 clearfix">
			<div class="ni_g_tit fl">
				设备列表  &nbsp;&nbsp;<em>></em>&nbsp;&nbsp; 设备 ${deviceInfo.name}
			</div>
			<div class="fr">
				
			</div>
		</div>
	</form>
	<div class="ni_g_cont clearfix mgt20">
		<div class="ni_g_kz_lr clearfix mglr40">
			<div class="fl ni_screen">
				<div class="ni_img">
					<img src="${imgPath}" alt="" widget="400px;"/>
				</div>
				<div class="ni_btn mgt15">
					<a id="shutdown" class="ni_i01 <c:if test="${deviceInfo.onLineType == 0 }">ni_no_at</c:if>" href="javascript:void(0)"><span>关机</span></a>
					<a id="restart" class="ni_i02 <c:if test="${deviceInfo.onLineType == 0 }">ni_no_at</c:if>" href="javascript:void(0)"><span>重启</span></a>
					<a id="lockScreen" class="ni_i03 <c:if test="${deviceInfo.onLineType == 0  }">ni_no_at</c:if>" href="javascript:void(0)"><span>锁屏</span></a>
					<a class="ni_i06 ni_last ni_fun_pop_wzgb <c:if test="${deviceInfo.onLineType == 0 }">ni_no_at</c:if>" href="javascript:;"><span>文字信息广播</span></a>
				</div>
			</div>
			<div class="fr ni_connect pdb10">
				<div class="ni_p mgt30">
					<span class="ni_n">地点: 
					${deviceInfo.schoolLocationName}/${deviceInfo.schoolLocationAreaName}
					</span>
				</div>
				<div class="ni_p clearfix">
					<div class="fl">
						<span class="ni_n">设备名称：</span>
						<span>${deviceInfo.deviceName}</span>
					</div>
					<div class="fr">
						
					</div>
				</div>
				<div class="ni_p clearfix">
					<div class="fl">
						<span class="ni_n">系统版本：</span>
						<span>${deviceInfo.systemVersion}</span>
					</div>
					<div class="fr">
						<span class="ni_n">内 存：</span>
						<span>${deviceInfo.storage }</span>
					</div>
				</div>
				<div class="ni_p clearfix">
					<div class="fl">
						<span class="ni_n">存 储：</span>
						<span>${deviceInfo.diskSize}</span>
					</div>
					<div class="fr">
						<span class="ni_n">分辨率：</span>
						<span>${deviceInfo.resolution}</span>
					</div>
				</div>
				
				<div class="ni_p clearfix">
					<div class="fl">
						<span class="ni_n">mac地址：</span>
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
					<c:forEach items="${list}" var="obj" varStatus="status">
						<tr>
							<td>
								${obj.week}
							</td>
							
							<td>
								<fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd" />
							</td>
							
							<td>
								${obj.subjectName}
							</td>
							
							<td>
								${obj.userName}
							</td>
							
							<td>
								${obj.useTakingStr}
							</td>
							
							<td class="ni_fun_tab_img ni_g_tab_img" data-img="${obj.imgPaths}">
								<c:if test="${not empty obj.imgPaths }">
								<c:set value="${fn:split(obj.imgPaths, ',')}" var="imgPath" />
								共${fn:length(imgPath)}张
								</c:if>
								
								
							</td>
							
						</tr>
					</c:forEach>
					
				
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
		<input type="text" class="" style="width:40px;"id="delayTime" name="delayTime" style="width:40px;"
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


function showImg5(el){
	$(el).each(function(){
		var imgs = $(this).attr("data-img").split(",")

		for(var i=0;i<imgs.length;i++){
			if(i >=5) {
				break;
			}
			
			if(imgs[i]==''){
				continue;
			}
			
			var img = $("<img src='"+imgs[i]+"'/>");
			$(this).prepend(img);
		}
	})
}

showImg5(".ni_fun_tab_img");


$('.ni_fun_tab_img img').on('click', function () {
	var dia_imgs = null;
	
	var ni_fun_tab_img = $(this).parent(".ni_fun_tab_img");
	var igs = [];
	ni_fun_tab_img.each(function(){
	
		var imgs = $(this).attr("data-img").split(",")
		for(var i=0;i<imgs.length;i++){
			var json={src:imgs[i]}
			igs.push(json)
		}
	})
	
    var dialog = art.dialog({
        title: '截图',
        content: $('.ni_g_pop_show_imgs_list')[0],   //弹出框的内容
        width: '870x',
        padding:0,
        initialize:function(){
    		dia_imgs = new showImgList();
			dia_imgs.init(".ni_img_box",".ni_ul",".ni_next",".ni_prev",igs)
        },
    });
    return false;
})

//查看大图列表 
function showImgList(){

}

showImgList.prototype = {
	el_box:null,	//总元素
	el_img_box:null,//图片集父元素
	el_next:null,	//下一个，翻页按钮
	el_prev:null,	//上一个，翻页按钮
	el_time:null,	//图片时间
	el_all_num:null,//总图片数
	el_now_num:null,//当前图片数
	now_num:null,	//当前第几个
	imgs:null,		//图片集合对象		
	init:function(box,img_box,next,prev,imgs){
		this.el_box = $(box);
		this.el_img_box = this.el_box.find(img_box);
		this.el_next = this.el_box.find(next);
		this.el_prev = this.el_box.find(prev);
		this.el_time = this.el_box.find(".ni_time em");
		this.el_all_num = this.el_box.find(".ni_num .ni_all");
		this.el_now_num = this.el_box.find(".ni_num .ni_now");
		this.imgs = imgs;
		//重置
		this.off();
		//初始化图片列表，时间，页数
		this.el_img_box.html("");
		for(var i=0;i<this.imgs.length;i++){
			var $li = $("<li class='ni_li'><img src='"+this.imgs[i].src+"'/></li>")
			this.el_img_box.append($li);
		}
		this.el_time.html(this.imgs[0].time);
		this.el_all_num.html(this.imgs.length);
		this.el_now_num.html(1);
		this.now_num = 1;
		this.to_page(1);
		var _this = this;
		//绑定翻页事件
		this.el_prev.on("click",function(){
			_this.fun_prev();
		})
		this.el_next.on("click",function(){
			_this.fun_next();
		})
	},
	fun_prev:function(){
		if(this.now_num <=1){
			return;
		}else{
			this.to_page(--this.now_num);
		}
	},
	fun_next:function(){
		if(this.now_num >= this.imgs.length){
			return;
		}else{
			this.to_page(++this.now_num);
		}
	},
	to_page:function(num){
		var mgl = (num-1)*this.el_img_box.find("img").outerWidth()*-1;
		this.el_img_box.stop(true,true).animate({"margin-left":mgl},400);
		this.el_time.html(this.imgs[num-1].time);
		this.el_now_num.html(num);
		if(this.imgs.length <=1){
			this.el_prev.addClass("no_hover");
			this.el_next.addClass("no_hover");
		}
		else if(num <=1){
			this.el_prev.addClass("no_hover")
			this.el_next.removeClass("no_hover");
		}
		else if(num >= this.imgs.length){
			this.el_next.addClass("no_hover")
			this.el_prev.removeClass("no_hover");
		}

	},
	off:function(){
		this.el_prev.off("click");
		this.el_next.off("click");
		this.el_img_box.html("");
		this.el_img_box.css("margin-left","0px");
	}
}


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
		myConfirm('系统确认框','是否发送命令！',function(r){  
    		if(r){ 
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
    		}
		})
	});

	$("#restart").bind("click", function() {
		myConfirm('系统确认框','是否发送命令！',function(r){  
    		if(r){
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
    		}
		})
		
	});

	$("#lockScreen").bind("click", function() {
		myConfirm('系统确认框','是否发送命令！',function(r){  
    		if(r){
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
				
				myConfirm('系统确认框','是否发送命令！',function(r){  
		    		if(r){
		    			$.ajax({
							type : "post",
							async : true,
							url : "../monitor/sendMessage",
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