<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev"><a href="" class="mgr5">系统管理</a>&gt;</span><span class="on mgl5">基础资料维护</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
            <div class="wql_g_tit">
                <div class="wql_tit01 clearfix">
                    <h3 class="fl">基础资料分类</h3>
                    <div class="wql_g_btn mgt8 fr">
                        <div class="wql_btn01">
                            <a href="#" class="wql_addClass01">添加分类</a>
                        </div>
                    </div>
                </div>
                
            </div>
            <div class="site_sysTable wql_g_table mgt15">
                <table class="wql_table01 t_l">
                    <thead>
                        <tr>
                            <th width="20%" class="pdl15">分类编号</th>
                            <th width="20%">分类名称</th>
                            <th width="20%">创建人</th>
                            <th width="20%">创建日期</th>
                            <th>操作</th>                                     
                        </tr>
                    </thead>
                    <tbody>
                	<c:forEach items="${list}" var="obj" varStatus="status">
			    		<tr class="dictTypeTr">
                            <td><span class="pdl15">${obj.id}</span></td>
                            <td>${obj.name}</td>
                            <td>${obj.creatorName}</td>
                            <td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>
                            <a href="javascript:void(0);"  onclick="updateDictType('${obj.id}','${obj.name}')">编辑</a>
                            <a href="javascript:void(0);"  onclick="toDictData('${obj.id}')">查看值</a>
                            </td>                                     
                        </tr>
		    		</c:forEach>
                    </tbody>
                </table>
                
                <form id="pageForm"  name="pageForm" action="${ctx}/manage/dictController/dictTypelist" method="post">
               
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
</div>
<!-- 弹窗 S-->
<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01 ">
        <div class="wql_g_list01 w5em">
            <ul class="wql_ul">
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>分类名称
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" name ="name" id="typeName" type="text" style="width:200px;">
                            <div class="wql_tips" name="typeNameError"></div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript">
//登录浮层

function toDictData(id){
	window.location.href = 'dictDatalist?dictTypeId='+id
}

function updateDictType(id,name){
	 $("#typeName").val(name)
	 var dialog = art.dialog({
         title:'基础资料分类添加',
         content:$(".wql_g_artDialog")[0],
         width:'560px',
         okValue:"确定",
         
         padding:'30px 60px',
         cancelValue:"取消",
         cancel:true,
         ok:function() {
 			var typeName = $("#typeName").val();//分类名称
 			
 			$.ajax({
 				type : "post",
 				async : true,
 				url : "editDictType",
 				data : {
 					name : typeName,
 					id:id
 				},
 				dataType : "json",
 				success : function(msg) {
 					
 				},
 				error : function(errorMsg) {
 				}
 			})
 			$("#pageForm").submit();
 		}
     });
}

$(function(){
    
	$(".dictTypeTr").bind("click",function(){
		//alert("1");
	});
	
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
    	
    	$("#typeName").val('');
    	
        var dialog = art.dialog({
            title:'基础资料分类添加',
            content:$(".wql_g_artDialog")[0],
            width:'560px',
            okValue:"确定",
            padding:'30px 60px',
            cancelValue:"取消",
            cancel:true,
            ok:function() {
    			var typeName = $("#typeName").val();//分类名称
    			
    			if(typeName ==''){
    				$("div[name='typeNameError']").text('分类名称必填');//应用名称
    				return ;
    			}
    			
    			$.ajax({
    				type : "post",
    				async : true,
    				url : "saveDictType",
    				data : {
    					name : typeName
    				},
    				dataType : "text",
    				success : function(msg) {
    					alert(msg)
    					//if(msg=='success'){
    					
    						//$("#pageForm").submit();
    					//}
    				},
    				error : function(errorMsg) {
    				}
    			})
    			//$("#pageForm").submit();
    		}
        });
    });
    $('.wql_addClass02').on('click',function(){
        var dialog = art.dialog({
            title:'基础资料分类明细添加',
            content:$(".wql_g_artDialog")[1],
            width:'560px',
            okValue:"确定",
            padding:'30px 40px',
            cancelValue:"取消",
            cancel:true,
            ok:function() {
    			var sendText = $("#sendMessage").val();
    			var delayTime = $("#delayTime").val();

    			var deviceCodes = getCheckValue();

    			console.log(sendText);
    			console.log(delayTime);
    			$.ajax({
    				type : "post",
    				async : true,
    				url : "batchSendMessage",
    				data : {
    					sendText : sendText,
    					delayTime : delayTime,
    					deviceCodes : deviceCodes
    				},
    				dataType : "json",
    				success : function(result) {
    					this.close();
    				},
    				error : function(errorMsg) {

    				}
    			})
    		}
        });
    });

})



</script>


