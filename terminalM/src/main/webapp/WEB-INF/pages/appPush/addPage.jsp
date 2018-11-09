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
                <span class="prev">应用商城 &gt;</span><span class="on mgl5">终端应用管控</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
            
            <div class="wql_g_tit onbb mgt10">
                <div class="wql_tit01 ">
                    <h3>请选择要推送的应用（一次最多同时推送5款应用）</h3>
                    
                </div>
            </div>
            <div class="wql_g_list mgt10">
                <div class="wql_list05">
                    <ul class="wql_ul clearfix appUl">
                       	
                        <li class="wql_li" name="appBtnLi">
                            <a href="#" class="wql_sel_appBtn"><img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/images/wql_appicon01.png"><span class="mgl10">选择应用</span></a>
                        </li>
                        
                    </ul>
                </div>
            </div>
            <div class="wql_g_tit onbb mgt10">
                <div class="wql_tit01 ">
                    <h3>请选择要推送的终端范围</h3>
                    
                </div>
            </div>
            <div class="mgt10">
               			<span class="sys_seleautodiv mgr10" style="width: 120px; z-index: 3;">
		                    <span class="sys_seleautocur" name="provinceSpan">
		                        <p>请选择</p>
		                        <input type="hidden" class="selRes" value="">
		                    </span>
		                    <span class="sys_seleautodrop animate" name="province" style="width: 120px; display: none; z-index: 9999;">
		                    </span>
		                </span>
		                <span class="sys_seleautodiv mgr10"  style="width: 120px; z-index: 3;">
		                    <span class="sys_seleautocur" name="citySpan">
		                        <p name="cityP">请选择</p>
		                        <input type="hidden" class="selRes" value="">
		                    </span>
		                    <span class="sys_seleautodrop animate"  style="width: 120px; display: none; z-index: 9999;">
		                    </span>
		                </span>
		                <span class="sys_seleautodiv mgr10"  style="width: 120px; z-index: 3;">
		                    <span class="sys_seleautocur" name="areaSpan">
		                        <p>请选择</p>
		                        <input type="hidden" class="selRes" value="">
		                    </span>
		                    <span class="sys_seleautodrop animate"  style="width: 120px; display: none; z-index: 9999;">
		                    </span>
		                </span>
		                <span class="sys_seleautodiv mgr10"  style="width: 120px; z-index: 3;">
		                    <span class="sys_seleautocur" name="schoolSpan">
		                        <p>请选择</p>
		                        <input type="hidden" class="selRes" value="">
		                    </span>
		                    <span class="sys_seleautodrop animate" style="width: 120px; display: none; z-index: 9999;">
		                      
		                    </span>
		                </span>
		                <span class="sys_seleautodiv mgr10"  style="width: 120px; z-index: 3;">
		                    <span class="sys_seleautocur" name="classSpan">
		                        <p>请选择</p>
		                        <input type="hidden" class="selRes" value="">
		                    </span>
		                    <span class="sys_seleautodrop animate" style="width: 120px; display: none; z-index: 9999;">
		                        
		                    </span>
		               </span>
            </div>
            <div class="wql_g_btn mgt50">
                <div class="wql_btn05 t_c">
                    <a href="javascript:;" class="wql_btn wql_borblue">确 认</a>
                </div>
            </div>
            
        </div>

        <!-- 主体部分 E-->

    </div>
</div>


