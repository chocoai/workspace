<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<link rel="stylesheet" href="http://apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
<style>

.ui-autocomplete{
       z-index: 11111111111;
	  
}

</style>

<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">班级规则设置&gt;</span><span class="on mgl5">应用白名单</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
           
            <div class="wql_g_tit">
                <div class="wql_tit01 clearfix">
                    <div class="wql_g_btn fr">
                        <div class="wql_btn01">
                             <a href="#" class="wql_addBtn">确定</a>
                        </div>
                    </div>
                </div>
            </div>
            <span class="radiolist2 clearfix">
             <c:forEach items="${list}" var="obj" varStatus="status">
            <div class="wql_g_box mgt20">
            	<div class="wql_box01 f14 lh24 pdlr20 pdtb15 wql_bg_eef5fd w6em">
            		<div class="clearfix">
            			<div class="wql_box_cont fl">
            				<label class="sys_hRadio hRadio mgr15">
            				<input type="radio" name="ruleId" value="${obj.id }" style="display: none;"></label>
            			</div>
            		
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">规则名称</div>
            				<div class="wql_box_r">${obj.name }</div>
            			</div>
            			<div class="wql_box_cont fl">
                            <div class="wql_box_l c888 t_r">应用数量</div>
                            <div class="wql_box_r">${obj.totalNum}个</div>
                        </div>
            			<div class="wql_g_btn fr">
            				<div class="wql_btn07">
            					<a href="javascript:;" class="wql_edit mgr20" onclick="detailRule('${obj.id}')"></a>            					
            				</div>
            			</div>
            		</div>
            		<div class="clearfix mgt20">
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">备注</div>
            				<div class="wql_box_r">
            				<c:if test="${not empty obj.memo }">
            					${obj.memo}
            				</c:if>
            				<c:if test="${empty obj.memo }">
            					无备注
            				</c:if>
            				</div>
            			</div>
            			
            		</div>
            	</div>
            </div>
            </c:forEach>
            </span>
            
            
            <form id="pageForm"  name="pageForm" action="${ctx}/manage/appWhitelistRule/list" method="post">
            
            <input type="hidden" name="ruleType" value="${ruleType }">
            <input type="hidden" name="schoolClassId" value="${schoolClassId }">
            
	            <div class="mgt40">
	                <c:if test="${not empty page.totalPage && page.totalPage > 1 }">
								<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
								currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
								action="${ctx}/manage/urlBlackListRule/list"></newTag:page>
					 </c:if>
	            </div>
            </form>
        </div>

    </div>
</div>


<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        
        <div class="wql_g_list01 w7em">
            <ul class="wql_ul">
               
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	规则名称：
                        </div>
                        <div class="wql_box_r">
                        	<span name="name"></span>
                        </div>
                    </div>
                </li>
             
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>备注：
                        </div>
                        <div class="wql_box_r">
                        	<span name="memo"></span>
                        </div>
                    </div>
                </li>
                
                
                
            </ul>
        </div>
        <div class=" mgt30">
            <div class="clearfix">
                <div class="fl lh32 f18">应用列表</div>
                <div class="wql_g_btn fr">
                    <div class="wql_btn01 clearfix">
                        <!-- <a href="javascript:;" class="fl mgr20 wql_add_URL">添加应用</a> -->
                    </div>
                </div>
            </div>
            <div class="site_sysTable wql_g_table mgt15 chklist2">
                <table class="wql_table02 t_c">
                    <thead>
                        <tr>
                            <th>应用</th>
                        </tr>
                    </thead>
                    <tbody id="appList">
                    
                    </tbody>
                </table>
            </div>
            <div class="mgt40">
               
            </div>
        </div>
    </div>
</div>

<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        
        <div class="wql_g_list01 w7em">
            <ul class="wql_ul">
               
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>请输入应用名：
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" name="appName"  id="appName" style="width:395px;" >
                            <input type="hidden" name="appId" />
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

<script src="http://apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script type="text/javascript">


