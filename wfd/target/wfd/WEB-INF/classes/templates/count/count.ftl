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
        <a class="icon icon-left pull-left" href="index.html"></a>
        <h1 class="title">全校统计</h1>
    </header>
    <div class="content zxf_content infinite-scroll bgfff mgt025" data-distance="100">
        <div class="zxf_con-block">
            <div class="zxf_helpStyle_wp bgfff mgt05">
                <ul>
                    <li class="pdl075 pdr05 bb clearfix teacherReceiveCount">
                        <i class="icon icon icon-right pull-right"></i>
                        <a class=" fr mgr06 f09">${total1}</a>
                        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_repy.png" alt="" class="img_repy mgr075">
                        <a  class="txt_ellipisis w60 con">全校老师回复问题数</a>
                    </li>
                    <li class="pdl075 pdr05 bb clearfix teacherLikeCount">
                        <i class="icon icon icon-right pull-right"></i>
                        <a class=" fr mgr06 f09">${total2}</a>
                        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_pick.png" alt="" class="img_pick mgr075">
                        <a  class="txt_ellipisis w60 con">全校老师被点赞数 </a>
                    </li>
                    <li class="pdl075 pdr05 bb clearfix studentPost">
                        <i class="icon icon icon-right pull-right"></i>
                        <a class=" fr mgr06 f09">${total3}</a>
                        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_quiz.png" alt="" class="img_quiz mgr075">
                        <a class="txt_ellipisis w60 con">全校学生提问数 </a>
                    </li>
                    <li class="pdl075 pdr05 bb clearfix hotQeustion">
                        <i class="icon icon icon-right pull-right"></i>
                        <a class=" fr mgr06 f09">${total4}</a>
                        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_hotques.png" alt="" class="img_hotques mgr075">
                        <a class="txt_ellipisis w60 con">热门问题 </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <script>
    </script>
</div>
<!-- popup弹窗 -->

<!-- 侧栏 -->
<div class="panel-overlay"></div>
<!-- Left Panel with Reveal effect -->
<!-- 学生 -->
<script type="text/javascript" src="${base}/js/fastclick.js"></script>
<script>
    //初始化FastClick实例
    $(function() {
        FastClick.attach(document.body);
    });
    
    $(document).on('touchend', '.icon.icon-left.pull-left',function(e) {
        window.location.href="${base}/index.html";
    });

	$(document).on('touchend', '.teacherReceiveCount',function(e) {
        window.location.href="${base}/teacherReceiveCount.html";
    });

	$(document).on('touchend', '.teacherLikeCount',function(e) {
        window.location.href="${base}/teacherLikeCount.html";
    });
    
    $(document).on('touchend', '.studentPost',function(e) {
        window.location.href="${base}/studentPost.html";
    });
    
    $(document).on('touchend', '.hotQeustion',function(e) {
        window.location.href="${base}/hotQeustion.html";
    });

    appGoback = function(){
        window.location.href="${base}/index.html";
    }
</script>
</body>
</html>