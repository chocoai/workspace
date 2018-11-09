<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">系统管理 &gt;</span><span class="on mgl5">设备类型</span>
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
                            	<c:forEach items="${terminalTypeList}" var="obj" varStatus="status">
                            		<c:if test="${obj.key == terminalUserTypeInt}">
                            			<li class="wql_li on"><a href="javascript:void(0)" onclick="submit('${obj.key}')">${obj.value}(${obj.count})</a></li>
                            		</c:if>
                            		<c:if test="${obj.key != terminalUserTypeInt}">
                            			<li class="wql_li"><a href="javascript:void(0)"onclick="submit('${obj.key}')"  >${obj.value}(${obj.count})</a></li>
                            		</c:if>
                            	</c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="wql_g_btn mgt8 fr">
                        <div class="wql_btn01">
                            <!-- <a href="#" class="wql_addClass01">添加终端</a> -->
                        </div>
                    </div>
                </div>
                 
            </div>
            <div class="wql_g_list03">
                <ul class="wql_ul clearfix">
                    <c:forEach items="${list}" var="obj" varStatus="status">
                    	<li class="wql_li">
                        <a href="#">
                       		<c:set value="${obj.iconPath}" var="iconPath" />
                        	<c:set value="${fn:indexOf(iconPath, '.png')}" var="fgf" />
	                        <c:set value="${fn:substring(iconPath, 0,fgf)}" var="name" />
                            <div class="wql_img wql_img01"><img src="${obj.iconPath }"></div>
                            <div class="wql_img wql_imghover"><img src="${name}_hover.png"></div>
                            <p> ${obj.name} </p>
                        </a>
                        <!-- <span class="wql_delico" onclick="deleteTerminalType('${obj.id }')"></span> -->
                    </li>
                    </c:forEach>
                </ul>
            </div>
            
            <form id="pageForm"  name="pageForm" action="${ctx}/manage/terminalDeviceType/list" method="post">
            	<input type="hidden" name="terminalUserType" id="terminalUserType" value="${terminalUserTypeInt}">
	            <div class="mgt40">
	                	 <c:if test="${not empty page.totalPage && page.totalPage > 1 }">
							<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
							currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
							action="${ctx}/manage/dictController/dictTypelist"></newTag:page>
					   </c:if>
	            </div>
            </form>
        </div>

    </div>
</div>

<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01 ">
        <div class="wql_g_list01 w9em">
            <ul class="wql_ul">
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>终端名称
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp error" name="name" id="name" type="text" style="width:300px;">
                            <div class="wql_tips">终端名称必填！</div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>终端用户
                        </div>
                        <div class="wql_box_r">
                            <span class="radiolist2 clearfix">
                            	<c:forEach items="${listTerminalUserTypeList}" var="terminalUserType" varStatus="status">
                            	 	<label class="sys_hRadio hRadio mgr10">
                            	 	<input type="radio" name="terminal_user_type" value="${terminalUserType.key}" style="display: none;">${terminalUserType.value}
                            	 	</label>
                            	</c:forEach>
                            </span>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt10">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>屏幕尺寸
                        </div>
                        <div class="wql_box_r">
                           <span class="radiolist2 clearfix fl">
                           		<c:forEach items="${screenTypeList}" var="screenType" varStatus="status">
			                    	<label class="sys_hRadio hRadio mgr10">
			                    	<input type="radio" name="screenType" value="${screenType.key}" style="display: none;">${screenType.value}</label>
			                    </c:forEach>
                            </span>
                            <input class="wql_inp" type="text" name="screenSize" style="width:70px;"> 寸
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt10">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>屏幕是否支持触控
                        </div>
                        <div class="wql_box_r">
                            <span class="radiolist2 clearfix">
                            	<c:forEach items="${listTouchTypeList}" var="touchType" varStatus="status">
                               		<label class="sys_hRadio hRadio mgr10">
                               		<input type="radio" name="touchType" value="${touchType.key}" style="display: none;">
                               		${touchType.value}
                               		</label>
                            	</c:forEach>
                            </span>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt10">
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
<script type="text/javascript">

function deleteTerminalType(id){
	alert(id)
	$.ajax({
			type : "post",
			async : true,
			url : "deleteTerminalDeviceType",
			data : {
				id : id
			},
			dataType : "json",
			success : function(msg) {
				if(msg=='001'){
					alert("该设备类型下有设备，不能删除!");
				}else if(msg=='success'){
					$("#pageForm").submit();
				}
			},
			error : function(errorMsg) {
			}
	})
}

$(function(){
	 $('.radiolist2').hradio2()
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
    };
    
    oneScreen();
    $(window).on('resize',function(){
        oneScreen();
    });
    
    $('.wql_addClass01').on('click',function(){
    
    	
        var dialog = art.dialog({
            title:'添加终端',
            content:$(".wql_g_artDialog")[0],
            width:'560px',
            okValue:"确定",
            padding:'25px 40px',
            cancelValue:"取消",
            cancel:true,
            ok:function() {
     			var name = $("input[name='name']").val();
     			var terminal_user_type = $("input[name='terminal_user_type']").val();
     			var memo = $("textarea[name='memo']").val();//分类名称
     			var screenType = $("input[name='screenType']").val();
     			var screenSize = $("input[name='screenSize']").val();
     			var touchType = $("input[name='touchType']").val();
     			
     			
     			$.ajax({
     				type : "post",
     				async : true,
     				url : "saveTerminalDeviceType",
     				data : {
     					name : name,
     					terminalUserType : terminal_user_type,
     					memo : memo,
     					screenType : screenType,
     					screenSize : screenSize,
     					touchType : touchType,
     				},
     				dataType : "json",
     				success : function(msg) {
     					$("#pageForm").submit();
     				},
     				error : function(errorMsg) {
     				}
     			})
     			
     		}
        });
    });
    
})

function submit(terminalUseType){
	$("#terminalUserType").val(terminalUseType);
	$("#pageForm").submit();
}


</script>
