<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev"><a href="" class="mgr5">首页</a>&gt;<a href="" class="mglr5">终端订单管理</a>&gt;</span><span class="on mgl5">设置实施学校</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="clearfix">
            <div class="wql_mainBox fl wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40"style="width:540px;">
                <div class="wql_g_tit">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl mgr30">已分配激活码的学校</h3>
                        <div class="wql_g_search fr mgt10">
                            <div class="wql_search01">
                                <input type="text" class="wql_inp" width="150" placeholder="学校名称">
                                <span class="wql_searchico"></span>
                            </div>
                            
                            
                        </div>
                        <a href="#" name="addSchool" id="addSchool">添加学校</a>
                    </div>
                    
                </div>
               
                <div class="site_sysTable wql_g_table mgt15">
                    <table class="wql_table01 t_l">
                        <thead>
                            <tr>
                                <th width="40%" class="pdl30">学校</th>
                                <th width="15%">激活码</th>
                                <th width="30%">授权数量</th>
                                <th width="10%">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach items="${orderSchoolList}" var="obj" varStatus="status">
                        		 <tr <c:if test="${obj.schoolId == schoolId}">style="background:#f5f7f9"</c:if>>
	                                <td name="schoolNameTd"  >
		                                <span class="pdl30" style="color:blue">
		                                	${obj.schoolName }
		                                </span>
		                                <input type="hidden" name="schoolIdTd" value="${obj.schoolId}">
		                                <input type="hidden" name="orderSchoolIdTd" value="${obj.id}">
	                                </td>
	                                <td><span>${obj.licenceCode}</span></td>
	                                
	                                
	                                <td width="30%">
	                                	<span> 
	                                	<c:if test="${empty obj.orderSchoolTerminalDevice }">
	                                			没有授权设备
	                                	</c:if>
	                                	
	                                	<c:forEach items="${obj.orderSchoolTerminalDevice}" var="orderSchoolTerminalDevice" varStatus="status">
	                                		<label>${orderSchoolTerminalDevice.terminalDeviceTypeName }/${orderSchoolTerminalDevice.quan}台</label><br>
	                                	</c:forEach>
	                                	</span>
	                                </td>
	                                
	                                <td>
	                                <span>
		                                <c:if test="${empty obj.licenceCode }">
		                               	 	<a href="javascript:void(0)" onclick="setLicenceCode('${obj.id}')">分配激活码</a>
		                                </c:if>
	                                </span>
	                                </td>
	                            </tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                    <div class="mgt40">
                       
                    </div>
                </div>
                
            </div>
            <div class="wql_mainBox fr wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40"style="width:540px;">
                <div class="wql_g_tit">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl mgr30">激活码可激活的终端数</h3>
                    </div>
                </div>
               
                <div class="site_sysTable wql_g_table mgt15">
                    <table class="wql_table02 t_l">
                        <thead>
                            <tr>
                                <th width="60%" class="pdl30">设备类别</th>
                                <th>设备数量</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach items="${terminalDeviceTypeQuanList}" var="terminalDeviceTypeQuan" varStatus="status">
                        		<tr>
                                	<td><span class="pdl30">${terminalDeviceTypeQuan.name}</span></td>
                                	<td><span><input type="text" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" name="${terminalDeviceTypeQuan.id}"  class="terminalDeviceQuan wql_inp wql_readonly" value="<c:if test="${not empty terminalDeviceTypeQuan.quan }">${terminalDeviceTypeQuan.quan }</c:if>" ></span></td>
                            	</tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                    <div class="mgt40">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 弹窗 S-->
<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01 ">
        <div class="wql_g_list01 w5em">
                          学校:<select  name="schoolName" style="width: 350px;" class="inp focus">
                <option value="" selected="selected">请选择学校</option>
            	<c:forEach items="${schoolList}" var="school" varStatus="status">
            		<option value="${school.id }" >${school.name}</option>
            	</c:forEach>
            </select>  
        </div>
    </div>
</div>


<form id="pageForm"  name="pageForm" action="${ctx}/manage/order/setSchoolPage" method="post">

<input type="hidden" id="orderId" name="orderId" value="${orderId}">

<input type="hidden" id="schoolId" name="schoolId" value="${schoolId}">

<input type="hidden" id="orderSchoolId" name="orderSchoolId" value="${orderSchoolId}">

</form>
<script type="text/javascript">

var orderId = $("#orderId").val();


function schoolDeviceTerminal(id){
	alert(id);
}

function setLicenceCode(id){
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/order/setSchoolLicence",
		data : {
			"orderSchoolId"  : id
		},
		async : true,
		dataType : 'text',
		success : function(msg) {
			
			if(msg=='000000'){
				$("#pageForm").submit();
			}else{
				myAlert('系统确认框',msg,function(r){
	    	          
	    	    });
			}
		}
	});
	
}

//登录浮层
$(function(){
    
	
	$('.terminalDeviceQuan').blur(function(){
		
		
		var quan = $(this).val();
		var terminalDeviceTypeId = $(this).attr("name");
		
		if(quan == ''){
			return ;
		}
		console.log($(this).val()+":"+terminalDeviceTypeId);
		$.ajax({
			type : "post",
			async : true,
			url : "saveOrupdateOrderSchoolTerminalDevice",
			data : {
				terminalDeviceTypeId : terminalDeviceTypeId,
				quan :quan,
				orderId : orderId,
				orderSchoolId : $("#orderSchoolId").val()
			},
			dataType : "text",
			success : function(msg) {
				
				if(msg=='success'){
					myAlert('系统确认框','操作成功！',function(r){
		    	          
		    	    });
				}else{
					myAlert('系统确认框',msg,function(r){
		    	          
		    	    });
					
				}
				//var img = $("<img src='../../images/gou.png'/>");
				//$("#gou").html(img);
			},
			error : function(errorMsg) {
				
			}
		})
	}); 
	
	
	 $("td[name='schoolNameTd']").on('click',function(){
		 
		var schoolId = $(this).find("input[name='schoolIdTd']").val();
		
		var orderSchoolId = $(this).find("input[name='orderSchoolIdTd']").val();
		$("#schoolId").val(schoolId)
		
		$("#orderSchoolId").val(orderSchoolId)
		
		$("#pageForm").submit();
		 	
	})
	
	
	$("#addSchool").on("click",function(){
		var dialog = art.dialog({
            title:'添加学校',
            content:$(".wql_g_artDialog")[0],
            width:'560px',
            okValue:"确定",
            padding:'30px 40px',
            cancelValue:"取消",
            cancel:true,
            ok:function() {
            
            	var schoolId = $("select[name='schoolName']").val();
            	
            	console.log(schoolId);
	
            	$.ajax({
        			type : "POST",
        			url : "${ctx}/manage/order/addSchool",
        			data : {
        				"orderId"  : orderId,
        				"schoolId" : schoolId
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
    })
	
  
    
	
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