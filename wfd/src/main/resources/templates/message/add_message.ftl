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
    <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/swiper.min.css">
    <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/jquery.js"></script>
    <script>
        $.config = {
            autoInit: true,
            router:false,
        }
    </script>
    <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/light7.js"></script>
    <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/cn.min.js"></script>
    <script type="text/javascript" src="${base}/js/fastclick.js"></script>
    <script type='text/javascript' src='http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/swiper.min.js' charset='utf-8'></script>
</head>
<body>
<div class="page">
    <header class="bar bar-nav bgfff">
        <a class="wql_icon wql_icon02 zindex20 fl" id="closeBtn"></a>
        <h1 class="title">评论</h1>
        <a class="zindex20 fr lh22">发布</a>
    </header>

    <div class="content bgfff">
        <!-- 输入框    -->
        <div class="wql_inputBox mgt025 pdt05 f075">
            <div id="content" class="inputor needsclick wql_inp pdlr075" contenteditable="true" style="height: 8rem;width: 100%;overflow-y: auto;box-sizing: border-box;padding:.5rem;"></div>
            <div class="zxf_processWp pdtb0625  pdlr075 bt bb">
                <a href="javascript:;" class="icon_photo mgr120"></a>
                <a href="javascript:;" class="icon_audio mgr120"></a>
                <a href="javascript:;" class="icon_at"></a>
            </div>
        </div>
        <form id="addForm" method="post" action="addMessage" enctype="multipart/form-data">
            <input type="hidden" name="postId" value="${postId}">
            <input type="hidden" name="turn" value="${turn!}" />
            <input type="hidden" name="receiver" id="receiver" <#if receiver??>value="${receiver?c}"</#if> >
        <!-- 上传图片 S-->
        <div class="wql_g_upImg pdt075 pdlr075 wql_bg_fff mgt05">
            <ul class="wql_ul clearfix" id="imgView">

            </ul>
        </div>
        </form>
        <!-- 上传图片 E-->
    </div>
    <div class="zxf_coverWp">
        <div class="zxf_toastBox toastBox_send">
            <i class="icon_sending"></i>
            <p>正在发布...</p>
        </div>
        <div class="zxf_toastBox toastBox_ok">
            <i class="icon_ok"></i>
            <p>操作成功</p>
        </div>
    </div>

    <div class="zxf_popup bgfff">
    </div>
