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
    max-height: 100px;
    overflow-y: auto;
    /* 防止水平滚动条 */
    overflow-x: hidden;
}
 * html .ui-autocomplete {
    height: 100px;
 }
</style>

<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->

        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev"><a href="" class="mgr5">用户管理</a>&gt;</span><span class="on mgl5">学校信息管理</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <form id="pageForm"  name="pageForm" action="${ctx}/manage/school/list" method="post">
       
        <!-- 主体部分 S-->
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
            
            <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
               
                <div class="wql_g_tit mgt20">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">学校列表</h3>
                        <div class="wql_g_btn mgt8 fr">
                            <div class="wql_btn01">
                                <a href="#" class="wql_add">从慧教云选择</a>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div class="site_sysTable wql_g_table wql_scrollX mgt15 " >
                    <table class="wql_table01 t_c chklist2 mgb10" >
                        <thead>
                            <tr>
                                <th class="pdl15" width="100">学校类别</th>
                                <th width="150">学校</th>
                                <th width="150">官网</th>
                                <th width="150">邮箱</th>
                                <th width="150">电话</th>
                                <th width="200">地址</th>
                                <th width="220">操作</th>
                            </tr>
                        </thead>
                        <tbody id="schoolList">

                        </tbody>
                    </table>
                    
                    <div class="base_page clearfix" id="base_page" onselectstart="return false"></div>
                    
                                      
                </div>

            </div>
        </div>
        </form>

        <!-- 主体部分 E-->

    </div>
</div>
<!--//登陆 end--> 

<!-- 弹窗 S-->
<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        <div class="wql_g_list01 w7em" style="max-height:500px;height: 400px;">
            <ul class="wql_ul">
               	<li class="wql_li mgt30" style="width: 250px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>平台：
                        </div>
                        <div class="wql_box_r">
                           	<select id="addPlatformCode" name="platformCode" class="sel mgr10" style="width:150px;">
								<option value="">全部</option>
								<c:forEach items="${platformInfoList}" var="a" varStatus="st">
									<option value="${a.platformCode }" >${a.platformName }</option>
								</c:forEach>
							</select>
							<div class="wql_tips" name="platformCodeError"></div>
                        </div>
                    </div>
                </li>
                
                
                <li class="wql_li mgt30" style="width: 250px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>省：
                        </div>
                        <div class="wql_box_r">
                            <select id="province" name="provincecode" class="sel mgr10" style="width:150px;">
								<option value="">全部</option>
								<c:forEach items="${provinceList}" var="a" varStatus="st">
								<option value="${a.area_code }" >${a.area_name }</option>
								</c:forEach>
							</select>
							<div class="wql_tips" name="provinceError"></div>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30" style="width: 250px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	市：
                        </div>
                        <div class="wql_box_r">
                            <select id="city" name="citycode" class="sel mgr10" style="width:150px;">
								<option value="">全部</option>
							</select>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30" style="width: 250px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	区：
                        </div>
                        <div class="wql_box_r">
                            <select id="area" name="areacode" class="sel mgr10" style="width:150px;">
								<option value="">全部</option>
							</select>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>学校名称：
                        </div>
                        <div class="wql_box_r">
                        	<input class="wql_inp" type="text" name="orgName"  id="orgName" style="width:395px;" >             
                        	<div class="wql_tips" name="orgNameError"></div>
							<input name="orgaId" type="hidden" />
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt20" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>学校分类：
                        </div>
                        <div class="wql_box_r">
                            <span class="radiolist2 clearfix">
                            <c:forEach items="${schoolTypeList}" var="schoolType" varStatus="status">
                                   <label class="sys_hRadio hRadio mgr15"><input type="radio" name="schoolType" value="${schoolType.key }" style="display: none;">${schoolType.value}</label>
                            </c:forEach>
                            </span>
                            <div class="wql_tips" name="schoolTypeError"></div>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	官网：
                        </div>
                        <div class="wql_box_r">
                            <input name="webUrl" class="wql_inp" type="text" style="width:395px;">
                        </div>
                    </div>
                </li>
                 <li class="wql_li mgt30" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	联系人：
                        </div>
                        <div class="wql_box_r">
                            <input name="contacts" class="wql_inp" type="text" style="width:395px;">
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>邮箱：
                        </div>
                        <div class="wql_box_r">
                            <input name="email" class="wql_inp" type="text" style="width:395px;">
                            <div class="wql_tips" name="emailError"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>电话：
                        </div>
                        <div class="wql_box_r">
                            <input name="mobile" class="wql_inp" type="text" style="width:395px;">
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>地址：
                        </div>
                        <div class="wql_box_r">
                            <input name="address" class="wql_inp" type="text" style="width:395px;">
                            <div class="wql_tips" name="addressError"></div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>


