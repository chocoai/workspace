<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<div class="wql_g_content wql_bge7e9eb pdb30">
	<div class="w1200">
	
	<div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev"><a href="" class="mgr5">首页</a>&gt;</span><span class="on mgl5">终端订单管理</span>
            </div>
    </div>
	
	<div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
	<div class="bo_title mgt15">
        <h3><i class="ico ico1"></i>数据指标</h3>
    </div>
    <div class="code_table data_index">
        <table class="mod_table t_c" width="100%" style="border-collapse:collapse;">
            <tr>
                <td width="16%">
                	<p>已经授权学校数</p>
                    <p class="num"><span class="cf00">${schoolCount}</span></p>
                    <p class="incr"><em></em></p>
                    <p class="info"></p>
                </td>
                <td width="16%">
                	<p>一体机未授权</p>
                    <p class="num"><span class="c00f">${aioNotUseCount}</span></p>
                    <p class="incr">已授权<em>${aioUseCount}</em>可授权总数<em>${aioCount}</em></p>
                    <p class="info"></p>
                </td>
                <td  width="16%">
                	<p>电子书包未授权</p>
                	<p class="num"><span class="c000">${ebookpackageNotUseCount }</span></p>
                    <p class="incr">已授权<em>${ebookpackageUseCount }</em>可授权总数<em>${ebookpackageCount}</em></p>
                    <p class="info"></p>
                </td>
            </tr>
        </table>
    </div>
    
	    <div class="site_sysTable wql_g_table mgt15">
	    		<div class="wql_g_tit mgt20">
	                <div class="wql_tit01 clearfix">
	                    <h3 class="fl"></h3>
	                    <div class="wql_g_btn mgt8 fr">
	                        <div class="wql_btn01 clearfix">
	                             <a href="javascript:void(0)" class="wql_addClass02 fl mgr15">添加学校</a>
	                        </div>
	                    </div>
	                </div>
	            </div>
	    
	                <table class="wql_table01 t_l">
	                    <thead>
	                        <tr>
	                            <th class="pdl15">学校名称</th>
	                            <th>激活码</th>
	                            <th>授权总数</th>
	                            <th>已授权</th>
	                            <th>未授权</th>
	                            <th width="165">操作</th>                            
	                        </tr>
	                    </thead>
	                    <tbody>
	                    	<c:forEach items="${orderSchoolList}" var="orderSchool" varStatus="status">
		        				<tr>
		        					<td>${ orderSchool.schoolName}</td>
		        					<td>${ orderSchool.licenceCode}</td>
		        					<td>${ orderSchool.deviceCount}</td>
		        					<td>${ orderSchool.deviceUseCount}</td>
		        					<td>${ orderSchool.deviceCount - orderSchool.deviceUseCount}</td>
		        					<td>
		        						<a href="javascript:void(0)" onclick="detail('${orderSchool.id}')">查看</a>
		        					    <a href="javascript:void(0)" onclick="edit('${orderSchool.id}','${orderSchool.schoolId }','${ orderSchool.schoolName}')">编辑</a>
		        					    <a href="javascript:void(0)" onclick="del('${orderSchool.id}')">删除</a>
		        					</td>
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

<input class="wql_inp" type="hidden" name="orderId" value="${orderId}">

<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01 ">
        <div class="wql_g_list01 w5em">
        	<ul class="wql_ul">
        		<li class="wql_li mgr30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>学校
                        </div>
                        <div class="wql_box_r">
                            <select  name="schoolName" style="width: 350px;" class="inp focus">
				                <option value="" selected="selected">请选择学校</option>
				            	<c:forEach items="${schoolList}" var="school" varStatus="status">
				            		<option value="${school.id }" >${school.name}</option>
				            	</c:forEach>
				            </select>
				            
                        </div>
                         <div class="wql_box_r">
                        	<div class="wql_tips" name="schoolIdError"></div>
                        </div>  
                    </div>
                </li>
        		
        		<c:forEach items="${orderTerminalDeviceTypeList}" var="orderTerminalDeviceType" varStatus="status">
        			<li class="wql_li mgr30">
	                    <div class="wql_li_box">
	                        <div class="wql_box_l">
	                            <em>*</em>${orderTerminalDeviceType.terminalDeviceName}
	                        </div>
	                        <div class="wql_box_r">
	                            <input class="wql_inp" on="${orderTerminalDeviceType.terminalDeviceTypeId}" max="${orderTerminalDeviceType.schoolNotDeviceTotalQuan}" type="text" style="width:300px;" name="deviceNum">
	                            	最多可分配${orderTerminalDeviceType.schoolNotDeviceTotalQuan} 个设备
	                            <div class="wql_tips" name="error"></div>
	                        </div>
	                    </div>
	                </li>
        		</c:forEach>
        	</ul>
        </div>
        
        <div class=" mgt30">
        </div>
    </div>
