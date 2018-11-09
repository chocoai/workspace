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
      router: false,
    }
  </script>
  <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/light7.js"></script>
</head>

<body>
  <div class="page">
    <header class="bar bar-nav bgfff">
      <a class="wql_icon wql_icon02 zindex20 fl" href="${base}/page/manager/classManage.html?userId=${userId}"></a>
      <h1 class="title">${title}</h1>
      <a class="zindex20 fr lh22 wql_link">提交</a>
    </header>
    <div class="content">
      <div class="zxf_creatClass_wp">
        <div class="zxf_creatClass_item pdlr075 bgfff mgt05">
          <div class="zxf_creadHead f085 c_normal pdtb075 bb">
            <span class="mgr075 tit">班级名称</span>
            <input type="text" placeholder="限20个字符，必填" id="className" name="className" value="${className!}" class="inp f075">
          </div>
          <div class="zxf_creatCon pdtb075 c_normal f085">
            <p class="clearfix mgb05">添加学生<span class="fr c_9f9f9f"><!--<em id="studentCount"></em>--></span></p>
            <div class="zxf_add_studentBox pdtb05 after">
              <ul class="clearfix">
              	<#if (studentList?size>0) >
              		<#list studentList as student>
	              		<li class="t_c f085 c_normal">
		                  <a href="javascript:;" class="icon_del_red"></a>
		                  <img src="${student.logoUrl}" alt="">
		                  <p class="txt_ellipsis w90 mgt02" platformCode="${student.platformCode}" id="${student.id}">${student.name}</p>
		                </li>
	              	</#list>
              	</#if>
                
                <li class="t_c last">
                  <a href="javascript:;" class="zxf_add_tagBtn"></a>
                </li>
              </ul>             
            </div>
          </div>

        </div>
        <div class="zxf_creatClass_item pdlr075 bgfff mgt05">
          <div class="zxf_creadHead f075 c_616161 pdtb075 bb">
            <span class="mgr075 tit">关联版块</span>
          </div>
          <div class="zxf_creatCon pdtb075 c_normal f085">
            <div class="zxf_relModuleBox pdtb05">
              <ul class="clearfix">
                <#list allPlateList as plate>
	                <li class="t_c" id="${plate.id}">
	                  <a href="javascript:;" class="f075" style="overflow: hidden; text-overflow:ellipsis; white-space: nowrap">${plate.name}</a>
	                </li>
                </#list>

              </ul>
            </div>

          </div>

        </div>
      </div>


    </div>
    
  <script type="text/javascript" src="${base}/js/common/common.js"></script>
    <input type="hidden" name="userId" id="userId" value="${userId}"/>
  <input type="hidden" name="classId" id="classId" value="${classId}"/>
 
   <script type="text/javascript" src="${base}/js/json2.js"></script>
   <script type="text/javascript" src="${base}/js/manager/createClass.js"></script>
<script type="text/javascript" src="${base}/js/fastclick.js"></script>
    <script>    
    	$(function() {    
	    FastClick.attach(document.body);    
	})
    appGoback = function(){
	 	window.location.href="${base}/page/manager/classManage.html?userId=${userId}";
	}
	
    	$('.zxf_relModuleBox').find('li').each(function() {		
	 		//var classValue = $(this).attr("class");
	 		
	 		var id = $(this).attr("id")
	 		
	 		<#if (plateList?size>0) >
		 		<#list plateList as plate>
		 			var plateId = '${plate.id}'
		 			
		 			console.log(plateId+":"+id);
		 			if(id==plateId){
		 				$(this).toggleClass("on");
		 			}
		 		</#list>
	 		</#if>
	 	})
    	
    
      // 删除
      $(document).on('touchend', '.icon_del_red', function () {
        $(this).parents('li').remove();     
      });
      // 选中
      $(".zxf_relModuleBox").on("touchend","ul>li",function(){
        $(this).toggleClass("on");
      });
      
    </script>
  </div>
</body>

</html>