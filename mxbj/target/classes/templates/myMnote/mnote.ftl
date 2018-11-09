<#assign base=request.contextPath />
<!doctype html>
<!--[if IE 9 ]>    <html  class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html> <!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="${base}/css/jquery.ui.css">
	<link rel="stylesheet" href="${base}/css/jquery.ui.html4.css">
	<link rel="stylesheet" href="${base}/css/public.css">
	<link rel="stylesheet" href="${base}/css/docs.css">
	<title>墨香笔记阅读器</title>
</head>
<body>
<#assign numberchangeToChinese = "com.whty.mxbj.common.utils.freemarker.NumberChangeToChineseMethod"?new()/>
<div class="g_wrap" style="background:#0c9183">
	<!--使用中-->
	<#if title =="1"><div class="book_info"></div></#if>
	<#if title =="2"><div class="book_info saved"></div></#if>

	<!--已归档-->
	<div id="canvas">
		<!--图标按钮 bgin-->
		<div class="button_list">
			<ul>
				<li>
					<div class="button_group ">
						<div class="button_item catalogue">
							<a title="查看目录" href="javascript:void(0);" name="edit" class="icon icon-list"></a>
							<div class="catalogue_box dis_none">
								<h2>目录<a href="javascript:void(0);" name="close" class="icon icon-close"></a></h2>
								<ul>
									<li><a class="clearfix" href="#page/1">
										<span class="fl">封面</span><span class="fr">封面</span></a></li>
									<#list pageList as page>
									<li><a class="clearfix" href="#page/${page_index+3}">
										<span class="fl">第${numberchangeToChinese(page.pageId)}页</span><span class="fr">${page.pageId}</span></a></li>
									</#list>
									<li><a class="clearfix" href="#page/${pageList?size+3}">
										<span class="fl">封底</span><span class="fr">封底</span></a></li>
								</ul>
							</div>
						</div>
						<div class="button_item share">
							<a title="转发分享" href="javascript:void(0);" name="edit" class="icon icon-share"></a>
							<div class="share_box dis_none" style="width: 160px">
								<h2>分享到</h2>
								<div class="bdsharebuttonbox" data-tag="share_1">
                                    <a href="javascript:;" class="bds_teach icon_teachManage"></a>
									<a class="bds_dialog" href="#"></a>
									<a class="bds_weixin" data-cmd="weixin" href="#"></a>
									<a class="bds_qzone" data-cmd="qzone" href="#"></a>
									<a class="bds_tsina" data-cmd="tsina"></a>
								</div>
							</div>
						</div>
						<div class="button_item fullScreen">
							<a title="全屏阅读" href="javascript:void(0);" name="fullScreen" class="icon icon-full-screen"></a>
						</div>
					</div>
				</li>
				<li>
					<div class="button_group change">
						<a title="上一页" href="javascript:void(0);" name="prev" class="icon icon-arrow-left"></a>
						<a title="下一页" href="javascript:void(0);" name="next" class="icon icon-arrow-right"></a>
					</div>
				</li>
			</ul>
		</div>
		<!--图标按钮 end-->
		<!--书本 begin-->
		<div id="book-zoom">
			<div class="sample-docs">
				<!--封面 begin-->
				<div class="hard page_cover">
					
				</div>
				<div class="hard page_cover">
				
				</div>
				<!--封面 end-->
				<!--书页内容 begin-->
				<#list pageList as page>
					<div>
						<div class="newcheck right">
							<input id="input_check${page_index}" type="checkbox" value="${page.pageFileUrl}" class="input_check" >
							<label for="input_check${page_index}" class="spanlabel"></label>
						</div>
						<div class="gradient"><div class="loader"></div>
						</div>
						<img src="" data-src="${page.pageFileUrl}" alt="" width="471" height="590">
					</div>
				</#list>
				
				<!--书页内容 end-->
			</div>
		</div>
		<!--书本 end-->
		<!--滑块 begin-->
		<div id="slider-bar" class="turnjs-slider">
			<div id="slider"></div>
		</div>
		<!--滑块 end-->
	</div>
	<div id="newBuildPop" class="buildPop dis_none f14">
		<div class="content">
			
			<#if (textBookList?size > 0)>
				<ul>
				<li style="margin-bottom: 30px">
					<span class="gs_tit">将内容同步至：</span>
					<span class="selectsWrap">
							<#list textBookList as textBook>
								<#if textBook_index == 0>
									<p class="titName" style="width:240px;">
										<span>${textBook_index} ${textBook.planName}</span>
										<em class="tri"></em>
										<input type="hidden" id="planId" name="planId" value="${textBook.planId}">
										<input type="hidden" id="pushedId" name="pushedId" value="${textBook.pushedId}">
									</p>
								</#if>
							</#list>
							
							<div class="dropCon" style="width:238px;">
								<div class="itemGroup">
									<#list textBookList as textBook>
									<#if textBook_index == 0>
										<a class="cur" href="javascript:;" planId="${textBook.planId}" pushedId="${textBook.pushedId}"  textBookName="${textBook.textBookName}"  textBookId="${textBook.textBookId}" value="${textBook.pushedId}">${textBook.planName}</a>
									<#else>
										<a href="javascript:;" planId="${textBook.planId}"  pushedId="${textBook.pushedId}"  textBookName="${textBook.textBookName}" textBookId="${textBook.textBookId}" value="${textBook.pushedId}">${textBook.planName}</a>
									</#if>
									</#list>
								</div>
							</div>
					</span>
				</li>
				
				<li class="f12 mainli">
				
				</li>
			<#else>
				请去添加教材  <a href="http://zhjx.huijiaoyun.com/org/teaching/lg/login" target="_blank" style="color:#00F">去添加</a>
			</ul>
			</#if>
			
			
			<div class="t_c mgt30 pdb10">
				<a href="#" class="rd_blue_btn mgr20" true>确定</a>
				<a href="#" class="rd_dark_btn " false>取消</a>				
			</div>
		</div>
	</div>
