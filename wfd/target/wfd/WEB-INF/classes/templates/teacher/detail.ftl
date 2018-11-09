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
    <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/css/light7-swiper.min.css">
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
    <script type='text/javascript' src='http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/light7-swiper.min.js' charset='utf-8'></script>
</head>
<body>
<div class="page">
    <header class="bar bar-nav bgfff">  
        <a class="icon icon-left fl"></a>      
        <h1 class="title">详情</h1>
        <a class="wql_icon wql_icon01 fr zindex20 isTop"></a>  
    </header>
    <div class="content pdb25">   
        <div class="wql_g_box">
            <div class="wql_box01 clearfix wql_bg_fff pd075">
                <div class="wql_box_l fl"><img src="images/img_stu1.png"></div>
                <div class="wql_box_r">
                    <div class="wql_tit lh12 f085 c555">${platePost.userName}</div>
                    <div class="wql_subTit lh1 c9a9a9a f065">${platePost.relativeCreateTime}<em class="mglr05">|</em> ${platePost.viewCount}人浏览</div>
                </div>
            </div>
        </div>
        <div class="c353535 pdlr075 wql_bg_fff f075 lh1">${platePost.content}</div>
        <!-- 九宫格 S -->
        <div class="wql_g_list">
            <div class="wql_list02 pdlr05 pdt05 wql_bg_fff">
                <ul class="wql_ul clearfix pdlr025 bb pdb05">
                    <li class="wql_li">
                        <img src="images/wql_imgList01.jpg">
                    </li>
                    <li class="wql_li">
                        <img src="images/wql_imgList02.jpg">
                    </li>
                    <li class="wql_li">
                        <img src="images/wql_imgList03.jpg">
                    </li>
                    <li class="wql_li">
                        <img src="images/wql_imgList01.jpg">
                    </li>
                    <li class="wql_li">
                        <img src="images/wql_imgList02.jpg">
                    </li>
                    <li class="wql_li">
                        <img src="images/wql_imgList03.jpg">
                    </li>
                    <li class="wql_li">
                        <img src="images/wql_imgList01.jpg">
                    </li>
                    <li class="wql_li">
                        <img src="images/wql_imgList02.jpg">
                    </li>
                    <li class="wql_li">
                        <img src="images/wql_imgList03.jpg">
                    </li>
                </ul>
            </div>
        </div>
        <!-- 九宫格 E -->
        <!-- 点赞 S-->
        <div class="wql_g_like wql_bg_fff pdtb05">
            <div class="wql_like01 t_c">
              <a href="javascript:;" class="zxf_like_btn">
                <i class="icon_like"></i>
                <em>${platePost.likeCount}</em>
              </a>
            </div>
        </div>
        <!-- 点赞 E-->
        
        <div class="wql_bg_fff pdlr075 mgt05">
            <div class="clearfix lh22 f075 bb">
                <div class="fl">回复时间排序</div>
                <div class="fr">排序<span class="icon icon-down mgl025"></span></div>
            </div>
            <ul class="wql_commentList">
                <#list postMessageList as postMessage>
            		<li class="wql_li pdb075 bb">
	                    <div class="wql_g_box">
	                        <div class="wql_box01 clearfix pdt075 pdb05">
	                            <div class="wql_box_l fl"><img src="images/img_stu2.png"></div>
	                            <div class="wql_box_r">
	                                <div class="wql_tit lh12 f085 c555">${postMessage.userName}
	                                		<#if postMessage.userName == '1' >  
	                                			<span class="wql_flag wql_flag01">老师</span>
	                                		</#if>
	                                		<#if postMessage.userId == userId >  
	                                			<span class="wql_flag wql_flag03">楼主</span>
	                                		</#if>
	                                    <span class="wql_icon wql_icon01 fr mgl05 isTop"></span>
	                                    <div class="wql_g_like fr">
	                                        <div class="wql_like01">
	                                            <a href="javascript:;" class="wql_icon wql_icon_good">${postMessage.likeCount}</a>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="wql_subTit lh1 c9a9a9a f065">${postMessage.relativeCreateTime}</div>
	                            </div>
	                        </div>
	                    </div> 
	                    <div class="mgl3 mgr125 c353535 f075 lh1">${postMessage.content}</div>
	                </li>
            	</#list>
            </ul>
        </div>
        
    </div>
    <div class="wql_g_footBtn">
        <div class="wql_footBtn01 bt f075 c9a9a9a wql_bg_fff pdl075">
            说说你的看法...
        </div>
    </div>
</div>
<script>
    // 点赞
    $(document).on("click",".zxf_like_btn",function(){
        $(this).toggleClass("on");
    })
    // 更多 S
    $(document).on("click",".wql_icon01",function(){
        var that = $(this);
        var isTop = $(this).hasClass('isTop') ? '取消置顶' : '置顶帖子' ;
        var col = $(this).hasClass('isTop') ? 'danger' : '#007aff' ;
        var buttons1 = [
           
            {   
                
                text: isTop,
                color: col,
                onClick: function() {
                    that.toggleClass('isTop');
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
                                    $.modal({
                                        title: '确认删除评论？',
                                        buttons: [
                                            {   
                                                title:  ' ',
                                                text: '取消',
                                                onClick: function() {
                                                
                                                }
                                            },
                                            {
                                                text: '删除',
                                                onClick: function() {
                                                    
                                                }
                                            },
                                        ]
                                    })
                                }
                            },
                        ]
                    })
                }
            },
            {
                text: '收藏帖子',
                
            }
        ];
        var buttons2 = [
            {
                text: '取消',
              
            }
        ];
        var groups = [buttons1, buttons2];
        $.actions(groups);
    })
    
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
            loop:true
        });
        myPhotoBrowserPopup.open();
    });
</script>
</body>
</html>