<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->

        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">终端设备 &gt;</span><span class="on mgl5">一体机管理</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        
       
        <!-- 主体部分 S-->
        <div class="clearfix wql_g_mainBox ">
        	<form id="pageForm"  name="pageForm" action="${ctx}/manage/aio/list" method="post">
        	
	        <input type="hidden" name="provinceCode" value="${provinceCode}">
	        <input type="hidden" name="cityCode" value="${cityCode}">
	        <input type="hidden" name="areaCode" value="${areaCode}">
	        
            <div class="wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
                <div class="wql_g_tit">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">查询条件</h3>
                    </div>
                    
                </div>
                <div class="wql_g_list02">
                    <ul class="wql_ul clearfix mgt25">
                    	<li class="wql_li mgr10">
                    		地区
		                   		<span class="sys_seleautodiv mgr10" name="province_sys_seleautodiv" style="width: 120px; z-index: 3;">
				                    <span class="sys_seleautocur" name="provinceSpan" id="provinceSpan">
				                        <p>请选择</p>
				                        <input type="hidden" class="selRes" value="-1">
				                    </span>
				                    <span class="sys_seleautodrop animate" name="province" style="width: 120px; display: none; z-index: 9999;">
				                    				                    	
				                    </span>
				                </span>
				                <span class="sys_seleautodiv mgr10" name="city_sys_seleautodiv" style="width: 120px; z-index: 3;">
				                    <span class="sys_seleautocur" name="citySpan">
				                        <p name="cityP">请选择</p>
				                        <input type="hidden" class="selRes" value="-11">
				                    </span>
				                    <span class="sys_seleautodrop animate"  style="width: 120px; display: none; z-index: 9999;">
				                    
				                    </span>
				                </span>
				                <span class="sys_seleautodiv mgr10" name="area_sys_seleautodiv" style="width: 120px; z-index: 3;">
				                    <span class="sys_seleautocur" name="areaSpan">
				                        <p>请选择</p>
				                        <input type="hidden" class="selRes" value="-1">
				                    </span>
				                    <span class="sys_seleautodrop animate"  style="width: 120px; display: none; z-index: 9999;">
				                    	
				                    </span>
				                </span>
	                    </li>
                   
                        <li class="wql_li">
                            <div class="wql_li_box mgl15">
                                <a href="javascript:;" class="wql_btn_search">查询</a>
                            </div>
                        </li>
                    </ul>
                </div> 
                <div class="wql_g_tit">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">教学一体机信息列表</h3>
                        <div class="wql_g_btn mgt8 fr">
                            <div class="wql_btn01 clearfix">
                               <a href="#" class="wql_addClass02 fl controller">终端监控</a>
                               <a href="#" class="wql_addClass01 mgl30 fl sendMessage">文字广播</a>
                               <a href="#" class="wql_addClass01 mgl30 fl updateConfig">更新设备配置文件</a>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div class="site_sysTable wql_g_table mgt15">
                    <table class="wql_table01 t_l chklist2">
                        <thead>
                            <tr>
                            	<th width="2%"><label class="chkAll"><input type="checkbox" name="chkAll"/></label></th>
                                <th  width="10%">设备编号</th>
                                <th  width="10%">存放位置</th>
                                <th width="10%">设备名字</th>
                                <th width="10%">操作系统</th>
                                <th width="15%">注册日期</th>
                                <th width="10%">状态</th>
                                <th width="10%">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${list}" var="obj" varStatus="status">
                        		<tr>
                        			<td>
                        			
                        			<label class=""><input type="checkbox" value='${obj.id}' name="chkItm"/></label>
                        		
                        			</td>
                        			<td>${obj.deviceCode}</td>
                        			<td>${obj.schoolLocationName}/${obj.schoolLocationAreaName}</td>
                        			<td>${obj.deviceName}</td>
                        			<td>${obj.systemVersion}</td>
                        			<td>
                        				<fmt:formatDate value="${obj.registerTime}" pattern="yyyy-MM-dd" />
                        			</td>
									
									<td>
			                        <c:if test="${obj.onLineType==0 }">
	                        			<span class="ni_on">在线</span>
	                        		</c:if>
	                        		
	                        		<c:if test="${obj.onLineType==1 }">
	                        			<span class="ni_off">离线</span>
	                        		</c:if>
									</td>
									
                        			<td>
                        				<span><a href="javascript:void(0)" onclick="deviceDetail('${obj.id}')" class="">设备详情</a></span>
                        			</td>
                        			
                        		</tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                   
                    <div class="mgt40">
                        <%@ include file="/WEB-INF/pages/common/vpage.jsp"%>
                    </div>
                </div>
            </div>
            </form>
        </div>
        

        <!-- 主体部分 E-->

    </div>