</div>

<!-- 分享弹窗 start -->
<div class="gs_teachingShare_dialog dis_none">
    <div class="gs_teachingShareWrap">
        <div class="gs_shareList clearfix mgb20">
            <span class="gs_selectLabel fl">请选择学期：</span>
            <div class="gs_selecDrop fl" id="termList">
                <p class="gs_selectArrow">2018-2019学年 上学期</p>
                <ul class="gs_selectDropList">
                    <li><a href="javascript:;">2018-2019学年 上学期</a></li>
                    <li><a href="javascript:;">2018-2019学年 上学期</a></li>
                    <li><a href="javascript:;">2018-2019学年 上学期</a></li>
                </ul>
            </div>
        </div>
        <div class="gs_shareList clearfix mgb20">
            <span class="gs_selectLabel fl">将内容同步至：</span>
            <div class="gs_selecDrop fl" id="gettaskmodel">
                <p class="gs_selectArrow">我的专题课</p>
                <ul class="gs_selectDropList">
                    <li>
                        <a href="javascript:;">我的专题课</a>
                        <ul>
                            <li><a href="javascript:;">我的专题课1</a></li>
                            <li><a href="javascript:;">我的专题课2</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:;">我的课题研究</a>
                        <ul>
                            <li><a href="javascript:;">我的课题研究1</a></li>
                            <li><a href="javascript:;">我的课题研究2</a></li>
                        </ul>
                    </li>
                    <li><a href="javascript:;">我的听评课</a></li>
                </ul>
            </div>
        </div>
        <div class="gs_projectTimeList">
            <ul id="taskList">
                <li>
                    <i class="icon_checkbox"></i>
                    <div class="gs_projectDetail">
                        <h5>专题活动 沁园春 长沙</h5>
                        <p class="gs_projectTxt">2018-8-29 14:00至2018-8-29 15:00</p>
                        <p class="gs_projectTxt">主持人：王晓雅</p>
                        <p class="gs_projectTxt">地点：多功能教师</p>
                    </div>
                    <em class="icon_state un">未开始</em>
                </li>
                <li>
                    <i class="icon_checkbox"></i>
                    <div class="gs_projectDetail">
                        <h5>专题活动 沁园春 长沙</h5>
                        <p class="gs_projectTxt">2018-8-29 14:00至2018-8-29 15:00</p>
                        <p class="gs_projectTxt">主持人：王晓雅</p>
                        <p class="gs_projectTxt">地点：多功能教师</p>
                    </div>
                    <em class="icon_state over">已结束</em>
                </li>
            </ul>
        </div>
    </div>