</div>
<script>
    //初始化FastClick实例
    $(function() {
        FastClick.attach(document.body);
    });
    <#if content??>
    var content ="${content}";
    $("#content").html(content);
    </#if>

    var plateId="${plateId!}";

    // 删除
    $(document).on("touchend",'.wql_delIcon',function(){
        $li = $(this).parents('.wql_li');
        $li.remove();
    })

    var post_flag = false;
    $(document).on("touchend",'.lh22',function(){
        var content = $("#content").html();
        var myAudio = $("input[name='myAudio']").length;
        var path =$("input[name='path']").length;
        var ranges = [
            '\ud83c[\udf00-\udfff]',
            '\ud83d[\udc00-\ude4f]',
            '\ud83d[\ude80-\udeff]'
        ];
        content = content .replace(new RegExp(ranges.join('|'), 'g'), '');
        var limit = 9-$("#imgView>li").length;
        if(limit==9){
            if(content==null || content==''){
                return $.alert("请填写内容！")
            }
        }
        if(post_flag){
            return $.alert("正在发送中，请勿重复操作！")
        }
        post_flag = true;//标记当前状态为正在提交状态
        var form = new FormData(document.getElementById("addForm"));
        var inputType=$("<input type='hidden' name='content'/>");
        inputType.attr("value",content);
        //注入参数到表单
        //form.append(inputType);
        //form.hide();
        //提交表单
        //form.submit();
        form.append("content",content);
        $.ajax({
            type: "post",
            url: "addMessage",
            processData: false,
            contentType:false,
            data: form,
            //关键部分,用xhrOnProgress方法监听xhr,并返回挂载了监听函数的新xhr对象
            xhr: xhrOnProgress(function(e) {
                var percent =  (Math.round(e.loaded / e.total * 10000) / 100.00 + "%");// 小数点后两位百分比
                $(".zxf_coverWp").show(2000);
                $(".toastBox_send").show().next('.toastBox_ok').hide();
                /*$(".icon_sending").siblings("p").text(percent)*/
            }),
            success: function(data) {
                $(".zxf_coverWp").hide();
                if(data.result=='success'){
                    $(".zxf_coverWp").hide().find(".toastBox_send").hide();
                    $(".zxf_coverWp").show();
                    $(".toastBox_ok").show().next('.toastBox_send').hide();
                    setTimeout(function (){
                        $(".zxf_coverWp").hide();
                        setTimeout(function (){
                            window.location.href="getPostDetail?postId=${postId!}&turn=${turn!}"
                        }, 100);
                    }, 1000);
                }
            }
        });
    })

    //上传进度监听方法
    var xhrOnProgress = function(fun) {
        xhrOnProgress.onprogress = fun; //绑定监听
        //使用闭包实现监听绑
        return function() {
            //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
            var xhr = $.ajaxSettings.xhr();

            //判断监听函数是否为函数
            if(typeof xhrOnProgress.onprogress !== 'function'){
                return xhr;
            }

            //如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
            if(xhrOnProgress.onprogress && xhr.upload) {
                xhr.upload.onprogress = xhrOnProgress.onprogress;
            }
            return xhr;
        }
    }

    $(document).on("touchend",'#closeBtn',function(){
        var turn = "${turn!}";
        var type = "${type!}";
        if(turn!=null && turn!=''){
            if(type==1){
               return window.location.href="${base}/getPostDetail?postId=${postId!}&turn=${turn!}";
            }
            if(turn==1){
                return window.location.href="${base}/index.html";
            }
            if(turn==2){
                return window.location.href="${base}/index.html?plateId=${plateId!}";
            }
            return window.location.href="${base}/index.html";
        }else{
            return window.location.href="${base}/index.html";
        }
    });

    $(document).on('touchstart','.icon_photo.mgr120',function(){
        var limit = 9-$("#imgView>li").length;
        if(limit<=0){
            return $.alert("已选9张图片或语音！")
        }
        if(navigator.userAgent.indexOf('Android') > -1){
            !window.jslistener || window.jslistener.gainPicture(1,limit);
        }else {
            if(navigator.userAgent.indexOf('iOS_WKWebView') > -1){
                window.webkit.messageHandlers.gainPicture.postMessage({flagId:1,limit:limit});
            }else {
                gainPicture(1, limit);
            }
        }
    });

    var mothed = {};
    mothed.setPicture  = function (json) {
        if(json ==null || json =='' || json.length <=0){
            return;
        }
        var obj = JSON.parse(json)
        for(var i =0;i<obj.pic.length;i++){
            var img ='<li class=\"wql_li\"><img src=\"'+obj.pic[i]+'\"><i class=\"wql_delIcon\"></i><input type="hidden" name="path" value=\"'+obj.pic[i]+'\" /></li>';
            $("#imgView").append(img);
        }
    };

    appGoback = function(){
        var turn = "${turn!}";
        var type = "${type!}";
        if(turn!=null && turn!=''){
            if(type==1){
                return window.location.href="${base}/getPostDetail?postId=${postId!}&turn=${turn!}";
            }
            if(turn==1){
                return window.location.href="${base}/index.html";
            }
            if(turn==2){
                return window.location.href="${base}/index.html?plateId=${plateId!}";
            }
            return window.location.href="${base}/index.html";
        }else{
            return window.location.href="${base}/index.html";
        }
    }

    function getUsers(){
        $.ajax({
            cache : false,
            contentType : false,
            dataType : "html",
            processData : false,
            type:"get",
            url:"${base}/page/manager/allPlateUser?postId=${postId}&plateId=${plateId}&turn=${turn!}",
            async: true,
            success:function(data){
                $(".zxf_popup.bgfff").html(data);
                $("#search").focus();
            }
        })
    }
$(".icon_at").on("click", function () {
    getUsers()
    $('.zxf_popup').animate({
        'bottom': 0
    }, 500, function () {
        $(".zxf_navRight").show();
    });
})
function  getBack() {
    //选完人后元素复位操作
    $(".zxf_navRight").hide();
    $('.zxf_popup').animate({
        'bottom': '-100%'
    }, 500, function () {});
    $(".index-list-bar").remove()
}
function setCaretPosition(ctrl, pos) { //获取光标位置
    if (ctrl.setSelectionRange) {
        ctrl.focus();
        ctrl.setSelectionRange(pos, pos);
    } else if (ctrl.createTextRange) {
        var range = ctrl.createTextRange();
        range.collapse(true);
        range.moveEnd('character', pos);
        range.moveStart('character', pos);
        range.select();
    }
}

