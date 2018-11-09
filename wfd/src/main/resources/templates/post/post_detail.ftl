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
    <title>详情</title>
    <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/light7.css">
    <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/swiper.min.css">
    <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/wql_base.css">
    <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/weiLesson.css">
    <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/jquery.js"></script>
    <script>
        $.config = {
            autoInit: true,
            router:false,
        }
    </script>
    <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/light7.js"></script>
    <script type="text/javascript" src="${base}/js/fastclick.js"></script>
    <script type='text/javascript' src='http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/swiper.min.js' charset='utf-8'></script>
</head>
<body>
<div class="page">
    <header class="bar bar-nav bgfff">
        <a class="icon icon-left fl"></a>
        <h1 class="title">详情</h1>
        <#if tPlatePost.isTop>
            <a class="wql_icon wql_icon01 fr zindex20 isTop"></a>
        <#else>
            <a class="wql_icon wql_icon01 fr zindex20 "></a>
        </#if>

    </header>
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
    <div class="content pdb25">
        <div class="wql_g_box">
            <div class="wql_box01 clearfix wql_bg_fff pd075">
                <div class="wql_box_l fl"><img src="${tPlatePost.logoUrl!}"></div>
                <div class="wql_box_r">
                    <div class="wql_tit lh12 f085 c555">${tPlatePost.userName!}</div>
                    <div class="wql_subTit lh1 c9a9a9a f065">${tPlatePost.createTime?string('yyyy-MM-dd HH:mm:ss')} <em class="mglr05">|</em> ${tPlatePost.viewCount!0}浏览</div>
                </div>
            </div>
        </div>
        <div class="c353535 pdlr075 wql_bg_fff f075 lh1">${tPlatePost.content!}</div>
        <!-- 九宫格 S -->
        <div class="wql_g_list">
            <div class="wql_list02 pdlr05 pdt05 wql_bg_fff">
                <ul class="wql_ul clearfix pdlr025 bb pdb05">
                    <#list tPlatePost.postImgList as img>
                        <#if img.downUrl??>
                            <li class="wql_li">
                                <img src="${img.downUrl!}">
                            </li>
                        </#if>
                    </#list>
                </ul>
            </div>
        </div>
        <!-- 九宫格 E -->
        <!-- 点赞 S-->
        <div class="wql_g_like wql_bg_fff pdtb05">
            <div class="wql_like01 t_c">
                <#if tPlatePost.imgCount??>
                    <a href="javascript:;" class="zxf_like_btn on" onclick="getLikePost(this,'${tPlatePost.id}')">
                <#else>
                    <a href="javascript:;" class="zxf_like_btn" onclick="getLikePost(this,'${tPlatePost.id}')">
                </#if>
                    <i class="icon_like"></i>
                    <em>${tPlatePost.likeCount!0}</em>
                </a>
            </div>
        </div>
        <!-- 点赞 E-->

        <div class="wql_bg_fff pdlr075 mgt05" id="startView">
            <div class="clearfix lh22 f075 bb">
                <div class="fl" id="orderSort">回复时间排序</div>
                <span class="fr open-popup open-time" data-popup=".popup-time">排序<i class="icon icon-down mgl05"></i></span>
            </div>
            <ul class="wql_commentList">

            </ul>
        </div>

    </div>
    <div class="wql_g_footBtn">
        <div class="wql_footBtn01 bt f075 c9a9a9a wql_bg_fff clearfix">
            <a href="javascript:;" class="icon_voiceBtn fl mgtb035"></a>
            <p class="bl fl pdl075 mgtb035" style="width: 80%" onclick="sendMessage('${tPlatePost.id}')">说说你的看法...</p>
        </div>
    </div>