</div>
<!-- 分享弹窗 start -->

<input type="hidden" id="loginPlatformCode" name="loginPlatformCode" value="${loginPlatformCode}">
<input type="hidden" id="userId" name="userId" value="${userId}">
<input type="hidden" id="noteId" name="noteId" value="${noteId}">
<input type="hidden" id="noteType" name="noteType" value="${noteType}">
<input type="hidden" id="userName" name="userName" value="${userName}">

<input type="hidden" id="textBookListSize" name="textBookListSize" value="${textBookList?size}">

<script type="text/javascript" src="${base}/js/jquery.min.1.7.js"></script>
<script type="text/javascript" src="${base}/js/jquery-ui-1.8.20.custom.min.js"></script>
<script type="text/javascript" src="${base}/js/turn.js"></script>
<script type="text/javascript" src="${base}/js/modernizr.2.5.3.min.js"></script>
<script type="text/javascript" src="${base}/js/hash.js"></script>
<script type="text/javascript" src="${base}/js/docs.js"></script>
<script type="text/javascript" src="${base}/js/jquery.artDialog.js"></script>
<script type="text/javascript" src="${base}/js/jquery.artDialog.plugins.js"></script>
<script type="text/javascript">

	var pushedId='';
	var textBookId = '';
	var textBookName = '';
	var planId = '';
	var userId = $("#userId").val();
	var userName = $("#userName").val();
	var loginPlatformCode= $("#loginPlatformCode").val();
	var noteId = $("#noteId").val();
	var noteType = $("#noteType").val();
	var lessonId = '';

function addHtml(node){
 	var subTree = node.subTree;

 	var htmlStr ='';
 	if(subTree.length>0){
    	htmlStr += '<div class="mainliTitle showItems">';
   		htmlStr += '<div class="titleBox">'
		htmlStr += '<i class=""></i>';
	
		if(node.lession){
			htmlStr += '<span><input type="hidden" name="lessonId" value="'+node.id+'">'+node.text+'</span>';
		}else{
			htmlStr += '<span>'+node.text+'</span>';
		}
	
		htmlStr += '</div>';
	}else{
    	htmlStr += '<div class="lastItem showItems">';
   		htmlStr += '<div >';
    	htmlStr += '<p>';
    	
    	if(node.lession){
			htmlStr += '<span><input type="hidden" name="lessonId" value="'+node.id+'">'+node.text+'</span>';
		}else{
			htmlStr += '<span>'+node.text+'</span>';
		}

    	htmlStr += '</p>';
    	htmlStr += '</div>';
	}

	if(subTree.length>0){
		for(var i=0;i<subTree.length;i++){
		   var sub = subTree[i];
		   htmlStr += addHtml(sub);
		}
	}
	htmlStr += '</div>';
	return htmlStr;
}