<!-- 弹窗 S-->
<div class="wql_g_artDialog dis_none" >
    <div class="wql_artDialog03 pdtb30">
        <div class="wql_DialogBox ">
            <div class="wql_box_l pdlr20">
                <div class="wql_appimg t_c">
                    <img src="" name="detailIcon">
                    <p class="wql_tit lh20 mgtb20 f20 c454545" name="detailName"></p>
                </div>
                <div class="wql_appinfo">
                    <div name="detailVersion"></div>
                    <div class=""><span class="pdr5 mgr5 br" name="detailFileSize"></span>
                    <span class="pdr5 mgr5 br" name="detailCreatorName"></span>
                    <span class="" name="detailCreateTime"></span></div>
                    <div name="detailCompany"></div>
                </div>
                <div class="wql_targets">
                    <div class="wql_tit mgt15 f20 lh30">适用年级</div>
                    <div class="mgt10" name="detailGrade">
                       
                    </div>
                </div>
                <div class="wql_targets">
                    <div class="wql_tit mgt15 f20 lh30">适用学科</div>
                    <div class="mgt10" name="detailSubject">
                       
                    </div>
                </div>
                <div class="mgt25 lh20">累计推送终端数<em>3450</em></div>
            </div>
            <div class="wql_box_r pdlr20">
                <div class="clearfix">
                    <div class="fl lh20 f18">应用截图</div>
                    <div class="wql_g_btn fr">
                        <div class="wql_btn07">
                            <a href="javascript:;" class="wql_close"></a>
                        </div>
                    </div>
                </div>
                <div class="wql_bannerbox mgtb15">
                    <ul class="wql_bannerimg">
                      
                    </ul>
                    <div class="wql_num clearfix">
                        
                    </div>
                </div>
                
                <div class="lh20 f18">应用介绍</div>
                <div class="mgt10" name="detailDescription">
                
                </div>
                    

            </div>
        </div>
    </div>
</div>

<div class="wql_g_artDialog dis_none" >
    <div class="wql_artDialog01">
        <div class="wql_DialogBox ">
            <div class="wql_g_tit nobb">
                <div class="wql_tit01 clearfix">
                    <h3 class="fl">功能区构成信息列表</h3>
                    
                </div>
                
            </div>
            <div class="site_sysTable wql_g_table mgt15">
                <table class="wql_table01 t_c">
                    <thead>
                        <tr>
                            <th class="pdl15">学校</th>
                            <th>班级</th>
                            <th>学生账号</th>
                            <th>设备编号</th>
                            <th>Mac地址</th>
                            <th>品牌</th>
                            <th>型号</th>
                            <th>推送成功/失败</th>
                            <th width="140">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <tr>
                            <td><span class="pdl15">光谷一小</span></td>
                            <td><span></span></td>
                            <td><span></span></td>
                            <td><span></span></td>
                            <td><span></span></td>
                            <td><span></span></td>
                            <td><span></span></td>
                            <td><span class="red">失败</span></td>
                            <td><span><a href="" class="mgr10">取消推送</a><a href="" class="">再次推送</a></span></td>
                        </tr>
                        <tr>
                            <td><span class="pdl15">光谷二小</span></td>
                            <td><span></span></td>
                            <td><span></span></td>
                            <td><span></span></td>
                            <td><span></span></td>
                            <td><span></span></td>
                            <td><span></span></td>
                            <td><span class="green1">成功</span></td>
                            <td><span></span></td>
                        </tr>
                    </tbody>
                </table>
                
            </div>
            <div class="mgt40">
                <p class="site_syspage t_c"><span class="wql_paginal">每页<em>10</em>条</span><span class="wql_allpage">共<em>4</em>条</span><a href="#" class="prev"></a><a href="#">1</a><a class="on" href="#">2</a><a href="#">3</a><a href="#">4</a><span class="other">...</span><a href="#" class="next"></a><span class="all">共 12 页</span></p>
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
                            	请输入应用名：
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
<!-- 弹窗 E-->

<script src="http://apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>

<script type="text/javascript">

var currentData;

function queryClass(schoolId){
	var htmlStr = '<option value="">全部</option>';
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/school/querySchoolClass",
		data : {"schoolId" : schoolId},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null && data.list != null && data.list.length > 0){
				
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i]
					console.log(obj)
					htmlStr += '<option value="'+obj.id+'">'+obj.className+'</option>';
				}
			}
		}
	});
	return htmlStr;
}

function queryClass2(schoolId){
	var htmlStr = '<a href="#" class="active" value="">请选择</a>';
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/school/querySchoolClass",
		data : {"schoolId" : schoolId},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null && data.list != null && data.list.length > 0){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i]
					htmlStr += '<a href="#" value="'+obj.id+'">'+obj.className+'</a>';
				}
			}
		}
	});
	return htmlStr;
}

function querySchool2(areaCode){
	var htmlStr = '<a href="#" class="active" value="">请选择</a>';
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/school/querySchoolByCode",
		data : {"code" : areaCode},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null && data.list != null && data.list.length > 0){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i];
					htmlStr += '<a href="#" value="'+obj.id+'">'+obj.name+'</a>';
				}
			}
		}
	});
	return htmlStr;
}

