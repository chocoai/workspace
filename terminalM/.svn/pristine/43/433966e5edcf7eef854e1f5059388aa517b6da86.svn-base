<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
    	
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">业务管理 &gt;</span><span class="on mgl5">订单管理</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <form id="pageForm"  name="pageForm" action="${ctx}/manage/order/list" method="post">
        
        <input type="hidden" name="provinceCode" value="${provinceCode}">
        <input type="hidden" name="cityCode" value="${cityCode}">
        <input type="hidden" name="areaCode" value="${areaCode}">
        
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
            <div class="wql_g_tit">
                <div class="wql_tit01 clearfix">
                    <h3 class="fl">查询条件</h3>
                </div>
                
            </div>
            <div class="wql_g_list02">
                <ul class="wql_ul clearfix mgt25">
                	
                	<li class="wql_li mgr30">地区:
	                   		<span class="sys_seleautodiv mgr10" style="width: 120px; z-index: 3;">
			                    <span class="sys_seleautocur" name="provinceSpan">
			                        <p>请选择</p>
			                        <input type="hidden" class="selRes" value="-1">
			                    </span>
			                    <span class="sys_seleautodrop animate" name="province" style="width: 120px; display: none; z-index: 9999;">
			                    				                    	
			                    </span>
			                </span>
			                <span class="sys_seleautodiv mgr10"  style="width: 120px; z-index: 3;">
			                    <span class="sys_seleautocur" name="citySpan">
			                        <p name="cityP">请选择</p>
			                        <input type="hidden" class="selRes" value="-11">
			                    </span>
			                    <span class="sys_seleautodrop animate"  style="width: 120px; display: none; z-index: 9999;">
			                    
			                    </span>
			                </span>
			                <span class="sys_seleautodiv mgr10"  style="width: 120px; z-index: 3;">
			                    <span class="sys_seleautocur" name="areaSpan">
			                        <p>请选择</p>
			                        <input type="hidden" class="selRes" value="-1">
			                    </span>
			                    <span class="sys_seleautodrop animate"  style="width: 120px; display: none; z-index: 9999;">
			                    	
			                    </span>
			                </span>
                    </li>
                    <li class="wql_li mgr30">
                        <div class="wql_li_box">
                            <span>订单编号：</span>
                            <input type="text" class="site_sysinp" name="orderNumber" value="${orderNumber}" style="width:140px;">
                        </div>
                    </li>
                    <li class="wql_li">
                        <div class="wql_li_box">
                            <span>合同编号：</span>
                            <input type="text" class="site_sysinp" name="contactNumber" value="${contactNumber}" style="width:140px;">
                            <a href="javascript:;" class="wql_btn_search">查询</a>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="wql_g_tit ">
                <div class="wql_tit01 clearfix">
                    <h3 class="fl">订单信息列表</h3>
                    <div class="wql_g_btn mgt8 fr">
                        <div class="wql_btn01 clearfix">
                             <a href="addPage" class="wql_addClass02 fl mgr15">添加订单</a>
                        </div>
                    </div>
                </div>
                
            </div>
            <div class="site_sysTable wql_g_table mgt15">
                <table class="wql_table01 t_l">
                    <thead>
                        <tr>
                            <th class="pdl15">地区</th>
                            <th>订单编号</th>
                            <th>合同编号</th>
                            <th>设备数量</th>
                            <th>订单日期</th>
                            <th>实施数量</th>       
                            <th>订单状态</th>       
                            <th width="165">操作</th>                                     
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${list}" var="obj" varStatus="status">
                       	 	<tr>
	                        	<td>
	                        		<span class="pdl15">  
										<c:if test="${not empty obj.provinceName }">
											${obj.provinceName}
										</c:if>
										<c:if test="${not empty obj.cityName }">
											/${obj.cityName}
										</c:if>
										<c:if test="${not empty obj.areaName }">
											/${obj.areaName}
										</c:if>
									</span>
	                        	</td>
	                            <td><span>${obj.orderNumber }</span></td>
	                            <td><span>${obj.contractNumber }</span></td>
	                            <td><span>${obj.deviceNum }</span></td>
	                            <td><span><fmt:formatDate value="${obj.orderTime }" pattern="yyyy-MM-dd"/></span></td>
	                          
	                            <td><span>${obj.carryNum }</span></td>
	                            
	                            <td>
	                            	<c:if test="${obj.status ==1 }">
	                            		<span class="ni_off">生成设备中</span>
	                            	</c:if>
	                            	<c:if test="${obj.status ==2 }">
	                            		<span class="ni_on">生成设备完成</span>
	                            	</c:if>
	                            </td> 
	                            <td>
	                            	<span>
	                            		<a href="javascript:void(0)" onclick="deleteOrder('${obj.id}')" class="mgr30">删除订单</a>
	                            		
	                            		<c:if test="${obj.status ==2 }">
	                            			<a href="setBuildSchoolPage?orderId=${obj.id}" class="">实施区域</a>
	                            		</c:if>
	                            	</span>
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


$(".wql_btn_search").on("click",function(){
    
	$('input[name="provinceCode"]').val($('span[name="provinceSpan"]').find('.selRes').val())
	$('input[name="cityCode"]').val($('span[name="citySpan"]').find('.selRes').val())
	$('input[name="areaCode"]').val($('span[name="areaSpan"]').find('.selRes').val())
	
	$("#pageForm").submit();
})

function applyAliDevice(id,status){
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/order/createDevice",
		data : {
			"orderId"  : id
		},
		async : false,
		dataType : 'text',
		success : function(msg) {
			if(msg=='success'){
				myAlert('系统确认框','申领成功！',function(r){  
					$("#pageForm").submit();
				});
			}else{
				myAlert('系统确认框',msg,function(r){  
	    	          
		    	});
			}
		}
	});
}

function deleteOrder(id){
	myConfirm('系统确认框','是否删除！',function(r){  
        if(r){  
        	$.ajax({
    			type : "POST",
    			url : "${ctx}/manage/order/deleteOrder",
    			data : {
    				"id"  : id
    			},
    			async : false,
    			dataType : 'text',
    			success : function(msg) {
    				if(msg=='success'){
    					$("#pageForm").submit();
    				}else{
    					myAlert('系统确认框',msg,function(r){  
    		    	          
    			    	});
    				}
    			}
    		});
        }  
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
	
    //复选框
    $('.chklist2').hcheckboxnew2(); 

    $('.radiolist2').hradio2()
	
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
})



</script>