<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>



<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">系统管理 &gt;</span><span class="on mgl5">品牌型号维护</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="clearfix wql_g_mainBox ">
            <div class="wql_Lside wql_bgfff wql_b_radius6">
                <div class="wql_sideTit t_c">
                    <h3>终端设备</h3>
                </div>
                <div class="wql_g_sideNav">
                    <dl class="wql_dl">
                    	<c:forEach items="${terminalDeviceTypeTree}" var="tree" varStatus="status">
                    		<dt class="on">
                        		${tree.value}<i class="wql_upico"></i>
                        	</dt>

                        	 	 <c:if test="${not empty tree.subValue }">
                        	 	 	<c:set value="${fn:split(tree.subValue, ',')}" var="subValues" />
                        	 	 	<c:forEach items="${subValues}" var="subValue">
                        	 	 	<c:set value="${fn:indexOf(subValue, ':')}" var="fgf" />
                        	 	 	<c:set value="${fn:substring(subValue, 0,fgf)}" var="name" />
                        	 	 	<c:set value="${fn:length(subValue)}" var="subValueLength" />            	 	 	
                        	 	 	<c:set value="${fn:substring(subValue, fgf+1,subValueLength)}" var="terminalDeviceTypeId" />
                        	 	 	<dd>
										<a href="javascript:;">${name}</a>
										<span style="display:none" name="terminalDeviceTypeId">${terminalDeviceTypeId}</span>
										<span style="display:none" name="terminalName">${name}</span>
			                        </dd>
									</c:forEach>
                        	 	 </c:if>
                    	</c:forEach>
                    </dl>
                </div>
            </div>
            <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
                <div class="wql_g_tit">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">查询条件</h3>
                    </div>
                    
                </div>
                <div class="wql_g_list02">
                    <ul class="wql_ul clearfix mgt25">
                        <li class="wql_li mgr30">
                            <div class="wql_li_box">
                                <span>品牌：</span> 
                                <input class="site_sysinp" name="brandName" type="text" style="width:150px;">
                            </div>
                        </li>
                        <li class="wql_li">
                            <div class="wql_li_box">
                                <span>型号：</span>
                                <input class="site_sysinp" name="modelName" type="text" style="width:150px;">
                                <a href="javascript:;" class="wql_btn_search">查询</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="wql_g_tit mgt20">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">品牌型号信息列表</h3>
                        <div class="wql_g_btn mgt8 fr">
                            <div class="wql_btn01">
                                <a href="#" class="wql_addClass02">添加品牌型号</a>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div class="site_sysTable wql_g_table mgt15">
                    <table class="wql_table01 t_l">
                        <thead>
                            <tr>
                                <th class="pdl15">品牌</th>
                                <th>厂家</th>
                                <th>型号</th>
                                <th>创建人</th>
                                <th>创建日期</th>
                                <th width="125">操作</th>
                            </tr>
                        </thead>
                        <tbody id="brandList">  </tbody>
                    </table>
                   <div class="mgt40">
                </div>
            </div>
        </div>
        

        <!-- 主体部分 E-->

    </div>
</div>

<!-- 弹窗 S-->
<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01 ">
        <div class="wql_g_list01 w5em">
            <ul class="wql_ul">
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>终端设备
                        </div>
                        <div class="wql_box_r">
                            <div class="wql_text"style="width:307px;" name="terminalName"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>厂家
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" style="width:300px;" name="vendor">
                            <div class="wql_tips" name="vendorError"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>品牌
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" style="width:300px;" name="brandName">
                            <div class="wql_tips" name="brandNameError"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>型号
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" style="width:300px;" name="modelName">
                            <div class="wql_tips" name="modelNameError"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>备注
                        </div>
                        <div class="wql_box_r"><textarea class="wql_textarea" name="memo" style="width:300px;"></textarea></div>
                    </div>
                </li>
                
            </ul>
        </div>
    </div>
</div>
<input type="hidden" name="currPage" id="currPage" value="1">
<input type="hidden" name="totalPage" id="totalPage">
<input type="hidden" name="pageSize" id="pageSize" value="10">
<script type="text/javascript" src="${ctx}/js/MyPageJs/page.js"></script>
<script type="text/javascript">


var currentPage ;//当前页
var totalPage ;//总页数
var terminalDeviceTypeId='';
var terminalName='';

