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
            router:true
        }
    </script>
    <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/light7.js"></script>
    <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/cn.min.js"></script>
    
</head>
<body>
<div class="page">
  <header class="bar bar-nav zxf_header bgfff"> 
    <a class="icon icon-left pull-left" ></a>   
      <h1 class="title">老师被点赞数统计</h1>
  </header>
  <div class="content zxf_content infinite-scroll mgt025" data-distance="100"> 
    <div class="zxf_con-block">  
      <!--<p class="load_more t_c">上滑加载更多...</p>-->  
      <div class="zxf_refuseWp bgfff">
        <dl class="zxf_refuse_dlWp f075 c_normal">
          <dt class="bb">
            <ul>
              <li class="clearfix t_c">
                <div class="sort fl w25">排名</div>
                <div class="teacher fl w50">教师</div>
                <div class="num fl w25">数量</div>
              </li>
            </ul>
          </dt>
          
          <#list list as obj>
          	<dd class="bb">
	            <ul>
	              <li class="clearfix t_c">
	                <div class="sort fl w25">${obj_index+1}</div>
	                <div class="teacher fl w50">${obj.name}</div>
	                <div class="num fl w25 c_01b7e5">${obj.teacherLikeCount}</div>
	              </li>
	            </ul>
	         </dd>
          </#list>
          
          
        </dl>
      </div>            
    </div>
    <!-- 加载提示符 -->
    <!--
    <div class="infinite-scroll-preloader zxf_preloader">
      <div class="preloader">
      </div>
    </div>-->
  </div>
  <script> 
    // 上拉加载
    //var loading = false;
    //var maxItems = 100;
    //var itemsPerLoad = 10;
    //function addItems(number, lastIndex) {
    //        var html = '';
    //        for (var i = lastIndex + 1; i <= lastIndex + number; i++) {
     //           html += '<dd class="bb">';
     //           html += '<ul>';
     //           html += '<li class="clearfix t_c">';
    //            html += '<div class="sort fl w25">'+i+'</div>';
    //            html += '<div class="teacher fl w50">张老师</div>'; 
    //            html += '<div class="num fl w25 c_01b7e5">23</div>';
    //            html += '</li>';
    //            html += '</ul>';
     //           html += '</dd>';               
    //        };
    //        $('.zxf_refuse_dlWp').append(html);
   //     }
   // var lastIndex = 10;
   // $(document).on('infinite', '.infinite-scroll',function() {
   //   $(".load_more").hide();
   //   $(".zxf_preloader").show();
   //     // 如果正在加载，则退出
   //     if (loading) return;
        // 设置flag
    //    loading = true;
   //     setTimeout(function() {
   //         loading = false;
    //        if (lastIndex >= maxItems) {
    //            $.detachInfiniteScroll($('.infinite-scroll'));
    //            $('.infinite-scroll-preloader').remove();
    //            return;
    //        }
     //       addItems(itemsPerLoad, lastIndex);
     //       lastIndex = $('.zxf_refuse_dlWp dd').length;
    //    }, 1000);
    //  });      
    appGoback = function(){
        window.location.href="${base}/count.html";
    }
    $(document).on('touchend', '.icon.icon-left.pull-left',function(e) {
        window.location.href="${base}/count.html";
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