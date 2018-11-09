<#assign base=request.contextPath />
<!doctype html>
<!--[if IE 9 ]>    <html  class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html> <!--<![endif]-->
<head>
<meta HTTP-EQUIV="expires" CONTENT="300">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="../css/jquery.ui.css">
	<link rel="stylesheet" href="../css/jquery.ui.html4.css">
	<link rel="stylesheet" href="../css/docs.css">
	<title>墨香笔记阅读器</title>
</head>
<body>

<#assign numberchangeToChinese = "com.whty.mxbj.common.utils.freemarker.NumberChangeToChineseMethod"?new()/>
<div class="g_wrap">
	<#if title =="1"><div class="book_info"></div></#if>
	<div id="canvas">
		<#if title =="2"><div class="book_info saved"></div></#if>
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
							<div class="share_box dis_none">
								<h2>分享到</h2>
								<div class="bdsharebuttonbox" data-tag="share_1">
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
				<div class="hard ">
					<div class="gradient"><div class="loader"></div></div>
					<img src="" data-src="${coverImageUrl}" alt="" width="471" height="600" />
				</div>
				<div class="hard page_cover">
				</div>
				<!--封面 end-->
				<!--书页内容 begin-->

				<#list pageList as page>
					<div>
						<div class="gradient"><div class="loader"></div></div>
						<img src="" data-src="${page.pageFileUrl}" alt="" width="471" height="600">
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
</div>
<script type="text/javascript" src="../js/jquery.min.1.7.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.20.custom.min.js"></script>
<script type="text/javascript" src="../js/turn.js"></script>
<script type="text/javascript" src="../js/modernizr.2.5.3.min.js"></script>
<script type="text/javascript" src="../js/hash.js"></script>
<script type="text/javascript" src="../js/docs.js"></script>
<script type="text/javascript">
	$(function () {
	    var len=$('.sample-docs').children().size();
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
                    setPreview(ui.value,pageNum);
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
</script>
<!--百度分享 begin-->
<script>
    window._bd_share_config = {
        common : {
            bdText : '墨香笔记',
            bdDesc : '墨香笔记',
            bdUrl : document.URL,
            bdPic : 'http://mxbj.huijiaoyun.com/mxbj/images/index/logo.png'
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