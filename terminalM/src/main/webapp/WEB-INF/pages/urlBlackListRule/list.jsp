<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>


<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">设备管理 &gt;</span><span class="on mgl5">规则管理</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
            <div class="wql_g_tit">
                <div class="wql_tit01 clearfix">
                    <div class="wql_g_nav fl mgt10">
                        <div class="wql_nav01">
                            <ul class="clearfix">
                                <li class="wql_li"><a href="${ctx}/manage/useTimeRule/list">使用时段设置</a></li>
                                <li class="wql_li on"><span class="wql_bl"></span><a href="${ctx}/manage/urlBlackListRule/list">网址黑名单</a></li>
                                <li class="wql_li"><span class="wql_bl"></span><a href="${ctx}/manage/appWhitelistRule/list">应用白名单</a></li>
                                <li class="wql_li"><span class="wql_bl"></span><a href="${ctx}/manage/browserWhitelistRule/list">浏览器上网设置</a></li>
                                <li class="wql_li"><span class="wql_bl"></span><a href="${ctx}/manage/browserTagRule/list">浏览器上网标签设置</a></li>                            
                            	<li class="wql_li"><span class="wql_bl"></span><a href="${ctx}/manage/appNetBlackList/list">应用网络黑名</a></li>
                            </ul>
                        </div>
                    </div>
                    
                    <div class="wql_g_btn mgt8 fr">
			            <div class="wql_btn01">
			                 <a href="#" class="wql_addBtn">添加规则</a>
			            </div>
			        </div>
                </div>
            </div>
 
            
             <c:forEach items="${list}" var="obj" varStatus="status">
            <div class="wql_g_box mgt20">
            	<div class="wql_box01 f14 lh24 pdlr20 pdtb15 wql_bg_eef5fd w6em">
            		<div class="clearfix">
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">规则名称</div>
            				<div class="wql_box_r">${obj.name }</div>
            			</div>
            			<div class="wql_box_cont fl">
                            <div class="wql_box_l c888 t_r">黑名单网址数</div>
                            <div class="wql_box_r">${obj.urlTotal}个</div>
                        </div>
            			<div class="wql_g_btn fr">
            				<div class="wql_btn07">
            					<a href="javascript:;" class="wql_edit mgr20" onclick="detailRule('${obj.id}')"></a>
            					<a href="javascript:;" class="wql_del" onclick="deleteRule('${obj.id}')"></a>
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
            
            
            
            <form id="pageForm"  name="pageForm" action="${ctx}/manage/urlBlackListRule/list" method="post">
	            <div class="mgt40">
	                <c:if test="${not empty page.totalPage && page.totalPage > 1 }">
								<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
								currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
								action="${ctx}/manage/urlBlackListRule/list"></newTag:page>
					 </c:if>
	            </div>
            </form>
        </div>

        <!-- 主体部分 E-->

    </div>
</div>


<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        
        <div class="wql_g_list01 w7em">
            <ul class="wql_ul">
               
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>规则名称：
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" name="name" style="width:395px;" placeholder="">
                            <div class="wql_tips" name="nameError"></div>
                        </div>
                    </div>
                </li>
             
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>备注：
                        </div>
                        <div class="wql_box_r">
                            <textarea class="wql_textarea" name="memo" style="width:395px;"></textarea>
                        </div>
                    </div>
                </li>
                
                
                
            </ul>
        </div>
        <div class=" mgt30">
            <div class="clearfix">
                <div class="fl lh32 f18">黑名单网址列表</div>
                <div class="wql_g_btn fr">
                    <div class="wql_btn01 clearfix">
                        <a href="javascript:;" class="fl mgr20 wql_add_URL">添加黑名单</a>
                        <!-- <a href="javascript:;" class="fl wql_remove_URL">批量移除黑名单</a> -->
                    </div>
                </div>
            </div>
            <div class="site_sysTable wql_g_table mgt15 chklist2">
                <table class="wql_table02 t_c">
                    <thead>
                        <tr>

                            <th>网址</th>
                            <th width="100">操作</th>
                        </tr>
                    </thead>
                    <tbody id="urlBlackList">
                    
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
                            <em>*</em>请输入网址：
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" name="url" style="width:395px;" placeholder="">
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>


<script type="text/javascript">


