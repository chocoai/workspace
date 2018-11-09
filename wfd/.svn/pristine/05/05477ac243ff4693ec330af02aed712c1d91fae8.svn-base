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
      <a href="javascript:;" class="icon_people zxf_user_btn open-panel" data-panel="#panel-right-teacher">
        <span class="bg_red_num">12</span>
      <a class="icon icon-left pull-left zxf_goback"></a>
      </a>       
        <h1 class="title">
            ${plateName}
            <a class="icon icon-down icon_sele mgl05 mgt_4"></a>
        </h1>
    </header>
    <div class="content zxf_content infinite-scroll" data-distance="100">
      <a href="javascript:;" class="zxf_editBtn_fixed"></a>
      <div class="zxf_subject_intro bgfff mgtb05">
        <ul>
          
        </ul>
      </div>
      <div class="zxf_time_sort pdlr075 bgfff">
          <div class="clearfix bb pdtb075">
            <span class="fl">回复时间排序</span>
            <span class="fr open-popup open-time" data-popup=".popup-time">排序<i class="icon icon-down mgl05"></i></span>
          </div>        
      </div>
      <div class="zxf_con-block">
        <div class="">
          <div class="tab active">
            <div class="">  
              <div class="zxf_stu_item_wp">                 
                   
   
              </div>
            </div>
     
            <div class="infinite-scroll-preloader">
              <div class="preloader">
              </div>
            </div>
          </div>          
        </div>
      </div>
    </div>
    
    <!-- 弹窗 -->
    <div class="zxf_popup_helpStyle dis_none">
      <div class="zxf_helpStyle_wp bgfff pdb05">
        <ul>
          <li class="pdlr075 bb">
            <i class="icon_gou"></i>
            <img class="icon_li mgr075" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_li1.png">
            <a href="javascript:;" class="txt_ellipsis w60 con" id="">全部</a>
          </li>
          <#list plateList as plate>
          	  <li class="pdlr075 bb">
          	  	<i class="icon_gou"></i>
	            <img class="icon_li mgr075" src="${plate.icon}">  
	            <a href="javascript:;" class="txt_ellipsis w60 con" id="${plate.id}">${plate.name}</a>
	          </li>
          </#list>
        </ul>
      </div>
    </div>
    
    <input type="hidden" name="plateId" value="${plateId}" id="plateId" />
    <script type="text/javascript" src="../../js/teacher/plate.js"></script>
    <script>
    		
    
      // 点赞
      $(".zxf_stu_item_wp").on("click",".zxf_like_btn",function(){
        $(this).toggleClass("on");
      });
      
      // 微辅导弹窗li点击
      $(".zxf_header").on("click",".icon_sele",function(e){
        e.stopPropagation();
        if($(this).hasClass("icon-up")){
          $(this).removeClass("icon-up").addClass("icon-down");
          $(".zxf_popup_helpStyle").slideUp();
        }else{
          $(this).addClass("icon-up").removeClass("icon-dowm");
          $(".zxf_popup_helpStyle").slideDown();
        };
      });
      $(document).on("click",function(e){
      
        e.stopPropagation();
        $(".zxf_popup_helpStyle").hide();
      })
      $(".zxf_popup_helpStyle").on("click","li",function(){
         var id = $(this).find(".txt_ellipsis").attr("id");
      
         console.log(id)
      	console.log(this)
      	
      	window.location.href = "plate.html?plateId="+id;
      	
        $(this).addClass("on").siblings().removeClass("on");
      });
      
      // 侧边栏

      // 上拉加载
      var loading = false;
      var maxItems = 100;
      var itemsPerLoad = 10;
      function addItems(number, lastIndex) {
             // var html = '';
              //for (var i = lastIndex + 1; i <= lastIndex + number; i++) {
                  
             // };
             // console.log(html)
              $('.zxf_stu_item_wp').append(html);
          }
      var lastIndex = 2;
      $(document).on('infinite', '.infinite-scroll',function() {
        // 如果正在加载，则退出
        if (loading) return;
        // 设置flag
        loading = true;
        setTimeout(function() {
            loading = false;
            if (lastIndex >= maxItems) {
                $.detachInfiniteScroll($('.infinite-scroll'));
                $('.infinite-scroll-preloader').remove();
                return;
            }
            startLine= startLine+10;
            loadPost();
            
            //addItems(itemsPerLoad, lastIndex);
            lastIndex = $('.zxf_stu_item_wp .zxf_stu_item').length;
        }, 1000);
    });
    </script>