$(function () {
		var len=$('.sample-docs').children().size();
		console.log(len)
        var pageNum=0;
        if(len%2==1){
            pageNum=len+3
		}else{
            pageNum=len+2
		}
        function loadApp() {
            var flipbook = $('.sample-docs');
            // Check if the CSS was already loaded
            if (flipbook.width()==0 || flipbook.height()==0) {
                setTimeout(loadApp, 10);
                return;
            }
            // 滑块组件
            $( "#slider" ).slider({
                min: 1,
                max: Infinity,
                start: function(event, ui) {
                    if (!window._thumbPreview) {
                        _thumbPreview = $('<div />', {'class': 'thumbnail'}).html('<div></div>');
                        setPreview(ui.value,pageNum);
                        _thumbPreview.appendTo($(ui.handle));
                    } else
                        setPreview(ui.value,pageNum);
                    moveBar(false);
                },

                slide: function(event, ui) {
                    // setPreview(ui.value,pageNum);
                },

                stop: function() {
                    if (window._thumbPreview)
                        _thumbPreview.removeClass('show');
                    $('.sample-docs').turn('page', Math.max(1, $(this).slider('value')));
                }
            });
            // URIs
            Hash.on('^page\/([0-9]*)$', {
                yep: function(path, parts) {
                    var page = parts[1];
                    if (page!==undefined) {
                        if ($('.sample-docs').turn('is'))
                            $('.sample-docs').turn('page', page);
                    }
                },
                nop: function(path) {
                    if (!$('.sample-docs').turn('is'))
                        $('.sample-docs').turn('page', 1);
                }
            });
            // 键盘翻页事件
            $(document).keydown(function(e){
                var previous = 37, next = 39;
                switch (e.keyCode) {
                    case previous:
                        $('.sample-docs').turn('previous');
                        break;
                    case next:
                        $('.sample-docs').turn('next');
                        break;
                }
            });
            // 创建书本
            flipbook.turn({
                elevation: 50,
                acceleration: false,
                gradients: true,
                autoCenter: true,
                duration: 1000,
                pages: pageNum,
                when: {
                    turning: function(e, page, view) {
                        Hash.go('page/'+page).update();
                    },
                    turned: function(e, page, view) {
                        var book = $(this);
						$('#slider').slider('value', getViewNumber(book, view[1]));
                        book.turn('center');
                    },
                    start: function(e, pageObj) {
                        moveBar(true);
						$('.sample-docs').find('img').each(function () {
							$(this).attr('src',$(this).data('src'))
							$(this).load(function () {
								$(this).prev().children().remove()
                            })
                        })
                    },
                    end: function(e, pageObj) {
                        var book = $(this);
                        /*setTimeout(function() {
							$('#slider').slider('value', getViewNumber(book));
                        }, 1);*/
                        moveBar(false);
                    },
                    missing: function (e, pages) {
                        for (var i = 0; i < pages.length; i++)
                            addPage(pages[i], $(this));
                    }
                }
            }). turn('page', 2);
            $('#slider').slider('option', 'max', flipbook.turn('pages'));
            flipbook.addClass('animated');

            //左右翻页
            $('[name=next]').on('click',function () {
                flipbook.turn('next')
            })
            $('[name=prev]').on('click',function () {
                flipbook.turn('previous')
            })

			//目录&&分享显示隐藏
            $('[name=edit]').on('click',function () {
                $(this).next().toggle()
            })
			$('[name=close]').on('click',function () {
				$(this).parents('.catalogue_box').hide()
            })
            //全屏
            $('[name=fullScreen]').on('click',function () {
                var de = document.documentElement;
                if (de.requestFullscreen) {
                    de.requestFullscreen();
                    $(this).removeClass('icon-full-screen').addClass('icon-full-screen-exit')
                } else if (de.mozRequestFullScreen) {
                    de.mozRequestFullScreen();
                    $(this).removeClass('icon-full-screen').addClass('icon-full-screen-exit')
                } else if (de.webkitRequestFullScreen) {
                    de.webkitRequestFullScreen();
                    $(this).removeClass('icon-full-screen').addClass('icon-full-screen-exit')
                }else if (de.msRequestFullscreen) {
                    de.msRequestFullscreen();
                    $(this).removeClass('icon-full-screen').addClass('icon-full-screen-exit')
                }else{
                    alert('您的浏览器不支持全屏API 请按F11全屏体验')
                }
                if(document.mozFullScreen||document.webkitIsFullScreen||document.msFullscreenElement){
                    if (document.exitFullscreen) {
                        document.exitFullscreen();
                        $(this).removeClass('icon-full-screen-exit').addClass('icon-full-screen')
                    } else if (document.mozCancelFullScreen) {
                        document.mozCancelFullScreen();
                        $(this).removeClass('icon-full-screen-exit').addClass('icon-full-screen')
                    } else if (document.webkitCancelFullScreen) {
                        document.webkitCancelFullScreen();
                        $(this).removeClass('icon-full-screen-exit').addClass('icon-full-screen')
                    }
                }
            })

            // Show canvas

            $('#canvas').css({visibility: 'visible'});
        }
		// Hide canvas
        $('#canvas').css({visibility: 'hidden'});
        loadApp()

	})
	
	$(function(){
			// 新增*选中和弹窗
			function diaLog(a, tit, width, fn) {
				var dialog = art.dialog({
					title: tit,
					content: a[0],   //弹出框的内容
					width: width,                 //弹出框的宽度
					drag: true,                     //弹出框是否可移动，默认为false
					lock: true,                   //弹出框是否可移动，默认为true,只当有lock为true时，才出有背景的出现
					//maskClick:false,
					initialize: fn
					//自定义函数
				});
				return dialog;
			}

			$('.bds_dialog').on('click', function () {
				var a = $('#newBuildPop');
				var dialog = diaLog(a, '分享 ', '430px', function () {
					var That = this;
					var btnTue = a.find("a[true]");
					var btnFalse = a.find('a[false]');
					btnTue && btnTue.off('click');
					btnTue && btnTue.on('click', function () {
					
						if($("#textBookListSize").val()==0){
						    dialog.close();
							return false;
						}
					
						if(lessonId==''){
							alert('请选择分享的课时!')
							return false;
						}
					
						var pages=[];
					
						$(".input_check").each(function(){
							 if($(this).prop("checked")==true){
							 		pages.push($(this).val())
						   	  } 
				        });
						
						
						if(pages.length==0){
							alert("请选择分享的页面！");
							return false;
						}
						
					
					
						$.ajax({
				            async : false,
				            cache : false,
				            traditional: true,
				            type : 'POST',
				            data : {
				                pushedId : pushedId,
				                userName : userName,
				                noteType :noteType,
				                planId : planId,
				                textBookId : textBookId,
				                textBookName : textBookName,
				                planId : planId,
				                userId : userId,
				                loginPlatformCode : loginPlatformCode,
				                noteId : noteId,
				                lessonId : lessonId,
				                pushedId : pushedId,
				                pageUrl : pages
				            },
				            url : "synLessonRes",// 请求的action路径
				            error : function() {// 请求失败处理函数
				            },
				            success : function(data) {
				               art.tips({
									type: 1,
									content: '成功！',
									time: '1000',
									lock: true//设置为false的时候没有背景
							   })
				            }
				        });
					
						dialog.close();
					});
					btnFalse && btnFalse.on('click', function () {
						
						dialog.close();
					});
				})
			})

			/*多选项下拉框 || 普通下拉框 begin*/
		$.fn.selectsDropper = function () {
			var _me = $(this);
			var titName = _me.find('.titName');
			var dropCon = _me.find('.dropCon');
			titName.unbind().on('click', function (e) {
				e.stopPropagation();
				var cur = this;
				$('.titName').each(function (index) {
					if (this !== cur) {
						$(this).parents('.selectsWrap').removeClass('show');
					}
					;
				});
				if (_me.hasClass('show')) {
					_me.removeClass('show');
				} else {
					_me.addClass('show');
				}
				;
			});
			if (dropCon.find('label').length <= 0) {
				if (dropCon.find('a.cur').length > 0) {
					var val = dropCon.find('a.cur').attr('value');
					var txt = dropCon.find('a.cur').text();
					titName.children('input:hidden').val(val);
					titName.children('span').text(txt);
				}
				;
				dropCon.on('click', 'a', function (e) {
					
					textBookId = $(this).attr('textBookId');
					textBookName =  $(this).attr('textBookName');
					planId = $(this).attr('planId');
					
					//console.log($(this).attr('textBookId'));
					//console.log($(this).attr('textBookName'));
					//console.log($(this).attr('planId'));
					//console.log($(this).attr('pushedId'));

					var val = $(this).attr('value');
					var txt = $(this).text();
					titName.children('input:hidden').val(val);
					titName.children('span').text(txt);
					_me.removeClass('show');
				});
			}
			;
			dropCon.on('click', function (e) {
				pushedId = $("#pushedId").val();
				var loginPlatformCode = $("#loginPlatformCode").val();
				
				var htmlStr = '';
				
				$.ajax({
					type : "POST",
					url : "lessonTree",
					data : {"loginPlatformCode" : loginPlatformCode,"pushedId" : pushedId},
					async : false,
					dataType : 'json',
					success : function(data) {
						
						if(data != null && data.list != null && data.list.length > 0){
							
							htmlStr += '<dl>';
							
							for(var i=0;i<data.list.length;i++){
								var obj = data.list[i];
								//console.log(obj)
								
								htmlStr += '<dd>';
								htmlStr += '<div class="mainliTitle">';
								htmlStr += '<div class="titleBox">';
								htmlStr += '<i class=""></i>';
								htmlStr += '<span>'+obj.text+'</span>';
								htmlStr += '</div>';

								var sub = obj.subTree;
								
								if(obj.subTree.length>0){
									for(var j=0;j<obj.subTree.length;j++){
									   var sub = obj.subTree[j];
									   htmlStr += addHtml(sub);
									}
								}
								
								htmlStr += '</div>';
								htmlStr += '<dd>';
							}
							htmlStr += '</dl>';
						}
					}
				});
				
				$('.mainli').html(htmlStr);
			
				e.stopPropagation();
			});
			$(document).on('click', function () {
				_me.removeClass('show');
			});
		};
		$('.selectsWrap').each(function () {
			$(this).selectsDropper();
		});
		/*多选项下拉框 || 普通下拉框 end*/


		$(document).on("click",".mainliTitle> .titleBox",function(){
		
			var changeLessonId  = $(this).find("input[name='lessonId']").val();
		
			//alert(changeLessonId);
		
			if(changeLessonId == undefined){
				lessonId = '';
			}else{
				lessonId = changeLessonId;
			}
		
			//console.log(lessonId)
		
			$(this).parent().find('.showItems').eq(0).slideToggle().siblings('.showItems').slideToggle()
			if($(this).find('i').hasClass('on')){
				$(this).find('i').removeClass('on')
			}else{
				$(this).find('i').addClass('on')
			}
			
			//$(".mainli").children().each(function(){
			//	console.log(this)
			//	$(this).removeClass('active');
			//});
			
			$(".active").removeClass('active');
			
			$(this).addClass('active')
		})


		$(document).on("click",".mainli .showItems p",function(){
			$(".active").removeClass('active');
			//$('.mainli p').removeClass('active')
			$(this).addClass('active')
			
			var changeLessonId  = $(this).find("input[name='lessonId']").val();
		
			//alert(changeLessonId);
		
			if(changeLessonId == undefined){
				lessonId = '';
			}else{
				lessonId = changeLessonId;
			}
			//console.log(lessonId)
		})

        //$('.mainliTitle> .titleBox').click(function(){
		//	$(this).parent().find('.showItems').eq(0).slideToggle().siblings('.showItems').slideToggle()
		//	if($(this).find('i').hasClass('on')){
		//		$(this).find('i').removeClass('on')
		//	}else{
		//		$(this).find('i').addClass('on')
		//	}
		//})
		//$('.mainli .showItem p').click(function(){
		//	$('.mainli p').removeClass('active')
		//	$(this).addClass('active')
		//})


        // 点击分享弹窗
        $('.bds_teach').on('click', function () {
            var dialog = art.dialog({
                title:'分享',
                content:$(".gs_teachingShare_dialog")[0],
                okValue:"确定",
                cancelValue:"取消",
                cancel:true,
                initialize:function(){
					$("#taskList").empty();
                    taskList();
                },
                ok:function() {

                }
            })
        });

        termList();
        function termList(){
            $("#termList").empty();
            $("#gettaskmodel").empty();
            $.ajax({
                url:"${base}/myMnote/getShareTerm",
                type:"post",
                data:{
                    url:"http://kcsq.t.huijiaoyun.com/index.php?r=api/jyglapi/termlist",
                    userId:"${userId}",
                    loginPlatformCode:"${loginPlatformCode}"
                },
                dataType:"json",
                success:function (data) {
                    if(data.result=='000000'){
                        if(data.data.length>0){
                            var html ='<p class="gs_selectArrow">'+data.data[0].title+'</p>' +
									'<input type="hidden" id ="termid" value=\"'+data.data[0].id+'\">'+
                                    '                <ul class="gs_selectDropList">'
                            for(var i = 0;i<data.data.length;i++){
                                html +='<li><a href="javascript:;">'+data.data[i].title+'</a></li>'
                            }
                            html +='                </ul>'
                            console.log(html)
                            $("#termList").append(html);
                        }

                    }
                    gettaskmodel()
                }
            });
        }

        function gettaskmodel(){
            $.ajax({
                url:"${base}/myMnote/getShareTerm",
                type:"post",
                data:{
                    url:"http://kcsq.t.huijiaoyun.com/index.php?r=api/jyglapi/gettaskmodel",
                    userId:"${userId}",
                    loginPlatformCode:"${loginPlatformCode}"
                },
                dataType:"json",
                success:function (data) {
                    if(data.result=='000000'){
                        if(data.data.length>0){
                            var html ='<p class="gs_selectArrow">'+data.data[0].title+'</p>' +
                                    '<input type="hidden" id ="typeid" value=\''+data.data[0].typeid+'\' />' +
                                    '                <ul class="gs_selectDropList">'
                            for(var i = 0;i<data.data.length;i++){
                                html +='<li><a href="javascript:;">'+data.data[i].title+'</a><ul>'
                                if(typeof(data.data[i].content_type) != "undefined" ){
                                    for(var j = 0;j<data.data[i].content_type.length;j++){
                                        html +='<li><a href="javascript:;">'+data.data[i].content_type[j]+'</a></li>'
                                    }
                                }
                                html +='</ul></li>'
                            }
                            html +='                </ul>'
                            console.log(html)
                            $("#gettaskmodel").append(html);
                        }
                    }
                }
            })
        }

        function taskList(){
            $.ajax({
                url:"${base}/myMnote/getShareTitleList",
                type:"post",
                data:{
                    url:"http://kcsq.t.huijiaoyun.com/index.php?r=api/jyglapi/TaskList",
                    userId:"${userId}",
                    termid:$('#termid').val(),
                    typeid:$('#typeid').val()
                },
                dataType:"json",
                success:function (data) {
                    if(data.result=='000000'){
						if(data.data.length>0){
						    var html ='';
						    for(var i =0;i<data.data.length;i++){
                                 html +='<li>' +
                                        '                    <i class="icon_checkbox"></i>' +
                                        '                    <div class="gs_projectDetail">' +
                                        '                        <h5>'+data.data[i].title+'</h5>' +
                                        '                        <p class="gs_projectTxt">'+data.data[i].star_time+'至'+data.data[i].end_time+'</p>' +
                                        '                        <p class="gs_projectTxt">主持人：'+data.data[i].uname+'</p>' +
                                        '                        <p class="gs_projectTxt">地点：'+data.data[i].address+'</p>' +
                                        '                    </div>' +
                                        '                    <em class="icon_state over">已结束</em>' +
                                        '                </li>'
							}
                            $("#taskList").add(html);
						}
					}
                }
            });
		}

        // 分享弹框复选框样式切换
        $('.gs_teachingShare_dialog').on('click','.icon_checkbox',function () {
            $(this).toggleClass('checked');
        });
        // 分享下拉显示隐藏
        $('.gs_teachingShare_dialog .gs_selecDrop').on('click','.gs_selectArrow',function () {
            if($(this).siblings('.gs_selectDropList').css('display') == 'none'){
                $('.gs_selectDropList').hide();
			}
            $(this).siblings('.gs_selectDropList').toggle();
        });
        $('#termList').on('click','li',function () {
            var $this = $(this);
            var txt = $this.find('a').text();
            $this.parents('.gs_selectDropList').siblings('.gs_selectArrow').text(txt);
            $this.parent('.gs_selectDropList').hide();
        });

        $('#gettaskmodel').on('click','li',function (e) {
            var $this = $(this);
            var txt = $this.children('a').text();
            var txt2 = $this.parent('ul').siblings('a').text();
            if(txt2!=null && txt2!=''){
                txt =txt2+"->"+txt
			}
            e.stopPropagation();
            $this.parents('.gs_selectDropList').siblings('.gs_selectArrow').text(txt);
            $this.parents('.gs_selectDropList').hide();
        });

        $('.gs_teachingShare_dialog').on('click',function (e) {
            if($(e.target)[0].className != 'gs_selectArrow') {
                $('.gs_selectDropList').hide();
            }
        });
	})
</script>
<!--百度分享 begin-->
<script>
    window._bd_share_config = {
        common : {
            bdText : '自定义分享内容',
            bdDesc : '自定义分享摘要',
            bdUrl : '自定义分享url地址',
            bdPic : '自定义分享图片'
        },
        share : [{
            "bdSize" : 24
        }],
    }
    with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)];
</script>
<!--百度分享 end-->
</body>
</html>