</div>






<!-- 弹窗 S-->
<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog02">
        <div class="pdlr40 pdt30">
            <img width="450px" id="currentImg" height="300px" src="">
        </div>
    </div>
</div>

<div class="ni_g_pop_zdgk dis_none">
	<div class="clearfix">
		<div class="fl ni_lb">
			<span class="hover power">电源管理</span>
			<span class="lock">锁屏设置</span>
		</div>
		<div class="fl mgt30 mgl30">
			<div class="ni_tab">
				<div class="ni_cont">
					<div class="ni_d1">
						<a class="ni_off mgr20" href="javascript:void(0)" id="shutdown"><span>关机</span></a>
						<a class="ni_res " href="javascript:void(0)" id="restsrt"><span>重启</span></a>
					</div>
					<div class="ni_d_lr clearfix mgt20">
						
					</div>
					<div class="ni_d_lr clearfix mgt20 mgb30">
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="ni_g_pop_txt_udp dis_none pdb20">
	<div class="ni_tit pdtb5">输入广播消息</div>
	<textarea class="inp" style="width:80%;height:120px;margin:0 auto;display:block;" name="sendMessage" id="sendMessage"></textarea>
	
	<div class="ni_d_lr radiolist mgt15">
		<span>连续播放次数：</span>
		<span>连续时间：</span>
		<input type="text" class="" id="delayTime" name="delayTime" style="width:40px;" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
		秒
	</div>
</div>

<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        <div class="wql_on_off t_c">
            <em></em><span>关机后，终端屏幕将倒数10秒后自动关机，是否确定关机？</span>
        </div>
    </div>
</div>

<div class="ni_g_pop_show_imgs_list dis_none">
	<div class="ni_img_box">
		<div class="ni_prev"></div>
		<div class="ni_next"></div>
		<div class="ni_list">
			<ul class="ni_ul">
			</ul>
		</div>
		<div class="ni_del clearfix mgt25">
			<span class="fl ni_time">时间<em></em></span>
			<span class="fr ni_num">第<em class="ni_now">1</em>张，共<em class="ni_all">10</em>张</span>
		</div>
		
	</div>
</div>

<input type="hidden" name="currPage" id="currPage" value="1">
<input type="hidden" name="totalPage" id="totalPage">
<input type="hidden" name="pageSize" id="pageSize" value="10">

<input type="hidden" name="isLock" id="isLock" value="${isLock}">

<script type="text/javascript">

function queryClass2(schoolId){
	var htmlStr = '<a href="#" class="active" value="-1">请选择</a>';
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/school/querySchoolClass",
		data : {"schoolId" : schoolId},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null && data.list != null && data.list.length > 0){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i]
					htmlStr += '<a href="#" value="'+obj.id+'">'+obj.className+'</a>';
				}
			}
		}
	});
	return htmlStr;
}

function querySchool2(areaCode){
	var htmlStr = '<a href="#" class="active" value="-1">请选择</a>';
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/school/querySchoolByCode",
		data : {"code" : areaCode},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null && data.list != null && data.list.length > 0){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i];
					htmlStr += '<a href="#" value="'+obj.id+'">'+obj.name+'</a>';
				}
			}
		}
	});
	return htmlStr;
}

