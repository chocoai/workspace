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
        <h1 class="title">发帖</h1>
        <a class="zindex20 fr lh22 wql_link zxf_declareBtn" id="publishBtn">发布</a>
    </header>
    <div class="zxf_coverWp">
        <div class="zxf_toastBox toastBox_send">
            <i class="icon_sending"></i>
            <p>正在发布...</p>
        </div>
        <div class="zxf_toastBox toastBox_send2">
            <i class="icon_sending"></i>
            <p>正在保存...</p>
        </div>
        <div class="zxf_toastBox toastBox_ok">
            <i class="icon_ok"></i>
            <p>操作成功</p>
        </div>
    </div>
    <div class="content">
        <div class="wql_inputBox mgt05 wql_bg_fff pdt05 f075">
            <div name="content" id="content" class="inputor needsclick wql_inp pdlr075" contenteditable="true" style="height: 8rem;width: 100%;overflow-y: auto;box-sizing: border-box;padding:.5rem;"></div>
            <div class="zxf_processWp pdtb0625  pdlr075 bt bb">
                <a href="javascript:;" class="icon_photo mgr120"></a>
                <a href="javascript:;" class="icon_at"></a>
            </div>
        </div>
        <!-- 上传图片 S-->
        <form id="addForm" method="post" action="addPost" enctype="multipart/form-data">
        <input type="hidden" name="turn" value="${turn!}">
        <#if userIds??>
            <#list userIds as userId>
                <input type="hidden" name="userId" value="${userId}">
            </#list>
        </#if>
        <div class="wql_g_upImg pdt075 pdlr075 wql_bg_fff bb">
            <ul class="wql_ul clearfix" id="imgView">
                <#if tPlatePostEditImgs??>
                    <#list tPlatePostEditImgs as img>
                    <li class="wql_li">
                        <img src="${img.imgUrl!}">
                        <input type="hidden" value="${img.imgUrl!}" name="imgHttpUrls"/>
                        <i class="wql_delIcon"></i>
                    </li>
                    </#list>
                </#if>
            </ul>
        </div>
        <!-- 上传图片 E-->
        <div class="zxf_creatClass_item pdlr075 bgfff">
            <div class="zxf_creadHead f075 c_616161 pdtb075 bb">
                <span class="mgr075 tit">选择归属版块</span>
            </div>
            <div class="zxf_creatCon pdtb075 c_normal f085">
                <div class="zxf_relModuleBox pdtb05">
                    <ul class="clearfix">
                        <#list tPlates as tPlate>
                        <li class="t_c <#if tPlatePostEdit?? && tPlatePostEdit.plateId??><#if tPlate.id=tPlatePostEdit.plateId>on</#if><#elseif plateId??><#if tPlate.id=plateId>on</#if></#if>">
                            <a href="javascript:;" class="f075" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">${tPlate.name}</a>
                            <input type="hidden" name="plateId" value="${tPlate.id}">
                        </li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>
        </form>
    </div>
    <div class="zxf_popup bgfff">
        <jsp:include page="../manager/atPage.jsp"></jsp:include>
    </div>

