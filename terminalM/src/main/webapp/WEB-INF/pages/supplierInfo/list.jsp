<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>


<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">系统管理 &gt;</span><span class="on mgl5">供应商管理</span>
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
                                <span>供应商编号：</span>
                                <input type="text" class="site_sysinp" name="searchSupplierNum"  style="width:220px;">
                            </div>
                        </li>
                        <li class="wql_li">
                            <div class="wql_li_box">
                                <span>供应商名称：</span>
                                <input type="text" class="site_sysinp" name="searchSupplierName" style="width:220px;">
                                <a href="javascript:;" class="wql_btn_search" >查询</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="wql_g_tit mgt20">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">供应商信息列表</h3>
                        <div class="wql_g_btn mgt8 fr">
                            <div class="wql_btn01">
                                <a href="#" class="wql_addClass02">添加供应商</a>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div class="site_sysTable wql_g_table mgt15">
                    <table class="wql_table01 t_l">
                        <thead>
                            <tr>
                                <th class="pdl15" width="20%">供应商编号</th>
                                <th width="20%">供应商名称</th>
                                <th width="20%">创建人</th>
                                <th width="20%">创建日期</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="supplierinfoList"></tbody>
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
        <div class="wql_g_list01 w8em">
            <ul class="wql_ul">
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>供应商名称
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp " type="text" name="name" style="width:300px;">
                            <div class="wql_tips" name="nameError"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>供应商联系人
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" name="contact" style="width:300px;">
                            <div class="wql_tips" name="contactError"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>供应商联系方式
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" name ="contactTel" style="width:300px;">
                            <div class="wql_tips" name="contactTelError"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>供应商地址
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" name="address" style="width:300px;">
                            <div class="wql_tips" name="addressError"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>备注
                        </div>
                        <div class="wql_box_r"><textarea name="memo" class="wql_textarea" style="width:300px;"></textarea></div>
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

var supplierNum = '';
var name = '';

function search(){
	supplierNum = $("input[name='searchSupplierNum']").val('');
	name = $("input[name='searchSupplierName']").val('');
	loadList(1);
}


function loadList(pageValue){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/supplierInfo/querySupplierInfo",
		data : {
			"supplierNum" : supplierNum,
			"name" : name,
			"terminalDeviceTypeId" : terminalDeviceTypeId,
			"pageValue" : pageValue
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
						htmlStr += '<td><span class="pdl15">'+obj.supplierNum+'</span></td>';
						htmlStr += '<td>'+obj.name+'</td>';
						htmlStr += '<td>'+obj.creatorName+'</td>';
						htmlStr += '<td>'+new Date(obj.createTime.time).format("yyyy-MM-dd hh:mm:ss")+'</td>';
						htmlStr += '<td><span><a href="javascript:void(0)" onclick="deleteSupplierInfo('+obj.id+')" class="">删除</a></span>  <span><a href="javascript:void(0)" onclick="editSupplierInfo('+obj.id+')" class="">编辑</a></span></td>';
						htmlStr += '</tr>';
					}
				}
				myPage();
			}
			
			if(htmlStr != ''){
				$("#supplierinfoList").html(htmlStr);
			}else{
				$("#supplierinfoList").html('<tr><td colspan="12" align="center"><font color="red">暂无数据</font></td></tr>');
				$("#base_page").html('');
			}
		}
	});
}

function editSupplierInfo(id){
	
	var dialog = art.dialog({
        title:'编辑',
        content:$(".wql_g_artDialog")[0],
        width:'560px',
        okValue:"确定",      
        cancelValue:"取消",
        cancel:true,
        initialize: function() {
        	$.ajax({
 				type : "post",
 				async : true,
 				url : "detailSupplierInfo",
 				data : {
 					id : id
 				},
 				dataType : "json",
 				success : function(data) {
 					$("input[name='name']").val(data.name);
 	     			$("input[name='contact']").val(data.contact);
 	     			$("input[name='contactTel']").val(data.contactTel);
 	     			$("textarea[name='memo']").val(data.memo);//分类名称
 	     			$("input[name='address']").val(data.address);
 				},
 				error : function(errorMsg) {
 				}
 			})
        },
        ok:function() {
        	var name = $("input[name='name']").val();
  			var contact = $("input[name='contact']").val();
  			var contactTel = $("input[name='contactTel']").val();
  			var memo = $("textarea[name='memo']").val();//分类名称
  			var address = $("input[name='address']").val();
			
  			var flag=true;
 			
 			if(name == ''){
 				$("div[name='nameError']").html('供应商名称必填');
 				flag=false;
 			}
 			
 			if(contact == ''){
 				$("div[name='contactError']").html("供应商联系人必填");
 				flag=false;
 			}
 			
 			if(contactTel == '' ){
 				$("div[name='contactTelError']").html("供应商联系方式必填");
 				flag=false;
 			}

 			if(address == ''){
 				$("div[name='addressError']").html("供应商地址必填");
 				flag=false;
 			}
 			
 			
 			if(!flag)
 				return false;
			
 			$.ajax({
 				type : "post",
 				async : true,
 				url : "updateSupplierInfo",
 				data : {
 					id : id,
 					contact:contact,
 					contactTel:contactTel,
 					memo:memo,
 					address : address,
 					name :name
 				},
 				dataType : "text",
 				success : function(msg) {
 					loadList(1)
 				},
 				error : function(errorMsg) {
 				}
 			})
 			
 		}
    });
	
	
}