</div>
<script>
    //初始化FastClick实例
    $(function() {
        FastClick.attach(document.body);
    });
    <#if colType>
        $(".wql_icon.wql_icon01.fr.zindex20").addClass("isCol");
    </#if>


    var userType ="${userObj.userType!}";
    var userId = "${userObj.id?c}";
    var creator = "${tPlatePost.creator?c}";
    // 点赞
    $(document).on("click",".zxf_like_btn",function(){
        $(this).toggleClass("on");
    });

    // 时间排序
    $(".zxf_time_sort").on("click",".open-time",function(){
        if($(this).hasClass("on")){
            $(this).removeClass("on").find(".icon").addClass("icon-down").removeClass("icon-up");
        }else{
            $(this).addClass("on").find(".icon").addClass("icon-up").removeClass("icon-down");
        }
    });
    $(document).on("click",".popup-overlay, .popup-time a",function(){
        $(".open-time").removeClass("on").find(".icon").removeClass("icon-up").addClass("icon-down");
    });
    $(document).on("click",".fr.open-popup.open-time",function(){
        $(".open-time").removeClass("on").find(".icon").removeClass("icon-down").addClass("icon-up");
    });

function getLikePost(obj,postId){
        var type = 0;
        if($(obj).hasClass("on")){//删除
            type=1;
        }
        $.ajax({
            type:"get",
            url:"addPostLike",
            data:{
                "postId":postId,
                "type":type
            },
            async: true,
            dataType:'json',
            success:function(data){
                $(obj).find("em").text(data)
            }
        });
    }

    function sendMessage(postId){
        window.location.href="${base}/sendMessage.html?postId="+postId+"&turn=${turn!}";
    }

    function getLike(obj,messageId){
        var type = 0;
        if($(obj).hasClass("on")){//删除
            type=1;
        }
        $.ajax({
            type:"get",
            url:"addMessageLike",
            data:{
                "messageId":messageId,
                "type":type
            },
            async: true,
            dataType:'json',
            success:function(data){
                $(obj).find("em").text(data)
            }
        });
    }
    // 更多 S
    $(document).on("click",".wql_icon01.fr.zindex20",function(){

        var that = $(this);
        var type = $(this).hasClass('isTop');
        var type2 = $(this).hasClass('isCol');
        var isTop = $(this).hasClass('isTop') ? '取消置顶' : '置顶帖子' ;
        var isCol = $(this).hasClass('isCol') ? '取消收藏' : '收藏帖子' ;
        var col = $(this).hasClass('isTop') ? 'danger' : '#007aff' ;
        var col2 = $(this).hasClass('isCol') ? 'danger' : '#007aff' ;
        if(userType == 1){
            var buttons1 = [
                {
                    text: isTop,
                    color: col,
                    onClick: function() {
                        that.toggleClass('isTop');
                        $.ajax({
                            type:"get",
                            url:"updatePostByPostId",
                            data:{
                                "postId":"${tPlatePost.id}",
                                "type":type
                            },
                            async: true,
                            dataType:'json',
                            success:function (msg) {
                                if(msg==1){
                                    $(".zxf_coverWp").show(2000);
                                    $('.toastBox_ok').show();
                                    setTimeout(function(){
                                        $(".zxf_coverWp").hide();
                                    },2000)
                                }else{
                                    $.alert("操作失败！")
                                }
                            }
                        });
                    }
                },
                {
                    text: '删除帖子',
                    onClick: function() {
                        $.modal({
                            title:  '提示',
                            text: '删除此帖子后，其中的所有回复都会被删除。',
                            buttons: [
                                {
                                    text: '取消',
                                    onClick: function() {

                                    }
                                },
                                {
                                    text: '删除帖子',
                                    onClick: function() {
                                        $.ajax({
                                            type:"get",
                                            url:"deletePostByPostId",
                                            data:{
                                                "postId":"${tPlatePost.id}",
                                            },
                                            async: true,
                                            dataType:'json',
                                            success:function (msg) {
                                                if(msg==1){
                                                    $(".zxf_coverWp").show(2000);
                                                    $('.toastBox_ok').show();
                                                    setTimeout(function(){
                                                        $(".zxf_coverWp").hide();
                                                    },2000)
                                                    setTimeout(function (){
                                                        window.location.href="${base}/index.html"
                                                    }, 2000);
                                                }
                                            }
                                        });
                                    }
                                },
                            ]
                        })
                    }
                },
                {
                    text: isCol,
                    color: col2,
                    onClick: function() {
                        that.toggleClass('isCol');
                        $.ajax({
                            type:"get",
                            url:"addCollection",
                            data:{
                                "postId":"${tPlatePost.id}",
                                "type":type2
                            },
                            async: true,
                            dataType:'json',
                            success:function (msg) {
                                if(msg==1){
                                    $(".zxf_coverWp").show(2000);
                                    $('.toastBox_ok').show();
                                    setTimeout(function(){
                                        $(".zxf_coverWp").hide();
                                    },2000)
                                }else if(msg==2){
                                    $.alert("已添加！")
                                }else{
                                    $.alert("添加失败！")
                                }
                            }

                        })
                    }
                }
            ];
            var buttons2 = [
                {
                    text: '取消',

                }
            ];
            var groups = [buttons1, buttons2];
            $.actions(groups);
        }else {
            var buttons1 = [
                {
                    text: isCol,
                    color: col2,
                    onClick: function() {
                        that.toggleClass('isCol');
                        $.ajax({
                            type:"get",
                            url:"addCollection",
                            data:{
                                "postId":"${tPlatePost.id}",
                                "type":type2
                            },
                            async: true,
                            dataType:'json',
                            success:function (msg) {
                                if(msg==1){
                                    $(".zxf_coverWp").show(2000);
                                    $('.toastBox_ok').show();
                                    setTimeout(function(){
                                        $(".zxf_coverWp").hide();
                                    },2000)
                                }else if(msg==2){
                                    $.alert("已添加！")
                                }else{
                                    $.alert("添加失败！")
                                }
                            }

                        })
                    }
                }
            ];
            var buttons2 = [
                {
                    text: '取消',

                }
            ];
            var groups = [buttons1, buttons2];
            $.actions(groups);
        }

    }).on("click",".modal-overlay",function(){
        $(this).removeClass("modal-overlay-visible");
        $(".actions-modal").hide();
    });

    // 评论
    $(document).on("click",".wql_icon01.fr.mgl05",function(){
        var type = $(this).siblings(".wql_flag02").text()!=null && $(this).siblings(".wql_flag02").text()!='';
        var isCol = $(this).siblings(".wql_flag02").text()!=null && $(this).siblings(".wql_flag02").text()!='' ? '取消最佳答案' : '设置最佳答案' ;
        var col2 =  $(this).siblings(".wql_flag02").text()!=null && $(this).siblings(".wql_flag02").text()!='' ? 'danger' : '#007aff' ;
        var messageId = $(this).find("input[name='messageId']").val();
        
        var pinlunId = $(this).find("input[name='messageId']").attr("userId");

        if(userType==1){
            var buttons1 = [
                {
                    text: isCol,
                    color: col2,
                    onClick: function() {
                        $.ajax({
                            type:"get",
                            url:"bestAnswer",
                            data:{
                                "messageId":messageId,
                                "type":type
                            },
                            async: true,
                            dataType:'json',
                            success:function (msg) {
                                if(msg==1){
                                   window.location.reload()
                                }
                            }
                        })
                    }
                },
                {
                    text: '删除评论',
                    onClick: function() {
                        $.modal({
                            title:  '提示',
                            text: '确定删除此评论？',
                            buttons: [
                                {
                                    text: '取消',
                                    onClick: function() {

                                    }
                                },
                                {
                                    text: '确定',
                                    onClick: function() {
                                        $.ajax({
                                            type:"get",
                                            url:"deleteMessageByMessageId",
                                            data:{
                                                "messageId":messageId,
                                            },
                                            async: true,
                                            dataType:'json',
                                            success:function (msg) {
                                                if(msg==1){
                                                   window.location.reload()
                                                }
                                            }
                                        });
                                    }
                                },
                            ]
                        })
                    }
                },
                {
                    text: '收藏评论',
                    onClick: function() {
                        $.ajax({
                            type:"get",
                            url:"addCollectionMessage",
                            data:{
                                "messageId":messageId,
                            },
                            async: true,
                            dataType:'json',
                            success:function (msg) {
                                if(msg==1){
                                    $(".zxf_coverWp").show(2000);
                                    $('.toastBox_ok').show();
                                    setTimeout(function(){
                                        $(".zxf_coverWp").hide();
                                    },2000)
                                }else if(msg==2){
                                    $.alert("已添加！")
                                }else{
                                    $.alert("添加失败！")
                                }
                            }

                        })
                    }
                }
            ];
            var buttons2 = [
                {
                    text: '取消',

                }
            ];
            var groups = [buttons1, buttons2];
            $.actions(groups);
        }else if(userId == pinlunId){
        	 var buttons1 = [
        	 		 {
	                    text: '删除评论',
	                    onClick: function() {
	                        $.modal({
	                            title:  '提示',
	                            text: '确定删除此评论？',
	                            buttons: [
	                                {
	                                    text: '取消',
	                                    onClick: function() {
	
	                                    }
	                                },
	                                {
	                                    text: '确定',
	                                    onClick: function() {
	                                        $.ajax({
	                                            type:"get",
	                                            url:"deleteMessageByMessageId",
	                                            data:{
	                                                "messageId":messageId,
	                                            },
	                                            async: true,
	                                            dataType:'json',
	                                            success:function (msg) {
	                                                if(msg==1){
	                                                   window.location.reload()
	                                                }
	                                            }
	                                        });
	                                    }
	                                },
	                            ]
	                        })
	                    }
	                },
	                {
	                    text: '收藏评论',
	                    onClick: function() {
	                        $.ajax({
	                            type:"get",
	                            url:"addCollectionMessage",
	                            data:{
	                                "messageId":messageId,
	                            },
	                            async: true,
	                            dataType:'json',
	                            success:function (msg) {
	                                if(msg==1){
	                                    $(".zxf_coverWp").show(2000);
	                                    $('.toastBox_ok').show();
	                                    setTimeout(function(){
	                                        $(".zxf_coverWp").hide();
	                                    },2000)
	                                }else if(msg==2){
	                                    $.alert("已添加！")
	                                }else{
	                                    $.alert("添加失败！")
	                                }
	                            }
	
	                        })
	                    }
	                }
	            ];
	            var buttons2 = [
	                {
	                    text: '取消',
	
	                }
	            ];
	            var groups = [buttons1, buttons2];
	            $.actions(groups);
        }else{
            var buttons1 = [
                {
                    text: '收藏评论',
                    onClick: function() {
                        $.ajax({
                            type:"get",
                            url:"addCollectionMessage",
                            data:{
                                "messageId":messageId,
                            },
                            async: true,
                            dataType:'json',
                            success:function (msg) {
                                if(msg==1){
                                    $(".zxf_coverWp").show(2000);
                                    $('.toastBox_ok').show();
                                    setTimeout(function(){
                                        $(".zxf_coverWp").hide();
                                    },2000)
                                }else if(msg==2){
                                    $.alert("已添加！")
                                }else{
                                    $.alert("添加失败！")
                                }
                            }

                        })
                    }
                }
            ];
            var buttons2 = [
                {
                    text: '取消',

                }
            ];
            var groups = [buttons1, buttons2];
            $.actions(groups);
        }

    }).on("click",".modal-overlay",function(){
        $(this).removeClass("modal-overlay-visible");
        $(".actions-modal").hide();
    });