var brandName ='';
var modelName = '';

function loadList(pageValue){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/brand/querybrand",
		data : {
			"terminalDeviceTypeId" : terminalDeviceTypeId,
			"brandName" : brandName,
			"modelName":modelName,
			'pageValue':pageValue
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			totalPage = data.pages;
       	 	currentPage = data.pageNum;
			var htmlStr = '';
			
			if(data != null){
				if(data.list != null && data.list.length > 0){
					dataArray = data.list;
					for(var i=0;i<data.list.length;i++){
						var obj = data.list[i];
						htmlStr += '<tr>';
						htmlStr += '<td><span class="pdl15">'+obj.brandName+'</span></td>';
						htmlStr += '<td>'+obj.vendor+'</td>';
						htmlStr += '<td>'+obj.modelName+'</td>';
						htmlStr += '<td>'+obj.creatorName+'</td>';
						htmlStr += '<td>'+new Date(obj.createTime.time).format("yyyy-MM-dd hh:mm:ss")+'</td>';
						htmlStr += '<td><span><a href="javascript:void(0)" onclick="deletebrand('+obj.id+')" class="">删除</a></span>  <span><a href="javascript:void(0)" onclick="editbrand('+obj.id+')" class="">编辑</a></span></td>';
						htmlStr += '</tr>';
					}
				}
				
				myPage();
			}
			if(htmlStr != ''){
				$("#brandList").html(htmlStr);
			}else{
				$("#brandList").html('<tr><td colspan="12" align="center"><font color="red">暂无数据</font></td></tr>');
				$("#base_page").html('');
			}
		}
	});
}

function editbrand(id){
	var oldBrandName;
	var oldModelName;
	var dialog = art.dialog({
        title:'添加品牌型号',
        content:$(".wql_g_artDialog")[0],
        width:'560px',
        okValue:"确定",
        padding:'30px 40px',
        cancelValue:"取消",
        cancel:true,
        initialize: function() {
        	$.ajax({
 				type : "post",
 				async : true,
 				url : "detailBrand",
 				data : {
 					id : id,
 				},
 				dataType : "json",
 				success : function(data) {
 					$("div[name='vendorError']").html('');
 		    		$("div[name='brandNameError']").html('');
 		    		$("div[name='modelNameError']").html('');
 		    	
 		    		$("input[name='vendor']").val(data.vendor);
 		    		$("input[name='brandName']").val(data.brandName);
 		    		$("input[name='modelName']").val(data.modelName);
 		    		oldBrandName = data.brandName;
 		    		oldModelName = data.modelName;
 		    		
 		    		$("textarea[name='memo']").val(data.memo);
 				},
 				error : function(errorMsg) {
 				}
 			})
        },
        ok:function() {

        	var vendor = $("input[name='vendor']").val();
 			var brandName = $("input[name='brandName']").val();
 			var memo = $("textarea[name='memo']").val();//分类名称
 			var modelName = $("input[name='modelName']").val();
 			
 		
 			
 			var flag=true;
 			
 			if(vendor==''){
 				$("div[name='vendorError']").html('请输入厂商');
 				flag=false;
 			}
 			
 			if(brandName ==''){
 				$("div[name='brandNameError']").html("请输入品牌");
 				flag=false;
 			}
 			
 			if(modelName==''){
 				$("div[name='modelNameError']").html("请输入型号");
 				flag=false;
 			}
 			
 			if(!flag)
 				return false;
 			
 			
 			console.log(oldBrandName+":"+oldModelName)
 			
 			$.ajax({
 				type : "post",
 				async : true,
 				url : "updateBrand",
 				data : {
 					vendor : vendor,
 					oldBrandName : oldBrandName,
 					oldModelName : oldModelName,
 					brandName : brandName,
 					modelName : modelName,
 					memo : memo,
 					id : id
 				},
 				dataType : "text",

 				success : function(msg) {
 					if(msg=="success"){
 						loadList();
 					}else{
 						myAlert('系统消息',msg,function(r){  
 	     	    	          
 	     	    	    });
 					}
 				},
 				error : function(errorMsg) {

 				}
 			})
 			
 			
 			
 		}
    });
}