$(document).on("touchstart",'.icon_audio',function(){
    $(".myAudio").each(function(i, item){
        item.pause();
        item.currentTime = 0
        $(".zxf_audioBtn").removeClass("on");
    });
    var limit = 9-$("#imgView>li").length;
    if(limit<=0){
        return $.alert("已选9张图片或语音！")
    }
    if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)){
        if(navigator.userAgent.indexOf('iOS_WKWebView') > -1){
            window.webkit.messageHandlers.openRecordDialog.postMessage({recordCallback:"recordCallback",limit:120});
        }else {
            openRecordDialog("recordCallback",120);
        }
    }else{
        //0：js回调函数名
        //1：录音最大时间
        jslistener.openRecordDialog("recordCallback",120);
    }
});
function recordCallback(ret,duration,data){
    //ret=0成功
    if(ret==0){
        var taking =transTime(parseInt(duration));
        var audio = '<li class="wql_li t_c audio">' +
                    '<div class="zxf_audioBox">' +
                    '   <audio class="myAudio" src=\"'+data+'\" type="audio/aac"></audio>' +
                    '   <a href="javascript:;" class="zxf_audioBtn mgt05"></a>' +
                    '   <span class="zxf_audio_durationTime mgt05">'+taking+'</span>' +
                    '</div>' +
                    '   <i class="wql_delIcon"></i>' +
                    '   <input type="hidden" name="myAudio" value=\"'+data+'\" />' +
                    '   <input type="hidden" name="taking" value=\"'+parseInt(duration)+'\" />' +
                    '</li>'
        $("#imgView").append(audio);
    }
}

$(document).on("touchstart", ".zxf_audioBtn", function(){
    $(".myAudio").each(function(i, item){
        item.pause();
        item.currentTime = 0
        $(".zxf_audioBtn").removeClass("on");
    });
    var audio = $(this).prev(".myAudio")[0];
    var obj = $(this)
    if($(this).hasClass("on")){
        $(this).removeClass("on");
        audio.pause();
        audio.currentTime = 0
    }else{
        $(this).parent("li").siblings().find(".zxf_audioBtn").removeClass("on");
        if($(this).parent("li").siblings().find(".zxf_audioBtn").prev(".myAudio")[0]!=null){
            $(this).parent("li").siblings().find(".zxf_audioBtn").prev(".myAudio")[0].pause();
        }
        $(this).addClass("on");
        audio.play();
        audio.addEventListener('timeupdate', function () {
            $(this).siblings('.zxf_audio_durationTime').html(transTime(audio.duration - audio.currentTime))
        }, false);
        audio.addEventListener('ended', function () {
            $(this).siblings('.zxf_audio_durationTime').html(transTime(audio.duration))
            obj.removeClass("on");
        }, false);
    }
});

    /**
     * 音频播放时间换算
     * @param {number} value - 音频当前播放时间，单位秒
     */
    function transTime(value) {
        var time = "";
        var h = parseInt(value / 3600);
        value %= 3600;
        var m = parseInt(value / 60);
        var s = parseInt(value % 60);
        if (h > 0) {
            time = formatTime(h + ":" + m + ":" + s);
        } else {
            time = formatTime(m + ":" + s);
        }
        return time;
    }
    /**
     * 格式化时间显示，补零对齐
     * eg：2:4  -->  02:04
     * @param {string} value - 形如 h:m:s 的字符串
     */
    function formatTime(value) {
        var time = "";
        var s = value.split(':');
        var i = 0;
        for (; i < s.length - 1; i++) {
            time += s[i].length == 1 ? ("0" + s[i]) : s[i];
            time += ":";
        }
        time += s[i].length == 1 ? ("0" + s[i]) : s[i];
        return time;
    }

    //图片浏览器
    $(".wql_ul").on('click','img',function () {

        // var img =  $('img');
        var img =  $(this).parents(".wql_ul").find('img')

        var imgSrc = [];
        for( var i =0;i<img.length;i++){
            imgSrc.push(img[i].src)
        }
        var index=0;

        for(var i=0;i<imgSrc.length;i++){
            if($(this)[0].src==imgSrc[i]){
                index=i;
                break;
            }
        }
        var myPhotoBrowserPopup = $.photoBrowser({
            photos :imgSrc,
            type: 'popup',
            initialSlide: index,
            // zoom:false,
            //loop:true
        });
        myPhotoBrowserPopup.open();
    });

</script>
</body>
</html>