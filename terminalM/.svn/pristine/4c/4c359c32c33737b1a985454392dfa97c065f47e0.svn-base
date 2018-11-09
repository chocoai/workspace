<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev"><a href="" class="mgr5">终端设备</a>&gt;</span><span class="on mgl5">智能平板管理</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="clearfix wql_g_mainBox "> 
      
            <form id="pageForm"  name="pageForm" action="${ctx}/manage/ebookpackage/list" method="post">
            
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
					                    <span class="sys_seleautocur" name="provinceSpan">
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
                            <div class="wql_li_box">
                                <a href="javascript:;" class="wql_btn_search">查询</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="wql_g_tit">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">智能平板信息列表</h3>
                        <div class="wql_g_btn mgt8 fr">
                            <div class="wql_btn01">
                               <a href="#" class="wql_addClass02 fl controller">终端监控</a>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div class="site_sysTable wql_g_table mgt15">
                    <table class="wql_table01 t_l chklist2">
                        <thead>
                            <tr>
                            	<th><label class="chkAll"><input type="checkbox" name="chkAll"/></label></th>
                                <th width="">设备名称</th>
                                <th width="">品牌</th>
                                <th width="">型号</th>
                                <th width="">登记学校</th>
                                <th width="">最近使用班级</th>
                                <th width="">最近使用姓名</th>
                                <th width="">最近登录时间</th>
                                <th width="">状态</th>
                                <th width="">操作</th>
                            </tr>
                        </thead>
                        <tbody >
                        	<c:forEach items="${list}" var="obj" varStatus="status">
                        		<tr>
                        			<td>
                        			<label class=""><input type="checkbox" value='${obj.id}' name="chkItm"/></label>
                        			</td>
                        			 <td>${obj.deviceName}</td>
		                             <td>${obj.brandName}</td>
		                             <td>${obj.modelName}</td>
		                             <td>${obj.schoolName}</td>
		                             <td>${obj.className}</td>
		                             <td>${obj.userName}</td>
		                           
		                             <td><fmt:formatDate value="${obj.loginTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		                          	<td>
		                             <c:if test="${obj.onLineType==0 }">
                        				在线
                        			</c:if>
                        			<c:if test="${obj.onLineType==1 }">
                        				离线
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
    
    </div>
</div>




<!-- 弹窗 S-->
<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog02">
        <div class="pdlr40 pdt30">
            <img width="450px" id="currentImg" height="300px" src="">
            <div class="wql_g_btn mgtb25">
                <div class="wql_btn05 t_c">
                 	<!-- <a href="javascript:;" class="wql_btn mgl10 mgr20 wql_borblue upload_app_info">上传应用</a> -->
                    <a href="javascript:;" class="wql_btn mgl10 mgr20 wql_borblue lock_screen">锁屏</a>
                    <a href="javascript:;" class="wql_btn mgl10 mgr20 wql_borblue un_lock_screen">解锁</a>
                    <a href="javascript:;" class="wql_btn mgl10 mgr20 wql_borblue screen_shot">刷新</a>
                </div>
            </div>
        </div>
        <div class="pdt20 pdb30 bt t_c">
            <span class="c888 lh20 f16 wql_zk">展开截屏记录</span>
            <a href="javascript:;" class="wql_btn_more"></a>
        </div>
        <div class="dis_none wql_zkbox pdb30">
            <div class="wql_g_listimg mglr35">
                <ul class="wql_ul clearfix" id="monitorImages">
                    
                </ul>
            </div>
             <div class="base_page clearfix" id="base_page" onselectstart="return false"></div>
            <div class="mgt30">
               
            </div>
        </div>
    </div>
</div>

<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        <div class="wql_on_off t_c">
            <em></em><span>确定锁屏？</span>
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


function deviceDetail(deviceId){
	window.location.href="deviceDetail?deviceId=" + deviceId;
}


function queryArea(levelId,parentId){
	var htmlStr = '<option value="">全部</option>';
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
					htmlStr += '<option value="'+obj.area_code+'">'+obj.area_name+'</option>';
				}
			}
		}
	});
	return htmlStr;
}

var deviceId='';

var schoolId='';
var areaCode='';
var cityCode='';
var provinceCode='';

var brandName = '';
var modelName = '';

var shutdown = 1;
var restart = 0;

var lockScreen = 1;
var unLockScreen = 0;

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