function deletebrand(id){
	myConfirm('系统确认框','是否删除！',function(r){
        if(r){
        	$.ajax({
    			type : "POST",
    			url : "${ctx}/manage/brand/deleteBrand",
    			data : {
    				"id"  : id
    			},
    			async : false,
    			dataType : 'text',
    			success : function(msg) {
    				if(msg=='success'){
    					loadList();
    					//$("#pageForm").submit();
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
	
	loadList();
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

    
    $(".wql_btn_search").on("click",function(){
	     
    	
        brandName = $("input[name='brandName']").val();
   		modelName =  $("input[name='modelName']").val();
   		
   		
   		loadList();
   		
   		
    })
    
    $(".wql_g_sideNav dd").on('click',function(){
        $(this).addClass('active').siblings('dd').removeClass('active');
        $(this).siblings('dt').removeClass('on');
       
        terminalDeviceTypeId = $(this).children("span[name=terminalDeviceTypeId]").text();
        terminalName = $(this).children("span[name=terminalName]").text();

        loadList()
        if($(this).prevUntil('dt').length>0){
             $(this).prevUntil('dt').last().prev().addClass('on');
        }
        else{
             $(this).prev().addClass('on');
        }
    })

    $(".wql_g_sideNav dt").on("click",function(){
        var that = $(this);
        var ico = that.find('i');
        
        terminalDeviceTypeId='';
        terminalName='';
        
        if(ico.hasClass('wql_upico')){
            ico.removeClass('wql_upico').addClass('wql_downico');
            that.nextUntil('dt').hide();
        }
        else{
            ico.removeClass('wql_downico').addClass('wql_upico');
            that.nextUntil('dt').show();
        }
    })

    $('.wql_addClass02').on('click',function(){
    	
    	if(terminalName == null ||terminalName==''){
    		myAlert('系统确认框','请选择终端设备！',function(r){  
    	    });
    		return ;
    	}
    	
    	
    	
    	$("input[name='vendor']").val('');
		$("input[name='brandName']").val('');
		$("textarea[name='memo']").val('');//分类名称
		$("input[name='modelName']").val('');
		$("div[name='terminalName']").text(terminalName);
		
		
		$("div[name='vendorError']").html('');
		$("div[name='brandNameError']").html('');
		$("div[name='modelNameError']").html('');
		
        var dialog = art.dialog({
            title:'添加品牌型号',
            content:$(".wql_g_artDialog")[0],
            width:'560px',
            okValue:"确定",
            padding:'30px 40px',
            cancelValue:"取消",
            cancel:true,
            ok:function() {
            	
            	$("div[name='vendorError']").html('');
        		$("div[name='brandNameError']").html('');
        		$("div[name='modelNameError']").html('');
            	
            	var vendor = $("input[name='vendor']").val();
     			var brandName = $("input[name='brandName']").val();
     			var memo = $("textarea[name='memo']").val();//分类名称
     			var modelName = $("input[name='modelName']").val();
     			
     			if(terminalDeviceTypeId==null ||terminalDeviceTypeId==''){
     				myAlert('系统消息','请选择终端设备！',function(r){  
     	    	          
     	    	    });
     				return ;
     			} 
     			
     			var flag=true;
     			
     			if(vendor==''){
     				$("div[name='vendorError']").html('请输入厂商');
     				flag=false;
     			}
     			
     			if(brandName ==''){
     				$("div[name='brandNameError']").html("请输入品牌");
     				flag=false;
     			}
     			
     			if(modelName==''){
     				$("div[name='modelNameError']").html("请输入型号");
     				flag=false;
     			}
     			
     			if(!flag)
     				return false;
     			
     			
     			$.ajax({
     				type : "post",
     				async : true,
     				url : "saveBrand",
     				data : {
     					vendor : vendor,
     					brandName:brandName,
     					modelName:modelName,
     					memo:memo,
     					terminalDeviceTypeId:terminalDeviceTypeId
     				},
     				dataType : "text",

     				success : function(msg) {
     					console.log(msg);
     					//console.log(type(msg))
     					if(msg=="success"){
     						loadList();
     					}else{
     						myAlert('系统消息',msg,function(r){  
     	     	    	          
     	     	    	    });
     					}
     				},
     				error : function(errorMsg) {

     				}
     			})
     			
     			
     			
     		}
        });
    });

})



</script>