function detailRule(id){
	$("span[name='name']").text('');//分类名称
 	$("span[name='memo']").text('');//备注
 	$("#appList").children("tr").remove();
 	
 	
 	
 	
	$.ajax({
 		type : "POST",
 		url : "${ctx}/manage/appWhitelistRule/detailAppWhitelistRule",
 		traditional:true,
 		data :{
 			"id" :id
 		},
 		async : false,
 		dataType : 'json',
 		success : function(data) {
 			$("span[name='name']").text(data.name);//分类名称
         	$("span[name='memo']").text(data.memo);//备注

         	var appWhiteList = data.appWhitelist;
       		
         	for(var i=0;i<appWhiteList.length;i++){
         		
         		 var tr = '<tr class="wl_addTr">'+
                 '   <td><span name="'+appWhiteList[i].appInfoId+'">'+appWhiteList[i].appInfoName+'</span><input type="hidden" name="tdAppId" value="'+appWhiteList[i].appInfoId+'"></td>'+
                 '</tr>'
                
        		$('#appList').append(tr);
         	}
 		}
 	});
	
	 var dialog = art.dialog({
         title:'添加应用',
         content:$(".wql_g_artDialog")[0],
         width:'500px',
         okValue:"确定",
         padding:'20px',
         traditional:true,
         cancelValue:"退出",
         cancel:true
         
     })
	
}


$(function(){
	
	
	 $("#appName").autocomplete({
	      source: function(request, response ){
	    	  $.ajax({  
                  url : "${ctx}/manage/appWhitelistRule/findAppList",  
                  type : "post",  
                  dataType : "json",  
                  data : {"appName":$("#appName").val()},  
                  success: function( data ) {  
                       response( $.map( data.list, function( item ) {  
                             return {
                               label: item.name,
                               value: item.name,
                               id:item.id//选中后，填充到id里面的值  
                             }  
                  }));  
                 }  
            });
	      },
	 	  
     	select: function( event, ui ) {  
     		$("input[name='appId']").val(ui.item.id);
    	 } 
	  });
	
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

    //模拟多选
    $('.wql_g_sel .wql_selbtn01 a').on("click",function(){
        $(this).hasClass('active')?$(this).removeClass('active'):$(this).addClass('active');
    })

   $('.radiolist2').each(function(){
   		var that = $(this)
   		that.find('label').on('click',function(){
   			that.find('label.hRadio_Checked').text()==='自定义'?that.next('.wql_user_defined').show():that.next('.wql_user_defined').hide();
   		})
   })

   
   
   $('#appList').on('click','.wql_delBtn', function(){
	   var tr = $(this).parent("tr");
	   console.log($(this).text())
	   $(this).parent().parent().parent().remove();
   });
   
    $('.wql_addBtn').on('click',function(){
		var ruleId = $(".hRadio_Checked").find("input[name='ruleId']").val();
    	
    	var schoolClassId = $("input[name='schoolClassId']").val()
    	
    	$.ajax({
				type : "post",
				async : true ,
				url : "saveSchoolClassAppWhitelistRule",
				data : {
					ruleId : ruleId,
					schoolClassId : schoolClassId,
				},
				dataType : "text",
				success : function(msg) {
					console.log(msg);
					if(msg=='success'){
						window.location.href = "list?schoolClassId="+schoolClassId;					
					}else{
						myAlert('系统确认框',msg,function(r){  
  		    	          
    		    	    });
					}
				},
				error : function(errorMsg) {
				}
			})
    })
    
    
    $('.wql_remove_URL').on('click',function(){
    	var tr = $('.chkAll.sys_checkbox.sys_checked').parent().parent();
    	
    	$('.chkAll.sys_checkbox.sys_checked').parent().parent().each(function(){                   // 遍历 tr
    	     //alert(1)
    	});
    	
    	//console.log(tr)
    	//tr.remove();
    });
    
    $('.wql_add_URL').on('click',function(){
        var dialog = art.dialog({
            title:'添加黑名单网址',
            content:$(".wql_g_artDialog")[1],
            width:'500px',
            okValue:"确定",
            padding:'20px',
            okValue:"确定",
            cancelValue:"退出",
            ok:function(){
            	
            	var appId = $("input[name='appId']").val();
            	var appName = $("#appName").val();
            	
            	 var tr = '<tr class="wl_addTr">'+
                 '   <td><span name="'+appId+'">'+appName+'</span><input type="hidden" name="tdAppId" value="'+appId+'"></td>'+
                 '    <td><span><a href="javascript:;" class="wql_delBtn" >移除</a></span></td>'+
                '</tr>'
        		$('#appList').append(tr);
            	
            },
            cancel:true
        })
    })
   
})



</script>