function queryArea2(levelId,parentId){
	var htmlStr = '<a href="#" class="active" value="">请选择</a>';
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/area/queryArea",
		data : {"levelId" : levelId,"parentId" : parentId},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null && data.list != null && data.list.length > 0){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i];
					htmlStr += '<a href="#" value="'+obj.area_code+'">'+obj.area_name+'</a>';
				}
			}
		}
	});
	return htmlStr;
}



function screenMonitor(deviceId){
	var dialog = art.dialog({
        title: '截图',
        content: $('.ni_g_pop_show_imgs_list')[0],   //弹出框的内容
        width: '870x',
        padding:0,
        initialize:function(){
        	
        	$.ajax({
        		type : "POST",
        		url : "${ctx}/manage/monitor/getAllMonitor",
        		data : {
        			"deviceId" : deviceId
        		},
        		async : false,
        		dataType : 'json',
        		success : function(data) {
        			
        			var htmlStr = '';
        			if(data != null){
        				if(data.list != null && data.list.length > 0){
        					dataArray = data.list;

        					dia_imgs = new showImgList();
                			dia_imgs.init(".ni_img_box",".ni_ul",".ni_next",".ni_prev",dataArray)
        				}
        			}
        		}
        	});
        },
    });
}

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
				var $li = $("<li class='ni_li'><img src='"+this.imgs[i].path+"'/></li>")
				this.el_img_box.append($li);
			}
			//console.log(imgs[0].createTime)
			//console.log(new Date(this.imgs[0].createTime.time).format("yyyy-MM-dd hh:mm:ss"));
			this.el_time.html(new Date(this.imgs[0].createTime.time).format("yyyy-MM-dd hh:mm:ss"));
			
			//console.log(this.el_time.html());
			
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
			
			this.el_time.html(new Date(this.imgs[num-1].createTime.time).format("yyyy-MM-dd hh:mm:ss"));
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



function deviceDetail(deviceId){
	window.location.href="deviceDetail?deviceId=" + deviceId;
}

var deviceId='';
var schoolId='';
var areaCode='';
var cityCode='';
var provinceCode='';

var brandName = '';
var modelName = '';

var shutdown = 1
var restart = 0;

var optSheet = 1;



$(document).on('click','#shutdown',function(){
	$('#shutdown').removeClass("ni_res ni_off");
	$('#restsrt').removeClass("ni_res ni_off");

	$('#shutdown').addClass("ni_off");
	$('#restsrt').addClass("ni_res");
	shutdown = 1;
	restart = 0;
})

$(document).on('click','#restsrt',function(){
	$('#shutdown').removeClass("ni_res ni_off");
	$('#restsrt').removeClass("ni_res ni_off");

	$('#shutdown').addClass("ni_res");
	$('#restsrt').addClass("ni_off");
	shutdown = 0;
	restart = 1;
})

var $ckAll = $("input[name='ckAll']");

$ckAll.bind("click",function() {
	$("input[name='ckItm']").prop("checked",this.checked);
});

$("input[name='ckItm']").live("click",function() {
    var b=$("input[name='ckItm']").filter(":checked").length==$("input[name='ckItm']").length;
    var flag=$ckAll.prop("checked",b?true:false);
});


$(".wql_btn_search").on("click",function(){
	
	$('input[name="provinceCode"]').val($('span[name="provinceSpan"]').find('.selRes').val())
	$('input[name="cityCode"]').val($('span[name="citySpan"]').find('.selRes').val())
	$('input[name="areaCode"]').val($('span[name="areaSpan"]').find('.selRes').val())
	
	$("#pageForm").submit();
})

function terminalMonitor(id){
	deviceId = id;//设置设备id
	
	//$("#currentImg").attr("src","");
	
	$("#monitorImages").html('');
	var dialog = art.dialog({
        title:'终端屏幕',
        content:$(".wql_g_artDialog")[0],
        width:'560px',
        padding:'0'
    });
	
}


