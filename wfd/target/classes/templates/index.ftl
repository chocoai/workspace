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
    <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/swiper.min.css">
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
    <script type='text/javascript' src='http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/swiper.min.js' charset='utf-8'></script>
    <script type="text/javascript" src="${base}/js/fastclick.js"></script>
</head>
<body>
<div class="page">
    <header class="bar bar-nav zxf_header bgfff">
        <a href="javascript:;" class="icon_people zxf_user_btn open-panel" data-panel="#panel-right-student">
			<#if message.total !=0>
                <span class="bg_red_num"><i>${message.total!}</i></span>
			</#if>
        </a>
        <a class="icon icon-left pull-left zxf_goback zxf_desBox" onclick="getApp()"></a>
        <h1 class="title">
            微辅导·<em id="title">全部</em>
            <a class="icon icon-down icon_sele mgl05 mgt_4"></a>
        </h1>
    </header>
    <div class="zxf_coverWp">
        <div class="zxf_toastBox toastBox_ok">
            <i class="icon_ok"></i>
            <p>正在加载</p>
        </div>
    </div>
    <#if (tPlates?size >0)>
    <a href="javascript:;" class="zxf_editBtn_fixed" onclick="sendPost()"></a>
    </#if>
    <div class="content zxf_content infinite-scroll pull-to-refresh-content" data-ptr-distance="55" data-distance="100">
        <!-- 上拉加载符 -->
        <#--<div class="pull-to-refresh-layer dis_none">
            <div class="preloader"></div>
            <div class="pull-to-refresh-arrow"></div>
        </div>-->
        <div id="contentTime"></div>
    </div>
    <!-- preloader -->
    <div class="infinite-scroll-preloader">
        <div class="preloader"></div>
    </div>
    <!-- 弹窗 -->
    <div class="zxf_popup_helpStyle dis_none" style="position: absolute;width: 100%">
        <div class="zxf_helpStyle_wp bgfff pdb05">
            <ul>
                <li class="pdlr075 bb on">
                    <i class="icon_gou"></i>
                    <img class="icon_li mgr075" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_all.png">
                    <a href="javascript:;" class="txt_ellipsis w60 con">全部</a>
                    <input type="hidden" id="tPlateId2" value="${plateId!}">
                </li>
                <#list tPlates as tPlate>
                <li class="pdlr075 bb">
                    <i class="icon_gou"></i>
                    <img class="icon_li mgr075" src="${tPlate.icon}">
                    <input type="hidden" id="tPlateId" value="${tPlate.id}">
                    <a href="javascript:;" class="txt_ellipsis w60 con zxf_desBox">${tPlate.name}</a>
                </li>
				</#list>
            </ul>
        </div>
    </div>
    <script>
        //初始化FastClick实例
        $(function() {
            FastClick.attach(document.body);
        });
        var turnTypePost=1;//返回跳转标识
        var typeNum=0;//排序字段标识
        var tabType="${tabType!}";//实时、热门刷新用
        function getIndexPost2(type) {
            var orderSort = "回复时间排序";
            typeNum=0;
            if(type==2){
                typeNum=1
                orderSort ="帖子热度排序";
            }
            pageCurrent=0;
            $.attachInfiniteScroll($('.infinite-scroll'));
            if(!$("div").hasClass("infinite-scroll-preloader")){
                var preloader ='<div class="infinite-scroll-preloader">' +
                        '        <div class="preloader"></div>' +
                        '    </div>'
                $(".page-inited").append(preloader);
            }
            $.ajax({
                type:"get",
                url:"/wfd/getIndexPost",
                data:{
                    "pageNum":pageCurrent,
                    "pageSize":itemsPerLoad,
                    "plateId":trunPlateId,
                    "type":typeNum
                },
                dataType : "json",
                async: true,
                success:function(data){
                    $(".zxf_content").empty();
                    var html='<div class="zxf_subject_intro bgfff mgtb025" >' +
                            '          <ul id="topPost">' +
                            '          </ul>' +
                            '      </div>' +
                            '      <div class="zxf_time_sort pdlr075 bgfff ">' +
                            '          <div class="clearfix bb pdtb075">' +
                            '              <span class="fl">'+orderSort+'</span>' +
                            '              <span class="fr open-popup open-time sortBtn" data-popup=".popup-time">排序<i class="icon icon-down mgl05"></i></span>' +
                            '          </div>' +
                            '      </div>' +
                            '      <div class="zxf_con-block">' +
                            '          <div class="">' +
                            '              <div class="tab active">' +
                            '                  <div class="">' +
                            '                      <div class="zxf_stu_item_wp">' +
                            '                      </div>' +
                            '                  </div>' +
                            '              </div>' +
                            '          </div>' +
                            '      </div>';
                    if(!$(".zxf_content").find(".zxf_stu_item_wp").hasClass("zxf_stu_item_wp")){
                        $(".zxf_content").append(html);
                    }
                    if(data.posts!=null && data.posts.length>0){
                        var html1 ='';
                        for(var i =0;i<data.posts.length;i++){
                            if(data.posts[i].isTop){
                                html1 +='            <li class="clearfix pdlr075 bb zxf_desBox" onclick="getPostDetailTurn(\''+data.posts[i].id+'\',\''+data.posts[i].plateId+'\')">' +
                                        '                <a href="javascript:;" class="go_top zxf_bor_blueBtn fl"><em>置顶</em></a>' +
                                        '                <p class="des">'+data.posts[i].content+'</p>' +
                                        '            </li>'
                            }
                        }
                        $("#topPost").append(html1);
                    }
                    if(data.list!=null && data.list.length>0){
                        var html2 ='';
                        for(var i=0;i<data.list.length;i++){
                            if(!data.list[i].isTop){
                                html2 +='<div class="zxf_stu_item pd075 mgb05 bgfff">' +
                                        '                                  <div class="zxf_desBox" onclick="getPostDetailTurn(\''+data.list[i].id+'\',\''+data.list[i].plateId+'\',\''+i+'\')">' +
                                        '                                  <div class="clearfix">' +
                                        '                                      <img src=\''+data.list[i].logoUrl+'\' alt="" class="fl zxf_stu_img">' +
                                        '                                      <div class="zxf_stu_text">' +
                                        '                                          <h2 class="clearfix">' +
                                        '                                              <p class="fl">'+data.list[i].userName+'</p>' +
                                        '                                              <span class="fr subject">'+data.list[i].plateName+'</span>' +
                                        '                                          </h2>' +
                                        '                                          <p class="zxf_visit_info">' +
                                        '                                              <span>'+data.list[i].relativeCreateTime+'</span>' +
                                        '                                              <em class="mglr05">|</em>' +
                                        '                                              <span>'+data.list[i].viewCount+'浏览</span>' +
                                        '                                          </p>' +
                                        '                                      </div>' +
                                        '                                  </div>' +
                                        '                                  <div class="zxf_des_wp mgt05 ">' +
                                        '                                      <div class="mult_line_ellipsis">'+data.list[i].content+'</div>'+
                                        '                                  </div>' +
                                        '                                  </div>'
                                if(data.list[i].postImgList!=null && data.list[i].postImgList.length>0){
                                    html2 += '                              <div class="zxf_img_Box mgt05">' +
                                            '                                  <a href="javascript:;" class="photo_btn">共<em class="num">'+data.list[i].postImgList.length+'</em>张照片</a>'
                                    for(var j=0;j<data.list[i].postImgList.length;j++){
                                        if(data.list[i].postImgList[j].downUrl!=null && data.list[i].postImgList[j].downUrl!=''){
                                            html2 +='<img src=\''+data.list[i].postImgList[j].downUrl+'\' alt="" class="photo">'
                                        }
                                    }
                                    html2 +=' </div>';
                                }
                                html2 +='                                 <div class="mgt05 zxf_user_response bt clearfix">' +
                                        '                                      <a href="javascript:;" class="zxf_comment_btn fl zxf_desBox" onclick="sendMessageTurn(\''+data.list[i].id+'\',\''+data.list[i].plateId+'\',\''+i+'\')">' +
                                        '                                          <i class="icon_comment mgr025"></i>' +
                                        '                                          <em>'+data.list[i].messageCount+'</em>' +
                                        '                                      </a>'
                                if(data.list[i].imgCount!=null && data.list[i].imgCount!=''){
                                    html2 +='<a href="javascript:;" class="zxf_like_btn fr on" onclick="getLikePost(this,\''+data.list[i].id+'\')">'
                                }else{
                                    html2 +='<a href="javascript:;" class="zxf_like_btn fr" onclick="getLikePost(this,\''+data.list[i].id+'\')">'
                                }
                                html2 += '                                          <i class="icon_like"></i>' +
                                        '                                          <em>'+data.list[i].likeCount+'</em>' +
                                        '                                      </a>' +
                                        '                                  </div>' +
                                        '                              </div>';
                            }
                        }
                        $(".zxf_stu_item_wp").append(html2);
                    }else{
                        $(".zxf_noCon_wp").remove()
                        var html='<div class="zxf_noCon_wp t_c pdt8">' +
                                '        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_nocon6.png" alt="" class="img_nocon6">' +
                                '        <p class="mgt075">还没有人发布帖子哦!</p>' +
                                '    </div>';
                        $(".zxf_content").append(html);
                    }
                }
            });
        }

        var loading = false;
        var maxItems = 100;
        var pageCurrent=0;
        var itemsPerLoad = 10;

        function addItems(number, lastIndex) {
            pageCurrent++;
            if(trunPlateId ==null || trunPlateId ==''){
                $.ajax({
                    type:"get",
                    url:"/wfd/getPostRealTime",
                    data:{
                        "pageNum":pageCurrent,
                        "pageSize":itemsPerLoad,
                        "type":typeNum
                    },
                    dataType : "json",
                    async: true,
                    success:function(data){
                        console.log("进来+"+data.list.length)
                        if(data.list.length==0){
                            pageCurrent--;
                            $.detachInfiniteScroll($('.infinite-scroll'));
                            $('.infinite-scroll-preloader').remove();
                            return;
                        }
                        pageCurrent = data.pageNum;
                        typeNum = data.type;
                        if(data.list!=null && data.list.length>0){
                            var html2 ='';
                            for(var i=0;i<data.list.length;i++){
                                var divNum = pageCurrent*itemsPerLoad+i;
                                html2 +='                            <div class="zxf_stu_item pdlr075 pdt075 mgb025 bgfff">' +
                                        '                                <div class="zxf_desBox" onclick="getPostDetail(\''+data.list[i].id+'\',\''+trunPlateId+'\',\''+divNum+'\')"><div class="clearfix">' +
                                        '                                    <img src=\''+data.list[i].logoUrl+'\' alt="" class="fl zxf_stu_img">' +
                                        '                                    <div class="zxf_stu_text">' +
                                        '                                        <h2 class="clearfix">' +
                                        '                                            <p class="fl">'+data.list[i].userName+'</p>' +
                                        '                                            <span class="fr subject">'+data.list[i].plateName+'</span>' +
                                        '                                        </h2>' +
                                        '                                        <p class="zxf_visit_info">' +
                                        '                                            <span>'+data.list[i].relativeCreateTime+'</span>' +
                                        '                                            <em class="mglr05">|</em>' +
                                        '                                            <span>'+data.list[i].viewCount+'浏览</span>' +
                                        '                                        </p>' +
                                        '                                    </div>' +
                                        '                                </div>' +
                                        '                                <div class="zxf_des_wp mgt05 ">' +
                                        '                                    <div class="mult_line_ellipsis">' +
                                        '                                        '+data.list[i].content+'' +
                                        '                                    </div>' +
                                        '                                </div></div>'
                                if(data.list[i].postImgList!=null && data.list[i].postImgList.length>0){
                                    html2 += '                                <div class="zxf_img_Box mgt05">' +
                                            '                                    <a href="javascript:;" class="photo_btn">共<em class="num">'+data.list[i].postImgList.length+'</em>张照片</a>'
                                    for(var j =0;j<data.list[i].postImgList.length;j++){
                                        html2 += '<img src=\''+data.list[i].postImgList[j].downUrl+'\' alt="" class="photo">'
                                    }
                                    html2 +='                                </div>'
                                }
                                html2 +='                                <div class="mgt05 zxf_user_response bt clearfix">' +
                                        '                                      <a href="javascript:;" class="zxf_comment_btn fl zxf_desBox" onclick="sendMessage(\''+data.list[i].id+'\',\'\',\''+divNum+'\')">' +
                                        '                                        <i class="icon_comment mgr025"></i>' +
                                        '                                        <em>'+data.list[i].messageCount+'</em>' +
                                        '                                    </a>' +
                                        '                                    <a href="javascript:;" class="zxf_like_btn fr">' +
                                        '                                        <i class="icon_like"></i>' +
                                        '                                        <em>'+data.list[i].likeCount+'</em>' +
                                        '                                    </a>' +
                                        '                                </div>' +
                                        '                            </div>'
                            }
                            $(".zxf_stu_item_wp").append(html2);
                        }
                        console.log("运行到底部")
                        if(data.list.length!=10){
                            $.detachInfiniteScroll($('.infinite-scroll'));
                            $('.infinite-scroll-preloader').remove();
                            return;
                        }
                    },
                    error: function(xhr, type){
                        $.alert('Ajax error!');
                    }
                });
            }else {
                $.ajax({
                    type:"get",
                    url:"/wfd/getIndexPost",
                    data:{
                        "pageNum":pageCurrent,
                        "pageSize":itemsPerLoad,
                        "plateId":trunPlateId,
                        "type":typeNum
                    },
                    dataType : "json",
                    async: true,
                    success:function(data){
                        if(data.list.length==0){
                            pageCurrent--;
                            $.detachInfiniteScroll($('.infinite-scroll'));
                            $('.infinite-scroll-preloader').remove();
                            return;
                        }
                        pageCurrent = data.pageNum;
                        if(data.list!=null && data.list.length>0){
                            var html2 ='';
                            for(var i=0;i<data.list.length;i++){
                                if(!data.list[i].isTop){
                                    var divNum = pageCurrent*itemsPerLoad+i;
                                    html2 +='<div class="zxf_stu_item pd075 mgb05 bgfff">' +
                                            '                                  <div class="zxf_desBox" onclick="getPostDetailTurn(\''+data.list[i].id+'\',\''+data.list[i].plateId+'\',\''+divNum+'\')">' +
                                            '                                  <div class="clearfix">' +
                                            '                                      <img src=\''+data.list[i].logoUrl+'\' alt="" class="fl zxf_stu_img">' +
                                            '                                      <div class="zxf_stu_text">' +
                                            '                                          <h2 class="clearfix">' +
                                            '                                              <p class="fl">'+data.list[i].userName+'</p>' +
                                            '                                              <span class="fr subject">'+data.list[i].plateName+'</span>' +
                                            '                                          </h2>' +
                                            '                                          <p class="zxf_visit_info">' +
                                            '                                              <span>'+data.list[i].relativeCreateTime+'</span>' +
                                            '                                              <em class="mglr05">|</em>' +
                                            '                                              <span>'+data.list[i].viewCount+'浏览</span>' +
                                            '                                          </p>' +
                                            '                                      </div>' +
                                            '                                  </div>' +
                                            '                                  <div class="zxf_des_wp mgt05 ">' +
                                            '                                      <div class="mult_line_ellipsis">'+data.list[i].content+'</div>'+
                                            '                                  </div>' +
                                            '                                  </div>'
                                    if(data.list[i].postImgList!=null && data.list[i].postImgList.length>0){
                                        html2 += '                              <div class="zxf_img_Box mgt05">' +
                                                '                                  <a href="javascript:;" class="photo_btn">共<em class="num">'+data.list[i].postImgList.length+'</em>张照片</a>'
                                        for(var j=0;j<data.list[i].postImgList.length;j++){
                                            if(data.list[i].postImgList[j].downUrl!=null && data.list[i].postImgList[j].downUrl!=''){
                                                html2 +='<img src=\''+data.list[i].postImgList[j].downUrl+'\' alt="" class="photo">'
                                            }
                                        }
                                        html2 +=' </div>';
                                    }
                                    html2 +='                                 <div class="mgt05 zxf_user_response bt clearfix">' +
                                            '                                      <a href="javascript:;" class="zxf_comment_btn fl zxf_desBox" onclick="sendMessageTurn(\''+data.list[i].id+'\',\''+data.list[i].plateId+'\',\''+divNum+'\')">' +
                                            '                                          <i class="icon_comment mgr025"></i>' +
                                            '                                          <em>'+data.list[i].messageCount+'</em>' +
                                            '                                      </a>'
                                    if(data.list[i].imgCount!=null && data.list[i].imgCount!=''){
                                        html2 +='<a href="javascript:;" class="zxf_like_btn fr on" onclick="getLikePost(this,\''+data.list[i].id+'\')">'
                                    }else{
                                        html2 +='<a href="javascript:;" class="zxf_like_btn fr" onclick="getLikePost(this,\''+data.list[i].id+'\')">'
                                    }
                                    html2 += '                                          <i class="icon_like"></i>' +
                                            '                                          <em>'+data.list[i].likeCount+'</em>' +
                                            '                                      </a>' +
                                            '                                  </div>' +
                                            '                              </div>';
                                }
                            }
                            $(".zxf_stu_item_wp").append(html2);
                        }
                        if(data.list.length!=10){
                            $.detachInfiniteScroll($('.infinite-scroll'));
                            $('.infinite-scroll-preloader').remove();
                            return;
                        }
                    },
                    error: function(xhr, type){
                        $.alert('Ajax error!');
                        // 即使加载出错，也得重置
                    }
                });
            }
        }
        var lastIndex = 0;
        $(document).on('infinite', '.infinite-scroll',function() {
            var html = '<div class="infinite-scroll-preloader">' +
                    '              <div class="preloader">' +
                    '              </div>' +
                    '            </div>'
            if(!$(".zxf_content").find(".infinite-scroll-preloader").hasClass("infinite-scroll-preloader")){
                $(".zxf_stu_item_wp").after(html);
            }

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
                lastIndex = $('.zxf_stu_item_wp').length;
                addItems(itemsPerLoad, lastIndex);
            }, 1000);
        });


        function getPostRealTime(pageNum,pageSize,type){//实时获取
            $(".zxf_content").empty();
            $(".zxf_content").css("top","4.2rem");
            tabType = type;
            turnTypePost=1;
            pageCurrent =0;
            $.attachInfiniteScroll($('.infinite-scroll'));
            if(!$("div").hasClass("infinite-scroll-preloader")){
                var preloader ='<div class="infinite-scroll-preloader">' +
                        '        <div class="preloader"></div>' +
                        '    </div>'
                $(".page-inited").append(preloader);
            }
            trunPlateId = "";
            var html='<div class="zxf_noCon_wp t_c pdt8">' +
                    '        <p class="mgt075">正在加载……</p>' +
                    '    </div>';
            $(".zxf_content").append(html);
            $.ajax({
                type:"get",
                url:"/wfd/getPostRealTime",
                data:{
                    "pageNum":pageNum,
                    "pageSize":itemsPerLoad,
                    "type":type
                },
                dataType : "json",
                async: true,
                success:function(data){
                    $(".zxf_buttons-tab").remove();
                    typeNum = data.type;
                    var html='';
                    var header="";
                    if(data.list!=null && data.list.length>0){
                        header +='<div class="buttons-tab bgfff zxf_buttons-tab">'
                        if(data.type==0){
                            header += '            <a href="#tab1" onclick="getPostRealTime(0,10,0)" class="tab-link active button zxf_tabTit">实时</a>' +
                                    '            <a href="#tab2" onclick="getPostRealTime(0,10,1)" class="tab-link button zxf_tabTit">热门</a>'
                        }else{
                            header += '            <a href="#tab1" onclick="getPostRealTime(0,10,0)" class="tab-link  button zxf_tabTit">实时</a>' +
                                    '            <a href="#tab2" onclick="getPostRealTime(0,10,1)" class="tab-link active button zxf_tabTit">热门</a>'
                        }
                        header +='        </div>'
                        html +=  '        <div class="zxf_con-block">' +
                                '            <div class="tabs">' +
                                '                <div id="tab1" class="tab active">' +
                                '                    <div class="">' +
                                '                        <div class="zxf_stu_item_wp">'+
                                '                        </div>' +
                                '                    </div>' +
                                '                </div>' +
                                '            </div>' +
                                '        </div>';
                        if(!$(".zxf_content").find(".zxf_stu_item_wp").hasClass("zxf_stu_item_wp")){
                            $(".zxf_content").append(html);
                        }
                        $("header").after(header)
                        var html2 ='';
                        for(var i=0;i<data.list.length;i++){
                            html2 +='                            <div class="zxf_stu_item pdlr075 pdt075 mgb025 bgfff">' +
                                    '                                <div class="zxf_desBox" onclick="getPostDetail(\''+data.list[i].id+'\',\''+trunPlateId+'\',\''+i+'\')"><div class="clearfix">' +
                                    '                                    <img src=\''+data.list[i].logoUrl+'\' alt="" class="fl zxf_stu_img">' +
                                    '                                    <div class="zxf_stu_text">' +
                                    '                                        <h2 class="clearfix">' +
                                    '                                            <p class="fl">'+data.list[i].userName+'</p>' +
                                    '                                            <span class="fr subject">'+data.list[i].plateName+'</span>' +
                                    '                                        </h2>' +
                                    '                                        <p class="zxf_visit_info">' +
                                    '                                            <span>'+data.list[i].relativeCreateTime+'</span>' +
                                    '                                            <em class="mglr05">|</em>' +
                                    '                                            <span>'+data.list[i].viewCount+'浏览</span>' +
                                    '                                        </p>' +
                                    '                                    </div>' +
                                    '                                </div>' +
                                    '                                <div class="zxf_des_wp mgt05">' +
                                    '                                    <div class="mult_line_ellipsis">' +
                                    '                                        '+data.list[i].content+'' +
                                    '                                    </div>' +
                                    '                                </div></div>'
                            if(data.list[i].postImgList!=null && data.list[i].postImgList.length>0){
                                html2 += '                                <div class="zxf_img_Box mgt05">' +
                                        '                                    <a href="javascript:;" class="photo_btn">共<em class="num">'+data.list[i].postImgList.length+'</em>张照片</a>'
                                for(var j =0;j<data.list[i].postImgList.length;j++){
                                    html2 += '<img src=\''+data.list[i].postImgList[j].downUrl+'\' alt="" class="photo">'
                                }
                                html2 +='                                </div>'
                            }
                            html2 +='                                <div class="mgt05 zxf_user_response bt clearfix">' +
                                    '                                      <a href="javascript:;" class="zxf_comment_btn fl zxf_desBox" onclick="sendMessage(\''+data.list[i].id+'\',\'\',\''+i+'\')">' +
                                    '                                        <i class="icon_comment mgr025"></i>' +
                                    '                                        <em>'+data.list[i].messageCount+'</em>' +
                                    '                                    </a>';
                            if(data.list[i].imgCount!=null && data.list[i].imgCount!=''){
                                html2 +='<a href="javascript:;" class="zxf_like_btn fr on" onclick="getLikePost(this,\''+data.list[i].id+'\')">'
                            }else{
                                html2 +='<a href="javascript:;" class="zxf_like_btn fr" onclick="getLikePost(this,\''+data.list[i].id+'\')">'
                            }
                            html2 +='                                        <i class="icon_like"></i>' +
                                    '                                        <em>'+data.list[i].likeCount+'</em>' +
                                    '                                    </a>' +
                                    '                                </div>' +
                                    '                            </div>'

                        }
                        $(".zxf_stu_item_wp").append(html2);
                        $(".zxf_noCon_wp").remove()
                    }else{
                        $(".zxf_noCon_wp").remove()
                        var html='<div class="zxf_noCon_wp t_c pdt8">' +
                                '        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_nocon6.png" alt="" class="img_nocon6">';
                                <#if (tPlates?size >0)>
                                    html +='        <p class="mgt075">还没有人发布帖子哦!</p>'
                                <#else>
                                    html +='        <p class="mgt075">你还没有加入学习版块哦!</p>'
                                </#if>
                        html +='</div>';
                        $(".zxf_content").append(html);
                    }
                    $(".zxf_coverWp").hide();
                    if(data.list.length!=10){
                        $.detachInfiniteScroll($('.infinite-scroll'));
                        $('.infinite-scroll-preloader').remove();
                        return;
                    }
                },
                error : function(errorMsg) {
                }
            })
        }

        function getIndexPost(pageNum,pageSize,plateId,type){
            typeNum= type;
            $(".zxf_content").empty();
            $(".zxf_content").css("top","2.1rem");
            $(".zxf_buttons-tab").remove();
            turnTypePost=2
            pageCurrent =0;
            $.attachInfiniteScroll($('.infinite-scroll'));
            if(!$("div").hasClass("infinite-scroll-preloader")){
                var preloader ='<div class="infinite-scroll-preloader">' +
                        '        <div class="preloader"></div>' +
                        '    </div>'
                $(".page-inited").append(preloader);
            }
            trunPlateId =plateId;
            var html='<div class="zxf_noCon_wp t_c pdt8">' +
                    '        <p class="mgt075">正在加载……</p>' +
                    '    </div>';
            $(".zxf_content").append(html);
            $.ajax({
                type:"get",
                url:"/wfd/getIndexPost",
                data:{
                    "pageNum":pageNum,
                    "pageSize":itemsPerLoad,
                    "plateId":plateId,
                    "type":typeNum
                },
                dataType : "json",
                async: true,
                success:function(data){
                    typeNum = data.type;
                    var html='<div class="zxf_subject_intro bgfff mgtb025">' +
                            '          <ul id="topPost">' +
                            '          </ul>' +
                            '      </div>' +
                            '      <div class="zxf_time_sort pdlr075 bgfff ">' +
                            '          <div class="clearfix bb pdtb075">' +
                            '              <span class="fl">回复时间排序</span>' +
                            '              <span class="fr open-popup open-time sortBtn" data-popup=".popup-time">排序<i class="icon icon-down mgl05"></i></span>' +
                            '          </div>' +
                            '      </div>' +
                            '      <div class="zxf_con-block">' +
                            '          <div class="">' +
                            '              <div class="tab active">' +
                            '                  <div class="">' +
                            '                      <div class="zxf_stu_item_wp">' +
                            '                      </div>' +
                            '                  </div>' +
                            '              </div>' +
                            '          </div>' +
                            '      </div>';
                    if(!$(".zxf_content").find(".zxf_stu_item_wp").hasClass("zxf_stu_item_wp")){
                        $(".zxf_content").append(html);
                    }
                    if(data.posts!=null && data.posts.length>0){
                        var html1 ='';
                        for(var i =0;i<data.posts.length;i++){
                            if(data.posts[i].isTop){
                                html1 +='            <li class="clearfix pdlr075 bb zxf_desBox" onclick="getPostDetailTurn(\''+data.posts[i].id+'\',\''+data.posts[i].plateId+'\')">' +
                                        '                <a href="javascript:;" class="go_top zxf_bor_blueBtn fl"><em>置顶</em></a>' +
                                        '                <p class="des">'+data.posts[i].content+'</p>' +
                                        '            </li>'
                            }
                        }
                        $("#topPost").append(html1);
                    }
                    if(data.list!=null && data.list.length>0){
                        var html2 ='';
                        for(var i=0;i<data.list.length;i++){
                            if(!data.list[i].isTop){
                                html2 +='<div class="zxf_stu_item pd075 mgb05 bgfff">' +
                                        '                                  <div class="zxf_desBox" onclick="getPostDetailTurn(\''+data.list[i].id+'\',\''+data.list[i].plateId+'\',\''+i+'\')">' +
                                        '                                  <div class="clearfix">' +
                                        '                                      <img src=\''+data.list[i].logoUrl+'\' alt="" class="fl zxf_stu_img">' +
                                        '                                      <div class="zxf_stu_text">' +
                                        '                                          <h2 class="clearfix">' +
                                        '                                              <p class="fl">'+data.list[i].userName+'</p>' +
                                        '                                              <span class="fr subject">'+data.list[i].plateName+'</span>' +
                                        '                                          </h2>' +
                                        '                                          <p class="zxf_visit_info">' +
                                        '                                              <span>'+data.list[i].relativeCreateTime+'</span>' +
                                        '                                              <em class="mglr05">|</em>' +
                                        '                                              <span>'+data.list[i].viewCount+'浏览</span>' +
                                        '                                          </p>' +
                                        '                                      </div>' +
                                        '                                  </div>' +
                                        '                                  <div class="zxf_des_wp mgt05 ">' +
                                        '                                      <div class="mult_line_ellipsis">'+data.list[i].content+'</div>'+
                                        '                                  </div>' +
                                        '                                  </div>'
                                if(data.list[i].postImgList!=null && data.list[i].postImgList.length>0){
                                    html2 += '                              <div class="zxf_img_Box mgt05">' +
                                            '                                  <a href="javascript:;" class="photo_btn">共<em class="num">'+data.list[i].postImgList.length+'</em>张照片</a>'
                                    for(var j=0;j<data.list[i].postImgList.length;j++){
                                        if(data.list[i].postImgList[j].downUrl!=null && data.list[i].postImgList[j].downUrl!=''){
                                            html2 +='<img src=\''+data.list[i].postImgList[j].downUrl+'\' alt="" class="photo">'
                                        }
                                    }
                                    html2 +=' </div>';
                                }
                                html2 +='                                 <div class="mgt05 zxf_user_response bt clearfix">' +
                                        '                                      <a href="javascript:;" class="zxf_comment_btn fl zxf_desBox" onclick="sendMessageTurn(\''+data.list[i].id+'\',\''+data.list[i].plateId+'\',\''+i+'\')">' +
                                        '                                          <i class="icon_comment mgr025"></i>' +
                                        '                                          <em>'+data.list[i].messageCount+'</em>' +
                                        '                                      </a>'
                                if(data.list[i].imgCount!=null && data.list[i].imgCount!=''){
                                    html2 +='<a href="javascript:;" class="zxf_like_btn fr on" onclick="getLikePost(this,\''+data.list[i].id+'\')">'
                                }else{
                                    html2 +='<a href="javascript:;" class="zxf_like_btn fr" onclick="getLikePost(this,\''+data.list[i].id+'\')">'
                                }
                                html2 += '                                          <i class="icon_like"></i>' +
                                        '                                          <em>'+data.list[i].likeCount+'</em>' +
                                        '                                      </a>' +
                                        '                                  </div>' +
                                        '                              </div>';
                            }
                        }
                        $(".zxf_stu_item_wp").append(html2);
                        $(".zxf_noCon_wp").remove();
                    }else{
                        $(".zxf_noCon_wp").remove()
                        var html='<div class="zxf_noCon_wp t_c pdt8">' +
                                '        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_nocon6.png" alt="" class="img_nocon6">' +
                                '        <p class="mgt075">还没有人发布帖子哦!</p>' +
                                '    </div>';
                        $(".zxf_content").append(html);
                    }
                    if(data.list.length!=10){
                        $.detachInfiniteScroll($('.infinite-scroll'));
                        $('.infinite-scroll-preloader').remove();
                        return;
                    }
                }
            });
        }


        function getLikePost(obj,postId){
            var type = 0;
            if($(obj).hasClass("on")){//删除
                type=1;
                $(obj).removeClass("on");
            }else{
                $(obj).addClass("on");
            }
            $.ajax({
                type:"get",
                url:"/wfd/addPostLike",
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

        $(document).on("click",".popup-overlay, .popup-time a",function(){
            $(".open-time").removeClass("on").find(".icon").removeClass("icon-up").addClass("icon-down");
        });
        $(document).on("click",".fr.open-popup.open-time",function(){
            $(".open-time").removeClass("on").find(".icon").removeClass("icon-down").addClass("icon-up");
        });


        var trunPlateId = $("#tPlateId2").val();
        if(trunPlateId !=null && trunPlateId !=''){
            for(var i=0;i<$('.zxf_popup_helpStyle li').length;i++){
                if($('.zxf_popup_helpStyle li').eq(i).find('#tPlateId').val()==trunPlateId){
                    $("#title").text($('.zxf_popup_helpStyle li').eq(i).find('a').text())
                    $('.zxf_popup_helpStyle li').eq(i).addClass("on").siblings().removeClass("on");
                }
            }
            getIndexPost(0,10,trunPlateId,0)
        }else {
            getPostRealTime(0,10,tabType);
        }
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

        // 点赞
        $(".zxf_stu_item_wp").on("click",".zxf_like_btn",function(){
            $(this).toggleClass("on");
        });
        // 微辅导弹窗li点击
        $(".zxf_header").on("click",".icon_sele",function(e){
            e.stopPropagation();
            if($(this).hasClass("icon-up")){
                $(this).removeClass("icon-up").addClass("icon-down");
                $(".zxf_popup_helpStyle").hide();
            }else{
                $(this).addClass("icon-up").removeClass("icon-dowm");
                $(".zxf_popup_helpStyle").show();
            };
        });
        $(document).on("click",function(e){
            e.stopPropagation();
            $(".zxf_popup_helpStyle").hide();
            $(".icon_sele").removeClass("icon-up");
        })
        $(".zxf_popup_helpStyle").on("click","li",function(e){
            e.stopPropagation();
            $(this).addClass("on").siblings().removeClass("on");
            $("#title").text($(this).text())
            $(".zxf_popup_helpStyle").hide();
            $(".icon_sele").removeClass("icon-up");
            var tPlateId= $(this).find("#tPlateId").val();
            if(typeof(tPlateId)=="undefined"){
                return getPostRealTime(0,10,'');
            }
            if(tPlateId==""){
                getPostRealTime(0,10,'')
            }else{
                getIndexPost(0,10,tPlateId,0)
            }
        });
        //图片浏览器
        $(document).on('click','.zxf_img_Box img.photo',function () {
            var img =  $(this).parents(".zxf_img_Box").find('img')
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
                // loop:true
            });
            myPhotoBrowserPopup.open();
        });

        // 下拉刷新
        $(document).on('refresh', '.pull-to-refresh-content',function(e) {
            var html ='<div class="pull-to-refresh-layer">' +
                    '            <div class="preloader"></div>' +
                    '            <div class="pull-to-refresh-arrow"></div>' +
                    '        </div>'
            if(!$(".zxf_content").find(".pull-to-refresh-arrow").hasClass("pull-to-refresh-arrow")){
                $(".zxf_content").prepend(html);
            }
            sessionStorage.clear();
            setTimeout(function() {
                $(".pull-to-refresh-layer").remove()
                window.location.href="${base}/index.html?plateId="+trunPlateId+"&tabType="+tabType+"";
                $.pullToRefreshDone('.pull-to-refresh-content');
            }, 2000);
        });

        function getPostDetail(postId,plateId,divNum){
            sessionStorage.setItem("divNum",divNum);
            sessionStorage.setItem("pageNum",pageCurrent);
            console.log("divNum:"+divNum);
            console.log("pageNum:"+pageCurrent);
            location.href="${base}/getPostDetail?postId="+postId+"&plateId="+plateId+"&turn=1&tabType="+tabType;
        }
        function getPostDetailTurn(postId,plateId,divNum){
            sessionStorage.setItem("divNum",divNum);
            sessionStorage.setItem("pageNum",pageCurrent);
            console.log("divNum:"+divNum);
            console.log("pageNum:"+pageCurrent);
            location.href="${base}/getPostDetail?postId="+postId+"&plateId="+plateId+"&turn=2";
        }

        function sendMessage(postId,plateId,divNum){
            sessionStorage.setItem("divNum",divNum);
            sessionStorage.setItem("pageNum",pageCurrent);
            console.log("divNum:"+divNum);
            console.log("pageNum:"+pageCurrent);
            location.href="${base}/getPostDetail?postId="+postId+"&plateId="+plateId+"&turn=1&tabType="+tabType+"&sendEntrance=0";
        }

        function sendMessageTurn(postId,plateId,divNum){
            sessionStorage.setItem("divNum",divNum);
            sessionStorage.setItem("pageNum",pageCurrent);
            console.log("divNum:"+divNum);
            console.log("pageNum:"+pageCurrent);
            location.href="${base}/getPostDetail?postId="+postId+"&plateId="+plateId+"&turn=2&sendEntrance=0";
        }

        function sendPost(){
            sessionStorage.clear();
            window.location.href="${base}/sendPost.html?plateId="+trunPlateId+"&turn="+turnTypePost;
        }
        function getUserPost(){
            sessionStorage.clear();
            window.location.href="${base}/getUserPost.html";
        }
        function getPostMessage(){
            sessionStorage.clear();
            window.location.href="${base}/getPostMessage.html";
        }
        function getLike(){
            sessionStorage.clear();
            window.location.href="${base}/getLike.html";
        }
        function getReply(){
            sessionStorage.clear();
            window.location.href="${base}/getReply.html";
        }
        function getCollection(){
            sessionStorage.clear();
            window.location.href="${base}/getCollection.html";
        }
        function plateList(userId){
            sessionStorage.clear();
            window.location.href="${base}/page/manager/plate/list.html?userId="+userId;
        }
        function classManage(userId){
            sessionStorage.clear();
            window.location.href="${base}/page/manager/classManage.html?userId="+userId;
        }
        function count(){
            sessionStorage.clear();
            window.location.href="${base}/count.html";
        }

    </script>

</div>
<!-- popup弹窗 -->
<div class="popup popup-time">
    <div class="zxf_block pdlr075">
        <div class="zxf_opretion mgb05 bgStyle">
            <a href="javascript:;" onclick="getIndexPost2(1)" class="refuse_btn bb close-popup">回复时间排序</a>
            <a href="javascript:;" onclick="getIndexPost2(2)" class="send_btn close-popup">帖子热度排序</a>
        </div>
        <a href="javascript:;" class="cancel_btn close-popup bgStyle">取消</a>
    </div>
</div>

<!-- 侧栏 -->
<div class="panel-overlay"></div>
<!-- Left Panel with Reveal effect -->
<!-- 学生 -->
<div class="panel panel-right panel-cover zxf_panel panel-student" id='panel-right-student'>
    <div class="">
        <div class="zxf_panel_head pdtb05 pdlr1 bb clearfix bgfff">
            <img src="${userObj.logoUrl}" alt="" class="fl mgr075">
            <p class="name fl">${userObj.name}</p>
        </div>
        <div class="zxf_panel_nav pdtb085 bgfff">
            <ul class="clearfix">
                <li class="t_c">
                    <p class="num">${message.sendPostNum!}</p>
                    <span class="des">提问数量</span>
                </li>
                <li class="t_c">
                    <p class="num">${message.postReceiveNum+message.commentReceiveNum}</p>
                    <span class="des">回复问题数</span>
                </li>
                <li class="t_c">
                    <p class="num">${message.postLikeNum + message.commentLikeNum}</p>
                    <span class="des">被点赞数</span>
                </li>
            </ul>
        </div>
        <div class="zxf_helpStyle_wp bgfff mgt05">
            <ul>
                <li class="pdl075 pdr05 bb clearfix zxf_desBox" onclick="getUserPost()">
                    <i class="icon icon icon-right pull-right"></i>
                    <div class="fl imgBox mgr06">
                        <img class="img mgr075 img_mypost" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_mypost.png">
                    </div>
                    <a href="javascript:;" class="txt_ellipsis w60 con fl">我的帖子</a>
                </li>
                <li class="pdl075 pdr05 bb clearfix zxf_desBox" onclick="getPostMessage()">
                    <i class="icon icon icon-right pull-right"></i>
            <#if message.commentTotal !=0>
            	<span class="bg_red_num fr mgr06 bg_red_num2"><i>${message.commentTotal!}</i></span>
            </#if>
                    <div class="fl imgBox mgr06">
                        <img class="img mgr075 img_repy" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_repy.png">
                    </div>
                    <a href="javascript:;" class="txt_ellipsis w60 con fl">评论我的</a>
                </li>
                <li class="pdl075 pdr05 bb clearfix zxf_desBox" onclick="getLike()">
                    <i class="icon icon icon-right pull-right"></i>
             <#if message.postLikeTotal + message.commentLikeTotal !=0>
            	<span class="bg_red_num fr mgr06 bg_red_num2"><i>${message.postLikeTotal + message.commentLikeTotal}</i></span>
             </#if>
                    <div class="fl imgBox mgr06">
                        <img class="img mgr075 img_pick" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_pick.png">
                    </div>
                    <a href="javascript:;" class="txt_ellipsis w60 con fl">点赞我的</a>
                </li>
                <li class="pdl075 pdr05 bb clearfix zxf_desBox" onclick="getReply()">
                    <i class="icon icon icon-right pull-right"></i>
            <#if message.replyTotal !=0>
            	<span class="bg_red_num fr mgr06 bg_red_num2"><i>${message.replyTotal!}</i></span>
            </#if>
                    <div class="fl imgBox mgr06">
                        <img class="img mgr075 img_at" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_@.png">
                    </div>
                    <a href="javascript:;" class="txt_ellipsis w60 con fl">@我的</a>
                </li>
                <li class="pdl075 pdr05 bb clearfix zxf_desBox" onclick="getCollection()">
                    <i class="icon icon icon-right pull-right"></i>
                    <div class="fl imgBox mgr06">
                        <img class="img mgr075 img_collect" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_collect.png">
                    </div>
                    <a href="javascript:;" class="txt_ellipsis w60 con fl">我的收藏</a>
                </li>
            </ul>
        </div>

    <#if (userObj.identityId ??) && (userObj.identityId =='3')>
    <div class="zxf_helpStyle_wp bgfff mgt05">
        <ul>
            <li class="pdl075 pdr05 bb clearfix zxf_desBox" onclick="plateList('#{userObj.id}')">
                <i class="icon icon icon-right pull-right"></i>
                <div class="fl imgBox mgr06">
                    <img class="img mgr075 img_module" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_module.png">
                </div>
                <a target="_blank" href="javascript:;" class="txt_ellipsis w60 con fl">版块管理</a>
            </li>
            <li class="pdl075 pdr05 bb clearfix zxf_desBox" onclick="classManage('#{userObj.id}')">
                <i class="icon icon icon-right pull-right"></i>
                <div class="fl imgBox mgr06">
                    <img class="img mgr075 img_class" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_class.png">
                </div>
                <a target="_blank" href="javascript:;" class="txt_ellipsis w60 con fl">班级管理</a>
            </li>
            <li class="pdl075 pdr05 bb clearfix zxf_desBox" onclick="count()">
                <i class="icon icon icon-right pull-right"></i>
                <div class="fl imgBox mgr06">
                    <img class="img mgr075 img_counter" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_counter.png">
                </div>
                <a href="javascript:;" class="txt_ellipsis w60 con fl">全校统计</a>
            </li>
        </ul>
    </div>
    </#if>
        <div class="zxf_helpStyle_wp bgfff mgt05">
            <ul>
                <li class="pdl075 pdr05 bb clearfix">
                    <label class="label-switch fr mgt06">
                        <input type="checkbox">
                        <div class="checkbox"></div>
                    </label>
                    <div class="fl imgBox mgr06">
                        <img class="img mgr075 img_message" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/icon/icon_message.png">
                    </div>
                    <a href="javascript:;" class="txt_ellipsis w60 con fl">接收微辅导的应用消息</a>
                </li>
            </ul>
        </div>

    </div>
</div>

<script>
	<#if (userObj.receiveMessage ??) && (userObj.receiveMessage =='0')>
		$(".label-switch input[type='checkbox']").prop("checked", true);
	<#else>
		$(".label-switch input[type='checkbox']").prop("checked", false);
	</#if>

	var userId = '${userObj.id}'
	userId.replace(",", "")

$(document).on("click",".label-switch",function(){
	//var ss = $(":checked").css("background");
	//console.log(ss)

	if($(this).find("input[type='checkbox']").is(":checked")){
		$.ajax({
			type : "post",
			async : true,
			url : "updateReceiveMessage",
			traditional:true,
			data : {
				userId : userId,
				receiveMessage :"0"
			},
			dataType : "text",
			success : function(msg) {
			},
			error : function(errorMsg) {
			}
		})
	}else{
		$.ajax({
			type : "post",
			async : true,
			url : "updateReceiveMessage",
			traditional:true,
			data : {
				userId : userId,
				receiveMessage :"1"
			},
			dataType : "text",
			success : function(msg) {
			},
			error : function(errorMsg) {
			}
		})
	}

});

function getRollBack(pageNum) {
    pageCurrent++;
    console.log("最开始的时候pageCurrent："+pageCurrent)
    if(trunPlateId ==null || trunPlateId ==''){
        $.ajax({
            type:"get",
            url:"/wfd/getPostRealTime",
            data:{
                "pageNum":pageCurrent,
                "pageSize":itemsPerLoad,
                "type":tabType
            },
            dataType : "json",
            async: true,
            success:function(data){
                console.log("进来+"+data.list.length)
                if(data.list.length==0){
                    pageCurrent--;
                    $.detachInfiniteScroll($('.infinite-scroll'));
                    $('.infinite-scroll-preloader').remove();
                    return;
                }
                pageCurrent = data.pageNum;
                typeNum = data.type;
                if(data.list!=null && data.list.length>0){
                    var html2 ='';
                    for(var i=0;i<data.list.length;i++){
                        var divNum = pageCurrent*itemsPerLoad+i;
                        html2 +='                            <div class="zxf_stu_item pdlr075 pdt075 mgb025 bgfff">' +
                                '                                <div class="zxf_desBox" onclick="getPostDetail(\''+data.list[i].id+'\',\''+trunPlateId+'\',\''+divNum+'\')"><div class="clearfix">' +
                                '                                    <img src=\''+data.list[i].logoUrl+'\' alt="" class="fl zxf_stu_img">' +
                                '                                    <div class="zxf_stu_text">' +
                                '                                        <h2 class="clearfix">' +
                                '                                            <p class="fl">'+data.list[i].userName+'</p>' +
                                '                                            <span class="fr subject">'+data.list[i].plateName+'</span>' +
                                '                                        </h2>' +
                                '                                        <p class="zxf_visit_info">' +
                                '                                            <span>'+data.list[i].relativeCreateTime+'</span>' +
                                '                                            <em class="mglr05">|</em>' +
                                '                                            <span>'+data.list[i].viewCount+'浏览</span>' +
                                '                                        </p>' +
                                '                                    </div>' +
                                '                                </div>' +
                                '                                <div class="zxf_des_wp mgt05 ">' +
                                '                                    <div class="mult_line_ellipsis">' +
                                '                                        '+data.list[i].content+'' +
                                '                                    </div>' +
                                '                                </div></div>'
                        if(data.list[i].postImgList!=null && data.list[i].postImgList.length>0){
                            html2 += '                                <div class="zxf_img_Box mgt05">' +
                                    '                                    <a href="javascript:;" class="photo_btn">共<em class="num">'+data.list[i].postImgList.length+'</em>张照片</a>'
                            for(var j =0;j<data.list[i].postImgList.length;j++){
                                html2 += '<img src=\''+data.list[i].postImgList[j].downUrl+'\' alt="" class="photo">'
                            }
                            html2 +='                                </div>'
                        }
                        html2 +='                                <div class="mgt05 zxf_user_response bt clearfix">' +
                                '                                      <a href="javascript:;" class="zxf_comment_btn fl zxf_desBox" onclick="sendMessage(\''+data.list[i].id+'\',\'\',\''+divNum+'\')">' +
                                '                                        <i class="icon_comment mgr025"></i>' +
                                '                                        <em>'+data.list[i].messageCount+'</em>' +
                                '                                    </a>' +
                                '                                    <a href="javascript:;" class="zxf_like_btn fr">' +
                                '                                        <i class="icon_like"></i>' +
                                '                                        <em>'+data.list[i].likeCount+'</em>' +
                                '                                    </a>' +
                                '                                </div>' +
                                '                            </div>'
                    }
                    $(".zxf_stu_item_wp").append(html2);
                    console.log("运行到底部")
                }
                if(data.list.length!=10){
                    $.detachInfiniteScroll($('.infinite-scroll'));
                    $('.infinite-scroll-preloader').remove();
                    return;
                }
                if(pageCurrent<pageNum){
                    getRollBack(pageNum)
                }
            },
            error: function(xhr, type){
                $.alert('Ajax error!');
            }
        });
    }else {
        $.ajax({
            type:"get",
            url:"/wfd/getIndexPost",
            data:{
                "pageNum":pageCurrent,
                "pageSize":itemsPerLoad,
                "plateId":trunPlateId
            },
            dataType : "json",
            async: true,
            success:function(data){
                if(data.list.length==0){
                    pageCurrent--;
                    $.detachInfiniteScroll($('.infinite-scroll'));
                    $('.infinite-scroll-preloader').remove();
                    return;
                }
                pageCurrent = data.pageNum;
                if(data.list!=null && data.list.length>0){
                    var html2 ='';
                    for(var i=0;i<data.list.length;i++){
                        if(!data.list[i].isTop){
                            var divNum = pageCurrent*itemsPerLoad+i;
                            html2 +='<div class="zxf_stu_item pd075 mgb05 bgfff">' +
                                    '                                  <div class="zxf_desBox" onclick="getPostDetailTurn(\''+data.list[i].id+'\',\''+data.list[i].plateId+'\',\''+divNum+'\')">' +
                                    '                                  <div class="clearfix">' +
                                    '                                      <img src=\''+data.list[i].logoUrl+'\' alt="" class="fl zxf_stu_img">' +
                                    '                                      <div class="zxf_stu_text">' +
                                    '                                          <h2 class="clearfix">' +
                                    '                                              <p class="fl">'+data.list[i].userName+'</p>' +
                                    '                                              <span class="fr subject">'+data.list[i].plateName+'</span>' +
                                    '                                          </h2>' +
                                    '                                          <p class="zxf_visit_info">' +
                                    '                                              <span>'+data.list[i].relativeCreateTime+'</span>' +
                                    '                                              <em class="mglr05">|</em>' +
                                    '                                              <span>'+data.list[i].viewCount+'浏览</span>' +
                                    '                                          </p>' +
                                    '                                      </div>' +
                                    '                                  </div>' +
                                    '                                  <div class="zxf_des_wp mgt05 ">' +
                                    '                                      <div class="mult_line_ellipsis">'+data.list[i].content+'</div>'+
                                    '                                  </div>' +
                                    '                                  </div>'
                            if(data.list[i].postImgList!=null && data.list[i].postImgList.length>0){
                                html2 += '                              <div class="zxf_img_Box mgt05">' +
                                        '                                  <a href="javascript:;" class="photo_btn">共<em class="num">'+data.list[i].postImgList.length+'</em>张照片</a>'
                                for(var j=0;j<data.list[i].postImgList.length;j++){
                                    if(data.list[i].postImgList[j].downUrl!=null && data.list[i].postImgList[j].downUrl!=''){
                                        html2 +='<img src=\''+data.list[i].postImgList[j].downUrl+'\' alt="" class="photo">'
                                    }
                                }
                                html2 +=' </div>';
                            }
                            html2 +='                                 <div class="mgt05 zxf_user_response bt clearfix">' +
                                    '                                      <a href="javascript:;" class="zxf_comment_btn fl zxf_desBox" onclick="sendMessageTurn(\''+data.list[i].id+'\',\''+data.list[i].plateId+'\',\''+divNum+'\')">' +
                                    '                                          <i class="icon_comment mgr025"></i>' +
                                    '                                          <em>'+data.list[i].messageCount+'</em>' +
                                    '                                      </a>'
                            if(data.list[i].imgCount!=null && data.list[i].imgCount!=''){
                                html2 +='<a href="javascript:;" class="zxf_like_btn fr on" onclick="getLikePost(this,\''+data.list[i].id+'\')">'
                            }else{
                                html2 +='<a href="javascript:;" class="zxf_like_btn fr" onclick="getLikePost(this,\''+data.list[i].id+'\')">'
                            }
                            html2 += '                                          <i class="icon_like"></i>' +
                                    '                                          <em>'+data.list[i].likeCount+'</em>' +
                                    '                                      </a>' +
                                    '                                  </div>' +
                                    '                              </div>';
                        }
                    }
                    $(".zxf_stu_item_wp").append(html2);
                }
                if(data.list.length!=10){
                    $.detachInfiniteScroll($('.infinite-scroll'));
                    $('.infinite-scroll-preloader').remove();
                    return;
                }
                if(pageCurrent<pageNum){
                    getRollBack(pageNum)
                }
            },
            error: function(xhr, type){
                $.alert('Ajax error!');
                // 即使加载出错，也得重置
            }
        });
    }
}

	window.onload=function () {
        if(window.sessionStorage){
            var divNum =parseInt(sessionStorage.getItem("divNum"));
            var pageNum=parseInt(sessionStorage.getItem("pageNum"));
            if(!isNaN(pageNum)){
                console.log("pageNum:"+pageNum);
                console.log("divNum:"+divNum);
                getRollBack(pageNum+1)
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
</script>

</body>
</html>