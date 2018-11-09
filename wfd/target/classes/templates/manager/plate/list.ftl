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
      <a class="icon icon-left pull-left"></a>
      <h1 class="title">版块管理</h1>
      <a class="icon_add_btn icon_add_tag fr zindex20 mgt06"></a>
    </header>
    <#if  (plateList?size>0) >
    <div class="content zxf_content infinite-scroll" data-distance="100">
      <div class="zxf_con-block">
        <div class="zxf_class_itemWp">
         <#list plateList as plate>
         	<div class="zxf_class_item pdl15 pdt12 pdb035 bgfff mgt05">
	            <div class="zxf_classCon">
	              <h2 class="pdb1 pdl025 pdr075 fwn clearfix" id="${plate.id}">
	                <div class="fl img_blue mgr05">
	                   <img src="${plate.icon}">
	                </div>
	                <a href="javascript:;" class="txt_ellipsis w75 fl c_normal mgt05">${plate.name}</a>
	                <a href="javascript:;" class="icon icon-right pull-right mgt05 c_a4a4a4"></a>
	              </h2>
	              <div class="zxf_people_count bt pdl025 pdt035 clearfix  pdr075">
	                <span class="fl pl">评论：<em class="num">${plate.messageCount}</em></span>
	                <span class="fl dz mgl1">点赞：<em class="num">${plate.likeCount}</em></span>
	                <div class="fr">
	                  <a href="javascript:;" class="zxf_editBtn " id="${plate.id}"><em class="zxf_em">编辑</em></a>
	                  <a href="javascript:;" class="zxf_delBtn mgl05" id="${plate.id}"><em class="zxf_em">删除</em></a>
	                </div>
	              </div>
	            </div>
	          </div>
         </#list>
        </div>
        <!-- 加载提示符 -->
        <!-- <div class="infinite-scroll-preloader">
        <div class="preloader">
        </div>
      </div>        -->
      </div>
    </div>
    
    <#else>
	    <div class="content zxf_content bgfff mgt05 pdt8">
	     <div class="zxf_noCon_wp t_c">
	       <p class="c_normal pdb4">您还没有创建学习版块</p>
	       <a href="javascript:;" class="zxf_big_bg_blueBtn">立即创建</a>
	     </div>
	    </div>
    </#if>
    <input type="hidden" id="userId" value="${userId}" name="userId">
    
   <script type="text/javascript" src="${base}/js/json2.js"></script>
   <script type="text/javascript" src="${base}/js/manager/plate/list.js"></script>
    <script type="text/javascript" src="${base}/js/fastclick.js"></script>
    <script>
    //$(function() {    
	 //   FastClick.attach(document.body);    
	//}); 
    	appGoback = function(){
	 		window.location.href="${base}/index.html";
		}
    	
        $(document).on('touchend', '.icon.icon-left.pull-left',function(e) {
            window.location.href="${base}/index.html";
        });
    </script>
  </div>
  <!-- popup弹窗 -->

  <!-- 侧栏 -->
  <div class="panel-overlay"></div>
  <!-- Left Panel with Reveal effect -->
  <!-- 学生 -->
</body>

</html>