<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        <div class="wql_g_list01 w7em">
            <ul class="wql_ul">
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	地区：
                        </div>
                       <div class="wql_box_r">
                             <span name="editProvinceName" style="width:395px;"></span> 
                             <span name="editCityName" style="width:395px;"></span>
                             <span name="editAreaName" style="width:395px;"></span>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	学校：
                        </div>
                        <div class="wql_box_r">
							<span name="editOrgName" ></span>          
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	官网：
                        </div>
                        <div class="wql_box_r">
                            <input name="editWebUrl" class="wql_inp" type="text" style="width:395px;">
                        </div>
                    </div>
                </li>
                
                 <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	联系人：
                        </div>
                        <div class="wql_box_r">
                            <input name="editContacts" class="wql_inp" type="text" style="width:395px;">
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	邮箱：
                        </div>
                        <div class="wql_box_r">
                            <input name="editEmail" class="wql_inp" type="text" style="width:395px;">
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	电话：
                        </div>
                        <div class="wql_box_r">
                            <input name="editMobile" class="wql_inp" type="text" style="width:395px;">
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	地址：
                        </div>
                        <div class="wql_box_r">
                            <input name="editAddress" class="wql_inp" type="text" style="width:395px;">
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

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
		url : "${ctx}/manage/school/querySchool",
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
						htmlStr += '<td>'+obj.orgType+'</td>';
						htmlStr += '<td>'+obj.name+'</td>';
						htmlStr += '<td>'+obj.webUrl+'</td>';
						htmlStr += '<td>'+obj.email+'</td>';
						htmlStr += '<td>'+obj.mobile+'</td>';
						htmlStr += '<td>'+obj.address+'</td>';
						htmlStr += '<td><span><a href="javascript:void(0)" onclick="updateSchool('+obj.id+')" class="">编辑</a>   <a href="javascript:void(0)" onclick="deleteSchool('+obj.id+')" class="">删除</a></span></td>';
						htmlStr += '</tr>';
					}
				}
				
				if(data.page != null){
					setPage($("#base_page"),data.page);
				}
			}
			if(htmlStr != ''){
				$("#schoolList").html(htmlStr);
			}else{
				$("#schoolList").html('<tr><td colspan="12" align="center"><font color="red">暂无数据</font></td></tr>');
				$("#base_page").html('');
			}
		}
	});
}


function deleteSchool(id){
	$.ajax({  
        url : "delete",  
        type : "post",  
        dataType : "text",  
        data : {
      	  "id":id
        },
        success: function( data ) {  
          if(data=='success'){
          	  $("#pageForm").submit();            		  
      	  }
       }  
    });
}

function updateSchool(id){
	  var dialog = art.dialog({
          title:'编辑学校',
          content:$(".wql_g_artDialog")[1],
          width:'660px', 
          height:'350px',       
          okValue:"确定",
          padding:'30px 40px',
          cancelValue:"取消",
          cancel:true,
          initialize:function(){
        	  $("span[name='editOrgName']").text('');
        	  $("input[name='editWebUrl']").val('');
        	  $("input[name='editContacts']").val('');
        	  $("input[name='editEmail']").val('');
        	  $("input[name='editMobile']").val('');
        	  $("input[name='editAddress']").val('');
        	  
        	  $.ajax({  
                  url : "detail",  
                  type : "post",  
                  dataType : "json",  
                  data : {
                	  "id":id,

                  },
                  success: function( data ) {  
                	  console.log(data)
                	  
                	  $("span[name='editProvinceName']").text(data.provinceName);
                	  $("span[name='editCityName']").text(data.cityName);
                	  $("span[name='editAreaName']").text(data.areaName);
                	  $("span[name='editOrgName']").text(data.name);
                	  $("input[name='editWebUrl']").val(data.webUrl);
                	  $("input[name='editContacts']").val(data.contacts);
                	  $("input[name='editEmail']").val(data.email);
                	  $("input[name='editMobile']").val(data.mobile);
                	  $("input[name='editAddress']").val(data.address);
 
                 }  
              });
          },
          ok:function(){
        	  var webUrl = $("input[name='editWebUrl']").val();
        	  var contacts = $("input[name='editContacts']").val();
        	  var email = $("input[name='editEmail']").val();
        	  var mobile = $("input[name='editMobile']").val();
        	  var address = $("input[name='editAddress']").val();
        	  
        	  
        	  $.ajax({  
                  url : "update",  
                  type : "post",  
                  dataType : "text",  
                  data : {
                	  "id":id,
					  "webUrl":webUrl,
					  "contacts":contacts,
					  "email":email,
					  "mobile":mobile,
					  "address":address
                  },
                  success: function( data ) {  
                	  
                	  if(data=='success'){
	                	  $("#pageForm").submit();            		  
                	  }
                 }  
              });
          }
      });
}

$(function(){
	
	$("input[name='orgName']").autocomplete({
			
	      source: function(request, response ){
	    	  var platformCode = $("select[name='platformCode']").val();
	          var province = $("#province").val();
	          var city = $("#city").val();
	    	  $.ajax({  
              url : "getAllSchool",  
              type : "post",  
              dataType : "json",  
              data : {
            	  "orgName":$("#orgName").val(),
            	  "platformCode":platformCode,
            	  "province": province,
            	  "city": city
              },
              success: function( data ) {  
            	  
                   response( $.map( data, function( item ) {  
                	   	 console.log(item);
                         return {
                         label: item.text,
                         value: item.text,
                         id:item.id//选中后，填充到id里面的值  
                         }  
              }));  
             }  
        });
	      },
	      scrollHeight: 300,
			scroll: true,
			minLength: 2,
 	select: function( event, ui ) {  
 		 $("input[name='orgaId']").val(ui.item.id);
 		 
 		 console.log($("input[name='orgaId']").val())
	} 
 });
	
	
	
	$("#province").bind("change", function(){
		$('#city').html('<option value="">全部</option>');
		if($("#province").val()==''){
			$('#city').html('<option value="">全部</option>');
			return;	
		}
		
		$('#city').html(queryArea(2,$("#province").val()));
   });
	
	$("#city").bind("change", function(){
		$('#area').html('<option value="">全部</option>');
		if($("#city").val()==''){
			$('#area').html('<option value="">全部</option>');
			return;	
		}
		$('#area').html(queryArea(3,$("#city").val()));
   });
	
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
            width:'1200px', 
            height:'500px',       
            okValue:"确定",
            padding:'30px 40px',
            cancelValue:"取消",
            cancel:true,
            zIndex:1,
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