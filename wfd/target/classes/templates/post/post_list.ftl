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
        <h1 class="title">我的帖子</h1>
    </header>
    <div class="zxf_coverWp">
        <div class="zxf_toastBox toastBox_ok">
            <i class="icon_ok"></i>
            <p>操作成功</p>
        </div>
    </div>
    <div class="content zxf_content infinite-scroll" data-distance="100">
        <div class="zxf_con-block">
            <div class="zxf_stu_item_wp mgb05">

            </div>
            <!-- 加载提示符 -->
            <div class="infinite-scroll-preloader">
                <div class="preloader">
                </div>
            </div>
        </div>
    </div>
    <!-- 弹窗 -->
    <script>

        //初始化FastClick实例
        $(function() {
            FastClick.attach(document.body);
        });
        // 下拉加载
        var loading = false;
        var maxItems = 16;//分页中总数量
        var itemsPerLoad = 10;//分页中每页的个数;默认10条
        var lastIndex = 2;//默认显示2条数据，latsIndex表示当前最后的索引显示
        getLikeListInit(0,itemsPerLoad);
        function getLikeListInit(pageNum,itemsPerLoad){
            $.ajax({
                type:"get",
                url:"getPostList",
                data:{
                    "pageNum":pageNum,
                    "pageSize":itemsPerLoad
                },
                async: true,
                dataType:'json',
                success:function(data){
                    maxItems=data.pageTotal;
                    if(data.list.length>0){
                        var html = '';
                        for (var i = 0; i <data.list.length; i++) {
                            html +=' <div class="zxf_stu_item pd075 mgt025 bgfff" id="'+i+'">' +
                                    '                    <div class="clearfix">' +
                                    '                        <div class="zxf_item_left fl">' +
                                    '                  <span class="date t_c">' +
                                    '                    <em class="day">'+data.list[i].day+'</em>' +
                                    '                    <em class="month">'+data.list[i].month+'</em>' +
                                    '                    <em class="month">'+data.list[i].dataTime+'</em>' +
                                    '                  </span>' +
                                    '                        </div>' +
                                    '                        <div class="zxf_stu_text">' +
                                    '                            <div class="zxf_mypostCon pdt05">' +
                                    '                                <div class="zxf_mypost_item">' +
                                    '                                    <a href="#" class="wql_icon wql_icon01 fr zindex20" onclick="deletePost(\''+data.list[i].id+'\')"></a>' +
                                    '                                    <div class="zxf_desBox" onclick="getPostDetail(\''+data.list[i].id+'\',\''+i+'\')">' +
                                    '                                            <h2 class="clearfix" style="margin-top: -10px;">' +
                                    '                                                <span class="subject">'+data.list[i].plateName+'</span>' +
                                    '                                            </h2>' +
                                    '                                            <div class="zxf_des_wp mult_line_ellipsis">' +
                                    '                                                    '+data.list[i].content+'' +
                                    '                                            </div>' +
                                    '                                    </div>'
                            if(data.list[i].postImgList.length>0){
                                for(var j = 0; j < 1; j++){
                                    if(data.list[i].postImgList[j].downUrl!=null && data.list[i].postImgList[j].downUrl!=''){
                                        html += '<div class="zxf_img_Box mgt05">'+
                                                '<a href="javascript:;" class="photo_btn">共<em class="num">'+data.list[i].postImgList.length+'</em>张照片</a>' +
                                                '<img src=\''+data.list[i].postImgList[j].downUrl+'\' alt="">'+
                                                '</div>'
                                    }
                                }
                            }
                            html += '<div class="mgt05 zxf_user_response clearfix">' +
                                    '                                        <a href="javascript:;" class="zxf_like_btn fr mgl175">' +
                                    '                                            <i class="icon_like2"></i>' +
                                    '                                            <em>'+data.list[i].likeCount+'</em>' +
                                    '                                        </a>' +
                                    '                                        <a href="javascript:;" class="zxf_comment_btn fr">' +
                                    '                                            <i class="icon_comment2 mgr025"></i>' +
                                    '                                            <em>'+data.list[i].messageCount+'</em>' +
                                    '                                        </a>' +
                                    '                                    </div>' +
                                    '                                </div>' +
                                    '                            </div>' +
                                    '                        </div>' +
                                    '                    </div>' +
                                    '                </div>'
                        }
                        $('.zxf_stu_item_wp').append(html);
                    }else{
                        var html = '<div class="zxf_noCon_wp pdt8 t_c">' +
                                '        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_nocon1.png" alt="" class="img_nocon1">' +
                                '        <p class="mgt075">您还没有发布帖子哦!</p>' +
                                '      </div>';
                        $('.zxf_stu_item_wp').append(html);
                    }
                    if(data.list.length!=10){
                        $.detachInfiniteScroll($('.infinite-scroll'));
                        $('.infinite-scroll-preloader').remove();
                        return;
                    }
                }
            })
        }
        var pageCurrent=0;
        function addItems(number, lastIndex) {//参数：number:分页个数；lastIndex:表示当前最后的索引显示
            pageCurrent++;
            if(pageCurrent*itemsPerLoad >= maxItems){
                $.detachInfiniteScroll($('.infinite-scroll'));
                $('.infinite-scroll-preloader').remove();
                return;
            }
            $.ajax({
                type:"get",
                url:"getPostList",
                data:{
                    "pageNum":pageCurrent,
                    "pageSize":itemsPerLoad
                },
                async: true,
                dataType:'json',
                success:function(data){
                    pageCurrent = data.pageNum;
                    if(data.list.length>0){
                        var html = '';
                        for (var i = 0; i <data.list.length; i++) {
                            var divNum = pageCurrent*10+i;
                            html +=' <div class="zxf_stu_item pd075 mgt025 bgfff" id="'+divNum+'">' +
                                    '                    <div class="clearfix">' +
                                    '                        <div class="zxf_item_left fl">' +
                                    '                  <span class="date t_c">' +
                                    '                    <em class="day">'+data.list[i].day+'</em>' +
                                    '                    <em class="month">'+data.list[i].month+'</em>' +
                                    '                    <em class="month">'+data.list[i].dataTime+'</em>' +
                                    '                  </span>' +
                                    '                        </div>' +
                                    '                        <div class="zxf_stu_text">' +
                                    '                            <div class="zxf_mypostCon pdt05">' +
                                    '                                <div class="zxf_mypost_item">' +
                                    '                                    <a href="#" class="wql_icon wql_icon01 fr zindex20" onclick="deletePost(\''+data.list[i].id+'\')"></a>' +
                                    '                                    <div class="zxf_desBox" onclick="getPostDetail(\''+data.list[i].id+'\',\''+divNum+'\')">' +
                                    '                                            <h2 class="clearfix" style="margin-top: -10px;">' +
                                    '                                                <span class="subject">'+data.list[i].plateName+'</span>' +
                                    '                                            </h2>' +
                                    '                                            <div class="zxf_des_wp mult_line_ellipsis">' +
                                    '                                                    '+data.list[i].content+'' +
                                    '                                            </div>' +
                                    '                                    </div>'
                            if(data.list[i].postImgList.length>0){
                                for(var j = 0; j < 1; j++){
                                    if(data.list[i].postImgList[j].downUrl!=null && data.list[i].postImgList[j].downUrl!=''){
                                        html += '<div class="zxf_img_Box mgt05">'+
                                                '<a href="javascript:;" class="photo_btn">共<em class="num">'+data.list[i].postImgList.length+'</em>张照片</a>' +
                                                '<img src=\''+data.list[i].postImgList[j].downUrl+'\' alt="">'+
                                                '</div>'
                                    }
                                }
                            }
                            html += '<div class="mgt05 zxf_user_response bb clearfix">' +
                                    '                                        <a href="javascript:;" class="zxf_like_btn fr mgl175">' +
                                    '                                            <i class="icon_like2"></i>' +
                                    '                                            <em>'+data.list[i].likeCount+'</em>' +
                                    '                                        </a>' +
                                    '                                        <a href="javascript:;" class="zxf_comment_btn fr">' +
                                    '                                            <i class="icon_comment2 mgr025"></i>' +
                                    '                                            <em>'+data.list[i].messageCount+'</em>' +
                                    '                                        </a>' +
                                    '                                    </div>' +
                                    '                                </div>' +
                                    '                            </div>' +
                                    '                        </div>' +
                                    '                    </div>' +
                                    '                </div>'
                        }
                        $('.zxf_stu_item_wp').append(html);
                    }
                    if(data.list.length!=10){
                        $.detachInfiniteScroll($('.infinite-scroll'));
                        $('.infinite-scroll-preloader').remove();
                        return;
                    }
                }
            })
        }
        
        function deletePost(id) {
            var userType = "${userObj.userType!}";
            if(userType==0){
                var buttons1 = [
                    {
                        text: '收藏帖子',
                        onClick: function() {
                            $.ajax({
                                type:"get",
                                url:"addCollection",
                                data:{
                                    "postId":id,
                                    "type":false
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
                                        $.alert("已收藏！")
                                    }else{
                                        $.alert("添加失败！")
                                    }
                                }

                            })
                        }
                    }
                ];
            }
            if(userType==1){
                var buttons1 = [
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
                                                    "postId":id,
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
                                                            window.location.reload();
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
                        text: '收藏帖子',
                        onClick: function() {
                            $.ajax({
                                type:"get",
                                url:"addCollection",
                                data:{
                                    "postId":id,
                                    "type":false
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
                                        $.alert("已收藏！")
                                    }else{
                                        $.alert("添加失败！")
                                    }
                                }

                            })
                        }
                    }
                ];
            }
            var buttons2 = [
                {
                    text: '取消',
                }
            ];
            var groups = [buttons1, buttons2];
            $.actions(groups);
        }
        $(document).on('click', '.modal-overlay',function() {
            $(this).removeClass("modal-overlay-visible");
            $(".actions-modal").hide();
        });

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
                addItems(itemsPerLoad, lastIndex);
                lastIndex = $('.zxf_stu_item_wp .zxf_stu_item').length;
            }, 1000);
        });
        $(document).on('touchend', '.icon.icon-left.pull-left',function(e) {
            sessionStorage.clear();
            window.location.href="${base}/index.html";
        });
        appGoback = function(){
            sessionStorage.clear();
            window.location.href="${base}/index.html";
        }
        function getPostDetail(postId,divNum){
            sessionStorage.setItem("postListDivNum",divNum);
            sessionStorage.setItem("postListPageNum",pageCurrent);
            location.href="${base}/getPostDetail?postId="+postId+"&turn=3";
        }

        window.onload=function () {
            if(window.sessionStorage){
                var divNum =parseInt(sessionStorage.getItem("postListDivNum"));
                var pageNum=parseInt(sessionStorage.getItem("postListPageNum"));
                if(!isNaN(pageNum)){
                    getRollBack(pageNum);
                    var timeout=0;
                    var interval = setInterval(function () {
                        var divNum2 = $('.zxf_stu_item').length;
                        console.log("divNum2:"+divNum2);
                        timeout++;
                        if(divNum2>divNum){
                            $('.zxf_stu_item')[divNum].scrollIntoView();
                            clearInterval(interval);
                        }
                        if(timeout>20){
                            clearInterval(interval);
                        }
                    },500);
                }
            }
        }

        function getRollBack(pageNum) {
            pageCurrent++;
            $.ajax({
                type:"get",
                url:"getPostList",
                data:{
                    "pageNum":pageCurrent,
                    "pageSize":itemsPerLoad
                },
                async: true,
                dataType:'json',
                success:function(data){
                    pageCurrent = data.pageNum;
                    if(data.list.length>0){
                        var html = '';
                        for (var i = 0; i <data.list.length; i++) {
                            var divNum = pageCurrent*itemsPerLoad+i;
                            html +=' <div class="zxf_stu_item pd075 mgt025 bgfff" id="'+divNum+'">' +
                                    '                    <div class="clearfix">' +
                                    '                        <div class="zxf_item_left fl">' +
                                    '                  <span class="date t_c">' +
                                    '                    <em class="day">'+data.list[i].day+'</em>' +
                                    '                    <em class="month">'+data.list[i].month+'</em>' +
                                    '                    <em class="month">'+data.list[i].dataTime+'</em>' +
                                    '                  </span>' +
                                    '                        </div>' +
                                    '                        <div class="zxf_stu_text">' +
                                    '                            <div class="zxf_mypostCon pdt05">' +
                                    '                                <div class="zxf_mypost_item">' +
                                    '                                    <a href="#" class="wql_icon wql_icon01 fr zindex20" onclick="deletePost(\''+data.list[i].id+'\')"></a>' +
                                    '                                    <div class="zxf_desBox" onclick="getPostDetail(\''+data.list[i].id+'\',\''+divNum+'\')">' +
                                    '                                            <h2 class="clearfix" style="margin-top: -10px;">' +
                                    '                                                <span class="subject">'+data.list[i].plateName+'</span>' +
                                    '                                            </h2>' +
                                    '                                            <div class="zxf_des_wp mult_line_ellipsis">' +
                                    '                                                    '+data.list[i].content+'' +
                                    '                                            </div>' +
                                    '                                    </div>'
                            if(data.list[i].postImgList.length>0){
                                for(var j = 0; j < 1; j++){
                                    if(data.list[i].postImgList[j].downUrl!=null && data.list[i].postImgList[j].downUrl!=''){
                                        html += '<div class="zxf_img_Box mgt05">'+
                                                '<a href="javascript:;" class="photo_btn">共<em class="num">'+data.list[i].postImgList.length+'</em>张照片</a>' +
                                                '<img src=\''+data.list[i].postImgList[j].downUrl+'\' alt="">'+
                                                '</div>'
                                    }
                                }
                            }
                            html += '<div class="mgt05 zxf_user_response bb clearfix">' +
                                    '                                        <a href="javascript:;" class="zxf_like_btn fr mgl175">' +
                                    '                                            <i class="icon_like2"></i>' +
                                    '                                            <em>'+data.list[i].likeCount+'</em>' +
                                    '                                        </a>' +
                                    '                                        <a href="javascript:;" class="zxf_comment_btn fr">' +
                                    '                                            <i class="icon_comment2 mgr025"></i>' +
                                    '                                            <em>'+data.list[i].messageCount+'</em>' +
                                    '                                        </a>' +
                                    '                                    </div>' +
                                    '                                </div>' +
                                    '                            </div>' +
                                    '                        </div>' +
                                    '                    </div>' +
                                    '                </div>'
                        }
                        $('.zxf_stu_item_wp').append(html);
                    }
                    if(data.list.length!=10){
                        $.detachInfiniteScroll($('.infinite-scroll'));
                        $('.infinite-scroll-preloader').remove();
                        return;
                    }
                    if(pageCurrent<pageNum){
                        getRollBack(pageNum)
                    }
                }
            })
        }
    </script>
</div>
<!-- popup弹窗 -->
</body>
</html>