//登录浮层
$(function(){
	
	$('span[name="provinceSpan"]').siblings('.sys_seleautodrop').html('<a href="#" class="active" value="">请选择</a>');
	$('span[name="citySpan"]').siblings('.sys_seleautodrop').html('<a href="#" class="active" value="">请选择</a>');
	$('span[name="areaSpan"]').siblings('.sys_seleautodrop').html('<a href="#" class="active" value="">请选择</a>');

	$('span[name="province"]').html(queryArea2(1,0))
	
	var provinceCode = '${provinceCode}';
	var provinceList = ${provinceList};

	
	
	
	
	if(provinceCode!=''){
		for(var i=0;i<provinceList.length;i++){
			var obj = provinceList[i];
			
			if(obj.area_code==provinceCode){
				var provinceA = $('span[name="provinceSpan"]').siblings('.sys_seleautodrop').find("a");
				provinceA.each(function(){ //便利除标题行外所有行
					$(this).removeClass("active");
		    	});
				$('span[name="provinceSpan"]').siblings('.sys_seleautodrop').append('<a href="#" class="active" value="'+obj.area_code+'">'+obj.area_name+'</a>');
			}
		}
	}
	
	
	<c:if test="${not empty cityList}">
	var cityCode = '${cityCode}';
	var cityList = ${cityList} ;
	
	if(cityCode != ''){
		var htmlStr = '<a href="#" class="" value="">请选择</a>';
		for(var i=0;i<cityList.length;i++){
			var obj = cityList[i];
			if(obj.area_code==cityCode){
				htmlStr += '<a href="#" class="active" value="'+obj.area_code+'">'+obj.area_name+'</a>';
			}else{
				htmlStr += '<a href="#" class="" value="'+obj.area_code+'">'+obj.area_name+'</a>';
			}
		}
		$('span[name="citySpan"]').siblings('.sys_seleautodrop').html(htmlStr);
	}
	</c:if>
	
	<c:if test="${not empty areaList}">
	var areaCode = '${areaCode}';
	var areaList = ${areaList} ;
	
	if(areaCode != ''){
		var htmlStr = '<a href="#" class="" value="">请选择</a>';
		for(var i=0;i<areaList.length;i++){
			var obj = areaList[i];
			if(obj.area_code==areaCode){
				htmlStr += '<a href="#" class="active" value="'+obj.area_code+'">'+obj.area_name+'</a>';
			}else{
				htmlStr += '<a href="#" class="" value="'+obj.area_code+'">'+obj.area_name+'</a>';
			}
		}
		$('span[name="areaSpan"]').siblings('.sys_seleautodrop').html(htmlStr);
	}
	</c:if>
	
	
	
	$('span[name="citySpan"]').on("click",function(){
		var provinceCode = $('span[name="provinceSpan"]').find('.selRes').val();
		$(this).siblings('.sys_seleautodrop').html(queryArea2(2,provinceCode));
	})
	
	$('span[name="areaSpan"]').on("click",function(){
		var cityCode = $('span[name="citySpan"]').find('.selRes').val();
		$(this).siblings('.sys_seleautodrop').html(queryArea2(3,cityCode));
	})
	
    $('.chklist2').hcheckboxnew2();
    
    
    $(".sys_seleautodiv").sysSeleautoBox();

    
    var content_h = $('.wql_g_content').outerHeight();
    function oneScreen(){
        var win_h = $(window).height();
        
        var header_h = $('.g_syshead').outerHeight();
        var footer_h = $('.g_sysfooter').outerHeight();
        var mainWrap_h = win_h - header_h - footer_h;
        //console.log(win_h+' '+header_h+' '+footer_h+' '+content_h)
        if(content_h<mainWrap_h){
            $('.wql_g_content').height(mainWrap_h-30);
        }
        else{
            $('.wql_g_content').height(content_h-30);
        }
        $('.wql_mainBox').css('minHeight',mainWrap_h-135);
    };
    oneScreen();
    $(window).on('resize',function(){
        oneScreen();
    });

    $(".wql_g_sideNav02 .wql_dd").on('click',function(){
        var dl = $(this).parents('.wql_dl');
        // dl.find(".wql_dt").addClass('on').siblings().removeClass('on');
        $('.wql_g_sideNav02 .wql_dd').removeClass('active');
        $(this).addClass('active');
        $('.wql_g_sideNav02 .wql_dt').removeClass('on');
        dl.find(".wql_dt").addClass('on');
    })

    $(".power").on('click',function(){
    	
    	optSheet=1;
    	
    	$(".lock").attr("class","lock");
    	
    	$(this).attr("class","power hover");
    	
    	var nid1 = $("div[class='ni_d1']");
    	
    	nid1.children().remove();
    	
    	var html='<a class="ni_off mgr20" href="javascript:void(0)" id="shutdown"><span>关机</span></a><a class="ni_res " href="javascript:void(0)" id="restsrt"><span>重启</span></a>';
    	
    	nid1.html(html);
    })
    
    $(".lock").on('click',function(){
    	optSheet=2;
    	
    	$(".power").attr("class","power");
    	
    	$(this).attr("class","lock hover");
		
		var nid1 = $("div[class='ni_d1']");
    	
    	nid1.children().remove();
    	
    	var html='<a class="mgr20" href="javascript:void(0)" id="lockScreen"><span>锁屏</span></a>';
    	
    	nid1.html(html);
    })
    
    
    $(".wql_g_sideNav02 dt").on("click",function(){
        var that = $(this);
        var ico = that.find('i');
        if(ico.hasClass('wql_upico')){
            ico.removeClass('wql_upico').addClass('wql_downico');
            that.nextUntil('dt').hide();
        }
        else{
            ico.removeClass('wql_downico').addClass('wql_upico');
            that.nextUntil('dt').show();
        }
    })

    
 	$('.wql_btn.update_config').on('click',function(){
    	
    	myConfirm('系统确认框','是否发送更新配置命令！',function(r){  
    		if(r){ 
    			$.ajax({
    				type : "post",
    				async : true,
    				url : "../monitor/updateConfig",
    				data : {
    					deviceId : deviceId
    				},
    				dataType : "text",
    				success : function(msg) {
    					var n = noty({
    					    text        : '指令已发送',
    					    type        : 'success',
    					    dismissQueue: true,
    					    timeout     : 10000,
    					    closeWith   : ['click'],
    					    layout      : 'topCenter',
    					    theme       : 'defaultTheme',
    					    maxVisible  : 10
    					});
    				},
    				error : function(errorMsg) {
    				}
    			})
    		}
		}); 	
    });
    
    
    $('.wql_btn.upload_app_info').on('click',function(){
    	myConfirm('系统确认框','是否发送上报配置命令！',function(r){  
    		if(r){ 
    			$.ajax({
    				type : "post",
    				async : true,
    				url : "../monitor/uploadAppInfo",
    				data : {
    					deviceId : deviceId
    				},
    				dataType : "text",
    				success : function(msg) {
    					var n = noty({
    					    text        : '指令已发送',
    					    type        : 'success',
    					    dismissQueue: true,
    					    timeout     : 10000,
    					    closeWith   : ['click'],
    					    layout      : 'topCenter',
    					    theme       : 'defaultTheme',
    					    maxVisible  : 10
    					});
    				},
    				error : function(errorMsg) {
    				}
    			})
    		}
    	}); 
    });
    
    
$('.wql_g_listimg').on('click','.wql_li',function(){
    	$("#currentImg").attr("src", $(this).find("img").attr("src"));
        $(this).addClass('active').siblings().removeClass('active')
    })
    
    $('.wql_btn.un_lock_screen').on('click',function(){
		myConfirm('系统确认框','是否锁屏命令！',function(r){
			if(r){ 
				$.ajax({
					type : "post",
					async : true,
					url : "../monitor/lockUnScreen",
					data : {
						deviceId : deviceId
					},
					dataType : "text",
					success : function(msg) {
						var n = noty({
						    text        : '指令已发送',
						    type        : 'success',
						    dismissQueue: true,
						    timeout     : 10000,
						    closeWith   : ['click'],
						    layout      : 'topCenter',
						    theme       : 'defaultTheme',
						    maxVisible  : 10
						});
					},
					error : function(errorMsg) {
					}
				})
			}
		}); 
});
    
      $('.wql_btn.shut_down').on('click',function(){
			myConfirm('系统确认框','是否发送关机命令！',function(r){  
				if(r){ 
					$.ajax({
						type : "post",
						async : true,
						url : "../monitor/shutDown",
						data : {
							deviceId : deviceId
						},
						dataType : "text",
						success : function(msg) {
							var n = noty({
							    text        : '指令已发送',
							    type        : 'success',
							    dismissQueue: true,
							    timeout     : 10000,
							    closeWith   : ['click'],
							    layout      : 'topCenter',
							    theme       : 'defaultTheme',
							    maxVisible  : 10
							});
						},
						error : function(errorMsg) {
						}
					})
				}
		}); 
    });
    
    $('.wql_btn.restart').on('click',function(){
    	myConfirm('系统确认框','是否发送重启命令！',function(r){  
            if(r){  
            	$.ajax({
    				type : "post",
    				async : true,
    				url : "../monitor/restart",
    				data : {
    					deviceId : deviceId
    				},
    				dataType : "text",
    				success : function(msg) {
    					var n = noty({
						    text        : '指令已发送',
						    type        : 'success',
						    dismissQueue: true,
						    timeout     : 10000,
						    closeWith   : ['click'],
						    layout      : 'topCenter',
						    theme       : 'defaultTheme',
						    maxVisible  : 10
						});
    				},
    				error : function(errorMsg) {
    				}
    			})
            }  
       }); 

    });
    
     $('.wql_btn.screen_shot').on('click',function(){
    	 myConfirm('系统确认框','是否发送截屏命令！',function(r){ 
    		 if(r){  
    			 $.ajax({
    	 				type : "post",
    	 				async : true,
    	 				url : "../monitor/screenShot",
    	 				data : {
    	 					deviceId : deviceId
    	 				},
    	 				dataType : "text",
    	 				success : function(msg) {
    	 					var n = noty({
    						    text        : '指令已发送',
    						    type        : 'success',
    						    dismissQueue: true,
    						    timeout     : 10000,
    						    closeWith   : ['click'],
    						    layout      : 'topCenter',
    						    theme       : 'defaultTheme',
    						    maxVisible  : 10
    						});
    	 				},
    	 				error : function(errorMsg) {
    	 				}
    	 			})
    		 }
    		
		}); 
    });
    
     
     $('.wql_btn.lock_screen').on('click',function(){
    	 //myConfirm('系统确认框','是否重启！',function(r){  
    		 $.ajax({
 				type : "post",
 				async : true,
 				url : "../monitor/lockScreen",
 				data : {
 					deviceId : deviceId
 				},
 				dataType : "text",
 				success : function(msg) {
 					//console.log(msg)
 					//alert(msg)
 				},
 				error : function(errorMsg) {
 				}
 			})
		//}); 
    });
     
    
     $('.updateConfig').on('click',function(){
    	 myConfirm('系统确认框','是否发送命令！',function(r){
    		 if(r){  
    			 $.ajax({
    	    			type : "POST",
    	    			url : "${ctx}/manage/monitor/sendDeviceConfig",
    	    			async : false,
    	    			dataType : 'json',
    	    			success : function(data) {
    	    				
    	    			}
    	    	});
    		 }
    	 })
     })
     
    $('.wql_btn_more').on('click',function(){
        $(this).hasClass('active') ? $(this).removeClass('active'):$(this).addClass('active');
        $('.wql_zkbox').toggle();
        var h = $('.wql_zkbox').outerHeight(true)
        $('.wql_artDialog02').scrollTop(h)

    })
    $('.wql_g_listimg .wql_li').on('click',function(){
        $(this).addClass('active').siblings().removeClass('active')
    })
    
    $('.controller').on('click', function() { // 带取消，确定按钮的
    	var deviceCodes = getCheckValue();
    	console.log(deviceCodes)
    	var n=deviceCodes.split(","); 
    	
    	var deviceNum=0
    	if(n!=""){
    		deviceNum = n.length;
    	}
    	console.log(deviceNum)
    	var dialog = art.dialog({
    		title : '终端管控(已经选择'+deviceNum+'台设备)',
    		content : $(".ni_g_pop_zdgk")[0],
    		width : '500px',
    		ok : function() {
    			
    			var url ="../monitor/";
    			if(optSheet==1){
        			if (shutdown == 1) {
        				url += "batchShutdown";
        			} else {
        				url += "batchRestart";
        			}
    				
    			}else if(optSheet==2){
    				url +='batchLockScreen'
    			}
    			 myConfirm('系统确认框','是否发送命令！',function(r){
    				 if(r){  
    					 $.ajax({
    		    				type : "post",
    		    				async : true,
    		    				url : url,
    		    				data : {
    		    					ids : deviceCodes
    		    				},
    		    				dataType : "text",
    		    				success : function(result) {
    		    					var n = noty({
    								    text        : '指令已发送',
    								    type        : 'success',
    								    dismissQueue: true,
    								    timeout     : 10000,
    								    closeWith   : ['click'],
    								    layout      : 'center',
    								    theme       : 'defaultTheme',
    								    maxVisible  : 10
    								});
    		    				},
    		    				error : function(errorMsg) {
    		    				}
    		    			})
    				 }
    			 })
    				 
    			
    		},
    		padding : 0,
    		cancelVal : '关闭',
    		cancel : true,
    		maskClick : true
    	});
    });
    
    $('.sendMessage').on('click', function() { // 甯﹀彇娑堬紝纭畾鎸夐挳鐨�
    	var dialog = art.dialog({
    		title : '文字广播消息',
    		content : $(".ni_g_pop_txt_udp")[0],
    		width : '500px',
    		ok : function() {
    			var sendText = $("#sendMessage").val();
    			var delayTime = $("#delayTime").val();
				
    			var deviceCodes = getCheckValue();
    			myConfirm('系统确认框','是否重启！',function(r){
    				if(r){
    					$.ajax({
    	    				type : "post",
    	    				async : true,
    	    				url : "../monitor/batchSendMessage",
    	    				data : {
    	    					sendText : sendText,
    	    					excuteTime : delayTime,
    	    					ids : deviceCodes
    	    				},
    	    				dataType : "json",
    	    				success : function(result) {
    	    					var n = noty({
								    text        : '指令已发送',
								    type        : 'success',
								    dismissQueue: true,
								    timeout     : 10000,
								    closeWith   : ['click'],
								    layout      : 'center',
								    theme       : 'defaultTheme',
								    maxVisible  : 10
								});
    	    					this.close();
    	    				},
    	    				error : function(errorMsg) {

    	    				}
    	    			})
    				}
    			})
    			
    		},
    		padding : 0,
    		cancelVal : '鍏抽棴',
    		cancel : true,
    		maskClick : true
    	});
    });
})

function getCheckValue() {
	var ids = "";
	
 	$('.sys_checkbox.sys_checked').each(function(){                   // 遍
 		
    	  var id = $(this).find("input[name='chkItm']").val();
		
    	  if (typeof(id) != "undefined") { 	
    		  ids = ids +","+id;
    	   }
    	  
  	}); 	
	if(ids != ""){
		return ids.substring(1);
	}
	return "";
}


var isLock =$("input[name='isLock']").val();

if(isLock==2){
	$("span[name='province_sys_seleautodiv']").addClass("wql_disable");
	$("span[name='city_sys_seleautodiv']").addClass("wql_disable");
	$("span[name='area_sys_seleautodiv']").addClass("wql_disable");
}





</script>
