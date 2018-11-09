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
      <a class="wql_icon wql_icon02 zindex20 fl" href="${base}/page/manager/plate/list.html?userId=${userId}"></a>
      <h1 class="title">${title}</h1>
      <a class="zindex20 fr lh22 wql_link">提交</a>
    </header>
    <div class="content">
      <div class="zxf_creatClass_wp">
        <div class="zxf_creatClass_item bgfff mgt05">
          <div class="zxf_creadHead f085 c_normal pd075 bb">
            <span class="mgr075 tit">版块名称 </span> 
            <input type="text" name="plateName"  id="plateName" value="${plateName!}" placeholder="限10个字符，必填" class="inp f075">
          </div>
          <div class="zxf_creatCon pd075 c_normal f085 clearfix bb">
            <div class="fl zxf_addCover">
              <img class="zxf_add_tagBtn mgr125 logo" src="<#if icon??>${icon}<#else>http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/wql_addImg.png</#if>">
              <!--<a href="javascript:;" class="zxf_add_tagBtn mgr125"></a>-->
              <span class="c_afafaf f075">点击添加版块封面,必填</span>
            </div>
          </div>
          <div class="zxf_creatCon pd075 c_normal f085">
            <p class="clearfix mgb05">添加老师</p>
            <div class="zxf_add_studentBox pdtb05 after">
            	<ul class="clearfix">
            	
            		<#if teacherList??>
	            		<#list teacherList as teacher>
		              		<li class="t_c f085 c_normal">
			                  <a href="javascript:;" class="icon_del_red"></a>
			                  <img src="<#if teacher.logoUrl??>${teacher.logoUrl}<#else>http://www.huijiaoyun.com/uploads/avatar/data/41/45/191ac780686340f59d9f61dad257aa27_90x90.jpg</#if>" alt="">
			                  <p class="txt_ellipsis w90 mgt02" platformCode="${teacher.platformCode}" id="${teacher.id}">${teacher.name}</p>
			                </li>
		              	</#list>
            		</#if>
            		
            		<li class="t_c last">
            			<a href="javascript:;" class="zxf_add_tagBtn addStudent"></a> 
            		</li>	
	              </ul>          
                        
            </div>
          </div>
        </div>
        <div class="zxf_creatClass_item pdlr075 bgfff mgt05">
          <div class="zxf_moduleTIntro f075 c_normal pdtb05">
           <textarea placeholder="版块简介，限20字符内，选填" name="plateText"  id="plateText" style="width:100%;height: 4rem;">${plateText!}</textarea>
          </div>
        </div>
      </div>

    </div>

  </div>
  
   <input type="hidden" name="plateId" id="plateId"  value="${plateId}">
   <input type="hidden" name="userId" id="userId" value="${userId}">
   
   <script type="text/javascript" src="${base}/js/common/common.js"></script>
   <script type="text/javascript" src="${base}/js/json2.js"></script>
   
   <script type="text/javascript" src="${base}/js/fastclick.js"></script>
   
   <script type="text/javascript" src="${base}/js/manager/plate/create.js"></script>
  
  <script>    
  	$(document).on('touchend','.zxf_add_tagBtn.logo',function(){
		if(navigator.userAgent.indexOf('Android') > -1){
            gainAnswerPicture(1,1);
        }else {
            gainAnswerPictureIos(1,1);
        }
	})

	function gainAnswerPictureIos(flagId,limit){
        if(navigator.userAgent.indexOf('iOS_WKWebView') > -1){
            window.webkit.messageHandlers.gainPicture.postMessage({flagId:flagId,limit:limit});
        }else {
            gainPicture(1, limit);
        }
    }

	//function gainAnswerPictureIos(flagId,limit){
      //  gainPicture(flagId,limit );
    //}

    function gainAnswerPicture(flagId,limit) {
        !window.jslistener || window.jslistener.gainPicture(flagId,limit );
    }
	
	var mothed = {};
    mothed.setPicture  = function (json) {
        if(json ==null || json =='' || json.length <=0){
            return;
        }
        var obj = JSON.parse(json)
        $(".logo").attr("src",obj.pic[0]);
    };
	  	
  
  	$(function() {    
	    FastClick.attach(document.body);    
	}); 
  
    appGoback = function(){
	   window.location.href="${base}/page/manager/plate/list.html?userId=${userId}";
	}
  	
  
  	$('.zxf_relModuleBox').find('li').each(function() {
  		//console.log($(this).attr("id"));
    })
  
    // 删除
    $(document).on('touchend', '.icon_del_red', function () {
      $(this).parents('li').remove();     
    });
    // 选中
    $(".zxf_relModuleBox").on("touchend","ul>li",function(){
      $(this).toggleClass("on");
    })
    
    // 添加
      $(".zxf_add_studentBox").on("click","li.last",function(){
        //var $html="";
       	//$html+='<li class="t_c f085 c_normal">';
        //$html+='<a href="javascript:;" class="icon_del_red"></a>';
        //$html+='<img src="images/img.png" alt="">';
        //$html+='<p class="txt_ellipsis w90 mgt02">林丽</p>';
        //$html+='</li>';
        //$(this).before($html);                    
      })
  </script>
</body>

</html>