</div>
<!-- popup弹窗 -->
<div class="popup popup-time">
  <div class="zxf_block pdlr075">
    <div class="zxf_opretion mgb05 bgStyle">
      <a href="javascript:;" class="refuse_btn bb" >回复时间排序</a>
      <a href="javascript:;" class="send_btn">发帖时间排序</a>
    </div>
   <a href="javascript:;" class="cancel_btn close-popup bgStyle">取消</a>
  </div>
</div>

<!-- 侧栏 -->
<div class="panel-overlay"></div>
<!-- Left Panel with Reveal effect -->
<!-- 教师 -->
<div class="panel panel-right panel-cover zxf_panel panel-teacher" id='panel-right-teacher'>
  <div class="">
    <div class="zxf_panel_head pdtb05 pdlr1 bb clearfix bgfff">
      <img src="images/img_teacher_big1.png" alt="" class="fl mgr075">
      <p class="name fl">刘倩</p>
    </div>
    <div class="zxf_panel_nav pdtb085 bgfff">
      <ul class="clearfix">        
        <li class="t_c">
          <p class="num">23</p>
          <span class="des">回复问题数</span>
        </li>
        <li class="t_c">
          <p class="num">18</p>
          <span class="des">被点赞数</span>
        </li>
      </ul>
    </div>
    <div class="zxf_helpStyle_wp bgfff mgt05">
        <ul>
          <li class="pdl075 pdr05 bb clearfix">
            <i class="icon icon icon-right pull-right"></i>
            <span class="bg_red_num fr mgr06">12</span>
            <i class="icon_li icon_li1 mgr075"></i>
            我的帖子
          </li>
          <li class="pdl075 pdr05 bb clearfix">
            <i class="icon icon icon-right pull-right"></i>  
            <span class="bg_red_num fr mgr06">99</span>          
            <i class="icon_li icon_li2 mgr075"></i>
            评论我的            
          </li>
          <li class="pdl075 pdr05 bb clearfix">
            <i class="icon icon icon-right pull-right"></i>            
            <i class="icon_li icon_li3 mgr075"></i>
            点赞我的
          </li>
          <li class="pdl075 pdr05 bb clearfix">
            <i class="icon icon icon-right pull-right"></i>  
            <span class="bg_red_num fr mgr06">5</span>          
            <i class="icon_li icon_li4 mgr075"></i>
            @我的
          </li>
          <li class="pdl075 pdr05 bb clearfix">
            <i class="icon icon icon-right pull-right"></i>            
            <i class="icon_li icon_li5 mgr075"></i>
            我的收藏
          </li>          
        </ul>
    </div>
    <div class="zxf_helpStyle_wp bgfff mgt05">
        <ul>
          <li class="pdl075 pdr05 bb clearfix">
            <i class="icon icon icon-right pull-right"></i>
            <span class="bg_red_num fr mgr06">12</span>
            <i class="icon_li icon_li1 mgr075"></i>
            	版块管理
          </li>
          <li class="pdl075 pdr05 bb clearfix">
            <i class="icon icon icon-right pull-right"></i>  
            <span class="bg_red_num fr mgr06">99</span>          
            <i class="icon_li icon_li2 mgr075"></i>
            班级管理            
          </li>
          <li class="pdl075 pdr05 bb clearfix">
            <i class="icon icon icon-right pull-right"></i>            
            <i class="icon_li icon_li3 mgr075"></i>
            全校统计
          </li>          
        </ul>
    </div>
    <div class="zxf_helpStyle_wp bgfff mgt05">
        <ul>
          <li class="pdl075 pdr05 bb clearfix">
            <i class="icon icon icon-right pull-right"></i>            
            <i class="icon_li icon_li1 mgr075"></i>
            接收微社区的钉钉消息
          </li>            
        </ul>
    </div>
    
  </div>    
  </div>
</body>
</html>