$(document).on('click','#lockScreen',function(){
	$('#lockScreen').removeClass("ni_res ni_off");
	$('#unLockScreen').removeClass("ni_res ni_off");

	$('#lockScreen').addClass("ni_off");
	$('#unLockScreen').addClass("ni_res");
	lockScreen = 1;
	unLockScreen = 0;
})

$(document).on('click','#unLockScreen',function(){
	$('#lockScreen').removeClass("ni_res ni_off");
	$('#unLockScreen').removeClass("ni_res ni_off");

	$('#lockScreen').addClass("ni_res");
	$('#unLockScreen').addClass("ni_off");
	lockScreen = 0;
	unLockScreen = 1;
})


$(".wql_btn_search").on("click",function(){
	
    modelName = $("input[name='modelName']").val();
	brandName =  $("input[name='brandName']").val();
	
	$("#pageForm").submit();
})



function terminalMonitor(id){
	deviceId = id;//设置设备id
	
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
        console.log(win_h+' '+header_h+' '+footer_h+' '+content_h)
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
    	
    	var html='<a class="ni_off mgr20" href="javascript:void(0)" id="lockScreen"><span>锁屏</span><a class="ni_res " href="javascript:void(0)" id="unLockScreen"><span>解锁</span></a>';
    	
    	nid1.html(html);
    })

$('.wql_btn.upload_app_info').on('click',function(){
    	
    	//myConfirm('系统确认框','是否上传应用！',function(r){  
    		$.ajax({
				type : "post",
				async : true,
				url : "../monitor/uploadAppInfo",
				data : {
					deviceId : deviceId
				},
				dataType : "text",
				success : function(msg) {
					//alert(msg)
				},
				error : function(errorMsg) {
				}
			})
		//}); 
    	
    	
    });
    
$('.wql_btn.un_lock_screen').on('click',function(){
	//myConfirm('系统确认框','是否关机！',function(r){  
		$.ajax({
			type : "post",
			async : true,
			url : "../monitor/lockUnScreen",
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
    
      $('.wql_btn.shut_down').on('click',function(){
			//myConfirm('系统确认框','是否关机！',function(r){  
				$.ajax({
					type : "post",
					async : true,
					url : "../monitor/shutDown",
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
    
    $('.wql_btn.restart').on('click',function(){
    	//myConfirm('系统确认框','是否重启！',function(r){  
           // if(r){  
            	$.ajax({
    				type : "post",
    				async : true,
    				url : "../monitor/restart",
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
           // }  
       //}); 

    });
    
     $('.wql_btn.screen_shot').on('click',function(){
    	 //myConfirm('系统确认框','是否重启！',function(r){  
    		 $.ajax({
 				type : "post",
 				async : true,
 				url : "../monitor/updateImage",
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
    
   
    $('.wql_btn_more').on('click',function(){
        $(this).hasClass('active') ? $(this).removeClass('active'):$(this).addClass('active');
        $('.wql_zkbox').toggle();
        var h = $('.wql_zkbox').outerHeight(true)
        console.log(h)
        $('.wql_artDialog02').scrollTop(h)

    })
    
     $('.wql_g_listimg').on('click','.wql_li',function(){
    	
    	$("#currentImg").attr("src", $(this).find("img").attr("src"));
    	
        //$(this).addClass('active').siblings().removeClass('active')
        $(this).addClass('active').siblings().removeClass('active')
    })
    
    $('.wql_g_listimg .wql_li').on('click',function(){
    	console.log(123)
        
    })
    
    $('.controller').on('click', function() { // 带取消，确定按钮的
    	
    	var deviceCodes = getCheckValue();
    	   
    	var n=deviceCodes.split(","); 
    	
    	var deviceNum=0
    	if(n!=""){
    		deviceNum = n.length;
    	}
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
    				
    			} else if(optSheet==2){
    				if(lockScreen==1){
    					url +='batchLockScreen';
    				} else {
    					url +='batchUnLockScreen';
    				}
    			}
    			
    			
    			$.ajax({
    				type : "post",
    				async : true,
    				url : url,
    				data : {
    					ids : deviceCodes
    				},
    				dataType : "json",
    				success : function(result) {
    					
    				},
    				error : function(errorMsg) {

    				}
    			})
    		},
    		padding : 0,
    		cancelVal : '关闭',
    		cancel : true,
    		maskClick : true
    	});
    });
})

function getCheckValue() {
	var ids = "";
	
 	$('.sys_checkbox.sys_checked').each(function(){                   // 遍历
    	  var id = $(this).find("input[name='chkItm']").val();
 			
    	  if (typeof(id) != "undefined") { 
    		  console.log(id)
    		  ids += ","+id;
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