</div>




<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01 ">
        	<span name="detailSchoolName"></span>
        	<div class="site_sysTable wql_g_table mgt15 chklist2">
                <table class="wql_table02 t_c">
                    <thead>
                        <tr>
                       		<th>设备</th>
	        				<th>可授权总数</th>
	        				<th>已授权</th>
	        				<th>未授权</th>
                        </tr>
                    </thead>
                    <tbody id="orderSchoolList">
                    
                    </tbody>
                </table>
            </div>
    </div>
</div>

<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01 ">
        <div class="wql_g_list01 w5em">
        	<ul class="wql_ul" id="schoolTerminalDeviceTypeUl">
        		
        	</ul>
     	</div>
     </div>
</div>

<script type="text/javascript">
	
function detail(id){
	var dialog = art.dialog({
        title:'查看设备授权情况',
        content:$(".wql_g_artDialog")[1],
        width:'760px',
        okValue:"确定",
        padding:'20px',
        //padding:'30px 40px',
        cancelValue:"取消",
        cancel:true,
        initialize: function() {
        	
        	$.ajax({
        		type : "POST",
        		traditional: true,//这里设置为true
        		url : "${ctx}/manage/order/getSchoolTerminalDeviceDetail",
        		data : {
        			"orderSchoolId"  : id,
        			"orderId" : $("input[name='orderId']").val()
        		},
        		async : false,
        		dataType : 'json',
        		success : function(data) {
        			var htmlStr = '';
        			$("#orderSchoolList").html('');
        			if(data != null){
        				if(data.list != null && data.list.length > 0){
        					dataArray = data.list;
        					for(var i=0;i<data.list.length;i++){
        						var obj = data.list[i];
        						
        						console.log(obj)
        						
        						htmlStr += '<tr>';
        						htmlStr += '<td>'+obj.terminalDeviceTypeName+'</td>';
        						htmlStr += '<td>'+obj.quan+'</td>';
        						htmlStr += '<td>'+obj.useDeviceTotalQuan+'</td>';
        						htmlStr += '<td>'+obj.notUseDeviceTotalQuan+'</td>';
        						htmlStr += '</tr>';
        					}
        				}
        				
        				$("span[name='detailSchoolName']").text(data.schoolName);
        			}
        			
        			if(htmlStr != ''){
        				$("#orderSchoolList").html(htmlStr);
        			}
        		}
        	});
        	
        },
        ok:function() {
        	
 		}
    });
	
	
	
}

function edit(id,schoolId,schoolName){
	var dialog = art.dialog({
        title:'编辑学校['+schoolName+']',
        content:$(".wql_g_artDialog")[2],
        width:'760px',
        okValue:"确定",
        padding:'20px',
        //padding:'30px 40px',
        cancelValue:"取消",
        cancel:true,
        initialize: function() {
        	$.ajax({
        		type : "POST",
        		traditional: true,//这里设置为true
        		url : "${ctx}/manage/order/getSchoolTerminalDeviceDetail",
        		data : {
        			"orderSchoolId"  : id,
        			"orderId" : $("input[name='orderId']").val()
        		},
        		async : false,
        		dataType : 'json',
        		success : function(data) {
        			var htmlStr = '';
        			if(data != null){
        				if(data.list != null && data.list.length > 0){
        					dataArray = data.list;
        					for(var i=0;i<data.list.length;i++){
        						var obj = data.list[i];
        						htmlStr += '<li class="wql_li mgr30">';
        						htmlStr += '<div class="wql_li_box">';
        						htmlStr += '<div class="wql_box_l">';
        						htmlStr += '<em>*</em>'+obj.terminalDeviceTypeName+'';
        						htmlStr += '</div>';
        						htmlStr += '<div class="wql_box_r">';
        						htmlStr += '<input class="wql_inp" bindDeviceNum="'+obj.bindDeviceNum+'" on="'+obj.terminalDeviceTypeId+'" max="'+obj.notOrderSchoolTerminalDeviceTotalQuan+'" type="text" value='+obj.quan+' style="width:300px;" name="editDeviceNum">最多分配'+obj.notOrderSchoolTerminalDeviceTotalQuan+'';
        						htmlStr += '<div class="wql_tips" name="error"></div>';
        						htmlStr += ' </div>';
        						htmlStr += '</div>';
        						htmlStr += '</li>';
        					}
        				}
        			}
        			if(htmlStr != ''){
        				$("#schoolTerminalDeviceTypeUl").html(htmlStr);
        			}
        		}
        	});
        },
        ok:function() {
        	var deviceNumAttr = [];
        	var flag=true;
        	$("input[name='editDeviceNum']").each(function(){

        		var deviceTerminalTypeId = parseInt($(this).attr("on")) ;
        		var deviceNum = parseInt($(this).val()) ;
        		var max = parseInt($(this).attr("max"));

        		var bindDeviceNum = parseInt($(this).attr("bindDeviceNum"));//绑定的设备数
        		
        		var errorNode = $(this).parent().find("div[name='error']");
        		
        		console.log(deviceNum)
        		
        		if(deviceNum==''){
        			errorNode.html('请填写设备数量');
        			flag=false;
        			return ;
        		}
        		        		
        		if(deviceNum > max){
        			errorNode.html('不能大于最大值');
        			flag=false;
        			return 
        		}
        		
        		if(deviceNum <= bindDeviceNum){
        			errorNode.html('设备数量必须大于已授权的数量');
        			flag=false;
        			return ;
        		}
        		
        	
        		deviceNumAttr.push(deviceTerminalTypeId+","+deviceNum);
        	});
        	
 			if(schoolId==''){
 				$("div[name='schoolIdError']").html('请选择学校');
 				flag=false;
 			}
 				
 			if(!flag)
 				return false;
        	
        	$.ajax({
        		type : "POST",
        		traditional: true,//这里设置为true
        		url : "${ctx}/manage/order/updateSchoolTerminalDevice",
        		data : {
        			"orderSchoolId"  : id,
        			"deviceNumAttr":deviceNumAttr
        		},
        		async : false,
        		dataType : 'text',
        		success : function(msg) {
        			if(msg=='success'){
    					window.location.reload()
    				}else{
    					myAlert('系统确认框',msg,function(r){
    		    	          
    		    	    });
    				}
        		}
        	});
        	
 		}
    });
	
}

