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
        <a class="icon icon-left pull-left" ></a>        
        <h1 class="title">热门问题</h1>
    </header>
    <div class="content zxf_content infinite-scroll" data-distance="100">     
      <div class="zxf_con-block">                
        <div class="" id="content">  
        		
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
    
     function sendMessageTurn(postId,plateId){
         /*sessionStorage.setItem("divNum",divNum);
         sessionStorage.setItem("pageNum",pageCurrent);
         console.log("divNum:"+divNum);
         console.log("pageNum:"+pageCurrent);*/
         location.href="${base}/getPostDetail?postId="+postId+"&plateId="+plateId+"&turn=8&sendEntrance=0";
    }
    function getPostDetailTurn(postId,plateId) {
        location.href="${base}/getPostDetail?postId="+postId+"&plateId="+plateId+"&turn=8";
    }

	function getIndexPost(){
		$.ajax({
			type:"get",
            url:"getHostPost",
            dataType : "json",
            async: true,
            success:function(data){
            	if(data.list!=null && data.list.length>0){
                        var html2 ='';
                        for(var i=0;i<data.list.length;i++){
                            var index=i+1;
                            html2 +='<div class="zxf_stu_item pd075 mgb05 bgfff">' +
                                    '                                  <div onclick="getPostDetailTurn(\''+data.list[i].id+'\',\''+data.list[i].plateId+'\')">' +
                                    '                                  <div class="clearfix">' +
                                    '									<div class="zxf_imgBox fl">'+
                                    '										<i class="icon_level">'+index+'</i>'+
                                    '                                      	<img src=\''+data.list[i].logoUrl+'\' alt="" class="fl zxf_stu_img">' +
                                    '									</div>'+
                                    '                                      <div class="zxf_stu_text">' +
                                    '                                          <h2 class="clearfix">' +
                                    '                                              <p class="fl">'+data.list[i].userName+'</p>' +
                                    '                                              <span class="fr subject">'+data.list[i].plateName+'</span>'
                                     if(data.list[i].userType=='1'){
                                        html2 +='<a href="javascript:;" class="zxf_bor_orangeBtn">老师</a>'
                                    }


                                    html2 += ' </h2>' +
                                    '                                          <p class="zxf_visit_info">' +
                                    '                                              <span>'+data.list[i].relativeCreateTime+'</span>' +
                                    '                                              <em class="mglr05">|</em>' +
                                    '                                              <span><em class="num">'+data.list[i].viewCount+'</em>浏览</span>' +
                                    '                                          </p>' +
                                    '                                      </div>' +
                                    '                                  </div>' +
                                    '                                  <div class="zxf_des_wp mgt05 mult_line_ellipsis">' +
                                    '                                      <div>'+data.list[i].content+'</div>'+
                                    '                                  </div>' +
                                    '                                  </div>'
                            if(data.list[i].postImgList!=null && data.list[i].postImgList.length>0){
                                html2 += '                              <div class="zxf_img_Box mgt05">' +
                                        '                                  <a href="javascript:;" class="photo_btn"><span>共<em class="num">'+data.list[i].postImgList.length+'</em>张照片</span></a>'
                                for(var j=0;j<data.list[i].postImgList.length;j++){
                                    if(data.list[i].postImgList[j].downUrl!=null && data.list[i].postImgList[j].downUrl!=''){
                                        html2 +='<img src=\''+data.list[i].postImgList[j].downUrl+'\' alt="" class="photo">'
                                    }
                                }
                                html2 +=' </div>';
                            }
                            html2 +='                                 <div class="mgt05 zxf_user_response bt clearfix">' +
                                    '                                      <a href="javascript:;" class="zxf_comment_btn fl" onclick="sendMessageTurn(\''+data.list[i].id+'\',\''+data.list[i].plateId+'\')">' +
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
                        $(".content").append(html2);
                    }else{
                        var html='<div class="zxf_noCon_wp t_c pdt8">' +
                                '        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_nocon6.png" alt="" class="img_nocon6">' +
                                '        <p class="mgt075">还没有人发布帖子哦!</p>' +
                                '    </div>';
                        $("#contentTime").append(html);
                    }
            }

        });
	}

   getIndexPost();

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
    
	appGoback = function(){
        window.location.href="${base}/count.html";
    }
        $(document).on('touchend', '.icon.icon-left.pull-left',function(e) {
            window.location.href="${base}/count.html";
        });
    </script>
</div>
</body>
</html>