</div>
<input type="hidden" name="platePostEditContent" id="platePostEditContent" value="<#if tPlatePostEdit??>${tPlatePostEdit.content!}</#if>">
<script>
    //初始化FastClick实例
    $(function() {
        FastClick.attach(document.body);
    });
    var post_flag = false;
    var edit_flag =false;
    var tPlatePostEditId = "";
    <#if tPlatePostEdit??  && tPlatePostEdit.content??>
    var contentEdit = $("#platePostEditContent").val();
    tPlatePostEditId="${tPlatePostEdit.id!}";
    $("#content").html(contentEdit);
    </#if>
    // 统计
    $(document).on('keyup','.wql_inputBox .wql_inp',function(){
        var $that = $(this);
        var $str = $that.val();
        var $curNum = $that.next().find('.wql_curNum');
        $curNum.text($str.length);
    })

    // 删除
    $(document).on('touchend','.wql_delIcon',function(){
        $li = $(this).parents('.wql_li');
        $li.remove();
        /*var limit = 9-$("input[name='path']").length;
        if(limit>0){//添加+号
            if(!$("#updateImg").hasClass("wql_btn_upimg")){
                var html ='<li class="wql_li">\n' +
                        '                    <div class="wql_btn_upimg" id="updateImg"/>\n' +
                        '                </li>';
                $("#imgView").append(html);
            }
        }*/
    })
    var plateId="${plateId!}";
    <#if tPlatePostEdit?? && tPlatePostEdit.plateId??>
        plateId="${tPlatePostEdit.plateId!}"
    </#if>
    $(document).on('touchend','.zxf_relModuleBox li',function (){
        $(this).addClass('on').siblings().removeClass('on');
        plateId=$(this).find("input[name='plateId']").val();
    })
    $(document).on('touchend','#publishBtn',function (){
        var content = $("#content").html();
        var ranges = [
            '\ud83c[\udf00-\udfff]',
            '\ud83d[\udc00-\ude4f]',
            '\ud83d[\ude80-\udeff]'
        ];
        content = content .replace(new RegExp(ranges.join('|'), 'g'), '');
        var limit = 9-$("#imgView>li").length;
        var result =0;
        for(var i=0;i<$('.zxf_relModuleBox li').length;i++){
            if($('.zxf_relModuleBox li').eq(i).hasClass('on')){
                result =1;
            }
        }
        if(result ==0 ){
            $.alert("请选择归属模板！")
            return;
        }
        if(limit==9){
            if(content==null || content==''){
                return $.alert("请填写内容！")
            }
        }
        if(post_flag){
            return $.alert("正在发送中，请勿重复操作！")
        }
        post_flag = true;//标记当前状态为正在提交状态

        for(var i=0;i<$('.zxf_relModuleBox li').length;i++){
            if(!$('.zxf_relModuleBox li').eq(i).hasClass('on')){
                var $input =$('.zxf_relModuleBox li').eq(i).find("input");
                $input.remove();
            }
        }
        var form = new FormData(document.getElementById("addForm"));
        var inputType=$("<input type='hidden' name='content'/>");
        inputType.attr("value",content);
        //注入参数到表单
        form.append("content",content);
        //form.hide();
        //提交表单
        //form.submit();
        $.ajax({
            type: "post",
            url: "addPost",
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
                            window.location.href="getPostDetail?postId="+data.postId+"&plateId="+plateId+"&turn=${turn!}"
                        }, 100);
                    }, 1000);

                }
            }
        });
    });

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


    $(document).on('touchend','#closeBtn',function (){
        var content = $("#content").html();
        var limit = 9-$("#imgView>li").length;
        if(limit==9){
            if(content==null || content==''){
                location.href='${base}/index.html?plateId=${plateId!}';
                return;
            }
        }
        $.modal({
            text: '将此次编辑保留？',
            buttons: [
                {
                    text: '不保留',
                    onClick: function() {
                        if(tPlatePostEditId ==""){
                            location.href='${base}/index.html?plateId=${plateId!}';
                        }else{
                            $.ajax({
                                type:"get",
                                url:"deletePostContent",
                                data:{
                                    "id":tPlatePostEditId,
                                },
                                async: true,
                                dataType:'json',
                                success:function (msg) {
                                    if(msg==1){
                                        location.href='${base}/index.html?plateId=${plateId!}';
                                    }
                                }
                            })
                        }
                    }
                },
                {
                    text: '保留',
                    onClick: function() {
                        if(edit_flag){
                            return $.alert("正在保存，请勿重复操作！")
                        }
                        plateId=$(".clearfix").find(".on").find("input[name='plateId']").val();
                        var content = $("#content").html();
                        var imgUrls= new Array();
                        var userIds= new Array();
                        $("input[name='path']").each(function(){
                            imgUrls.push($(this).val());
                        });
                        $("input[name='userId']").each(function(){
                            userIds.push($(this).val());
                        });
                        var imgHttpUrls= new Array();imgHttpUrls
                        $("input[name='imgHttpUrls']").each(function(){
                            imgHttpUrls.push($(this).val());
                        });
                        edit_flag = true;
                        $(".zxf_coverWp").show(2000);
                        $(".toastBox_send2").show().next('.toastBox_ok').hide();
                        $.ajax({
                            type:"post",
                            url:"savePostContent",
                            data:{
                                "content":content,
                                "plateId":plateId,
                                "imgUrls":imgUrls,
                                "imgHttpUrls":imgHttpUrls,
                                "userIds":userIds
                            },
                            async: true,
                            dataType:'json',
                            traditional:true,
                            success:function (msg) {
                                $(".zxf_coverWp").hide();
                                if(msg==1){
                                    $(".zxf_coverWp").hide().find(".toastBox_send2").hide();
                                    $(".zxf_coverWp").show();
                                    $(".toastBox_ok").show().next('.toastBox_send2').hide();
                                    setTimeout(function (){
                                        $(".zxf_coverWp").hide();
                                        setTimeout(function (){
                                            location.href='${base}/index.html?plateId=${plateId!}';
                                        }, 100);
                                    }, 1000);
                                }
                            }
                        })
                    }
                },
            ]
        })
    })

    $(document).on('touchend','.icon_photo.mgr120',function(){
        var limit = 9-$("#imgView>li").length;
        if(limit<=0){
            return $.alert("已选9张图片！")
        }
        if(navigator.userAgent.indexOf('Android') > -1){
            gainAnswerPicture(1,limit);
        }else {
            gainAnswerPictureIos(1,limit);
        }
    })

    appGoback = function(){
        var content = $("#content").html();
        var limit = 9-$("#imgView>li").length;
        if(limit==9){
            if(content==null || content==''){
                location.href='${base}/index.html?plateId=${plateId!}';
                return;
            }
        }
        $.modal({
            text: '将此次编辑保留？',
            buttons: [
                {
                    text: '不保留',
                    onClick: function() {
                        if(tPlatePostEditId ==""){
                            location.href='${base}/index.html?plateId=${plateId!}';
                        }else{
                            $.ajax({
                                type:"get",
                                url:"deletePostContent",
                                data:{
                                    "id":tPlatePostEditId,
                                },
                                async: true,
                                dataType:'json',
                                success:function (msg) {
                                    if(msg==1){
                                        location.href='${base}/index.html?plateId=${plateId!}';
                                    }
                                }
                            })
                        }
                    }
                },
                {
                    text: '保留',
                    onClick: function() {
                        if(edit_flag){
                            return $.alert("正在保存，请勿重复操作！")
                        }
                        plateId=$(".clearfix").find(".on").find("input[name='plateId']").val();
                        var content = $("#content").html();
                        var imgUrls= new Array();
                        var userIds= new Array();
                        $("input[name='path']").each(function(){
                            imgUrls.push($(this).val());
                        });
                        $("input[name='userId']").each(function(){
                            userIds.push($(this).val());
                        });
                        var imgHttpUrls= new Array();imgHttpUrls
                        $("input[name='imgHttpUrls']").each(function(){
                            imgHttpUrls.push($(this).val());
                        });
                        edit_flag = true;
                        $(".zxf_coverWp").show(2000);
                        $(".toastBox_send2").show().next('.toastBox_ok').hide();
                        $.ajax({
                            type:"post",
                            url:"savePostContent",
                            data:{
                                "content":content,
                                "plateId":plateId,
                                "imgUrls":imgUrls,
                                "imgHttpUrls":imgHttpUrls,
                                "userIds":userIds
                            },
                            async: true,
                            dataType:'json',
                            traditional:true,
                            success:function (msg) {
                                $(".zxf_coverWp").hide();
                                if(msg==1){
                                    $(".zxf_coverWp").hide().find(".toastBox_send2").hide();
                                    $(".zxf_coverWp").show();
                                    $(".toastBox_ok").show().next('.toastBox_send2').hide();
                                    setTimeout(function (){
                                        $(".zxf_coverWp").hide();
                                        setTimeout(function (){
                                            location.href='${base}/index.html?plateId=${plateId!}';
                                        }, 100);
                                    }, 1000);
                                }
                            }
                        })
                    }
                },
            ]
        })
    }

    function gainAnswerPictureIos(flagId,limit){
        if(navigator.userAgent.indexOf('iOS_WKWebView') > -1){
            window.webkit.messageHandlers.gainPicture.postMessage({flagId:flagId,limit:limit});
        }else {
            gainPicture(1, limit);
        }
    }

    function gainAnswerPicture(flagId,limit) {
        !window.jslistener || window.jslistener.gainPicture(flagId,limit );
    }

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
        /*var limit = 9-$("input[name='path']").length;
        if(limit>0){//添加+号
            if(!$("#updateImg").hasClass("wql_btn_upimg")){
                var html ='<li class="wql_li">\n' +
                        '                    <div class="wql_btn_upimg" id="updateImg"/>\n' +
                        '                </li>';
                $("#imgView").append(html);
            }
        }
        if(limit<=0){//删除+号
            $li = $("#updateImg").parents('.wql_li');
            $li.remove();
        }*/
    };

    function getUsers(){
        $.ajax({
            cache : false,
            contentType : false,
            dataType : "html",
            processData : false,
            type:"get",
            url:"${base}/page/manager/allPlateUser?plateId="+plateId,
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