function del(id){
	console.log(id)
	$.ajax({
		type : "POST",
		traditional: true,//这里设置为true
		url : "${ctx}/manage/order/deleteOrderSchool",
		data : {
			"orderSchoolId"  : id
		},
		async : false,
		dataType : 'text',
		success : function(msg) {
			if(msg=='success'){
				window.location.reload()
			}else{
				myAlert('系统确认框',msg,function(r){
	    	          
	    	    });
			}
		}
	});
	
}
	
$(function(){
	
	$('.wql_addClass02').on('click',function(){
		var dialog = art.dialog({
	        title:'添加学校',
	        content:$(".wql_g_artDialog")[0],
	        width:'760px',
	        
	        okValue:"确定",
	        padding:'20px',
	        //padding:'30px 40px',
	        cancelValue:"取消",
	        cancel:true,
	        initialize: function() {
	        	
	        },
	        ok:function() {
	 			
	        	var schoolId = $("select[name='schoolName']").val();
	        	var orderId = $("input[name='orderId']").val();
	        	
	        	var deviceNumAttr = [];
	        	
	        	var flag=true;
	        	
	        	$("input[name='deviceNum']").each(function(){

	        		var deviceTerminalTypeId = parseInt($(this).attr("on")) ;
	        		var deviceNum = parseInt($(this).val()) ;
	        		var max = parseInt($(this).attr("max"));

	        		
	        		var errorNode = $(this).parent().find("div[name='error']");
	        		
	        		
	        		if(deviceNum==''){
	        			errorNode.html('请填写设备数量');
	        			
	        			flag=false;
	        			return ;
	        		}
	        		
	        		
	        		if(deviceNum > max){
	        			
	        			errorNode.html('不能大于最大值');
	        			
	        			flag=false;
	        			return 
	        		}
	        	
	        		deviceNumAttr.push(deviceTerminalTypeId+","+deviceNum);
	        	});
	        	
	 			if(schoolId==''){
	 				$("div[name='schoolIdError']").html('请选择学校');
	 				flag=false;
	 			}
	 				
	 			if(!flag)
	 				return false;
	        
	        	
	        	$.ajax({
        			type : "POST",
        			traditional: true,//这里设置为true
        			url : "${ctx}/manage/order/addSchoolDeviceNum",
        			data : {
        				"orderId"  : orderId,
        				"schoolId" : schoolId,
        				"deviceNumAttr":deviceNumAttr
        			},
        			async : false,
        			dataType : 'text',
        			success : function(msg) {
        				if(msg=='success'){
        					window.location.reload()
        				}else{
        					myAlert('系统确认框',msg,function(r){
        		    	          
        		    	    });
        				}
        			}
        		});
	        	
	 		}
	    });
	})
	
	
})
	
	
</script>