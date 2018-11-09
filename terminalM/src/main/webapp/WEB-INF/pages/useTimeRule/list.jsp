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
                                 <li class="wql_li on"><a href="${ctx}/manage/useTimeRule/list">使用时段设置</a></li>
                                <li class="wql_li"><span class="wql_bl"></span><a href="${ctx}/manage/urlBlackListRule/list">网址黑名单</a></li>
                                <li class="wql_li"><span class="wql_bl"></span><a href="${ctx}/manage/appWhitelistRule/list">应用白名单</a></li>
                                <li class="wql_li "><span class="wql_bl"></span><a href="${ctx}/manage/browserWhitelistRule/list">浏览器上网设置</a></li>
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
            	<div class="wql_box01 f14 lh24 pdlr20 pdtb15 wql_bg_eef5fd w5em">
            		<div class="clearfix">
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">规则名称</div>
            				<div class="wql_box_r">${obj.name}</div>
            			</div>
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">可使用日期</div>
            				<div class="wql_box_r">
            				<c:if test="${not empty obj.typeName }">
            					${obj.typeName }
            				</c:if>
            				<c:if test="${empty obj.typeName }">
            					无日期
            				</c:if>
            				</div>
            			</div>
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">可使用时点</div>
            				<div class="wql_box_r">${obj.startTime}--${obj.endTime}</div>
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
             <form id="pageForm"  name="pageForm" action="${ctx}/manage/useTimeRule/list" method="post">
	            <div class="mgt40">
	                 <c:if test="${not empty page.totalPage && page.totalPage > 1 }">
								<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
								currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
								action="${ctx}/manage/useTimeRule/list"></newTag:page>
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
                            <input class="wql_inp" type="text" name="name" style="width:395px;" >
                            <div class="wql_tips" name="nameError"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>可使用日期：
                        </div>
                        <div class="wql_box_r">
                            <span class="radiolist2 clearfix">
                                <label class="sys_hRadio hRadio mgr15 dateType" name="dataTypeLabel">
                                <input type="radio" name="dateType" value="1" style="display: none;">无限制</label><label class="sys_hRadio hRadio mgr15 dateType"><input type="radio" name="dateType" value="2" style="display: none;">自定义</label>
                            </span>
                            <div class="wql_user_defined dis_none wql_bg_f7f7f7 pd20 mgt20 dateType">
                            	<div class="radiolist2 clearfix">
                            		<label class="sys_hRadio hRadio mgr15 useTimeType">
                            		<input type="radio" name="useTimeType" value="2" style="display: none;">国家法定节假日和双休日</label><br>
                            		
                            		<label class="sys_hRadio hRadio mgr15 useTimeType">
                            		<input type="radio" name="useTimeType" value="3" style="display: none;">正常工作日</label><br>
                            		
                            		<label class="sys_hRadio hRadio mgr15 useTimeType">
                            		<input type="radio" name="useTimeType" value="4" style="display: none;">日期段自定义</label><br>
                            		
                            	</div>
                            	<div class="seleautoBox ">
	                                <div class="seleautoBox ">
	                                <input id="d4311" name = "startDate" class="Wdate site_sysinp wd140" type="text" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'%y-%M-%d'})">
	                                - <input id="d4312" name="endDate" class="Wdate site_sysinp wd140" type="text" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'d4311\')}'})">
	                            </div>
	                            </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>可使用时点：
                        </div>
                        <div class="wql_box_r">
                            <span class="radiolist2 clearfix">
                                <label class="sys_hRadio hRadio mgr15 timeDateType" name="timeDataTypeLabel"><input type="radio" name="timeDateType" value="1" style="display: none;">无限制</label><label class="sys_hRadio hRadio mgr15 timeDateType" name="timeDataTypeLabel"><input type="radio" name="timeDateType" value="2" style="display: none;">自定义</label>
                            </span>
                            <div class="wql_user_defined dis_none wql_bg_f7f7f7 pd20 mgt20 timeType">
                            	<div class="radiolist2 clearfix">
                            		<label class="sys_hRadio hRadio mgr15 timeType">
                            		<input type="radio" name="timeType" value="2" style="display: none;">08:00--18:00</label><br>
                            		<label class="sys_hRadio hRadio mgr15 timeType">
                            		<input type="radio" name="timeType" value="3" style="display: none;">08:00--21:00</label><br>
                            		<label class="sys_hRadio hRadio mgr15 timeType">
                            		<input type="radio" name="timeType" value="4" style="display: none;">日期段自定义</label>
                            	</div>
                            	<div class="seleautoBox ">
	                                <input  name="startTime" class="Wdate site_sysinp  wd140"  type="text" onfocus="WdatePicker({dateFmt:'HH:mm',isShowToday:false,})">
	                                - <input  name="endTime" class="Wdate site_sysinp  wd140" type="text" onfocus="WdatePicker({dateFmt:'HH:mm',isShowToday:false,})">
	                            </div>
                            </div>
                        </div>
                    </div>
                </li>

                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>备注：
                        </div>
                        <div class="wql_box_r">
                            <textarea class="wql_textarea" name ="memo" style="width:395px;"></textarea>
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
 	//$("textarea[name='memo']").val('');//备注
 	$("div[name='nameError']").html('');
 	$(".hRadio_Checked").removeClass("hRadio_Checked");
 	$("input[name='memo']").val('');//分类名称
 	var ruleName = '';
	$.ajax({
 		type : "POST",
 		url : "${ctx}/manage/useTimeRule/detailUseTimeRule",
 		traditional:true,
 		data :{
 			"id" :id
 		},
 		async : false,
 		dataType : 'json',
 		success : function(data) {
 			console.log(data)
 			$("input[name='name']").val(data.name);
 			$("textarea[name='memo']").text(data.memo);
 			
 			var dateType = data.dateType;
 			ruleName = data.name;
 			
 			if(dateType == 1){
 				$(".sys_hRadio.hRadio.mgr15.dateType").eq(0).addClass("hRadio_Checked"); 
 				$(".sys_hRadio.hRadio.mgr15.useTimeType").eq(3).addClass("hRadio_Checked"); 
 			}else{
 				var dateTypeNode = $(".sys_hRadio.hRadio.mgr15.dateType").eq(1);
 				dateTypeNode.addClass('hRadio_Checked');
 			
 				if(dateType ==2){
 					$(".sys_hRadio.hRadio.mgr15.useTimeType").eq(0).addClass("hRadio_Checked");
 				}
 				
				if(dateType ==3){
					$(".sys_hRadio.hRadio.mgr15.useTimeType").eq(1).addClass("hRadio_Checked");
				}
				 				
				if(dateType ==4){
					$(".sys_hRadio.hRadio.mgr15.useTimeType").eq(2).addClass("hRadio_Checked");
					
					$("input[name='startDate']").val(new Date(data.startDate.time).format("yyyy-MM-dd"));
		 			$("input[name='endDate']").val(new Date(data.endDate.time).format("yyyy-MM-dd"));
				}
				$(".wql_user_defined.dis_none.wql_bg_f7f7f7.pd20.mgt20.dateType").show();
 			}
 			
 			var timeType = data.timeType;
 			
 			if(timeType == 1){
 				$(".sys_hRadio.hRadio.mgr15.timeDateType").eq(0).addClass("hRadio_Checked"); 
 			}else{
 				$(".sys_hRadio.hRadio.mgr15.timeDateType").eq(1).addClass("hRadio_Checked"); 
 				
 				$(".wql_user_defined.dis_none.wql_bg_f7f7f7.pd20.mgt20.timeType").show();
 				
 				if(timeType==2){
 					$(".sys_hRadio.hRadio.mgr15.timeType").eq(0).addClass("hRadio_Checked");	
 				}
 				
				if(timeType==3){
					$(".sys_hRadio.hRadio.mgr15.timeType").eq(1).addClass("hRadio_Checked");
 				}
 				
				if(timeType==4){
					$(".sys_hRadio.hRadio.mgr15.timeType").eq(2).addClass("hRadio_Checked");
					$("input[name='startTime']").val(data.startTime);
 		 			$("input[name='endTime']").val(data.endTime);
				}
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
        	 	 
        	 
           		var name = $("input[name='name']").val();
            	
            	var dateType = $('.sys_hRadio.hRadio.mgr15.dateType.hRadio_Checked').find("input[name='dateType']").val();
            	var useTimeType = $('.sys_hRadio.hRadio.mgr15.useTimeType.hRadio_Checked').find("input[name='useTimeType']").val();      
            	var timeDateType = $('.sys_hRadio.hRadio.mgr15.timeDateType.hRadio_Checked').find("input[name='timeDateType']").val();
            	var timeType = $('.sys_hRadio.hRadio.mgr15.timeType.hRadio_Checked').find("input[name='timeType']").val();
            	
            	var startTime =  $("input[name='startTime']").val();
            	var endTime =  $("input[name='endTime']").val();
            	var memo = $("textarea[name='memo']").val();//分类名称
            	var startDate = $("input[name='startDate']").val();
            	var endDate = $("input[name='endDate']").val();
            	
            	console.log(startDate+":"+endDate);
            	
            	var flag=true;
             	
         		if(name==''){
         			$("div[name='nameError']").html('请输入规则名称');
         			flag=false;
         		}
             	
         		if(!flag)
         			return false;
            	

            	if(timeType == 2){
            		startTime = "08:00";
            		endTime = "18:00";
            	}
            	
            	if(timeType == 3){
            		startTime = "08:00";
            		endTime = "21:00";
            	}

            	if(dateType != 1){
            		dateType = useTimeType
            	}
            	
            	if(timeDateType != 1){
            		timeDateType = timeType;
            	}
         	
            	$.ajax({
     				type : "post",
     				async : false,
     				url : "updateUseTimeRule",
     				data : {
     					oldRuleName:ruleName,
     					name:name,
     					id:id,
     					timeType : timeDateType,
     					startTime : startTime,
     					endTime : endTime,
     					dateType :  dateType,
     					startDate : startDate,
     					endDate : endDate,
     					memo : memo
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
            	
            	
            	
         },
         cancel:true
         
     })
	
}


function deleteRule(id){
	
	myConfirm('系统确认框','是否删除！',function(r){
		 if(r){  
			 $.ajax({
					type : "POST",
					url : "${ctx}/manage/useTimeRule/deleteUseTimeRule",
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
   			$(this).find('input').click(function(e){
   				
   				var type = $(this).attr("type");
   				if(type='redio'){
   					var name = $(this).attr('name');
   					var dateType = $("input[name='"+name+"']").removeAttr("checked");
   					$(this).attr('checked','true');
   				}
	      		e.stopPropagation()
	      	});
   		})
   })

    $('.wql_addBtn').on('click',function(){
    	$("div[name='nameError']").html('');
    	$(".hRadio_Checked").removeClass("hRadio_Checked");
    	
    	$(".wql_user_defined").hide();
    	
    	var name = $("input[name='name']").val('');
    	var startTime =  $("input[name='startTime']").val('');
    	var endTime =  $("input[name='endTime']").val('');
    	var memo = $("textarea[name='memo']").val('');//分类名称            	
    	var startDate = $("input[id='d4311']").val('');
    	var endDate = $("input[name='d4312']").val('');

        var dialog = art.dialog({
            title:'添加规则',
            content:$(".wql_g_artDialog")[0],
            width:'500px',
            okValue:"确定",
            padding:'20px',
            okValue:"确定",
            cancelValue:"退出",
            ok:function(){
            	var name = $("input[name='name']").val();
            	
            	var dateType = $('.sys_hRadio.hRadio.mgr15.dateType.hRadio_Checked').find("input[name='dateType']").val();
            	var useTimeType = $('.sys_hRadio.hRadio.mgr15.useTimeType.hRadio_Checked').find("input[name='useTimeType']").val();      
            	var timeDateType = $('.sys_hRadio.hRadio.mgr15.timeDateType.hRadio_Checked').find("input[name='timeDateType']").val();
            	var timeType = $('.sys_hRadio.hRadio.mgr15.timeType.hRadio_Checked').find("input[name='timeType']").val();
            	
            	var startTime =  $("input[name='startTime']").val();
            	var endTime =  $("input[name='endTime']").val();
            	var memo = $("textarea[name='memo']").val();//分类名称
            	var startDate = $("input[name='startDate']").val();
            	var endDate = $("input[name='endDate']").val();
            	
            	var flag=true;
             	
         		if(name==''){
         			$("div[name='nameError']").html('请输入规则名称');
         			flag=false;
         		}
             	
         		if(!flag)
         			return false;
            	

            	if(timeType ==2 ){
            		startTime = "08:00";
            		endTime = "18:00";
            	}
            	
            	if(timeType==3){
            		startTime = "08:00";
            		endTime = "21:00";
            	}

            	if(dateType != 1){
            		dateType = useTimeType
            	}
            	
            	if(timeDateType != 1){
            		timeDateType = timeType;
            	}
            	
            	
            	$.ajax({
     				type : "post",
     				async : false,
     				url : "saveUseTimeRule",
     				data : {
     					name:name,
     					
     					timeType : timeDateType,
     					startTime : startTime,
     					endTime : endTime,
     					
     					dateType :  dateType,
     					startDate : startDate,
     					endDate : endDate,
     					memo : memo
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
            },
            cancel:true,
            initialize: function() {
                
            }
            
           
        })
    })

    
    $(".sys_hRadio.hRadio.mgr15.useTimeType").on("click",function(){
    	var classValue = $(this).attr("class");
    	if(classValue.indexOf("hRadio_Checked")>-1){
    		var dateType = $(this).find("input[name='useTimeType']").val();
    		if((dateType==1) || (dateType==2) || (dateType==3)){
    			$("input[name='startDate']").val('');
    			$("input[name='endDate']").val('');
    		}
		}
    });
   
    $(".sys_hRadio.hRadio.mgr15.timeType").on("click",function(){
    	var classValue = $(this).attr("class");
    	if(classValue.indexOf("hRadio_Checked")>-1){
    		var dateType = $(this).find("input[name='timeType']").val();
    		if((dateType==1) || (dateType==2) || (dateType==3)){
    			$("input[name='startTime']").val('');
    			$("input[name='endTime']").val('');
    		}
		}
    });
    
})



</script>
