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
    <script>
        $.config = {
            autoInit: true,
            router:true
        }
    </script>
    <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/jquery.js"></script>
    <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/light7.js"></script>
    <script type="text/javascript" src="${base}/js/fastclick.js"></script>
</head>
<body>
<div class="page">
    <header class="bar bar-nav zxf_header bgfff">
        <a class="icon icon-left pull-left zxf_desBox"></a>
        <h1 class="title">@我的</h1>
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
                    <#if replies?size !=0>
                <#list replies as reply>
                    <div class="zxf_stu_item pd075 bb">
                        <div class="clearfix">
                            <div class="zxf_imgBox fl">
								<#if !reply.isRead>
								<i class="icon_red_dot"></i>
								</#if>
								<img src="${reply.logoUrl!}" alt="" class="fl zxf_stu_img zxf_desBox" id="${reply.platePostId!}">
							</div>
                            <div class="zxf_stu_text">
                                <h2 class="clearfix">
                                    <p class="fl zxf_desBox" id="${reply.platePostId!}">${reply.userName!}</p>
                                    <#if reply.userType=='0'>
                                    	<a class="wql_icon wql_icon01 fr zindex20 <#if reply.favoriteTime?? >isCol</#if>" id="${reply.id!}" ></a>
                                    </#if>
                                    <#if reply.userType=='1'>
							            <a class="wql_icon wql_icon01 fr zindex20 isDelete <#if reply.favoriteTime?? >isCol</#if>" id="${reply.id!}"></a>
							        </#if>
                                    
                                    
                                </h2>
                                <p class="zxf_visit_info zxf_desBox" id="${reply.platePostId!}">
                                    <span>${reply.createTimeStr!}</span>
                                </p>
                                <div class="zxf_des_wp mgt05 zxf_desBox" id="${reply.platePostId!}">
                                    <p class="mult_line_ellipsis">
                                        回复：<span>${reply.content!}</span>
                                    </p>
                                    <div class="zxf_origin_post pd05 bgStyle2 mgt06 mult_line_ellipsis" id="${reply.platePostId!}" >
                                        <p class="mult_line_ellipsis">
                                            原贴：${reply.postContent!}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
                    <#else>
                    <div class="zxf_noCon_wp pdt8 t_c">
                        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_nocon4.png" alt="" class="img_nocon3">
                        <p class="mgt075">您还没有收到提醒哦!</p>
                    </div>
                    </#if>
                </div>
            </div>
            <!-- 加载提示符 -->
            <!-- <div class="infinite-scroll-preloader">
              <div class="preloader">
              </div>
            </div>        -->
        </div>
    </div>
    <script>
        //初始化FastClick实例
        $(function() {
            FastClick.attach(document.body);
        });
    	$(document).on('click', '.zxf_des_wp',function(e) {
           var postId = $(this).attr("id");
           location.href="${base}/getPostDetail?postId="+postId+"&turn=6";
        });
        
    	$(document).on('click', '.zxf_stu_img',function(e) {
           var postId = $(this).attr("id");
           location.href="${base}/getPostDetail?postId="+postId+"&turn=6";
        });
        
        $(document).on('click', '.fl',function(e) {
           var postId = $(this).attr("id");
           location.href="${base}/getPostDetail?postId="+postId+"&turn=6";
        });
        
        $(document).on('click', '.zxf_visit_info',function(e) {
           var postId = $(this).attr("id");
           location.href="${base}/getPostDetail?postId="+postId+"&turn=6";
        });
    	
    	
    
        $(document).on('touchend', '.icon.icon-left.pull-left',function(e) {
            window.location.href="${base}/ediIsRead";
        });
        appGoback = function(){
            window.location.href="${base}/ediIsRead";
        }

        function getPostDetail(postId){
            location.href="${base}/getPostDetail?postId="+postId+"&turn=6";
        }
        
       
        
        var userType ="${userObj.userType!}";
        
        
        $(document).on('touchend', '.zindex20',function(e) {
  			var that = $(this);

  			var post_message_id = $(this).attr("id");
            var type = $(this).hasClass('isDelete');  
        	var type2 = $(this).hasClass('isCol');
        	var isCol = $(this).hasClass('isCol') ? '取消收藏' : '收藏评论' ;
        	var col2 = $(this).hasClass('isCol') ? 'danger' : '#007aff' ;
        	
        	if(userType == 1){
        			var buttons1 = [
		                {
		                    text: '删除帖子',
		                    onClick: function() {
		                        $.modal({
		                            title:  '提示',
		                            text: '确定需要删除此回复吗?',
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
		                                            url:"deleteMessageByMessageId",
		                                            data:{
		                                                "messageId":post_message_id
		                                            },
		                                            async: true,
		                                            dataType:'json',
		                                            success:function (msg) {
		                                                if(msg==1){
		                                                    $(".zxf_coverWp").show(2000);
		                                                    $(".toastBox_send").hide().next('.toastBox_ok').show();
		                                                    setTimeout(function(){
		                                                        $(".zxf_coverWp").hide();
		                                                    },2000)
		                                                    setTimeout(function (){
		                                                        window.location.href="${base}/getReply.html"
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
		                            url:"addCollectionMessage",
		                            data:{
		                                "messageId":post_message_id
		                            },
		                            async: true,
		                            dataType:'json',
		                            success:function (msg) {
		                                if(msg==1){
		                                    $(".zxf_coverWp").show(2000);
		                                    $(".toastBox_send").hide().next('.toastBox_ok').show();
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
	                    text: isCol,
		                color: col2,
	                    onClick: function() {
	                        	that.toggleClass('isCol');
		                        $.ajax({
		                            type:"get",
		                            url:"optCollectionMessage",
		                            data:{
		                                "messageId":post_message_id,
		                                "type":type2
		                            },
		                            async: true,
		                            dataType:'json',
		                            success:function (msg) {
		                                if(msg==1){
		                                    $(".zxf_coverWp").show(2000);
		                                    $(".toastBox_send").hide().next('.toastBox_ok').show();
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
        });
        
    </script>
</div>
<!-- popup弹窗 -->

<!-- 侧栏 -->
<div class="panel-overlay"></div>
<!-- Left Panel with Reveal effect -->
<!-- 学生 -->
</body>
</html>