function detailRule(id){
	$("input[name='name']").val('');//分类名称
 	$("textarea[name='memo']").val('');//备注
 	$("#urlBlackList").children("tr").remove();
 	
 	
	$.ajax({
 		type : "POST",
 		url : "${ctx}/manage/urlBlackListRule/detailUrlBlackList",
 		traditional:true,
 		data :{
 			"id" :id
 		},
 		async : false,
 		dataType : 'json',
 		success : function(data) {
 			$("input[name='name']").val(data.name);//分类名称
         	$("textarea[name='memo']").val(data.memo);//备注
         	
 			console.log(data)
         	var urlList = data.urlList;
       
         	console.log(urlList)
         	
         	for(var i=0;i<urlList.length;i++){
         		 var tr = '<tr class="wl_addTr">'+
                 '   <td><span name="url">'+urlList[i].url+'</span></td>'+
                 '    <td><span><a href="javascript:;" class="wql_delBtn" >移除</a></span></td>'+
                '</tr>'
                
        		$('#urlBlackList').append(tr);
         	}
 		}
 	});
	
	 var dialog = art.dialog({
         title:'黑名单规则设置',
         content:$(".wql_g_artDialog")[0],
         width:'500px',
         okValue:"确定",
         padding:'20px',
         okValue:"确定",
         traditional:true,
         cancelValue:"退出",
         ok:function(){
        	var name = $("input[name='name']").val();//分类名称
         	var memo = $("textarea[name='memo']").val();//备注
         	var urlBlacklList = $("#urlBlackList").children("tr");
         	
         	var urlList =[];
         	
         	urlBlacklList.each(function(){ //便利除标题行外所有行        			
     			var url = $(this).find("span[name='url']").text();
     			
     			urlList.push(url)
     			
         	});
         	
         	var flag=true;
         	
     		if(name==''){
     			$("div[name='nameError']").html('请输入规则名称');
     			flag=false;
     		}
         	
     		if(!flag)
     			return false;
     		
     		
         	$.ajax({
         		type : "POST",
         		url : "${ctx}/manage/urlBlackListRule/updateUrlBlackList",
         		traditional:true,
         		data :{
         			"name" : name,
         			"memo" : memo,
         			"ruleId" :id,
         			"urlList" : urlList
         		},
         		async : false,
         		dataType : 'text',
         		success : function(msg) {
         			if(msg=='success'){
         				$("#pageForm").submit();
         			}
         		}
         	});
         	
         },
         cancel:true
         
     })
	
}

function deleteRule(id){
	
	myConfirm('系统确认框','是否删除！',function(r){
		 if(r){  
			 $.ajax({
					type : "POST",
					url : "${ctx}/manage/urlBlackListRule/deleteUrlBlackList",
					traditional:true,
					data :{
						"id" : id
					},
					async : false,
					dataType : 'text',
					success : function(msg) {
						if(msg=='success'){
							$("#pageForm").submit();
						}else{
							myAlert('系统消息',msg,function(r){  
	 	     	    	          
	 	     	    	    });
						}
					}
				});
		 }
	})
}

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

   
   
   $('#urlBlackList').on('click','.wql_delBtn', function(){
	   var tr = $(this).parent("tr");
	   //console.log($(this).text())
	   $(this).parent().parent().parent().remove();
   });
   
    $('.wql_addBtn').on('click',function(){
    	$("input[name='name']").val('');//分类名称
    	
    	$("textarea[name='memo']").val('');//备注
    	
    	$("#urlBlackList").children("tr").remove();
    	
        var dialog = art.dialog({
            title:'黑名单规则设置',
            content:$(".wql_g_artDialog")[0],
            width:'500px',
            okValue:"确定",
            padding:'20px',
            okValue:"确定",
            traditional:true,
            cancelValue:"退出",
            ok:function(){
            	
            	var name = $("input[name='name']").val();//分类名称
            	var memo = $("textarea[name='memo']").val();//备注
            	
            	var urlBlacklList = $("#urlBlackList").children("tr");
            	
            	var urlList =[];
            	
            	urlBlacklList.each(function(){ //便利除标题行外所有行        			
        			var url = $(this).find("span[name='url']").text();
        			
        			urlList.push(url)
        			//console.log(url);
            	});
            	
            	var flag=true;
            	
        		if(name==''){
        			$("div[name='nameError']").html('请输入规则名称');
        			flag=false;
        		}
            	
        		if(!flag)
        			return false;
        		
            	
        		
            	$.ajax({
            		type : "POST",
            		url : "${ctx}/manage/urlBlackListRule/saveUrlBlackList",
            		traditional:true,
            		data :{
            			"name" : name,
            			"memo" : memo,
            			"urlList" : urlList
            		},
            		async : false,
            		dataType : 'text',
            		success : function(msg) {
            			if(msg=='success'){
            				$("#pageForm").submit();
            			}
            		}
            	});
            	
            },
            cancel:true
            
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
    	
    	$("input[name='url']").val('');
    	
        var dialog = art.dialog({
            title:'添加黑名单网址',
            content:$(".wql_g_artDialog")[1],
            width:'500px',
            okValue:"确定",
            padding:'20px',
            okValue:"确定",
            cancelValue:"退出",
            ok:function(){
            	
            	var url = $("input[name='url']").val();
            	 var tr = '<tr class="wl_addTr">'+
                 '   <td><span name="url">'+url+'</span></td>'+
                 '    <td><span><a href="javascript:;" class="wql_delBtn" >移除</a></span></td>'+
                '</tr>'
        		$('#urlBlackList').append(tr);
            	
            },
            cancel:true
        })
    })
   
})



</script>
