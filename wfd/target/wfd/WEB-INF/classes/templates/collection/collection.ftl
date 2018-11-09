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
        <a href="javascript:;" class="fr zxf_editBtn f085 mgt05" style="height: 25px;margin-bottom: 0.5rem;">编辑</a>
        <h1 class="title">我的收藏</h1>
    </header>
    <div class="zxf_coverWp">
        <div class="zxf_toastBox toastBox_ok">
            <i class="icon_ok"></i>
            <p>操作成功</p>
        </div>
    </div>
    <div class="content zxf_content infinite-scroll bgfff  mgt05" data-distance="100">
        <div class="zxf_con-block">
            <div class="zxf_stu_item_wp pdb05">
                 <#if collections?size !=0>
                <#list collections as obj>
                <#if obj.type==1>
                    <div class="zxf_stu_item pd075 bb">
                        <input type="hidden" name="userId" value="${obj.userId?c}">
                        <input type="hidden" name="platePostId" value="${obj.id!}">
                        <a href="javascript:;" class="label_btn icon_label"></a>
                        <div class="clearfix item">
                            <img src="${obj.plogoUrl!}" alt="" class="fl zxf_stu_img">
                            <div class="zxf_stu_text zxf_desBox"  onclick="getPostDetail('${obj.id!}',null,'${obj_index}')">
                                <h2 class="clearfix">
                                    <p class="fl">${obj.postCreator!}</p>
                                </h2>
                                <p class="zxf_visit_info">
                                    <span>${obj.createTime?string('yyyy-MM-dd HH:mm:ss')}</span>
                                </p>
                                <div class="zxf_des_wp mgt05">
                                    <p class="mult_line_ellipsis">${obj.postContent!}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </#if>
                <#if obj.type==2>
                <div class="zxf_stu_item pd075 bb">
                    <input type="hidden" name="mUserId" value="${obj.userId?c}">
                    <input type="hidden" name="mPlatePostId" value="${obj.id!}">
                    <a href="javascript:;" class="label_btn icon_label"></a>
                    <div class="clearfix item">
                        <img src="${obj.mlogoUrl!}" alt="" class="zxf_stu_img fl">
                        <div class="zxf_stu_text zxf_desBox"  onclick="getPostDetail('${obj.replyPostId!}','${obj.id!}','${obj_index}')">
                            <h2 class="clearfix">
                                <p class="fl">${obj.messageCreator!}</p>
                            </h2>
                            <p class="zxf_visit_info">
                                <span>${obj.createTime?string('yyyy-MM-dd HH:mm:ss')}</span>
                            </p>
                            <div class="zxf_des_wp mgt05">
                                <p class="mult_line_ellipsis">回复：
                                    <span>
                                        ${obj.replyContent!}
                                        <#if obj.messageAudioUrl??>
                                            【语音】
                                        </#if>
                                        <#if obj.messageImgUrl??>
                                            【图片】
                                        </#if>
                                    </span>
                                </p>
                                <div class="zxf_origin_post pd05 bgStyle2 mgt06">
                                    <p class="mult_line_ellipsis">
                                        ${obj.replyPostContent!}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </#if>
                </#list>
            <#else>
                    <div class="zxf_noCon_wp pdt8 t_c">
                        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_nocon5.png" alt="" class="img_nocon3">
                        <p class="mgt075">您还没有收藏帖子哦!</p>
                    </div>
            </#if>
            </div>
        </div>
    </div>
    <script>
        //初始化FastClick实例
        $(function() {
            FastClick.attach(document.body);
        });

        // 编辑
        $(document).on("click",".zxf_editBtn",function(){
            var $that=this;
            if($(this).hasClass("on")){
                $.confirm('确定要删除收藏帖子？',
                        function () {
                            var userIds= new Array();
                            var platePostIds = new Array();
                            var mUserIds = new Array();
                            var mPlatePostIds = new Array();
                            $(".zxf_stu_item.on.chked").find("input[name='userId']").each(function(){
                                userIds.push($(this).val());
                            })
                            $(".zxf_stu_item.on.chked").find("input[name='platePostId']").each(function(){
                                platePostIds.push($(this).val());
                            })
                            $(".zxf_stu_item.on.chked").find("input[name='mUserId']").each(function(){
                                mUserIds.push($(this).val());
                            })
                            $(".zxf_stu_item.on.chked").find("input[name='mPlatePostId']").each(function(){
                                mPlatePostIds.push($(this).val());
                            })
                            $(".zxf_stu_item.on.chked").remove();
                            $($that).removeClass("on").text("编辑");
                            $(".zxf_stu_item").removeClass("on chked");
                            if(platePostIds.length>0 || mPlatePostIds.length>0){
                                $.ajax({
                                    type:"get",
                                    url:"deleteCollection",
                                    data:{
                                        "userIds":userIds,
                                        "platePostIds":platePostIds,
                                        "mUserIds":mUserIds,
                                        "mPlatePostIds":mPlatePostIds
                                    },
                                    traditional:true,
                                    async: true,
                                    dataType:'json',
                                    success:function(msg){
                                        if(msg==0){
                                            window.location.reload();
                                        }
                                    }
                                })
                            }
                        },
                        function () {
                            $($that).removeClass("on").text("编辑");
                            $(".zxf_stu_item").removeClass("on chked");
                        }
                );
            }else{
                $(this).addClass("on").text("删除");
                $(".zxf_stu_item").addClass("on");
            }
        }).on("click",".label_btn",function(){
            $(this).parents(".zxf_stu_item").toggleClass("chked");
        });

        $(document).on('touchend', '.icon.icon-left.pull-left',function(e) {
            window.location.href="${base}/index.html";
        });

        appGoback = function(){
            window.location.href="${base}/index.html";
        }
        
        function getPostDetail(postId,id,divNum) {
            sessionStorage.setItem("collectionDivNum",divNum);
            if(id ==null){
                window.location.href="${base}/getPostDetail?postId="+postId+"&turn=7";
            }else{
                window.location.href="${base}/getPostDetail?postId="+postId+"&turn=7&messageId="+id;
            }
        }

        window.onload=function () {
            if(window.sessionStorage){
                var divNum =parseInt(sessionStorage.getItem("collectionDivNum"));
                $('.zxf_stu_item')[divNum].scrollIntoView();
            }
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