<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="screen-orientation" content="portrait">
  <meta name="x5-fullscreen" content="true">
  <title>微辅导</title>
  <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/light7.css">
  <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/wql_base.css">
  <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/weiLesson.css">
  <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/jquery.js"></script>
  <script>
    $.config = {
      autoInit: true,
      router: true
    }
  </script>
  <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/light7.js"></script>
  <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/cn.min.js"></script>

</head>

<body>
  <div class="page">
    <header class="bar bar-nav zxf_header bgfff">
      <a class="icon icon-left pull-left" href="${base}/page/manager/classManage.html?userId=${userId}"></a>
      <h1 class="title">${tclass.name}</h1>
      <!--<a class="icon_add_btn icon_add_tag fr zindex20 mgt06"></a>-->
    </header>
    <div class="content zxf_content infinite-scroll" data-distance="100">
      <div class="buttons-tab bgfff zxf_buttons-tab">
        <a href="#tab1" class="tab-link active button">班级学生</a>
        <a href="#tab2" class="tab-link button">关联版块</a>
      </div>
      <div class="zxf_con-block">
        <div class="tabs">
          <div id="tab1" class="tab active">
            <div class="zxf_search_box pdlr075 pdtb05 bgfff clearfix fixed">
              <div class="zxf_search bgfff w65 fl">
                <label class="icon_search" for="search"></label>
                <input type="search" id="search" placeholder="请输入" value="${name!}">
              </div>
              <a href="javascript:;" class="zxf_bg_blueBtn zxf_add_studentBtn fr">添加学生</a>
            </div>
            <div class="zxf_cstu_item_wp">
              
              	<#if (aUserList?size>0) >  
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">A</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list aUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
                <#if (bUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">B</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list bUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (cUserList?size>0)>
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">C</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list cUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (dUserList?size>0)>
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">D</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list dUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (eUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">E</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list eUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (fUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">F</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list fUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (gUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">G</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list gUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (hUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">H</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list hUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (iUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">I</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list iUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (jUserList?size>0 )>
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">J</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list jUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (kUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">K</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list kUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (lUserList?size>0)>
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">L</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list lUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (mUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">M</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list mUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (nUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">N</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list nUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (oUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">O</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list oUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (pUserList?size>0)>
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">P</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list pUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (qUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">Q</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list qUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (rUserList?size>0)>
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">R</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list rUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (sUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">S</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list sUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (tUserList?size>0)>
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">T</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list tUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (uUserList?size>0)>
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">U</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list uUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (vUserList?size>0)>
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">V</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list vUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (wUserList?size>0)>
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">W</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list wUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (xUserList?size>0)>
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">X</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list xUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (yUserList?size>0) >
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">Y</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list yUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
            	
            	<#if (zUserList?size>0)>
            		<div class="zxf_cstu_item">
		                <h4 class="f085 pdtb035 c_normal pdl075 pdr075">Z</h4>
		                <div class="zxf_cstuCon bgfff">
		                  <ul class="student_list pdl075">
		                  	<#list zUserList as user>
		                  		<li class="clearfix f085 c_normal bb pdr075">
			                      <span class="name fl">${user.name}</span>
			                      <a href="javascript:;" id="${user.id}" class="zxf_bor_grayBtn zxf_exitBtn fr mgt05">退出班级</a>
			                    </li>
		                  	</#list>
		                  </ul>
		                </div>
		             </div>
            	</#if>
              
                
            </div>
            <!-- 加载提示符 
            <div class="infinite-scroll-preloader">
              <div class="preloader">
              </div>
            </div>-->
          </div>
          <div id="tab2" class="tab bgfff">
            <div class="zxf_relation_moduleWp mh29">   
                <div class="zxf_relation_moduleList pdb05">
                    <ul class="">
                    	<#list plateList as plate>
                    		<li class="pdtb06 bb clearfix pdlr075">    
                    			<#if plate.relationStatus ==1 >
                    			 	<a href="javascript:;" id="${plate.id}" class="zxf_relativeBtn on">取消关联</a>
                    			 <#else>
                    			 	<a href="javascript:;" id="${plate.id}" class="zxf_relativeBtn ">关联</a>
                    			 </#if>                                
		                        <div class="zxf_imgBox fl"><img class="icon_li mgr075" src="${plate.icon}"></div>
		                        <div class="zxf_txtBox w80">
		                          <a href="javascript:;" class="txt_ellipsis w90 f085 con mgb02">${plate.name}</a>
		                          <p class="txt_ellipsis w90 des f075">${plate.description}</p>
		                        </div>
		                     </li>
                    	</#list>
                    </ul>
                  </div>           
            </div>
          </div>
        </div>
      </div>
    </div>  
    
    <input type="hidden" name="classId" id="classId"  value="${tclass.id}">
   	<input type="hidden" name="userId" id="userId" value="${userId}">
    <script type="text/javascript" src="${base}/js/json2.js"></script>
    <script type="text/javascript" src="${base}/js/manager/editClass.js"></script>
    <script type="text/javascript" src="${base}/js/fastclick.js"></script>
    <script>
    $(function() {    
	    //FastClick.attach(document.body);    
	}); 
    appGoback = function(){
	 		window.location.href="${base}/page/manager/classManage.html?userId=${userId}";
	}
    
    
      // 关联
      $(".zxf_relation_moduleWp").on("click",".zxf_relativeBtn",function(){
      		var classValue = $(this).attr("class")
      		console.log(classValue);
      		var plateId = $(this).attr("id");
 			var classId = $("#classId").val();
      
      		if(classValue.indexOf("on")>-1){
      			
      			$.ajax({
            		type : "post",
            		async : true,
            		url : "deleteClassPlate",
            		traditional:true,
            		data : {
            			plateId : plateId,
            			classId : classId
            		},
            		dataType : "text",
            		success : function(msg) {
            		},
            		error : function(errorMsg) {
            		}
            	})
      		}else{
      			$.ajax({
            		type : "post",
            		async : true,
            		url : "addClassPlate",
            		traditional:true,
            		data : {
            			plateId : plateId,
            			classId : classId
            		},
            		dataType : "text",
            		success : function(msg) {
            		},
            		error : function(errorMsg) {
            		}
            	})
      		
      		}
      
	        if($(this).hasClass("on")){
	          $(this).removeClass("on").text("关联");
	        }else{
	          $(this).addClass("on").text("取消关联");
	        }
      });
      // 退出班级
      $(document).on("click",".zxf_exitBtn",function(){
        //退出班级
        // $.modal({
        //   title:  '将学生退出班级？',              
        //   buttons: [
        //     {
        //       text: '取消',
        //       onClick: function() {
              
        //       }
        //     },
        //     {
        //       text: '退出班级',
        //       onClick: function() {
              
        //       }
        //     }
        //   ]
        // });
        //删除板块
        
      })
    </script>
  </div>
</body>

</html>