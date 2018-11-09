<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>


<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">班级规则 &gt;</span><span class="on mgl5">浏览器标签规则</span>
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
            				<div class="wql_box_l c888 t_r">标签网址数</div>
            				<div class="wql_box_r">${obj.urlTotal}个</div>
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
            <form id="pageForm"  name="pageForm" action="${ctx}/manage/schoolClassRule/addSchoolClassRulePage" method="post">
            
            <input type="hidden" name="ruleType" value="${ruleType }">
            <input type="hidden" name="schoolClassId" value="${schoolClassId }">
            
	            <div class="mgt40">
	                <c:if test="${not empty page.totalPage && page.totalPage > 1 }">
								<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
								currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
								action="${ctx}/manage/schoolClassRule/addSchoolClassRulePage"></newTag:page>
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
                        	<span name="ruleName"></span>
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
                <div class="fl lh32 f18">浏览器标签网址列表</div>
                <div class="wql_g_btn fr">
                    <div class="wql_btn01 clearfix">
                        <!-- <a href="javascript:;" class="fl mgr20 wql_add_URL">添加网址</a> -->
                        <!-- <a href="javascript:;" class="fl wql_remove_URL">批量移除黑名单</a> -->
                    </div>
                </div>
            </div>
            <div class="site_sysTable wql_g_table mgt15 chklist2">
                <table class="wql_table02 t_c">
                    <thead>
                        <tr>
                            <th>网址</th>
                            <th>标签</th>
                            <th>Logo</th>
                            <th>分组编号</th>
                            <th>分组名称</th>
                        </tr>
                    </thead>
                    <tbody id="tagList">
                    
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
               
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            		网址：
                        </div>
                        <div class="wql_box_r">
                        	<span name="tagUrl"></span>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	标签：
                        </div>
                        <div class="wql_box_r">
                        	<span name="tagName"></span>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	网址logo：
                        </div>
                        <div class="wql_box_r">
                        	<span name="tagLogo"></span>
                        </div>
                    </div>
                </li>
                
                 <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	分组编号：
                        </div>
                        <div class="wql_box_r">
                        	<span name="tagGroupNum"></span>
                        </div>
                    </div>
                </li>
                
                 <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	分组名称：
                        </div>
                        <div class="wql_box_r">
                        	<span name="tagGroupName"></span>

                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>


<script type="text/javascript">


function detailRule(id){
	$("span[name='name']").text('');//分类名称
 	$("span[name='memo']").text('');//备注
 	$("#tagList").children("tr").remove();
 	
	$.ajax({
 		type : "POST",
 		url : "${ctx}/manage/browserTagRule/detailBrowserTagRule",
 		traditional:true,
 		data :{
 			"id" :id
 		},
 		async : false,
 		dataType : 'json',
 		success : function(data) {
 			$("span[name='ruleName']").text(data.name);//分类名称
         	$("span[name='memo']").text(data.memo);//备注
         	
 			
         	var tagList = data.browserTagList;
       
         	for(var i=0;i<tagList.length;i++){
         		 var tr = '<tr class="wl_addTr">'+
                 '   <td><span name="url">'+tagList[i].url+'</span></td>'+
                 '   <td><span name="name">'+tagList[i].name+'</span></td>'+
                 '   <td><span name="logo">'+tagList[i].logo+'</span></td>'+
                 '   <td><span name="groupNum">'+tagList[i].groupNum+'</span></td>'+
                 '   <td><span name="groupName">'+tagList[i].groupName+'</span></td>'+
                '</tr>'
                
        		$('#tagList').append(tr);
         	}
 		}
 	});
	
	 var dialog = art.dialog({
         title:'黑名单规则设置',
         content:$(".wql_g_artDialog")[0],
         width:'500px',
         okValue:"确定",
         padding:'20px',
         traditional:true,
         cancelValue:"退出",
         cancel:true
         
     })
	
}

function deleteRule(id){
	
	myConfirm('系统确认框','是否删除！',function(r){
		 if(r){  
			 $.ajax({
					type : "POST",
					url : "${ctx}/manage/browserTagRule/deleteBrowserTagRule",
					traditional:true,
					data :{
						"id" : id
					},
					async : false,
					dataType : 'text',
					success : function(msg) {
						if(msg=='success'){
							$("#pageForm").submit();
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

   
   
   $('#tagList').on('click','.wql_delBtn', function(){
	   var tr = $(this).parent("tr");
	   console.log($(this).text())
	   $(this).parent().parent().parent().remove();
   });
   
    $('.wql_addBtn').on('click',function(){
		var ruleId = $(".hRadio_Checked").find("input[name='ruleId']").val();
    	
    	var schoolClassId = $("input[name='schoolClassId']").val()
    	
    	$.ajax({
				type : "post",
				async : true,
				url : "saveSchoolClassBrowserTagRule",
				data : {
					ruleId:ruleId,
					schoolClassId : schoolClassId,
				},
				dataType : "text",
				success : function(msg) {
					window.location.href = "list?schoolClassId="+schoolClassId;
				},
				error : function(errorMsg) {
				}
			})
    })
    
    
    $('.wql_remove_URL').on('click',function(){
    	var tr = $('.chkAll.sys_checkbox.sys_checked').parent().parent();
    	
    	$('.chkAll.sys_checkbox.sys_checked').parent().parent().each(function(){                   // 遍历 tr
    	  
    	});
    	
    	//console.log(tr)
    	//tr.remove();
    });
    
    $('.wql_add_URL').on('click',function(){
    	$("input[name='tagUrl']").val('');
    	$("input[name='tagName']").val('');
    	$("input[name='tagLogo']").val('');
    	$("input[name='tagGroupNum']").val('');
    	$("input[name='tagGroupName']").val('');
    	
        var dialog = art.dialog({
            title:'添加标签',
            content:$(".wql_g_artDialog")[1],
            width:'500px',
            okValue:"确定",
            padding:'20px',
            okValue:"确定",
            cancelValue:"退出",
            ok:function(){
            	
            	var url = $("input[name='tagUrl']").val();
            	var name = $("input[name='tagName']").val();
            	var logo = $("input[name='tagLogo']").val();
            	var groupNum = $("input[name='tagGroupNum']").val();
            	var groupName = $("input[name='tagGroupName']").val();
            	
            	
				var flag=true;
            	
        		if(url==''){
        			$("div[name='tagUrlError']").html('请输入网址');
        			flag=false;
        		}
            	
        		if(name==''){
        			$("div[name='tagNameError']").html('请输入标签');
        			flag=false;
        		}
        		
        		if(logo==''){
        			$("div[name='tagLogoError']").html('请输入网址logo');
        			flag=false;
        		}
        		
        		if(groupNum==''){
        			$("div[name='tagGroupNumError']").html('请输入分组编号');
        			flag=false;
        		}
        		
        		if(groupName==''){
        			$("div[name='tagGroupNameError']").html('请输入分组名称');
        			flag=false;
        		}
        		
        		if(!flag)
        			return false;
            	
            	
            	 var tr = '<tr class="wl_addTr">'+
                 '   <td><span name="url">'+url+'</span></td>'+
                 '   <td><span name="name">'+name+'</span></td>'+
                 '   <td><span name="logo">'+logo+'</span></td>'+
                 '   <td><span name="groupNum">'+groupNum+'</span></td>'+
                 '   <td><span name="groupName">'+groupName+'</span></td>'+
                 '    <td><span><a href="javascript:;" class="wql_delBtn" >移除</a></span></td>'+
                '</tr>'
        		$('#tagList').append(tr);
            	
            },
            cancel:true
        })
    })
   
})



</script>
