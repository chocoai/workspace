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
    <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/weiLesson.css">\
    <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/jquery.js"></script>
    <script>
        $.config = {
            autoInit: true,
            router:true
        }
    </script>
    <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/light7.js"></script>
    <script type="text/javascript" src="${base}/js/fastclick.js"></script>
</head>
<body>
<div class="page">
    <header class="bar bar-nav zxf_header bgfff">
        <a class="icon icon-left pull-left"></a>
        <h1 class="title">评论我的</h1>
    </header>
    <div class="zxf_coverWp">
        <div class="zxf_toastBox toastBox_ok">
            <i class="icon_ok"></i>
            <p>操作成功</p>
        </div>
    </div>
    <div class="content zxf_content infinite-scroll bgfff  mgt05" data-distance="100">
        <div class="zxf_con-block">
            <div class="">
                <div class="zxf_stu_item_wp criticism pdb05">
                </div>
            </div>
            <!-- 加载提示符 -->
            <div class="infinite-scroll-preloader">
              <div class="preloader">
              </div>
            </div>
        </div>
    </div>
    <script>
        //初始化FastClick实例
        $(function() {
            FastClick.attach(document.body);
        });
        // 点赞
        $(".zxf_stu_item_wp").on("click",".zxf_like_btn",function(){
            $(this).toggleClass("on");
        });
        // 下拉加载
        var loading = false;
        var maxItems = 16;//分页中总数量
        var itemsPerLoad = 10;//分页中每页的个数;默认10条
        var lastIndex = 2;//默认显示2条数据，latsIndex表示当前最后的索引显示
        getLikeListInit(1,itemsPerLoad);
        function getLikeListInit(pageCurrent,itemsPerLoad){
            $.ajax({
                type:"get",
                url:"getPostMessageList",
                data:{
                    "pageNum":pageCurrent,
                    "pageSize":itemsPerLoad
                },
                async: true,
                dataType:'json',
                success:function(data){
                    maxItems=data.pageTotal;
                    if(data.list.length>0){
                        var html = '';
                        for (var i = 0; i <data.list.length; i++) {
                            html += '                    <div class="zxf_stu_item pd075 bb">' +
                                    '                        <div class="clearfix">' +
                                    '                           <div class="zxf_imgBox fl">'
                            if(!data.list[i].isRead){
                                html +='<i class="icon_red_dot"></i>'
                            }
                            html += '                            <img src="'+data.list[i].logoUrl+'" alt="" class="fl zxf_stu_img">' +
                                    '                  </div>'+
                                    '                            <div class="zxf_stu_text zxf_desBox"  onclick="getPostDetail(\''+data.list[i].platePostId+'\',\''+data.list[i].id+'\','+data.list[i].isRead+','+i+')">' +
                                    '                                <h2 class="clearfix">' +
                                    '                                    <p class="fl">'+data.list[i].messageName+'</p>' +
                                    /*'                                    <a class="wql_icon wql_icon01 fr zindex20"></a>' +*/
                                    '                                </h2>' +
                                    '                                <p class="zxf_visit_info">' +
                                    '                                    <span>'+data.list[i].createTimeStr+'</span>' +
                                    '                                </p>' +
                                    '                                <div class="zxf_des_wp mgt05 ">' +
                                    '                                    <p class="mult_line_ellipsis">回复：<span>'+data.list[i].content+'</span>'
                                    if(data.list[i].audioUrl!=''){
                                        html +='【语音】'
                                    }
                                    if(data.list[i].imgUrl!=''){
                                        html +='【图片】'
                                    }
                            html += '</p>' +
                                    '                                    <div class="zxf_origin_post pd05 bgStyle2 mgt06">' +
                                    '                                        <p class="mult_line_ellipsis">' +
                                    '                                            原帖：'+data.list[i].postContent+'' +
                                    '                                        </p>' +
                                    '                                    </div>' +
                                    '                                </div>' +
                                    '                            </div>' +
                                    '                        </div>' +
                                    '                </div>'
                        }
                        $('.zxf_stu_item_wp').append(html);
                    }else{
                        var html='<div class="zxf_noCon_wp pdt8 t_c">' +
                                '        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_nocon2.png" alt="" class="img_nocon2">' +
                                '        <p class="mgt075">您还没有收到评论哦!</p>' +
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
        var pageCurrent=1;
        function addItems(number, lastIndex) {//参数：number:分页个数；lastIndex:表示当前最后的索引显示
            pageCurrent++;
            $.ajax({
                type:"get",
                url:"getPostMessageList",
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
                            var divNum = (pageCurrent-1)*itemsPerLoad+i;
                            html += '                    <div class="zxf_stu_item pd075 bb">' +
                                    '                        <div class="clearfix">' +
                                    '                           <div class="zxf_imgBox fl">'
                            if(!data.list[i].isRead){
                                html +='<i class="icon_red_dot"></i>'
                            }
                            html += '                            <img src="'+data.list[i].logoUrl+'" alt="" class="fl zxf_stu_img">' +
                                    '                  </div>'+
                                    '                            <div class="zxf_stu_text zxf_desBox" onclick="getPostDetail(\''+data.list[i].platePostId+'\',\''+data.list[i].id+'\','+data.list[i].isRead+','+divNum+')">' +
                                    '                                <h2 class="clearfix">' +
                                    '                                    <p class="fl">'+data.list[i].messageName+'</p>' +
                                    /*'                                    <a class="wql_icon wql_icon01 fr zindex20"></a>' +*/
                                    '                                </h2>' +
                                    '                                <p class="zxf_visit_info">' +
                                    '                                    <span>'+data.list[i].createTimeStr+'</span>' +
                                    '                                </p>' +
                                    '                                <div class="zxf_des_wp mgt05">' +
                                    '                                    <p class="mult_line_ellipsis">回复：<span>'+data.list[i].content+'</span>'
                            if(data.list[i].audioUrl!=''){
                                html +='【语音】'
                            }
                            if(data.list[i].imgUrl!=''){
                                html +='【图片】'
                            }
                            html += '                                    </p><div class="zxf_origin_post pd05 bgStyle2 mgt06">' +
                                    '                                        <p class="mult_line_ellipsis">' +
                                    '                                            原帖：'+data.list[i].postContent+'' +
                                    '                                        </p>' +
                                    '                                    </div>' +
                                    '                                </div>' +
                                    '                            </div>' +
                                    '                        </div>' +
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
           window.location.href="${base}/updateMessage";
        });
        appGoback = function(){
            sessionStorage.clear();
            window.location.href="${base}/updateMessage";
        }

        function getPostDetail(postId,id,isRead,divNum){
            sessionStorage.setItem("postMessageDivNum",divNum);
            sessionStorage.setItem("postMessagePageNum",pageCurrent);
            console.log("postMessagePageNum",divNum);
            console.log("postMessagePageNum",pageCurrent);
            if(!isRead){//未读
                $.ajax({
                    url:"saveMessageIsRead",
                    type:"get",
                    dataType:"json",
                    data:{
                        messageId:id,
                    },
                    async:true,
                    success:function(msg){
                        if(msg==1){
                            location.href="getPostDetail?postId="+postId+"&turn=4&messageId="+id;
                        }
                    }
                })
            }else{
                location.href="getPostDetail?postId="+postId+"&turn=4&messageId="+id;
            }
        }

        window.onload=function () {
            if(window.sessionStorage){
                var divNum =parseInt(sessionStorage.getItem("postMessageDivNum"));
                var pageNum=parseInt(sessionStorage.getItem("postMessagePageNum"));
                if(!isNaN(pageNum)){
                    getRollBack(pageNum);
                    console.log("postMessageDivNum",divNum);
                    console.log("postMessagePageNum",pageNum);
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
                type: "get",
                url: "getPostMessageList",
                data: {
                    "pageNum": pageCurrent,
                    "pageSize": itemsPerLoad
                },
                async: true,
                dataType: 'json',
                success: function (data) {
                    pageCurrent = data.pageNum;
                    if (data.list.length > 0) {
                        var html = '';
                        for (var i = 0; i < data.list.length; i++) {
                            var divNum = (pageCurrent-1)*itemsPerLoad+i;
                            html += '                    <div class="zxf_stu_item pd075 bb">' +
                                    '                        <div class="clearfix">' +
                                    '                           <div class="zxf_imgBox fl">'
                            if (!data.list[i].isRead) {
                                html += '<i class="icon_red_dot"></i>'
                            }
                            html += '                            <img src="' + data.list[i].logoUrl + '" alt="" class="fl zxf_stu_img">' +
                                    '                  </div>' +
                                    '                            <div class="zxf_stu_text zxf_desBox" onclick="getPostDetail(\'' + data.list[i].platePostId + '\',\'' + data.list[i].id + '\',' + data.list[i].isRead + ',' + divNum + ')">' +
                                    '                                <h2 class="clearfix">' +
                                    '                                    <p class="fl">' + data.list[i].messageName + '</p>' +
                                    /*'                                    <a class="wql_icon wql_icon01 fr zindex20"></a>' +*/
                                    '                                </h2>' +
                                    '                                <p class="zxf_visit_info">' +
                                    '                                    <span>' + data.list[i].createTimeStr + '</span>' +
                                    '                                </p>' +
                                    '                                <div class="zxf_des_wp mgt05">' +
                                    '                                    <p class="mult_line_ellipsis">回复：<span>' + data.list[i].content + '</span>'
                            if (data.list[i].audioUrl != '') {
                                html += '【语音】'
                            }
                            if (data.list[i].imgUrl != '') {
                                html += '【图片】'
                            }
                            html += '                                    </p><div class="zxf_origin_post pd05 bgStyle2 mgt06">' +
                                    '                                        <p class="mult_line_ellipsis">' +
                                    '                                            原帖：' + data.list[i].postContent + '' +
                                    '                                        </p>' +
                                    '                                    </div>' +
                                    '                                </div>' +
                                    '                            </div>' +
                                    '                        </div>' +
                                    '                </div>'
                        }
                        $('.zxf_stu_item_wp').append(html);
                    }
                    if (data.list.length != 10) {
                        $.detachInfiniteScroll($('.infinite-scroll'));
                        $('.infinite-scroll-preloader').remove();
                        return;
                    }
                    if (pageCurrent < pageNum) {
                        getRollBack(pageNum)
                    }
                }
            });
        }
    </script>
</div>
<!-- popup弹窗 -->

<!-- 侧栏 -->
<div class="panel-overlay"></div>
<!-- Left Panel with Reveal effect -->
<!-- 学生 -->
</body>
</html>