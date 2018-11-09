<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">班级规则 &gt;</span><span class="on mgl5">使用时段</span>
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
            	<div class="wql_box01 f14 lh24 pdlr20 pdtb15 wql_bg_eef5fd w5em">
            		<div class="clearfix">
            			<div class="wql_box_cont fl">
            				<label class="sys_hRadio hRadio mgr15">
            				<input type="radio" name="ruleId" value="${obj.id }" style="display: none;"></label>
            			</div>
            			
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">规则名称</div>
            				<div class="wql_box_r">${obj.name}</div>
            			</div>
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">可使用日期</div>
            				<div class="wql_box_r">${obj.typeName }</div>
            			</div>
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">可使用时点</div>
            				<div class="wql_box_r">${obj.startTime}--${obj.endTime}</div>
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
            				<div class="wql_box_r">${obj.memo }</div>
            			</div>
            		</div>
            	</div>
            </div>
            </c:forEach>
            </span>
             <form id="pageForm" name="pageForm" action="${ctx}/manage/schoolClassRule/addSchoolClassRulePage" method="post">
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
                        	<span name="name"></span>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>可使用日期：
                        </div>
                        <div class="wql_box_r">
                        	<span name="useDate"></span>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	可使用时点：
                        </div>
                        <div class="wql_box_r">
                        	<span name="useTime"></span>
                        </div>
                    </div>
                </li>

                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	备注：
                        </div>
                        <div class="wql_box_r">
                        	<span name="memo"></span>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript">

function detailRule(id){
	$("span[name='name']").text('');
 	$("span[name='memo']").text('');
 	
 	$("span[name='useTime']").text('');
 	$("span[name='useDate']").text('');
 	
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
 			$("span[name='name']").text(data.name);//分类名称
         	$("span[name='memo']").text(data.memo);//备注
         	
 			var dateType = data.dateType;
         	var timeType = data.timeType;
         	
         	if(dateType == 1){
         		$("span[name='useDate']").text("无限制");//备注
         	}else if(dateType == 2){
         		$("span[name='useDate']").text("国家法定节假日和双休日");//备注
         	}else if(dateType == 3){
         		$("span[name='useDate']").text("正常工作日");//备注
         	}else if(dateType == 4){
         		$("span[name='useDate']").text(new Date(data.startDate).format("yyyy-MM-dd") +"--"+new Date(data.endDate).format("yyyy-MM-dd")  );//备注
         	}
       		
         	if(timeType == 1){
         		$("span[name='useTime']").text("无限制");
         	}else if(timeType == 2){
         		$("span[name='useTime']").text(data.startTime+"--"+data.endTime);
         	}else if(timeType == 3){
         		$("span[name='useTime']").text(data.startTime+"--"+data.endTime);
         	}else if(timeType == 4){
         		$("span[name='useTime']").text(data.startTime+"--"+data.endTime);
         	}
 		}
 	});
	
	 var dialog = art.dialog({
         title:'使用时段规则',
         content:$(".wql_g_artDialog")[0],
         width:'500px',
         padding:'20px',
         okValue:"确定",
         traditional:true,
         cancelValue:"退出",
         cancel:true
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
    	
		var ruleId = $(".hRadio_Checked").find("input[name='ruleId']").val();
    	
    	var schoolClassId = $("input[name='schoolClassId']").val()
    	
    	$.ajax({
				type : "post",
				async : true,
				url : "saveSchoolClassUsetimeRule",
				data : {
					ruleId:ruleId,
					schoolClassId : schoolClassId,
				},
				dataType : "text",
				success : function(msg) {
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

   
})



</script>
