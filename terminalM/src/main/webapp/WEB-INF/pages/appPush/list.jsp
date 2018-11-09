<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">应用商城 &gt;</span><span class="on mgl5"> 终端应用管控</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <form id="pageForm"  name="pageForm" action="${ctx}/manage/appPush/list" method="post">
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">

            <div class="wql_g_tit ">
                <div class="wql_tit01 ">
                    <h3 class="">查询条件</h3>
                </div>
            </div>
            <div class="wql_g_list02">
                <ul class="wql_ul clearfix mgt25">
                    <li class="wql_li mgr30">
                        <div class="wql_li_box">
                            <span class="mgr10">应用程序名称：</span><input type="text" value="${appName}" name="appName" class="site_sysinp" style="width:200px;">
                        </div>
                    </li>
                    <li class="wql_li mgl30">
                        <div class="wql_li_box">
                            <span class="mgr10">推送时间：</span>
                            <div class="seleautoBox mgb10 fr">
                                <input id="d4311" class="Wdate site_sysinp wd140"  name="startTime" value="${startTime}" type="text" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'%y-%M-%d'})">
                                - <input id="d4312" class="Wdate site_sysinp wd140"  name="endTime" value="${endTime}" type="text" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'%y-%M-%d'})">
                            </div>
                        </div>
                    </li>
                    <li class="wql_li">
                            <div class="wql_li_box mgl15">
                                <a href="javascript:;" class="wql_btn_search">查询</a>
                            </div>
                    </li>
                </ul>
            </div>
            <div class="wql_g_tit ">
                <div class="wql_tit01 clearfix">
                    <h3 class="fl">应用程序推送记录列表</h3>
                    <div class="wql_g_btn mgt8 fr">
                        <div class="wql_btn01">
                            <a href="addPage" >创建推送</a>
                        </div>
                    </div>
                </div> 
            </div>
            <div class="site_sysTable wql_g_table mgt15">
                <table class="wql_table01 t_l">
                    <thead>
                        <tr>
                            <th class="pdl15">应用</th>
                            <th>推送时间</th>
                            <th>推送人</th>
                            <th>推送设备数</th>
                            <th>推送成功数</th>
                            <th>推送地点</th> 
                            <th>状态</th>             
                            <th width="165">操作</th>                                     
                        </tr>
                    </thead>
                    
                    <tbody>
                    	<c:forEach items="${list}" var="obj" varStatus="status">
	                    	<tr>
	                            <td><span class="pdl15">${obj.appName }[${obj.appVersion }]</span></td>
	                            <td><span><fmt:formatDate value="${obj.pushTime }" pattern="yyyy-MM-dd" /></span></td>
	                            <td><span>${obj.creatorName }</span></td>
	                            <td><span>${obj.pushDeviceQuan}</span></td>
	                            <td><span>${obj.pushDeviceNum}</span></td>
	                            <td><span>
	                            <c:if test="${not empty obj.provinceName }">
	                           	 	${obj.provinceName}
	                            </c:if>
	                            
	                            <c:if test="${not empty obj.cityName }">
	                           	 	/${obj.cityName}
	                            </c:if>
	                            
	                            <c:if test="${not empty obj.areaName }">
	                           	 	/${obj.provinceName}
	                            </c:if>
	                            
	                            <c:if test="${not empty obj.schoolName }">
	                           	 	/${obj.schoolName}
	                            </c:if>
	                            
	                            <c:if test="${not empty obj.className }">
	                           	 	/${obj.className}
	                            </c:if>
	                            
	                           	</span></td>
	                            <td>
	                            <c:if test="${obj.status==1}">
	                             	<span class="ce74c3c">进行中</span>
	                            </c:if>
	                            <c:if test="${obj.status==2 }">
	                            	推送完成
	                            </c:if>
	                            </td>
	                            
	                            <td>
	                            <span>
	                            <a href="#" class="mgr30" onclick="deleteAppPush('${obj.id}')">删除</a>
	                            <a href="#" class="mgr30" onclick="rePushApp('${obj.id}')">重新推送</a>
	                            </span>
	                            </td>
	                        </tr>
                    	</c:forEach>
                    </tbody>
                </table>
                <div class="mgt40">
                   <c:if test="${not empty page.totalPage && page.totalPage > 1 }">
							<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
							currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
							action="${ctx}/manage/appPush/list"></newTag:page>
				</c:if>
                </div>
            </div>
        </div>
		</form>
        <!-- 主体部分 E-->

    </div>
</div>

<script type="text/javascript">


function deleteAppPush(appId){
	$.ajax({
		type : "post",
		async : true,
		url : "deleteAppPush",
		data : {
			appId : appId
		},
		dataType : "text",
		success : function(msg) {
			if(msg=='success'){
				$("#pageForm").submit();
			}else{
				myAlert('系统消息',msg,function(r){  
     	    	});
			}
		},
		error : function(errorMsg) {
		}
	})
}

function rePushApp(appId){
	$.ajax({
		type : "post",
		async : true,
		url : "rePushApp",
		data : {
			appId : appId
		},
		dataType : "text",
		success : function(msg) {
			if(msg=='success'){
				$("#pageForm").submit();
			}else{
				myAlert('系统消息',msg,function(r){  
	    	          
     	    	});
			}
		},
		error : function(errorMsg) {
		}
	})
}

//登录浮层
$(function(){
    //复选框
    $('.chklist2').hcheckboxnew2();

    $('.radiolist2').hradio2()
	
    $(".sys_seleautodiv").sysSeleautoBox();
   
    function oneScreen(){
        var win_h = $(window).height();
        var header_h = $('.g_syshead').outerHeight();
        var footer_h = $('.g_sysfooter').outerHeight();
        var mainWrap_h = win_h - header_h - footer_h;
        $('.wql_mainBox').css('minHeight',mainWrap_h-135);
    };
    
    oneScreen();
    
    $(window).on('resize',function(){
        oneScreen();
    });
    
    
    $(".wql_btn_search").on("click",function(){
    	$("#pageForm").submit();
    })
})



</script>