function querySchool(areaCode){
	var htmlStr = '<option value="">全部</option>';
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/school/querySchoolByCode",
		data : {"code" : areaCode},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null && data.list != null && data.list.length > 0){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i];
					
					htmlStr += '<option value="'+obj.id+'">'+obj.name+'</option>';
				}
			}
		}
	});
	return htmlStr;
}

function queryArea2(levelId,parentId){
	var htmlStr = '<a href="#" class="active" value="">请选择</a>';
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
					htmlStr += '<a href="#" value="'+obj.area_code+'">'+obj.area_name+'</a>';
				}
			}
		}
	});
	return htmlStr;
}

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


function detail(id){
	 $(".wql_bannerimg").html('<li class="wql_li active"><img src="../../images/timg.jpg"></li>');
	   $(".wql_num").html('<a href="javascript:;" class="active"></a>');
	   
     var dialog = art.dialog({
  	   	id:"detailDialog",
          title:false,
          content:$(".wql_g_artDialog")[0],
          width:'900px',
          okValue:"确定",
          padding:'0',
          cancel: false,
          initialize: function() {
          	$('.wql_close').on('click',function(){
          		top.art.dialog({id:"detailDialog"}).close();
              })
          	$.ajax({
   				type : "POST",
   				url : "${ctx}/manage/appInfo/detail",
   				data : {
   					"id" : id,
   				},
   				async : false,
   				dataType : 'json',
   				traditional: true,
   				success : function(data) {
   					
   					$("img[name='detailIcon']").attr('src',data.icon);
   					$("div[name='detailName']").html(data.name);
   					$("div[name='detailVersion']").html(data.version);
   					$("span[name='detailFileSize']").html(data.fileSize);
   					$("span[name='detailCreatorName']").html(data.creatorName);
   					$("span[name='detailCreateTime']").html(new Date(data.createTime.time).format("yyyy-MM-dd"));
   					$("div[name='detailCompany']").html(data.company);
   					$("div[name='detailDescription']").html(data.description);
   					
   					var grades = data.gradeList;
   					var subjects = data.subjectList;
   					var gradespan = '';
   					var subjectspan = '';
   					$.each(grades,function(index,value){
   						  gradespan += '<span>'+value.name+'</span>';
   					});
   					
   					
   					if(gradespan==''){
   						gradespan="全年级";
   					}
   					
   					$("div[name='detailGrade']").html(gradespan);
   					$.each(subjects,function(index,value){
   						  subjectspan += '<span>'+value.name+'</span>';
   					});
   					
   					if(subjectspan==''){
   						subjectspan="全学科";
   					}
   					
   					$("div[name='detailSubject']").html(subjectspan);
   					
   					var appImgList = data.appImgList;
   					var wql_bannerimgLi ='';
   				
   					var wql_a = '';
   					
   					$.each(appImgList,function(index,value){
   						if(index==1){
	     						wql_bannerimgLi += '<li class="wql_li active"><img src="'+value.url+'"></li>';
 							 	wql_a +='<a href="javascript:;" class="active"></a>';				
   						}else{
   							wql_bannerimgLi += '<li class="wql_li"><img src="'+value.url+'"></li>';
   							wql_a +='<a href="javascript:;"></a>';
   						}
 						});
   					
   					if(wql_bannerimgLi !=''){
   						$(".wql_bannerimg").html('');
   						$(".wql_bannerimg").html(wql_bannerimgLi);
   						$(".wql_num").html('');
   						$(".wql_num").html(wql_a);
   					}
   				}
   		});
          }
      })
}

