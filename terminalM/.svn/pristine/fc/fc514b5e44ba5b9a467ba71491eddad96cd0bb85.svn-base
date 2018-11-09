<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<form id="pageForm"  name="pageForm" action="${ctx}/manage/order/add" method="post">



<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">业务管理 &gt;</span><span class="on mgl5">添加订单</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
            <div class="wql_g_tit">
                <div class="wql_tit01 clearfix">
                    <h3 class="fl">添加订单</h3>
                </div>
                
            </div>
            <div class="wql_addorderBox" style="width:520px;margin:30px auto;">
                <div class="wql_g_list01 w6em">
                    <ul class="wql_ul">
                        <li class="wql_li">
                            <div class="wql_li_box">
                                <div class="wql_box_l">
                                    <em>*</em>合同编号：
                                </div>
                                <div class="wql_box_r">
                                    <input class="wql_inp" name="contractNumber" type="text" style="width:395px;">
                                    <div class="wql_tips" name="contractNumberError"></div>
                                </div>
                            </div>
                        </li>
                        <li class="wql_li mgt30">
                            <div class="wql_li_box">
                                <div class="wql_box_l">
                                    <em>*</em>供应商：
                                </div>
                                <div class="wql_box_r">
                                   <span class="sys_seleautodiv wql_sel" name="supplierInfo" style="width: 415px; z-index: 3;">
                                        <span class="sys_seleautocur">
                                            <p>请选择供应商</p>
                                            <input type="hidden" class="selRes" value="0">
                                        </span>
                                         <span class="sys_seleautodrop animate" style="width: 415px; z-index: 9999; display: none;">
                                        <c:forEach items="${supplierInfoList}" var="supplierInfo" varStatus="status">
                                        	<a href="#" value="${supplierInfo.id}">${supplierInfo.name }</a>
                                        </c:forEach>
                                        </span>
                                    </span>
                                    <div class="wql_tips" name="supplierInfoError"></div>
                                </div>
                            </div>
                        </li>
                        <li class="wql_li mgt30">
                            <div class="wql_li_box">
                                <div class="wql_box_l">
                                    <em>*</em>订单日期：
                                </div>
                                <div class="wql_box_r">
                                	<input type="text" name="orderTime"  class="Wdate inp wd140"  onfocus="WdatePicker({maxDate:'\'2020-10-01\'}'})" id="d4311" >
                                	<div class="wql_tips" name="orderTimeError"></div>
                                </div>
                            </div>
                        </li>
                        <li class="wql_li mgt20"> 	
                            <div class="wql_li_box">
                                <div class="wql_box_l">
                                    <em>*</em>实施区域：
                                </div>
                                <div class="wql_box_r">
                                		<table>
                                			<tr>
                                				<td>省：</td>
                                				<td>
                                					<span class="sys_seleautodiv mgr10" style="width: 120px; z-index: 3;">
									                    <span class="sys_seleautocur" name="provinceSpan">
									                        <p>请选择</p>
									                        <input type="hidden" class="selRes" value="-1">
									                    </span>
									                    <span class="sys_seleautodrop animate" name="province" style="width: 120px; display: none; z-index: 9999;">
									                    </span>
									                </span>
												</td>
                                			</tr>
                                			
                                			<tr>
                                				<td>市:</td>
                                				<td>
                                					<span class="sys_seleautodiv mgr10"  style="width: 120px; z-index: 3;">
									                    <span class="sys_seleautocur" name="citySpan">
									                        <p name="cityP">请选择</p>
									                        <input type="hidden" class="selRes" value="">
									                    </span>
									                    <span class="sys_seleautodrop animate"  style="width: 120px; display: none; z-index: 9999;">
									                    </span>
									                </span>
												</td>
                                			</tr>
                                			
                                			<tr>
                                				<td>区/县</td>
                                				<td>
                                					<span class="sys_seleautodiv mgr10"  style="width: 120px; z-index: 3;">
									                    <span class="sys_seleautocur" name="areaSpan">
									                        <p>请选择</p>
									                        <input type="hidden" class="selRes" value="">
									                    </span>
									                    <span class="sys_seleautodrop animate"  style="width: 120px; display: none; z-index: 9999;">
									                    </span>
									                </span>
                                				</td>
                                			</tr>
                                		</table>
										<div class="wql_tips" name="provinceError"></div>
                                </div>
                            </div>
                        </li>
                       
                        <li class="wql_li mgt30">
                            <div class="wql_li_box">
                                <div class="wql_box_l">
                                    <em></em>备注
                                </div>
                                <div class="wql_box_r"><textarea class="wql_textarea" name ="memo" style="width:395px;" placeholder="限50字"></textarea></div>
                            </div>
                        </li>
                        <li class="wql_li mgt30">
                            <div class="wql_li_box">
                                <div class="wql_box_l">
                                    <em>*</em>设备详情
                                </div>
                                <div class="wql_box_r">
                                    <div class="clearfix bb2">
                                        <div class="fl">增加设备类型<div class="wql_tips" name="deviceDetailError"></div></div>
                                        <div class="wql_g_btn fr">
                                            <div class="wql_btn01">
                                                <a href="javascript:;" class="wql_addBtn">增加行</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="site_sysTable wql_g_table mgt15 chklist2">
                                        <table class="wql_table02 t_l">
                                            <thead>
                                                <tr>
                                                    <th class="pdl15" width="45"><label class="chkAll sys_checkbox"><input type="checkbox" value="1" style="display: none;"></label></th>
                                                    <th>设备类别</th>
                                                    <th width="100">设备数量</th>
                                                    <th width="100">操作</th>
                                                </tr>
                                            </thead>
                                            <tbody id="terminalDeviceTypeList"></tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="wql_li mgt30">
                            <div class="wql_li_box">
                                <div class="wql_box_l">
                                    <em></em>
                                </div>
                                <div class="wql_box_r">
                                    <div class="wql_g_btn pdt20 pdb10">
                                        <div class="wql_btn04">
                                            <a href="javascript:;" class="btn01 mgr20">确认</a>
                                            <a href="javascript:;" class="btn02">返回</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            
            
        </div>

        <!-- 主体部分 E-->

    </div>
