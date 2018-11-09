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
    <script type="text/javascript" src="${base}/js/fastclick.js"></script>
</head>
<body>
<div class="page">
    <header class="bar bar-nav zxf_header bgfff">
        <a class="icon icon-left pull-left"></a>
        <h1 class="title">微辅导</h1>
    </header>
    <div class="content bgfff mgt025">
        <div class="zxf_noCon_wp pdt8 t_c">
            <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_noNet.png" alt="" class="img_noNet">
            <p class="mgt1">登录超时，请返回APP...</p>
            <a href="#" class="zxf_reLoading_btn mgt095" onclick="getApp()">返回APP</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    //初始化FastClick实例
    $(function() {
        FastClick.attach(document.body);
    });
    function getApp(){
        if(navigator.userAgent.indexOf('Android') > -1){
            window.jslistener.returnapp();
        }else {
            if(navigator.userAgent.indexOf('iOS_WKWebView') > -1){
                window.webkit.messageHandlers.returnapp.postMessage({});
            }else {
                returnapp();
            }
        }
    }
    appGoback = function(){
        if(navigator.userAgent.indexOf('Android') > -1){
            window.jslistener.returnapp();
        }else {
            if(navigator.userAgent.indexOf('iOS_WKWebView') > -1){
                window.webkit.messageHandlers.returnapp.postMessage({});
            }else {
                returnapp();
            }
        }
    }
</script>
</body>
</html>