$(function(){
	
//$('#province').html(queryArea(1,0));	
	$('span[name="provinceSpan"]').siblings('.sys_seleautodrop').html('<a href="#" class="active" value="">请选择</a>');
	$('span[name="citySpan"]').siblings('.sys_seleautodrop').html('<a href="#" class="active" value="">请选择</a>');
	$('span[name="areaSpan"]').siblings('.sys_seleautodrop').html('<a href="#" class="active" value="">请选择</a>');
	$('span[name="schoolSpan"]').siblings('.sys_seleautodrop').html('<a href="#" class="active" value="">请选择</a>');
	$('span[name="classSpan"]').siblings('.sys_seleautodrop').html('<a href="#" class="active" value="">请选择</a>');
	
	$('span[name="province"]').html(queryArea2(1,0))
	
	$('span[name="citySpan"]').on("click",function(){
		var provinceCode = $('span[name="provinceSpan"]').find('.selRes').val();
		$(this).siblings('.sys_seleautodrop').html(queryArea2(2,provinceCode));
	})
	
	$('span[name="areaSpan"]').on("click",function(){
		var cityCode = $('span[name="citySpan"]').find('.selRes').val();
		$(this).siblings('.sys_seleautodrop').html(queryArea2(3,cityCode));
	})
	
	$('span[name="schoolSpan"]').on("click",function(){
		var code;
		
		var provinceCode = $('span[name="provinceSpan"]').find('.selRes').val();
		var cityCode = $('span[name="citySpan"]').find('.selRes').val();
		var areaCode = $('span[name="citySpan"]').find('.selRes').val();

		if(areaCode != '-1'){
			$(this).siblings('.sys_seleautodrop').html(querySchool2(areaCode));
		}else{
			if(cityCode != '-1'){
				$(this).siblings('.sys_seleautodrop').html(querySchool2(cityCode));	
			}else{
				if(provinceCode !='-1'){
					$(this).siblings('.sys_seleautodrop').html(querySchool2(provinceCode));	
				}
			}
		}
		
	})
	
	
	$('span[name="classSpan"]').on("click", function(){
		$(this).siblings('.sys_seleautodrop').html(queryClass2($('span[name="schoolSpan"]').find('.selRes').val()));
	});
	
	//$("#province").bind("change", function(){
	//	$('#area').html('<option value="">全部</option>');
	//	$('#city').html('<option value="">全部</option>');
	//	if($("#province").val()==''){
	//		$('#org').html('<option value="">全部</option>');
	//		$('#city').html('<option value="">全部</option>');
	//		return;	
	//	}
		
	//	
	//	$('#schoolId').html(querySchool($("#province").val()))
	//	$('#city').html(queryArea(2,$("#province").val()));
	//});
	
	//$("#city").bind("change", function(){
	//	if($("#city").val()==''){
	//		$('#org').html('<option value="">全部</option>');
	//		$('#area').html('<option value="">全部</option>');
	//		return;		
	//	}
	//	$('#schoolId').html(querySchool($("#city").val()))
	//	$('#area').html(queryArea(3,$("#city").val()));
	//}); 
	
	//$("#area").bind("change", function(){
	//	if($("#city").val()==''){
	//		$('#org').html('<option value="">全部</option>');
	//		return;		
	//	}
		
	//	$('#schoolId').html(querySchool($("#city").val()))
	//}); 
	
	//$("#schoolId").bind("change",function(){
	//	if($("#schoolId").val()==''){
	//		$('#classId').html('<option value="">全部</option>');
	//		return;		
	//	}
		
	//	$('#classId').html(queryClass($("#schoolId").val()))
	//})
	
	
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

    Banner()

    // banner
    function Banner(){
        $('.wql_bannerbox').each(function() {
            //获取控件
            var img = $(this).find('.wql_li')
            var btn = $(this).find('.wql_num a')
            var w = btn.outerWidth(true);  //每个按钮的宽度 
            var l = img.length;
          
            $('.wql_num').css('marginLeft',-w*l/2) //底部按钮定位
            var index = 0;
            
            var timer = setInterval(function(){
                index = ++index>(l-1)?0:index;
                showimg(index,img);
                
            },3000)

            function showimg(){
                img.eq(index).show().siblings().hide()
                img.eq(index).addClass('active').siblings().removeClass('active');
                btn.eq(index).addClass('active').siblings().removeClass('active');
            }
            $(btn).on('click',function(){
                index = $(this).index();
                showimg()
            })
        });
        
    }


    $('.wql_g_btn .wql_btn07 .wql_unfold').on('click',function(){
        var dialog = art.dialog({
            title:false,
            content:$(".wql_g_artDialog")[0],
            width:'900px',
            okValue:"确定",
            padding:'0',
            cancel: false,
            initialize: function() {
                $('.wql_close').on('click',function(){
                    dialog.close()
                })
            }
        })
    })

 
    $('.wql_borblue').on('click',function(){
    	
    	var provinceCode = $('span[name="provinceSpan"]').find('.selRes').val();
    	

    	console.log(provinceCode);
    	
    	var cityCode = $('span[name="citySpan"]').find('.selRes').val();
    	
    	console.log(cityCode);
    	
    	var areaCode = $('span[name="areaSpan"]').find('.selRes').val();
    	
    	console.log(areaCode);
    	
    	var schoolId = $('span[name="schoolSpan"]').find('.selRes').val();
    	
    	console.log(schoolId);
    	
    	var classId = $('span[name="classSpan"]').find('.selRes').val();
    	
    	console.log(classId);
    	
    	var appList = $(".wql_ul.appUl").children("li");
    	
    	var appIds=[];
    	
    	$.each(appList,function(i,ckItm){
    		var appId = $(this).find("input[name='appId']").val();
    		appIds.push(appId);
		});
 
    	
    	//return false;
    	
    	$.ajax({
			type : "POST",
			url : "${ctx}/manage/appPush/saveAppPush",
			data : {
				"appId":appIds,
				"provinceCode":provinceCode,
				"cityCode" : cityCode,
				"areaCode": areaCode,
				"schoolId" : schoolId,
				"classId" : classId
			},
			async : false,
			dataType : 'text',
			traditional: true,
			success : function(msg) {
				
				if(msg=='success'){
					window.location.href="list";
				}else{
					myAlert('系统消息',msg,function(r){  
    	    	          
	     	    	});
				}
				
			}
		});
    	
    	
    });
    
    $('.wql_sel_appBtn').on('click',function(){
    	
    	
    	$("input[name='appName']").val('');//分类名称
    	
    	var dialog = art.dialog({
            title:'添加应用',
            content:$(".wql_g_artDialog")[2],
            width:'500px',
            okValue:"确定",
            padding:'20px',
            okValue:"确定",
            cancelValue:"退出",
            ok:function(){

            	var li = '<li class="wql_li">'+
            	'<div class="wql_li_head clearfix">'+
            	'<input type="hidden" name="appId" value='+currentData.id+'>'+
            	'<div class="fl wql_tit"><h3>'+currentData.name+'</h3></div>'+
            	'<a href="javascript:;" class="fr wql_link wql_cancelBtn">撤销选择</a>'+
            	'</div>'+
            	'<div class="wql_li_box mgt25">'+
            	'<div class="wql_box_l"><img src="'+currentData.icon+'"></div>'+
            	'<div class="wql_box_r">'+
            	'<div class="wql_versionsbox clearfix">'+
            	'<div class="wql_versions fl">'+currentData.version+'版本</div>'+
            	'</div>'+
            	'<div class="wql_appinfo"><span class="pdr5 mgr5 br">'+currentData.fileSize+'</span><span class="pdr5 mgr5 br">'+currentData.creatorName+'</span><span class="">'+new Date(currentData.createTime.time).format("yyyy-MM-dd")+'</span></div>'+
            	'<div>'+currentData.company+'</div>'+
            	'</div>'+
                '</div>'+   
                '<div class="clearfix mgt20">'+    
                '<div class="wql_g_btn fr">'+
                '<div class="wql_btn07">'+
                 '<a href="javascript:;" class="wql_unfold" onclick="detail('+currentData.id+')"></a>'+  
                 '</div>'+
                 ' </div>'+
                 '</div>'+
                 '</li>'
                 
                 $("li[name='appBtnLi']").before(li);       
            },
            cancel:true
        })
    	
    })
    
    
    $(document).on('click','.wql_cancelBtn',function(){
    	$(this).parent().parent().remove();
    })
    
 

    
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
                        	 name : item.name,
                        	 version : item.version,
                        	 fileSize :item.fileSize,
                        	 createTime : item.createTime,
                        	 company:item.company,
                        	 creatorName :item.creatorName,
                        	 icon: item.icon,
                             label: item.name+":"+item.version,
                             value: item.name,
                             id:item.id//选中后，填充到id里面的值  
                             
                           }  
                }));  
               }  
          });
	      },
	 	  
   	select: function( event, ui ) {  
   		currentData = ui.item;
  	} 
   });
    
})



</script>