</div>
</form>
<span id="terminalDeviceTypeListSpan" style="display:none;">
<span class="sys_seleautodrop animate"  style="width: 120px; z-index: 9999; display:none;">
    <c:forEach items="${terminalDeviceTypeList}" var="terminalDeviceType" varStatus="st">
        <a href="#" value="${terminalDeviceType.id}">${terminalDeviceType.name}</a>
    </c:forEach>
</span>
</span>


<script type="text/javascript">
function queryClass2(schoolId){
	var htmlStr = '<a href="#" class="active" value="">请选择</a>';
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
	var htmlStr = '<a href="#" class="active" value="">请选择</a>';
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


//登录浮层
$(function(){
	$('span[name="provinceSpan"]').siblings('.sys_seleautodrop').html('<a href="#" class="active" value="">请选择</a>');
	$('span[name="citySpan"]').siblings('.sys_seleautodrop').html('<a href="#" class="active" value="">请选择</a>');
	$('span[name="areaSpan"]').siblings('.sys_seleautodrop').html('<a href="#" class="active" value="">请选择</a>');

	$('span[name="province"]').html(queryArea2(1,0))
	
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

    $(document).on("click",".wql_addBtn",function(){
    	var terminalDeviceTypeListSpan = $("#terminalDeviceTypeListSpan").html();
    	
    	//console.log(terminalDeviceTypeListSpan)
        var tr = '<tr>'+
                 '   <td><span class="pdl15"><label class="sys_checkbox"><input type="checkbox" value="1" style="display: none;"></label></span></td>'+
                 '   <td><span>'+
                 '       <span class="sys_seleautodiv mgr10" style="width: 120px; z-index: 3;">'+
                 '           <span class="sys_seleautocur">'+
                 '               <p>请选择</p>'+
                 '               <input type="hidden" class="selRes" value="1">'+
                 '           </span>'+
                 '           '+terminalDeviceTypeListSpan+

                 '       </span></span>'+
                 '       </td>'+
                 '   <td><span><input type="text" class="edit wql_inp" style="width:40px;" value=""></span></td>'+
                 '   <td><span><a href="javascript:;" class="wql_delBtn">删除</a></span></td>'+
                '</tr>'
        $('.wql_g_table tbody').append(tr);
        $(".sys_seleautodiv").sysSeleautoBox();
        
    });
    $(document).on("click",".wql_g_table .wql_delBtn",function(){
        var that = $(this);
        that.parents('tr').remove();
        console.log(that.parents('tr'))
    });
    
    
    
    $(document).on("click",".btn01",function(){
    	
    	var orderNumber = $("input[name='orderNumber']").val();
    	var contractNumber = $("input[name='contractNumber']").val();
		
		var orderTime = $("input[name='orderTime']").val();//分类名称
		
		var provinceCode = $('span[name="provinceSpan"]').find('.selRes').val();//分类名称
		var cityCode = $('span[name="citySpan"]').find('.selRes').val();//分类名称
		var areaCode = $('span[name="areaSpan"]').find('.selRes').val();//分类名称
		var memo = $("textarea[name='memo']").val();//备注
		var supplierId =$("span[name='supplierInfo']").find("input[class='selRes']").val();
		var terminalDeviceTypeIds = $("#terminalDeviceTypeList").children("tr");

		var terminalDeviceTypeInfo = [];
		
		terminalDeviceTypeIds.each(function(){ //便利除标题行外所有行
			var terminalDeviceTypeId = $(this).find("input[class='selRes']").val();
			var quan = $(this).find("input[class='edit wql_inp']").val();
			terminalDeviceTypeInfo.push(terminalDeviceTypeId + ":" + quan)
    	});
		
		var flag=true;
			
		if(provinceCode==''){
			$("div[name='provinceError']").html("请选择省份");
			flag=false;
		}
			
		if(contractNumber ==''){
			$("div[name='contractNumberError']").html("请输入合同编号");
			flag=false;
		}
			
		if(supplierId==''){
			$("div[name='supplierInfoError']").html("请选择供应商");
			flag=false;
		}
		
		if(orderTime==''){
			$("div[name='orderTimeError']").html("请输入订单时间");
			flag=false;
		}
		
		if(terminalDeviceTypeInfo==''){
			$("div[name='terminalDeviceTypeInfoError']").html("请添加设备");
			flag=false;
		}
		
			
		if(!flag)
			return false;
		
		//var supplierInfo = $("span[name='supplierInfo']");
		
		console.log(supplierId);
		
    	$.ajax({
    		type : "POST",
    		url : "${ctx}/manage/order/add",
    		traditional:true,
    		data : {
    			"supplierId" : supplierId,
    			"terminalDeviceTypeInfo" : terminalDeviceTypeInfo,
    			"orderNumber" : orderNumber,
    			"contractNumber" : contractNumber,
    			"orderTime" : orderTime,
    			"provinceCode" : provinceCode,
    			"cityCode" : cityCode,
    			"areaCode" : areaCode
    		},
    		async : false,
    		dataType : 'text',
    		success : function(msg) {
    			if(msg=='success'){
    				window.location.href='list';  
    			}else{
    				myAlert('系统确认框',msg,function(r){  
		    	          
			    	});
    			}
    		}
    	});
    	
        
    });
    
    
})



</script>