function deleteSupplierInfo(id){
	myConfirm('系统确认框','是否删除！',function(r){  
        if(r){  
        	$.ajax({
    			type : "POST",
    			url : "${ctx}/manage/supplierInfo/deleteSupplierInfo",
    			data : {
    				"id"  : id
    			},
    			async : false,
    			dataType : 'text',
    			success : function(msg) {
    				
    				
    				if(msg=='0001'){
    					myAlert('系统确认框','该供应商下有订单，不能删除！',function(r){  
    		    	          
    		    	    });
    				}
    				
    				
    				if(msg=='success'){
    					loadList(1)
    				}else{
    					
    					
    				}
    			}
    		});
        }  
    });  
}

//登录浮层
$(function(){
	loadList(1);
	
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

    $(".wql_g_sideNav dd").on('click',function(){
        $(this).addClass('active').siblings('dd').removeClass('active');

        $(this).siblings('dt').removeClass('on')

        terminalDeviceTypeId = $(this).children("span[name=terminalDeviceTypeId]").text();
        terminalName = $(this).children("span[name=terminalName]").text();
        loadList(1)
        
        
        if($(this).prevUntil('dt').length>0){
             $(this).prevUntil('dt').last().prev().addClass('on')
        }
        else{
             $(this).prev().addClass('on')
        }
        
    })

    $(".wql_btn_search").on("click",function(){
      
         supplierNum = $("input[name='searchSupplierNum']").val();
   		name =  $("input[name='searchSupplierName']").val();
   		loadList(1);
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
    	
    	
    	if(terminalDeviceTypeId==null ||terminalDeviceTypeId==''){
				myAlert('系统确认框','请选择终端设备！',function(r){  
	    	          
	    	    });
				return ;
			}
    	
    	$("input[name='name']").val('');
		$("input[name='contact']").val('');
		$("textarea[name='memo']").val('');//分类名称
		$("input[name='contactTel']").val('');
		$("input[name='address']").val('');
    	
		
		$("div[name='nameError']").html('');
		$("div[name='contactError']").html('');
		$("div[name='contactTelError']").html('');
		$("div[name='addressError']").html('');
		
		
		
        var dialog = art.dialog({
            title:'添加供应商',
            content:$(".wql_g_artDialog")[0],
            width:'560px',
            okValue:"确定",
            padding:'30px 40px',
            cancelValue:"取消",
            cancel:true,
            ok:function() {
            	
    			$("div[name='nameError']").html('');
     			$("div[name='contactError']").html('');
     			$("div[name='contactTelError']").html('');
     			$("div[name='addressError']").html('');
            	
            	var name = $("input[name='name']").val();
     			var contact = $("input[name='contact']").val();
     			var contactTel = $("input[name='contactTel']").val();
     			var memo = $("textarea[name='memo']").val();//分类名称
     			var address = $("input[name='address']").val();
     			
     			if(terminalDeviceTypeId==null ||terminalDeviceTypeId==''){
     				myAlert('系统确认框','请选择终端设备类型！',function(r){  
     	    	          
     	    	    });
     				return ;
     			}
     			
				var flag=true;
     			
     			if(name == ''){
     				$("div[name='nameError']").html('供应商名称必填');
     				flag=false;
     			}
     			
     			if(contact == ''){
     				$("div[name='contactError']").html("供应商联系人必填");
     				flag=false;
     			}
     			
     			if(contactTel == '' ){
     				$("div[name='contactTelError']").html("供应商联系方式必填");
     				flag=false;
     			}

     			if(address == ''){
     				$("div[name='addressError']").html("供应商地址必填");
     				flag=false;
     			}
     			
     			if(!flag)
     				return false;
     			
     			setTimeout(function(){
     			
     				$.ajax({
         				type : "post",
         				async : true,
         				url : "saveSupplierInfo",
         				data : {
         					name : name,
         					contact:contact,
         					memo:memo,
         					contactTel:contactTel,
         					address:address,
         					terminalDeviceTypeId :terminalDeviceTypeId
         				},
         				dataType : "text",
         				success : function(msg) {
         				
         					if(msg=="success"){
         						loadList(1);
         					}else{
         						myAlert('系统消息',msg,function(r){  
         	     	    	          
         	     	    	    });
         					}
         					
         				},
         				error : function(errorMsg) {
         				}
         			})
		
				}, 1);
     		}
        });
    });

})



</script>