appGoback = function(){
    var turn = "${turn!}";
    var plateId = "${tPlatePost.plateId!}";
    if(turn!=null && turn!=''){
        if(turn==1){
            return window.location.href="${base}/index.html?tabType=${tabType!}";
        }
        if(turn==2){
            if(plateId!=null && plateId!=''){
                return window.location.href="${base}/index.html?plateId="+plateId
            }
        }
        if(turn==3){
            return window.location.href="${base}/getUserPost.html";
        }
        if(turn==4){
            return window.location.href="${base}/getPostMessage.html";
        }
        if(turn==5){
            return  window.location.href="${base}/getLike.html";
        }
        if(turn==6){
            return window.location.href="${base}/getReply.html";
        }
        if(turn==7){
            return  window.location.href="${base}/getCollection.html";
        }
        if(turn==8){
            return  window.location.href="${base}/hotQeustion.html";
        }
    }else{
        return window.location.href="${base}/index.html";
    }
}

    $(document).on('touchend', '.icon-left.fl',function(e) {
        var turn = "${turn!}";
        var plateId = "${tPlatePost.plateId!}";
        if(turn!=null && turn!=''){
            if(turn==1){
                return window.location.href="${base}/index.html?tabType=${tabType!}";
            }
            if(turn==2){
                if(plateId!=null && plateId!=''){
                    return window.location.href="${base}/index.html?plateId="+plateId
                }
            }
            if(turn==3){
                return window.location.href="${base}/getUserPost.html";
            }
            if(turn==4){
                return window.location.href="${base}/getPostMessage.html";
            }
            if(turn==5){
                return  window.location.href="${base}/getLike.html";
            }
            if(turn==6){
                return window.location.href="${base}/getReply.html";
            }
            if(turn==7){
                return  window.location.href="${base}/getCollection.html";
            }
            if(turn==8){
                return  window.location.href="${base}/hotQeustion.html";
            }
        }else{
            return window.location.href="${base}/index.html";
        }

    });

    getPostMessagesByPostId(1);
    function getPostMessagesByPostId(type){
        $("#orderSort").text("回复时间排序")
        var orderByClause = "tpm.create_time desc";
        if(type==2){
            orderByClause = "like_count desc";
            $("#orderSort").text("点赞数量排序")
        }
        
        
        
        $.ajax({
            type:"get",
            url:"getPostMessagesByPostId",
            data:{
                "postId":"${tPlatePost.id}",
                "orderByClause":orderByClause
            },
            async: true,
            dataType:'json',
            success:function(data){
                $('.wql_commentList').empty();
                if(data.list!=null && data.list.length>0){
                    var html = '';
                    for (var i = 0; i <data.list.length; i++) {
                        html +='<li class="wql_li pdb075 bb" id="'+data.list[i].id+'">' +
                                '                    <div class="wql_g_box">' +
                                '                        <div class="wql_box01 clearfix pdt075 pdb05">' +
                                '                            <div class="wql_box_l fl"><img src="'+data.list[i].logoUrl+'"></div>' +
                                '                            <div class="wql_box_r">' +
                                '                                <div class="wql_tit lh12 f085 c555">'+data.list[i].userName+''
                                if(data.list[i].userType ==1){
                                    html +='<span class="wql_flag wql_flag01">老师</span>'
                                }
                                if(data.list[i].userId =="${tPlatePost.creator}"){
                                    html +='<span class="wql_flag wql_flag03">楼主</span>'
                                }
                                if(data.list[i].isTop){
                                    html +='<span class="wql_flag wql_flag02">最佳答案</span>'
                                }
                        html +='<span class="wql_icon wql_icon01 fr mgl05"><input type="hidden" name="messageId" userId="'+data.list[i].userId+'" value="'+data.list[i].id+'"></span>' +
                                '                                    <div class="wql_g_like fr">'+
                                '                                        <div class="wql_like01 ">'
                                if(data.list[i].messageName!=''){
                                    html +='                                            <a href="javascript:;" onclick="getLike(this,\''+data.list[i].id+'\')" class="zxf_like_btn on">'
                                }else{
                                    html +='                                            <a href="javascript:;" onclick="getLike(this,\''+data.list[i].id+'\')" class="zxf_like_btn">'
                                }
                                html +='                                                <i class="icon_like"></i>' +
                                '                                                <em>'+data.list[i].likeCount+'</em>' +
                                '                                            </a>' +
                                '                                        </div>' +
                                '                                    </div>' +
                                '                                </div>' +
                                '                                <div class="wql_subTit lh1 c9a9a9a f065">'+data.list[i].createTimeStr+'</div>' +
                                '                            </div>' +
                                '                        </div>' +
                                '                    </div>' +
                                        '<div class="mgl3 mgr125 c353535 f075 lh1 zxf_talkCon">' +
                                        '                      <div class="zxf_overHideWp"><div class="zxf_overCon">' +
                                        '                        '+data.list[i].content+'' +
                                        '                      </div></div>' +
                                        '                      <a href="javascript:;" class="zxf_upBtn f07 ">' +
                                        '                        <em>展开全文</em>' +
                                        '                        <i class="icon_blue_down mgl05"></i>' +
                                        '                      </a>' +
                                        '                    </div>'
                        html += '                    <div class="mgl3 mgr125">' +
                                '                        <div class="wql_g_list">' +
                                '                            <div class="wql_list02 wql_bg_fff">' +
                                '                                <ul class="wql_ul clearfix">'
                        if(data.list[i].tPostMessageAudios!=null &&　data.list[i].tPostMessageAudios.length>0){
                            for(var j = 0; j < data.list[i].tPostMessageAudios.length; j++){
                                if(data.list[i].tPostMessageAudios[j].downUrl!=null && data.list[i].tPostMessageAudios[j].downUrl!=''){
                                    var useTaking = transTime(data.list[i].tPostMessageAudios[j].audioUsetaking)
                                    html +='<li class="wql_li t_c audio">' +
                                            '<div class="zxf_audioBox">'+
                            '                    <audio class="myAudio">'+
                            '                        <source src=\"'+data.list[i].tPostMessageAudios[j].downUrl+'\"  type="audio/aac">'+
                            '                    </audio>'+
                            '                    <a href="javascript:;" class="zxf_audioBtn mgt05"></a>'+
                            '                    <span class="zxf_audio_durationTime mgt05">'+useTaking+'</span>'+
                            '                </div> '+
                            '                </li>'
                                }
                            }
                        }
                        if(data.list[i].tPostMessageImgs!=null &&　data.list[i].tPostMessageImgs.length>0){
                            for(var j = 0; j < data.list[i].tPostMessageImgs.length; j++){
                                if(data.list[i].tPostMessageImgs[j].downUrl!=null && data.list[i].tPostMessageImgs[j].downUrl!=''){
                                    html +='                                    <li class="wql_li"  onclick="getPic(this,'+j+')">' +
                                            '                                        <img src=\''+data.list[i].tPostMessageImgs[j].downUrl+'\'>' +
                                            '                                    </li>'
                                }
                            }
                        }
                         html +='                                </ul>' +
                                '                            </div>' +
                                '                        </div>' +
                                '                    </div>' +
                                '                </li>';
                    }
                    $('.wql_commentList').append(html);
                    var messageId ="${messageId!}";
                    if(messageId!=''&& messageId !='undefined'){
                        $(".content ").animate({scrollTop: ($("#"+messageId).offset().top-48)}, 1000);
                    }


                }else {
                    var html='<div class="zxf_noCon_wp t_c pdtb2">' +
                            '            <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_nocon2.png" alt="" class="img_nocon2">' +
                            '            <p class="mgt075">还没有人评论帖子哦!</p>' +
                            '          </div>';
                    $('.wql_commentList').append(html);
                }
                var sendEntrance ="${sendEntrance!}"
                if(sendEntrance!=''&& sendEntrance !='undefined'){
                    $(".content ").animate({scrollTop: ($("#startView").offset().top-48)}, 1000);
                }
                // 展开收起
                var $p = $(".wql_commentList").find(".zxf_overCon");
                $p.each(function(i, item){
                    var H = $(item).height();
                    if( H >= 88) {
                        $(item).parents(".zxf_talkCon").addClass("pdb15")
                        $(this).parents(".zxf_overHideWp").next(".zxf_upBtn").show();
                    }else{
                        $(this).parents(".zxf_overHideWp").next(".zxf_upBtn").hide();
                    }
                })
            }
        });
    }


    function getPic(obj,num){
        var img =  $(obj).parents(".wql_list02").find('img')

        var imgSrc = [];
        for( var i =0;i<img.length;i++){
            imgSrc.push(img[i].src)
        }
        var index=num;
        if(imgSrc==null || imgSrc.length<=0){
            return;
        }
        var myPhotoBrowserPopup = $.photoBrowser({
            photos :imgSrc,
            type: 'popup',
            initialSlide: index,
            // zoom:false,
            //loop:true
        });
        myPhotoBrowserPopup.open();
    }

    //图片浏览器
    $(".wql_g_list .wql_list02").on('click','img',function () {

        // var img =  $('img');
        var img =  $(this).parents(".wql_list02").find('img')


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


$(".wql_commentList").on("click", ".zxf_upBtn", function(){
    $(this).toggleClass("on");
    var $em = $(this).find("em"),
            $overHWp = $(this).parents(".zxf_talkCon").find(".zxf_overHideWp");
    if($(this).hasClass("on")){
        $overHWp.css("max-height", "none" );
        $em.text("收起");
    }else{
        $overHWp.css("max-height", "3rem" );
        $em.text("展开全文");
    }
});

$(document).on("click", ".zxf_audioBtn", function(){
    var audio = $(this).prev(".myAudio")[0];
    var obj = $(this);
    if($(this).hasClass("on")){
        $(this).removeClass("on");
        audio.pause();
        audio.currentTime = 0
    }else{
        $(".myAudio").each(function(i, item){
            item.pause();
            item.currentTime = 0
            $(".zxf_audioBtn").removeClass("on");
        });
        $(this).addClass("on");
        audio.play();
        // 监听音频播放时间并更新进度条
        audio.addEventListener('timeupdate', function () {
            var audioTime = audio.duration - audio.currentTime;
            if(audioTime<0){
                audioTime=0
            }
            $(this).siblings('.zxf_audio_durationTime').html(transTime(audioTime))
        }, false);
        // 监听播放完成事件
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



    $(document).on('touchend', '.icon_voiceBtn',function(e) {
        $(".myAudio").each(function(i, item){
            item.pause();
            item.currentTime = 0
            $(".zxf_audioBtn").removeClass("on");
        });
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
    })

    function recordCallback(ret,duration,data){
        if(ret==0){
            $(".zxf_coverWp").show(2000);
            $(".toastBox_send").show().next('.toastBox_ok').hide();
            var taking =parseInt(duration);
            $.ajax({
                type:"post",
                url:"saveAudio",
                data:{
                    base64:data,
                    taking:taking,
                    postId:"${tPlatePost.id}"
                },
                dataType : "json",
                async: true,
                success:function (msg) {
                    if(msg==1){
                        $(".zxf_coverWp").hide().find(".toastBox_send").hide();
                        $(".zxf_coverWp").show();
                        $(".toastBox_ok").show().next('.toastBox_send').hide();
                        setTimeout(function (){
                            $(".zxf_coverWp").hide();
                            setTimeout(function (){
                                window.location.reload();
                            }, 100);
                        }, 1000);
                    }
                }
            })
        }
    }
</script>
<!-- popup弹窗 -->
<div class="popup popup-time">
    <div class="zxf_block pdlr075">
        <div class="zxf_opretion mgb05 bgStyle">
            <a href="javascript:;" class="refuse_btn bb close-popup" onclick="getPostMessagesByPostId(1)">回复时间排序</a>
            <a href="javascript:;" class="send_btn close-popup" onclick="getPostMessagesByPostId(2)">点赞数量排序</a>
        </div>
        <a href="javascript:;" class="cancel_btn close-popup bgStyle">取消</a>
    </div>
</div>
</body>
</html>