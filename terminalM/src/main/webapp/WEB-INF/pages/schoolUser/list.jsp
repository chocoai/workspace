<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<link rel="stylesheet" href="http://apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<style>
.select2-dropdown{
       z-index: 11111111111;
	  
}
.ui-autocomplete{
       z-index: 11111111111;
}
</style>

<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->

        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">用户管理&gt;</span><span class="on mgl5">学校信息管理</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        
       
        <!-- 主体部分 S-->
        <div class="clearfix wql_g_mainBox ">
            <div class="wql_Lside wql_bgfff wql_b_radius6">
                <div class="wql_sideTit t_c">
                    <h3>地理位置   </h3>
                </div>
                <div class="wql_g_sideNav02 mgt10">
                	<c:forEach items="${nodeTree}" var="province" varStatus="status">
                	<dl class="wql_dl">
                		<dt class="on wql_dt" onclick="showSchool('${province.id}')">${province.name}
                		<i class="wql_upico"></i>
                		</dt>
                        <dd>
                            <dl class="">
                            	<c:forEach items="${province.subNodeList}" var="city" varStatus="cityStatus">
                            		<dt onclick="showSchool('${province.id}')">${city.name }
                            		<i class="wql_upico"></i></dt>
                                <dd> 
                                    <dl class="">
                                    	<c:forEach items="${city.subNodeList}" var="area" varStatus="areaStatus">
                                    		 <dd class="wql_dd active">
                                    		 <a href="javascript:;" onclick="showSchool('${province.id}')">${area.name}</a></dd>
                                    	</c:forEach>
                                    </dl>
                                </dd>
                                </c:forEach>
                            </dl>
                        </dd>
                	</dl>
                	</c:forEach>
                </div>
            </div>
            <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
               
                <div class="wql_g_tit mgt20">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">
                        	<c:if test="userType ==1">
                        		教师列表
                        	</c:if>
                        	<c:if test="userType ==2">
                        		学生列表
                        	</c:if>
                        </h3>
                        <div class="wql_g_btn mgt8 fr">
                            <div class="wql_btn01">
                            	
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div class="site_sysTable wql_g_table wql_scrollX mgt15 " >
                    <table class="wql_table01 t_c chklist2 mgb10" >
                        <thead>
                            <tr>
                                <th class="pdl15" width="50">班级</th>
                                <th width="150">学校</th>
                                <th width="150">姓名</th>
                                <th width="150">用户编码</th>
                            </tr>
                        </thead>
                        <tbody id="schoolUserList">

                        </tbody>
                    </table>
                    <div class="base_page clearfix" id="base_page" onselectstart="return false"></div>
                     <form id="pageForm"  name="pageForm" action="${ctx}/manage/schoolUser/list" method="post">
                    <input type="hidden" name="userType" value="${userType}" >
                    
                    <div class="mgt40">
                      	
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


<script src="http://apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script type="text/javascript">

function queryArea(levelId,parentId){
	var htmlStr = '<option value="">全部</option>';
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/area/queryArea",
		data : {"levelId" : levelId,"parentId" : parentId},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null && data.list != null && data.list.length > 0){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i];
					htmlStr += '<option value="'+obj.area_code+'">'+obj.area_name+'</option>';
				}
			}
		}
	});
	return htmlStr;
}


//登录浮层

function showSchool(areaCode){
	alert(11)
}

var areaCode = '';

