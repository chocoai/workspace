<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->

        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev"><a href="" class="mgr5">用户管理</a>&gt;</span><span class="on mgl5">学生信息管理</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        
       
        <!-- 主体部分 S-->
        <div class="clearfix wql_g_mainBox ">
            <div class="wql_Lside wql_bgfff wql_b_radius6">
                <div class="wql_sideTit t_c">
                    <h3>学校</h3>
                </div>
                <div class="wql_g_sideNav02 mgt10">
                    <dl class="wql_dl">
                    <c:forEach items="${nodeTree}" var="pl" varStatus="status">
                        <dt class="on wql_dt" ><input type="hidden" name="province" value="${pl.areaCode }">${pl.areaName }<i class="wql_upico"></i></dt>
                        	<dd>
                        		<dl class=""  id="${pl.areaCode }">
		
                        		</dl>
                        	</dd>
                    </c:forEach>
                    </dl>
                    
                </div>
            </div>
            <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
               
                <div class="wql_g_tit mgt20">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">学生信息列表</h3>
                        <div class="wql_g_search fr mgt10">
                            <div class="wql_search01">
                                <input type="text" class="wql_inp" placeholder="年级/班级/姓名/学号" style="width:210px;">
                                <span class="wql_searchico"></span>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div class="site_sysTable wql_g_table wql_scrollX mgt15 " >
                    <table class="wql_table01 t_c" >
                        <thead>
                            <tr>
                                <th class="pdl15" width="150">学校</th>
                                <th width="150">年级</th>
                                <th width="150">班级</th>
                                <th width="150">学号</th>
                                <th width="150">姓名</th>
                                <th width="150">性别</th>
                                <th width="150">登录账号</th>
                            </tr>
                        </thead>
                        <tbody id="studentList">

                        </tbody>
                    </table>
                    <div class="base_page clearfix" id="base_page" onselectstart="return false"></div>
                     <form id="pageForm"  name="pageForm" action="${ctx}/manage/student/list" method="post">
                    
                    
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
        

        <!-- 主体部分 E-->

    </div>
</div>
<!--//登陆 end--> 

<!-- 弹窗 S-->

<!-- 弹窗 E-->
<input type="hidden" name="currPage" id="currPage" value="1">
<input type="hidden" name="totalPage" id="totalPage">
<input type="hidden" name="pageSize" id="pageSize" value="10">
<script type="text/javascript">
//登录浮层

var areaCode = '';
function loadList(){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/student/querySchool",
		data : {
			"areaCode" : areaCode,
			"currPage" : $('#currPage').val(),
			"pageSize" : $('#pageSize').val()
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			console.log(areaCode+"la")
			var htmlStr = '';
			if(data != null){
				if(data.list != null && data.list.length > 0){
					dataArray = data.list;
					for(var i=0;i<data.list.length;i++){
						var obj = data.list[i];
						var a;
						if(obj.gender=='1'){
							a='男';
						}else{
							a='女';
						}
						htmlStr += '<tr>';
						htmlStr += '<td>'+obj.schoolName+'</td>';
						htmlStr += '<td>'+obj.grade+'</td>';
						htmlStr += '<td>'+obj.classes+'</td>';
						htmlStr += '<td>'+obj.stNumber+'</td>';
						htmlStr += '<td>'+obj.name+'</td>';
						htmlStr += '<td>'+a+'</td>';
						htmlStr += '<td>'+obj.account+'</td>';
						htmlStr += '</tr>';
					}
				}
				
				if(data.page != null){
					setPage($("#base_page"),data.page);
				}
			}
			if(htmlStr != ''){
				$("#studentList").html(htmlStr);
			}else{
				$("#studentList").html('<tr><td colspan="12" align="center"><font color="red">暂无数据</font></td></tr>');
				$("#base_page").html('');
			}
		}
	});
}

function cityList(areaCode){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/area/queryArea",
		data : {
			"parentId" : areaCode
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			var htmlStr = '';
			
			if(data != null){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i];
					htmlStr += '<dt class="cit">';
					htmlStr += '<input type="hidden" name="city" value='+obj.area_code+'>';
					htmlStr += obj.area_name+'<i class="wql_upico"></i></dt>';
					htmlStr += '<dd>';
					htmlStr += '<dl class="" id='+obj.area_code+'>';
					
					htmlStr += '</dl>';
					htmlStr += '</dd>';
				}
			}
			if(htmlStr != ''){
				$("#"+areaCode).html(htmlStr);
			}else{
				$("#"+areaCode).html('<tr><td colspan="12" align="center"><font color="red">暂无数据</font></td></tr>');
			}
		}
	});
}

function areaList(areaCode){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/area/queryArea",
		data : {
			"parentId" : areaCode
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			var htmlStr = '';
			
			if(data != null){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i];
					htmlStr += '<dt class="are">';
					htmlStr += '<input type="hidden" name="area" value='+obj.area_code+'>';
					htmlStr += obj.area_name+'<i class="wql_upico"></i></dt>';
				}
			}
			if(htmlStr != ''){
				$("#"+areaCode).html(htmlStr);
			}else{
				$("#"+areaCode).html('<tr><td colspan="12" align="center"><font color="red">暂无数据</font></td></tr>');
			}
		}
	});
}


$(function(){
	$(".wql_searchico").on("click",function(){
	   	
	   	loadList();
	})	
	loadList();
	
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

    $(".wql_g_sideNav02 .wql_dd").on('click',function(){
        var dl = $(this).parents('.wql_dl');
        // dl.find(".wql_dt").addClass('on').siblings().removeClass('on');
        $('.wql_g_sideNav02 .wql_dd').removeClass('active');
        $(this).addClass('active');
        $('.wql_g_sideNav02 .wql_dt').removeClass('on');
        dl.find(".wql_dt").addClass('on');
        
    })

    $(".wql_g_sideNav02 dt").on("click",function(){
    	areaCode = $(this).find("input[name=province]").val();
       cityList(areaCode);
       var ico = $(this).find('i');
        if(ico.hasClass('wql_upico')){
            ico.removeClass('wql_upico').addClass('wql_downico');
            $(this).nextUntil('dt').hide();
        }
        else{
            ico.removeClass('wql_downico').addClass('wql_upico');
            $(this).nextUntil('dt').show();
        } 
        
    })
    	
	$(document).on("click",".cit",function(){
       areaCode = $(this).find("input[name=city]").val();
       areaList(areaCode);
       console.log(areaCode)
       var ico = $(this).find('i');
        if(ico.hasClass('wql_upico')){
            ico.removeClass('wql_upico').addClass('wql_downico');
            $(this).nextUntil('dt').hide();
        }
        else{
            ico.removeClass('wql_downico').addClass('wql_upico');
            $(this).nextUntil('dt').show();
        }
	});
    
 	$(document).on("click",".are",function(){
       areaCode = $(this).find("input[name=area]").val();
       console.log(areaCode)
       var ico = $(this).find('i');
        if(ico.hasClass('wql_upico')){
            ico.removeClass('wql_upico').addClass('wql_downico');
            $(this).nextUntil('dt').hide();
        }
        else{
            ico.removeClass('wql_downico').addClass('wql_upico');
            $(this).nextUntil('dt').show();
        }
        $("#studentList").html("");
        loadList();
	}); 
	
    //$('.wql_scrollX').perfectScrollbar();
})
</script>