function loadList(){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/schoolUser/querySchoolUser",
		data : {
			"areaCode" : areaCode,
			"currPage" : $('#currPage').val(),
			"pageSize" : $('#pageSize').val()
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			var htmlStr = '';
			if(data != null){
				if(data.list != null && data.list.length > 0){
					dataArray = data.list;
					for(var i=0;i<data.list.length;i++){
						var obj = data.list[i];
						htmlStr += '<tr>';
						//htmlStr += '<td>'+obj.orgType+'</td>';
						//htmlStr += '<td>'+obj.name+'</td>';
						//htmlStr += '<td>'+obj.webUrl+'</td>';
						//htmlStr += '<td>'+obj.email+'</td>';
						//htmlStr += '<td>'+obj.mobile+'</td>';
						//htmlStr += '<td>'+obj.address+'</td>';
						//htmlStr += '<td><span><a href="javascript:void(0)" onclick="updateSchool('+obj.id+')" class="">编辑</a>   <a href="javascript:void(0)" onclick="deleteSchool('+obj.id+')" class="">删除</a></span></td>';
						htmlStr += '</tr>';
					}
				}
				
				if(data.page != null){
					setPage($("#base_page"),data.page);
				}
			}
			if(htmlStr != ''){
				//$("#schoolUserList").html(htmlStr);
			}else{
				$("#schoolUserList").html('<tr><td colspan="12" align="center"><font color="red">暂无数据</font></td></tr>');
				$("#base_page").html('');
			}
		}
	});
}




$(function(){
	
	loadList();
	
    $('.chklist2').hcheckboxnew2();
    $('.radiolist2').hradio2();
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
       //cityList(areaCode);
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
       //areaCode = $(this).find("input[name=city]").val();
       //areaList(areaCode);
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
         $("#schoolList").html("");
         loadList();
 	}); 
    
    $('.wql_add').on('click',function(){
        var dialog = art.dialog({
            title:'创建学校',
            content:$(".wql_g_artDialog")[0],
            width:'660px', 
            height:'350px',       
            okValue:"确定",
            padding:'30px 40px',
            cancelValue:"取消",
            cancel:true,
            ok:function(){
            	
            	var schoolType = $(".hRadio_Checked").find("input[name='schoolType']").val();
            	
            	console.log(schoolType);
            	
            	var schoolName = $("input[name='orgName']").val();
            	var webUrl = $("input[name='webUrl']").val();
            	var email = $("input[name='email']").val();
            	var mobile = $("input[name='mobile']").val();
            	var address = $("input[name='address']").val();
            	var contacts = $("input[name='contacts']").val();
            	var platformCode =  $("select[name='platformCode']").val();
  	          	var province = $("#province").val();
  	          	var city = $("#city").val();
            	var area = $("#area").val();
            	var orgaId = $("input[name='orgaId']").val();
            	
            	console.log(orgaId)
            	
				var flag=true;
				
            	if(schoolType=='' || schoolType == undefined ){
            		$("div[name='schoolTypeError']").html('请选择学校类型');
     				flag=false;
            	}
            	
            	if(province==''){
            		$("div[name='provinceError']").html('请选择省份');
     				flag=false;
            	}
            	
				if(address == ''){
     				$("div[name='addressError']").html('学校地址必填');
     				flag=false;
     			}
     			
     			if(email == ''){
     				$("div[name='emailError']").html('邮箱必填');
     				flag=false;
     			}
     			
     			if(platformCode == ''){
     				$("div[name='platformCodeError']").html('请选择平台');
     				flag=false;
     			}
     			
     			if(schoolName == ''){
     				$("div[name='orgNameError']").html('学校名称必填');
     				flag=false;
     			}
     			
     			if(!flag)
     				return false;
     			
     			$.ajax({
     				type : "post",
     				async : true,
     				url : "save",
     				data : {
     					schoolName : schoolName,
     					mobile : mobile,
     					address : address,
     					email : email,
     					webUrl : webUrl,
     					aamOrgaId : orgaId,
     					platformCode:platformCode,
     					provinceCode:province,
     					cityCode:city,
     					schoolType:schoolType,
     					areaCode:areaCode,
     					contacts:contacts
     				},
     				dataType : "text",
     				success : function(msg) {
     					$("#pageForm").submit();
     				},
     				error : function(errorMsg) {
     				}
     			})
            }
          
            
        });
    });
    //$('.wql_scrollX').perfectScrollbar();
})
</script>